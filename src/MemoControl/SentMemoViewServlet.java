package MemoControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecvMemoDAO;
import model.RecvMemoDTO;
import model.SentMemoDAO;
import model.SentMemoDTO;

/**
 * Servlet implementation class recvMemoViewServlet
 */
@SuppressWarnings("serial")
public class SentMemoViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realseqno=request.getParameter("realseqno");
		try {
			SentMemoDAO sentMemoDAO=new SentMemoDAO();
			SentMemoDTO sentMemo=sentMemoDAO.loadFromDB(realseqno);
			request.setAttribute("memo", sentMemo);
			RequestDispatcher dispatcher=request.getRequestDispatcher("sentMemoView.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
