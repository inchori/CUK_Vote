package VoteControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class DeleteVoteApplyServlet
 */
public class DeleteVoteApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginedID").equals("master")) {
			
			VoteApplyInfoDAO voteApplyInfoDAO = null;
			try {
				voteApplyInfoDAO = new VoteApplyInfoDAO();
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
				voteApplyInfoDAO.delete(request.getParameter("voteKind"));
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
			
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", request.getParameter("voteKind") +  "을(를) 삭제하였습니다.");
			response.sendRedirect("./voteApplyView");
		}
		
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "삭제시킬 권한이 없습니다.");
			response.sendRedirect("./voteApplyView");
		}
	}

}
