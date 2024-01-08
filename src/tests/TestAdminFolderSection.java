package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminFolderSection;
import utils.ExtentReports.ExtentTestManager;

public class TestAdminFolderSection extends TestBase {
	public AdminFolderSection adminFolder;
	private Logger log = LoggerHelper.getLogger(TestAdminFolderSection.class);
	public IntrayToolsPage intrayTools ;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	public LoginPage login;
	private String folder1RefName;
	private String folder2RefName;
	private String folder3RefName;
	
	@BeforeClass
	public void setupClass()  {
		adminFolder = new AdminFolderSection(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		windowHelper=new WindowHelper(driver);
		xls = new ExcelReader();
		dropdownHelper=new DropdownHelper(driver);
		login = new LoginPage(driver);
		folder1RefName=ObjectReader.reader.getFolder1RefName();
		folder2RefName=ObjectReader.reader.getFolder2RefName();
		folder3RefName=ObjectReader.reader.getFolder3RefName();
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
	
	@Test(priority=601,enabled = true)
	public void TestDataDownloadFolder1RefCSV() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String expMsg = "Save successful.";
		String getFileName=ObjectReader.reader.getAccountImportFileName();
		adminFolder.navigateToDataImport();
		adminFolder.selectEntityDropdownDataImport(folder1RefName,getEnvVariable);
		adminFolder.enterValueInFilePathDataImport("\\\\MUM-LAP-1086\\Users\\amit.khambad\\Pictures\\DataImport\\"+getFileName);
		adminFolder.enterFileTypeAndSeparation("Csv", ",",getEnvVariable);
		adminFolder.selectFieldsFromDataImport("Folder1_Ref","Surname","Forename");
		String actMsg = adminFolder.clickOnSaveFileSchema();
		adminFolder.ClickOnImport();
		AssertionHelper.verifyText(actMsg, expMsg);
		log.info("Job Index is "+adminFolder.getJobIndexNumber());
	}
	
	@Test(priority=602,enabled = true)
	public void TestDataDownloadFolder2RefCSV() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String expMsg = "Save successful.",
				fsName="RZ1";
		String getFileName=ObjectReader.reader.getMiscImportFileName();
		getApplicationUrl();
		adminFolder.navigateToDataImport();
		adminFolder.selectEntityDropdownDataImport(folder2RefName,getEnvVariable);
		adminFolder.enterValueInFilePathDataImport("\\\\MUM-LAP-1086\\Users\\amit.khambad\\Pictures\\DataImport\\"+getFileName);
		adminFolder.enterFileTypeAndSeparation("Csv", ",",getEnvVariable);
		adminFolder.selectFieldsFromDataImport("Folder2_Ref","Surname","Forename");
		String actMsg = adminFolder.clickOnSaveFileSchema();
		//adminFolder.ClickOnImport();
		navigationMenu.clickOnIcon("Run import now", "Import");
		
		String getActualSuccessNumber = adminFolder.GetNumberOfSuccessAfterImport();
		AssertionHelper.verifyText(getActualSuccessNumber, String.valueOf(1));
		click(adminFolder.downloadBtn, "Clicking on download button");
		sleepFor(2000);
		windowHelper.clickOkOnPopup();
		
		boolean istiffDocExist = navigationMenu.isDownloadedFileExist("FlexibleImpor"+".log");
		AssertionHelper.verifyText(actMsg, expMsg);
		String jobIndex = adminFolder.getJobIndexNumber();
		log.info("Job Index is "+jobIndex);
	
		//adminFolder.runBatchFile("3", fsName);
		//new CapturePage(driver).enterFolder1RefEntitySearchPage("UNI1");
	}
	
