package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;


import io.github.bonigarcia.wdm.Config;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ConfigReader;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
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
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Bundle.Bundling;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;

/*
 * Suite created by Sagar
 */

public class TestBundling_UAT extends TestBase  
{

	public DocumentToolsPage docTools ;
	private Logger log = LoggerHelper.getLogger(TestCapturePage.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	public AdminUserSection adminUser;
	public VerificationHelper verificationHelper;
	public ToolkitSystemSection toolkitSystem;
	public Bundling bundle;
	public LoginPage login;
	public FolderFlagReference folderPage ;
	public AdminUserSection adminUserSection;
	public AlertHelper alertHelper;
	public WindowHelper windowHelper;
	public WaitHelper waitHelper;
	public AdminDocumentSection adminDocument;
	public IntrayToolsPage intrayTools;
	
	private String superAdminUserName = "SAU"+generateRandomNumber();
	private String userNew="User_N"+generateRandomNumber();
	private String bundlingLicenseKey = "{1D731834-D7E5-43D7-9077-929D34FB8B5Y}";
	private String bundleName ="bun-"+generateRandomNumber();
	private String bundleName1 ="bund-"+generateRandomNumber();
	private String editedBundleName = bundleName+"-Edited";
	private String routingType="To User";
	private String docRef1 = "Super1"+generateRandomNumber();
	private String docRef2 = "Super2"+generateRandomNumber();
	private String docRef3 = "Super3"+generateRandomNumber();
	private String indexingDocRef1 = "indexing"+docRef1;
	private String indexingDocRef2 = "indexing"+docRef2;
	private String routingTo;
	private String documentType;
	
	List<String> getAllusers = new ArrayList<String>();
	List<String> getAllTeams = new ArrayList<String>();
	List<String> getAllDocTypes = new ArrayList<String>();
 
	@BeforeClass
	public void setupClass()  
	{
		docTools = new DocumentToolsPage(driver);
		capture = new CapturePage(driver);
		home= new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		xls = new ExcelReader();
		intrayTools= new IntrayToolsPage(driver);
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		folderPage = new FolderFlagReference(driver);
		verificationHelper = new VerificationHelper(driver);
		adminUser = new AdminUserSection(driver);
		toolkitSystem= new ToolkitSystemSection(driver);
		bundle = new Bundling(driver);
	}
	
	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}
	
	@DataProvider(name="docreReferenceData")
	public Object[][] docreReferenceData() throws Exception
	{
		Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");	
		return formData;
	}
	
	@DataProvider(name="addDocumentType")
	public Object[][] addDocumentType() throws Exception
	{
		Object[][] formData = xls.readExcelToMap( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","DocumentTypeData");

		return formData;
	}
	
	@Test(priority=17,enabled = true)
	public void TestUserCanAccessBundling() throws InterruptedException 
	{

//		1. Login through the admin user give bundle rights to test user and verify the bundling can be accessed through the test user 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
 

//	    Set Bundle Licence key
		toolkitSystem.checkConfigValue("BundleLicenceKey","{1D731834-D7E5-43D7-9077-929D34FB8B5Y}");
		
		getApplicationUrl();
	    sleepFor(4000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
//		waitHelper.waitForElement(indexDocumentBtn, 10);

		AssertionHelper.verifyTrue(bundle.isShowCompletedBundleButtonPresent(),"Assertion to check if show completed button present");

	}
	
	@Test(priority=18,enabled = true)
	public void TestUserCanCreateABundle() throws InterruptedException 
	{

//		2. Create a new bundle and verify the bundle is created 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
	    navigationMenu.clickOnAddIcon();
	    sleepFor(1000);	
		sendKeys(bundle.bundleNameField, bundleName, "Entering bundle name");
		navigationMenu.clickOnIcon("Save changes made to bundle list", "Actions");
	     navigationMenu.waitForAddIcon();	
	}
	
	@Test(priority=19,enabled = true)
	public void TestUserCanEditCreatedBundle() throws InterruptedException {

//		3. Edit bundle and verify the bundle is Edited 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
// 	    Verify the bundle is added 
		getApplicationUrl();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.searchInFilter(bundleName);													//User_N147 //User_N5341
		
								   
		WebElement filteredElement = navigationMenu.getfilteredRowElement("bundleListDataTable");
		String filteredBundle =  filteredElement.getText();
		AssertionHelper.verifyText(filteredBundle, bundleName);
		
		sleepFor(2000);	
	    navigationMenu.clickOnFilteredRow("bundleListDataTable");
		navigationMenu.clickOnIcon("Edit selected bundle list ", "Actions");
		sendKeys(bundle.bundleNameField, editedBundleName, "Entering bundle name");
		navigationMenu.clickOnIcon("Save changes made to bundle list", "Actions");
	    navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=20,enabled = true,dataProvider = "captureFormData")
	public void TestUserCanAddDocumentsToABundle(Map<String, String> map) throws InterruptedException 
	{
		String routingTo=superAdminUserName;
		String documentType=map.get("DocumentType");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		//Verify the bundle is edited 
		getApplicationUrl();
	    sleepFor(4000);	
		navigationMenu.clickOnIcon("Bundles", "Search");

		navigationMenu.searchInFilter(editedBundleName);			// bundleName  editedBundleName
	    sleepFor(2000);	

								   
	    WebElement filteredElement1 = navigationMenu.getfilteredRowElement("bundleListDataTable");
		String filteredBundle1 =  filteredElement1.getText();
		AssertionHelper.verifyText(filteredBundle1, editedBundleName);
	

		
		//Index document under bundle
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef1, null,getEnvVariable);		
		getApplicationUrl();	
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef2, null,getEnvVariable);		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef3, null,getEnvVariable);			
		
