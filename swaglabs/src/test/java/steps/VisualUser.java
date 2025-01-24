package steps;

import static org.junit.Assert.assertFalse;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.WBManager;
import org.openqa.selenium.io.FileHandler;

public class VisualUser {

    WebDriver driver = WBManager.getDriver();

    @Given ("I am logged in with standard_user")
    public void i_am_logged_in_with_standard_user (){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @And ("I take and save screenshot of {string}")
    public void i_take_and_save_screenshot_of_home_page (String nameOfScreenshot) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File("src/test/screenshots/" + nameOfScreenshot + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And ("I log in with visual user")
    public void i_log_in_with_visual_user () throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).sendKeys("visual_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();
    }

    @Then ("I should see that picture {string} and picture {string} are same")
    public void i_should_have_two_same_pictures_of_homepage (String nameOfFirstPicture, String nameOfSecondPicture) throws IOException {
        BufferedImage expectedImage = ImageIO.read(new File("src/test/screenshots/" + nameOfFirstPicture + ".png"));
        BufferedImage actualImage = ImageIO.read(new File("src/test/screenshots/" + nameOfSecondPicture + ".png"));
        int width1 = expectedImage.getWidth();
        int height1 = expectedImage.getHeight();
        int width2 = actualImage.getWidth();
        int height2 = actualImage.getHeight();
        assertFalse(width1 != width2 || height1 != height2);
    
        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                Color expectedColor = new Color(expectedImage.getRGB(x, y));
                Color actualColor = new Color(actualImage.getRGB(x, y));
                assertFalse(!expectedColor.equals(actualColor));
            }
        }
    }
}
