package com.github.melpis.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.melpis.board.manager.BoardManager;

/**
 * Servlet implementation class BoardRemoveServlet
 */
public class BoardRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터 추출
		String sSeq = request.getParameter("seq");
		int seq = 0;
		try{
			seq = Integer.parseInt(sSeq);
		}catch (NumberFormatException e) {
		
		}
		//2. 유효성 검사
		if(seq < 1){
			
		}
		//3. 처리
		BoardManager boardManager = new BoardManager();
		boardManager.removeBoard(seq);
		//4. 결과 출력
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("성공 했습니다 ");
		out.println("<br/> ");

		out.println("<input type='button' value='리스트' onClick='location.href=\"./list.do\"'>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

}
