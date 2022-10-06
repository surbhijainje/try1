package com.rest.validation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AutomateGet {

	@Test(enabled = false)
	public void validate_get_status_code() {

		given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().log().all().assertThat().statusCode(201);

	}

	@Test(enabled = false)
	public void validate_response_body() {

		given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().log().all().assertThat().statusCode(200).body("workspaces.name",
						hasItems("APITESTING", "APIBATCH_9PM", "My Workspace"),

						"workspaces.type", hasItems("personal", "personal", "personal"), "workspaces[0].name",
						equalTo("APITESTING"), "workspaces.size()", equalTo(3));
	}

	@Test(enabled = false)
	public void extract_response() {

		Response res = given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().log().all().assertThat().statusCode(200).extract().response();

		System.out.println("Extracted Response" + res.asString());

	}

	@Test(enabled = false)
	public void extract_single_response() {

		Response res = given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().log().all().assertThat().statusCode(200).extract().response();

		System.out.println("Extracted Response" + res.path("workspaces[0].name"));

	}

	@Test(enabled = false)
	public void extract_single_response2() {

		Response res = given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().log().all().assertThat().statusCode(200).extract().response();

		JsonPath jsonpath = new JsonPath(res.asString());

		System.out.println("Extracting single value :" + jsonpath.getString("workspaces[1].name"));

	}

	@Test(enabled = true)
	public void hamcrest_assert_on_extracted_response() {

		String name = given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().
				// log().all().
				assertThat().statusCode(200).extract().response().path("workspaces[0].name");

		System.out.println("Workspace name =" + name);

		assertThat(name, equalTo("APITESTING"));

		// Assert.assertEquals(name, "APITESTING2");

	}

	@Test(enabled = true)
	  public void hamcrest_assert_on_extracted_response1() {
		
		  given().
		       baseUri("https://api.getpostman.com")
			   .header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
    when()
			 .get("/workspaces").
	     then().
	          //log().all().
	            assertThat().
	              statusCode(200).
	              body("workspaces.name", contains("APITESTING","APIBATCH_9PM"),
	            		"workspaces.type", hasItems("personal","personal"),
	            		"workspaces[0].name", equalTo("APITESTING"),
	            		"workspaces.size()" ,equalTo(3)
	            		         
	            		  
	       );
	             
	}	 
	 
		
}
