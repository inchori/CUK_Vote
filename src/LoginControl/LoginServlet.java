package LoginControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentInfoDAO;
import model.StudentInfoDTO;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String ID=request.getParameter("ID");
		String password=request.getParameter("password");
		try {
			StudentInfoDAO studentInfoDAO=new StudentInfoDAO();
			StudentInfoDTO studentInfoDTO=new StudentInfoDTO();
			studentInfoDTO.setID(ID);
			studentInfoDTO.setPassword(password);
			int result=studentInfoDAO.login(studentInfoDTO.getID(), studentInfoDTO.getPassword());
			if(result==1) {
				//�α��ο� ���������� ����2(�α����� ȭ��)���� �̵�
				request.getSession().setAttribute("loginedID", studentInfoDTO.getID());
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�α��εǾ����ϴ�.");
				response.sendRedirect("main");
			}
			else if(result==0) {
				//��й�ȣ�� Ʋ��
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "��й�ȣ�� �ٸ��ϴ�.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-1) {
				//�������� �ʴ� ���̵�
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�������� �ʴ� ���̵��Դϴ�.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-2) {
				//�����ͺ��̽� ����
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�����ͺ��̽� ������ �߻��߽��ϴ�.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-3) {
				//���̵��Է¾���
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "���̵� �Է��ϼ���.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-4) {
				//��й�ȣ �Է� ����
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "��й�ȣ�� �Է��ϼ���.");
				response.sendRedirect("login.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
