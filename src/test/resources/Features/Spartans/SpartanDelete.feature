Feature: Delete a user API
  As a user I want to delete an individual spartan by ID

  Background:
    Given I am on the "api/Spartans" api

  @HappyPath
  Scenario: Successfully delete a spartan by ID
    When the spartanID is 11
    And I have a valid authorisation token
    And I submit my request as a delete
    Then my response should be status code 204
    Then when I search for id 11 it should return status code 204