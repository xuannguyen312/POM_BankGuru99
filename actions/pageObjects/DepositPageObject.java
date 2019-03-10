package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.DepositPageUI;
import pageUIs.HomePageUI;

public class DepositPageObject extends AbstractPage {
	WebDriver driver;
	
	public DepositPageObject (WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public boolean isTransactionPageDisplay() {
		waitToElementVisible(driver, DepositPageUI.HEADER_TRANSFER_TEXT);
		return isControlDisplay(driver, DepositPageUI.HEADER_TRANSFER_TEXT);
	}
	
	public int compareAmount (int value1, int value2, int value3) {
		if (value1+value2==value3)
		{
			System.out.println("Current Amount + New Amount = Current Balance is " + value3);	
		}
		else
		 System.out.println("Current Balance is showing wrong value");
		return value3;
	}

}
