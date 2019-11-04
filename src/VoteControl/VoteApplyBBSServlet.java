package VoteControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class VoteApplyBBSServlet
 */
public class VoteApplyBBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();
		
		VoteApplyInfoDAO voteApplyInfoDAO = null;
		try {
			voteApplyInfoDAO = new VoteApplyInfoDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(session.getAttribute("loginedID") != null) {
			VoteApplyInfoDTO voteApplyInfo = new VoteApplyInfoDTO();
			
			try {
				voteApplyInfo = voteApplyInfoDAO.load(request.getParameter("voteKind"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			
			StudentInfoDAO studentInfoDAO = null;
	     	
	     	try {
	     		studentInfoDAO = new StudentInfoDAO();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			int count = 0;
			try {
				count = candidateDAO.Count(voteApplyInfo.getVoteKind());
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
					candidate = candidateDAO.loadForBoard(voteApplyInfo.getVoteKind(), i);
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
					subCandidate = subCandidateDAO.loadForBoard(voteApplyInfo.getVoteKind(), i);
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
			request.setAttribute("voteApplyInfo", voteApplyInfo);
			request.setAttribute("candidateList", candidateList);
			request.setAttribute("subCandidateList", subCandidateList);
			request.setAttribute("candidateInfoList", candidateInfoList);
			request.setAttribute("subCandidateInfoList", subCandidateInfoList);
			dispatcher = context.getRequestDispatcher("/voteApplyBBS.jsp");
			dispatcher.forward(request, response);
		}
		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "로그인을 먼저 해주세요");
			response.sendRedirect("login.jsp");
		}
	}

}
