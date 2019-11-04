package MemoControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RecvMemoDAO;

/**
 * Servlet implementation class DeleteRecvMemoServlet
 */
@SuppressWarnings("serial")
public class DeleteRecvMemoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recv_id=request.getParameter("recv_id");
		String seqno=request.getParameter("seqno");
		try {
			RecvMemoDAO recvMemoDAO=new RecvMemoDAO();
			int result=recvMemoDAO.delete(seqno, recv_id);
			if(result==1) {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "������ �����Ͽ����ϴ�.");
				response.sendRedirect("./recvMemo");
			}
			else{
				//��й�ȣ�� Ʋ��
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�����ͺ��̽� �����Դϴ�.");
				response.sendRedirect("./recvMemo");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
