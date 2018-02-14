<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>책 목록</title>
</head>
<body>
	<h1>==== 도서 목록 ====</h1>
	<a href="book-add.do">도서 등록</a>
	
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>출판사</th>
				<th>가격</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ list }" var="book">
				<tr>
					<td>${ book.isbn }</td>
					<td><a href="book-detail.do?isbn=${ book.isbn }">${ book.title }</a></td>
					<td>${ book.author }</td>
					<td>${ book.publisher }</td>
					<td>${ book.price }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>