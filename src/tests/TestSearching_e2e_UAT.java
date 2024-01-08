package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.MoreSearch;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import main.EDRM.hybridFramework.pageObject.Admin.Group;
import main.EDRM.hybridFramework.pageObject.Admin.IndexAdmin;
import main.EDRM.hybridFramework.pageObject.Admin.MailStatus;
import main.EDRM.hybridFramework.pageObject.Admin.QuickSearch;
import main.EDRM.hybridFramework.pageObject.Admin.Role;
import main.EDRM.hybridFramework.pageObject.Admin.Teams;
import main.EDRM.hybridFramework.pageObject.Bundle.Bundling;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;
import utils.ExtentReports.ExtentTestManager;

/*
 * Suite created by Sagar
 */

public class TestSearching_e2e_UAT extends TestBase {
	public CapturePage capture;
	public HomePage home;
	public static NavigationMenu navigationMenu;
	public AdminUserSection adminUser;
	public VerificationHelper verificationHelper;
	public LoginPage login;
	public AdminUserSection adminUserSection;
	public WindowHelper windowHelper;
	public WaitHelper waitHelper;
	public DocumentToolsPage docTools;
	public Bundling bundle;
	public IntrayToolsPage intrayTools;
	public AdminDocumentSection adminDoc;
	public Group group;
	public Role role;
	public Teams teams;
	public MailStatus mailStatus;
	public ToolkitSystemSection toolkit;
	public AdminUserSection adminuser;
	public FileSystemSection adminFS ;
	public ExcelReader xls;
	public IndexAdmin indexAdmin;
	public QuickSearch quickSearch;
	
	private Logger log = LoggerHelper.getLogger(CapturePage.class);
	private String envType="NECDM_Lives";
	private String randomNo = generateRandomNumber();
//	private String superAdminUserName = "SAU"; 
//	private String superAdminUserName_1 = superAdminUserName+"_1_CZ"; 
//	private String superAdminUserName_D = superAdminUserName+"_D_CZ"; 
	private String superAdminUserName = "SAU_AT"; 
	private String superAdminUserName_1 = superAdminUserName+"_1"; 
	private String superAdminUserName_D = superAdminUserName+"_D"; 
	public String TN1 = "1AT_"+superAdminUserName;
	public String TN2 = "2AT_"+superAdminUserName;
	public String TN3 = "3AT_"+superAdminUserName;
	public String tagRadioButton = "";
	public String savedSearchInputName = superAdminUserName + "MS_" + randomNo;
	public String savedSearchInputName1 = randomNo + superAdminUserName + "_MS_" +  "_D";
	public String savedSearchInputName2 = superAdminUserName + "MS2_" + randomNo;
	
	public String scopeType = "File System";
//	public String FileSystemName = "CD";
	public String FileSystemName = "BD";

//	private String Role, RoleName, TeamId, TeamName, statusCode, statusDescription, statusCode1, statusDescription1,typeId, groupName, folderName;
	private String Role_1, RoleName_1, TeamId_1, TeamName_1, statusCode_1, statusDescription_1, statusCode1_1, statusDescription1_1,typeId_1, groupName_1, folderName_1;
	private String Role_D, RoleName_D, TeamId_D, TeamName_D, statusCode_D, statusDescription_D, statusCodeD_D, statusDescriptionD_D,typeId_D, groupName_D, folderName_D;

	private String 	docRef_SAU_1, accRef_SAU_1, docRef_SAU_2, accRef_SAU_2, docRef_SAU_3, accRef_SAU_3, docRef_SAU_4, accRef_SAU_4, docRef_SAU_5, accRef_SAU_5, docRef_SAU_6, accRef_SAU_6;
	private String docRef_SAU_7, accRef_SAU_7, docRef_SAU_8, accRef_SAU_8, docRef_SAU_9, accRef_SAU_9;

	private String Role = "R"+ "_" + superAdminUserName, 
	RoleName = "RN_"+ "_" + Role,
	TeamId = "TId"+ "_" + superAdminUserName,
	TeamName = "TN_"+ TeamId,
	statusCode =  "SC"+ "_" + superAdminUserName,
	statusDescription ="Desc_"+statusCode,
	statusCode1 =  statusCode+"_1",
	statusDescription1 ="Desc_"+statusCode,
	typeId = superAdminUserName,
	groupName = "GNs"+ "_" + superAdminUserName,
	folderName = "Account ref";

