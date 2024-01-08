package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

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

public class TestSearching_e2e_B_UAT extends TestBase {
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
	public MoreSearch moreSearch;
	
	private Logger log = LoggerHelper.getLogger(CapturePage.class);
	private String envType="NECDM_Lives";
	private String randomNo = generateRandomNumber();
	private String superAdminUserName = "SAU_AT"; 
	
	private String 	Role = "R", 
					RoleName = "RN"+ "_" + Role,
					TeamId = "TId",
					TeamName = "TN_"+ TeamId;

//	Role1						Role2
//	TeamId_1A	TeamId_1B		TeamId_2A	TeamId_2B
//	Ui_1A		Ui_1B			Ui_2A		Ui_2B
//	Uii_1A		Uii_1B			Uii_2A		Uii_2B
//	Uiii_1A		Uiii_1B			Uiii_2A		Uiii_2B

	private String 	Role_1 = "R1A"+ "_" + Role, 
					RoleName_1 = "R1A"+ "_" + RoleName, 
					TeamId_1A = "R1A"+ "_" + TeamId, 
					TeamName_1A = "R1A"+ "_" + TeamName, 
					TeamId_1B = "R1B"+ "_" + TeamId, 
					TeamName_1B = "R1B"+ "_" + TeamName; 
					
	private String 	Ui_1A = "Ui_1A",
					Uii_1A = "Uii_1A",
					Uiii_1A = "Uiii_1A",
					Ui_1B = "Ui_1B",
					Uii_1B = "Uii_1B",
					Uiii_1B = "Uiii_1B";
					
	private String 	Role_2 = "R2A"+ "_" + Role, 
					RoleName_2 = "R2A"+ "_" + RoleName, 
					TeamId_2A = "R2A"+ "_" + TeamId, 
					TeamName_2A = "R2A"+ "_" + TeamName, 
					TeamId_2B = "R2B"+ "_" + TeamId, 
					TeamName_2B = "R2B"+ "_" + TeamName; 
	
	private String 	Ui_2A = "Ui_2A",
					Uii_2A = "Uii_2A",
					Uiii_2A = "Uiii_2A",
					Ui_2B = "Ui_2B",
					Uii_2B = "Uii_2B",
					Uiii_2B = "Uiii_2B";
	


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
		moreSearch = new MoreSearch(driver);
		xls = new ExcelReader();
	}

	@Test(priority = 1, enabled = false)
	public void prerequisite() throws InterruptedException  {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable------------------------------->>"+getEnvVariable);
//		Role1						Role2
//		TeamId_1A	TeamId_1B		TeamId_2A	TeamId_2B
//		Ui_1A		Ui_1B			Ui_2A		Ui_2B
//		Uii_1A		Uii_1B			Uii_2A		Uii_2B
//		Uiii_1A		Uiii_1B			Uiii_2A		Uiii_2B

		adminUser.CreateSuperAdminUserWithTeamAndRole(Ui_1A, Role_1, RoleName_1, TeamId_1A, TeamName_1A, getEnvVariable);
		adminUser.CreateSuperAdminUserWithTeamAndRole(Uii_1A, Role_1, RoleName_1, TeamId_1A, TeamName_1A, getEnvVariable);
//		adminUser.CreateSuperAdminUserWithTeamAndRole(Uiii_1A, Role_1, RoleName_1, TeamId_1A, TeamName_1A, getEnvVariable);

		adminUser.CreateSuperAdminUserWithTeamAndRole(Ui_1B, Role_1, RoleName_1, TeamId_1B, TeamName_1B, getEnvVariable);
		adminUser.CreateSuperAdminUserWithTeamAndRole(Uii_1B, Role_1, RoleName_1, TeamId_1B, TeamName_1B, getEnvVariable);
//		adminUser.CreateSuperAdminUserWithTeamAndRole(Uiii_1B, Role_1, RoleName_1, TeamId_1B, TeamName_1B, getEnvVariable);

		adminUser.CreateSuperAdminUserWithTeamAndRole(Ui_2A, Role_2, RoleName_2, TeamId_2A, TeamName_2A, getEnvVariable);
		adminUser.CreateSuperAdminUserWithTeamAndRole(Uii_2A, Role_2, RoleName_2, TeamId_2A, TeamName_2A, getEnvVariable);
		adminUser.CreateSuperAdminUserWithTeamAndRole(Uiii_2A, Role_2, RoleName_2, TeamId_2A, TeamName_2A, getEnvVariable);

		adminUser.CreateSuperAdminUserWithTeamAndRole(Ui_2B, Role_2, RoleName_2, TeamId_2B, TeamName_2B, getEnvVariable);
		adminUser.CreateSuperAdminUserWithTeamAndRole(Uii_2B, Role_2, RoleName_2, TeamId_2B, TeamName_2B, getEnvVariable);
//		adminUser.CreateSuperAdminUserWithTeamAndRole(Uiii_2B, Role_2, RoleName_2, TeamId_2B, TeamName_2B, getEnvVariable);
		}
	
