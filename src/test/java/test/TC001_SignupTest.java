package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC001_SignupTest extends ProjectSpecificationMethods {
	
	@BeforeTest
	public void setup() {
		sheetName="Signup";
		
	}
	
	@Test(dataProvider = "excelRead")
	public void signupTest(String username, String password, String testName) {
		
		HomePage hp = new HomePage(driver);
		hp.isSignUpButtonVisible()
		.clickSignup()
		.signupEnterUsername(username)
		.signupEnterPassword(password)
		.clickSignUpButton(testName);
	}

}
