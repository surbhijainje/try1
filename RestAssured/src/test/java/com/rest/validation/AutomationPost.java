package com.rest.validation;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import  io.restassured.RestAssured;
import  io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import static io.restassured.RestAssured.config;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;

public class AutomationPost {
	
	
	@BeforeClass
	public void beforeClass() {

		/*
		 * requestSpecification= with(). baseUri("https://api.getpostman.com").
		 * header("X-Api-Key",
		 * "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		 */
		
		Set<String> headers = new HashSet<String>();
		
		headers.add("X-Api-Key");
		headers.add("Accept");

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().

		            setBaseUri("https://api.getpostman.com").
		            addHeader("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
		             setContentType(ContentType.JSON).
		             //blacklisting multiple values 
		             setConfig(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
	//handling single vale // setConfig(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
		            log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();
		
		
		/*requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("X-Api-Key", "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();*/

		/*
		 * responseSpecification= RestAssured.expect(). statusCode(200).
		 * contentType(ContentType.JSON). log().all();
		 */

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				 expectStatusCode(200)
				.expectContentType(ContentType.JSON);
				 

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}

	@Test(enabled=false)
	public void validate_post_bddStyle() {
		String payload ="{\r\n"
				+ "    \"workspace\" : {\r\n"
				+ "            \"name\": \"RESTVALIDATION5\",\r\n"
				+ "            \"type\": \"personal\",\r\n"
				+ "            \"visibility\": \"only-me\"\r\n"
				+ "        \r\n"
				+ "    }\r\n"
				+ "}";
		RestAssured.
		given().body(payload)
		.when().post("/workspaces").
		 then().log().all().body("workspace.name", equalTo("RESTVALIDATION5"),
				                   "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
		
	   // System.out.println("workspace id :" workspaces[4].id);
	}
	
	@Test(enabled=false)
	public void validate_post_nonbdd() {
		
		
		String payload ="{\r\n"
				+ "    \"workspace\" : {\r\n"
				+ "            \"name\": \"HarshilWorkspace\",\r\n"
				+ "            \"type\": \"personal\",\r\n"
				+ "            \"visibility\": \"only-me\"\r\n"
				+ "        \r\n"
				+ "    }\r\n"
				+ "}";
		
		
	Response response=RestAssured.with().
		             body(payload).
		             post("/workspaces");
	
	       int sc = response.getStatusCode();
	       System.out.println(sc);
	       System.out.println( response.getContentType());
	       System.out.println(response.getBody());
	
	    assertThat(response.<String>path("workspace.name"), equalTo("HarshilWorkspace"));
	    assertThat(response.<String>path("workspace.id"),  matchesPattern("^[a-z0-9-]{36}$"));
	 
	   
	  
	    
	}
	
	
	@Test(enabled=false)
	public void validate_post_file() {
		
		
		File file =new File("src/main/resources/data.json");
		
	Response response=RestAssured.with().
		             body(file).
		             post("/workspaces");
	
	}
	
	
	@Test(enabled=false)
	public void validate_post_map() {
		
		
		HashMap<String,Object> mainObject =new  HashMap<>();
		
		HashMap<String,String> nestedObject =new  HashMap<>();
		
		nestedObject.put("name", "workspace testing");
		nestedObject.put("description", "workspace testing description");
		nestedObject.put("type", "personal");
		
        mainObject.put("workspace", nestedObject);
		
	Response response=RestAssured.with().
		             body(mainObject).
		             post("/workspaces");
	
	}
	
	@Test(enabled=true)
	public void validate_post_map1() {
		//ObjectMapper objectMapper =new ObjectMapper();
		
		HashMap<String,Object> mainObject1 =new  HashMap<>();
		
		HashMap<String,String> nestedObject =new  HashMap<>();
		
		nestedObject.put("name", "workspace testing");
		nestedObject.put("description", "workspace testing description");
		nestedObject.put("type", "personal");
		
		
		
        mainObject1.put("workspace", nestedObject);
        
        
		
	                 RestAssured.given().
		             body(mainObject1).
		             post("/workspaces");
	
	}
	
	
	
}