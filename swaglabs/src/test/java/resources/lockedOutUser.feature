Feature: Problems

Scenario Outline: Unsuccesful login with locked out user credentials
    Given I am on the login page
    When I enter username "<username>"
    When I enter password "<password>"
    When I click on login button
    Then I should see error message "<message>"

    Examples:
      | username        | password     | message                                             |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out. |