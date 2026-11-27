Feature: List of courses
  As a user
  I want to be able to find a list of all the courses

Background: Fetch list of course
  Given I am on the "api/courses" api
  When I submit my request as a get

@HappyPath
Scenario: Fetch list of courses
  Then my response should be status code 200
  And the list should contain 5