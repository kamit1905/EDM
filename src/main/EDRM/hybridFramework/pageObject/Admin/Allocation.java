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
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class Allocation extends TestBase{
		
	private WebDriver driver;
	public CapturePage capture;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			Logger log = LoggerHelper.getLogger(Allocation.class);
	
			@FindBy(how=How.XPATH,using="//input[@id='MailRoutingRuleSetName']")
			public WebElement mailRoutingRuleSetNameTxt;
			
			@FindBy(how=How.XPATH,using="//input[@id='MailRoutingRuleSetDescription']")
			public WebElement mailRoutingRuleSetDescriptionTxt;

			@FindBy(how=How.XPATH,using="//button[@id='btnSaveMailRoutingRuleSet']")
			public WebElement btnSaveMailRoutingRuleSet;

			@FindBy(how=How.XPATH,using="//button[@data-button='Rules']")
			public WebElement rulesBtn;
												
			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentType']")	
			public WebElement documentTypeDD;
        
				
			@FindBy(how=How.XPATH,using="//button[@data-id='RoleId']")							//created by sagar on 29.07.2023
			public WebElement roleDD;   
          
			
			@FindBy(how=How.XPATH,using="//input[@id='LowRange']")								//created by sagar on 29.07.2023
			public WebElement rangeFromTxt;   
          
			@FindBy(how=How.XPATH,using="//input[@id='HighRange']")								//created by sagar on 29.07.2023
			public WebElement rangeToTxt;   
			
			@FindBy(how=How.XPATH,using="//button[@data-id='TeamId']")							//created by sagar on 29.07.2023
			public WebElement teamDD;   
          
			
			@FindBy(how=How.XPATH,using="//button[@data-id='UserId']")							//created by sagar on 29.07.2023
			public WebElement userIdDD;   
          
			
			@FindBy(how=How.XPATH,using="//table[@id='mailRoutingRuleSetTable']")		//created by sagar on02.08.2023
			public WebElement verifyEmptyTable;

//			@FindBy(how=How.XPATH,using="//table[@id='mailRoutingTable']")		//created by sagar on02.08.2023
//			public WebElement mailRoutingTableEmpty;
//			
//			@FindBy(how=How.XPATH,using="//table[@id='mailRoutingRuleSetTable']")		//created by sagar on02.08.2023
//			public WebElement mailRoutingRuleSetTableEmpty;
			
//			@FindBy(how=How.XPATH,using="//input[@aria-activedescendant='bs-select-4-0']")		//created by sagar on 29.07.2023
//			public WebElement documentTypeDDTxt;   

//			@FindBy(how=How.XPATH,using="//input[@aria-activedescendant='bs-select-5-0']")		//created by sagar on 29.07.2023
//			public WebElement roleDDTxt;   

//			@FindBy(how=How.XPATH,using="//input[@aria-activedescendant='bs-select-6-1']")		//created by sagar on 29.07.2023
//			public WebElement teamDDTxt;   
			
//			@FindBy(how=How.XPATH,using="//input[@aria-activedescendant='bs-select-7-0']")		//created by sagar on 29.07.2023
//			public WebElement userIdDDTxt;   
			
			
	public Allocation(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		capture = new CapturePage(driver);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		actionHelper=new ActionHelper(driver);
	}
	
	//created by sagar on 29.07.2023
	public void selectDocumentType (String DocumentType,String getEnvVariable) 
	{
		new DropdownHelper(driver).selectFromAutocompleDD(DocumentType, documentTypeDD);
	}
	
	//created by sagar on 29.07.2023
	public void selectRole (String Role,String getEnvVariable) 
	{
			new DropdownHelper(driver).selectFromAutocompleDD(Role, roleDD);
	}
	
	//created by sagar on 29.07.2023
	public void selectTeam (String Team,String getEnvVariable) 
	{
		new DropdownHelper(driver).selectFromAutocompleDD(Team, teamDD);
	}
	
	//created by sagar on 29.07.2023
	public void selectUserId (String UserId,String getEnvVariable) 
	{
		new DropdownHelper(driver).selectFromAutocompleDD(UserId, userIdDD);
	}
	
	public void addMailRouting (String documentType, String role, String rangeFrom, String rangeTo, String TeamID, String UserName)
	{
		String getEnvVariable = System.getProperty("propertyName");

		navigationMenu.waitForIcon("Add a new mail allocation rule", "Actions");
		navigationMenu.clickOnIconMenu("Add a new mail allocation rule", "Actions");

		navigationMenu.waitForIcon("Save changes made to mail allocation rule", "Actions");
		sendKeys(rangeFromTxt, rangeFrom, "entering Range from");
		sendKeys(rangeToTxt, rangeTo, "entering Range to");
		selectRole(role, getEnvVariable);
		sleepFor(3000);
		selectDocumentType(documentType, getEnvVariable);
		sleepFor(2000);
//		selectTeam(ObjectReader.reader.getLoggedInUsersTeamId(), getEnvVariable);
		selectTeam(TeamID, getEnvVariable);
		selectUserId(UserName, getEnvVariable);
		navigationMenu.clickOnIconMenu("Save changes made to mail allocation rule", "Actions");
		navigationMenu.waitForAddIcon();
	}
	
	//created by sagar on 29.07.2023
	public void addAllocation(String ruleName, String documentType, String role, String TeamID, String UserName1, String UserName2) {
			getApplicationUrl();
			sleepFor(1000);
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			sleepFor(6000);			
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
			sleepFor(2000);
	        navigationMenu.waitForIcon("Add a new mail allocation rule", "Actions");
			navigationMenu.clickOnIconMenu("Add a new mail allocation rule", "Actions");
	        
    		new WindowHelper(driver).waitForModalDialogWithTextInSpan("Add Mail Allocation Rule Set");
			sendKeys(mailRoutingRuleSetNameTxt, ruleName, "entering mail routing rule set name");
			sendKeys(mailRoutingRuleSetDescriptionTxt, ruleName+" Description", "entering routing rule set description");
			click(btnSaveMailRoutingRuleSet, "Clicking on save button");
			
			getApplicationUrl();
			sleepFor(1000);
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
			sleepFor(2000);
			
			navigationMenu.searchInFilter(ruleName);
    		sleepFor(1000);
//    		capture.clickOnFirstItemOfIntray();
    		navigationMenu.clickOnFilteredRow("mailRoutingRuleSetTable");
						
			waitHelper.waitForElementClickable(driver, rulesBtn, 35);
			new ActionHelper(driver).moveToElementAndClick(rulesBtn);

			addMailRouting (documentType, role, "S0100", "S0999", TeamID, UserName1);
			addMailRouting (documentType, role, "S1000", "S9999", TeamID,  UserName2);
	}

	//created by sagar on 29.07.2023
	public void editAllocation(String ruleName, String documentType, String role, String TeamID, String UserName1, String UserName2, String rangeFrom, String rangeTo) {
			getApplicationUrl();
			sleepFor(1000);
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			sleepFor(6000);			
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
			sleepFor(2000);
	        navigationMenu.waitForIcon("Add a new mail allocation rule", "Actions");
			//navigationMenu.waitForAddIcon();
	        
			navigationMenu.searchInFilter(ruleName);
    		sleepFor(1000);
//    		capture.clickOnFirstItemOfIntray();
    		navigationMenu.clickOnFilteredRow("mailRoutingRuleSetTable");		
			waitHelper.waitForElementClickable(driver, rulesBtn, 35);
			new ActionHelper(driver).moveToElementAndClick(rulesBtn);

			addMailRouting (documentType, role, rangeFrom, rangeTo, TeamID, UserName1);
	}
	
	//created by sagar on 02.08.2023	--> not checked only created
	public void deleteMailRouting(String UserName) {
		navigationMenu.searchInFilter(UserName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailRoutingTable");	
		navigationMenu.clickOnIconMenu("Delete selected mail allocation rule", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);    
//		refreshPage();
//		sleepFor(2000);	
//		
////		verification
//		navigationMenu.clickOnFilteredRow("mailRoutingTable");
//		String verifyEmptyTable = mailRoutingTableEmpty.getText();
//		String expMsgInEmptyTable = "No matching records found";
////		String expMsgInEmptyTable = "No items available";
//		AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable); 
	}
	
	//created by sagar on 02.08.2023	--> not checked only created
	public void deleteAllocation(String ruleName, String UserName1, String UserName2) {
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
		sleepFor(2000);
        navigationMenu.waitForIcon("Add a new mail allocation rule", "Actions");
 		navigationMenu.searchInFilter(ruleName);
		sleepFor(1000);
		
		navigationMenu.clickOnFilteredRow("mailRoutingRuleSetTable");	
		waitHelper.waitForElementClickable(driver, rulesBtn, 35);
		new ActionHelper(driver).moveToElementAndClick(rulesBtn);		
		
//		delete mail routing for user1 
		deleteMailRouting(UserName1);
//		delete mail routing for user2 
		deleteMailRouting(UserName2);

		
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
//		sleepFor(2000);
//		navigationMenu.waitForIcon("Add a new mail allocation rule", "Actions");
// 		navigationMenu.searchInFilter(ruleName);
//		navigationMenu.clickOnIconMenu("Delete selected mail allocation rule", "Actions");
//		new WindowHelper(driver).waitForPopup("Delete");
//		String getMsg = new WindowHelper(driver).getPopupMessage();
//		new WindowHelper(driver).clickOkOnPopup();
//		sleepFor(2000);	
//		System.out.println("get message ===================================="+getMsg); 
// 		
//		//verify rule is deleted
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
//		sleepFor(2000);
//		navigationMenu.waitForIcon("Add a new mail allocation rule", "Actions");
// 		navigationMenu.searchInFilter(ruleName);
//		
//		String VerifyEmptyTable = mailRoutingRuleSetTableEmpty.getText();
//		String expMsgInEmptyTable = "No matching records found";
//		AssertionHelper.verifyText(VerifyEmptyTable, expMsgInEmptyTable);
	}

	
}
