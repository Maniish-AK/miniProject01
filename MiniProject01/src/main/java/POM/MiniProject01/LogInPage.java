package POM.MiniProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	
	static WebDriver driver;
	
	@FindBy (xpath = "//input[@id='loginusername']")
	public static WebElement loginUsername;
	
	@FindBy (xpath = "//input[@id='loginpassword']")
	public static WebElement loginPassword;
	
	@FindBy (xpath = "//button[text()='Log in']")
	public static WebElement loginButton;
	
	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		loginUsername.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		loginPassword.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}

}
