package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.BatchLetter;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.FolderFlagReference;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.Letters;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.MoreSearch;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminFolderSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitEmailTemplate;
import utils.ExtentReports.ExtentTestManager;

public class AUAT_Lastest_DemoPresentationCases extends TestBase {
	
	public FileSystemSection adminFS ;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	public LoginPage login;
	public DocumentToolsPage docTools;
	public AdminWorkflowSection workflow;
	public AdminFolderSection adminFolder;
	private Logger log = LoggerHelper.getLogger(AUAT_Automation1.class);
	
	private String batchIndexingDocRef="";
	private String batchIndexingDocRef2 = "";
	private String batchIndexingDocRef3 = "";
	private String batchIndexingDocRef4 = "";
	
	private String savedSearchInputName = "";
	private String homePageTile = "";
	private String parameMetroTile = "";
	private String teamMetroTile = "";
	private String roleMetroTile = "";
	private String fileSystemMetroTile = "";
	
	private String checkedOutDocRef = "";
	private String campaignName = "";
	private String tmpName = "";
	
	private String folder1Ref = "";
	private String folder1RefSubject = "";
	private String folder2RefSubject = "";
	private String listenerName = "";
	
	private String docRefPA1 = "";
	private String accRefPA1 = "";
	
	private String docRefPA2 = "";
	private String accRefPA2 = "";
	
