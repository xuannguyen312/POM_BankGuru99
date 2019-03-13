package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class WithDrawalPageObject extends AbstractPage {
WebDriver driver;
	
	public WithDrawalPageObject (WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public int compareAmount (int value1, int value2, int value3) {
		if (value1-value2==value3)
		{
			System.out.println("Current Amount - Withdraw Amount = Current Balance is " + value3);	
		}
		else
		 System.out.println("Current Balance is showing wrong value");
		return value3;
	}

}
