package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

public class TestMailStatusUAT_5 extends TestBase {
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

	private Logger log = LoggerHelper.getLogger(CapturePage.class);

	private String envType="NECDM_Lives";
	private String randomNo = generateRandomNumber();
	private String superAdminUserName = "SAU_AT"; 
	private String supervisorUserName = "SUV_AT"; 
	
	private String Role, RoleName, TeamId, TeamName, statusCode, statusDescription, statusCode1, statusDescription1,typeId, groupName, folderName;

	private String 	docRef_SAU_1, accRef_SAU_1, docRef_SAU_2, accRef_SAU_2, docRef_SAU_3, accRef_SAU_3, docRef_SAU_4, accRef_SAU_4, docRef_SAU_5, accRef_SAU_5, docRef_SAU_6, accRef_SAU_6, docRef_SAU_7, accRef_SAU_7, docRef_SAU_8, accRef_SAU_8, docRef_SAU_9, accRef_SAU_9;
	
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
		statusCode1 =  "SC1"+ "_" + randomNo;
		statusDescription1 ="Desc_"+statusCode;
		typeId = superAdminUserName;
		groupName = "GN"+ "_" + superAdminUserName;
		folderName = "Account ref";
		docRef_SAU_1 = "DR1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_1 = "AR1" +"_" + superAdminUserName + randomNo;
		
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
			adminUser.reAssignRightsToSuperAdminUser(superAdminUserName, getEnvVariable);
			sleepFor(2000);
//			adminUser.EditUserWithTeamAndRole(superAdminUserName, RoleName, TeamName);	//basic user or standard user use this
//			sleepFor(1000);

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
			teams.addMailStatusToTeam(TeamId, statusCode, getEnvVariable);
		}	
	
//		Verify 2nd mail status is added		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		WebElement filteredmailstatus1 = navigationMenu.getfilteredRowElement("mailStatusDataTable");
		String filteredMailStatus1 = filteredmailstatus1.getText();
		boolean mailstatus1 = (statusCode.equals(filteredMailStatus1));
		if (mailstatus1 == false) {
			sleepFor(1000);
//			add Mail Status
			mailStatus.selectAndAddMailStatus("Active", statusCode1, statusDescription1, TeamName, getEnvVariable);
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
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_1, accRef_SAU_1, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

	}
	
////=============================================================================================================================	
////=============================================================================================================================	
//	For Super Admin User
//	Verify that tooltip of 'Deleted' submenu should display 'Deleted mail statuses maintanence'.
//	@Test(priority = 3, enabled = true)
	@Test(priority = 3, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTooltipOfDeletedSubMenuShouldDisplayDeletedMailStatusesMaintenance() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Deleted");
		sleepFor(3000);
		String expTitle = "Deleted Mail Status";
		String actTitle = navigationMenu.navBarTitleLbl.getText();
		AssertionHelper.verifyText(actTitle, expTitle);
 	}

////	pending --> how to have no mail status
////	Verify that if no mail status are available, 'Edit' and 'Delete' button should be disabled.
////	@Test(priority = 3, enabled = true)
//	@Test(priority = 3, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	public void ToVerifyThatIfNoMailStatusAreAvailableEditAndDeleteButtonShouldBeDisabled() throws InterruptedException {
//		String getEnvVariable = System.getProperty("propertyName");
//		System.out.println("getEnvVariable====================" + getEnvVariable);
//		String superAdminUserName1 = "SAU_AT_forNMS";							
//		
////		Verify created new user is added
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIcon("User maintenance", "User");
//		navigationMenu.searchInFilter(superAdminUserName1);
//		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
//		String filteredUser = filteredElement.getText();
//		boolean userStatus = (superAdminUserName1.equals(filteredUser));  		
//		if (userStatus==false) {
//			sleepFor(1000);
////			Create user
//			adminUser.CreateSuperAdminUser(superAdminUserName1, getEnvVariable);
//			sleepFor(1000);
//			
////			logout from the user or change user
//			getApplicationUrl();
//			new HomePage(driver).clickOnChangeUser();
//			sleepFor(2000);
//
////	 		For new user change password
//			new LoginPage(driver).loginWithCredentialsForChangePassword(superAdminUserName1, "P@ssword123");
//		}	
//		else {
////			adminUser.reAssignRightsToSuperAdminUser(superAdminUserName1, getEnvVariable);
////			sleepFor(2000);
//
////			logout from the user or change user
//			new HomePage(driver).clickOnChangeUser();
//			sleepFor(2000);
//		}
//
//// 		Login with new user first time and change password
//		new LoginPage(driver).loginWithCredentials(superAdminUserName1, ObjectReader.reader.getPassword());
//
//		getApplicationUrl();
//		sleepFor(1000);
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
//		sleepFor(2000);
//		navigationMenu.clickOnIconMenu("Active");
//		
//		sleepFor(1000);
//		String [] buttonsIconToolTip1 = {"Edit selected mail status ","Delete selected mail status"};
//		for(int i=0; i<=buttonsIconToolTip1.length-1; i++)
//		{
//			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip1[i]);
//			WebElement button=driver.findElement(By.xpath(Xpath));
//			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
//			AssertionHelper.verifyFalse(Button, "---------- The Element " + buttonsIconToolTip1[i] + " is disabled");
//			sleepFor(1000);
//		}	
//
////		logout from the user or change user
//		new HomePage(driver).clickOnChangeUser();
//		sleepFor(2000);
//
////		Login with new user first time and change password
//		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
//	}
	
//	Verify that 'Edit' and 'Delete' button should be disabled if no record is selected.
//	@Test(priority = 4, enabled = true)
	@Test(priority = 4, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatEditAndDeleteButtonShouldBeDisabledIfNoRecordIsSelected() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		
		sleepFor(1000);
		String [] buttonsIconToolTip1 = {"Edit selected mail status ","Delete selected mail status"};
		for(int i=0; i<=buttonsIconToolTip1.length-1; i++)
		{
			String Xpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , buttonsIconToolTip1[i]);
			WebElement button=driver.findElement(By.xpath(Xpath));
			boolean Button = new VerificationHelper(driver).isElementEnabled(button);
			AssertionHelper.verifyFalse(Button, "---------- The Element " + buttonsIconToolTip1[i] + " is disabled");
			sleepFor(1000);
		}
 	}
	
//	Verify that the 'Changes To' dropdown is disabled by default.
//	@Test(priority = 5, enabled = true)
	@Test(priority = 5, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTheChangesToDropdownIsDisabledByDefault() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		
		navigationMenu.clickOnIcon("Add a new mail status", "Actions");
		navigationMenu.waitForMenuIcon("Cancel changes", "Actions", getEnvVariable);		
		new JavaScriptHelper(driver).scrollToBottom();

		sleepFor(2000);
		boolean disabledDD = new VerificationHelper(driver).isElementEnabled(mailStatus.changesToDD);
		AssertionHelper.verifyFalse(disabledDD, "Verifying the changes to dropdown is disabled");
	}	
	
