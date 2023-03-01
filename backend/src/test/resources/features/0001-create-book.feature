Feature: This is a feature

  Scenario: This is a scenario
    Given request headers are:
      | Content-Type | application/json |
      | Accept       | application/json |
    When I send a GET request to /books
    Then response status is OK
    And response header "Content-Type" is "application/json"
    And response body is:
    """
    [{"id":1, "title":"string","price":0.0,"author":{"id":1,"name":"toto"}},{"id":2,"title":"string","price":0.0,"author":{"id":2,"name":"string"}}]
    """
