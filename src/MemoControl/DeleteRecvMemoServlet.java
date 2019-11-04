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
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "쪽지를 삭제하였습니다.");
				response.sendRedirect("./recvMemo");
			}
			else{
				//비밀번호가 틀림
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "데이터베이스 오류입니다.");
				response.sendRedirect("./recvMemo");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
