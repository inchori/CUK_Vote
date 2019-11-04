package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EndVoteDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public EndVoteDAO() throws ClassNotFoundException {
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
	
	public int insertCheck(String voteKind) {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select * from endvoteinfo where voteKind='%s';";
			String command=String.format(query, voteKind);
			rs=stmt.executeQuery(command);
			if(rs.next()) return 0; //이미 존재
			else return 1; //그 데이터 없음
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
		return -1; //데이터베이스 오류
	}

	public void change(EndVoteDTO endVoteInfo) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "update endvoteinfo set endDate:='%s', endTime:='%s', getVote:='%d' where voteKind='%s';";
		String command = String.format(query, endVoteInfo.getEndDate(), endVoteInfo.getEndTime(), endVoteInfo.getGetVote(), endVoteInfo.getVoteKind());
		int rowNum = stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 DB에 입력할 수 없습니다.");
		close(this.conn, this.stmt, this.rs);
	}
	
	public int delete(String key){
		try{
			getConnection();
			this.stmt=conn.createStatement();
			String query = "delete from endvoteinfo where voteKind = '%s'";
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

	public EndVoteDTO load(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		EndVoteDTO result = new EndVoteDTO();
		String query = "select * from endvoteinfo where voteKind = '" + key + "';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("투표종류(" + ")에 해당하는 데이터가 없습니다.");
		result.setVoteKind(rs.getString("voteKind"));
		result.setEndDate(rs.getDate("endDate"));
		result.setEndTime(rs.getTime("endTime"));
		result.setGetVote(rs.getInt("getVote"));
		return result;
	}
	
	public ArrayList<EndVoteDTO> loadList() throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		ArrayList<EndVoteDTO> result = new ArrayList<EndVoteDTO>();
		String query = "select * from endvoteinfo;";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("투표종류(" + ")에 해당하는 데이터가 없습니다.");
		do {
			EndVoteDTO endVote = new EndVoteDTO();
			endVote.setVoteKind(rs.getString("voteKind"));
			endVote.setEndDate(rs.getDate("endDate"));
			endVote.setEndTime(rs.getTime("endTime"));
			endVote.setGetVote(rs.getInt("getVote"));
			result.add(endVote);
		}while(rs.next());
		return result;
	}
	
	public int getNext() {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select seqno from endvoteinfo order by seqno desc;";
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
	
	public int insert(EndVoteDTO endVoteInfo){
		try {
			String query="insert into endvoteinfo (seqno, voteKind, endDate, endTime, getVote) "
					+ "values ('%s', '%s', '%s', '%s', '%s');";
			String result=String.valueOf(getNext());
			getConnection();
			this.stmt=conn.createStatement();
			if(result=="-1") return -1;
			String command=String.format(query, result, endVoteInfo.getVoteKind(), endVoteInfo.getEndDate(), endVoteInfo.getEndTime(), endVoteInfo.getGetVote());
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
	
	public ArrayList<EndVoteDTO> getList(int pageNumber) {
		ArrayList<EndVoteDTO> arr=new ArrayList<EndVoteDTO>();
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from endvoteinfo where seqno<'%s' order by seqno desc limit 10;";
			int result=getNext()-(pageNumber-1)*10;
			String command=String.format(query, Integer.toString(result));
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			while(rs.next()){
				EndVoteDTO endVote = new EndVoteDTO();
				endVote.setVoteKind(rs.getString("voteKind"));
				endVote.setEndDate(rs.getDate("endDate"));
				endVote.setEndTime(rs.getTime("endTime"));
				endVote.setGetVote(rs.getInt("getVote"));
				arr.add(endVote);
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
	
	public boolean nextPage(int pageNumber) {
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from endvoteinfo where seqno<'%d' order by seqno desc limit 10;";
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
