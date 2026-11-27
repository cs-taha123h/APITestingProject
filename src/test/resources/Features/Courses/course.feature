Feature: Return course
  As a user
  I want to be able search for a specific course

  Background:
    Given I am on the "api/courses" api

  @HappyPath
  Scenario Outline: Fetch existing courses
    Given the courseID is "<CourseID>"
    When I submit my request as a get
    Then my response should be status code 200
    And courseName should be "<CourseName>"

    Examples:
      |CourseID|CourseName|
      |1       |TECH 300  |
      |2       |TECH 301  |
      |3       |TECH 302  |
      |4       |TECH 303  |
      |5       |DATA 304  |

  @SadPath
  Scenario: Fetch non-existing courses
    Given the courseID is "0"
    When I submit my request as a get
    Then my response should be status code 204