package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WBManager;

public class ShoppingCart {
    
    WebDriver driver = WBManager.getDriver();

    @Given("I am logged in with standard_user and added two product to shopping cart")
    public void i_am_logged_in_with_standard_user_and_added_two_product_to_shopping_cart () {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        for (int i = 0; i < 2; i ++) {
            items.get(i).findElement(By.cssSelector("button.btn_primary")).click();
        }
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    
    @When ("I click on the name of first shown product in product list")
    public void i_click_on_name_of_first_product_in_product_list () {
        List<WebElement> lista = driver.findElements(By.className("inventory_item"));
        lista.get(0).findElement(By.tagName("a")).click();

    }

    @When ("I click on name of first product in shopping list cart")
    public void i_click_on_name_of_first_product_in_shopping_list_cart () {
        List<WebElement> lista = driver.findElements(By.className("cart_item"));
        lista.get(0).findElement(By.tagName("a")).click();
    }

    @When ("I click on remove button of first product in shopping cart list")
    public void i_click_on_remove_button_of_first_product_in_shopping_cart_list () {
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='cart_item']"));
        lista.get(0).findElement(By.cssSelector("button.btn_secondary")).click();
       
    } 

    @When ("I click on remove button of product page")
    public void i_click_on_remove_button_of_product_page () {
        driver.findElement(By.id("remove")).click();
    }

    @When ("I click on menu button")
    public void i_click_on_menu_button () throws InterruptedException {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
    }

    @When("I click on Continue Shopping button")
    public void i_click_on_continue_shopping_button() {
        driver.findElement(By.id("continue-shopping")).click();
    }

    @And ("I click on option {string}")
    public void  i_click_on_option(String option) {
        WebElement menuItem = driver.findElement(By.xpath("//a[contains(text(), '" + option + "')]"));
        if (menuItem.isDisplayed() && menuItem.isEnabled()) {
            menuItem.click();
         } else {
             System.out.println("Element is not visible.");
         }
    }

    @Then ("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page () throws InterruptedException {
        assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"));
        Thread.sleep(1000);
    } 

    @Then ("I should see one product left in shopping cart list")
    public void i_should_see_one_product_left_in_shopping_cart_list () {
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='cart_item']"));
        String badgeText = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals(1, lista.size());
        assertEquals("1",badgeText);
    }
    
    @After("@SecondSet")
    public void tearDown() {
        WBManager.closeDriver();
    }
}
