<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="loginpage" /></title>
</head>
<body>
	<h1><spring:message code="login" /></h1>
	<div>Language : ${ pageContext.response.locale }</div>
	<a href="login.do?lang=ko"><spring:message code="label.ko" /></a>
	<a href="login.do?lang=en"><spring:message code="label.en" /></a><br>
	
	<form action="<c:url value='/login-processing' />" method="post">
		<input type="email" name="email" placeholder="<spring:message code="email" />" required><br>
		<input type="password" name="password" placeholder="<spring:message code="password" />" required><br><br>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		<input type="submit" value="<spring:message code="login" />">
	</form>
	<br>
	<p>
		<a href="<c:url value='/join.do' />"><spring:message code="join" /></a>
	</p>
	<a href="index.do"><spring:message code="home" /></a>
	
	<c:if test="${ param.error == 'login' }">
		<p style="color:#FF0000"><spring:message code="idpserror" /></p>
	</c:if>
	
	<c:if test="${ param.logout == 'true' }">
		<p style="color:#FF0000"><spring:message code="logoutdone" /></p>
	</c:if>
	
</body>
</html>