<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
BoardVO boardVO=(BoardVO)request.getAttribute("boardVO");
int seq = (Integer) request.getAttribute("seq");
%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.github.melpis.board.vo.BoardVO"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="./edit_process.do" method="post">
	<table border="1">
		<tr>	
			<td>
			<input type="hidden" id ="seq" name="seq" value="<%=seq%>"/>
			글번호
			</td>
			<td> <%=seq %></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" id="title" name="title"  size="" maxlength="" value="<%=boardVO.getTitle() %>"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="10" cols="30" name="content"><%=boardVO.getContent() %></textarea> </td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="수정"/></td>
		</tr>
	</table>
	</form>
</body>
</html>