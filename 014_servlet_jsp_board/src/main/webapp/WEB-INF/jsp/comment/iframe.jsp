<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시물 목록</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">


	var Comment = {page:1};
	var boardSeq = ${boardSeq};
	var userId = ${sessionUserId};
	
function loadCommentList(){
	//1. 데이터 추출
	//2. 유효성 검사
	//3. 처리
	//4. 결과 출력
	CommentManager.listCommentByBoardSeq(boardSeq,Comment,listSuccess);
}
function listSuccess(commentList){
	var objList= document.getElementById("commentList");
	for(var indexI =0 ; indexI < commentList.length-1; indexI++){
		var comment = commentList[indexI];
		var seq = comment.seq;			
		var content =comment.content;
		var registDate =comment.registDate;	
		var commentUserId = comment.member.userId;
		var html="";
		html+="<tr align='center'>";
		html+="		<td  width='40' >";
		html+= seq;
		html+="		</td>";
		html+="		<td  width='50'  >";
		html+= commentUserId;
		html+="		</td>";
		html+="		<td width='230'>";
		html+= content;
		html+="		</td>";
		html+="		<td width='70'>";
		html+= registDate;
		html+="		</td>";
		html+="		<td width='60'>";
		html+= "<input type='button' value='삭제' onclick=";
		html+= "deleteComment("+seq+","+commentUserId+"); ";		
		html+=	"/>";
		html+="		</td>";	
		html+="</tr>";


		objList.innerHTML +=html;
		
	}
	                    	
	
}

function deleteComment(seq,commentUserId){


	if(commentUserId == userId){
		CommentManager.removeComment(seq,delelteCallback);
	}else{
		alert("삭제 할수 없습니다.");
	}
}

function registCommnet()
{
	var content = document.getElementById("content").value;
	if(content == "")
	{
		alert("내용없음 ");
		document.getElementById("content").focus;
		return;
	}
	document.getElementById("content").disabled="disabled";
	document.getElementById("registButton").disabled="disabled";
	var comment = {content:content};
	
	CommentManager.registComment(userId,boardSeq,comment,registCallback);
	
	
}
function registCallback(){
	var objList= document.getElementById("commentList").innerHTML='';
	document.getElementById("content").value="";
	document.getElementById("content").disabled="";
	loadCommentList(boardSeq,Comment);
}
function delelteCallback(){
	var objList= document.getElementById("commentList").innerHTML='';
	loadCommentList(boardSeq,Comment);
}


</script>



</head>
<body onload="loadCommentList(${boardSeq});" >
						<table width="690" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="5">
									<table width="690" border="0" cellpadding="0" cellspacing="0" background="/images/board_title_bg.gif">
										<tr height="20">
											<td width="40" align="center">번호</td>
											<td width="50" align="center">작성자</td>
											<td width="230" align="center">내용</td>
											<td width="70" align="center">등록일</td>
											<td width="60" align="center">삭제</td>
										</tr>
									</table>
								</td>
							</tr>
							<tbody id="commentList" align="center">
							
							</tbody>
						
							<tr>
								<td colspan="5" height="1" bgcolor="#d6d7d6"></td>
							</tr>
							
							
							
				
							<tr>
								<td height="70" colspan="5">
									<table width="690" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
										<tr>
											<td align="left">
											<textarea rows="3" cols="72" id="content" name="content"></textarea>
											</td>
											<td align="right" valign="bottom">
					
												<img  id="registButton" src="/images/button_write.gif" width="81" height="31" border="0" onclick="registCommnet(${boardSeq});" style="cursor: pointer;"/>
												
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
				
			
			<!-- 본문 끝 -->
	
</body>
</html>