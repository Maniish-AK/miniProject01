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

public class ProductPage extends ProjectSpecificationMethods {

	public static String numericPrice1;
	public static String numericPrice2;
	
	@FindBy(xpath = "//h2[@class='name']")
	public WebElement productTitle;

	@FindBy(xpath = "//h3[@class='price-container']")
	public WebElement productAmount;

	@FindBy(xpath = "//a[contains(text(),'Add to cart')]")
	public WebElement addToCartButton;
	
	@FindBy(xpath = "//a[contains(text(),'Cart')]")
    WebElement cartMenu;

	public ProductPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductPage getProductTitle() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productTitle));
		String productName = productTitle.getText();
		Assert.assertTrue(productName.contains("s6"), "Product name is incorrect.");
		return this;
	}

	public ProductPage getProductPrice1() {

		String productPrice1 = productAmount.getText();
		numericPrice1 = productPrice1.replaceAll("[^0-9]", "");
		Assert.assertTrue(numericPrice1.matches("^[0-9]+$"), "Product price is not in the correct format.");
		return this;
	}
	
	public ProductPage getProductPrice2() {

		String productPrice2 = productAmount.getText();
		numericPrice2 = productPrice2.replaceAll("[^0-9]", "");
		Assert.assertTrue(numericPrice2.matches("^[0-9]+$"), "Product price is not in the correct format.");
		return this;
	}

	public ProductPage addToCart() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		addToCartButton.click();
//		driver.switchTo().alert().accept();
		return this;
	}
	
	public CartPage clickCartMenu() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(cartMenu));
        cartMenu.click();
		return new CartPage(driver);
	}

}
