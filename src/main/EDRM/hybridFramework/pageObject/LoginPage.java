package main.EDRM.hybridFramework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;

public class LoginPage extends TestBase{
	 
	 private WebDriver driver;
	 WaitHelper waitHelper;

	 
	 @FindBy(how=How.ID,using="UserName")	 
	 public WebElement usernameTxt;
	 
	 @FindBy(how=How.ID,using="Password")	 
	 public WebElement passwordTxt;
	 
	 @FindBy(how=How.ID,using="LoginButton")	 
	 public WebElement loginBtn;
	 
	 @FindBy(how=How.XPATH,using="//h3[@id='dialogTitle' and text()='Sign in details']")	 
	 public WebElement signInDetailsHeading;
	 
	 @FindBy(how=How.XPATH,using="//div[@class='user']/h1")	
	 public WebElement welcomeTitleText;
			
	 @FindBy(how=How.XPATH,using="//div[@class='user']/h2")	
	 public WebElement userTitleText;
	 
	 @FindBy(how = How.XPATH,using = "//div[@class='container-fluid']//p[text()='Change Password']")
	 public WebElement changePasswordHeader;
	 
	 @FindBy(how = How.XPATH,using = "//input[@id='CurrentPassword']")
	 public WebElement currentPasswordInput;
	 
	 @FindBy(how = How.XPATH,using = "//input[@id='NewPassword']")
	 public WebElement newPasswordInput;
	 
	 @FindBy(how = How.XPATH,using = "//input[@id='ConfirmPassword']")
	 public WebElement confirmPasswordInput;
	 
	 @FindBy(how = How.XPATH,using = "//button[text()='Change Password']")
	 public WebElement changePasswordBtn;
	 
	 @FindBy(how = How.XPATH,using = "//div[@class='alert alert-warning align-center']")
	 public WebElement loginErrorMessage;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Login Page Object Created");
	}
	
	public HomePage loginWithCredentials(String username, String password) throws InterruptedException {
	waitHelper.waitForElement(usernameTxt,ObjectReader.reader.getExplicitWait());
	sendKeys(usernameTxt,username,"Entering username on login page as "+username);
	sendKeys(passwordTxt,password,"Entering password on login page as "+password);
	click(loginBtn,"Clicking on login button");
	Thread.sleep(1000);
	if(new VerificationHelper(driver).isElementDisplayedByEle(signInDetailsHeading)) {
		new WindowHelper(driver).clickOkOnPopup();
	}
	
	//waitHelper.waitForElementVisible(welcomeTitleText, 30, 1);
	//return new HomePage(driver);
	
	boolean welcomeText = new VerificationHelper(driver).isElementDisplayedByEle(welcomeTitleText);
	if(welcomeText) {
		waitHelper.waitForElementVisible(welcomeTitleText, 30, 1);
		return new HomePage(driver);
	}else {
		driver.navigate().back();
		waitHelper.waitForElement(usernameTxt,ObjectReader.reader.getExplicitWait());
		sendKeys(usernameTxt,username,"Entering username on login page as "+username);
		sendKeys(passwordTxt,password,"Entering password on login page as "+password);
		click(loginBtn,"Clicking on login button");
		waitHelper.waitForElementVisible(welcomeTitleText, 30, 1);
		return new HomePage(driver);
	}
	
	}
	
	public String getWelcomeText() {
		return welcomeTitleText.getText();
	}


	public String getUsernameText() {
		return (userTitleText.getText().split(" "))[0];
	}
	
	
	public void loginWithCredentialsForChangePassword(String username, String password) throws InterruptedException{
		waitHelper.waitForElement(usernameTxt,ObjectReader.reader.getExplicitWait());
		sendKeys(usernameTxt,username,"Entering username on login page as "+username);
		sendKeys(passwordTxt,password,"Entering password on login page as "+password);
		click(loginBtn,"Clicking on login button");
		Thread.sleep(1000);
		
		new WaitHelper(driver).waitForElement(changePasswordHeader, 20);
		sendKeys(currentPasswordInput, password, "Entering current password as "+password);
		sendKeys(newPasswordInput, ObjectReader.reader.setUserPassword(), "Entering new password as ");
		sendKeys(confirmPasswordInput, ObjectReader.reader.setUserPassword(), "Entering confirm password as ");
		sleepFor(1000);
		click(changePasswordBtn, "Clicking on change password button");
		
		new WindowHelper(driver).waitForPopup("Password Changed");
		new WindowHelper(driver).clickOkOnPopup();
	}

	
}
