package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.Status;

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
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import main.EDRM.hybridFramework.pageObject.Admin.Group;
import main.EDRM.hybridFramework.pageObject.Admin.MailStatus;
import main.EDRM.hybridFramework.pageObject.Admin.Role;
import main.EDRM.hybridFramework.pageObject.Admin.Teams;
import main.EDRM.hybridFramework.pageObject.Bundle.Bundling;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;
import utils.ExtentReports.ExtentTestManager;

/*
 * Suite created by Sagar
 */

public class TestIntray_UAT5 extends TestBase {
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
	private Logger log = LoggerHelper.getLogger(CapturePage.class);
	public AdminUserSection adminuser;

	private String envType="NECDM_Lives";
	private String randomNo = generateRandomNumber();
	private String superAdminUserName = "SAU_AT", 
	fileSystemAdminUserName = "FSA_AT", 
	supervisorUserName = "SUV_AT", 
	standardUserName = "STD_AT", 
	basicUserName = "BAS_AT"; 	
	private String Role, RoleName, TeamId, TeamName, statusCode, statusDescription, typeId, groupName, folderName;

	private String 	docRef_SAU_1, accRef_SAU_1, docRef_SAU_2, accRef_SAU_2, docRef_SAU_2_1, docRef_SAU_3, accRef_SAU_3, docRef_SAU_3_1, accRef_SAU_3_1, 
					docRef_SAU_4, accRef_SAU_4, docRef_SAU_5, accRef_SAU_5, docRef_SAU_6, accRef_SAU_6, docRef_SAU_6_1,  accRef_SAU_6_1, docRef_SAU_6_2, accRef_SAU_6_2, docRef_SAU_6_3, accRef_SAU_6_3, docRef_SAU_6_4, accRef_SAU_6_4,  docRef_SAU_7_1, accRef_SAU_7_1,  docRef_SAU_7_2, accRef_SAU_7_2, docRef_SAU_8, accRef_SAU_8, accRef_SAU_8_1,  miscRef_SAU_8_1, propRef_SAU_8_1, accRef_SAU_8_2,  miscRef_SAU_8_2, propRef_SAU_8_2, docRef_SAU_9, accRef_SAU_9, docRef_SAU_9_1, accRef_SAU_9_1, docRef_SAU_10, accRef_SAU_10, docRef_SAU_11, accRef_SAU_11, docRef_SAU_12, accRef_SAU_12,
					docRef_FSA_1, accRef_FSA_1, docRef_FSA_2, accRef_FSA_2, docRef_FSA_3, accRef_FSA_3, docRef_FSA_3_1, accRef_FSA_3_1, docRef_FSA_3_2, accRef_FSA_3_2, docRef_FSA_3_3, accRef_FSA_3_3, docRef_FSA_3_4, accRef_FSA_3_4, docRef_FSA_3_5, accRef_FSA_3_5, docRef_FSA_3_6, accRef_FSA_3_6, docRef_FSA_3_5_1, accRef_FSA_3_5_1, docRef_FSA_5, accRef_FSA_5,  docRef_FSA_7_1, accRef_FSA_7_1, docRef_FSA_7_2, accRef_FSA_7_2, docRef_FSA_8, accRef_FSA_8, accRef_FSA_8_1,  miscRef_FSA_8_1, propRef_FSA_8_1, accRef_FSA_8_2,  miscRef_FSA_8_2, propRef_FSA_8_2,
					docRef_SUV_1, accRef_SUV_1, docRef_SUV_2, accRef_SUV_2, docRef_SUV_3, accRef_SUV_3, docRef_SUV_3_1, accRef_SUV_3_1, docRef_SUV_3_2, accRef_SUV_3_2, docRef_SUV_3_3, accRef_SUV_3_3, docRef_SUV_3_4, accRef_SUV_3_4, docRef_SUV_3_5, accRef_SUV_3_5, docRef_SUV_3_6, accRef_SUV_3_6, docRef_SUV_3_5_1, accRef_SUV_3_5_1, docRef_SUV_5, accRef_SUV_5, docRef_SUV_7_1, accRef_SUV_7_1, docRef_SUV_7_2, accRef_SUV_7_2, docRef_SUV_8, accRef_SUV_8, accRef_SUV_8_1,  miscRef_SUV_8_1, propRef_SUV_8_1, accRef_SUV_8_2,  miscRef_SUV_8_2, propRef_SUV_8_2,
					docRef_STD_1, accRef_STD_1, docRef_STD_2, accRef_STD_2, docRef_STD_3, accRef_STD_3, docRef_STD_3_1, accRef_STD_3_1, docRef_STD_3_2, accRef_STD_3_2, docRef_STD_3_3, accRef_STD_3_3, docRef_STD_3_4, accRef_STD_3_4, docRef_STD_4, accRef_STD_4, docRef_STD_4_1, accRef_STD_4_1, docRef_STD_4_2, accRef_STD_4_2, docRef_STD_4_1_1, accRef_STD_4_1_1, docRef_STD_5, accRef_STD_5,  docRef_STD_7_1, accRef_STD_7_1, docRef_STD_7_2, accRef_STD_7_2, docRef_STD_8, accRef_STD_8, accRef_STD_8_1,  miscRef_STD_8_1, propRef_STD_8_1, accRef_STD_8_2,  miscRef_STD_8_2, propRef_STD_8_2,
					docRef_BAS_1, accRef_BAS_1, docRef_BAS_2, accRef_BAS_2, docRef_BAS_3, accRef_BAS_3, docRef_BAS_3_1, accRef_BAS_3_1, docRef_BAS_3_2, accRef_BAS_3_2,  docRef_BAS_8, accRef_BAS_8, accRef_BAS_8_1,  miscRef_BAS_8_1, propRef_BAS_8_1, accRef_BAS_8_2,  miscRef_BAS_8_2, propRef_BAS_8_2;
		
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
		adminuser = new AdminUserSection(driver);
	}

//	e2e
	@Test(priority = 1, enabled = true)
	public void prerequisiteForSuperAdminUser() throws InterruptedException {
//		superAdminUserName = "SAU_AT";							//"SAU"+randomNo;
		Role = "R"+ "_" + superAdminUserName;
		RoleName = "RN_"+ "_" + Role;
		TeamId = "TId"+ "_" + superAdminUserName;
		TeamName = "TN_"+ TeamId;
		statusCode =  "SC"+ "_" + superAdminUserName;
		statusDescription ="Desc_"+statusCode;
		typeId = superAdminUserName;
		groupName = "GN"+ "_" + superAdminUserName;
		folderName = "Account ref";
		docRef_SAU_1 = "DR1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_1 = "AR1" +"_" + superAdminUserName + randomNo;
		docRef_SAU_2 = "DR2" +"_" + superAdminUserName + randomNo;
		accRef_SAU_2 = "AR2" +"_" + superAdminUserName + randomNo;
		docRef_SAU_3 = "DR3" +"_" + superAdminUserName + randomNo;
		accRef_SAU_3 = "AR3" +"_" + superAdminUserName + randomNo;
		docRef_SAU_4 = "DR4" +"_" + superAdminUserName + randomNo;
		accRef_SAU_4 = "AR4" +"_" + superAdminUserName + randomNo;
		docRef_SAU_5 = "DR5" +"_" + superAdminUserName + randomNo;
		accRef_SAU_5 = "AR5" +"_" + superAdminUserName + randomNo;
		docRef_SAU_6 = "DR6" +"_" + superAdminUserName + randomNo;
		accRef_SAU_6 = "AR6" +"_" + superAdminUserName + randomNo;
		docRef_SAU_7_1 = "DR7_1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_7_1 = "AR7_1" +"_" + superAdminUserName + randomNo;
		docRef_SAU_7_2 = "DR7_2" +"_" + superAdminUserName + randomNo;
		accRef_SAU_7_2 = "AR7_2" +"_" + superAdminUserName + randomNo;
		docRef_SAU_8 = "DR8" +"_" + superAdminUserName + randomNo;
		accRef_SAU_8 = "AR8" +"_" + superAdminUserName + randomNo;
		docRef_SAU_9 = "DR9" +"_" + superAdminUserName + randomNo;
		accRef_SAU_9 = "AR9" +"_" + superAdminUserName + randomNo;
		docRef_SAU_9_1 = "DR9_1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_9_1 = "AR9_1" +"_" + superAdminUserName + randomNo;
		
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		Verify created new user is added
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
			adminUser.EditUserWithTeamAndRole(superAdminUserName, RoleName, TeamName);	//basic user or standard user use this
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
//			sleepFor(2000);
//			adminUser.EditUserWithTeamAndRole(superAdminUserName, RoleName, TeamName);	//basic user or standard user use this
//			sleepFor(1000);

//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}

// 		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());

////		verify role is added
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIconMenu("Role maintenance", "User");
//		navigationMenu.searchInFilter(Role);
//		WebElement filteredrole = navigationMenu.getfilteredRowElement("rolesTable");
//		String filteredRole = filteredrole.getText();
//		boolean roleStatus = (Role.equals(filteredRole));
//		if (roleStatus==false) {
//			sleepFor(1000);
////			add Role
//			role.addNewRole(Role, RoleName);
//			sleepFor(1000);
//		}
//
////		verify Team is added
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIcon("Team maintenance", "User");
//		navigationMenu.searchInFilter(TeamId);
//		WebElement filteredteam = navigationMenu.getfilteredRowElement("teamsTable");
//		String filteredTeam = filteredteam.getText();
//		boolean teamStatus = (TeamId.equals(filteredTeam));
//		if (teamStatus== false) {
//			sleepFor(1000);
////			add Team
//			getApplicationUrl();
//			teams.addTeam(TeamId, TeamName, RoleName, getEnvVariable);	
//			sleepFor(1000);
//		}		
//		
////		Verify mail status is added		
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
//		sleepFor(2000);
//		navigationMenu.clickOnIconMenu("Active");
//		navigationMenu.waitForIcon("Add a new mail status");
//		navigationMenu.searchInFilter(statusCode);
//		sleepFor(1000);
//		WebElement filteredmailstatus = navigationMenu.getfilteredRowElement("mailStatusDataTable");
//		String filteredMailStatus = filteredmailstatus.getText();
//		boolean mailstatus = (statusCode.equals(filteredMailStatus));
//		if (mailstatus == false) {
//			sleepFor(1000);
////			add Mail Status
//			mailStatus.selectAndAddMailStatus("Active", statusCode, statusDescription, TeamName, getEnvVariable);
//			sleepFor(1000);
////			verify mail status is added to Team
//			teams.addMailStatusToTeam(TeamId, statusCode, getEnvVariable);
//		}	
//		
////		verify group is added
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIcon("Document Type Group", "Document");
//		sleepFor(2000);
//		navigationMenu.waitForAddIcon();
//		navigationMenu.searchInFilter(groupName);
//		String filteredgroup = navigationMenu.getfilteredRowElement("documentTypeGroupDataTable").getText();
//		boolean groupStatus = (groupName.equals(filteredgroup));
//		if (groupStatus == false) {
//			sleepFor(1000);
////			add Group
//			getApplicationUrl();
//			group.AddDocumentTypeGroup(groupName);
//			navigationMenu.searchInFilter(groupName);
//			sleepFor(1000);
//		}		
//		
////		add document type with groupName
////		getApplicationUrl();
////		adminDoc.addDocumentType(typeId, groupName, getEnvVariable);
//		
//		
////		capture documents 	------------>> docRef_SAU_1 to docRef_SAU_9 are for unit testing flows test case no. 6 to 34
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_1, accRef_SAU_1, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SAU_2, accRef_SAU_2, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_3, accRef_SAU_3, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_4, accRef_SAU_4, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_5, accRef_SAU_5, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//	
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_6, accRef_SAU_6, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//		
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_7_1, accRef_SAU_7_1, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//		
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_7_2, accRef_SAU_7_2, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
//		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_8, accRef_SAU_8, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

//		
//		getApplicationUrl();
////		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9_1, accRef_SAU_9_1, "To Team", TeamName, getEnvVariable);
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9_1, accRef_SAU_9_1, null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	
		
////		Verify document is captured
//		getApplicationUrl();
//		capture.searchWithCriteria("Doc Ref",docRef_SAU_9_1);
//		capture.clickOnDocumentListBtn();
//		capture.clickOnFirstItemOfIntray();
//		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
//		AssertionHelper.verifyText(docRef_SAU_9_1, ActualDocumentRef);	
	}
	
	@Test(priority = 200, enabled = true)
	public void prerequisiteForFileSystemAdminUser() throws InterruptedException {
//		fileSystemAdminUserName = "FSA_AT";						
		Role = "R"+ "_" + fileSystemAdminUserName;
		RoleName = "RN_"+ "_" + Role;
		TeamId = "TId"+ "_" + fileSystemAdminUserName;
		TeamName = "TN_"+ TeamId;
		statusCode =  "SC"+ "_" + fileSystemAdminUserName;
		statusDescription ="Desc_"+statusCode;
		typeId = fileSystemAdminUserName;
		groupName = "GN"+ "_" + fileSystemAdminUserName;
		folderName = "Account ref";
		docRef_FSA_1 = "DR1" +"_" + fileSystemAdminUserName + randomNo;
		accRef_FSA_1 = "AR1" +"_" + fileSystemAdminUserName + randomNo; 
		docRef_FSA_2 = "DR2" +"_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_2 = "AR2" +"_" + fileSystemAdminUserName + randomNo;  
		docRef_FSA_3 = "DR3" +"_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_3 = "AR3" +"_" + fileSystemAdminUserName + randomNo;				
		docRef_FSA_5 = "DR4" +"_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_5 = "AR4" +"_" + fileSystemAdminUserName + randomNo;			
		docRef_FSA_7_1 = "DR7_1" +"_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_7_1 = "AR7_1" +"_" + fileSystemAdminUserName + randomNo;			
		docRef_FSA_7_2 = "DR7_2" +"_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_7_2 = "AR7_2" +"_" + fileSystemAdminUserName + randomNo;			
		docRef_FSA_8 = "DR8" +"_" + fileSystemAdminUserName + randomNo;
		accRef_FSA_8 = "AR8" +"_" + fileSystemAdminUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		logout from the user or change user
		getApplicationUrl();
		sleepFor(4000);
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

// 		For new user change password
		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
//		Log out from the user
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		Verify created new user is added
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(fileSystemAdminUserName);
		Thread.sleep(1000);
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser = filteredElement.getText();
		boolean userStatus = (fileSystemAdminUserName.equals(filteredUser));  		
		if (userStatus==false) {
			sleepFor(1000);
			
//			Create user
			adminUser.CreateFileSystemAdminUser(fileSystemAdminUserName, getEnvVariable);
			sleepFor(1000);
			adminUser.EditUserWithTeamAndRole(superAdminUserName, RoleName, TeamName);	//basic user or standard user use this
			sleepFor(1000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(fileSystemAdminUserName, "P@ssword123");
		}	
		else {
			adminUser.reAssignRightsToFileSystemAdminUser(fileSystemAdminUserName, getEnvVariable);
			sleepFor(2000);
			adminUser.EditUserWithTeamAndRole(superAdminUserName, RoleName, TeamName);	//basic user or standard user use this
			sleepFor(1000);
				
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}

// 		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUserName, ObjectReader.reader.getPassword());

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
			getApplicationUrl();
			sleepFor(1000);
			mailStatus.selectAndAddMailStatus("Active", statusCode, statusDescription, TeamName, getEnvVariable);
			sleepFor(1000);
//		verify mail status is added to Team
		teams.addMailStatusToTeam(TeamId, statusCode, getEnvVariable);
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
		
//		add document type with groupName
//		getApplicationUrl();
//		adminDoc.addDocumentType(typeId, groupName, getEnvVariable);
		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_1, accRef_FSA_1, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_FSA_2, accRef_FSA_2, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_3, accRef_FSA_3, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_5, accRef_FSA_5, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_7_1, accRef_FSA_7_1, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_7_2, accRef_FSA_7_2, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_8, accRef_FSA_8, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

	}
	
	@Test(priority = 300, enabled = true)
	public void prerequisiteForSupervisorUser() throws InterruptedException {
//		supervisorUserName = "SUV_AT";								
		Role = "R"+ "_" + supervisorUserName;
		RoleName = "RN_"+ "_" + Role;
		TeamId = "TId"+ "_" + supervisorUserName;
		TeamName = "TN_"+ TeamId;
		statusCode =  "SC"+ "_" + supervisorUserName;
		statusDescription ="Desc_"+statusCode;
		typeId = supervisorUserName;
		groupName = "GN"+ "_" + supervisorUserName;
		folderName = "Account ref";
		docRef_SUV_1 = "DR1" +"_" + supervisorUserName + randomNo;
		accRef_SUV_1 = "AR1" +"_" + supervisorUserName + randomNo;
		docRef_SUV_2 = "DR2" +"_" + supervisorUserName + randomNo;
		accRef_SUV_2 = "AR2" +"_" + supervisorUserName + randomNo;
		docRef_SUV_3 = "DR3" +"_" + supervisorUserName + randomNo;
		accRef_SUV_3 = "AR3" +"_" + supervisorUserName + randomNo;
		docRef_SUV_5 = "DR5" +"_" + supervisorUserName + randomNo; 
		accRef_SUV_5 = "AR5" +"_" + supervisorUserName + randomNo;	
		docRef_SUV_7_1 = "DR7_1" +"_" + supervisorUserName + randomNo; 
		accRef_SUV_7_1 = "AR7_1" +"_" + supervisorUserName + randomNo;	
		docRef_SUV_7_2 = "DR7_2" +"_" + supervisorUserName + randomNo; 
		accRef_SUV_7_2 = "AR7_2" +"_" + supervisorUserName + randomNo;	
		docRef_SUV_8 = "DR8" +"_" + supervisorUserName + randomNo; 
		accRef_SUV_8 = "AR8" +"_" + supervisorUserName + randomNo;	
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		logout from the user or change user
		getApplicationUrl();
		sleepFor(4000);
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

// 		For new user change password
		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

//		change file system
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		Verify created new user is added
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(supervisorUserName);
		Thread.sleep(1000);
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser = filteredElement.getText();
		boolean userStatus = (supervisorUserName.equals(filteredUser));  		
		if (userStatus==false) {
			sleepFor(1000);
//			Create user
			adminUser.CreateSupervisorUser(supervisorUserName, getEnvVariable);
			sleepFor(1000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(supervisorUserName, "P@ssword123");
		}	
		
		else {
				adminUser.reAssignRightsToSupervisorUser(supervisorUserName, getEnvVariable);
				sleepFor(2000);
				
//			logout from the user or change user
				new HomePage(driver).clickOnChangeUser();
				sleepFor(2000);
		}
		
// 		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(supervisorUserName, ObjectReader.reader.getPassword());

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
			getApplicationUrl();
			sleepFor(1000);
			mailStatus.selectAndAddMailStatus("Active", statusCode, statusDescription, TeamName, getEnvVariable);
			sleepFor(1000);
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
		
//		add document type with groupName
//		getApplicationUrl();
//		adminDoc.addDocumentType(typeId, groupName, getEnvVariable);
		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SUV_1, accRef_SUV_1, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SUV_2, accRef_SUV_2, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SUV_3, accRef_SUV_3, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SUV_5, accRef_SUV_5, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SUV_7_1, accRef_SUV_7_1, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SUV_7_2, accRef_SUV_7_2, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_SUV_8, accRef_SUV_8, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	
	}
	
	@Test(priority = 400, enabled = true)
	public void prerequisiteForStandardUser() throws InterruptedException {
//		standardUserName = "STD_AT";										//"STD"+randomNo;
		Role = "R"+ "_" + standardUserName;
		RoleName = "RN_"+ "_" + Role;
		TeamId = "TId"+ "_" + standardUserName;
		TeamName = "TN_"+ TeamId;
		statusCode =  "SC"+ "_" + standardUserName;
		statusDescription ="Desc_"+statusCode;
		typeId = standardUserName;
		groupName = "GN"+ "_" + standardUserName;
		folderName = "Account ref";
		docRef_STD_1 = "DR1" +"_" + standardUserName + randomNo;
		accRef_STD_1 = "AR1" +"_" + standardUserName + randomNo; 
		docRef_STD_2 = "DR2" +"_" + standardUserName + randomNo; 
		accRef_STD_2 = "AR2" +"_" + standardUserName + randomNo;
		docRef_STD_3 = "DR3" +"_" + standardUserName + randomNo; 
		accRef_STD_3 = "AR3" +"_" + standardUserName + randomNo;
		docRef_STD_4 = "DR4" +"_" + standardUserName + randomNo; 
		accRef_STD_4 = "AR4" +"_" + standardUserName + randomNo;
		docRef_STD_5 = "DR5" +"_" + standardUserName + randomNo; 
		accRef_STD_5 = "AR5" +"_" + standardUserName + randomNo;
		docRef_STD_7_1 = "DR7_1" +"_" + standardUserName + randomNo; 
		accRef_STD_7_1 = "AR7_1" +"_" + standardUserName + randomNo;
		docRef_STD_7_2 = "DR7_2" +"_" + standardUserName + randomNo; 
		accRef_STD_7_2 = "AR7_2" +"_" + standardUserName + randomNo;
		docRef_STD_8 = "DR8" +"_" + standardUserName + randomNo; 
		accRef_STD_8 = "AR8" +"_" + standardUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		logout from the user or change user
		getApplicationUrl();
		sleepFor(4000);
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

// 		For new user change password
		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		Verify created new user is added
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(standardUserName);
		Thread.sleep(1000);
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser = filteredElement.getText();
		boolean userStatus = (standardUserName.equals(filteredUser));  		
		if (userStatus==false) {
			sleepFor(1000);
			
//			Create user
			adminUser.CreateStandardUser(standardUserName, getEnvVariable);
			sleepFor(1000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(standardUserName, "P@ssword123");
		}	
		
		else {
			adminUser.reAssignRightsToStandardUser(standardUserName, getEnvVariable);
			sleepFor(2000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}

// 		Login with super admin user to capture document for Basic user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_STD_1, accRef_STD_1, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_2, accRef_STD_2, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_3, accRef_STD_3, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_4, accRef_STD_4, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_5, accRef_STD_5, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_7_1, accRef_STD_7_1, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_7_2, accRef_STD_7_2, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_STD_8, accRef_STD_8, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);		
		
// 		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(standardUserName, ObjectReader.reader.getPassword());
	}
	
	@Test(priority = 500, enabled = true)
	public void prerequisiteForBasicUser() throws InterruptedException {
//		basicUserName = "BAS_AT";											//"BAS"+randomNo;
		Role = "R"+ "_" + basicUserName;
		RoleName = "RN_"+ "_" + Role;
		TeamId = "TId"+ "_" + basicUserName;
		TeamName = "TN_"+ TeamId;
		statusCode =  "SC"+ "_" + basicUserName;
		statusDescription ="Desc_"+statusCode;
		typeId = basicUserName;
		groupName = "GN"+ "_" + basicUserName;
		folderName = "Account ref";
		docRef_BAS_1 = "DR1" +"_" + basicUserName + randomNo;
		accRef_BAS_1 = "AR1" +"_" + basicUserName + randomNo; 
		docRef_BAS_2 = "DR2" +"_" + basicUserName + randomNo;
		accRef_BAS_2 = "AR2" +"_" + basicUserName + randomNo;
		docRef_BAS_3 = "DR3" +"_" + basicUserName + randomNo;
		accRef_BAS_3 = "AR3" +"_" + basicUserName + randomNo;
		docRef_BAS_8 = "DR8" +"_" + basicUserName + randomNo;
		accRef_BAS_8 = "AR8" +"_" + basicUserName + randomNo;

//		logout from the user or change user
		getApplicationUrl();
		sleepFor(4000);
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

// 		For new user change password
		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		Verify created new user is added
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(basicUserName);
		Thread.sleep(1000);
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser = filteredElement.getText();
		boolean userStatus = (basicUserName.equals(filteredUser));  		
		if (userStatus==false) {
			sleepFor(1000);
//			Create user
			adminUser.CreateBasicUser(basicUserName, getEnvVariable);
			sleepFor(1000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		For new user change password
			new LoginPage(driver).loginWithCredentialsForChangePassword(basicUserName, "P@ssword123");
		}	
		else {
			adminUser.reAssignRightsToBasicUser(basicUserName, getEnvVariable);
			sleepFor(2000);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
		}
		
// 		Login with super admin user to capture document for Basic user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_BAS_1, accRef_BAS_1, null, basicUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_BAS_2, accRef_BAS_2, null, basicUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_BAS_3, accRef_BAS_3, null, basicUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "sample.pdf", null, docRef_BAS_8, accRef_BAS_8, null, basicUserName, getEnvVariable);
		sleepFor(2000);	
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);		

// 		Login with new user first time
		new LoginPage(driver).loginWithCredentials(basicUserName, ObjectReader.reader.getPassword());
	}

	
////=============================================================================================================================	
////=============================================================================================================================	
//	For Super Admin User
//	done
//	To verify that user without Administrative rights can search the captured document using Intray search
//	@Test(priority = 6, enabled = true)
	@Test(priority = 6, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatUserWithoutAdministrativeRightsCanSearchTheCapturedDocumentUsingIntraySearch() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		//pre-requisite : Logged in user should not have administrative rights
		//remove administrative right
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminUserName); 									
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Administrator");
		userPermission.put("System Administration", systemAdministrationList);
		new AdminUserSection(driver).RemoveSecurityRightsFromUser(userPermission);
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		sleepFor(1000);
		
//		To verify that user without Administrative rights can search the captured document using Intray search
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);					
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef);					
		sleepFor(3000);
		
		//post-requisite : Logged in user should have administrative rights
		//add administrative right
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminUserName); 										// User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);

		systemAdministrationList.add("Is Administrator");
		userPermission.put("System Administration", systemAdministrationList);
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		sleepFor(1000);
 	}
	
	