	@BeforeClass
	public void setupClass() {
		intrayTools = new IntrayToolsPage(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDoc = new AdminDocumentSection(driver);
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		verificationHelper = new VerificationHelper(driver);
		adminUser = new AdminUserSection(driver);
		docTools = new DocumentToolsPage(driver);
		bundle = new Bundling(driver);
		group = new Group(driver);
		role = new Role(driver);
		teams = new Teams(driver);
		mailStatus = new MailStatus(driver);
		toolkit = new ToolkitSystemSection(driver);
		adminuser = new  AdminUserSection (driver);
		adminFS = new FileSystemSection(driver);
		indexAdmin = new IndexAdmin(driver);
		quickSearch = new QuickSearch(driver);
		xls = new ExcelReader();
	}

	@Test(priority = 1, enabled = true)
	public void prerequisiteForSuperAdminUser() throws InterruptedException {
//		for 2nd user
		Role_1 = "R"+ "_" + superAdminUserName_1;
		RoleName_1 = "RN_"+ "_" + Role_1;
		TeamId_1 = "T"+ "_" + superAdminUserName_1;
		TeamName_1 = "TN_"+ TeamId_1;
		statusCode_1 =  "S"+ "_" + superAdminUserName_1;		//SAU_AT
		statusDescription_1 ="D_"+statusCode_1;
		statusCode1_1 =  statusCode_1+"_1";
		statusDescription1_1 ="D_"+statusCode_1;
		typeId_1 = superAdminUserName_1;
		groupName_1 = "GN"+ "_" + superAdminUserName_1;
		
//		for 1st user
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		TeamId = "TId"+ "_" + superAdminUserName;
		TeamName = "TN_"+ TeamId;
		statusCode =  "SC"+ "_" + superAdminUserName;
		statusDescription ="Desc_"+statusCode;
		statusCode1 =  statusCode+"_1";
		statusDescription1 ="Desc_"+statusCode;
		typeId = superAdminUserName;
		groupName = "GNs"+ "_" + superAdminUserName;
		folderName = "Account ref";
		docRef_SAU_1 = "DR1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_1 = "AR1" +"_" + superAdminUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		2nd user
//		Verify created new user is added
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminUserName_1);
		Thread.sleep(1000);
		WebElement filteredElement_1 = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser_1 = filteredElement_1.getText();
		boolean userStatus_1 = (superAdminUserName_1.equals(filteredUser_1));  		
		if (userStatus_1==false) {
			sleepFor(1000);
//			Create user
			adminUser.CreateSuperAdminUser(superAdminUserName_1, getEnvVariable);
			sleepFor(1000);
			
//			logout from the user or change user
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(superAdminUserName_1, "P@ssword123");
		}	
		else {
//			adminUser.reAssignRightsToSuperAdminUser(superAdminUserName_1, getEnvVariable);
			sleepFor(2000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}

// 		Login with new user first time
		new LoginPage(driver).loginWithCredentials(superAdminUserName_1, ObjectReader.reader.getPassword());

//		verify role is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Role maintenance", "User");
		navigationMenu.searchInFilter(Role_1);
		WebElement filteredrole_1 = navigationMenu.getfilteredRowElement("rolesTable");
		String filteredRole_1 = filteredrole_1.getText();
		boolean roleStatus_1 = (Role_1.equals(filteredRole_1));
		if (roleStatus_1==false) {
			sleepFor(1000);
//			add Role
			role.addNewRole(Role_1, RoleName_1);
			sleepFor(1000);
		}

//		verify Team is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.searchInFilter(TeamId_1);
		WebElement filteredteam_1 = navigationMenu.getfilteredRowElement("teamsTable");
		String filteredTeam_1 = filteredteam_1.getText();
		boolean teamStatus_1 = (TeamId_1.equals(filteredTeam_1));
		if (teamStatus_1== false) {
			sleepFor(1000);
//			add Team
			getApplicationUrl();
			teams.addTeam(TeamId_1, TeamName_1, RoleName_1, getEnvVariable);	
			sleepFor(1000);
		}		

		adminUser.EditUserWithTeamAndRole(superAdminUserName_1, RoleName_1, TeamName_1);	//basic user or standard user use this
		sleepFor(1000);

//		Verify mail status is added		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode_1);
		sleepFor(1000);
		WebElement filteredmailstatus_1 = navigationMenu.getfilteredRowElement("mailStatusDataTable");
		String filteredMailStatus_1 = filteredmailstatus_1.getText();
		boolean mailstatus_1 = (statusCode_1.equals(filteredMailStatus_1));
		if (mailstatus_1 == false) {
			sleepFor(1000);
//			add Mail Status
			mailStatus.selectAndAddMailStatus("Active", statusCode_1, statusDescription_1, TeamName_1, getEnvVariable);
			sleepFor(1000);
//			verify mail status is added to Team
//			teams.addMailStatusToTeam(TeamId_1, statusCode_1, getEnvVariable);
			teams.addMailStatusToTeam(TeamId_1, statusDescription_1, getEnvVariable);
		}	
	
//		verify group is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Document Type Group", "Document");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(groupName_1);
		String filteredgroup_1 = navigationMenu.getfilteredRowElement("documentTypeGroupDataTable").getText();
		boolean groupStatus_1 = (groupName_1.equals(filteredgroup_1));
		if (groupStatus_1 == false) {
			sleepFor(1000);
			
//			add Group
			getApplicationUrl();
			group.AddDocumentTypeGroup(groupName_1);
			navigationMenu.searchInFilter(groupName_1);
			sleepFor(1000);
		}		
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

//		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

//		----------------------------------------------------------------	
//		1st user
//		Verify created new user is added
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminUserName);
		Thread.sleep(1000);
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser = filteredElement.getText();
		boolean userStatus = (superAdminUserName.equals(filteredUser));  		
		if (userStatus==false) {
			sleepFor(1000);
//			Create user
			adminUser.CreateSuperAdminUser(superAdminUserName, getEnvVariable);
			sleepFor(1000);
			
//			logout from the user or change user
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(superAdminUserName, "P@ssword123");
		}	
		else {
//			adminUser.reAssignRightsToSuperAdminUser(superAdminUserName, getEnvVariable);
			sleepFor(2000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}

// 		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());

//		verify role is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Role maintenance", "User");
		navigationMenu.searchInFilter(Role);
		WebElement filteredrole = navigationMenu.getfilteredRowElement("rolesTable");
		String filteredRole = filteredrole.getText();
		boolean roleStatus = (Role.equals(filteredRole));
		if (roleStatus==false) {
			sleepFor(1000);
//			add Role
			role.addNewRole(Role, RoleName);
			sleepFor(1000);
		}

//		verify Team is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.searchInFilter(TeamId);
		WebElement filteredteam = navigationMenu.getfilteredRowElement("teamsTable");
		String filteredTeam = filteredteam.getText();
		boolean teamStatus = (TeamId.equals(filteredTeam));
		if (teamStatus== false) {
			sleepFor(1000);
//			add Team
			getApplicationUrl();
			teams.addTeam(TeamId, TeamName, RoleName, getEnvVariable);	
			sleepFor(1000);
		}		

		adminUser.EditUserWithTeamAndRole(superAdminUserName, RoleName, TeamName);	//basic user or standard user use this
		sleepFor(1000);

//		verify tag is added
		adminDoc.verifyAndAddTag(TN1);					// use this simple method to add tags
		adminDoc.verifyAndAddTag(TN2);
		adminDoc.verifyAndAddTag(TN3);		
		
//		Verify mail status is added		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode);
		sleepFor(1000);
		WebElement filteredmailstatus = navigationMenu.getfilteredRowElement("mailStatusDataTable");
		String filteredMailStatus = filteredmailstatus.getText();
		boolean mailstatus = (statusCode.equals(filteredMailStatus));
		if (mailstatus == false) {
			sleepFor(1000);
//			add Mail Status
			mailStatus.selectAndAddMailStatus("Active", statusCode, statusDescription, TeamName, getEnvVariable);
			sleepFor(1000);
//			verify mail status is added to Team
//			teams.addMailStatusToTeam(TeamId, statusCode, getEnvVariable);
			teams.addMailStatusToTeam(TeamId, statusDescription, getEnvVariable);
		}	
	
//		verify group is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Document Type Group", "Document");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(groupName);
		String filteredgroup = navigationMenu.getfilteredRowElement("documentTypeGroupDataTable").getText();
		boolean groupStatus = (groupName.equals(filteredgroup));
		if (groupStatus == false) {
			sleepFor(1000);
			
//			add Group
			getApplicationUrl();
			group.AddDocumentTypeGroup(groupName);
			navigationMenu.searchInFilter(groupName);
			sleepFor(1000);
		}		
	
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_1, accRef_SAU_1, null, ObjectReader.reader.getUserName(), getEnvVariable);
		sleepFor(2000);	
		}
	
//	Verify that User who has security permission "View other users intray" can search for the documents of other user
	@Test(priority = 2, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 2, enabled = true)
	public void ToVerifyThatUserWhoHasSecurityPermissionViewOtherUsersIntrayCanSearchForTheDocumentsOfOtherUser() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String userName = ObjectReader.reader.getUserName();
		docRef_SAU_2 = "DR2" +"_" + superAdminUserName + randomNo;
		accRef_SAU_2 = "AR2" +"_" + superAdminUserName + randomNo;
		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_2, accRef_SAU_2, null, ObjectReader.reader.getUserName(), getEnvVariable);
		sleepFor(2000);	
		
		test.log(Status.INFO, "userName============="+userName);
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		sleepFor(1000);
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getUserName(),"input-Users","input-users");		
		}else {
			docTools.selectFromTeamUnderIntray(ObjectReader.reader.getUserName());
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		navigationMenu.getColumnValueFromTable("User ID");
//		String actUserName = navigationMenu.getColumnValueFromTable("User ID");			
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(docRef_SAU_2);
		String actUserName = navigationMenu.getColumnValue("User ID");
		AssertionHelper.verifyText(actUserName, ObjectReader.reader.getUserName()); 			 		
	}

//	Verify that we can search for the document by using tags
//	Verify that we can remove selected tags from the More searching options screen
//	Verify that "Any" function work as expected when searching documents with tags
	@Test(priority = 3, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 3, enabled = true)
	public void verifySearchForDocumentUsingTagsAndRemoveSelectedTagsFromMoreSearching() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String userName = ObjectReader.reader.getUserName();
		docRef_SAU_3 = "DR3" +"_" + superAdminUserName + randomNo;
		accRef_SAU_3 = "AR3" +"_" + superAdminUserName + randomNo;
		docRef_SAU_4 = "DR4" +"_" + superAdminUserName + randomNo;
		accRef_SAU_4 = "AR4" +"_" + superAdminUserName + randomNo;
		tagRadioButton = "Any";

//		verify tag is added
		adminDoc.verifyAndAddTag(TN1);					// use this simple method to add tags
		adminDoc.verifyAndAddTag(TN2);
		adminDoc.verifyAndAddTag(TN3);
	
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_3, accRef_SAU_3, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_4, accRef_SAU_4, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);
			
//		Add tag to 1st captured document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_3);
		capture.clickOnIntrayListBtn();
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		sleepFor(3000);
		sleepFor(1000);
		sendKeys(docTools.txtAreaInAddTagPopup, TN1, "Adding the tag name as"+TN1);
