Feature: BookController - update

  Background:
    Given there are no books in the database
    And there are no authors in the database
    And request headers are:
      | Content-Type | application/json |


  Scenario: update book with unknown id throws not found
    Given request body is:
      """
      {
        "title": "The Lord of the Rings",
        "price": 19.99,
        "authorId": 1
      }
      """
    When I send a PUT request to /books/1
    Then response status is NOT_FOUND
    And response string value at "$.message" is "Book not found with given id"
    And response string value at "$.url" is "/books/1"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario: update book with null title throws bad request
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": null,
        "price": 19.99,
        "authorId": 1
      }
      """
    When I send a PUT request to /books/1
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books/1"
    And response string value at "$.details.errors[0].field" is "title"
    And response string value at "$.details.errors[0].message" is "Book title is mandatory"
    And response value at "$.timestamp" is number

  Scenario: update book with empty title throws bad request
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": "",
        "price": 19.99,
        "authorId": 1
      }
      """
    When I send a PUT request to /books/1
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books/1"
    And response string value at "$.details.errors[0].field" is "title"
    And response string value at "$.details.errors[0].message" is "Book title is mandatory"
    And response value at "$.timestamp" is number

  Scenario: update book with null price throws bad request
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": "The Lord of the Rings",
        "price": null,
        "authorId": 1
      }
      """
    When I send a PUT request to /books/1
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books/1"
    And response string value at "$.details.errors[0].field" is "price"
    And response string value at "$.details.errors[0].message" is "Book price is mandatory"
    And response value at "$.timestamp" is number

  Scenario: update book with negative price throws bad request
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": "The Lord of the Rings",
        "price": -1,
        "authorId": 1
      }
      """
    When I send a PUT request to /books/1
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books/1"
    And response string value at "$.details.errors[0].field" is "price"
    And response string value at "$.details.errors[0].message" is "Book price must be greater than 0"
    And response value at "$.timestamp" is number

  Scenario: update book with null author id throws bad request
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": "The Lord of the Rings",
        "price": 19.99,
        "authorId": null
      }
      """
    When I send a PUT request to /books/1
    Then response status is BAD_REQUEST
    And response string value at "$.status" is "BAD_REQUEST"
    And response string value at "$.message" is "Method argument not valid"
    And response string value at "$.url" is "/books/1"
    And response string value at "$.details.errors[0].field" is "authorId"
    And response string value at "$.details.errors[0].message" is "Book authorId is mandatory"
    And response value at "$.timestamp" is number

  Scenario: update book with unknown author id throws not found
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": "The Lord of the Rings",
        "price": 19.99,
        "authorId": 999
      }
      """
    When I send a PUT request to /books/1
    Then response status is NOT_FOUND
    And response string value at "$.status" is "NOT_FOUND"
    And response string value at "$.message" is "Author not found with given id"
    And response string value at "$.url" is "/books/1"
    And response number value at "$.details.id" is 999
    And response value at "$.timestamp" is number

  Scenario: update book
    Given there are 1 books in the database
    And request body is:
      """
      {
        "title": "Les chagasses font des fougasses",
        "price": 19.99,
        "authorId": 1
      }
      """
    When I send a PUT request to /books/1
    Then response status is OK
    And response number value at "$.id" is 1
    And response string value at "$.title" is "Les chagasses font des fougasses"
    And response number value at "$.price" is 19.99
    And response number value at "$.author.id" is 1
    And response string value at "$.author.name" matches "^Author \d+$"
