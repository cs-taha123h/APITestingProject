Feature: GET api tests
  As a user I want to access an individual spartan by ID

  Background:
    Given I am on the "api/Spartans" api

  @HappyPath
  Scenario: Search for existing spartan
    Given the courseID is "2"
    And I have a valid authorisation token
    When I submit my request as a get
    Then my response should be status code 200
    And the spartan name should "John Lennon"

  @SadPath
  Scenario: Search for non-existing spartan
    Given the courseID is "0"
    And I have a valid authorisation token
    When I submit my request as a get
    Then my response should be status code 204