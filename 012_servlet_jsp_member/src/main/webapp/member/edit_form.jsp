<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
//1. ������ ����
//2. ��ȿ�� �˻�
//3. ó��
//4. ��� ���
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խù� ��� ��</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">
function validate(objFrm) {
	//1. ������ ����
	//2. ��ȿ�� �˻�
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
			<!-- TOP �� -->
		</td>
	</tr>
	<tr>
		<td width="250" valign="top" bgcolor="#f7f7f7" style="padding-top: 10px;">
			<!-- LEFT ���� -->
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
						<form name="frmRegist" method="post" action="edit.do" onsubmit="return validate(this);">
						<table width="690" border="0" cellpadding="10" cellspacing="0" style="border: solid 1px #d6d7d6;">
							<tr>
								<td align="left">���̵�</td>
								<td align="left">
									${paramMemer.userId}
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">�н�����</td>
								<td align="left" valign="top">
									<input type="password" id="userPw" name="userPw" value="" size="30" />
								</td>
							</tr>
							
							<tr>
								<td align="left" valign="top">�̸�</td>
								<td align="left" valign="top">
									${paramMemer.userName}
								</td>
							</tr>
							
							<tr>
								<td align="left" valign="top">������</td>
								<td align="left" valign="top">
									${paramMemer.registDate}
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">���� �α��� ��¥</td>
								<td align="left" valign="top">
									${paramMemer.lastVisitedDate}
								</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center">
									<input type="image" src="/images/button_edit.gif" width="67" height="30" />
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
			<table width="100%">
				<tr>
					<td>
						<img src="/images/copyright.gif" width="960" height="58" />
					</td>
				</tr>
			</table>
			<!-- BOTTOM �� -->
		</td>
	</tr>
</table>
</body>
</html>