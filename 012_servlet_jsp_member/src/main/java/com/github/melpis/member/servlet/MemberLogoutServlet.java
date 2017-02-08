package com.github.melpis.member.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String viewName = null;   
    private String errorPage = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogoutServlet() {
        super();
       
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	this.viewName = config.getInitParameter("ViewName");
    }
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1. 데이터 추출
		//2. 유효성 검사
		//3. 처리
		HttpSession session = request.getSession(false);
		if(session == null){
			try {
				throw new Exception("로그인이 필요한 페이지");
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher(this.errorPage);
				return;
			}
			
		}else if(session.getAttribute("userId")== null){
			try {
				throw new Exception("로그인이 필요한 페이지");
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher(this.errorPage);
				return;
			}
		}else{
			session.invalidate();
		}
		//4. 결과 출력
	
		request.getRequestDispatcher(this.viewName).forward(request, response);
		
		
	}

}
