package com.employeeapi.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_PostEmployeeRecord extends TestBase {

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	public void createEmployeeRecord() throws InterruptedException {

		logger.info("**** Starting TC_003 ****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(5);
	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		AssertJUnit.assertTrue(responseBody.contains(empName));
		AssertJUnit.assertTrue(responseBody.contains(empSal));
		AssertJUnit.assertTrue(responseBody.contains(empAge));

	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
	}

	@Test
	public void checkContentType() {
		String contentType = response.header("Content-Type");
		AssertJUnit.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test
	public void checkServerType() {
		String serverType = response.header("Server");
		AssertJUnit.assertEquals(serverType, "nginx/1.16.0");

	}

	/*
	 * @Test public void checkContentEncoding() { String contentEncoding =
	 * response.header("Content-Encoding"); Assert.assertEquals(contentEncoding,
	 * "null"); }
	 */
	@AfterMethod
	@AfterClass
	public void tearDown() {
		logger.info("**** Ending TC_003 ****");
	}

}
