Feature: test List Patients and Get Patient by id

  @getPatients
  Scenario: verify user can get list of patients
    Given base url
    And user has valid authorization
    When user hits GET "/api-patients"
    Then verify status code is 200

    @getPatientsUnauthorized
  Scenario: verify unauthorized user cannot get list of patients
    Given base url
    And user has invalid authorization
    When user hits GET "/api-patients"
    Then verify status code is 401

  @getPatientsWithQueryParam
  Scenario: verify user can filter the list of patients
    Given base url
    And user has valid authorization
    And user provided query param "page" with value "2"
    And user provided query param "gender" with value "Male"
    And user provided query param "status" with value "Active"
    And user provided query param "pageSize" with value "5"
    When user hits GET "/api-patients"
    Then verify status code is 200