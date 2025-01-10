Feature: Performance test

Scenario: Logging in using JMeter
    Given I simulate multiple performance glitch users logging in to the application
    Then I should see saved results in file

Scenario: Testing going back during overviewing product
   Given I am logged in with performance glitch user credentials
   When I click on the name of first shown product in product list
   Then I should be redirected to the invetory item page
   When I click back on products button
   Then I should be redirected to the product page in less than a second

Scenario: Continuing shopping while reviewing the cart
    Given I am logged in with performance glitch user credentials
    When I click on Add to cart button for the following products:
     | product_name |
     | Sauce Labs Backpack |
    Then I should see badge on shopping cart with "1" product
    And I click on Shopping cart button
    Then I should be redirected to the cart page
    When I click on continue shopping button 
    Then I should be redirected to the homepage in less than a second