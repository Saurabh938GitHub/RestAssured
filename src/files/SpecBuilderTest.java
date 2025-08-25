package files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

	@Test(dataProvider="BooksData")
	public static void testing(double lat, double lng, String name, int accuracy) {
		createAddress(lat, lng, name, accuracy);
		
	}
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{-38.383494, 33.427362, "Frontline house1", 38}, {-50.383494, 55.427362, "Frontline house2", 50}};
	}
	
	public static void createAddress(double lat, double lng, String name, int accuracy) {
		
//		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		RequestSpecification req1= given().spec(req);
		 
		Response response= req1.body(payload.AddPlace())
		 .when().post("maps/api/place/add/json");
		
		String responseToString= response.then().spec(res).body("scope", equalTo("APP"))
				 .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		 
		 System.out.println(responseToString);
	}
	

}
