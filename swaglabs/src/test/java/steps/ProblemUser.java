package steps;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @When("I add first two product and click of shopping cart icon")
    public void i_add_first_two_product_and_click_of_shopping_cart_icon() {
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        for (int i = 0; i < 2; i ++) {
            items.get(i).findElement(By.cssSelector("button.btn_primary")).click();
        }
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    
    @After
    public void tearDown() {
        WBManager.closeDriver();
    }
}
