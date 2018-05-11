CREATE TABLE book (
	isbn_no			INT				NOT NULL PRIMARY KEY AUTO_INCREMENT,
	isbn			VARCHAR(20)		NOT NULL,
	title			VARCHAR(255)	NOT NULL,
	author			VARCHAR(255)	NOT NULL,
	publisher		VARCHAR(255)	NOT NULL,
	attachment		VARCHAR(255),
	UNIQUE (isbn)
);

SELECT * FROM book;
DROP TABLE book;