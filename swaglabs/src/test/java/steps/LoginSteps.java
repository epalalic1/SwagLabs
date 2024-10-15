package steps;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WBManager;


public class LoginSteps {

    WebDriver driver = WBManager.getDriver();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://www.saucedemo.com/");
    }
    
    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @When("I enter username {string}")
    public void  i_enter_username(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should see error message {string}")
    public void i_should_see_error_message(String errorMessage) {
        String actualMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertEquals(errorMessage, actualMessage);
    }

    @After
    public void tearDown() {
        WBManager.closeDriver();
    }
}
