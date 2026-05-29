package stepDefinitions;

import context.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PetSteps {

	ScenarioContext scenarioContext;
	Response response;

	public PetSteps(ScenarioContext scenarioContext){
		this.scenarioContext = scenarioContext;
	}

	@Given("I have details of a new pet")
	public void addPetDetails() {
		System.out.println("Pet details are ready");
	}

	@When("I send a request to add the pet")
	public void sendRequestToAddPet() {
		String requestBody = "{\n" +
				"  \"id\": 101,\n" +
				"  \"name\": \"doggie\",\n" +
				"  \"status\": \"available\",\n" +
				"  \"photoUrls\": [\"https://example.com/dog.jpg\"]\n" +
				"}";

		response = given()
				.baseUri("https://petstore.swagger.io/v2")
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/pet")
				.then()
				.extract().response();

		int petId = response.jsonPath().getInt("id");
		scenarioContext.set("PET_ID",petId);
		System.out.println("Response: " + response.asPrettyString());
	}

	@Then("the pet should be created successfully")
	public void petDetailsShouldBeCreated() {
		Assert.assertEquals(200, response.getStatusCode());
	}

	@And("the response should contain the name {string}")
	public void responseShouldContainName(String expectedName) {
		String actualName = response.jsonPath().getString("name");
		Assert.assertEquals(expectedName, actualName);
	}

	// ─── Method 1 — Inline values ────────────────────────────────
	@When("I search for pets with status {string}")
	public void iSearchForPetsWithStatus(String status) {
		response = given()
				.baseUri("https://petstore.swagger.io/v2")
				.contentType(ContentType.JSON)
				.queryParam("status", status)
				.when()
				.get("/pet/findByStatus")
				.then()
				.extract().response();

		System.out.println("Response: " + response.asPrettyString());
	}

	@Then("I should get a list of available pets")
	public void iShouldGetListOfAvailablePets() {
		Assert.assertEquals(200, response.getStatusCode());

		List<Object> pets = response.jsonPath().getList("$"); // ✅ Object not Objects
		Assert.assertFalse("Pets list should not be empty", pets.isEmpty());

		System.out.println("Total pets found: " + pets.size());
	}

	// ─── Method 2 — DocString ─────────────────────────────────────
	@When("I send request to add the following pet:")
	public void iSendRequestToAddTheFollowingPet(String requestBody) { // ✅ String param added
		response = given()
				.baseUri("https://petstore.swagger.io/v2")
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/pet")
				.then()
				.extract().response();

		System.out.println("Response: " + response.asPrettyString());
	}

	// ─── Method 3 — DataTable ─────────────────────────────────────
	@When("I add a pet with following details:")
	public void iAddAPetWithFollowingDetails(DataTable dataTable) { // ✅ DataTable param added
		Map<String, String> petData = dataTable.asMap(String.class, String.class);

		String requestBody = "{\n" +
				"  \"id\": " + petData.get("id") + ",\n" +
				"  \"name\": \"" + petData.get("name") + "\",\n" +
				"  \"status\": \"" + petData.get("status") + "\",\n" +
				"  \"photoUrls\": [\"https://example.com/dog.jpg\"]\n" +
				"}";

		response = given()
				.baseUri("https://petstore.swagger.io/v2")
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/pet")
				.then()
				.extract().response();

		System.out.println("Response: " + response.asPrettyString());
	}
}