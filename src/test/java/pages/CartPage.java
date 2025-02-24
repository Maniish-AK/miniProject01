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

public class CartPage extends ProjectSpecificationMethods {

	@FindBy(xpath = "//a[contains(text(),'Delete')]")
	WebElement deleteButton;

	@FindBy(xpath = "//button[contains(text(),'Place Order')]")
	WebElement placeOrderButton;

	@FindBy(xpath = "//h3[@id='totalp']")
	WebElement totalPrice;

	@FindBy(xpath = "//tr[@class='success']")
	WebElement cartItem;

	@FindBy(xpath = "//a[@id='nava']//img[@style='margin-right:10px']")
	WebElement logo;

	public CartPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CartPage isCartEmpty() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(cartItem));
		cartItem.isDisplayed();
		return this;
	}

	public LoginPage clickLogo() {

		logo.click();
		return new LoginPage(driver);
	}

	public CartPage validateTotalAmountAfterProductAdd() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(totalPrice));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String totalPriceAfterProduct2 = totalPrice.getText();
		double expectedTotal = Double.parseDouble(ProductPage.numericPrice1.replaceAll("[^\\d.]", ""))
				+ Double.parseDouble(ProductPage.numericPrice2.replaceAll("[^\\d.]", ""));
		double actualTotal = Double.parseDouble(totalPriceAfterProduct2.replaceAll("[^\\d.]", ""));
		Assert.assertEquals(actualTotal, expectedTotal, "Total price calculation is incorrect.");
		return this;
	}

	public CartPage deleteCartItem() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(deleteButton));
		deleteButton.click();
		return this;
	}

	public PurchasePage clickPlaceOrderButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
		placeOrderButton.click();
		return new PurchasePage(driver);
	}

}
