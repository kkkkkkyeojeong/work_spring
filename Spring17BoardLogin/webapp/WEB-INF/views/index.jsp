<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>홈페이지</title>
</head>
<body>
	<h1>홈페이지</h1>
	<h3>만나서 반갑습니다~!!!</h3>
	<br>
	<a href="board/board-list.do">글 목록으로 이동</a><br>
	<a href="admin/users-list.do">사용자 목록으로 이동</a>
	
	<p>
	<c:if test="${ isLogin == 'false' }">
		<a href="join.do">회원가입</a><br>
		<a href="login.do">로그인</a>
	</c:if>
	
	<c:if test="${ isLogin == 'true' }">
		<a href="logout.do">로그아웃</a>
	</c:if>
	</p>

</body>
</html>