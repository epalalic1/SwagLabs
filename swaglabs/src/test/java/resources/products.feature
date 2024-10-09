Feature: Product display

 Background:
  Given I am login with standard_user

 Scenario: Products displayed in default ascending order by name
  Then I should see all product displayed

 Scenario Outline: Product displayed in specific order by specific parameter
  When I click on sorting option "<sorting_option>"
  Then I sholud see products sorted in "<order>" order by "<sorting_parameter>"

  Examples:
      | sorting_option      | order        | sorting_parameter |
      | Name (Z to A)       | d            | name              |
      | Price (high to low) | d            | price             |
      | Price (low to high) | a            | price             |

 Scenario: Adding product to cart
    When I click on Add to cart button of product with name "Sauce Labs Backpack"
    Then I should see badge on shopping cart with "1" product
    And I click on Shopping cart button
    Then I should be redirected to the cart page 
    And I should see one product added to the shopping card named "Sauce Labs Backpack"

 Scenario: Removing product from cart on homepage
  When I click on Add to cart button of product with name "Sauce Labs Backpack"
  Then I should see badge on shopping cart with "1" product
  And I click on remove button of product with name "Sauce Labs Backpack"
  And I click on Shopping cart button
  Then I should be redirected to the cart page 
  And I should see shopping cart with "0" product