//		sleepFor(2000);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sendKeys(docTools.txtAreaInAddTagPopup, TN2, "Adding the tag name as"+TN2);
		new ActionHelper(driver).pressEnter();
		windowHelper.clickOnModalFooterButton("Ok");
		sleepFor(1000);

//		Add tag to 2nd captured document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_4);
		capture.clickOnIntrayListBtn();
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(1000);
//		sleepFor(3000);
		sendKeys(docTools.txtAreaInAddTagPopup, TN1, "Adding the tag name as"+TN1);
//		sleepFor(2000);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		windowHelper.clickOnModalFooterButton("Ok");
		sleepFor(1000);
		
//		verify on intraylist
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		sleepFor(1000);
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
			sleepFor(1000);
			docTools.selectFromInputUnderMoreSearch(TN1,"input-docTags","input-docTags");		
			docTools.selectFromInputUnderMoreSearch(TN2,"input-docTags","input-docTags");		

		}else {
			docTools.selectFromTeamUnderIntray(TN1);
			docTools.selectFromTeamUnderIntray(TN2);
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		navigationMenu.getColumnValueFromTable("Tags");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(docRef_SAU_3);
		String actTagColumn = navigationMenu.getColumnValue("Tags");
		String actDocRef = navigationMenu.getColumnValue("Doc Ref");
		navigationMenu.searchInFilter(docRef_SAU_4);
		String actTagColumn1 = navigationMenu.getColumnValue("Tags");
		String actDocRef1 = navigationMenu.getColumnValue("Doc Ref");

//		Verify that we can remove selected tags from the More searching options screen
//		2 Remove the tag
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_3);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sendKeys(docTools.txtAreaInAddTagPopup, TN3, "Adding the tag name as"+TN3);
		new ActionHelper(driver).pressEnter();
		windowHelper.clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
//		verify on intraylist
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		sleepFor(1000);
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			WebElement moreEle = driver.findElement(By.xpath(String.format(docTools.tmpBtnMoreSection, "IntrayCriteriaMore")));
			new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
			sleepFor(1000);
			docTools.selectFromInputUnderMoreSearch(TN1,"input-docTags","input-docTags");			
			docTools.selectFromInputUnderMoreSearch(TN2,"input-docTags","input-docTags");			
			sleepFor(1000);
			String logmsg="Pressing back space button";
			log.info(logmsg);
			ExtentTest test=ExtentTestManager.getTest();
			test.log(Status.INFO, logmsg);
			sleepFor(2000);
			new ActionHelper(driver).pressSpecifedKey(Keys.BACK_SPACE);
			sleepFor(1000);
			docTools.selectFromInputUnderMoreSearch(TN3,"input-docTags","input-docTags");			
		}else {
			docTools.selectFromTeamUnderIntray(TN1);
			docTools.selectFromTeamUnderIntray(TN2);
			String logmsg="Pressing back space button";
			log.info(logmsg);
			ExtentTest test=ExtentTestManager.getTest();
			test.log(Status.INFO, logmsg);
			new ActionHelper(driver).pressSpecifedKey(Keys.BACK_SPACE);
			docTools.selectFromTeamUnderIntray(TN3);
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		navigationMenu.getColumnValueFromTable("Tags");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(docRef_SAU_3);
		String actTagColumn2 = navigationMenu.getColumnValue("Tags");
		String actDocRef2 = navigationMenu.getColumnValue("Doc Ref");
		navigationMenu.searchInFilter(docRef_SAU_4);
		String actTagColumn3 = navigationMenu.getColumnValue("Tags");
		String actDocRef3 = navigationMenu.getColumnValue("Doc Ref");
		
		AssertionHelper.verifyTextContains(actTagColumn, TN1); 			 		
		AssertionHelper.verifyTextContains(actTagColumn, TN2); 
		AssertionHelper.verifyTextContains(actTagColumn, TN1); 			 		
		AssertionHelper.verifyTextContains(actTagColumn1, TN1); 			 		
		AssertionHelper.verifyText(docRef_SAU_3, actDocRef);
		AssertionHelper.verifyText(docRef_SAU_4, actDocRef1);
	
		AssertionHelper.verifyTextContains(actTagColumn2, TN1); 	
		AssertionHelper.verifyTextContains(actTagColumn2, TN2); 	
		AssertionHelper.verifyTextContains(actTagColumn2, TN3); 	
		AssertionHelper.verifyTextContains(actTagColumn3, TN1); 	
		AssertionHelper.verifyText(docRef_SAU_3, actDocRef2);
		AssertionHelper.verifyText(docRef_SAU_4, actDocRef3);
	}

//	Verify that "All" function work as expected when searching documents with tags
	@Test(priority = 4, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 4, enabled = true)
	public void verifyThatAllFunctionWorkAsExpectedWhenSearchingDocumentWithTags() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String userName = ObjectReader.reader.getUserName();
		docRef_SAU_5 = "DR5" +"_" + superAdminUserName + randomNo;
		accRef_SAU_5 = "AR5" +"_" + superAdminUserName + randomNo;
		docRef_SAU_6 = "DR6" +"_" + superAdminUserName + randomNo;
		accRef_SAU_6 = "AR6" +"_" + superAdminUserName + randomNo;
		tagRadioButton = "All";
		
////	verify tag is added
		getApplicationUrl();
		adminDoc.verifyAndAddTag(TN1);					
		adminDoc.verifyAndAddTag(TN2);
		adminDoc.verifyAndAddTag(TN3);
	
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_5, accRef_SAU_5, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_6, accRef_SAU_6, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);
			
//		Add tag to 1st captured document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);
		capture.clickOnIntrayListBtn();
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
		sendKeys(docTools.txtAreaInAddTagPopup, TN1, "Adding the tag name as"+TN1);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sendKeys(docTools.txtAreaInAddTagPopup, TN2, "Adding the tag name as"+TN2);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sendKeys(docTools.txtAreaInAddTagPopup, TN3, "Adding the tag name as"+TN3);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		windowHelper.clickOnModalFooterButton("Ok");
		sleepFor(1000);

//		Add tag to 2nd captured document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_6);
		capture.clickOnIntrayListBtn();
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
		sendKeys(docTools.txtAreaInAddTagPopup, TN1, "Adding the tag name as"+TN1);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sendKeys(docTools.txtAreaInAddTagPopup, TN3, "Adding the tag name as"+TN3);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		windowHelper.clickOnModalFooterButton("Ok");
		sleepFor(1000);
		
//		verify on intraylist
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		sleepFor(1000);
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
			sleepFor(1000);
			docTools.selectFromInputUnderMoreSearch(TN1,"input-docTags","input-docTags");		
			docTools.selectFromInputUnderMoreSearch(TN2,"input-docTags","input-docTags");		
			docTools.selectFromInputUnderMoreSearch(TN3,"input-docTags","input-docTags");		
		}else {
			docTools.selectFromTeamUnderIntray(TN1);
			docTools.selectFromTeamUnderIntray(TN2);
			docTools.selectFromTeamUnderIntray(TN3);
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		navigationMenu.getColumnValueFromTable("Tags");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(docRef_SAU_5);
		String actTagName = navigationMenu.getColumnValue("Tags");
		String actDocRef = navigationMenu.getColumnValue("Doc Ref");
		navigationMenu.searchInFilter(docRef_SAU_6);
		String actUserName1 = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");

		AssertionHelper.verifyTextContains(actTagName, TN1); 			 		
		AssertionHelper.verifyTextContains(actTagName, TN2); 
		AssertionHelper.verifyTextContains(actTagName, TN3); 			 		
		AssertionHelper.verifyText(actDocRef, docRef_SAU_5);
		String expUserName1 = "No matching records found";
		AssertionHelper.verifyText(actUserName1, expUserName1);
	}

	
