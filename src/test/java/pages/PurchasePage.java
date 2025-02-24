package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

public class PurchasePage extends ProjectSpecificationMethods {

	@FindBy(id = "name")
    WebElement nameInput;

    @FindBy(id = "country")
    WebElement countryInput;

    @FindBy(id = "city")
    WebElement cityInput;

    @FindBy(id = "card")
    WebElement cardInput;

    @FindBy(id = "month")
    WebElement monthInput;

    @FindBy(id = "year")
    WebElement yearInput;

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    WebElement purchaseButton;
    
    @FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
    WebElement confirmMessage;
    
    @FindBy(xpath = "//button[@class='confirm btn btn-lg btn-primary']")
    WebElement confirmOk;
	
	public PurchasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public PurchasePage enterName() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(nameInput));
		nameInput.sendKeys("Maniish");
		return this;
	}

	public PurchasePage enterCountry() {

		countryInput.sendKeys("United States");
		return this;
	}
	
	public PurchasePage enterCity() {

		cityInput.sendKeys("Los Angeles");
		return this;
	}

	public PurchasePage enterCard() {

		cardInput.sendKeys("4445666677778888");
		return this;
	}
	
	public PurchasePage enterMonth() {

		monthInput.sendKeys("12");
		return this;
	}

	public PurchasePage enterYear() {

		yearInput.sendKeys("2025");
		return this;
	}
	
	public PurchasePage clickPurchaseButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(purchaseButton));
		purchaseButton.click();
		return this;
	}
	
	public LoginPage clickConfirmationOkButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confirmOk));
		confirmOk.click();
		return new LoginPage(driver);
	}

}
