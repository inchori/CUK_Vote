package VoteControl;

import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class VoteViewServlet
 */
public class VoteViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		String voteKind = request.getParameter("voteKind");	
		HttpSession session = request.getSession();

		VoteDAO voteDAO = null;
		try {
			voteDAO = new VoteDAO();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		VoteDTO voteInfo = new VoteDTO();

		try {
			voteInfo = voteDAO.load(voteKind);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date date = new Date();
		java.sql.Date sentDate = new java.sql.Date(date.getTime());

		Calendar voteCalTime = Calendar.getInstance();
		voteCalTime.setTime(voteInfo.getEndTime());

		Calendar voteCalDate = Calendar.getInstance();
		voteCalDate.setTime(voteInfo.getEndDate());
		voteCalDate.set(Calendar.HOUR_OF_DAY, voteCalTime.get(Calendar.HOUR_OF_DAY));
		voteCalDate.set(Calendar.MINUTE, voteCalTime.get(Calendar.MINUTE));
		voteCalDate.set(Calendar.SECOND, voteCalTime.get(Calendar.SECOND));

		Date date2 = voteCalDate.getTime();
		java.sql.Date voteDate = new java.sql.Date(date2.getTime());

		voteCalTime.setTime(voteInfo.getStartTime());

		voteCalDate.setTime(voteInfo.getStartDate());
		voteCalDate.set(Calendar.HOUR_OF_DAY, voteCalTime.get(Calendar.HOUR_OF_DAY));
		voteCalDate.set(Calendar.MINUTE, voteCalTime.get(Calendar.MINUTE));
		voteCalDate.set(Calendar.SECOND, voteCalTime.get(Calendar.SECOND));

		Date date3 = voteCalDate.getTime();
		java.sql.Date voteStartDate = new java.sql.Date(date3.getTime());

		if(sentDate.after(voteDate)){
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "날짜 또는 시간이 지났습니다.");	
			response.sendRedirect("./voteKindView");
		}
		else if(sentDate.before(voteStartDate)) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "날짜 또는 시간이 아직 안됬습니다.");	
			response.sendRedirect("./voteKindView");
		}
		else if(session.getAttribute("loginedID") == null) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "로그인을 먼저 해주세요");
			response.sendRedirect("login.jsp");
		}
		else{
			VotedInfoDAO votedInfoDAO = null;
			try {
				votedInfoDAO = new VotedInfoDAO();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			StudentInfoDAO studentInfoDAO = null;

			try {
				studentInfoDAO = new StudentInfoDAO();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			StudentInfoDTO student = new StudentInfoDTO();
			try {
				student = studentInfoDAO.load((String) session.getAttribute("loginedID"));
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			try {
				if(votedInfoDAO.voteCheck(voteKind, student.getStudentID())){
					session.setAttribute("messageType", "오류 메시지");
					session.setAttribute("messageContent", "이미 투표 했습니다.");
					response.sendRedirect("./voteKindView");
					return;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			CandidateDAO candidateDAO = null;
			try {
				candidateDAO = new CandidateDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SubCandidateDAO subCandidateDAO = null;
			try {
				subCandidateDAO = new SubCandidateDAO();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int count = 0;
			try {
				count = candidateDAO.Count(voteKind);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Map<CandidateDTO, SubCandidateDTO> subCandidateList = new HashMap<CandidateDTO, SubCandidateDTO>();
			List<CandidateDTO> candidateList = new ArrayList<CandidateDTO>();
			Map<CandidateDTO, StudentInfoDTO> candidateInfoList = new HashMap<CandidateDTO, StudentInfoDTO>();
			Map<SubCandidateDTO, StudentInfoDTO> subCandidateInfoList = new HashMap<SubCandidateDTO, StudentInfoDTO>();

			for(int i = 1; i<= count; i++) {
				CandidateDTO candidate = new CandidateDTO();
				SubCandidateDTO subCandidate = null;

				try {
					candidate = candidateDAO.load(voteKind, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					candidateInfoList.put(candidate, studentInfoDAO.load(candidate.getStudentID()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					subCandidate = subCandidateDAO.load(voteKind, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				candidateList.add(candidate);
				if(subCandidate != null) {
					try {
						subCandidateInfoList.put(subCandidate, studentInfoDAO.load(subCandidate.getStudentID()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					subCandidateList.put(candidate, subCandidate);
				}
			}

			RequestDispatcher dispatcher = null;
			request.setAttribute("candidateList", candidateList);
			request.setAttribute("subCandidateList", subCandidateList);
			request.setAttribute("candidateInfoList", candidateInfoList);
			request.setAttribute("subCandidateInfoList", subCandidateInfoList);
			request.setAttribute("voteKind", voteKind);
			dispatcher = context.getRequestDispatcher("/vote.jsp");
			dispatcher.forward(request, response);
		}	
	}
}
