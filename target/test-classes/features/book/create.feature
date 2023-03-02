Feature: BookController - create

  Background:
    Given there are no authors in the database
    And request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |

  Scenario: create book with null title throws bad request
    Given request body is:
    """
    {
      "title": null,
      "price": 0.0,
      "authorId": 1
    }
    """
    When I send a POST request to /books
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books"
    And response string value at "$.details.errors[0].field" is "title"
    And response string value at "$.details.errors[0].message" is "Book title is mandatory"
    And response value at "$.timestamp" is number

  Scenario: create book with empty title throws bad request
    Given request body is:
    """
    {
      "title": "",
      "price": 0.0,
      "authorId": 1
    }
    """
    When I send a POST request to /books
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books"
    And response string value at "$.details.errors[0].field" is "title"
    And response string value at "$.details.errors[0].message" is "Book title is mandatory"
    And response value at "$.timestamp" is number


  Scenario: create book with null price throws bad request
    Given request body is:
    """
    {
      "title": "Book title",
      "price": null,
      "authorId": 1
    }
    """
    When I send a POST request to /books
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books"
    And response string value at "$.details.errors[0].field" is "price"
    And response string value at "$.details.errors[0].message" is "Book price is mandatory"
    And response value at "$.timestamp" is number

  Scenario: create book with negative price throws bad request
    Given request body is:
    """
    {
      "title": "Book title",
      "price": -5,
      "authorId": 1
    }
    """
    When I send a POST request to /books
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books"
    And response string value at "$.details.errors[0].field" is "price"
    And response string value at "$.details.errors[0].message" is "Book price must be greater than 0"
    And response value at "$.timestamp" is number

  Scenario: create book with null authorId throws bad request
    Given request body is:
    """
    {
      "title": "Book title",
      "price": 50,
      "authorId": null
    }
    """
    When I send a POST request to /books
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books"
    And response string value at "$.details.errors[0].field" is "authorId"
    And response string value at "$.details.errors[0].message" is "Book authorId is mandatory"
    And response value at "$.timestamp" is number

  Scenario: create book with unknown author id throws not found
    Given request body is:
    """
    {
      "title": "Book title",
      "price": 50,
      "authorId": 1
    }
    """
    When I send a POST request to /books
    Then response status is NOT_FOUND
    And response string value at "$.status" is "NOT_FOUND"
    And response string value at "$.message" is "Author not found with given id"
    And response string value at "$.url" is "/books"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario: create book
    Given there are 1 authors in the database
    And request body is:
    """
    {
      "title": "L'art de chier dans les bois",
      "price": 69,
      "authorId": 1
    }
    """
    When I send a POST request to /books
    Then response status is CREATED
    And response number value at "$.id" is 1
    And response string value at "$.title" is "L'art de chier dans les bois"
    And response number value at "$.price" is 69
    And response number value at "$.author.id" is 1
    And response string value at "$.author.name" matches "^Author \d+$"


