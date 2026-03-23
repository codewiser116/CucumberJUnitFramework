package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

public class APIUtils {



    public String getToken(){

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", ConfigurationReader.getProperty("email"));
        requestBody.put("password", ConfigurationReader.getProperty("password"));

        String responseBody = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("apiBaseURL"))
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api-auth/login")
                .getBody().asString();

        JSONObject jsonObject = new JSONObject(responseBody);

        return jsonObject.getString("token");
    }



}
