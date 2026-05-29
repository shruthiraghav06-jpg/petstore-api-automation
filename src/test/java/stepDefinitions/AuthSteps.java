package stepDefinitions;

import constants.contextKeys.ContextKeys;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class AuthSteps {
    ScenarioContext scenarioContext;
    public AuthSteps(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;
    }

    //====Pattern 1 - API Key====
    @Given("I have a valid API key")
    public void iHaveValidApiKey(){
        String apiKey = "SpecialKey";
        scenarioContext.set(ContextKeys.AUTH_TOKEN,apiKey);
        System.out.println("API key stored is: "+apiKey);
    }

    //====Pattern 2 - Bearer Token
    @Given("I login with username {string} and password {string}")
    public void iLoginWithCredentials(String username,String password){

        String reqBody = "{\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response response = given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .queryParam("username",username)
                .queryParam("password",password)
                    .when().get("/user/login")
                .then().extract().response();
        System.out.println(response.asPrettyString());
        String message = response.jsonPath().getString("message");
        String session = message.split(":")[1];
        scenarioContext.set(ContextKeys.AUTH_TOKEN,session);
        System.out.println(session);
    }
    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully(){
        Assert.assertNotNull("Login is successful, token is generated",ContextKeys.AUTH_TOKEN);
    }
}
