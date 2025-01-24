Feature: Visual user

Background:
    Given I am logged in with standard_user
    And I take and save screenshot of "standard_home_page"
    When I click on the name of first shown product in product list
    And I take and save screenshot of "standard_product_review_page"
    When I click on add to cart button of product page
    And I click on Shopping cart button
    And I take and save screenshot of "standard_first_step_page"
    When I click on Checkout button
    And I take and save screenshot of "standard_second_step_page"
    And I enter "John", "John" and "111"
    And I click on Continue button
    And I take and save screenshot of "standard_third_step_page"
    And I click on Finish button
    And I take and save screenshot of "standard_final_step_page"
    When I click on main menu button
    Then I should see menu item labeled with "Logout"
    When I click on menu item labeled with "Logout"
    And I log in with visual user

Scenario: Visual testing of homepage
    And I take and save screenshot of "visual_home_page"
    Then I should see that picture "standard_home_page" and picture "visual_home_page" are same


Scenario: Visual testing of product review page
    When I click on the name of first shown product in product list
    And I take and save screenshot of "visual_product_review_page"
    Then I should see that picture "standard_home_page" and picture "visual_home_page" are same

Scenario: Visual testing of first step checking out
    When I click on the name of first shown product in product list
    When I click on add to cart button of product page
    And I click on Shopping cart button
    And I take and save screenshot of "visual_first_step_page"
    Then I should see that picture "standard_first_step_page" and picture "visual_first_step_page" are same

Scenario: Visual testing of second step of checking out
    When I click on the name of first shown product in product list
    When I click on add to cart button of product page
    And I click on Shopping cart button 
    When I click on Checkout button
    And I take and save screenshot of "visual_second_step_page"
    Then I should see that picture "standard_second_step_page" and picture "visual_second_step_page" are same


Scenario: Visual testing of third step page
    When I click on the name of first shown product in product list
    When I click on add to cart button of product page
    And I click on Shopping cart button 
    When I click on Checkout button
    And I enter "John", "John" and "111"
    And I click on Continue button
    And I take and save screenshot of "visual_third_step_page"
    Then I should see that picture "standard_third_step_page" and picture "visual_third_step_page" are same

Scenario: Visual testing of final step page
    When I click on the name of first shown product in product list
    When I click on add to cart button of product page
    And I click on Shopping cart button 
    When I click on Checkout button
    And I enter "John", "John" and "111"
    And I click on Continue button
    And I click on Finish button
    And I take and save screenshot of "visual_final_step_page"
    Then I should see that picture "standard_final_step_page" and picture "visual_final_step_page" are same
