package com.github.melpis.member.servlet;

import com.github.melpis.member.manager.MemberManager;
import com.github.melpis.member.vo.MemberVO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class MemberRegistServlet
 */
public class MemberRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String formViewName = null;
    private String resultViewName = null;
    private String errorViewName = null;
    private MemberManager memberManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
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
        //1. 데이터 추출
        MemberVO member = new MemberVO();
        String userId = request.getParameter("userId");
        member.setUserId(userId);
        String userPw = request.getParameter("userPw");
        member.setUserPw(userPw);
        String userPwConfirm = request.getParameter("userPWConfrim");
        member.setUserPwConfirm(userPwConfirm);
        String userName = request.getParameter("userName");
        member.setUserName(userName);
        //2. 유효성 검사
        //3. 처리
        try {
            this.memberManager.registMember(member);
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }
        //4. 결과 출력


        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}
