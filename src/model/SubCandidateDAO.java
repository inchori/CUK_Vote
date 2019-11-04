package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubCandidateDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public SubCandidateDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}
	private void getConnection() throws Exception {
		this.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/webvotedb", "root", "student");
		if(conn==null)
			throw new Exception("데이터베이스에 연결할 수 없습니다.");
	}
	
	
	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
	      if(rs!=null)rs.close();
	      if(stmt!=null)stmt.close();
	      if(conn!=null)conn.close();
   }
	
		
	public int insert(SubCandidateDTO subcandidateinfo) {     
		try {
			getConnection();
			this.stmt = conn.createStatement();
			String query = "insert into subcandidateinfo (studentID, no, carreer, voteKind, imgPath)" 
					+ "values ('%d', '%d', '%s', '%s', '%s')";
			String command=String.format(query, subcandidateinfo.getStudentID(), subcandidateinfo.getNo(), subcandidateinfo.getCarreer(),
					subcandidateinfo.getVoteKind(), subcandidateinfo.getImgPath());
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
	   
	      
	public int insertCheck(String ID) {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select * from subcandidateinfo where studentID='%s';";
			String command=String.format(query, ID);
			rs=stmt.executeQuery(command);
			if(rs.next()||ID.equals("")) return 0; //이미 존재
			else return 1; //그 데이터 없음
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				close(conn, stmt, rs);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1; //데이터베이스 오류
	}
	   
	   
	public void change(SubCandidateDTO Subcandidateinfo) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		String query="update subcandidateinfo set studentID:='%s', no:='%s', career:='%s', votekind:='%s', imgpath:='%s'";
		String command=String.format(query, Subcandidateinfo.getStudentID(), Subcandidateinfo.getNo(), Subcandidateinfo.getCarreer(), 
				Subcandidateinfo.getVoteKind(), Subcandidateinfo.getImgPath());
		int rowNum=stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 DB에 입력할 수 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}
	   	   
	   
	public void delete(String key) throws Exception {
		getConnection();
		this.stmt=conn.createStatement();
		String query="delete from subcandidateinfo where voteKind ='%s'";
		String command=String.format(query,key);
		int rowNum=stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 삭제할 수 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}
	   
   
	public SubCandidateDTO load(String kind, int seqno) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		SubCandidateDTO result = new SubCandidateDTO();
		String query="select * from subcandidateinfo where voteKind = '" + kind + "' and no = '" + seqno + "';";
		this.rs=stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("해당하는 학번(" + kind + ")에 데이터가 없습니다.");
		result.setStudentID(rs.getInt("studentID"));
		result.setNo(rs.getInt("no"));
		result.setCarreer(rs.getString("carreer"));
		result.setVoteKind(rs.getString("votekind"));
		result.setImgPath(rs.getString("imgpath"));
		close(this.conn,this.stmt,this.rs);
		return result;
	}
	
	public SubCandidateDTO loadForBoard(String kind, int seqno) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		SubCandidateDTO result = new SubCandidateDTO();
		String query="select * from subcandidateinfo where voteKind = '" + kind + "' and no = '" + seqno + "';";
		this.rs=stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("해당하는 학번(" + kind + ")에 데이터가 없습니다.");
		result.setStudentID(rs.getInt("studentID"));
		result.setNo(rs.getInt("no"));
		result.setCarreer(rs.getString("carreer"));
		result.setVoteKind(rs.getString("votekind"));
		result.setImgPath(rs.getString("imgpath"));
		
	    result.setCarreer(result.getCarreer().replace("\r\n", "<br>"));
	    
		close(this.conn,this.stmt,this.rs);
		return result;
	}
	
	public ArrayList<SubCandidateDTO> loadList(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		ArrayList<SubCandidateDTO> result = new ArrayList<SubCandidateDTO>();
		String query = "select * from subcandidateinfo where voteKind='" + key +"';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			return result;
		do {
			SubCandidateDTO subCandidate = new SubCandidateDTO();
			subCandidate.setStudentID(rs.getInt("studentID"));
			subCandidate.setNo(rs.getInt("no"));
			subCandidate.setCarreer(rs.getString("carreer"));
			subCandidate.setVoteKind(rs.getString("votekind"));
			subCandidate.setImgPath(rs.getString("imgpath"));
			result.add(subCandidate);
		}while(rs.next());
	    close(this.conn,this.stmt,this.rs);
		return result;
	}
}
