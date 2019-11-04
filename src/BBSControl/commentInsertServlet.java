package BBSControl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;
import model.CommentListDTO;

public class commentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public commentInsertServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
	    String content = request.getParameter("content");
	    String id = request.getParameter("id");
	    int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	    Date date=new Date();
	    java.sql.Date sentDate=new java.sql.Date(date.getTime());
	    java.sql.Time sentTime=new java.sql.Time(date.getTime());

	    try {
			CommentDAO commentListDAO = new CommentDAO();
			commentListDAO.insert(boardNo,id, sentDate, sentTime, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    response.sendRedirect("bbs?boardNo="+boardNo);
	}
}
