Feature: all tests related to retrieving appointments

  @getAppointments @apiRegression
  Scenario: verify user can get list of appointments
    Given base url
    And user has valid authorization
    When user hits GET "/api-appointments"
    Then verify status code is 200