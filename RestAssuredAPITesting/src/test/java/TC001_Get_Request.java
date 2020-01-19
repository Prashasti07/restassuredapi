import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_Request {
//Validate Status code and status line
	@Test
	public void getWeatherDetails() {
		
		//Specify Base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest= RestAssured.given();
				
		//Response object
		Response response= httpRequest.request(Method.GET,"/Hyderabad");
		
		//Print Response in Console
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is ====>  "+responseBody);
		
		//Status Code Validation
		int statusCode=response.getStatusCode();
		System.out.println("Status Code is:  "+statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not correct");
		
		//Status Line Verfication
		String statusLine=response.getStatusLine();
		System.out.println("Status Line is:  "+ statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK","Incorrect Status Line");
	}
}
