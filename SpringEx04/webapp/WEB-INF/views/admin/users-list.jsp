<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 사용자 목록</title>
</head>
<body>
	<h1>도서 사용자 목록</h1>
	<a href="index.html">홈으로 이동</a>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이메일</th>
				<th>이름</th>
				<th>첨부파일</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ list }" var="users">
				<tr>
					<td>${ users.no }</td>
					<td>${ users.email }</td>
					<td>${ users.name }</td>
					<td><img width="100" height="100" src="${ uploadPath }/${ users.attachment}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>