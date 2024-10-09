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
