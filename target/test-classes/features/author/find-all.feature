Feature: AuthorController - find all

  Scenario: should return empty list when database is empty
    Given there are no authors in the database
    And request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    When I send a GET request to /books
    Then response status is OK
    And response header "Content-Type" is "application/json"
    And response array length at "$" is 0

  Scenario: should return all authors
    Given there are 15 authors in the database
    And request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    When I send a GET request to /authors
    Then response status is OK
    And response header "Content-Type" is "application/json"
    And response array length at "$" is 15
    And all response values at "$[*].id" are numbers
    And all response string values at "$[*].name" match "^Author \d+$"