//	Create Role level Saved search with tile and without tile (with proper search criteria which would fetch documents) and check both positive and negative scenario
//	Delete saved searches from Edit Role page with proper security setting - need to check
//	@Test(priority = 2, enabled = false, dependsOnMethods = {"prerequisite"})
	@Test(priority = 2, enabled = false)
	public void verifyRoleLevelSavedSearch() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

//		Role1						Role2
//		TeamId_1A	TeamId_1B		TeamId_2A	TeamId_2B
//		Ui_1A		Ui_1B			Ui_2A		Ui_2B
//		Uii_1A		Uii_1B			Uii_2A		Uii_2B
//		Uiii_1A		Uiii_1B			Uiii_2A		Uiii_2B

		String 	role = "R", 
				roleName = "RN"+ "_" + Role,
				teamId = "TId",
				teamName = "TN_"+ TeamId;
		
		String 	Role_2 = "R2A"+ "_" + role, 
				RoleName_2 = "R2A"+ "_" + roleName, 
				TeamId_2A = "R2A"+ "_" + teamId, 
				TeamName_2A = "R2A"+ "_" + teamName, 
				TeamId_2B = "R2B"+ "_" + teamId, 
				TeamName_2B = "R2B"+ "_" + teamName; 
		String 	Ui_2A = "Ui_2A",
				Uii_2A = "Uii_2A",
				Uiii_2A = "Uiii_2A",
				Ui_2B = "Ui_2B",
				Uii_2B = "Uii_2B",
				Uiii_2B = "Uiii_2B";
		String 	Ui_1A = "Ui_1A",
				Uii_1A = "Uii_1A",
				Uiii_1A = "Uiii_1A",
				Ui_1B = "Ui_1B",
				Uii_1B = "Uii_1B",
				Uiii_1B = "Uiii_1B";
		
		String documentType = "Default - General Default Document Type";
		String Team = TeamId_2A;
		String TeamName = TeamName_2A;
		String User = Ui_2A;
		String Role = Role_2;
		String RoleName = RoleName_2;
		String mailStatus = null;

		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName1 = "1_" + Role + randomNo;
		boolean tileCheckbox1 = true;
		String savedSearchInputName2 = "2_" + Role + randomNo;
		boolean tileCheckbox2 = false;
		String tagRadioButton = "Any";
		String scopeType = "Role";				//"Team";		//"Current User";		//"File System";
		
		try {
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Ui_2A, ObjectReader.reader.getPassword());
			
//			create more search and save it
			moreSearch.createMoreSearchWithAndWithoutHomePageTile(documentType, Team, TeamName, User, paramsList, mailStatus, tagRadioButton, Role, RoleName, savedSearchInputName1, tagRadioButton, scopeType, tileCheckbox1, getEnvVariable);
//			To avoid the glitch of not showing the values in the fields of more search we visit the more search 
			getApplicationUrl();
			docTools.clickOnMoreSearch();
			sleepFor(4000);
			
