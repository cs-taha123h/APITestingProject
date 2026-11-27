Feature: auth login
  As a new or existing user when I attempt to authorize my login
  I want to be able to access things that I have permission to access

  Background:
    Given I am on the "auth/login" api

  @HappyPath
  Scenario: Login in with correct password
    When my username is "sparta" and password is "global"
    And I submit my request as a post
    Then my response should be status code 200

  @SadPath
  Scenario: Login in with incorrect password
    When my username is "username" and password is "password"
    And I submit my request as a post
    Then my response should be status code 401