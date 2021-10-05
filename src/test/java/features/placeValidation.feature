Feature: Validating place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being added using AddPlace API
    Given Add place payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "post" http request
    Then the API call got success with status code 200
    And the "status" in response body is "OK"
    And the "scope" in response body is "APP"
    And capture the place_id
    And verify place_id created maps to "<name>" using "getPlaceApi"

    Examples:
      | name            | language | address                 |
      | AmrinAbba House | English  | 258 Vasser DrPiscataway |
     # | Sarah Place  | Spanish0 | 257 vasser Dr, Piscataway |

  @DeletePlace @Regression
  Scenario Outline: Verify if place is being deleted using AddPlace API
    Given delete place payload
    When user calls <apiResource> with <callingMethod> http request
    Then the API call got success with status code 200
    And the "status" in response body is "OK"
    Examples:
      | apiResource      | callingMethod |
      | "deletePlaceApi" | "delete"      |

  #Scenario Outline: Verify if place is being returned  using getPlace API
   # Given place id for getPlace API <idPlace>
    #When user calls <apiResource> with <callingMethod> http request
    #Then the API call got success with status code 200
    #And return place details in a file
    #Examples:
     # | idPlace                            | apiResource   | callingMethod |
      #| "1c3fd9dcfb5a5afdec9dfd5353dd8f32" | "getPlaceApi" | "GET"         |