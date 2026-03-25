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
    When user hits POST "/api-appointments"
    Then verify status code is 201
    And verify "appointment_id" in response body is not null
    And verify "status" in response body is equal to "Scheduled"
    Then clean up by cancelling the appointment


  @doctorFailsToCreateAppointment
  Scenario: verify appointment cannot be created if required fields are missing
    Given base url
    And "doctor" has valid authorization
    And user adds following data in request body
#      | patient_id  | PT-000013 |
      | provider_id | DR-010    |
      | reason      | allergy   |
    And user provides valid appointment date and time
    When user hits POST "/api-appointments"
    Then verify status code is 400
    And verify "error" in response body is equal to "Validation failed"


  Scenario: verify appointment cannot be created if invalid inputs are given
    Given base url
    And "doctor" has valid authorization
    And user adds following data in request body
      | patient_id  | PT-non-existing |
      | provider_id | DR-non-existing |
      | reason      | allergy         |
    And user provides valid appointment date and time
    When user hits POST "/api-appointments"
    Then verify status code is 400
    And verify "error" in response body is not null


  Scenario: verify user cannot schedule appointment if start and end times are same
    Given base url
    And "doctor" has valid authorization
    And user adds following data in request body
      | patient_id  | PT-000013 |
      | provider_id | DR-010    |
      | reason      | allergy   |
    And user provides same times for start and end
    When user hits POST "/api-appointments"
    Then verify status code is 400
    And verify "error" in response body is equal to "End time must be after start time"


  Scenario: verify user cannot create appointment without authorization
    Given base url
    And user has invalid authorization
    And user adds following data in request body
      | patient_id  | PT-000013 |
      | provider_id | DR-010    |
      | reason      | allergy   |
    And user provides valid appointment date and time
    When user hits POST "/api-appointments"
    Then verify status code is 401
    And verify "error" in response body is equal to "Unauthorized"


  @conflictingAppointment
  Scenario: verify user can create appointment
    Given base url
    And "doctor" has valid authorization
    And user adds following data in request body
      | patient_id  | PT-000013 |
      | provider_id | DR-010    |
      | reason      | allergy   |
    And user provides valid appointment date and time
    When user hits POST "/api-appointments"
    Then verify status code is 201
    And verify "appointment_id" in response body is not null
    And verify "status" in response body is equal to "Scheduled"

#    scheduling second appointment for the same doctor for the same time
    And user adds following data in request body
      | patient_id  | PT-000015 |
      | provider_id | DR-010    |
      | reason      | fever     |
    And user provides conflicting date and time for appointment
    When user hits POST "/api-appointments"
    Then verify status code is 409
    And verify "error" in response body is equal to "Provider has a conflicting appointment"
