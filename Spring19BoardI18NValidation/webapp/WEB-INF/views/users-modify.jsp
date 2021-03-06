<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정 </title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form action="<c:url value='/users-modify.do' />" method="post" enctype="multipart/form-data">
		<span>이메일: ${ users.email }</span><br>

		<label>기존 비밀번호<input type="password" name="oldPassword"></label><br>

		<label>변경할 비밀번호<input type="password" name="newPassword"></label><br>

		<label>이름<input type="text" name="name" value="${ users.name }"></label><br>

		<label>첨부파일<input type="file" name="attachment"></label><br>
		<img width="50" height="50" alt="기존프로필"" src="${ uploadpath }/${ users.attachment }"><br>
		
		<input type="submit" name="회원가입">
		<a href="index.do">취소</a>
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	
	<c:if test="${ param.error == 'password' }">
		<div style="color:#ff0000">기존 비밀번호를 다시 입력해주세요.</div>
	</c:if>
	
</body>
</html>