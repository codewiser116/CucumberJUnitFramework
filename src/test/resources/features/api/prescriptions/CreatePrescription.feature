Feature: test prescription creation

  Scenario: successful prescription creation
    Given base url
    And user has valid authorization
    And user adds "patient_id" with value "PT-000012" in request body
    And user adds "medication_name" with value "Metoprolol" in request body
    And user adds "dosage" with value "100 mg" in request body
    And user adds "frequency" with value "Once daily" in request body
    And user adds "duration_days" with value "10" in request body
    And user adds "instructions" with value "consult with your doctor" in request body
    And user hits POST "/api-prescriptions"
    Then verify status code is 201


