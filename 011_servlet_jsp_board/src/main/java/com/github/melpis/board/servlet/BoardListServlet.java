package com.github.melpis.board.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.board.manager.BoardManager;
import com.github.melpis.board.vo.BoardVO;

/**
 * Servlet implementation class BoardListServlet
 */
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardManager boardManager = null;
    static int a =0;
    
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
    	this.boardManager = (BoardManager)config.getServletContext().getAttribute("boardManager");
    	
    	
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		BoardVO paramBoard= new BoardVO();
		List<BoardVO> returnBoardList = null;
		//1. 데이터 추출
		String sPage=request.getParameter("page");

		//2. 유효성검사

		int nowPage=0;
		try{
			nowPage=Integer.parseInt(sPage);
		}catch (NumberFormatException e) {
			nowPage=1;
		}

		if(nowPage < 1){nowPage=1;}
		paramBoard.setPage(nowPage);
		String searchColum = request.getParameter("searchColum");
		String searchWord = request.getParameter("searchWord");
		paramBoard.setSearchColum(searchColum);
		paramBoard.setSearchWord(searchWord);

		//3. 처리

		paramBoard.setTotalCount(this.boardManager.totalPageCount(paramBoard));
		returnBoardList = this.boardManager.boardList(paramBoard);

		//4. 결과 출력
		request.setAttribute("returnBoardList", returnBoardList);
		request.setAttribute("paramBoard", paramBoard);


		String viewName = getServletConfig().getInitParameter("viewName");
		request.getRequestDispatcher(viewName).forward(request, response);

	}

}
