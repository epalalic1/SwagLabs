package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
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

    @When ("I click on {string} button") 
    public void  i_click_on_socialMediaButton (String socialMedia) throws InterruptedException {
        Thread.sleep(2000);
        if(socialMedia.equals("twitter")) {
          driver.findElement(By.xpath("//li[@class='social_twitter']/a")).click();
        }
        else if (socialMedia.equals("facebook")) {
            driver.findElement(By.xpath("//li[@class='social_facebook']/a")).click();
        }
        else if (socialMedia.equals("linkedin")) {
            driver.findElement(By.xpath("//li[@class='social_linkedin']/a")).click();
        }
        Thread.sleep(2000);
    }

    @Then ("I should be redirected to {string} page")
    public void i_should_be_redirected_to_socialMediaPage_page(String socialMediaPage) throws InterruptedException {
        Thread.sleep(2000);
        Set<String> windows = driver.getWindowHandles();
        for (String string : windows) {
            driver.switchTo().window(string);
            if (driver.getCurrentUrl().contains(socialMediaPage)) {
                break;
            }
        }
        if(socialMediaPage.equals("twitter")) {
            assertTrue(driver.getCurrentUrl().contains("https://x.com/saucelabs"));
        }
        else if (socialMediaPage.equals("facebook")) {
            assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/saucelabs"));
        }
        else if (socialMediaPage.equals("linkedin")) {
            assertTrue(driver.getCurrentUrl().contains("https://www.linkedin.com/"));
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

    @And ("I should see Add button for the following products") 
    public void i_should_see_add_button_for_the_following_products(DataTable dataTable) {
        List<String> products = dataTable.asList(String.class);
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        for (WebElement item : items) {
            for (String nameOfProduct : products) {
                if (item.findElement(By.className("inventory_item_label")).findElement(By.tagName("a")).getText().equals(nameOfProduct)) {
                    assertEquals("Add to cart",driver.findElement(By.className("btn_inventory")).getText());
                }
            }
        }
    }

}
