Feature: Login with error user

Background:
    Given I am logged in with error user


  Scenario Outline: Product displayed in specific order by specific parameter - error
    When I click on sorting option "<sorting_option>"
    Then I sholud see products sorted in "<order>" order by "<sorting_parameter>"

    Examples:
      | sorting_option      | order        | sorting_parameter |
      | Name (Z to A)       | d            | name              |
      | Price (high to low) | d            | price             |
      | Price (low to high) | a            | price             |

  Scenario: Adding product to cart - error
    When I click on Add to cart button for the following products:
     | product_name |
     | Sauce Labs Backpack     |
     | Sauce Labs Bike Light   |
     | Sauce Labs Bolt T-Shirt |
    Then I should see badge on shopping cart with "3" product
    And I click on Shopping cart button
    Then I should be redirected to the cart page 
    And I should see shoping cart with the following products:
     | product_name |
     | Sauce Labs Backpack     |
     | Sauce Labs Bike Light   |
     | Sauce Labs Bolt T-Shirt |

  Scenario: Removing product from cart on homepage - error
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

 Scenario: Adding product to shopping cart while reviewing it - error
   When I click on name for the following product:
        | product_name |
        | Sauce Labs Bolt T-Shirt |
   Then I should be redirected to the invetory item page
   When I click on add to cart button of product page
   And I click on Shopping cart button
   Then I should be redirected to the cart page 
   And I should have 1 product added to the shopping cart

Scenario: Finishing order -error
    When I click on Add to cart button for the following products:
        | product_name |
        | Sauce Labs Backpack     |
    And I click on Shopping cart button
    When I click on Checkout button
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Continue button
    Then I should redirected to the third step of checkout
    And I click on Finish button
    Then I should be redirected to the last step of checkout
    When I click on Back Home
    Then I should be redirected to the homepage

Scenario Outline: Unsuccessful transition to the second step of checkout - error
    When I click on Add to cart button for the following products:
        | product_name |
        | Sauce Labs Backpack     |
    And I click on Shopping cart button
    When I click on Checkout button
    And I enter "<firstName>", "<lastName>" and "<postalCode>"
    And I click on Continue button
    Then I should see error message "<message>"
    But I should not be redirected to the second step of checkout

     Examples:
      | firstName | lastName | postalCode | message                      |
      | Mike      |          |   11111    | Error: Last Name is required |

Scenario: Removing product from cart on product review page - error
  When I click on Add to cart button for the following products:
     | product_name |
     | Sauce Labs Backpack |
     | Sauce Labs Bike Light |
  Then I should see badge on shopping cart with "2" product
  When I click on name of first product in shopping list cart
  When I click on remove button of product page
  Then I should see badge on shopping cart with "1" product
  And I click on Shopping cart button
  Then I should be redirected to the cart page 
  And I should see shoping cart with the following products:
     | product_name |
     | Test.allTheThings() T-Shirt (Red) |