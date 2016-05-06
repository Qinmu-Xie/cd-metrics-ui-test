@ui_test
Feature: test deliflow login

  Scenario: user login
    Given I enter deliflow url
    When I login as admin
    Then I should in the Team view
    And close the browser