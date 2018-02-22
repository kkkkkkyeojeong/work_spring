DROP TABLE book;
DROP TABLE users;

CREATE TABLE users (
	no			INT 			NOT NULL	 PRIMARY KEY	AUTO_INCREMENT,
	email		VARCHAR(255)	NOT NULL,
	password 	VARCHAR(255)	NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	attachment	VARCHAR(255),
	UNIQUE (email)
);
DESC book;
CREATE TABLE book (
	isbn	 	INT 			NOT NULL 	PRIMARY KEY AUTO_INCREMENT,
	title 		VARCHAR(30) 	NOT NULL,
	author 		VARCHAR(30) 	NOT NULL,
	publisher	VARCHAR(30) 	NOT NULL,
	price 		INT 			NOT NULL,
	description VARCHAR(255)	NOT NULL,
	user_no 	INT	 			NOT NULL,
	attachment VARCHAR(255),
	FOREIGN KEY (user_no) REFERENCES users (no)
);

INSERT INTO users (email, password, name, attachment) 
	VALUES ('aaa@naver.com', '1234', '홍길동', NULL);
INSERT INTO users (email, password, name, attachment) 
	VALUES ('bbb@naver.com', '5678', '김철수', NULL);
INSERT INTO users (email, password, name, attachment) 
	VALUES ('ccc@naver.com', '0000', '박영희', NULL);

INSERT INTO book (title, author, publisher, price, description, user_no, attachment) 
	values ('제목1', '저자1', '출판사1', 15000, '내용1', '1', NULL);
INSERT INTO book (title, author, publisher, price, description, user_no, attachment) 
	values ('제목2', '저자2', '출판사2', 10000, '내용2', '1', NULL);

select * from book;
SELECT * FROM users;