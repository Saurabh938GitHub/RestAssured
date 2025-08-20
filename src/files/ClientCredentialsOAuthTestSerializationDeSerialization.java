package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;

public class ClientCredentialsOAuthTestSerializationDeSerialization {

	public static void main(String[] args) {


		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		String response= given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type","client_credentials")
				.formParam("scope", "trust")
				.when().post("oauthapi/oauth2/resourceOwner/token")
				.then().extract().response().asString();
		
		JsonPath js=ReUsableMethods.rawToJson(response);
		String access_token= js.getString("access_token");
		System.out.println(access_token);
		
		GetCourse gc= given().queryParam("access_token", access_token)
				.when().get("oauthapi/getCourseDetails")
				.then().assertThat().statusCode(401).extract().response().as(GetCourse.class);
		
		String instructor= gc.getInstructor();
		String linkedIn= gc.getLinkedIn();
		System.out.println("instructor:"+instructor);
		System.out.println("linkedIn:"+linkedIn);
		System.out.println("--------------------------xxxxxxxxxxxx-----------------------------");
		
		
		System.out.println("printing name and price of second course of Api courses");
		String secondCourseTitleOfApiCourses= gc.getCourses().getApi().get(1).getCourseTitle();
		System.out.println("secondCourseTitleOfApiCourses:"+secondCourseTitleOfApiCourses);
		
		for(int i=0; i<gc.getCourses().getApi().size(); i++) {
		if(gc.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
			System.out.println("price of 'SoapUI Webservices testing' course is:"+ gc.getCourses().getApi().get(i).getPrice());
		}
		}
		
		System.out.println("--------------------------xxxxxxxxxxxx-----------------------------");
		ArrayList<String> expectedCourseTitles= new ArrayList<String>();
		expectedCourseTitles.add("Selenium Webdriver Java");
		expectedCourseTitles.add("Cypress");
		expectedCourseTitles.add("Protractor");
		ArrayList<String> actualCourseTitles= new ArrayList<String>();
		
		System.out.println("printing name of all courses of Web Automation courses");
		for(int i=0; i<gc.getCourses().getWebAutomation().size(); i++) {
			actualCourseTitles.add(gc.getCourses().getWebAutomation().get(i).getCourseTitle());
				System.out.println("course "+(i+1)+" is :"+ gc.getCourses().getWebAutomation().get(i).getCourseTitle());
			}
		Assert.assertTrue(actualCourseTitles.equals(expectedCourseTitles));
		System.out.println("--------------------------xxxxxxxxxxxx-----------------------------");
		
		
		
	}

}