//	Verify that the 'Changes To' dropdown is enabled when 'Is Timed Change' checkbox is selected.
//	@Test(priority = 6, enabled = true)
	@Test(priority = 6, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTheChangesToDropdownIsEnabledWhenIsTimedChangeCheckboxIsSelected() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		navigationMenu.clickOnIcon("Add a new mail status", "Actions");
		navigationMenu.waitForMenuIcon("Cancel changes", "Actions", getEnvVariable);

		new JavaScriptHelper(driver).scrollToBottom();
		mailStatus.isTimedChangedChkbx.click();
		boolean enabledDD = new VerificationHelper(driver).isElementEnabled(mailStatus.changesToDD);
		AssertionHelper.verifyTrue(enabledDD, "Verifying the changes to dropdown is enabled");
	}
	
//	Verify that the Mail Status should not get saved on the click of No button or 'Cancel' button.
//	Verify that ,on the click of 'Cancel' button of the Leave page popup, the system should stay on the same page.
//	@Test(priority = 7, enabled = true)
	@Test(priority = 7, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTheMailStatusShouldNotGetSavedOnTheClickOnCancelBtn() throws InterruptedException {
		String statusCode =  "SC"+ randomNo;
		String statusDescription ="Desc_"+statusCode;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if (!status) {
			sleepFor(1000);
			refreshPage();
			sleepFor(1000);
		}				
		navigationMenu.clickOnIcon("Add a new mail status", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
		sendKeys(mailStatus.statusCodeTxt,statusCode,"Entering status code as "+statusCode);
		sendKeys(mailStatus.statusDescriptionTxt,statusDescription,"Entering status description as "+statusDescription);
		new JavaScriptHelper(driver).scrollToBottom();
		mailStatus.availableTeamDD(TeamName, getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIcon("Cancel changes", "Actions");
		
 		new WindowHelper(driver).waitForPopup("Leave Page");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickCancelOnPopup1("Leave Page");
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
		sleepFor(1000);
		
//		verification
		String expTitle = "Add Mail Status";
		String actTitle = navigationMenu.navBarTitleLbl.getText();

		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIcon("Cancel changes", "Actions");
 		new WindowHelper(driver).waitForPopup("Leave Page");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickCancelOnPopup("Leave Page");
		sleepFor(2000);	
		System.out.println("get message1 ===================================="+getMsg1);
		sleepFor(1000);		
		navigationMenu.waitForIcon("Add a new mail status");
		
		//Verification				
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
//		boolean status1 = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
//		if (!status1) {
//			sleepFor(1000);
//			refreshPage();
//			sleepFor(1000);
//		}				
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		
		String actStatusCode = navigationMenu.getNoRecordsFoundMessage("mailStatusDataTable");
//		WebElement actStatusCode = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusCode+"']"));
//		WebElement actStatusCodeXpath = navigationMenu.getfilteredRowElement("deletedMailStatusDataTable");
//		String actStatusCode = actStatusCodeXpath.getText();

		AssertionHelper.verifyText(actTitle, expTitle);
		AssertionHelper.verifyText(actStatusCode, "No matching records found");
	}
	
//	Verify that the Mail Status should get saved on the click of Yes button on 'Cancel' button.
//	@Test(priority = 8, enabled = true)
	@Test(priority = 8, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTheMailStatusShouldGetSavedOnTheClickOnYesBtnOnCancelBtn() throws InterruptedException {
		String statusCode =  "SC"+ randomNo;
		String statusDescription ="Desc_"+statusCode;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if (!status) {
			sleepFor(1000);
			refreshPage();
			sleepFor(1000);
		}				
		navigationMenu.clickOnIcon("Add a new mail status", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
		sendKeys(mailStatus.statusCodeTxt,statusCode,"Entering status code as "+statusCode);
		sendKeys(mailStatus.statusDescriptionTxt,statusDescription,"Entering status description as "+statusDescription);
		new JavaScriptHelper(driver).scrollToBottom();
		mailStatus.availableTeamDD(TeamName, getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIcon("Cancel changes", "Actions");
 		new WindowHelper(driver).waitForPopup("Leave Page");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup("Leave Page");
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
		sleepFor(1000);		
		navigationMenu.waitForIcon("Add a new mail status");
		
		//Verification				
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
//		boolean status1 = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
//		if (!status1) {
//			sleepFor(1000);
//			refreshPage();
//			sleepFor(1000);
//		}				
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);

		WebElement actStatusCodeXpath = navigationMenu.getfilteredRowElement("mailStatusDataTable");
//		WebElement actStatusCode = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusCode+"']"));
		String actStatusCode = actStatusCodeXpath.getText();
		AssertionHelper.verifyText(actStatusCode, statusCode);
	}	
	
//	Verify that a mail status should not get deleted on the click of 'Cancel' button on the popup after clicking 'Delete' button.
//	@Test(priority = 9, enabled = true)
	@Test(priority = 9, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTheMailStatusShouldNotGetDeletedOnTheClickOfCancelButtonOnThePopupAfterClickingDeleteButton() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String mailStatus = "Active";
		
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		
		navigationMenu.clickOnIconMenu(mailStatus);
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Delete selected mail status", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickCancelOnPopup("Delete");
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);

		
		//Verification				
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		
		WebElement actStatusCodeXpath = navigationMenu.getfilteredRowElement("mailStatusDataTable");
		String actStatusCode = actStatusCodeXpath.getText();
//		WebElement actStatusCode = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusCode1+"']"));
		AssertionHelper.verifyText(actStatusCode, statusCode1);
	}		
	
//	Verify that on the Edit Mail Status page Status Code, Description textboxes are disabled
//	@Test(priority = 10, enabled = true)
	@Test(priority = 10, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatOnTheEditMailStatusPageStatusCodeDescriptionTextBoxexAreDisabled() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String mailStatus = "Active";
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
//		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
//		if (!status) {
//			sleepFor(1000);
//			refreshPage();
//			sleepFor(1000);
//		}
		navigationMenu.searchInFilter(statusCode);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);			
		navigationMenu.clickOnIcon("Edit selected mail status ", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
		
		//Verification				
		boolean StatusCode = verificationHelper.isElementEnabled(new MailStatus(driver).statusCodeTxt);
		boolean StatusCodeDescription = verificationHelper.isElementEnabled(new MailStatus(driver).statusDescriptionTxt);

		try {
			AssertionHelper.verifyFalse(StatusCode, "The Element "+ new MailStatus(driver).statusCodeTxt +"is disbled");
			System.out.println("--------StatusCode------"+ StatusCode);

		} catch (NoSuchElementException e) {
		    System.out.println("Element is enabled");
		}
		try {
			AssertionHelper.verifyTrue(StatusCodeDescription, "The Element "+ new MailStatus(driver).statusCodeTxt +"is enabled");
			System.out.println("--------StatusCodeDescription------"+ StatusCodeDescription);
		} catch (NoSuchElementException e) {
		    System.out.println("Element is disabled");
		}
	}		
	
//	Verify that the deleted mail status should be available in the 'Deleted' submenu of the Mail Status.
//	Verify that the deleted mail status should not get restored when clicked on the 'Cancel' button of the popup
//	Verify that the deleted mail status should get restored when clicked on the 'Ok' button of the popup
//	Verify that a popup message should appear on the click of the 'Restore' button.
//	Verify that the deleted mail status which was restored should now be displayed under the 'Active' submenu.	
//	// Verify that 'Restore' button should be disabled if no record is selected.
//	@Test(priority = 11, enabled = true)
	@Test(priority = 11, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatTheDeletedMailStatusShouldBeAvailableInTheDeletedSubmenuOfTheMailStatus() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String mailStatus = "Active";
		String mailStatus1 = "Deleted";
		
//		Verify that the deleted mail status should be available in the 'Deleted' submenu of the Mail Status.
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		sleepFor(2000);
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Delete selected mail status", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(3000);	
		System.out.println("get message ===================================="+getMsg);
		
		//Verification 1				
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);

		String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("mailStatusDataTable");
		String expMsgInEmptyTable = "No matching records found";
		
		//Verification 2				
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus1);
		navigationMenu.waitForMenuIcon("Restore the deleted mail status", "Actions", getEnvVariable);
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("deletedMailStatusDataTable");
		sleepFor(2000);

		WebElement actDeletedStatusCodeXpath = navigationMenu.getfilteredRowElement("deletedMailStatusDataTable");
		String actDeletedStatusCode = actDeletedStatusCodeXpath.getText();
//		WebElement actDeletedStatusCodeXpath = driver.findElement(By.xpath("//table[@id='deletedMailStatusDataTable']//*[text()='"+statusCode1+"']"));
//		String actDeletedStatusCode = actDeletedStatusCodeXpath.getText();
		
//		Verify that the deleted mail status should not get restored when clicked on the 'Cancel' button of the popup
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus1);
		navigationMenu.waitForMenuIcon("Restore the deleted mail status", "Actions", getEnvVariable);
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("deletedMailStatusDataTable");
		sleepFor(2000);

		WebElement actStatusCodeXpath = navigationMenu.getfilteredRowElement("deletedMailStatusDataTable");
		String actStatusCode = actStatusCodeXpath.getText();
		
		sleepFor(2000);
		navigationMenu.clickOnIcon("Restore the deleted mail status", "Actions");
		new WindowHelper(driver).waitForPopup("Restore");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
//		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg1);
		String expMsg = "Are you sure you want to Restore "+ statusCode1 +"?";
		new WindowHelper(driver).clickCancelOnPopup("Restore");
				
//		Verification :--> popup message appear on the click of the 'Restore' button			
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(2000);
//		navigationMenu.clickOnFilteredRow("deletedMailStatusDataTable");	
		sleepFor(2000);

//		Verify that the deleted mail status should get restored when clicked on the 'Ok' button of the popup
		navigationMenu.clickOnIcon("Restore the deleted mail status", "Actions");
		new WindowHelper(driver).waitForPopup("Restore");
		String getMsg2 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg2);

//		verification1
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus1);
		navigationMenu.waitForMenuIcon("Restore the deleted mail status", "Actions", getEnvVariable);
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("deletedMailStatusDataTable");
		sleepFor(2000);

		WebElement actStatusCodeXpath1 = navigationMenu.getfilteredRowElement("deletedMailStatusDataTable");
		String actStatusCode1 = actStatusCodeXpath1.getText();
		
