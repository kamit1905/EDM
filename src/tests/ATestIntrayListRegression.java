package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
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
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;

public class ATestIntrayListRegression extends TestBase {
	
	public IntrayToolsPage intrayTools ;
	private Logger log = LoggerHelper.getLogger(TestIntrayTools.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	WaitHelper waitHelper;
	WindowHelper windowHelper;
	AdminDocumentSection adminDocument;
	public ToolkitSystemSection toolkitSystem;
	public VerificationHelper verificationHelper;
	public AdminUserSection adminUser;
	public LoginPage login;
	public ActionHelper action;
	public DocumentToolsPage docTools ;
	
	
	private String superAdminUserName;
	private String fileSystemAdminUser;
	private String supervisorUser;
	private String standardUser;
	private String basicUser;
	
	//For Assioacted history
	private String docRef1;
	private String docRef2;
	private String accRef1;
	
	//For Distribution
	private String SAUDist;
	private String FSADist;
	private String SUPDist;
	private String STDDistr;
	private String BASDistr;
	
	private String SAUDistMul;
	private String FSADistMul;
	private String SUPDistMul;
	private String STDDistMul;
	private String BASDistMul;
	
	@BeforeClass
	public void setupClass()  {
		intrayTools = new IntrayToolsPage(driver);
		adminUser= new AdminUserSection(driver);
		capture = new CapturePage(driver);
		home= new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		waitHelper= new WaitHelper(driver);
		xls = new ExcelReader();
		toolkitSystem= new ToolkitSystemSection(driver);
		verificationHelper= new VerificationHelper(driver);
		login = new LoginPage(driver);
		docTools = new DocumentToolsPage(driver);
	}
	
	@Test(priority = 1,enabled = false)
	public void CreateNewSuperAdminUser() throws InterruptedException {
		superAdminUserName = "SAU"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new AdminUserSection(driver).CreateSuperAdminUser(getEnvVariable, getEnvVariable);
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(superAdminUserName, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.setUserPassword());
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 2,enabled = false)
	public void CreateNewFileSystemAdminUser() throws InterruptedException {
		fileSystemAdminUser = "FSA"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new AdminUserSection(driver).CreateFileSystemAdminUser(getEnvVariable, getEnvVariable);
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(fileSystemAdminUser, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUser, ObjectReader.reader.setUserPassword());
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 3,enabled = false)
	public void CreateNewSupervisorUser() throws InterruptedException {
		supervisorUser = "SUV"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new AdminUserSection(driver).CreateSupervisorUser(getEnvVariable, getEnvVariable);
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(supervisorUser, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(supervisorUser, ObjectReader.reader.setUserPassword());
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 4,enabled = false)
	public void CreateNewStandardUser() throws InterruptedException {
		standardUser = "STD"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new AdminUserSection(driver).AddStandardUser(standardUser, getEnvVariable);
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(standardUser, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(standardUser, ObjectReader.reader.setUserPassword());
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 5,enabled = false)
	public void CreateNewBasicUser() throws InterruptedException {
		basicUser = "BSA"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new AdminUserSection(driver).CreateBasicUser(basicUser, getEnvVariable);
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(basicUser, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(basicUser, ObjectReader.reader.setUserPassword());
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 100,enabled = true)
	public void loginwithsuperuser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("SUA7948", "33");
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 101,enabled = true)
	public void CaptureDocumentAndVerifyAssociatedDocumentsUsingSAU() throws InterruptedException {
		accRef1 = "SAP"+generateRandomNumber(); docRef1 ="ASSOC1"+generateRandomNumber(); docRef2= "ASSOC2"+generateRandomNumber();
		String docType = "ScanDoc - Generic Scanned Document";
		boolean createRef = true;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef1, accRef1, "", "", createRef,getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef2, accRef1, "", "", createRef,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef1);
		capture.clickOnIntrayListBtn();
		navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Show document associated with this account", "Mail");
		
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			
			navigationMenu.searchInFilter(docRef1);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef1 = navigationMenu.getColumnValueFromTable("Account Ref");
			
			sleepFor(2000);
			navigationMenu.searchInFilter(docRef2);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef2 = navigationMenu.getColumnValueFromTable("Account Ref");

			AssertionHelper.verifyText(getAccountRef1, accRef1);
			AssertionHelper.verifyText(getAccountRef2, accRef1);
			//sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}

	@Test(priority = 102,enabled = true)
	public void VerifyDistributeIconUsingSuperAdminUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
		//navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		boolean status =  navigationMenu.waitForIconStatus("Distribute intray item", "Mail");
		AssertionHelper.verifyTrue(status, "Checking button is present or not");
	}
	
	@Test(priority = 103,enabled = true)
	public void DistributeDocumentUsingSuperAdmin() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		SAUDist = "SAUDist"+generateRandomNumber();
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, SAUDist, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", SAUDist);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSAUDistributionUser());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getSAUDistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getSAUDistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SAUDist);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}
	
	@Test(priority = 104,enabled = true)
	public void DistributeDocumentUsingMultiReferralBySuperAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		SAUDistMul = "SAUDistMul"+generateRandomNumber();
		
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, SAUDistMul, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", SAUDistMul);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSuperUserName());
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSAUDistributionUser());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using office connect user on more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getSAUDistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getSAUDistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SAUDistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
		
		//Verify it using imasys user
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getSuperUserName(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getSuperUserName(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SAUDistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}
	
	@Test(priority = 105,enabled = true)
	public void DistributeDocumentUsingInfoOnlyBySuperAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String SAUDistInfo = "SAUDistInfo"+generateRandomNumber();
		
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, SAUDistInfo, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", SAUDistInfo);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getSAUDistributionUser());

		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using office connect user on more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getSAUDistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getSAUDistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SAUDistInfo);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
		String getMailStaus = navigationMenu.getColumnValueFromTable("Mail Status");
		AssertionHelper.verifyText(getMailStaus, "Info Only");
	}
	
	
	 // File System admin user test cases
	 
