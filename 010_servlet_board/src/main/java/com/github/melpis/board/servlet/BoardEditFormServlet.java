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
 * Servlet implementation class BoardEditFormServlet
 */
public class BoardEditFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEditFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardVO boardVO = null;
		//1. 데이터 추출
		String sSeq = request.getParameter("seq");
		int seq = 0;
		try{
			seq = Integer.parseInt(sSeq);
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		//2. 유효성 검사
		if (seq < 1){
			
		}
		//3. 처리
		BoardManager boardManager= new BoardManager();
		boardVO=boardManager.getBoard(seq);
		//4. 결과 값 출력

		PrintWriter out=response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<form action='./edit_process.do' method='post'>");
		
		out.println("<table border='1'>");
		out.println("<input type='hidden' id='seq' name='seq' value="+seq+">");
		out.println("<tr>");
		
		out.println("<td>");
		out.println("글번호");
		out.println("</td>");

		out.println("<td>");
		out.println(seq);
		out.println("</td>");
	
		out.println("</tr>");

		out.println("<tr>");
		
		out.println("<td>");
		out.println("제목");
		out.println("</td>");
		
		out.println("<td>");
		out.println("<input type='text' id='title' name='title' value='"+boardVO.getTitle()+"'>");
		out.println("</td>");
		
		out.println("</tr>");
		
		out.println("<tr>");
		
		out.println("<td>");
		out.println("내용");
		out.println("</td>");
		
		out.println("<td>");
		out.println("<input type='text' id='cotent' name='content' value='"+boardVO.getContent()+"'>");
		out.println("</td>");
		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2'>");
		
		out.println("<input type='submit' value='수정'>");
		out.println("</td>");
		
		out.println("</tr>");
		
		
		
		
		out.println("</table>");
		out.println("</form>");
		
		out.println("</BODY>");
		out.println("</HTML>");
	}

}
