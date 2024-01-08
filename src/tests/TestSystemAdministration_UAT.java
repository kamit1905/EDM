package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.FolderFlagReference;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.Barcode;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.DocumentTypes;
import main.EDRM.hybridFramework.pageObject.Admin.EmailDomain;
import main.EDRM.hybridFramework.pageObject.Admin.Group;
import main.EDRM.hybridFramework.pageObject.Admin.LicenseAllocation;
import main.EDRM.hybridFramework.pageObject.Admin.MailStatus;
import main.EDRM.hybridFramework.pageObject.Admin.Teams;
import main.EDRM.hybridFramework.pageObject.Admin.Role;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;

/*
 * suite created by Sagar
 */

public class TestSystemAdministration_UAT extends TestBase  
{

	public DocumentToolsPage docTools;
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	public AdminUserSection adminUser;
	public AdminUserSection adminUserSection;
	public AdminDocumentSection adminDoc;
	public AdminWorkflowSection adminworkflow;
	public LoginPage login;
	public FolderFlagReference folderPage;
	public Group group;
	public Barcode barcode;
	public MailStatus mailStatus;
	public EmailDomain emailDomain;
	public Teams teams;
	public Role role;
	public LicenseAllocation licenseAllocation;
	AlertHelper alertHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	AdminDocumentSection adminDocument;
	IntrayToolsPage intrayTools;


	List<String> getAllusers = new ArrayList<String>();
	List<String> getAllTeams = new ArrayList<String>();
	List<String> getAllDocTypes = new ArrayList<String>();

	String Role,
	RoleName,
	TeamId,
	TeamName,
	EditedTeamName,
	statusCode,
	statusDescription,
	statusDescriptionEdited,
	docType,
	docDescription,
	groupName,
	typeId,
	editedGroupName,	
	folderName;
	
	private String userNew = "";

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
		folderPage = new FolderFlagReference(driver);
		adminUser = new AdminUserSection(driver);
		adminDoc = new AdminDocumentSection(driver);
		adminworkflow = new AdminWorkflowSection(driver);
		group = new Group(driver);
		barcode = new Barcode(driver);
		mailStatus = new MailStatus(driver);
		emailDomain = new EmailDomain(driver);
		teams = new Teams(driver);
		role = new Role(driver);
		licenseAllocation = new LicenseAllocation(driver);
	}

	@DataProvider(name="addDocumentType")
	public Object[][] addDocumentType() throws Exception
	{
		Object[][] formData = xls.readExcelToMap( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","DocumentTypeData");

		return formData;
	}
	
	@Test(priority = 1, enabled = false)
	public void TestAddUser() throws InterruptedException {
//		1. Add user and verify the user is added 
		userNew = "User_N" + generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();
		sleepFor(1000);

		new AdminUserSection(driver).enterUserId(userNew);
		new AdminUserSection(driver).enterUsername(userNew);

		sleepFor(1000);
		new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).selectSecurityRestrictionType("Restrict", getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		adminList.add("Update Environment");

		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		intrayList.add("View Intrays Across File Systems");

		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");

		List<String> reportsList = new ArrayList<String>();
		reportsList.add("View Reports");

		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");

		userPermission.put("Admin", adminList);
		userPermission.put("Intray", intrayList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Reports", reportsList);
		userPermission.put("Cold", coldList);

		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}

//		Verify created new user is added
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userNew);
		Thread.sleep(1000);
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		String filteredUser = filteredElement.getText();
		AssertionHelper.verifyText(filteredUser, userNew);

//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

		// For new user change password
		new LoginPage(driver).loginWithCredentialsForChangePassword(userNew, "P@ssword123");

		// Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(userNew, ObjectReader.reader.getPassword());

//		Verify the Added roles are displayed
		sleepFor(1000);
		navigationMenu.clickOnTab("Capture");

		sleepFor(2000);
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
	}

	@Test(priority = 2, enabled = false)
	public void TestEditUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();

		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userNew); 										// User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		intrayList.add("View Intrays Across File Systems");

		userPermission.put("Intray", intrayList);

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
	}

	@Test(priority = 3, enabled = false)
	public void TestDeleteUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
