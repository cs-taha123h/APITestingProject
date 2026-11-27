Feature: SPARTA GET
  As a user I want to access the list of spartans

  Background:
    Given I am on the "api/spartans" api
    And I have a valid authorisation token
    When I submit my request as a get

  @HappyPath
  Scenario: Search for a list of spartans
    Then my response should be status code 200
    And the list should contain 33