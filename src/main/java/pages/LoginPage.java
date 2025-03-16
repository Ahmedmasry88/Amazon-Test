package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By emailField = By.id("ap_email");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By signInButton = By.id("signInSubmit");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}
