package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VoteApplyInfoDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public VoteApplyInfoDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	private void getConnection() throws Exception {
		this.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/webvotedb", "root", "student");
		if(conn==null)
			throw new Exception("�����ͺ��̽��� ������ �� �����ϴ�.");
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}

	public void change(VoteApplyInfoDTO voteApplyInfo) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "update voteapplyinfo set startDate:='%s', startTime:='%s', endDate:='%s', endTime:='%s', writer:='%s' where voteKind='%s';";
		String command = String.format(query, voteApplyInfo.getStartDate(), voteApplyInfo.getStartTime(), voteApplyInfo.getEndDate(), voteApplyInfo.getEndTime(), voteApplyInfo.getVoteKind(), voteApplyInfo.getWriter());
		int rowNum = stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("�����͸� DB�� �Է��� �� �����ϴ�.");
		close(this.conn, this.stmt, this.rs);
	}
	
	public int insertCheck(String voteKind) {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select * from voteapplyinfo where voteKind='%s';";
			String command=String.format(query, voteKind);
			rs=stmt.executeQuery(command);
			if(rs.next()) return 0; //�̹� ����
			else return 1; //�� ������ ����
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1; //�����ͺ��̽� ����
	}

	public int delete(String key){
		try{
			getConnection();
			this.stmt=conn.createStatement();
			String query = "delete from voteapplyinfo where voteKind = '%s'";
			String command = String.format(query, key);
			int result=stmt.executeUpdate(command);
			if(result==0) return 0;
			String query2 = "alter table voteapplyinfo auto_increment=1;";
			stmt.executeUpdate(query2);
			String query3 = "set @cnt=0;";
			stmt.executeUpdate(query3);
			String query4 = "update voteapplyinfo set seqno=@cnt:=@cnt+1;";
			String command2=String.format(query4);
			stmt.executeUpdate(command2);
			if(result==1) return 1;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				close(conn,stmt,rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1; //�����ͺ��̽� ����;
	}

	public VoteApplyInfoDTO load(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		VoteApplyInfoDTO result = new VoteApplyInfoDTO();
		String query = "select * from voteapplyinfo where voteKind = '" + key + "';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("��ǥ����(" + ")�� �ش��ϴ� �����Ͱ� �����ϴ�.");
		result.setVoteKind(rs.getString("voteKind"));
		result.setStartDate(rs.getDate("startDate"));
		result.setStartTime(rs.getTime("startTime"));
		result.setEndDate(rs.getDate("endDate"));
		result.setEndTime(rs.getTime("endTime"));
		result.setWriter(rs.getString("writer"));
		return result;
	}


	public ArrayList<VoteApplyInfoDTO> getList(int pageNumber) {
		ArrayList<VoteApplyInfoDTO> arr=new ArrayList<VoteApplyInfoDTO>();
		try {
			//������������ �� �����´�. asc�� ��������
			String query="select * from voteapplyinfo where seqno<'%s' order by seqno desc limit 10;";
			int result=getNext()-(pageNumber-1)*10;
			String command=String.format(query, Integer.toString(result));
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			while(rs.next()){
				VoteApplyInfoDTO voteApplyInfo = new VoteApplyInfoDTO();
				voteApplyInfo.setSeqno(rs.getInt("seqno"));
				voteApplyInfo.setVoteKind(rs.getString("voteKind"));
				voteApplyInfo.setStartDate(rs.getDate("startDate"));
				voteApplyInfo.setStartTime(rs.getTime("startTime"));
				voteApplyInfo.setEndDate(rs.getDate("endDate"));
				voteApplyInfo.setEndTime(rs.getTime("endTime"));
				voteApplyInfo.setWriter(rs.getString("writer"));
				arr.add(voteApplyInfo);
			};
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn,stmt,rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return arr;		
	}

	public int getNext() {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select seqno from voteapplyinfo order by seqno desc;";
			String command=String.format(query);
			rs=stmt.executeQuery(command);
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//�� ���̵��� ù���� �������
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn,stmt,rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//������ ���̽� ����
	}

	public int insert(VoteApplyInfoDTO voteApplyInfo){
		try {
			String query="insert into voteapplyinfo (seqno, voteKind, startDate, startTime, endDate, endTime, writer) "
					+ "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s');";
			String result=String.valueOf(getNext());
			getConnection();
			this.stmt=conn.createStatement();
			if(result=="-1") return -1;
			String command=String.format(query, result, voteApplyInfo.getVoteKind(), voteApplyInfo.getStartDate(), voteApplyInfo.getStartTime(), voteApplyInfo.getEndDate(), voteApplyInfo.getEndTime(), voteApplyInfo.getWriter());
			return stmt.executeUpdate(command);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				close(conn,stmt,rs);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//�����ͺ��̽� ����
	}

	public boolean nextPage(int pageNumber) {
		try {
			//������������ �� �����´�. asc�� ��������
			String query="select * from voteapplyinfo where seqno<'%d' order by seqno desc limit 10;";
			int result=getNext()-(pageNumber-1)*10;
			String command=String.format(query, result);
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			if(rs.next()) {
				//����� �ϳ��� �����ϸ�
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn,stmt,rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;		
	}
}
