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
 * Servlet implementation class BoardDeleteServlet
 */
public class BoardDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardManager boardManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.boardManager = (BoardManager) config.getServletContext().getAttribute("boardManager");

    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = false;
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
        paramBoard.setPage(Integer.parseInt(sPage));
        //3. 처리

        result = boardManager.removeBoard(seq);

        request.setAttribute("result", result);
        request.setAttribute("paramBoard", paramBoard);
        request.getRequestDispatcher("/board/delete_process.jsp").forward(request, response);

    }

}
