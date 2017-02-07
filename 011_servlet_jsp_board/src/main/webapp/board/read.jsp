<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
BoardVO returnBoard=(BoardVO)request.getAttribute("returnBoard");

%>   
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.github.melpis.board.vo.BoardVO"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		
		<tr>
			<%if (returnBoard == null){ %>
			<td colspan="5">없음 </td>
			<%} %>
			
			<td><%=returnBoard.getSeq() %></td>
			<td><%=returnBoard.getTitle() %></td>
			<td><%=returnBoard.getContent() %></td>
			<td><%=returnBoard.getRegistDate() %></td>
			<td><%=returnBoard.getReadCount() %></td>
		</tr>
		
		<tr>
			<td colspan="6" align="right">
			
				<input type="button" value="수정" onclick="location.href='./edit_form.do?seq=<%=returnBoard.getSeq() %>'"/>
				&nbsp;
				<input type="button" value="삭제" onclick="location.href='./delete.do?seq=<%=returnBoard.getSeq() %>'"/>
				&nbsp;
				<input type="button" value="리스트" onclick="location.href='./list.do?page=<%=returnBoard.getPage() %>'"/>
			</td>
		
		</tr>
	</table>
</body>
</html>