//		Verify that the deleted mail status which was restored should now be displayed under the 'Active' submenu.	
//		verification2
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		navigationMenu.waitForIcon("Add a new mail status");
		navigationMenu.searchInFilter(statusCode1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);

		WebElement actStatusCodeXpath2 = navigationMenu.getfilteredRowElement("mailStatusDataTable");
		String actStatusCode2 = actStatusCodeXpath2.getText();
//		WebElement actStatusCodeXpath2 = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusCode1+"']"));
//		String actStatusCode2 = actStatusCodeXpath2.getText();
		
		AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable); 
		AssertionHelper.verifyText(actDeletedStatusCode, statusCode1);
		AssertionHelper.verifyText(actStatusCode, statusCode1);
		AssertionHelper.verifyText(getMsg1, expMsg);
		AssertionHelper.verifyText(actStatusCode1, "No matching records found");
		AssertionHelper.verifyText(actStatusCode2, statusCode1);
	}		
	
//	Verify that 'Restore' button should be disabled if there are no deleted mail status.
	@Test(priority = 12, enabled = true)
//	@Test(priority = 12, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatRestoreButtonShouldBeDisabledIfThereAreNoDeletedMailStatus() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String mailStatus = "Deleted";
		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		navigationMenu.waitForMenuIcon("Restore the deleted mail status", "Actions", getEnvVariable);
		
//		verification
		navigationMenu.isIconEnabled("Restore the deleted mail status", "Actions");
	}
	
//	Verify that document having mail status with "Outstanding item" flag checked can be seen in my intray
//	To verify the Change button functionality under Intray
//	To verify the "From" textbox in Change Mail Status window
//	To verify the "To" dropdown in Change Mail Status window	
	@Test(priority = 13, enabled = true)
//	@Test(priority = 13, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatDocumentHavingMailStatusWithOutstandingItemFlagCheckedCanBeSeenInMyIntray() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String TeamId = "TId"+ "_" + superAdminUserName;
		
		String mailStatus = "Active";
//		String mailStatus1 = "Deleted";
		
//		String toMailStatus = "New";
//		String toMailStatus1 = "In Progress";
		String toMailStatus2 = "Verified";
//		String toMailStatus3 ="Complete";
		
		docRef_SAU_2 = "DR2" +"_" + superAdminUserName + randomNo;
		accRef_SAU_2 = "AR2" +"_" + superAdminUserName + randomNo;
		
		
////		pre-requisite : Logged in user should have Take Work from a Shared In-Tray right
////		add Take Work from a Shared In-Tray right
//		getApplicationUrl();
//		navigationMenu.clickOnTab("Administration");
//		navigationMenu.clickOnIcon("User maintenance", "User");
//		navigationMenu.searchInFilter(superAdminUserName); 										// User_N147 //User_N5341
//		navigationMenu.clickOnFilteredRow("userDataTable");
//		navigationMenu.clickOnEditIcon();
//		sleepFor(2000);
//		new AdminUserSection(driver).clickOnUserNavTab("Security");
//		sleepFor(2000);
//		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
//		List<String> intrayProcessingList = new ArrayList<String>();
//		intrayProcessingList.add("Take Work from a Shared In-Tray");
//		userPermission.put("Intray Processing", intrayProcessingList);
//		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
//		sleepFor(1000);
//		if (getEnvVariable.equals("Enterprise_Sp1s")) {
//			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
//			sleepFor(2000);
//			WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
//			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
//			String getMsg = new WindowHelper(driver).getPopupMessage();
//			if(getMsg == "No changes to save.") {
//				new WindowHelper(driver).clickOkOnPopup();
//				navigationMenu.clickOnIcon("Cancel changes", "Actions");
//				navigationMenu.waitForAddIcon();
//			}
//		} else {
//			navigationMenu.clickOnSaveIcon();
//			sleepFor(2000);
//			WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
//			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
//			String getMsg = new WindowHelper(driver).getPopupMessage();
//			if(getMsg == "No changes to save.") {
//				new WindowHelper(driver).clickOkOnPopup();
//				navigationMenu.clickOnIcon("Cancel changes", "Actions");
//				navigationMenu.waitForAddIcon();
//				}
//		}
//
//		sleepFor(10000);
//		
//				
//		getApplicationUrl();
//		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
//		sleepFor(2000);
				
		
//		precondition - 
//		String [] toMailStatus = {"New","Verified"};
//		for(int i=0; i<=toMailStatus.length-1; i++)
//		{
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		sleepFor(2000);
		navigationMenu.searchInFilter(toMailStatus2);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Edit selected mail status ", "Actions");
		navigationMenu.waitForIcon("Save changes");
		boolean outstandingItemChkbxStatus = verificationHelper.isElementSelected(this.mailStatus.IsOutstandingItemChkbx);
		if(outstandingItemChkbxStatus == false) {
			this.mailStatus.IsOutstandingItemChkbx.click();
			sleepFor(1000);
			navigationMenu.clickOnIcon("Save changes made to mail status", "Actions");
		}
		else {
			sleepFor(1000);
			navigationMenu.clickOnIcon("Cancel changes", "Actions");
		}
	 	navigationMenu.waitForAddIcon();
