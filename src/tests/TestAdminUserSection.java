package tests;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import utils.ExtentReports.ExtentTestManager;

public class TestAdminUserSection extends TestBase {
	public FileSystemSection adminFS ;
	public AdminUserSection adminUser;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	VerificationHelper verificationHelper;
	public LoginPage login;
	
	@BeforeClass
	public void setupClass()  {
		adminFS = new FileSystemSection(driver);
		adminUser= new AdminUserSection(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		xls = new ExcelReader();
		windowHelper = new WindowHelper(driver);
		dropdownHelper=new DropdownHelper(driver);
		verificationHelper=new VerificationHelper(driver);
		login = new LoginPage(driver);
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
	
	@DataProvider(name="newRoleTeamData")
	public Object[][] newRoleTeamData() throws Exception
	{
		Object[][] formData = xls.readExcel( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newRoleAndTeam");
				
		return formData;
	}
	
	
	
	@DataProvider(name="newUsersData")
	public Object[][] newUsersData() throws Exception
	{
		Object[][] formData = xls.readExcel( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newUsersData");
		return formData;
	}
	
	@Test( priority=403,enabled = true )
	public void TestViewUsersLandingPage() throws InterruptedException {
	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUserLink();
	 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Edit user", "Actions"),"Verifying if edit user is enabled");
	 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Delete this user", "Actions"),"Verifying if edit user is enabled");
	 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Cancel changes", "Actions"),"Verifying if edit user is enabled");
	}
	
	//Incomplete
	@Test( priority=404,enabled = true )
	public void TestEditOnUsersLandingPage() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
	 
	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUserLink();
	 navigationMenu.clickOnIcon("Edit user", "Actions");
	 AssertionHelper.verifyText(navigationMenu.getNavbarText(), "Edit User"); 
	 String secLevel = adminUser.getSecurityLevel();
	 String newSecValue= String.valueOf(Integer.parseInt(secLevel)-1);
	 sleepFor(1000);
	 adminUser.enterSecurityLevel(newSecValue);
	 sleepFor(1000);
	 //navigationMenu.clickOnSaveIcon();
	 //windowHelper.clickOkOnPopup();
	 if(getEnvVariable.equals("Enterprise_Sp1s")) {
    	 navigationMenu.clickOnSaveIcon();
    	 navigationMenu.waitForIcon("Edit user");
     }else {
    	 navigationMenu.clickOnSaveIconForUser();
    	 navigationMenu.waitForIcon("Edit user");
     }
//	 navigationMenu.clickOnSaveIconForUser();					//Added becuse above is not working
//	 navigationMenu.clickOnTab("User");
//	 navigationMenu.waitForIcon("Edit user");
	 //Validate changes edited are reflected or not
	 //Validate cancel changes button
	 //Validate no on cancel popup
	 
	}

	
	@Test(priority=405, enabled = true )
	public void TestCancelChangesFromUsersLandingPage() throws InterruptedException {
	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUserLink();
	 navigationMenu.clickOnIcon("Cancel changes", "Actions");
	 navigationMenu.waitForAddIcon();
	 AssertionHelper.verifyText(navigationMenu.getNavbarText(), "Users");
	}
	
	@Test(priority=406, enabled = true )
	public void TestTeamLandingFromUsersLandingPage() throws InterruptedException {
	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUserLink();
	 adminUser.clickOnTeamName();
	 navigationMenu.waitForIcon("Edit team");
	 AssertionHelper.verifyText(navigationMenu.getNavbarText(), "Team Landing Page");
	}
	
	@Test(priority=407, enabled = true )
	public void TestContentAuditActionsUsersLandingPage() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
		test = ExtentTestManager.getTest();
	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUserLink();
	 adminUser.clickOnAuditActionsTab();
	 AssertionHelper.verifyTrue(adminUser.isAuditActionsElementsPresent(), "Waiting for audit action elements");
	 Set<String> Unqvalues = adminUser.getUniqueActionColumnValues();
	 System.out.println("Unqvalues "+Unqvalues);
	 test.log(Status.INFO, Unqvalues.toString());
	 for(String value:Unqvalues) {
		 if(!value.equals("UpdateMetadata")) {
		 adminUser.selectFromAuditActionType(value,getEnvVariable);
		 String actVal = navigationMenu.getColumnValueByIndex(adminUser.aduitActionGridTableID, 4);
		 AssertionHelper.verifyText(actVal, value);
		 }
	 }
	 
	 home.refreshCurrentFileSystem();				//Added because in next node user is not searchable in R530
	 sleepFor(3000);
	 
	}
	
	@Test(priority=408, enabled = true )
	public void TestCheckedOutItemUsersLandingPage() throws InterruptedException {
		test = ExtentTestManager.getTest();
	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUserLink();
	 adminUser.clickOnAuditActionsTab();
	 adminUser.clickOnCheckedOutItemsTab();
	 int expectedCnt= adminUser.getCheckedOutTileCount();
	 test.info("ExpectedCount "+expectedCnt);
	 int actualCnt = adminUser.getCountCheckedoutTableRows();
	 test.info("ActualCount "+actualCnt);
//	 AssertionHelper.verifyText(String.valueOf(actualCnt), String.valueOf(expectedCnt));
//	 if(actualCnt>1) {
//		 AssertionHelper.verifyTrue(expectedCnt == actualCnt, "Comparing "+expectedCnt+" with "+actualCnt+" to be equal");
//	 }else {
//		 AssertionHelper.verifyTrue(expectedCnt == 0, "Comparing "+expectedCnt+" with "+actualCnt+" to be equal"); 
//	 }
	 
	}
	
	/**********Printer test cases    ********/
	
	@Test(priority=451, enabled = true )
	public void TestAddPrinters() throws InterruptedException {
	 String printerName = "Canon";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("Printers configuration", "User");
	 navigationMenu.waitForAddIcon();
	 navigationMenu.clickOnIcon("Add a new printer", "Actions");
	 navigationMenu.waitForIcon("Save changes made to printer");
	 adminUser.enterPrinterName(printerName);
	 adminUser.enterPrinterPath("//Home/canon");
	 sleepFor(2000);
	 try {																		//added try catch to avoid error for duplicate record
		 navigationMenu.clickOnIcon("Save changes made to printer", "Actions");
		 navigationMenu.waitForAddIcon();
		 navigationMenu.searchInFilter(printerName);
		 Thread.sleep(2000);
		 String actualPrinter = navigationMenu.getfilteredRowElement(adminUser.printerTableID).getText();
		 AssertionHelper.verifyText(actualPrinter, printerName);
		 
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("Team maintenance", "User");
		 navigationMenu.waitForFilterTextBox();
		 navigationMenu.waitForAddIcon();
		 navigationMenu.clickOnFilteredRow("teamsTable");
		 navigationMenu.clickOnEditIcon();
		 navigationMenu.waitForIcon("Cancel changes");
		 List<String> options = dropdownHelper.getAllOptionsFromDropdown("PrinterId",getEnvVariable);
		 AssertionHelper.verifyTrue(options.contains(printerName), "Verifying Printer name in teams printer dropdown");
	   } catch (Exception e) {
		 String actMessage = windowHelper.getPopupMessage();
		 windowHelper.clickOkOnPopup();
		 AssertionHelper.verifyTextContains(actMessage, "already exists");
		 
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("Team maintenance", "User");
		 navigationMenu.waitForFilterTextBox();
		 navigationMenu.waitForAddIcon();
		 navigationMenu.clickOnFilteredRow("teamsTable");
		 navigationMenu.clickOnEditIcon();
		 navigationMenu.waitForIcon("Cancel changes");
		 List<String> options = dropdownHelper.getAllOptionsFromDropdown("PrinterId",getEnvVariable);
		 AssertionHelper.verifyTrue(options.contains(printerName), "Verifying Printer name in teams printer dropdown");
	}
	
}
	

	@Test(priority=454, enabled=true)
	public void TestVerifyPrinterFileSystem() throws InterruptedException {
	 String printerName = "Canon";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

	 getApplicationUrl();
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("File System maintenance", "File System");
	 navigationMenu.waitForAddIcon();
	 String fsName = home.getCurrentFileSystemName().split("-")[0].trim();
	 System.out.println("File system name is "+fsName);
	 
	 String fsName1 = home.getCurrentFileSystemName();
	 System.out.println("fsName1============"+fsName1);
	 navigationMenu.searchInFilter(fsName);
	 navigationMenu.clickOnFilteredRow(adminFS.fileSystemTableId);
	 navigationMenu.clickOnEditIcon();
	 navigationMenu.waitForIcon("Save changes");
	  List<String> options =dropdownHelper.getAllOptionsFromDropdown(adminFS.ddPrinterFileSystemId,getEnvVariable);
	  System.out.println("options "+options);
	  AssertionHelper.verifyTrue(options.contains(printerName), "Verifying Printer name in teams printer dropdown");
	}
	
	@Test(priority=456, enabled=true)
	public void TestAddedPrinterShownUnderAddTeam() throws InterruptedException {
	 String printerName = "Canon";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

	 getApplicationUrl();
	 adminUser.clickOnTeamsUnderAdmin();
	 adminUser.clickOnAddIconForTeam();
	 List<String> printerTexts = adminUser.getDropdownPrintersTextUnderAddTeam(getEnvVariable);
	 AssertionHelper.verifyTrue(printerTexts.contains(printerName), "Verifying printer name exists for "+printerName);
	}
	 	
	
	}



