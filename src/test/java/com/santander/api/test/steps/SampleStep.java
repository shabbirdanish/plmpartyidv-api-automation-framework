//package com.santander.api.test.steps;
//
//import com.santander.api.automation.config.ApplicationConfig;
//import com.santander.api.automation.core.RestAssuredAPI;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.response.Response;
//import org.json.simple.parser.ParseException;
//import org.junit.Assert;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//
//public class SampleStep {
//
//    @Autowired
//    RestAssuredAPI restAssured;
//
//    @Autowired
//    ApplicationConfig appConfig;
//
//    Response resp = null;
//    String genderServiceURL = "";
//    String dataServiceURL = "";
//
//    @Before
//    public void AddAdditionalConfig() {
//        System.out.println("Adding additional configuration");
//        appConfig.addProperties("src/test/resources/additional.properties");
//    }
//
//    @Given("^an API endPoint for users$")
//    public void an_API_endPoint_for_users() throws IOException, ParseException {
//        resp = restAssured.httpGet(appConfig.getProperty("url"));
//        //restAssured.httpPostWithJSON()
//    }
//
//    @And("^I want to make GET call$")
//    public void i_want_to_make_GET_call()  {
//
//        System.out.println("checking the apiURL= " + resp.getContentType());
//
//    }
//    @And("^I expect a JSON Response$")
//    public void I_expect_a_JSON_Reponse() {
//        // String title =  response.body().jsonPath().get("title");
//    }
//
//    @Given("an API endPoint for gender prediction")
//    public void an_api_end_point_for_gender_prediction() {
//        genderServiceURL = appConfig.getProperty("genderservice");
//    }
//
//    @When("when api service called with {word}")
//    public void when_api_service_called_with_rebecca(String name) {
//        resp = restAssured.httpGet(genderServiceURL.replace("???",name));
//    }
//
//    @Then("return response is {int}")
//    public void return_response(int responsecode) {
//        System.out.println("Actual Response Code:" + resp.statusCode());
//
//        Assert.assertEquals("response code not matching",responsecode, resp.statusCode());
//    }
//
//    @Then("return gender is {word}")
//    public void return_gender(String gender) {
//        String expectedValue = gender;
//        String actualValue = resp.body().jsonPath().get("gender").toString();
//
//        System.out.println(String.format("Expected %s and Actual %s", expectedValue, actualValue));
//
//        Assert.assertEquals("Gender prediction wrong",expectedValue,actualValue);
//    }
//
//
//    @Given("^an API endPoint for dataservice$")
//    public void an_API_endPoint_for_dataservice() throws IOException, ParseException {
//
//        System.out.println("Test Key:" + appConfig.getProperty("test.key"));
//        dataServiceURL = appConfig.getProperty("dataservice");
//    }
//
//    @When("^make GET call for version$")
//    public void make_GET_call_for_version() {
//        resp = restAssured.httpGet(dataServiceURL + "/version");
//    }
//
//    @Then("^response code match 200$")
//    public void verify_response_code() {
//        System.out.println("Actual Response Code:" + resp.statusCode());
//
//        Assert.assertEquals("response code not matching",200, resp.statusCode());
//    }
//
//    @And("^returned version match 1.1.0$")
//    public void verify_version() {
//        String expectedValue = "1.1.0";
//        String actualValue = resp.prettyPrint();
//
//        System.out.println(String.format("Expected %s and Actual %s", expectedValue, actualValue));
//
//        Assert.assertEquals("Gender prediction wrong",expectedValue,actualValue);
//    }
//}