//		}
		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_2, accRef_SAU_2, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	
		
//		Change mail status 1st time
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(toMailStatus2);

//		verification : "From" textbox in Change Mail Status window is disable
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);		
		capture.clickOnChangeEmailStatusButton();
		waitHelper.waitForElement(capture.changeMailStatusLbl, 40);
		sleepFor(4000);
		String fromMailStatusContent = new IntrayToolsPage(driver).fromTxtBox.getAttribute("readonly");
		System.out.println("-------------------->>>>>>>>>>>------->"+ fromMailStatusContent);
		System.out.println("-------------------->>>>>>>>>>>------->>"+ fromMailStatusContent.equalsIgnoreCase("readonly"));

//		AssertionHelper.verifyTrue(fromMailStatusContent.equalsIgnoreCase("true"), "--------------------enabledFromMailStatus-"+ fromMailStatusContent);
		AssertionHelper.verifyFalse(fromMailStatusContent.equalsIgnoreCase("readonly"), "--------------------enabledFromMailStatus-"+ fromMailStatusContent);

//		verification
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActualTeamName = navigationMenu.getColumnValueFromTable("Team");


		AssertionHelper.verifyText(toMailStatus2, ActualMailStatus);	
		AssertionHelper.verifyText(docRef_SAU_2, ActualDocumentRef);	
		AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Outstanding Item"), "Checkking if green tick present for Outstanding Item column");
		AssertionHelper.verifyText(TeamId, ActualTeamName);	
	}

//	e2e
//	Verify that document having mail status with "Outstanding item" flag unchecked can not be seen in my intray
//	@Test(priority = 14, enabled = true)
	@Test(priority = 14, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void ToVerifyThatDocumentHavingMailStatusWithOutstandingItemFlagUncheckedCanBeSeenInMyIntray() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String mailStatus = "Active";
//		String mailStatus1 = "Deleted";
		
//		String toMailStatus = "New";
//		String toMailStatus1 = "In Progress";
		String toMailStatus2 = "Verified";
//		String toMailStatus3 ="Complete";

		docRef_SAU_3 = "DR3" +"_" + superAdminUserName + randomNo;
		accRef_SAU_3 = "AR3" +"_" + superAdminUserName + randomNo;

//		precondition - 
//		String [] toMailStatus = {"New","Verified"};
//		for(int i=0; i<=toMailStatus.length-1; i++)
//		{
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
			sleepFor(2000);
			navigationMenu.clickOnIconMenu(mailStatus);
			sleepFor(2000);
			navigationMenu.searchInFilter(toMailStatus2);
			sleepFor(1000);
			navigationMenu.clickOnFilteredRow("mailStatusDataTable");
			sleepFor(2000);
			navigationMenu.clickOnIcon("Edit selected mail status ", "Actions");
			navigationMenu.waitForIcon("Save changes");
			boolean outstandingItemChkbxStatus = verificationHelper.isElementSelected(this.mailStatus.IsOutstandingItemChkbx);
			if(outstandingItemChkbxStatus == true) {
				this.mailStatus.IsOutstandingItemChkbx.click();
				sleepFor(1000);
				navigationMenu.clickOnIcon("Save changes made to mail status", "Actions");
			}
			else {
				sleepFor(1000);
				navigationMenu.clickOnIcon("Cancel changes", "Actions");
			}
	 		navigationMenu.waitForAddIcon();
//		}	
	 		
//		capture documents
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_3, accRef_SAU_3, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	
		
//		Change mail status 1st time
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_3);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(toMailStatus2);

//		verification
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_3);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String actOutstandingItemValue = navigationMenu.getColumnValueFromTable("Outstanding Item");			
		
//		post-condition - 
//		String [] toMailStatus = {"New","Verified"};
//		for(int i=0; i<=toMailStatus.length-1; i++)
//		{
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
			sleepFor(2000);
			navigationMenu.clickOnIconMenu(mailStatus);
			sleepFor(2000);
			navigationMenu.searchInFilter(toMailStatus2);
			sleepFor(1000);
			navigationMenu.clickOnFilteredRow("mailStatusDataTable");
			sleepFor(2000);
			navigationMenu.clickOnIcon("Edit selected mail status ", "Actions");
			navigationMenu.waitForIcon("Save changes");
			boolean outstandingItemChkbxStatus1 = verificationHelper.isElementSelected(this.mailStatus.IsOutstandingItemChkbx);
			if(outstandingItemChkbxStatus1 == false) {

				this.mailStatus.IsOutstandingItemChkbx.click();
				sleepFor(1000);
				navigationMenu.clickOnIcon("Save changes made to mail status", "Actions");
			}
			else {
				sleepFor(1000);
				navigationMenu.clickOnIcon("Cancel changes", "Actions");
			}
	 		navigationMenu.waitForAddIcon();
//		}
	 		
		AssertionHelper.verifyText(actOutstandingItemValue, "");	
		AssertionHelper.verifyText(toMailStatus2, ActualMailStatus);	
		AssertionHelper.verifyText(docRef_SAU_3, ActualDocumentRef);	
		sleepFor(2000);
//		AssertionHelper.verifyFalse(navigationMenu.isGreenTickPresentForColumn("Outstanding Item"));
	}
	
//	e2e
//	To verify the Complete button in My intray
//	@Test(priority = 15, enabled = true)
	@Test(priority = 15, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyTheCompleteButtonInMyIntray() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Change mail status to complete", "Mail");
		new WindowHelper(driver).waitForPopup("Update Intray Mail Status");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(2000);
		new WindowHelper(driver).waitForPopup("Update Intray Mail Status");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg1);
		sleepFor(2000);
		boolean status = new WindowHelper(driver).isPopupDisplayed();
		if (status) {
			String getErrorMsg = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get error message ====================================" + getErrorMsg);
			sleepFor(2000);
		}
	
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			
		AssertionHelper.verifyText(getMsg1, "Mail status changed successfully.");
	//	AssertionHelper.verifyText(getMsg, "Are you sure you want to update the MailStatus to \"Complete\" for the selected item(s) ?");
		AssertionHelper.verifyText(ActualMailStatus, "Complete");
	}	
	
	
	
