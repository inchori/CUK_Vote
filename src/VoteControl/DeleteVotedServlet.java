package VoteControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class DeleteVotedServlet
 */
public class DeleteVotedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginedID").equals("master")) {
			EndVoteDAO endVoteDAO = null;
			try {
				endVoteDAO = new EndVoteDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CandidateDAO candidateDAO = null;
			try {
				candidateDAO = new CandidateDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SubCandidateDAO subCandidateDAO = null;
			try {
				subCandidateDAO = new SubCandidateDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				endVoteDAO.delete(request.getParameter("voteKind"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				candidateDAO.delete(request.getParameter("voteKind"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				subCandidateDAO.delete(request.getParameter("voteKind"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("messageType", "���� �޽���");
			session.setAttribute("messageContent", request.getParameter("voteKind") +  "��(��) �����Ͽ����ϴ�.");
			response.sendRedirect("./endVoteView");
		}
		
		else {
			session.setAttribute("messageType", "���� �޽���");
			session.setAttribute("messageContent", "������ų ������ �����ϴ�.");
			response.sendRedirect("./endVoteView");
		}
	}

}
