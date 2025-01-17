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

public class ProductCategoryDisplayTest {
	
	WebDriver driver;
	
	@SuppressWarnings("static-access")
	@BeforeTest
    public void setUp() {
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
    }
	
	@Test
    public void testWelcomeMessageVisibility() {
		HomePage hp = new HomePage(driver);
        Assert.assertTrue(hp.isWelcomeMessageVisible(), "Welcome message is not displayed on the homepage.");
    }
	
	@Test
    public void testMenuItemsVisibility() {
		HomePage hp = new HomePage(driver);
        Assert.assertTrue(HomePage.isHomeMenuVisible(), "Home menu is not visible.");
        Assert.assertTrue(HomePage.isContactMenuVisible(), "Contact menu is not visible.");
        Assert.assertTrue(hp.isCartMenuVisible(), "Cart menu is not visible.");
    }
	
	@Test
    public void testLogoVisibility() {
        Assert.assertTrue(HomePage.isLogoVisible(), "Logo is not displayed on the homepage.");
    }
	
	@Test
    public void testPhonesCategoryVisibility() {
        Assert.assertTrue(HomePage.isPhonesCategoryVisible(), "Phones category is not displayed on the homepage.");
    }
	
	@Test
    public void testLaptopsCategoryVisibility() {
        Assert.assertTrue(HomePage.isLaptopsCategoryVisible(), "Laptops category is not displayed on the homepage.");
    }
	
	@Test
    public void testMonitorsCategoryVisibility() {
        Assert.assertTrue(HomePage.isMonitorsCategoryVisible(), "Monitors category is not displayed on the homepage.");
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver
        }
    }

}
