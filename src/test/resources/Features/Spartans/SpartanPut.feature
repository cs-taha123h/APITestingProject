Feature: PUT spartan API
  As a user I want to update an existing spartan by ID

  Background:
    Given I am on the "api/Spartans" api
    And I have a valid authorisation token

  @HappyPath
  Scenario: Successfully update a spartan by ID
    When the spartanID is 2
    And I have a valid spartan update request body
    And I submit my request as a put
    Then my response should be status code 204
    And the spartan details should be updated