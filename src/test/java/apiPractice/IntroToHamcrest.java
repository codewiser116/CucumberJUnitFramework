package apiPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntroToHamcrest {

    /**
     * 1. hit the GET list of prescriptions
     * 2. verify status code is 200
     * 3. verify the number of prescriptions = 25
     */
    @Test
    public void verifyListOfPrescriptions(){
        Response response = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1/")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODU2MzY3LCJpYXQiOjE3NzM4NTI3NjcsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzg1Mjc2N31dLCJzZXNzaW9uX2lkIjoiMGE3MTY2NTgtMTk2ZS00OTdlLTliNmItMjYwYzhkODJmNzE4IiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.GdA-i_7sj0_tUlnf5QGKl2xSqZtlBxTTGSQlBBtqjl6TYLJGUQW430Svv0r_Xi3UgYbKLJ6nJMUpHwQHUSjpbQ")
                .get("/api-prescriptions");

        Assertions.assertEquals(200, response.statusCode());

        JSONObject responseBody = new JSONObject(response.body().asString()); // extracted entire response body and put inside JSONObject
        JSONArray arrayOfPrescriptions = responseBody.getJSONArray("data"); // extracted prescriptions only

        Assertions.assertEquals(25, arrayOfPrescriptions.length());
    }


    /**
     * 1. hit the GET list of prescriptions
     *      query param status = Active
     * 2. verify status code is 200
     * 3. verify the number of prescriptions = 25
     */
    @Test
    public void verifyListOfPrescriptionsWithQueryParam(){
        Response response = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1/")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODYwMTA0LCJpYXQiOjE3NzM4NTY1MDQsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzg1NjUwNH1dLCJzZXNzaW9uX2lkIjoiNzBkNmRlZTMtNDUxYi00NmYxLWJmMGItZmU3MTYyMTRjYTEyIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.Ds_UoxZTEUFqFepTsjnl3ekZaPC0fCsD2qZeKxa4ny-Yy8sI_AbDX8clMpLj8GR5We3rtMm7Jl7FcXCCgqK_xw")
                .queryParam("status", "Active")
                .queryParam("search", "Metoprolol")
                .get("/api-prescriptions");

        Assertions.assertEquals(200, response.statusCode());

        JSONObject responseBody = new JSONObject(response.body().asString()); // extracted entire response body and put inside JSONObject
        JSONArray arrayOfPrescriptions = responseBody.getJSONArray("data"); // extracted prescriptions only

        Assertions.assertEquals(25, arrayOfPrescriptions.length());

        // verify all prescriptions are Active and medication name = Metoprolol

        for (int i = 0; i < arrayOfPrescriptions.length(); i++){

            JSONObject prescription = arrayOfPrescriptions.getJSONObject(i);

            assertThat(prescription.getString("status"), equalTo("Active"));
            assertThat(prescription.getString("medication_name"), equalTo("Metoprolol"));
        }
    }

}
