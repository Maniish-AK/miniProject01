package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC003_MenuAndCategories extends ProjectSpecificationMethods{
	
	@BeforeTest
	public void setup() {
		sheetName="Signup";
		
	}
	
	@Test
	public void menuAndCategoriesTest() {
		
		HomePage hp = new HomePage(driver);
		hp.clickLogin()
		.loginEnterUsername()
		.loginEnterPassword()
		.clickLoginButton()
		.validateLogin()
		.isHomeMenuVisible()
		.isContactMenuVisible()
		.isAboutusMenuVisible()
		.isCartMenuVisible()
		.isLogoutMenuVisible()
		.isPhonesCategoryVisible()
		.isLaptopsCategoryVisible()
		.isMonitorsCategoryVisible()
		.isLogoVisible();
	}

}
