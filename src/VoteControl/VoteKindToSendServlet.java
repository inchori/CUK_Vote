package VoteControl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class VoteKindToSendServlet
 */
public class VoteKindToSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		String voteKind = request.getParameter("voteKind");
		String isStartDate = request.getParameter("startDate");
		String isStartTime = request.getParameter("startTime");
		String isEndDate = request.getParameter("endDate");
		String isEndtTime = request.getParameter("endTime");
		String candidateNum = request.getParameter("candidateNum");

		try {
			if(voteKind == null || voteKind.equals("") || isStartDate == null || isStartDate.equals("") || isStartTime == null || isStartTime.equals("")|| isEndDate == null || isEndDate.equals("") || isEndtTime == null || isEndtTime.equals("") || candidateNum == null || candidateNum.equals("")) {
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "모두 다 입력해주세요.");	
				response.sendRedirect("./voteKindApply.jsp");
				return;
			}

			EndVoteDAO endVoteDAO = new EndVoteDAO();
			VoteApplyInfoDAO voteApplyInfoDAO = new VoteApplyInfoDAO();
			VoteDAO voteDAO = new VoteDAO();

			if(endVoteDAO.insertCheck(voteKind) == 0 || voteApplyInfoDAO.insertCheck(voteKind) == 0 || voteDAO.insertCheck(voteKind) == 0) {
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "모두 다 입력해주세요.");	
				response.sendRedirect("./voteKindApply.jsp");
				return;
			}
			
			if(session.getAttribute("loginedID") != null){
				VoteApplyInfoDTO voteApplyInfo = new VoteApplyInfoDTO();
				
				voteApplyInfo.setVoteKind(request.getParameter("voteKind"));
				
				voteApplyInfo.setVoteKind(request.getParameter("voteKind"));

				java.util.Date utilStartDate = null;
				try {
					utilStartDate = dateFormat.parse(request.getParameter("startDate"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Date startDate = new java.sql.Date(utilStartDate.getTime());
				voteApplyInfo.setStartDate(startDate);

				java.sql.Time startTime = null;
				try {
					startTime = new java.sql.Time(timeFormat.parse(request.getParameter("startTime")).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				voteApplyInfo.setStartTime(startTime);

				java.util.Date utilEndDate = null;
				try {
					utilEndDate = dateFormat.parse(request.getParameter("endDate"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				java.sql.Date endDate = new java.sql.Date(utilEndDate.getTime());
				voteApplyInfo.setEndDate(endDate);

				java.sql.Time endTime = null;
				try {
					endTime = new java.sql.Time(timeFormat.parse(request.getParameter("endTime")).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				voteApplyInfo.setEndTime(endTime);

				voteApplyInfo.setWriter((String)session.getAttribute("loginedID"));
				
				session.setAttribute("candidateNum", request.getParameter("candidateNum"));
				session.setAttribute("voteApplyInfo", voteApplyInfo);
				response.sendRedirect("./addVoteApply.jsp");
			}
			else {
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "로그인을 먼저 해주세요");
				response.sendRedirect("login.jsp");
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
