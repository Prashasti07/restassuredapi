package com.employeeapi.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmployees extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {

		logger.debug("**** Started TC001_GetAllEmployees ****");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given(); // using this we will send request
		response = httpRequest.request(Method.GET, "/employees"); // employeess is path parameter
		logger.debug("**** REsponse ****"+ response.asString());
		Thread.sleep(3);
	}

	@Test
	public void checkResponseBody() {

		logger.debug("**** Check Response Body ****");
		String responseBody = response.getBody().asString();

		logger.debug("Response Body ==>  " + responseBody);
		AssertJUnit.assertTrue(responseBody != null);

	}

	@Test
	public void checkStatusCode() {
		logger.debug("**** Check Status Code ****");
		int statusCode = response.getStatusCode();
		logger.debug("Status Code ===>  " + statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
	}

	@Test
	public void checkResponseTime() {
		logger.debug("**** Check Response Time ****");
		long responseTime = response.getTime();
		logger.debug("Response Time ==>  " + responseTime);

		if (responseTime > 2000)
			logger.warn("Response Time is greater than 2000");

		AssertJUnit.assertTrue(responseTime < 2000);
	}

	@Test
	public void checkStatusLine() {
		logger.debug("**** Check Status Line ****");
		String statusLine = response.getStatusLine();
		logger.debug("Status Line ==>  " + statusLine);
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkContentType() {
		logger.debug("**** Check Content Type ****");
		String contentType = response.getContentType();
		logger.debug("Content Type==>  " + contentType);

		AssertJUnit.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test
	public void checkServerType() {
		logger.debug("**** Check Server Type ****");
		String serverType = response.header("Server");
		logger.debug("Server Type==>  " + serverType);

		AssertJUnit.assertEquals(serverType, "nginx/1.16.0");
	}

	@Test
	public void checkContentEncoding() {
		logger.debug("**** Check Content Encoding ****");
		String contentEncoding = response.header("Content-Encoding");
		logger.debug("Content Encoding==>  " + contentEncoding);
		AssertJUnit.assertEquals(contentEncoding, "gzip");

	}

	@Test
	public void checkContentLength() {
		logger.debug("**** Check Content Length ****");
		String contentLength=response.header("Content-Length");
		logger.debug("Content Length==>  "+ contentLength);
		AssertJUnit.assertFalse(contentLength.isEmpty());
		//OR
		if(Integer.parseInt(contentLength)<100) 
			logger.warn("Content Length is less than 100");
		
		AssertJUnit.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	public void checkCookies() {
		logger.debug("**** Check Cookies ****");
		String cookie=response.getCookie("abc");
	}
	
	@AfterMethod
	@AfterTest
	public void tearDown() {
		logger.debug("**** Finished TC001_GetAllEmployees ****");
	}
}
