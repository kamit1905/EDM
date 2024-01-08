package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
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
import utils.ExtentReports.ExtentTestManager;

public class TestSmokeCases extends TestBase {
	public FileSystemSection adminFS ;
	public AdminDocumentSection adminDocument;
	public AdminUserSection adminUser;
	public DocumentToolsPage docTools;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public NavigationMenu navigationMenu;
	public DropdownHelper dropdownHelper;
	public WindowHelper windowHelper;
	public LoginPage login;
	public static String teamName;
	public static String roleName;
	public IntrayToolsPage intrayTools;
	private Logger log = LoggerHelper.getLogger(TestSmokeCases.class);
	
	
	@BeforeClass
	public void setupClass()  {
		adminFS = new FileSystemSection(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		xls = new ExcelReader();
		windowHelper=  new WindowHelper(driver);
		adminDocument= new AdminDocumentSection(driver);
		login = new LoginPage(driver);
		adminUser= new AdminUserSection(driver);
		docTools = new DocumentToolsPage(driver);
		intrayTools = new IntrayToolsPage(driver);
	}
	
	
	/*@Test( enabled=true,priority=0, description = "This function tests the valid flow of login", groups = "smoke")
	public void TestLoginFunctionality() throws InterruptedException {
		String username = ObjectReader.reader.getLoginId();
		String password = ObjectReader.reader.getPassword();
		login.loginWithCredentials(username,password);
		//refreshPage();
		String welcomeActual = login.getWelcomeText();
		String usernameActual = login.getUsernameText();
		AssertionHelper.verifyText(welcomeActual, "Welcome");
		System.out.println("UsernameActual is "+usernameActual);
		AssertionHelper.verifyText(usernameActual, username);

	}*/
	
	@DataProvider(name="newFileSystemData")
	public Object[][] newFileSystemData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newFileSystemData");
				
		return formData;
	}
	
	@Test(enabled=false,dataProvider = "newFileSystemData")
	public void TestAddFileSystem(Map<String, String> map) {
		String FSName=map.get("FSName"),
				description=map.get("description");
		Boolean folder1chk=Boolean.parseBoolean(map.get("folder1chk")),
				folder2chk=Boolean.parseBoolean(map.get("folder2chk")),
				folder3chk=Boolean.parseBoolean(map.get("folder3chk"));
			String	folder1EntityName=map.get("folder1Name"),
				folder2EntityName=  map.get("folder2Name"),
				folder3EntityName =  map.get("folder3Name");
		navigationMenu.getApplicationUrl();		
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("File System maintenance","File System");
		navigationMenu.clickOnAddIcon();
		adminFS.clickOnNextButton();
		adminFS.enterFileSystemName(FSName);
		adminFS.enterDescriptionName(description);
		adminFS.clickOnNextButton();
		adminFS.enterAdminUserId(map.get("userId"));
		adminFS.enterAdminUsername(map.get("userName"));
		adminFS.clickOnNextButton();
		adminFS.enterFolderNames(folder1EntityName, folder2EntityName, folder3EntityName);
		adminFS.selectMandatoryFolder(folder1chk, folder2chk, folder3chk);
		adminFS.clickOnNextButton();
		adminFS.clickOnNextButton();
		adminFS.clickOnFinishButton();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("File System maintenance","File System");
		adminFS.searchInFilter(FSName);
		String fsname = adminFS.getFileSystemName();
		AssertionHelper.verifyText(fsname, FSName);
		home.refreshCurrentFileSystem();
	}
		
