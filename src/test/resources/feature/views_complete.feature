@ui_test
Feature: all views should be working (no errors)

  Background:
    Given login as admin
    Then choose the first team

    Scenario: team dashboard should work
      Then There should be more than 1 of [.card]
      Then There should be 6 of [.smallstat]
      Then All [.smallstat .value] should not be empty or undefined

    Scenario: pipeline dashboard should work
      When click sidebar with title 流水线
      Then There should be more than 0 of [.pipeline-group__item]
      Then All [.pipeline-group__item_state] should not be empty or undefined
      Then All [.pipeline-group__item_title] should not be empty or undefined
      Then There should be more than 0 of [.pipeline-group__item_compare]
      When click the first pipeline
      Then There should be more than 1 of [.sub-menus>li>a]
      Then Click every [.sub-menus>li>a] should nav to proper view
    @dev
    Scenario: pipeline monitor view should work
      When click sidebar with title 构建监控
      Then There should be more than 0 of [.pipeline-overview-item]

    Scenario:
