@SecondSet
Feature: Shopping cart 

Background:
    Given I am logged in with standard_user and added two product to shopping cart

Scenario: Removing product from shoping cart
    When I click on remove button of first product in shopping cart list 
    Then I should see one product left in shopping cart list

Scenario: Removing product while rewiewing it from
 When I click on name of first product in shopping list cart
 Then I should be redirected to the invetory item page
 When I click on remove button of product page
 Then I should see badge on shopping cart with "1" product
 And I click on Shopping cart button
 Then I should be redirected to the cart page 
 And I should have 1 product added to the shopping cart

Scenario: Checking if data remains after logging out and logging back in
    When I click on menu button
    And I click on option "Logout"
    Then I should be redirected to the login page
    When I enter username "standard_user"
    When I enter password "secret_sauce"
    When I click on login button
    Then I should be redirected to the homepage
    Then I should see badge on shopping cart with "2" product
    And I click on Shopping cart button
    Then I should be redirected to the cart page 
    And I should have 2 product added to the shopping cart
    

Scenario: Return to shopping page
    When I click on Continue Shopping button
    Then I should be redirected to the homepage
    Then I should see badge on shopping cart with "2" product