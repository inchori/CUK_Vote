package VoteControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class BBsVoteServlet
 */
public class BBsVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		
		StudentInfoDAO studentInfoDAO = null;
		try {
			studentInfoDAO = new StudentInfoDAO();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BBSVoteInfoDAO bbsVoteInfoDAO = null;
		bbsVoteInfoDAO = new BBSVoteInfoDAO();
		
		
		VotedInfoDAO votedInfoDAO = null;
		try {
			votedInfoDAO = new VotedInfoDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StudentInfoDTO student = null;
		try {
			student = studentInfoDAO.load((String)session.getAttribute("loginedID"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BBSVoteInfoDTO bbsVoteInfo = new BBSVoteInfoDTO();
		try {
			bbsVoteInfo = bbsVoteInfoDAO.load(Integer.parseInt(request.getParameter("boardNo")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("candidateChoice") != null) {
			VotedInfoDTO votedInfo = new VotedInfoDTO();
		
			votedInfo.setStudentID(student.getStudentID());
			votedInfo.setCandidateNo(Integer.parseInt(request.getParameter("candidateChoice")));
			votedInfo.setVoteKind(request.getParameter("boardNo"));
			
			
			
			if(request.getParameter("candidateChoice").equals("1")) {
				try {
					bbsVoteInfoDAO.getVote1(bbsVoteInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(request.getParameter("candidateChoice").equals("2")) {
				try {
					bbsVoteInfoDAO.getVote2(bbsVoteInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			votedInfoDAO.insert(votedInfo);
			bbsVoteInfoDAO.insert(bbsVoteInfo);
			
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "투표를  했습니다.");
			response.sendRedirect("./bbs?boardNo=" + bbsVoteInfo.getSeqno());
		}
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "선택을 하고 버튼을 눌러주세요.");
			response.sendRedirect("./bbs?boardNo=" + bbsVoteInfo.getSeqno());
		}
	}

}
