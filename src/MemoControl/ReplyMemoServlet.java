package MemoControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

/**
 * Servlet implementation class CanWriteMemoServlet
 */
@SuppressWarnings("serial")
public class ReplyMemoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("sent_id")!=null) {
			String sent_id=request.getParameter("sent_id");
			String recv_id=request.getParameter("recv_id");
			String sentDate=request.getParameter("sentDate");
			String sentTime=request.getParameter("sentTime");
			String title=request.getParameter("title");
			request.setAttribute("sent_id", sent_id);
			request.setAttribute("recv_id", recv_id);
			request.setAttribute("sentDate", sentDate);
			request.setAttribute("sentTime", sentTime);
			request.setAttribute("title", title);
			RequestDispatcher dispatcher=request.getRequestDispatcher("writeMemo.jsp");
			dispatcher.forward(request, response);
		}
	}
}
