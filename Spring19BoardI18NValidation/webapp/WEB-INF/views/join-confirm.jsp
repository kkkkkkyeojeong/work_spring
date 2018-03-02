<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="joindone" /></title>
</head>
<body>
	<h1>*** ${ name } <spring:message code="welcome" /> ***</h1>
	
	<a href="<c:url value='/index.do' />"><spring:message code="home" /></a>
	
	<div>Language : ${ pageContext.response.locale }</div>
	<a href="join-confirm.do?lang=ko"><spring:message code="label.ko" /></a>
	<a href="join-confirm.do?lang=en"><spring:message code="label.en" /></a><br><br>
	
</body>
</html>