//	e2e
//	To verify the functionality of 'Intray History'
//	// To verify that when the setting 'HistoryExcludeMailStatus' is set as blank then the documents of all the mail status is displayed
//	@Test(priority = 18, enabled = true)
	@Test(priority = 18, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
//	@Test(priority = 18, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyTheFunctionalityOfIntrayHistory () throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		docRef_SAU_5 = "DR5" +"_" + superAdminUserName + randomNo;
		accRef_SAU_5 = "AR5" +"_" + superAdminUserName + randomNo;		
		String configKey = "HistoryExcludeMailStatus";
		String configValue = "P";
		String toMailStatus = "Pending";
//		pre-requisite
////		logout from the user or change user
//		getApplicationUrl();
//		new HomePage(driver).clickOnChangeUser();
//		sleepFor(2000);
//// 		Login with the another user
//		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		
		getApplicationUrl();
		toolkit.checkConfigValue(configKey, configValue);

//		Test Case
//		capture document 
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_5, accRef_SAU_5, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_5+"_1", accRef_SAU_5, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_5+"_2", accRef_SAU_5, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

//		Verify document is captured in intray list
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",accRef_SAU_5);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualAccRef = navigationMenu.getColumnValueFromTable("Account Ref");
		
		String ColumnName = "Doc Ref";
		String status = "",index="";
		try {
			sleepFor(2000);
			WebElement columnEle = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, ColumnName)));
			boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
			log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
		if (!columnEleVisibility){   //Previously is element present
			navigationMenu.selectColumnVisiblityFromColumnConfig(ColumnName);
		}
		index = columnEle.getAttribute("data-column-index");
		}
		catch(Exception e) {
			if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.gearIconBtn)) {
				navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
				sleepFor(1000);
			}
			navigationMenu.selectColumnVisiblityFromColumnConfig(ColumnName);
			WebElement columnElePost = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, ColumnName)));
			sleepFor(2000);
			//dragAndDropColumnToFirstPosition(columnElePost);
			index = columnElePost.getAttribute("data-column-index");
		}
		int actualIndex = Integer.parseInt(index)+1;
		String actDocRef = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		String actDocRef1 = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/ancestor::tr/following-sibling::tr/td["+String.valueOf(actualIndex)+"]")).getText();
		String actDocRef2 = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/ancestor::tr/following-sibling::tr[2]/td["+String.valueOf(actualIndex)+"]")).getText();

//		for intray history		
		navigationMenu.clickOnIcon("Intray History", "Mail");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			capture.clickOnFirstItemOfIntray();
			String ActualMailStatus3 = navigationMenu.getColumnValueFromTable("Mail Status");
			sleepFor(2000);	
			AssertionHelper.verifyText("New", ActualMailStatus3);		
			sleepFor(3000);	
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}	
		
//		Change mail status of 1st document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(toMailStatus);
//		Change mail status of 2nd document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5+"_1");
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(toMailStatus);

//		verification
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_5);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualMailStatus4 = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualDocumentRef4 = navigationMenu.getColumnValueFromTable("Doc Ref");
//		String actOutstandingItemValue = navigationMenu.getColumnValueFromTable("Outstanding Item");			
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Account Ref",accRef_SAU_5);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Intray History", "Mail");

		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			capture.clickOnFirstItemOfIntray();
			String ActualDocumentRef5 = navigationMenu.getColumnValueFromTable("Doc Ref");
			sleepFor(2000);
			AssertionHelper.verifyText(docRef_SAU_5+"_2", ActualDocumentRef5);		
			sleepFor(4000);

		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}

		AssertionHelper.verifyText(docRef_SAU_5, actDocRef);		
		AssertionHelper.verifyText(docRef_SAU_5+"_1", actDocRef1);		
		AssertionHelper.verifyText(docRef_SAU_5+"_2", actDocRef2);		
		AssertionHelper.verifyText(accRef_SAU_5, ActualAccRef);		
		AssertionHelper.verifyText("New", ActualMailStatus);
		
////		post-requisite
//		getApplicationUrl();
//		toolkit.checkConfigValue(configKey, "");
	}	
	
//	To verify that user is able to add multiple value for 'HistoryExcludeMailStatus' config setting
	@Test(priority = 19, enabled = true)
//	@Test(priority = 19, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyThatUserIsAbleToAddMultipleValueForHistoryExcludeMailStatusConfigSetting () throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		docRef_SAU_6 = "DR6" +"_" + superAdminUserName + randomNo;
		accRef_SAU_6 = "AR6" +"_" + superAdminUserName + randomNo;		
		String configKey = "HistoryExcludeMailStatus";
		String configValue = "";
		String toMailStatus = "Pending";
//		pre-requisite
		getApplicationUrl();
		toolkit.checkConfigValue(configKey, configValue);

//		Test Case
//		capture document 
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_6, accRef_SAU_6, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_6+"_1", accRef_SAU_6, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_6+"_2", accRef_SAU_6, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

//		Verify document is captured in intray list
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",accRef_SAU_6);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

//		for intray history		
		navigationMenu.clickOnIcon("Intray History", "Mail");
		try {
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			sleepFor(6000);
			capture.clickOnFirstItemOfIntray();
			String ActualMailStatus1 = navigationMenu.getColumnValueFromTable("Mail Status");
			
			String ColumnName = "Doc Ref";
			String status = "",index="";
			try {
				sleepFor(2000);
				WebElement columnEle = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, ColumnName)));
				boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
				log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
			if (!columnEleVisibility){   //Previously is element present
				navigationMenu.selectColumnVisiblityFromColumnConfig(ColumnName);
			}
			index = columnEle.getAttribute("data-column-index");
			}
			catch(Exception e) {
				if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.gearIconBtn)) {
					navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
					sleepFor(1000);
				}
				navigationMenu.selectColumnVisiblityFromColumnConfig(ColumnName);
				WebElement columnElePost = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, ColumnName)));
				sleepFor(2000);
				//dragAndDropColumnToFirstPosition(columnElePost);
				index = columnElePost.getAttribute("data-column-index");
			}
			int actualIndex = Integer.parseInt(index)+1;
			String actDocRef = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
			String actDocRef1 = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/ancestor::tr/following-sibling::tr/td["+String.valueOf(actualIndex)+"]")).getText();
			String actDocRef2 = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/ancestor::tr/following-sibling::tr[2]/td["+String.valueOf(actualIndex)+"]")).getText();
			
			sleepFor(2000);	
			AssertionHelper.verifyText(docRef_SAU_6, actDocRef);		
			AssertionHelper.verifyText(docRef_SAU_6+"_1", actDocRef1);		
			AssertionHelper.verifyText(docRef_SAU_6+"_2", actDocRef2);
			AssertionHelper.verifyText("New", ActualMailStatus1);		
			sleepFor(3000);	
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}	
		AssertionHelper.verifyText(accRef_SAU_6, ActualAccRef);		
		AssertionHelper.verifyText("New", ActualMailStatus);
	}	

//	e2e
//	To verify that the user should not be allowed to delete the mail status which is associated with the document, also the deleted mail status should not be displayed under Change dialogue box"
//	@Test(priority = 20, enabled = true)
	@Test(priority = 20, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyThatTheUserShouldNotBeAllowedToDeleteTheMailStatusWhichIsAssociatedWithTheDocument () throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		docRef_SAU_6 = "DR6" +"_" + superAdminUserName + randomNo;
		accRef_SAU_6 = "AR6" +"_" + superAdminUserName + randomNo;		
		
//		Test Case
//		capture document 
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_6, accRef_SAU_6, null, superAdminUserName, getEnvVariable);
		sleepFor(2000);	

//		Verify document is captured in intray list
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",accRef_SAU_6);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualAccRef = navigationMenu.getColumnValueFromTable("Account Ref");

		AssertionHelper.verifyText(accRef_SAU_6, ActualAccRef);		
		AssertionHelper.verifyText("New", ActualMailStatus);
	}	

