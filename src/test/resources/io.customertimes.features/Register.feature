Feature: Registration

  Scenario Outline: Register in Juice Shop
    Given User goes to registration page
    When User enters email <email>, password <password>, repeat password <repeatPassword>, chooses security question and enter answer <answer>
    And User clicks on Register button
    Then User should be registered in application with success message <successMessage>
    Examples:
      | email               | password | repeatPassword | answer | successMessage                                             |
      | "eva00011@gmail.com"| "123456" | "123456"       | "Cat"  | "Registration completed successfully. You can now log in." |





