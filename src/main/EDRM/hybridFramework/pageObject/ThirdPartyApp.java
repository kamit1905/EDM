package main.EDRM.hybridFramework.pageObject;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class ThirdPartyApp extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			NavigationMenu navigationMenu;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			Logger log = LoggerHelper.getLogger(ThirdPartyApp.class);
	
			
			@FindBy(how=How.XPATH,using="//select[@id='LicenceTypeId']")
			public WebElement ddSelectLiscenseTypeThirdParty;
			
			@FindBy(how=How.XPATH,using="//input[@type='checkbox' and @id='AllFileSystem']")
			public WebElement chkAllFileSystemThirdParty;
			
			@FindBy(how=How.XPATH,using="//button[@id='GenerateAuthenticationToken']")
			public WebElement btnGenerateAuthTokenThirdParty;
			
			@FindBy(how=How.XPATH,using="//button[@id='DisplayMailStatus']")
			public WebElement btnDisplayMailStatusThirdParty;
			
			@FindBy(how=How.XPATH,using="//button[@id='DocumentList']")
			public WebElement btnDocListThirdParty;
			
			@FindBy(how=How.XPATH,using="//button[@id='Capture']")
			public WebElement btnCaptureThirdParty;
			
			@FindBy(how=How.XPATH,using="//button[@id='DisplayAspect']")
			public WebElement btnDisplayAspectThirdParty;
			
			@FindBy(how=How.XPATH,using="//div[@id='MailStatus']/h4")
			public WebElement titleMailStatusListThirdParty;
			
			@FindBy(how=How.XPATH,using="//div/h2[text()='Capture']")
			public WebElement titleHeadingCaptureThirdParty;
			
			@FindBy(how=How.ID,using="capture-btn")
			public WebElement btnProceedCaptureMidFormThirdParty;
			
			
			String tmpInputXpathThirdParty = "//input[@id='%s']",
					tmpButtonXpathThirdParty = "//button[@id='%s']";
		
			
	public ThirdPartyApp(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		log.info("Third party app object is created.");
	}
	
	public void navigateToThirdpartyURL() {
		driver.navigate().to("http://edm-app-2012/ThirdPartyAppR520SP1");
		waitHelper.waitForElement(ddSelectLiscenseTypeThirdParty, 20);
	}
	
	
	public void enterValuesInTokenGenerationFieldsClickOnGenerate(Map<String,String> fields) {
	enterFileSystemThirdPartyApp(fields.get("fileSystem"));	
	enterCredentialThirdPartyApp();
	enterSecurityProfileThirdPartyApp(fields.get("securityProfile"));
	enterSecurityLevelThirdPartyApp(fields.get("securityLevel"));
	enterUsernameImpersonate(fields.get("impersonateUser"));
	selectLiscenseTypeThirdPartyApp();
	enterDurationThirdPartyApp(fields.get("duration"));
	enterFolder1RefThirdPartyApp(fields.get("folder1Ref"));
	click(chkAllFileSystemThirdParty, "Checking all file system checkbox");
	clickOnButtonThirdParty("GenerateAuthenticationToken");
	waitHelper.waitForElementClickable(driver, btnDocListThirdParty, 20);
	}
	
	
	private void clickOnButtonThirdParty(String btnId) {
		WebElement btnEle = driver.findElement(By.xpath(String.format(tmpButtonXpathThirdParty, btnId)));
		click(btnEle, "Clicking on button ele of id"+btnId);
	}
	
	private WebElement getButtonEleThirdParty(String btnId) {
		WebElement btnEle = driver.findElement(By.xpath(String.format(tmpButtonXpathThirdParty, btnId)));
		return btnEle;
	}
	
	
	public WebElement getTxtInputElmenentFromIdThridParty(String id) {
		return driver.findElement(By.xpath(String.format(tmpInputXpathThirdParty, id)));
	}
	
	public void enterFileSystemThirdPartyApp(String fileSystem) {
		sendKeys(getTxtInputElmenentFromIdThridParty("FileSystem"), fileSystem, "Entering file system as "+fileSystem); 
	}
	
	public void enterCredentialThirdPartyApp() {
		sendKeys(getTxtInputElmenentFromIdThridParty("UserName"), ObjectReader.reader.getSuperLoginId(), "Entering username as Imasys"); 
		sendKeys(getTxtInputElmenentFromIdThridParty("Password"), ObjectReader.reader.getSuperUserPassword(), "Entering password of Imasys"); 
	}
	public void enterSecurityProfileThirdPartyApp(String securityProfile) {
		sendKeys(getTxtInputElmenentFromIdThridParty("SecurityProfileName"), securityProfile, "Entering security profile as "+securityProfile); ;
	}
	
	public void enterSecurityLevelThirdPartyApp(String secLevel) {
		sendKeys(getTxtInputElmenentFromIdThridParty("SecurityLevel"), secLevel, "Entering sec level as "+secLevel); ;
	}
	
	public void enterUsernameImpersonate(String userImpersonate) {
		sendKeys(getTxtInputElmenentFromIdThridParty("UserNameToImpersonate"), userImpersonate, "Entering impersonate user as "+userImpersonate); ;
	}
	
	public void selectLiscenseTypeThirdPartyApp() {
		new DropdownHelper(driver).selectFromDropdown(ddSelectLiscenseTypeThirdParty, "ApiLicenceKey");
	}
	
	public void enterDurationThirdPartyApp(String duration) {
		sendKeys(getTxtInputElmenentFromIdThridParty("Duration"), duration, "Entering duration as "+duration); ;
	}
	
	public void enterFolder1RefThirdPartyApp(String foder1Ref) {
		sendKeys(getTxtInputElmenentFromIdThridParty("Folder1Ref"), foder1Ref, "Entering folder 1 ref as "+foder1Ref); ;
	}
	
	public boolean areAllButtonsVisible() {
		boolean btn1 = btnGenerateAuthTokenThirdParty.isDisplayed();
		boolean btn2 = btnCaptureThirdParty.isDisplayed();
		boolean btn3 = btnDisplayMailStatusThirdParty.isDisplayed();
		boolean btn4 = btnDocListThirdParty.isDisplayed();
		boolean btn5 = btnDisplayAspectThirdParty.isDisplayed();
		boolean finalCondn = btn1 && btn2 && btn3 && btn4 && btn5;
		return finalCondn;
	}

	public void clickOnDisplayMailStatusButton() {
		click(btnDisplayMailStatusThirdParty, "Clicking on dispay mail status btn");
		waitHelper.waitForElementVisible(titleMailStatusListThirdParty, 20, 1);
	}

	public boolean isMailStatusListVisible() {
		boolean isMailList = new VerificationHelper(driver).isElementDisplayedByEle(titleMailStatusListThirdParty);
		return isMailList;
	}

	public void clickOnGetDocumentListButton() {
		click(btnDocListThirdParty, "Clicking On btn doc list");
		new WindowHelper(driver).switchToNewlyOpenedTab();
		navigationMenu.waitForFilterTextBox();
	}

	public String getFolder1RefOfDocumentList() {
		String folder1Ref = navigationMenu.getColumnValueFromTable("Account Ref");		
		new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();
		return folder1Ref;
	}

	public void clickOnCaptureDocumentButton() {
		click(btnCaptureThirdParty, "Clicking On capture doc list");
		waitHelper.waitForElement(titleHeadingCaptureThirdParty, 10);
	}
	
	public CapturePage clickOnProceedToCaptureButton() {
		click(btnProceedCaptureMidFormThirdParty, "Clicking on proceed to capture button");
		CapturePage capture = new CapturePage(driver);
		waitHelper.waitForElement(capture.docRefTxt, 20);
		return capture;
	}

	/*
	 * public String fillCaptureFormWithDetails(Map<String, String> fields) {
	 * enterFileSystemThirdPartyApp(fields.get("fileSystem"));
	 * enterFolder1RefThirdPartyApp(fields.get("folder1Ref")); CapturePage capture=
	 * clickOnProceedToCaptureButton(); capture.enterDocRef(fields.get("docRef"));
	 * capture.clickOnFileUploadIcon(); capture. return tmpButtonXpathThirdParty; }
	 */
	
	
	
}
