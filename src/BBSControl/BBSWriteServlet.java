package BBSControl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

public class BBSWriteServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
    
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("euc-kr");
      
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
      
      HttpSession session = request.getSession();
      String writer = request.getParameter("id");
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      
      if(title == null || title.equals("") || content == null || content.equals("")) {
         session.setAttribute("messageType", "오류 메시지");
         session.setAttribute("messageContent", "내용을 모두 채워주세요");
         response.sendRedirect("bbswrite.jsp");
         return;
      }
      
      BBSDAO bbsdao = null;
      bbsdao = new BBSDAO();
      BBSDTO bbsdto = new BBSDTO();
      bbsdto.setTitle(title);
      bbsdto.setContent(content);
      bbsdto.setWriter(writer);
      bbsdao.insert(bbsdto);
      
      try {
		bbsdto = bbsdao.read(bbsdto.getTitle(), bbsdto.getWriter());
      } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      
      if(request.getParameter("voteCheck") != null) {
    	  String voteKind = request.getParameter("voteKind");
    	  java.util.Date utilEndDate = null;
			try {
				utilEndDate = dateFormat.parse(request.getParameter("endDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date endDate = new java.sql.Date(utilEndDate.getTime()); 
			
			java.sql.Time endTime = null;
			try {
				endTime = new java.sql.Time(timeFormat.parse(request.getParameter("endTime")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String candidate1 = request.getParameter("candidate1");
			String candidate2 = request.getParameter("candidate2");
			if(voteKind == null || voteKind.equals("")|| endDate == null || endDate.equals("") || endTime == null || endTime.equals("") || candidate1 == null || candidate1.equals("") || candidate2 == null || candidate2.equals("")) {
				session.setAttribute("messageType", "오류 메시지");
		        session.setAttribute("messageContent", "내용을 모두 채워주세요");
		        response.sendRedirect("bbswrite.jsp");
		        return;
			}
			BBSVoteInfoDAO bbsVoteInfoDAO = null;
			bbsVoteInfoDAO = new BBSVoteInfoDAO();
			
			BBSVoteInfoDTO bbsVoteInfo = new BBSVoteInfoDTO();
			
			bbsVoteInfo.setSeqno(bbsdto.getSeqno());
			bbsVoteInfo.setVoteKind(voteKind);
			bbsVoteInfo.setEndDate(endDate);
			bbsVoteInfo.setEndTime(endTime);
			bbsVoteInfo.setCandidate1(candidate1);
			bbsVoteInfo.setCandidate2(candidate2);
			bbsVoteInfoDAO.insert(bbsVoteInfo);
      }
      
      session.setAttribute("messageType", "성공 메시지");
      session.setAttribute("messageContent", "성공적으로 게시물이 작성되었습니다.");
      response.sendRedirect("bbslist");
      }
   }
