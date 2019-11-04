package VoteControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class AllVoteNumViewServlet
 */
public class AllVoteNumViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		
		try {
			VoteDAO voteDAO = new VoteDAO();
			
			ArrayList<VoteDTO> voteList = voteDAO.loadList();
			
			Collections.sort(voteList);
			
			for(Iterator<VoteDTO> it = voteList.iterator() ; it.hasNext() ;) {
				Date date = new Date();
			    java.sql.Date sentDate = new java.sql.Date(date.getTime());
			    
			    VoteDTO voteInfo = it.next();
			    
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
			    	it.remove();
			    }
			}
			
			VotedInfoDAO votedInfoDAO = new VotedInfoDAO();
			
			HashMap<VoteDTO, Integer> voteListCount = new HashMap<VoteDTO, Integer>();
			for(int i = 0; i < voteList.size(); i++) {
				voteListCount.put(voteList.get(i), votedInfoDAO.voteCount(voteList.get(i).getVoteKind()));
			}
			
			RequestDispatcher dispatcher = null;
			request.setAttribute("voteList", voteList);
			request.setAttribute("voteListCount", voteListCount);
			dispatcher = context.getRequestDispatcher("/main.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
