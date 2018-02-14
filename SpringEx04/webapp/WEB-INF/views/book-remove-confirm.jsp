<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 삭제 확인</title>
</head>
<body>
	<h1>도선 삭제 확인</h1>
	<p>정말로 ${ isbn }번 글을 삭제하시겠습니까?</p>

	<form action="book-remove.do" method="post">
		<input type="hidden" name="isbn" value="${ isbn }">
		<input type="submit" value="삭제하기">
	</form>
	<a href="book-list.do">글 목록으로 이동</a>

</body>
</html>