import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class Basics {

	public static void main(String[] args) {

 RestAssured.baseURI="https://rahulshettyacademy.com/";
 
 given().log().all().header("Content-Type", "application/json").queryParam("key", "qaclick123")
 .body(payload.AddPlace())
 .when().post("maps/api/place/add/json")
 .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
 .header("Server", "Apache/2.4.52 (Ubuntu)");

	}

}
