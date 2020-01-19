package com.employeeapi.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GetSingleEmployeeData extends TestBase {

	@BeforeClass
	public void getSingleEmployeeData() throws InterruptedException {

		logger.info("**** Started TC002_GetSingleEmployeeData ****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empId);
		System.out.println("REsponse ==>  " + response.asString());
		Thread.sleep(3);

	}

	@Test
	public void checkStatusCode() {
		logger.info("**** check Status Code ****");
		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 200);
	}

	@Test
	public void checkResponseTime() {
		logger.info("**** check Response Time ****");
		long time = response.getTime();
		System.out.println("Time==>  " + time);
		AssertJUnit.assertTrue(time > 0);
	}

	@Test
	public void checkContentType() {
		logger.info("**** check Content Type ****");
		String contentType = response.header("Content-Type");
		System.out.println("content Type  ==>  " + contentType);
		AssertJUnit.assertEquals(contentType,"application/json;charset=utf-8" );
	}
	
	@Test
	public void checkServerType() {
		logger.info("**** Check Server Type ****");
		String serverType= response.header("Server");
		AssertJUnit.assertEquals(serverType, "nginx/1.16.0");
		
	}
	
	@Test
	public void checkContentLength() {
		logger.info("**** Check Content Length ****");
		String contentLength= response.header("Content-Length");
		AssertJUnit.assertTrue(Integer.parseInt(contentLength)>0);
	}
	
	
	@AfterMethod
	@Test
	public void tearDown() {
		logger.info("**** Finished TC002_GetSingleEmployeeData ****");
	}

}
