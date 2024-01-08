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

public class EmailConnect extends TestBase {
	
	private WebDriver driver;
	NavigationMenu navigationMenu;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	CapturePage capture;
	Logger log = LoggerHelper.getLogger(EmailConnect.class);
	
	
	@FindBy(how = How.XPATH,using = "//input[@id='Listener_Id']")
	public WebElement listenerNameInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='Listener_Capture']")
	public WebElement listenerCaptureDD;
	
	@FindBy(how = How.XPATH,using = "//input[@id='Listener_Folder']")
	public WebElement listenerFolderInput;
	
	@FindBy(how = How.XPATH,using = "//input[@id='Listener_IndexedFolder']")
	public WebElement listenerIndexFolderInput;
	
	@FindBy(how = How.XPATH,using = "//input[@id='Listener_FailedToIndexFolder']")
	public WebElement listenerFailedIndexFolderInput;
	
	@FindBy(how = How.XPATH,using = "//p[text()='Add Microsoft365 Listener']")
	public WebElement listenerPageHeader;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='Fields']")
	public WebElement listenersFieldsDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='Filters']")
	public WebElement listenersFileterDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='DocumentTypeId']")
	public WebElement documentTypeDD;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FileSystemId']")
	public WebElement fileSystemDD;
	
	@FindBy(how = How.XPATH,using = "//input[@id='FreeText']")
	public WebElement freeTextInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FieldExpression']")
	public WebElement fieldExpressionsDD;
	
	@FindBy(how = How.XPATH,using = "//input[contains(@data-bind,'Listener.UnreadOnly')]")
	public WebElement listenerUnreadOnlyCheckbox;
	
	@FindBy(how = How.XPATH,using = "//input[contains(@data-bind,'Listener.MarkAsRead')]")
	public WebElement listenerMarkAsReadOnlyCheckbox;
	
	@FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Add Field']")
	public WebElement addFieldButton;
	
	
	
	
	public EmailConnect(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		capture= new CapturePage(driver);
		alertHelper= new AlertHelper(driver);
		windowHelper=new WindowHelper(driver);
		waitHelper=new WaitHelper(driver);
	}
	
	
	public void EnterListenerName(String lisName) {
		sendKeys(listenerNameInput, lisName, "Entering listener name "+lisName);
	}
	
	public void SelectListenerCaptureDropdown(String capName) {
		new DropdownHelper(driver).selectFromAutocompleDD(capName, listenerCaptureDD);
	}
	
	public void EnterListenerFolderInput(String folderName) {
		sendKeys(listenerFolderInput, folderName, "Entering folder name "+folderName);
	}
	
	public void EnterIndexedFolderRulesInput(String indexFolInput) {
		sendKeys(listenerIndexFolderInput, indexFolInput, "Entering index folder input "+indexFolInput);
	}
	
	
	public void EnterFailedIndexedFolderRulesInput(String failedIndexFolInput) {
		sendKeys(listenerFailedIndexFolderInput, failedIndexFolInput, "Entering failed index folder input "+failedIndexFolInput);
	}
	
	public void SelectUnReadCheckbox() {
		click(listenerUnreadOnlyCheckbox, "Clicking on unread checkbox");
	}
	
	public void SelectMarkAsReadCheckbox() {
		click(listenerMarkAsReadOnlyCheckbox, "Clicking on Markasreadonly checkbox");
	}
	
	
	public void AddFieldsInListener() {
		navigationMenu.clickOnIcon("Add Field", "Field Actions");
		//click(addFieldButton, "Clicking on add Field button");
		new WindowHelper(driver).waitForModalDialog("Equals Field");
		
	}
	
	public void SelectFieldTypeInListener(String fieldType,String listenerFilter,String fieldTypeValue,String freeTxt,String fieldExpression) {
		new EmailConnect(driver).AddFieldsInListener();
		if(fieldType.equals("DocumentTypeId") || fieldType.equals("FileSystemId")) {
			new DropdownHelper(driver).selectFromAutocompleDD(fieldType, listenersFieldsDD);
			sleepFor(2000);
			new DropdownHelper(driver).selectFromAutocompleDD(listenerFilter, listenersFileterDD);
			
			if(fieldType.equals("DocumentTypeId")) {
				new DropdownHelper(driver).selectFromAutocompleDD(fieldTypeValue, documentTypeDD);
				sleepFor(1000);
				new WindowHelper(driver).clickOnModalFooterButton("Ok");
				sleepFor(2000);
			}else if(fieldType.equals("FileSystemId")) {
				new DropdownHelper(driver).selectFromAutocompleDD(fieldTypeValue, fileSystemDD);
				sleepFor(1000);
				new WindowHelper(driver).clickOnModalFooterButton("Ok");
				sleepFor(2000);
			}
		}else if(fieldType.equals("DocRef")) {
			new DropdownHelper(driver).selectFromAutocompleDD(fieldType, listenersFieldsDD);
			sleepFor(2000);
			new DropdownHelper(driver).selectFromAutocompleDD(listenerFilter, listenersFileterDD);
			sleepFor(1000);
			sendKeys(freeTextInput, freeTxt, "Entering input "+freeTxt);
			sleepFor(1000);
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			sleepFor(2000);
		}else if(fieldType.equals("Folder2Ref")) {
			new DropdownHelper(driver).selectFromAutocompleDD(fieldType, listenersFieldsDD);
			sleepFor(2000);
			new DropdownHelper(driver).selectFromAutocompleDD(listenerFilter, listenersFileterDD);
			sleepFor(1000);
			new DropdownHelper(driver).selectFromAutocompleDD(fieldExpression, fieldExpressionsDD);
			sleepFor(1000);
			sendKeys(freeTextInput, freeTxt, "Entering input "+freeTxt);
			sleepFor(1000);
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			sleepFor(2000);
		}
	}
	

}
