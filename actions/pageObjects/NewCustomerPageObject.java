package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage {
	
	WebDriver driver;
	
	public NewCustomerPageObject (WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public boolean isNewCustomerPageDisplayed() {
		waitToElementVisible(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
		return isControlDisplay(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
	}

}
