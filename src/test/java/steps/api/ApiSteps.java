package steps.api;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import utils.ConfigurationReader;

public class ApiSteps {

    RequestSpecification request;
    Response response;

    @Given("base url")
    public void base_url() {
        request = RestAssured.given().baseUri(ConfigurationReader.getProperty("apiBaseURL"));
    }

    @Given("user has valid authorization")
    public void user_has_valid_authorization() {
        request = request.header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzcyMzc1LCJpYXQiOjE3NzM3Njg3NzUsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzc2ODc3NX1dLCJzZXNzaW9uX2lkIjoiMGYxYjVhNjItYmZkMy00YzEyLWJlOGQtZGVhZjJiY2E2ZWMyIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.kuNbMVIe_3nNl0nhi-cmPmpdP0TyFzqZPq1KJ1VAKqiU4HdB7R4hQq0X1OlFF2adI29M6Ip8Lyg5MEpyzVVHVA");
    }

    @Given("user has invalid authorization")
    public void user_has_invalid_authorization() {
        request = request.header("Authorization", "Bearer invalidTokenM6Ip8Lyg5MEpyzVVHVA");

    }

    @When("user hits GET {string}")
    public void user_hits_get(String endpoint) {
        response = request.get(endpoint);
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Given("user provided query param {string} with value {string}")
    public void user_provided_query_param_with_value(String key, String value) {
       request = request.queryParam(key, value);
    }

}
