package com.rest.pojo.complexcollectiondata;

public class Requestroot {
	
	private String name;
	Request request;
	
	public Requestroot() {
		
	}
	
	public Requestroot(String name, Request request) {
		super();
		this.name = name;
		this.request = request;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	
	

}
