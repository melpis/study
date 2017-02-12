package com.github.melpis.board.servlet;

import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class BoardEditServlet
 */
public class BoardEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorViewName = null;
    private String formViewName = null;
    private String resultViewName = null;
    private BoardManager boardManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.errorViewName = config.getServletContext().getInitParameter("errorViewName");
        this.formViewName = config.getInitParameter("formViewName");
        this.resultViewName = config.getInitParameter("resultViewName");
        this.boardManager = new BoardManager();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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


        BoardVO boardVO = null;
        BoardVO board = new BoardVO();
        //1. 데이터 추출
        BoardVO paramBoard = new BoardVO();
        String sSeq = request.getParameter("seq");
        int seq = 0;
        try {
            seq = Integer.valueOf(sSeq);
        } catch (NumberFormatException e) {
            // 예외 처리
        }

        String sPage = request.getParameter("page");
        paramBoard.setPage(Integer.valueOf(sPage));

        //2. 유효성 검사
        if (seq < 1) {
            // 예외 처리
        }

        board.setSeq(seq);
        String userId = (String) session.getAttribute("sessionUserId");


        //3. 처리
        boardVO = this.boardManager.getBoard(board);
        if (!boardVO.getMember().getUserId().equals(userId)) {
            try {
                throw new Exception("등록한 아이디가 아닙니다.");
            } catch (Exception e) {
                request.setAttribute("exception", e);
                request.getRequestDispatcher(this.errorViewName).forward(request, response);
                return;
            }
        }

        //4. 결과 출력
        request.setAttribute("boardVO", boardVO);
        request.setAttribute("paramBoard", paramBoard);
        request.getRequestDispatcher(this.formViewName).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        BoardVO paramBoard = new BoardVO();
        BoardVO board = new BoardVO();
        request.setCharacterEncoding("EUC-KR");
        String sSeq = request.getParameter("seq");
        int seq = 0;
        try {
            seq = Integer.valueOf(sSeq);
        } catch (NumberFormatException e) {
            // 예외 처리
        }
        board.setSeq(seq);
        String title = request.getParameter("title");
        board.setTitle(title);
        String content = request.getParameter("content");
        board.setContent(content);

        String sPage = request.getParameter("page");
        paramBoard.setPage(Integer.valueOf(sPage));
        paramBoard.setSeq(seq);
        //2. 유효성 검사
        if (board.getSeq() < 1) {
            // 예외 처리
        }
        if (board.getTitle() == null || board.getTitle().length() > 100) {
            // 예외 처리
        }
        if (board.getContent() == null || board.getContent().length() > 4000) {
            // 예외 처리
        }
        //3. 처리

        this.boardManager.editBoard(board);

        //4. 결과 출력
        request.setAttribute("paramBoard", paramBoard);

        request.getRequestDispatcher(this.resultViewName).forward(request, response);
    }

}
