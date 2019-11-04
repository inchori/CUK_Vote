package MemoControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RecvMemoDAO;
import model.SentMemoDAO;

/**
 * Servlet implementation class DeleteRecvMemoServlet
 */
@SuppressWarnings("serial")
public class DeleteSentMemoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sent_id=request.getParameter("sent_id");
		String seqno=request.getParameter("seqno");
		try {
			SentMemoDAO sentMemoDAO=new SentMemoDAO();
			int result=sentMemoDAO.delete(seqno, sent_id);
			if(result==1) {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "������ �����Ͽ����ϴ�.");
				response.sendRedirect("./sentMemo");
			}
			else{
				//��й�ȣ�� Ʋ��
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�����ͺ��̽� �����Դϴ�.");
				response.sendRedirect("./sentMemo");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
