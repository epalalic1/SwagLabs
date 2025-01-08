package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WBManager;

public class menuAndSocialLinks {

    WebDriver driver = WBManager.getDriver();

    @When("I click on main menu button")
    public void i_click_on_menu_button() throws InterruptedException {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000);
    }

    @When("I click on menu item labeled with {string}")
    public void i_click_on_menu_item_labeled_with(String label) {
        if (label.equals("AllItems")) {
            driver.findElement(By.id("inventory_sidebar_link")).click();
        } else if (label.equals("About")) {
            driver.findElement(By.id("about_sidebar_link")).click();
        } else if (label.equals("Logout")) {
            driver.findElement(By.id("logout_sidebar_link")).click();
        } else if (label.equals("Reset App State")) {
            driver.findElement(By.id("reset_sidebar_link")).click();
        }
    }

    @Then("I should see menu item labeled with {string}")
    public void i_should_see_menu_item_labeled_with(String label) {
        if (label.equals("AllItems")) {
            assertEquals(true, driver.findElement(By.id("inventory_sidebar_link")).isDisplayed());
        } else if (label.equals("About")) {
            assertEquals(true, driver.findElement(By.id("about_sidebar_link")).isDisplayed());
        } else if (label.equals("Logout")) {
            assertEquals(true, driver.findElement(By.id("logout_sidebar_link")).isDisplayed());
        } else if (label.equals("Reset App State")) {
            assertEquals(true, driver.findElement(By.id("reset_sidebar_link")).isDisplayed());
        }
    }

    @Then("I should should be redirected to about page")
    public void i_should_should_be_redirected_to_about_page() {
        String expectedUrl = "https://saucelabs.com/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Then("I should not see badge on shopping cart")
    public void i_should_not_see_badge_on_shopping_cart() {
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));
        if (!elements.isEmpty()) {
            throw new AssertionError("Element with class 'shopping_cart_badge' is displayed.");
        } else {
        }
    }

}
