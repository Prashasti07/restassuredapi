import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_Get_Authorisation {

	@Test
	public void authorizationTest() {

		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authScheme= new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		
		RestAssured.authentication=authScheme;
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/");

		// Print Response in Console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is ====>  " + responseBody);

		// Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:  " + statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not correct");
	}
}
