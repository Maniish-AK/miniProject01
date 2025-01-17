package POM.MiniProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	static WebDriver driver;
	
	@FindBy(xpath = "//h2[@class='name']")
    public WebElement productTitle;

    @FindBy(xpath = "//h3[@class='price-container']")
    public WebElement productPrice;

    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    public WebElement addToCartButton;
    
	
	public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public String getProductTitle() {
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }

}
