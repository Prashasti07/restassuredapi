import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Get_Request {
	//Validate header
	@Test
	public void googleMapTest() {

		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Print Response in Console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is ====>  " + responseBody);

		// Capture details of header
		String contentTypeValue=response.getHeader("Content-Type");
		System.out.println("Content-Type is ====>  " + contentTypeValue);
		Assert.assertEquals(contentTypeValue, "application/json");
		
		//Content-Encoding Header	
		String contentEncodingValue=response.getHeader("Content-Encoding");
		System.out.println("content Encoding Value is ====>  " + contentEncodingValue);
		Assert.assertEquals(contentEncodingValue, "gzip");
		
	}
}
