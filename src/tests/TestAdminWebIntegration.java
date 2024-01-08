package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.WebIntegration;
import utils.ExtentReports.ExtentTestManager;

public class TestAdminWebIntegration extends TestBase {
	
	public DocumentToolsPage docTools ;
	private Logger log = LoggerHelper.getLogger(TestAdminWebIntegration.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	AlertHelper alertHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	public LoginPage login;
	public WebIntegration webIntegration;
	public AdminDocumentSection adminDocumentSection;
	
	@BeforeClass
	public void setupClass()  {
		docTools = new DocumentToolsPage(driver);
		capture = new CapturePage(driver);
		home= new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		xls = new ExcelReader();
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		webIntegration = new WebIntegration(driver);
		adminDocumentSection = new AdminDocumentSection(driver);
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
	
	
	
	@Test(priority = 94,enabled=true)
	public void AddWebIntegration() {
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String webInteName="User Team and FileSystem";
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Web Integration Options maintenance","Integration");
		navigationMenu.clickOnAddIcon();
		webIntegration.enterWebIntegrationName(webInteName);
		webIntegration.enterDecoratedLink("https://www.google.co.in/?q=[[System.LoggedOn.FileSystem]]");
		webIntegration.selectAvaliableInAllFileSystem(true);
		navigationMenu.clickOnIconMenu("Save changes made to integration option", "Actions");	
		if(!getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.waitForFilterTextBox();
		}
		sleepFor(2000);
	}
	
	@Test(priority = 95,enabled=true)
	public void EditWebIntegration() {
		String webInteName="User Team and FileSystem";
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Web Integration Options maintenance","Integration");
		navigationMenu.searchInFilter(webInteName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("webIntegrationOptionsDatatable");
		//navigationMenu.clickOnEditIcon();
		webIntegration.enterDecoratedLink("https://www.google.co.in/?q=[[System.LoggedOn.Team]] [[System.LoggedOn.FileSystem]]");
		//webIntegration.selectDocumentLink(true);
		navigationMenu.clickOnIcon("Save changes made to integration option", "Actions");
		navigationMenu.waitForFilterTextBox();
	}
	
	@Test(priority = 96,enabled=true)
	public void AccessLinkFromDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef="DocAccessLink",
		accRef="X1_"+generateRandomNumber();
		String currentFS = new HomePage(driver).getCurrentFileSystemName().split("-")[0].trim();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		sleepFor(2000);
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, accRef,getEnvVariable);
		
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIconMenu("Select Integration", "Integration");
		navigationMenu.clickOnIconMenu("User Team and FileSystem");
		try {
			windowHelper.switchToNewlyOpenedTab();
			String getText=webIntegration.getValueFromNewelyOpenedTab();
			//String getValueFromSearchBox=driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).getText();
			//System.out.println("getText "+getText);
			String [] seperateValues = getText.split(" ");
			AssertionHelper.verifyText(seperateValues[1],currentFS);
		} finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}	
	}
	
	@Test(priority = 97,enabled=true)
	public void DeleteWebIntegration() {
		getApplicationUrl();
		String webInteName="User Team and FileSystem";
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Web Integration Options maintenance","Integration");
		navigationMenu.searchInFilter(webInteName);
		sleepFor(1000);
		webIntegration.clickOnFilteredWebIntegration();
		navigationMenu.clickOnDeleteIcon();
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Web Integration Options maintenance","Integration");
		navigationMenu.searchInFilter(webInteName);
		//navigationMenu.waitForFilterTextBox();
		sleepFor(1000);
		String noRecordMsg = navigationMenu.getNoRecordsFoundMessage("webIntegrationOptionsDatatable");
		//AssertionHelper.verifyText(noRecordMsg, "No items available");
		
	}

}
