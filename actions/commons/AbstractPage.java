package commons;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageObject;
import pageObjects.PageFactoryManager;
import pageUIs.AbstractPageUI;
import pageUIs.HomePageUI;

public class AbstractPage {
	
	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getText(WebDriver driver, String location) {
		WebElement element = driver.findElement(By.xpath(location));
		return element.getText();
	}
	
	public String getTextInElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator,  (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	
	public void sendKeyToElement (WebDriver driver, String locator, String valueToSendKey, String...dynamicValue) {
		locator = String.format(locator,  (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(valueToSendKey);
	}
	
	public void sendKeyToElement (WebDriver driver, String locator, int valueToSendKey, String...dynamicValue) {
		locator = String.format(locator,  (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(String.valueOf(valueToSendKey));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void waitToElementVisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	
	public void waitToElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void waitToElementInVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		try {
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public boolean isControlDisplay(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	
	public boolean isControlDisplay(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			boolean status = element.isDisplayed();
			System.out.println("Element=" + status);
			return status;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean isControlUndisplayed(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		Date date = new Date();
		System.out.println("Started time = " + date.toString());
		// 5s
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = driver.findElements(By.xpath(locator));

		if (elements.size() > 0 && elements.get(0).isDisplayed()) {
			date = new Date();
			System.out.println("End time = " + date.toString());
			// 30s
			overrideGlobalTimeout(driver, longTimeout);
			return false;
		} else {
			date = new Date();
			System.out.println("End time = " + date.toString());
			// 30s
			overrideGlobalTimeout(driver, longTimeout);
			return true;
		}
	}
	
	public void overrideGlobalTimeout(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	
	
	public AbstractPage openDynamicPage(WebDriver driver, String pageName) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		switch (pageName) {
		case "Deposit":
			return PageFactoryManager.getDepositPage(driver);
		case "New Customer":
			return PageFactoryManager.getNewCustomerPage(driver);
		case "New Account":
			return PageFactoryManager.getNewAccountPage(driver);
		case "Edit Customer":
			return PageFactoryManager.getEditCustomerPage(driver);
		case "Withdrawal":
			return PageFactoryManager.getWithDrawalPage(driver);
		case "Fund Transfer":
			return PageFactoryManager.getFundTransferPage(driver);
			default:
				return PageFactoryManager.getHomePage(driver);
		}
	}
	
	public void inputToDynamicTextbox(WebDriver driver, String textboxNameID, String valueToSendkey) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON, textboxNameID );
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON, valueToSendkey, textboxNameID);
	}
	
	public void inputToDynamicTextbox(WebDriver driver, String textboxNameID, int valueToSendkey) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON, textboxNameID );
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON, valueToSendkey, textboxNameID);
	}
	
	public void clickToDynamicRadioButton(WebDriver driver, String radioButtonNameValue) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, radioButtonNameValue );
		clickToElement(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, radioButtonNameValue);
	}
	
	public void inputToDynamicTextArea(WebDriver driver, String textAreaNameID, String valueToSendkey) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXT_AREA, textAreaNameID );
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXT_AREA, valueToSendkey, textAreaNameID);
	}
	
	public void clickToDynamicButton(WebDriver driver, String buttonNameID) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON, buttonNameID );
		clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON, buttonNameID);
	}
	
	
	
	public boolean isDynamicPageOrMessageDisplayed(WebDriver driver, String pageHeadingName) {
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_HEADING_NAME, pageHeadingName);
		return isControlDisplay(driver, AbstractPageUI.DYNAMIC_PAGE_HEADING_NAME, pageHeadingName);
	}
	
	public boolean isDynamicPageOrMessageDisplayedID(WebDriver driver, String accountID) {
		waitToElementVisible(driver, AbstractPageUI.HEADER_TRANSFER_TEXT, accountID);
		return isControlDisplay(driver, AbstractPageUI.HEADER_TRANSFER_TEXT, accountID);
	}
	
	public boolean isDynamicPageOrMessageDisplayedID2(WebDriver driver, String accountID) {
		waitToElementVisible(driver, AbstractPageUI.HEADER_TRANSFER_TEXT_2, accountID);
		return isControlDisplay(driver, AbstractPageUI.HEADER_TRANSFER_TEXT_2, accountID);
	}
	
	public String getDynamicTextInTable(WebDriver driver, String rowName)
	{
		waitToElementVisible(driver, AbstractPageUI.DYNAMIC_TABLE_ROW_NAME, rowName);
		return getTextInElement(driver, AbstractPageUI.DYNAMIC_TABLE_ROW_NAME, rowName);
}
	
	int shortTimeout = 5;
	int longTimeout = 30;
	
}

