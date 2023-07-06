package Day2RestAssured;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DiffWaystocreatePostRequestBody {
	int id;
	//1.Post request body using HashMap
//	@Test(priority=1)
	void testPostusingHashmap() {
		
	HashMap datas=new HashMap();
	datas.put("name", "veera");
	datas.put("job", "Trainee");
	
	id=given().contentType("application/json").body(datas)
	.when().post("https://reqres.in/api/users").jsonPath().getInt("id");
	//.then().statusCode(201).log().all(); 
		}
	
	
	//2.using org.json
	//@Test(priority=1)
	void testPostusingOrgJson() {
		
		JSONObject data=new JSONObject();
		
		data.put("name", "morpheus");
		data.put("job", "leader");
		
		given().contentType("application/json").body(data.toString())
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).body("name",equalTo("morpheus")).log().all(); //
			}
	//3.using POJO class
	//@Test(priority=1)
	void testPostusingPojoClass() {
		
		POJO_PostRequest data=new POJO_PostRequest();
		
		data.setName("morpheus");
		data.setJob("leader");
		
		given().contentType("application/json").body(data)
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).body("name",equalTo("morpheus")).log().all(); //
			}
	//4.using external json file
	@Test(priority=1)
	void testPostusingexternaljson() throws FileNotFoundException {
		
		File f=new File(".//body.json");
				
		FileReader fr=new FileReader(f);
		
		JSONTokener jt=new JSONTokener(fr);
		
		JSONObject data=new JSONObject(jt);	
		given().contentType("application/json").body(data.toString())
		.when().post("https://reqres.in/api/users")
		.then().statusCode(201).body("Name",equalTo("Test")).log().all(); //
			}
 	@Test(priority=2)
	void DeleteUser() {
		given()
		.when().delete("https://reqres.in/api/users/2")
		.then().statusCode(204).log().all();
	}
	
 


}
