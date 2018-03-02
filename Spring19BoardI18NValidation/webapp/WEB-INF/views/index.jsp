<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="homepage" /></title>
</head>
<body>
	<h1><spring:message code="homepage" /></h1>
	<h3><spring:message code="greeting" /></h3>
	
	<div>Language : ${ pageContext.response.locale }</div>
	<a href="index.do?lang=ko"><spring:message code="label.ko" /></a>
	<a href="index.do?lang=en"><spring:message code="label.en" /></a><br>
	
	<br>
	<a href="<c:url value='/board/board-list.do'/>"><spring:message code="blist" /></a><br>
	<a href="<c:url value='/admin/users-list.do'/>"><spring:message code="ulist" /></a>
	
	<p>
	<sec:authorize access="!hasRole('ADMIN') and !hasRole('USER')">
		<a href="<c:url value='/login.do'/>"><spring:message code="login" /></a>
		<a href="<c:url value='/join.do'/>"><spring:message code="join" /></a><br>
	</sec:authorize>

	<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
		<a href="<c:url value='/users-modify.do'/>"><spring:message code="profile" /></a>
		<a href="<c:url value='/logout.do'/>"><spring:message code="logout" /></a>
	</sec:authorize>
	</p>

</body>
</html>