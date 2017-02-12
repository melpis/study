<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시물 상세 조회</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body style="margin-top: 0px; margin-left: 0px;">
<table width="970" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<!-- TOP 시작 -->
			<%@include file="../include/top.jsp" %>
			<!-- TOP 끝 -->
		</td>
	</tr>
	<tr>
		<td width="250" valign="top" bgcolor="#f7f7f7" style="padding-top: 10px;">
			<!-- LEFT 시작 -->
			<%@ include file="../include/left.jsp" %>
			<!-- LEFT 끝 -->
		</td>
		<td width="740" align="center">
			<!-- 본문 시작 -->
			<table width="710" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td>
						<img src="/images/contents_title_01.gif" width="711" height="86" />
					</td>
				</tr>
				<tr>
					<td height="30">&nbsp;</td>
				</tr>
				<tr>
					<td align="center" valign="top">
					<table width="690" border="0" cellpadding="10" cellspacing="0" style="border: solid 1px #d6d7d6;">
						<tr align="left">
							<td>제목 : ${returnBoard.title}</td>
						</tr>
						
						<tr>
							<td align="right">
								작성자 : ${returnBoard.member.userId} <br/>  작성일: ${returnBoard.registDate}
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<c:forEach var="attachfile" items="${returnAttacfilelist}">
									파일 : <a href="/attachfile/download.do?seq=${attachfile.seq }"><b>${attachfile.fileName}</b></a> 다운수 : ${attachfile.downloadCount}
									<br/><br/>
								</c:forEach>
							</td>
						</tr>
						
						<tr align="left">
							<td height="300" valign="top">
								내용 : ${returnBoard.content} 
							</td>
						</tr>
						<tr>
							<td align="right">
								<b>조회수 <font color="#FF0000">${returnBoard.readCount}</font></b>
							</td>
						</tr>
						<tr>
							<td align="right">
								
								<img src="/images/button_edit.gif" width="67" height="30"  onclick="location.href='./edit.do?page=${paramBoard.page}&seq=${returnBoard.seq}'" style="cursor:pointer;"/>
								<img src="/images/button_delete.gif" width="67" height="30" onclick="location.href='./delete.do?page=${paramBoard.page}&seq=${returnBoard.seq}'" style="cursor:pointer;"/>
							</td>
						</tr>
					</table>
					<br />
					
					
					
					<table width="690" border="0" cellpadding="10" cellspacing="0">
						<tr>
							<td align="right">
								<a href="./list.do?page=${paramBoard.page}'">
								<img src="/images/button_list.gif" width="43" height="14" border="0" />
								</a>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="70">
						<iframe src="/comment/comment.do?boardSeq=${returnBoard.seq}" id="idCommentList" width="773" height="100%" frameborder="0" scrolling="no"  marginheight="0" marginwidth="0" style="height: 1246px; width: 773px;">
							
						
						</iframe>
					</td>
				</tr>
				
				<tr>
					<td height="70">&nbsp;</td>
				</tr>
				
				
			</table>
		<!-- 본문 끝 -->
		</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#b5d7ef">
			<!-- BOTTOM 시작 -->
			<%@ include file="../include/bottom.jsp" %>
			<!-- BOTTOM 끝 -->
		</td>
	</tr>
</table>
</body>
</html>
