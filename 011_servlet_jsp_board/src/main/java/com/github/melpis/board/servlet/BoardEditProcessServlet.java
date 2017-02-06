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
 * Servlet implementation class BoardEditProcessServlet
 */
public class BoardEditProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardManager boardManager = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	this.boardManager = (BoardManager)config.getServletContext().getAttribute("boardManager");
    	
    }
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		BoardVO boardVO = new BoardVO();
		//1. 데이터 추출
		String sSeq = request.getParameter("seq");
		String title = request.getParameter("title");
		String content =request.getParameter("content");
		int seq = 0;
		try{
			seq = Integer.parseInt(sSeq);
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}


		String sPage = request.getParameter("page");
		BoardVO paramBoard = new BoardVO();
		paramBoard.setPage(Integer.parseInt(sPage));
		paramBoard.setSeq(seq);

		//2. 유효성 검사
		if(seq < 1){

		}
		if(title == null ||title.length() >100 ){

		}
		if(content ==  null || content.length() > 4000){

		}
		boardVO.setSeq(seq);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		//3. 처리

		boardManager.editBoard(boardVO);

		request.setAttribute("paraBoard", paramBoard);

		request.getRequestDispatcher("/board/edit_process.jsp").forward(request, response);
	}

}
