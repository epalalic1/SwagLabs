Feature: Checkout

Background: 
    Given I am logged in with standard_user and added two product to shopping cart

Scenario: Loading first step of creating check
    When I click on Checkout button
    Then I should be redirected to the first step of checkout

Scenario Outline: Successful transition to the second step of checkout
    When I click on Checkout button
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Continue button
    Then I should redirected to the third step of checkout

    Examples:
      | firstName | lastName | postalCode |
      | Mike      | Johnson  | 11111      |

Scenario Outline: Unsuccessful transition to the second step of checkout
    When I click on Checkout button
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Continue button
    Then I should see error message "<message>"
    But I should not be redirected to the second step of checkout

    Examples:
      | firstName | lastName | postalCode | message |
      |           |          |            | Error: First Name is required  |
      | Mike      |          |            | Error: Last Name is required   |
      |           | Johnson  |            | Error: First Name is required  |
      |           |          |   11111    | Error: First Name is required  |
      | Mike      | Johnson  |            | Error: Postal Code is required |
      |           | Johnson  |   11111    | Error: First Name is required  |
      | Mike      |          |   11111    | Error: Last Name is required   |

Scenario: Returning to the shopping page
    When I click on Checkout button
    Then I should be redirected to the first step of checkout
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Cancel button
    Then I should be redirected to the shopping cart page

    Examples:
      | firstName | lastName | postalCode |
      | Mike      | Johnson  | 11111      |

Scenario: Checking whether all data is displayed correctly in the overview
    When I click on Checkout button
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Continue button
    Then I should redirected to the third step of checkout
    And I should see two added product in checkout list

     Examples:
      | firstName | lastName | postalCode |
      | Mike      | Johnson  | 11111      |

Scenario: Verify total amount including tax
    When I click on Checkout button
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Continue button
    Then I should redirected to the third step of checkout
    And I should see calculated amount together with and without tax

     Examples:
      | firstName | lastName | postalCode |
      | Mike      | Johnson  | 11111      |

