<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 등록</title>
</head>
<body>
	<h1>도서 등록</h1>
	<form action="book-add.do" method="post" enctype="multipart/form-data">
		<div>
			<label>제목<input type="text" name="title"></label>
		</div>
		<div>
			<label>저자<input type="text" name="author"></label>
		</div>
		<div>
			<label>출판사<input type="text" name="publisher"></label>
		</div>
		<div>
			<label>가격<input type="number" name="price"></label>
		</div>
		<div>
			<label>내용</label>
			<textarea name="description"></textarea>
		</div>
		
		<div>
			<label>첨부파일<input type="file" name="attachment"></label>
		</div>
		
		<input type="submit" value="도서 작성">
		<input type="reset" value="내용 전체 삭제"><br>
		<a href="book-list.do">도서 목록으로 이동</a>
	</form>

</body>
</html>