//	pending
////	Verify the UI of Intray List page
//	@Test(priority = 7, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	public void a() throws InterruptedException {
//		String getEnvVariable = System.getProperty("propertyName");
//		System.out.println("getEnvVariable====================" + getEnvVariable);
//
//		getApplicationUrl();
//		capture.searchWithCriteria("Doc Ref",docRef4);
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
//		AssertionHelper.verifyText(docRef4, ActualDocumentRef);
//		sleepFor(3000);
//		
//		
//	}

	
//	done
//	Verify the columns displayed on Intray List page.
//	@Test(priority = 8, enabled = true)
	@Test(priority = 8, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyColumnsDisplayedOnIntrayListPage() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4		"DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef);			//docRef4		"DR4_SAU_AT6744"
		sleepFor(3000);
		String DocumentType = navigationMenu.getColumnValueFromTable("Document Type");
		System.out.println("-------- Document Type is "+ DocumentType);
		sleepFor(2000);
		String DocRef2 = navigationMenu.getColumnValueFromTable("Doc Ref 2");
		System.out.println("-------- Doc Ref 2 is "+ DocRef2);
		sleepFor(2000);
		String AccountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		System.out.println("-------- Account Ref is "+ AccountRef);
		sleepFor(2000);
		String PropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");
		System.out.println("-------- Property Ref is "+ PropertyRef);
		sleepFor(2000);
		String GUID = navigationMenu.getColumnValueFromTable("Guid");
		System.out.println("-------- GUID is "+ GUID);
		sleepFor(2000);
		String DateReceived = navigationMenu.getColumnValueFromTable("Date Received");
		System.out.println("-------- Date Received is "+ DateReceived );
		sleepFor(2000);
		String DocumentTypeDescription = navigationMenu.getColumnValueFromTable("Document Type Description");
		System.out.println("-------- Document Type Description is "+ DocumentTypeDescription);
		sleepFor(2000);
		String Tags = navigationMenu.getColumnValueFromTable("Tags");
		System.out.println("-------- Tags is "+ Tags );
		sleepFor(2000);
		String AccountAddress = navigationMenu.getColumnValueFromTable("Account Address");
		System.out.println("-------- Account Address is "+ AccountAddress);
		sleepFor(2000);
		String PropertyAddress = navigationMenu.getColumnValueFromTable("Property Address");
		System.out.println("-------- Property Address is "+ PropertyAddress);
		sleepFor(2000);
		String Priority = navigationMenu.getColumnValueFromTable("Priority");
		System.out.println("-------- Priority is "+ Priority );
		sleepFor(2000);
		String MailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		System.out.println("-------- Mail Status is "+ MailStatus );
		sleepFor(2000);
		String Nr_notes = navigationMenu.getColumnValueFromTable("nr_notes");
		System.out.println("-------- nr_notes is "+ Nr_notes);
		sleepFor(2000);
		String NrPages = navigationMenu.getColumnValueFromTable("nr_notes");
		System.out.println("-------- Nr pages is "+ NrPages);			//--> not visible
		sleepFor(2000);
		String TargetDate = navigationMenu.getColumnValueFromTable("nr_notes");
		System.out.println("-------- Target date is "+ TargetDate);		//--> not visible
		sleepFor(2000);
		String FileType = navigationMenu.getColumnValueFromTable("File Type");
		System.out.println("-------- File Type is "+ FileType);
	}
	
	
//	done
//	Verify default active buttons on Intray list page
//	@Test(priority = 9, enabled = true)
	@Test(priority = 9, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyDefaultActiveButtonsOnIntrayListPage() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		
		String [] buttonsIconToolTip = {"Re-run the same search to refresh the lists","Refine current search","Show results statistics"};
		for(int i=0; i<=buttonsIconToolTip.length-1; i++){
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "The Element "+ buttonsIconToolTip[i] +"is enabled");
		}
	}
	
//	done
//	Verify that relative buttons gets activated when user selects any of the document from the list.
//	@Test(priority = 10, enabled = true)
	@Test(priority = 10, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyThatRelativeButtonsGetActivatedWhenUserSelectsAnyOfTheDocumentFromTheList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
//		new JavaScriptHelper(driver).zoomByPercentage("80");
		sleepFor(2000);
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();

		String [] buttonsIconToolTip = {"Re-run the same search to refresh the lists","Refine current search","Show results statistics"};
		for(int i=0; i<=buttonsIconToolTip.length-1; i++){
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "---------- The Element "+ buttonsIconToolTip[i] +"is enabled");
			sleepFor(1000);
		}
		capture.clickOnFirstItemOfIntray();

//		collapse all navigation menu
		String [] section = {"Document", "Mail", "Version Control", "Case", "Records Management"};
		for(int i=0; i<=section.length-1; i++)
		{
			navigationMenu.collapseNavigationMenu(section[i]);
		}
		sleepFor(2000);
		
//		new JavaScriptHelper(driver).zoomByPercentage("80");
//		sleepFor(2000);
		String auditActionXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Audit Actions");
		WebElement auditActionButton =driver.findElement(By.xpath(auditActionXpath));
		if(new VerificationHelper(driver).isElementDisplayedByEle(auditActionButton)) {
			navigationMenu.expandNavigationMenu("Document");
		}
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(1000);
		String [] buttonsIconToolTip1 = {
		"Email document link", 
		"Re-run the same search to refresh the lists", "Refine current search","Show results statistics",
		"View document", "Launch document in external viewer", "Add new document to case", 
		"Create a copy of document", "Reindex document", "Select entity", 
		"Manage tags for selected document", "Intray landing page", "Add additional index to document", 
		"Index PDF as TiFF", "Searchable Pdf", "Notes", "Audit Actions", 
		};
		for(int i=0; i<=buttonsIconToolTip1.length-1; i++)
		{
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip1[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "---------- The Element " + buttonsIconToolTip1[i] + " is enabled");
			sleepFor(1000);
		}
		sleepFor(2000);
//		navigationMenu.expandNavigationMenu("Document");
		navigationMenu.collapseNavigationMenu("Document");
		sleepFor(2000);		
	
		////new JavaScriptHelper(driver).zoomByPercentage("80");
		//sleepFor(2000);
		String distributeIntrayItemXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Distribute intray item");
		WebElement distributeIntrayItemButton =driver.findElement(By.xpath(distributeIntrayItemXpath));
		if(new VerificationHelper(driver).isElementDisplayedByEle(distributeIntrayItemButton)) {
			navigationMenu.expandNavigationMenu("Mail");
		}
//		navigationMenu.expandNavigationMenu("Mail");
//		sleepFor(2000);
		String [] buttonsIconToolTip2 = {
		"Change mail status", "Change mail status to complete",
		"Show document associated with this account", "Intray History", "Distribute intray item",
		"Mark intray item urgent"
		};
		for(int i=0; i<=buttonsIconToolTip2.length-1; i++)
		{
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip2[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "---------- The Element " + buttonsIconToolTip2[i] + " is enabled");
			sleepFor(1000);
		}
		sleepFor(2000);
//		navigationMenu.expandNavigationMenu("Mail");
		navigationMenu.collapseNavigationMenu("Mail");
		sleepFor(2000);	
		
		////new JavaScriptHelper(driver).zoomByPercentage("80");
		//sleepFor(2000);
		String recaptureDocumentXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Recapture Document");
		WebElement recaptureDocumentButton =driver.findElement(By.xpath(recaptureDocumentXpath));
		if(new VerificationHelper(driver).isElementDisplayedByEle(recaptureDocumentButton)) {
			navigationMenu.expandNavigationMenu("Version Control");
		}
//		navigationMenu.expandNavigationMenu("Mail");
//		sleepFor(2000);
		String [] buttonsIconToolTip3 = {
		"Show document version history list", "Update document", "Undo edit", "Recapture Document", "Edit document"
		};
		for(int i=0; i<=buttonsIconToolTip3.length-1; i++)
		{
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip3[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "---------- The Element " + buttonsIconToolTip3[i] + " is enabled");
			sleepFor(1000);
		}
		sleepFor(2000);
//		navigationMenu.expandNavigationMenu("Version Control");
		navigationMenu.collapseNavigationMenu("Version Control");
		sleepFor(2000);	

//		new JavaScriptHelper(driver).zoomByPercentage("80");
//		sleepFor(2000);
		String addANewMemoToCaseXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new memo to case");
		WebElement addANewMemoToCaseButton =driver.findElement(By.xpath(addANewMemoToCaseXpath));
		if(new VerificationHelper(driver).isElementDisplayedByEle(addANewMemoToCaseButton)) {
			navigationMenu.expandNavigationMenu("Case");
		}
//		navigationMenu.expandNavigationMenu("Case");
//		sleepFor(1000);
		String [] buttonsIconToolTip4 = {
		"View Case Sub-Folder", "Assign to master document", "Add new memo to case",
		"Add ", 
		"Produce letters from document list.", "Produce an adhoc letter", 
		"Generate Email", 
		"Select Integration"
		};
		for(int i=0; i<=buttonsIconToolTip4.length-1; i++)
		{
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip4[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "---------- The Element " + buttonsIconToolTip4[i] + " is enabled");
			sleepFor(1000);
		}	
		sleepFor(2000);
//		navigationMenu.expandNavigationMenu("Case");
		navigationMenu.collapseNavigationMenu("Case");
		sleepFor(2000);
	
//		new JavaScriptHelper(driver).zoomByPercentage("80");
//		sleepFor(2000);
		String viewRecordDetailsXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Distribute intray item");
		WebElement viewRecordDetailsButton =driver.findElement(By.xpath(viewRecordDetailsXpath));
		if(new VerificationHelper(driver).isElementDisplayedByEle(viewRecordDetailsButton)) {
			navigationMenu.expandNavigationMenu("Records Management");
		}
//		navigationMenu.expandNavigationMenu("Records Management");
//		sleepFor(1000);
		String [] buttonsIconToolTip5 = {
		"View record declaration", 									
		"View record details"
		};
		for(int i=0; i<=buttonsIconToolTip5.length-1; i++)
		{
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip5[i]);
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyTrue(Button, "---------- The Element " + buttonsIconToolTip5[i] + " is enabled");
			sleepFor(1000);
		}
		sleepFor(2000);
//		navigationMenu.expandNavigationMenu("Records Management");
		navigationMenu.collapseNavigationMenu("Records Management");
		sleepFor(2000);
	}
	
//	Verify that Refresh functionality works as expected.
//	@Test(priority = 11, enabled = true)
	@Test(priority = 11, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyRefreshFunctionalityWorksAsExpected() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);					
		capture.clickOnIntrayListBtn();
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef);					
		sleepFor(3000);
		
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef1);			
		AssertionHelper.verifyText(ActualDocumentRef, ActualDocumentRef1);
	}
	
//	Verify that Statistics functionality works as expected.
//	@Test(priority = 12, enabled = true)
	@Test(priority = 12, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyStatisticsFunctionalityWorksAsExpected() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");

		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
		System.out.println("The intray count is =========" + actIntrayCount);
		System.out.println("The account ref count is ========" + actAccountRefCount);

		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
	}
	
//	Verify that user can Add a document using 'Add' functionality
//	@Test(priority = 13, enabled = true)
	@Test(priority = 13, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyUserCanAddADocumentUsingAddFunctionality() throws InterruptedException {
		docRef_SAU_3_1 = "DR3_1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_3_1 = "AR3_1" +"_" + superAdminUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_3);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_3_1, accRef_SAU_3_1, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_3_1);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef_SAU_3_1, ActDocRef);
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");
		AssertionHelper.verifyText(accRef_SAU_3_1, ActAccRef);
	}
	
//	Verify that user remains on same page after selecting cancel button while deleting the selected document.
//	@Test(priority = 14, enabled = true)
	@Test(priority = 14, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyUserRemainsOnSamePageAfterSelectingCancelButtonWhileDeletingTheSelectedDocument() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_4);						//docRef6		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Delete document", "Document");

		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOnCancelButton();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);

		//verification
//		capture.clickOnFirstItemOfIntray();
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_4);						//docRef6		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");
		AssertionHelper.verifyText(docRef_SAU_4, ActDocRef);
		AssertionHelper.verifyText(accRef_SAU_4, ActAccRef);								
	}
	
//	Verify that document gets deleted after selecting Ok button while deleting the selected document.
//	@Test(priority = 15, enabled = true)
	@Test(priority = 15, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser", "VerifyUserRemainsOnSamePageAfterSelectingCancelButtonWhileDeletingTheSelectedDocument"})
//	@Test(priority = 15, enabled = true, dependsOnMethods = {"VerifyUserRemainsOnSamePageAfterSelectingCancelButtonWhileDeletingTheSelectedDocument"})
	public void verifyDocumentGetsDeletedAfterSelectingOkButtonWhileDeletingSelectedDocument() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_4);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Delete document", "Document");

		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);
		
		if(getMsg == "The selected document(s) are either locked or check-out for deleting.") {
//	 		verify document is locked
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(1000);
			String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
				
			String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
			WebElement button1 =driver.findElement(By.xpath(Xpath1));
			boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
			sleepFor(2000);	

			AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
			AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
		}
		else if(getMsg == "Are you sure you want to Delete the highlighted Document(s)") {
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
			sleepFor(3000);

//	 		verify document is deleted
//			capture.clickOnFirstItemOfIntray();
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_SAU_4);						//docRef3		 "DR4_SAU_AT6744"
			capture.clickOnIntrayListBtn();
//			String verifyEmptyTable = capture.verifyEmptyTable.getText();
			String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
			String expMsgInEmptyTable = "No items available";
			AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);
		}
		

	}
	
//	Verify that user can lock the document using 'lock' button.
//	@Test(priority = 16, enabled = true)
	@Test(priority = 16, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyUserCanLockTheDocumentUsingLockButton() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_5);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
		WebElement button =driver.findElement(By.xpath(Xpath));
		boolean Button = new VerificationHelper(driver).isElementEnabled(button);
		
		String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
		WebElement button1 =driver.findElement(By.xpath(Xpath1));
		boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
		
		sleepFor(2000);
		navigationMenu.clickOnIcon("Lock document", "Document");
		new WindowHelper(driver).waitForPopup("Lock Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);		
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);

		String Xpath2= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
		WebElement button2 =driver.findElement(By.xpath(Xpath2));
		if(new VerificationHelper(driver).isElementDisplayedByEle(button2)) {
			navigationMenu.expandNavigationMenu("Document");
		}
		boolean Button2 = new VerificationHelper(driver).isElementEnabled(button2);
			
		String Xpath3 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
		WebElement button3 =driver.findElement(By.xpath(Xpath3));
		boolean Button3 = new VerificationHelper(driver).isElementEnabled(button3);
		sleepFor(2000);	

//		navigationMenu.expandNavigationMenu("Document");
//		navigationMenu.collapseNavigationMenu("Document");
//		sleepFor(2000);	
		
		navigationMenu.clickOnIcon("Edit document", "Version Control");

		new WindowHelper(driver).waitForPopup("Edit Document");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		String expMsg1 = "The document is currently locked from editing.";
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg1);
		sleepFor(3000);		
		AssertionHelper.verifyTrue(Button, "The Element Lock document is enabled before locking the document");
		AssertionHelper.verifyFalse(Button1, "The Element Unlock document is disabled before locking the document");
		AssertionHelper.verifyFalse(Button2, "The Element Lock document is disabled after locking the document");
		AssertionHelper.verifyTrue(Button3, "The Element Unlock document is enabled after locking the document");
		AssertionHelper.verifyText(getMsg1, expMsg1);
	}
	
//	Verify that user can Unlock the locked document using 'Unlock' button.
//	@Test(priority = 17, enabled = true, dependsOnMethods = {"verifyUserCanLockTheDocumentUsingLockButton"})
	@Test(priority = 17, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser", "verifyUserCanLockTheDocumentUsingLockButton"})
	public void verifyUserCanUnlockTheLockedDocumentUsingUnlockButton() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_5);		
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
		WebElement button =driver.findElement(By.xpath(Xpath));
		boolean Button = new VerificationHelper(driver).isElementEnabled(button);
		
		String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
		WebElement button1 =driver.findElement(By.xpath(Xpath1));
		boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
		
		sleepFor(2000);
		navigationMenu.clickOnIcon("Unlock document", "Document");
		new WindowHelper(driver).waitForPopup("Unlock Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);		
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(1000);
		String Xpath2= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
		WebElement button2 =driver.findElement(By.xpath(Xpath2));
		boolean Button2 = new VerificationHelper(driver).isElementEnabled(button2);
			
		String Xpath3 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
		WebElement button3 =driver.findElement(By.xpath(Xpath3));
		boolean Button3 = new VerificationHelper(driver).isElementEnabled(button3);
//		navigationMenu.expandNavigationMenu("Document");

		navigationMenu.collapseNavigationMenu("Document");

		AssertionHelper.verifyTrue(Button, "The Element 'Unlock document' is enabled before unlocking the document");
		AssertionHelper.verifyFalse(Button1, "The Element 'Lock document' is disabled before unlocking the document");
		AssertionHelper.verifyFalse(Button2, "The Element 'unLock document' is disabled after unlocking the document");
		AssertionHelper.verifyTrue(Button3, "The Element 'lock document' is enabled after unlocking the document");
	}
	
//	Verify that user cannot index non pdf document to TIFF using 'Index PDF as TIFF' button
//	@Test(priority = 18, enabled = true)
	@Test(priority = 18, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyUserCannotIndexNonPdfDocumentToTIFFUsingIndexPdfAsTIFFButton() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);				
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Index PDF as TiFF", "Document");
		
		sleepFor(2000);
		new WindowHelper(driver).waitForPopup("Index PDF as TiFF");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		String expMsg1 = "You can only select this option for document of file type .pdf";
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg1);
		sleepFor(3000);		
		AssertionHelper.verifyText(getMsg1, expMsg1);
	}
	
//	done
//	Verify that user index pdf document to TIFF using 'Index PDF as TIFF' button
//	@Test(priority = 19, enabled = true)
	@Test(priority = 19, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyUserIndexPdfDocumentToTIFFUsingIndexPdfAsTIFFButton() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String docRef_SAU_2_1 = "DR2_1" +"_" + superAdminUserName + randomNo;

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_2);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Index PDF as TiFF", "Document");
		navigationMenu.waitForMenuIcon("Index the following changes", "Actions", getEnvVariable);

		capture.enterDocRef(docRef_SAU_2_1);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		capture.selectRouteToDD(superAdminUserName,getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD("Default - General Default Document Type",getEnvVariable);
		sleepFor(1000);
		capture.clickOnIndexDocument();		
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		
//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_2_1);		
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActFileType = navigationMenu.getColumnValueFromTable("File Type");
		AssertionHelper.verifyText(ActFileType, ".tif" );	
		}
	
////	pending
////	Verify that user can mark the document as 'Urgent' using Urgent button
////	@Test(priority = 20, enabled = true)
//	@Test(priority = 20, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser", "verifyUserCanUnlockTheLockedDocumentUsingUnlockButton"})
//	public void verifyUserCanMarkTheDocumentAsUrgentUsingUrgentButton() throws InterruptedException {
//		String getEnvVariable = System.getProperty("propertyName");
//		System.out.println("getEnvVariable====================" + getEnvVariable);
//		
//		getApplicationUrl();
//		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);						//docRef3		 "DR4_SAU_AT6744"
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//
////		new JavaScriptHelper(driver).zoomByPercentage("80");
//		sleepFor(2000);	
//		navigationMenu.clickOnIcon("Mark intray item urgent", "Urgent Status");
//		
//		
//		new WindowHelper(driver).waitForPopup("Urgent Status");
//		String getMsg = new WindowHelper(driver).getPopupMessage();
//		sleepFor(2000);
//		new WindowHelper(driver).clickOkOnPopup();
//		sleepFor(2000);
//		System.out.println("get message ====================================" + getMsg);
//		sleepFor(3000);		
//		
////		====================================================================================================
//////		need to discuss with Amit
////		solution got as 
////		AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Outstanding Item"), "Checkking if green tick present for Outstanding Item column");
////		need to implement here in this test case
////		====================================================================================================
////		intrayTools.isImageLoaded("Urgent");
////		====================================================================================================
////		verification
//		getApplicationUrl();
//		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);				
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//		String ActUrgentColumnValue = navigationMenu.getColumnValueFromTable("Urgent");
////		AssertionHelper.verifyText("", ActUrgentColumnValue);		
//	}
	
//	Verify that user can remove the urgent status of document using Unmark button 
//	@Test(priority = 21, enabled = true)
	@Test(priority = 21, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 21, enabled = true, dependsOnMethods = {"verifyUserCanMarkTheDocumentAsUrgentUsingUrgentButton"})
	public void verifyThanUserCanRemoveTheUrgentStatusOfDocumentUsingUnmarkButton() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);					
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Remove urgent marker", "Urgent Status");
		new WindowHelper(driver).waitForPopup("Urgent Status");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);		
		
//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);						//docRef3		 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActUrgentColumnValue = navigationMenu.getColumnValueFromTable("Urgent");
		AssertionHelper.verifyText("", ActUrgentColumnValue);	
	}
	
	
//	Verify that user can edit document metadata from Intray page
//	@Test(priority = 22, enabled = true)
	@Test(priority = 22, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyUserCanEditDocumentMetadataFromIntrayPage() throws InterruptedException {
		String docRef2_1 = "DR1_2_"+ docRef_SAU_1;
		String markerID = "Protect";
		String mailOption = null;
		String priority = null;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		Edit Meta Data
		intrayTools.editMetaData(markerID, mailOption, priority, docRef_SAU_1, docRef2_1, getEnvVariable);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);						//AmitK ==> check, some changes made under click on icon method by sagar on 21/09/2023	
		capture.clickOnIntrayListBtn();
		sleepFor(2000);	
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);	
		String ActProtectiveMarkerID = navigationMenu.getColumnValueFromTable("Protective Marker");
		String ActdocRef2_1 = navigationMenu.getColumnValueFromTable("Doc Ref 2");
		AssertionHelper.verifyText(markerID, ActProtectiveMarkerID);					
		AssertionHelper.verifyText(docRef2_1, ActdocRef2_1);					
	}
	
////	pending
////	Verify that Intray item can be moved to Pending when pending selected falls on Holiday and 
////	is greater than current date by 20 days
//	@Test(priority = 23, enabled = true)
////	@Test(priority = 23, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	public void verifyIntrayItemCanBeMovedToPendingWhenPendingSelectedFallsOnHolidayAndGreaterThanCurrentDateBy20Days() throws InterruptedException {
//		String getEnvVariable = System.getProperty("propertyName");
//		System.out.println("getEnvVariable====================" + getEnvVariable);
//		getApplicationUrl();
//		
//		
//	}
	
