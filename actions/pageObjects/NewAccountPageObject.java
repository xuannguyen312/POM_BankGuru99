package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commons.AbstractPage;
import pageUIs.NewAccountPageUI;

public class NewAccountPageObject extends AbstractPage {
	WebDriver driver;
	
	public NewAccountPageObject (WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public void selectOnDropDown(String optionValue) throws InterruptedException {
		waitToElementVisible(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		Select select = new Select (driver.findElement(By.xpath(NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN)));
		select.selectByVisibleText(optionValue);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),optionValue);
		Thread.sleep(2000);
		
	}
	
	public int compareAmount (int valueInitial, int valueAmount) {
		if (valueInitial == valueAmount)
		{
			System.out.println("Current Amount and Initial Deposit are equal");
			return valueAmount;
		}
		else
		return valueInitial;
		
	}
	

}
