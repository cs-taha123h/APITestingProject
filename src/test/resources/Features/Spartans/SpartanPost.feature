Feature: POST spartan requests
  As a user I want to add a new spartan

  Background:
    Given I am on the "api/Spartans" api

  @HappyPath
  Scenario: Successfully add a new spartan
    And I send a valid spartan request body
    And I have a valid authorisation token
    When I submit my request as a post
    Then my response should be status code 200
    And the response should contain the new spartan details

  @SadPath
  Scenario: Attempt to add a spartan with invalid details
    And I send an invalid spartan request body
    And I have a valid authorisation token
    When I submit my request as a post
    Then my response should be status code 415