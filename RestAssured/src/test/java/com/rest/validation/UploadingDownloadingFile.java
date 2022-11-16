package com.rest.validation;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class UploadingDownloadingFile {
	
	
	@Test
	public void upload_file_multipart_form_data() {
		
		RestAssured.given().
		baseUri("https://postman-echo.com").
		multiPart("file" , new File("add.txt")).
		log().all().
		when().
		post("/post").
		then().
		log().all().
		assertThat().statusCode(200);
		
	}

}
