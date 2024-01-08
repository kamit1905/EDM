package main.EDRM.hybridFramework.pageObject.Toolkit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class RetentionAndDisposal extends TestBase {
	
	private WebDriver driver;
	NavigationMenu navigationMenu;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	CapturePage capture;
	Logger log = LoggerHelper.getLogger(GIM.class);
	
	@FindBy(how = How.XPATH,using = "//input[@id='Name']")
	public WebElement policyNameInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='PeriodId']")
	public WebElement periodIdDD;
	
	@FindBy(how = How.XPATH,using = "//input[@name='PeriodNumber']")
	public WebElement periodNumberInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FieldId']")
	public WebElement fieldIdDD;
	
	@FindBy(how = How.XPATH,using = "//input[@id='DeleteEverything']")
	public WebElement deleteEveryThingChk;

	@FindBy(how = How.XPATH,using = "//input[@id='DeleteDocumentEntry']")
	public WebElement deleteDocumentEntryChk;
	
	@FindBy(how = How.XPATH,using = "//input[@id='DeleteRecordEntry']")
	public WebElement deleteRecordEntryChk;
	
	@FindBy(how = How.XPATH,using = "//input[@id='DeletePhysicalFile']")
	public WebElement deletePhysicalFileChk;
	
	@FindBy(how = How.XPATH,using = "//input[@id='DeleteEntireAuditTrail']")
	public WebElement deleteEntireAuditTrailChk;
	
	@FindBy(how = How.XPATH,using = "//input[@id='DeleteAuditTrailExceptLastEntry']")
	public WebElement deleteEntireAuditTrailExceptLastEntryChk;
	
	@FindBy(how = How.XPATH,using = "//input[@name='ConfirmChanges']")
	public WebElement confirmChangesCheckbox;
	
	@FindBy(how = How.XPATH,using = "//p[text()='Document Type Assignment']")
	public WebElement documentTypeHeader;
	
	@FindBy(how = How.XPATH,using = "//button[contains(@data-id,'RetentionPolicies')]")
	public WebElement policyAssignDD;
	
	@FindBy(how = How.XPATH,using = "//input[contains(@class,'ApplyDocumentTypePolicy')]")
	public WebElement applyToDocumentChk;
	
	@FindBy(how = How.XPATH,using = "//span[text()='Turn off auditing during the update']/ancestor::div[1]//input[@id='DisableAuditing']")
	public WebElement turnOffAuditCheckbox;
	
	@FindBy(how = How.XPATH,using = "//span[text()='Delay the update']/ancestor::div[1]//input[@id='DelayUpdate']")
	public WebElement delayUpdateCheckbox;
	
	public RetentionAndDisposal(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		capture= new CapturePage(driver);
		alertHelper= new AlertHelper(driver);
		windowHelper=new WindowHelper(driver);
		waitHelper=new WaitHelper(driver);
	}
	
	
	public void EnterPolicyName(String policy) {
		sendKeys(policyNameInput, policy, "Entering policy name as "+policy);
	}
	
	public void SelectDocumenToBeRetained(String period) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(period, periodIdDD);
	}
	
	public void EnterPeriodInput(String perInput) {
		sendKeys(periodNumberInput, perInput, "Entering period number as"+perInput);
	}
	
	public void SelectFieldIdDD(String field) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(field, fieldIdDD);
	}

	public void DeleteEveryThingCheckbox() {
		click(deleteEveryThingChk, "Clicking on Delete Every Thing Checkbox");
	}
	
	public void DeleteDocumentEntryCheckbox() {
		new JavaScriptHelper(driver).scrollToElementAndClick(deleteDocumentEntryChk);
	}
	
	public void DeleteRecordEntryCheckbox() {
		new JavaScriptHelper(driver).scrollToElementAndClick(deleteRecordEntryChk);
	}
	
	public void DeletePhysicalFileCheckbox() {
		new JavaScriptHelper(driver).scrollToElementAndClick(deletePhysicalFileChk);
	}
	
	public void DeleteEntireAuditTrailCheckbox() {
		new JavaScriptHelper(driver).scrollToElementAndClick(deleteEntireAuditTrailChk);
	}
	
	public void DeleteEntireAuditTrailExceptLastEntry() {
		new JavaScriptHelper(driver).scrollToElementAndClick(deleteEntireAuditTrailExceptLastEntryChk);
	}
	
	public void SeletRententionPolicyForDocumentType(String docType) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(docType, policyAssignDD);
		sleepFor(1000);
		click(applyToDocumentChk, "Clicking to apply to document checkbox");
	}
}
