Feature: Empty registration form validation

  Scenario: User lefts all fields empty

    Given User navigates to registration page
    When User lefts all fields empty
    Then Error message should be displayed on every field