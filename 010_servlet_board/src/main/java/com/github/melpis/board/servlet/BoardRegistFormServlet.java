package com.github.melpis.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<FORM action = './regist_process.do' method='post'>");
		out.println("<table boder='1'>");
		out.println("<tr>");
		out.println("<td>");
		out.println("제목");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type='text' id='title' name='title' value='' size='' maxlength=''/>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("내용");
		out.println("</td>");
		out.println("<td>");
		out.println("<textarea row='10' col='20' id='content' name='content' ></textarea>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2'>");
		out.println("<input type='submit' value='전송'/>");
		out.println("</td>");

		out.println("</tr>");
		out.println("</table>");
		out.println("</FORM>");


		out.println("</BODY>");
		out.println("</HTML>");
	}

}
