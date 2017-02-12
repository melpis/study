<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String userId = (String)session.getAttribute("sessionUserId"); %>
			<table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#add7ef">
				<tr>
					<td align="center" style="border: solid 1px #94bace; background-color: #FFFFFF;">
						<table width="100%" border="0" cellpadding="5" cellspacing="0" background="/images/left_menu_bg.gif">
							<tr>
								<td height="32" align="left">
									<strong>API Center</strong><br/><br/>
									<c:if test="<%=userId != null %>">
										<%=userId%> 님 접속
									</c:if>
								</td>
							</tr>
						</table>
						<table width="90%" border="0" cellspacing="0" cellpadding="3">
							<tr>
								<td width="15" height="30">
									<img src="/images/left_menu_icon.gif" width="5" height="2"/>
								</td>
								
								<td align="left"><a href="/member/login.do">로그인</a></td>
								
							</tr>
							<tr>
								<td colspan="2" height="1" bgcolor="#dedbde"></td>
							</tr>
							<tr>
								<td width="15" height="30">
									<img src="/images/left_menu_icon.gif" width="5" height="2" />
								</td>
								<td align="left"><a href="/board/list.do">게시판</a></td>
							</tr>
							<c:if test="<%=userId != null %>">
							<tr>
								<td colspan="2" height="1" bgcolor="#dedbde"></td>
							</tr>
							<tr>
								<td width="15" height="30">
									<img src="/images/left_menu_icon.gif" width="5" height="2" />
								</td>
								<td align="left"><a href="/member/logout.do">로그아웃</a></td>
							</tr>
							</c:if>
							<tr>
								<td colspan="2" height="5"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
