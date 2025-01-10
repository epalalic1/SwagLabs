package steps;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WBManager;

public class PerformanceGlitchUser {
    
    WebDriver driver = WBManager.getDriver();
    long startTim;
    long startTimContinueShopping;

    @Given ("I am logged in with performance glitch user credentials")
    public void i_am_logged_in_with_performance_glitch_user_credentials() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
        Thread.sleep(5000);
    }

    @Given ("I simulate multiple performance glitch users logging in to the application")
    public void simulate_multiple_performance_glitch_users_logging_in_to_the_application() {
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
            JMeterUtils.setJMeterHome("C:/JMeter");
            JMeterUtils.loadJMeterProperties("C:/JMeter/bin/jmeter.properties");
            System.setProperty("jmeter.logfile", "src/test/logs/jmeter.log");
            JMeterUtils.initLocale();
            HashTree testPlanTree = null;
            try {
                testPlanTree = SaveService.loadTree(new File("src/test/jmeter/login.jmx"));
            } catch (Exception e) {
                e.printStackTrace();
            }
    
             Summariser summer = new Summariser("summary");
            String logFile = "src/test/logs/result.jtl";
            ResultCollector logger = new ResultCollector(summer);
            logger.setFilename(logFile);
            testPlanTree.add(testPlanTree.getArray()[0], logger);

            jmeter.configure(testPlanTree);
            jmeter.run();
    }
    @When ("I click back on products button")
    public void i_click_back_on_products() throws InterruptedException {
        driver.findElement(By.id("back-to-products")).click();
        startTim = System.currentTimeMillis();
        Thread.sleep(3000);
    }

    @When ("I click on continue shopping button")
    public void i_click_on_continue_shopping_button () throws InterruptedException {
        driver.findElement(By.id("continue-shopping")).click();
        startTimContinueShopping = System.currentTimeMillis();
        Thread.sleep(3000);
    }

    @Then ("I should be redirected to the homepage in less than a second")
    public void i_should_be_redirected_to_the_homepage_in_less_than_a_second () {
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTimContinueShopping; 
        assertTrue(loadTime < 1000);
    }

    @Then ("I should be redirected to the product page in less than a second")
    public void i_should_be_redirected_to_the_product_page_in_less_than_a_second() {
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTim; 
        assertTrue(loadTime < 1000);
    }

    @Then ("I should see saved results in file")
    public void i_should_see_saved_results_in_file () {
        Path path = Paths.get("src/test/logs/result.jtl");
        assertTrue(Files.exists(path));
        assertTrue(Files.isRegularFile(path));
    }
}
