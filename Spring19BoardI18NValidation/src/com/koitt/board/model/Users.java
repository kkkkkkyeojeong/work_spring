package com.koitt.board.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 8182883476282750550L;
	
	private Integer no;
	private String email;
	private String password;
	private String name;
	private List<Board> boardlist;		// 해당 사용자의 게시물 목록
	private String attachment;			// 프로필 사진 파일명
	private Set<Authority> authorities;	// 해당 사용자의 권한 목록
	
	// 1. 기본생성자
	public Users() {}

	// 2. 생성자
	public Users(Integer no, String email, String password, String name, String attachment) {
		super();
		this.no = no;
		this.email = email;
		this.password = password;
		this.name = name;
		this.attachment = attachment;
	}
	
	/*
	 * 사용자 한명은 게시물을 여러개 가질 수 있는 일대다(1:N) 관계이므로 위와 같이 List 필드로 표현한다.
	 */

	// 3. getter, setter
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Board> getBoardlist() {
		return boardlist;
	}

	public void setBoardlist(List<Board> boardlist) {
		this.boardlist = boardlist;
	}
	
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	// 4. HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardlist == null) ? 0 : boardlist.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		return result;
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Users)) {
			return false;
		}
		Users other = (Users) obj;
		if(this.no.equals(other.no)) {
			return true;
		}
		return false;
	}

	// 6. toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Users [no=");
		builder.append(no);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append(", boardlist=");
		builder.append(boardlist);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append("]");
		return builder.toString();
	}

	
	


	
	
	
	
	
	
	
	

}
