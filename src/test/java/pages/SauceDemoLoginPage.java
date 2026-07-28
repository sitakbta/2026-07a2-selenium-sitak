package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemoLoginPage {
    WebDriver driver;

    // Locators for login page
    By usernameLocator = By.id("user-name");
    By passwordLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");

    public SauceDemoLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSauceDemoPage() {
        driver.get("https://www.saucedemo.com/");
    }

    public void sendTextToUsername(String text) {
        driver.findElement(usernameLocator).sendKeys(text);
    }

    public void sendTextToPassword(String text) {
        driver.findElement(passwordLocator).sendKeys(text);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
}
