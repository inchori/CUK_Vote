package VoteControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class DeleteAfterTimeVoteServlet
 */
public class DeleteAfterTimeVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginedID").equals("master")) {
			try {
				VoteDAO voteDAO = new VoteDAO();
				
				EndVoteDAO endVoteDAO = new EndVoteDAO();
				
				VotedInfoDAO votedInfoDAO = new VotedInfoDAO();
				
				ArrayList<VoteDTO> voteList = voteDAO.loadList();
				
				
				for(VoteDTO voteInfo : voteList) {
					Date date = new Date();
				    java.sql.Date sentDate = new java.sql.Date(date.getTime());
				    
				    Calendar voteCalTime = Calendar.getInstance();
				    voteCalTime.setTime(voteInfo.getEndTime());
				     
				    Calendar voteCalDate = Calendar.getInstance();
				    voteCalDate.setTime(voteInfo.getEndDate());
				    voteCalDate.set(Calendar.HOUR_OF_DAY, voteCalTime.get(Calendar.HOUR_OF_DAY));
				    voteCalDate.set(Calendar.MINUTE, voteCalTime.get(Calendar.MINUTE));
				    voteCalDate.set(Calendar.SECOND, voteCalTime.get(Calendar.SECOND));
				     
				    Date date2 = voteCalDate.getTime();
				    java.sql.Date voteDate = new java.sql.Date(date2.getTime());
				    
				    if(sentDate.after(voteDate)) {
				    	
				    	
				    	EndVoteDTO endVoteInfo = new EndVoteDTO();
				    	
				    	endVoteInfo.setVoteKind(voteInfo.getVoteKind());
				    	endVoteInfo.setEndDate(voteInfo.getEndDate());
				    	endVoteInfo.setEndTime(voteInfo.getEndTime());
				    	endVoteInfo.setGetVote(votedInfoDAO.voteCount(voteInfo.getVoteKind()));
				    	
				    	votedInfoDAO.delete(voteInfo.getVoteKind());
				    	
				    	endVoteDAO.insert(endVoteInfo);
				    	
				    	voteDAO.delete(voteInfo.getVoteKind());
				    }
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "시간이 지난 투표들을 모두 삭제하였습니다.");
			response.sendRedirect("./voteKindView");
		}
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "삭제시킬 권한이 없습니다.");
			response.sendRedirect("./voteKindView");
		}
	}

}
