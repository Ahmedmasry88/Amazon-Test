package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

   
    private By addressSelection = By.xpath("//input[@name='addressID']");
    private By useThisAddressButton = By.xpath("//input[@aria-labelledby='shipToThisAddressButton']");
    private By cashOnDeliveryOption = By.xpath("//input[@value='CashOnDelivery']");
    private By continueButton = By.name("continue");
    private By totalAmount = By.cssSelector(".grand-total-price"); 

   
    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    
    public void enterAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(addressSelection)).click();
        wait.until(ExpectedConditions.elementToBeClickable(useThisAddressButton)).click();
    }

    
    public void selectCashOnDelivery() {
        wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    
    public boolean isTotalAmountCorrect() {
        String total = wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount)).getText();
        return total != null && !total.isEmpty(); 
    }

    
    public boolean isOrderConfirmed() {
        return driver.getTitle().contains("Order Confirmation");
    }
}
