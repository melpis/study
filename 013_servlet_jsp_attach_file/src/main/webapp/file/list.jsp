<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խù� ���</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />

<script type="text/javascript">
function check(){

	if(confirm("�ٿ�ε� �Ͻðڽ��ϱ�?"))
	{
		return true;
	}else{
		return false;
	}
}
function checkDel(seq){

	if(confirm("���� �Ͻðڽ��ϱ�?"))
	{
		location.href="delete.do?seq="+seq;
	}else{
		return false;
	}
}

</script>
</head>
<body style="margin-top: 0px; margin-left: 0px;">
<table width="970" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<!-- TOP ���� -->
			<%@ include file="../include/top.jsp" %>
			<!-- TOP �� -->
		</td>
	</tr>
	<tr>
		<td width="250" valign="top" bgcolor="#f7f7f7" style="padding-top: 10px;">
			<!-- LEFT ���� -->
			<%@ include file="../include/left.jsp" %>
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
						<table width="690" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="6">
									<table width="690" border="0" cellpadding="0" cellspacing="0" background="/images/board_title_bg.gif">
										<tr height="40">
											<td width="30" align="center">��ȣ</td>
											<td width="100" align="center" style="padding-left: 10px;">���ϸ�</td>
											<td width="60" align="center">���� ������</td>
											<td width="60" align="center">����Ʈ Ÿ��</td>
											<td width="60" align="center">�����</td>
											<td width="60" align="center">�ٿ�ε� Ƚ��</td>
										</tr>
									</table>
								</td>
							</tr>



						<c:forEach var="attachfile" items="${paramList}">
							<tr height="40">
								<td width="30" align="center">${attachfile.seq}</td>
								<td width="100" align="left" style="padding-left: 10px;">
									<img src="/images/icon_new.gif" width="27" height="14" />
									<a href="download.do?seq=${attachfile.seq}" onclick="return check();">${attachfile.fileName}</a>
									<input type="button" value="����" onclick="return checkDel(${attachfile.seq});">
								</td>
								<td width="60" align="center">${attachfile.fileSize}</td>
								<td width="60" align="center">${attachfile.contentType}</td>
								<td width="60" align="center">${attachfile.registDate}</td>
								<td width="60" align="center">${attachfile.downloadCount}</td>
								
							</tr>
							<tr>
								<td colspan="6" height="1" bgcolor="#d6d7d6"></td>
							</tr>
						</c:forEach>
							
							<tr>
								<td height="70" colspan="6">
									<table width="690" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td align="left">
											
		
											</td>
											<td align="right">
											
												<img src="/images/button_save.gif" width="81" height="31" border="0" onclick="location.href='./regist.do'" style="cursor:pointer;"/>
					
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
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
			<%@ include file="../include/bottom.jsp" %>
			<!-- BOTTOM �� -->
		</td>
	</tr>
</table>
</body>
</html>
