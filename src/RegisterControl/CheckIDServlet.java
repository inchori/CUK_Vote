package RegisterControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentInfoDAO;

/**
 * Servlet implementation class CheckIDServlet
 */
@SuppressWarnings("serial")
@WebServlet("/CheckIDServlet")
public class CheckIDServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("euc-kr");
			String ID=request.getParameter("ID");
			try {
				response.getWriter().write(new StudentInfoDAO().insertCheck(ID)+"");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
}
