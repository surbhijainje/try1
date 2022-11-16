package com.rest.validation;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.pojo.simple.Workspace;
import com.rest.pojo.simple.WorkspaceRoot;
import io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class WorkspacePojoTest {

	@BeforeClass
	public void beforeClass() {

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().

			  	setBaseUri("https://api.getpostman.com")
				.addHeader("x-api-Key", "PMAK-631f56dc4b0195082cc006a7-33600d3ee26530647ec6ba47c6c8d30c37")
				.setContentType(ContentType.JSON).log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).log(LogDetail.ALL);

		RestAssured.responseSpecification = responseSpecBuilder.build();

	}

	@Test(dataProvider = "workspace")
	public void workspace_serialize_deserialize_datadriven(String name, String type, String description) {

		Workspace workspace = new Workspace(name, type, description);

		WorkspaceRoot workspaceroot = new WorkspaceRoot(workspace);

		RestAssured.given().body(workspaceroot).when().post("/workspaces");

		// MatcherAssert.assertThat(deserializedWorkspaceRoot.getWorkspace().getName(),Matchers.equalTo(workspaceroot.getWorkspace().getName()));

	}

	@DataProvider(name = "workspace")

	public Object[][] testData() {
		return new Object[][] { 
			{ "TestWorkspace1", "personal", "TestWorkSpace1 Description" }, 
			{ "SeleniumWorkspace2", "team", "SeleniumWorkspace2 Description" } 
			};

	}

}
