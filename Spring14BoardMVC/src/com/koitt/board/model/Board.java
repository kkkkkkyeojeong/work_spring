package com.koitt.board.model;

import java.io.Serializable;
import java.util.Date;

// Java Bean, VO(Value Object), DTO(Data Transfer Object)
public class Board implements Serializable{ 	// 7. implements Serializable

	// 8.
	private static final long serialVersionUID = 1L;
	private Integer no;		// 글번호
	private String title;	// 글 제목
	private String content; // 글 내용
	private Integer userNo;	// 사용자 번호
	private Date regdate;	// 작성일 

	// 1. 기본생성자
	public Board() {}

	// 2. 생성자(모든 필드 초기화)
	public Board(Integer no, String title, String content,Integer userNo, Date regdate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.userNo = userNo;
		this.regdate = regdate;
	}

	// 3. getter, setter
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

	// 4. HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((regdate == null) ? 0 : regdate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userNo == null) ? 0 : userNo.hashCode());
		return result;
	}

	// 5. equals
	@Override
	public boolean equals(Object obj) {
		// 4-1. 주소비교
		if (this == obj) {
			return true;
		}

		// 4-2. 타입체크
		if (!(obj instanceof Board)) {
			return false;
		}
		
		// 4-3. 글번호 비교하기 위해 다운캐스팅  
		Board other = (Board) obj;
		if (this.no.equals(other.no)) {
			return true;
		}
		return false;
	}

	// 6. toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [no=");
		builder.append(no);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", regdate=");
		builder.append(regdate);
		builder.append("]");
		return builder.toString();
	}

	
	

	
	
}















