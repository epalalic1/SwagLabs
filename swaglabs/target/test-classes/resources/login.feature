Feature: Login Functionality

  Scenario: Successful login
    Given I am on the login page
    When I enter valid credentials
    Then I should be redirected to the homepage

  Scenario: Unsuccessful login - empty username and password
    Given I am on the login page
    When I click on login button
    Then I should see error message "Epic sadface: Username is required"

  Scenario: Unsuccessful login - empty password
    Given I am on the login page
    When I enter username "standard_user"
    When I click on login button
    Then I should see error message "Epic sadface: Password is required"

  Scenario: Unsuccessful login - empty username
    Given I am on the login page
    When I enter password "secret_sauce"
    When I click on login button
    Then I should see error message "Epic sadface: Username is required"

  Scenario: Unsuccesful login - invalid credentials
    Given I am on the login page
    When I enter invalid credentials
    When I click on login button
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Unsuccesful login - invalid username
    Given I am on the login page
    When I enter username "invalid_username"
    When I enter password "secret_sauce"
    When I click on login button
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"
  
  Scenario: Unsuccesful login - invalid password
    Given I am on the login page
    When I enter username "standard_user"
    When I enter password "invalid_password"
    When I click on login button
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"

  