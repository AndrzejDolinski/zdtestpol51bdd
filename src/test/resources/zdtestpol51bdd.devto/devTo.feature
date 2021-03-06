Feature: devTo basic features
Scenario: Open first seeing blog
  Given Brawser is open
  When I go to devto mainpage
  And I click on first blog displayed
  Then I should be redirected to blog page