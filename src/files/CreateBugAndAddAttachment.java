package files;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBugAndAddAttachment {

	@Test
	public void createBug() {

 RestAssured.baseURI="https://938saurav.atlassian.net/";
 
 String response= given().header("Content-Type", "application/json").header("Accept","application/json")
		 .header("Authorization",payload.BearerTokenForBasicAuth())
		 .body(payload.CreateBug("SKJIR", "Dropdowns are not working"))
		 .when().post("rest/api/3/issue")
		 .then().assertThat().statusCode(201).extract().response().asString();
 
 JsonPath js= ReUsableMethods.rawToJson(response);
 
 String id= js.getString("id");
 String key= js.getString("key");
 
 System.out.println("id of the bug is:"+id);
 System.out.println("key of the bug is:"+key);
 
 String response1= given().header("X-Atlassian-Token","no-check")
		 .header("Authorization",payload.BearerTokenForBasicAuth()).pathParam("key", key)
		 .multiPart("file", new File("C:/Users/938sa/OneDrive/Desktop/attachment1.png"))
		 .when().post("rest/api/3/issue/{key}/attachments")
		 .then().assertThat().statusCode(200).extract().response().asString();
 
JsonPath js1= ReUsableMethods.rawToJson(response1);
 
 String id1= js1.getString("id");
 String filename= js1.getString("filename");
 String emailAddress= js1.getString("author.emailAddress");
 String displayName= js1.getString("author.displayName");
 
 System.out.println("id of the attachment is:"+id1);
 System.out.println("name of the attachment is:"+filename);
 System.out.println("email of the attachment author is:"+emailAddress);
 System.out.println("name of the attachment author is:"+displayName);
 
	}

}
