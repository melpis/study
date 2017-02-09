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
 * Servlet implementation class MemberViewServlet
 */
public class MemberViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String viewName = null;
    private MemberManager manager = null;
    private String errorPage = null;

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
        super.init(config);
        this.viewName = config.getInitParameter("viewName");
        this.errorPage = config.getServletContext().getInitParameter("error");

        this.manager = new MemberManager();
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberVO returnMember = null;
        //1. 데이터 추출
        HttpSession session = request.getSession(false);

        if (session == null) {

            try {
                throw new Exception("로그인이 필요합니다");
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher(this.errorPage).forward(request, response);
                return;
            }


        } else if (session.getAttribute("userId") == null) {
            try {
                throw new Exception("로그인이 필요합니다");
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.getRequestDispatcher(this.errorPage).forward(request, response);
                return;
            }
        }

        String sessionUserId = (String) session.getAttribute("userId");
        //2. 유효성 검사
        //3. 처리
        returnMember = this.manager.getMember(sessionUserId);
        //4. 결과 출력

        request.setAttribute("member", returnMember);
        request.getRequestDispatcher(this.viewName).forward(request, response);
    }

}
