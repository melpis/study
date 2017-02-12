<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ�� ��� ��</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">
function validate(objFrm) {
	//1. ������ ����
	var userId = objFrm.userId.value;
	var userPw = objFrm.userPw.value;
	var userPwConfirm = objFrm.userPwConfirm.value;
	var userName = objFrm.userName.value;
	//2. ��ȿ�� �˻�
		if (userId == null
				|| userId.length < 6
				|| userId.length > 20) {
			// ���� ó��
			alert("���̵� �ùٸ��� �ʽ��ϴ�.");
			objFrm.userId.focus();
			return false;
		}
		if (userPw == null
				|| userPw.length < 6
				|| userPw.length > 20) {
			// ���� ó��
			alert("�н����尡 �ùٸ��� �ʽ��ϴ�.");
			objFrm.userPw.focus();
			return false;
		}
		if (userPw != userPwConfirm) {
			// ���� ó��
			alert("�н������ �н����� Ȯ���� ��ġ���� �ʽ��ϴ�.");
			objFrm.userPw.focus();
			return false;
		}
		if (userName == null
				|| userName.length < 6
				|| userName.length > 20) {
			// ���� ó��
			alert("������ �ùٸ��� �ʽ��ϴ�.");
			objFrm.userName.focus();
			return false;
		}

	//3. ó��
	//4. ��� ���
	return true;	
}
</script>

</head>
<body style="margin-top: 0px; margin-left: 0px;">
<table width="970" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<!-- TOP ���� -->
			<%@include file="../include/top.jsp" %>
			<!-- TOP �� -->
		</td>
	</tr>
	<tr>
		<td width="250" valign="top" bgcolor="#f7f7f7" style="padding-top: 10px;">
			<!-- LEFT ���� -->
			<%@include file="../include/left.jsp" %>
			<!-- LEFT �� -->
		</td>
		<td width="740" align="center">
			<!-- ���� ���� -->
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
								<td align="left">���̵�</td>
								<td align="left">
									<input type="text" id="userId" name="userId" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left">�н�����</td>
								<td align="left">
									<input type="password" id="userPw" name="userPw" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left">�н����� Ȯ��</td>
								<td align="left">
									<input type="password" id="userPwConfirm" name="userPwConfirm" value="" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left">����</td>
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
		<!-- ���� �� -->
		</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#b5d7ef">
			<!-- BOTTOM ���� -->
			<%@include file="../include/bottom.jsp" %>
			<!-- BOTTOM �� -->
		</td>
	</tr>
</table>
</body>
</html>