//	Verify that on Intray list when system refreshes for any reason, it navigates back to intray list itself and not to Document list
//	@Test(priority = 24, enabled = true)
	@Test(priority = 24, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyIntrayListWhenSystemRefreshesItNavigatesBackToIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						
		capture.clickOnIntrayListBtn();
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();

    	AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef);			
    	AssertionHelper.verifyTextContains(ActTitleTxt, "In-Tray");
	}

//  Verify that Refresh button  is available and is Able to refresh on Document / Intray Tool ribbon for Super Admin User
//	@Test(priority = 25, enabled = true)
	@Test(priority = 25, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyRefreshButtonIsAvailableAndIsAbleToRefreshForSuperAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		//for intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();

		//for document list
    	getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);					
		capture.clickOnDocumentListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();

    	AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef);					
    	AssertionHelper.verifyTextContains(ActTitleTxt, "In-Tray");
		AssertionHelper.verifyText(docRef_SAU_1, ActualDocumentRef1);				
    	AssertionHelper.verifyTextContains(ActTitleTxt1, "Document Search");
	}	
	
	
//	Verify that Statistics button  is available and  is Able to view statistics on Document Tool ribbon for Super Admin User 
//	@Test(priority = 26, enabled = true)
	@Test(priority = 26, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyThatStatisticsButtonIsAvailableAndIsAbleToViewStatisticsOnDocumentToolRibbon() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");

		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
		System.out.println("The intray count is =========" + actIntrayCount);
		System.out.println("The account ref count is ========" + actAccountRefCount);

		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
	}	
	
//	Verify that Statistics button  is available and  is Able to view statistics on Document / Intray Tool ribbon for Super Admin User can 
//	@Test(priority = 27, enabled = true)
	@Test(priority = 27, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyThatStatisticsButtonIsAvailableAndIsAbleToViewStatisticsOnIntrayToolRibbonForSuperAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");

		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);

//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");

		new WindowHelper(driver).waitForPopup("Statistics");
		String actDocumentCount1 = intrayTools.statisticPopupDocumentCount.getText();
		String actAccountRefCount1 = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
		AssertionHelper.verifyTextContains("1", actDocumentCount1);
		AssertionHelper.verifyTextContains("1", actAccountRefCount1);	
	}
	
	
//	Verify that View button  is available and is able to view on Document / Intray Tool ribbon for Super Admin User can 
	@Test(priority = 28, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void verifyViewButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolForSuperAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Document List
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}	

//		For Intray List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
//	Verify that Launch button  is available and is able to view the document on Document / Intray Tool ribbon for Super Admin User 
	@Test(priority = 29, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyLaunchButtonIsAvailableAndIsAbleToViewTheDocumentOnDocumentAndIntrayForSuperAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
	}

//	Verify that copy button  is available and is able to copy document on Document / Intray Tool ribbon for Super Admin User can 
	@Test(priority = 30, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void CopyButtonIsAvailableAndIsAbleToCopyTheDocumentOnDocumentAndIntrayToolRibbonForSuperAdminUser() throws InterruptedException {
		docRef_SAU_6_1 = "DR6_1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_6_1 = "AR6_1" +"_" + superAdminUserName + randomNo;
		docRef_SAU_6_2 = "DR6_2" +"_" + superAdminUserName + randomNo;
		accRef_SAU_6_2 = "AR6_2" +"_" + superAdminUserName + randomNo;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		copy document using copy button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_6);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
//		navigationMenu.expandNavigationMenu("Document");
		navigationMenu.collapseNavigationMenu("Document");

		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_SAU_6_1+"_and_"+accRef_SAU_6_1);
		new AlertHelper(driver).acceptAlertIfPresent();
		sleepFor(2000);
		capture.enterDocRef(docRef_SAU_6_1);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(superAdminUserName,getEnvVariable);

		if(accRef_SAU_6_1!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_SAU_6_1, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_SAU_6_1);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_6_1);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

		
//		copy document using copy button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_6);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
//		navigationMenu.expandNavigationMenu("Document");
		navigationMenu.collapseNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_SAU_6_2+"_and_"+accRef_SAU_6_2);
		new AlertHelper(driver).acceptAlertIfPresent();
		capture.enterDocRef(docRef_SAU_6_2);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(superAdminUserName,getEnvVariable);

		if(accRef_SAU_6_2!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_SAU_6_2, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_SAU_6_2);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SAU_6_2);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

		AssertionHelper.verifyText(docRef_SAU_6_1, ActDocRef);
		AssertionHelper.verifyText(accRef_SAU_6_1, ActAccRef);
		AssertionHelper.verifyText(docRef_SAU_6_2, ActDocRef1);
		AssertionHelper.verifyText(accRef_SAU_6_2, ActAccRef1);
	}

//		Verify that Add button  is available and is able to add the document with add button on Document / Intray Tool ribbon for Super Admin User can 
		@Test(priority = 31, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void AddButtonIsAvailableAndIsAbleToAddTheDocumentWithAddButtonOnDocumentAndIntrayToolRibbonForSuperAdminUser() throws InterruptedException {
			docRef_SAU_6_3 = "DR6_3" +"_" + superAdminUserName + randomNo;
			accRef_SAU_6_3 = "AR6_3" +"_" + superAdminUserName + randomNo;
			docRef_SAU_6_4 = "DR6_4" +"_" + superAdminUserName + randomNo;
			accRef_SAU_6_4 = "AR6_4" +"_" + superAdminUserName + randomNo;
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);

//			copy document using copy button in intraylist
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_6);						
			capture.clickOnIntrayListBtn();
			sleepFor(3000);	
			capture.clickOnFirstItemOfIntray();
			
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(2000);	
			if(envType.equals("Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
				sleepFor(2000);		
			}
			else if(envType.equals("NECDM_Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
				sleepFor(2000);		
			}
			navigationMenu.expandNavigationMenu("Document");

			navigationMenu.clickOnIcon("Create a copy of document", "Document");
			
			log.info("capturing doc with "+"_"+docRef_SAU_6_3+"_and_"+accRef_SAU_6_3);
			new AlertHelper(driver).acceptAlertIfPresent();
			sleepFor(2000);
			capture.enterDocRef(docRef_SAU_6_3);
			if(accRef_SAU_6_3!=null) {
				boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_SAU_6_3, "Account Ref");
				log.info("Flag value of success is "+success);
				if(!success) {
					new WindowHelper(driver).clickOkOnPopup();
					navigationMenu.waitForIconWithDataButton("Save", "Actions");
				  capture.enterFolder1Ref(accRef_SAU_6_3);
				  capture.clickOnSaveButton();
				  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
				}
			}
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
			capture.clickOnIndexDocument();
			try {
				 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
				 capture.clickOkOnSuccesfullPopup();
				 getApplicationUrl();
			} catch (Exception e) {
				getApplicationUrl();
			}
			sleepFor(2000);	

			//verification
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_SAU_6_3);						
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();

			String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
			String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

			
//			copy document using copy button in Document list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_6);						
			capture.clickOnDocumentListBtn();
			sleepFor(3000);	
			capture.clickOnFirstItemOfIntray();
			
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(2000);	
			if(envType.equals("Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
				sleepFor(2000);		
			}
			else if(envType.equals("NECDM_Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
				sleepFor(2000);		
			}
			navigationMenu.expandNavigationMenu("Document");
			
			navigationMenu.clickOnIcon("Create a copy of document", "Document");
			
			log.info("capturing doc with "+"_"+docRef_SAU_6_4+"_and_"+accRef_SAU_6_4);
			new AlertHelper(driver).acceptAlertIfPresent();
			capture.enterDocRef(docRef_SAU_6_4);
			if(accRef_SAU_6_4!=null) {
				boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_SAU_6_4, "Account Ref");
				log.info("Flag value of success is "+success);
				if(!success) {
					new WindowHelper(driver).clickOkOnPopup();
					navigationMenu.waitForIconWithDataButton("Save", "Actions");
				  capture.enterFolder1Ref(accRef_SAU_6_4);
				  capture.clickOnSaveButton();
				  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
				}
			}
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
			capture.clickOnIndexDocument();
			try {
				 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
				 capture.clickOkOnSuccesfullPopup();
				 getApplicationUrl();
			} catch (Exception e) {
				getApplicationUrl();
			}
			sleepFor(2000);	

			//verification
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_SAU_6_4);						
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();

			String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
			String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

			AssertionHelper.verifyText(docRef_SAU_6_3, ActDocRef);
			AssertionHelper.verifyText(accRef_SAU_6_3, ActAccRef);
			AssertionHelper.verifyText(docRef_SAU_6_4, ActDocRef1);
			AssertionHelper.verifyText(accRef_SAU_6_4, ActAccRef1);
		}

//		Verify that Delete button  is available and is Able to Delete documents on Document / Intray Tool ribbon for Super Admin User can 
		@Test(priority = 32, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyDeleteButtonIsAvailableAndIsAbleToDeleteDocumentOnDocumentAndIntrayToolRibbonForSuperAdminUser() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			
//			For Intray list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_SAU_7_1);						//docRef3		 "DR4_SAU_AT6744"
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Delete document", "Document");

			new WindowHelper(driver).waitForPopup("Delete Document");
			String getMsg = new WindowHelper(driver).getPopupMessage();
			sleepFor(2000);
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
			sleepFor(3000);
			
			if(getMsg == "The selected document(s) are either locked or check-out for deleting.") {
//		 		verify document is locked
				navigationMenu.expandNavigationMenu("Document");
				sleepFor(1000);
				String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
				WebElement button =driver.findElement(By.xpath(Xpath));
				boolean Button = new VerificationHelper(driver).isElementEnabled(button);
					
				String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
				WebElement button1 =driver.findElement(By.xpath(Xpath1));
				boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
				sleepFor(2000);	

				AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
				AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
			}
			else if(getMsg == "Are you sure you want to Delete the highlighted Document(s)") {
				new WindowHelper(driver).clickOkOnPopup();
				sleepFor(2000);
				System.out.println("get message ====================================" + getMsg);
				sleepFor(3000);

//		 		verify document is deleted
//				capture.clickOnFirstItemOfIntray();
				getApplicationUrl();
				capture.searchWithCriteria("Doc Ref",docRef_SAU_7_1);	
				capture.clickOnIntrayListBtn();
//				String verifyEmptyTable = capture.verifyEmptyTable.getText();
				String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
				String expMsgInEmptyTable = "No items available";
				AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);
			}
			
//				For Document list
				getApplicationUrl();
				capture.searchWithCriteria("Doc Ref", docRef_SAU_7_2);						//docRef3		 "DR4_SAU_AT6744"
				capture.clickOnDocumentListBtn();
				capture.clickOnFirstItemOfIntray();
				navigationMenu.clickOnIcon("Delete document", "Document");

				new WindowHelper(driver).waitForPopup("Delete Document");
				String getMsg1 = new WindowHelper(driver).getPopupMessage();
				sleepFor(2000);
				new WindowHelper(driver).clickOkOnPopup();
				sleepFor(2000);
				System.out.println("get message ====================================" + getMsg1);
				sleepFor(3000);
				
				if(getMsg1 == "The selected document(s) are either locked or check-out for deleting.") {
//			 		verify document is locked
					navigationMenu.expandNavigationMenu("Document");
					sleepFor(1000);
					String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
					WebElement button =driver.findElement(By.xpath(Xpath));
					boolean Button = new VerificationHelper(driver).isElementEnabled(button);
						
					String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
					WebElement button1 =driver.findElement(By.xpath(Xpath1));
					boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
					sleepFor(2000);	

					AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
					AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
				}
				else if(getMsg1 == "Are you sure you want to Delete the highlighted Document(s)") {
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					System.out.println("get message ====================================" + getMsg1);
					sleepFor(3000);

//			 		verify document is deleted
//					capture.clickOnFirstItemOfIntray();
					getApplicationUrl();
					capture.searchWithCriteria("Doc Ref",docRef_SAU_7_2);	
					capture.clickOnIntrayListBtn();
//					String verifyEmptyTable = capture.verifyEmptyTable.getText();
					String verifyEmptyTable1 = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
					String expMsgInEmptyTable1 = "No items available";
					AssertionHelper.verifyText(verifyEmptyTable1, expMsgInEmptyTable1);
				}
			}

//		Verify that Associate button  is available and is able to view on Document / Intray Tool ribbon for Super Admin User
		@Test(priority = 33, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyAssociateButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolRibbonForSuperAdminUser() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			accRef_SAU_8_1 = "MR8_1" + "_" + superAdminUserName + randomNo;
			miscRef_SAU_8_1 = "MR8_1" + "_" + superAdminUserName + randomNo;		
			propRef_SAU_8_1 = "PR8_1" + "_" + superAdminUserName + randomNo;		

			accRef_SAU_8_2 = "MR8_2" + "_" + superAdminUserName + randomNo;
			miscRef_SAU_8_2 = "MR8_2" + "_" + superAdminUserName + randomNo;		
			propRef_SAU_8_2 = "PR8_2" + "_" + superAdminUserName + randomNo;	
			String docType = "Default - General Default Document Type";
			
//			For Intray list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			
			navigationMenu.clickOnIcon("Reindex document", "Document");
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			capture.enterIndexingInformation("0", accRef_SAU_8_1, miscRef_SAU_8_1, propRef_SAU_8_1);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
			capture.enterDocRef(docRef_SAU_8);
			sleepFor(2000);

			capture.selectDocumentTypemDD(docType, getEnvVariable);
			sleepFor(2000);
			capture.selectRoutingTypeDD("To User", getEnvVariable);
			sleepFor(2000);
			capture.selectRouteToDD(superAdminUserName, getEnvVariable);
			
			capture.clickOnIndexDocument();
			try {
				//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
				sleepFor(3000);
				capture.clickOkOnSuccesfullPopup();
				getApplicationUrl(true);
			} catch (Exception e) {
				getApplicationUrl(true);
			}
			
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_1);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
			String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");				
			
//			verification
//			verify associated button is present on intray list
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(2000);	
			if(envType.equals("Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
				sleepFor(2000);		
			}
			else if(envType.equals("NECDM_Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
				sleepFor(2000);		
			}
			navigationMenu.expandNavigationMenu("Document");
			
//			for Show Account
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_1);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
//			for Show Misc
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_1);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	    	String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
//			for Show Account
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_1);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	    	String ActTitleTxt2 = navigationMenu.navBarTitleLbl.getText();
	 		
//			For Document list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			
			navigationMenu.clickOnIcon("Reindex document", "Document");
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			capture.enterIndexingInformation("0", accRef_SAU_8_2, miscRef_SAU_8_2, propRef_SAU_8_2);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
			capture.enterDocRef(docRef_SAU_8);
			sleepFor(2000);
			capture.selectDocumentTypemDD(docType, getEnvVariable);
			sleepFor(2000);
			capture.selectRoutingTypeDD("To User",getEnvVariable);
			sleepFor(2000);
			capture.selectRouteToDD(superAdminUserName,getEnvVariable);

			capture.clickOnIndexDocument();
			try {
				//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
				sleepFor(3000);
				capture.clickOkOnSuccesfullPopup();
				getApplicationUrl(true);
			} catch (Exception e) {
				getApplicationUrl(true);
			}
			
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_2);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
			String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

//			verification
//			verify associated button is present on document list
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(2000);	
			if(envType.equals("Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
				sleepFor(2000);		
			}
			else if(envType.equals("NECDM_Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
				sleepFor(2000);		
			}
			navigationMenu.expandNavigationMenu("Document");
			
//			for Show Account
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_2);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	    	String ActTitleTxt3 = navigationMenu.navBarTitleLbl.getText();
//			for Show Misc
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_2);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	    	String ActTitleTxt4 = navigationMenu.navBarTitleLbl.getText();
//			for Show Account
			getApplicationUrl();
			capture.searchWithCriteria("Account Ref", accRef_SAU_8_2);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	    	String ActTitleTxt5 = navigationMenu.navBarTitleLbl.getText();
	 		
			AssertionHelper.verifyText(docRef_SAU_8, ActualDocRef);
			AssertionHelper.verifyText(propRef_SAU_8_1, ActualPropertyRef);
			AssertionHelper.verifyText(docRef_SAU_8, ActualDocRef1);
			AssertionHelper.verifyText(propRef_SAU_8_2, ActualPropertyRef1);

	    	AssertionHelper.verifyTextContains(ActTitleTxt, "Account Landing Page");
	    	AssertionHelper.verifyTextContains(ActTitleTxt1, "Misc Landing Page");
	    	AssertionHelper.verifyTextContains(ActTitleTxt2, "Property Landing Page");
	    	AssertionHelper.verifyTextContains(ActTitleTxt3, "Account Landing Page");
	    	AssertionHelper.verifyTextContains(ActTitleTxt4, "Misc Landing Page");
	    	AssertionHelper.verifyTextContains(ActTitleTxt5, "Property Landing Page");
		}
		
//		Verify that Manage Tags button  is available and Can associate an existing Tag to the document on Document / Intray Tool ribbon for Super Admin User can 
		@Test(priority = 34, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//		@Test(priority = 34, enabled = true)
		public void verifyManageTagsButtonIsAvailableAndIsAbleToAddOrRemoveTagsOnDocumentAndIntrayToolRibbonForSuperAdminUser() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String docType = "Default - General Default Document Type";
			String TagName1 = "Test1";
			String TagName2 = "Test2";
			
//			For Intray list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
//			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			
			click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
//			windowHelper.waitForModalFormDialog("Add Tag");
			windowHelper.waitForModalFormDialog("Add Tag");
			
			sleepFor(3000);
			sendKeys(intrayTools.editTagsOrNameTxt, TagName1, "Adding or editing the tag name as"+TagName1);
			sleepFor(2000);
			new ActionHelper(driver).pressTab();
			sleepFor(3000);
			String actValidationMsg = intrayTools.validationMsg.getText();
			boolean validationMsgForExistingTagName = actValidationMsg.equals(TagName1 + "already exists.");
			if(!validationMsgForExistingTagName) {
//				click(intrayTools.okBtn,"Clicking on ok button");	
				new ActionHelper(driver).pressTab();
				new ActionHelper(driver).pressEnter();
			}
			else{
//				click(intrayTools.cancelBtn,"Clicking on cancel button");	
				new ActionHelper(driver).pressTab();
				new ActionHelper(driver).pressTab();
				new ActionHelper(driver).pressEnter();
			}
//			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			
			sleepFor(3000);
//			click(intrayTools.okBtn,"Clicking on ok button");	
			
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);						
			capture.clickOnIntrayListBtn();
			sleepFor(3000);	
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
//			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			
//			click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
			sleepFor(1000);
			sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName1, "Adding the tag name as"+TagName1);
			sleepFor(1000);
			new ActionHelper(driver).pressEnter();
			sleepFor(1000);
			new ActionHelper(driver).pressEscape();
			sleepFor(1000);
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
			sleepFor(5000);

//			click(intrayTools.okBtn,"clicking on ok button");	
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			
//			verification
//			For Intray list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);						
			capture.clickOnIntrayListBtn();
			sleepFor(3000);	
//			capture.clickOnFirstItemOfIntray();
			String ActualtagName = navigationMenu.getColumnValueFromTable("Tags");					
			
//			For Document list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
//			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			
			click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
//			windowHelper.waitForModalFormDialog("Add Tag");
			windowHelper.waitForModalFormDialog("Add Tag");
			sleepFor(3000);
			sendKeys(intrayTools.editTagsOrNameTxt, TagName2, "Adding or editing the tag name as"+TagName2);
			sleepFor(2000);
			new ActionHelper(driver).pressTab();
			sleepFor(3000);
			String actValidationMsg1 = intrayTools.validationMsg.getText();
			boolean validationMsgForExistingTagName1 = actValidationMsg1.equals(TagName2 + "already exists.");
			if(!validationMsgForExistingTagName1) {
//				click(intrayTools.okBtn,"Clicking on ok button");	
				new ActionHelper(driver).pressTab();
				new ActionHelper(driver).pressEnter();
			}
			else{
//				click(intrayTools.cancelBtn,"Clicking on cancel button");	
				new ActionHelper(driver).pressTab();
				new ActionHelper(driver).pressTab();
				new ActionHelper(driver).pressEnter();
			}
			try {
				windowHelper.waitForModalFormDialog("Add/Remove Tags");
			} catch (Exception e) {
				windowHelper.waitForModalFormDialog("Add/Remove Tags");
			}
			sleepFor(3000);
//			click(intrayTools.okBtn,"Clicking on ok button");	
			
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);						
			capture.clickOnDocumentListBtn();
			sleepFor(3000);	
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			
//			click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
			sleepFor(1000);
			sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName2, "Adding the tag name as"+TagName2);
			sleepFor(1000);
			new ActionHelper(driver).pressEnter();
			sleepFor(1000);
//			new ActionHelper(driver).pressEscape();
//			sleepFor(1000);
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
			sleepFor(5000);
			
//			click(intrayTools.okBtn,"clicking on ok button");	
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			
//			verification
//			For Document list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_8);						
			capture.clickOnDocumentListBtn();
			sleepFor(3000);	
//			capture.clickOnFirstItemOfIntray();
			String ActualtagName1 = navigationMenu.getColumnValueFromTable("Tags");					
			System.out.println("================"+ActualtagName1+"-----------------------");
			
	    	AssertionHelper.verifyTextContains(ActualtagName, TagName1);
//	    	AssertionHelper.verifyTextContains(ActualtagName1, TagName2);
		}

//		e2e
//		To verify  that when a Status of  a document in Intray iist is changed, No Message or Popup is showed and yet Status changes successfully.	
//		To verify that when a status of any document from Intray List is changed twice, No message or popup appears and yet status changes successfully.
//		To verify that Mail status of Document from Intray List should be updated and same should be recorded in Audit logs also.
//		Change the mail Status of Document from Intray List, to Other status (User Generated) and check That no message or Popup appears and Status are canged successfully, also populated in Audit Logs.
//		To verify that when changing a mail status to  pending, an entry for 'Intray Pending Reason is logged in Intray Audit.
//		To verify that actions performed on the intray of the user are captured under Intray Audit Actions on the landing page of the logged in user..
//		To verify that user is able to see all the associated audit actions of an intray under 'Intray Audit' tab on Audit Action popup
//		To verify that 'Intray Audit' tab lists all the associated audit actions of the currently selected intray
//		@Test(priority = 35, enabled = true)
		@Test(priority = 35, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void VerifyWhenAStatusOfAnyDocumentFromIntrayListIsChangedTwice_NoMessageOrPopupAppearsAndYetStatusChangesSuccessfully() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String docType = "Default - General Default Document Type";
			
			docRef_SAU_9 = "DR9" +"_" + superAdminUserName + randomNo;
			accRef_SAU_9 = "AR9" +"_" + superAdminUserName + randomNo;
			statusCode =  "SC"+ "_" + superAdminUserName;
			statusDescription ="Desc_"+statusCode;
			String toMailStatus = "In Progress";
			String toMailStatus1 = statusDescription;
			 
			
//			pre-requisite
			String configKey1 = "AllocateIntrayItem";
			String configKey2 = "ChangeMailStatusIntray";
			String configKey3 = "DeleteIntray";
			String configKey4 = "DistributeIntrayItem";
			
			String configKey5 = "CaptureImport";
			String configKey6 = "AddDocumentTag";
			String configKey7 = "AddTag";
			String configKey8 = "ReindexDocument";
			String configKey9 = "FlexibleEntityUpdated";
			String configKey10 = "RenditionCreated";
			String configValue = "1";
			
			getApplicationUrl();
			toolkit.checkConfigValue(configKey1, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey2, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey3, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey4, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey5, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey6, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey7, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey8, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey9, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey10, configValue);
			
			
//			1. capture document
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9, accRef_SAU_9, "To Team", TeamName, getEnvVariable);
			sleepFor(2000);	

			
//			2. Take shared mail item
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.collapseNavigationMenu("Document");
			sleepFor(2000);
			navigationMenu.clickOnIcon("Take shared mail item", "Mail");
			new WindowHelper(driver).waitForPopup("Take Item");
			String getMsg = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
			sleepFor(3000);
			
			