//		String userNew = "User_N5926";
		String password = ObjectReader.reader.getPassword();
		getApplicationUrl();

		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userNew); // userNew
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnDeleteIcon(); 
		sleepFor(4000);

//		Verify not able to login through deleted user
//		logout from the user or change user
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

		// For new user change password
//		new LoginPage(driver).loginWithCredentials(userNew, password);
		waitHelper.waitForElement(new LoginPage(driver).usernameTxt,ObjectReader.reader.getExplicitWait());
		sendKeys(new LoginPage(driver).usernameTxt,userNew,"Entering username on login page as "+userNew);
		sendKeys(new LoginPage(driver).passwordTxt,password,"Entering password on login page as "+password);
		click(new LoginPage(driver).loginBtn,"Clicking on login button");
		
		sleepFor(2000);
		
//		=============================================================================================================
//		Note : --> ask amit to add this xpath (loginErrorMessage) in doctools

        boolean LoginErrorMessage = new VerificationHelper(driver).isElementDisplayedByEle(login.loginErrorMessage);
        AssertionHelper.verifyTrue(LoginErrorMessage, "As user is deleted so not able to login through that user");
//		=============================================================================================================
 	}

	@Test(priority = 4, enabled = false)
	public void TestRestoreUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();

		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

//		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Deleted user maintenance", "User");
		navigationMenu.searchInFilter(userNew);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("deletedUsersDataTable");
		navigationMenu.clickOnIcon("Restore the deleted user", "Actions");

		new WindowHelper(driver).waitForPopup("Restore");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
		sleepFor(1000);
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(4000);

		getApplicationUrl();
		sleepFor(4000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userNew);
		sleepFor(1000);
		
		String ActualUser = navigationMenu.getColumnValueFromTable("User");
		AssertionHelper.verifyText(userNew, ActualUser);
	}
	
	@Test(priority = 5, enabled = false)										
	public void TestAddLicenseAllocation() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
			
		sleepFor(2000);
//		licenseAllocation.licenseAllocation(userNew, "NEC Document Management Client");
		licenseAllocation.licenseAllocation(userNew, "NEC Document Management Client");
	}
	
	@Test(priority = 6, enabled = false)										
	public void TestEditLicenseAllocation() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
			
		sleepFor(2000);
//		licenseAllocation.licenseAllocation(userNew, "Office Connect");
		licenseAllocation.licenseAllocation(userNew, "Office Connect");
	}
	
	@Test(priority = 7, enabled = false)										
	public void TestUpdateEmailDomainInformation() throws InterruptedException {
		String domainName = "necsws"+generateRandomNumber()+".com";
		String emailAddress = "ABC"+ generateRandomNumber();

		emailDomain.addAndVerifyEmailDomainInformation(domainName, emailAddress);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(userNew); // User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		sendKeys(new AdminUserSection(driver).addEmailAddress, emailAddress + "@" + domainName,
				"Entering Domain Name " + domainName);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
//		new WindowHelper(driver).waitForPopup("Warning");
//		String getMsg = new WindowHelper(driver).getPopupMessage();
//		new WindowHelper(driver).clickOkOnPopup();
//		sleepFor(2000);
//		System.out.println("get message ====================================" + getMsg);

