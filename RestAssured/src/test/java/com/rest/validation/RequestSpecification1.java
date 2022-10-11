package com.rest.validation;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class RequestSpecification1 {
	
	//RequestSpecification requestSpecification;
	
    //public static RequestSpecification xyz;
	
	//DefaultRequestSpecification
	@BeforeClass
	public void beforeClass() {
		
	
		
	/* requestSpecification= with().
				baseUri("https://api.getpostman.com").
		        header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d"); */
		
		RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
	    
		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification=requestSpecBuilder.build();
		
		
		
	            
	}
	
	@Test
	public void validate_statuscode() {
		
		Response response =get("/workspaces").then(). log().all().extract().response();
	  assertThat(response.statusCode(),is(equalTo(200)));      

	}
	
	
	@Test(enabled = false)
	public void validate_response_body() {
		
		
		Response response = get("/workspaces").then().log().all().extract().response();
		 assertThat(response.statusCode(),is(equalTo(200)));
		 assertThat(response.path("workspaces.name"), equalTo("APITESTING"));
		
		
	}

	
	
	

}
