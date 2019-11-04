package VoteControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class ApplyVoteServlet
 */
public class ApplyVoteServlet extends HttpServlet {
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
			
			VoteApplyInfoDTO voteApplyInfo = new VoteApplyInfoDTO();
			try {
				voteApplyInfo = voteApplyInfoDAO.load(request.getParameter("voteKind"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			VoteDAO voteDAO = null;
			try {
				voteDAO = new VoteDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			VoteDTO vote = new VoteDTO();
			
			vote.setVoteKind(voteApplyInfo.getVoteKind());
			vote.setStartDate(voteApplyInfo.getStartDate());
			vote.setStartTime(voteApplyInfo.getStartTime());
			vote.setEndDate(voteApplyInfo.getEndDate());
			vote.setEndTime(voteApplyInfo.getEndTime());
			
			voteDAO.insert(vote);
			
			try {
				voteApplyInfoDAO.delete(request.getParameter("voteKind"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", request.getParameter("voteKind") + "을(를) 시작 시켰습니다.");
			response.sendRedirect("./voteApplyView");
			
		}
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "시작시킬 권한이 없습니다.");
			response.sendRedirect("./voteApplyView");
		}
	}

}
