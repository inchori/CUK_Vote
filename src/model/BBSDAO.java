package model;

import java.sql.*;
import java.util.Date;


public class BBSDAO {

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

	public int insert(BBSDTO bbsdto) { //게시물 등록
		try {
			Date date=new Date();
			java.sql.Date bbsDate=new java.sql.Date(date.getTime());
			java.sql.Time bbsTime=new java.sql.Time(date.getTime());
			getConnection();
			String query="insert into boardinfo (seqno, title, content, writer, date, time ,step) "
					+ "values ('%s', '%s', '%s', '%s','%s','%s', 0);";
			String command=String.format(query, bbsdto.getSeqno(), bbsdto.getTitle(), bbsdto.getContent(), bbsdto.getWriter(), bbsDate, bbsTime, 0);
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

	public BBSDTO read(int seqNo) throws Exception{ //게시물 불러오기
		getConnection();
		this.stmt=conn.createStatement();
		BBSDTO bbsDTO = new BBSDTO();
		String query="select * from boardinfo where seqno = "+ seqNo +";";
		this.rs=stmt.executeQuery(query);

		if(rs.next()) {
			bbsDTO.setSeqno(rs.getInt("seqno"));
			bbsDTO.setTitle(rs.getString("title"));
			bbsDTO.setWriter(rs.getString("Writer"));
			bbsDTO.setDate(rs.getDate("date"));
			bbsDTO.setTime(rs.getTime("time"));
			bbsDTO.setContent(rs.getString("content"));
			bbsDTO.setStep(rs.getInt("step"));
		}
		close(this.conn,this.stmt,this.rs);
		return bbsDTO;
	}
	
	public BBSDTO read(String title, String writer) throws Exception{ //게시물 불러오기
		getConnection();
		this.stmt=conn.createStatement();
		BBSDTO bbsDTO = new BBSDTO();
		String query="select * from boardinfo where title = '"+ title +"' and writer = '" + writer + "';";
		this.rs=stmt.executeQuery(query);

		if(rs.next()) {
			bbsDTO.setSeqno(rs.getInt("seqno"));
			bbsDTO.setTitle(rs.getString("title"));
			bbsDTO.setWriter(rs.getString("Writer"));
			bbsDTO.setDate(rs.getDate("date"));
			bbsDTO.setTime(rs.getTime("time"));
			bbsDTO.setContent(rs.getString("content"));
			bbsDTO.setStep(rs.getInt("step"));
		}
		close(this.conn,this.stmt,this.rs);
		return bbsDTO;
	}

	public void step(int seqNo) throws Exception {
		getConnection();
		this.stmt = conn.createStatement();
		String query="update boardinfo set step = step + 1 WHERE seqno = '"+ seqNo +"';";
		int rowNum=stmt.executeUpdate(query);
		if(rowNum < 1)
			throw new Exception("조회 수의 변화가 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}

	public BBSList load(int upperSeqNo) throws Exception{ //게시물 리스트 불러오기
		getConnection();
		this.stmt=conn.createStatement();
		BBSList bbsList = new BBSList();
		String query="select * from boardinfo where seqno < '"+ upperSeqNo +"' order by seqno desc;";
		this.rs=stmt.executeQuery(query);

		for (int cnt = 0; cnt < 15; cnt++) {
			if (!rs.next())
				break;
			bbsList.setSeqNo(cnt, rs.getInt("seqno"));
			bbsList.setTitle(cnt, rs.getString("title"));
			bbsList.setWriter(cnt, rs.getString("Writer"));
			bbsList.setDate(cnt, rs.getDate("date"));
			bbsList.setTime(cnt, rs.getTime("time"));
			bbsList.setStep(cnt, rs.getInt("step"));
		}
		if (!rs.next())
			bbsList.setLastPage(true);
		close(this.conn,this.stmt,this.rs);	
		return bbsList;
	}
	
	
	public int lastSeqNo() throws Exception{
		getConnection();
		this.stmt = conn.createStatement();
		String query = "select count(*) from boardinfo;";
		ResultSet rs = stmt.executeQuery(query);
		int lastSeqNo = 0;
		while(rs.next()) 
			lastSeqNo = rs.getInt("count(*)");
		return lastSeqNo;
	}

	public void change(int seqNo, String title, String content) throws Exception { //게시물 수정
		getConnection();
		this.stmt=conn.createStatement();
		String query = "UPDATE boardinfo set title = '" + title + "', content = '" + content + "' where seqno = '" + seqNo + "';";
		int rowNum=stmt.executeUpdate(query);
		if(rowNum<1)
			throw new Exception("데이터를 수정할 수 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}

	public void delete(int seqno) throws Exception{ //게시물 삭제
		getConnection();
		this.stmt=conn.createStatement();
		String query="delete from boardinfo where seqno ='%d'";
		String command=String.format(query,seqno);
		int rowNum=stmt.executeUpdate(command);
		String query2 = "alter table boardinfo auto_increment = 1;";
		stmt.executeUpdate(query2);
		String query3 = "set @cnt=0;";
		stmt.executeUpdate(query3);
		String query4 = "update boardinfo set seqno=@cnt:=@cnt+1";
		stmt.executeUpdate(query4);
		if(rowNum<1)
			throw new Exception("데이터를 삭제할 수 없습니다.");
		close(this.conn,this.stmt,this.rs);
	}  
}