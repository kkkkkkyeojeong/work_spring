package com.koitt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="record")
public class Report {
		
		private Integer refId;
		private String name;
		private Integer age;
		private Date dob;
		private BigDecimal income;
		
		
		@XmlAttribute(name="refId")
		public Integer getRefId() {
			return refId;
		}
		public void setRefId(Integer refId) {
			this.refId = refId;
		}
		
		@XmlElement(name="name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@XmlElement(name="age")
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
	
		@XmlJavaTypeAdapter(JaxbDateAdapter.class)
		@XmlElement
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		
		@XmlJavaTypeAdapter(JaxBigDecimalAdapter.class)
		@XmlElement
		public BigDecimal getIncome() {
			return income;
		}
		public void setIncome(BigDecimal income) {
			this.income = income;
		}

}
