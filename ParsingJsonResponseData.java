package Day4RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponseData {
	
	//@Test(priority=1)
	void testJsonResponse() {
		//Approach 1
		
		/* given().contentType(ContentType.JSON)
		.when().get("https://reqres.in/api/users?page=2")
		
		.then().statusCode(200).header("Content-Type", "application/json; charset=utf-8")
			.body("data[5].last_name", equalTo("Howell")); */
		
		
		//Approach 2
		
		Response res=given().contentType(ContentType.JSON)
				.when().get("https://reqres.in/api/users?page=2");
		Assert.assertEquals(res.getStatusCode(),200); //Validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String empname=res.jsonPath().get("data[5].last_name").toString();
		Assert.assertEquals(empname, "Howell");
		 
	}
	
	@Test(priority=2)
	void testJsonResponseData() {
		Response res=given().contentType(ContentType.JSON)
				.when().get("https://reqres.in/api/users?page=2");
		
		//JSON OBJECT class
		
		JSONObject jo=new JSONObject(res.asString());  //Converting response to json object
//		for (int i=0;i<jo.getJSONArray("data").length();i++) {
//			String empname=jo.getJSONArray("data").getJSONObject(i).get("avatar").toString();
//			System.out.println(empname);
//		}
		
		//Search the title of the book in json  -- validation 1
		Boolean status=false;
		for (int i=0;i<jo.getJSONArray("data").length();i++) {
			String empname=jo.getJSONArray("data").getJSONObject(i).get("avatar").toString();
			if (empname.equals("https://reqres.in/img/faces/7-image.jpg")) {
				status=true;
				break;
			}
			Assert.assertEquals(true, status);
			
			// Validate id
			
			double no=0;
			for (int i1=0;i1<jo.getJSONArray("data").length();i1++) {
				String id_value=jo.getJSONArray("data").getJSONObject(i1).get("id").toString();
				
				no=no+ Double.parseDouble(id_value);	
		}
			System.out.println("adding value is "+no);
			Assert.assertEquals(7, status);
		
	}

}}
