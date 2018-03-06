package com.koitt.model;

public class Domain {

	private Integer id;
	private String domain;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Domain [id=");
		builder.append(id);
		builder.append(", domain=");
		builder.append(domain);
		builder.append("]");
		return builder.toString();
	}
	
	

}