	private String gimJobName = "";
	private String retentionPolicyName = "";
	private String folder2RefName = "";
	
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
		docTools = new DocumentToolsPage(driver);
		workflow = new AdminWorkflowSection(driver);
		adminFolder = new AdminFolderSection(driver);
	}
	
	@DataProvider(name="docreReferenceData")
	public Object[][] docreReferenceData() throws Exception{
		Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");	
		return formData;
	}
	
	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}
	
	//More Searching
	@DataProvider(name="moreSearchDateData")
    public Object[][] bankHolidaysData() throws Exception {
        Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","MoreSearch");
        return formData;
    }
	
	
	@Test(priority=1,enabled = false)				
	public void IndexUsingBatchIndexing() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		batchIndexingDocRef = "BD"+generateRandomNumber();
		String folder1Ref = "FF"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateToBatchIndex();
		new NavigationMenu(driver).searchInFilter("Batch");
		new DocumentToolsPage(driver).clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Index selected batch", "Action");
		sleepFor(3000);
		capture.clickOnAddIconInPreview();
		new CapturePage(driver).ExpandCoreFieldSectionOfBatchIndexing();
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		capture.enterDocRef(batchIndexingDocRef);
		
		new CapturePage(driver).ExpandGeneralFieldSectionOfBatchIndexing();
		
		boolean success =new CapturePage(driver).enterFolder1RefCapturePageAndSearch(folder1Ref,"Account Ref" );
		log.info("Flag value of success is "+success);
		if(!success) {
			new WindowHelper(driver).clickOkOnPopup();
			navigationMenu.waitForIcon("Cancel change");
		  new CapturePage(driver).enterFolder1Ref(folder1Ref);
		  new NavigationMenu(driver).clickOnSaveIcon();
		}
		navigationMenu.clickOnIcon("Index", "Actions");
		sleepFor(5000);
		new WaitHelper(driver).waitForElement(new CapturePage(driver).batchPageHeaderList, 15);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",batchIndexingDocRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
	}
	
	@Test(priority=2,enabled = true,dataProvider = "captureFormData")
	public void MoveBatchToDifferentFS(Map<String,String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String getCurrentFSName = ObjectReader.reader.getFileSystemName();

		String [] getFileSystem = map.get("memoFileSystem").split("-");
		
		getApplicationUrl();
		capture.navigateToBatchIndex();
		new NavigationMenu(driver).searchInFilter("Batch");
		new DocumentToolsPage(driver).clickOnFirstItemInList();
		String getBatchDescrption = driver.findElement(By.xpath("//table[@id='batchesTable']/tbody/tr[1]/td[2]")).getText();
		System.out.println("Batch Description "+getBatchDescrption);
		sleepFor(1000);
		navigationMenu.clickOnIcon("Change fileSystem for selected item", "Action");
		new WindowHelper(driver).waitForModalDialog("File System Selection");
		new CapturePage(driver).selectFileSystemForBatchIndexing(getFileSystem[1].trim());
		new WindowHelper(driver).clickOnModalFooterButton("Update");
		sleepFor(2000);		
		
		try {
			getApplicationUrl();
			new HomePage(driver).changeFileSystem(getFileSystem[0].trim());
			getApplicationUrl();
			capture.navigateToBatchIndex();
			new NavigationMenu(driver).searchInFilter(getBatchDescrption);
			new DocumentToolsPage(driver).clickOnFirstItemInList();
		} finally {
			new HomePage(driver).changeFileSystem(getCurrentFSName);
		}
	}
	
	@Test(priority=3,enabled = true)
	public void AddCommentsToBatchIndexingPageAndVerify() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String commentTxt = "AddedComment";
		
		batchIndexingDocRef2 = "BD"+generateRandomNumber();
		
		getApplicationUrl();
		capture.navigateToBatchIndex();
		new NavigationMenu(driver).searchInFilter("Batch");
		new DocumentToolsPage(driver).clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Index selected batch", "Action");
		sleepFor(3000);
		capture.clickOnAddIconInPreview();
		
		navigationMenu.clickOnIcon("Page Comments", "Document");
		new WindowHelper(driver).waitForModalDialog("Page comment");
		sendKeys(new CapturePage(driver).commentTxtForBatch, commentTxt, "Added comment "+commentTxt);
		new WindowHelper(driver).clickOnModalFooterButton("Add ");
		sleepFor(2000);
		boolean commentFlag = new VerificationHelper(driver).isElementDisplayedByEle(new CapturePage(driver).commentIconOnBatchIndexing);
		
		new CapturePage(driver).ExpandCoreFieldSectionOfBatchIndexing();
		new JavaScriptHelper(driver).scrollToBottom();
		capture.enterDocRef(batchIndexingDocRef2);
		navigationMenu.clickOnIcon("Index", "Actions");
		new WaitHelper(driver).waitForElementInvisible(driver, new CapturePage(driver).batchPageHeader, 15);
		
		AssertionHelper.verifyTrue(commentFlag, "Verifying comment icon after adding comment");

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",batchIndexingDocRef2 );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();

		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			sleepFor(6000);
			AssertionHelper.verifyTrue(new VerificationHelper(driver).isElementDisplayed(By.xpath("//div[@class='awesome-marker leaflet-zoom-animated']//i")), "Checking comment icon on view document");
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority=4,enabled = true)
	public void CanAddPagesToIndexDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		batchIndexingDocRef3 = "PD"+generateRandomNumber();
		
		getApplicationUrl();
		capture.navigateToBatchIndex();
		new NavigationMenu(driver).searchInFilter("More");
		new DocumentToolsPage(driver).clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Index selected batch", "Action");
		sleepFor(3000);
		capture.clickOnAddIconInPreview();
		
		new CapturePage(driver).ExpandCoreFieldSectionOfBatchIndexing();
		new JavaScriptHelper(driver).scrollToBottom();
		capture.enterDocRef(batchIndexingDocRef3);
		navigationMenu.clickOnIcon("Index", "Actions");
		sleepFor(4000);
		
		capture.clickOnAddIconInPreview();
		navigationMenu.clickOnIcon("Add pages to indexed document", "Document");
		new WindowHelper(driver).waitForModalDialog("Document");
		click(new CapturePage(driver).indexDocumentListRow, "clicking on document row");
		new WindowHelper(driver).clickOnModalFooterButton("Add ");
		sleepFor(2000);
		String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Add to indexed");
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",batchIndexingDocRef3 );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();

		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}		
	}
	
	@Test(priority=5,enabled = true)
	public void VerifyListOfIndexedDocumentsForSelectedBatch() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		batchIndexingDocRef4 = "ID"+generateRandomNumber();
		
		getApplicationUrl();
		capture.navigateToBatchIndex();
		new NavigationMenu(driver).searchInFilter("More");
		new DocumentToolsPage(driver).clickOnFirstItemInList();
		String getBatchDescription = driver.findElement(By.xpath("//table[@id='batchesTable']/tbody/tr[1]/td[2]")).getText();
		navigationMenu.clickOnIcon("Index selected batch", "Action");
		sleepFor(3000);
		capture.clickOnAddIconInPreview();
		
		new CapturePage(driver).ExpandCoreFieldSectionOfBatchIndexing();
		new JavaScriptHelper(driver).scrollToBottom();
		capture.enterDocRef(batchIndexingDocRef4);
		navigationMenu.clickOnIcon("Index", "Actions");
		sleepFor(4000);
		
		getApplicationUrl();
		capture.navigateToBatchIndex();
		new NavigationMenu(driver).searchInFilter(getBatchDescription);
		new DocumentToolsPage(driver).clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Index selected batch", "Action");
		sleepFor(3000);
		navigationMenu.clickOnIcon("List documents indexed from this batch", "Document");
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			sleepFor(2000);
			navigationMenu.searchInFilter(batchIndexingDocRef4);
			new DocumentToolsPage(driver).clickOnFirstItemInList();
			
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			
			capture.clickOnAddIconInPreview();
			new CapturePage(driver).ExpandCoreFieldSectionOfBatchIndexing();
			new JavaScriptHelper(driver).scrollToBottom();
			capture.enterDocRef(batchIndexingDocRef4);
			navigationMenu.clickOnIcon("Index", "Actions");	
			new WaitHelper(driver).waitForElement(new CapturePage(driver).batchPageHeaderList, 15);
			
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}				

	}
	
	@Test(priority=6,enabled = true)
	public void UnlockBatch() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		//test.log(Status.INFO ,"======="+getEnvVariable+"==========");
		try {
			getApplicationUrl();
			capture.navigateToBatchIndex();
			navigationMenu.searchInFilter("Batch");
			new DocumentToolsPage(driver).clickOnFirstItemInList();
			String getBatchDescription = driver.findElement(By.xpath("//table[@id='batchesTable']/tbody/tr[1]/td[2]")).getText();
			navigationMenu.clickOnIcon("Index selected batch", "Action");
			sleepFor(3000);

			
			new JavaScriptHelper(driver).openNewTabUsingJavascriptExecutor();
			new WindowHelper(driver).switchToNewlyOpenedTab();
			getApplicationUrl();
			//driver.get(ObjectReader.reader.getUrl());
			capture.navigateToBatchIndex();
			navigationMenu.searchInFilter(getBatchDescription);
			new DocumentToolsPage(driver).clickOnFirstItemInList();
			navigationMenu.clickOnIcon("Unlock selected batch", "Action");
			windowHelper.waitForPopup("Batch Unlocked");
			AssertionHelper.verifyTextContains(windowHelper.getPopupMessage(), "unlocked");
			windowHelper.clickOkOnPopup();		
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			getApplicationUrl();
		}
	}
	
	@Test(enabled = false,priority = 7,dataProvider = "moreSearchDateData")
	public void SearchDocumentUsingDateRange(Map<String,String> map) {
		test = ExtentTestManager.getTest();
		String monthYearFieldFrom=map.get("monthYearFrom"),
				   monthFieldFrom=map.get("monthFrom"),
				   dayFieldFrom=map.get("dayFrom"),
				   monthYearFieldTo=map.get("monthYearTo"),
				   monthFieldTo=map.get("monthTo"),
				   dayFieldTo=map.get("dayTo");
		
		
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		new MoreSearch(driver).EnterFromDate(monthYearFieldFrom,monthFieldFrom,dayFieldFrom);
		sleepFor(2000);
		new MoreSearch(driver).EnterToDate(monthYearFieldTo,monthFieldTo,dayFieldTo);
		capture.clickOnDocumentListBtn();
	}
	
	@Test(enabled = true,priority = 8)
	public void CreateSaveSearch() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		savedSearchInputName = "DocuemntTy"+generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
		new MoreSearch(driver).ClickOnSaveBtnOnMoreSearchPage();
		new WindowHelper(driver).waitForModalDialog("Save Search");
		new MoreSearch(driver).InputSearchName(savedSearchInputName);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Save Search");		//
		
		AssertionHelper.verifyText(getMsg, "Search saved successfully.");
		
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		new DocumentToolsPage(driver).clickOnDocumentListButton();
		String actDocType =  new DocumentToolsPage(driver).getAllDocumentTypeValuesToSet();
		AssertionHelper.verifyText(actDocType, "ScanDoc"); 
	}
	
	@Test(enabled = true,priority = 9)
	public void DeleteSavedSearch() {
		test = ExtentTestManager.getTest();
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();
		
		getApplicationUrl();
		//new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		navigationMenu.clickOnTab("Search"); // Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName,
					"Enetering in search =" + savedSearchInputName);
		String getMsg = driver.findElement(By.xpath("//li[@class='no-results']")).getText();
		AssertionHelper.verifyTextContains(getMsg, "No results matched");		
	}
	
	@Test(enabled = true,priority = 10)
	public void CreateHomePageTile() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		List<String> paramsList = new ArrayList<>();

		homePageTile = "NewTile"+generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("Default - General Default Document Type","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex("Default - General Default Document Type");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
		new MoreSearch(driver).CreateHomePageTile(homePageTile, "Current User", true, paramsList,"");
		
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(homePageTile);
		AssertionHelper.verifyTrue(tileFlag, "Verifing home page Tile");
	}
	
	@Test(enabled = true,priority = 11)
	public void DeleteHomePageTile() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).SelectEditTileButton();
		String getPopUpMsg = new HomePage(driver).DeleteMetroTile(homePageTile);
		test.log(Status.INFO, getPopUpMsg);
		sleepFor(3000);
		AssertionHelper.verifyTextContains(getPopUpMsg, "Do you want to delete associated saved search as well?");
		
		getApplicationUrl();
		boolean metroTile = new HomePage(driver).VerifyThatMetroTileNotPresent(homePageTile);
		AssertionHelper.verifyFalse(metroTile);	
	}
	
	@Test(enabled = true,priority = 12)
	public void CreateParameterizedHomePageTile(){
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		List<String> paramsList = new ArrayList<>();
		paramsList.add("F1-001R1");

		parameMetroTile = "PRTile"+generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("Default - General Default Document Type","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex("Default - General Default Document Type");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
		new MoreSearch(driver).CreateHomePageTile(parameMetroTile, "Current User", true, paramsList,"");
		
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(parameMetroTile);
		AssertionHelper.verifyTrue(tileFlag, "Verifing home page Tile");
		
		new HomePage(driver).ClickOnMetroTileParameterizedIcon(parameMetroTile,paramsList.get(0));
		String actFolderRf =  new DocumentToolsPage(driver).getAllFolder1RefToSet();
		AssertionHelper.verifyText(actFolderRf, "F1-001R1");
	}
	
	@Test(enabled = true, priority = 13)
	public void CreateTeamTile() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		String teamName = ObjectReader.reader.getLoggedInUsersTeam();
		List<String> paramsList = new ArrayList<>();

		teamMetroTile = "TMTile" + generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();

		new MoreSearch(driver).CreateHomePageTile(teamMetroTile, "Team", true, paramsList, teamName);

		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(teamMetroTile);
		AssertionHelper.verifyTrue(tileFlag, "Verifing home page Team Tile");

		//To verify that tile is not present for other team
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getSuperLoginId(),ObjectReader.reader.getSuperUserPassword());
		sleepFor(2000);
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());		
		
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(teamMetroTile);
		AssertionHelper.verifyFalse(tileFlag1, "Verifing home page Team Tile is not present");
	}
	
	@Test(enabled = true, priority = 14)
	public void VerifyThatForNonTeamMember() throws InterruptedException {
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getCaptureUser(),ObjectReader.reader.getPassword());
			sleepFor(2000);
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());		
			
			getApplicationUrl();
			boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(teamMetroTile);
			AssertionHelper.verifyFalse(tileFlag1, "Verifing home page Team Tile is not present");
		} finally {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword());
			sleepFor(4000);
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
	}
	
	@Test(enabled = true, priority = 15)
	public void HideSystemTiles() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).SelectEditTileButton();
		new HomePage(driver).HideMetroTile("Number of checked-out items.");
		sleepFor(5000);
		
		getApplicationUrl();
		boolean metroTile = new HomePage(driver).VerifyThatMetroTileNotPresent("Number of checked-out items.");
		AssertionHelper.verifyFalse(metroTile);	
	}
	
	@Test(enabled = true, priority = 16)
	public void ShowSystemTiles() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).SelectEditTileButton();
		new HomePage(driver).ShowMetroTile("Number of checked-out items.");
		sleepFor(5000);
		
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage("Number of checked-out items.");
		AssertionHelper.verifyTrue(tileFlag, "Verifing home page Tile");	
	}
	
	@Test(enabled = true,priority = 19,dataProvider = "captureFormData")
	public void CreateFSTile(Map<String,String> map){
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String getCurrentFSName = ObjectReader.reader.getFileSystemName();

		String [] getFileSystem = map.get("memoFileSystem").split("-");

		String roleName = ObjectReader.reader.getRoleName();
		List<String> paramsList = new ArrayList<>();

		fileSystemMetroTile = "FSTile" + generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();

		new MoreSearch(driver).CreateHomePageTile(fileSystemMetroTile, "File System", true, paramsList, roleName);
	
		getApplicationUrl();
		boolean tileFlag1 = new MoreSearch(driver).CheckTileIsPresentOnHomePage(fileSystemMetroTile);
		AssertionHelper.verifyTrue(tileFlag1, "Verifing home page file System tile is present");

		try {
			getApplicationUrl();
			new HomePage(driver).changeFileSystem(getFileSystem[0].trim());
			getApplicationUrl();
			boolean tileFlag = new HomePage(driver).VerifyThatMetroTileNotPresent(fileSystemMetroTile);
			AssertionHelper.verifyFalse(tileFlag, "Verifing home page File System Tile is not present");
		} finally {
			new HomePage(driver).changeFileSystem(getCurrentFSName);
		}
	}
	
	@Test(enabled = true,priority = 20)
	public void DeleteFSTile() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).SelectEditTileButton();
		String getPopUpMsg = new HomePage(driver).DeleteMetroTile(fileSystemMetroTile);
		test.log(Status.INFO, getPopUpMsg);
		sleepFor(5000);
		AssertionHelper.verifyTextContains(getPopUpMsg, "Do you want to delete associated saved search as well?");
		
		getApplicationUrl();
		boolean metroTile = new HomePage(driver).VerifyThatMetroTileNotPresent(fileSystemMetroTile);
		AssertionHelper.verifyFalse(metroTile);	
	}
	
	@Test(enabled = true,priority = 21)
	public void SearchDocumentUsingMoreSearch() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
		}else {
			docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
		}
		docTools.clickOnDocumentListButton();
		String actDocType =  docTools.getAllDocumentTypeValuesToSet();
		AssertionHelper.verifyText(actDocType, "ScanDoc"); 
	}
	
	@Test(priority = 22,enabled = true)
	public void GroupDocumentListByDifferentColumnAndResetGroups() {
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", "%%");
		capture.clickOnDocumentListBtn();
		new DocumentToolsPage(driver).GroupDocumentListByUsingColoumn("Document Type");
		
		AssertionHelper.verifyTrue(new DocumentToolsPage(driver).VerifyDocumentGroupByDocumentType("DEFAULT"), "Verifying default document type after group");
		AssertionHelper.verifyTrue(new DocumentToolsPage(driver).VerifyDocumentGroupByDocumentType("SCANDOC"), "Verifying default document type after group");
		
		//To Reset groups
		click(new NavigationMenu(driver).gearIconBtn, "clicking on Gear icon button");
		click(new DocumentToolsPage(driver).resetGroupsBtn, "Clicking on Reset group button");
	}
	
	@Test(enabled = true,priority = 23)
	public void GenerateLettersFromDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String docRef = "GP1"+generateRandomNumber();
		String folder1Ref = "DCP"+generateRandomNumber();
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,folder1Ref,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Produce letters from document list.", "Letter Production");
		new Letters(driver).GenerateLetter("Template", null, null);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","Template" );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
	}
	
	@Test(enabled = false,priority = 24)
	public void GenerateLetterThoroughMyIntray() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String docRef = "GP2"+generateRandomNumber();
		String folder1Ref = "AAP"+generateRandomNumber();
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,folder1Ref,getEnvVariable);

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);	
		new NavigationMenu(driver).searchInFilter(docRef);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Produce letters from document list.", "Letter Production");
		new Letters(driver).GenerateLetter("Template", null, null);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","Template" );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();	
	}
	
	@Test(enabled = true,priority = 26)
	public void CreateBatchLetter() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String folder1Ref = "LT"+generateRandomNumber();
		getApplicationUrl();
		new BatchLetter(driver).navigateToBatchLetter();
		
		new DropdownHelper(driver).selectFromAutocompleDD("description", new BatchLetter(driver).officeTemplateDD);
		
		new CapturePage(driver).selectRoutingTypeDD("To User", getEnvVariable);
		new CapturePage(driver).selectRouteToDD(ObjectReader.reader.getUserName(), getEnvVariable);
		
		boolean success =new CapturePage(driver).enterFolder1RefCapturePageAndSearch(folder1Ref,"Account Ref" );
		log.info("Flag value of success is "+success);
		if(!success) {
			new WindowHelper(driver).clickOkOnPopup();
			navigationMenu.waitForIcon("Cancel change");
		  new CapturePage(driver).enterFolder1Ref(folder1Ref);
		  new NavigationMenu(driver).clickOnSaveIcon();
		}

		new NavigationMenu(driver).clickOnAddIcon();
		
		new NavigationMenu(driver).clickOnIcon("Create Letter", "Action");
		new WindowHelper(driver).waitForPopup("Batch Letter Creation");
		String getSuccessCnt = new BatchLetter(driver).getSuccessCountOnBatchLetter.getText();
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		AssertionHelper.verifyTextContains(String.valueOf(getSuccessCnt), "1");
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Account Ref",folder1Ref );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
	}
	
	@Test(enabled = true,priority = 27)
	public void CheckoutDocument() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		checkedOutDocRef = "CHK1"+generateRandomNumber();
		String folder1Ref = "CO"+generateRandomNumber();
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, "Demo.docx", null, checkedOutDocRef,folder1Ref,getEnvVariable);
		
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", checkedOutDocRef);
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnEditIconUnderVersionControl();
		 sleepFor(1000);
		 docTools.clickOnEditDocumentPopup("Later");
		 sleepFor(2000);
		 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");
	}
	
	@Test(enabled = true,priority = 28)
	public void VerifyCheckedoutDocumentAndCheckInBack() throws InterruptedException {
		getApplicationUrl();
		 navigationMenu.clickOnCaptureTab();
		 navigationMenu.clickOnIcon("List all my checked-out documents", "Other");
		 sleepFor(2000);
		 navigationMenu.searchInFilter(checkedOutDocRef);
		 capture.clickOnFirstItemOfIntray();
		 
		 navigationMenu.clickOnIcon("Upload changes", "Actions");
		 //new WindowHelper(driver).waitForPopup("Undo Edit");    
		 String getPopUpMsg = new WindowHelper(driver).getPopupMessageClickOk("Update Document");
		//Are you sure you want to Update Document
		 
		 new WindowHelper(driver).waitForModalDialog("Update Document");
		 new DocumentToolsPage(driver).clickOnFileUploadOnUpdateDocument();
		 docTools.enterCommentsUpdateDocuments("CommentAuto");
		 sleepFor(1000);
		 String expVersion = docTools.getRevisionUploadDocument();		 
		 docTools.clickOnUpdateDocumentButton("Ok");				
	}
	
	@Test(priority = 29, enabled = true)
	public void TestAddNewCampaign() throws InterruptedException {
		campaignName = "Auto" + generateRandomNumber();
		getApplicationUrl();
		sleepFor(2000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance", "Workflow");
		navigationMenu.waitForAddIcon();
		navigationMenu.clickOnAddIcon();
		navigationMenu.waitForIcon("Cancel changes");
		workflow.addCampaignName(campaignName); 
		workflow.clickOnAddField();
		workflow.ClickOnRemoveAllField();
		workflow.selectAvailableFields("Document Type");
		workflow.clickOnAddField();
		// workflow.clickOnAddWeightedValueToField("Camp","Doc Ref","Text");
		workflow.AddDocumentTypeWeightedValues("ScanDoc");
		workflow.checkExcludeAllOthers();
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(campaignName);
			sleepFor(1000);
			String actualCampaign = workflow.getFilteredCampaignRowText();
			AssertionHelper.verifyText(actualCampaign, campaignName.toUpperCase());
		} catch (Exception e) {
			String actMessage = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyTextContains(actMessage, "Campaign name already exists.");
		}
	}
	
	@Test(priority = 30, enabled = true)
	public void AddCampaignToTeam() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.searchInFilter(ObjectReader.reader.getLoggedInUsersTeamId());
		new AdminUserSection(driver).clickOnFilteredTeam();
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new AdminUserSection(driver).selectUsersCampaign(campaignName.toUpperCase(),getEnvVariable);
		navigationMenu.clickOnSaveIcon();
		new WindowHelper(driver).waitForModalDialog("User Level Campaign List");
		click(new AdminWorkflowSection(driver).overwriteUsersCheckbox, "Clicking on users overwrite checkbox");
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		String getActualPopUpMsg = new WindowHelper(driver).getPopupMessageClickOk("User Level Campaign List");
		sleepFor(2000);
		AssertionHelper.verifyText(getActualPopUpMsg, "Are you sure you want all users updated to have campaign "+campaignName.toUpperCase()+"?");
		navigationMenu.waitForAddIcon();
	}
	
	@Test(priority = 31, enabled = true)
	public void VerifyCampaignFunctionality() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		//new HomePage(driver).refreshCurrentFileSystem();
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		capture.clickOnFirstItemOfIntray();
		
		workflow.isCampaignDropdownButtonShown();
		List<String> ddOptions =workflow.clickOnCampaignDropdownGetText(getEnvVariable);
		test.log(Status.INFO, "ddOptions==============="+ddOptions.toString());
		AssertionHelper.verifyTrue(ddOptions.contains(campaignName.toUpperCase()), "Checking whether dropdown contains campaign");
		
		//String actAccoutRef = navigationMenu.getColumnValueFromTable("Account Ref");
		String actAccoutRef = navigationMenu.getColumnValue("Document Type");
		AssertionHelper.verifyText(actAccoutRef, "ScanDoc");			
	}
	
	//It is use to delete campaign
	@Test(priority=32,enabled = true)
	public void TestDeleteCampaign1() throws InterruptedException {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);
		//workflow.clickOnFilteredCampaignRowText();
		navigationMenu.clickOnFilteredRow("priorityManagerTable");
		navigationMenu.clickOnIcon("Delete selected campaign", "Actions");
		sleepFor(2000);
		windowHelper.waitForPopup("Delete");
		new WindowHelper(driver).clickOnModalFooterButton("Yes");
		sleepFor(2000);
		windowHelper.waitForPopup("Delete");
		sleepFor(1000);
		new WindowHelper(driver).clickOnModalFooterButton("Yes");
		sleepFor(3000);
		navigationMenu.waitForAddIcon();
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);	
	}
	
	//creat email template
	@Test(priority = 33,enabled = false)
	public void CreateEmailTemplate() {
		tmpName = "Pend"+generateRandomNumber();
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Email Template","Templates");
		navigationMenu.clickOnAddIcon();
		new ToolkitEmailTemplate(driver).InputEmailTemplateName(tmpName);
		new ToolkitEmailTemplate(driver).SelectDocumentTypeFromDD("Default");
		new ToolkitEmailTemplate(driver).InputEmailSubject("Application Pending");
		new ToolkitEmailTemplate(driver).EnterEmailTemplateContent("Dear", "Account","Account Ref");
		new ToolkitEmailTemplate(driver).EnterEmailTemplateContent("You Application with reference", "Document","Doc Ref 2");
		new ToolkitEmailTemplate(driver).EnterEmailTemplateContent("has been pended owing to xyz reasons.", "", "");
		new ToolkitEmailTemplate(driver).EnterEmailTemplateContent("Please adhere to instructions", "", "");
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Document Types");
		sleepFor(1000);
		new ToolkitEmailTemplate(driver).SelectDocumentType("Default");
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	//Create folder1 reference & capture document 
	@Test(priority = 34,enabled = false)
	public void CaptureDocumentForSendEmail() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String forename = "Narendra"+generateRandomNumber();
		String surname = "Adani"+generateRandomNumber();
		String email = "amit.khambad@necsws.com";
		String docRef = "IFG"+generateRandomNumber();
		folder1Ref = "RDX"+generateThreeDigitRandomNumber();
		
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		new FolderFlagReference(driver).navigateToAddNewEntityPage(fold1entityName);
		new FolderFlagReference(driver).enterFolder1Ref(folder1Ref);
		new ActionHelper(driver).pressTab();
    	new JavaScriptHelper(driver).scrollToElement(new CapturePage(driver).folder1RefEmailAddress);
    	sleepFor(2000);
    	sendKeys(new CapturePage(driver).folder1RefEmailAddress, email, "Entering value as "+email);

    	new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
    	navigationMenu.clickOnNavTab("Person");
    	sendKeys(new FolderFlagReference(driver).surnameFieldInput, surname, "Entering Surname "+surname);
    	sleepFor(1000);
    	sendKeys(new FolderFlagReference(driver).forenameFieldInput, forename, "Entering forename "+forename);
    	new FolderFlagReference(driver).clickOnSaveIconWaitForItsInvisiblity();
    	
    	
    	getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,folder1Ref,getEnvVariable);
		
	}
	
	//It is use to send email using outbound
	@Test(priority = 35,enabled = false)
	public void SendEmailUsingOutbound() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		 getApplicationUrl();
		 capture.searchWithCriteria("Account Ref", folder1Ref);
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIconMenu("Generate Email", "Outbound");
		new IntrayToolsPage(driver).SendEmailUsingOutbound(tmpName, "SMTP1", getEnvVariable);
		String getActualEmailAddress = new IntrayToolsPage(driver).toEmailTextBox.getText();
		String getActualDisplayName = new IntrayToolsPage(driver).toDisplayName.getText();
		test.log(Status.INFO, "getActualEmailAddress====="+getActualEmailAddress);
		test.log(Status.INFO, "getActualDisplayName====="+getActualDisplayName);
		
		click(new IntrayToolsPage(driver).generateEmailOkButton, "Clicking on generate email ok button");
		new WaitHelper(driver).waitForElementInvisible(driver, new IntrayToolsPage(driver).busyEmailDialogueWhileSendingEmail, 20);
		sleepFor(30000);
		//new WindowHelper(driver).waitForPopup("Generate Email");
		String getPopUpMsg = new WindowHelper(driver).getPopupMessageClickOk("Generate Email");
		test.log(Status.INFO, "getPopUpMsg====="+getPopUpMsg);
	}
	
	//It is use to verify that after sending document, document gets captured
	@Test(priority = 36,enabled = false)
	public void SearchDocumentAndVerifyDocumentIsPresent() {
		 getApplicationUrl();
		 capture.searchWithCriteria("Account Ref", folder1Ref);
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		
		 String actAccoutRef = navigationMenu.getColumnValue("File Type");
		 AssertionHelper.verifyText(actAccoutRef, ".pdf");			
	}
	
	@Test(priority = 37,enabled = false)
	public void DeleteEmailTemplate() {
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Email Template","Templates");
		navigationMenu.searchInFilter(tmpName);
		navigationMenu.clickOnFilteredRowLink("emailTemplateTable");
		//navigationMenu.clickOnDeleteIcon();
		click(navigationMenu.btnDeleteIcon,"Clicking on delete icon");
		sleepFor(3000);
		String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Delete");
		AssertionHelper.verifyTextContains(getMsg, "Are you sure you want to delete");		
	}
	
	@Test(priority = 38,enabled = true)
	public void PublishDocumentOnPublicAccess() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		docRefPA1="PA_"+generateRandomNumber();
		accRefPA1="AC_"+generateRandomNumber();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRefPA1, accRefPA1,getEnvVariable);
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRefPA1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		docTools.clickOnCreateAndEditButtonOfPublicAccess();
		docTools.SelectRedactionTemplateForPA("RedTemp");
		new AdminUserSection(driver).clickOnUserNavTab("Viewer");
		docTools.PublishDocumentOnPublicAccess("Public Rendition", "Save and Publish Rendition");
	}
	
	@Test(priority = 39,enabled = true)
	public void VerifyThatDocumentIsPublishedOnPublicAccessAndViewIt() {
		getApplicationUrl();
		docTools.verifyDocumentOnPublicAccess(accRefPA1);
		docTools.ViewDocumentOnPublicAccess(accRefPA1);
	}
	
	@Test(priority = 40,   enabled = true)
	public void EditFileSystemAndUpdateRenditionType() {
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("File System maintenance", "File System");
		 navigationMenu.waitForAddIcon();
		 String fsName = home.getCurrentFileSystemName().split("-")[0].trim();
		 
		 navigationMenu.searchInFilter(fsName);
		 navigationMenu.clickOnFilteredRow(adminFS.fileSystemTableId);
		 navigationMenu.clickOnEditIcon();
		 navigationMenu.waitForIcon("Save changes");
		 
		 adminFS.ClickOnNavigationBar("Public Access");
		 adminFS.ClickOnAllowPublish(true);
		 adminFS.SelectPublicRenditionCreator("Advanced");
		 
		 try {
			 navigationMenu.clickOnSaveIcon();
			 navigationMenu.waitForAddIcon();
			 
			 home.refreshCurrentFileSystem();
			 sleepFor(3000);
		} catch (Exception e) {
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyText(actMessage, "No changes to save.");
		}
	}
	
	@Test(priority = 41,enabled = true)
	public void PublishOfficeBasedDocument() throws InterruptedException {
		String filepath = ObjectReader.reader.getTestDocFolderPath();	
		String filename1 = "Demo.docx";
		String filename2 = "Demo_Updated.docx";

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		docRefPA2="PP_"+generateRandomNumber();
		accRefPA2="AP_"+generateRandomNumber();
		capture.navigateAndCaptureDocWithParameters(null, filename1, null, docRefPA2, accRefPA2,getEnvVariable);
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRefPA2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		//docTools.clickOnCreateAndEditButtonOfPublicAccess();
		click(new DocumentToolsPage(driver).publicAccessBtn, "Clickng on public access button");
		navigationMenu.clickOnIconMenu("Create and edit rendition", "Rendition");
		
		new WindowHelper(driver).waitForPopup("Rendition");
		sleepFor(2000);
		new WindowHelper(driver).clickOnModalFooterButton("No");
		
		new WindowHelper(driver).waitForPopup("Create Rendition");
		sleepFor(2000);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		
		new WindowHelper(driver).waitForModalDialog("Save Rendition");
		sendKeys(new DocumentToolsPage(driver).renditionNameInput, "sample", "Entering the input");
		sleepFor(1000);
		new DropdownHelper(driver).selectFromAutocompleDD("Public Rendition",new DocumentToolsPage(driver).renditionTypeDD);
		click(new DocumentToolsPage(driver).chooseFileBtnPA, "Clicking on upload file documents check in");
		new CapturePage(driver).uploadFileAngGetFileName(filepath,filename2);
		sleepFor(2000);
		click(new DocumentToolsPage(driver).saveAndPublishRadioBtn, "Clicking on save and publish radio button");
		click(new DocumentToolsPage(driver).confirmYesBtn,"Clicked on confirm yes button");
		sleepFor(5000);
	}
	
	@Test(priority = 42,enabled = true)
	public void VerifyThatOfficeDocumentIsPublishedOnPublicAccessAndViewIt() {
		getApplicationUrl();
		docTools.verifyDocumentOnPublicAccess(accRefPA2);
		docTools.ViewDocumentOnPublicAccess(accRefPA2);
	}

	@Test(priority = 43,enabled = true)
	public void EditFileSystemAndUpdateRenditionTypeBackToOriginal() {
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("File System maintenance", "File System");
		 navigationMenu.waitForAddIcon();
		 String fsName = home.getCurrentFileSystemName().split("-")[0].trim();
		 
		 navigationMenu.searchInFilter(fsName);
		 navigationMenu.clickOnFilteredRow(adminFS.fileSystemTableId);
		 navigationMenu.clickOnEditIcon();
		 navigationMenu.waitForIcon("Save changes");
		 
		 adminFS.ClickOnNavigationBar("Public Access");
		 adminFS.ClickOnAllowPublish(true);
		 adminFS.SelectPublicRenditionCreator("TIFF to PDF");
		 
		 try {
			 navigationMenu.clickOnSaveIcon();
			 navigationMenu.waitForAddIcon();
			 
			 home.refreshCurrentFileSystem();
			 sleepFor(3000);
		} catch (Exception e) {
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyText(actMessage, "No changes to save.");
		}
	}

}
