package com.rest.validation;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import  io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Testing {
	
	@BeforeMethod
	public void beforeClass() {

		/*
		 * requestSpecification= with(). baseUri("https://api.getpostman.com").
		 * header("X-Api-Key",
		 * "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		 */

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();

		/*
		 * responseSpecification= RestAssured.expect(). statusCode(200).
		 * contentType(ContentType.JSON). log().all();
		 */

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).log(LogDetail.ALL);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}

	
	
	
	@Test(enabled=true)
	public void validate_post_map() {
		
		
		HashMap<String,Object> mainObject1 =new  HashMap<>();
		
		HashMap<String,String> nestedObject =new  HashMap<>();
		
		nestedObject.put("name", "workspace testing1");
		nestedObject.put("description", "workspace testing1 description");
		nestedObject.put("type", "personal");
		
        mainObject1.put("workspace", nestedObject);
        
        
        
		
	RestAssured.given().
	body(mainObject1).
		             post("/workspaces");
	
	}

}
