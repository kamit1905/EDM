package main.EDRM.hybridFramework.pageObject.Toolkit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;

public class ToolkitEmailTemplate extends TestBase {
	NavigationMenu navigationMenu;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	CapturePage capture;
	Logger log = LoggerHelper.getLogger(ToolkitEmailTemplate.class);
	
	
	
	@FindBy(how = How.XPATH,using = "//input[@id='Name']")
	public WebElement emailTempNameInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='DocumentTypeId']")
	public WebElement documentTypeDD;
	
	@FindBy(how = How.XPATH,using = "//input[@id='Subject']")
	public WebElement subjectInput;
	
	@FindBy(how = How.XPATH,using = "//iframe[@id='GenerateEmailBody_ifr']")
	public WebElement emailBodyIframe;
	
	@FindBy(how = How.XPATH,using = "//body[@data-id='GenerateEmailBody']//p")
	public WebElement emailContentInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='DocumentTypes']")
	public WebElement documentTypesDD;
	
	@FindBy(how = How.XPATH,using = "//a[@data-title='Add new document type']")
	public WebElement addDocuTypeBtn;
	
	
	
	private String tmpSelectLbl = "//span[contains(text(),'%s')]/ancestor::button[1]",
			tmpRefLblName = "//div[text()='%s']";
	
	
	public ToolkitEmailTemplate(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		capture= new CapturePage(driver);
		alertHelper= new AlertHelper(driver);
		windowHelper=new WindowHelper(driver);
		waitHelper=new WaitHelper(driver);
	}

	public void InputEmailTemplateName(String emailTempName) {
		sendKeys(emailTempNameInput, emailTempName, "Inputting email template name as "+emailTempName);
	}
	
	public void SelectDocumentTypeFromDD(String docType) {
		new DropdownHelper(driver).selectFromAutocompleDD(docType, documentTypeDD);
	}
	
	public void InputEmailSubject(String subject) {
		sendKeys(subjectInput, subject, "Inputting subject as "+subject);
	}
	
	public void SelectSectionLabel(String lblName) {
		if(!lblName.isEmpty()) {
			String tmpLbl = String.format(tmpSelectLbl, lblName);
			WebElement lblSelectSection = driver.findElement(By.xpath(tmpLbl));
			click(lblSelectSection, "Clicking on select Lbl Section");
		}
	}
	
	public void SelectSpecificRefInSection(String refName) {
		if(!refName.isEmpty()) {
			String tmpSpcRef = String.format(tmpRefLblName, refName);
			WebElement refEle = driver.findElement(By.xpath(tmpSpcRef));
			new ActionHelper(driver).moveToElementAndClick(refEle);
		}
	}
	
	public void EnterEmailTemplateContent(String desc,String sectionName,String refName) {
		new ActionHelper(driver).SwithcToFrame(emailBodyIframe);
		sendKeys(emailContentInput, desc, sectionName);
		new ActionHelper(driver).pressSpace();
		sleepFor(1000);
		new ActionHelper(driver).SwitchBackToMainFrameOfPage();
		SelectSectionLabel(sectionName);
		sleepFor(2000);
		SelectSpecificRefInSection(refName);
		sleepFor(1000);
		new ActionHelper(driver).SwithcToFrame(emailBodyIframe);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).SwitchBackToMainFrameOfPage();
	}
	
	public void SelectDocumentType(String docType) {
		new DropdownHelper(driver).selectFromAutocompleDD(docType, documentTypesDD);
		sleepFor(1000);
		click(addDocuTypeBtn, "Clicking on add doc type button");
	}
}
