package steps;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WBManager;
import org.openqa.selenium.WebElement;

public class Checkout {

    WebDriver driver = WBManager.getDriver();

    @When ("I click on Checkout button")
    public void i_click_on_Checkout_button() {
        driver.findElement(By.id("checkout")).click();
    }

    @And("I enter {string}, {string} and {string}")
    public void i_enter_firstName_lastName_and_postalCode(String firstName, String lastName, String postalCode) throws InterruptedException {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }

    @And ("I click on Continue button")
    public void i_click_on_Continue_button () throws InterruptedException {
        driver.findElement(By.id("continue")).click();;
    }

    @Then ("I should be redirected to the first step of checkout")
    public void i_should_be_redirected_to_the_first_step_of_checkout () {
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
    }

    @Then ("I should redirected to the third step of checkout")
    public void i_should_redirected_to_the_third_step_of_checkout() {
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
    }

    @But ("I should not be redirected to the second step of checkout")
    public void i_should_not_be_redirected_to_the_second_step_of_checkout() {
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
    }

    @Then ("I should be redirected to the shopping cart page")
    public void i_should_be_redirected_to_the_shopping_cart_page() {
        assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
    }

    @And ("I click on Cancel button")
    public void  i_click_on_Cancel_button() {
        driver.findElement(By.id("cancel")).click();
    }

    @And ("I should see two added product in checkout list")
    public void i_should_see_two_added_product_in_checkout_list () {
        assertEquals(driver.findElements(By.className("cart_item")).size(), 2);
    }

    @And ("I should see calculated amount together with and without tax")
    public void i_should_see_calculated_amount_together_with_and_without_tax () {
        List<WebElement> lista = driver.findElements(By.className("cart_item"));
        Double amount = 0.00;
        for (WebElement item : lista) {
            String priceText = item.findElement(By.className("inventory_item_price")).getText();
            String amountText = priceText.replace("$", "").trim();
            Double price  = Double.parseDouble(amountText);
            amount += price;
        }
        assertTrue(driver.findElement(By.className("summary_subtotal_label")).getText().contains(amount.toString()));
        Double taxAmont = amount * 0.08;
        String formattedValue = String.format("%.2f", taxAmont);
        assertTrue(driver.findElement(By.className("summary_tax_label")).getText().contains(formattedValue));
        Double amountWithTax = amount + Double.parseDouble(formattedValue);
        assertTrue(driver.findElement(By.className("summary_total_label")).getText().contains(amountWithTax.toString()));
    }

}
