package Day3RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	//@Test(priority=1)
	void testcookies()
	{
		
		given()
		
		.when().get("https://www.google.com/")
		
		.then()
		.cookie("AEC","AUEFqZdJZzOYll5EsgT7XhYANvDo0CGtHOGqeS9hNu1g_hFq-kBFF8SbIc8")
		.log().all();
		
	}
	
	@Test(priority=2)
	void getcookiesinfo()
	{
		
		Response res=given()
		
		.when().get("https://www.google.com/");
		
		// Get single cookie info
		
//		String cookie_value=res.getCookie("AEC");
//		System.out.println("Value of cookie is ====> :" +cookie_value);
		
		// Get all cookie info
		
		Map<String, String> cookie_values=res.getCookies();		
		
		//System.out.println(cookie_values.keySet());
		
		for(String k:cookie_values.keySet()) {
			String cookie_value=res.getCookie(k);
			System.out.println(k+"      "+cookie_values);
		}
	}
	

}
