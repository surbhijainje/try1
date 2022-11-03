package com.rest.validation;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Parameter {

	
	
	
	public void single_query_parameter() {
		
	   RestAssured.
	            given().baseUri("https://postman-echo.com").
	            
	            queryParam("foo","bar").
	           // param("foo", "bar").
	            
	            log().all().
	           
	            when().get("/get").
	            then()
	            .assertThat().statusCode(200);
	
	}
	
	
	public void multiple_query_parameter() {
		
		HashMap<String,String> queryParams=new HashMap<>();
		queryParams.put("foo1", "bar1");
		queryParams.put("foo2", "bar2");
		
	   RestAssured.
	            given().baseUri("https://postman-echo.com").
	            queryParams(queryParams).
	            
	            log().all().
	           
	            when().get("/get").
	            then()
	            .assertThat().statusCode(200);
	
	}
	
	
	
	public void multiplevalue_query_parameter() {
		
		/*HashMap<String,String> queryParams=new HashMap<>();
		queryParams.put("foo1", "bar1");
		queryParams.put("foo2", "bar2"); */
		
	   RestAssured.
	            given().baseUri("https://postman-echo.com").
	            queryParam("foo", "bar","bar2","bar3").
	            
	            log().all().
	           
	            when().get("/get").
	            then()
	            .assertThat().statusCode(200);
	
	}
	
	@Test
	public void path_parameter() {
		
		
	   RestAssured.
	            given().baseUri("https://reqres.in/").
	            pathParam("userId",2).
	           
	            log().all().
	           
	            when().get("/api/users/{userId}/{userId1}").
	            then()
	            .assertThat().statusCode(200);
	
	}
}
