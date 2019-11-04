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
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "쪽지를 삭제하였습니다.");
				response.sendRedirect("./sentMemo");
			}
			else{
				//비밀번호가 틀림
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "데이터베이스 오류입니다.");
				response.sendRedirect("./sentMemo");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
