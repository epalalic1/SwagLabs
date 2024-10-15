package steps;


import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WBManager;

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
        driver.findElement(By.id("continue")).click();
        Thread.sleep(5000);
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

}
