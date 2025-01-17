package POM.MiniProject01;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy (xpath = "//a[@id='signin2']")
	public static WebElement signUpButton;
	
	@FindBy (xpath = "//a[@id='login2']")
	public static WebElement loginButton;
	
	@FindBy(xpath = "//a[contains(text(),'Home ')]")
    public static WebElement homeMenu;

    @FindBy(xpath = "//a[contains(text(),'Contact')]")
    public static WebElement contactMenu;
    
    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    public WebElement cartMenu;
	
	@FindBy (xpath = "//a[@id='nava']//img[@style='margin-right:10px']")
	public static WebElement logo;
	
	@FindBy (xpath = "//a[contains(text(),'Phones')]")
	public static WebElement phonesCategory;
	
	@FindBy (xpath = "//a[contains(text(),'Laptops')]")
	public static WebElement laptopsCategory;
	
	@FindBy (xpath = "//a[contains(text(),'Monitors')]")
	public static WebElement monitorsCategory;
	
	@FindBy (xpath = "//a[contains(text(), 'Welcome')]")
	public static WebElement welcomeMessage;
	
	@FindBy (xpath = "(//div[@id='tbodyid']//a[@class='hrefch'])[1]")
	public static WebElement productSelect1;
	
	@FindBy (xpath = "(//div[@id='tbodyid']//a[@class='hrefch'])[3]")
	public static WebElement productSelect2;
	
	@FindBy (xpath = "//a[@id='logout2']")
	public static WebElement logOutButton;
	

	public HomePage(WebDriver driver) {
		
//		driver = new ChromeDriver();
//		driver = this.driver;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static boolean isSignUpButtonVisible() {
        return signUpButton.isDisplayed();
    }

    public static boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }
    
    public boolean isLogoutButtonVisible() {
    	try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(logOutButton));
            return logOutButton.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isWelcomeMessageVisible() {
        return welcomeMessage.isDisplayed();
    }

    public static boolean isLogoVisible() {
        return logo.isDisplayed();
    }
    
    public static boolean isHomeMenuVisible() {
        return homeMenu.isDisplayed();
    }

    public static boolean isContactMenuVisible() {
        return contactMenu.isDisplayed();
    }
    
    public boolean isCartMenuVisible() {
        return cartMenu.isDisplayed();
    }
    
    public static boolean isPhonesCategoryVisible() {
        return phonesCategory.isDisplayed();
    }
    
    public static boolean isLaptopsCategoryVisible() {
        return laptopsCategory.isDisplayed();
    }
    
    public static boolean isMonitorsCategoryVisible() {
        return monitorsCategory.isDisplayed();
    }

    public void clickSignUp() {
        signUpButton.click();
    }

    public void clickLogin() {
        loginButton.click();
    }
    
    public void clickLogo() {
    	logo.click();
    }
    
    public void clickCart() {
    	cartMenu.click();
    }

    public void clickPhonesCategory() {
        phonesCategory.click();
    }

    public void clickLaptopsCategory() {
        laptopsCategory.click();
    }
	
    public void selectProduct1() {
    	productSelect1.click();
    }
    
    public void selectProduct2() {
    	productSelect2.click();
    }
    
    public void clickLogout() {
    	logOutButton.click();
    }
	
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public void openUrl(String url) {
		driver.get(url);
	}

}
