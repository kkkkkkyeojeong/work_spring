<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 수정</title>
</head>
<body>
	<h1>도서 수정</h1>
	<form action="<c:url value='/book/book-modify.do' />" method="post" enctype="multipart/form-data">
		<div>사용자 번호: ${ book.userNo }</div>
		<div>도서 번호: ${ book.isbn }</div>
		<div>
			<label>제목<input type="text" name="title" value="${ book.title }"></label>
		</div>
		<div>
			<label>저자<input type="text" name="author" value="${ book.author }"></label>
		</div>
		<div>
			<label>출판사<input type="text" name="publisher" value="${ book.publisher }"></label>
		</div>
		<div>
			<label>가격<input type="number" name="price" value="${ book.price }"></label>
		</div>
		<div>
			<label>내용</label>
			<textarea name="description">${ book.description }</textarea>
		</div>
		
		<div>
			<label>첨부파일<input type="file" name="attachment"></label>
		</div>
		
		<input type="hidden" name="isbn" value="${ book.isbn }">
		<input type="submit" value="도서 수정">
		<input type="reset" value="입력한 내용 삭제"><br>
		<a href="<c:url value='/book/book-list.do'/>">도서 목록으로 이동</a>
		
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
</body>
</html>