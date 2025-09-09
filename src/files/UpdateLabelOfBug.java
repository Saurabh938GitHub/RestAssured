package files;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateLabelOfBug {

	@Test
	public void updateLabelOfBug() {

 RestAssured.baseURI="https://938saurav.atlassian.net/";
 
 String key="SKJIR-9";
 
 String response= given().header("Content-Type", "application/json").header("Accept","application/json")
		 .header("Authorization",payload.BearerTokenForBasicAuth())
		 .pathParam("key", key)
		 .body(payload.UpdateBugLabels("ToDo", "Closed"))
		 .when().put("rest/api/2/issue/{key}")
		 .then().assertThat().statusCode(204).extract().response().asString();
 System.out.println(response);
 
	}

}
