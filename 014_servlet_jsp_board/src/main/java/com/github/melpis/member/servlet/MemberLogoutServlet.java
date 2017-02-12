package com.github.melpis.member.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class MemberLogoutServlet
 */
public class MemberLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String viewName = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.viewName = config.getInitParameter("viewName");
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //1. 데이터 추출
        HttpSession session = request.getSession(false);
        //2. 유효성 검사
        //3. 처리
        if (session != null) {
            session.invalidate();
        }
        //4. 결과 출력
        request.getRequestDispatcher(this.viewName).forward(request, response);
    }

}





