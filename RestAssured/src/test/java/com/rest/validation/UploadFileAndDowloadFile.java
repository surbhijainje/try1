package com.rest.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class UploadFileAndDowloadFile {
	
	
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
	
	
	@Test(enabled=false)
	public void dowload_file() throws IOException {
		
		byte[] bytes=RestAssured.given().
		baseUri("https://github.com").
		log().all().
		when().
		  get("/appium-boneyard/sample-code/blob/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
		  then().
		  log().all().
		  extract().
		  response().asByteArray();
		
		
	     OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
	     os.write(bytes);
	     os.close();
		  
	}

}
