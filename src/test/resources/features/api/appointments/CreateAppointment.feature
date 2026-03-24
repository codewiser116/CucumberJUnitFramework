Feature: verify different types of users can create appointment

  @doctorCreateAppointment
  Scenario: verify user can create appointment
    Given base url
    And "doctor" has valid authorization
    And user adds following data in request body
      | patient_id  | PT-000013 |
      | provider_id | DR-010    |
      | reason      | allergy   |
    And user provides valid appointment date and time
    And user hits POST "/api-appointments"
    Then verify status code is 201
    Then clean up by cancelling the appointment