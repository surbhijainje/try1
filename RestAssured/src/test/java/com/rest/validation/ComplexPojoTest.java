package com.rest.validation;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rest.pojo.complexcollectiondata.Body;
import com.rest.pojo.complexcollectiondata.Collection;
import com.rest.pojo.complexcollectiondata.CollectionRoot;
import com.rest.pojo.complexcollectiondata.Folder;
import com.rest.pojo.complexcollectiondata.Header;
import com.rest.pojo.complexcollectiondata.Info;
import com.rest.pojo.complexcollectiondata.Request;
import com.rest.pojo.complexcollectiondata.Requestroot;

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

		requestSpecBuilder.setBaseUri("https://api.getpostman.com");
		requestSpecBuilder.addHeader("x-api-key", "PMAK-63758b6ca022c2466471302c-af4005b37fcf9848e15072b53c78ea0695");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();

		/*
		 * responseSpecification= RestAssured.expect(). statusCode(200).
		 * contentType(ContentType.JSON). log().all();
		 */

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	
	
	
	@Test
	public void complex_pojo_create_collection() {
		
		Header header=new Header("Content-Type","application/json");
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(header);
		
		Body body = new Body("raw", "{\"data\":\"123\"}");
		
		Request request = new Request("https://postman-echo.com/post","POST",headerList,body,"This is sample POST Request");
		
		Requestroot requestroot =new Requestroot("Sample POST Request",request);
		List<Requestroot> requestRootList = new ArrayList<Requestroot>();
		requestRootList.add(requestroot);
		
		Folder folder = new Folder("This is Harshil  Folder",requestRootList);
		List<Object> folderList = new ArrayList<Object>();
		folderList.add(folder);
		
		Info info = new Info("Harshil Collection",
				"This collection makes a request to the Postman Echo service to get a list of request headers sent by an HTTP client.",
				"https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
		
		Collection collection = new Collection(info,folderList);
		CollectionRoot collectionRoot=new CollectionRoot(collection);
		
		RestAssured.given().body(collectionRoot).
		when().post("/collections").
		then().log().all();
	}

}