//	Verify that "My Search" can be created for all the users of logged in File system
//	Verify that we can create "My Search"
//	Verify that "My Search" can be pinned on the home page as a Metro Tile
	@Test(priority = 5, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 5, enabled = true)
	public void verifyThatMySearchCanBeCreatedForAllTheUsersOfLoggedInFileSystem() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String documentType1 = "Default";
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();

		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		docTools.expandMoreSectionIfHidden("IndexMore");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(documentType,"input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex(documentType);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName, scopeType, tileCheckbox, paramsList, RoleName);
		
//		verify home page tile system
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName);
		
//		verify my search is shown in original user
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		new DocumentToolsPage(driver).clickOnDocumentListButton();
		String actDocType =  new DocumentToolsPage(driver).getAllDocumentTypeValuesToSet();

//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
//		Login with other user							----->> superAdminUserName_1
		new LoginPage(driver).loginWithCredentials(superAdminUserName_1, ObjectReader.reader.getPassword());
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		verify my search tile is present in other user in same file system
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName);
		
//		visit to more search through other user			----->> this step is added to avoid glitch in 'verify my search is shown in other user in same file system' step
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		docTools.expandMoreSectionIfHidden("IndexMore");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(documentType,"input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex(documentType);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}		
		
//		verify my search is shown in other user in same file system
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		docTools.expandMoreSectionIfHidden("Index");
		WebElement inputEle = driver.findElement(By.xpath("//input[@id='input-docTypes']"));
		new JavaScriptHelper(driver).clickElement(inputEle);
		String actDocumentType = inputEle.getAttribute("value");
				
		System.out.println("---------->>> actDocumentType is " +actDocumentType );
		sleepFor(1000);
		new DocumentToolsPage(driver).clickOnDocumentListButton();
		
		String actDocType1 =  new DocumentToolsPage(driver).getAllDocumentTypeValuesToSet();
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
//		Login with other user							----->> superAdminUserName
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		getApplicationUrl();
		sleepFor(4000);

		AssertionHelper.verifyTrue(tileFlag, "Verifing home page file System tile is present");
		AssertionHelper.verifyTrue(tileFlag1, "Verifing home page file System tile is present");
		
		AssertionHelper.verifyText(actDocumentType, documentType1);
		AssertionHelper.verifyText(actDocType1, documentType1); 		
	}


//	Verify that the "My Search" can be Deleted
	@Test(priority = 6, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 6, enabled = true)
	public void verifyThatMySearchCanBeDeleted() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";

//		refresh file system
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);

		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();
		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Search"); 									// Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName, "Enetering in search =" + savedSearchInputName);

//		boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
//		if(elementStatus1 == true) {
//			WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
//			String getMsg2 = element1.getText();
//			AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
//			System.out.println("---------->> My search is deleted and no results founds");
//		}
//			else {
//				boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
//				if(elementStatus == true) {
//					WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
//					String getMsg1 = element.getText();
//					boolean status = getMsg1.contains(savedSearchInputName);
//					AssertionHelper.verifyFalse(status, "----->> My search is deleted");
//				}
//			}
		
		try {
			boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
			if(elementStatus1 == true) {
				WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
				String getMsg2 = element1.getText();
				AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
				System.out.println("---------->> My search is deleted and no results founds");
			}
		} catch (Exception e) {
			boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
			if(elementStatus == true) {
				WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
				String getMsg1 = element.getText();
				boolean status = getMsg1.contains(savedSearchInputName2);
				AssertionHelper.verifyFalse(status, "----->> My search is deleted");
			}		
		}
	}
	
//	Verify that when clicked on "Number of documents received today" metro tile, system reflects all the document captured on the given day in the particular file system
//	Verify that the number shown on the metro tile "Number of documents received today" is equal to the number of documents listed when clicked on the respective metro tile
	@Test(priority = 7, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 7, enabled = true)
	public void VerifyThatNumberShownOnMetroTileNumberOfDocumentsReceivedTodayIsEqualToTheNumberOfDocumentsListedWhenClickedOnTheRespectiveMetroTile() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String savedSearchInputName = "Outstanding items for today.";
		docRef_SAU_7 = "DR7" +"_" + superAdminUserName + randomNo;
		accRef_SAU_7 = "AR7" +"_" + superAdminUserName + randomNo;
		docRef_SAU_8 = "DR8" +"_" + superAdminUserName + randomNo;
		accRef_SAU_8 = "AR8" +"_" + superAdminUserName + randomNo;
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);

//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_7, accRef_SAU_7, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_8, accRef_SAU_8, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);

//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);
//		verify my search tile is present 
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName);
		sleepFor(15000);
		String actcountOnTileOutStandingItemsForToday = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName);
		int actcountOnTileOutStandingItemsForToday1 = Integer. parseInt(actcountOnTileOutStandingItemsForToday);
		
		new MoreSearch(driver).selectTileOnHomePage(savedSearchInputName);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		String actSearchResultIntrayDataTable = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
		String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
		
		navigationMenu.searchInFilter(docRef_SAU_7); 										
		navigationMenu.clickOnFilteredRow("searchResultIntrayDataTable");	
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");

		navigationMenu.searchInFilter(docRef_SAU_8); 										
		navigationMenu.clickOnFilteredRow("searchResultIntrayDataTable");	
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[4], actcountOnTileOutStandingItemsForToday);
		AssertionHelper.verifyText(docRef_SAU_7, ActualDocRef);
		AssertionHelper.verifyText(docRef_SAU_8, ActualDocRef1);
	}

//	Verify the Parameter popup dialogue.	
//	113434-Verify that the Outstanding Items or Outstanding items for today shows the correct number of items after a refresh
//	Verify that the number shown on the metro tile "Number of outstanding Intray Items for today" is equal to the number of documents listed when clicked on the respective metro tile
//	Verify that the number shown on the metro tile "Number of outstanding Intray Items" is equal to the number of documents listed when clicked on the respective metro tile
	@Test(priority = 8, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 8, enabled = true)
	public void VerifyThatTheOutstandingItemsOrOutstandingItemsForTodayShowsTheCorrectNumberOfItemsAfterARefresh() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String savedSearchInputName = "Outstanding items for today.";
		String savedSearchInputName1 = "Outstanding items.";
		docRef_SAU_7 = "DR7" +"_" + superAdminUserName + randomNo;
		accRef_SAU_7 = "AR7" +"_" + superAdminUserName + randomNo;
		docRef_SAU_8 = "DR8" +"_" + superAdminUserName + randomNo;
		accRef_SAU_8 = "AR8" +"_" + superAdminUserName + randomNo;
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);

//		create more search and save it
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch("Outstanding Mail Statuses","input-mailStatuses","input-mailStatuses");		//Added by amit to select user
			sleepFor(2000);
			
			click(new MoreSearch(driver).mailStatusSaveBtn, "Clicking on save btn on more search page");
			new WindowHelper(driver).waitForModalDialog("Save default mail statuses");
			new WindowHelper(driver).clickOnModalFooterButton("Save");
			sleepFor(3000);
		}else {
			docTools.selectFromMailStatusIntrayMoreSearch("Outstanding Mail Statuses", getEnvVariable);
		}

//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);

//		Get the count before capture of document on 'Outstanding items for today' and 'Outstanding item' tile 
		getApplicationUrl();
		sleepFor(5000);
		int noOfDocsCapturedWithNewStatus = 1;
		String actcountOnTileBeforeCapture = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName);
		int actcountOnTileBeforeCaptureno = Integer.parseInt(actcountOnTileBeforeCapture);
		int actCountOnTileAfterCaptureNo = actcountOnTileBeforeCaptureno + noOfDocsCapturedWithNewStatus ;
		String actcountOnTileBeforeCapture1 = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName1);
		int actcountOnTileBeforeCaptureno1 = Integer.parseInt(actcountOnTileBeforeCapture1);
		int actCountOnTileAfterCaptureNo1 = actcountOnTileBeforeCaptureno1 + noOfDocsCapturedWithNewStatus ;
		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_7, accRef_SAU_7, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_8, accRef_SAU_8, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);

