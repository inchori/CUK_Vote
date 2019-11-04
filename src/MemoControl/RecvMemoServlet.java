package MemoControl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecvMemoDAO;
import model.RecvMemoDTO;

/**
 * Servlet implementation class LoadRecvMemoServlet
 */
@SuppressWarnings("serial")
public class RecvMemoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginedID=(String) request.getSession().getAttribute("loginedID");
		if(loginedID==null) {
			//데이터베이스 오류
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "먼저 로그인하세요.");
			response.sendRedirect("login.jsp");
			return;
		}
		int pageNumber=1;
		if(request.getParameter("pageNumber")!=null)
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		try {
			RecvMemoDAO recvMemoDAO=new RecvMemoDAO();
			ArrayList<RecvMemoDTO> recvMemoList=recvMemoDAO.getList(pageNumber, loginedID);
			boolean nextPage=recvMemoDAO.nextPage(pageNumber+1, loginedID);                                                                    
			request.setAttribute("nextPage", nextPage);
			request.setAttribute("recvMemoList", recvMemoList);
			request.setAttribute("pageNumber", pageNumber);
			RequestDispatcher dispatcher=request.getRequestDispatcher("recvMemo.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
