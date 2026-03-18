package apiPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class IntroToRestAssured {

    /*
    TEST POST - LOGIN - REQUEST
        1. provide base url, endpoint
        2. provide request body: email and password
        3. provide headers: content type and accept type
        4. hit POST button
        5. verify status code is 200
        6. verify response body contains token
        7. print response body


        TWO JAVA CLASSES IN REST ASSURED:
            - RequestSpecification request - this object can be used to build your request
            - Response response - this object can be used to store your response
     */

    public static void main(String[] args) {

        // this represents request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "dr.chen@mediflow.com");
        requestBody.put("password", "Test@1234");


        RequestSpecification request = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .contentType(ContentType.JSON)
                .body(requestBody.toString());

        Response response = request.post("/api-auth/login");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY" + response.asPrettyString());
    }


    @Test
    public void testGetListOfPatients(){
        RequestSpecification request = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzY2NDA3LCJpYXQiOjE3NzM3NjI4MDcsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzc2MjgwN31dLCJzZXNzaW9uX2lkIjoiZmFjYWExMDItMzgzNi00NmRhLThhYTAtNzExZWNmMzE1YTUyIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.3ZUHdO8ys82x1-gP2Uk4449xmrI8NMa04p1daiCF7eh-sIXIZGG5XwLF6_3hjgzGeNTejnsvCB2_9MXrCJNIIA");

        Response response = request.get("/api-patients");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY" + response.asPrettyString());
    }

    @Test
    public void testUpdatePatient(){
        // this represents request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("patient_id", "PT-000162");
        requestBody.put("last_name", "Jackson");

        RequestSpecification request = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzY2NDA3LCJpYXQiOjE3NzM3NjI4MDcsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzc2MjgwN31dLCJzZXNzaW9uX2lkIjoiZmFjYWExMDItMzgzNi00NmRhLThhYTAtNzExZWNmMzE1YTUyIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.3ZUHdO8ys82x1-gP2Uk4449xmrI8NMa04p1daiCF7eh-sIXIZGG5XwLF6_3hjgzGeNTejnsvCB2_9MXrCJNIIA")
                .body(requestBody.toString());

        Response response = request.put("/api-patients/PT-000162");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY" + response.asPrettyString());
    }

    @Test
    public void testGetAppointments(){
        RequestSpecification request = RestAssured
                .given().baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJjMzEzZGIxNi01ZDc1LTRhOGYtODc4MS04OTdjYjExNzUzMDAiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzY2NjYxLCJpYXQiOjE3NzM3NjMwNjEsImVtYWlsIjoiZHIub3Ntb25hbGlldmFAbWVkaWZsb3cuY29tIiwicGhvbmUiOiIiLCJhcHBfbWV0YWRhdGEiOnsicHJvdmlkZXIiOiJlbWFpbCIsInByb3ZpZGVycyI6WyJlbWFpbCJdfSwidXNlcl9tZXRhZGF0YSI6eyJlbWFpbF92ZXJpZmllZCI6dHJ1ZX0sInJvbGUiOiJhdXRoZW50aWNhdGVkIiwiYWFsIjoiYWFsMSIsImFtciI6W3sibWV0aG9kIjoicGFzc3dvcmQiLCJ0aW1lc3RhbXAiOjE3NzM3NjMwNjF9XSwic2Vzc2lvbl9pZCI6Ijc3OWU3NTc0LWI1YTMtNDI4Ny1iNmUwLTQxZGU2YWViMTkzNyIsImlzX2Fub255bW91cyI6ZmFsc2V9.lJJNNH2sUIfOL9AaxPHXwfhkL7HC2kEYscxMzdvsZ9Y2lkMTI8HNCFi945IeWAZchshHImVRf5-yp-jRN4ALDQ")
                .queryParam("page", 2)
                .queryParam("pageSize", 10)
                .queryParam("status", "Cancelled");


        Response response = request.get("/api-appointments");
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.asPrettyString());
    }

    @Test
    public void serializeWithJson(){
        /*
        get all patients and print first_name, last_name, dob
        only of those patients whose first name starts with M
         */

        RequestSpecification request = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzY2NDA3LCJpYXQiOjE3NzM3NjI4MDcsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzc2MjgwN31dLCJzZXNzaW9uX2lkIjoiZmFjYWExMDItMzgzNi00NmRhLThhYTAtNzExZWNmMzE1YTUyIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.3ZUHdO8ys82x1-gP2Uk4449xmrI8NMa04p1daiCF7eh-sIXIZGG5XwLF6_3hjgzGeNTejnsvCB2_9MXrCJNIIA");

        Response response = request.get("/api-patients");

        System.out.println("STATUS CODE: " + response.statusCode());


    }

    @Test
    public void verifyAppointmentsQueryParams(){
        /*
        1. hit get all appointments endpoint with query params:
        page = 2
        pageSize = 10
        status = Cancelled
        2. in response body:
        - verify number of appointments = 10
        - verify page number = 2
        - verify all appointments = Cancelled

         */

        RequestSpecification request =
                RestAssured.given()
                        .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                        .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJkYmM4ZGMzMy1iNDQwLTQ3OWMtOTgyOC1kYjg1ZDExYzFjZTUiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODU2NDc2LCJpYXQiOjE3NzM4NTI4NzYsImVtYWlsIjoiZHIudGVtaXJvdmFAbWVkaWZsb3cuY29tIiwicGhvbmUiOiIiLCJhcHBfbWV0YWRhdGEiOnsicHJvdmlkZXIiOiJlbWFpbCIsInByb3ZpZGVycyI6WyJlbWFpbCJdfSwidXNlcl9tZXRhZGF0YSI6eyJlbWFpbF92ZXJpZmllZCI6dHJ1ZX0sInJvbGUiOiJhdXRoZW50aWNhdGVkIiwiYWFsIjoiYWFsMSIsImFtciI6W3sibWV0aG9kIjoicGFzc3dvcmQiLCJ0aW1lc3RhbXAiOjE3NzM4NTI4NzZ9XSwic2Vzc2lvbl9pZCI6IjY5ZDhiNjA4LTk5OTQtNDFiZS04ZmMwLTgyNjc1MzEwNmI2NyIsImlzX2Fub255bW91cyI6ZmFsc2V9.F-65kPEzGcsfNQFEOUj_t5d49xyUaq4yCTVk3LNLt4z4TslxaXr9GKC6-9HkGvlJ-G6cnL9bmI_TqoIGq7Lc2w")
                        .queryParam("page", 2)
                        .queryParam("pageSize", 10)
                        .queryParam("status", "Cancelled");

        Response response = request.get("/api-appointments");

        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);

        JSONArray arrayOfAppointments = jsonObject.getJSONArray("data");

        int actualPageSize = arrayOfAppointments.length();
        Assertions.assertEquals(10, actualPageSize); //appointments ==10

        for (int i = 0; i < arrayOfAppointments.length(); i++) {
            JSONObject appointmentObj = arrayOfAppointments.getJSONObject(i);

            String actualStatus = appointmentObj.getString("status");
            Assertions.assertEquals("Cancelled", actualStatus); //all appointments are cancelled

        }

        JSONObject pagination = jsonObject.getJSONObject("pagination");
        Assertions.assertEquals(2, pagination.getInt("page")); //page ==2
    }







}
