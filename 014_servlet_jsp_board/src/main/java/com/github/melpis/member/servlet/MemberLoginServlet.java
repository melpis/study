package com.github.melpis.member.servlet;

import com.github.melpis.member.manager.MemberManager;
import com.github.melpis.member.vo.MemberVO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class MemberLoginServlet
 */
public class MemberLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String formViewName = null;
    private String resultViewName = null;
    private String errorViewName = null;
    private MemberManager memberManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");

        this.formViewName = config.getInitParameter("formViewName");
        this.resultViewName = config.getInitParameter("resultViewName");

        this.memberManager = new MemberManager();
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //1. 데이터 추출
        //2. 유효성 검사
        //3. 처리
        //4. 결과 출력
        request.getRequestDispatcher(this.formViewName).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //1. 데이터 추출
        request.setCharacterEncoding("EUC-KR");
        MemberVO memberVO = new MemberVO();
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        memberVO.setUserId(userId);
        memberVO.setUserPw(userPw);
        //2. 유효성 검사
        if (memberVO.getUserId() == null
                || memberVO.getUserId().trim().length() < 6
                || memberVO.getUserId().trim().length() > 20) {
            // 예외 처리
        }
        if (memberVO.getUserPw() == null
                || memberVO.getUserPw().trim().length() < 6
                || memberVO.getUserPw().trim().length() > 20) {
            // 예외 처리
        }

        MemberVO loginMemberVO = null;
        try {
            loginMemberVO = this.memberManager.loginMember(memberVO);
            HttpSession session = request.getSession(true);
            session.setAttribute("sessionUserId", loginMemberVO.getUserId());
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }

        //4. 결과 출력

        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}





