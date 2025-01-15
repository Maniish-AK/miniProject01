package POM.MiniProject01;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogOutTest {
	
	WebDriver driver;
	
	@SuppressWarnings("static-access")
	@BeforeTest
    public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
//        driver = hp.getDriver();
        hp.openUrl("https://www.demoblaze.com/");
        hp.clickLogin();
//        Thread.sleep(4000);
        LogInPage lp = new LogInPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(lp.loginUsername));
        lp.enterUsername("ManiishGuvi");
        lp.enterPassword("Maniish@0212");
        lp.clickLoginButton();
//        Thread.sleep(4000);
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Welcome')]")));
        hp.isWelcomeMessageVisible();
    }
	
//	@SuppressWarnings("static-access")
//	@Test
//    public void testLogoutButtonVisibility() {
//		HomePage hp = new HomePage(driver);
//		hp.isWelcomeMessageVisible();
//    }
	
	@SuppressWarnings("static-access")
	@Test
    public void testLogoutAndRedirection() {
        // Click on the "Log out" button
		HomePage hp = new HomePage(driver);
		hp.clickLogout();
//		hp.isLoginButtonVisible();

        // Verify if the user is redirected to the homepage
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    try {
	        WebElement logInButtonVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='login2']")));
	        Assert.assertTrue(logInButtonVisible.isDisplayed(), "User was redirected to homepage after logging out.");
	    } catch (TimeoutException e) {
	        Assert.fail("Failed to find the Log in button after logout.");
	    }
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver
        }
    }

}