//		Restore Stage
		//emailDomain.deleteEmail(userNew);
		emailDomain.removeEmailDomainInformation(domainName);		
	}
		
	@Test(priority = 8, enabled = true)										
	public void TestAddRole() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();

		Role = "Role"+ generateRandomNumber();
		RoleName = "RN_"+ Role;

		role.addNewRole(Role, RoleName);
		
		//verify role is added
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Role maintenance", "User");
		navigationMenu.searchInFilter(Role);
		
		WebElement createdRoleId = driver.findElement(By.xpath("//table[@id='rolesTable']//*[text()='"+Role+"']"));
		boolean status=new VerificationHelper(driver).isElementDisplayedByEle(createdRoleId);			
		AssertionHelper.verifyTrue(status, "Checking created role is present or not");
	}
	
	@Test(priority = 9, enabled = true)
	public void TestAddTeam() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();

		TeamId = "Id"+ generateRandomNumber();
		TeamName = "TN_"+ TeamId;

		teams.addTeam(TeamId, TeamName, RoleName, getEnvVariable);
		
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.searchInFilter(TeamId);
		WebElement createdTeamId = driver.findElement(By.xpath("//table[@id='teamsTable']//*[text()='"+TeamId+"']"));
		boolean status1=new VerificationHelper(driver).isElementDisplayedByEle(createdTeamId);
		AssertionHelper.verifyTrue(status1, "Checking created team is present or not");
	}

	@Test(priority = 10, enabled = true)										
	public void TestEditTeam() throws InterruptedException {
		EditedTeamName = "Edited_"+ TeamName;
		getApplicationUrl();
		teams.editTeam(TeamId, EditedTeamName);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.searchInFilter(TeamId);
		WebElement editedTeamName = driver.findElement(By.xpath("//table[@id='teamsTable']//*[text()='"+EditedTeamName+"']"));
		boolean status1=new VerificationHelper(driver).isElementDisplayedByEle(editedTeamName);			
		AssertionHelper.verifyTrue(status1, "Verifying team");

		 	
		//Team and role delete
//		role.deleteRole(Role);
		
//		teams.deleteTeam(TeamId);
		
		
	}	
	
	@Test(priority = 11, enabled = true)										
	public void TestAddMailStatus() throws InterruptedException {
		statusCode =  "SC"+ generateRandomNumber();
		statusDescription ="Desc_"+statusCode;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		sleepFor(1000);
		mailStatus.selectAndAddMailStatus("Active", statusCode, statusDescription, EditedTeamName, getEnvVariable);
		
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
		WebElement actStatusCode = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusCode+"']"));
		AssertionHelper.verifyText(actStatusCode.getText(), statusCode);


	}
	
	@Test(priority = 12, enabled = true)										
	public void TestEditMailStatus() throws InterruptedException {
		statusDescriptionEdited = statusDescription+"_Edited";
				
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		sleepFor(1000);
		
		mailStatus.editMailStatus("Active", statusCode, statusDescriptionEdited, null, getEnvVariable);
		
		//verification of Edited mail status
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Active");
		boolean status1 = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if (!status1) {
			sleepFor(1000);
			refreshPage();
			sleepFor(1000);
		}
		navigationMenu.searchInFilter(statusDescriptionEdited);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		WebElement actStatusDescriptionEdited = driver.findElement(By.xpath("//table[@id='mailStatusDataTable']//*[text()='"+statusDescriptionEdited+"']"));
		AssertionHelper.verifyText(actStatusDescriptionEdited.getText(), statusDescriptionEdited);

		//Delete mail status
		mailStatus.deleteMailStatus("Active", statusCode);
		sleepFor(3000);
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		navigationMenu.searchInFilter(statusDescriptionEdited);		  							 
	//	navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		//String verifyEmptyTable =  mailStatus.mailStatusDataTableEmpty.getText();
		String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("mailStatusDataTable");
		String expMsgInEmptyTable = "No matching records found";
		AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable); 
	}
	
	@Test(priority=13,enabled = true)
	public void TestAddAndEditDocumentTypeGroup() throws InterruptedException {
		typeId = "DT_"+ generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		groupName = "GN"+ generateRandomNumber();
		editedGroupName = "Edited_" + groupName;

		//add Group
		group.AddDocumentTypeGroup(groupName);
		navigationMenu.searchInFilter(groupName);
		sleepFor(2000);
		String filteredText = navigationMenu.getfilteredRowElement("documentTypeGroupDataTable").getText();
		AssertionHelper.verifyText(filteredText, groupName);
		sleepFor(3000);
		
		//Edit group
		group.EditDocumentTypeGroup(groupName, editedGroupName);
		navigationMenu.searchInFilter(editedGroupName);
		sleepFor(2000);
		String filteredText1 = navigationMenu.getfilteredRowElement("documentTypeGroupDataTable").getText();
		AssertionHelper.verifyText(filteredText1, editedGroupName);

		
		//add document type with groupName
		adminDoc.addDocumentType(typeId, editedGroupName, getEnvVariable);
	
//		deleteDocumentType
		adminDoc.deleteDocumentType(typeId, getEnvVariable);
		getApplicationUrl();
		new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
		navigationMenu.searchInFilter(typeId);
    	String verifyEmptyTable =  navigationMenu.getNoRecordsFoundMessage("documentTypesTable");
    	String expMsgInEmptyTable = "No matching records found";
    	AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable); 

		
