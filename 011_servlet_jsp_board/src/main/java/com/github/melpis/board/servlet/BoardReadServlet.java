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
 * Servlet implementation class BoardReadServlet
 */
public class BoardReadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardManager boardManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReadServlet() {
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
        BoardVO returnBoard = null;
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
        returnBoard = this.boardManager.readBoard(seq);

        //4.결과 출력
        request.setAttribute("returnBoard", returnBoard);
        request.setAttribute("paramBoard", paramBoard);
        request.getRequestDispatcher("/board/read.jsp").forward(request, response);

    }

}
