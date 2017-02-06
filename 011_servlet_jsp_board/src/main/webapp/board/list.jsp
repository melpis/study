<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.List"%>
<%@page import="com.github.melpis.board.vo.BoardVO"%>
<%
    
	List<BoardVO> returnBoardList = (List<BoardVO>)request.getAttribute("returnBoardList");
 	BoardVO paramBoard= (BoardVO)request.getAttribute("paramBoard");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
body,div,table{ margin: 0;padding: 0; font-size: 14px;}

#first{font-size: 12px;}
#last{font-size: 12px;}


#wrap{width: 800px;margin: 0 auto;}

#top{
width: 800px;
height: 150px;
background: #000000 none repeat scroll 0 0;
margin-bottom: 20px;

}

#content{
width:800px;
float:left;
}


#leftSideBar{
width:180px;
float:left;
margin-right: 20px;


}
#rightSideBar{
width:500px;
float:right;
}

#footer{
width:800px;
float:left;
height: 100px;
margin-top: 15px;
background: #000000 none repeat scroll 0 0;
}

#leftTop{
width:180px; height:175px; background:#000; margin-bottom:10px;
}
#leftBottom{
width:180px; height:265px; background:#000;
}
#rightTop{
width:600px; height: 90px; background:#000; margin-bottom: 10px;
float: right;
}
#rightBottom{
width:600px;   
float: right;
}
</style>

</head>
<body>
<div id="wrap">
		<div id="top"></div>
		
			<div id="leftSideBar">
				<div id="leftTop"></div>
				<div id="leftBottom"></div>
			</div>
			<div id="rightSideBar">
				<div id="rightTop"></div>
				<div id="rightBottom">
		<table width="600">
		<tr align="center" >
			<td width="50px">글번호</td>
			<td width="400px">제목</td>
			<td width="100px">등록일</td>
			<td width="50px">조회수</td>
		</tr>
		<tr>
			<td colspan="4"><hr></hr></td>
		</tr>
		<%if(returnBoardList.size() < 1){ %>
		<tr>
			<td colspan="4">리스트 없음</td>
		</tr>
		<% }%>
	
		<%for(int indexI=0;indexI < returnBoardList.size(); indexI++){ 
			BoardVO boardVO= returnBoardList.get(indexI);	
		%>
		
		<tr align="center">
			<td><%=boardVO.getSeq()%> </td>
			<td><a href="./read.do?seq=<%=boardVO.getSeq()%>"><%=boardVO.getTitle() %> </a></td>
			<td><%=boardVO.getRegistDate() %> </td>
			<td><%=boardVO.getReadCount() %> </td>
		
		</tr>
		<tr>
			<td colspan="4"><hr></hr></td>
		</tr>	
	
	<%} %>
		<tr>
			<td colspan="4" align="right">
				<input type="button" value="등록" onclick="location.href='./regist_form.do'">
				
			</td>
	
		</tr>
		
		<tr>
			<td align="center" colspan="5">
				<a  id="first" href="list.do?page=<%=paramBoard.getFirstPage()%>">◀◀</a>
			
			
				<%if(paramBoard.getPage() == 1) {%>
					◀
				<%} else{%>
				<a href="list.do?page=<%=paramBoard.getPrevPage()%>">◀</a>
				<%} %>
				
				
				<%for (int indexI=paramBoard.getStartPage();indexI<=paramBoard.getEndPage();indexI++){ %>
				<a href="list.do?page=<%=indexI%>">[<%=indexI%>]</a>
				<%} %>
				
				<%if(paramBoard.getTotalpage()==paramBoard.getPage()){ %>
				▶
				<%}else{ %>
				<a href="list.do?page=<%=paramBoard.getNextPage()%>">▶</a>
				<%} %>
				
				<a id="last" href="list.do?page=<%=paramBoard.getTotalpage()%>">▶▶</a>
			</td>
		</tr>
	</table>
	
				
				</div>
			</div>
			
		<div id="footer"></div>
	</div>
	
	
</body>
</html>