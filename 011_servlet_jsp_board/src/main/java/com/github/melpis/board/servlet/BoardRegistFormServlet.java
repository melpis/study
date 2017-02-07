package com.github.melpis.board.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.board.vo.BoardVO;

/**
 * Servlet implementation class BoardRegistFormServlet
 */
public class BoardRegistFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRegistFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String sPage = request.getParameter("page");


        BoardVO paramBoard = new BoardVO();

        paramBoard.setPage(Integer.parseInt(sPage));
        request.setAttribute("paramBoard", paramBoard);
        request.getRequestDispatcher("/board/regist_form.jsp").forward(request, response);


    }
}
