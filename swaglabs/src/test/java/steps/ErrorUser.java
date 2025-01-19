package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.WBManager;

public class ErrorUser {
    WebDriver driver = WBManager.getDriver();

    @Given ("I am logged in with error user")
    public void i_am_logged_in_with_error_user () {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("error_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @When ("I click")
    public void  i_click() {
        System.out.println("Usli smo");
    }
}