//		complete the mail status of 2nd captured document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Change mail status to complete", "Mail");
		new WindowHelper(driver).waitForPopup("Update Intray Mail Status");
		String getMsg2 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg2);
		sleepFor(2000);
		new WindowHelper(driver).waitForPopup("Update Intray Mail Status");
		String getMsg3 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg3);
		sleepFor(2000);
		boolean status1 = new WindowHelper(driver).isPopupDisplayed();
		if (status1) {
			String getErrorMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get error message ====================================" + getErrorMsg1);
			sleepFor(2000);
		}		
		
//		Get the count after capture of document on 'Outstanding items for today' tile before 'refresh page'
		getApplicationUrl();
		String actcountOnTileBeforeRefresh = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName);
		String actcountOnTileBeforeRefresh1 = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName1);
		refreshPage();
		
//		Get the count after capture of document on 'Outstanding items for today' tile after 'refresh page'
		getApplicationUrl();
//		verify my search tile is present 
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName);
		sleepFor(15000);
		String actcountOnTile = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName);
		int actcountOnTileNo = Integer.parseInt(actcountOnTile);
//		Match the count after capture of document on 'Outstanding items for today' tile and inside the 'Outstanding items for today'
		new MoreSearch(driver).selectTileOnHomePage(savedSearchInputName);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		String actSearchResultIntrayDataTable = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
		String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
//		verify those captured items are present in the 'Outstanding items for today' 
		navigationMenu.searchInFilter(docRef_SAU_7); 										
		navigationMenu.clickOnFilteredRow("searchResultIntrayDataTable");	
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
	
//		Get the count after capture of document on 'Outstanding items' tile 
		getApplicationUrl();
//		verify my search tile is present 
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName1);
		sleepFor(15000);
		String actcountOnTile1 = new MoreSearch(driver).countTheNosOnTilePresentOnHomePage(savedSearchInputName1);
		int actcountOnTileNo1 = Integer.parseInt(actcountOnTile1);
//		Match the count after capture of document on 'Outstanding items for today' tile and inside the 'Outstanding items for today'
		new MoreSearch(driver).selectTileOnHomePage(savedSearchInputName1);
		sleepFor(1000);
		navigationMenu.waitForFilterTextBox();
		navigationMenu.filterTxt.click();
		new JavaScriptHelper(driver).clearText(navigationMenu.filterTxt);	
		navigationMenu.filterTxt.sendKeys("");
		sleepFor(1000);

		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		navigationMenu.waitForFilterTextBox();
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToBottom();
		String actSearchResultIntrayDataTable1 = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
		String[] actSearchResultIntrayDataTableValue1 = actSearchResultIntrayDataTable1.split(" ");  

		navigationMenu.searchInFilter(docRef_SAU_7); 										
		navigationMenu.clickOnFilteredRow("searchResultIntrayDataTable");	
		String ActualDocRef2 = navigationMenu.getColumnValueFromTable("Doc Ref");

		AssertionHelper.verifyText(actcountOnTileBeforeRefresh, actcountOnTile);
		AssertionHelper.verifyText(actcountOnTileBeforeRefresh1, actcountOnTile1);
		AssertionHelper.verifyTrue(actCountOnTileAfterCaptureNo==actcountOnTileNo, "----------->> the Outstanding items for today are matched");
		AssertionHelper.verifyTrue(actCountOnTileAfterCaptureNo1==actcountOnTileNo1, "----------->> the Outstanding items are matched");
		AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[4], actcountOnTile);
		AssertionHelper.verifyText(actSearchResultIntrayDataTableValue1[4], actcountOnTile1);
		AssertionHelper.verifyText(docRef_SAU_7, ActualDocRef);
		AssertionHelper.verifyText(docRef_SAU_7, ActualDocRef2);
		}
	
//	118514-Verify that system accepts parameter  search value (? or @) on More Search  page.
//	Verify that system displays a popup  dialogue when user selects saved search  with parameter values on it.
//	Verify the Parameter popup dialogue.
//	Verify that When the search is run,  system displays all the previously searched  parameters with blank values.
	@Test(priority = 9, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 9, enabled = true)
	public void VerifyThatWhenTheSearchIsRunSystemDisplaysAllThePreviouslySearchedParametersWithBlankValues() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String Team = "TId"+ "_" + superAdminUserName;
		String User = superAdminUserName;
		String mailStatus = "N - New";
		String Tag = "1AT_"+superAdminUserName;
		String parameterValue = "?/@";
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName = "2_"+superAdminUserName + randomNo;
		String savedSearchInputName1 = "3_"+superAdminUserName + randomNo;
		String tagRadioButton = "Any";
		
//		Verify the Parameter popup dialogue.
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
//		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);

//		create more search and save it
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IndexMore", "input-docTypes", documentType);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams", Team);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Users", User);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses", mailStatus);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("OtherCriteriaMore", "input-docTags", Tag);		
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
		sleepFor(1000);
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName, scopeType, tileCheckbox, paramsList, RoleName);
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
//		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		To avoid the glitch of not showing the values in the fields of more search we visit the more search 
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		sleepFor(4000);

//		verify my search is shown and all fields should be pre-populated
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		String actDocumentType = 
				docTools.getValueUnderMoreSearch("IndexMore", "input-docTypes");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		String actTeam = 
//				docTools.getValueUnderMoreSearch("IntrayCriteriaMore", "TeamInputs");
				docTools.getValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams");
		String actUserName = 
//				docTools.getValueUnderMoreSearch("IntrayCriteriaMore", "UserInputs");
		docTools.getValueUnderMoreSearch("IntrayCriteriaMore", "input-Users");
		String actMailStatus = 
//				docTools.getValueUnderMoreSearch("IntrayCriteriaMore", "MailStatusesInputs");
		docTools.getValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses");
		String actTagName = 
				docTools.getValueUnderMoreSearch("OtherCriteriaMore", "input-docTags");

//		post-requisite
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();

		
//		Verify that When the search is run,  system displays all the previously searched  parameters with blank values.
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);

//		create more search and save it
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IndexMore", "input-docTypes", parameterValue);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams", parameterValue);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Users", parameterValue);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses", parameterValue);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("OtherCriteriaMore", "input-docTags", parameterValue);		
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
		sleepFor(1000);
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName1, scopeType, tileCheckbox, paramsList, RoleName);
			
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);
		
//		verify my search is shown and all fields in it are blank
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName1);
		sleepFor(2000);
		String actDocumentType1 = 
				docTools.getBlankValueUnderMoreSearch( "IndexMore", "input-docTypes");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		String actTeam1 = 
				docTools.getBlankValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams");
		String actUserName1 = 
		docTools.getBlankValueUnderMoreSearch("IntrayCriteriaMore", "input-Users");
		String actMailStatus1 = 
				docTools.getBlankValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses");
		String actTagName1 = 
				docTools.getBlankValueUnderMoreSearch("OtherCriteriaMore", "input-docTags");
		
//		post-requisite
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName1);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();

		AssertionHelper.verifyText(actDocumentType1, "");
		AssertionHelper.verifyText(actTeam1, "");
		AssertionHelper.verifyText(actUserName1, "");
		AssertionHelper.verifyText(actMailStatus1, "");
		AssertionHelper.verifyText(actTagName1, "");	

		AssertionHelper.verifyTextContains(actDocumentType, documentType);
		AssertionHelper.verifyTextContains(actTeam, Team);
		AssertionHelper.verifyTextContains(actUserName, User);
		AssertionHelper.verifyTextContains(actMailStatus, mailStatus);
		AssertionHelper.verifyTextContains(actTagName, Tag);
	}
	
	
