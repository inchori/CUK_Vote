package BBSControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BBSDAO;
import model.BBSDTO;

public class BBSUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public BBSUpdateServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        try {
        	BBSDAO bbsDAO = new BBSDAO();
			BBSDTO bbsDTO = bbsDAO.read(boardNo);
		    request.setAttribute("BBS", bbsDTO);
		    request.setAttribute("boardNo",boardNo);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbsupdate.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
	    String content = request.getParameter("content");
        try {
        	BBSDAO bbsDAO = new BBSDAO();
			bbsDAO.change(boardNo, title, content);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        response.sendRedirect("bbslist");
	}
}
