<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="inaccessible" /></title>
</head>
<body>
	<h1><spring:message code="inaccessible" /></h1>
	
	<div>Language : ${ pageContext.response.locale }</div>
	<a href="access-denied.do?lang=ko"><spring:message code="label.ko" /></a>
	<a href="access-denied.do?lang=en"><spring:message code="label.en" /></a><br><br>
	
	<div>
		<span>${ email } = <spring:message code="inaccessible" /></span>
	</div>
	
	<a href="<c:url value='/index.do' />"><spring:message code="home" /></a>
</body>
</html>