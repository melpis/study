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
 * Servlet implementation class BoardEditFormServlet
 */
public class BoardEditFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardManager boardManager = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditFormServlet() {
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
        BoardVO boardVO = null;
        //1. 데이터 추출
        String sSeq = request.getParameter("seq");
        int seq = 0;
        try {
            seq = Integer.parseInt(sSeq);
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        //2. 유효성 검사
        if (seq < 1) {

        }

        String sPage = request.getParameter("page");


        BoardVO paramBoard = new BoardVO();

        paramBoard.setPage(Integer.parseInt(sPage));


        //3. 처리

        boardVO = boardManager.getBoard(seq);
        request.setAttribute("paramBoard", paramBoard);
        request.setAttribute("seq", seq);
        request.setAttribute("boardVO", boardVO);
        request.getRequestDispatcher("/board/edit_form.jsp").forward(request, response);


    }

}
