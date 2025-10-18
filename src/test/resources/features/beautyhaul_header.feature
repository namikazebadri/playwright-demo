Feature: Check BHI Header

  Scenario: Check Logo
    Given I am on Beautyhaul Homepage
    Then I should see logo

  Scenario: Check Logo (false)
    Given I am on Beautyhaul Homepage
    Then I should see logo false
