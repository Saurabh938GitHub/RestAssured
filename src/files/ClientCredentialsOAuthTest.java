package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class ClientCredentialsOAuthTest {

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
		
		String response1= given().queryParam("access_token", access_token)
				.when().get("oauthapi/getCourseDetails")
				.then().assertThat().statusCode(401).extract().response().asString();
		
		JsonPath js1=ReUsableMethods.rawToJson(response1);
		String instructor= js1.getString("instructor");
		String url= js1.getString("url");
		String services= js1.getString("services");
		String expertise= js1.getString("expertise");
		String linkedIn= js1.getString("linkedIn");
		String firstCourseTitle= js1.getString("courses.webAutomation[0].courseTitle");
		String secondCoursePrice= js1.getString("courses.webAutomation[1].price");
		String firstApiCourseTitle= js1.getString("courses.api[0].courseTitle");
		System.out.println("instructor:"+instructor);
		System.out.println("url:"+url);
		System.out.println("services:"+services);
		System.out.println("expertise:"+expertise);
		System.out.println("linkedIn:"+linkedIn);
		System.out.println("firstCourseTitle:"+firstCourseTitle);
		System.out.println("secondCoursePrice:"+secondCoursePrice);
		System.out.println("firstApiCourseTitle:"+firstApiCourseTitle);
		
		System.out.println(response1);
		
	}

}
