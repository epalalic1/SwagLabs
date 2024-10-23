package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import utils.WBManager;

public class ProblemUser {

    WebDriver driver = WBManager.getDriver();

    @Given ("I am logged in with problem user credentials")
    public void i_am_logged_in_with_problem_user_credentials () {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @After("@SecondSet")
    public void tearDown() {
        WBManager.closeDriver();
    }
}
