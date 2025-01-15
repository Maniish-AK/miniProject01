package POM.MiniProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	static WebDriver driver;
	
	@FindBy(xpath = "//a[contains(text(),'Delete')]")
    public static WebElement deleteButton;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    public WebElement placeOrderButton;
    
    @FindBy(xpath = "//h3[@id='totalp']")
    public WebElement totalPrice;

    @FindBy(xpath = "//tr[@class='success']")
    public WebElement cartItem;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void deleteCartItem() {
        deleteButton.click();
    }
    
    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void placeOrder() {
        placeOrderButton.click();
    }
    
    public boolean isPlaceOrderButtonVisible() {
		return placeOrderButton.isDisplayed();
	}
    
    public boolean isCartEmpty() {
        try {
            return !cartItem.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

}
