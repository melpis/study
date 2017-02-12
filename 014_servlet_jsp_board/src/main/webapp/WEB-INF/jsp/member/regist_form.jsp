<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 등록 폼</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">
function validate(objFrm) {
	//1. 데이터 추출
	var userId = objFrm.userId.value;
	var userPw = objFrm.userPw.value;
	var userPwConfirm = objFrm.userPwConfirm.value;
	var userName = objFrm.userName.value;
	//2. 유효성 검사
		if (userId == null
				|| userId.length < 6
				|| userId.length > 20) {
			// 예외 처리
			alert("아이디가 올바르지 않습니다.");
			objFrm.userId.focus();
			return false;
		}
		if (userPw == null
				|| userPw.length < 6
				|| userPw.length > 20) {
			// 예외 처리
			alert("패스워드가 올바르지 않습니다.");
			objFrm.userPw.focus();
			return false;
		}
		if (userPw != userPwConfirm) {
			// 예외 처리
			alert("패스워드와 패스워드 확인이 일치하지 않습니다.");
			objFrm.userPw.focus();
			return false;
		}
		if (userName == null
				|| userName.length < 6
				|| userName.length > 20) {
			// 예외 처리
			alert("성명이 올바르지 않습니다.");
			objFrm.userName.focus();
			return false;
		}

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
						<form name="frmRegist" method="post" action="/member/regist.do" onsubmit="return validate(this);">
						<table width="690" border="0" cellpadding="10" cellspacing="0" style="border: solid 1px #d6d7d6;">
							<tr>
								<td align="left">아이디</td>
								<td align="left">
									<input type="text" id="userId" name="userId" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left">패스워드</td>
								<td align="left">
									<input type="password" id="userPw" name="userPw" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left">패스워드 확인</td>
								<td align="left">
									<input type="password" id="userPwConfirm" name="userPwConfirm" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left">성명</td>
								<td align="left">
									<input type="text" id="userName" name="userName" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="image" src="/images/button_save.gif" width="67" height="30" />
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
			<%@include file="../include/bottom.jsp" %>
			<!-- BOTTOM 끝 -->
		</td>
	</tr>
</table>
</body>
</html>
