package stepdefinitions;

import base.BaseTest;
import constants.EndPoints;
import io.cucumber.java.en.*;
import io.restassured.filter.Filter;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.UserRequest;
import utils.ExtentRestAssuredFilter;
import utils.ExtentTestManager;

public class UserSteps extends BaseTest {

	Response response;
    static int userId;
    static UserRequest user;

    @Given("user prepares valid user payload")
    public void payload() {
    	
    	ExtentTestManager.getTest().info("user prepares valid user payload");

        user = new UserRequest();
        user.name = "Rahul Test";
        user.email = "rahketu2" + System.currentTimeMillis() + "@gmail.com";
        user.gender = "male";
        user.status = "active";

        //response = requestSpec().log().all().body(user).post(EndPoints.USERS);
        ExtentTestManager.getTest().info("Payload created");
    }

    @When("user sends POST request to create user")
    public void createUser() {
    	
    	ExtentTestManager.getTest().info("Sending POST request to create user");
    	
        response = requestSpec().log().all()
        		.filter(new ExtentRestAssuredFilter())
                .body(user).when()
                .post(EndPoints.USERS).then().log().all()
                .body(matchesJsonSchemaInClasspath("Schema/userSchemaResponseValidator.json"))
                .extract().response();
                
        userId = response.jsonPath().getInt("id");
        
    }

    @When("user sends GET request for created user")
    public void getUser() {
        response = requestSpec().log().all()
        		.filter(new ExtentRestAssuredFilter())
                .pathParam("id", userId)
                .get(EndPoints.USER_BY_ID).then().log().all().extract().response();
    }
    
    @When("user sends PUT request to update user")
    public void updateUser() {
        user.setName("Updated Rahul");
        response = requestSpec().log().all()
        		.filter(new ExtentRestAssuredFilter())
        		.pathParam("id", userId)
                .body(user)
                .put(EndPoints.USER_BY_ID).then().log().all().extract().response();
    }

    @When("user sends DELETE request")
    public void deleteUser() {
        response = requestSpec().log().all()
        		.filter(new ExtentRestAssuredFilter())
                .pathParam("id", userId)
                .delete(EndPoints.USER_BY_ID).then().log().all().extract().response();
    }
    
    @Then("response status should be {int}")
    public void validateStatus(int status) {
        response.then().statusCode(status);
    }
    
}