package files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo.GetAddAddressApiResponseBody;
import pojo.GetGetAddressApResponseBody;
import pojo.Location;
import pojo.SetAddAddressApiRequestBody;

public class SerializationDeSerializaionTest {
	static GetAddAddressApiResponseBody response= new GetAddAddressApiResponseBody();
	static GetGetAddressApResponseBody response1= new GetGetAddressApResponseBody();
	List<String> list1= new ArrayList<String>();

	@Test(dataProvider="BooksData")
	public static void testing(double lat, double lng, String name, int accuracy) {
		createAddress(lat, lng, name, accuracy);
		System.out.println("-----------------------------------xxxx---------------------------------------------");
		System.out.println();
		getAddress();
		System.out.println("-----------------------------------xxxx---------------------------------------------");
		System.out.println();
		assertResponseAndRequest(lat, lng, name, accuracy);
		
	}
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{-38.383494, 33.427362, "Frontline house1", 38}, {-50.383494, 55.427362, "Frontline house2", 50}};
	}
	
	public static void createAddress(double lat, double lng, String name, int accuracy) {
		SetAddAddressApiRequestBody setAdd= new SetAddAddressApiRequestBody();
		List<String> list= new ArrayList<String>();
		Location location= new Location();
		location.setLat(lat);
		location.setLng(lng);
		list.add("shoe park");
		list.add("shop");
		
		setAdd.setLocation(location);
		setAdd.setAccuracy(accuracy);
		setAdd.setName(name);
		setAdd.setPhone_number("(+91) 983 893 3937");
		setAdd.setAddress("29, side layout, cohen 09");
		setAdd.setTypes(list);
		setAdd.setWebsite("http://google.com");
		setAdd.setLanguage("French-IN");
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		 
		response= given().header("Content-Type", "application/json").queryParam("key", "qaclick123")
		 .body(setAdd)
		 .when().post("maps/api/place/add/json")
		 .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		 .header("Server", "Apache/2.4.52 (Ubuntu)")
		 .extract().response().as(GetAddAddressApiResponseBody.class);
		
		 
		 System.out.println("create address api response body status is: "+response.getStatus());
		 System.out.println("create address api response body place_id is :"+response.getPlace_id());
		 System.out.println("create address api response body scope is :"+response.getScope());
		 System.out.println("create address api response body reference is :"+response.getReference());
		 System.out.println("create address api response body id is: "+response.getId());
	}
	
	public static void getAddress() {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		 
		response1= given().queryParam("key", "qaclick123")
		 .queryParam("place_id", response.getPlace_id())
		 .when().get("/maps/api/place/get/json")
		 .then().assertThat().statusCode(200)
		 .extract().response().as(GetGetAddressApResponseBody.class);
		
		 System.out.println("get address api response body latitude is: "+response1.getLocation().getLatitude());
		 System.out.println("get address api response body latitude is: "+response1.getLocation().getLongitude());
		 System.out.println("get address api response body accuracy is: "+response1.getAccuracy());
		 System.out.println("get address api response body name is :"+response1.getName());
		 System.out.println("get address api response body phone number is :"+response1.getPhone_number());
		 System.out.println("get address api response body address is :"+response1.getAddress());
		 System.out.println("get address api response body website is: "+response1.getWebsite());
		 System.out.println("get address api response body language is: "+response1.getLanguage());
	}
	
	public static void assertResponseAndRequest(double lat, double lng, String name, int accuracy) {
		Assert.assertTrue(response1.getLocation().getLatitude()==lat);
		Assert.assertTrue(response1.getLocation().getLongitude()==lng);
		Assert.assertTrue(response1.getAccuracy()==accuracy);
		Assert.assertTrue(response1.getName().equals(name));
		Assert.assertTrue(response1.getPhone_number().equals("(+91) 983 893 3937"));
		Assert.assertTrue(response1.getAddress().equals("29, side layout, cohen 09"));
		Assert.assertTrue(response1.getWebsite().equals("http://google.com"));
		Assert.assertTrue(response1.getLanguage().equals("French-IN"));
		System.out.println("All assertions passed");
	}
	

}
