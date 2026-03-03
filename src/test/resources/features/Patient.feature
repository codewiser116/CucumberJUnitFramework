Feature: patient page related test cases

  Background:
    Given user goes to sign in page
    And user enters username "nurse.garcia@mediflow.com"
    And user enters password "Test@1234"
    And user clicks on sign in button

  Scenario: verify gender filter in patients list: female, male and other
    And user navigates to Patients page
    When user selects gender verify patients display correct gender
      | Female |
      | Male   |
      | Other  |

#  Scenario: verify gender filter in patients list: female, male and other
#    And user navigates to Patients page
#    When user selects "Female" in All Genders dropdown
#    Then verify "Female" patients are displayed