//	e2e
//	Verify whether application allows to create mail status
//	To verify that the user should not be allowed to delete the mail status which is associated with the document, also the deleted mail status should not be displayed under Change dialogue box"
//	@Test(priority = 21, enabled = true)
	@Test(priority = 21, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyWhetherApplicationAllowsToCreateMailStatus () throws InterruptedException {
			statusCode =  "SC"+ randomNo;

			statusDescription ="Desc_"+statusCode;
			TeamId = "TId"+ "_" + superAdminUserName;
			TeamName = "TN_"+ TeamId;
			String MailStatus1 = "Active";
			String MailStatus2 = "Deleted";
			String toMailStatus1 = "In Progress";
			String toMailStatus2 = "Verified";
			docRef_SAU_9 = "DR9" +"_" + superAdminUserName + randomNo;
			accRef_SAU_9 = "AR9" +"_" + superAdminUserName + randomNo;		
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			String expDeleteFailedMessage = "Could not delete mail status "+statusCode+" since this is in use on the intray. Please delete the intray items with this mail status first.";
			
			
////		logout from the user or change user
//			sleepFor(4000);
//			new HomePage(driver).clickOnChangeUser();
//			sleepFor(2000);
//
//// 		Login with new user first time and change password
//			new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		
//			Add mail status
			getApplicationUrl();
			sleepFor(1000);
			mailStatus.selectAndAddMailStatus("Active", statusCode, statusDescription, TeamName, getEnvVariable);
			
			//Verification				
			getApplicationUrl();
			sleepFor(1000);
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
			sleepFor(2000);
			navigationMenu.clickOnIconMenu("Active");
//			boolean status1 = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
//			if (!status1) {
//				sleepFor(1000);
//				refreshPage();
//				sleepFor(1000);
//			}				
			navigationMenu.waitForIcon("Add a new mail status");
			navigationMenu.searchInFilter(statusCode);
			sleepFor(1000);
			navigationMenu.clickOnFilteredRow("mailStatusDataTable");
			sleepFor(2000);
			WebElement actStatusCode = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusCode+"']"));
			AssertionHelper.verifyText(actStatusCode.getText(), statusCode);
				
//			capture document 
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_9, accRef_SAU_9, null, superAdminUserName, getEnvVariable);
			sleepFor(2000);	
	
//			Change mail status 1st time
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			capture.changeMailStatusUsingSelectClass(statusDescription);
			capture.clickOnChangeEmailStatusButton();
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			//waitHelper.waitForElement(capture.changeMailStatusLbl, 40);
			sleepFor(4000);

//			verification
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
			capture.clickOnIntrayListBtn();
			sleepFor(2000);
			capture.clickOnFirstItemOfIntray();
			sleepFor(2000);
			String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
			String ActualTeamName = navigationMenu.getColumnValueFromTable("Team");

//			Delete mail status
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
			sleepFor(2000);
			navigationMenu.clickOnIconMenu(MailStatus1);
			navigationMenu.searchInFilter(statusCode);
			sleepFor(1000);
			navigationMenu.clickOnFilteredRow("mailStatusDataTable");
			sleepFor(2000);
			navigationMenu.clickOnIcon("Delete selected mail status", "Actions");
	
			new WindowHelper(driver).waitForPopup("Delete");
			String getMsg0 = new WindowHelper(driver).getPopupMessage();
			sleepFor(2000);
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg0);
			sleepFor(3000);		
			
			new WindowHelper(driver).waitForPopup("Delete Failed");
			String getMsg = new WindowHelper(driver).getPopupMessage();
			sleepFor(2000);
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
			sleepFor(3000);
			
//			verification
//			Verification1
			sleepFor(3000);
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
			sleepFor(2000);
			navigationMenu.clickOnIconMenu(MailStatus1);
			navigationMenu.searchInFilter(statusCode);	
			WebElement actStatusCodeXpath = navigationMenu.getfilteredRowElement("mailStatusDataTable");
			String actDeletedStatusCode = actStatusCodeXpath.getText();

//			verification2
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
			sleepFor(2000);
			navigationMenu.clickOnIconMenu(MailStatus2);
			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
			if (!status) {
				sleepFor(1000);
				refreshPage();
				sleepFor(1000);
			}				
			navigationMenu.searchInFilter(statusCode);		  							 
			String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("deletedMailStatusDataTable");
			String expMsgInEmptyTable = "No matching records found";
			
//////			Change mail status 2nd time
////			getApplicationUrl();
////			capture.selectSearchTab();
////			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
////			capture.clickOnIntrayListBtn();
////			sleepFor(2000);
////			capture.clickOnFirstItemOfIntray();
////			sleepFor(2000);
////			capture.changeMailStatusUsingSelectClass(toMailStatus1);
////			capture.clickOnChangeEmailStatusButton();
////			waitHelper.waitForElement(capture.changeMailStatusLbl, 40);
////			sleepFor(4000);
////
//////			verification
////			getApplicationUrl();
////			capture.selectSearchTab();
////			capture.searchWithCriteria("Doc Ref", docRef_SAU_9);
////			capture.clickOnIntrayListBtn();
////			sleepFor(2000);
////			capture.clickOnFirstItemOfIntray();
////			sleepFor(2000);
////			String ActualMailStatus1 = navigationMenu.getColumnValueFromTable("Mail Status");
////			String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
////			String ActualTeamName1 = navigationMenu.getColumnValueFromTable("Team");
////			AssertionHelper.verifyText(statusDescription, ActualMailStatus1);	
////			AssertionHelper.verifyText(docRef_SAU_9, ActualDocumentRef1);	
////			AssertionHelper.verifyText(TeamId, ActualTeamName1);	
			
			AssertionHelper.verifyText(statusDescription, ActualMailStatus);	
			AssertionHelper.verifyText(docRef_SAU_9, ActualDocumentRef);	
			AssertionHelper.verifyText(TeamId, ActualTeamName);	
			AssertionHelper.verifyText(expDeleteFailedMessage, getMsg);	

			AssertionHelper.verifyText(actDeletedStatusCode, statusCode);
			AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable); 

		}
	
//	e2e
//	To verify that System allows to rewrite or append the existiing doc ref 2 comments when changing mail status 2nd time
//	Verify that system allows user to update the existing Doc ref 2 added while capturing the doc.	
//	@Test(priority = 22, enabled = true)
//	@Test(priority = 22, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	@Test(priority = 22, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyThatSystemAllowsUserToUpdateTheExistingDocRef2AddedWhileCapturingTheDoc () throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String TeamId = "TId"+ "_" + superAdminUserName;
				
		String toMailStatus1 = "In Progress";
		String toMailStatus2 = "Verified";
		
		docRef_SAU_7 = "DR7" +"_" + superAdminUserName + randomNo;
		accRef_SAU_7 = "AR7" +"_" + superAdminUserName + randomNo;		
		

