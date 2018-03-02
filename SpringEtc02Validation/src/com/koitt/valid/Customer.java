package com.koitt.valid;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull				// Null 값 허용 안됨
	@Length(max=10)			// 길이가 최대 10 까지만 허용
	private String name;
	
	@Range(min=19, max=50)	// 19~50까지만 입력가능
	private Integer age;
	
	
	public Customer() {}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
