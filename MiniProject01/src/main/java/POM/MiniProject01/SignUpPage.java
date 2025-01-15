package POM.MiniProject01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

	WebDriver driver;
	
	@FindBy (xpath = "//input[@id='sign-username']")
	public WebElement signupUsername;
	
	@FindBy (xpath = "//input[@id='sign-password']")
	public static WebElement signupPassword;
	
	@FindBy (xpath = "//button[text()='Sign up']")
	public static WebElement signupButton;
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		signupUsername.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		signupPassword.sendKeys(password);
	}
	
	public void clickSignUpButton() {
		signupButton.click();
	}

}
