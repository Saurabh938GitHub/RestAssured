package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166/";
		String response=given().log().all().header("Content-Type", "application/json")
		.body(payload.AddBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath js= ReUsableMethods.rawToJson(response);
		String id= js.getString("ID");
		System.out.println(id);
	}
	
	@Test(dataProvider="BooksData")
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166/";
		String response=given().log().all().header("Content-Type", "application/json")
		.body(payload.DeleteBook(isbn,aisle))
		.when().post("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= ReUsableMethods.rawToJson(response);
		System.out.println(js.getString("msg"));
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{"saur","11"}, {"abhi","22"}, {"laka","33"}};
	}
}
