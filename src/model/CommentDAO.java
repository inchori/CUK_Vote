package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class CommentDAO {
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
	public int insert(int boardNo, String id, Date comdate, Time comTime, String content){
		try {
			getConnection();
			this.stmt=conn.createStatement();
			String query="insert into comment (no, boardno, id, comDate, comTime, content) "
					+ "values ('%s', '%s', '%s', '%s', '%s', '%s');";

			String command=String.format(query, 0, boardNo, id, comdate, comTime, content);
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
	public void delete(int key) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		String query="delete from comment where no ='%d'";
		String command=String.format(query,key);
		int rowNum=stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 삭제할 수 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}
	public void BBSdelete(int key) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		String query="delete from comment where boardno ='%d'";
		String command=String.format(query,key);
		int rowNum=stmt.executeUpdate(command);
		if(rowNum<1)
			throw new Exception("데이터를 삭제할 수 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}
	public CommentListDTO load(int key, int upperSeqNo) throws Exception{
		getConnection();
		this.stmt=conn.createStatement();
		CommentListDTO commentListDTO = new CommentListDTO();
		String query="select * from comment where boardno = '" + key +"'and no < '"+ upperSeqNo +"' order by no asc;";
		this.rs=stmt.executeQuery(query);
		
		for (int cnt = 0; cnt < 10000; cnt++) {
		    if (!rs.next())
		          break;
		    commentListDTO.setCommentNoList(cnt, rs.getInt("no"));
			commentListDTO.setBoardNoList(cnt, rs.getInt("boardno"));
			commentListDTO.setIdList(cnt, rs.getString("id"));
			commentListDTO.setCommentDateList(cnt, rs.getDate("comDate"));
			commentListDTO.setCommentTimeList(cnt, rs.getTime("comTime"));
			commentListDTO.setContent(cnt, rs.getString("content"));
		}
		if (!rs.next())
			commentListDTO.setLastPage(true);
		close(this.conn,this.stmt,this.rs);	
		return commentListDTO;
	}
}
