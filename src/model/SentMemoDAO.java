package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SentMemoDAO {
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	public SentMemoDAO() throws ClassNotFoundException {
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
//	public int delete(int seqno) throws Exception{
//		try{
//			getConnection();
//			this.stmt=conn.createStatement();
//			String query="delete from memoinfo where seqno ='%d'";
//			String command=String.format(query, seqno);
//			int rowNum=stmt.executeUpdate(command);
//			String query2="alter table memoinfo auto_increment=1";
//			stmt.executeUpdate(query2);
//			String query3="set @cnt=0";
//			stmt.executeUpdate(query3);
//			String query4="update memoinfo set seqno=@cnt:=@cnt+1";
//			stmt.executeUpdate(query4);
//			if(rowNum<1)
//				return 0; //데이터를 삭제할 수 없음
//			else
//				return 1; //데이터를 삭제하였음
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally {
//			close(conn,stmt,rs);
//		}
//		return -1; //데이터베이스 오류;
//	}
	
	public int delete(String seqno, String ID){
		try{
			getConnection();
			this.stmt=conn.createStatement();
			String query="delete from sentmemoinfo where seqno ='%s' and sent_id='%s';";
			String command=String.format(query, seqno, ID);
			int result=stmt.executeUpdate(command);
			if(result==0) return 0;
			String query2 = "alter table sentmemoinfo auto_increment=1;";
			stmt.executeUpdate(query2);
			String query3 = "set @cnt=0;";
			stmt.executeUpdate(query3);
			String query4 = "update sentmemoinfo set seqno=@cnt:=@cnt+1 where sent_id='%s';";
			String command2=String.format(query4, ID);
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
	//param 바꿀속성이랑 속성값
	
	public SentMemoDTO loadFromDB(String realseqno) {
		SentMemoDTO memo=new SentMemoDTO();
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from sentmemoinfo where realseqno='%s';";
			String command=String.format(query, realseqno);
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			if(rs.next()){
				memo.setRealseqno(rs.getInt("realseqno"));
				memo.setSeqno(rs.getInt("seqno"));
				memo.setRecv_id(rs.getString("recv_id"));
				memo.setSent_id(rs.getString("sent_id"));
				memo.setTitle(rs.getString("title"));
				memo.setContent(rs.getString("content"));
				memo.setSentDate(rs.getDate("sentDate"));
				memo.setSentTime(rs.getTime("sentTime"));
				memo.setReadDate(rs.getDate("readDate"));
				memo.setReadTime(rs.getTime("readTime"));
				memo.setRead(rs.getString("read"));
				
				
				memo.setContent(memo.getContent().replace("\r\n", "<br>"));
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
		return memo;		
	}
	public ArrayList<SentMemoDTO> getList(int pageNumber, String ID) {
		ArrayList<SentMemoDTO> arr=new ArrayList<SentMemoDTO>();
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from sentmemoinfo where sent_id='%s' and seqno<'%s' order by seqno desc limit 10;";
			int result=getNext(ID)-(pageNumber-1)*10;
			String command=String.format(query, ID, Integer.toString(result));
			getConnection();
			this.stmt=conn.createStatement();
			this.rs=stmt.executeQuery(command);
			while(rs.next()){
				SentMemoDTO memo=new SentMemoDTO();
				memo.setRealseqno(rs.getInt("realseqno"));
				memo.setSeqno(rs.getInt("seqno"));
				memo.setRecv_id(rs.getString("recv_id"));
				memo.setSent_id(rs.getString("sent_id"));
				memo.setTitle(rs.getString("title"));
				memo.setContent(rs.getString("content"));
				memo.setSentDate(rs.getDate("sentDate"));
				memo.setSentTime(rs.getTime("sentTime"));
				memo.setReadDate(rs.getDate("readDate"));
				memo.setReadTime(rs.getTime("readTime"));
				memo.setRead(rs.getString("read"));
				arr.add(memo);
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
	public int getNext(String ID) {
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="select seqno from sentmemoinfo where sent_id='%s' order by seqno desc;";
			String command=String.format(query, ID);
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
	public int insert(SentMemoDTO memoinfo){
		try {
			String query="insert into sentmemoinfo (seqno, sent_id, recv_id, title, content, sentDate, sentTime) "
					+ "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s');";
			String result=String.valueOf(getNext(memoinfo.getSent_id()));
			getConnection();
			this.stmt=conn.createStatement();
			if(result=="-1") return -1;
			String command=String.format(query, result, memoinfo.getSent_id(),memoinfo.getRecv_id(), memoinfo.getTitle(),memoinfo.getContent(),memoinfo.getSentDate(),memoinfo.getSentTime());
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
	
	public boolean nextPage(int pageNumber, String ID) {
		try {
			//내림차순으로 다 가져온다. asc는 오름차순
			String query="select * from sentmemoinfo where sent_id='%s' and seqno<'%d' order by seqno desc limit 10;";
			int result=getNext(ID)-(pageNumber-1)*10;
			String command=String.format(query, ID, result);
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
