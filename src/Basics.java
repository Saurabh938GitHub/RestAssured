import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {

 RestAssured.baseURI="https://rahulshettyacademy.com/";
 
 String response= given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
 .body(payload.AddPlace())
 .when().post("maps/api/place/add/json")
 .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
 .header("Server", "Apache/2.4.52 (Ubuntu)")
 .extract().response().asString();
 
 System.out.println(response);
 
 JsonPath js= new JsonPath(response);
 String placeId= js.getString("place_id"); 
 
 System.out.println(placeId);
 
 // updating the address using put http method
 String newAddress= "Lorelle Society, Pune";
 String response1= given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		 .body("{\r\n"
		 		+ "\"place_id\":\""+placeId+"\",\r\n"
		 		+ "\"address\":\""+newAddress+"\",\r\n"
		 		+ "\"key\":\"qaclick123\"\r\n"
		 		+ "}")
		 .when().put("maps/api/place/update/json")
		 .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"))
		 .header("Server", "Apache/2.4.52 (Ubuntu)")
		 .extract().response().asString();
 
//verifying the updated address using get http method
String getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		 .when().get("maps/api/place/get/json")
		 .then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress))
		 .header("Server", "Apache/2.4.52 (Ubuntu)")
		 .extract().response().asString();
JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
String actualAddress= js1.getString("address"); 

System.out.println(actualAddress);
Assert.assertEquals(actualAddress, newAddress);
	}

}
