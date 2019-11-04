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
 * Servlet implementation class VoteApplyViewServlet
 */
public class VoteApplyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginedID") != null) {
			int pageNumber=1;
			
			if(request.getParameter("pageNumber")!=null)
				pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			
			VoteApplyInfoDAO voteApplyInfoDAO = null;
			try {
				 voteApplyInfoDAO = new VoteApplyInfoDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<VoteApplyInfoDTO> voteApplyList = new ArrayList<VoteApplyInfoDTO>();
			try {
				voteApplyList = voteApplyInfoDAO.getList(pageNumber);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			boolean nextPage=voteApplyInfoDAO.nextPage(pageNumber+1);
//			
//			Date date = new Date();
//		    java.sql.Date sentDate = new java.sql.Date(date.getTime());
//		    
//			
//			for(VoteApplyInfoDTO voteApplyInfo : voteApplyList) {
//				Calendar voteCalTime = Calendar.getInstance();
//			    voteCalTime.setTime(voteApplyInfo.getEndTime());
//			     
//			    Calendar voteCalDate = Calendar.getInstance();
//			    voteCalDate.setTime(voteApplyInfo.getEndDate());
//			    voteCalDate.set(Calendar.HOUR_OF_DAY, voteCalTime.get(Calendar.HOUR_OF_DAY));
//			    voteCalDate.set(Calendar.MINUTE, voteCalTime.get(Calendar.MINUTE));
//			    voteCalDate.set(Calendar.SECOND, voteCalTime.get(Calendar.SECOND));
//			     
//			    Date date2 = voteCalDate.getTime();
//			    java.sql.Date voteDate = new java.sql.Date(date2.getTime());
//			    
//			    if(sentDate.after(voteDate)){
//			    	
//			    	try {
//			    		voteApplyInfoDAO.delete(voteApplyInfo.getVoteKind());
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			    	
//			    	voteApplyList.remove(voteApplyInfo);
//			    }
//			}
			
			RequestDispatcher dispatcher = null;
			request.setAttribute("voteApplyList", voteApplyList);
			request.setAttribute("nextPage", nextPage);
			request.setAttribute("pageNumber", pageNumber);
			dispatcher = context.getRequestDispatcher("/voteApply.jsp");
			dispatcher.forward(request, response);
		}
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "로그인을 먼저 해주세요");
			response.sendRedirect("login.jsp");
		}
	}

}
