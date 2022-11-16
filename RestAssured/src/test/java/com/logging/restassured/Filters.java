package com.logging.restassured;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class Filters {
	
	
	
	
	
	
	
	
	
	@Test
	public void single_query_parameter() throws FileNotFoundException {
		
		
		PrintStream FileOutPutStream= new PrintStream(new File("Loginfo.log"));
		
		   RestAssured.
		            given().
		              baseUri("https://postman-echo.com").
		             filter(new RequestLoggingFilter(LogDetail.URI,FileOutPutStream)).
		             filter(new ResponseLoggingFilter(LogDetail.BODY,FileOutPutStream)).
		             
		            
		         
		            when().get("/get").
		            then()
		            .assertThat().statusCode(200);
		
		}
	
	@Test
	public void single_query_parameter1() throws FileNotFoundException {
		
		
		PrintStream FileOutPutStream= new PrintStream(new File("Loginfo.log"));
		
		   RestAssured.
		            given().
		              baseUri("https://postman-echo.com").
		              queryParam("foo", "bar").
		        
		             filter(new RequestLoggingFilter(LogDetail.URI,FileOutPutStream)).
		             filter(new ResponseLoggingFilter(LogDetail.BODY,FileOutPutStream)).
		             
		            
		         
		            when().get("/get").
		            then()
		            .assertThat().statusCode(200);
		
		}
	

}
