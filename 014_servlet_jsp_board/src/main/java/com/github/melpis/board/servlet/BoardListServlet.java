package com.github.melpis.board.servlet;

import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class BoardListServlet
 */
public class BoardListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String resultViewName = null;
    private BoardManager boardManager = null;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.resultViewName = config.getInitParameter("resultViewName");
        this.boardManager = new BoardManager();


    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardVO paramBoard = new BoardVO();
        List<BoardVO> returnBoardList = null;
        //1. 데이터 추출
        String sPage = request.getParameter("page");

        //2. 유효성검사
        int nowPage = 0;
        try {
            nowPage = Integer.parseInt(sPage);
        } catch (NumberFormatException e) {
            nowPage = 1;
        }

        if (nowPage < 1) {
            nowPage = 1;
        }
        paramBoard.setPage(nowPage);

        //3. 처리
        returnBoardList = this.boardManager.boardList(paramBoard);

        //4. 결과 출력
        request.setAttribute("returnBoardList", returnBoardList);
        request.setAttribute("paramBoard", paramBoard);


        request.getRequestDispatcher(this.resultViewName).forward(request, response);

    }

}
