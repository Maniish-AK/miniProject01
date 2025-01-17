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

public class CartTest {
	
	WebDriver driver;
	
	@BeforeTest
    public void setUp() {
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
    }
	
	@Test (priority=1)
    public void testAddRemoveProductFromCart() throws InterruptedException {
        // Navigate to the homepage
        HomePage hp = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(HomePage.productSelect1));
        hp.selectProduct1();

        // Select the first product to go to its details page
        ProductPage pp = new ProductPage(driver);
        wait.until(ExpectedConditions.visibilityOf(pp.productTitle));
        String productNameBeforeAdding = pp.getProductTitle();
        String productPriceBeforeAdding = pp.getProductPrice();
        pp.addToCart();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOf(hp.cartMenu));
        hp.clickCart();

        // Navigate to the cart page
        CartPage cp = new CartPage(driver);
        wait.until(ExpectedConditions.visibilityOf(CartPage.deleteButton));
        cp.deleteCartItem();
        Thread.sleep(3000);
        if(cp.isCartEmpty()) {
        	System.out.println("Cart is empty after product removed");
        }
    }
	
	@Test (priority=2)
    public void testUpdateCartTotalAfterDeletion() throws InterruptedException {
        // Navigate to the homepage
        HomePage hp = new HomePage(driver);
        hp.clickLogo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(HomePage.productSelect2));
        hp.selectProduct2();

        // Select the first product to go to its details page
        ProductPage pp = new ProductPage(driver);
        wait.until(ExpectedConditions.visibilityOf(pp.productPrice));
        String productPriceBeforeAdding = pp.getProductPrice();

        // Add product to the cart
        pp.addToCart();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOf(hp.cartMenu));
        hp.clickCart();

        // Navigate to the cart page
        CartPage cp = new CartPage(driver);
        wait.until(ExpectedConditions.visibilityOf(cp.totalPrice));
        String totalPriceBeforeDeletion = cp.getTotalPrice();

        wait.until(ExpectedConditions.visibilityOf(CartPage.deleteButton));
        cp.deleteCartItem();
        Thread.sleep(3000);
        Assert.assertNotEquals(totalPriceBeforeDeletion, 0, "Total price did not update after product removal.");
        if(cp.isCartEmpty()) {
        	System.out.println("Cart is empty after product removed");
        }
    }
	
	@Test (priority=3)
    public void testTotalPriceCalculation() throws InterruptedException {
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
        double expectedTotal = Double.parseDouble(productPrice1.replaceAll("[^\\d.]", "")) + Double.parseDouble(productPrice2.replaceAll("[^\\d.]", ""));
        double actualTotal = Double.parseDouble(totalPriceAfterProduct2.replaceAll("[^\\d.]", ""));
        Assert.assertEquals(actualTotal, expectedTotal, "Total price calculation is incorrect.");
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
