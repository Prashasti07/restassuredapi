import java.net.http.HttpResponse;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Post_Request {

	@Test
	public void RegistrationSuccessful() {

		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object

		// Request Paramter OR Body OR Request Payload
		JSONObject requestParam = new JSONObject();

		requestParam.put("FirstName", "JohnXYZ123096");
		requestParam.put("LastName", "XYZ123096");
		requestParam.put("UserName", "JohnXYZ123096");
		requestParam.put("Password", "JohnXYZxyx123096");
		requestParam.put("Email", "JohnXYZ123096@gmail.com");

		// Setting Header
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString()); //Attach data to the request

		// Send a Request
		Response response = httpRequest.request(Method.POST, "/register");

		// Print Response in Console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is ====>  " + responseBody);

		// Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:  " + statusCode);
		Assert.assertEquals(statusCode, 201, "Status Code is not correct");

		//Success Code  Validation
		String successCodeValue=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCodeValue, "OPERATION_SUCCESS", "Incorrect Success Code Value");
	}
}
