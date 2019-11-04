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
 * Servlet implementation class DeleteSentMemoSelectedServlet
 */
@SuppressWarnings("serial")
public class DeleteSentMemoSelectedServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String[] selected = request.getParameterValues("selected");
		if(selected==null) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "삭제할 쪽지를 선택해주세요.");
			response.sendRedirect("./sentMemo");
		}
		else {
			try {
				HttpSession session=request.getSession();
				String ID=session.getAttribute("loginedID").toString();
				SentMemoDAO sentMemoDAO=new SentMemoDAO();
				for(int i=0;i<selected.length;i++) {
					sentMemoDAO.delete(selected[i], ID);
				}
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "모두 삭제하였습니다.");
				response.sendRedirect("./sentMemo");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