//			create more search and save it
			getApplicationUrl();
			moreSearch.createMoreSearchWithAndWithoutHomePageTile(null, Team, TeamName, User, paramsList, mailStatus, tagRadioButton, Role, RoleName, savedSearchInputName2, tagRadioButton, scopeType, tileCheckbox2, getEnvVariable);
			
//			refresh file system
			getApplicationUrl();
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(2000);
			
//			To avoid the glitch of not showing the values in the fields of more search we visit the more search 
			getApplicationUrl();
			docTools.clickOnMoreSearch();
			sleepFor(4000);

//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status1 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyTrue(status1, "--------->> The " +savedSearchInputName1 + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Ui_2A);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Ui_2A);
			
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Uii_2A, ObjectReader.reader.getPassword());
//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status2 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyTrue(status2, "--------->> The " +savedSearchInputName1 + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Uii_2A);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Uii_2A);

			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Ui_2B, ObjectReader.reader.getPassword());
//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status3 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyTrue(status3, "--------->> The " +savedSearchInputName1 + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Ui_2B);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Ui_2B);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Ui_1A, ObjectReader.reader.getPassword());
//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status4 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyFalse(status4, "--------->> The " +savedSearchInputName1 + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Ui_1A);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Ui_1A);
			
//			post-requisite
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Uii_2A, ObjectReader.reader.getPassword());

//			delete more search
			moreSearch.deleteMoreSearch(savedSearchInputName1);
			moreSearch.deleteMoreSearch(savedSearchInputName2);
		} finally {
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}
	
//	Create Team level Saved search with tile and without tile (with proper search criteria which would fetch documents) and check both positive and negative scenario
//	Delete saved searches from Edit Team page with proper security setting - need to check
//	@Test(priority = 3, enabled = true, dependsOnMethods = {"prerequisite"})
	@Test(priority = 3, enabled = false)
	public void verifyTeamLevelSavedSearch() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

//		Role1						Role2
//		TeamId_1A	TeamId_1B		TeamId_2A	TeamId_2B
//		Ui_1A		Ui_1B			Ui_2A		Ui_2B
//		Uii_1A		Uii_1B			Uii_2A		Uii_2B
//		Uiii_1A		Uiii_1B			Uiii_2A		Uiii_2B

		String 	role = "R", 
				roleName = "RN"+ "_" + Role,
				teamId = "TId",
				teamName = "TN_"+ TeamId;
		
		String 	Role_2 = "R2A"+ "_" + role, 
				RoleName_2 = "R2A"+ "_" + roleName, 
				TeamId_2A = "R2A"+ "_" + teamId, 
				TeamName_2A = "R2A"+ "_" + teamName, 
				TeamId_2B = "R2B"+ "_" + teamId, 
				TeamName_2B = "R2B"+ "_" + teamName; 
		String 	Ui_2A = "Ui_2A",
				Uii_2A = "Uii_2A",
				Uiii_2A = "Uiii_2A",
				Ui_2B = "Ui_2B",
				Uii_2B = "Uii_2B",
				Uiii_2B = "Uiii_2B";
		String 	Ui_1A = "Ui_1A",
				Uii_1A = "Uii_1A",
				Uiii_1A = "Uiii_1A",
				Ui_1B = "Ui_1B",
				Uii_1B = "Uii_1B",
				Uiii_1B = "Uiii_1B";
		
		String documentType = "Default - General Default Document Type";
		String Team = TeamId_2A;
		String TeamName = TeamName_2A;
		String User = Ui_2A;
		String Role = Role_2;
		String RoleName = RoleName_2;
		String mailStatus = null;

		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName1 = "1_" + Team + randomNo;
		boolean tileCheckbox1 = true;
		String savedSearchInputName2 = "2_" + Team + randomNo;
		boolean tileCheckbox2 = false;
		String tagRadioButton = "Any";
		String scopeType = "Team";				//"Role";		//"Current User";		//"File System";
		
		try {
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Ui_2A, ObjectReader.reader.getPassword());
			