//			3. Take next item from team tray
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.collapseNavigationMenu("Document");
			sleepFor(2000);
			navigationMenu.clickOnIcon("Take next item from team tray", "Mail");
			new WindowHelper(driver).waitForPopup("Next");
			String actMsg_NextItemFromTeamIntray = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + actMsg_NextItemFromTeamIntray);
			sleepFor(3000);
			String expMsg_NextItemFromTeamIntray = "1 shared In-tray item(s) has been allocated";
		
			
//			4. Change mail status 1st time
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			capture.changeMailStatusUsingSelectClass(toMailStatus);
			
//			verification on intray audit action
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");

			
//			5. Change mail status 2nd time
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			capture.changeMailStatusUsingSelectClass(toMailStatus1);

//			verification on intray audit action
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualMailStatus1 = navigationMenu.getColumnValueFromTable("Mail Status");
			
			
//			6. Reindex the document
			String accRefIntray = "ARI"+"_01" + randomNo, miscRefIntray = "MRI"+"_01" + randomNo, propRefIntray = "PRI"+"_01" + randomNo;
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Reindex document", "Document");
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 50, 1);
			capture.enterIndexingInformation("0", accRefIntray, miscRefIntray, propRefIntray);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
			capture.selectDocumentTypemDD(docType, getEnvVariable);
			sleepFor(2000);
			capture.clickOnIndexDocument();
			try {
				sleepFor(3000);
				capture.clickOkOnSuccesfullPopup();
				getApplicationUrl(true);
			} catch (Exception e) {
				getApplicationUrl(true);
			}

			
//			7. create distribution item
			test = ExtentTestManager.getTest();
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
			intrayTools.clickOnModalApplyChangesButtonWithPopup();;
			sleepFor(2000);
			
			
//			8. Allocate Item
			String expDdTeamValue = "All";
			String expDdUserValue = "EmailConnectUser - Email Connect User";
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Allocate intray item", "Mail");
			new WindowHelper(driver).waitForModalDialog("Allocate intray item");
			new DropdownHelper(driver).selectFromAutocompleDD(expDdTeamValue,
			new DocumentToolsPage(driver).ddTeamUnderIntrayMoreSearch, getEnvVariable);
			new DropdownHelper(driver).selectFromAutocompleDD(expDdUserValue, new IntrayToolsPage(driver).allocateUserDD, getEnvVariable);
			click(new IntrayToolsPage(driver).allocateOkPopUpBtn, "Clicking in allocate ok button");
			sleepFor(20000);

			
//			9. Add tag to captured document
			String TagName1 = "Test1"+randomNo;
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			sendKeys(docTools.txtAreaInAddTagPopup, TagName1, "Adding the tag name as"+TagName1);
			new ActionHelper(driver).pressEnter();
			windowHelper.clickOnModalFooterButton("Ok");
			sleepFor(6000);
			
			
//			verification on landing page
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Intray landing page", "Document");
			sleepFor(5000);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			
//			verify 'audit action' in landing page from Document Audit for 1st audit action
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
	        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 50);
			String expDetailsFromDocumentAudit = "Mail status changed from 'N' to 'I' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expDetailsFromDocumentAudit);
			String actDetailsFromDocumentAudit = navigationMenu.getColumnValue("Details");
			sleepFor(2000);

//			verify 'audit action' in landing page from Document Audit for 2nd audit action
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
			new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 50);
			String expDetailsFromDocumentAudit1 = "Mail status changed from 'I' to 'SC_SAU_AT' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expDetailsFromDocumentAudit1);
			String actDetailsFromDocumentAudit1 = navigationMenu.getColumnValue("Details");
			sleepFor(2000);	
			
//			verify 'audit action' in landing page from Intray Audit for 1st audit action
	        navigationMenu.clickOnNavTabAtBottom("Intray Audit");
			new WaitHelper(driver).waitForElement(new NavigationMenu(driver).intrayAuditFilterSearch, 50);
	        String expDetailsFromIntrayAudit = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayAuditFilterInLandingPage(expDetailsFromIntrayAudit);
			String actDetailsFromIntrayAudit = navigationMenu.getColumnValueFromLandingPageGridForIntrayAudit("Action");
			sleepFor(2000);
			
//			verify 'audit action' in landing page from Intray Audit for 2nd audit action
	        navigationMenu.clickOnNavTabAtBottom("Intray Audit");
			new WaitHelper(driver).waitForElement(new NavigationMenu(driver).intrayAuditFilterSearch, 50);
	        String expDetailsFromIntrayAudit1 = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayAuditFilterInLandingPage(expDetailsFromIntrayAudit1);
			String actDetailsFromIntrayAudit1 = navigationMenu.getColumnValueFromLandingPageGridForIntrayAudit("Action");
			sleepFor(2000);
			
			
			
//			verification in intray list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Audit Actions", "Document");
			new WindowHelper(driver).waitForModalDialog("Audit Actions");

			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.documentActionFilterSearch);
			if (!status) {
				getApplicationUrl();
				capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
				capture.clickOnIntrayListBtn();
				sleepFor(2000);
				capture.clickOnFirstItemOfIntray();
				sleepFor(2000);
				navigationMenu.clickOnIcon("Audit Actions", "Document");
				new WindowHelper(driver).waitForModalDialog("Audit Actions");
			}
			
//			verify 'audit action' in intray list from document actions for 1st audit action
			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expDetailsFromDocumentAction = "Mail status changed from 'N' to 'I' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expDetailsFromDocumentAction);
			String actDetailsFromDocumentAction = navigationMenu.getColumnValueFromPopup("Details", "Audit Actions");
			sleepFor(2000);
			
//			verify 'audit action' in intray list from document actions for 2nd audit action
			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expDetailsFromDocumentAction1 = "Mail status changed from 'I' to 'SC_SAU_AT' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expDetailsFromDocumentAction1);
			String actDetailsFromDocumentAction1 = navigationMenu.getColumnValueFromPopup("Details", "Audit Actions");
			sleepFor(2000);
			String actActionFromDocumentAction = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			String expActionFromDocumentAction = "ChangeMailStatusIntray";
			sleepFor(2000);
			
//			verification for reindex document in Document actions
			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expDetailsFromDocumentAction_reindex = "Folder1Ref changed from "+accRef_SAU_9+" to "+accRefIntray+". Folder2Ref changed from  to "+propRefIntray+". Folder3Ref changed from  to "+miscRefIntray+". ";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup("Folder1Ref changed from");
			String actDetailsFromDocumentAction_reindex = navigationMenu.getColumnValueFromPopup("Details", "Audit Actions");
			sleepFor(2000);
			String actActionFromDocumentAction_reindex = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			String expActionFromDocumentAction_reindex = "ReIndexDocument";
			sleepFor(2000);
			
//			verification for distribute intray item in Document Actions
			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expActionFromDocumentActions_distributeItem = "DistributeIntrayItem";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromDocumentActions_distributeItem);
			String actActionFromDocumentActions_distributeItem = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");

//			verification for allocate item in Document Actions
			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expActionFromDocumentActions_allocateIntrayItem = "AllocateIntrayItem";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromDocumentActions_allocateIntrayItem);
			String actActionFromDocumentActions_allocateIntrayItem = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");

//			verification for add tag in Document actions
			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expActionFromIntrayActions_addTag = "AddDocumentTag";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromIntrayActions_addTag);
			String actActionFromIntrayActions_addTag = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			
			
//			verify 'audit action' in intray list from intray actions for 1st audit action
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expActionFromIntrayActions);
			String actActionFromIntrayActions = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			sleepFor(2000);
			String expChangeTypefromIntrayActions = "Intray Mail Status";
			String actChangeTypefromIntrayActions = navigationMenu.getColumnValueFromPopupFromIntrayAction("Change Type", "Audit Actions");
			
//			verify 'audit action' in intray list from intray actions for 2nd audit action
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions1 = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expActionFromIntrayActions1);
			String actActionFromIntrayActions1 = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			sleepFor(2000);
			String expChangeTypefromIntrayActions1 = "Intray Mail Status";
			String actChangeTypefromIntrayActions1 = navigationMenu.getColumnValueFromPopupFromIntrayAction("Change Type", "Audit Actions");
			
//			verification for distribute intray item in intray actions
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions_distributeItem = "DistributeIntrayItem";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expActionFromIntrayActions_distributeItem);
			String actActionFromIntrayActions_distributeItem = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");

//			verification for allocate item in intray actions
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions_allocateIntrayItem = "AllocateIntrayItem";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expActionFromIntrayActions_allocateIntrayItem);
			String actActionFromIntrayActions_allocateIntrayItem = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			
//			verification for take shared mail item in intray actions
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions_takeSharedMailItem = "AllocateIntrayItem";
			String expChangeTypeActionFromIntrayActions_takeSharedMailItem = "Intray Team Allocation";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expChangeTypeActionFromIntrayActions_takeSharedMailItem);
			String actActionFromIntrayActions_takeSharedMailItem = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			String actChangeTypeActionFromIntrayActions_takeSharedMailItem = navigationMenu.getColumnValueFromPopupFromIntrayAction("Change Type", "Audit Actions");
			
//			verification for take next item from team intray in intray actions
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions_takeNextItemFromTeamIntray = "AllocateIntrayItem";
			String expChangeTypeActionFromIntrayActions_takeNextItemFromTeamIntray = "Intray User Allocation";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expChangeTypeActionFromIntrayActions_takeSharedMailItem);
			String actActionFromIntrayActions_takeNextItemFromTeamIntray = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			String actChangeTypeActionFromIntrayActions_takeNextItemFromTeamIntray = navigationMenu.getColumnValueFromPopupFromIntrayAction("Change Type", "Audit Actions");

			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			
			AssertionHelper.verifyText(actDetailsFromIntrayAudit, expDetailsFromIntrayAudit);
			AssertionHelper.verifyText(actDetailsFromIntrayAudit1, expDetailsFromIntrayAudit1);
			AssertionHelper.verifyText(actActionFromIntrayActions, expActionFromIntrayActions);
			AssertionHelper.verifyText(actActionFromIntrayActions1, expActionFromIntrayActions1);
			AssertionHelper.verifyText(ActualMailStatus, toMailStatus);
			AssertionHelper.verifyText(ActualMailStatus1, toMailStatus1);
			AssertionHelper.verifyText(actActionFromDocumentAction, expActionFromDocumentAction);
			AssertionHelper.verifyText(actDetailsFromDocumentAudit, expDetailsFromDocumentAudit);
			AssertionHelper.verifyText(actDetailsFromDocumentAudit1, expDetailsFromDocumentAudit1);
			AssertionHelper.verifyTextContains(actDetailsFromDocumentAction, expDetailsFromDocumentAction);
			AssertionHelper.verifyTextContains(actDetailsFromDocumentAction1, expDetailsFromDocumentAction1);
			AssertionHelper.verifyText(actChangeTypefromIntrayActions, expChangeTypefromIntrayActions);
			AssertionHelper.verifyText(actChangeTypefromIntrayActions1, expChangeTypefromIntrayActions1);
			AssertionHelper.verifyText(actDetailsFromDocumentAction_reindex, expDetailsFromDocumentAction_reindex);
			AssertionHelper.verifyText(actActionFromDocumentAction_reindex, expActionFromDocumentAction_reindex);
			AssertionHelper.verifyText(actActionFromIntrayActions_distributeItem, expActionFromIntrayActions_distributeItem);			
			AssertionHelper.verifyText(actActionFromIntrayActions_allocateIntrayItem, expActionFromIntrayActions_allocateIntrayItem);			
			AssertionHelper.verifyText(actActionFromIntrayActions_distributeItem, expActionFromIntrayActions_distributeItem);			
			AssertionHelper.verifyText(actActionFromIntrayActions_allocateIntrayItem, expActionFromIntrayActions_allocateIntrayItem);			
			AssertionHelper.verifyText(actActionFromDocumentActions_distributeItem, expActionFromDocumentActions_distributeItem);			
			AssertionHelper.verifyText(actActionFromDocumentActions_allocateIntrayItem, expActionFromDocumentActions_allocateIntrayItem);			
			AssertionHelper.verifyText(actDetailsFromDocumentAction_reindex, expDetailsFromDocumentAction_reindex);			
			AssertionHelper.verifyText(actActionFromDocumentAction_reindex, expActionFromDocumentAction_reindex);			
			AssertionHelper.verifyText(actActionFromIntrayActions_distributeItem, expActionFromIntrayActions_distributeItem);			
			AssertionHelper.verifyText(actActionFromIntrayActions_distributeItem, expActionFromIntrayActions_distributeItem);			
			AssertionHelper.verifyText(expActionFromIntrayActions_takeNextItemFromTeamIntray, actActionFromIntrayActions_takeNextItemFromTeamIntray);
			AssertionHelper.verifyText(expChangeTypeActionFromIntrayActions_takeNextItemFromTeamIntray, actChangeTypeActionFromIntrayActions_takeNextItemFromTeamIntray);
			AssertionHelper.verifyText(expActionFromIntrayActions_addTag, actActionFromIntrayActions_addTag);
			}
		

//		e2e
//		To verify that user is able to see all the associated audit actions of a document under 'Document Audit' tab on Audit Action popup
//		@Test(priority = 36, enabled = true)
//		@Test(priority = 36, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		@Test(priority = 36, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void VerifyThatUserIsAbleToSeeAllTheAssociatedAuditActionsOfADocumentUnderDocumentAuditTabOnAuditActionPopup () throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String docType = "Default - General Default Document Type";
			statusCode =  "SC"+ "_" + superAdminUserName;
			statusDescription ="Desc_"+statusCode;
			TeamId = "TId"+ "_" + superAdminUserName;
			TeamName = "TN_"+ TeamId;
			
			docRef_SAU_9 = "DR9" +"_" + superAdminUserName + randomNo;
			accRef_SAU_9 = "AR9" +"_" + superAdminUserName + randomNo;
			
//			pre-requisite
			String configKey = "CaptureImport";
			String configKey1 = "AddTag";
			String configKey2 = "ReindexDocument";
			String configKey3 = "FlexibleEntityUpdated";
			String configKey4 = "RenditionCreated";
			String configKey5 = "AddDocumentTag";
			String configValue = "1";

			getApplicationUrl();
			toolkit.checkConfigValue(configKey, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey1, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey2, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey3, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey4, configValue);
			getApplicationUrl();
			toolkit.checkConfigValue(configKey5, configValue);
			
//			1. capture document
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9+"_2", accRef_SAU_9+"_2", "To Team", TeamName, getEnvVariable);
			sleepFor(2000);	
			
			
//			2. Add tag to captured document
			String TagName1 = "Test1"+randomNo;
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9+"_2");
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
			windowHelper.waitForModalFormDialog("Add/Remove Tags");
			sendKeys(docTools.txtAreaInAddTagPopup, TagName1, "Adding the tag name as"+TagName1);
			new ActionHelper(driver).pressEnter();
			windowHelper.clickOnModalFooterButton("Ok");
			sleepFor(6000);

			
//			3. Reindex document
			String accRefIntray = "ARI" + randomNo + "_02", miscRefIntray = "MRI" + randomNo + "_02", propRefIntray = "PRI" + randomNo + "_02";
//			String docType = "Default - General Default Document Type";
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9+"_2");
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Reindex document", "Document");
			//waitHelper.waitForElementVisible(docTools.viewerText, 35, 1);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			capture.enterIndexingInformation("0", accRefIntray, miscRefIntray, propRefIntray);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
			capture.selectDocumentTypemDD(docType, getEnvVariable);
			sleepFor(2000);

			capture.clickOnIndexDocument();
			try {
				//waitHelper.waitForElement(capture.successullyIndexMsg, 10);
				sleepFor(3000);
				capture.clickOkOnSuccesfullPopup();
				getApplicationUrl(true);
			} catch (Exception e) {
				getApplicationUrl(true);
			}

			
//			4. Re-reference 
			test = ExtentTestManager.getTest();
			String folder1Ref = "ARIRR" + randomNo + "_1", folder2Ref = "MRIRR" + randomNo + "_1",  folder3Ref = "PRIRR" + randomNo + "_1";
			String accountRef = ObjectReader.reader.getFolder1RefName(),  miscRef = ObjectReader.reader.getFolder2RefName(), propRef = ObjectReader.reader.getFolder3RefName();

			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9+"_2");
			capture.clickOnDocumentListBtn();
			//sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			//sleepFor(2000);
			docTools.clickOnReReferenceButton();
			new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
			sleepFor(4000);
			new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
			sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, folder1Ref, "Entering folder1 ref as" + folder1Ref);
			navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
			new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

			new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
			sleepFor(4000);
			new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
			sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, folder2Ref, "Entering folder2 ref as" + folder2Ref);
			navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
			new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

			new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
			sleepFor(4000);
			new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
			sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, folder3Ref, "Entering folder3 ref as" + folder3Ref);
			navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
			new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

			docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
			sleepFor(3000);
			docTools.clickOnApplyButton();
			String popContent = docTools.getReferencePopupContent();
			docTools.clickYesOnConfirmReferencePopUp();
			test.log(Status.INFO, "Content of ReReference pop up as" + popContent);
			docTools.clickOkOnRereferenceSusccessfulPopup();
//			sleepFor(4000);
			new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

			
//			5. create rendition - internal rendition
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9+"_2");
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.collapseNavigationMenu("Document");
			sleepFor(2000);
			
//			docTools.createInternalRendition();
			navigationMenu.clickOnIconMenu("Create Internal Rendition", "Rendition");
			sleepFor(3000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			log.info("First Switch=========="+driver.getWindowHandle());
			sleepFor(5000);
			new WaitHelper(driver).waitForElement(docTools.renditionViewerHeader, 20);

			click(waitHelper.waitForElementClickable(driver, docTools.saveRenditionBtn,50),"Clicking on save rendition btn");
//			click(saveRenditionBtn, "Clicking on save rendition btn");
			try {
				new WindowHelper(driver).waitForModalDialog("Save Internal Rendition");
				sleepFor(1000);
				sendKeys(docTools.renditionNameInput, "sample","Entering the input");
				sleepFor(3000);
//				click(waitHelper.waitForElementClickable(driver, docTools.confirmYesBtn, 50),"Clicked on confirm yes button");
				click(docTools.confirmYesBtn,"Clicked on confirm yes button");
				sleepFor(20000);
//				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 40, 1);
			}
			catch(Exception ex){
				System.out.println("get message ================= save internal rendition popup not found ===================");

//				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 40, 1);
			}
			
			
//			verify 'audit action' in Document Audit
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9+"_2");
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Audit Actions", "Document");
			new WindowHelper(driver).waitForModalDialog("Audit Actions");

			String expActionFromAuditActions_captureImport  = "CaptureImport";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromAuditActions_captureImport);
			String actActionFromAuditActions_captureImport = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");

			String expActionFromAuditActions_documentTag  = "AddDocumentTag";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromAuditActions_documentTag);
			String actActionFromAuditActions_documentTag = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			
			String expActionFromAuditActions_reindexDocument  = "ReIndexDocument";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromAuditActions_reindexDocument);
			String actActionFromAuditActions_reindexDocument = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");

			String expActionFromAuditActions_reReferenceDocument  = "ReIndexDocument";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromAuditActions_reReferenceDocument);
			String actActionFromAuditActions_reReferenceDocument = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			
			String expActionFromAuditActions_internalRendition  = "InternalRendition";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expActionFromAuditActions_internalRendition);
			String actActionFromAuditActions_internalRendition = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			
			new WindowHelper(driver).clickOnModalFooterButton("Ok");

			
//			verification on landing page
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9+"_2");
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Document landing page", "Document");
			sleepFor(5000);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			
//			Document Audit --> landing page
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
	        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
			String expActionFromLandingPage_captureImport  = "CaptureImport";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expActionFromLandingPage_captureImport);
			String actActionFromLandingPage_captureImport = navigationMenu.getColumnValue("Action");
			sleepFor(2000);
			
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
	        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
			String expActionFromLandingPage_documentTag  = "AddDocumentTag";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expActionFromLandingPage_documentTag);
			String actActionFromLandingPage_documentTag = navigationMenu.getColumnValue("Action");
			sleepFor(2000);
			
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
	        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
			String expActionFromLandingPage_reindexDocument  = "AddDocumentTag";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expActionFromLandingPage_reindexDocument);
			String actActionFromLandingPage_reindexDocument = navigationMenu.getColumnValue("Action");
			sleepFor(2000);
			
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
	        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
			String expActionFromLandingPage_reReferenceDocument  = "AddDocumentTag";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expActionFromLandingPage_reReferenceDocument);
			String actActionFromLandingPage_reReferenceDocument = navigationMenu.getColumnValue("Action");
			sleepFor(2000);
			
	        navigationMenu.clickOnNavTabAtBottom("Document Audit");
	        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
			String expActionFromLandingPage_internalRendition  = "InternalRendition";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expActionFromLandingPage_internalRendition);
			String actActionFromLandingPage_internalRendition = navigationMenu.getColumnValue("Action");
			sleepFor(2000);
		
			AssertionHelper.verifyText(actActionFromAuditActions_captureImport, expActionFromAuditActions_captureImport);			
			AssertionHelper.verifyText(actActionFromAuditActions_documentTag, expActionFromAuditActions_documentTag);			
			AssertionHelper.verifyText(actActionFromAuditActions_reindexDocument, expActionFromAuditActions_reindexDocument);			
			AssertionHelper.verifyText(actActionFromAuditActions_reReferenceDocument, expActionFromAuditActions_reReferenceDocument);			
			AssertionHelper.verifyText(actActionFromAuditActions_internalRendition, expActionFromAuditActions_internalRendition);
			
			AssertionHelper.verifyText(actActionFromLandingPage_captureImport, expActionFromLandingPage_captureImport);			
			AssertionHelper.verifyText(actActionFromLandingPage_documentTag, expActionFromLandingPage_documentTag);			
			AssertionHelper.verifyText(actActionFromLandingPage_reindexDocument, expActionFromLandingPage_reindexDocument);			
			AssertionHelper.verifyText(actActionFromLandingPage_reReferenceDocument, expActionFromLandingPage_reReferenceDocument);			
			AssertionHelper.verifyText(actActionFromLandingPage_internalRendition, expActionFromLandingPage_internalRendition);	
		}

		

//		@Test(priority = 37, enabled = true)
		@Test(priority = 37, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void WhenAStatusOfAnyDocumentFromMyIntrayIsChangedTwice_SecondTimeWithUserGeneratedThenNoMessageOrPopupAppearsAndYetStatusChangesSuccessfully() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String toMailStatus = "In Progress";
			String toMailStatus1 = statusDescription;
//			String toMailStatus1 = "Pending";
			String toMailStatus2 = "Verified";
//			String docRef_SAU_9_1 = "DR9_SAU_AT3705";
			
			
			getApplicationUrl();
//			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9_1, accRef_SAU_9_1, "To Team", TeamName, getEnvVariable);
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9_1, accRef_SAU_9_1, null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	
	
			
//			Change mail status 1st time
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			capture.changeMailStatusUsingSelectClass(toMailStatus);

//			verification
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");

//			verification on landing page
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Intray landing page", "Document");
			sleepFor(5000);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				
//			Document Audit --> landing page
		    navigationMenu.clickOnNavTabAtBottom("Document Audit");

		    new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
		    String expDetailsFromDocumentAudit = "Mail status changed from 'N' to 'I' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expDetailsFromDocumentAudit);
			String actDetailsFromDocumentAudit = navigationMenu.getColumnValue("Details");
			sleepFor(2000);
				
//			Intray Audit --> landing page
		    navigationMenu.clickOnNavTabAtBottom("Intray Audit");
			new WaitHelper(driver).waitForElement(new NavigationMenu(driver).intrayAuditFilterSearch, 35);
		    String expDetailsFromIntrayAudit = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayAuditFilterInLandingPage(expDetailsFromIntrayAudit);
			String actDetailsFromIntrayAudit = navigationMenu.getColumnValueFromLandingPageGridForIntrayAudit("Action");
			sleepFor(2000);

				
//			verify audit action in intray list
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Audit Actions", "Document");
			sleepFor(5000);
			new WindowHelper(driver).waitForModalDialog("Audit Actions");

			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expDetailsFromDocumentAction = "Mail status changed from 'N' to 'I' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expDetailsFromDocumentAction);
			String actDetailsFromDocumentAction = navigationMenu.getColumnValueFromPopup("Details", "Audit Actions");
			sleepFor(2000);
				
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expActionFromIntrayActions);
			String actActionFromIntrayActions = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			sleepFor(2000);
			String expChangeTypefromIntrayActions = "Intray Mail Status";
			String actChangeTypefromIntrayActions = navigationMenu.getColumnValueFromPopupFromIntrayAction("Change Type", "Audit Actions");
				
			new WindowHelper(driver).clickOnModalFooterButton("Ok");

				
