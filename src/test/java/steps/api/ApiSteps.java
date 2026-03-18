package steps.api;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
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
        request = request.header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODUxODY3LCJpYXQiOjE3NzM4NDgyNjcsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzg0ODI2N31dLCJzZXNzaW9uX2lkIjoiMmM3YjQ4YmItYWY2NS00MDM0LThhYTMtZDEwOWMwNWFlMTRmIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.VtaBxUU-fikrIXiAWC2AO4MHAXYL42etNBzijCMxKxx2UXMKZWBKYBrdoU2nNUhRtmKqagKBLcadSZ_Y_LLP-g");
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

    @Then("verify the number of patients is <= {int}")
    public void verify_the_number_of_patients_is(Integer maxNumberOfPatients) {
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);

        JSONArray arrayOfPatients = jsonObject.getJSONArray("data");
        System.out.println("NUMBER OF PATIENTS: " + arrayOfPatients.length());

        for (int i = 0; i < arrayOfPatients.length(); i++){
            JSONObject patientObj = arrayOfPatients.getJSONObject(i);
            System.out.println(patientObj.getString("first_name"));
            System.out.println(patientObj.getString("email"));
        }


    }

}
