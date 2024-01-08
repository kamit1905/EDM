package main.EDRM.hybridFramework.pageObject.Admin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class Group extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			AdminDocumentSection adminDoc;

			Logger log = LoggerHelper.getLogger(Group.class);

			@FindBy(how=How.XPATH,using="//input[@id='GroupName']")
			public WebElement groupNameTxt;								//created by sagar on 24.07.2023		
			
//        	xpaths on Users Home Screen
        	@FindBy(how=How.XPATH,using="//button[@data-button='Add']")
        	public WebElement addBtn;									//created by sagar on 19.07.2023
        	
//        	xpaths on General tab
        	@FindBy(how=How.XPATH,using="//input[@id='DomainName']")
        	public WebElement addDomainName;									//created by sagar on 19.07.2023
  
        	//xpaths for document type group
			@FindBy(how=How.XPATH,using="//button[@data-id='GroupId']")
			public WebElement ddGroup;															//created by sagar on 24.07.2023

			@FindBy(how=How.XPATH,using="//table[@id='documentTypesTable']//td")
			public WebElement documentTypesTableEmpty;											//created by sagar on 24.07.2023

			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']")
			public WebElement documentTypeDD;													//created by sagar on 24.07.2023
														//created by sagar on 24.07.2023	

        @FindBy(how=How.XPATH,using="//table[@id='documentTypeGroupDataTable']//td")		//created by sagar on 24.07.2023
		public WebElement documentTypeGroupDataTableEmpty;           

 
        //created by sagar on 24.07.2023          
	public Group(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDoc = new AdminDocumentSection(driver); 
		navigationMenu = new NavigationMenu(driver);
	}
	
	//created by sagar on 24.07.2023
	public void AddDocumentTypeGroup(String groupName) {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Document Type Group", "Document");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Add a new document type group", "Actions");
		sendKeys(groupNameTxt,groupName,"Entering group name as "+groupName);
		navigationMenu.clickOnIcon("Save changes made to doctype group", "Actions");
		navigationMenu.waitForAddIcon();
	}
	
	//created by sagar on 24.07.2023
	public void EditDocumentTypeGroup(String groupName, String editedGroupName) {
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Document Type Group", "Document");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(groupName);
		navigationMenu.clickOnFilteredRow("documentTypeGroupDataTable");
		navigationMenu.clickOnIcon("Edit selected document type group", "Actions");
		sleepFor(2000);
//		navigationMenu.waitForIcon("Cancel changes");
		sendKeys(groupNameTxt,editedGroupName,"Entering group name as "+editedGroupName);
		navigationMenu.clickOnIcon("Save changes made to doctype group", "Actions");
		navigationMenu.waitForAddIcon();
	}
	
	//created by sagar on 24.07.2023
	public void DeleteGroup (String groupName, String getEnvVariable) {
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Document Type Group", "Document");
		navigationMenu.waitForAddIcon();
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if (!status) {
			sleepFor(1000);
			refreshPage();
			sleepFor(1000);
		}
		navigationMenu.searchInFilter(groupName);
		sleepFor(1000);		    		
		navigationMenu.clickOnFilteredRow("documentTypeGroupDataTable");
		navigationMenu.clickOnIcon("Delete selected document type group", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);		    		
	}	
}
