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
 * Servlet implementation class MemberViewServlet
 */
public class MemberViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String viewName = null;
    private String errorViewName = null;
    private MemberManager memberManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");

        this.viewName = config.getInitParameter("viewName");

        this.memberManager = new MemberManager();
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.getRequestDispatcher(this.viewName).forward(request, response);


    }

}



