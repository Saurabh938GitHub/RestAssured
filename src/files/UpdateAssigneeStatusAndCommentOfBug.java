package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpdateAssigneeStatusAndCommentOfBug {

	@Test(dataProvider = "data")
	public void updateAssigneeStatusAndCommentOfBug(String key, String status, String assigneeAccountId, String comment) {

		RestAssured.baseURI = "https://938saurav.atlassian.net/";

		String transitionsResponse = given().header("Authorization", payload.BearerTokenForBasicAuth()).when()
				.get(baseURI + "/rest/api/2/issue/" + key + "/transitions").then().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(transitionsResponse);
		int statusListCount = js.getInt("transitions.size()");
		String statusId = "";
		for (int i = 0; i < statusListCount; i++) {
			if (js.getString("transitions[" + i + "].name").equals(status)) {
				statusId = js.getString("transitions[" + i + "].id");
				break;
			}
		}

		given().header("Content-Type", "application/json").header("Accept", "application/json")
				.header("Authorization", payload.BearerTokenForBasicAuth()).pathParam("key", key)
				.body(payload.UpdateStatusOfBug(statusId)).when().post("rest/api/2/issue/{key}/transitions").then()
				.assertThat().statusCode(204).extract().response().asString();

		given().header("Content-Type", "application/json").header("Accept", "application/json")
				.header("Authorization", payload.BearerTokenForBasicAuth()).pathParam("key", key)
				.body(payload.UpdateAssigneeOfBug(assigneeAccountId)).when().put("rest/api/2/issue/{key}/assignee")
				.then().assertThat().statusCode(204).extract().response().asString();

		String response = given().header("Content-Type", "application/json").header("Accept", "application/json")
				.header("Authorization", payload.BearerTokenForBasicAuth()).pathParam("key", key)
				.body(payload.AddCommentInBug(comment)).when().post("rest/api/3/issue/{key}/comment").then()
				.assertThat().statusCode(201).extract().response().asString();
		System.out.println("response------" + response);

	}

	@DataProvider(name = "data")
	public Object[][] getData() {
		return new Object[][] { { "SKJIR-5", "In Progress", "6098dc3e99b21f0070409880", "bug testing in progress" } };
	}

}
