Feature: AuthorController - create

  Background:
    Given there are no authors in the database

  Scenario: create author with null name throws bad request
    Given request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    And request body is:
    """
    {
      "name": null
    }
    """
    When I send a POST request to /authors
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/authors"
    And response string value at "$.details.errors[0].field" is "name"
    And response string value at "$.details.errors[0].message" is "Author name is mandatory"
    And response value at "$.timestamp" is number

  Scenario: create author with empty name throws bad request
    Given request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    And request body is:
    """
    {
      "name": ""
    }
    """
    When I send a POST request to /authors
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/authors"
    And response array length at "$.details.errors" is 1
    And response string value at "$.details.errors[0].field" is "name"
    And response string value at "$.details.errors[0].message" is "Author name is mandatory"
    And response value at "$.timestamp" is number

  Scenario: create author with name
    Given request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    And request body is:
    """
    {
      "name": "Rick Astley"
    }
    """
    When I send a POST request to /authors
    Then response status is CREATED
    And response number value at "$.id" is 1
    And response string value at "$.name" is "Rick Astley"

  Scenario: can create author with same name
    Given request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    And request body is:
    """
    {
      "name": "Michael Jackson"
    }
    """
    When I send a POST request to /authors
    Then response status is CREATED
    And response number value at "$.id" is 1
    And response string value at "$.name" is "Michael Jackson"

    Given request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    And request body is:
    """
    {
      "name": "Luv Resval"
    }
    """
    When I send a POST request to /authors
    Then response status is CREATED
    And response number value at "$.id" is 2
    And response string value at "$.name" is "Luv Resval"
