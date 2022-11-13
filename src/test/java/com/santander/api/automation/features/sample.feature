#Feature: To Test JSONPlaceholder with
#
#  @dataservice
#  Scenario: To test data service version
#    Given an API endPoint for dataservice
#    When make GET call for version
#    Then response code match 200
#    And returned version match 1.1.0
#
#  @googletest
#  Scenario: To test GET
#    Given an API endPoint for users
#    And I want to make GET call
#    And I expect a JSON Response
#
#  @gendertest
#  Scenario Outline: To predict gender with name
#    Given an API endPoint for gender prediction
#    When when api service called with <name>
#    Then return response is <responsecode>
#    Then return gender is <gender>
#
#    Examples:
#      | name    | responsecode | gender |
#      | john    | 200          | male   |
#      | rebecca | 200          | female |
