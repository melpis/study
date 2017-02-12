package com.github.melpis.member.servlet;

import com.github.melpis.member.manager.MemberManager;
import com.github.melpis.member.vo.MemberVO;
import net.bit.lecture.member.exception.MemberLoginRequiredException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class MemberEditServlet
 */
public class MemberEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String formViewName = null;
    private String resultViewName = null;
    private String errorViewName = null;
    private MemberManager memberManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
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
        MemberVO paramMember = null;
        //1. 데이터 추출
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute("exception", new MemberLoginRequiredException("로그인이 필요한 영역입니다."));
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;

        }
        String sessionUserId = (String) session.getAttribute("sessionUserId");
        if (sessionUserId == null) {
            request.setAttribute("exception", new MemberLoginRequiredException("로그인이 필요한 영역입니다."));
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;

        }
        //2. 유효성 검사

        //3. 처리
        MemberVO memberVO = new MemberVO();
        memberVO.setUserId(sessionUserId);
        paramMember = this.memberManager.getMember(memberVO);
        //4. 결과 출력
        request.setAttribute("memberVO", paramMember);

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

        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute("exception", new MemberLoginRequiredException("로그인이 필요한 영역입니다."));
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;

        }
        String sessionUserId = (String) session.getAttribute("sessionUserId");
        if (sessionUserId == null) {
            request.setAttribute("exception", new MemberLoginRequiredException("로그인이 필요한 영역입니다."));
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;

        }
        String userPw = request.getParameter("userPw");
        String userPwConfirm = request.getParameter("userPwConfirm");
        memberVO.setUserId(sessionUserId);
        memberVO.setUserPw(userPw);
        memberVO.setUserPwConfirm(userPwConfirm);

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
        if (!memberVO.getUserPw().equals(memberVO.getUserPwConfirm())) {
            // 예외 처리
        }

        try {
            this.memberManager.editMember(memberVO);
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }

        //4. 결과 출력

        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}
