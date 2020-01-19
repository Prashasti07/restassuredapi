import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_VerifyAllFieldsOfResponse {

	@Test
	public void getWeatherDetails() {

		// Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/Delhi");

		// Print Response in Console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is ====>  " + responseBody);
		
		JsonPath jsonPath = response.jsonPath();
		System.out.println(jsonPath.get("City"));
		System.out.println(jsonPath.get("Temperature"));
		System.out.println(jsonPath.get("Humidity"));
		System.out.println(jsonPath.get("WeatherDescription"));
		System.out.println(jsonPath.get("WindSpeed"));
		System.out.println(jsonPath.get("WindDirectionDegree"));
	
	}
}
