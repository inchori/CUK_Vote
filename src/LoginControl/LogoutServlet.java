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
		request.getSession().setAttribute("messageType", "Ȯ�� �޽���");
		request.getSession().setAttribute("messageContent", "�α׾ƿ� �Ǿ����ϴ�.");
		response.sendRedirect("main");
	}
}
