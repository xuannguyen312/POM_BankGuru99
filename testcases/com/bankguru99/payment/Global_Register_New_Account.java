package com.bankguru99.payment;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.LoginPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Global_Register_New_Account extends AbstractTest {
	
	private WebDriver driver;
	private static String email;
	public static String USER_ID, PASSWORD;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
	
		driver = openMultiBrowser(browserName);
		email = "automation" + randomNumber() + "@gmail.com"; 
		loginPage = PageFactoryManager.getLoginPage(driver);
	}
	
	@Test
	public void Account_01_Global_Register() {
		registerPage = loginPage.clickToHereLink();
		registerPage.inputToEmailIDTextBox(email);
		registerPage.clickToSubmitButton();
		USER_ID = registerPage.getUserIDText();
		PASSWORD= registerPage.getPasswordText();
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

}
