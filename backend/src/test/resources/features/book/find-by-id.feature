Feature: BookController - find by id

  Background:
    Given there are no books in the database

  Scenario: find book with unknown id throws not found
    When I send a GET request to /books/1
    Then response status is NOT_FOUND
    And response string value at "$.status" is "NOT_FOUND"
    And response string value at "$.message" is "Book not found with given id"
    And response string value at "$.url" is "/books/1"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario: find book with id
    Given there are 15 books in the database
    When I send a GET request to /books/1
    Then response status is OK
    And response string value at "$.title" matches "^Title \d+$"
    And response value at "$.price" is number
    And response value at "$.author.id" is number
    And response string value at "$.author.name" matches "^Author \d+$"
