@SecondSet
Feature: Problem user

Background: 
  Given I am logged in with problem user credentials


 Scenario Outline: Products displayed in default ascending order by name
  Then I sholud see products sorted in "<order>" order by "<sorting_parameter>"

  Examples:
      | order        | sorting_parameter |
      | a            | name              |

  Scenario: Check that products have valid images
   Then I should see for every product valid image displayed




