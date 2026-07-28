package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemoInventoryPage {
    WebDriver driver;

    // Locators for inventory page
    By productAddButtonLocator = By.xpath("(//button[contains(text(), 'Add to cart')])[2]");
    By cartBadgeLocator = By.xpath("//span[@class='shopping_cart_badge']");
    By cartLinkLocator = By.xpath("//a[@class='shopping_cart_link']");
    By productRemoveButtonLocator = By.xpath("(//button[contains(text(), 'Remove')])[2]");

    public SauceDemoInventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstItemToCart() {
        driver.findElement(productAddButtonLocator).click();
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadgeLocator).getText();
    }

    public void goToCart() {
        driver.findElement(cartLinkLocator).click();
    }

    public void removeFirstItemFromCart() {
        driver.findElement(productRemoveButtonLocator).click();
    }

    public boolean isCartBadgeVisible() {
        try {
            driver.findElement(cartBadgeLocator);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