//			Change mail status 2nd time
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			capture.changeMailStatusUsingSelectClass(toMailStatus1);

//			verification
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualMailStatus1 = navigationMenu.getColumnValueFromTable("Mail Status");

//			verification on landing page
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Intray landing page", "Document");
			sleepFor(5000);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				
//			Document Audit --> landing page
		    navigationMenu.clickOnNavTabAtBottom("Document Audit");
			new WaitHelper(driver).waitForElement(new NavigationMenu(driver).documentAuditFilterSearch, 35);
			String expDetailsFromDocumentAudit1 = "Mail status changed from 'I' to 'SC_SAU_AT' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentAuditFilterInLandingPage(expDetailsFromDocumentAudit1);
			String actDetailsFromDocumentAudit1 = navigationMenu.getColumnValue("Details");
			sleepFor(2000);
				
//			Intray Audit --> landing page
		    navigationMenu.clickOnNavTabAtBottom("Intray Audit");
			new WaitHelper(driver).waitForElement(new NavigationMenu(driver).intrayAuditFilterSearch, 35);
		    String expDetailsFromIntrayAudit1 = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayAuditFilterInLandingPage(expDetailsFromIntrayAudit1);
			String actDetailsFromIntrayAudit1 = navigationMenu.getColumnValueFromLandingPageGridForIntrayAudit("Action");
			sleepFor(2000);
				
//			verify 'audit action' in intray list
			getApplicationUrl();
			navigationMenu.clickOnIcon("View my intray", "Workflow");
			navigationMenu.searchInFilter(docRef_SAU_9_1);
//			capture.selectSearchTab();
//			capture.searchWithCriteria("Doc Ref", docRef_SAU_9_1);
//			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			navigationMenu.clickOnIcon("Audit Actions", "Document");
			new WindowHelper(driver).waitForModalDialog("Audit Actions");

			intrayTools.clickOnAuditActionsTab("Document Actions");
			String expDetailsFromDocumentAction1 = "Mail status changed from 'I' to 'SC_SAU_AT' in file system '"+ObjectReader.reader.getFileSystemName()+"'.";
			navigationMenu.searchInDocumentActionsFilterInAuditActionsPopup(expDetailsFromDocumentAction1);
			String actDetailsFromDocumentAction1 = navigationMenu.getColumnValueFromPopup("Details", "Audit Actions");
			sleepFor(2000);
			String actActionFromDocumentAction = navigationMenu.getColumnValueFromPopup("Action", "Audit Actions");
			String expActionFromDocumentAction = "ChangeMailStatusIntray";
			sleepFor(2000);
				
			intrayTools.clickOnAuditActionsTab("Intray Actions");
			String expActionFromIntrayActions1 = "ChangeMailStatusIntray";
			navigationMenu.searchInIntrayActionsFilterInAuditActionsPopup(expActionFromIntrayActions1);
			String actActionFromIntrayActions1 = navigationMenu.getColumnValueFromPopupFromIntrayAction("Action", "Audit Actions");
			sleepFor(2000);
			String expChangeTypefromIntrayActions1 = "Intray Mail Status";
			String actChangeTypefromIntrayActions1 = navigationMenu.getColumnValueFromPopupFromIntrayAction("Change Type", "Audit Actions");
				
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			
//			AssertionHelper.verifyText(ActualMailStatus, toMailStatus);
//			AssertionHelper.verifyText(ActualMailStatus1, toMailStatus1);
				
			AssertionHelper.verifyText(actActionFromDocumentAction, expActionFromDocumentAction);
			AssertionHelper.verifyText(actDetailsFromDocumentAudit, expDetailsFromDocumentAudit);
			AssertionHelper.verifyText(actDetailsFromDocumentAudit1, expDetailsFromDocumentAudit1);
				
			AssertionHelper.verifyTextContains(actDetailsFromDocumentAction, expDetailsFromDocumentAction);
			AssertionHelper.verifyTextContains(actDetailsFromDocumentAction1, expDetailsFromDocumentAction1);

				
//			AssertionHelper.verifyText(actDetailsFromIntrayAudit, expDetailsFromIntrayAudit);
//			AssertionHelper.verifyText(actDetailsFromIntrayAudit1, expDetailsFromIntrayAudit1);
//			AssertionHelper.verifyText(actActionFromIntrayActions, expActionFromIntrayActions);
//			AssertionHelper.verifyText(actActionFromIntrayActions1, expActionFromIntrayActions1);

			AssertionHelper.verifyText(actChangeTypefromIntrayActions, expChangeTypefromIntrayActions);
			AssertionHelper.verifyText(actChangeTypefromIntrayActions1, expChangeTypefromIntrayActions1);
			}	


//		e2e
//		Verify that 'show rows' selection works as expected for all the values, default and new
//		@Test(priority = 38, enabled = true)
//		@Test(priority = 38, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		@Test(priority = 38, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void showRowsSelectionWorksAsExpectedForAllTheValuesDefaultAndNew() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String configKey = "DataTableRowDisplay";
			String configValue = "30, 35, 45, 85";
			Actions actions = new Actions(driver);
			
			getApplicationUrl();
			toolkit.checkConfigValue(configKey, configValue);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", "%%");
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			
			new DropdownHelper(driver).presetTheShowRowsToTop(configValue);
//			verification
			String[] configValueArray = configValue.split(", "); 
			String expSearchResultIntrayDataTableValue = configValueArray[0];					//"35";
			String actSearchResultIntrayDataTable = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
			String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
			boolean actSearchResultIntrayDataTableValueCompare = expSearchResultIntrayDataTableValue.equals(actSearchResultIntrayDataTableValue[2]);
			if (actSearchResultIntrayDataTableValueCompare==false) 
			{
				AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[2], actSearchResultIntrayDataTableValue[4]);
			}
			else 
			{
				AssertionHelper.verifyText(expSearchResultIntrayDataTableValue, actSearchResultIntrayDataTableValue[2]);
			}
			sleepFor(3000);		
			
			for(int i = 1; i < configValueArray.length; i++){
				String expSearchResultIntrayDataTableValue1 = configValueArray[i];					//"35";
				String tmpshowRowsDDXpath = String.format(new DropdownHelper(driver).tmpshowRowsDD, expSearchResultIntrayDataTableValue1);
				WebElement ShowRowsDD = driver.findElement(By.xpath(tmpshowRowsDDXpath));

				new DropdownHelper(driver).selectFromAutocompleDDWithoutInputForShowRows(configValue);
//				verification
					String actSearchResultIntrayDataTable1 = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
					String[] actSearchResultIntrayDataTableValue1 = actSearchResultIntrayDataTable1.split(" ");  
					boolean actSearchResultIntrayDataTableValueCompare1 = expSearchResultIntrayDataTableValue1.equals(actSearchResultIntrayDataTableValue1[2]);
					if (actSearchResultIntrayDataTableValueCompare1==false) 
					{
						AssertionHelper.verifyText(actSearchResultIntrayDataTableValue1[2], actSearchResultIntrayDataTableValue1[4]);
					}
					else 
					{
						AssertionHelper.verifyText(expSearchResultIntrayDataTableValue1, actSearchResultIntrayDataTableValue1[2]);
					}
					sleepFor(3000);		
			}
		}
		

//		e2e
//		Verify that in Toolkit>Config setting DataTableRowDisplay setting have added ability to specify 250 , 500 or override
//		@Test(priority = 39, enabled = true)
//		@Test(priority = 39, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		@Test(priority = 39, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyThatInToolkitConfigSettingDataTableRowDisplaySettingHaveAbilityToSpecifity250_500OrOverride() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String configKey = "DataTableRowDisplay";
			String configValue = "250, 500, 1000";
			Actions actions = new Actions(driver);
			
			getApplicationUrl();
			toolkit.checkConfigValue(configKey, configValue);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", "%%");
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			
			new DropdownHelper(driver).presetTheShowRowsToTop(configValue);
//			verification
			String[] configValueArray = configValue.split(", "); 
			String expSearchResultIntrayDataTableValue = configValueArray[0];					//"35";
			String actSearchResultIntrayDataTable = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
			String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
			boolean actSearchResultIntrayDataTableValueCompare = expSearchResultIntrayDataTableValue.equals(actSearchResultIntrayDataTableValue[2]);
			if (actSearchResultIntrayDataTableValueCompare==false) 
			{
				AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[2], actSearchResultIntrayDataTableValue[4]);
			}
			else 
			{
				AssertionHelper.verifyText(expSearchResultIntrayDataTableValue, actSearchResultIntrayDataTableValue[2]);
			}
			sleepFor(3000);
		
			
			for(int i = 1; i < configValueArray.length; i++){
				String expSearchResultIntrayDataTableValue1 = configValueArray[i];					//"35";
				String tmpshowRowsDDXpath = String.format(new DropdownHelper(driver).tmpshowRowsDD, expSearchResultIntrayDataTableValue1);
				WebElement ShowRowsDD = driver.findElement(By.xpath(tmpshowRowsDDXpath));

				new DropdownHelper(driver).selectFromAutocompleDDWithoutInputForShowRows(configValue);
//				verification
					String actSearchResultIntrayDataTable1 = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
					String[] actSearchResultIntrayDataTableValue1 = actSearchResultIntrayDataTable1.split(" ");  
					boolean actSearchResultIntrayDataTableValueCompare1 = expSearchResultIntrayDataTableValue1.equals(actSearchResultIntrayDataTableValue1[2]);
					if (actSearchResultIntrayDataTableValueCompare1==false) 
					{
						AssertionHelper.verifyText(actSearchResultIntrayDataTableValue1[2], actSearchResultIntrayDataTableValue1[4]);
					}
					else 
					{
						AssertionHelper.verifyText(expSearchResultIntrayDataTableValue1, actSearchResultIntrayDataTableValue1[2]);
					}
					sleepFor(3000);
			}
		}	
		

//		e2e
//		Verify that In case of all the data is wrong in DataTableRowDislplay then display the default value of the datatable. i.e 10,25,50,100	
//		@Test(priority = 40, enabled = true)
		@Test(priority = 40, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyThatInCaseOfAllTheDataIsWrongInDataTableRowDisplayThenDisplayThedeFaultValueOfTheDataTable() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			String configKey = "DataTableRowDisplay";
			String configValue0 = "";
			String configValue = "10, 25, 50, 100";
			Actions actions = new Actions(driver);
			
			getApplicationUrl();
			toolkit.checkConfigValue(configKey, configValue0);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", "%%");
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			
			new DropdownHelper(driver).presetTheShowRowsToTop(configValue);
//			verification
			String[] configValueArray = configValue.split(", "); 
			String expSearchResultIntrayDataTableValue = configValueArray[0];					//"35";
			String actSearchResultIntrayDataTable = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
			String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
			boolean actSearchResultIntrayDataTableValueCompare = expSearchResultIntrayDataTableValue.equals(actSearchResultIntrayDataTableValue[2]);
			if (actSearchResultIntrayDataTableValueCompare) 
			{
				AssertionHelper.verifyText(expSearchResultIntrayDataTableValue, actSearchResultIntrayDataTableValue[2]);
				
			}
			else 
			{
				AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[2], actSearchResultIntrayDataTableValue[4]);
			}
			sleepFor(3000);
		
			
			for(int i = 1; i < configValueArray.length; i++){
				String expSearchResultIntrayDataTableValue1 = configValueArray[i];					//"35";
				String tmpshowRowsDDXpath = String.format(new DropdownHelper(driver).tmpshowRowsDD, expSearchResultIntrayDataTableValue1);
				WebElement ShowRowsDD = driver.findElement(By.xpath(tmpshowRowsDDXpath));

				new DropdownHelper(driver).selectFromAutocompleDDWithoutInputForShowRows(configValue);
//				verification
					String actSearchResultIntrayDataTable1 = new DropdownHelper(driver).searchResultIntrayDataTable_info.getText();
					String[] actSearchResultIntrayDataTableValue1 = actSearchResultIntrayDataTable1.split(" ");  
					boolean actSearchResultIntrayDataTableValueCompare1 = expSearchResultIntrayDataTableValue1.equals(actSearchResultIntrayDataTableValue1[2]);
					if (actSearchResultIntrayDataTableValueCompare1==false) 
					{
						AssertionHelper.verifyText(actSearchResultIntrayDataTableValue1[2], actSearchResultIntrayDataTableValue1[4]);
					}
					else 
					{
						AssertionHelper.verifyText(expSearchResultIntrayDataTableValue1, actSearchResultIntrayDataTableValue1[2]);
					}
					sleepFor(3000);
			}
		}	

//		e2e
//		Verify that system displays a popup with list of Intray items along with Ok button on it when user selects any document and click on 'Intray Details' button.//		
//		@Test(priority = 41, enabled = true)
		@Test(priority = 41, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyThatSystemDisplaysAPopupWithListOfIntrayItemsAlongWithOkButtonOnItWhenUserSelectsAnyDocument() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			docRef_SAU_10 = "DR10" +"_" + superAdminUserName + randomNo;
			accRef_SAU_10 = "AR10" +"_" + superAdminUserName + randomNo;		
			
//			pre-requisite : add 'Allow Allocate' security rights.
//			add 'Allow Allocate' security rights.
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(superAdminUserName); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);
			new AdminUserSection(driver).clickOnUserNavTab("Security");
			sleepFor(2000);

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			List<String> intrayProcessingList = new ArrayList<String>();

			intrayProcessingList.add("Allow Intray Allocate");
			userPermission.put("Intray Processing", intrayProcessingList);
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				
				try {
					navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
					navigationMenu.waitForAddIcon();
				}
				catch(Exception ex){
					new WindowHelper(driver).waitForPopup("Save");
					String getMsg = new WindowHelper(driver).getPopupMessage();
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					System.out.println("get message ====================================" + getMsg);
					sleepFor(3000);	
				}
				
			} else {
				try {
					navigationMenu.clickOnSaveIcon();
					navigationMenu.waitForAddIcon();
				}
				catch(Exception ex){
					new WindowHelper(driver).waitForPopup("Save");
					String getMsg = new WindowHelper(driver).getPopupMessage();
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					System.out.println("get message ====================================" + getMsg);
					sleepFor(3000);	
				}
			}
			sleepFor(1000);
//			-------------------------------------------------------------------------------------
//			capture document 
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_10+"_1", docRef_SAU_10+"_1", null, null, getEnvVariable);
			sleepFor(2000);	

			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_10+"_2", docRef_SAU_10+"_2", null, null, getEnvVariable);
			sleepFor(2000);	

			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", "DR"+"%");
			capture.clickOnDocumentListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);			
			
			navigationMenu.clickOnIcon("Intray details", "Document");

			new WindowHelper (driver).waitForModalFormDialog("Intray Details");
			String ActualUserID = navigationMenu.getTableCellValueByClassName("dataTableGrid", "1", "1");
			String expectedUserID = ObjectReader.reader.getUserName();
			new WindowHelper(driver).clickIntrayDetailsOkOnPopup();
			sleepFor(2000);	

			AssertionHelper.verifyText(expectedUserID, ActualUserID);
		}
		
//		e2e
//		Verify that system allocates selected document/s to allocated Team/ User after selecting 'Ok' button.		
//		Verify that after allocating document to Team or User, respective Column grid is upadted on Intray Page,
//		@Test(priority = 42, enabled = true)
		@Test(priority = 42, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyThatSystemAllocatesSelectedDocumentToAllocatedTeamOrUserAfterSelectingOkButton() throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			docRef_SAU_11 = "DR11" +"_" + superAdminUserName + randomNo;
			accRef_SAU_11 = "AR11" +"_" + superAdminUserName + randomNo;		
			String expDdTeamValue = "All";											// OR	"TId_"+superAdminUserName+" - TN_TId_"+superAdminUserName;
			String expDdUserValue = superAdminUserName+" - "+superAdminUserName;	// OR	superAdminUserName;
			TeamId = "TId"+ "_" + superAdminUserName;
			TeamName = "TN_"+ TeamId;
			String userName = superAdminUserName;
			System.out.println("getEnvVariable====================" + getEnvVariable);
			
//			pre-requisite : 
//			1.User should have 'Allow Allocate' security rights.
//			2. User should have "View Other Team Intray" security rights
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(superAdminUserName); 										
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);
			new AdminUserSection(driver).clickOnUserNavTab("Security");
			sleepFor(2000);

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			List<String> intrayList = new ArrayList<String>();
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayList.add("View Other Teams In-Trays");
			intrayProcessingList.add("Allow Intray Allocate");
			
			userPermission.put("Intray", intrayList);
			userPermission.put("Intray Processing", intrayProcessingList);
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				
				try {
					navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
					navigationMenu.waitForAddIcon();
				}
				catch(Exception ex){
					new WindowHelper(driver).waitForPopup("Save");
					String getMsg = new WindowHelper(driver).getPopupMessage();
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					System.out.println("get message ====================================" + getMsg);
					sleepFor(3000);	
				}
				
			} else {
				try {
					navigationMenu.clickOnSaveIcon();
					navigationMenu.waitForAddIcon();
				}
				catch(Exception ex){
					new WindowHelper(driver).waitForPopup("Save");
					String getMsg = new WindowHelper(driver).getPopupMessage();
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					System.out.println("get message ====================================" + getMsg);
					sleepFor(3000);	
				}
			}
			sleepFor(1000);
			
//			3. User should be available in a Team.		
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Team maintenance", "User");
			navigationMenu.searchInFilter(TeamId);
			navigationMenu.clickOnFilteredRow("teamsTable");
			navigationMenu.clickOnIcon("Edit selected team ", "Actions");
			adminuser.clickOnTeamUsersTab();

			teams.verifyUserAddedToTeamIfNotAddUserToTeam(superAdminUserName, getEnvVariable);	//.get(i)

			try {
				navigationMenu.clickOnIconMenu("Save changes made to team", "Actions");

				//verify document type with groupName				
				navigationMenu.waitForAddIcon();
			}
			catch(Exception ex){
				new WindowHelper(driver).waitForPopup("Save");
				String actMessage = new WindowHelper(driver).getPopupMessage();
				sleepFor(2000);
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
				sleepFor(2000);
				System.out.println("get message ====================================" + actMessage);
				sleepFor(3000);
			}
			
//			-------------------------------------------------------------------------------------
//			capture documents
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_11, accRef_SAU_11, null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_11);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Allocate intray item", "Mail");
			new WindowHelper(driver).waitForModalDialog("Allocate intray item");
			new DropdownHelper(driver).selectFromAutocompleDD(expDdTeamValue,
					new DocumentToolsPage(driver).ddTeamUnderIntrayMoreSearch, getEnvVariable);
			new DropdownHelper(driver).selectFromAutocompleDD(expDdUserValue, new IntrayToolsPage(driver).allocateUserDD,
					getEnvVariable);
			click(new IntrayToolsPage(driver).allocateOkPopUpBtn, "Clicking in allocate ok button");
			sleepFor(20000);

//			Verification
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_11);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			AssertionHelper.verifyTextContains(superAdminUserName, navigationMenu.getColumnValueFromTable("User ID"));
			AssertionHelper.verifyText(TeamId, TeamId);	
		}		

