package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import main.EDRM.hybridFramework.pageObject.Admin.WebIntegration;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;

public class TestSecurityProfile extends TestBase {
	
	public DocumentToolsPage docTools ;
	private Logger log = LoggerHelper.getLogger(TestCapturePage.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	AlertHelper alertHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	AdminDocumentSection adminDocument;
	IntrayToolsPage intrayTools;
	public LoginPage login;
	public FileSystemSection adminFS ;
	public WebIntegration webIntegration;
	public ToolkitSystemSection toolkitSystemSection;
	public AdminDocumentSection adminDocumentSection;
	
	public AdminUserSection adminUserSection;
	
	private String profile = "";
	private String userNew="";
	private String anotherProfile="";
	
	
	private String description="";
	
	@BeforeClass
	public void setupClass()  {
		docTools = new DocumentToolsPage(driver);
		capture = new CapturePage(driver);
		home= new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		xls = new ExcelReader();
		intrayTools= new IntrayToolsPage(driver);
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		adminFS = new FileSystemSection(driver);
		webIntegration = new WebIntegration(driver);
		toolkitSystemSection = new ToolkitSystemSection(driver);
		adminDocumentSection = new AdminDocumentSection(driver);
		adminUserSection = new AdminUserSection(driver);
	}
	
	/*@Test( enabled=true,priority=0, description = "This function tests the valid flow of login", groups = "smoke")
	public void TestLoginFunctionality() throws InterruptedException {
		String username = ObjectReader.reader.getLoginId();
		String password = ObjectReader.reader.getPassword();
		login.loginWithCredentials(username,password);
		String welcomeActual = login.getWelcomeText();
		String usernameActual = login.getUsernameText();
		AssertionHelper.verifyText(welcomeActual, "Welcome");
		System.out.println("No record message is "+ObjectReader.reader.getNoRecordMessage());
		AssertionHelper.verifyText(usernameActual, username);
	}*/
	
	
	@Test(priority=102,enabled = true)		//true
	public void AddSecurityProfile() {
		profile = "TeamL_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        adminUserSection.enterProfileName(profile);
        adminUserSection.enterProfileDescription("profile description");
        adminUserSection.clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		
		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		
		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");
		
		List<String> folderList = new ArrayList<String>();
		folderList.add("Allow Create Dummy Folder References ");
		folderList.add("Allow Translate Folder References");

		List<String> documentList = new ArrayList<String>();
		//documentList.add("Use Clipbook");
		documentList.add("Create Renditions and apply Redaction Templates");
		documentList.add("Edit Documents (CheckIn and CheckOut)");
		documentList.add("Create and edit Redaction Templates");
		documentList.add("Scan Documents");
		documentList.add("Batch Index Documents");
		documentList.add("Capture Documents");
		documentList.add("ReIndex Documents");
		documentList.add("Allow locking of documents to prevent editing");
		documentList.add("Allow unlocking of documents to allow editing");
		documentList.add("Allow routing of reindexed documents");
		documentList.add("Alter Protective Marker");
		documentList.add("Can Export Document");
		documentList.add("Can Export Document Metadata");
		documentList.add("Can Edit Metadata");
		//documentList.add("Can Print Document");
		documentList.add("Allow Document Linking");
		
		List<String> reportsList = new ArrayList<String>();
		reportsList.add("View Reports");
		
		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");
		
		List<String> memoList = new ArrayList<String>();
		memoList.add("Update Another User's Memos (excluding Intray)");
		
		List<String> caseManagementList = new ArrayList<String>();
		caseManagementList.add("QA User (VF)");
		caseManagementList.add("Pre-Assessor (VF)");
		
		List<String> workFlowList = new ArrayList<String>();
		workFlowList.add("Access Enterprise Workflow");
		workFlowList.add("Open Item");
		workFlowList.add("Delete Item");
		workFlowList.add("Pick Item");
		workFlowList.add("Return Item");
		workFlowList.add("Forward Items By User");
		workFlowList.add("Forward Items By Role");
		workFlowList.add("Resubmit Item");
		workFlowList.add("Display Audit Information");
		
		userPermission.put("System Administration", systemAdministrationList);
		userPermission.put("Admin", adminList);
		userPermission.put("Intray", intrayList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Folder", folderList);
		userPermission.put("Document", documentList);
		userPermission.put("Reports", reportsList);
		userPermission.put("Cold", coldList);
		//userPermission.put("Memo", memoList);
		userPermission.put("Case Management", caseManagementList);
		userPermission.put("Enterprise Workflow", workFlowList);
		
		adminUserSection.addSecurityPermissionForUser(userPermission);
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
//		if(!getEnvVariable.equals("Enterprise_Sp1s")) {
//			//navigationMenu.waitForAddIcon();
//		}
	}
	
	@Test(priority=103,enabled = true)		//true
	public void EditSecurityProfile() {
		String profileName=profile;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        //new NavigationMenu(driver).waitForFilterTextBox();
        navigationMenu.searchInFilter(profileName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//new NavigationMenu(driver).clickOnEditIcon();
		adminUserSection.enterProfileDescription("profile description updated");
		adminUserSection.clickOnUserNavTab("Security");
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> documentList = new ArrayList<String>();
		documentList.add("Use Clipbook");
		documentList.add("Can Print Document");
		
		userPermission.put("Document", documentList);
		adminUserSection.addSecurityPermissionForUser(userPermission);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		//navigationMenu.clickOnSaveIcon();
		//navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=104,enabled = true)		//true
	public void AddNewUserAndCheckSecurityRight() {
		String userFirst = "User_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        adminUserSection.enterUserId(userFirst);
        adminUserSection.enterUsername(userFirst);
        sleepFor(1000);
        adminUserSection.enterSecurityLevel("9");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(profile,getEnvVariable);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(3000);
		
		HashMap<String, Boolean> checkMap = new LinkedHashMap<String, Boolean>();
		checkMap.put("Is Supervisor", true);
		checkMap.put("Campaign Admin", true);
		checkMap.put("Alter User/Team Campaigns", true);
		checkMap.put("Can Create Tags ", true);
		checkMap.put("Can Edit Tags ", true);
		checkMap.put("Can Delete Tags ", true);
		
		checkMap.put("DIP", true);
		
		checkMap.put("View Other Users In-Trays", true);
		checkMap.put("View Other Teams In-Trays", true);
		
		checkMap.put("Take Work from a Shared In-Tray", true);
		checkMap.put("Process Work From Another Users In-Tray", true);
		checkMap.put("Distribute Work to Other Users In-Trays", true);
		
		checkMap.put("Allow Create Dummy Folder References ", true);
		checkMap.put("Allow Translate Folder References", true);
		
		checkMap.put("Use Clipbook", true);
		checkMap.put("Create Renditions and apply Redaction Templates", true);
		checkMap.put("Edit Documents (CheckIn and CheckOut)", true);
		checkMap.put("Create and edit Redaction Templates", true);
		checkMap.put("Scan Documents", true);
		checkMap.put("Batch Index Documents", true);
		checkMap.put("Capture Documents", true);
		checkMap.put("ReIndex Documents", true);
		checkMap.put("Allow locking of documents to prevent editing", true);
		checkMap.put("Allow unlocking of documents to allow editing", true);
		checkMap.put("Allow routing of reindexed documents", true);
		checkMap.put("Alter Protective Marker", true);
		checkMap.put("Can Export Document", true);
		checkMap.put("Can Export Document Metadata", true);
		checkMap.put("Can Edit Metadata", true);
		checkMap.put("Can Print Document", true);
		checkMap.put("Allow Document Linking", true);
		checkMap.put("Use Clipbook", true);
		checkMap.put("Can Print Document", true);
		
		checkMap.put("View Reports", true);
		
		checkMap.put("COLD", true);
		checkMap.put("Allow Cold Document Redaction", true);
		
		//checkMap.put("Update Another User's Memos (excluding Intray)", true);
		checkMap.put("QA User (VF)", true);
		checkMap.put("Pre-Assessor (VF)", true);
		
		checkMap.put("Access Enterprise Workflow", true);
		checkMap.put("Open Item", true);
		checkMap.put("Delete Item", true);
		checkMap.put("Pick Item", true);
		checkMap.put("Return Item", true);
		checkMap.put("Forward Items By User", true);
		checkMap.put("Forward Items By Role", true);
		checkMap.put("Resubmit Item", true);
		checkMap.put("Display Audit Information", true);
		
		adminUserSection.checkSecurityPermissionWhileAddngUser(checkMap);	
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		adminUserSection.clickOnUserNavTab("General");
		sleepFor(1000);
		//adminUserSection.clickOnUserSaveIcon();
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=105,enabled = true)		//true
	public void DeleteProfileAndVerifyErrorMessage() {
		String profileName=profile;
		String expectedErrorMsg="Security Profile "+profileName+" cannot be deleted";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
       // new NavigationMenu(driver).waitForFilterTextBox();
        navigationMenu.searchInFilter(profileName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//navigationMenu.clickOnDeleteIcon();
		navigationMenu.clickOnIconMenu("Delete this security profile", "Actions");
		navigationMenu.clickOkOnPopup();
		sleepFor(1000);
		String getErrorMessage=new WindowHelper(driver).getPopupMessage();
		System.out.println("getErrorMessage "+getErrorMessage);
		AssertionHelper.verifyTextContains(getErrorMessage, expectedErrorMsg);
		navigationMenu.clickOkOnPopup();
		
		
	}
	
	@Test(priority=106,enabled = true)		//true
	public void AddAnotherSecurityProfile() {
		anotherProfile = "TeamL_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        //navigationMenu.clickOnAddIcon();
        navigationMenu.clickOnIconMenu("Add a new security profile", "Actions");
        
        adminUserSection.enterProfileName(anotherProfile);
        adminUserSection.enterProfileDescription("profile description");
        adminUserSection.clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Can Create Tags ");
		//systemAdministrationList.add("Can Edit Tags ");
		//systemAdministrationList.add("Can Delete Tags ");
		
		adminUserSection.addSecurityPermissionForUser(userPermission);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//adminUserSection.clickOnUserSaveIcon();
		//navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=107,enabled = true)
	public void DeleteSecurityProfile() {
		String secondProfileName=anotherProfile;
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        //new NavigationMenu(driver).waitForFilterTextBox();
		navigationMenu.searchInFilter(secondProfileName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//navigationMenu.clickOnDeleteIcon();
		navigationMenu.clickOnIconMenu("Delete this security profile", "Actions");
		sleepFor(1000);
		navigationMenu.clickOkOnPopup();
		
	}
	
	@Test(priority=108,enabled = true)		//true
	public void AddAnotherNewUserWithSomeSecurityRight() {
		userNew= "User_N"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        //navigationMenu.clickOnAddIcon();
        navigationMenu.clickOnIconMenu("Add a new user", "Actions");
        
        adminUserSection.enterUserId(userNew);
        adminUserSection.enterUsername(userNew);
        sleepFor(1000);
        adminUserSection.enterSecurityLevel("9");
        sleepFor(1000);
        new JavaScriptHelper(driver).scrollToTop();
        adminUserSection.clickOnUserNavTab("Security");
        sleepFor(2000);
        
        HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> systemAdministrationList = new ArrayList<String>();   
		systemAdministrationList.add("System Administration");systemAdministrationList.add("Is Supervisor");systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");

		adminUserSection.addSecurityPermissionForUser(userPermission);
		//adminUserSection.clickOnUserSaveIcon();
		//navigationMenu.waitForAddIcon();
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=109,enabled = true)			//true
	public void OverrideSecurityProfileRightToEXistingUser() {
		String secondUser=userNew;	
		String profileName=profile;				
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        
        //new NavigationMenu(driver).waitForFilterTextBox();
		navigationMenu.searchInFilter(secondUser);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(4000);
		adminUserSection.selectUserSecurityProfile(profileName,getEnvVariable);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		
		new JavaScriptHelper(driver).scrollToTop();
        //adminUserSection.clickOnUserNavTab("Security");
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		new WindowHelper(driver).waitForPopup("User Security Profile");
        String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
        System.out.println("getPopUpMessage "+getPopUpMessage);
        AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
        sleepFor(1000);
        new WindowHelper(driver).clickOnModalFooterButton("Yes");
        sleepFor(3000);
        navigationMenu.waitForAddIcon();
	}
 //==============================================================================================
	@Test(priority=110,enabled = true)			//true
	public void VerifySecurityRightAfterOverriding() {
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		
		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		
		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");
		
		List<String> folderList = new ArrayList<String>();
		folderList.add("Allow Create Dummy Folder References ");
		folderList.add("Allow Translate Folder References");

		List<String> documentList = new ArrayList<String>();
		documentList.add("Use Clipbook");
		documentList.add("Create Renditions and apply Redaction Templates");
		documentList.add("Edit Documents (CheckIn and CheckOut)");
		documentList.add("Create and edit Redaction Templates");
		documentList.add("Scan Documents");
		documentList.add("Batch Index Documents");
		documentList.add("Capture Documents");
		documentList.add("ReIndex Documents");
		documentList.add("Allow locking of documents to prevent editing");
		documentList.add("Allow unlocking of documents to allow editing");
		documentList.add("Allow routing of reindexed documents");
		documentList.add("Alter Protective Marker");
		documentList.add("Can Export Document");
		documentList.add("Can Export Document Metadata");
		documentList.add("Can Edit Metadata");
		documentList.add("Can Print Document");
		documentList.add("Allow Document Linking");
		
		List<String> reportsList = new ArrayList<String>();
		reportsList.add("View Reports");
		
		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");
		
		List<String> memoList = new ArrayList<String>();
		memoList.add("Update Another User's Memos (excluding Intray)");
		
		List<String> caseManagementList = new ArrayList<String>();
		caseManagementList.add("QA User (VF)");
		caseManagementList.add("Pre-Assessor (VF)");
		
		List<String> workFlowList = new ArrayList<String>();
		workFlowList.add("Access Enterprise Workflow");
		workFlowList.add("Open Item");
		workFlowList.add("Delete Item");
		workFlowList.add("Pick Item");
		workFlowList.add("Return Item");
		workFlowList.add("Forward Items By User");
		workFlowList.add("Forward Items By Role");
		workFlowList.add("Resubmit Item");
		workFlowList.add("Display Audit Information");
		
		userPermission.put("System Administration", systemAdministrationList);
		userPermission.put("Admin", adminList);
		userPermission.put("Intray", intrayList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Folder", folderList);
		userPermission.put("Document", documentList);
		userPermission.put("Reports", reportsList);
		userPermission.put("Cold", coldList);
		//userPermission.put("Memo", memoList);
		userPermission.put("Case Management", caseManagementList);
		userPermission.put("Enterprise Workflow", workFlowList);
		
		String modifiedSecurityUser=userNew;
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        
        //new NavigationMenu(driver).waitForFilterTextBox();
        navigationMenu.searchInFilter(modifiedSecurityUser);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		
		adminUserSection.clickOnUserNavTab("Security");
		adminUserSection.verifySecurityPermissionAfterOverriding(userPermission);
		
	}
	
	@Test(priority=109,enabled = false)
	public void VerifyUser() {
		String newUserName="Yaml_1";			//userNew;	
		String profileName="TeamL_1874";			//profile;	
		
		HashMap<String, Boolean> ma = new LinkedHashMap<String, Boolean>();
		ma.put("Is Supervisor", true);
		ma.put("Campaign Admin", true);
		ma.put("Alter User/Team Campaigns", true);
		ma.put("Can Create Tags ", true);
		ma.put("Can Edit Tags ", true);
		ma.put("Can Delete Tags ", true);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        
        new NavigationMenu(driver).waitForFilterTextBox();
		sendKeys(new NavigationMenu(driver).filterTxt, newUserName, "Entering description in text");
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(4000);
		adminUserSection.selectUserSecurityProfile(profileName,"");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		
		new JavaScriptHelper(driver).scrollToTop();
        adminUserSection.clickOnUserNavTab("Security");
        //adminUserSection.checkColour(ma);
		
	}
	
	@Test(priority=111,enabled = true)
	public void AddSecurityRightWithoutOverriding() {
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Stamp Administrator");
		systemAdministrationList.add("Is RM Administrator");
		
		userPermission.put("System Administration", systemAdministrationList);
		
		String secondUser=userNew;	
		//String profileName=profile;				
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        
        //new NavigationMenu(driver).waitForFilterTextBox();
		navigationMenu.searchInFilter(secondUser);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		
		adminUserSection.clickOnUserNavTab("Security");
		adminUserSection.addSecurityPermissionForUser(userPermission);
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions"); 
		new WindowHelper(driver).waitForPopup("User Security Profile");
	     String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	     System.out.println("getPopUpMessage "+getPopUpMessage);
	     AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	     sleepFor(1000);
	     new WindowHelper(driver).clickOnModalFooterButton("No");
	     sleepFor(3000);
	     navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=112,enabled = true)
	public void VerifySecurityRightWithoutOverriding() {
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Stamp Administrator");
		systemAdministrationList.add("Is RM Administrator");
		
		userPermission.put("System Administration", systemAdministrationList);
		
		String secondUser=userNew;
		getApplicationUrl();
	     navigationMenu.clickOnTab("Administration");
	     navigationMenu.clickOnIcon("User maintenance", "User");
	        
	     //new NavigationMenu(driver).waitForFilterTextBox();
		 navigationMenu.searchInFilter(secondUser);
		 sleepFor(1000);
		 navigationMenu.clickOnFilteredRow("userDataTable");
		 navigationMenu.clickOnEditIcon();
		 
		 sleepFor(2000);
		 String getValidationErrorMessage=adminUserSection.getValidationMessage();
		 AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		 
		 adminUserSection.clickOnUserNavTab("Security");
		 adminUserSection.VerifyConfilctedRowInSecurityRight(userPermission);
	}
	
	@Test(priority=113,enabled = true)
	public void EditSecurityRightInProfile() {
		
		String profileName=profile;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        //new NavigationMenu(driver).waitForFilterTextBox();
		navigationMenu.searchInFilter(profileName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//new NavigationMenu(driver).clickOnEditIcon();
		adminUserSection.enterProfileDescription("profile description updated");
		adminUserSection.clickOnUserNavTab("Security");
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> administrationList = new ArrayList<String>();
		administrationList.add("Is ALG Adminstrator");
		//administrationList.add("Allow Send Email");
		
		userPermission.put("System Administration", administrationList);
		adminUserSection.addSecurityPermissionForUser(userPermission);
		//adminUserSection.clickOnUserSaveIcon();
		//navigationMenu.waitForAddIcon();
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=114,enabled = true)
	public void VerifySecurityRightsInUsers() {
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is ALG Adminstrator");
		//systemAdministrationList.add("Allow Send Email");
		
		userPermission.put("System Administration", systemAdministrationList);
		
		String secondUser=userNew;
		getApplicationUrl();
	     navigationMenu.clickOnTab("Administration");
	     navigationMenu.clickOnIcon("User maintenance", "User");
	        
	     new NavigationMenu(driver).waitForFilterTextBox();
		 navigationMenu.searchInFilter(secondUser);
		 sleepFor(1000);
		 navigationMenu.clickOnFilteredRow("userDataTable");
		 navigationMenu.clickOnEditIcon();
		 
		 sleepFor(2000);
		 String getValidationErrorMessage=adminUserSection.getValidationMessage();
		 AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		 
		 adminUserSection.clickOnUserNavTab("Security");
		 adminUserSection.VerifyConfilctedRowInSecurityRight(userPermission);
		 //navigationMenu.clickOnSaveIcon();
		 navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		 
		 new WindowHelper(driver).waitForPopup("User Security Profile");
	     String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	     System.out.println("getPopUpMessage "+getPopUpMessage);
	     AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	     sleepFor(1000);
	     new WindowHelper(driver).clickOnModalFooterButton("Yes");
	     sleepFor(3000);
	     navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=115,enabled = true)
	public void EditSecurityRightOnceAgainInProfile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String profileName=profile;
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        //new NavigationMenu(driver).waitForFilterTextBox();
		navigationMenu.searchInFilter(profileName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//new NavigationMenu(driver).clickOnEditIcon();
		adminUserSection.enterProfileDescription("profile description updated");
		adminUserSection.clickOnUserNavTab("Security");
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> administrationList = new ArrayList<String>();
		//administrationList.add("Is Letter Administrator");
		//administrationList.add("Is GIM Administrator");
		administrationList.add("Is Retention Administrator");
		
		userPermission.put("System Administration", administrationList);
		adminUserSection.addSecurityPermissionForUser(userPermission);
		//adminUserSection.clickOnUserSaveIcon();
		//navigationMenu.waitForAddIcon();
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=116,enabled = true)
	public void VerifySecurityRightsOnceAgainInUsers() {
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		//systemAdministrationList.add("Is Letter Administrator");
		//systemAdministrationList.add("Is GIM Administrator");
		systemAdministrationList.add("Is Retention Administrator");
		
		userPermission.put("System Administration", systemAdministrationList);
		
		String secondUser=userNew;
		getApplicationUrl();
	     navigationMenu.clickOnTab("Administration");
	     navigationMenu.clickOnIcon("User maintenance", "User");
	        
	     //new NavigationMenu(driver).waitForFilterTextBox();
		 navigationMenu.searchInFilter(secondUser);
		 sleepFor(1000);
		 navigationMenu.clickOnFilteredRow("userDataTable");
		 navigationMenu.clickOnEditIcon();
		 
		 sleepFor(2000);
		 String getValidationErrorMessage=adminUserSection.getValidationMessage();
		 AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		 
		 adminUserSection.clickOnUserNavTab("Security");
		 adminUserSection.VerifyConfilctedRowInSecurityRight(userPermission);
		 //navigationMenu.clickOnSaveIcon();
		 navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		 
		 new WindowHelper(driver).waitForPopup("User Security Profile");
	     String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	     System.out.println("getPopUpMessage "+getPopUpMessage);
	     AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	     sleepFor(1000);
	     new WindowHelper(driver).clickOnModalFooterButton("No");
	     sleepFor(3000);
	     navigationMenu.waitForAddIcon();
	     
	     //new NavigationMenu(driver).waitForFilterTextBox();
	     navigationMenu.searchInFilter(secondUser);
		 sleepFor(1000);
		 navigationMenu.clickOnFilteredRowLink("userDataTable");
		 navigationMenu.clickOnEditIcon();
		 
		 sleepFor(1000);
		 adminUserSection.clickOnUserNavTab("Security");
		 adminUserSection.VerifyConfilctedRowInSecurityRight(userPermission);
		 
		 getApplicationUrl();
		 new AlertHelper(driver).acceptAlertIfPresent();
	}

}

//Security Profile TeamL_3847 cannot be deleted,
