package com.koitt.model;

import java.util.List;

public class Owner {
	
	private String ownerName;
	private List<Pet> petlist;

	public Owner() {}
	
	public Owner(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<Pet> getPetlist() {
		return petlist;
	}

	public void setPetlist(List<Pet> petlist) {
		this.petlist = petlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Owner [ownerName=");
		builder.append(ownerName);
		builder.append(", petlist=");
		builder.append(petlist);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
