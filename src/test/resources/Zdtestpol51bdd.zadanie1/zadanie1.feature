Feature: devTo zadanie 1
  Scenario: Enter sign up  page
    Given Brawser is start
    When enter mainpage
    When I click on Sign In button
    Then I should be redirected to login page
