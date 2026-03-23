package steps.api;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import pojo.Appointment;
import pojo.AppointmentResponse;
import pojo.Prescription;
import utils.APIUtils;
import utils.ConfigurationReader;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class ApiSteps extends APIUtils {

    RequestSpecification request;
    Response response;
    JSONObject requestBody = new JSONObject();


    @Given("base url")
    public void base_url() {
        request = RestAssured.given().baseUri(ConfigurationReader.getProperty("apiBaseURL"))
                .contentType(ContentType.JSON);
    }

    @Given("user has valid authorization")
    public void user_has_valid_authorization() {
        request = request.header("Authorization", "Bearer " + getToken());
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
        System.out.println(response.asPrettyString());
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

    @Then("verify {string} in response body is not null")
    public void verify_in_response_body_is_not_null(String key) {
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        Assertions.assertNotNull(jsonObject.get(key));
    }

    @Then("verify the number of appointments is <= {int}")
    public void verify_the_number_of_appointments_is(Integer numOfAppointments) {
        AppointmentResponse appointmentResponse = response.as(AppointmentResponse.class);
        Assertions.assertTrue(appointmentResponse.getData().size() <= numOfAppointments);
    }

    @Then("verify each appointment has id, provider_id, appointment_id, patient_id")
    public void verify_each_appointment_has_id_provider_id_appointment_id_patient_id() {

        AppointmentResponse appointmentResponse = response.as(AppointmentResponse.class);

        for (Appointment appointment : appointmentResponse.getData()){
            Assertions.assertNotNull(appointment.getId());
            Assertions.assertNotNull(appointment.getAppointment_id());
            Assertions.assertNotNull(appointment.getProvider_id());
            Assertions.assertNotNull(appointment.getPatient_id());
        }
    }

    @Given("user adds {string} with value {string} in request body")
    public void user_adds_with_value_in_request_body(String key, String value) {
        requestBody.put(key, value);
        request = request.body(requestBody.toString());
    }

    @Given("user hits POST {string}")
    public void user_hits_post(String endpoint) {
        response = request.post(endpoint);
        System.out.println(response.asPrettyString());
    }

}
