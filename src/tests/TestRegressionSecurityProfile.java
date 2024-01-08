package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
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
import utils.ExtentReports.ExtentTestManager;

public class TestRegressionSecurityProfile extends TestBase {
	public DocumentToolsPage docTools;
	private Logger log = LoggerHelper.getLogger(TestRegressionSecurityProfile.class);
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
	public FileSystemSection adminFS;
	public WebIntegration webIntegration;
	public ToolkitSystemSection toolkitSystemSection;
	public AdminDocumentSection adminDocumentSection;

	public AdminUserSection adminUserSection;

	private String securityProfile1 = "";
	private String securityProfile2 = "";

	private String userFirst = "";
	private String userSecond = "";
	private String description = "";
	private String userThird = "";
	
	private String delSecurityProfile1;
	private String delSecurityProfile2;
	private String delSecurityProfile3;
	
	private String delSecurityError1;
	private String delSecurityError2;
	private String delSecurityError3;
	private String userSec1;
	private String userSec2;
	private String userSec3;
	
	private String existingUser1;
	private String existingUser2;
	
	@BeforeClass
	public void setupClass() {
		docTools = new DocumentToolsPage(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		xls = new ExcelReader();
		intrayTools = new IntrayToolsPage(driver);
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		adminFS = new FileSystemSection(driver);
		webIntegration = new WebIntegration(driver);
		toolkitSystemSection = new ToolkitSystemSection(driver);
		adminDocumentSection = new AdminDocumentSection(driver);
		adminUserSection = new AdminUserSection(driver);
	}

	/*
	 * @Test( enabled=true,priority=0, description =
	 * "This function tests the valid flow of login", groups = "smoke") public void
	 * TestLoginFunctionality() throws InterruptedException { String username =
	 * ObjectReader.reader.getLoginId(); String password =
	 * ObjectReader.reader.getPassword();
	 * login.loginWithCredentials(username,password); String welcomeActual =
	 * login.getWelcomeText(); String usernameActual = login.getUsernameText();
	 * AssertionHelper.verifyText(welcomeActual, "Welcome");
	 * System.out.println("No record message is "+ObjectReader.reader.
	 * getNoRecordMessage()); AssertionHelper.verifyText(usernameActual, username);
	 * }
	 */

	@Test(priority = 100, enabled = true) // true
	public void AddSecurityProfile() {
		securityProfile1 = "TeamSe_" + generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");
		navigationMenu.clickOnAddIcon();

		adminUserSection.enterProfileName(securityProfile1);
		adminUserSection.enterProfileDescription("profile description");
		adminUserSection.clickOnUserNavTab("Security");

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");

		userPermission.put("System Administration", systemAdministrationList);

		adminUserSection.addSecurityPermissionForUser(userPermission);
		// navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to security profile", "Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
	}

	@Test(priority = 101, enabled = true)
	public void AddNewUserAndAsssignSecurityProfile() {
		userFirst = "UserF_" + generateRandomNumber();
		userSecond = "UserS_" + generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();

		adminUserSection.enterUserId(userFirst);
		adminUserSection.enterUsername(userFirst);
		sleepFor(1000);
		adminUserSection.enterSecurityLevel("9");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(securityProfile1,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}

		// Add another user and Assign same security profile to that user
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();

		adminUserSection.enterUserId(userSecond);
		adminUserSection.enterUsername(userSecond);
		sleepFor(1000);
		adminUserSection.enterSecurityLevel("9");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(securityProfile1,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority = 102, enabled = true)
	public void EditSecurityProfileAndCascaseToFirstUser() {				//Case 6
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(securityProfile1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		sleepFor(2000);
		adminUserSection.enterProfileDescription("profile description updated");
		adminUserSection.clickOnUserNavTab("Security");
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> systemAdminList = new ArrayList<String>();
		systemAdminList.add("System Administration");
		systemAdminList.add("Is Administrator");
		
		userPermission.put("System Administration", systemAdminList);
		adminUserSection.addSecurityPermissionForUser(userPermission);
		
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Cascade Users");
		sleepFor(1000);
		adminUserSection.CascadeUserCheckbox(userFirst);
	
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}

	}
	
	@Test(priority = 103, enabled = true)				//Case 6
	public void VerifyCascadedChangesForFirstUser() {
		test = ExtentTestManager.getTest();
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList1 = new ArrayList<>();
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userFirst);
		sleepFor(1000);
		new AdminUserSection(driver).clickOnFilteredUser();

		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		
		systemAdministrationList1.add("System Administration");
		systemAdministrationList1.add("Is Administrator");
		systemAdministrationList1.add("Is Supervisor");
		systemAdministrationList1.add("Campaign Admin");
		systemAdministrationList1.add("Alter User/Team Campaigns");
		userPermission.put("System Administration", systemAdministrationList1);
		
		adminUserSection.checkSecurityPermissionAfterCascadingToUser(userPermission);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		adminUserSection.clickOnUserNavTab("General");
		sleepFor(1000);
		
		//Checking security rights for second user
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userSecond);
		sleepFor(1000);
		new AdminUserSection(driver).clickOnFilteredUser();

		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		adminUserSection.checkSecurityPermissionAfterCascadingToUser(userPermission);	
		sleepFor(1000);
	}


