package BBSControl;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BBSDAO;
import model.BBSDTO;
import model.BBSVoteInfoDAO;
import model.BBSVoteInfoDTO;
import model.CommentDAO;
import model.CommentListDTO;
import model.StudentInfoDAO;
import model.StudentInfoDTO;
import model.VotedInfoDAO;

public class BBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    public BBSServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String strUpperSeqNo = request.getParameter("LAST_SEQ_NO");
        int upperSeqNo;
        if (strUpperSeqNo == null)
              upperSeqNo = Integer.MAX_VALUE;
        else
              upperSeqNo = Integer.parseInt(strUpperSeqNo);
        
        try {
        	BBSDAO bbsDAO = new BBSDAO();
			BBSDTO bbsDTO = bbsDAO.read(boardNo);
			bbsDAO.step(boardNo);
		    request.setAttribute("BBS", bbsDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        BBSVoteInfoDAO bbsVoteInfoDAO = new BBSVoteInfoDAO();
        BBSVoteInfoDTO bbsVoteInfo = null;
        
        try {
			bbsVoteInfo = bbsVoteInfoDAO.load(boardNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	     
        if(bbsVoteInfo != null) {
	     Calendar voteCalTime = Calendar.getInstance();
	     voteCalTime.setTime(bbsVoteInfo.getEndTime());
	     
	     Calendar voteCalDate = Calendar.getInstance();
	     voteCalDate.setTime(bbsVoteInfo.getEndDate());
	     voteCalDate.set(Calendar.HOUR_OF_DAY, voteCalTime.get(Calendar.HOUR_OF_DAY));
	     voteCalDate.set(Calendar.MINUTE, voteCalTime.get(Calendar.MINUTE));
	     voteCalDate.set(Calendar.SECOND, voteCalTime.get(Calendar.SECOND));
	     
	     Date date2 = voteCalDate.getTime();
	     java.sql.Date voteDate = new java.sql.Date(date2.getTime());
	     
	     VotedInfoDAO votedinfoDAO = null;
		try {
			votedinfoDAO = new VotedInfoDAO();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     
	     StudentInfoDAO studentInfoDAO = null;
		try {
			studentInfoDAO = new StudentInfoDAO();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     
	     StudentInfoDTO studentInfo = null;
		try {
			studentInfo = studentInfoDAO.load((String)session.getAttribute("loginedID"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     
		int isVote = 1;
		
	     try {
			if(votedinfoDAO.voteCheck(String.valueOf(bbsVoteInfo.getSeqno()), studentInfo.getStudentID()) == false) {
				 isVote = 0;
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    request.setAttribute("isVote", isVote);
        request.setAttribute("bbsVoteInfo", bbsVoteInfo);
        request.setAttribute("endDate", voteDate);
        }
        
		try {
			CommentDAO commentListDAO = new CommentDAO();
	        CommentListDTO commentlist = commentListDAO.load(boardNo,upperSeqNo);
        	request.setAttribute("commentCnt", commentlist.getListSize());
        	request.setAttribute("COMMENT_LIST", commentlist);
	        request.setAttribute("boardNo",boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("BBS.jsp");
        dispatcher.forward(request, response);
	}
}
