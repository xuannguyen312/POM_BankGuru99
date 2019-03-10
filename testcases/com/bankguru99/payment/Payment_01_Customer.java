package com.bankguru99.payment;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.DepositPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageUIs.AbstractPageUI;
import pageUIs.DepositPageUI;
import pageUIs.NewAccountPageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Payment_01_Customer extends AbstractTest {
  WebDriver driver;
  
  private String email, USER_ID, PASSWORD,password_ID, customerName, gender, dateOfBirth, address, city, state, pin, phone, dateOfBirthExpected, genderValue, customerID;
  private String descriptionValue, accountID; 
  private int currentBalanceActual;
  private int currentAmount;
  
  private LoginPageObject loginPage;
  private RegisterPageObject registerPage;
  private HomePageObject homePage;
  private NewCustomerPageObject newCustomerPage;
  private EditCustomerPageObject editCustomerPage;
  private NewAccountPageObject newAccountPage;
  private DepositPageObject depositPage;
  
	
  @Parameters("browser")
  @BeforeClass
  public void beforeClass(String browserName) {
	  driver = openMultiBrowser(browserName);
	  email = "automation" + randomNumber() + "@gmail.com"; 
	  loginPage = PageFactoryManager.getLoginPage(driver);
	  customerName = "automation";
	  gender = "f";
	  genderValue = "female";
	  dateOfBirth = "01/01/2000";
	  address = "258 Le Duan";
	  city = "Sai Gon";
	  state = "Thu Duc";
	  pin = "234567";
	  phone = "1234565434";
	  password_ID ="1234561789";
	  dateOfBirthExpected = "2000-01-01";
	  
  }

  @Test
  public void Payment_01_LoginWithRegisteredInfo() {
	  loginPage.inputToUserIDTextBox(Global_Register_New_Account.USER_ID);
	  loginPage.inputToPasswordTextBox(Global_Register_New_Account.PASSWORD);
	  homePage = loginPage.clickToLoginButton();
	  Assert.assertTrue(homePage.isHomePageDisplay()); 
  }
  
  @Test
  public void Payment_02_Create_New_Customer_Account() throws InterruptedException {
	  newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");
	  Assert.assertTrue(newCustomerPage.isNewCustomerPageDisplayed());
	  
	  newCustomerPage.inputToDynamicTextbox(driver, "name", customerName);
	  newCustomerPage.clickToDynamicRadioButton(driver, gender);
	  newCustomerPage.inputToDynamicTextbox(driver, "dob", dateOfBirth);
	  newCustomerPage.inputToDynamicTextArea(driver, "addr", address);
	  newCustomerPage.inputToDynamicTextbox(driver, "city", city);
	  newCustomerPage.inputToDynamicTextbox(driver, "state", state);
	  newCustomerPage.inputToDynamicTextbox(driver, "pinno", pin);
	  newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", phone);
	  newCustomerPage.inputToDynamicTextbox(driver, "emailid", email);
	  newCustomerPage.inputToDynamicTextbox(driver, "password", password_ID);
	  Thread.sleep(2000);
	  newCustomerPage.clickToDynamicButton(driver, "sub");
	  
	  verifyTrue(newCustomerPage.isDynamicPageOrMessageDisplayed(driver, "Customer Registered Successfully!!!"));
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Gender"), genderValue);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Birthdate"), dateOfBirthExpected);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Address"), address);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "City"), city);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "State"), state);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Pin"), pin);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Mobile No."), phone);
	  verifyEquals(newCustomerPage.getDynamicTextInTable(driver, "Email"), email);
	  
	  customerID = newCustomerPage.getDynamicTextInTable(driver, "Customer ID");
	  Thread.sleep(2000);
  
  }
  
  @Test
  public void Payment_03_Edit_Customer_Account() throws InterruptedException {
	  
	 String editAddress = "530 Hau Giang";
	 String editCity = "Ho Chi Minh";
	 String editState = "Quan Sau";
	 String editPin = "100001";
	 String editPhone = "1234560004";
	 String editEmail = "automation007@gmail.com";
	  
	  editCustomerPage = (EditCustomerPageObject) newCustomerPage.openDynamicPage(driver, "Edit Customer");
	  Thread.sleep(2000);
	  verifyTrue(editCustomerPage.isDynamicPageOrMessageDisplayed(driver, "Edit Customer Form"));
	  editCustomerPage.inputToDynamicTextbox(driver, "cusid", customerID);
	  editCustomerPage.clickToDynamicButton(driver, "AccSubmit");
	  verifyTrue(editCustomerPage.isDynamicPageOrMessageDisplayed(driver, "Edit Customer"));
	  
	  editCustomerPage.inputToDynamicTextArea(driver, "addr", editAddress);
	  editCustomerPage.inputToDynamicTextbox(driver, "city", editCity);
	  editCustomerPage.inputToDynamicTextbox(driver, "state", editState);
	  editCustomerPage.inputToDynamicTextbox(driver, "pinno", editPin);
	  editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", editPhone);
	  editCustomerPage.inputToDynamicTextbox(driver, "emailid", editEmail);
	  Thread.sleep(2000);
	  editCustomerPage.clickToDynamicButton(driver, "sub");
	  
	  verifyTrue(editCustomerPage.isDynamicPageOrMessageDisplayed(driver, "Customer details updated Successfully!!!"));
	  Thread.sleep(2000);
	  verifyEquals(editCustomerPage.getDynamicTextInTable(driver, "Address"), editAddress);
	  verifyEquals(editCustomerPage.getDynamicTextInTable(driver, "City"), editCity);
	  verifyEquals(editCustomerPage.getDynamicTextInTable(driver, "State"), editState);
	  verifyEquals(editCustomerPage.getDynamicTextInTable(driver, "Pin"), editPin);
	  verifyEquals(editCustomerPage.getDynamicTextInTable(driver, "Mobile No."), editPhone);
	  verifyEquals(editCustomerPage.getDynamicTextInTable(driver, "Email"), editEmail);
  }
  
  @Test
  public void Payment_04_Add_New_Account() throws InterruptedException {
	  // Chuyển trang từ Edit Customer đến trang New Account
	  newAccountPage = (NewAccountPageObject) editCustomerPage.openDynamicPage(driver, "New Account");
	  verifyTrue(newAccountPage.isDynamicPageOrMessageDisplayed(driver, "Add new account form"));
	 
	  // Tạo New Account 
	  newAccountPage.inputToDynamicTextbox(driver, "cusid", customerID);
	  newAccountPage.selectOnDropDown("Current");
	  newAccountPage.inputToDynamicTextbox(driver, "inideposit", NewAccountPageUI.INIDEPOSIT_VALUE);
	  Thread.sleep(2000);
	  newAccountPage.clickToDynamicButton(driver, "button2");
	  
	  //Xác định tạo New Account thành công
	  verifyTrue(newAccountPage.isDynamicPageOrMessageDisplayed(driver, "Account Generated Successfully!!!"));
	  Thread.sleep(2000);
	  
	  //Get về Current Amount
	  currentAmount = Integer.parseInt(newAccountPage.getDynamicTextInTable(driver, "Current Amount"));
	  System.out.println("Current Amount is: " + currentAmount);
	  
	  // So sánh giá trị của Initial Deposit và Current Amount
	  newAccountPage.compareAmount(currentAmount, NewAccountPageUI.INIDEPOSIT_VALUE);
	  accountID = newAccountPage.getDynamicTextInTable(driver, "Account ID");
  
  }
  
  @Test
  public void Payment_05_Transfer_On_Deposit_Page() throws InterruptedException{
	  // Chuyển trang từ New Account sang Deposit Page
	  depositPage = (DepositPageObject) newAccountPage.openDynamicPage(driver, "Deposit");
	  verifyTrue(depositPage.isDynamicPageOrMessageDisplayed(driver, "Amount Deposit Form"));
	  
	  // Nhập Amount Value để transfer
	  depositPage.inputToDynamicTextbox(driver, "accountno", accountID);
	  depositPage.inputToDynamicTextbox(driver, "ammount", DepositPageUI.AMOUNT_VALUE);
	  depositPage.inputToDynamicTextbox(driver, "desc", DepositPageUI.DESCRIPTION_VALUE);
	  Thread.sleep(2000);
	  depositPage.clickToDynamicButton(driver, "AccSubmit");
	  
	  // Xác nhận transfer thành công
	  verifyTrue(depositPage.isTransactionPageDisplay());
	  Thread.sleep(2000);
	  
	  // Kiểm tra Current Balance
	 currentBalanceActual = Integer.parseInt(depositPage.getDynamicTextInTable(driver, "Current Balance"));
	 depositPage.compareAmount(DepositPageUI.AMOUNT_VALUE, NewAccountPageUI.INIDEPOSIT_VALUE, currentBalanceActual);
  }
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

}
