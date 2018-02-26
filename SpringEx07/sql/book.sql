DROP TABLE book;
DROP TABLE users_authority;
DROP TABLE authority;
DROP TABLE users;

CREATE TABLE users (
	no			INT 			NOT NULL	 PRIMARY KEY	AUTO_INCREMENT,
	email		VARCHAR(255)	NOT NULL,
	password 	VARCHAR(255)	NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	attachment	VARCHAR(255),
	UNIQUE (email)
);

# 사용자 권한 정의한 테이블 
CREATE TABLE authority(
	id		INT 			NOT NULL	PRIMARY KEY,
	name	VARCHAR(30)		NOT NULL
);

# 사용자 번호와 사용자 권한 아이디값을 연결하는 테이블
CREATE TABLE users_authority(
	users_no		INT 	NOT NULL,
	authority_id	INT		NOT NULL,
	FOREIGN KEY (users_no) REFERENCES users (no),
	FOREIGN KEY (authority_id) REFERENCES authority (id)
);

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

# 권한 입력
INSERT INTO authority (id, name) 
	VALUES (10, 'ADMIN');
INSERT INTO authority (id, name) 
	VALUES (20, 'USER');

INSERT INTO users (email, password, name, attachment) 
	VALUES ('admin@naver.com', '$2a$10$nGNPzkcfRqs.0r4T7tuzf.qzn/c.LMUfa4MxzcyJym5NyHFb0AvPq', '홍길동', NULL);
INSERT INTO users (email, password, name, attachment) 
	VALUES ('user1@naver.com', '$2a$10$nGNPzkcfRqs.0r4T7tuzf.qzn/c.LMUfa4MxzcyJym5NyHFb0AvPq', '김철수', NULL);
INSERT INTO users (email, password, name, attachment) 
	VALUES ('adminuser@naver.com', '$2a$10$nGNPzkcfRqs.0r4T7tuzf.qzn/c.LMUfa4MxzcyJym5NyHFb0AvPq', '박영희', NULL);

INSERT INTO users_authority VALUES (1, 10);	# 관리자에게 관리자 권한 부여
INSERT INTO users_authority VALUES (2, 20);	# 일반인에게 사용자 권한 부여
INSERT INTO users_authority VALUES (3, 10);	# 관리일반에게 관리자 권한 부여
INSERT INTO users_authority VALUES (3, 20);	# 관리일반에게 사용자 권한 부여
	
INSERT INTO book (title, author, publisher, price, description, user_no, attachment) 
	values ('제목1', '저자1', '출판사1', 15000, '내용1', '1', NULL);
INSERT INTO book (title, author, publisher, price, description, user_no, attachment) 
	values ('제목2', '저자2', '출판사2', 10000, '내용2', '1', NULL);
	

# 1.users_authority 테이블과 authority 테이블을 EQUI JOIN 하는 SQL문
SELECT users_authority.users_no, authority.id, authority.name
FROM users_authority, authority
WHERE users_authority.authority_id = authority.id;
	
# 2. 1번에서 조인한 결과 테이블과 users 테이블을 EQUI JOIN 하는 SQL문
SELECT u.no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname"
FROM users u, 
	(SELECT users_authority.users_no, authority.id, authority.name
	FROM users_authority, authority
	WHERE users_authority.authority_id = authority.id) ua
WHERE u.no = ua.users_no;

# 3. 2번 결과에서 한 사용자에 대한 정보만 가져오는 SQL문(2번 + AND u.no = #{no})
SELECT u.no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname"
FROM users u, 
	(SELECT users_authority.users_no, authority.id, authority.name
	FROM users_authority, authority
	WHERE users_authority.authority_id = authority.id) ua
WHERE u.no = ua.users_no AND u.no = 1;

# 4. book 테이블과 users 테이블 EQUI JOIN
SELECT b.isbn, b.title, b.author, b.publisher, b.price, b.description, b.user_no, b.attachment, 
	u.email, u.name, u.attachment as "uattachment"
FROM book b, users u 
WHERE b.user_no = u.no ORDER BY b.isbn DESC;

# 5. 4번 sQL문에서 하나의 게시물을 선택하기 위한 SQL문
SELECT b.isbn, b.title, b.author, b.publisher, b.price, b.description, b.user_no, b.attachment,
	u.email, u.name, u.attachment as "uattachment"
FROM book b, users u 
WHERE b.user_no = u.no AND b.isbn = 2 ORDER BY b.isbn DESC;

# 6. 사용자 번호 3번 유저와 같이 관리자 권한과 일반 사용자 권한을 함께 입력할 경우 
INSERT ALL
	INTO users_authority(users_no, authority_id)
 	VALUES (3, 10)
 	INTO users_authority(users_no, authority_id)
 	VALUES (3, 20)
SELECT * FROM DUAL;
 
# 6-1. 6번을 MySQL 버전으로 변경
INSERT INTO users_authority(users_no, authority_id) VALUES
(8, 10),
(8, 20);

SELECT * FROM book;

