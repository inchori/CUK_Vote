package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VoteDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public VoteDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	private void getConnection() throws Exception {
		this.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/webvotedb", "root", "student");
		if(conn==null)
			throw new Exception("데이터베이스에 연결할 수 없습니다.");
	}
	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
	
	public int insertCheck(String votekind) {
		try {
			getConnection();
			this.stmt = conn.createStatement();
			String query = "select * from voteinfo where voteKind='%s';";
			String command = String.format(query, votekind);
			rs = stmt.executeQuery(command);
			if(rs.next()||votekind.equals("")) return 0;
			else
				return 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				close(conn, stmt, rs);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public void change(VoteDTO voteinfo) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "update voteinfo set startDate:='%s', startTime:='%s', endDate:='%s', endTime:='%s' where voteKind='%s';";
		String command = String.format(query, voteinfo.getStartDate(), voteinfo.getStartTime(), voteinfo.getEndDate(), voteinfo.getEndTime(), voteinfo.getVoteKind());
		int rowNum = stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 DB에 입력할 수 없습니다.");
		close(this.conn, this.stmt, this.rs);
	}
	
	public int delete(String key){
		try{
			getConnection();
			this.stmt=conn.createStatement();
			String query = "delete from voteinfo where voteKind = '%s'";
			String command = String.format(query, key);
			int result=stmt.executeUpdate(command);
			if(result==0) return 0;
			String query2 = "alter table voteinfo auto_increment=1;";
			stmt.executeUpdate(query2);
			String query3 = "set @cnt=0;";
			stmt.executeUpdate(query3);
			String query4 = "update voteinfo set seqno=@cnt:=@cnt+1;";
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
		return -1; //데이터베이스 오류;
	}

	public VoteDTO load(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		VoteDTO result = new VoteDTO();
		String query = "select * from voteinfo where voteKind = '" + key + "';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("투표종류(" + ")에 해당하는 데이터가 없습니다.");
		result.setVoteKind(rs.getString("voteKind"));
		result.setStartDate(rs.getDate("startDate"));
		result.setStartTime(rs.getTime("startTime"));
		result.setEndDate(rs.getDate("endDate"));
		result.setEndTime(rs.getTime("endTime"));
		return result;
	}
	

	public ArrayList<VoteDTO> loadList() throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		ArrayList<VoteDTO> result = new ArrayList<VoteDTO>();
		String query = "select * from voteinfo;";
		this.rs = stmt.executeQuery(query);
		while(rs.next()){
			VoteDTO voteKind = new VoteDTO();
			voteKind.setSeqno(rs.getInt("seqno"));
			voteKind.setVoteKind(rs.getString("voteKind"));
			voteKind.setStartDate(rs.getDate("startDate"));
			voteKind.setStartTime(rs.getTime("startTime"));
			voteKind.setEndDate(rs.getDate("endDate"));
			voteKind.setEndTime(rs.getTime("endTime"));
			result.add(voteKind);
		};
		close(this.conn,this.stmt,this.rs);
		return result;
	}
	
	
	public int getNext() {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select seqno from voteinfo order by seqno desc;";
			String command=String.format(query);
			rs=stmt.executeQuery(command);
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//그 아이디의 첫번쨰 쪽지라면
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(conn,stmt,rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//데이터 베이스 오류
	}

	public ArrayList<VoteDTO> getList(int pageNumber) {
		ArrayList<VoteDTO> arr=new ArrayList<VoteDTO>();
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from voteinfo where seqno<'%s' order by seqno desc limit 10;";
			int result=getNext()-(pageNumber-1)*10;
			String command=String.format(query, Integer.toString(result));
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			while(rs.next()){
				VoteDTO voteInfo = new VoteDTO();
				voteInfo.setSeqno(rs.getInt("seqno"));
				voteInfo.setVoteKind(rs.getString("voteKind"));
				voteInfo.setStartDate(rs.getDate("startDate"));
				voteInfo.setStartTime(rs.getTime("startTime"));
				voteInfo.setEndDate(rs.getDate("endDate"));
				voteInfo.setEndTime(rs.getTime("endTime"));
				arr.add(voteInfo);
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
	

	public int insert(VoteDTO voteInfo){
		try {
			String query="insert into voteinfo (seqno, voteKind, startDate, startTime, endDate, endTime) "
					+ "values ('%s', '%s', '%s', '%s', '%s', '%s');";
			String result=String.valueOf(getNext());
			getConnection();
			this.stmt=conn.createStatement();
			if(result=="-1") return -1;
			String command=String.format(query, result, voteInfo.getVoteKind(), voteInfo.getStartDate(), voteInfo.getStartTime(), voteInfo.getEndDate(), voteInfo.getEndTime());
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
		return -1;//데이터베이스 오류
	}
	

	public boolean nextPage(int pageNumber) {
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from voteinfo where seqno<'%d' order by seqno desc limit 10;";
			int result=getNext()-(pageNumber-1)*10;
			String command=String.format(query, result);
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			if(rs.next()) {
				//결과가 하나라도 존재하면
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
