package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    
    private By cartItems = By.cssSelector(".sc-list-item"); 
    private By proceedToCheckoutButton = By.name("proceedToRetailCheckout");

    
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

   
    public boolean isCartNotEmpty() {
        return driver.findElements(cartItems).size() > 0;
    }


    public void verifyProductsInCart() {
        driver.get("https://www.amazon.eg/gp/cart/view.html"); 
        Assert.assertTrue(isCartNotEmpty(), "Cart is empty after adding products!");
    }

   
    public void proceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }
}
