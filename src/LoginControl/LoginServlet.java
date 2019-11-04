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
				//로그인에 성공했을때 메인2(로그인한 화면)으로 이동
				request.getSession().setAttribute("loginedID", studentInfoDTO.getID());
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "로그인되었습니다.");
				response.sendRedirect("main");
			}
			else if(result==0) {
				//비밀번호가 틀림
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "비밀번호가 다릅니다.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-1) {
				//존재하지 않는 아이디
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "존재하지 않는 아이디입니다.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-2) {
				//데이터베이스 오류
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-3) {
				//아이디입력안함
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "아이디를 입력하세요.");
				response.sendRedirect("login.jsp");
			}
			else if(result==-4) {
				//비밀번호 입력 안함
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "비밀번호를 입력하세요.");
				response.sendRedirect("login.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
