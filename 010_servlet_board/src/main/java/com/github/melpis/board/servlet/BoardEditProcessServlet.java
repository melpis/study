package com.github.melpis.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardEditProcessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO boardVO = new BoardVO();
		//1. 데이터 추출
		String sSeq = request.getParameter("seq");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int seq = 0;
		try {
			seq = Integer.parseInt(sSeq);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		//2. 유효성 검사 
		if (seq < 1) {

		}
		if (title == null || title.length() > 100) {

		}
		if (content == null || content.length() > 4000) {

		}
		boardVO.setSeq(seq);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		//3. 처리
		BoardManager boardManager = new BoardManager();
		boardManager.editBoard(boardVO);
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
