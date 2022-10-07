package com.logging.restassured;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import static io.restassured.RestAssured.config;

import java.util.HashSet;
import java.util.Set;


public class Logging {

		@Test(enabled=false)
		public void simple_test(){
			
			RestAssured.
			        given().
			             baseUri("https://api.getpostman.com").
			             header("X-Api-Key","PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
			             log().headers().
			       when().
			             get("/workspaces").
			       then().
			              log().ifError().
			             statusCode(200).
			             body("workspaces[0].name", Matchers.is(Matchers.equalTo("APITESTING")));
		}
			            		 
					          
	
		
		
		@Test
		public void simple_Test1() {
			
			RestAssured.
			        given().
			             baseUri("https://api.getpostman.com").
			             header("X-Api-Key","PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
			            // log().ifValidationFails().
			             config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
			       when().
			             get("/workspaces").
			       then().
			             // log().ifValidationFails().
			             statusCode(200).
			             body("workspaces[0].name", Matchers.is(Matchers.equalTo("APITESTING")));
			            		 
					      
	       }
		
		
		@Test
		public void simple_Test2() {
			
			Set<String> headers=new HashSet<String>();
			
			headers.add("X-Api-Key");
			headers.add("Accept");
			
			
			
			
			RestAssured.
			        given().
			             baseUri("https://api.getpostman.com").
			             header("X-Api-Key","PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d").
			            // log().ifValidationFails().
			            // config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))).
			             config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
			              log().all().
			       when().
			             get("/workspaces").
			       then().
			             // log().ifValidationFails().
			             statusCode(200).
			             body("workspaces[0].name", Matchers.is(Matchers.equalTo("APITESTING")));
			            		 
					      
	       }
}
		










