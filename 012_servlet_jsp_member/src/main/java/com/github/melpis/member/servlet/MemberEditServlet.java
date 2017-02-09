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
 * Servlet implementation class MemberEditServlet
 */
public class MemberEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorPage = null;
    private String formViewName = null;
    private String resultViewName = null;
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
        super.init(config);

        this.errorPage = config.getServletContext().getInitParameter("error");
        this.formViewName = config.getInitParameter("formViewName");
        this.resultViewName = config.getInitParameter("resultViewName");
        this.memberManager = new MemberManager();


    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberVO paramMember = null;
        HttpSession session = request.getSession(false);

        if (session == null) {
            try {
                throw new Exception("로그인이 필요한 폐이지");
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher(this.errorPage).forward(request, response);
                return;
            }

        } else if (session.getAttribute("userId") == null) {
            try {
                throw new Exception("로그인이 필요한 폐이지");
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher(this.errorPage).forward(request, response);
                return;
            }
        }

        String sessionUserId = (String) session.getAttribute("userId");


        paramMember = this.memberManager.getMember(sessionUserId);

        request.setAttribute("paramMemer", paramMember);
        request.getRequestDispatcher(this.formViewName).forward(request, response);


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberVO paramMember = null;


        HttpSession session = request.getSession();
        String sessionUserId = (String) session.getAttribute("userId");
        String userPw = request.getParameter("userPw");
        if (userPw.length() < 4 || userPw.length() > 12) {
            try {
                throw new Exception("패스워드가 적합하지 않습니다");
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher(this.errorPage).forward(request, response);
                return;
            }

        }

        MemberVO memberVO = new MemberVO();
        memberVO.setUserId(sessionUserId);
        memberVO.setUserPw(userPw);
        paramMember = this.memberManager.editMember(memberVO);

        request.setAttribute("paramMember", paramMember);


        request.getRequestDispatcher(this.resultViewName).forward(request, response);

    }

}
