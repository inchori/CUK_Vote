package VoteControl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class EndVoteViewServlet
 */
public class EndVoteViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();

		if(session.getAttribute("loginedID") != null) {

			try {

				int pageNumber = 1;

				if(request.getParameter("pageNumber")!=null)
					pageNumber=Integer.parseInt(request.getParameter("pageNumber"));

				EndVoteDAO endVoteDAO = new EndVoteDAO();
				
				boolean nextPage=endVoteDAO.nextPage(pageNumber+1);
				
				ArrayList<EndVoteDTO> endVoteList = endVoteDAO.getList(pageNumber);
				
				RequestDispatcher dispatcher = null;
				request.setAttribute("endVoteList", endVoteList);
				request.setAttribute("nextPage", nextPage);
				request.setAttribute("pageNumber", pageNumber);
				dispatcher = context.getRequestDispatcher("/endVoteBoard.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "로그인을 먼저 해주세요");
			response.sendRedirect("login.jsp");
		}
	}

}
