package com.rest.validation;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class Parameters {

	
	
	//@Test(enabled=false)
	public void single_query_parameter() {
		
	    given().
		      baseUri("https://postman-echo.com").
		     param("foo1","bar1").
		      //queryParam("foo1","bar1").
		      log().all().
	   when().
	         get("/get").
	   then().
		            log().all().
		            assertThat().
		            statusCode(200);
	}


	//@Test(enabled=false)
	
	public void multiple_query_parameter() {
		
		HashMap<String,String> queryParams=new HashMap<>();
		queryParams.put("foo1","bar1");
		queryParams.put("foo2","bar2");
		
		given().
	      baseUri("https://postman-echo.com").
	      queryParams(queryParams).
	     
	      log().all().
   when().
        get("/get").
   then().
	            log().all().
	            assertThat().
	            statusCode(200);
		
	}
	
//@Test(enabled=true)
	
	public void multiple_value_query_parameter() {
		
	/*	HashMap<String,String> queryParams=new HashMap<>();
		queryParams.put("foo1","bar1");
		queryParams.put("foo2","bar2"); */
		
		given().
	      baseUri("https://postman-echo.com").
	      queryParam("foo1", "bar1","bar2","bar3").
	     
	      log().all().
   when().
        get("/get").
   then().
	            log().all().
	            assertThat().
	            statusCode(200);
		
	}

  
 // @Test(enabled=false)
  public void path_parameter() {
	  
	  given().baseUri("https://reqres.in/").
	  pathParam("userId",2).
	  log().all().
	  
	  when().
	  get("api/users/{userId}").
	 
	  then().log().all().
	  assertThat().
	  statusCode(200);
  }
	
 

}
