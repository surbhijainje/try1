package com.rest.validation;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class AutomateGet {

	@Test(enabled = false)
	public void validate_get_status_code() {

		given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").when()
				.get("/workspaces").then().log().all().assertThat().statusCode(201);

	}

	@Test
	public void validate_response_body() {

		given().baseUri("https://api.getpostman.com")
				.header("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
	       when()
				.get("/workspaces").
		    then().log().all().
		           assertThat().
		              statusCode(200).
		              body("workspaces.name",
						hasItems("APITESTING", "APIBATCH_9PM", "My Workspace"),

						"workspaces.type", hasItems("personal", "personal", "personal"),
						"workspaces[0].name",  equalTo("APITESTING"),
						"workspaces.size()",equalTo(3));
	      }
	
	   
	
	
	

}
