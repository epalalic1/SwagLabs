package steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ShoppingCart {
    
    WebDriver driver;

    @Given("I am logged in with standard_user and added two product to shopping cart")
    public void i_am_logged_in_with_standard_user_and_added_two_product_to_shopping_cart () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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

    @When ("I click on remove button of first product in shopping cart list")
    public void i_click_on_remove_button_of_first_product_in_shopping_cart_list () {
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='cart_item']"));
        lista.get(0).findElement(By.cssSelector("button.btn_secondary")).click();
    } 

    @Then ("I should see one product left in shopping cart list")
    public void i_should_see_one_product_left_in_shopping_cart_list () {
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='cart_item']"));
        assertEquals(1, lista.size());
    }

    @After("@SecondSet")
    public void close() {
        driver.close();
    }
}
