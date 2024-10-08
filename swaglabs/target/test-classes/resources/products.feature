Feature: Product display

 Background:
  Given I am login with standard_user

 Scenario: Products displayed in default ascending order by name
  Then I should see all product displayed

 Scenario: Product sorted in descending order by name
  When I click on sorting option "Name (Z to A)"
  Then I sholud see products sorted in descending order by name