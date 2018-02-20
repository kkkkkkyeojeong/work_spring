DROP TABLE book;

CREATE TABLE book (
	isbn	 	INT 			NOT NULL 	PRIMARY KEY AUTO_INCREMENT,
	title 		VARCHAR(30) 	NOT NULL,
	author 		VARCHAR(30) 	NOT NULL,
	publisher	VARCHAR(30) 	NOT NULL,
	price 		INT 			NOT NULL,
	description VARCHAR(255)	NOT NULL,
	attachment VARCHAR(255)
);

INSERT INTO book (title, author, publisher, price, description, attachment) 
	values ('제목1', '저자1', '출판사1', 15000, '내용1', NULL);
INSERT INTO book (title, author, publisher, price, description, attachment) 
	values ('제목2', '저자2', '출판사2', 10000, '내용2', NULL);

select * from book;