//		deleteGroup
		group.DeleteGroup(editedGroupName, getEnvVariable);

			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Document Type Group", "Document");
			navigationMenu.searchInFilter(groupName);
	    	//navigationMenu.clickOnFilteredRow("documentTypeGroupDataTable");
	    	String verifyEmptyTable1 = navigationMenu.getNoRecordsFoundMessage("documentTypeGroupDataTable");
	    	String expMsgInEmptyTable1 = "No matching records found";
	    	AssertionHelper.verifyText(verifyEmptyTable1, expMsgInEmptyTable1); 

	}

	@Test(priority = 14, enabled = true)										
	public void TestAddBarcode() throws InterruptedException {
		typeId = "DT_"+ generateRandomNumber();
		folderName = "Account ref";
		groupName = "GN"+ generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

//		//add Group
		group.AddDocumentTypeGroup(groupName);
//		
//		//add document type with groupName
		adminDoc.addDocumentType(typeId, groupName, getEnvVariable);

		//add Barcode
		barcode.AddBarcode(typeId, groupName, folderName, getEnvVariable);
		navigationMenu.searchInFilter(typeId);
		sleepFor(2000);
		String filteredText = navigationMenu.getColumnValueFromTable("Document Type");			
		AssertionHelper.verifyText(filteredText, typeId);
	}
	
	@Test(priority = 15, enabled = true)										
	public void TestEditBarcode() throws InterruptedException {
		String folderName = "Property Ref";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		barcode.editBarcode(typeId, folderName, "3", getEnvVariable);
		sleepFor(2000);
		barcode.deleteBarcode(typeId);
    	//verify document group is deleted
		navigationMenu.searchInFilter(typeId);
		navigationMenu.clickOnFilteredRow("barcodeTable");
		sleepFor(2000);
    	//String verifyEmptyTable = barcodeTableEmpty.getText();
		String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("barcodeTable");
    	System.out.println("============"+verifyEmptyTable);
    	String expMsgInEmptyTable = "No items available";
    	AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);		
	}
																					
	@Test(priority=16,enabled = true)
	public void TestUserCanBrowseFileSystem() throws InterruptedException 
	{
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
		new HomePage(driver).clickOnBrowserFileSystemOption(ObjectReader.reader.getFileSystemName());

//		verify given file system is applied
		sleepFor(2000);
		home.clickOnGearIcon();
		String actualFileSystem = home.lblFileSystemName.getText();  
		String expectedFileSystem = ObjectReader.reader.getFileSystemName();
		AssertionHelper.verifyTextContains(actualFileSystem, expectedFileSystem);
	}

	@Test(priority=17,enabled = true)
	public void TestUserCanChangeFileSystemBannerBarColours () throws InterruptedException 
	{
		String backgroundColor = "Slate";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
	    new HomePage(driver).clickOnGearIcon();
		new HomePage(driver).clickOnChangeColourScheme();
		new HomePage(driver).ClickOnColourCodeDropDown(backgroundColor);
		sleepFor(1000);
		new WindowHelper(driver).clickOnModalFooterButton("Save");
		sleepFor(1000);
		
//		verify the file system banner bar color is changed
		getApplicationUrl();
		WebElement ele = driver.findElement(By.cssSelector("#MainBar"));
		String Actual_bgColorRGB = ele.getCssValue("background-color");
		System.out.println("========Actual_bgColorRGB==========" +Actual_bgColorRGB);
		String Expected_bgColorRGB = HomePage.getBGColor("RGB_Verify", backgroundColor);
		AssertionHelper.verifyTextContains(Actual_bgColorRGB, Expected_bgColorRGB);
	}

}
