package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddNewEmployees {

	// Method 1 with single value
	/*
	 * @Test public void addNewEmployees() {
	 * 
	 * RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	 * 
	 * RequestSpecification httpRequest = RestAssured.given();
	 * 
	 * JSONObject requestParams = new JSONObject();
	 * 
	 * requestParams.put("name", "Prashasti"); requestParams.put("salary", "70000");
	 * requestParams.put("age", "26");
	 * 
	 * // Added header httpRequest.header("Content-Type", "application/json");
	 * 
	 * // Add Json Body to request httpRequest.body(requestParams.toJSONString());
	 * 
	 * //Send Post Request Response response=
	 * httpRequest.request(Method.POST,"/create");
	 * 
	 * //Capture Response Body to perform Validations
	 * 
	 * String responseBody=response.getBody().asString();
	 * 
	 * Assert.assertEquals(responseBody.contains("Prashasti"),true);
	 * Assert.assertEquals(responseBody.contains("7000"),true);
	 * Assert.assertEquals(responseBody.contains("26"),true);
	 * 
	 * //Verify Status Code int statusCode=response.getStatusCode();
	 * Assert.assertEquals(statusCode,200); }
	 */

	// Method 2 with Data Provider, hard-coded value in method itself
	@Test(dataProvider = "empDataProvider")
	public void addNewEmployees(String ename, String eage, String esal) {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("name", ename);
		requestParams.put("salary", eage);
		requestParams.put("age", esal);

		// Added header
		httpRequest.header("Content-Type", "application/json");

		// Add Json Body to request
		httpRequest.body(requestParams.toJSONString());

		// Send Post Request
		Response response = httpRequest.request(Method.POST, "/create");

		// Capture Response Body to perform Validations

		String responseBody = response.getBody().asString();
		System.out.println("Response Body ==>  " + responseBody);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		Assert.assertEquals(responseBody.contains(esal), true);

		// Verify Status Code

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	/*
	 * @DataProvider(name="empDataProvider") public String[][] getEmpData() {
	 * 
	 * 
	 * String empData[][]= {{"abc251","50000","20"},{"abc12","6000","21"},
	 * {"abc23","70000","22"}}; return (empData); }
	 */

	// Method 3 -> Read data from Excel

	@DataProvider(name = "empDataProvider")
	public String[][] getEmpDataXls() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/resources/empData.xlsx";
		int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
		System.out.println("Total Rows are: " + rowCount);
		int columnCount = ExcelUtils.getCellCount(path, "Sheet1", 1);
		System.out.println("Total Columns are: " + columnCount);

		String empdata[][] = new String[rowCount][columnCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				empdata[i - 1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return empdata;
	}
}
