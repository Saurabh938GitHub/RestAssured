import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String args[]) {
	
	JsonPath js= new JsonPath(payload.CoursePrice());
	
	int count= js.getInt("courses.size()");
	System.out.println(count);
	
	int totalAmount= js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
	
	String titleFirstCourse= js.getString("courses[0].title");
	System.out.println(titleFirstCourse);
	
	System.out.println("printing all courses title and price");
	for(int i=0; i<count; i++) {
		String courseTitles= js.getString("courses["+i+"].title");
		System.out.println(courseTitles);
		int coursePrices= js.getInt("courses["+i+"].price");
		System.out.println(coursePrices);
	}
	
	System.out.println("print no of copies sold for RPA course");
	for(int i=0; i<count; i++) {
		String courseTitles= js.getString("courses["+i+"].title");
		if(courseTitles.equalsIgnoreCase("RPA")) {
			System.out.println(js.getInt("courses["+i+"].copies"));
			break;
		}
	}
	
    System.out.println("verify if sum of all course prices matches with purchase amount");
	
	int purchaseAmount= js.getInt("dashboard.purchaseAmount");
	System.out.println("purchase amount is:"+purchaseAmount);
	int allCoursePrice=0;
	for(int i=0; i<count; i++) {
		int price= js.getInt("courses["+i+"].price");
		int copies= js.getInt("courses["+i+"].copies");
		allCoursePrice=allCoursePrice+(price*copies);
		
	}
	System.out.println("sum of all course prices is:"+allCoursePrice);
	Assert.assertEquals(purchaseAmount, allCoursePrice);
	
	}
}