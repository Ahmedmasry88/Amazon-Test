package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader; 

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By signInButton = By.id("nav-link-accountList");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openAmazon() {
        String amazonUrl = ConfigReader.getProperty("amazon_url");
        if (amazonUrl != null) {
            driver.get(amazonUrl);
        } else {
            System.out.println("amazon_url not found in config.properties");
        }
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public boolean isUserLoggedIn() {
        return driver.findElements(signInButton).size() > 0;
    }
}
