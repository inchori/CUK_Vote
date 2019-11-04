package LoginControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getSession().setAttribute("messageType", "확인 메시지");
		request.getSession().setAttribute("messageContent", "로그아웃 되었습니다.");
		response.sendRedirect("main");
	}
}
