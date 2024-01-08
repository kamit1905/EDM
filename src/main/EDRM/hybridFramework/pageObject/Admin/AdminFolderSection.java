package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class AdminFolderSection extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			ActionHelper actionHelper;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			Logger log = LoggerHelper.getLogger(AdminFolderSection.class);
	
			
			@FindBy(how=How.XPATH,using="//button[@data-id='ImportableEntities']")
			public WebElement ddBtnEntityDataImport;
			
			@FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			public WebElement ddBtnEntityDataImport551OnWards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='selectedFileType']")
			public WebElement ddBtnFileFormatDataImport;
			
			@FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			public WebElement ddBtnFileFormatDataImport551OnWards;
			
			@FindBy(how=How.XPATH,using="//input[@id='filePath']")
			public WebElement txtFilePathDataImport;
			
			@FindBy(how=How.ID,using="fieldSeparationChar")
			public WebElement txtFieldSeparationDataImport;
			
			@FindBy(how=How.XPATH,using="//span[contains(@data-bind,'jobIndex')]")
			public WebElement lblJobIndexDataImport;
			
			@FindBy(how = How.XPATH,using = "//p[@id='dialogContent']//label[contains(text(),'Number of successes')]/ancestor::div[1]//span[@data-bind='text:importSuccessCount']")
			public WebElement lblNumberOfSuccess;
		
			@FindBy(how=How.XPATH,using="//input[@id='FolderRef']")
			public WebElement folderRefInput;
			
			@FindBy(how=How.XPATH,using="//input[@id='Name']")
			public WebElement groupNameInput;
			
			@FindBy(how=How.XPATH,using="//input[@id='Folder1Ref']")
			public WebElement folder1RefInput;
			
			@FindBy(how=How.XPATH,using="//a[@data-title='Add Folder']")
			public WebElement addFolder1ToGroupBtn;
			
			@FindBy(how = How.XPATH,using = "//label[text()='Property Ref']/../div/div/a[contains(@data-title,'Search for')]")
			public WebElement folder3LookupBtn;
			
			@FindBy(how = How.XPATH,using = "//label[text()='Account Ref']/../div/div/a[contains(@data-title,'Search for')]")
			public WebElement folder1LookupBtn;
			
			@FindBy(how = How.XPATH,using = "//label[text()='Account Ref']/ancestor::div[1]//a[contains(@data-bs-original-title,'Edit')]")
			public WebElement folder1RefEditBtn;

			@FindBy(how = How.XPATH,using = "//label[text()='Property Ref']/ancestor::div[1]//a[contains(@data-bs-original-title,'Edit')]")
			public WebElement folder3RefEditBtn;
			
			@FindBy(how = How.XPATH,using = "//h3[text()='Data Download Import']/ancestor::div[2]//a[text()='Download']")
			public WebElement downloadBtn;
		
			
			private String folderRefNavBarTitle = "//p[text()='Search for %s']";
			
			String tmpFieldMappingDDButton ="(//fieldset[@id='fieldMappingContainer']//button)[%s]",
					tmpFieldMappingTxtLength ="(//fieldset[@id='fieldMappingContainer']//input[@name='fieldLength'])[%s]",
					tmpFoldeRefHeader="//p[text()='Add %s']";
			
			String tmpAccountSecurity = "//a[text()='%s Security']";
			String tmpDeleteFolderRefSecurity = "//a[text()='%s']/ancestor::tr[1]//a[@data-icon='Delete']";
			
	public AdminFolderSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		actionHelper = new ActionHelper(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
	}
	
	/**
	 * Navigate to data import
	 */
	public void navigateToDataImport() {
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Data Download maintenance", "Folder");
		waitHelper.waitForElementClickable(driver, ddBtnEntityDataImport, 20);
	}
	
	/**
	 * Select entity dropdown for data import 
	 * @param entity dropdown value
	 */
	public void selectEntityDropdownDataImport(String entity,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddBtnEntityDataImport, "Clicking on entity drop down button");
			dropdownHelper.selectFromAutocompleDDWithoutInput(entity, ddBtnEntityDataImport551OnWards,getEnvVariable);
		}else {
			dropdownHelper.selectFromAutocompleDDWithoutInput(entity, ddBtnEntityDataImport);
		}
	}
	
	/**
	 * Enter value in file parth for data import
	 * @param filePath complete file path for data import
	 */
	public void enterValueInFilePathDataImport(String filePath) {
		sendKeys(txtFilePathDataImport, filePath, "Entering file path as "+filePath);
		actionHelper.pressTab();
		waitHelper.waitForElementClickable(driver, ddBtnFileFormatDataImport, 30);
	}
	
	/**
	 * File type for data import
	 * @param fileType e.g csv
	 */
	public void selectFileTypeDataImport(String fileType,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddBtnFileFormatDataImport, "Clicking on File Format Button");
			dropdownHelper.selectFromAutocompleDDWithoutInput(fileType, ddBtnFileFormatDataImport,getEnvVariable);
		}else {
			dropdownHelper.selectFromAutocompleDDWithoutInput(fileType, ddBtnFileFormatDataImport);
		}
	}
	
	/**
	 * Enter field separation for data import
	 * @param fieldSeparation e.g comma, |
	 */
	public void enterFieldSeparationDataImport(String fieldSeparation) {
		sendKeys(txtFieldSeparationDataImport, fieldSeparation, "Entering field separator as "+fieldSeparation);
	}
	
	/**
	 * Select fields from data import 
	 * @param fields e.g "Folder1_Ref","Surname","Forename"
	 * @throws InterruptedException
	 */
	public void selectFieldsFromDataImport(String... fields) throws InterruptedException {
		for(int i=0;i<fields.length-1;i++) {
		WebElement ddBtnEle = driver.findElement(By.xpath(String.format(tmpFieldMappingDDButton, i+1)));
		sleepFor(2000);
		dropdownHelper.selectFromAutocompleDD(fields[i], ddBtnEle);
	}

//		if(fields[3].equals("Enterprise_R551")) {
//			for(int i=0;i<fields.length-1;i++) {
//				WebElement ddBtnEle = driver.findElement(By.xpath(String.format(tmpFieldMappingDDButton, i+1)));
//				click(ddBtnEle, "clicking on drop down");
//				dropdownHelper.selectFromAutocompleDD(fields[i], ddBtnEle);
//			}
//
//		}else {
//			for(int i=0;i<fields.length-1;i++) {
//				WebElement ddBtnEle = driver.findElement(By.xpath(String.format(tmpFieldMappingDDButton, i+1)));
//				dropdownHelper.selectFromAutocompleDD(fields[i], ddBtnEle);
//			}
//		}
	}
	
	/**
	 * Select field with legth from data import
	 * @param fields  list of fields like FirstName,LastName
	 * @param len length of the fields
	 * @throws InterruptedException
	 */
	public void selectFieldsWithLengthFromDataImport(List<String> fields, List<Integer> len) throws InterruptedException {
		for(int i=0;i<fields.size();i++) { 
			WebElement ddBtnEle = driver.findElement(By.xpath(String.format(tmpFieldMappingDDButton, i+1)));
			dropdownHelper.selectFromAutocompleDD(fields.get(i), ddBtnEle);
		}
		
		for(int i=0;i<len.size();i++) { 
			WebElement txtEle = driver.findElement(By.xpath(String.format(tmpFieldMappingTxtLength, i+1)));
			String lenValue = String.valueOf(len.get(i));
			sendKeys(txtEle, lenValue, "Entering length as "+lenValue);
		}
	}
	
	/**
	 * Enter file type and separation folder in data download form
	 * @param fileType e.g csv,xlsx
	 * @param fieldSeparation e.g , |
	 */
	public void enterFileTypeAndSeparation(String fileType, String fieldSeparation,String getEnvVariable) {
			selectFileTypeDataImport(fileType,getEnvVariable);
			if(!fieldSeparation.isEmpty())
				enterFieldSeparationDataImport(fieldSeparation);
	}
	
	/**
	 * Save file schema for data download import
	 * @return data download import popup message
	 */
	public String clickOnSaveFileSchema() {
		navigationMenu.clickOnIcon("Save file schema", "Import");
		windowHelper.waitForPopup("Data Download Import");
		String popupMsg = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		return popupMsg;
	}
	
	/**
	 * Get job index number for data download 
	 * @return job index number
	 */
	public String getJobIndexNumber() {
		return verificationHelper.getText(lblJobIndexDataImport);
	}
	
	public String GetNumberOfSuccessAfterImport() {
		new WaitHelper(driver).waitForElement(lblNumberOfSuccess, 10);
		String getNumberOfSuccess = lblNumberOfSuccess.getText();
		return getNumberOfSuccess;
	}
	
	/**
	 * Run batch file for data download folder 
	 * @param jobIndex
	 * @param fsName
	 */
	public void runBatchFile(String jobIndex, String fsName) {
		String batchPath = ObjectReader.reader.getResourceFolderPath()+"test.bat"+" "+jobIndex+" "+fsName ;
		runExecutable(batchPath);
		
	}
	
	/*
	 * Clicking on import Icon
	 */
	public void ClickOnImport() {
		navigationMenu.clickOnIcon("Run import now", "Import");
		
		String getActualSuccessNumber = GetNumberOfSuccessAfterImport();
		AssertionHelper.verifyText(getActualSuccessNumber, String.valueOf(1));
		
		windowHelper.clickOkOnPopup();
	}
	
	
	public void enterFolderRef(String folderReference) {
		sendKeys(folderRefInput, folderReference, "Inputing the text "+folderReference);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
	}
	
	public void clickOnSaveInFolderRestriction1(String accHeader) {
		try {
			//new ActionHelper(driver).pressTab();
			//new WindowHelper(driver).waitForPopup("Not found");
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		} catch (Exception e) {
			String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "This item does not exist. Do you want to create a new one?");
			 sleepFor(3000);
			 String folderRefHeader=String.format(tmpFoldeRefHeader, accHeader);
			 WebElement folderRefElement=driver.findElement(By.xpath(folderRefHeader));
			 new WaitHelper(driver).waitForElementVisible(folderRefElement, 10, 2);
			 
			 navigationMenu.clickOnSaveIcon();
			 //sleepFor(2000);
			 new WaitHelper(driver).waitForElementVisible(By.xpath("//p[text()='Add Folder1 Security']"), 10, 2);
			 navigationMenu.clickOnSaveIcon();
			 navigationMenu.waitForAddIcon();
		}
		
	}
		
	public void AddNewFolderRefFromGroupRestriction(String accHeader,String environmentVar) {
		try {
			new ActionHelper(driver).pressTab();
			sleepFor(3000);
			new WindowHelper(driver).waitForPopup("Not found");
			
			String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "This item does not exist. Do you want to create a new one?");
			 sleepFor(3000);
			 String folderRefHeader=String.format(tmpFoldeRefHeader, accHeader);
			 WebElement folderRefElement=driver.findElement(By.xpath(folderRefHeader));
			 new WaitHelper(driver).waitForElementVisible(folderRefElement, 10, 2);
			 
			 navigationMenu.clickOnSaveIcon();
			 sleepFor(2000);
			 new WaitHelper(driver).waitForElementVisible(By.xpath("//p[contains(text(),'Security Restriction Group')]"), 10, 2);				//changed
			 new ActionHelper(driver).pressTab();
			 sleepFor(3000);
			 click(addFolder1ToGroupBtn, "Clicking on folder1 group btn");
		} catch (Exception e) {
			 sleepFor(3000);
			 click(addFolder1ToGroupBtn, "Clicking on folder1 group btn");
		}
	}


	
	public void clickOnSaveInFolderRestriction(String accHeader,String environmentVar) {
			//String getFolderRef = ObjectReader.reader.getFolder1RefName();
			
			if(environmentVar.equals("Enterprise_R531") || environmentVar.equals("Enterprise_R540")) {
				try {
					new ActionHelper(driver).pressTab();
					sleepFor(2000);
					navigationMenu.clickOnSaveIcon();
					navigationMenu.waitForAddIcon();
				} catch (Exception e) {
					String actMessage = windowHelper.getPopupMessage();
					 windowHelper.clickOkOnPopup();
					 AssertionHelper.verifyTextContains(actMessage, "This item does not exist. Do you want to create a new one?");
					 sleepFor(3000);
					 String folderRefHeader=String.format(tmpFoldeRefHeader, accHeader);
					 WebElement folderRefElement=driver.findElement(By.xpath(folderRefHeader));
					 new WaitHelper(driver).waitForElementVisible(folderRefElement, 10, 2);
					 
					 navigationMenu.clickOnSaveIcon();
					 //sleepFor(2000);
					 new WaitHelper(driver).waitForElementVisible(By.xpath("//p[text()='Add "+accHeader+" Security']"), 10, 2);				//changed
					 
					 new ActionHelper(driver).pressTab();
					 sleepFor(5000);
					 navigationMenu.clickOnSaveIcon();	
					 navigationMenu.waitForAddIcon();
				}
			}else {
				try {
					new ActionHelper(driver).pressTab();
					new WindowHelper(driver).waitForPopup("Not found");
					
					String actMessage = windowHelper.getPopupMessage();
					 windowHelper.clickOkOnPopup();
					 AssertionHelper.verifyTextContains(actMessage, "This item does not exist. Do you want to create a new one?");
					 sleepFor(3000);
					 String folderRefHeader=String.format(tmpFoldeRefHeader, accHeader);
					 WebElement folderRefElement=driver.findElement(By.xpath(folderRefHeader));
					 new WaitHelper(driver).waitForElementVisible(folderRefElement, 10, 2);
					 
					 navigationMenu.clickOnSaveIcon();
					 sleepFor(4000);
					 new WaitHelper(driver).waitForElementVisible(By.xpath("//p[text()='Add "+accHeader+" Security']"), 10, 2);				//changed
					 
					 new ActionHelper(driver).pressTab();
					 sleepFor(7000);
					 navigationMenu.clickOnSaveIcon();	
					 navigationMenu.waitForAddIcon();

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
//		try {
//			new ActionHelper(driver).pressTab();
//			sleepFor(4000);
//			new WindowHelper(driver).waitForPopup("Not found");
//			
//			String actMessage = windowHelper.getPopupMessage();
//			 windowHelper.clickOkOnPopup();
//			 AssertionHelper.verifyTextContains(actMessage, "This item does not exist. Do you want to create a new one?");
//			 sleepFor(3000);
//			 String folderRefHeader=String.format(tmpFoldeRefHeader, accHeader);
//			 WebElement folderRefElement=driver.findElement(By.xpath(folderRefHeader));
//			 new WaitHelper(driver).waitForElementVisible(folderRefElement, 10, 2);
//			 
//			 navigationMenu.clickOnSaveIcon();
//			 //sleepFor(2000);
//			 new WaitHelper(driver).waitForElementVisible(By.xpath("//p[text()='Add "+accHeader+" Security']"), 10, 2);				//changed
//			 
//			 new ActionHelper(driver).pressTab();
//			 sleepFor(5000);
//			 navigationMenu.clickOnSaveIcon();	
//			 navigationMenu.waitForAddIcon();
//
//		} catch (Exception e) {
//			navigationMenu.clickOnSaveIcon();
//			navigationMenu.waitForAddIcon();
//		}
	}
	
	
	/**
	 * Navigate to admin account security
	 */
	public void navigateToAdminAccountSecurity() {
		String getFolderRef = ObjectReader.reader.getFolder1RefName();
		navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIconMenu("Select Folder Security", "Folder");
		 navigationMenu.clickOnIconMenu(getFolderRef+" Folder Security maintenance");			//change Folder1 to Account
		 navigationMenu.waitForAddIcon();
	}
	
	/**
	 * Navigate to admin prpperty security
	 */
	public void navigateToAdminPropertySecurity() {
		String getFolderRef = ObjectReader.reader.getFolder3RefName();
		navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIconMenu("Select Folder Security", "Folder");
		 navigationMenu.clickOnIconMenu(getFolderRef+" Folder Security maintenance");			//change Folder3 to Account
		 navigationMenu.waitForAddIcon();
	}

	
	public void enterGroupName(String group) {
		sendKeys(groupNameInput, group, "Entering group name "+group);
	}
	
	public void addFolderRefInGroup(List<String> refList) {
		
		for(int i=0;i<refList.size();i++) {
			sendKeys(folder1RefInput, refList.get(i), "Entering reference "+refList.get(i));
			new ActionHelper(driver).pressTab();
			sleepFor(3000);
			click(addFolder1ToGroupBtn, "Clicking on folder1 group btn");
		}
	}
	
    /*
     * Search and select the folder reference While under folder restriction
     */
    public void SearchAndSelectFolderReference(String entityName,String folderRefValue) {
    	new NavigationMenu(driver).waitForAddIcon();
    	navigationMenu.expandAccordionIfCollapsed("General");
		new CapturePage(driver).enterFolderRefEnityPage(entityName, folderRefValue);
		navigationMenu.clickOnIconUnderAction("Run");
		//sleepFor(3000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 20);
		navigationMenu.clickOnFilteredRow("crudGridResults");
		navigationMenu.clickOnIcon("Select this", "Actions");
		//new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
    }
    
    public boolean AccountSecurityButton(String refName) {
    	String accountSecurityr=String.format(tmpAccountSecurity, refName);
    	//WebElement accountSecurityBtn = driver.findElement(By.xpath(accountSecurityr));
    	boolean btnAccountSecurityStatus = new VerificationHelper(driver).isElementDisplayed(By.xpath(accountSecurityr));
    	return btnAccountSecurityStatus;
    }
    
    public void DeleteFolderRestrictionFromGroup(String folderRef) {
    	String delFolderBtn = String.format(tmpDeleteFolderRefSecurity, folderRef);
    	WebElement deleteBtn = driver.findElement(By.xpath(delFolderBtn));
    	click(deleteBtn, "Clickin on Delete Btn");
    }

}
