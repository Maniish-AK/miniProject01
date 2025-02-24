package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC002_LoginTest extends ProjectSpecificationMethods {
	
	@BeforeTest
	public void setup() {
		sheetName="Login";
		
	}
	
	@Test
	public void loginTest() {
		
		HomePage hp = new HomePage(driver);
		hp.isLoginButtonVisible()
		.clickLogin()
		.loginEnterUsername()
		.loginEnterPassword()
		.clickLoginButton()
		.validateLogin();
	}

}
