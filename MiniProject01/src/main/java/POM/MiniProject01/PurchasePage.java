package POM.MiniProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
	
	static WebDriver driver;
	
	@FindBy(xpath = "//input[@id='name']")
    public WebElement nameInput;

    @FindBy(xpath = "//input[@id='country']")
    public static WebElement countryInput;

    @FindBy(xpath = "//input[@id='city']")
    public static WebElement cityInput;

    @FindBy(xpath = "//input[@id='card']")
    public static WebElement cardInput;

    @FindBy(xpath = "//input[@id='month']")
    public static WebElement monthInput;

    @FindBy(xpath = "//input[@id='year']")
    public static WebElement yearInput;

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    public WebElement purchaseButton;
    
    @FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
    public WebElement confirmMessage;
    
    @FindBy(xpath = "//button[@class='confirm btn btn-lg btn-primary']")
    public WebElement confirmOk;
    
    
	
	public PurchasePage(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void enterUserDetails(String name, String country, String city, String card, String month, String year) {
        nameInput.sendKeys(name);
        countryInput.sendKeys(country);
        cityInput.sendKeys(city);
        cardInput.sendKeys(card);
        monthInput.sendKeys(month);
        yearInput.sendKeys(year);
    }
	
	public boolean isPurchaseButtonVisible() {
		return purchaseButton.isDisplayed();
	}
	
	public boolean isNameFieldVisible() {
		return nameInput.isDisplayed();
	}
	
	public boolean isCountryFieldVisible() {
		return countryInput.isDisplayed();
	}
	
	public boolean isCityFieldVisible() {
		return cityInput.isDisplayed();
	}
	
	public boolean isCardFieldVisible() {
		return cardInput.isDisplayed();
	}
	
	public boolean isMonthfieldVisible() {
		return monthInput.isDisplayed();
	}
	
	public boolean isYearFieldVisible() {
		return yearInput.isDisplayed();
	}
	
	public void confirmPurchase() {
        purchaseButton.click();
    }
	
	public String getConfirmationMessage() {
		String msg = confirmMessage.getText();
		return msg;
    }
	
	public void confirmPurchaseOk() {
		confirmOk.click();
    }

}