//	Verify that 'Save' button saves and updates the mail status list properly.
	@Test(priority = 10, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 10, enabled = true)
	public void verifyThatSaveButtonSavesAndUpdatesTheEmailStatusListProperly() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnIcon("Default Statuses", "Workflow");
		new WindowHelper(driver).waitForModalDialog("Save default mail statuses");
		sleepFor(2000);
		click(new HomePage(driver).MailStatusesInputs, "Clicking on mail status input");
		sleepFor(100);
		new ActionHelper(driver).pressEnter();
		sleepFor(100);
		new ActionHelper(driver).pressEscape();

		TreeSet<String> all_elements_text1 = new TreeSet<String>();
		all_elements_text1 = docTools.getAllValuesMailStatusesInputs();
		new WindowHelper(driver).clickSaveOnDefaultMailStatusPopup();
		
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		  docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch("Default Mail Statuses","input-mailStatuses","input-mailStatuses");
		}else {
			docTools.selectFromMailStatusIntrayMoreSearch("Default Mail Statuses", getEnvVariable);
		}
		
		TreeSet<String> all_elements_text2 = new TreeSet<String>();
		all_elements_text2 = docTools.getAllValuesUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses");
		boolean value = all_elements_text1.equals(all_elements_text2); 		
		AssertionHelper.verifyTrue(value, "--------->> The default mail status contains same valuew ----------------");
	}	

//	Verify that system displays last search   criteria only when user navigates on   More Search page via 'Refine Search'
//	Verify Refine search works as expected
	@Test(priority = 11, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 11, enabled = true)
	public void verifyRefineSearchWorksAsExpected() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		TeamId = "TId"+ "_" + superAdminUserName;
		TeamName = "TN_"+ TeamId;
		String documentType = "Default - General Default Document Type";
		String documentType1 = "Default";
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();

		
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		docTools.expandMoreSectionIfHidden("IndexMore");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(documentType,"input-docTypes","input-docTypes");
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex(documentType);
			sleepFor(2000);
		}
		
		capture.clickOnDocumentListBtn();
		navigationMenu.waitForFilterTextBox();
		
		navigationMenu.clickOnIcon("Refine current search", "General");
		new NavigationMenu(driver).waitForNavBarTitle("Refine Search");
		String getDocType = new DocumentToolsPage(driver).getDocTypeValue.getAttribute("data-value");
		
		docTools.expandMoreSectionIfHidden("Intray");
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(TeamName,"input-Teams","input-teams");	
		}else {
			docTools.selectFromTeamUnderIntray(TeamName);
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		String actTeamName =  navigationMenu.getColumnValueFromTable("Team");			
		AssertionHelper.verifyText(actTeamName, TeamId); 
		AssertionHelper.verifyTextContains(getDocType, documentType1);
	}	

//	Verify that Mail Statuses list box displays list of all user mail statuses
	@Test(priority = 12, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 12, enabled = true)
	public void VerifyThatMailStatusesListBoxDisplaysListOfAllUserMailStatuses() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		docRef_SAU_9 = "DR9" +"_" + superAdminUserName + randomNo;
		accRef_SAU_9 = "AR9" +"_" + superAdminUserName + randomNo;
		String statusCode =  "SC"+ "_" + superAdminUserName,
			   statusDescription ="Desc_"+statusCode;
		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9, accRef_SAU_9, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

//		Change mail status 1st time
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(statusDescription);

//		verify on intraylist
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		sleepFor(1000);
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(statusCode + " - " + statusDescription,"input-mailStatuses","input-mailStatuses");
		}else {
			docTools.selectFromMailStatusIntrayMoreSearch(statusDescription, getEnvVariable);
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		navigationMenu.getColumnValue("Mail Status Code");
		navigationMenu.getColumnValueFromTable("Mail Status");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(docRef_SAU_9);
		String actMailStatusCode = navigationMenu.getColumnValue("Mail Status Code");
		String actMailStatusDescription = navigationMenu.getColumnValueFromTable("Mail Status");
	
		AssertionHelper.verifyText(actMailStatusCode, statusCode); 			 		
		AssertionHelper.verifyText(actMailStatusDescription, statusDescription); 
	}

//	Verify 'Outstanding' checkbox functionality	
	@Test(priority = 13, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 13, enabled = true)
	public void verifyOutstandingCheckBoxFunctionality() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String mailStatus = "Outstanding Mail Statuses";
		String mailStatus1 = "Active";
		String toMailStatus2 = "Verified";
		
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		  docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(mailStatus,"input-mailStatuses","input-mailStatuses");		//Added by amit to select user
			sleepFor(2000);
		}else {
			docTools.selectFromMailStatusIntrayMoreSearch(mailStatus, getEnvVariable);
		}
		
		sleepFor(2000);
		List<WebElement> inputEle = driver.findElements(By.xpath("//div[@id='MailStatusesInputs']/..//div[@class='item']"));
		TreeSet<String> all_elements_text = new TreeSet<String>();
		sleepFor(2000);
		for(int i=0; i<inputEle.size(); i++){
			all_elements_text.add(inputEle.get(i).getText());
		}
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus1);
		sleepFor(2000);
		for(Object MailStatus:all_elements_text) {
			String fullMailStatus = MailStatus.toString();
			String [] actMailStatus = fullMailStatus.split("- ");
			navigationMenu.searchInFilter(actMailStatus[1]);
			new ActionHelper(driver).pressSpecifedKey(Keys.BACK_SPACE);
			sleepFor(100);
			sleepFor(1000);
			AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Outstanding Item"), "green tick is present for Outstanding Item column for mail status -->" + MailStatus.toString());
			}
	}	
	
	
//	Verify whether user searches gets deleted when clicked on confirmation
//	Verify whether metro tile is deleted for user searches which are deleted for the user
	@Test(priority = 14, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 14, enabled = true)
	public void verifyWhetherMetroTileIsDeletedForUserSearchesWhichAreDeletedForTheUser() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String Team = "TId"+ "_" + superAdminUserName;
		String User = superAdminUserName;
		String mailStatus = "N - New";
		String Tag = "1AT_"+superAdminUserName;
		String parameterValue = "?/@";
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName2 = superAdminUserName + "MS2_" + randomNo;
		String tagRadioButton = "Any";
//		String scopeType = "File System";
		String scopeType = "Current User";
		
//		create more search and save it
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IndexMore", "input-docTypes", documentType);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams", Team);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Users", User);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses", mailStatus);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("OtherCriteriaMore", "input-docTags", Tag);		
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
		sleepFor(1000);
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName2, scopeType, tileCheckbox, paramsList, RoleName);
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);
		
//		To avoid the glitch of not showing the values in the fields of more search we visit the more search 
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		sleepFor(4000);

//		Delete more search from user
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminUserName); // User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		navigationMenu.clickOnIconMenu("Delete ALL Saved Search", "Actions");
		
//		verification --> Verify whether user searches gets deleted when clicked on confirmation
		new WindowHelper(driver).waitForPopup("Delete ALL Saved Search");
		String rereferenceMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		AssertionHelper.verifyTextContains(rereferenceMsg, "Are you sure you want to delete all saved searches as well as tiles associated for the user '"+superAdminUserName+"'?");
		try {
			new WindowHelper(driver).waitForPopup("Delete ALL Saved Search");
			String rereferenceMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			System.out.println("<<-------------Delete ALL Saved Search--------------------"+rereferenceMsg1+"--------------------------->>");
			AssertionHelper.verifyTextContains(rereferenceMsg1, "Saved search deleted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			new WindowHelper(driver).waitForPopup("Delete Failed");
			String rereferenceMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			System.out.println("<<---------------Delete Failed------------------"+rereferenceMsg1+"--------------------------->>");
			AssertionHelper.verifyTextContains(rereferenceMsg1, "No saved searches found for selected scope '"+superAdminUserName+"'.");
		}

//		verification --> Verify whether metro tile is deleted for user searches which are deleted for the user
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Search"); 									
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName2, "Enetering in search =" + savedSearchInputName2);

		try {
			boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
			if(elementStatus1 == true) {
				WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
				String getMsg2 = element1.getText();
				AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
				System.out.println("---------->> My search is deleted and no results founds");
			}
		} catch (Exception e) {
			e.printStackTrace();
			boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
			if(elementStatus == true) {
				WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
				String getMsg1 = element.getText();
				boolean status = getMsg1.contains(savedSearchInputName2);
				AssertionHelper.verifyFalse(status, "----->> My search is deleted");
			}		
		}
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName2);
		AssertionHelper.verifyFalse(tileFlag1, "---------->> My search tile is not present");
	}
	
	
