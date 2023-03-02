Feature: BookController - delete

  Background:
    Given there are no books in the database
    And there are no authors in the database

  Scenario: delete book with unknown id throws not found
    When I send a DELETE request to /books/1
    Then response status is NOT_FOUND
    And response string value at "$.status" is "NOT_FOUND"
    And response string value at "$.message" is "Book not found with given id"
    And response string value at "$.url" is "/books/1"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario: delete book
    Given there are 1 books in the database
    When I send a DELETE request to /books/1
    Then response status is NO_CONTENT
