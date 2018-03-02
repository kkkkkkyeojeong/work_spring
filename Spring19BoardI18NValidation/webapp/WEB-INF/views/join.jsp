<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="join" /></title>
</head>
<body>
	<h1><spring:message code="join" /></h1>
	
	<div>Language : ${ pageContext.response.locale }</div>
	<a href="join.do?lang=ko"><spring:message code="label.ko" /></a>
	<a href="join.do?lang=en"><spring:message code="label.en" /></a><br><br>
	
	<form action="<c:url value='/join.do' />" method="post" enctype="multipart/form-data">
		<div>
			<label><spring:message code="email" />  <input type="email" name="email"></label>
		</div>
		<div>
			<label><spring:message code="password" />  <input type="password" name="password"></label>
		</div>
		<div>
			<label><spring:message code="name" />  <input type="text" name="name"></label>
		</div>
		<div>
			<label><spring:message code="attachment" />  <input type="file" name="attachment"></label>
		</div>
		
		<input type="submit" name="<spring:message code="join" />">
		<a href="index.do"><spring:message code="home" /></a>
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	
</body>
</html>