//		pre-requisite
//		capture document 
		getApplicationUrl();
		String filepath = null, filename = null, docType = null,  docRef = docRef_SAU_7+"_1", accRef = accRef_SAU_7+"_1", userType = null,  userName = superAdminUserName;
		userType = userType == null ? "To User" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		filename= filename == null ? "file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		capture.clickOnFileUploadIcon();
		capture.uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		capture.selectRoutingTypeDD(userType,getEnvVariable);
		capture.selectRouteToDD(userName,getEnvVariable);
		capture.enterDocRef(docRef);
		capture.enterDocRef2(docRef +"_doc2");

		if(accRef!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
				capture.enterFolder1Ref(accRef);
				capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
		}
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(1000);
		capture.clickOnIndexDocument();
		sleepFor(3000);
		
		try {
			 waitHelper.waitForElement(capture.successullyIndexMsg,25);		
			 capture.clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		
		
////		capture document 
//		getApplicationUrl();
//		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_7+"_2", accRef_SAU_7+"_2", null, superAdminUserName, getEnvVariable);
//		sleepFor(2000);	

//		TestCase
//		Change mail status 1st time
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_7+"_1");
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(toMailStatus1);
		capture.clickOnChangeEmailStatusButton();
		waitHelper.waitForElement(capture.changeMailStatusLbl, 40);
		sleepFor(4000);

//		verification
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_7+"_1");
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActualTeamName = navigationMenu.getColumnValueFromTable("Team");
		AssertionHelper.verifyText(toMailStatus1, ActualMailStatus);	
		AssertionHelper.verifyText(docRef_SAU_7+"_1", ActualDocumentRef);	
		AssertionHelper.verifyText(TeamId, ActualTeamName);	

//		TestCase
//		Change mail status 2nd time
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_7+"_1");
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		capture.changeMailStatusUsingSelectClass(toMailStatus2);
		capture.clickOnChangeEmailStatusButton();
		waitHelper.waitForElement(capture.changeMailStatusLbl, 40);
		sleepFor(4000);

//		verification
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef_SAU_7+"_1");
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualMailStatus1 = navigationMenu.getColumnValueFromTable("Mail Status");
		String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		String ActualTeamName1 = navigationMenu.getColumnValueFromTable("Team");
		AssertionHelper.verifyText(toMailStatus2, ActualMailStatus1);	
		AssertionHelper.verifyText(docRef_SAU_7+"_1", ActualDocumentRef1);	
		AssertionHelper.verifyText(TeamId, ActualTeamName1);	
	}
	

//	e2e
//	To verify the Take item functionality when few users are assigned to the team and document is captured routing to team
//	@Test(priority = 23, enabled = true)
//	@Test(priority = 23, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser", "prerequisiteForSupervisorUser"})
	@Test(priority = 23, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser", "prerequisiteForSupervisorUser"})
	public void VerifyTheTakeItemFunctionalityWhenFewUsersAreAssignedToTheTeamAndDocuentIsCapturedRoutingToTeam() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		docRef_SAU_4 = "DR4" +"_" + superAdminUserName + randomNo;
		accRef_SAU_4 = "AR4" +"_" + superAdminUserName + randomNo;		
		String TeamId = "TId"+ "_" + superAdminUserName;
		
//		try {
//			pre-requisite
//			add Take Work from a Shared In-Tray right
//					getApplicationUrl();
//					navigationMenu.clickOnTab("Administration");
//					navigationMenu.clickOnIcon("User maintenance", "User");
//					navigationMenu.searchInFilter(superAdminUserName); 										// User_N147 //User_N5341
//					navigationMenu.clickOnFilteredRow("userDataTable");
//					navigationMenu.clickOnEditIcon();
//					sleepFor(2000);
//					new AdminUserSection(driver).clickOnUserNavTab("Security");
//					sleepFor(2000);
//					HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
//					List<String> intrayProcessingList = new ArrayList<String>();
//					intrayProcessingList.add("Take Work from a Shared In-Tray");
//					userPermission.put("Intray Processing", intrayProcessingList);
//					new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
//					sleepFor(1000);
//					if (getEnvVariable.equals("Enterprise_Sp1s")) {
//						navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
//						sleepFor(2000);
//						WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
//						boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
//						String getMsg = new WindowHelper(driver).getPopupMessage();
//						if(getMsg == "No changes to save.") {
//							new WindowHelper(driver).clickOkOnPopup();
//							navigationMenu.clickOnIcon("Cancel changes", "Actions");
//							navigationMenu.waitForAddIcon();
//						}
//					} else {
//						navigationMenu.clickOnSaveIcon();
//						sleepFor(2000);
//						WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
//						boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
//						String getMsg = new WindowHelper(driver).getPopupMessage();
//						if(getMsg == "No changes to save.") {
//							new WindowHelper(driver).clickOkOnPopup();
//							navigationMenu.clickOnIcon("Cancel changes", "Actions");
//							navigationMenu.waitForAddIcon();
//							}
//					sleepFor(6000);
//		} catch (Exception e) {
//			System.out.println("---->> pre-requisite for adding or removing rights failed---");		}
		
//		add atleast 2 users to the team
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.searchInFilter(TeamId);
		navigationMenu.clickOnFilteredRow("teamsTable");
		navigationMenu.clickOnIcon("Edit selected team ", "Actions");
		adminuser.clickOnTeamUsersTab();

		teams.verifyUserAddedToTeamIfNotAddUserToTeam(supervisorUserName, getEnvVariable);	//.get(i)
		teams.verifyUserAddedToTeamIfNotAddUserToTeam(superAdminUserName, getEnvVariable);	//.get(i)

		try {
			navigationMenu.clickOnIconMenu("Save changes made to team", "Actions");

			//verify document type with groupName				
//			navigationMenu.waitForAddIcon();
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
//			AssertionHelper.verifyText(actMessage, "No changes to save.");
		}
		
//		capture document 
		getApplicationUrl();
	    capture.navigateAndCaptureDocWithParameters(null,null, null, docRef_SAU_4, "Mail", null, null, null, null, getEnvVariable);	
		sleepFor(2000);	

		try {
//			logout from the user or change user
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);

//	 		Login with the another user
			new LoginPage(driver).loginWithCredentials(supervisorUserName, ObjectReader.reader.getPassword());
			
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref",docRef_SAU_4);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Take shared mail item", "Mail");

			new WindowHelper(driver).waitForPopup("Take Item");
			String getMsg1 = new WindowHelper(driver).getPopupMessage();
			sleepFor(2000);
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg1);
			sleepFor(3000);
			
			String ActualUserID = navigationMenu.getColumnValueFromTable("User ID");
			AssertionHelper.verifyText(supervisorUserName, ActualUserID);	
			sleepFor(2000);

		} catch (Exception e) {
			System.out.println("---->> verification of userID failed--");		}

//		post-requisite
//		logout from the user or change user
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
// 		Login with the another user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		}
	
//	=========================================================================================================
//	e2e
//	@Test(priority = 24, enabled = true)
	@Test(priority = 24, enabled = true)
	
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
//				adminUser.reAssignRightsToSupervisorUser(supervisorUserName, getEnvVariable);
//				sleepFor(2000);
				
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
			
		

	}
	

//	e2e
//	To verify that if "Take Work from a Shared In-Tray" permission is not provided then Take item and Next buttons are not available
//	To verify that Take item and Next buttons are visible only if "Take Work from a Shared In-Tray" permission is provided
	//Note : even on disabling this option the items are still visible.
	//Need to discuss with Amit for this
//	@Test(priority = 25, enabled = true)
//	@Test(priority = 25, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	@Test(priority = 25, enabled = true, dependsOnMethods = {"prerequisiteForSuperAdminUser"})
	public void VerifyThatIfTakeWorkFromASharedIntrayPermisionIsNotProvidedThenTakeItemAndNextButtonsAreNotAvailable() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

