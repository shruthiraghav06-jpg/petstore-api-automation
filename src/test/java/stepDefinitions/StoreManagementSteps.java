package stepDefinitions;

import context.ScenarioContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StoreManagementSteps {

    ScenarioContext scenarioContext;
    Response response;

    public StoreManagementSteps(ScenarioContext scenarioContext){
        this.scenarioContext=scenarioContext;
    }

    @Given("I have details of pet")
    public void addPetDetails(){
        System.out.println("I have details of pet");
    }

    @When("I send a request to place order for the pet")
    public void placeOrderForPet(){
        System.out.println("I send a request to place order for the pet");
    }

    @Then("order should be created successfully with {int}")
    public void orderShouldBeCreated(int orderID){
        System.out.println("order should be created successfully with valid orderId");
    }

    @When("I send request to find the order by {int}")
    public void iSendRequestToFindTheOrderByOrderID(int orderID) {
        System.out.println("I send request to find the order by orderID");
    }

    @Then("I should see the respective order details")
    public void iShouldSeeTheRespectiveOrderDetails() {
        System.out.println("I should see the respective order details");
    }

    @When("I send request to delete the order by {int}")
    public void iSendRequestToDeleteTheOrderBy(int orderID) {
        System.out.println("I send request to delete the order by 10");
    }

    @Then("the order should get deleted")
    public void theOrderShouldGeDeleted() {
        System.out.println("the order should get deleted");
    }
}