//		e2e
//		Verify that user can add notes to a document from Intray, My Intray, Document Landing page and Intray Landing page.
//		Verify that user can add notes to a dcoument on document, document landing, intray and Intray landing page through viewer.
//		@Test(priority = 43, enabled = true)
		@Test(priority = 43, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
		public void verifyThatUserCanAddNotesToADocumentFromIntrayMyIntrayDocumentLandingPageAndIntrayLandingPage () throws InterruptedException {
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable====================" + getEnvVariable);
			
			docRef_SAU_12 = "DR12" +"_" + superAdminUserName + randomNo;
			accRef_SAU_12 = "AR12" +"_" + superAdminUserName + randomNo;
			String expTextAreaTitle = "sample Testing";
			String expTextAreaDescription = "This is new line characted for the above line"+ randomNo; 	//+"7232";	//;	// +"7232"; //		
			String expTextAreaTitle1 = expTextAreaTitle + " 1";
			String expTextAreaDescription1 = expTextAreaDescription + " 1";
			String expTextAreaTitle2 = expTextAreaTitle + " 2";
			String expTextAreaDescription2 = expTextAreaDescription + " 2";
			String expTextAreaTitle3 = expTextAreaTitle + " 3";
			String expTextAreaDescription3 = expTextAreaDescription + " 3";
			
			String expTextAreaTitle_b = expTextAreaTitle + "_b";
			String expTextAreaDescription_b = expTextAreaDescription + "_b";		
			String expTextAreaTitle1_b = expTextAreaTitle_b + " 1";
			String expTextAreaDescription1_b = expTextAreaDescription_b + " 1";
			String expTextAreaTitle2_b = expTextAreaTitle_b + " 2";
			String expTextAreaDescription2_b = expTextAreaDescription_b + " 2";
			String expTextAreaTitle3_b = expTextAreaTitle_b + " 3";
			String expTextAreaDescription3_b = expTextAreaDescription_b + " 3";
			
//			pre-requisite
//			capture documents
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_12, accRef_SAU_12, null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	

			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_12+"_1", accRef_SAU_12+"_1", null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	
			
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_12+"_2", accRef_SAU_12+"_2", null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	
			
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_12+"_3", accRef_SAU_12+"_3", null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	
//			---------------------------------------------------------------------------------------
//			Verify that user can add notes to a document from Intray, My Intray, Document Landing page and Intray Landing page.
//			Verify that user can add notes to a dcoument on document, document landing, intray and Intray landing page through viewer.

//          1st way For intray --> to add notes from Documents >> notes 
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Notes", "Document");
			new WindowHelper(driver).waitForModalDialog("Notes");
			click(docTools.addNoteBtn, "Clicking on add document note button");
			new WindowHelper(driver).waitForModalDialog("Notes");
			sleepFor(3000);
			sendKeys(docTools.notesTitleInput, expTextAreaTitle, "Entering the notes Title Input");
			sendKeys(docTools.notesTextAreaInput, expTextAreaDescription, "Entering the Text Area Description Input");
			click(docTools.saveDocumentNoteBtn, "Clicking on save button");
			new WindowHelper(driver).waitForModalDialog("Notes");
			click(docTools.documentCloseNoteBtn, "Clicking on close document note button");
			
//			verification
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Notes", "Document");
			new WindowHelper(driver).waitForModalDialog("Notes");
			String actTextAreaDescription = docTools.notesParagraph.getText();
			String actTextAreaTitle = docTools.notesTitle.getText();
			
//	        2nd way For intray --> to add notes from administration >> notes 
			getApplicationUrl();
			adminDoc.clickOnAdminNotes();
			navigationMenu.clickOnAddIcon();
			adminDoc.SelectNotesType("Paragraph", getEnvVariable); // Changed in R551
			adminDoc.EnterTextAreaDescriptionForNotes(expTextAreaDescription1);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_1");
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			docTools.clickOnIntrayLandingPageIcon();
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.ClickOnAddNotesToDocument();
			docTools.EnterTitleInNotes(expTextAreaTitle1);
			docTools.SelctParagraphforDocument(expTextAreaDescription1, getEnvVariable);
			navigationMenu.clickOnSaveIcon();
			
//			verification
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_1");
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			docTools.clickOnIntrayLandingPageIcon();
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			click(docTools.notesTab, "Clicking on Notes Tab");
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			String actTextAreaDescription1 = intrayTools.notesDescription.getText();
			String actTextAreaTitle1 = intrayTools.notesTitle.getText();

//          3rd way For Document List --> to add notes from Documents >> notes 
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_2");
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Notes", "Document");
			new WindowHelper(driver).waitForModalDialog("Notes");
			click(docTools.addNoteBtn, "Clicking on add document note button");
			new WindowHelper(driver).waitForModalDialog("Notes");
			sleepFor(3000);
			sendKeys(docTools.notesTitleInput, expTextAreaTitle2, "Entering the notes Title Input");
			sendKeys(docTools.notesTextAreaInput, expTextAreaDescription2, "Entering the Text Area Description Input");
			click(docTools.saveDocumentNoteBtn, "Clicking on save button");
			new WindowHelper(driver).waitForModalDialog("Notes");
			click(docTools.documentCloseNoteBtn, "Clicking on close document note button");
			
//			verification
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_2");
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Notes", "Document");
			new WindowHelper(driver).waitForModalDialog("Notes");
			String actTextAreaDescription2 = docTools.notesParagraph.getText();
			String actTextAreaTitle2 = docTools.notesTitle.getText();
			
//	        4th way For Document List --> to add notes from administration >> notes 
			getApplicationUrl();
			adminDoc.clickOnAdminNotes();
			navigationMenu.clickOnAddIcon();
			adminDoc.SelectNotesType("Paragraph", getEnvVariable); // Changed in R551
			adminDoc.EnterTextAreaDescriptionForNotes(expTextAreaDescription3);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_3");
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			docTools.clickOnLandingPageIcon();
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.ClickOnAddNotesToDocument();
			docTools.EnterTitleInNotes(expTextAreaTitle3);
			docTools.SelctParagraphforDocument(expTextAreaDescription3, getEnvVariable);
			navigationMenu.clickOnSaveIcon();
			
//			verification
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_3");
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			docTools.clickOnLandingPageIcon();
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			click(docTools.notesTab, "Clicking on Notes Tab");
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			String actTextAreaDescription3 = intrayTools.notesDescription.getText();
			String actTextAreaTitle3 = intrayTools.notesTitle.getText();
//			------------------------------------------------------------------------------------------------
			
//			Verify that user can add notes to a dcoument on document, document landing, intray and Intray landing page through viewer.
//          1st way For intray --> to add notes from Documents >> notes 
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			
			navigationMenu.clickOnIcon("View document", "Document");
			try {
				sleepFor(4000);
				new WindowHelper(driver).switchToNewlyOpenedTab();
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				sleepFor(6000);
				navigationMenu.clickOnTab("Document"); 
				navigationMenu.clickOnIcon("Notes", "Tools");
				 
				new WindowHelper(driver).waitForModalDialog("Notes");
				String actTextAreaDescription_ = docTools.notesParagraph.getText();
				String actTextAreaTitle_ = docTools.notesTitle.getText();
				
				AssertionHelper.verifyText(expTextAreaDescription, actTextAreaDescription_);
				AssertionHelper.verifyText(expTextAreaTitle, actTextAreaTitle_ );
				
				new WindowHelper(driver).waitForModalDialog("Notes");
				click(docTools.addNoteBtn, "Clicking on add document note button");
				new WindowHelper(driver).waitForModalDialog("Notes");
				sleepFor(3000);
				sendKeys(docTools.notesTitleInput, expTextAreaTitle_b, "Entering the notes Title Input");
				sendKeys(docTools.notesTextAreaInput, expTextAreaDescription_b, "Entering the Text Area Description Input");
				click(docTools.saveDocumentNoteBtn, "Clicking on save button");
				new WindowHelper(driver).waitForModalDialog("Notes");
				click(docTools.documentCloseNoteBtn, "Clicking on close document note button");
				
				new WindowHelper(driver).waitForModalDialog("Notes");
				String actTextAreaDescription_b = docTools.notesParagraph.getText();
				String actTextAreaTitle_b = docTools.notesTitle.getText();
				AssertionHelper.verifyText(expTextAreaDescription_b, actTextAreaDescription_b);
				AssertionHelper.verifyText(expTextAreaTitle_b, actTextAreaTitle_b );
			} finally {
				new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			}
			
//	        2nd way For intray --> to add notes from administration >> notes 
			getApplicationUrl();
			adminDoc.clickOnAdminNotes();
			navigationMenu.clickOnAddIcon();
			adminDoc.SelectNotesType("Paragraph", getEnvVariable); // Changed in R551
			adminDoc.EnterTextAreaDescriptionForNotes(expTextAreaDescription1_b);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_1");
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("View document", "Document");
			sleepFor(3000);

			try {
				sleepFor(6000);
				new WindowHelper(driver).switchToNewlyOpenedTab();
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				sleepFor(6000);
				navigationMenu.clickOnTab("Document"); 
				navigationMenu.clickOnIcon("Document landing page", "Tools");
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				sleepFor(2000);
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				docTools.ClickOnAddNotesToDocument();
				docTools.EnterTitleInNotes(expTextAreaTitle1_b);
				docTools.SelctParagraphforDocument(expTextAreaDescription1_b, getEnvVariable);
				navigationMenu.clickOnSaveIcon();
				
//				verification
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(1000);
				click(docTools.notesTab, "Clicking on Notes Tab");
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);

				String actTextAreaDescription1_b = intrayTools.notesDescription.getText();
				String actTextAreaTitle1_b = intrayTools.notesTitle.getText();
				
				AssertionHelper.verifyText(expTextAreaDescription1_b, actTextAreaDescription1_b);
				AssertionHelper.verifyText(expTextAreaTitle1_b, actTextAreaTitle1_b);
				
			} finally {
				new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			}
			
			
//          3rd way For Document List --> to add notes from Documents >> notes 
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_2");
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("View document", "Document");
			try {
				sleepFor(4000);
				new WindowHelper(driver).switchToNewlyOpenedTab();
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				sleepFor(6000);
				navigationMenu.clickOnTab("Document"); 
				sleepFor(4000);
				navigationMenu.clickOnIcon("Notes", "Tools");

				new WindowHelper(driver).waitForModalDialog("Notes");
				String actTextAreaDescription2_ = docTools.notesParagraph.getText();
				String actTextAreaTitle2_ = docTools.notesTitle.getText();
				
				AssertionHelper.verifyText(expTextAreaDescription2, actTextAreaDescription2_);
				AssertionHelper.verifyText(expTextAreaTitle2, actTextAreaTitle2_ );
				
				new WindowHelper(driver).waitForModalDialog("Notes");
				click(docTools.addNoteBtn, "Clicking on add document note button");
				new WindowHelper(driver).waitForModalDialog("Notes");
				sleepFor(3000);
				sendKeys(docTools.notesTitleInput, expTextAreaTitle2_b, "Entering the notes Title Input");
				sendKeys(docTools.notesTextAreaInput, expTextAreaDescription2_b, "Entering the Text Area Description Input");
				click(docTools.saveDocumentNoteBtn, "Clicking on save button");
				new WindowHelper(driver).waitForModalDialog("Notes");
				click(docTools.documentCloseNoteBtn, "Clicking on close document note button");
				
				new WindowHelper(driver).waitForModalDialog("Notes");
				String actTextAreaDescription2_b = docTools.notesParagraph.getText();
				String actTextAreaTitle2_b = docTools.notesTitle.getText();
				AssertionHelper.verifyText(expTextAreaDescription2_b, actTextAreaDescription2_b);
				AssertionHelper.verifyText(expTextAreaTitle2_b, actTextAreaTitle2_b );
			} finally {
				new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			}
			
//	        4th way For Document List --> to add notes from administration >> notes 
			getApplicationUrl();
			adminDoc.clickOnAdminNotes();
			navigationMenu.clickOnAddIcon();
			adminDoc.SelectNotesType("Paragraph", getEnvVariable); // Changed in R551
			adminDoc.EnterTextAreaDescriptionForNotes(expTextAreaDescription3_b);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_12+"_3");
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("View document", "Document");
			try {
				sleepFor(4000);
				new WindowHelper(driver).switchToNewlyOpenedTab();
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				sleepFor(6000);
				navigationMenu.clickOnTab("Document"); 
				navigationMenu.clickOnIcon("Document landing page", "Tools");
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
				sleepFor(2000);
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				docTools.ClickOnAddNotesToDocument();
				docTools.EnterTitleInNotes(expTextAreaTitle3_b);
				docTools.SelctParagraphforDocument(expTextAreaDescription3_b, getEnvVariable);
				navigationMenu.clickOnSaveIcon();
				
//				verification
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(1000);
				click(docTools.notesTab, "Clicking on Notes Tab");
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				String actTextAreaDescription3_b = intrayTools.notesDescription.getText();
				String actTextAreaTitle3_b = intrayTools.notesTitle.getText();
				
				AssertionHelper.verifyText(expTextAreaDescription3_b, actTextAreaDescription3_b);
				AssertionHelper.verifyText(expTextAreaTitle3_b, actTextAreaTitle3_b);
				
			} finally {
				new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			}
			
//          ------------------------------------------------------------------------------------------------			
//			Post-requisite
			getApplicationUrl();
			adminDoc.clickOnAdminNotes();
			
			navigationMenu.searchInFilter(expTextAreaDescription1);
		    navigationMenu.clickOnFilteredRow("standardNotesTable");
			navigationMenu.clickOnIcon("Delete selected standard text for note", "Actions");
	 		new WindowHelper(driver).waitForPopup("Delete");
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);				
			
			navigationMenu.searchInFilter(expTextAreaDescription3);
		    navigationMenu.clickOnFilteredRow("standardNotesTable");
			navigationMenu.clickOnIcon("Delete selected standard text for note", "Actions");
	 		new WindowHelper(driver).waitForPopup("Delete");
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			
			navigationMenu.searchInFilter(expTextAreaDescription1_b);
		    navigationMenu.clickOnFilteredRow("standardNotesTable");
			navigationMenu.clickOnIcon("Delete selected standard text for note", "Actions");
	 		new WindowHelper(driver).waitForPopup("Delete");
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);				
			
			navigationMenu.searchInFilter(expTextAreaDescription3_b);
		    navigationMenu.clickOnFilteredRow("standardNotesTable");
			navigationMenu.clickOnIcon("Delete selected standard text for note", "Actions");
	 		new WindowHelper(driver).waitForPopup("Delete");
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);			
			getApplicationUrl();
			adminDoc.clickOnAdminNotes();
			String expMsgInEmptyTable = "No matching records found";

			navigationMenu.searchInFilter(expTextAreaDescription1);
			sleepFor(1000);
			String verifyEmptyTable1 = navigationMenu.getNoRecordsFoundMessage("standardNotesTable");

			navigationMenu.searchInFilter(expTextAreaDescription3);
			sleepFor(1000);
			String verifyEmptyTable3 = navigationMenu.getNoRecordsFoundMessage("standardNotesTable");

			navigationMenu.searchInFilter(expTextAreaDescription1_b);
			sleepFor(1000);
			String verifyEmptyTable1_b = navigationMenu.getNoRecordsFoundMessage("standardNotesTable");

			navigationMenu.searchInFilter(expTextAreaDescription3_b);
			sleepFor(1000);
			String verifyEmptyTable3_b = navigationMenu.getNoRecordsFoundMessage("standardNotesTable");
			
//			test case verification
			AssertionHelper.verifyText(expTextAreaDescription, actTextAreaDescription);
			AssertionHelper.verifyText(expTextAreaTitle, actTextAreaTitle );
			AssertionHelper.verifyText(expTextAreaDescription1, actTextAreaDescription1);
			AssertionHelper.verifyText(expTextAreaTitle1, actTextAreaTitle1 );
			AssertionHelper.verifyText(expTextAreaDescription2, actTextAreaDescription2);
			AssertionHelper.verifyText(expTextAreaTitle1, actTextAreaTitle1 );
			AssertionHelper.verifyText(expTextAreaDescription3, actTextAreaDescription3);
			AssertionHelper.verifyText(expTextAreaTitle3, actTextAreaTitle3 );
			
//			post-requisite verificaiton
			AssertionHelper.verifyText(expMsgInEmptyTable, verifyEmptyTable1);
			AssertionHelper.verifyText(expMsgInEmptyTable, verifyEmptyTable3);
			AssertionHelper.verifyText(expMsgInEmptyTable, verifyEmptyTable1_b);
			AssertionHelper.verifyText(expMsgInEmptyTable, verifyEmptyTable3_b);		
		}		
		
////===============================================================================================================================	
////===============================================================================================================================	
//	For File system admin user

	//done
	//Verify that Refresh button is available and is Able to refresh on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 201, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void verifyRefreshButtonIsAvailableAndIsAbleToRefreshForFileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		//for intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_1);				
		capture.clickOnIntrayListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();

		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Re-run the same search to refresh the lists");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== refresh button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Re-run the same search to refresh the lists");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== refresh button is present in intray list");
			sleepFor(2000);		
		}
		
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
	
		//for document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_1);				
		capture.clickOnDocumentListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();

		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Re-run the same search to refresh the lists");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== refresh button is present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Re-run the same search to refresh the lists");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== refresh button is present in document list");
			sleepFor(2000);		
		}
		
		String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
	
		AssertionHelper.verifyText(docRef_FSA_1, ActualDocumentRef);			
		AssertionHelper.verifyTextContains(ActTitleTxt, "In-Tray");
		AssertionHelper.verifyText(docRef_FSA_1, ActualDocumentRef1);			
		AssertionHelper.verifyTextContains(ActTitleTxt1, "Document Search");
		}
		
	//Verify that   Statistics button  is available and  is Able to view statistics on Document / Intray Tool ribbon for  File System Admin User 
	//@Test(priority = 202, enabled = true)
	@Test(priority = 202, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void VerifyThatStatisticsButtonIsAvailableAndIsAbleToViewStatisticsOnIntrayToolRibbonForFileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
	//	For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Show results statistics");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== Statistics button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Show results statistics");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== Statistics button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);

		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
	//	For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Show results statistics");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== Statistics button is present in Document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Show results statistics");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== Statistics button is present in Document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);

		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actDocumentCount1 = intrayTools.statisticPopupDocumentCount.getText();
		String actAccountRefCount1 = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
		AssertionHelper.verifyTextContains("1", actDocumentCount1);
		AssertionHelper.verifyTextContains("1", actAccountRefCount1);	
	}
		
		
	//Verify that   View button  is available and is able to view on Document / Intray Tool ribbon for  File System Admin User 
	//@Test(priority = 203, enabled = true)
	@Test(priority = 203, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void verifyViewButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolForFileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		For document list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "View document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== view button is present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "View document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== view button is present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);

		navigationMenu.clickOnIcon("View document", "Document");
		
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	
	//	For Intray List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "View document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== view button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "View document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== view button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);
		
		navigationMenu.clickOnIcon("View document", "Document");
		
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	////Verify that   Launch button  is available and is able to view the document on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 204, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void VerifyLaunchButtonIsAvailableAndIsAbleToViewTheDocumentOnDocumentAndIntrayForFileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		for Intray list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);

		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
		
//		for Document list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
	}	

	
////Verify that   copy button  is available and is able to copy document on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 205, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void CopyButtonIsAvailableAndIsAbleToCopyTheDocumentOnDocumentAndIntrayToolRibbonForFileSystemAdminUser() throws InterruptedException {
		docRef_FSA_3_1 = "DR3_1" +"_" + fileSystemAdminUserName + randomNo;
		accRef_FSA_3_1 = "AR3_1" +"_" + fileSystemAdminUserName + randomNo;
		docRef_FSA_3_2 = "DR3_2" +"_" + fileSystemAdminUserName + randomNo;
		accRef_FSA_3_2 = "AR3_2" +"_" + fileSystemAdminUserName + randomNo;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		copy document using copy button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");

		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_FSA_3_1+"_and_"+accRef_FSA_3_1);
		new AlertHelper(driver).acceptAlertIfPresent();
		sleepFor(2000);
		capture.enterDocRef(docRef_FSA_3_1);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(fileSystemAdminUserName,getEnvVariable);

		if(accRef_FSA_3_1!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_FSA_3_1, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_FSA_3_1);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_3_1);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

		
//		copy document using copy button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_FSA_3_2+"_and_"+accRef_FSA_3_2);
		new AlertHelper(driver).acceptAlertIfPresent();
		capture.enterDocRef(docRef_FSA_3_2);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(fileSystemAdminUserName,getEnvVariable);

		if(accRef_FSA_3_2!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_FSA_3_2, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_FSA_3_2);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_3_2);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

		AssertionHelper.verifyText(docRef_FSA_3_1, ActDocRef);
		AssertionHelper.verifyText(accRef_FSA_3_1, ActAccRef);
		AssertionHelper.verifyText(docRef_FSA_3_2, ActDocRef1);
		AssertionHelper.verifyText(accRef_FSA_3_2, ActAccRef1);
	}

//	Verify that   Add  button  is available and is able to add the document with add button on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 206, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void AddButtonIsAvailableAndIsAbleToAddTheDocumentWithAddButtonOnDocumentAndIntrayToolRibbonForFileSystemAdminUser() throws InterruptedException {
		docRef_FSA_3_3 = "DR3_3" +"_" + fileSystemAdminUserName + randomNo;
		accRef_FSA_3_3 = "AR3_3" +"_" + fileSystemAdminUserName + randomNo;
		docRef_FSA_3_4 = "DR3_4" +"_" + fileSystemAdminUserName + randomNo;
		accRef_FSA_3_4 = "AR3_4" +"_" + fileSystemAdminUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		For Intray List 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");

		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_3_3, accRef_FSA_3_3, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_3_3);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

//		For Document List 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3);					
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	
		
		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_FSA_3_4, accRef_FSA_3_4, null, fileSystemAdminUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_3_4);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

		AssertionHelper.verifyText(docRef_FSA_3_3, ActDocRef);
		AssertionHelper.verifyText(accRef_FSA_3_3, ActAccRef);
		AssertionHelper.verifyText(docRef_FSA_3_4, ActDocRef1);
		AssertionHelper.verifyText(accRef_FSA_3_4, ActAccRef1);
	}

//	Verify that   Reindex button  is available on Document / Intray Tool ribbon for  File System Admin User 
//	@Test(priority = 207, enabled = true)
	@Test(priority = 207, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void ReindexButtonIsAvailableAndAbleToReindexOnDocumentAndIntrayToolRibbonForFileSystemAdminUser() throws InterruptedException {
		docRef_FSA_3_5 = "DR3_5" + "_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_3_5 = "AR3_5" + "_" + fileSystemAdminUserName + randomNo;	
		String miscRef_FSA_3_5 = "MR3_5" + "_" + fileSystemAdminUserName + randomNo;		
		String propRef_FSA_3_5 = "PR3_5" + "_" + fileSystemAdminUserName + randomNo;		
		docRef_FSA_3_6 = "DR3_6" + "_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_3_6 = "AR3_6" + "_" + fileSystemAdminUserName + randomNo;	
		String miscRef_FSA_3_6 = "MR3_6" + "_" + fileSystemAdminUserName + randomNo;		
		String propRef_FSA_3_6 = "PR3_6" + "_" + fileSystemAdminUserName + randomNo;		
		docRef_FSA_3_5_1 = "DR3_5_1" + "_" + fileSystemAdminUserName + randomNo; 
		accRef_FSA_3_5_1 = "AR3_5_1" + "_" + fileSystemAdminUserName + randomNo;	
		String miscRef_FSA_3_5_1 = "MR3_5_1" + "_" + fileSystemAdminUserName + randomNo;		
		String propRef_FSA_3_5_1 = "PR3_5_1" + "_" + fileSystemAdminUserName + randomNo;		

		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_FSA_3_5, miscRef_FSA_3_5, propRef_FSA_3_5);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_FSA_3_5);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(fileSystemAdminUserName,getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_3_5);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		

//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3_5);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_FSA_3_6, miscRef_FSA_3_6, propRef_FSA_3_6);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_FSA_3_6);
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(fileSystemAdminUserName,getEnvVariable);

		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_3_6);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

		
//		For Intray -- Change the Routing type to 'To team' and select the team from Route to drop down
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_3_6);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_FSA_3_5_1, miscRef_FSA_3_5_1, propRef_FSA_3_5_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_FSA_3_5_1);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To Team",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD("testersman",getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_3_5_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef_1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef_1 = navigationMenu.getColumnValueFromTable("Property Ref");				
		String ActualTeam_1 = navigationMenu.getColumnValueFromTable("Team");				
		
		AssertionHelper.verifyText(docRef_FSA_3_5, ActualDocRef);
		AssertionHelper.verifyText(propRef_FSA_3_5, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_FSA_3_6, ActualDocRef1);
		AssertionHelper.verifyText(propRef_FSA_3_6, ActualPropertyRef1);

		AssertionHelper.verifyText(docRef_FSA_3_5_1, ActualDocRef_1);
		AssertionHelper.verifyText(propRef_FSA_3_5_1, ActualPropertyRef_1);
		AssertionHelper.verifyText("testersman", ActualTeam_1);
	}

//	Verify that   Rereference  button  is available and is Able to Rereference documents on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 208, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void ReferenceButtonIsAvailableAndIsAbleToReferenceDocumentsOnDocumentAndIntrayToolRibbonForFileSystemAdminUser() throws InterruptedException {
		String accRef_FSA_5_1 = "AR5_1" + "_" + fileSystemAdminUserName + randomNo;	
		String miscRef_FSA_5_1 = "MR5_1" + "_" + fileSystemAdminUserName + randomNo;		
		String propRef_FSA_5_1 = "PR5_1" + "_" + fileSystemAdminUserName + randomNo;			

		String accRef_FSA_5_2 = "AR5_2" + "_" + fileSystemAdminUserName + randomNo;	
		String miscRef_FSA_5_2 = "MR5_2" + "_" + fileSystemAdminUserName + randomNo;		
		String propRef_FSA_5_2 = "PR5_2" + "_" + fileSystemAdminUserName + randomNo;			

		String accountRef = ObjectReader.reader.getFolder1RefName();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String propRef = ObjectReader.reader.getFolder3RefName();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		For Intray
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_5);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, accRef_FSA_5_1, "Entering folder1 ref as" + accRef_FSA_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, miscRef_FSA_5_1, "Entering folder2 ref as" + miscRef_FSA_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, propRef_FSA_5_1, "Entering folder3 ref as" + propRef_FSA_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_5_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		

//		For Document List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_5);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, accRef_FSA_5_2, "Entering folder1 ref as" + accRef_FSA_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, miscRef_FSA_5_2, "Entering folder2 ref as" + miscRef_FSA_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, propRef_FSA_5_2, "Entering folder3 ref as" + propRef_FSA_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent1 = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent1);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_5_2);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropRef1 = navigationMenu.getColumnValueFromTable("Property Ref");				

		AssertionHelper.verifyText(docRef_FSA_5, ActualDocRef);
		AssertionHelper.verifyText(propRef_FSA_5_1, ActualPropRef);
		AssertionHelper.verifyText(docRef_FSA_5, ActualDocRef1);
		AssertionHelper.verifyText(propRef_FSA_5_2, ActualPropRef1);
	}

//	Verify that   Delete button  is available and is Able to Delete documents on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 209, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void verifyDeleteButtonIsAvailableAndIsAbleToDeleteDocumentOnDocumentAndIntrayToolRibbonForfileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_FSA_7_1);						//docRef3		 "DR4_FSA_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Delete document", "Document");

		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);
		
		if(getMsg == "The selected document(s) are either locked or check-out for deleting.") {
//	 		verify document is locked
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(1000);
			String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
				
			String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
			WebElement button1 =driver.findElement(By.xpath(Xpath1));
			boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
			sleepFor(2000);	

			AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
			AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
		}
		else if(getMsg == "Are you sure you want to Delete the highlighted Document(s)") {
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
			sleepFor(3000);

//	 		verify document is deleted
//			capture.clickOnFirstItemOfIntray();
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_FSA_7_1);	
			capture.clickOnIntrayListBtn();
//			String verifyEmptyTable = capture.verifyEmptyTable.getText();
			String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
			String expMsgInEmptyTable = "No items available";
			AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);
		}
		
