package com.github.melpis.board.servlet;

import com.github.melpis.attachfile.manager.AttachfileManager;
import com.github.melpis.attachfile.vo.AttachfileVO;
import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class BoardReadServlet
 */
public class BoardReadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorViewName = null;
    private String resultViewName = null;
    private BoardManager boardManager = null;
    private AttachfileManager attachfileManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");
        this.resultViewName = config.getInitParameter("resultViewName");
        this.boardManager = new BoardManager();
        this.attachfileManager = new AttachfileManager();


    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardVO returnBoard = null;
        List<AttachfileVO> returnAttacfilelist = null;


        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("sessionUserId") == null) {
            try {
                throw new Exception("로그인이 필요해요");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher(this.errorViewName).forward(request, response);
                return;
            }

        }
        //1. 데이터 추출
        String sSeq = request.getParameter("seq");

        int seq = 0;
        try {
            seq = Integer.parseInt(sSeq);
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        BoardVO paramBoard = new BoardVO();
        String sPage = request.getParameter("page");
        paramBoard.setPage(Integer.parseInt(sPage));

        //2. 유효성 검사
        if (seq < 1) {

        }
        //3. 처리
        try {
            returnBoard = this.boardManager.readBoard(seq);
            returnAttacfilelist = this.attachfileManager.getAttachfileListByBoardSeq(seq);
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }

        //4.결과 출력
        request.setAttribute("returnBoard", returnBoard);
        request.setAttribute("paramBoard", paramBoard);
        request.setAttribute("returnAttacfilelist", returnAttacfilelist);

        request.getRequestDispatcher(this.resultViewName).forward(request, response);


    }

}
