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
 * Servlet implementation class BoardReadServlet
 */
public class BoardReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardReadServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		//2. 유효성 검사
		if (seq < 1) {

		}
		//3. 처리
		BoardManager boardManager = new BoardManager();
		returnBoard = boardManager.readBoard(seq);
		//4. 결과 출력
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<script type='text/javascript'>");
		out.println("</script>");
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


		if (returnBoard == null) {
			out.println("<tr>");
			out.println("<td colspan='5'>");

			out.println(" 없음!");
			out.println("</td>");
			out.println("</tr>");
		}

		out.println("<tr>");


		out.println("<td>");
		out.println(returnBoard.getSeq());
		out.println("</td>");

		out.println("<td>");
		out.println(returnBoard.getTitle());
		out.println("</td>");

		out.println("<td>");
		out.println(returnBoard.getContent());
		out.println("</td>");

		out.println("<td>");
		out.println(returnBoard.getRegistDate());
		out.println("</td>");

		out.println("<td>");
		out.println(returnBoard.getReadCount());
		out.println("</td>");

		out.println("</tr>");

		out.println("<tr>");

		out.println("<td align='right' colspan='5'>");
		out.println("<input type='button' value='수정' onClick='location.href=\"./edit_form.do?seq=" + returnBoard.getSeq() + "\"'/>");

		out.println("</br>");

		out.println("<input type='button' value='삭제' onClick='location.href=\"./delete.do?seq=" + returnBoard.getSeq() + "\"'/>");


		out.println("</td>");

		out.println("</tr>");
	}

}
