package tests;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import utils.ExtentReports.ExtentTestManager;

public class TestAdminFileSystem extends TestBase {
	public FileSystemSection adminFS ;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	public LoginPage login;
	private Logger log = LoggerHelper.getLogger(TestAdminFileSystem.class);
	
	private String descriptionBank="";
	
	@BeforeClass
	public void setupClass()  {
		adminFS = new FileSystemSection(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		xls = new ExcelReader();
		dropdownHelper = new DropdownHelper(driver);
		login = new LoginPage(driver);
		windowHelper = new WindowHelper(driver);
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
	
	@DataProvider(name="bankHolidayData")
    public Object[][] bankHolidaysData() throws Exception
    {
        Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","bankHoliday");
        return formData;
    }
	
	@Test(priority=551,enabled = true, description = "Test navigation to flexible admin flow")
	public void TestGoToFlexibleIndexAdmin() {
	 //navigationMenu.clickOnHomePageIcon();
	 getApplicationUrl();
	 adminFS.clickOnAdminTab();
	 //adminFS.clickOnFlexibleIndexAdminButton();	 
	 navigationMenu.clickOnIconMenu("Flexible Index maintenance", "File System");
	 sleepFor(2000);
	 AssertionHelper.verifyTrue(adminFS.isDocumentElementDisplayed(), "Assertion for is document element displayed");
	}
		
	
	@DataProvider(name="newFileSystemData")
	public Object[][] newFileSystemData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newFileSystemData");
				
		return formData;
	}	
	
	@Test(priority=90,enabled = true,dataProvider = "newFileSystemData")		//true
	public void TestAddNewCacheServer(Map<String, String> map) {
		String FSName= map.get("FSName"),
		description = map.get("description");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Cache Servers maintenance","Cache");
		navigationMenu.clickOnAddIcon();
		String navbatTitle = navigationMenu.getNavbarText();
		AssertionHelper.verifyText(navbatTitle, "Add Cache Server");
		
		//adminFS.selectCacheStatus(map.get("cacheStatus"));
		adminFS.selectCacheStatus("Offline",getEnvVariable);
		adminFS.enterHighWaterMark(map.get("highWaterMark"));
		adminFS.enterClearBlackMark(map.get("clearBlackMark"));
		adminFS.enterWeighting(map.get("weighting"));
		adminFS.enterServerPath(map.get("serverPath"));
		adminFS.clickOnFileSystem();
		//String FSDropdown = FSName+" - "+description;
		//String selctedFS = adminFS.AddFileSystem(FSDropdown);
		//AssertionHelper.verifyText(selctedFS, FSDropdown);
		
		//adminFS.clickOnSaveButton();
		//home.refreshFileSystem(FSName);
		//home.refreshCurrentFileSystem();
	}
	
	@Test(priority=91,enabled = true,dataProvider = "newFileSystemData")
	public void EditCacheServer(Map<String, String> map) {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String [] getFileSystemName=new HomePage(driver).getCurrentFileSystemName().split("\n");
		
		//String FSName= map.get("FSName"),
		//description = map.get("description");
		String FSName=getFileSystemName[0];
		//description = getFileSystemName[1];
		
		String FSDropdown = FSName;		//+" - "+description;
		getApplicationUrl();
		
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Cache Servers maintenance","Cache");
		navigationMenu.searchInFilter("Online");
		adminFS.clickOnFilteredCacheRow();
		navigationMenu.clickOnEditIcon();
		adminFS.clickOnFileSystem();
		adminFS.RemoveFileSystemFromCache(FSDropdown,getEnvVariable);
		sleepFor(1000);
		//adminFS.clickOnSaveButton();
		navigationMenu.clickOnIconMenu("Save changes made to cache server", "Actions");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Cache Servers maintenance","Cache");
		navigationMenu.searchInFilter("Online");
		adminFS.clickOnFilteredCacheRow();
		navigationMenu.clickOnEditIcon();
		adminFS.clickOnFileSystem();
		adminFS.AddFileSystem(FSDropdown,getEnvVariable);
		sleepFor(1000);
		adminFS.clickOnSaveButton();
		//navigationMenu.clickOnIconMenu("Save changes made to cache server", "Actions");
		
		 sleepFor(1000);
         home.refreshCurrentFileSystem();
	}
	
	@Test(priority=92,enabled = true)
	public void VerifyErrorMessageInCacheServer() {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Cache Servers maintenance","Cache");
		navigationMenu.searchInFilter("Online");
		adminFS.clickOnFilteredCacheRow();
		navigationMenu.clickOnEditIcon();
		
		sleepFor(1000);
		adminFS.enterHighWaterMark("50");
		sleepFor(2000);
		adminFS.enterClearBlackMark("90");
		sleepFor(1000);
		//click(adminFS.saveBtn,"Clicking on save cache server button");
		navigationMenu.clickOnIconMenu("Save changes made to cache server", "Actions");
		
		new WindowHelper(driver).waitForPopup("Cache Servers");
		String warnMsg =  windowHelper.getPopupMessage();
		AssertionHelper.verifyTextContains(warnMsg, "The values Clear Back Mark and High Water Mark are equal. This means that Cache Clear down will never effect this cache. Do you want to Continue with Save?");
		sleepFor(1000);
		adminFS.clickOnDialogCancelButton();
		
		navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
		
		windowHelper.waitForPopup("Leave Page");	
		sleepFor(1000);
		windowHelper.clickOnModalFooterButton("No");	
	}
	
	
	@Test(priority=99,enabled = true,dataProvider = "bankHolidayData")
	public void AddBankHoliday(Map<String, String> map) {
		String getFutureDate=getDate("2","Plus");
		String monthYearField=map.get("monthYear"),
			   monthField=map.get("month"),
			   dayField=map.get("day"),
		fileSystemField=map.get("specificFileSystem");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		descriptionBank=map.get("description")+"_"+generateRandomNumber();
		
		//String getAnotherFutureDate=getDate("6", "Plus");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Bank Holidays maintenance","File System");
		navigationMenu.waitForAddIcon();
		
		navigationMenu.clickOnAddIcon();
		//adminFS.enterBankHolidayDateUsingCalender("September 2022", "Sep", "1");
		adminFS.enterBankHolidayDateUsingCalender(monthYearField,monthField,dayField);
		adminFS.enterDescriptionName(descriptionBank);
		adminFS.unselectSpecificToFileSystemCheckbox(fileSystemField);	
		
		try {
			//adminFS.clickOnSaveButton();
			navigationMenu.clickOnIconMenu("Save changes made to bank holiday", "Actions");
			if(!getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.waitForAddIcon();
			}
		} catch (Exception e) {
			String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "already exists.");
		}
		
	}
	
