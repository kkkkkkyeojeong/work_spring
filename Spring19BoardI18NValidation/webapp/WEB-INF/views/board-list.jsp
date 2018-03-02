<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="blist" /></title>
</head>
<body>
	<h1><spring:message code="blist" /></h1>
	
	<div>Language : ${ pageContext.response.locale }</div>
	<a href="board-list.do?lang=ko"><spring:message code="label.ko" /></a>
	<a href="board-list.do?lang=en"><spring:message code="label.en" /></a><br><br>
	
	<a href="<c:url value='/board/board-add.do' />"><spring:message code="write" /></a>
	<table>
		<thead>
			<tr>
				<th><spring:message code="no" /></th>
				<th><spring:message code="title" /></th>
				<th><spring:message code="userNo" /></th>
				<th><spring:message code="regdate" /></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ list }" var="board">
				<tr>
					<td>${ board.no }</td>
					<td><a href="<c:url value='/board/board-detail.do?no=${ board.no }' />">${ board.title }</a></td>
					<td>${ board.users.name } (${ board.users.email })</td>
					<td>${ board.regdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value='/index.do' />"><spring:message code="home" /></a>
	
</body>
</html>