	@Test(priority = 104, enabled = true)				//Case 11
	public void DeleteSecondUserAndVerifyThatUserIsNotPresentInCascadedList() {
		test = ExtentTestManager.getTest();
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userSecond);
		sleepFor(1000);
		new AdminUserSection(driver).clickOnFilteredUser();

		navigationMenu.clickOnDeleteIcon();
		sleepFor(2000);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");
		navigationMenu.searchInFilter(securityProfile1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Cascade Users");

		boolean status = new VerificationHelper(driver).isElementPresent(By.xpath("//td[text()='"+userSecond+"']"));
		AssertionHelper.verifyFalse(status,"Verifying that second user is not there in list");
		test.log(Status.INFO, "User is not present");
	}
	
	@Test(priority=105,enabled = true)		//Maximum security added here		Case 12
	public void AddSecondSecurityProfile() {
		securityProfile2 = "TeamSe_" + generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");
		navigationMenu.clickOnAddIcon();

		adminUserSection.enterProfileName(securityProfile2);
		adminUserSection.enterProfileDescription("profile description");
		adminUserSection.clickOnUserNavTab("Security");

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		systemAdministrationList.add("Is System Implementer");
		
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
		userPermission.put("Case Management", caseManagementList);
		userPermission.put("Enterprise Workflow", workFlowList);
		
		adminUserSection.addSecurityPermissionForUser(userPermission);
		navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		
	}
	