//			create more search and save it
			moreSearch.createMoreSearchWithAndWithoutHomePageTile(documentType, Team, TeamName, User, paramsList, mailStatus, tagRadioButton, Role, RoleName, savedSearchInputName1, tagRadioButton, scopeType, tileCheckbox1, getEnvVariable);

//			To avoid the glitch of not showing the values in the fields of more search we visit the more search 
			getApplicationUrl();
			docTools.clickOnMoreSearch();
			sleepFor(4000);
			
//			create more search and save it
			getApplicationUrl();
			moreSearch.createMoreSearchWithAndWithoutHomePageTile(null, Team, TeamName, User, paramsList, mailStatus, tagRadioButton, Role, RoleName, savedSearchInputName2, tagRadioButton, scopeType, tileCheckbox2, getEnvVariable);
			
//			refresh file system
			getApplicationUrl();
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(2000);
			
//			To avoid the glitch of not showing the values in the fields of more search we visit the more search 
			getApplicationUrl();
			docTools.clickOnMoreSearch();
			sleepFor(4000);

//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status1 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyTrue(status1, "--------->> The " +savedSearchInputName1 + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Ui_2A);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Ui_2A);

//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Uii_2A, ObjectReader.reader.getPassword());
//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status2 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyTrue(status2, "--------->> The " +savedSearchInputName1 + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Uii_2A);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Uii_2A);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Ui_2B, ObjectReader.reader.getPassword());
//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status3 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyFalse(status3, "--------->> The " +savedSearchInputName1 + "tile is not present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Ui_2B);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Ui_2B);

//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Ui_1A, ObjectReader.reader.getPassword());
//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status4 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName1);
			AssertionHelper.verifyFalse(status4, "--------->> The " +savedSearchInputName1 + "tile is not present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName1, Ui_1A);
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName2, Ui_1A);
			
//			post-requisite
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Uii_2A, ObjectReader.reader.getPassword());

