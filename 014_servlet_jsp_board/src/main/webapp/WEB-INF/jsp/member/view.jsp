<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 정보 조회</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">
</script>

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
			<%@include file="../include/left.jsp" %>
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
							<tr>
								<td align="left">아이디</td>
								<td align="left">
									${memberVO.userId}
								</td>
							</tr>
							<tr>
								<td align="left">패스워드</td>
								<td align="left">
									${memberVO.userPw}
								</td>
							</tr>
							<tr>
								<td align="left">성명</td>
								<td align="left">
									${memberVO.userName}
								</td>
							</tr>
							<tr>
								<td align="left">가입일</td>
								<td align="left">
									${memberVO.registDate}
								</td>
							</tr>
							<tr>
								<td align="left">최근 로그인 날짜</td>
								<td align="left">
									${memberVO.lastVisitedDate}
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="button" value="수정" onclick="location.href='edit.do'">
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
				<tr>
					<td height="70">&nbsp;</td>
				</tr>
			</table>
		<!-- 본문 끝 -->
		<%@include file="../include/bottom.jsp" %>
			<!-- BOTTOM 끝 -->
		</td>
	</tr>
</table>
</body>
</html>
