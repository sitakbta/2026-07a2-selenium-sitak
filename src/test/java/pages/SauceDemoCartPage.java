package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemoCartPage {
    WebDriver driver;

    // Locators for cart page
    By cartItemsLocator = By.xpath("//div[@class='cart_item']");
    By removeButtonLocator = By.xpath("//button[contains(text(), 'Remove')]");

    public SauceDemoCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItemsLocator).size();
    }

    public void removeItemFromCart() {
        driver.findElement(removeButtonLocator).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItemsLocator).isEmpty();
    }
}
