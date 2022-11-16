package com.rest.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Filters {
	RequestSpecification requestSpecification;
	 ResponseSpecification responseSpecification;
	

    @BeforeMethod
    public void beforeClass() {
    	
    	RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder().
    			setBaseUri("https://postman-echo.com").
    			
    			addFilter(new RequestLoggingFilter(LogDetail.URI)).
    			addFilter(new ResponseLoggingFilter(LogDetail.ALL));
    	
    	       requestSpecification = requestSpecBuilder.build();
    	       
    	       
    	       ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder().
    	    		   expectStatusCode(200);
    	      responseSpecification = responseSpecBuilder.build();
    	
    }
	
	@Test
	public void loggingFilter() {
		
		   RestAssured.given().spec(requestSpecification).
		   
		   
		     when().get("/get").
		            then().spec(responseSpecification)
		            .assertThat().statusCode(200);
		
		}

   
   
	public void loggingFilter1() throws FileNotFoundException {
		
	   PrintStream FileOutPutStream = new PrintStream(new File("restassured.log"));
		   RestAssured.
		            given().
		            baseUri("https://postman-echo.com").
		            when().get("/get").
		            then()
		            .assertThat().statusCode(200);
		
		}




}