//	Verify whether role searches gets deleted when clicked on confirmation
//	Verify whether metro tile is deleted for role searches which are deleted for the role
	@Test(priority = 15, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 15, enabled = true)
	public void verifyWhetherMetroTileIsDeletedForRoleSearchesWhichAreDeletedForTheRole() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String Team = "TId"+ "_" + superAdminUserName;
		String User = superAdminUserName;
		String mailStatus = "N - New";
		String Tag = "1AT_"+superAdminUserName;
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName4 = superAdminUserName + "MS3_" + randomNo;
		String tagRadioButton = "Any";
//		String scopeType = "File System";
//		String scopeType = "Current User";
		String scopeType = "Role";
		
//		create more search and save it
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IndexMore", "input-docTypes", documentType);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams", Team);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Users", User);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses", mailStatus);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("OtherCriteriaMore", "input-docTags", Tag);		
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
		sleepFor(1000);
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName4, scopeType, tileCheckbox, paramsList, RoleName);
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);
		
//		To avoid the glitch of not showing the values in the fields of more search we visit the more search 
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		sleepFor(4000);

//		Delete more search from user
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Role maintenance", "User");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(RoleName); // User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("rolesTable");
		navigationMenu.clickOnIconMenu("Edit selected role", "Actions");

		sleepFor(2000);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		navigationMenu.clickOnIconMenu("Delete ALL Saved Search", "Actions");
		
//		verification --> Verify whether user searches gets deleted when clicked on confirmation
		new WindowHelper(driver).waitForPopup("Delete ALL Saved Search");
		String rereferenceMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		AssertionHelper.verifyTextContains(rereferenceMsg, "Are you sure you want to delete all saved searches as well as tiles associated for the role '"+Role+"'?");
		try {
			new WindowHelper(driver).waitForPopup("Delete ALL Saved Search");
			String rereferenceMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			System.out.println("<<-------------Delete ALL Saved Search--------------------"+rereferenceMsg1+"--------------------------->>");
			AssertionHelper.verifyTextContains(rereferenceMsg1, "Saved search deleted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			new WindowHelper(driver).waitForPopup("Delete Failed");
			String rereferenceMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			System.out.println("<<---------------Delete Failed------------------"+rereferenceMsg1+"--------------------------->>");
			AssertionHelper.verifyTextContains(rereferenceMsg1, "No saved searches found for selected scope '"+Role+"'.");
		}

//		verification --> Verify whether metro tile is deleted for user searches which are deleted for the user
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Search"); 									
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName4, "Enetering in search =" + savedSearchInputName4);

		try {
			boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
			if(elementStatus1 == true) {
				WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
				String getMsg2 = element1.getText();
				AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
				System.out.println("---------->> My search is deleted and no results founds");
			}
		} catch (Exception e) {
			e.printStackTrace();
			boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
			if(elementStatus == true) {
				WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
				String getMsg1 = element.getText();
				boolean status = getMsg1.contains(savedSearchInputName4);
				AssertionHelper.verifyFalse(status, "----->> My search is deleted");
			}		
		}
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName4);
		AssertionHelper.verifyFalse(tileFlag1, "---------->> My search tile is not present");
	}

//	Verify whether file system searches gets deleted when clicked on confirmation
//	Verify whether metro tile is deleted for file system searches which are deleted for the file system	
	@Test(priority = 16, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 16, enabled = true)
	public void verifyWhetherMetroTileIsDeletedForFileSystemSearchesWhichAreDeletedForTheFileSystem() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String documentType = "Default - General Default Document Type";
		String Team = "TId"+ "_" + superAdminUserName;
		String User = superAdminUserName;
		String mailStatus = "N - New";
		String Tag = "1AT_"+superAdminUserName;
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName4 = superAdminUserName + "MS4_" + randomNo;
		String tagRadioButton = "Any";
		String scopeType = "File System";
//		String scopeType = "Current User";
//		String scopeType = "Role";
		
//		create more search and save it
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IndexMore", "input-docTypes", documentType);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams", Team);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Users", User);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses", mailStatus);		
		sleepFor(2000);
		docTools.enterValueUnderMoreSearch("OtherCriteriaMore", "input-docTags", Tag);		
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
		sleepFor(1000);
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName4, scopeType, tileCheckbox, paramsList, RoleName);
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(2000);
		
//		To avoid the glitch of not showing the values in the fields of more search we visit the more search 
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		sleepFor(4000);

//		Delete more search from user
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("File System maintenance", "File System");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(ObjectReader.reader.getFileSystemName()); // User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("fileSystemDataTable");
		navigationMenu.clickOnIconMenu("Edit selected file system", "Actions");

		sleepFor(2000);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		navigationMenu.clickOnIconMenu("Delete ALL Saved Search", "Actions");
		
//		verification --> Verify whether user searches gets deleted when clicked on confirmation
		new WindowHelper(driver).waitForPopup("Delete ALL Saved Search");
		String rereferenceMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		AssertionHelper.verifyTextContains(rereferenceMsg, "Are you sure you want to delete all saved searches as well as tiles associated for the file system '"+ObjectReader.reader.getFileSystemName()+"'?");
		try {
			new WindowHelper(driver).waitForPopup("Delete ALL Saved Search");
			String rereferenceMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			System.out.println("<<-------------Delete ALL Saved Search--------------------"+rereferenceMsg1+"--------------------------->>");
			AssertionHelper.verifyTextContains(rereferenceMsg1, "Saved search deleted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			new WindowHelper(driver).waitForPopup("Delete Failed");
			String rereferenceMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			System.out.println("<<---------------Delete Failed------------------"+rereferenceMsg1+"--------------------------->>");
			AssertionHelper.verifyTextContains(rereferenceMsg1, "No saved searches found for selected scope '"+ObjectReader.reader.getFileSystemName()+"'.");
		}

//		verification --> Verify whether metro tile is deleted for user searches which are deleted for the user
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Search"); 									
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName4, "Enetering in search =" + savedSearchInputName4);

		try {
			boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
			if(elementStatus1 == true) {
				WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
				String getMsg2 = element1.getText();
				AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
				System.out.println("---------->> My search is deleted and no results founds");
			}
		} catch (Exception e) {
			e.printStackTrace();
			boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
			if(elementStatus == true) {
				WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
				String getMsg1 = element.getText();
				boolean status = getMsg1.contains(savedSearchInputName4);
				AssertionHelper.verifyFalse(status, "----->> My search is deleted");
			}		
		}
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName4);
		AssertionHelper.verifyFalse(tileFlag1, "---------->> My search tile is not present");
	}
	
//	Verify that  selected field from dropdown gets added to  quick search and highlighted.
//	Verify that selected field  from Quick search list page is displayed under Quicksearch widget dropdown with proper sorting.
	@Test(priority = 17, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 17, enabled = true)
	public void verifyThatSelectedFieldFromDropdownGetsAddedToQuickSearchAndHighlighted() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
	
