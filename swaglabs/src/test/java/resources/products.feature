@FirstSet
Feature: Product display

 Background:
  Given I am login with standard_user

 Scenario Outline: Products displayed in default ascending order by name
  Then I sholud see products sorted in "<order>" order by "<sorting_parameter>"

  Examples:
      | order        | sorting_parameter |
      | a            | name              |

 Scenario Outline: Product displayed in specific order by specific parameter
  When I click on sorting option "<sorting_option>"
  Then I sholud see products sorted in "<order>" order by "<sorting_parameter>"

  Examples:
      | sorting_option      | order        | sorting_parameter |
      | Name (Z to A)       | d            | name              |
      | Price (high to low) | d            | price             |
      | Price (low to high) | a            | price             |

 Scenario: Adding product to cart
    When I click on Add to cart button for the following products:
     | product_name |
     | Sauce Labs Backpack |
    Then I should see badge on shopping cart with "1" product
    And I click on Shopping cart button
    Then I should be redirected to the cart page 
    And I should see shoping cart with the following products:
     | product_name |
     | Sauce Labs Backpack |
  
 Scenario: Removing product from cart on homepage
  When I click on Add to cart button for the following products:
     | product_name |
     | Sauce Labs Backpack |
     | Test.allTheThings() T-Shirt (Red) |
  Then I should see badge on shopping cart with "2" product
  And I click on remove button of the following products
     | product_name |
     | Sauce Labs Backpack |
  And I click on Shopping cart button
  Then I should be redirected to the cart page 
  And I should see shoping cart with the following products:
     | product_name |
     | Test.allTheThings() T-Shirt (Red) |

 Scenario: Adding product to shopping cart while reviewing it
   When I click on the name of first shown product in product list
   Then I should be redirected to the invetory item page
   When I click on add to cart button of product page
   Then I should see badge on shopping cart with "1" product
   And I click on Shopping cart button
   Then I should be redirected to the cart page 
   And I should have 1 product added to the shopping cart

Scenario: Check that products have valid images
   Then I should see for every product valid image displayed