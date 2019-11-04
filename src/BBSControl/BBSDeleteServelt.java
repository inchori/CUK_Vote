package BBSControl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
public class BBSDeleteServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BBSDeleteServelt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		try {
			BBSDAO bbsDAO = new BBSDAO();
			bbsDAO.delete(boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BBSVoteInfoDAO bbsVoteInfoDAO = new BBSVoteInfoDAO();
		CommentDAO commentdao = new CommentDAO();
		try {
			commentdao.BBSdelete(boardNo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BBSVoteInfoDTO bbsVoteInfo = null;
		try {
			bbsVoteInfo = bbsVoteInfoDAO.load(boardNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(bbsVoteInfo != null) {
			VotedInfoDAO votedInfoDAO = null;
			try {
				votedInfoDAO = new VotedInfoDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				votedInfoDAO.delete(String.valueOf(bbsVoteInfo.getSeqno()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				bbsVoteInfoDAO.delete(boardNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("bbslist");
	}

}
