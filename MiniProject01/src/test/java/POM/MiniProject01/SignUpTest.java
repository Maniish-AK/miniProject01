package POM.MiniProject01;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUpTest {

	WebDriver driver;
	
	@BeforeTest
    public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
        hp.openUrl("https://www.demoblaze.com/");
    }
	
	@Test
    public void testSignUpButtonVisibility() {
		Assert.assertTrue(HomePage.isSignUpButtonVisible(), "Sign Up button is not visible");
	}
	
	@Test
    public void testSignUpButtonClickability() {
        Assert.assertTrue(HomePage.isSignUpButtonVisible(), "Sign Up button is clickable");
//        HomePage.clickSignUp();
    }
	
	@Test
    public void testSignUpFunctionality() throws InterruptedException {
		HomePage hp = new HomePage(driver);
        hp.clickSignUp();
//        Thread.sleep(4000);
        SignUpPage sp = new SignUpPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(sp.signupUsername));
        sp.enterUsername("testguviuser4");
        sp.enterPassword("guvi123");
        sp.clickSignUpButton();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver
        }
    }

}
	
	
