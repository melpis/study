package com.github.melpis.board.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;

/**
 * Servlet implementation class BoardRegistProcessServlet
 */
public class BoardRegistProcessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardManager boardManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRegistProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.boardManager = (BoardManager) config.getServletContext().getAttribute("boardManager");

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        //post 방식
        request.setCharacterEncoding("EUC-KR");
        BoardVO boardVO = new BoardVO();
        //1. 데이터 추출

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        //2. 유효성 검사
        if (title == null || title.length() > 100) {

        }
        if (content == null || content.length() > 4000) {

        }
        boardVO.setTitle(title);
        boardVO.setContent(content);
        //3. 처리
        int seq = boardManager.registerBoard(boardVO);
        request.setAttribute("seq", seq);
        request.getRequestDispatcher("/board/regist_process.jsp").forward(request, response);
    }

}
