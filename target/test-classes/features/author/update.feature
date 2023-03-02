Feature: AuthorController - update

  Background:
    Given there are no authors in the database

  Scenario: update an author with unknown id throws not found
    Given request headers are:
      | Content-Type | application/json |
    And request body is:
      """
      {
        "name": "John Doe"
      }
      """
    When I send a PUT request to /authors/1
    Then response status is NOT_FOUND
    And response string value at "$.message" is "Author not found with given id"
    And response string value at "$.url" is "/authors/1"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario: update an author with null name throws bad request
    Given there are 1 authors in the database
    And request headers are:
      | Content-Type | application/json |
    And request body is:
      """
      {
        "name": null
      }
      """
    When I send a PUT request to /authors/1
    Then response status is BAD_REQUEST
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/authors/1"
    And response string value at "$.details.errors[0].field" is "name"
    And response string value at "$.details.errors[0].message" is "Author name is mandatory"
    And response value at "$.timestamp" is number

  Scenario: update an author with empty name throws bad request
    Given there are 1 authors in the database
    Given request headers are:
      | Content-Type | application/json |
    And request body is:
      """
      {
        "name": ""
      }
      """
    When I send a PUT request to /authors/1
    Then response status is BAD_REQUEST
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/authors/1"
    And response string value at "$.details.errors[0].field" is "name"
    And response string value at "$.details.errors[0].message" is "Author name is mandatory"
    And response value at "$.timestamp" is number

  Scenario: update an author
    Given there are 1 authors in the database
    Given request headers are:
      | Content-Type | application/json |
    And request body is:
      """
      {
        "name": "John Doe"
      }
      """
    When I send a PUT request to /authors/1
    Then response status is OK
    And response string value at "$.name" is "John Doe"
    And response value at "$.id" is number