		//adding captured document to bundle
		getApplicationUrl();
		bundle.addDocumentToBundle(editedBundleName, docRef1);		// bundleName
	    sleepFor(2000);	
		getApplicationUrl();
		bundle.addDocumentToBundle(editedBundleName, docRef2);		// bundleName	
	    sleepFor(2000);	
		getApplicationUrl();
		bundle.addDocumentToBundle(editedBundleName, docRef3);		// bundleName	"bundleDemo-N4895"
	    sleepFor(2000);		
	}
	
	@Test(priority=22,enabled = true)
	public void TestUserCanOpenABundle() throws InterruptedException 
	{
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
	    sleepFor(4000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.searchInFilter(editedBundleName);			// bundleName
        navigationMenu.clickOnFilteredRow("bundleListDataTable");
		navigationMenu.clickOnIcon("Open Bundle Documents", "Actions");		
	    sleepFor(1000);	

//		verification		newly added --> to check the title of the opened bundle
	    String actBundleTitle = navigationMenu.navBarTitleLbl.getText();
	    String expBundleTitle = "Bundle "+editedBundleName+" item(s)";
		AssertionHelper.verifyText(expBundleTitle, actBundleTitle);
	}
	
				   
	@Test(priority=23,enabled = true,dataProvider = "captureFormData")
	public void TestUserCanRemoveDocumentsFromABundle(Map<String, String> map) throws InterruptedException {
		routingTo=superAdminUserName;
		documentType=map.get("DocumentType");
		
//		Remove documents from bundle
							   
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.searchInFilter(editedBundleName);
        navigationMenu.clickOnFilteredRow("bundleListDataTable");
		navigationMenu.clickOnIcon("Open Bundle Documents", "Actions");		
												  
		
//		remove The Item Or Document From The Bundle
																   
		navigationMenu.searchInFilter(docRef1);
		sleepFor(3000);
		navigationMenu.clickOnFilteredRow("bundleDocumentResultDataTable");
		navigationMenu.clickOnIcon("Remove item from document list", "Action");	
										 
				 
																	 
		
		new WindowHelper(driver).waitForPopup("Remove the selected item");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);

//		verify the item or document is removed from the bundle
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.searchInFilter(editedBundleName);
        navigationMenu.clickOnFilteredRow("bundleListDataTable"); 
		navigationMenu.clickOnIcon("Open Bundle Documents", "Actions");		
		navigationMenu.searchInFilter(docRef1);

		String verifyEmptyTableInTheBundle = bundle.verifyEmptyTableInTheBundle.getText();
		String actMsgInEmptyTableInTheBundle = "No matching records found";
		AssertionHelper.verifyText(verifyEmptyTableInTheBundle, actMsgInEmptyTableInTheBundle);
	}

	@Test(priority=24,enabled = true,dataProvider = "captureFormData")
	public void TestUserCanAddIndexingCriteria(Map<String, String> map) throws InterruptedException {
		routingTo=superAdminUserName;
		documentType=map.get("DocumentType");

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");

		navigationMenu.searchInFilter(editedBundleName);
        navigationMenu.clickOnFilteredRow("bundleListDataTable");
        navigationMenu.clickOnIcon("Add Indexing Criteria", "Actions");		
		capture.enterDocRef(indexingDocRef2);
		navigationMenu.clickOnIcon("Add Indexing Criteria", "Actions");		
	
		new WindowHelper(driver).waitForPopup("Bundle List Indexing Criteria");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
		
//		verification		newly added --> indexing criteria value should be prepopulated
		getApplicationUrl();
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.searchInFilter(editedBundleName);
        navigationMenu.clickOnFilteredRow("bundleListDataTable");
        navigationMenu.clickOnIcon("Add Indexing Criteria", "Actions");		
		navigationMenu.waitForIconWithDataButton("Save", "Actions");
        AssertionHelper.verifyText(capture.docRefTxt.getAttribute("value"), indexingDocRef2);
	}	
	
	@Test(priority=25,enabled = true)
	public void TestDocumentsInABundleCanBeCollatedIntoAPdf () throws InterruptedException 
	{
 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.searchInFilter(editedBundleName);

//		collate the created bundle
		navigationMenu.clickOnFilteredRow("bundleListDataTable");
		navigationMenu.clickOnIcon("Collate bundle and index", "Bundle");	

		bundle.collateIncludePageNumberInFooter("Left");
		bundle.collateIncludeDocumentDateInFooter("Left");
		bundle.clickOncollateBundleOkBtn();
		
		new WindowHelper(driver).waitForPopup("Collate");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		
		System.out.println("get message ==========================" + getMsg);
		
//		Verify the bundle is collated --> check complete in the status column
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
		navigationMenu.clickOnIcon("Show Completed", "Bundle");	
		navigationMenu.searchInFilter(editedBundleName);
        navigationMenu.clickOnFilteredRow("bundleListDataTable");
 		AssertionHelper.verifyText("Complete", navigationMenu.getColumnValueFromTable("Status") );
	}
	
	@Test(priority=25,enabled = true,dataProvider = "captureFormData")
	public void TestViewCollatedBundleDocument (Map<String, String> map) throws InterruptedException 
	{
 
		//	view document
			getApplicationUrl();
		    capture.searchWithCriteria("Doc Ref", indexingDocRef2);
		    capture.clickOnDocumentListBtn();
		    capture.clickOnFirstItemOfIntray();
		    navigationMenu.clickOnIcon("View document", "Document");
		    try {
		        sleepFor(4000);
		        new WindowHelper(driver).switchToNewlyOpenedTab();
		        waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		        sleepFor(6000);
				AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
						"Verifying text name of viewer is displayed");
		   } finally {
		        new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		   }
			 
	}
	
	@Test(priority=26,enabled = true)
	public void TestUserCanDeleteBundle() throws InterruptedException 
	{
//		Create a new bundle and verify the bundle is created 
								   
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
	    sleepFor(2000);	
		navigationMenu.clickOnIcon("Bundles", "Search");
	    navigationMenu.clickOnAddIcon();
	    sleepFor(1000);	
		sendKeys(bundle.bundleNameField, bundleName1, "Entering bundle name");
		navigationMenu.clickOnIcon("Save changes made to bundle list", "Actions");
	    sleepFor(4000);
		
//		4. Delete bundle and verify the bundle is Deleted 
		getApplicationUrl();
	    sleepFor(4000);	
		navigationMenu.clickOnIcon("Bundles", "Search");

		navigationMenu.searchInFilter(bundleName1);		
        navigationMenu.clickOnFilteredRow("bundleListDataTable");
		navigationMenu.clickOnIcon("Delete selected bundle list", "Actions");
		
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
      
//		Verify the bundle is deleted 
		getApplicationUrl();
	    sleepFor(4000);	
		navigationMenu.clickOnIcon("Bundles", "Search");

		navigationMenu.searchInFilter(bundleName1);
		String getTxt = navigationMenu.getNoRecordsFoundMessage("bundleListDataTable");
		AssertionHelper.verifyTextContains(getTxt, "No matching records found");
	}
												  
																				
}
  
