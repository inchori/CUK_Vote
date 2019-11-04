package MemoControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecvMemoDAO;
import model.RecvMemoDTO;

/**
 * Servlet implementation class recvMemoViewServlet
 */
@SuppressWarnings("serial")
public class RecvMemoViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realseqno=request.getParameter("realseqno");
		try {
			RecvMemoDAO recvMemoDAO=new RecvMemoDAO();
			RecvMemoDTO recvMemo=recvMemoDAO.loadFromDB(realseqno);
			request.setAttribute("memo", recvMemo);
			RequestDispatcher dispatcher=request.getRequestDispatcher("recvMemoView.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
