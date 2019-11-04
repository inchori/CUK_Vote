package ProfileControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.StudentInfoDAO;
import model.StudentInfoDTO;

/**
 * Servlet implementation class LoadProfile
 */
public class LoadProfileServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ServletContext context = this.getServletContext();
      HttpSession session = request.getSession();
      
      StudentInfoDAO studentDAO = null;
      try {
         studentDAO = new StudentInfoDAO();
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      StudentInfoDTO student = new StudentInfoDTO();
      try {
         student = studentDAO.load((String)session.getAttribute("loginedID"));
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      request.setAttribute("student", student);
      RequestDispatcher dispatcher = null;
      dispatcher = context.getRequestDispatcher("/UpdateProfile.jsp");
      dispatcher.forward(request, response);
   }

}