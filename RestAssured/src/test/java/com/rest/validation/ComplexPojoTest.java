package com.rest.validation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class ComplexPojoTest {
	
	
	@BeforeClass
	public void beforeClass() {

		/*
		 * requestSpecification= with(). baseUri("https://api.getpostman.com").
		 * header("X-Api-Key",
		 * "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		 */

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		requestSpecBuilder.setBaseUri("https://d7562748-37aa-44fe-81ae-16abe93bcda2.mock.pstmn.io");
		
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();

		/*
		 * responseSpecification= RestAssured.expect(). statusCode(200).
		 * contentType(ContentType.JSON). log().all();
		 */

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(201)
				.expectContentType(ContentType.JSON).log(LogDetail.ALL);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	
	
	
	@Test
	public void complex_pojo_create_collection() {
		
		RestAssured.given().body(getClass()).
		when().post("/collection").
		then().extract().response();
	}

}
