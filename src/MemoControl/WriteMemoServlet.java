package MemoControl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecvMemoDAO;
import model.RecvMemoDTO;
import model.SentMemoDAO;
import model.SentMemoDTO;


@SuppressWarnings("serial")
public class WriteMemoServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String sent_id=(String) request.getSession().getAttribute("loginedID");
		String recv_id=request.getParameter("recv_id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		content.replace("\r\n", "<br>");//db에 넣을때 하거나 불러올때 하거나 둘중하나
		if(recv_id==null||recv_id.equals("")||sent_id==null||sent_id.equals("")||title==null||title.equals("")||content==null||content.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("writeMemo.jsp");
			return;
		}
		Date date=new Date();
		java.sql.Date sentDate=new java.sql.Date(date.getTime());
		java.sql.Time sentTime=new java.sql.Time(date.getTime());
		try {
			RecvMemoDTO memoinfo=new RecvMemoDTO();
			RecvMemoDAO memoinfoDao=new RecvMemoDAO();
			SentMemoDTO sentMemo=new SentMemoDTO();
			SentMemoDAO sentMemoDao=new SentMemoDAO();
			memoinfo.setSent_id(sent_id);
			memoinfo.setRecv_id(recv_id);
			memoinfo.setTitle(title);
			memoinfo.setContent(content);
			memoinfo.setSentDate(sentDate);
			memoinfo.setSentTime(sentTime);
			sentMemo.setSent_id(sent_id);
			sentMemo.setRecv_id(recv_id);
			sentMemo.setTitle(title);
			sentMemo.setContent(content);
			sentMemo.setSentDate(sentDate);
			sentMemo.setSentTime(sentTime);
			int result=memoinfoDao.insert(memoinfo);
			int result2=sentMemoDao.insert(sentMemo);
			if(result==1&&result2==1) {
				request.getSession().setAttribute("messageType", "성공 메시지");
				request.getSession().setAttribute("messageContent", "쪽지를 보냈습니다.");
				response.sendRedirect("./recvMemo");
			}
			else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "쪽지를 보내지 못했습니다.");
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}
