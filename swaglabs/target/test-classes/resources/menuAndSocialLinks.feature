Feature: Menu and social links

Background:
  Given I am login with standard_user

 Scenario: Seeing all products
    When I click on main menu button
    Then I should see menu item labeled with "AllItems"
    When I click on menu item labeled with "AllItems"
    Then I sholud see products sorted in "<order>" order by "<sorting_parameter>"

    Examples:
      | order        | sorting_parameter |
      | a            | name              |

 Scenario: Seeing about page
    When I click on main menu button
    Then I should see menu item labeled with "About"
    When I click on menu item labeled with "About"
    Then I should should be redirected to about page

 Scenario: Logging out
    When I click on main menu button
    Then I should see menu item labeled with "Logout"
    When I click on menu item labeled with "Logout"
    Then I should be redirected to the login page

 Scenario Outline: Reseting app state after adding product to cart
    When I click on Add to cart button for the following products:
     | product_name |
     | Sauce Labs Backpack |
    Then I should see badge on shopping cart with "1" product
    When I click on main menu button
    Then I should see menu item labeled with "Reset App State"
    When I click on menu item labeled with "Reset App State"
    Then I should not see badge on shopping cart