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

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;

import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;


public class TestAdminJobRole extends TestBase {
	public ExtentTest test;
	public NavigationMenu navigationMenu;
	public DropdownHelper dropdownHelper;
	public VerificationHelper verificationHelper;
	public WindowHelper windowHelper;
	public LoginPage login;
	public AdminDocumentSection adminDoc;
	public AdminUserSection adminUserSection;
	
	private String role = "";
	private String updatedJobRole="";
	private String profile = "";
	private String userNew="";
	//private String anotherProfile="";
	
	private String role1="";
	private String role2="";
	private String role3="";
	private List<String> jobRoleList;
	
	private String userFirst ="";
	
	
	@BeforeClass
	public void setupClass()  {
		navigationMenu=new NavigationMenu(driver);
		windowHelper=  new WindowHelper(driver);
		login = new LoginPage(driver);
		adminUserSection = new AdminUserSection(driver);
		verificationHelper = new VerificationHelper(driver);
		adminDoc = new AdminDocumentSection(driver);
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
	
	@Test(priority=1, enabled =true)
	public void AddJobRole() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		role = "Job"+generateRandomNumber();
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Job Role maintenance", "User");
		navigationMenu.clickOnAddIcon();
		
		adminUserSection.enterJobRoleName(role);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			sleepFor(2000);
			navigationMenu.clickOnIconMenu("Save changes made to job role", "Actions");
			navigationMenu.waitForAddIcon();
		}else {
			sleepFor(2000);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=2, enabled =true)
	public void EditJobRole() { 
		updatedJobRole = role+"Edit";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Job Role maintenance", "User");
		
		navigationMenu.searchInFilter(role);
		navigationMenu.clickOnFilteredRowLink("jobRoleTable");
		adminUserSection.enterJobRoleName(updatedJobRole);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			sleepFor(2000);
			navigationMenu.clickOnIconMenu("Save changes made to job role", "Actions");
			navigationMenu.waitForAddIcon();
		}else {
			sleepFor(2000);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=3, enabled =true)
	public void ConfigureJobRoleForDocType() {
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		//updatedJobRole = role+"Edit";
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.searchInFilter("Default");
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		new JavaScriptHelper(driver).clickElement(adminDoc.securityTab);
		adminDoc.selectEnableUserSecurityAccess();
		adminDoc.selectJobRoleFromDDForDoc(updatedJobRole,getEnvVariable);
		
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();	
	}
	
