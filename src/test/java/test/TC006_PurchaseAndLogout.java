package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC006_PurchaseAndLogout extends ProjectSpecificationMethods {
	
	
	@BeforeTest
	public void setup() {
		sheetName="Signup";
		
	}
	
	@Test
	public void purchaseAndLogout() {
		
		HomePage hp = new HomePage(driver);
		hp.clickLogin()
		.loginEnterUsername()
		.loginEnterPassword()
		.clickLoginButton()
		.validateLogin()
		.selectProduct1()
		.getProductTitle()
		.getProductPrice1()
		.addToCart()
		.clickCartMenu()
		.isCartEmpty()
		.clickPlaceOrderButton()
		.enterName()
		.enterCountry()
		.enterCity()
		.enterCard()
		.enterMonth()
		.enterYear()
		.clickPurchaseButton()
		.clickConfirmationOkButton();
		
	}

}
