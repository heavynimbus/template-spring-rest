Feature: BookController - find all

  Background:
    Given there are no books in the database
    And there are no authors in the database
    And request headers are:
      | Accept | application/json |


  Scenario: find all books should return empty list
    When I send a GET request to /books
    Then response status is OK
    And response array length at "$" is 0

  Scenario: find all books
    Given there are 15 books in the database
    When I send a GET request to /books
    Then response status is OK
    And response array length at "$" is 15
    And all response values at "$[*].id" are numbers
    And all response string values at "$[*].title" match "^Title \d+$"

