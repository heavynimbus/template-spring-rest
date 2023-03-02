Feature: AuthorController - delete

  Background:
    Given there are no authors in the database

  Scenario: Delete an author with unknown id throws not found
    When I send a DELETE request to /authors/1
    Then response status is NOT_FOUND
    And response string value at "$.message" is "Author not found with given id"
    And response string value at "$.url" is "/authors/1"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario: Delete an author with known id
    Given there are 1 authors in the database
    When I send a DELETE request to /authors/1
    Then response status is NO_CONTENT
