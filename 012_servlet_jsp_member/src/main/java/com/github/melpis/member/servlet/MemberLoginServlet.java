package com.github.melpis.member.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.melpis.member.menager.MemberManager;
import com.github.melpis.member.vo.MemberVO;

/**
 * Servlet implementation class MemberLoginServlet
 */
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberManager memberManager = null;
    private String formViewName = null;
    private String resultViewName = null;
	private String errorPage = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	this.memberManager = new MemberManager();
    	this.formViewName = config.getInitParameter("formViewName");
    	this.resultViewName = config.getInitParameter("resultViewName");
    	this.errorPage = config.getServletContext().getInitParameter("error");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터 추출
		//2. 유효성 검사
		//3. 처리
		//4. 결과 출력
		
		request.getRequestDispatcher(formViewName).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 데이터 추출
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		//2. 유효성 검사
		if(userId  == null)
		{
			try {
				throw new Exception("아이디가 없음");
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher(this.errorPage).forward(request, response);
				return;
			}
		}
		if(userPw == null){
			try {
				throw new Exception("패스워드 없음");
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher(this.errorPage).forward(request, response);
				return;
			}
		}
		MemberVO memberVO = new MemberVO();
		memberVO.setUserId(userId);
		memberVO.setUserPw(userPw);
		
		//3. 처리
		try {
			this.memberManager.MemberLogin(memberVO);
		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher(this.errorPage).forward(request, response);
			return;
		}
		
		
		HttpSession session  = request.getSession();
		session.setAttribute("userId",userId);
		//4. 결과 출력
		request.setAttribute("userId", userId);
		
		request.getRequestDispatcher(resultViewName).forward(request, response);
	}

}
