package com.rest.validation;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class AutomatePut {
public	String workspaceId = "2e46d069-a3a5-495e-9304-7e73667a72d8";
AutomatePut auto=new AutomatePut();
     
	
	
	@BeforeClass
	public void beforeClass() {

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().

		            setBaseUri("https://api.getpostman.com").
		            addHeader("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
		            setContentType(ContentType.JSON).
		            log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();
		
		

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				 expectStatusCode(200)
				.expectContentType(ContentType.JSON).
				 log(LogDetail.ALL);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	
	@Test(enabled=true)
	public void validate_put_bddStyle() {
		
		
		String payload ="{\r\n"
				+ "    \"workspace\" : {\r\n"
				+ "            \"name\": \"UPDATEDWORKSPACE\",\r\n"
				+ "            \"type\": \"personal\",\r\n"
				+ "            \"visibility\": \"only-me\"\r\n"
				+ "        \r\n"
				+ "    }\r\n"
				+ "}";
		RestAssured.
		given()
		.body(payload).
		pathParam(workspaceId,auto.workspaceId)
		.when()
		.put("/workspaces/{workspaceId}").
		 then().
		 log().all().
		 body("workspace.name", equalTo("UPDATEDWORKSPACE"),
				 "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		
	
	  // System.out.println("workspace id :" workspaces[4].id);
	}

}