//			For Document list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_FSA_7_2);						//docRef3		 "DR4_FSA_AT6744"
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Delete document", "Document");

			new WindowHelper(driver).waitForPopup("Delete Document");
			String getMsg1 = new WindowHelper(driver).getPopupMessage();
			sleepFor(2000);
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg1);
			sleepFor(3000);
			
			if(getMsg1 == "The selected document(s) are either locked or check-out for deleting.") {
//		 		verify document is locked
				navigationMenu.expandNavigationMenu("Document");
				sleepFor(1000);
				String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
				WebElement button =driver.findElement(By.xpath(Xpath));
				boolean Button = new VerificationHelper(driver).isElementEnabled(button);
					
				String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
				WebElement button1 =driver.findElement(By.xpath(Xpath1));
				boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
				sleepFor(2000);	

				AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
				AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
			}
			else if(getMsg1 == "Are you sure you want to Delete the highlighted Document(s)") {
				new WindowHelper(driver).clickOkOnPopup();
				sleepFor(2000);
				System.out.println("get message ====================================" + getMsg1);
				sleepFor(3000);

//		 		verify document is deleted
//				capture.clickOnFirstItemOfIntray();
				getApplicationUrl();
				capture.searchWithCriteria("Doc Ref",docRef_FSA_7_2);	
				capture.clickOnIntrayListBtn();
//				String verifyEmptyTable = capture.verifyEmptyTable.getText();
				String verifyEmptyTable1 = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
				String expMsgInEmptyTable1 = "No items available";
				AssertionHelper.verifyText(verifyEmptyTable1, expMsgInEmptyTable1);
			}
		}
	
//	Verify that Associate button  is available and is able to view on Document / Intray Tool ribbon for Super Admin User
	@Test(priority = 210, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void verifyAssociateButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolRibbonForFileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		accRef_FSA_8_1 = "MR8_1" + "_" + fileSystemAdminUserName + randomNo;
		miscRef_FSA_8_1 = "MR8_1" + "_" + fileSystemAdminUserName + randomNo;		
		propRef_FSA_8_1 = "PR8_1" + "_" + fileSystemAdminUserName + randomNo;		

		accRef_FSA_8_2 = "MR8_2" + "_" + fileSystemAdminUserName + randomNo;
		miscRef_FSA_8_2 = "MR8_2" + "_" + fileSystemAdminUserName + randomNo;		
		propRef_FSA_8_2 = "PR8_2" + "_" + fileSystemAdminUserName + randomNo;	
		String docType = "Default - General Default Document Type";
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_FSA_8_1, miscRef_FSA_8_1, propRef_FSA_8_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_FSA_8);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User", getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(fileSystemAdminUserName, getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		
//		verification
//		verify associated button is present on intray list
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt2 = navigationMenu.navBarTitleLbl.getText();
 		
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_FSA_8_2, miscRef_FSA_8_2, propRef_FSA_8_2);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_FSA_8);
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(fileSystemAdminUserName,getEnvVariable);

		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

//		verification
//		verify associated button is present on document list
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt3 = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt4 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_FSA_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt5 = navigationMenu.navBarTitleLbl.getText();
 		
		AssertionHelper.verifyText(docRef_FSA_8, ActualDocRef);
		AssertionHelper.verifyText(propRef_FSA_8_1, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_FSA_8, ActualDocRef1);
		AssertionHelper.verifyText(propRef_FSA_8_2, ActualPropertyRef1);

    	AssertionHelper.verifyTextContains(ActTitleTxt, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt1, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt2, "Property Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt3, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt4, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt5, "Property Landing Page");
	}

//	Verify that   Manage Tags button  is available and Can associate an existing Tag to the document on Document / Intray Tool ribbon for  File System Admin User 
	@Test(priority = 211, enabled = true, dependsOnMethods = {"prerequisiteForFileSystemAdminUser"})
	public void verifyManageTagsButtonIsAvailableAndIsAbleToAddOrRemoveTagsOnDocumentAndIntrayToolRibbonForfileSystemAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String docType = "Default - General Default Document Type";
		String TagName3 = "Test1";
		String TagName4 = "Test2";
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
		windowHelper.waitForModalFormDialog("Add Tag");
		sleepFor(3000);
		sendKeys(intrayTools.editTagsOrNameTxt, TagName3, "Adding or editing the tag name as"+TagName3);
		sleepFor(2000);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		String actValidationMsg = intrayTools.validationMsg.getText();
		boolean validationMsgForExistingTagName = actValidationMsg.equals(TagName3 + "already exists.");
		if(!validationMsgForExistingTagName) {
//			click(intrayTools.okBtn,"Clicking on ok button");	
			new ActionHelper(driver).pressTab();
			sleepFor(1000);
			new ActionHelper(driver).pressEnter();
			sleepFor(5000);
		}
		else{
//			click(intrayTools.cancelBtn,"Clicking on cancel button");	
			new ActionHelper(driver).pressTab();
			sleepFor(1000);
			new ActionHelper(driver).pressTab();
			sleepFor(1000);
			new ActionHelper(driver).pressEnter();
			sleepFor(5000);
		}
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
//		click(intrayTools.okBtn,"Clicking on ok button");	
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
		sleepFor(1000);
		sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName3, "Adding the tag name as"+TagName3);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).pressEscape();
		sleepFor(1000);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(12000);

//		click(intrayTools.okBtn,"clicking on ok button");	
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		
//		verification
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		String ActualtagName = navigationMenu.getColumnValueFromTable("Tags");					
		
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
		windowHelper.waitForModalFormDialog("Add Tag");
		sleepFor(3000);
		sendKeys(intrayTools.editTagsOrNameTxt, TagName4, "Adding or editing the tag name as"+TagName4);
		sleepFor(2000);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		String actValidationMsg1 = intrayTools.validationMsg.getText();
		boolean validationMsgForExistingTagName1 = actValidationMsg1.equals(TagName4 + "already exists.");
		if(!validationMsgForExistingTagName1) {
//			click(intrayTools.okBtn,"Clicking on ok button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		else{
//			click(intrayTools.cancelBtn,"Clicking on cancel button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
//		click(intrayTools.okBtn,"Clicking on ok button");	
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
		sleepFor(1000);
		sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName4, "Adding the tag name as"+TagName4);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).pressEscape();
		sleepFor(1000);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(12000);

//		click(intrayTools.okBtn,"clicking on ok button");	
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		
//		verification
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_FSA_8);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		String ActualtagName1 = navigationMenu.getColumnValueFromTable("Tags");					

		
    	AssertionHelper.verifyTextContains(ActualtagName, TagName3);
    	AssertionHelper.verifyTextContains(ActualtagName1, TagName4);
	}	
	
	////===============================================================================================================================	
	////===============================================================================================================================	
	////	For Supervisor user
	
	//done
	// Verify that  Refresh button  is available and is Able to refresh on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 301, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void verifyRefreshButtonIsAvailableAndIsAbleToRefreshForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		//for intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
	
		//for document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
	
		AssertionHelper.verifyText(docRef_SUV_1, ActualDocumentRef);					//docRef4		"DR4_SAU_AT6744"
		AssertionHelper.verifyTextContains(ActTitleTxt, "In-Tray");
		AssertionHelper.verifyText(docRef_SUV_1, ActualDocumentRef1);					//docRef4		"DR4_SAU_AT6744"
		AssertionHelper.verifyTextContains(ActTitleTxt1, "Document Search");
		}
		
	//Verify that  Statistics button  is available and  is Able to view statistics on Document / Intray Tool ribbon for Supervisor User 
	//@Test(priority = 302, enabled = true)
	@Test(priority = 302, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void VerifyThatStatisticsButtonIsAvailableAndIsAbleToViewStatisticsOnIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
	//	For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_1);					
		capture.clickOnIntrayListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
	//	For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_1);						
		capture.clickOnDocumentListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actDocumentCount1 = intrayTools.statisticPopupDocumentCount.getText();
		String actAccountRefCount1 = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
		AssertionHelper.verifyTextContains("1", actDocumentCount1);
		AssertionHelper.verifyTextContains("1", actAccountRefCount1);	
	}
		
	//Verify that  View button  is available and is able to view on Document / Intray Tool ribbon for Supervisor User 
	//@Test(priority = 303, enabled = true)
	@Test(priority = 303, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void verifyViewButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	
	//	For Intray List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	//Verify that  Launch button  is available and is able to view the document on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 304, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void VerifyLaunchButtonIsAvailableAndIsAbleToViewTheDocumentOnDocumentAndIntrayForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		for Intray list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);

		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
		
//		for Document list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");

	}	
	
//	Verify that  copy button  is available and is able to copy document on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 305, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void CopyButtonIsAvailableAndIsAbleToCopyTheDocumentOnDocumentAndIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		docRef_SUV_3_1 = "DR3_1" +"_" + supervisorUserName + randomNo;
		accRef_SUV_3_1 = "AR3_1" +"_" + supervisorUserName + randomNo;
		docRef_SUV_3_2 = "DR3_2" +"_" + supervisorUserName + randomNo;
		accRef_SUV_3_2 = "AR3_2" +"_" + supervisorUserName + randomNo;

//		copy document using copy button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_SUV_3_1+"_and_"+accRef_SUV_3_1);
		new AlertHelper(driver).acceptAlertIfPresent();
		capture.enterDocRef(docRef_SUV_3_1);
	
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(supervisorUserName,getEnvVariable);
	
		if(accRef_SUV_3_1!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_SUV_3_1, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_SUV_3_1);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	
	
		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_3_1);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
	
		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");
	
		
	//	copy document using copy button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_SUV_3_2+"_and_"+accRef_SUV_3_2);
		new AlertHelper(driver).acceptAlertIfPresent();
		capture.enterDocRef(docRef_SUV_3_2);
		
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(supervisorUserName,getEnvVariable);
	
		if(accRef_SUV_3_2!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_SUV_3_2, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_SUV_3_2);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	
	
		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_3_2);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
	
		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");
	
		AssertionHelper.verifyText(docRef_SUV_3_1, ActDocRef);
		AssertionHelper.verifyText(accRef_SUV_3_1, ActAccRef);
		AssertionHelper.verifyText(docRef_SUV_3_2, ActDocRef1);
		AssertionHelper.verifyText(accRef_SUV_3_2, ActAccRef1);
	}

//	 Verify that  Add  button  is available and is able to add the document with add button on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 306, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void AddButtonIsAvailableAndIsAbleToAddTheDocumentWithAddButtonOnDocumentAndIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		docRef_SUV_3_3 = "DR3_3" +"_" + supervisorUserName + randomNo;
		accRef_SUV_3_3 = "AR3_3" +"_" + supervisorUserName + randomNo;
		docRef_SUV_3_4 = "DR3_4" +"_" + supervisorUserName + randomNo;
		accRef_SUV_3_4 = "AR3_4" +"_" + supervisorUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
//		For Intray List 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");

		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SUV_3_3, accRef_SUV_3_3, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_3_3);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

//		For Document List 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3);					
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	
		
		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SUV_3_4, accRef_SUV_3_4, null, supervisorUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_3_4);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

		AssertionHelper.verifyText(docRef_SUV_3_3, ActDocRef);
		AssertionHelper.verifyText(accRef_SUV_3_3, ActAccRef);
		AssertionHelper.verifyText(docRef_SUV_3_4, ActDocRef1);
		AssertionHelper.verifyText(accRef_SUV_3_4, ActAccRef1);
	}

//	 Verify that  Reindex button  is available on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 307, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void ReindexButtonIsAvailableAndAbleToReindexOnDocumentAndIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		docRef_SUV_3_5 = "DR3_5" + "_" + supervisorUserName + randomNo; 
		accRef_SUV_3_5 = "AR3_5" + "_" + supervisorUserName + randomNo;	
		docRef_SUV_3_6 = "DR3_6" + "_" + supervisorUserName + randomNo; 
		accRef_SUV_3_6 = "AR3_6" + "_" + supervisorUserName + randomNo;	
		
		String miscRef_SUV_3_5 = "MR3_5" + "_" + supervisorUserName + randomNo;		
		String propRef_SUV_3_5 = "PR3_5" + "_" + supervisorUserName + randomNo;		
		String miscRef_SUV_3_6 = "MR3_6" + "_" + supervisorUserName + randomNo;		
		String propRef_SUV_3_6 = "PR3_6" + "_" + supervisorUserName + randomNo;		

		docRef_SUV_3_5_1 = "DR3_5_1" + "_" + supervisorUserName + randomNo; 
		accRef_SUV_3_5_1 = "AR3_5_1" + "_" + supervisorUserName + randomNo;	
		String miscRef_SUV_3_5_1 = "MR3_5_1" + "_" + supervisorUserName + randomNo;		
		String propRef_SUV_3_5_1 = "PR3_5_1" + "_" + supervisorUserName + randomNo;		

		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_SUV_3_5, miscRef_SUV_3_5, propRef_SUV_3_5);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_SUV_3_5);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_3_5);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");					

//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3_5);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();

		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_SUV_3_6, miscRef_SUV_3_6, propRef_SUV_3_6);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_SUV_3_6);
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_3_6);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

	
//		For Intray -- Change the Routing type to 'To team' and select the team from Route to drop down
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_3_6);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_SUV_3_5_1, miscRef_SUV_3_5_1, propRef_SUV_3_5_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_SUV_3_5_1);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To Team",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD("testersman",getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_3_5_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef_1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef_1 = navigationMenu.getColumnValueFromTable("Property Ref");				
		String ActualTeam_1 = navigationMenu.getColumnValueFromTable("Team");				
		
		AssertionHelper.verifyText(docRef_SUV_3_5, ActualDocRef);
		AssertionHelper.verifyText(propRef_SUV_3_5, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_SUV_3_6, ActualDocRef1);
		AssertionHelper.verifyText(propRef_SUV_3_6, ActualPropertyRef1);

		AssertionHelper.verifyText(docRef_SUV_3_5_1, ActualDocRef_1);
		AssertionHelper.verifyText(propRef_SUV_3_5_1, ActualPropertyRef_1);
		AssertionHelper.verifyText("testersman", ActualTeam_1);	
	}

//	 Verify that  Rereference button  is available and is Able to Rereference documents on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 308, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void ReferenceButtonIsAvailableAndIsAbleToReferenceDocumentsOnDocumentAndIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		String accRef_SUV_5_1 = "AR5_1" + "_" + supervisorUserName + randomNo;	
		String miscRef_SUV_5_1 = "MR5_1" + "_" + supervisorUserName + randomNo;		
		String propRef_SUV_5_1 = "PR5_1" + "_" + supervisorUserName + randomNo;			

		String accRef_SUV_5_2 = "AR5_2" + "_" + supervisorUserName + randomNo;	
		String miscRef_SUV_5_2 = "MR5_2" + "_" + supervisorUserName + randomNo;		
		String propRef_SUV_5_2 = "PR5_2" + "_" + supervisorUserName + randomNo;			

		String accountRef = ObjectReader.reader.getFolder1RefName();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String propRef = ObjectReader.reader.getFolder3RefName();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		For Intray
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_5);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, accRef_SUV_5_1, "Entering folder1 ref as" + accRef_SUV_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, miscRef_SUV_5_1, "Entering folder2 ref as" + miscRef_SUV_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, propRef_SUV_5_1, "Entering folder3 ref as" + propRef_SUV_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_5_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		

//		For Document List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_5);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, accRef_SUV_5_2, "Entering folder1 ref as" + accRef_SUV_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, miscRef_SUV_5_2, "Entering folder2 ref as" + miscRef_SUV_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, propRef_SUV_5_2, "Entering folder3 ref as" + propRef_SUV_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent1 = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent1);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_5_2);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropRef1 = navigationMenu.getColumnValueFromTable("Property Ref");				

		AssertionHelper.verifyText(docRef_SUV_5, ActualDocRef);
		AssertionHelper.verifyText(propRef_SUV_5_1, ActualPropRef);
		AssertionHelper.verifyText(docRef_SUV_5, ActualDocRef1);
		AssertionHelper.verifyText(propRef_SUV_5_2, ActualPropRef1);
	}

//	 Verify that  Delete button  is available and is Able to Delete documents on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 309, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void verifyDeleteButtonIsAvailableAndIsAbleToDeleteDocumentOnDocumentAndIntrayToolRibbonForsupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_SUV_7_1);						//docRef3		 "DR4_SUV_AT6744"
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Delete document", "Document");

		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);
		
		if(getMsg == "The selected document(s) are either locked or check-out for deleting.") {
//	 		verify document is locked
			navigationMenu.expandNavigationMenu("Document");
			sleepFor(1000);
			String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
			WebElement button =driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
				
			String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
			WebElement button1 =driver.findElement(By.xpath(Xpath1));
			boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
			sleepFor(2000);	

			AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
			AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
		}
		else if(getMsg == "Are you sure you want to Delete the highlighted Document(s)") {
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
			sleepFor(3000);

//	 		verify document is deleted
//			capture.clickOnFirstItemOfIntray();
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_SUV_7_1);	
			capture.clickOnIntrayListBtn();
//			String verifyEmptyTable = capture.verifyEmptyTable.getText();
			String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
			String expMsgInEmptyTable = "No items available";
			AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);
		}
		
//			For Document list
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef_SUV_7_2);						//docRef3		 "DR4_SUV_AT6744"
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Delete document", "Document");

			new WindowHelper(driver).waitForPopup("Delete Document");
			String getMsg1 = new WindowHelper(driver).getPopupMessage();
			sleepFor(2000);
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg1);
			sleepFor(3000);
			
			if(getMsg1 == "The selected document(s) are either locked or check-out for deleting.") {
//		 		verify document is locked
				navigationMenu.expandNavigationMenu("Document");
				sleepFor(1000);
				String Xpath= String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Lock document");
				WebElement button =driver.findElement(By.xpath(Xpath));
				boolean Button = new VerificationHelper(driver).isElementEnabled(button);
					
				String Xpath1 = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Unlock document");
				WebElement button1 =driver.findElement(By.xpath(Xpath1));
				boolean Button1 = new VerificationHelper(driver).isElementEnabled(button1);
				sleepFor(2000);	

				AssertionHelper.verifyFalse(Button, "The Element Lock document is disabled after locking the document");
				AssertionHelper.verifyTrue(Button1, "The Element Unlock document is enabled after locking the document");
			}
			else if(getMsg1 == "Are you sure you want to Delete the highlighted Document(s)") {
				new WindowHelper(driver).clickOkOnPopup();
				sleepFor(2000);
				System.out.println("get message ====================================" + getMsg1);
				sleepFor(3000);

//		 		verify document is deleted
//				capture.clickOnFirstItemOfIntray();
				getApplicationUrl();
				capture.searchWithCriteria("Doc Ref",docRef_SUV_7_2);	
				capture.clickOnIntrayListBtn();
//				String verifyEmptyTable = capture.verifyEmptyTable.getText();
				String verifyEmptyTable1 = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
				String expMsgInEmptyTable1 = "No items available";
				AssertionHelper.verifyText(verifyEmptyTable1, expMsgInEmptyTable1);
			}
		}

//	Verify that Associate button  is available and is able to view on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 310, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void verifyAssociateButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		accRef_SUV_8_1 = "MR8_1" + "_" + supervisorUserName + randomNo;
		miscRef_SUV_8_1 = "MR8_1" + "_" + supervisorUserName + randomNo;		
		propRef_SUV_8_1 = "PR8_1" + "_" + supervisorUserName + randomNo;		

		accRef_SUV_8_2 = "MR8_2" + "_" + supervisorUserName + randomNo;
		miscRef_SUV_8_2 = "MR8_2" + "_" + supervisorUserName + randomNo;		
		propRef_SUV_8_2 = "PR8_2" + "_" + supervisorUserName + randomNo;	
		String docType = "Default - General Default Document Type";
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_SUV_8_1, miscRef_SUV_8_1, propRef_SUV_8_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_SUV_8);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User", getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(supervisorUserName, getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		
//		verification
//		verify associated button is present on intray list
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt2 = navigationMenu.navBarTitleLbl.getText();
 		
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_SUV_8_2, miscRef_SUV_8_2, propRef_SUV_8_2);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_SUV_8);
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(supervisorUserName,getEnvVariable);

		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

//		verification
//		verify associated button is present on document list
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt3 = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt4 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_SUV_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt5 = navigationMenu.navBarTitleLbl.getText();
 		
		AssertionHelper.verifyText(docRef_SUV_8, ActualDocRef);
		AssertionHelper.verifyText(propRef_SUV_8_1, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_SUV_8, ActualDocRef1);
		AssertionHelper.verifyText(propRef_SUV_8_2, ActualPropertyRef1);

    	AssertionHelper.verifyTextContains(ActTitleTxt, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt1, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt2, "Property Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt3, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt4, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt5, "Property Landing Page");
	}
	
//	 Verify that  Manage Tags button  is available and Can associate an existing Tag to the document on Document / Intray Tool ribbon for Supervisor User 
	@Test(priority = 311, enabled = true, dependsOnMethods = {"prerequisiteForSupervisorUser"})
	public void verifyManageTagsButtonIsAvailableAndIsAbleToAddOrRemoveTagsOnDocumentAndIntrayToolRibbonForSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String docType = "Default - General Default Document Type";
		String TagName5 = "Test1";
		String TagName6 = "Test2";
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
		windowHelper.waitForModalFormDialog("Add Tag");
		sleepFor(3000);
		sendKeys(intrayTools.editTagsOrNameTxt, TagName5, "Adding or editing the tag name as"+TagName5);
		sleepFor(2000);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		String actValidationMsg = intrayTools.validationMsg.getText();
		boolean validationMsgForExistingTagName = actValidationMsg.equals(TagName5 + "already exists.");
		if(!validationMsgForExistingTagName) {
//			click(intrayTools.okBtn,"Clicking on ok button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		else{
//			click(intrayTools.cancelBtn,"Clicking on cancel button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
//		click(intrayTools.okBtn,"Clicking on ok button");	
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
		sleepFor(1000);
		sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName5, "Adding the tag name as"+TagName5);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).pressEscape();
		sleepFor(1000);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(12000);

//		click(intrayTools.okBtn,"clicking on ok button");	
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		
//		verification
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		String ActualtagName = navigationMenu.getColumnValueFromTable("Tags");					
		
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
		windowHelper.waitForModalFormDialog("Add Tag");
		sleepFor(3000);
		sendKeys(intrayTools.editTagsOrNameTxt, TagName6, "Adding or editing the tag name as"+TagName6);
		sleepFor(2000);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		String actValidationMsg1 = intrayTools.validationMsg.getText();
		boolean validationMsgForExistingTagName1 = actValidationMsg1.equals(TagName6 + "already exists.");
		if(!validationMsgForExistingTagName1) {
//			click(intrayTools.okBtn,"Clicking on ok button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		else{
//			click(intrayTools.cancelBtn,"Clicking on cancel button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
//		click(intrayTools.okBtn,"Clicking on ok button");	
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
		sleepFor(1000);
		sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName6, "Adding the tag name as"+TagName6);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).pressEscape();
		sleepFor(1000);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(12000);

//		click(intrayTools.okBtn,"clicking on ok button");	
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		
//		verification
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_SUV_8);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		String ActualtagName1 = navigationMenu.getColumnValueFromTable("Tags");					
		
   	AssertionHelper.verifyTextContains(ActualtagName, TagName5);
   	AssertionHelper.verifyTextContains(ActualtagName1, TagName6);
	}	

	////===============================================================================================================================	
	////===============================================================================================================================	
	//	For Standard user
	
	//Verify that  Refresh button  is available and is Able to refresh on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 401, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void verifyRefreshButtonIsAvailableAndIsAbleToRefreshForstandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		//for intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	//	capture.clickOnFirstItemOfIntray();
	//	String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
	//	AssertionHelper.verifyText(docRef1, ActualDocumentRef);					//docRef4		"DR4_SAU_AT6744"
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
	
		//for document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	//	capture.clickOnFirstItemOfIntray();
	//	String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
	//	AssertionHelper.verifyText(docRef1, ActualDocumentRef1);					//docRef4		"DR4_SAU_AT6744"
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
	
		AssertionHelper.verifyTextContains(ActTitleTxt, "In-Tray");
		AssertionHelper.verifyTextContains(ActTitleTxt1, "Document Search");
		}
		
	//Verify that  Statistics button  is available and  is Able to view statistics on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 402, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void VerifyThatStatisticsButtonIsAvailableAndIsAbleToViewStatisticsOnIntrayToolRibbonForStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
	//	For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
	
	//	For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actDocumentCount1 = intrayTools.statisticPopupDocumentCount.getText();
		String actAccountRefCount1 = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		
		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
		AssertionHelper.verifyTextContains("1", actDocumentCount1);
		AssertionHelper.verifyTextContains("1", actAccountRefCount1);	
	}
		
		
	//Verify that  View button  is available and is able to view on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 403, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	//@Test(priority = 403, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser", "prerequisiteForSupervisorUser"})
	public void verifyViewButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolForStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_STD_1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	
	//	For Intray List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
					"Verifying text name of viewer is displayed");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	////Verify that  Launch button  is available and is able to view the document on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 404, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	//@Test(priority = 404, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser", "prerequisiteForSupervisorUser"})
	public void VerifyLaunchButtonIsAvailableAndIsAbleToViewTheDocumentOnDocumentAndIntrayForStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		for Intray list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_STD_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		sleepFor(2000);

		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
		
