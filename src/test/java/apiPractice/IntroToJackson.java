package apiPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojo.Appointment;
import pojo.AppointmentResponse;
import pojo.Pagination;
import utils.ConfigurationReader;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntroToJackson {

    @Test
    public void testListOfAppointments(){

        Response response = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("apiBaseURL"))
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczOTQ0MzA0LCJpYXQiOjE3NzM5NDA3MDQsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzk0MDcwNH1dLCJzZXNzaW9uX2lkIjoiYTZmOTFmNDAtZTkzMS00ZjdlLWFhZjktNTQ4NDcyNDhkMzZiIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.btCVM4rGhTkc8-iMGo_x5AWyE7O93itj-L5REZ3POlQZOkDn9dcUStawnGQ_VzWOBFBH_qKDaLXYEcuNWgH4XA")
                .get("api-appointments");

        assertThat(response.statusCode(), equalTo(200));

        AppointmentResponse appointmentResponse = response.as(AppointmentResponse.class);
        List<Appointment> appointments = appointmentResponse.getData();
        Pagination pagination = appointmentResponse.getPagination();

        for (Appointment appointment : appointments){
            System.out.println(appointment.getPatient_id());
        }

        System.out.println(pagination.getPageSize());

        /*
        print appointment_id and status of only those appointments whose status = Scheduled
         */


    }

    /*
    {
  "patient_id": "PT-000001",
  "provider_id": "DR-001",
  "datetime_start": "2026-03-19T17:48:56.682Z",
  "datetime_end": "2026-03-19T17:48:56.682Z",
  "reason": "string"
}
     */
    @Test
    public void testCreateAppointment(){
        Appointment requestBody = new Appointment();
        requestBody.setPatient_id("PT-000832");
        requestBody.setProvider_id("DR-009");
        requestBody.setDatetime_start("2026-06-10T15:30:00+00:00");
        requestBody.setDatetime_end("2026-06-10T15:45:00+00:00");
        requestBody.setReason("Allergy");

        Response response = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("apiBaseURL"))
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiIwMGE5ZTEwOS0wMmQ4LTRhMDUtYTFjNS1kZDNiNGRlZmE4ZWIiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczOTQ0MzA0LCJpYXQiOjE3NzM5NDA3MDQsImVtYWlsIjoiZHIuY2hlbkBtZWRpZmxvdy5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImVtYWlsIiwicHJvdmlkZXJzIjpbImVtYWlsIl19LCJ1c2VyX21ldGFkYXRhIjp7ImVtYWlsX3ZlcmlmaWVkIjp0cnVlfSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3Mzk0MDcwNH1dLCJzZXNzaW9uX2lkIjoiYTZmOTFmNDAtZTkzMS00ZjdlLWFhZjktNTQ4NDcyNDhkMzZiIiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.btCVM4rGhTkc8-iMGo_x5AWyE7O93itj-L5REZ3POlQZOkDn9dcUStawnGQ_VzWOBFBH_qKDaLXYEcuNWgH4XA")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api-appointments");

        System.out.println(response.asPrettyString());

        assertThat(response.statusCode(), equalTo(201));

        Appointment appointmentResponse = response.as(Appointment.class);

        System.out.println(appointmentResponse.getAppointment_id());
        System.out.println(appointmentResponse.getReason());
        System.out.println(appointmentResponse.getProvider_id());

    }





}