//			delete more search
			moreSearch.deleteMoreSearch(savedSearchInputName1);
			moreSearch.deleteMoreSearch(savedSearchInputName2);
		} finally {
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
//	CaneditRoletile security setting - user with this security setting can edit and delete the tile or saved search
//	Delete saved searches from Edit Role page with proper security setting - need to check
//	@Test(priority = 4, enabled = false, dependsOnMethods = {"prerequisite"})
	@Test(priority = 4, enabled = true)
	public void verifyRoleLevelSavedSearchWithoutSecuritySetting() throws InterruptedException {
		test = ExtentTestManager.getTest();
		test.log(Status.INFO, "<<------Running test Can edit Role tile security setting - user with this security setting can edit and delete the tile or saved search------>>");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

//		Role1						Role2
//		TeamId_1A	TeamId_1B		TeamId_2A	TeamId_2B
//		Ui_1A		Ui_1B			Ui_2A		Ui_2B
//		Uii_1A		Uii_1B			Uii_2A		Uii_2B
//		Uiii_1A		Uiii_1B			Uiii_2A		Uiii_2B

		String 	role = "R", 
				roleName = "RN"+ "_" + Role,
				teamId = "TId",
				teamName = "TN_"+ TeamId;
		
		String 	Role_2 = "R2A"+ "_" + role, 
				RoleName_2 = "R2A"+ "_" + roleName, 
				TeamId_2A = "R2A"+ "_" + teamId, 
				TeamName_2A = "R2A"+ "_" + teamName, 
				TeamId_2B = "R2B"+ "_" + teamId, 
				TeamName_2B = "R2B"+ "_" + teamName; 
		String 	Ui_2A = "Ui_2A",
				Uii_2A = "Uii_2A",
				Uiii_2A = "Uiii_2A",
				Ui_2B = "Ui_2B",
				Uii_2B = "Uii_2B",
				Uiii_2B = "Uiii_2B";
		
		String documentType = "Default - General Default Document Type";
		String Team = TeamId_2A;
		String TeamName = TeamName_2A;
		String User = Ui_2A;
		String Role = Role_2;
		String RoleName = RoleName_2;
		String mailStatus = null;

		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName = Role + randomNo;
		boolean tileCheckbox = true;
		String tagRadioButton = "Any";
		String scopeType = "Role";				//"Team";		//"Current User";		//"File System";
		
		try {
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Uiii_2A, ObjectReader.reader.getPassword());
			
//			create more search and save it
			moreSearch.createMoreSearchWithAndWithoutHomePageTile(documentType, Team, TeamName, User, paramsList, mailStatus, tagRadioButton, Role, RoleName, savedSearchInputName, tagRadioButton, scopeType, tileCheckbox, getEnvVariable);
//			To avoid the glitch of not showing the values in the fields of more search we visit the more search 
			getApplicationUrl();
			docTools.clickOnMoreSearch();
			sleepFor(4000);

//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status1 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName);
			AssertionHelper.verifyTrue(status1, "--------->> The " +savedSearchInputName + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName, Uiii_2A);
			
//			pre-requisite ----->> Disable the rights
			//remove Take Work from a Shared In-Tray right
			adminUser.removeOrAddSecuirityRightFromUser(Uiii_2A, "System Administration", "Can Edit Role Tiles", "remove", getEnvVariable);
			
//			refresh file system
			getApplicationUrl();
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(2000);
//			verification
			getApplicationUrl();
			moreSearch.searchWithSavedSearch(savedSearchInputName);
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			
			boolean eleStatus = moreSearch.verifyGivenButtonIsDisable("Save");
			AssertionHelper.verifyFalse(eleStatus, "--------->> Save Button is Disabled");
			boolean eleStatus1 = moreSearch.verifyGivenButtonIsDisable("Delete");
			AssertionHelper.verifyFalse(eleStatus1, "--------->> Delete Button is Disabled");
			
		} 
			finally {
//				post-requisite----->> Enable the rights
				adminUser.removeOrAddSecuirityRightFromUser(Uiii_2A, "System Administration", "Can Edit Role Tiles", "add", getEnvVariable);
//				refresh file system
				getApplicationUrl();
				new HomePage(driver).refreshCurrentFileSystem();
				sleepFor(2000);
				
				moreSearch.deleteMoreSearch(savedSearchInputName);
				
//				verification
				getApplicationUrl();
				sleepFor(1000);
				navigationMenu.clickOnTab("Search"); 									// Change this if it causes prblm
				click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
				sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName, "Enetering in search =" + savedSearchInputName);
				
				SoftAssert soft = new SoftAssert();
//				soft.assertEquals(moreSearch.verifyGivenButtonIsDisable("Save"), true);
//				soft.assertEquals(moreSearch.verifyGivenButtonIsDisable("Delete"), true);
				
//				logout from the user or change user
				new HomePage(driver).clickOnChangeUser();
				sleepFor(2000);
//				Login with new user first time and change password
				new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}
	