//		for Document list
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_STD_1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Launch document in external viewer");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== launch button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");		
		
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");

	}	
	
//	Verify that  copy button  is available and is able to copy document on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 405, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void CopyButtonIsAvailableAndIsAbleToCopyTheDocumentOnDocumentAndIntrayToolRibbonForStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		docRef_STD_3_1 = "DR3_1" +"_" + standardUserName + randomNo;
		accRef_STD_3_1 = "AR3_1" +"_" + standardUserName + randomNo;
		docRef_STD_3_2 = "DR3_2" +"_" + standardUserName + randomNo;
		accRef_STD_3_2 = "AR3_2" +"_" + standardUserName + randomNo;
		
	//	copy document using copy button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_STD_3_1+"_and_"+accRef_STD_3_1);
		new AlertHelper(driver).acceptAlertIfPresent();
		capture.enterDocRef(docRef_STD_3_1);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(standardUserName,getEnvVariable);

		if(accRef_STD_3_1!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_STD_3_1, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
				capture.enterFolder1Ref(accRef_STD_3_1);
				capture.clickOnSaveButton();
				waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	
	
		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_3_1);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
	
		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");
	
		
	//	copy document using copy button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		
		log.info("capturing doc with "+"_"+docRef_STD_3_2+"_and_"+accRef_STD_3_2);
		new AlertHelper(driver).acceptAlertIfPresent();
		capture.enterDocRef(docRef_STD_3_2);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(standardUserName,getEnvVariable);

		if(accRef_STD_3_2!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef_STD_3_2, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef_STD_3_2);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 35);
			}
		}
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,35);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		sleepFor(2000);	
	
		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_3_2);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
	
		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");
	
		AssertionHelper.verifyText(docRef_STD_3_1, ActDocRef);
		AssertionHelper.verifyText(accRef_STD_3_1, ActAccRef);
		AssertionHelper.verifyText(docRef_STD_3_2, ActDocRef1);
		AssertionHelper.verifyText(accRef_STD_3_2, ActAccRef1);
	}
	
//	Verify that  Add button  is available and is able to add the document with add button on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 406, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void AddButtonIsAvailableAndIsAbleToAddTheDocumentWithAddButtonOnDocumentAndIntrayToolRibbonForStandardUser() throws InterruptedException {
		docRef_STD_3_3 = "DR3_3" +"_" + standardUserName + randomNo;
		accRef_STD_3_3 = "AR3_3" +"_" + standardUserName + randomNo;
		docRef_STD_3_4 = "DR3_4" +"_" + standardUserName + randomNo;
		accRef_STD_3_4 = "AR3_4" +"_" + standardUserName + randomNo;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
//		For Intray List 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");

		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_STD_3_3, accRef_STD_3_3, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_3_3);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

//		For Document List 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_3);					
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== copy button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	
		
		navigationMenu.clickOnIcon("Add new document to case", "Document");
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_STD_3_4, accRef_STD_3_4, null, standardUserName, getEnvVariable);
		sleepFor(2000);	

		//verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_STD_3_4);						
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActAccRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

		AssertionHelper.verifyText(docRef_STD_3_3, ActDocRef);
		AssertionHelper.verifyText(accRef_STD_3_3, ActAccRef);
		AssertionHelper.verifyText(docRef_STD_3_4, ActDocRef1);
		AssertionHelper.verifyText(accRef_STD_3_4, ActAccRef1);
	}

//	Verify that  Reindex button  is available on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 407, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void ReindexButtonIsAvailableAndAbleToReindexOnDocumentAndIntrayToolRibbonForStandardUser() throws InterruptedException {
		docRef_STD_4_1 = "DR4_1" + "_" + standardUserName + randomNo; 
		accRef_STD_4_1 = "AR4_1" + "_" + standardUserName + randomNo;	
		String miscRef_STD_4_1 = "MR4_1" + "_" + standardUserName + randomNo;		
		String propRef_STD_4_1 = "PR4_1" + "_" + standardUserName + randomNo;		

		docRef_STD_4_2 = "DR4_2" + "_" + standardUserName + randomNo; 
		accRef_STD_4_2 = "AR4_2" + "_" + standardUserName + randomNo;	
		String miscRef_STD_4_2 = "MR4_2" + "_" + standardUserName + randomNo;		
		String propRef_STD_4_2 = "PR4_2" + "_" + standardUserName + randomNo;		
		
		docRef_STD_4_1_1 = "DR4_1_1" + "_" + standardUserName + randomNo; 
		accRef_STD_4_1_1 = "AR4_1_1" + "_" + standardUserName + randomNo;	
		String miscRef_STD_4_1_1 = "MR4_1_1" + "_" + standardUserName + randomNo;		
		String propRef_STD_4_1_1 = "PR4_1_1" + "_" + standardUserName + randomNo;		

		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_4);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_STD_4_1, miscRef_STD_4_1, propRef_STD_4_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_STD_4_1);
		sleepFor(2000);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(standardUserName,getEnvVariable);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_4_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");					

//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_4_1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();

		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== reindex button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_STD_4_2, miscRef_STD_4_2, propRef_STD_4_2);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_STD_4_2);
		sleepFor(2000);

		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(standardUserName,getEnvVariable);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_4_2);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					


//		For Intray -- Change the Routing type to 'To team' and select the team from Route to drop down
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_4_2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.expandNavigationMenu("Document");
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_STD_4_1_1, miscRef_STD_4_1_1, propRef_STD_4_1_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_STD_4_1_1);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To Team",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD("testersman",getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_4_1_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef_1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef_1 = navigationMenu.getColumnValueFromTable("Property Ref");				
		String ActualTeam_1 = navigationMenu.getColumnValueFromTable("Team");				
		
		AssertionHelper.verifyText(docRef_STD_4_1, ActualDocRef);
		AssertionHelper.verifyText(propRef_STD_4_1, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_STD_4_2, ActualDocRef1);
		AssertionHelper.verifyText(propRef_STD_4_2, ActualPropertyRef1);

		AssertionHelper.verifyText(docRef_STD_4_1_1, ActualDocRef_1);
		AssertionHelper.verifyText(propRef_STD_4_1_1, ActualPropertyRef_1);
		AssertionHelper.verifyText("testersman", ActualTeam_1);	
	}

//	Verify that  Rereference button  is available and is Able to Rereference documents on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 408, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void ReferenceButtonIsAvailableAndIsAbleToReferenceDocumentsOnDocumentAndIntrayToolRibbonForStandardUser() throws InterruptedException {
		String accRef_STD_5_1 = "AR5_1" + "_" + standardUserName + randomNo;	
		String miscRef_STD_5_1 = "MR5_1" + "_" + standardUserName + randomNo;		
		String propRef_STD_5_1 = "PR5_1" + "_" + standardUserName + randomNo;			

		String accRef_STD_5_2 = "AR5_2" + "_" + standardUserName + randomNo;	
		String miscRef_STD_5_2 = "MR5_2" + "_" + standardUserName + randomNo;		
		String propRef_STD_5_2 = "PR5_2" + "_" + standardUserName + randomNo;			

		String accountRef = ObjectReader.reader.getFolder1RefName();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String propRef = ObjectReader.reader.getFolder3RefName();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		For Intray
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_5);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, accRef_STD_5_1, "Entering folder1 ref as" + accRef_STD_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, miscRef_STD_5_1, "Entering folder2 ref as" + miscRef_STD_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, propRef_STD_5_1, "Entering folder3 ref as" + propRef_STD_5_1);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_5_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		

//		For Document List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_5);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, accRef_STD_5_2, "Entering folder1 ref as" + accRef_STD_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, miscRef_STD_5_2, "Entering folder2 ref as" + miscRef_STD_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, propRef_STD_5_2, "Entering folder3 ref as" + propRef_STD_5_2);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 35);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent1 = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent1);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 35);

//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_5_2);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropRef1 = navigationMenu.getColumnValueFromTable("Property Ref");				

		AssertionHelper.verifyText(docRef_STD_5, ActualDocRef);
		AssertionHelper.verifyText(propRef_STD_5_1, ActualPropRef);
		AssertionHelper.verifyText(docRef_STD_5, ActualDocRef1);
		AssertionHelper.verifyText(propRef_STD_5_2, ActualPropRef1);
	}

//	Verify that  Delete button  is available and is Able to Delete documents on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 409, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void verifyDeleteButtonIsAvailableAndIsAbleToDeleteDocumentOnDocumentAndIntrayToolRibbonForstandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_7_1);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Delete button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Delete button is not present in intray list");
			sleepFor(2000);		
		}
		
//		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);		
		navigationMenu.collapseNavigationMenu("Document");
		
//		Delete document using delete button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_7_1);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
	
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "===================  Delete button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "===================  Delete button is not present in document list");
			sleepFor(2000);		
		}
//		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);		
		navigationMenu.collapseNavigationMenu("Document");
	}

//	Verify that Associate button  is available and is able to view on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 410, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void verifyAssociateButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolRibbonForstandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		accRef_STD_8_1 = "MR8_1" + "_" + standardUserName + randomNo;
		miscRef_STD_8_1 = "MR8_1" + "_" + standardUserName + randomNo;		
		propRef_STD_8_1 = "PR8_1" + "_" + standardUserName + randomNo;		

		accRef_STD_8_2 = "MR8_2" + "_" + standardUserName + randomNo;
		miscRef_STD_8_2 = "MR8_2" + "_" + standardUserName + randomNo;		
		propRef_STD_8_2 = "PR8_2" + "_" + standardUserName + randomNo;	
		String docType = "Default - General Default Document Type";
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_STD_8_1, miscRef_STD_8_1, propRef_STD_8_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_STD_8);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User", getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(standardUserName, getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");				
		
//		verification
//		verify associated button is present on intray list
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt2 = navigationMenu.navBarTitleLbl.getText();
 		
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_STD_8_2, miscRef_STD_8_2, propRef_STD_8_2);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_STD_8);
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(standardUserName,getEnvVariable);

		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

//		verification
//		verify associated button is present on document list
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt3 = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt4 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_STD_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt5 = navigationMenu.navBarTitleLbl.getText();
 		
		AssertionHelper.verifyText(docRef_STD_8, ActualDocRef);
		AssertionHelper.verifyText(propRef_STD_8_1, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_STD_8, ActualDocRef1);
		AssertionHelper.verifyText(propRef_STD_8_2, ActualPropertyRef1);

    	AssertionHelper.verifyTextContains(ActTitleTxt, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt1, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt2, "Property Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt3, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt4, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt5, "Property Landing Page");
	}

//	Verify that  Manage Tags button  is available and Can associate an existing Tag to the document on Document / Intray Tool ribbon for  Standard User 
	@Test(priority = 411, enabled = true, dependsOnMethods = {"prerequisiteForStandardUser"})
	public void verifyManageTagsButtonIsAvailableAndIsAbleToAddOrRemoveTagsOnDocumentAndIntrayToolRibbonForStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String docType = "Default - General Default Document Type";
		String TagName7 = "Test1";
		String TagName8 = "Test2";
		
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
		windowHelper.waitForModalFormDialog("Add Tag");
		sleepFor(3000);
		sendKeys(intrayTools.editTagsOrNameTxt, TagName7, "Adding or editing the tag name as"+TagName7);
		sleepFor(2000);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		String actValidationMsg = intrayTools.validationMsg.getText();
		boolean validationMsgForExistingTagName = actValidationMsg.equals(TagName7 + "already exists.");
		if(!validationMsgForExistingTagName) {
//			click(intrayTools.okBtn,"Clicking on ok button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		else{
//			click(intrayTools.cancelBtn,"Clicking on cancel button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
//		click(intrayTools.okBtn,"Clicking on ok button");	
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
		sleepFor(1000);
		sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName7, "Adding the tag name as"+TagName7);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).pressEscape();
		sleepFor(1000);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(12000);

//		click(intrayTools.okBtn,"clicking on ok button");	
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		
		
//		verification
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		String ActualtagName = navigationMenu.getColumnValueFromTable("Tags");					
		
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		click(intrayTools.addRemoveTagsAddIcon,"Clicking on add icon");	
		windowHelper.waitForModalFormDialog("Add Tag");
		sleepFor(3000);
		sendKeys(intrayTools.editTagsOrNameTxt, TagName8, "Adding or editing the tag name as"+TagName8);
		sleepFor(2000);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		String actValidationMsg1 = intrayTools.validationMsg.getText();
		boolean validationMsgForExistingTagName1 = actValidationMsg1.equals(TagName8 + "already exists.");
		if(!validationMsgForExistingTagName1) {
//			click(intrayTools.okBtn,"Clicking on ok button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		else{
//			click(intrayTools.cancelBtn,"Clicking on cancel button");	
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressTab();
			new ActionHelper(driver).pressEnter();
		}
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
		sleepFor(3000);
//		click(intrayTools.okBtn,"Clicking on ok button");	
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		windowHelper.waitForModalFormDialog("Add/Remove Tags");
//		click(intrayTools.addOrRemoveTagTxt,"clicking on tag name as"+TagName1);	
		sleepFor(1000);
		sendKeysWithoutClear(intrayTools.addOrRemoveTagTxt, TagName8, "Adding the tag name as"+TagName8);
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(1000);
		new ActionHelper(driver).pressEscape();
		sleepFor(1000);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		new ActionHelper(driver).pressEnter();
		sleepFor(12000);

//		click(intrayTools.okBtn,"clicking on ok button");	
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		
//		verification
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_STD_8);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		String ActualtagName1 = navigationMenu.getColumnValueFromTable("Tags");					

		
    	AssertionHelper.verifyTextContains(ActualtagName, TagName7);
    	AssertionHelper.verifyTextContains(ActualtagName1, TagName8);
	}	

	////===============================================================================================================================	
	////===============================================================================================================================	
	//	For Basic user
		
	//Verify that  Refresh button  is available and is Able to refresh on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 501, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void verifyRefreshButtonIsAvailableAndIsAbleToRefreshForbasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
	//	for intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_BAS_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	//	capture.clickOnFirstItemOfIntray();
	//	String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
	//	AssertionHelper.verifyText(docRef1, ActualDocumentRef);					//docRef4		"DR4_SAU_AT6744"
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
	
	//	for document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_BAS_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		
		refreshPage();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
	//	capture.clickOnFirstItemOfIntray();
	//	String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
	//	AssertionHelper.verifyText(docRef1, ActualDocumentRef1);					//docRef4		"DR4_SAU_AT6744"
		sleepFor(3000);
		navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
	
		AssertionHelper.verifyTextContains(ActTitleTxt, "In-Tray");
		AssertionHelper.verifyTextContains(ActTitleTxt1, "Document Search");
		}
		
	//Verify that  Statistics button  is available and  is Able to view statistics on Document / Intray Tool ribbon for  Basic User 
	//@Test(priority = 502, enabled = true)
	@Test(priority = 502, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	//@Test(priority = 502, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser", "prerequisiteForSupervisorUser"})
	public void VerifyThatStatisticsButtonIsAvailableAndIsAbleToViewStatisticsOnIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_BAS_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnIntrayListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actIntrayCount = intrayTools.statisticPopupIntrayCount.getText();
		String actAccountRefCount = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
	
//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef_BAS_1);						//docRef4 "DR4_SAU_AT6744"
		capture.clickOnDocumentListBtn();
		navigationMenu.clickOnIconMenu("Show results statistics", "General");
	
		new WindowHelper(driver).waitForPopup("Statistics");
		String actDocumentCount1 = intrayTools.statisticPopupDocumentCount.getText();
		String actAccountRefCount1 = intrayTools.statisticPopupAccountRefCount.getText();		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
	
		AssertionHelper.verifyTextContains("1", actIntrayCount);
		AssertionHelper.verifyTextContains("1", actAccountRefCount);
		AssertionHelper.verifyTextContains("1", actDocumentCount1);
		AssertionHelper.verifyTextContains("1", actAccountRefCount1);	
	}	
		
	//Verify that  View button  is available and is not able to view on Document / Intray Tool ribbon for  Basic User 
	//@Test(priority = 503, enabled = true)
	@Test(priority = 503, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	//@Test(priority = 503, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser", "prerequisiteForSupervisorUser"})
	public void verifyViewButtonIsAvailableAndIsNotAbleToViewOnDocumentAndIntrayToolForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
		
			String actviewDocumentH2txt = intrayTools.viewDocumentH2txt.getText();
			sleepFor(2000);
			AssertionHelper.verifyText("Something went wrong, please retry or contact your system administrator.", actviewDocumentH2txt);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	
	//	For Intray List
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		new NavigationMenu(driver).waitForIcon("View document", "Document");
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			String actviewDocumentH2txt1 = intrayTools.viewDocumentH2txt.getText();
			sleepFor(2000);
			AssertionHelper.verifyText("Something went wrong, please retry or contact your system administrator.", actviewDocumentH2txt1);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	//Verify that  Launch button  is available and is able to view the document on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 504, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	//@Test(priority = 504, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser", "prerequisiteForSupervisorUser"})
	public void VerifyLaunchButtonIsAvailableAndIsAbleToViewTheDocumentOnDocumentAndIntrayForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
	}	

//	Verify that  copy button  is not available and is not able to copy document on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 505, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void CopyButtonIsNotAvailableAndIsNotAbleToCopyTheDocumentOnDocumentAndIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
	//	copy document using copy button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	
	//	copy document using copy button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Create a copy of document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== copy button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	}

//	Verify that  Add  button  is not available and is not able to add the document with add button on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 506, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void AddButtonIsNotAvailableAndIsNotAbleToAddTheDocumentWithAddButtonOnDocumentAndIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		copy document using copy button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== add button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== add button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		copy document using copy button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== add button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Add new document to case");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== add button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	}
	
//	Verify that  Reindex button  is not available on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 507, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void ReindexButtonIsNotAvailableAndNotAbleToReindexOnDocumentAndIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
//		Reindex document using Reindex button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Reindex button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Reindex button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
			
//		Reindex document using Reindex button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
	
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Reindex button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Reindex document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Reindex button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
	}
	
//	Verify that  Rereference button  is not available  and is not able to Rereference documents on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 508, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void ReferenceButtonIsNotAvailableAndIsNotAbleToReferenceDocumentsOnDocumentAndIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
//		Reference document using Reference button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , " Re-Reference document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Re-Reference button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , " Re-Reference document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Re-Reference button is not present in intray list");
			sleepFor(2000);		
		}
		
//		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);		
		navigationMenu.collapseNavigationMenu("Document");
		
//		Reference document using Reference button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
	
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , " Re-Reference document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "===================  Re-Reference button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , " Re-Reference document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "===================  Re-Reference button is not present in document list");
			sleepFor(2000);		
		}
//		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);		
		navigationMenu.collapseNavigationMenu("Document");
	}
	
//	Verify that  Delete button  is not available and is not Able to Delete documents on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 509, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void DeleteButtonIsNotAvailableAndIsNotAbleToDeleteDocumentsOnDocumentAndIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
	
//		Delete document using delete button in intraylist
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Delete button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "=================== Delete button is not present in intray list");
			sleepFor(2000);		
		}
		
//		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);		
		navigationMenu.collapseNavigationMenu("Document");
		
//		Delete document using delete button in Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_3);						
		capture.clickOnDocumentListBtn();
		sleepFor(3000);	
//		capture.clickOnFirstItemOfIntray();
	
		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "===================  Delete button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Delete document");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyFalse(status, "===================  Delete button is not present in document list");
			sleepFor(2000);		
		}
//		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);		
		navigationMenu.collapseNavigationMenu("Document");
	}

//	Verify that  Associate button  is available and is able to view on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 510, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void verifyAssociateButtonIsAvailableAndIsAbleToViewOnDocumentAndIntrayToolRibbonForbasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		accRef_BAS_8_1 = "MR8_1" + "_" + basicUserName + randomNo;
		miscRef_BAS_8_1 = "MR8_1" + "_" + basicUserName + randomNo;		
		propRef_BAS_8_1 = "PR8_1" + "_" + basicUserName + randomNo;		

		accRef_BAS_8_2 = "MR8_2" + "_" + basicUserName + randomNo;
		miscRef_BAS_8_2 = "MR8_2" + "_" + basicUserName + randomNo;		
		propRef_BAS_8_2 = "PR8_2" + "_" + basicUserName + randomNo;	
		String docType = "Default - General Default Document Type";
		
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
	
//		Login with super admin user to capture document for Basic user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());

//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_BAS_8_1, miscRef_BAS_8_1, propRef_BAS_8_1);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_BAS_8);
		sleepFor(2000);

		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User", getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(basicUserName, getEnvVariable);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);		

//			Login with new user first time
		new LoginPage(driver).loginWithCredentials(basicUserName, ObjectReader.reader.getPassword());

//		verification
//		verify associated button is present on intray list
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");				

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in intray list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt1 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt2 = navigationMenu.navBarTitleLbl.getText();
 		

 //		For Document list
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
	
//		Login with super admin user to capture document for Basic user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
    	
    	getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef_BAS_8_2, miscRef_BAS_8_2, propRef_BAS_8_2);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.enterDocRef(docRef_BAS_8);
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		sleepFor(2000);
		capture.selectRouteToDD(basicUserName,getEnvVariable);

		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 35);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);		

//		Login with new user first time
		new LoginPage(driver).loginWithCredentials(basicUserName, ObjectReader.reader.getPassword());
		
//		verification
//		verify associated button is present on document list
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");					
		String ActualPropertyRef1 = navigationMenu.getColumnValueFromTable("Property Ref");					

		navigationMenu.expandNavigationMenu("Document");
		sleepFor(2000);	
		if(envType.equals("Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		else if(envType.equals("NECDM_Lives")) {
			String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , "Select entity");
			boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
			AssertionHelper.verifyTrue(status, "=================== associated button is not present in document list");
			sleepFor(2000);		
		}
		navigationMenu.expandNavigationMenu("Document");
		
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Account",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt3 = navigationMenu.navBarTitleLbl.getText();
//		for Show Misc
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Misc",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt4 = navigationMenu.navBarTitleLbl.getText();
//		for Show Account
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef_BAS_8_2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnAssociatedAndSelect("Show Property",getEnvVariable);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
    	String ActTitleTxt5 = navigationMenu.navBarTitleLbl.getText();
 		
		AssertionHelper.verifyText(docRef_BAS_8, ActualDocRef);
		AssertionHelper.verifyText(propRef_BAS_8_1, ActualPropertyRef);
		AssertionHelper.verifyText(docRef_BAS_8, ActualDocRef1);
		AssertionHelper.verifyText(propRef_BAS_8_2, ActualPropertyRef1);

    	AssertionHelper.verifyTextContains(ActTitleTxt, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt1, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt2, "Property Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt3, "Account Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt4, "Misc Landing Page");
    	AssertionHelper.verifyTextContains(ActTitleTxt5, "Property Landing Page");
	}

//	Verify that  Manage Tags button  is available and Can associate an existing Tag to the document on Document / Intray Tool ribbon for  Basic User 
	@Test(priority = 511, enabled = true, dependsOnMethods = {"prerequisiteForBasicUser"})
	public void verifyManageTagsButtonIsAvailableAndIsAbleToAddOrRemoveTagsOnDocumentAndIntrayToolRibbonForBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String docType = "Default - General Default Document Type";
		String expMsg = "User does not have System Implementer or System Administrator or DIP security permission."; 

//		For Intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_8);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		
		new WindowHelper(driver).waitForPopup("GetDocumentTagMapping Failed");
		String getActMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getActMsg);
		sleepFor(3000);
    	AssertionHelper.verifyText(expMsg, getActMsg);

//		For Document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef_BAS_8);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		
		new WindowHelper(driver).waitForPopup("GetDocumentTagMapping Failed");
		String getActMsg1 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getActMsg1);
		sleepFor(3000);
    	AssertionHelper.verifyText(expMsg, getActMsg1);    	
	}
	
////	===============================================================================================================================	
////	===============================================================================================================================	
	
}











