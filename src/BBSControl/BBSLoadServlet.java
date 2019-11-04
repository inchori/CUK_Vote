package BBSControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BBSDAO;
import model.BBSList;

public class BBSLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BBSLoadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strUpperSeqNo = request.getParameter("LAST_SEQ_NO");
        int upperSeqNo;
        if (strUpperSeqNo == null) {
              upperSeqNo = Integer.MAX_VALUE;
              boolean firstPage = true;
              request.setAttribute("firstPage",firstPage);
        }
        else {
              upperSeqNo = Integer.parseInt(strUpperSeqNo);
              boolean firstPage = false;
              request.setAttribute("firstPage",firstPage);
        }
		try {
			BBSDAO bbsDAO = new BBSDAO();
			BBSList bbslist = bbsDAO.load(upperSeqNo);
			int lastSeqNo = bbsDAO.lastSeqNo();
			request.setAttribute("BBS_LIST", bbslist);
			request.setAttribute("bbsCnt", bbslist.getListSize()); //불러오는 갯수
			request.setAttribute("lastSeqNo",lastSeqNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("bbslist.jsp?LAST_SEQ_NO="+upperSeqNo);
        dispatcher.forward(request, response);
	}
}
