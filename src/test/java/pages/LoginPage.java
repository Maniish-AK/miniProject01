package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class LoginPage extends ProjectSpecificationMethods {
	
	@FindBy (xpath = "//a[contains(text(), 'Welcome')]")
	WebElement welcomeMessage;
	
	@FindBy(xpath = "//a[contains(text(),'Home ')]")
    WebElement homeMenu;

    @FindBy(xpath = "//a[contains(text(),'Contact')]")
    WebElement contactMenu;
    
    @FindBy(xpath = "//a[contains(text(),'About us')]")
    WebElement aboutusMenu;
    
    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    WebElement cartMenu;
    
    @FindBy(id = "logout2")
	WebElement logoutMenu;
	
	@FindBy (xpath = "//a[@id='nava']//img[@style='margin-right:10px']")
	WebElement logo;
	
	@FindBy (xpath = "//a[contains(text(),'Phones')]")
	WebElement phonesCategory;
	
	@FindBy (xpath = "//a[contains(text(),'Laptops')]")
	WebElement laptopsCategory;
	
	@FindBy (xpath = "//a[contains(text(),'Monitors')]")
	WebElement monitorsCategory;
	
	@FindBy (xpath = "(//div[@id='tbodyid']//a[@class='hrefch'])[1]")
	WebElement productSelect1;
	
	@FindBy (xpath = "(//div[@id='tbodyid']//a[@class='hrefch'])[3]")
	WebElement productSelect2;

	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage validateLogin() {
		
		String expectedMsg = "Welcome ManiishGuvi";
		String actualMsg = welcomeMessage.getText();
		
		Assert.assertEquals(actualMsg, expectedMsg);
		return this;
	}
	
	public LoginPage isHomeMenuVisible() {

		homeMenu.isDisplayed();
		return this;
	}
	
	public LoginPage isContactMenuVisible() {

		contactMenu.isDisplayed();
		return this;
	}
	
	public LoginPage isAboutusMenuVisible() {

		aboutusMenu.isDisplayed();
		return this;
	}
	
	public LoginPage isCartMenuVisible() {

		cartMenu.isDisplayed();
		return this;
	}
	
	public LoginPage isLogoutMenuVisible() {

		logoutMenu.isDisplayed();
		return this;
	}
	
	public LoginPage isLogoVisible() {

		logo.isDisplayed();
		return this;
	}
	
	public LoginPage isPhonesCategoryVisible() {

		phonesCategory.isDisplayed();
		return this;
	}
	
	public LoginPage isLaptopsCategoryVisible() {

		laptopsCategory.isDisplayed();
		return this;
	}
	
	public LoginPage isMonitorsCategoryVisible() {

		monitorsCategory.isDisplayed();
		return this;
	}
	
	public ProductPage selectProduct1() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productSelect1));
		productSelect1.click();
		return new ProductPage(driver);
	}
	
	public ProductPage selectProduct2() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productSelect2));
        productSelect2.click();
		return new ProductPage(driver);
	}
	
	public LoginPage clickLogout() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(logoutMenu));
		logoutMenu.click();
		return this;
	}

}