	@Test(priority = 200,enabled = true)
	public void LoginUsingFileSystemAdminUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("FSA2023", "33");
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 201,enabled = false)
	public void CaptureDocumentAndVerifyAssociatedDocumentsUsingFSA() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUser, ObjectReader.reader.getPassword());
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef1);
		capture.clickOnIntrayListBtn();
		navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Show document associated with this account", "Mail");
		
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			
			navigationMenu.searchInFilter(docRef1);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef1 = navigationMenu.getColumnValueFromTable("Account Ref");
			
			sleepFor(2000);
			navigationMenu.searchInFilter(docRef2);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef2 = navigationMenu.getColumnValueFromTable("Account Ref");

			AssertionHelper.verifyText(getAccountRef1, accRef1);
			AssertionHelper.verifyText(getAccountRef2, accRef1);
			//sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 202,enabled = false)
	public void VerifyDistributeIconUsingFileSystemAdminUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
		//navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		boolean status =  navigationMenu.waitForIconStatus("Distribute intray item", "Mail");
		AssertionHelper.verifyTrue(status, "Checking button is present or not");
	}
	
	@Test(priority = 203,enabled = false)
	public void DistributeDocumentUsingFileSystemAdmin() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		FSADist = "FSADist"+generateRandomNumber();
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, FSADist, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", FSADist);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getFSADistributionUser());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getFSADistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getFSADistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(FSADist);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}

	@Test(priority = 204,enabled = false)
	public void DistributeDocumentUsingMultiReferralByFileSystemAdmin() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		FSADistMul = "FSADistMul"+generateRandomNumber();
		
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, FSADistMul, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", FSADistMul);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getFSADistributionUser());
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSAUDistributionUser());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using office connect user on more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getSAUDistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getSAUDistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(FSADistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
		
		//Verify it using imasys user
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getFSADistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getFSADistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(FSADistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}

	
	
	  //Supervisor user test cases
	 
	@Test(priority = 300, enabled = true)
	public void LoginUsingSupervisorUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("SUV3202", "33");
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
		
	@Test(priority = 301, enabled = false)
	public void CaptureDocumentAndVerifyAssociatedDocumentsUsingSupervisorUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(supervisorUser, ObjectReader.reader.getPassword());

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnIntrayListBtn();
		navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Show document associated with this account", "Mail");

		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);

			navigationMenu.searchInFilter(docRef1);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

			sleepFor(2000);
			navigationMenu.searchInFilter(docRef2);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef2 = navigationMenu.getColumnValueFromTable("Account Ref");

			AssertionHelper.verifyText(getAccountRef1, accRef1);
			AssertionHelper.verifyText(getAccountRef2, accRef1);
			// sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 302, enabled = false)
	public void VerifyDistributeIconUsingSupervisorUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
		//navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		boolean status =  navigationMenu.waitForIconStatus("Distribute intray item", "Mail");
		AssertionHelper.verifyTrue(status, "Checking button is present or not");
	}
	
	@Test(priority = 303,enabled = false)
	public void DistributeDocumentUsingSupervisorUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		SUPDist = "SUPDist"+generateRandomNumber();
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, SUPDist, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", SUPDist);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getLoggedInUsersTeamId());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getLoggedInUsersTeam(),"input-Teams","input-teams");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getLoggedInUsersTeam(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SUPDist);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}

	@Test(priority = 304,enabled = false)
	public void DistributeDocumentUsingMultiReferralBySupervisor() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		SUPDistMul = "SUPDistMul"+generateRandomNumber();
		
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, SUPDistMul, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", SUPDistMul);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getFSADistributionUser());
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSAUDistributionUser());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using office connect user on more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getSAUDistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getSAUDistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SUPDistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
		
		//Verify it using imasys user
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getFSADistributionUser(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getFSADistributionUser(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(SUPDistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}

	
	
	 // Standard User Test cases
	 
	@Test(priority = 400, enabled = true)
	public void LoingUsingStandardUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("STD2030", "33");
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority = 401, enabled = false)
	public void CaptureDocumentAndVerifyAssociatedDocumentsUsingStandardUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(standardUser,ObjectReader.reader.getPassword());

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnIntrayListBtn();
		navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Show document associated with this account", "Mail");

		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);

			navigationMenu.searchInFilter(docRef1);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

			sleepFor(2000);
			navigationMenu.searchInFilter(docRef2);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef2 = navigationMenu.getColumnValueFromTable("Account Ref");

			AssertionHelper.verifyText(getAccountRef1, accRef1);
			AssertionHelper.verifyText(getAccountRef2, accRef1);
			// sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 402, enabled = false)
	public void VerifyDistributeIconUsingStandardUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
		//navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		boolean status =  navigationMenu.waitForIconStatus("Distribute intray item", "Mail");
		AssertionHelper.verifyTrue(status, "Checking button is present or not");
	}
	
	@Test(priority = 403,enabled = false)
	public void DistributeDocumentUsingStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		STDDistr = "STDDistr"+generateRandomNumber();
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, STDDistr, null,null,"STD2030",getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", STDDistr);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getOtherUserTeamId());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getOtherUserTeamName(),"input-Teams","input-teams");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getOtherUserTeamName(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(STDDistr);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}

	@Test(priority = 404,enabled = false)
	public void DistributeDocumentUsingMultiReferralByStandardUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		STDDistMul = "STDDistMul"+generateRandomNumber();
		
		getApplicationUrl();			
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, STDDistMul, null,null,"STD2030",getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", STDDistMul);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getLoggedInUsersTeamId());
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getOtherUserTeamId());
		//Write code to Enter notes in popup
		intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
		intrayTools.clickOnModalApplyChangesButtonWithPopup();;
		sleepFor(2000);
		
		//Verify using office connect user on more search
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getLoggedInUsersTeam(),"input-Teams","input-teams");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getOtherUserTeamId(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(STDDistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
		
		//Verify it using imasys user
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			//docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getLoggedInUsersTeam(),"input-Users","input-users");		//Added by amit to select user
		}else {
			//docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getOtherUserTeamId(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		
		navigationMenu.searchInFilter(STDDistMul);
		sleepFor(1000);
		intrayTools.clickOnFirstItemInList();
	}

	

	
	  //Basic user test cases
	 
	@Test(priority = 500, enabled = true)
	public void LoginWithBasicUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("BAS7774", "33");
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	
	@Test(priority = 501, enabled = false)
	public void CaptureDocumentAndVerifyAssociatedDocumentsUsingBasicUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(basicUser, ObjectReader.reader.getPassword());

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnIntrayListBtn();
		navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Show document associated with this account", "Mail");

		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);

			navigationMenu.searchInFilter(docRef1);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef1 = navigationMenu.getColumnValueFromTable("Account Ref");

			sleepFor(2000);
			navigationMenu.searchInFilter(docRef2);
			capture.clickOnFirstItemOfIntray();
			String getAccountRef2 = navigationMenu.getColumnValueFromTable("Account Ref");

			AssertionHelper.verifyText(getAccountRef1, accRef1);
			AssertionHelper.verifyText(getAccountRef2, accRef1);
			// sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 502, enabled = false)
	public void VerifyDistributeIconUsingBasicUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
		//navigationMenu.UnSelectMultiCheckbox();
		capture.clickOnFirstItemOfIntray();
		boolean status =  navigationMenu.waitForIconStatus("Distribute intray item", "Mail");
		AssertionHelper.verifyTrue(status, "Checking button is present or not");
	}
	
	@Test(priority = 503,enabled = false)
	public void DistributeDocumentUsingBasicUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();			
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", "%%");
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		new WindowHelper(driver).waitForPopup("Validate distribution Failed");
		String getPopMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		AssertionHelper.verifyTextContains(getPopMsg, "User does not have System Implementer or System Administrator or DIP security permission.");
	}

}
