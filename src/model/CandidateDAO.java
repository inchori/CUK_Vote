package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author 이동현
 * 후보자를 나타내는 DAO입니다.
 */
public class CandidateDAO {
   private Connection conn;
   private Statement stmt;
   private ResultSet rs;
   
   public CandidateDAO() throws ClassNotFoundException {
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
   public int insert(CandidateDTO candidateinfo) {
      try {
         getConnection();
         this.stmt = conn.createStatement();
         String query = "insert into candidateinfo (studentID, no, commitment, carreer, getvote, votekind, imgpath) " + "values ('%d', '%d', '%s', '%s', '%d', '%s', '%s')";
         String command=String.format(query, candidateinfo.getStudentID(), candidateinfo.getNo(), candidateinfo.getCommitment(), candidateinfo.getCarreer(),
               candidateinfo.getGetVote(), candidateinfo.getVoteKind(), candidateinfo.getImgPath());
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
         String query="select * from candidateinfo where studentID='%s';";
         String command=String.format(query, ID);
         rs=stmt.executeQuery(command);
         if(rs.next()||ID.equals("")) return 0; //이미 존재
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
   
   public void change(CandidateDTO candidateinfo) throws Exception{
      getConnection();
      this.stmt=conn.createStatement();
      String query="update candidateinfo set studentID:='%s', no:='%s', commitment:='%s', career:='%s', getvote:='%d', votekind:='%s', imgpath:='%s'";
      String command=String.format(query, candidateinfo.getStudentID(), candidateinfo.getNo(), candidateinfo.getCommitment(), candidateinfo.getCarreer(), 
            candidateinfo.getGetVote(), candidateinfo.getVoteKind(), candidateinfo.getImgPath());
      int rowNum=stmt.executeUpdate(command);
      if(rowNum<1)
         throw new Exception("데이터를 DB에 입력할 수 없습니다.");
      close(this.conn,this.stmt,this.rs);
   }
   
   public void delete(String key) throws Exception {
	      getConnection();
	      this.stmt=conn.createStatement();
	      String query="delete from candidateinfo where voteKind ='%s'";
	      String command=String.format(query,key);
	      int rowNum=stmt.executeUpdate(command);
	      if(rowNum<1)
	         throw new Exception("데이터를 삭제할 수 없습니다.");
	      close(this.conn,this.stmt,this.rs);
	   }
   
   public CandidateDTO load(String kind, int seqno) throws Exception{
      getConnection();
      this.stmt=conn.createStatement();
      CandidateDTO result=new CandidateDTO();
      String query="select * from candidateinfo where voteKind = '" + kind + "' and no = '" + seqno + "';";
      this.rs=stmt.executeQuery(query);
      if(!this.rs.next())
         throw new Exception("해당하는 데이터가 없습니다.");
      result.setStudentID(rs.getInt("studentID"));
      result.setNo(rs.getInt("no"));
      result.setCommitment(rs.getString("commitment"));
      result.setCarreer(rs.getString("carreer"));
      result.setGetVote(rs.getInt("getVote"));
      result.setVoteKind(rs.getString("voteKind"));
      result.setImgPath(rs.getString("imgPath"));
      close(this.conn,this.stmt,this.rs);
      return result;
   }
   
   public CandidateDTO loadForBoard(String kind, int seqno) throws Exception{
	      getConnection();
	      this.stmt=conn.createStatement();
	      CandidateDTO result=new CandidateDTO();
	      String query="select * from candidateinfo where voteKind = '" + kind + "' and no = '" + seqno + "';";
	      this.rs=stmt.executeQuery(query);
	      if(!this.rs.next())
	         throw new Exception("해당하는 데이터가 없습니다.");
	      result.setStudentID(rs.getInt("studentID"));
	      result.setNo(rs.getInt("no"));
	      result.setCommitment(rs.getString("commitment"));
	      result.setCarreer(rs.getString("carreer"));
	      result.setGetVote(rs.getInt("getVote"));
	      result.setVoteKind(rs.getString("voteKind"));
	      result.setImgPath(rs.getString("imgPath"));
	      
	      
	      result.setCommitment(result.getCommitment().replace("\r\n", "<br>"));
	      result.setCarreer(result.getCarreer().replace("\r\n", "<br>"));
	      close(this.conn,this.stmt,this.rs);
	      return result;
   }
   
	public int Count(String kind) throws Exception{
		getConnection();
		int count = 0;
		this.stmt = conn.createStatement();
		String query = "select * from candidateinfo where voteKind = '" + kind + "';";
		this.rs = stmt.executeQuery(query);
		while(this.rs.next()) {
			count++;
		}
		close(this.conn,this.stmt,this.rs);
		return count;
	}
	
	public void getVote(CandidateDTO candidate) throws Exception {
		getConnection();
	    this.stmt=conn.createStatement();
	    
	    String query="update candidateinfo set getVote='%d' where voteKind = '%s' and no = '%d';";
	    String command=String.format(query, candidate.getGetVote()+1, candidate.getVoteKind(), candidate.getNo());
	    int rowNum=stmt.executeUpdate(command);
	    if(rowNum<1)
	    	throw new Exception("데이터를 DB에 입력할 수 없습니다.");
	    close(this.conn,this.stmt,this.rs);
	}
	
	public ArrayList<CandidateDTO> loadList(String key) throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		ArrayList<CandidateDTO> result = new ArrayList<CandidateDTO>();
		String query = "select * from candidateinfo where voteKind='" + key +"';";
		this.rs = stmt.executeQuery(query);
		if(!this.rs.next())
			throw new Exception("투표종류(" + ")에 해당하는 데이터가 없습니다.");
		do {
			CandidateDTO candidate = new CandidateDTO();
			candidate.setStudentID(rs.getInt("studentID"));
			candidate.setNo(rs.getInt("no"));
			candidate.setCommitment(rs.getString("commitment"));
			candidate.setCarreer(rs.getString("carreer"));
			candidate.setGetVote(rs.getInt("getVote"));
			candidate.setVoteKind(rs.getString("voteKind"));
			candidate.setImgPath(rs.getString("imgPath"));
			result.add(candidate);
		}while(rs.next());
	    close(this.conn,this.stmt,this.rs);
		return result;
	}
}