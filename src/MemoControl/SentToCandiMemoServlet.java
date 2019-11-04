package MemoControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class SentToCandiMemoServlet
 */
public class SentToCandiMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		
		CandidateDAO candidateDAO = null;
		try {
			 candidateDAO = new CandidateDAO();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CandidateDTO candidate = new CandidateDTO();
		
		try {
			candidate = candidateDAO.load(request.getParameter("voteKind"), Integer.parseInt(request.getParameter("seqNo")));
		}  catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StudentInfoDAO studentInfoDAO = null;
		try {
			 studentInfoDAO = new StudentInfoDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StudentInfoDTO candidateInfo = new StudentInfoDTO();
		
		try {
			 candidateInfo = studentInfoDAO.load(candidate.getStudentID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("sent_id", candidateInfo.getID());
		
		response.setCharacterEncoding("text/html; charset=euc-kr");
		RequestDispatcher dispatcher=request.getRequestDispatcher("writeMemo.jsp");
		dispatcher.forward(request, response);
	}

}