	@Test(priority=4, enabled =true ,dependsOnMethods = {"ConfigureJobRoleForDocType"})
	public void DeleteJobRoleAndVerifyErrorMessage() {
		String expectedMsg = "Job Role "+updatedJobRole+" cannot be deleted as it is associated with either document type";
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Job Role maintenance", "User");
		navigationMenu.searchInFilter(updatedJobRole);
		navigationMenu.clickOnFilteredRowLink("jobRoleTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(2000);
		String getMessage = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		AssertionHelper.verifyTextContains(getMessage, expectedMsg);
		
		
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.searchInFilter("Default");
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		new JavaScriptHelper(driver).clickElement(adminDoc.securityTab);
		adminDoc.unselectEnableUserSecurityAccess();
		//adminDoc.selectJobRoleFromDDForDoc(role);
		
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();	
	}
	
	//Check this is issue with team
	public void AddJobRoleToAccountRefAndDelete() {
		String expectedMsg = "Job Role "+updatedJobRole+" cannot be deleted as it is associated with either document type";
		String accRef = "F1-00R1",entityName = "Account";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				
		home.searchAndNavigateToEditEnity(entityName,accRef);
		home.SelectJobRoleForAccountRef(updatedJobRole,getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Job Role maintenance", "User");
		navigationMenu.searchInFilter("Edit");
		navigationMenu.clickOnFilteredRowLink("jobRoleTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(2000);
		String getMessage = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		AssertionHelper.verifyTextContains(getMessage, expectedMsg);
	}
	
	@Test(priority=5, enabled =true ,dependsOnMethods = {"ConfigureJobRoleForDocType"})
	public void DeleteJobRole() {
		/*String accRef = "F1-00R1",entityName = "Account";
		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				
		home.searchAndNavigateToEditEnity(entityName,accRef);
		home.SelectJobRoleForAccountRef("(none)");*/
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Job Role maintenance", "User");
		navigationMenu.searchInFilter(updatedJobRole);
		navigationMenu.clickOnFilteredRowLink("jobRoleTable");
		navigationMenu.clickOnDeleteIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=6, enabled =true)
	public void AddJobRoleForSecurityProfile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		jobRoleList = new ArrayList<String>();
		
		role1 = "Job1"+generateRandomNumber();
		role2 = "Job2"+generateRandomNumber();
		role3 = "Job3"+generateRandomNumber();
		jobRoleList.add(role1);
		jobRoleList.add(role2);
		jobRoleList.add(role3);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Job Role maintenance", "User");
	
		for (int i = 0; i < jobRoleList.size(); i++) {
			navigationMenu.clickOnAddIcon();
			adminUserSection.enterJobRoleName(jobRoleList.get(i));
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				sleepFor(2000);
				navigationMenu.clickOnIconMenu("Save changes made to job role", "Actions");
				navigationMenu.waitForAddIcon();
			} else {
				sleepFor(2000);
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}
	}
	
	@Test(priority=7,enabled = true)	
	public void AddSecurityProfileAndJobRole() {
		profile = "TeamL_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        //navigationMenu.clickOnAddIcon();
        navigationMenu.clickOnIconMenu("Add a new security profile", "Actions");
        
        adminUserSection.enterProfileName(profile);
        adminUserSection.enterProfileDescription("profile description");
        sleepFor(2000);
        adminUserSection.clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Can Create Tags ");
		
		userPermission.put("System Administration", systemAdministrationList);
		
		adminUserSection.addSecurityPermissionForUser(userPermission);
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		adminUserSection.clickOnUserNavTab("Job Roles");
		adminUserSection.addJobRoleForSecurityProfile(jobRoleList.get(0),getEnvVariable);
		sleepFor(2000);
		adminUserSection.addJobRoleForSecurityProfile(jobRoleList.get(1),getEnvVariable);
		
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			sleepFor(2000);
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=8,enabled = true)		 
	public void AddNewUserAndVerifyBubbledMessage() {
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
        
        adminUserSection.clickOnUserNavTab("Job Roles");
        adminUserSection.addJobRoleForUser(jobRoleList,getEnvVariable);
        
        adminUserSection.clickOnUserNavTab("General");
		sleepFor(3000);
		adminUserSection.selectUserSecurityProfile(profile,getEnvVariable);
		new ActionHelper(driver).pressTab();
		//adminUserSection.selectUserSecurityProfile(profileName);
			//new ActionHelper(driver).pressTab();
		sleepFor(4000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Job Role conflicted with selected Profile.");
		
		navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
		windowHelper.waitForPopup("Leave Page");				
		sleepFor(2000);
		windowHelper.clickOnModalFooterButton("No");
		sleepFor(3000);
	}
	
	@Test(priority=9,enabled = true)         //Override with yes option
	public void AddNewUserAndModifySecurityRightAndVerifyErrorMsg() {
		List<String> jobListForUser = new ArrayList<String>();			//created new list to add incomplete list to user
		jobListForUser.add(jobRoleList.get(0));
		jobListForUser.add(jobRoleList.get(2));
		
		userFirst = "User_"+generateRandomNumber();
		
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
        sleepFor(1000);
        new JavaScriptHelper(driver).scrollToTop();
        adminUserSection.clickOnUserNavTab("Security");
        sleepFor(2000);
        
        HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> systemAdministrationList = new ArrayList<String>();   
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");

		userPermission.put("System Administration", systemAdministrationList);
		adminUserSection.addSecurityPermissionForUser(userPermission);
		
		new JavaScriptHelper(driver).scrollToTop();
		adminUserSection.clickOnUserNavTab("Job Roles");
        adminUserSection.addJobRoleForUser(jobListForUser,getEnvVariable);
        
        //Verified bubbled error message if user having different security and job role
        adminUserSection.clickOnUserNavTab("General");
		sleepFor(2000);
		adminUserSection.selectUserSecurityProfile(profile,getEnvVariable);
		//adminUserSection.selectUserSecurityProfile(profileName);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security and Job Role conflicted with selected Profile.");
		
		
		//Overriding with yes option and verifying job role
		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		new WindowHelper(driver).waitForPopup("User Security Profile");
        String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
        System.out.println("getPopUpMessage "+getPopUpMessage);
        AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes and job roles you have selected here and the linked profile's security attributes and job roles.Do you want to override the profile's security attributes and job roles?");
        sleepFor(1000);
        new WindowHelper(driver).clickOnModalFooterButton("Yes");
        sleepFor(3000);
        navigationMenu.waitForAddIcon();
		
		
		
//		navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
//		windowHelper.waitForPopup("Leave Page");				
//		sleepFor(2000);
//		windowHelper.clickOnModalFooterButton("No");
//		sleepFor(3000);		
	}
	
	@Test(priority=10,enabled = true)				//Verify after overriding with yes option
	public void VerifyJobRoleAfterOveririding() {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userFirst);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Job Roles");
		sleepFor(1000);
		List<String> associatedList =  adminUserSection.getAssociatedJobRolesFromUser();
		
		for(int i = 0;i<associatedList.size();i++) {
			AssertionHelper.verifyText(associatedList.get(i), jobRoleList.get(i));
		}
	}
	
	@Test(priority=11,enabled = true)
	public void DeleteJobRoleAndNotCascadeChangesToUser() {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(profile);
        navigationMenu.clickOnFilteredRow("securityProfilesTable");
        navigationMenu.clickOnEditIcon();
        
        sleepFor(1000);
        adminUserSection.clickOnUserNavTab("Job Roles");
        adminUserSection.deleteJobRoleFromSecurityProfile(jobRoleList.get(1));
        sleepFor(2000);
        //adminUserSection.clickOnUserNavTab("Cascade Users");
        //adminUserSection.AddCascadeUserToProfile(userFirst);
        //sleepFor(1000);
        navigationMenu.clickOnSaveIcon();
        navigationMenu.waitForAddIcon();	
        
        //Verifying the cascaded changes doesnot affect the user
        getApplicationUrl();
        navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userFirst);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToElement(adminUserSection.ddUsersSecurityProfile);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		String getValidationErrorMessage=adminUserSection.getValidationMessage();
		System.out.println("getValid=============== "+getValidationErrorMessage);
		//AssertionHelper.verifyText(getValidationErrorMessage, "User Security and Job Role conflicted with selected Profile.");
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Job Roles");
		sleepFor(1000);
		List<String> associatedList =  adminUserSection.getAssociatedJobRolesFromUser();
		
		AssertionHelper.verifyText(associatedList.get(0), jobRoleList.get(0));
		AssertionHelper.verifyText(associatedList.get(1), jobRoleList.get(1));
        
	}
	
	@Test(priority=12,enabled = true)
	public void DeleteJobRoleAndCascadeChangesToUser() {       //Cascade changes to user
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(profile);
        navigationMenu.clickOnFilteredRow("securityProfilesTable");
        navigationMenu.clickOnEditIcon();
        
        sleepFor(1000);
//        adminUserSection.clickOnUserNavTab("Job Roles");
//        adminUserSection.deleteJobRoleFromSecurityProfile(jobRoleList.get(1));
//        sleepFor(1000);
        adminUserSection.clickOnUserNavTab("Cascade Users");
        adminUserSection.AddCascadeUserToProfile(userFirst);
        sleepFor(1000);
        navigationMenu.clickOnSaveIcon();
        navigationMenu.waitForAddIcon();	
	}
	
	@Test(priority=13,enabled = true)					//Verify cascaded changes for that user
	public void VerifyUsersCascadedChanges() {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userFirst);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		adminUserSection.clickOnUserNavTab("Job Roles");
		sleepFor(1000);
		
		List<String> associatedList =  adminUserSection.getAssociatedJobRolesFromUser();
		AssertionHelper.verifyText(associatedList.get(0), jobRoleList.get(0));
	}
	
	@Test(priority=14,enabled = true)
	public void DeleteJobRoleAssociatedWithSecurityProfileAndUser() {
		String folderRef = ObjectReader.reader.getFolder1RefName();
		String expectedMsg = "Job Role " +role1+" cannot be deleted as it is associated with either document type or user or security profile or "+folderRef;
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Job Role maintenance", "User");
        navigationMenu.searchInFilter(role1);
        navigationMenu.clickOnFilteredRow("jobRoleTable");
        navigationMenu.clickOnDeleteIcon();
        
        sleepFor(2000);
		String getMessage = windowHelper.getPopupMessage();
		System.out.println("getMessage "+getMessage);
		windowHelper.clickOkOnPopup();
		AssertionHelper.verifyTextContains(getMessage, expectedMsg); 
	}
}


//There is a conflict between the user's security attributes and job roles you have selected here and the linked profile's security attributes and job roles.Do you want to override the profile's security attributes and job roles?

//User Job Role conflicted with selected Profile.
//User Security and Job Role conflicted with selected Profile.