	@Test(priority=100,enabled = true)
	public void EditBankHoliday() {
		String getDescription=descriptionBank;
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Bank Holidays maintenance","File System");
		new NavigationMenu(driver).waitForFilterTextBox();
		//sendKeys(new NavigationMenu(driver).filterTxt, getDescription, "Entering description in text");
		navigationMenu.searchInFilter("Bank_Holiday_");
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("bankHolidayDataTable");
		//new NavigationMenu(driver).clickOnEditIcon();
		//adminFS.enterBankHolidayDateUsingCalender("September 2021", "Sep", "3");
		adminFS.enterDescriptionName(descriptionBank+"delete");
		
		try {
			//adminFS.clickOnSaveButton();
			navigationMenu.clickOnIconMenu("Save changes made to bank holiday", "Actions");
			navigationMenu.waitForAddIcon();
		} catch (Exception e) {
			String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "already exists.");
		}
		
	}
	
	@Test(priority=101,enabled = true)
	public void DeleteBankHoliday() {
		String getDescription1=descriptionBank;
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Bank Holidays maintenance","File System");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter("delete");
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("bankHolidayDataTable");
		//navigationMenu.clickOnDeleteIcon();
		navigationMenu.clickOnIconMenu("Delete this bank holiday", "Actions");
		navigationMenu.clickOkOnPopup();
		navigationMenu.waitForAddIcon();
		
	}
	
	

}
