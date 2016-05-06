@ui_test
Feature: 用户团队管理

  Background:
    Given 我以admin登陆

  Scenario: 用户团队选择
    Given 如果我在某团队里面
    Then 我点击某团队时
    Then 我会进到该团队的页面

  Scenario: 新建团队
    When 我点击了 新的团队 按钮
    Then 我能看到新建团队的页面