//		pre-requisite --> check newly checked document & also verify previously checked documents
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Flexible Index maintenance", "File System");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		navigationMenu.clickOnIconMenu("Toggle system fields On and Off", "File System");
		
		String [] quickSearchContents = {"Protective Marker Id", "Doc Ref", "Doc Ref 2", "Priority", 
//										 "Account", "Misc", "Property", 
										 };

		for(int i=0; i<=quickSearchContents.length-1; i++)
		{
			indexAdmin.selectDocument(quickSearchContents[i]);
			sleepFor(1000);
			indexAdmin.checkOptimisedForSearchingCheckBox();
		}

		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Publish the schema", "Publish");
		new WindowHelper(driver).waitForPopup("Publish the schema");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(1000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(6000);
		System.out.println("get message ====================================" + getMsg);		//No changes to save.
		sleepFor(3000);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);

		
//		Testcase
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		
		navigationMenu.clickOnIconMenu("Quick Search", "File System");
		navigationMenu.waitForIcon("Cancel changes", "Actions");

		ArrayList<String> docs = new ArrayList<String>();
//		docs.add("Context");
		docs.add("ProtectiveMarkerId");
		quickSearch.addDocument(docs, getEnvVariable);
		navigationMenu.clickOnIconMenu("Save Quick Search Results", "Actions");
		new WindowHelper(driver).waitForPopup("Success");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		sleepFor(1000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(6000);
		System.out.println("get message ====================================" + getMsg1);		//No changes to save.
		sleepFor(3000);		
		navigationMenu.waitForIcon("Cancel changes", "Actions");
		
		ArrayList<String> all_Documents = new ArrayList<String>();
		all_Documents = quickSearch.getListOfDocuments();
		all_Documents.set(all_Documents.indexOf("Doc_Ref"), "Doc Ref");
		all_Documents.set(all_Documents.indexOf("Doc_Ref2"), "Doc Ref 2");
		all_Documents.set(all_Documents.indexOf("Folder1_Ref"), "Account Ref");
		all_Documents.set(all_Documents.indexOf("Folder2_Ref"), "Misc Ref");
		all_Documents.set(all_Documents.indexOf("Folder3_Ref"), "Property Ref");
		all_Documents.set(all_Documents.indexOf("Folder1_Ref2"), "Alt Account Ref");
		all_Documents.set(all_Documents.indexOf("ProtectiveMarkerId"), "Protective Marker Id");
		
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Search");
		click(capture.searchDD,"Clicking on search dropdown");
		
		ArrayList<String> all_Documents1 = new ArrayList<String>();
		all_Documents1 = quickSearch.searchListOfDocuments();
		System.out.println("---------------------------all_Documents-------------->>" + all_Documents);
		System.out.println("---------------------------all_Documents1-------------->>" + all_Documents1);
		
		AssertionHelper.verifyTrue(all_Documents.equals(all_Documents1), "----->> Both the list contains same documents");
		
//		post-requisite --> uncheck newly checked document 
		getApplicationUrl();
		home.refreshCurrentFileSystem();
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Quick Search", "File System");
		navigationMenu.waitForIcon("Cancel changes", "Actions");
		ArrayList<String> docs1 = new ArrayList<String>();
		quickSearch.deleteDocument("ProtectiveMarkerId");
//		quickSearch.deleteDocument("Context");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Flexible Index maintenance", "File System");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		navigationMenu.clickOnIconMenu("Toggle system fields On and Off", "File System");
		indexAdmin.selectDocument("Protective Marker Id");
		indexAdmin.uncheckOptimisedForSearchingCheckBox();
	}
	
	
	@DataProvider(name="newFileSystemData")
	public Object[][] newFileSystemData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newFileSystemData");
				
		return formData;
	}
	
//	112725-To verify whether a search tile appears in other file system when a system search is created in any file system ending with letter D //	Verify that the number shown on the metro tile "Number of documents received today" is equal to the number of documents listed when clicked on the respective metro tile
	@Test(priority = 100, enabled = true, dataProvider = "newFileSystemData", dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 100, enabled = true, dataProvider = "newFileSystemData")
	public void ToVerifyWhetherASearchTileAppearsInOtherFileSystemWhenASystemSearchIsCreatedInAnyFileSystemEndingWithLetterD(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String savedSearchInputName1 = randomNo + superAdminUserName + "_MS_" +  "_D";

//		pre-requisite
//		login with imasys user and create a new user with file system ending with letter "D"
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("imasys", "iawdefault");
		
//		Verify created new user is added
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminUserName_D);
		Thread.sleep(1000);
		WebElement filteredElement_D = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser_D = filteredElement_D.getText();
		boolean userStatus_D = (superAdminUserName_D.equals(filteredUser_D));  		
		if (userStatus_D==false) {
			sleepFor(1000);
//			Create user
			adminUser.CreateSuperAdminUser(superAdminUserName_D, getEnvVariable);
			sleepFor(1000);
			
//			logout from the user or change user
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(superAdminUserName_D, "P@ssword123");
		}	
		else {
//			adminUser.reAssignRightsToSuperAdminUser(superAdminUserName_D, getEnvVariable);
//			sleepFor(2000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}

// 		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(superAdminUserName_D, ObjectReader.reader.getPassword());

//		For user with file system ending with letter "D"
//		String FileSystemName = "BD";
		String FSDescription = "For Auto Test";
		String userName = superAdminUserName_D;
		String	folder1EntityName=map.get("folder1Name"),
		folder2EntityName=  map.get("folder2Name"),
		folder3EntityName =  map.get("folder3Name");
		boolean folder1chk=Boolean.parseBoolean(map.get("folder1chk")),
		folder2chk=Boolean.parseBoolean(map.get("folder2chk")),
		folder3chk=Boolean.parseBoolean(map.get("folder3chk"));
		String cacheServerPart = map.get("serverPath");
		String cacheServer = cacheServerPart +"\\Data\\Docs\\ (Online)";
		
//		add new file system ending with letter "D"
		adminFS.AddFileSystem(FileSystemName, userName, FSDescription, folder1EntityName, folder2EntityName, folder3EntityName, folder1chk, folder2chk, folder3chk, cacheServer, getEnvVariable);
//		verification :- file system is added 
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("File System maintenance","File System");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(FileSystemName);
		WebElement actColumnValueXpath = navigationMenu.getfilteredRowElement("fileSystemDataTable");
		String actColumnValue = actColumnValueXpath.getText();
		AssertionHelper.verifyText(actColumnValue, FileSystemName);
		home.refreshCurrentFileSystem();
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

//		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
//		--------------------------------------------------------
//		create more search and save it
		String documentType = "Default - General Default Document Type";
		String documentType1 = "Default";
		boolean tileCheckbox = true;
		String Role = "R"+ "_" + superAdminUserName;
		String RoleName = "RN_"+ "_" + Role;
		List<String> paramsList = new ArrayList<>();

		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		docTools.expandMoreSectionIfHidden("IndexMore");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(documentType,"input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex(documentType);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
//		create Home page title
		new MoreSearch(driver).CreateHomePageTile(savedSearchInputName1, scopeType, tileCheckbox, paramsList, RoleName);
//		verify home page tile system
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName1);

//		verify my search is shown in original user
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName1);
		sleepFor(2000);
		new DocumentToolsPage(driver).clickOnDocumentListButton();
		String actDocType =  new DocumentToolsPage(driver).getAllDocumentTypeValuesToSet();
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
//		Login with other user							----->> superAdminUserName_1
		new LoginPage(driver).loginWithCredentials(superAdminUserName_D, ObjectReader.reader.getPassword());
		
//		refresh file system
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(FileSystemName);
		sleepFor(2000);
		
//		verify my search tile is present in other user in same file system
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(savedSearchInputName1);
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
//		Login with other user							----->> superAdminUserName_1
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		
//		Delete the created more search
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName1);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();
		
		AssertionHelper.verifyTrue(tileFlag, "---------->> Verifing home page file System tile is present on same file system");
		AssertionHelper.verifyText(actDocType, documentType1); 	
		AssertionHelper.verifyFalse(tileFlag1, "---------->> Verifing home page file System tile is not present on other file system");
	}	
}











