<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խù� ���� ��</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">

function edit(){

	document.editForm.action="edit.do";
	document.editForm.submit();
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
						<form  method="post" name="editForm">
						<table width="690" border="0" cellpadding="10" cellspacing="0" style="border: solid 1px #d6d7d6;">
							<tr>
								<td align="left">����</td>
								<td align="left">
								<input type="hidden" name="page" value="${paramBoard.page}">
								<input type="hidden" name="seq" id="seq" value="${boardVO.seq}"/>
									
									<input type="text" id="title" name="title" value="${boardVO.title}" size="70" />
								</td>
							</tr>
							<tr>
								<td align="left" valign="top">����</td>
								<td align="left" valign="top">
									<textarea id="content" name="content" cols="60" rows="10">${boardVO.content}</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<img  src="/images/button_save.gif" width="67" height="30" onclick="edit();" style="cursor:pointer;" />
									<input type="button" value="���" onclick="location.href='page=${paramBoard.page}&seq=${boardVO.seq}'"/>
									
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
