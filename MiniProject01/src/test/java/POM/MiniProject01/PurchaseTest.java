package POM.MiniProject01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class PurchaseTest {
	
	WebDriver driver;
	
	@Test (priority=1)
    public void testPlaceOrderButtonVisible() throws InterruptedException {
        // Navigate to the homepage
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
        hp.openUrl("https://www.demoblaze.com/");
        hp.clickLogin();
        LogInPage lp = new LogInPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(LogInPage.loginUsername));
        lp.enterUsername("ManiishGuvi");
        lp.enterPassword("Maniish@0212");
        lp.clickLoginButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Welcome')]")));
        hp.isWelcomeMessageVisible();
        wait.until(ExpectedConditions.visibilityOf(HomePage.productSelect2));
        hp.selectProduct2();

        // Add a product to the cart
        ProductPage pp = new ProductPage(driver);
        wait.until(ExpectedConditions.visibilityOf(pp.addToCartButton));
        pp.addToCart();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOf(hp.cartMenu));
        hp.clickCart();
        CartPage cp = new CartPage(driver);
        wait.until(ExpectedConditions.visibilityOf(cp.cartItem));
        wait.until(ExpectedConditions.visibilityOf(cp.placeOrderButton));
        Assert.assertTrue(cp.isPlaceOrderButtonVisible(), "Place Order button is not visible");
    }
	
	@Test (priority=2)
    public void testOrderDetailsFields() {
        CartPage cp = new CartPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(cp.placeOrderButton));
        cp.placeOrder();

        // Verify that the order details fields are displayed
        PurchasePage pup = new PurchasePage(driver);
        wait.until(ExpectedConditions.visibilityOf(pup.nameInput));
        Assert.assertTrue(pup != null, "Purchase page is not loaded.");
        Assert.assertTrue(pup.isNameFieldVisible(), "Name field is not visible.");
        Assert.assertTrue(pup.isCountryFieldVisible(), "Country field is not visible.");
        Assert.assertTrue(pup.isCityFieldVisible(), "City field is not visible.");
        Assert.assertTrue(pup.isCardFieldVisible(), "Card field is not visible.");
        Assert.assertTrue(pup.isMonthfieldVisible(), "Month field is not visible.");
        Assert.assertTrue(pup.isYearFieldVisible(), "Year field is not visible.");
    }
	
	@Test (priority=3)
    public void testCompleteOrder() {
        // Enter user details and confirm the order
        PurchasePage pup = new PurchasePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(pup.nameInput));
        String name = "John Wick";
        String country = "USA";
        String city = "New York";
        String card = "1234567812345678";
        String month = "12";
        String year = "2025";
        
        pup.enterUserDetails(name, country, city, card, month, year);
        wait.until(ExpectedConditions.visibilityOf(pup.purchaseButton));
        pup.confirmPurchase();

        // Verify that the order is successfully placed
        // Assuming a confirmation message appears after placing the order
        wait.until(ExpectedConditions.visibilityOf(pup.confirmMessage));
        String confirmationMessage = pup.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("Thank you for your purchase!"), "Order was not successfully placed.");
        wait.until(ExpectedConditions.visibilityOf(pup.confirmOk));
        pup.confirmPurchaseOk();
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver
        }
    }

}
