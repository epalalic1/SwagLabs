@SecondSet
Feature: Shopping cart 

Background:
    Given I am logged in with standard_user and added two product to shopping cart

Scenario: Removing product from shoping cart
    When I click on remove button of first product in shopping cart list 
    Then I should see one product left in shopping cart list