//	CaneditTeamtile security setting - user with this security setting can edit and delete the tile or saved search//	Delete saved searches from Edit Team page with proper security setting - need to check
//	@Test(priority = 5, enabled = true, dependsOnMethods = {"prerequisite"})
	@Test(priority = 5, enabled = true)
	public void verifyTeamLevelSavedSearchWithoutSecuritySetting() throws InterruptedException {
		test = ExtentTestManager.getTest();
		test.log(Status.INFO, "<<------Running test Can edit Role tile security setting - user with this security setting can edit and delete the tile or saved search------>>");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

//		Role1						Role2
//		TeamId_1A	TeamId_1B		TeamId_2A	TeamId_2B
//		Ui_1A		Ui_1B			Ui_2A		Ui_2B
//		Uii_1A		Uii_1B			Uii_2A		Uii_2B
//		Uiii_1A		Uiii_1B			Uiii_2A		Uiii_2B

		String 	role = "R", 
				roleName = "RN"+ "_" + Role,
				teamId = "TId",
				teamName = "TN_"+ TeamId;
		
		String 	Role_2 = "R2A"+ "_" + role, 
				RoleName_2 = "R2A"+ "_" + roleName, 
				TeamId_2A = "R2A"+ "_" + teamId, 
				TeamName_2A = "R2A"+ "_" + teamName, 
				TeamId_2B = "R2B"+ "_" + teamId, 
				TeamName_2B = "R2B"+ "_" + teamName; 
		String 	Ui_2A = "Ui_2A",
				Uii_2A = "Uii_2A",
				Uiii_2A = "Uiii_2A",
				Ui_2B = "Ui_2B",
				Uii_2B = "Uii_2B",
				Uiii_2B = "Uiii_2B";
		
		String documentType = "Default - General Default Document Type";
		String Team = TeamId_2A;
		String TeamName = TeamName_2A;
		String User = Ui_2A;
		String Role = Role_2;
		String RoleName = RoleName_2;
		String mailStatus = null;

		List<String> paramsList = new ArrayList<>();
		String savedSearchInputName = Team + randomNo;
		boolean tileCheckbox = true;
		String tagRadioButton = "Any";
		String scopeType = "Team";				//"Role";		//"Current User";		//"File System";
		
		try {
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(Uiii_2A, ObjectReader.reader.getPassword());
			
//			create more search and save it
			moreSearch.createMoreSearchWithAndWithoutHomePageTile(documentType, Team, TeamName, User, paramsList, mailStatus, tagRadioButton, Role, RoleName, savedSearchInputName, tagRadioButton, scopeType, tileCheckbox, getEnvVariable);
//			To avoid the glitch of not showing the values in the fields of more search we visit the more search 
			getApplicationUrl();
			docTools.clickOnMoreSearch();
			sleepFor(4000);

//			Verification1 : check for HomePageTile 
			getApplicationUrl();
			boolean status1 = moreSearch.CheckTileIsPresentOnHomePage(savedSearchInputName);
			AssertionHelper.verifyTrue(status1, "--------->> The " +savedSearchInputName + "tile is present");
//			Verification2 : check for search Lists 
			moreSearch.verifyMoreSearchPresentInSearchList(savedSearchInputName, Uiii_2A);
			
//			pre-requisite ----->> Disable the rights
			//remove Take Work from a Shared In-Tray right
			adminUser.removeOrAddSecuirityRightFromUser(Uiii_2A, "System Administration", "Can Edit Team Tiles", "remove", getEnvVariable);
			
//			refresh file system
			getApplicationUrl();
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(2000);
			
//			verification
			getApplicationUrl();
			moreSearch.searchWithSavedSearch(savedSearchInputName);
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);	
			
			boolean eleStatus = moreSearch.verifyGivenButtonIsDisable("Save");
			AssertionHelper.verifyFalse(eleStatus, "--------->> Save Button is Disabled for TeamLevelSavedSearch");
			boolean eleStatus1 = moreSearch.verifyGivenButtonIsDisable("Delete");
			AssertionHelper.verifyFalse(eleStatus1, "--------->> Delete Button is Disabled for TeamLevelSavedSearch");
		} finally {
//			post-requisite----->> Enable the rights
			adminUser.removeOrAddSecuirityRightFromUser(Uiii_2A, "System Administration", "Can Edit Team Tiles", "add", getEnvVariable);
//			refresh file system
			getApplicationUrl();
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(2000);
			
			moreSearch.deleteMoreSearch(savedSearchInputName);
			
//			verification
			getApplicationUrl();
			sleepFor(1000);
			navigationMenu.clickOnTab("Search"); 									// Change this if it causes prblm
			click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
			sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName, "Enetering in search =" + savedSearchInputName);
			
			SoftAssert soft = new SoftAssert();
//			soft.assertEquals(moreSearch.verifyGivenButtonIsDisable("Save"), true);
//			soft.assertEquals(moreSearch.verifyGivenButtonIsDisable("Delete"), true);
			
//			logout from the user or change user
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
//			Login with new user first time and change password
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}
}











