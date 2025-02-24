package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods {

	@FindBy(id = "signin2")
	WebElement homeSignUpButton;

	@FindBy(id = "sign-username")
	WebElement signupUsername;

	@FindBy(id = "sign-password")
	WebElement signupPassword;

	@FindBy(xpath = "//button[text()='Sign up']")
	WebElement signupButton;

	@FindBy(id = "login2")
	WebElement homeLoginButton;

	@FindBy(id = "loginusername")
	public static WebElement loginUsername;

	@FindBy(id = "loginpassword")
	public static WebElement loginPassword;

	@FindBy(xpath = "//button[text()='Log in']")
	public static WebElement loginButton;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage isSignUpButtonVisible() {

		homeSignUpButton.isDisplayed();
		return this;
	}

	public HomePage clickSignup() {

		homeSignUpButton.click();
		return this;
	}

	public HomePage signupEnterUsername(String username) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signupUsername));
		signupUsername.sendKeys(username);
		return this;
	}

	public HomePage signupEnterPassword(String password) {

		signupPassword.sendKeys(password);
		return this;
	}

	public HomePage clickSignUpButton(String testName) {

		if(testName.equalsIgnoreCase("ValidDetails")) {
		signupButton.click();
//		driver.switchTo().alert().accept();
		}
		return this;
	}

	public HomePage isLoginButtonVisible() {

		homeLoginButton.isDisplayed();
		return this;
	}

	public HomePage clickLogin() {

		homeLoginButton.click();
		return this;
	}

	public HomePage loginEnterUsername() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginUsername));
		loginUsername.sendKeys("ManiishGuvi");
		return this;
	}

	public HomePage loginEnterPassword() {

		loginPassword.sendKeys("Maniish@0212");
		return this;
	}

	public LoginPage clickLoginButton() {

		loginButton.click();
		return new LoginPage(driver);
	}

}
