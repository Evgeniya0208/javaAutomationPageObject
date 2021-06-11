Feature: Registration

  Scenario Outline: Register in Juice Shop
    Given User goes to registration page
    When User enters email, password, repeat password, chooses security question and enter answer
    And User clicks on Register button
    Then User should be registered in application with success message <successMessage>
    Examples:
    | successMessage                                             |
    | "Registration completed successfully. You can now log in." |





