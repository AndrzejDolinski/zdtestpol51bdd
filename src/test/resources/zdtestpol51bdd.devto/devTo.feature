Feature: devTo basic features
Scenario: Open first seeing blog
  Given I go to devto mainpage
  When I click on first blog displayed
  Then I should be redirected to blog page