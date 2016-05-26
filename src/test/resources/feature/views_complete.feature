@ui_test
Feature: all views should be working (no errors)

  Background:
    Given login as admin
    Then choose team Test

    Scenario: team dashboard should work
      Then There should be more than 1 of [.card]
      Then There should be 6 of [.smallstat]
      Then All [.smallstat .value] should not be empty

    Scenario: pipeline dashboard should work
      When click sidebar with title 流水线
      Then There should be more than 1 of [.pipeline-group__item]
      Then All [.pipeline-group__item_state] should not be empty
      When click pipeline test-pipeline-1
      Then There should be more than 1 of [.nav__tabs li]
      Then Click every navTabs should nav to proper view

    Scenario: pipeline template view should work
      When click sidebar with title 流水线模版
      Then There should be more than 1 of [.list-item]
      Then There should be 1 of [.pipeline-template-preview__container]

    Scenario: pipeline monitor view should work
      When click sidebar with title 构建监控
      Then There should be 10 of [.pipeline-overview-item]
