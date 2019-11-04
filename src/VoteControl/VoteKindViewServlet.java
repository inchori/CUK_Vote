package VoteControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
 * Servlet implementation class VoteKindViewServlet
 */
public class VoteKindViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		HttpSession session = request.getSession();

		if(session.getAttribute("loginedID") != null) {

			ArrayList<VoteDTO> voteKindList = new ArrayList<VoteDTO>();

			try {
				int pageNumber = 1;

				if(request.getParameter("pageNumber")!=null)
					pageNumber=Integer.parseInt(request.getParameter("pageNumber"));

				VoteDAO voteDAO = new VoteDAO();

				voteKindList = voteDAO.getList(pageNumber);

				boolean nextPage=voteDAO.nextPage(pageNumber+1);

				int size = voteKindList.size();

				Map<String, ArrayList<CandidateDTO>> candidateList = new HashMap<String, ArrayList<CandidateDTO>>();
				Map<String, ArrayList<SubCandidateDTO>> subCandidateList = new HashMap<String, ArrayList<SubCandidateDTO>>();

				CandidateDAO candidateDAO = new CandidateDAO();
				SubCandidateDAO subCandidateDAO = new SubCandidateDAO();

				for(int i = 0; i<size; i++) {
					ArrayList<CandidateDTO> candidates = candidateDAO.loadList(voteKindList.get(i).getVoteKind());


					Collections.sort(candidates);
					
					candidateList.put(voteKindList.get(i).getVoteKind(), candidates);

					if(subCandidateDAO.loadList(voteKindList.get(i).getVoteKind()) != null) 
						subCandidateList.put(voteKindList.get(i).getVoteKind(), subCandidateDAO.loadList(voteKindList.get(i).getVoteKind()));

				}

				RequestDispatcher dispatcher = null;
				request.setAttribute("voteList", voteKindList);
				request.setAttribute("candidateList", candidateList);
				request.setAttribute("subCandidateList", subCandidateList);
				request.setAttribute("nextPage", nextPage);
				request.setAttribute("pageNumber", pageNumber);
				dispatcher = context.getRequestDispatcher("/voteBoard.jsp");
				dispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "로그인을 먼저 해주세요");
			response.sendRedirect("login.jsp");
		}
	}

}
