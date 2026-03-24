package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom;

public class APIUtils {


    /**
     *
     * @param userType: doctor, nurse, office manager
     * @return valid token
     */
    public String getToken(String userType){

        String email;

        switch (userType.toLowerCase()){
            case "doctor" :
                email = ConfigurationReader.getProperty("doctorEmail");
                break;
            case "nurse":
                email = ConfigurationReader.getProperty("nurseEmail");
                break;
            case "office manager":
                email = ConfigurationReader.getProperty("officeManagerEmail");
                break;
            default:
                throw new InputMismatchException("Error! Provide valid type of user: doctor, nurse, office manager");
        }


        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
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

    /**
     * generates random dates in following format
     * | datetime_start | 2025-03-23T16:40:07.561Z |
     * | datetime_end   | 2025-03-23T17:40:07.561Z |
     * @return
     */
    public String[] getStartEndTimeForAppointment() {
        Instant now = Instant.now();

        // Generate a random number of days between 1 and 365
        long daysToAdd = ThreadLocalRandom.current().nextLong(1, 366);

        // Generate a random number of hours between 0 and 23
        long hoursToAdd = ThreadLocalRandom.current().nextLong(8, 18);

        // Generate a random number of minutes between 0 and 59
        long minutesToAdd = ThreadLocalRandom.current().nextLong(0, 60);

        // Add the random days, hours, and minutes to the current date
        Instant futureDate = now.plus(daysToAdd, ChronoUnit.DAYS)
                .plus(hoursToAdd, ChronoUnit.HOURS)
                .plus(minutesToAdd, ChronoUnit.MINUTES);

        // Format the future date as ISO-8601 string
        String startDateTime = futureDate.toString();
        String endDateTime = futureDate.plus(1, ChronoUnit.HOURS).toString();

        return new String[]{startDateTime, endDateTime};
    }



}
