package com.github.melpis.comment.servlet;

import com.github.melpis.comment.manager.CommentManager;
import com.github.melpis.comment.vo.CommentVO;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CommentRegisetServlet
 */
public class CommentRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentManager commentManager = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentRegistServlet() {
        super();
       
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	this.commentManager = new CommentManager();
    }

    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String content=requset.getParameter("content");
    	
    	CommentVO commentVO = new CommentVO();
    	
    	commentVO.setContent(content);
    	this.commentManager.registComment(commentVO);
    	
    }

}
