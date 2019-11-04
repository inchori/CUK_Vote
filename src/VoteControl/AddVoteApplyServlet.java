package VoteControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class AddVoteApplyServlet
 */
public class AddVoteApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		HttpSession session = request.getSession();
		
		try {
			VoteApplyInfoDAO voteApplyInfoDAO = new VoteApplyInfoDAO();
			
			CandidateDAO candidateDAO = new CandidateDAO();
			
			SubCandidateDAO subCandidateDAO = new SubCandidateDAO();
			
			int candidateNum = Integer.parseInt(request.getParameter("candidateNum"));
			
			for(int i = 1 ; i<= candidateNum; i++) {
				if(request.getParameter("studentID" + i) == null || request.getParameter("studentID" + i).equals("") || request.getParameter("commitment" + i) == null || request.getParameter("commitment" + i).equals("") || request.getParameter("carreer" + i) == null || request.getParameter("carreer" + i).equals("")) {
					session.setAttribute("messageType", "오류 메시지");
					session.setAttribute("messageContent", "모두 다 입력해주세요.");	
					response.sendRedirect("./addVoteApply.jsp");
					return;
				}
				else if(request.getParameter("subCheck" + i) != null) {
					if(request.getParameter("subStudentID" + i) == null || request.getParameter("subStudentID" + i).equals("") || request.getParameter("subCarreer" + i) == null || request.getParameter("subCarreer" + i).equals("")) {
						session.setAttribute("messageType", "오류 메시지");
						session.setAttribute("messageContent", "모두 다 입력해주세요.");	
						response.sendRedirect("./addVoteApply.jsp");
						return;
					}
				}
			}
				
			voteApplyInfoDAO.insert((VoteApplyInfoDTO)session.getAttribute("voteApplyInfo"));
			
			session.removeAttribute("voteApplyInfo");
			

			session.removeAttribute("isSubCandidate");
			session.removeAttribute("candidateNum");
			
			for(int i = 1 ;i<=candidateNum; i++) {
				
				CandidateDTO candidate = new CandidateDTO();
				candidate.setVoteKind(request.getParameter("voteKind"));
				candidate.setStudentID(Integer.parseInt(request.getParameter("studentID" + i)));
				candidate.setNo(i);
				candidate.setCommitment(request.getParameter("commitment" + i));
				candidate.setCarreer(request.getParameter("carreer"+ i));
				candidate.setGetVote(0);
				candidate.setImgPath("");
				candidateDAO.insert(candidate);
				if(request.getParameter("subCheck" + i) != null) {
					SubCandidateDTO subCandidate = new SubCandidateDTO();
					subCandidate.setStudentID(Integer.parseInt(request.getParameter("subStudentID" + i)));
					subCandidate.setNo(i);
					subCandidate.setCarreer(request.getParameter("subCarreer"+ i));
					subCandidate.setVoteKind(request.getParameter("voteKind"));
					subCandidate.setImgPath("");
					subCandidateDAO.insert(subCandidate);
				}
			}
			response.sendRedirect("./voteApplyView");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
