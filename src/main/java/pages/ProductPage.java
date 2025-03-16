package pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By newConditionFilter = By.xpath("//span[contains(text(),'New')]");
	private By sortMenu = By.xpath("//span[@id='a-autoid-0-announce']");
	private By highToLowOption = By.xpath("//a[@id='s-result-sort-select_2']");
	private By productPrices = By.cssSelector(".a-price-whole");
	private By productTitles = By.cssSelector(".s-title-instructions-style");
	private By addToCartButton = By.id("add-to-cart-button");
	private By nextPageButton = By.xpath("//a[contains(@aria-label,'Next')]");

	public ProductPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public boolean isInVideoGamesSection() {
		return driver.getCurrentUrl().contains("video-games");
	}

	public void applyFilters() {
		wait.until(ExpectedConditions.elementToBeClickable(newConditionFilter)).click();
	}

	public void sortByPriceHighToLow() {
		wait.until(ExpectedConditions.elementToBeClickable(sortMenu)).click();
		wait.until(ExpectedConditions.elementToBeClickable(highToLowOption)).click();
	}

	public void addProductsBelow15K() {
		List<WebElement> prices = driver.findElements(productPrices);
		List<WebElement> productLinks = driver.findElements(productTitles);

		boolean productAdded = false;

		for (int i = 0; i < prices.size(); i++) {
			int price = Integer.parseInt(prices.get(i).getText().replace(",", "").trim());

			if (price < 15000) {
				productLinks.get(i).click(); 
				wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click(); 
				driver.navigate().back(); 
				productAdded = true;
			}
		}

		if (!productAdded) {
			try {
				WebElement nextPage = driver.findElement(nextPageButton);
				nextPage.click();
				wait.until(ExpectedConditions.stalenessOf(prices.get(0))); 
				addProductsBelow15K(); 
			} catch (NoSuchElementException e) {
				System.out.println("No more pages to check.");
			}
		}
	}
}
