Feature: Login Functionality

  Scenario: Successful login
    Given I am on the login page
    When I enter valid credentials
    Then I should be redirected to the homepage

  Scenario Outline: Unsuccesful login with different credentials
    Given I am on the login page
    When I enter username "<username>"
    When I enter password "<password>"
    When I click on login button
    Then I should see error message "<message>"

    Examples:
      | username       | password     | message                                                                   |
      | invalid_user   | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standard_user  | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
      |                | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user  |              | Epic sadface: Password is required                                        |
      |                |              | Epic sadface: Username is required                                        |
      | invalid_user   | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
