Feature: test prescription creation

  Background:
    Given base url

  Scenario: successful prescription creation
    And "doctor" has valid authorization
    And user adds "patient_id" with value "PT-000012" in request body
    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 201


  Scenario: verify user fails to create prescription with missing patient id
    And "doctor" has valid authorization
#    And user adds "patient_id" with value "PT-000012" in request body
    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 400

  Scenario: verify user fails to create prescription with medication name
    And "doctor" has valid authorization
    And user adds "patient_id" with value "PT-000012" in request body
#    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 400


  Scenario: verify user fails to create prescription with incorrect patient id
    And "doctor" has valid authorization
    And user adds "patient_id" with value "PT-invalid-user" in request body
    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 400

  Scenario: verify user with invalid credentials fails to create prescription
    And user has invalid authorization
    And user adds "patient_id" with value "PT-006757" in request body
    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 401

  Scenario Outline: verify nurse fails to create prescription
    And "<userType>" has valid authorization
    And user adds "patient_id" with value "PT-000043" in request body
    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 403
    Examples:
      | userType       |
      | nurse          |
      | office manager |

