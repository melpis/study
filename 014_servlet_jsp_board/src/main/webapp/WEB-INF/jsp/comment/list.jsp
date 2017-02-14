<%@ page language="java" contentType="text/xml; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<commentList>    
	<c:forEach var="comment" items="${commentList}">
	<comment>
		<seq>${comment.seq}</seq>
		<content>${comment.content}</content>
		<registDate>${comment.registDate}</registDate>
	</comment>
	</c:forEach>
</commentList>



    
    
    