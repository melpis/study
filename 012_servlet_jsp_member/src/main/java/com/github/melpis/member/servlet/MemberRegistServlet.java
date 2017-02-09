package com.github.melpis.member.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.member.menager.MemberManager;
import com.github.melpis.member.vo.MemberVO;

/**
 * Servlet implementation class MemberRegistServlet
 */
public class MemberRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberManager memberManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.memberManager = new MemberManager();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 데이터 추출
        //2. 유효성 검사
        //3. 처리

        String path = getServletConfig().getInitParameter("formViewName");
        request.getRequestDispatcher(path).forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberVO paramMember = null;
        //1. 데이터 추출
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        String userName = request.getParameter("userName");
        System.out.println(userId);
        System.out.println(userPw);
        System.out.println(userName);


        //2. 유효성 검사
        if (userId == null) {
        }
        if (userPw == null) {
        }
        if (userName == null) {
        }
        //3. 처리
        MemberVO memberVO = new MemberVO();
        memberVO.setUserId(userId);
        memberVO.setUserPw(userPw);
        memberVO.setUserName(userName);
        MemberManager manager = this.memberManager;
        paramMember = manager.registMemeber(memberVO);

        //4. 결과 출력
        request.setAttribute("paramMember", paramMember);
        String path = getServletConfig().getInitParameter("resultViewName");
        request.getRequestDispatcher(path).forward(request, response);

    }

}
