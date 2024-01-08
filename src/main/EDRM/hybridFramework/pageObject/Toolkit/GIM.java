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
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class GIM extends TestBase {
	private WebDriver driver;
	NavigationMenu navigationMenu;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	CapturePage capture;
	Logger log = LoggerHelper.getLogger(GIM.class);
	
	@FindBy(how = How.XPATH,using = "//input[@id='Name']")
	public WebElement jobNameInput;
	
	@FindBy(how = How.XPATH,using = "//input[@id='IsEnabled']")
	public WebElement isEnabledChkBox;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FolderId']")
	public WebElement folderDD;
	
	@FindBy(how = How.XPATH,using = "//input[@id='ControlFileMask']")
	public WebElement controlFileMaskInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='PreProcess']")
	public WebElement preProcessDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='EntityOption']")
	public WebElement entityOptionDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='JobDocumentType']")
	public WebElement jobDocumentTypeDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='EntityName']")
	public WebElement entityMappingDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FieldName']")
	public WebElement fieldMappingDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FieldValues']")
	public WebElement columnValueDD;
	
	@FindBy(how = How.XPATH,using = "//a[@data-title='Add mapping']")
	public WebElement addMappingBtn;
	
	
	public GIM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		capture= new CapturePage(driver);
		alertHelper= new AlertHelper(driver);
		windowHelper=new WindowHelper(driver);
		waitHelper=new WaitHelper(driver);
	} 
	
	
	public void EnterJobName(String jobName) {
		sendKeys(jobNameInput, jobName, "entering job name as "+jobName);
	}
	
	public void ClickOnIsEnabledCheckbox() {
		click(isEnabledChkBox, "Clicking on isenabled checkbox");
	}
	
	public void SelectFolder(String folderName) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(folderName, folderDD);
	}
	
	public void EnterControlMaskFile(String maskFileType) {
		sendKeys(controlFileMaskInput, maskFileType, "entering mask type as "+maskFileType);
	}
	
	public void SelectPreProcess(String preProcess) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(preProcess, preProcessDD);
	}
	
	public void SelectEntityOptionDD(String entityOption) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(entityOption, entityOptionDD);
	}
	
	public void SelectJobDocumentTypeDD(String docType) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(docType, jobDocumentTypeDD);
	}
	
	public void EnterFieldMappingInJob(String entityMapping,String fieldMapping,String colNumber) {
		sleepFor(2000);
		new DropdownHelper(driver).selectFromAutocompleDD(entityMapping, entityMappingDD);
		
		sleepFor(1000);
		new DropdownHelper(driver).selectFromAutocompleDD(fieldMapping, fieldMappingDD);
		
		sleepFor(1000);
		new DropdownHelper(driver).selectFromAutocompleDD(colNumber, columnValueDD);
		
		sleepFor(1000);
		click(addMappingBtn, "Clicking on add mapping button");
	}

}
