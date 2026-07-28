package tests;

import pages.SauceDemoLoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class POMSauceDemoTests {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
    }

    // Test Case 1: Verify error message when username field is empty
    @Test
    public void test1() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.openSauceDemoPage();
        loginPage.sendTextToUsername("");
        loginPage.clickLoginButton();

        String result = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assertions.assertEquals("Epic sadface: Username is required", result);

        driver.quit();
    }

    // Test Case 2: Verify error message when password field is empty
    @Test
    public void test2() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.openSauceDemoPage();
        loginPage.sendTextToUsername("standard_user");
        loginPage.sendTextToPassword("");
        loginPage.clickLoginButton();

        String result = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assertions.assertEquals("Epic sadface: Password is required", result);

        driver.quit();
    }

    // Test Case 3: Verify error message when valid username is used with wrong password
    @Test
    public void test3() {
        SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver);
        loginPage.openSauceDemoPage();
        loginPage.sendTextToUsername("standard_user");
        loginPage.sendTextToPassword("wrongpassword");
        loginPage.clickLoginButton();

        String result = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", result);

        driver.quit();
    }

}
