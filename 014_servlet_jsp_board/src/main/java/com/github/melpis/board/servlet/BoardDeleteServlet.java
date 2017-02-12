package com.github.melpis.board.servlet;

import com.github.melpis.attachfile.manager.AttachfileManager;
import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;
import com.github.melpis.comment.manager.CommentManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class BoardDeleteServlet
 */
public class BoardDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorViewName = null;
    private String resultViewName = null;
    private BoardManager boardManager = null;
    private AttachfileManager attachfileManager = null;
    private CommentManager commentManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
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
        this.commentManager = new CommentManager();
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        }
        //2. 유효성 검사
        if (seq < 1) {

        }

        String sPage = request.getParameter("page");
        BoardVO paramBoard = new BoardVO();
        BoardVO boardVO = new BoardVO();
        BoardVO returnBoard = new BoardVO();
        paramBoard.setPage(Integer.parseInt(sPage));
        //3. 처리
        boardVO.setSeq(seq);

        String userId = (String) session.getAttribute("sessionUserId");

        returnBoard = this.boardManager.getBoard(boardVO);

        if (!returnBoard.getMember().getUserId().equals(userId)) {
            try {
                throw new Exception("등록한 사용자가 아닙니다");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher(this.errorViewName).forward(request, response);
                return;
            }

        }

        try {
            this.attachfileManager.removeAttachfileByBoardSeq(seq);
            this.commentManager.removeComments(seq);
            this.boardManager.removeBoard(seq);
        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher(this.errorViewName).forward(request, response);
            return;
        }

        request.setAttribute("paramBoard", paramBoard);
        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}
