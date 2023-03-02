Feature:  AuthorController - find by id

  Background:
    Given there are no authors in the database

  Scenario:  Find author with unknown id throws not found
    When I send a GET request to /authors/1
    Then response status is NOT_FOUND
    And response string value at "$.status" is "NOT_FOUND"
    And response string value at "$.message" is "Author not found with given id"
    And response string value at "$.url" is "/authors/1"
    And response number value at "$.details.id" is 1
    And response value at "$.timestamp" is number

  Scenario Outline: Find author with known id returns author
    And there are 5 authors in the database
    When I send a GET request to /authors/<id>
    Then response status is OK
    And response number value at "$.id" is <id>
    And response string value at "$.name" matches "^Author \d+$"

    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |
      | 4  |
      | 5  |
