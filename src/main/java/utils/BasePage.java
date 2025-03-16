package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    private WebDriver driver;

    public void setup() {
        //System.setProperty("webdriver.chrome.driver", ""); 
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
