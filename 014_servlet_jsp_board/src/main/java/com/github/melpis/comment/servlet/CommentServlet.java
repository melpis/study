package com.github.melpis.comment.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String resultViewName = null;
    private String errorViewName = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.resultViewName = config.getInitParameter("resultViewName");
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("sessionUserId") == null) {
            try {
                throw new Exception("로그인이 필요해요");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher(this.errorViewName).forward(request, response);
                return;
            }

        }

        String sessionUserId = (String) session.getAttribute("sessionUserId");
        String sboardSeq = request.getParameter("boardSeq");
        int boardSeq = 0;

        try {
            boardSeq = Integer.parseInt(sboardSeq);
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("sessionUserId", sessionUserId);
        request.setAttribute("boardSeq", boardSeq);

        request.getRequestDispatcher(this.resultViewName).forward(request, response);


    }

}
