package test;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.BasePage;
import utils.ConfigReader;

public class AmazonTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private BasePage base;
	private HomePage homePage;
	private LoginPage loginPage;
	private MenuPage menuPage;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;

	@BeforeClass
	public void setup() {
		base = new BasePage();
		base.setup();
		driver = base.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		homePage = new HomePage(driver, wait);
		loginPage = new LoginPage(driver, wait);
		menuPage = new MenuPage(driver, wait);
		productPage = new ProductPage(driver, wait);
		cartPage = new CartPage(driver, wait);
		checkoutPage = new CheckoutPage(driver, wait);
	}

	@Test(priority = 1)
	public void loginTest() {
		try {
			String email = ConfigReader.getProperty("email");
			String password = ConfigReader.getProperty("password");

			homePage.openAmazon();
			homePage.clickSignIn();
			loginPage.enterEmail(email);
			loginPage.enterPassword(password);

			Assert.assertTrue(homePage.isUserLoggedIn(), "Login failed!");
		} catch (Exception e) {
			Assert.fail("Exception in loginTest: " + e.getMessage());
		}
	}

	@Test(priority = 2, dependsOnMethods = "loginTest")
	public void navigateToVideoGames() {
		try {
			menuPage.openAllMenu();
			menuPage.selectVideoGames();
			menuPage.selectAllVideoGames();

			Assert.assertTrue(productPage.isInVideoGamesSection(), "Navigation to Video Games failed!");
		} catch (Exception e) {
			Assert.fail("Exception in navigateToVideoGames: " + e.getMessage());
		}
	}

	@Test(priority = 3, dependsOnMethods = "navigateToVideoGames")
	public void applyFiltersAndSort() {
		try {
			productPage.applyFilters();
			productPage.sortByPriceHighToLow();

			//Assert.assertTrue(productPage.areFiltersApplied(), "Filters and sorting were not applied!");
		} catch (Exception e) {
			Assert.fail("Exception in applyFiltersAndSort: " + e.getMessage());
		}
	}

	@Test(priority = 4, dependsOnMethods = "applyFiltersAndSort")
	public void addToCartAndVerify() {
		try {
			productPage.addProductsBelow15K();
			cartPage.verifyProductsInCart();

			Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty after adding products!");

			cartPage.proceedToCheckout();
		} catch (Exception e) {
			Assert.fail("Exception in addToCartAndVerify: " + e.getMessage());
		}
	}

	@Test(priority = 5, dependsOnMethods = "addToCartAndVerify")
	public void checkoutAndVerifyTotal() {
		try {
			checkoutPage.enterAddress();
			checkoutPage.selectCashOnDelivery();

			Assert.assertTrue(checkoutPage.isTotalAmountCorrect(), "Total amount verification failed!");
		} catch (Exception e) {
			Assert.fail("Exception in checkoutAndVerifyTotal: " + e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		base.teardown();
	}
}