	@Test(priority=603,enabled = false)
	public void TestDataDownloadFolder3RefCSV() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String expMsg = "Save successful.";
		getApplicationUrl();
		adminFolder.navigateToDataImport();
		adminFolder.selectEntityDropdownDataImport("Prop",getEnvVariable);
		adminFolder.enterValueInFilePathDataImport("\\\\MUM-LAP-1086\\Users\\amit.khambad\\Pictures\\DataImport\\DT4.csv");
		adminFolder.enterFileTypeAndSeparation("Csv", ",",getEnvVariable);
		adminFolder.selectFieldsFromDataImport("Folder3_Ref","Surname","Forename",getEnvVariable);
		String actMsg = adminFolder.clickOnSaveFileSchema();
		AssertionHelper.verifyText(actMsg, expMsg);
		log.info("Job Index is "+adminFolder.getJobIndexNumber());
	}
	
	@Test(priority=604,enabled = true)				//true
	public void TestDataDownloadFolder1RefFixedLength() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String expMsg = "Save successful.",
				filePath="\\\\MUM-LAP-1086\\Users\\amit.khambad\\Pictures\\DataImport\\Fixleng52.txt";
		List<String> fields = new ArrayList<String> (Arrays.asList("File_System",
				"Folder1_Ref","Forename"));
		List<Integer> len = new ArrayList<Integer> (Arrays.asList(2,3,6));
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		adminFolder.navigateToDataImport();
		adminFolder.selectEntityDropdownDataImport(folder1RefName,getEnvVariable);
		adminFolder.enterValueInFilePathDataImport(filePath);
		adminFolder.enterFileTypeAndSeparation("FixedLength", "",getEnvVariable);
		adminFolder.selectFieldsWithLengthFromDataImport(fields, len);;
		String actMsg = adminFolder.clickOnSaveFileSchema();
		adminFolder.ClickOnImport();
		AssertionHelper.verifyText(actMsg, expMsg);
		log.info("Job Index is "+adminFolder.getJobIndexNumber());
	}
	
	@Test(priority=605,enabled = true)			//true
	public void TestDataDownloadFolder2RefFixedLength() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String expMsg = "Save successful.",
				filePath="\\\\MUM-LAP-1086\\Users\\amit.khambad\\Pictures\\DataImport\\Fixleng53.txt";
		List<String> fields = new ArrayList<String> (Arrays.asList("File_System",
				"Folder2_Ref","Forename"));
		List<Integer> len = new ArrayList<Integer> (Arrays.asList(2,3,6));
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		adminFolder.navigateToDataImport();
		adminFolder.selectEntityDropdownDataImport(folder2RefName,getEnvVariable);
		adminFolder.enterValueInFilePathDataImport(filePath);
		adminFolder.enterFileTypeAndSeparation("FixedLength", "",getEnvVariable);
		adminFolder.selectFieldsWithLengthFromDataImport(fields, len);;
		String actMsg = adminFolder.clickOnSaveFileSchema();
		adminFolder.ClickOnImport();
		AssertionHelper.verifyText(actMsg, expMsg);
		log.info("Job Index is "+adminFolder.getJobIndexNumber());
	}
	
	@Test(priority=606,enabled = false)
	public void TestDataDownloadFolder3RefFixedLength() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String expMsg = "Save successful.",
				filePath="\\\\MUM-LAP-1086\\Users\\amit.khambad\\Pictures\\DataImport\\Fixleng.txt";
		List<String> fields = new ArrayList<String> (Arrays.asList("File_System",
				"Folder3_Ref","Forename"));
		List<Integer> len = new ArrayList<Integer> (Arrays.asList(2,3,6));
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		adminFolder.navigateToDataImport();
		adminFolder.selectEntityDropdownDataImport("Prop",getEnvVariable);
		adminFolder.enterValueInFilePathDataImport(filePath);
		adminFolder.enterFileTypeAndSeparation("FixedLength", "",getEnvVariable);
		adminFolder.selectFieldsWithLengthFromDataImport(fields, len);;
		String actMsg = adminFolder.clickOnSaveFileSchema();
		AssertionHelper.verifyText(actMsg, expMsg);
		log.info("Job Index is "+adminFolder.getJobIndexNumber());
	}
	
	

}


/*
* - enabled false for 602
*/