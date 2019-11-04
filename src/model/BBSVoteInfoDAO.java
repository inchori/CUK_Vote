package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BBSVoteInfoDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private void getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webvotedb", "root", "student");
		if(conn==null)
			throw new Exception("데이터베이스에 연결할 수 없습니다.");
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}

	public int insert(BBSVoteInfoDTO bbsVoteInfo) { //게시물 등록
		try {
			getConnection();
			String query="insert into bbsvoteinfo (seqno, voteKind, endDate, endTime, candidate1, getVote1 ,candidate2, getVote2) "
					+ "values ('%d', '%s', '%s', '%s','%s',0, '%s', 0);";
			String command=String.format(query, bbsVoteInfo.getSeqno(), bbsVoteInfo.getVoteKind(), bbsVoteInfo.getEndDate(), bbsVoteInfo.getEndTime(), bbsVoteInfo.getCandidate1(), bbsVoteInfo.getCandidate2());
			stmt = conn.createStatement();
			stmt.executeUpdate(command);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(conn, stmt, rs);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public void delete(int key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "delete from bbsvoteinfo where seqno = '%d'";
		String command = String.format(query, key);
		int rowNum = stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 삭제할 수 없습니다.");
		close(this.conn, this.stmt, this.rs);
	}

	public BBSVoteInfoDTO load(int key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		BBSVoteInfoDTO result = new BBSVoteInfoDTO();
		String query = "select * from bbsvoteinfo where seqno = '" + key + "';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("투표종류(" + ")에 해당하는 데이터가 없습니다.");
		result.setVoteKind(rs.getString("voteKind"));
		result.setSeqno(rs.getInt("seqno"));
		result.setCandidate1(rs.getString("candidate1"));
		result.setCandidate2(rs.getString("candidate2"));
		result.setEndDate(rs.getDate("endDate"));
		result.setEndTime(rs.getTime("endTime"));
		result.setGetVote1(rs.getInt("getVote1"));
		result.setGetVote2(rs.getInt("getVote2"));
		return result;
	}
	
	public void getVote1(BBSVoteInfoDTO bbsVoteInfo) throws Exception {
		getConnection();
	    this.stmt=conn.createStatement();
	    String query="update bbsvoteinfo set getVote1:='%d' where voteKind = '%s' and seqno = '%d';";
	    String command=String.format(query, bbsVoteInfo.getGetVote1()+1, bbsVoteInfo.getVoteKind(), bbsVoteInfo.getSeqno());
	    int rowNum=stmt.executeUpdate(command);
	    if(rowNum<1)
	    	throw new Exception("데이터를 DB에 입력할 수 없습니다.");
	    close(this.conn,this.stmt,this.rs);
	}
	
	public void getVote2(BBSVoteInfoDTO bbsVoteInfo) throws Exception {
		getConnection();
	    this.stmt=conn.createStatement();
	    String query="update bbsvoteinfo set getVote2:='%d' where voteKind = '%s' and seqno = '%d';";
	    String command=String.format(query, bbsVoteInfo.getGetVote2()+1, bbsVoteInfo.getVoteKind(), bbsVoteInfo.getSeqno());
	    int rowNum=stmt.executeUpdate(command);
	    if(rowNum<1)
	    	throw new Exception("데이터를 DB에 입력할 수 없습니다.");
	    close(this.conn,this.stmt,this.rs);
	}
}
