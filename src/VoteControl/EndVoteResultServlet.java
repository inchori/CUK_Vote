package VoteControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class EndVoteResultServlet
 */
public class EndVoteResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();
		String voteKind = request.getParameter("voteKind");	
		
		if(session.getAttribute("loginedID") != null) {
			
			CandidateDAO candidateDAO = null;
			try {
				candidateDAO = new CandidateDAO();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			EndVoteDTO endVote = new EndVoteDTO();
			
			EndVoteDAO endVoteDAO = null;
			try {
				 endVoteDAO = new EndVoteDAO();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				endVote = endVoteDAO.load(voteKind);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int count = 0;
			try {
				count = candidateDAO.Count(voteKind);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
			
			for(int i = 1; i<= count; i++) {
				CandidateDTO candidate = new CandidateDTO();
				
				try {
					candidate = candidateDAO.load(voteKind, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				candidateList.add(candidate);
			}
			
			RequestDispatcher dispatcher = null;
			request.setAttribute("candidateList", candidateList);
			request.setAttribute("endVote", endVote);
			dispatcher = context.getRequestDispatcher("/endVoteResult.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "로그인을 먼저 해주세요");
			response.sendRedirect("login.jsp");
		}
	}

}
