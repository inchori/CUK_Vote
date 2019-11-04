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
		content.replace("\r\n", "<br>");//db�� ������ �ϰų� �ҷ��ö� �ϰų� �����ϳ�
		if(recv_id==null||recv_id.equals("")||sent_id==null||sent_id.equals("")||title==null||title.equals("")||content==null||content.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
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
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "������ ���½��ϴ�.");
				response.sendRedirect("./recvMemo");
			}
			else {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "������ ������ ���߽��ϴ�.");
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}
