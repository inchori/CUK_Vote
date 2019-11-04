package BBSControl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;

public class commentDeleteServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public commentDeleteServelt() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		try {
			CommentDAO commentListDAO = new CommentDAO();
	        commentListDAO.delete(commentNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("bbs?boardNo="+boardNo);
	}
}
