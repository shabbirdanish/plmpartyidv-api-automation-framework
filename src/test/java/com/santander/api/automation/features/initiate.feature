Feature: Start ID&V process for a new or an existing customer creating a unique partyIdvId.
Background:
Given A JWT token is generated if it is not present

  @NewApplicant @HappyPath @PostiveScenario
  Scenario: Creating the session of an applicant that is not an existing customer
    When Call INITIATE endpoint with valid payload
    Then verify http response with status code <200>
    And verify idvSessionId is returned in response
#
#  @PostiveScenario @NewApplicant
#  Scenario: Creating the session of an applicant where optional fields are blank initiate api payload
#    When Call INITIATE endpoint with valid payload having optional fields blank
#    Then verify http response with status code <200>
#    And verify idvSessionId is returned in response and is not blank
#
#
#  @PostiveScenario @NewApplicant
#  Scenario: Call initiate endpoint two times with same payload and get same idvSessionId
#    When Call INITIATE endpoint with valid payload
#    And Call INITIATE endpoint Again with same payload
#    Then verify idvSessionId returned is the same that was returned in first request
#
#
#  @PostiveScenario @NewApplicant
#  Scenario: Call initiate endpoint two times with different field in payload and get different idvSessionId
#    Given Call INITIATE endpoint with valid payload
#    When Again Call INITIATE endpoint with different paylod
#    Then verify http response with status code <200>
#    And verify that idvSessionId returned is different from that created in first request
#
#
#  @missingMandatoryField @NegativeScenario  @NewApplicant
#  Scenario: Call initiate endpoint with payload having Mandatory values blank
#    Given Call INITIATE endpoint with payload having Mandatory values blank
#    Then verify http  status code <400> and error message.
#
##  @Positive
##  Scenario: Verify that Valid Product Key and Product name should be passed in payload
##   When Call INITIATE endpoint with valid payload
##   Then verify valid product key and Product name passed in paylod
##    | PCA      | SANUKMOBILE |
##    | MORTGAGE | AGENT       |
##
## @ExistingCustomer @HappyPath @PostiveScenario
##  Scenario:Partytype is Existing Customer and Payload data Matches
##  Given Partytype is Exsiting Customer
##  When PartyiD exist and Payload data Matches
##  Then Existing idvSessionId for given Customer should be returned from System
##
## @ExistingCustomer @UnhappyPath @PostiveScenario
## Scenario:Partytype is Existing Customer and Payload data doesn't Match
##  Given Partytype is Exsiting Customer
##  When PartyiD exist and Payload doesn't Match
##  Then New idvSessionId for given Customer shouldb returned from System
##
## @ExistingCustomer @UnhappyPath @NegativeScenario
## Scenario:Partytype is Customer and Customer data doesn't exist.
##  Given Partytype is Customer
##  When Customer data doesn't exist
##  Then Error Message should be displayed

