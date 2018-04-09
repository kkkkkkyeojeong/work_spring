<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action="<c:url value='/join.do' />" method="post" enctype="multipart/form-data">
		<div>
			<label>이메일<input type="email" name="email"></label>
		</div>
		<div>
			<label>비밀번호<input type="password" name="password"></label>
		</div>
		<div>
			<label>이름<input type="text" name="name"></label>
		</div>
		<div>
			<label>첨부파일<input type="file" name="attachment"></label>
		</div>
		
		<input type="submit" name="회원가입">
		<a href="index.do">취소</a>
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	
</body>
</html>