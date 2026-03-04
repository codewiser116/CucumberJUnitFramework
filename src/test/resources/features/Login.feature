@smoke @regression @loginTests
Feature: all login related test scenarios

  Background:
    Given user goes to sign in page

  Scenario: verify doctor can login successfully
    When user enters username "dr.chen@mediflow.com"
    And user enters password "Test@1234"
    And user clicks on sign in button
    Then verify user signed in successfully

    Scenario:
      When user enters credentials "invalidUsername" and "invalidPassword"
      Then verify user failed to sign in

  Scenario:
    When user enters credentials "validUsername" and "validPassword"
    Then verify user signed in successfully

#
#  Scenario: verify nurse can login successfully
#    When user enters username "nurse.garcia@mediflow.com"
#    And user enters password "Test@1234"
#    And user clicks on sign in button
#    Then verify user signed in successfully
#
#  Scenario: verify office manager can login successfully
#    When user enters username "mgr.wilson@mediflow.com"
#    And user enters password "Test@1234"
#    And user clicks on sign in button
#    Then verify user signed in successfully

  @usersLogin @multiple @abc @zyx
  Scenario Outline: verify all types of users can login
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks on sign in button
    Then verify user signed in successfully
    Examples:
      | username                  | password  |
      | dr.chen@mediflow.com      | Test@1234 |
      | mgr.wilson@mediflow.com   | Test@1234 |
      | nurse.garcia@mediflow.com | Test@1234 |

  @usersLogin @multiple @abc @zyx
  Scenario Outline: verify all types of users fail to login with invalid credentials
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks on sign in button
    Then verify user failed to sign in
    Examples:
      | username                | password  |
      | dr@mediflow.com         | Test@1234 |
      | mgr.wilson@mediflow.com | TEST@1234 |
      |                         | Test@1234 |
      | mgr.wilson@mediflow.com |           |
      |                         |           |



#    command + option + shift + L  ===> to format your file

