@tag
Feature: Validate Post Get and Delete

  @Add @Regression
  Scenario Outline: verify is place is being added by API
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with with "post" http req
    Then the response status code should be 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify PlaceID created matched with "<name>" using "GetPlaceApi"
    Examples:
      |name       |language       |address      |
      |atharv     |Kannada        |Bangalore    |
#    |random     |Tamil          |Mysore    |

  @Delete @Regression
  Scenario: verify if Delete Call is working or not
    Given Delete Place Api With Paylod
    When user calls "DeletePlaceApi" with with "delete" http req
    Then the response status code should be 200
    And "status" in response body is "OK"