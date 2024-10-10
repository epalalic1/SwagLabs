package steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductsSteps {
    
    WebDriver driver;


    @Given("I am login with standard_user")
    public void i_am_login_with_standard_user() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @When ("I click on Add to cart button for the following products:")
    public void i_click_on_Add_to_cart_button_for_the_following_products (DataTable dataTable) {
        List<String> products = dataTable.asList(String.class);
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        for (WebElement item : items) {
            for (String nameOfProduct : products) {
                if (item.findElement(By.className("inventory_item_label")).findElement(By.tagName("a")).getText().equals(nameOfProduct)) {
                    item.findElement(By.cssSelector("button.btn_primary")).click();
                }
            }
        }
    }

    @When("I click on sorting option {string}")
    public void i_click_on_sorting_option(String sorting) {
        WebElement webElement = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select sel = new Select(webElement);
        sel.selectByVisibleText(sorting);        
    }
    @And ("I click on Shopping cart button")
    public void i_click_on_Shopping_cart_button() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @And ("I should see shoping cart with the following products:")
    public void i_should_see_shoping_cart_with_the_following_products (DataTable dataTable) {
        List<String> products = dataTable.asList(String.class);
        Boolean areSame = true;
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='cart_item']"));
        for (WebElement item : lista ) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (!products.contains(name)) {
                areSame = false;
                break;
            }
        }
        assertTrue(areSame);
    }

    @And ("I click on remove button of the following products")
    public void i_click_on_remove_button_of_the_following_products (DataTable dataTable){
        List<String> products = dataTable.asList(String.class);
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        for (WebElement item : items) {
            for (String nameOfProducts : products) {
                if (item.findElement(By.className("inventory_item_label")).findElement(By.tagName("a")).getText().equals(nameOfProducts)) {
                    item.findElement(By.cssSelector("button.btn_secondary")).click();
                }
            }
        }
    }

    @Then ("I should see badge on shopping cart with {string} product")
    public void i_should_see_badge_on_shopping_cart (String number) {
        Boolean isBadgeVisible = driver.findElement(By.className("shopping_cart_badge")).isDisplayed();
        String badgeText = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertTrue(isBadgeVisible);
        assertEquals(number,badgeText);
    }
    
    @Then ("I should be redirected to the cart page")
    public void i_should_be_redirected_to_the_cart_page() {
        assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
    }  

    @Then("I should see all product displayed")
    public void i_should_see_all_product_displayed() {
        Boolean res = driver.findElement(By.className("inventory_list")).isDisplayed();
        assertTrue(res);
    }

    @Then("I sholud see products sorted in {string} order by {string}")
    public void i_sholud_see_products_sorted_in_order_by (String order, String parameter) {
        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        List<String> listOfNames = new ArrayList<>();
        List<Double> listOfPrices = new ArrayList<>();
        if (parameter.equals("price")) { 
            for (WebElement item : lista) {
                String priceAll  = item.findElement(By.className("pricebar")).findElement(By.className("inventory_item_price")).getText();
                String priceText = priceAll.replace("$", "").trim();
                Double price  = Double.parseDouble(priceText);
                listOfPrices.add(price);
            }
            List<Double> sortedPrices = new ArrayList<>(listOfPrices);
            if (order.equals("d")) {
                Collections.sort(sortedPrices, Collections.reverseOrder());
                assertEquals(listOfPrices, sortedPrices);;
            }
            else if (order.equals("a")) {
                Collections.sort(sortedPrices);
                assertEquals(listOfPrices, sortedPrices);
            }
        }
        else if (parameter.equals("name")) {
            for (WebElement item : lista) {
                listOfNames.add(item.findElement(By.className("inventory_item_label")).findElement(By.tagName("a")).getText());
            }
            List<String> sortedNames = new ArrayList<>(listOfNames);
            Collections.sort(sortedNames, Collections.reverseOrder());
            assertEquals(sortedNames, listOfNames);
        }
    }

    @After
    public void close(){
        driver.close();
    }

}