//		Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		
		
//		1. capture document
		docRef_SAU_1 = "DR1" +"_" + superAdminUserName + randomNo;
		accRef_SAU_1 = "AR1" +"_" + superAdminUserName + randomNo;
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef_SAU_1+"_1", accRef_SAU_1+"_1", "To Team", TeamName, getEnvVariable);
		sleepFor(2000);	
		
//		Testcase
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", "%%");						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);	
		
		navigationMenu.expandNavigationMenu("Mail");
		sleepFor(2000);	
		
		String [] elements = {"Take next item from team tray","Take shared mail item"};
		for(int i=0; i<=elements.length-1; i++)
			{		
			if(envType.equals("Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , elements[i]);
				sleepFor(2000);
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				sleepFor(2000);
				AssertionHelper.verifyTrue(status, "=================== Element "+elements[i]+" is present in intray");
				sleepFor(2000);		
			}
			else if(envType.equals("NECDM_Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , elements[i]);
				sleepFor(2000);
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				sleepFor(2000);
				AssertionHelper.verifyTrue(status, "=================== Element "+elements[i]+" is present in intray");
				sleepFor(2000);		
			}
		}
		navigationMenu.collapseNavigationMenu("Mail");

	
		//pre-requisite : Logged in user should not have Take Work from a Shared In-Tray right
		//remove Take Work from a Shared In-Tray right
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
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		userPermission.put("Intray Processing", intrayProcessingList);
		new AdminUserSection(driver).RemoveSecurityRightsFromUser(userPermission);
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			sleepFor(6000);
			WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
			String getMsg = new WindowHelper(driver).getPopupMessage();
			if(getMsg == "No changes to save.") {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.clickOnIcon("Cancel changes", "Actions");
				navigationMenu.waitForAddIcon();
			}
		} else {
			navigationMenu.clickOnSaveIcon();
			sleepFor(6000);
			WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
			String getMsg = new WindowHelper(driver).getPopupMessage();
			if(getMsg == "No changes to save.") {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.clickOnIcon("Cancel changes", "Actions");
				navigationMenu.waitForAddIcon();
				}
		}
		sleepFor(1000);
	
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		sleepFor(2000);
		
//		Testcase
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", "%%");						
		capture.clickOnIntrayListBtn();
		sleepFor(3000);	
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.expandNavigationMenu("Mail");
		sleepFor(2000);	
		
//		String [] elements = {"Take next item from team tray","Take shared mail item"};
		for(int i=0; i<=elements.length-1; i++)
			{		
			if(envType.equals("Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpIconXpath , elements[i]);
				sleepFor(2000);
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				sleepFor(2000);
				System.out.println("-------------------------status" + status );
				if(status == true) {
					AssertionHelper.verifyTrue(status, "=================== Element "+elements[i]+" is present in intray");
					
					getApplicationUrl();
					capture.searchWithCriteria("Doc Ref", docRef_SAU_1+"_1");						
					capture.clickOnIntrayListBtn();
					sleepFor(3000);	
					capture.clickOnFirstItemOfIntray();
					navigationMenu.collapseNavigationMenu("Document");
					sleepFor(2000);	
					navigationMenu.clickOnIcon(elements[i], "Mail");
					waitHelper.waitForElementVisible(windowHelper.dialogOkBtn, 35, 1);

					String actErrorMsg = new WindowHelper(driver).getPopupMessage();
					sleepFor(2000);
					System.out.println("get message ====================================" + actErrorMsg);
					sleepFor(3000);
					String expErrorMsg = "User does not have permission to take intray work items.";
					AssertionHelper.verifyText(expErrorMsg, actErrorMsg);
				}
				else {
					AssertionHelper.verifyFalse(status, "=================== Element "+elements[i]+" is not present in intray");
				}
				sleepFor(2000);		
	
			}
			else if(envType.equals("NECDM_Lives")) {
				String fnlIconXpath = String.format(navigationMenu.tmpMenuIconXpathAfter540 , elements[i]);
				sleepFor(2000);
				boolean status = new VerificationHelper(driver).isElementPresent(By.xpath(fnlIconXpath));
				sleepFor(2000);
				System.out.println("-------------------------status" + status );
				if(status == true) {
					AssertionHelper.verifyTrue(status, "=================== Element "+elements[i]+" is present in intray");
					
					getApplicationUrl();
					capture.searchWithCriteria("Doc Ref", docRef_SAU_1+"_1");						
					capture.clickOnIntrayListBtn();
					sleepFor(3000);	
					capture.clickOnFirstItemOfIntray();
					navigationMenu.collapseNavigationMenu("Document");
					sleepFor(2000);	
					navigationMenu.clickOnIcon(elements[i], "Mail");
					waitHelper.waitForElementVisible(windowHelper.dialogOkBtn, 35, 1);

					String actErrorMsg = new WindowHelper(driver).getPopupMessage();
					sleepFor(2000);
					System.out.println("get message ====================================" + actErrorMsg);
					sleepFor(3000);
					String expErrorMsg = "User does not have permission to take intray work items.";
					AssertionHelper.verifyText(expErrorMsg, actErrorMsg);
				}
				else {
					AssertionHelper.verifyFalse(status, "=================== Element "+elements[i]+" is not present in intray");
				}
				sleepFor(2000);		
			}
		}
		navigationMenu.collapseNavigationMenu("Mail");
		
//		post-requisite :
		try {
//			Logged in user should have Take Work from a Shared In-Tray right
//			add Take Work from a Shared In-Tray right
					getApplicationUrl();
					navigationMenu.clickOnTab("Administration");
					navigationMenu.clickOnIcon("User maintenance", "User");
					navigationMenu.searchInFilter(superAdminUserName); 										// User_N147 //User_N5341
					navigationMenu.clickOnFilteredRow("userDataTable");
					navigationMenu.clickOnEditIcon();
					sleepFor(2000);
					new AdminUserSection(driver).clickOnUserNavTab("Security");
					sleepFor(2000);

//					HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
//					List<String> intrayProcessingList = new ArrayList<String>();

					intrayProcessingList.add("Take Work from a Shared In-Tray");
					userPermission.put("Intray Processing", intrayProcessingList);
					new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
					sleepFor(1000);
					if (getEnvVariable.equals("Enterprise_Sp1s")) {
						navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
						sleepFor(2000);
						WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
						boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
						String getMsg = new WindowHelper(driver).getPopupMessage();
						if(getMsg == "No changes to save.") {
							new WindowHelper(driver).clickOkOnPopup();
							navigationMenu.clickOnIcon("Cancel changes", "Actions");
							navigationMenu.waitForAddIcon();
						}
					} else {
						navigationMenu.clickOnSaveIcon();
						sleepFor(2000);
						WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
						boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
						String getMsg = new WindowHelper(driver).getPopupMessage();
						if(getMsg == "No changes to save.") {
							new WindowHelper(driver).clickOkOnPopup();
							navigationMenu.clickOnIcon("Cancel changes", "Actions");
							navigationMenu.waitForAddIcon();
							}
					}
		} catch (Exception e) {
			System.out.println("---->> post requisite for adding the 'Take Work from a Shared In-Tray right' failed--");		}
				   
				getApplicationUrl();
				new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
				sleepFor(2000);
	}	
}











