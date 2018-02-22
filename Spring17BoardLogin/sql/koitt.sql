#DDL (Date Definition Language)
DROP TABLE board;
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


CREATE TABLE board (
	no 			INT 			NOT NULL 	PRIMARY KEY AUTO_INCREMENT,
	title 		VARCHAR(30) 	NOT NULL,
	content 	VARCHAR(255) 	NULL,
	user_no		INT	 			NOT NULL,
	regdate 	DATE 			NOT NULL,
	attachment 	VARCHAR(255), 
	FOREIGN KEY (user_no) REFERENCES users (no)
);

#DML(Data Manipulation Language)
# 권한 입력
INSERT INTO authority (id, name) 
	VALUES (10, 'ADMIN');
INSERT INTO authority (id, name) 
	VALUES (20, 'USER');

# 사용자 입력 (비밀번호 1234)
INSERT INTO users (email, password, name, attachment) 
	VALUES ('admin@naver.com', '$2a$10$UhoMeaytQ7ESjeKT6To7ce55PSIzH5WtuHkrPKyVx4KTkqiYbr2cq', '관리자', NULL);
INSERT INTO users (email, password, name, attachment) 
	VALUES ('user1@naver.com', '$2a$10$UhoMeaytQ7ESjeKT6To7ce55PSIzH5WtuHkrPKyVx4KTkqiYbr2cq', '일반인', NULL);
INSERT INTO users (email, password, name, attachment) 
	VALUES ('user2@naver.com', '$2a$10$UhoMeaytQ7ESjeKT6To7ce55PSIzH5WtuHkrPKyVx4KTkqiYbr2cq', '관리일반', NULL);

# 사용자 권한 부여
INSERT INTO users_authority VALUES (1, 10);	# 관리자에게 관리자 권한 부여
INSERT INTO users_authority VALUES (2, 20);	# 일반인에게 사용자 권한 부여
INSERT INTO users_authority VALUES (3, 10);	# 관리일반에게 관리자 권한 부여
INSERT INTO users_authority VALUES (3, 20);	# 관리일반에게 사용자 권한 부여
	
# 게시판 입력
INSERT INTO board (title, content, user_no, regdate, attachment) 
	values ('제목-1', '내용-1', '1', STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
INSERT INTO board (title, content, user_no, regdate, attachment) 
	values ('제목-2', '내용-2', '1', STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
INSERT INTO board (title, content, user_no, regdate, attachment) 
	values ('제목-3', '내용-3', '1', STR_TO_DATE('2018-02-01', '%Y-%m-%d'), NULL);
	
#DQL(Data Query Language)
SELECT * FROM board;
SELECT * FROM users;
SELECT * FROM authority;
SELECT * FROM users_authority;

		










