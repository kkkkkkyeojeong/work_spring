#DDL (Date Definition Language)
DROP TABLE board;
DROP TABLE users;

CREATE TABLE users (
	no			INT 			NOT NULL	 PRIMARY KEY	AUTO_INCREMENT,
	email		VARCHAR(255)	NOT NULL,
	password 	VARCHAR(255)	NOT NULL,
	name		VARCHAR(255)	NOT NULL,
	UNIQUE (email)
);

CREATE TABLE board (
	no INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(30) NOT NULL,
	content VARCHAR(255) NULL,
	user_no INT	 NOT NULL,
	regdate DATE NOT NULL,
	attachment VARCHAR(255), 
	FOREIGN KEY (user_no) REFERENCES users (no)
);

INSERT INTO users (email, password, name) 
	VALUES ('aaa@naver.com', '1234', '홍길동');
INSERT INTO users (email, password, name) 
	VALUES ('bbb@naver.com', '5678', '김철수');
INSERT INTO users (email, password, name) 
	VALUES ('ccc@naver.com', '0011', '박영희');

#DML(Data Manipulation Language)
INSERT INTO board (title, content, user_no, regdate, attachment) 
	values ('제목-1', '내용-1', '1', STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
INSERT INTO board (title, content, user_no, regdate, attachment) 
	values ('제목-2', '내용-2', '1', STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
INSERT INTO board (title, content, user_no, regdate, attachment) 
	values ('제목-3', '내용-3', '1', STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
	
#DQL(Data Query Language)
SELECT * FROM board;
