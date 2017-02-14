package com.github.melpis.comment.servlet;

import com.github.melpis.comment.manager.CommentManager;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CommentDeleteServlet
 */
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentManager commentManager = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.commentManager = new CommentManager();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sSeq = request.getParameter("seq");
		int seq = 0;
		try{
			seq = Integer.parseInt(sSeq);
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		this.commentManager.removeComment(seq);
		response.sendRedirect(" ");

	}

}
