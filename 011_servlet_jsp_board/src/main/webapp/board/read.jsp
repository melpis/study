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
			<td>�۹�ȣ</td>
			<td>����</td>
			<td>����</td>
			<td>�����</td>
			<td>��ȸ��</td>
		</tr>
		
		<tr>
			<%if (returnBoard == null){ %>
			<td colspan="5">���� </td>
			<%} %>
			
			<td><%=returnBoard.getSeq() %></td>
			<td><%=returnBoard.getTitle() %></td>
			<td><%=returnBoard.getContent() %></td>
			<td><%=returnBoard.getRegistDate() %></td>
			<td><%=returnBoard.getReadCount() %></td>
		</tr>
		
		<tr>
			<td colspan="6" align="right">
			
				<input type="button" value="����" onclick="location.href='./edit_form.do?seq=<%=returnBoard.getSeq() %>'"/>
				&nbsp;
				<input type="button" value="����" onclick="location.href='./delete.do?seq=<%=returnBoard.getSeq() %>'"/>
				&nbsp;
				<input type="button" value="����Ʈ" onclick="location.href='./list.do?page=<%=returnBoard.getPage() %>'"/>
			</td>
		
		</tr>
	</table>
</body>
</html>