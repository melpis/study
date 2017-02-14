package com.github.melpis.comment.servlet;

import com.github.melpis.comment.manager.CommentManager;
import com.github.melpis.comment.vo.CommentVO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CommentListServlet
 */
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String viewName = null;
    private CommentManager commentManager = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.viewName = config.getInitParameter("viewName");
		this.commentManager = new CommentManager();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CommentVO> commentList = null;
		//1. 데이터 추출
		//2. 유효성 검사
		//3. 처리
		
		commentList= this.commentManager.listComment();
		//4. 결과 출력
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher(this.viewName).forward(request, response);
	}

}
