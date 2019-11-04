package RegisterControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentInfoDAO;
import model.StudentInfoDTO;

/**
 * Servlet implementation class RegisterInputServlet
 */
@SuppressWarnings("serial")
@WebServlet("/RegisterInputServlet")
public class RegisterInputServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String studentID=request.getParameter("studentID");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String birthday=request.getParameter("birthday");
		String ID=request.getParameter("ID");
		String name=request.getParameter("name");
		String major=request.getParameter("major");
		String gender=request.getParameter("gender");
		String grade=request.getParameter("grade");
		String mailAddress=request.getParameter("mailAddress");
		String phoneNumber=request.getParameter("phoneNumber");
		if(studentID==null||studentID.equals("")||password==null||password.equals("")||password2==null||password2.equals("")||birthday==null||birthday.equals("")||ID==null||ID.equals("")||name==null||name.equals("")
				||major==null||major.equals("")||gender==null||gender.equals("")||grade==null||grade.equals("")||mailAddress==null||mailAddress.equals("")||phoneNumber==null||phoneNumber.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
			response.sendRedirect("join.jsp");
			return;
		}
		if(!password.equals(password2)) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.");
			response.sendRedirect("join.jsp");
			return;
		}
		try {
			StudentInfoDTO studentinfo=new StudentInfoDTO();
			StudentInfoDAO studentinfoDao=new StudentInfoDAO();
			studentinfo.setStudentID(Integer.parseInt(studentID));
			studentinfo.setPassword(password);
			studentinfo.setBirthday(birthday);
			studentinfo.setID(ID);
			studentinfo.setName(name);
			studentinfo.setMajor(major);
			studentinfo.setGender(gender);
			studentinfo.setGrade(grade);
			studentinfo.setMailAddress(mailAddress);
			studentinfo.setPhoneNumber(phoneNumber);
			int result=studentinfoDao.insert(studentinfo);
			if(result==1) {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "ȸ�����ԵǾ����ϴ�.");
				response.sendRedirect("join.jsp");
			}
			else if(result==-2) {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "���� �й����� ���Ե� ȸ���� �ֽ��ϴ�.");
				response.sendRedirect("join.jsp");
			}
			else {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�̹� �����ϴ� ȸ���Դϴ�.");
				response.sendRedirect("join.jsp");
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}