	@Test(priority=2,enabled =false,dataProvider = "newFileSystemData")
	public void TestAddFileSystemToCache(Map<String, String> map) {
		String FSName= map.get("FSName"),
				description = map.get("description");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Cache Servers maintenance","Cache");
		navigationMenu.waitForAddIcon();
		navigationMenu.clickOnAddIcon();
		String navbatTitle = navigationMenu.getNavbarText();
		AssertionHelper.verifyText(navbatTitle, "Add Cache Server");
		adminFS.selectCacheStatus(map.get("cacheStatus"),getEnvVariable);
		adminFS.enterHighWaterMark(map.get("highWaterMark"));
		adminFS.enterClearBlackMark(map.get("clearBlackMark"));
		adminFS.enterWeighting(map.get("weighting"));
		adminFS.enterServerPath(map.get("serverPath"));
		adminFS.clickOnFileSystem();
		String FSDropdown = FSName+" - "+description;
		String selctedFS = adminFS.AddFileSystem(FSDropdown,getEnvVariable);
		AssertionHelper.verifyText(selctedFS, FSDropdown);
		adminFS.clickOnSaveButton();
		//home.refreshCurrentFileSystem();
		home.changeFileSystem(FSName);
	}
	
	
	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}	
	

	@Test(enabled =true, priority=5, dataProvider="captureFormData")			//true
	public void TestAddProtectiveMarker(Map<String, String> map) {
		String protMarker = map.get("ProtectiveMarker");	
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Protective Markers","Document");
		navigationMenu.waitForAddIcon();
		try {
		String actualMarker = adminDocument.addProtectiveMarker(protMarker);
		AssertionHelper.verifyText(actualMarker,protMarker);
		}
		catch(Exception ex){
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "already exists");
		 }
		}
	
	
	
	@DataProvider(name="newRoleTeamData")
	public Object[][] newRoleTeamData() throws Exception
	{
		Object[][] formData = xls.readExcel( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newRoleAndTeam");
				
		return formData;
	}
	
	//pass
	@Test(priority=6, enabled =true,dataProvider = "newRoleTeamData")			//true
	public void TestCreateRole(String roleId, String rolename, String teamId, String teamName) throws InterruptedException {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Role maintenance", "User");
		navigationMenu.clickOnAddIcon();
		
		try {
			adminUser.enterRole(roleId,rolename);
			List<String> roles = adminUser.getAddedRoleData();
			AssertionHelper.verifyText(roles.get(0), roleId);
			AssertionHelper.verifyText(roles.get(1), rolename);
			
		} catch (Exception e) {
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "already exists");
		}
		
	}
	
	//Verified with 522 Pass
	@Test(priority=7, enabled = true,dataProvider = "newRoleTeamData")			//true
	public void TestCreateTeam(String roleId, String rolename, String teamId, String teamName) {
		ArrayList <String> mailStatuses = new ArrayList <String>();
		mailStatuses.add("Complete");
		mailStatuses.add("Pending");
		mailStatuses.add("Verified");
		mailStatuses.add("Matched");
		mailStatuses.add("New");
		mailStatuses.add("New Template");
		mailStatuses.add("In Progress");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.clickOnAddIcon();
		adminUser.addTeamId(teamId);
		adminUser.addTeamName(teamName);
		adminUser.selectUsersRole(rolename,getEnvVariable);
		adminUser.clickOnMailStatusesTab();
		adminUser.addMailStatuses(mailStatuses,getEnvVariable);
		try {
		navigationMenu.clickOnSaveIcon();
		List<String> teamData = adminUser.getAddedTeamData();
		AssertionHelper.verifyText(teamData.get(0), teamId);
		AssertionHelper.verifyText(teamData.get(1), teamName);
		}
		 catch(Exception ex){
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "already");
		 }
		}
	
		//Adding because this team is used in distribution
		@Test(priority=8, enabled = true)			//true
		public void TestCreateAnotherTeam() {
			ArrayList <String> mailStatuses = new ArrayList <String>();
			mailStatuses.add("Complete");
			mailStatuses.add("Pending");
			mailStatuses.add("Verified");
			mailStatuses.add("Matched");
			mailStatuses.add("New");
			mailStatuses.add("New Template");
			mailStatuses.add("In Progress");
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Team maintenance", "User");
			navigationMenu.clickOnAddIcon();
			adminUser.addTeamId("Devs");
			adminUser.addTeamName("Devs");
			adminUser.selectUsersRole("QualityMan",getEnvVariable);
			adminUser.clickOnMailStatusesTab();
			adminUser.addMailStatuses(mailStatuses,getEnvVariable);
			try {
			navigationMenu.clickOnSaveIcon();
			List<String> teamData = adminUser.getAddedTeamData();
			//AssertionHelper.verifyText(teamData.get(0), "Devs");
			//AssertionHelper.verifyText(teamData.get(1), "Devs");
			}
			 catch(Exception ex){
				 String actMessage = windowHelper.getPopupMessage();
				 windowHelper.clickOkOnPopup();
				 AssertionHelper.verifyTextContains(actMessage, "already");
			 }
			}
	

	//Recheck again
	@Test(priority=11, enabled =true, dataProvider = "newRoleTeamData")
	public void TestAddUserToTeam(String roleId, String rolename, String teamId, String teamName) throws InterruptedException {
	 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
	 getApplicationUrl();
	 TestSmokeCases.roleName = rolename;
	 TestSmokeCases.teamName = teamName;
	 navigationMenu.clickOnTab("Administration");
	 navigationMenu.clickOnIcon("User maintenance", "User");
	 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
	 Thread.sleep(2000);
	 adminUser.clickOnFilteredUser();
	 navigationMenu.clickOnEditIcon();
	 adminUser.enterSecurityLevel("9");//change after every execution
	 Thread.sleep(2000);
	 adminUser.selectUsersRole(rolename,getEnvVariable);
	 sleepFor(1000);
	 adminUser.selectUsersTeam(teamName,getEnvVariable);
	 try {
		 Thread.sleep(2000);
		 if(getEnvVariable.equals("Enterprise_Sp1s")) {
			 navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
		 }else {
			 adminUser.clickOnUserSaveIcon(teamName, rolename,getEnvVariable); 
		 }
		 navigationMenu.waitForAddIcon();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("User maintenance", "User");
		 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
		 Thread.sleep(2000);
		 adminUser.clickOnFilteredUser();
		 navigationMenu.clickOnEditIcon();
		 String actualteamName = adminUser.getSelectedTeamName();
		 String actualRoleName = adminUser.getSelectedRoleName();
		 AssertionHelper.verifyText(actualteamName, teamName);
		 AssertionHelper.verifyText(actualRoleName, rolename);
		 
		 home.refreshCurrentFileSystem();
		
	} catch (Exception e) {
		String actMessage = windowHelper.getPopupMessage();
		 windowHelper.clickOkOnPopup();
		 AssertionHelper.verifyTextContains(actMessage, "No changes");
		 
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("User maintenance", "User");
		 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
		 Thread.sleep(2000);
		 adminUser.clickOnFilteredUser();
		 navigationMenu.clickOnEditIcon();
		 String actualteamName = adminUser.getSelectedTeamName();
		 String actualRoleName = adminUser.getSelectedRoleName();
		 AssertionHelper.verifyText(actualteamName, teamName);
		 AssertionHelper.verifyText(actualRoleName, rolename);
		 
		 home.refreshCurrentFileSystem();
	}
	 
	}
	
	@DataProvider(name="newUsersData")
	public Object[][] newUsersData() throws Exception
	{
		Object[][] formData = xls.readExcel( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","NewUsersData");
		return formData;
	}

	  
	 @DataProvider(name="docreReferenceData")
		public Object[][] docreReferenceData() throws Exception
		{
			Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");	
			return formData;
		}
	
}
