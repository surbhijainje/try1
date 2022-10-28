package com.rest.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class AutomateJsonArray {
	
	
	@BeforeClass
	public void beforeClass() {

		/*
		 * requestSpecification= with(). baseUri("https://api.getpostman.com").
		 * header("X-Api-Key",
		 * "PMAK-6334a0323ce9283aae75733b-c31406c4a94e35ab5d0cce1793d1678e7d");
		 */

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().

		            setBaseUri("https://5a4731ea-098b-4c2d-a850-183096063d34.mock.pstmn.io").
		            addHeader("x-mock-match-request-body", "true").
		            setContentType(ContentType.JSON).
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
				.expectContentType(ContentType.JSON).
				 log(LogDetail.ALL);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	
	
	@Test
	public void validate_post_request_payload_json_array_as_list(){
		
		HashMap<String,String> obj5001=new HashMap<String,String>();
		
		obj5001.put("id","5001");
		obj5001.put("type","None");
		
		
		HashMap<String,String> obj5002=new HashMap<String,String>();
		
		obj5002.put("id","5002");
		obj5002.put("type","Glazed");
		
		List<HashMap<String,String>> jsonList = new  ArrayList<HashMap<String,String>>();
		
		jsonList.add(obj5001);
		jsonList.add(obj5002);
		
		
		RestAssured.given().
		           body(jsonList).
		           when().post("/post").
		           then().
		           assertThat().
		           body("msg", Matchers.equalTo("succesfully created data"));
	}

}
