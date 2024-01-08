package main.EDRM.hybridFramework.pageObject.Admin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class Barcode extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			Logger log = LoggerHelper.getLogger(Barcode.class);
	
														//created by sagar on 24.07.2023
			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']")
			public WebElement documentTypeDD;													//created by sagar on 24.07.2023
			
//            @FindAll({
//            	@FindBy(how = How.XPATH,using="//*[@id='MainSection']//input[@aria-activedescendant='bs-select-4-0']"),
//            	@FindBy(how = How.XPATH,using="//*[@id='MainSection']//input[@aria-autocomplete='list']")
//            })
//            public WebElement documentTypeTxt;													//created by sagar on 24.07.2023	
			

            @FindBy(how = How.XPATH,using="//button[@data-id='Folder']")
            public WebElement folderDD;    														//created by sagar on 24.07.2023	        
			
//            @FindAll({
//            	@FindBy(how = How.XPATH,using="(//*[@id='MainSection']//input[@aria-autocomplete='list'])[2]"),
//            	@FindBy(how = How.XPATH,using="//input[@aria-activedescendant='bs-select-5-2']")
//            })
//            public WebElement folderDDTxt;     													//created by sagar on 24.07.2023	
            
            @FindBy(how=How.XPATH,using="//input[@id='Page']")
			public WebElement locateOnPageTxt;													//created by sagar on 24.07.2023

//            @FindBy(how=How.XPATH,using="//table[@id='documentTypeGroupDataTable']//td")		//created by sagar on 24.07.2023
//			public WebElement documentTypeGroupDataTableEmpty;    
//            
//            @FindBy(how=How.XPATH,using="//table[@id='barcodeTable']//td")						//created by sagar on 24.07.2023
//			public WebElement barcodeTableEmpty;

            
	public Barcode(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
	}

	//created by sagar on 24.07.2023
	public void selectFolder(String folderName) {
//		click(folderDD, "Clicking on Drop down button");
//		sendKeysWithoutClear(folderDDTxt, folderName, "Entering folder name");
//		new ActionHelper(driver).pressEnter();	
		
		new DropdownHelper(driver).selectFromAutocompleDD(folderName, folderDD);
	}
	
	//created by sagar on 24.07.2023
	public void selectDocumentType(String groupName,String getEnvVariable) {
//		click(documentTypeDD, "Clicking on Drop down button");
//		sendKeysWithoutClear(documentTypeTxt, groupName, "Entering document type");
//		new ActionHelper(driver).pressEnter();
		new CapturePage(driver).selectDocumentTypemDD(groupName, getEnvVariable);	
	} 
	
	//created by sagar on 24.07.2023
	public void AddBarcode(String typeId, String groupName, String folderName, String getEnvVariable) {
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Barcode configuration", "Document");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Add a new barcode", "Actions");
		selectDocumentType(groupName, getEnvVariable);
		selectFolder(folderName);
		navigationMenu.clickOnIcon("Save changes made to barcode", "Actions");
		navigationMenu.waitForAddIcon();				
	}
	
	//created by sagar on 24.07.2023
	public void editBarcode(String typeId, String folderName, String locateOnPage, String getEnvVariable) {
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Barcode configuration", "Document");
		sleepFor(2000);
		navigationMenu.searchInFilter(typeId);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("barcodeTable");
		navigationMenu.clickOnIcon("Edit selected barcode ", "Actions");
		selectFolder(folderName);
		sendKeys(locateOnPageTxt, locateOnPage, "Entering folder name");
		navigationMenu.clickOnIcon("Save changes made to barcode", "Actions");

		//verify barcode is edited
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(typeId);
		sleepFor(2000);
		String filteredText = navigationMenu.getColumnValueFromTable("Document Type");			
		AssertionHelper.verifyText(filteredText, typeId);	
		String filteredText1 = navigationMenu.getColumnValueFromTable("Page");			
		AssertionHelper.verifyText(filteredText1, locateOnPage);					
	}
	
	//created by sagar on 24.07.2023
	public void deleteBarcode(String typeId) {

		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Barcode configuration", "Document");
		sleepFor(2000);
		navigationMenu.searchInFilter(typeId);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("barcodeTable");
		navigationMenu.clickOnIcon("Delete selected barcode", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);		
	}
}