	@Test(priority=106,enabled = true)					//Case 12,18
	public void AddNewUserAndCheckSecurityRight() {
		userThird = "UserTh_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		//These security rights used after overiding
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		userPermission.put("System Administration", systemAdministrationList);

		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        adminUserSection.enterUserId(userThird);
        adminUserSection.enterUsername(userThird);
        sleepFor(1000);
        adminUserSection.enterSecurityLevel("9");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(securityProfile2,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(2000);
		
		HashMap<String, Boolean> checkMap = new LinkedHashMap<String, Boolean>();
		checkMap.put("System Administration", true);
		checkMap.put("Is Administrator", true);
		checkMap.put("Is Supervisor", true);
		checkMap.put("Campaign Admin", true);
		checkMap.put("Alter User/Team Campaigns", true);
		checkMap.put("Can Create Tags ", true);
		checkMap.put("Can Edit Tags ", true);
		checkMap.put("Can Delete Tags ", true);
		checkMap.put("Is System Implementer", true);
		
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
		sleepFor(2000);
		
		
		//Selecting less security rights & verifying rights
		HashMap<String, Boolean> checkMap1 = new LinkedHashMap<String, Boolean>();
		checkMap1.put("System Administration", true);
		checkMap1.put("Is Administrator", true);
		checkMap1.put("Is Supervisor", true);
		checkMap1.put("Campaign Admin", true);
		checkMap1.put("Alter User/Team Campaigns", true);

		adminUserSection.selectUserSecurityProfile(securityProfile1,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		adminUserSection.checkSecurityPermissionWhileAddngUser(checkMap1);	
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		adminUserSection.clickOnUserNavTab("General");
		sleepFor(2000);
		
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
			new WindowHelper(driver).waitForPopup("User Security Profile");
	        String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	        System.out.println("getPopUpMessage "+getPopUpMessage);
	        AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	        sleepFor(1000);
	        new WindowHelper(driver).clickOnModalFooterButton("Yes");
	        sleepFor(3000);
		}else {
			navigationMenu.clickOnSaveIcon();
			new WindowHelper(driver).waitForPopup("User Security Profile");
	        String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	        System.out.println("getPopUpMessage "+getPopUpMessage);
	        AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	        sleepFor(1000);
	        new WindowHelper(driver).clickOnModalFooterButton("Yes");
	        sleepFor(3000);
			navigationMenu.waitForAddIcon();
			
			//Verify after oveririding security rights
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(userThird);
			sleepFor(1000);
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			
			adminUserSection.clickOnUserNavTab("Security");
			sleepFor(1000);
			adminUserSection.verifySecurityPermissionAfterOverriding(userPermission);

		}
	}
	
	//Without overriding	
	@Test(priority=107,enabled = true)
	public void RemoveSecurityRightFromUserAndVerify() {		//		Case 14,17,20
		test = ExtentTestManager.getTest();
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Is Supervisor");
		
		userPermission.put("System Administration", systemAdministrationList);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userThird);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		adminUserSection.RemoveSecurityRightsFromUser(userPermission);
		//sleepFor(1000);
		//new JavaScriptHelper(driver).scrollToTop();
		sleepFor(3000);
		adminUserSection.clickOnUserNavTab("General");
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");

		//To check that after clicking on cancel it still on same page
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions"); 
		new WindowHelper(driver).waitForPopup("User Security Profile");
	    String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	    System.out.println("getPopUpMessage "+getPopUpMessage);
	    AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	    sleepFor(1000);
	    new WindowHelper(driver).clickOnModalFooterButton("Cancel");
	    sleepFor(3000);

		navigationMenu.clickOnIconMenu("Save changes made to user","Actions"); 
		new WindowHelper(driver).waitForPopup("User Security Profile");
	    String getPopUpMessage1 = new WindowHelper(driver).getPopupMessage();
	    System.out.println("getPopUpMessage1 "+getPopUpMessage1);
	    AssertionHelper.verifyTextContains(getPopUpMessage1, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	    sleepFor(1000);
	    new WindowHelper(driver).clickOnModalFooterButton("No");
	    sleepFor(3000);
	    navigationMenu.waitForAddIcon();
	    
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userThird);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		
		adminUserSection.clickOnUserNavTab("Security");
		adminUserSection.VerifyConfilctedRowInSecurityRight(userPermission);
	}
	
	@Test(priority=108,enabled = true)
	public void AddMultipleSecurityProfile() {
		delSecurityProfile1 = "DelSec1" + generateRandomNumber();
		delSecurityProfile2 = "DelSec2" + generateRandomNumber();
		delSecurityProfile3 = "DelSec3" + generateRandomNumber();
		for (int i = 1; i <= 3; i++) {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);

			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Security Profile maintenance", "User");
			navigationMenu.clickOnAddIcon();

			if(i==1) {
				adminUserSection.enterProfileName(delSecurityProfile1);
			}else if(i==2) {
				adminUserSection.enterProfileName(delSecurityProfile2);
			}else {
				adminUserSection.enterProfileName(delSecurityProfile3);
			}
		
			adminUserSection.enterProfileDescription("profile description");
			adminUserSection.clickOnUserNavTab("Security");

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("Is Supervisor");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");

			userPermission.put("System Administration", systemAdministrationList);

			adminUserSection.addSecurityPermissionForUser(userPermission);
			navigationMenu.clickOnIconMenu("Save changes made to security profile", "Actions");
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=109,enabled = true)
	public void DeleteMultipleSecurityProfile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");

