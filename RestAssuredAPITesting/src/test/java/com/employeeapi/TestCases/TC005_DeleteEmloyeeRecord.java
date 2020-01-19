package com.employeeapi.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DeleteEmloyeeRecord extends TestBase {

	@BeforeClass
	public void deleteEmployeeRecord() throws InterruptedException {

		logger.debug("**** Started TC005_Delete Employee Record ****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		System.out.println("Request ==>"+ httpRequest.toString());

		response = httpRequest.request(Method.GET, "/employees");
		JsonPath jsonPathEval = response.jsonPath();
		String empId = jsonPathEval.get("[0].id");
		System.out.println("Emp ID ===>  "+empId);
		response = httpRequest.request(Method.DELETE, "/delete" + empId);
		Thread.sleep(3);

	}
	@Test
	public void checkResponseBody() {
		logger.debug("**** Check Response Body ****");
		String responseBody=response.getBody().asString();
		AssertJUnit.assertEquals(responseBody.contains("successfully! deleted Records"),true);
	}

	@Test
	public void checkStatusCode() {
		logger.debug("**** Check Status Code ****");
		int statusCode=response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
	}
	
	@AfterMethod
	@AfterClass
	public void tearDown() {
		logger.debug("**** Finishing TC005_Delete Employee Record ****");
	}
}
