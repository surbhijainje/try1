package com.rest.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class AutomateComplexRequest {
	
	
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
				 expectStatusCode(201)
				.expectContentType(ContentType.JSON).
				 log(LogDetail.ALL);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	
	@Test
	public void validate_post_request_payload_complex_json() {
		
	List<Integer> idArrayList = new ArrayList<Integer>();
	idArrayList.add(5);
	idArrayList.add(9);
	
	HashMap<String,Object> batterHashMap2=new HashMap<String,Object>();
	
	batterHashMap2.put("id", idArrayList);
	batterHashMap2.put("type", "Chocolate");
	
	HashMap<String,Object> batterHashMap1=new HashMap<String,Object>();
	
	batterHashMap1.put("id", "1001");
	batterHashMap1.put("type", "Regular");
	
	List<HashMap<String,Object>> batterArrayList = new ArrayList<HashMap<String,Object>>();
		
	batterArrayList.add(batterHashMap1);
	batterArrayList.add(batterHashMap2);
	
	HashMap<String,List<HashMap<String,Object>>> battersHashMap = new HashMap<String,List<HashMap<String,Object>>>();
		
	battersHashMap.put("batter", batterArrayList);
	
	//topping
	
	List<String> typeArrayList = new ArrayList<String>();
	typeArrayList.add("test1");
	typeArrayList.add("test2");
	
	HashMap<String,Object> toppingHashMap2=new HashMap<String,Object>();
	toppingHashMap2.put("id", "5002");
	toppingHashMap2.put("type", typeArrayList);
	
	HashMap<String,Object> toppingHashMap1=new HashMap<String,Object>();
	toppingHashMap1.put("id", "5001");
	toppingHashMap1.put("type","None");
	
	List<HashMap<String,Object>> toppingArrayList = new ArrayList<HashMap<String,Object>>();
	toppingArrayList.add(toppingHashMap1);
	toppingArrayList.add(toppingHashMap2);
	
	HashMap<String,Object> mainHashMap=new HashMap<String,Object>();
	mainHashMap.put("id", "0001");
	mainHashMap.put("type", "donut");
	mainHashMap.put("name", "Cake");
	mainHashMap.put("ppu", "0.55");
	mainHashMap.put("batters",battersHashMap);
	mainHashMap.put("topping", toppingArrayList);
	
		RestAssured.
	          given().body(mainHashMap).
	          when().post("/postComplexRequest").
	          then().
	          assertThat().
	          body("msg", Matchers.equalTo("successfully posted data created"));
	
	}

}
