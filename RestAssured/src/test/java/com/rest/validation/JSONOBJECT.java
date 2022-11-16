package com.rest.validation;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class JSONOBJECT {
	
	
	
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
	public void serialize_json_using_jackson() throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		//syntax to create object node ( HashMap<String,String> nestedObject =new  HashMap<>(); )
		ObjectNode nestedObjectNode = objectMapper.createObjectNode();
		
		nestedObjectNode.put("name", "workspace testing1");
		nestedObjectNode.put("description", "workspace testing1 description");
		nestedObjectNode.put("type", "personal");
		
		//HashMap<String,Object> mainObject2 =new  HashMap<>();
	     ObjectNode mainObject2 = objectMapper.createObjectNode();
	     
	     mainObject2.set("workspace", nestedObjectNode);
		
	     String mainobjectstr = objectMapper.writeValueAsString(mainObject2);
	        
        RestAssured.given().
	    body(mainobjectstr).
        post("/workspaces");
	
	}


}
