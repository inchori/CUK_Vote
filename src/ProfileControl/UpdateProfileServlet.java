package ProfileControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class UpdateProfile
 */
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String birthday=request.getParameter("birthday");
		String major=request.getParameter("major");
		String gender=request.getParameter("gender");
		String grade=request.getParameter("grade");
		String mailAddress=request.getParameter("mailAddress");
		String phoneNumber=request.getParameter("phoneNumber");
		if(password==null||password.equals("")||password2==null||password2.equals("")||birthday==null||birthday.equals("")||major==null||major.equals("")||gender==null||
				gender.equals("")||grade==null||grade.equals("")||mailAddress==null||mailAddress.equals("")||phoneNumber==null||phoneNumber.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
			response.sendRedirect("./loadProfile");
			return;
		}
		if(!password.equals(password2)) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.");
			response.sendRedirect("./loadProfile");
			return;
		}
		try {
			StudentInfoDTO studentinfo=new StudentInfoDTO();
			StudentInfoDAO studentinfoDao=new StudentInfoDAO();
			studentinfo = studentinfoDao.load((String)session.getAttribute("loginedID"));
			studentinfo.setPassword(password);
			studentinfo.setBirthday(birthday);
			studentinfo.setMajor(major);
			studentinfo.setGender(gender);
			studentinfo.setGrade(grade);
			studentinfo.setMailAddress(mailAddress);
			studentinfo.setPhoneNumber(phoneNumber);
			int result = studentinfoDao.change(studentinfo);
			if(result >= 1) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "ȸ�� ������ ���������� �����Ǿ����ϴ�.");
			response.sendRedirect("main");
			}
			else {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "ȸ�� ���� ������ �����Ͽ����ϴ�.");
				response.sendRedirect("./loadProfile");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
