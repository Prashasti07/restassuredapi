import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Get_PrintAllHeaders {

	@Test
	public void getWeatherDetails() {

		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Print Response in Console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is ====>  " + responseBody);

		//Print All Headers
		Headers allHeaders=response.getHeaders();
	
		
		for(Header header:allHeaders){
			System.out.println(header.getName() +" ->" +" "+ header.getValue());
		
	}
	
	
	}
}
