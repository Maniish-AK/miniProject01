package POM.MiniProject01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductTest {

	WebDriver driver;

	@SuppressWarnings("static-access")
	@BeforeTest
	public void testProductRedirection() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
        hp.openUrl("https://www.demoblaze.com/");
        hp.clickLogin();
        LogInPage lp = new LogInPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(lp.loginUsername));
        lp.enterUsername("ManiishGuvi");
        lp.enterPassword("Maniish@0212");
        lp.clickLoginButton();
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Welcome')]")));
        hp.isWelcomeMessageVisible();
//        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(hp.productSelect1));
        hp.selectProduct1();
        
		ProductPage pp = new ProductPage(driver);
		wait.until(ExpectedConditions.visibilityOf(pp.productTitle));

		// Verify the product details page is shown
		Assert.assertTrue(pp.getProductTitle().length() > 0, "Product name is not displayed.");
		Assert.assertTrue(pp.getProductPrice().length() > 0, "Product price is not displayed.");
	}
	
	@Test (priority=1)
    public void testAddProductToCart() throws InterruptedException {
        HomePage hp = new HomePage(driver);
//        hp.selectProduct1();

        // Click on a product to go to its details page
        ProductPage pp = new ProductPage(driver);
        String productNameBeforeAdding = pp.getProductTitle();
        String productPriceBeforeAdding = pp.getProductPrice().replaceAll("[^0-9]", "");
//        productPriceBeforeAdding.replaceAll("[^0-9]", "");
        System.out.println(productPriceBeforeAdding);
        
        // Add the product to the cart
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOf(pp.addToCartButton));
        pp.addToCart();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        wait1.until(ExpectedConditions.visibilityOf(hp.cartMenu));
        hp.clickCart();

        // Go to the cart
        CartPage cp = new CartPage(driver);
        wait1.until(ExpectedConditions.visibilityOf(cp.totalPrice));
        String totalPrice = cp.getTotalPrice();
        System.out.println(totalPrice);

        // Verify the product is in the cart
        Assert.assertTrue(totalPrice.contains(productPriceBeforeAdding), "The product price in the cart does not match.");
        wait1.until(ExpectedConditions.visibilityOf(CartPage.deleteButton));
        cp.deleteCartItem();
    }
	
	@Test (priority=2)
    public void testProductDetailsAccuracy() {
        // Navigate to the homepage
        HomePage hp = new HomePage(driver);
        hp.clickLogo();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOf(HomePage.productSelect1));
        hp.selectProduct1();

        // Select the first product
        ProductPage pp = new ProductPage(driver);
        // Get product details (name and price)
        wait2.until(ExpectedConditions.visibilityOf(pp.productTitle));
        String productName = pp.getProductTitle();
        String productPrice = pp.getProductPrice();
        System.out.println(productPrice);
        String numericPrice = productPrice.replaceAll("[^0-9]", "");

        // Verify that the product details (name and price) are correctly displayed
        Assert.assertTrue(productName.contains("s6"), "Product name is incorrect.");
        Assert.assertTrue(numericPrice.matches("^[0-9]+$"), "Product price is not in the correct format.");
    }
//	
	@Test (priority=3)
    public void testAddMultipleProductsToCart() throws InterruptedException {
        // Navigate to the homepage
        HomePage hp = new HomePage(driver);
        hp.clickLogo();
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait3.until(ExpectedConditions.visibilityOf(HomePage.productSelect1));
        hp.selectProduct1();
        
        // Add first product to cart
        ProductPage pp1 = new ProductPage(driver);
        wait3.until(ExpectedConditions.visibilityOf(pp1.productTitle));
        String productName1 = pp1.getProductTitle();
        String productPrice1 = pp1.getProductPrice();
        pp1.addToCart();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        wait3.until(ExpectedConditions.visibilityOf(hp.cartMenu));
        hp.clickCart();
        CartPage cp = new CartPage(driver);
        wait3.until(ExpectedConditions.visibilityOf(cp.totalPrice));
        String totalPriceAfterProduct1 = cp.getTotalPrice();

        hp.clickLogo();
        wait3.until(ExpectedConditions.visibilityOf(HomePage.productSelect2));
        hp.selectProduct2();
        ProductPage pp2 = new ProductPage(driver);
        wait3.until(ExpectedConditions.visibilityOf(pp2.productTitle));
        String productName2 = pp2.getProductTitle();
        String productPrice2 = pp2.getProductPrice();
        pp2.addToCart();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        hp.clickCart();
        wait3.until(ExpectedConditions.visibilityOf(cp.totalPrice));
        String totalPriceAfterProduct2 = cp.getTotalPrice();

        // Verify both products are in the cart
        Assert.assertNotEquals(totalPriceAfterProduct1, totalPriceAfterProduct2, "Total price did not update after product removal.");
        wait3.until(ExpectedConditions.visibilityOf(CartPage.deleteButton));
        cp.deleteCartItem();
        Thread.sleep(3000);
        wait3.until(ExpectedConditions.visibilityOf(CartPage.deleteButton));
        cp.deleteCartItem();
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver
        }
    }

}
