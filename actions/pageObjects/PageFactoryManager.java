package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) 
	{
		return new RegisterPageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) 
	{
		return new LoginPageObject(driver);
	}
	
	public static HomePageObject getHomePage(WebDriver driver) 
	{
		return new HomePageObject(driver);
	}
	
	public static DepositPageObject getDepositPage(WebDriver driver)
	{
		return new DepositPageObject(driver);
	}
	
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
	
	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}

}
