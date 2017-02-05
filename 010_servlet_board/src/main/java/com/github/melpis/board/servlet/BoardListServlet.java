package com.github.melpis.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVO> returnBoardList = null;
		//1. 데이터 추출
		String sPage = request.getParameter("page");

		int page = 0;
		try {
			page = Integer.parseInt(sPage);
		} catch (NumberFormatException e) {
			page = 1;
		}
		//2. 유효성 검사
		if (page < 1) {
			page = 1;
		}
		//3. 처리
		BoardManager boardManager = new BoardManager();
		returnBoardList = boardManager.boardList(page);
		//4. 결과 출력
		PrintWriter out = response.getWriter();

		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<TABLE border='1'>");

		out.println("<tr>");

		out.println("<td>");
		out.println("글번호");
		out.println("</td>");

		out.println("<td>");
		out.println("제목");
		out.println("</td>");

		out.println("<td>");
		out.println("내용");
		out.println("</td>");

		out.println("<td>");
		out.println("등록일");
		out.println("</td>");

		out.println("<td>");
		out.println("조회수");
		out.println("</td>");

		out.println("</tr>");
		if (returnBoardList.size() < 1) {
			out.println("<tr>");
			out.println("<td colspan='5'>");
			out.println("리스트 없음");
			out.println("</td>");
			out.println("</tr>");
		}

		for (int indexI = 0; indexI < returnBoardList.size(); indexI++) {
			BoardVO boardVO = returnBoardList.get(indexI);
			out.println("<tr>");

			out.println("<td>");
			out.println(boardVO.getSeq());
			out.println("</td>");

			out.println("<td>");
			out.println("<a href=" + "./read.do?" + "seq=" + boardVO.getSeq() + ">");


			out.println(boardVO.getTitle());

			out.println("</a>");
			out.println("</td>");

			out.println("<td>");
			out.println(boardVO.getContent());
			out.println("</td>");

			out.println("<td>");
			out.println(boardVO.getRegistDate());
			out.println("</td>");

			out.println("<td>");
			out.println(boardVO.getReadCount());
			out.println("</td>");

			out.println("</tr>");
		}
		out.println("<tr>");
		out.println("<td colspan='5' align='right'>");
		out.println("<input type='button' value='등록' onClick='location.href=\"./regist_form.do\"' />");

		out.println("</td>");
		out.println("</tr>");

		out.println("</td>");
		out.println("</tr>");
		out.println("</TABLE>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

}
