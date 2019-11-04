package VoteControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class VoteServlet
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		
		String candidateChoice = request.getParameter("candidateChoice");
		
		if(candidateChoice == null || candidateChoice.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "후보를 고르지 않았습니다.");
			response.sendRedirect("./voteView?voteKind=" + request.getParameter("voteKind"));
			return;
		}
		
		StudentInfoDAO studentInfoDAO = null;
		try {
			studentInfoDAO = new StudentInfoDAO();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CandidateDAO candidateDAO = null;
		try {
			candidateDAO = new CandidateDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CandidateDTO candidate = new CandidateDTO();
		if(Integer.parseInt(request.getParameter("candidateChoice")) != 0) {
			try {
				candidate = candidateDAO.load(request.getParameter("voteKind"), Integer.parseInt(request.getParameter("candidateChoice")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				candidateDAO.getVote(candidate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		VotedInfoDAO votedInfoDAO = null;
		try {
			votedInfoDAO = new VotedInfoDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		StudentInfoDTO student = null;
		try {
			student = studentInfoDAO.load((String)session.getAttribute("loginedID"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		VotedInfoDTO votedInfo = new VotedInfoDTO();
		
		votedInfo.setStudentID(student.getStudentID());
		votedInfo.setCandidateNo(Integer.parseInt(request.getParameter("candidateChoice")));
		votedInfo.setVoteKind(request.getParameter("voteKind"));
		
		if(votedInfoDAO.insertCheck(votedInfo.getStudentID(), votedInfo.getVoteKind()) == 1) {
			try {
				candidateDAO.getVote(candidate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		votedInfoDAO.insert(votedInfo);
		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "투표가 되었습니다.");
		response.sendRedirect("./voteKindView");
	}

}
