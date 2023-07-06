package Day3RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeadersDemo {
	
	
//		@Test(priority=1)
		void testHeaders()
		{
			
			given()
			
			.when().get("https://www.google.com/")
			
			.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws").log().all();
			
		}
		
		@Test(priority=2)
		void getHeaders()
		{
			
			Response res=given()
			
			.when().get("https://www.google.com/");
			
			//Get single header info
			
//			String headervalue=res.getHeader("Content-Type");
//			System.out.println("The value of content type header is :" +headervalue );
			
			//Get all headers info

			 Headers myheaders=res.getHeaders();
			 
			  for(Header hd:myheaders) {
				  System.out.println(hd.getName()+"   "+hd.getValue());
			  }
			 
			 
			
		}
}
