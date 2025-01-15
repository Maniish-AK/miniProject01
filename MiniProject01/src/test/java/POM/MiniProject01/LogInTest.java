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

public class LogInTest {
	
	WebDriver driver;
	
	@BeforeTest
    public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
        hp.openUrl("https://www.demoblaze.com/");
    }
	
	@Test
    public void testLoginButtonVisibility() {
        Assert.assertTrue(HomePage.isLoginButtonVisible(), "Login button is not visible");
    }

    @Test
    public void testLoginButtonClickability() {
        Assert.assertTrue(HomePage.isLoginButtonVisible(), "Login button is clickable");
//        HomePage.clickLogin();
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void testLoginFunctionality() throws InterruptedException {
    	HomePage hp = new HomePage(driver);
        hp.clickLogin();
//        Thread.sleep(4000);
        LogInPage lp = new LogInPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(lp.loginUsername));
        lp.enterUsername("ManiishGuvi");
        lp.enterPassword("Maniish@0212");
        lp.clickLoginButton();
//        Thread.sleep(3000);
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Welcome')]")));
        hp.isWelcomeMessageVisible();
    }
    
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver
        }
    }

}
