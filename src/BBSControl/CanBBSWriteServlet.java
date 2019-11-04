package BBSControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CanBBSWriteServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String loginedID = (String) request.getSession().getAttribute("loginedID");
      if(loginedID==null) {
         request.getSession().setAttribute("messageType", "���� �޽���");
         request.getSession().setAttribute("messageContnent", "���� �α����� �Ǿ����� �ʽ��ϴ�");
         response.sendRedirect("login.jsp");
      }
      else {
         response.sendRedirect("bbswrite.jsp");
      }
   }
}