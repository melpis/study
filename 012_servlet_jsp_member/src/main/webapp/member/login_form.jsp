<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
//1. 데이터 추출
//2. 유효성 검사
//3. 처리
//4. 결과 출력
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">
function validate(objFrm) {
	//1. 데이터 추출
	//2. 유효성 검사
	//3. 처리
	//4. 결과 출력
	return true;	
}
</script>

</head>
<body style="margin-top: 0px; margin-left: 0px;">
<table width="970" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<!-- TOP 시작 -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="970" align="right" background="/images/top01_bg.gif">
						<img src="/images/top01_button.gif" width="248" height="35" />
					</td>
				</tr>
				<tr>
					<td>
						<img src="/images/top_img.jpg" width="970" height="130" />
					</td>
				</tr>
				<tr>
					<td>
						<img src="/images/top_menu_01.gif" width="970" height="46" />
					</td>
				</tr>
			</table>
			<!-- TOP 끝 -->
		</td>
	</tr>
	<tr>
		<td width="250" valign="top" bgcolor="#f7f7f7" style="padding-top: 10px;">
			<!-- LEFT 시작 -->
			<table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#add7ef">
				<tr>
					<td align="center" style="border: solid 1px #94bace; background-color: #FFFFFF;">
						<table width="100%" border="0" cellpadding="5" cellspacing="0" background="/images/left_menu_bg.gif">
							<tr>
								<td height="32" align="left">
									<strong>API Center</strong>
								</td>
							</tr>
						</table>
						<table width="90%" border="0" cellspacing="0" cellpadding="3">
							<tr>
								<td width="15" height="30">
									<img src="/images/left_menu_icon.gif" width="5" height="2"/>
								</td>
								<td align="left">Board 01</td>
							</tr>
							<tr>
								<td colspan="2" height="1" bgcolor="#dedbde"></td>
							</tr>
							<tr>
								<td width="15" height="30">
									<img src="/images/left_menu_icon.gif" width="5" height="2" />
								</td>
								<td align="left">Board 02</td>
							</tr>
							<tr>
								<td colspan="2" height="5"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
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
						<form name="frmRegist" method="post" action="login.do" onsubmit="return validate(this);">
						<table width="690" border="0" cellpadding="10" cellspacing="0" style="border: solid 1px #d6d7d6;">
							<tr>
								<td align="left">아이디</td>
								<td align="left">
									<input type="text" id="userId" name="userId" value="" size="30" />
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">패스워드</td>
								<td align="left" valign="top">
									<input type="password" id="userPw" name="userPw" value="" size="30" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="로그인" />
								</td>
							</tr>
						</table>
						</form>
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
			<table width="100%">
				<tr>
					<td>
						<img src="/images/copyright.gif" width="960" height="58" />
					</td>
				</tr>
			</table>
			<!-- BOTTOM 끝 -->
		</td>
	</tr>
</table>
</body>
</html>
