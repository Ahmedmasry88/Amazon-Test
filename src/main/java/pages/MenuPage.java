package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MenuPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By allMenuButton = By.id("nav-hamburger-menu");
    private By seeAll = By.xpath("//i[contains(@class,'hmenu-arrow-more')]"); // Fixed XPath
    private By videoGamesLink = By.xpath("//div[contains(text(),'Video Games')]");
    private By allVideoGamesLink = By.xpath("(//a[@class='hmenu-item'][normalize-space()='Video Games'])[3]");
    private By allVideoGames = By.xpath("//a[contains(text(),'All Video Games')]");

    public void openAllMenu() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(allMenuButton));
        menuButton.click();
    }

    public void selectVideoGames() {
        WebElement seeAllButton = wait.until(ExpectedConditions.elementToBeClickable(seeAll));
        seeAllButton.click();

        WebElement videoGames = wait.until(ExpectedConditions.elementToBeClickable(videoGamesLink));
        videoGames.click();
    }

    public void selectAllVideoGames() {
        WebElement videoGamesCategory = wait.until(ExpectedConditions.elementToBeClickable(allVideoGamesLink));
        videoGamesCategory.click();

        try {
            WebElement allVideoGamesElement = wait.until(ExpectedConditions.elementToBeClickable(allVideoGames));
            allVideoGamesElement.click();
            System.out.println("Clicked 'All Video Games'");
        } catch (Exception e) {
            WebElement allVideoGamesElement = driver.findElement(allVideoGames);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allVideoGamesElement);
            System.out.println("Clicked 'All Video Games' using JavaScript");
        }
    }
}