		new NavigationMenu(driver).SelectMultiCheckbox();
        navigationMenu.searchInFilter("DelSec");
		sleepFor(1000);
		navigationMenu.clickOnSpecificRow(getEnvVariable, "1");
		sleepFor(1000);
		navigationMenu.clickOnSpecificRow(getEnvVariable, "2");
		sleepFor(1000);
		navigationMenu.clickOnSpecificRow(getEnvVariable, "3");
		navigationMenu.clickOnIconMenu("Delete selected security profile", "Actions");
		navigationMenu.clickOkOnPopup();
		sleepFor(4000);
	}
	
	@Test(priority=110,enabled = true)
	public void AddAnotherSetOfMultipleSecurityProfile() {			//This is to assign users
		delSecurityError1 = "Error1" + generateRandomNumber();
		delSecurityError2 = "Error2" + generateRandomNumber();
		delSecurityError3 = "Error3" + generateRandomNumber();
		for (int i = 1; i <= 3; i++) {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);

			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Security Profile maintenance", "User");
			navigationMenu.clickOnAddIcon();

			if(i==1) {
				adminUserSection.enterProfileName(delSecurityError1);
			}else if(i==2) {
				adminUserSection.enterProfileName(delSecurityError2);
			}else {
				adminUserSection.enterProfileName(delSecurityError3);
			}
		
			adminUserSection.enterProfileDescription("profile description");
			adminUserSection.clickOnUserNavTab("Security");

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("Is Supervisor");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");

			userPermission.put("System Administration", systemAdministrationList);

			adminUserSection.addSecurityPermissionForUser(userPermission);
			navigationMenu.clickOnIconMenu("Save changes made to security profile", "Actions");
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=111,enabled = true)
	public void AsssginSecurityProfileToUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		userSec1 = "Sam1_"+generateRandomNumber();
		userSec2 = "Sam2_"+generateRandomNumber();
		userSec3 = "Sam3_"+generateRandomNumber();
		
		for (int i = 1; i <= 3; i++) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.clickOnAddIcon();

			if(i==1) {
				adminUserSection.enterUserId(userSec1);
				adminUserSection.enterUsername(userSec1);
				sleepFor(1000);
				adminUserSection.enterSecurityLevel("9");
				sleepFor(3000);
				adminUserSection.selectUserSecurityProfile(delSecurityError1,getEnvVariable);
			}else if(i==2) {
				adminUserSection.enterUserId(userSec2);
				adminUserSection.enterUsername(userSec2);
				sleepFor(1000);
				adminUserSection.enterSecurityLevel("9");
				sleepFor(3000);
				adminUserSection.selectUserSecurityProfile(delSecurityError2,getEnvVariable);
			}else {
				adminUserSection.enterUserId(userSec3);
				adminUserSection.enterUsername(userSec3);
				sleepFor(1000);
				adminUserSection.enterSecurityLevel("9");
				sleepFor(3000);
				adminUserSection.selectUserSecurityProfile(delSecurityError3,getEnvVariable);
			}
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(1000);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
				// navigationMenu.waitForAddIcon();
			} else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}

		}
	}

	@Test(priority=112,enabled = true)
	public void DeleteSecurityProfileAndVerifyError() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String expectedErrorMsg1 = "cannot be deleted";

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");

		new NavigationMenu(driver).SelectMultiCheckbox();
		navigationMenu.searchInFilter("Error");
		sleepFor(1000);
		new NavigationMenu(driver).clickOnSpecificRow(getEnvVariable, "1");
		sleepFor(1000);
		new NavigationMenu(driver).clickOnSpecificRow(getEnvVariable, "2");
		sleepFor(1000);
		new NavigationMenu(driver).clickOnSpecificRow(getEnvVariable, "3");
		sleepFor(1000);
		navigationMenu.clickOnIconMenu("Delete selected security profile", "Actions");
		navigationMenu.clickOkOnPopup();
		sleepFor(1000);
		String getErrorMessage = new WindowHelper(driver).getPopupMessage();
		System.out.println("getErrorMessage " + getErrorMessage);
		AssertionHelper.verifyTextContains(getErrorMessage, expectedErrorMsg1);
		navigationMenu.clickOkOnPopup();
	}
	
	@Test(priority=113,enabled = true)
	public void CreateNewUserAndAssignSecurityProfileToUser() {
		HashMap<String, Boolean> checkMap1 = new LinkedHashMap<String, Boolean>();
		checkMap1.put("System Administration", true);
		checkMap1.put("Is Administrator", true);
		checkMap1.put("Is Supervisor", true);
		checkMap1.put("Campaign Admin", true);
		checkMap1.put("Alter User/Team Campaigns", true);

		existingUser1 = "Existing1"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();

		adminUserSection.enterUserId(existingUser1);
		adminUserSection.enterUsername(existingUser1);
		sleepFor(1000);
		adminUserSection.enterSecurityLevel("9");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(securityProfile1,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		adminUserSection.checkSecurityPermissionWhileAddngUser(checkMap1);	
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("General");
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=114,enabled = true)
	public void OverridingSecurityProfileForExisitingUser() {
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> systemAdminList = new ArrayList<String>();
		systemAdminList.add("Folder Security Administrator");
		systemAdminList.add("Can Create Tags ");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(existingUser1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(2000);
		userPermission.put("System Administration", systemAdminList);
		adminUserSection.addSecurityPermissionForUser(userPermission);
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("General");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		
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
	public void VerifySecurityRightsAfterOverridingForExisingUser() {
		//These security rights used after overiding
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		userPermission.put("System Administration", systemAdministrationList);

		//Verify after oveririding security rights
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(existingUser1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		
		adminUserSection.clickOnUserNavTab("Security");
		adminUserSection.verifySecurityPermissionAfterOverriding(userPermission);
	}
	
	@Test(priority=116,enabled = true)
	public void CreateAnotherExistingUser() {
		HashMap<String, Boolean> checkMap1 = new LinkedHashMap<String, Boolean>();
		checkMap1.put("System Administration", true);
		checkMap1.put("Is Administrator", true);
		checkMap1.put("Is Supervisor", true);
		checkMap1.put("Campaign Admin", true);
		checkMap1.put("Alter User/Team Campaigns", true);

		existingUser2 = "Existing2"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();

		adminUserSection.enterUserId(existingUser2);
		adminUserSection.enterUsername(existingUser2);
		sleepFor(1000);
		adminUserSection.enterSecurityLevel("9");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(securityProfile1,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		adminUserSection.checkSecurityPermissionWhileAddngUser(checkMap1);	
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("General");
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=117,enabled = true)
	public void VerifySecurityRightsWithoutOveriridingCase() {
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Is Supervisor");
		
		userPermission.put("System Administration", systemAdministrationList);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(existingUser2);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(2000);
		adminUserSection.RemoveSecurityRightsFromUser(userPermission);
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("General");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions"); 
		new WindowHelper(driver).waitForPopup("User Security Profile");
	    String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
	    System.out.println("getPopUpMessage "+getPopUpMessage);
	    AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
	    sleepFor(1000);
	    new WindowHelper(driver).clickOnModalFooterButton("No");
	    sleepFor(3000);
	    navigationMenu.waitForAddIcon();
	    
	    //Verifying conflicted row
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(existingUser2);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Security");
		sleepFor(1000);
		adminUserSection.VerifyConfilctedRowInSecurityRight(userPermission);		
	}

}
