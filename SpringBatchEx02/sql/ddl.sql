DROP TABLE IF EXISTS ex_report;

# 광고 
# 일자, 노출수, 클릭수, 수익
CREATE TABLE ex_report (
	refId		INT,
	name		VARCHAR(10),
	age			INT,
	dob			DATE,
	income		DECIMAL(9,2)
);

SELECT * FROM ex_report;