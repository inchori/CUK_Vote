package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VotedInfoDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public VotedInfoDAO() throws ClassNotFoundException {
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
	
	public int insert(VotedInfoDTO votedInfo) {
		try {
			getConnection();
			this.stmt = conn.createStatement();
			String query = "insert into votedinfo (voteKind, studentID, candidateNo) " + "values ('%s', '%d', '%d');";
			String command = String.format(query, votedInfo.getVoteKind(), votedInfo.getStudentID(), votedInfo.getCandidateNo());
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
		return -1;
	}
	
	public int insertCheck(int studentID, String voteKind) {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select * from votedinfo where studentID='%d' and voteKind='%s';";
			String command=String.format(query, studentID, voteKind);
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

	public void change(VotedInfoDTO votedInfo) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "update votedinfo set voteKind:='%s', studentID:='%s', candidateNo:='%s';";
		String command = String.format(query, votedInfo.getVoteKind(), votedInfo.getStudentID(), votedInfo.getCandidateNo());
		int rowNum = stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 DB에 입력할 수 없습니다.");
		close(this.conn, this.stmt, this.rs);
	}
	

	public void delete(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "delete from votedinfo where voteKind = '%s'";
		String command = String.format(query, key);
		int rowNum = stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 삭제할 수 없습니다.");
		close(this.conn, this.stmt, this.rs);
	}

	public VotedInfoDTO load(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		VotedInfoDTO result = new VotedInfoDTO();
		String query = "select * from votedinfo where voteKind = '" + key + "';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("투표종류(" + ")에 해당하는 데이터가 없습니다.");
		result.setVoteKind(rs.getString("voteKind"));
		result.setStudentID(rs.getInt("studentID"));
		result.setCandidateNo(rs.getInt("candidateNo"));
		return result;
	}
	
	public boolean voteCheck(String voteKind, int studentID) throws Exception {
		getConnection();
		this.stmt = conn.createStatement();
		String query = "select * from votedinfo where voteKind = '" + voteKind + "' and studentID='" + studentID + "';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			return false; // 투표 못함
		else 
			return true; // 중복 투표
	}
	
	public int voteCount (String voteKind) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		int count = 0;
		String query = "select * from votedinfo where voteKind = '" + voteKind + "';";
		this.rs = stmt.executeQuery(query);
		while(rs.next()){
			count++;
		};
		return count;
	}
}
