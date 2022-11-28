package com.rest.pojo.complexcollectiondata;

import java.util.List;

public class Folder {
	
	private String name;
	List<Requestroot> item;
	
	public Folder() {
	}
	
	public Folder(String name,List<Requestroot> item) {
		
		this.name=name;
		this.item=item;
		}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Requestroot> getItem() {
		return item;
	}
	public void setRequestRoot(List<Requestroot> item) {
		this.item = item;
	}
	
}
