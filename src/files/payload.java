package files;

public class payload {

	public static String AddPlace() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String CoursePrice() {
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "";
	}
	
	public static String AddBook(String isbn, String aisle) {
		String payload="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		return payload;
	}
	
	public static String DeleteBook(String isbn, String aisle) {
		String payload="{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+(isbn+aisle)+"\"\r\n"
				+ " \r\n"
				+ "} ";
		return payload;
	}
	
	public static String CreateBug(String key, String summary) {
		String payload="{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \""+key+"\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \""+summary+"\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
		return payload;
	}
	
	public static String UpdateBugLabels(String toAdd, String toRemove) {
		String payload="{\r\n"
				+ "  \"update\": {\r\n"
				+ "    \"labels\": [\r\n"
				+ "      {\r\n"
				+ "        \"add\": \""+toAdd+"\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"remove\": \""+toRemove+"\"\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}";
		return payload;
	}
	
	public static String UpdateStatusOfBug(String transitionId) {
		String payload="{\"transition\": {\"id\": \"" + transitionId + "\"}}";
		return payload;
	}
	
	public static String UpdateAssigneeOfBug(String assigneeId) {
		String payload="{\"accountId\": \"" + assigneeId +"\"}";
		return payload;
	}
	
	public static String AddCommentInBug(String comment) {
		String payload="{\r\n"
				+ "  \"body\": {\r\n"
				+ "    \"version\": 1,\r\n"
				+ "    \"type\": \"doc\",\r\n"
				+ "    \"content\": [\r\n"
				+ "      {\r\n"
				+ "        \"type\": \"paragraph\",\r\n"
				+ "        \"content\": [\r\n"
				+ "          {\r\n"
				+ "            \"type\": \"text\",\r\n"
				+ "            \"text\": \""+comment+"\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}";
		return payload;
	}
	
	public static String BearerTokenForBasicAuth() {
		String token="Basic OTM4c2F1cmF2QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBwWUJmVWhkTnRuQUgtazNXdDlyYzZReXd0d1BNZVZVcUttRW9jUHd4a2RvVDc5N0N3cmxXUUdvVVl6RjFadmdkMWJsa01Yei16Uk14MkRIb0dhdmtKMTRTa1hFY3JjNEdTUUVxN2x2SGJkdHBOZnNSTTZRSTBTSUF1SDNlOF9BN3pmUDYySVJCUjViYWFCVG9ObmI1clROUUNMb3dJMlN6eUM0bjl0bXowUEU9QzZDRDM2RkY=";
		return token;
	}
	
}
