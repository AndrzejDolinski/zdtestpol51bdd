Feature: basic calculator functions
  Scenario: adding two numbers
    Given I have a calculator
    When I add 2 and 3
    Then I should get 5
  Scenario: subtraction two numbers
    Given I have a calculator
    When  I subtract 2 from 3
    Then I should get 1
  Scenario: dividing two numbers
    Given I have a calculator
    When I divide 4 by 2
    Then I should get 2
  Scenario: multiplication two numbers
    Given I have a calculator
    When  I multiply 2 times 3
    Then I should get 6