package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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
import main.EDRM.hybridFramework.pageObject.FolderFlagReference;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import utils.ExtentReports.ExtentTestManager;



public class ATestRegressionCapture extends TestBase {
	public DocumentToolsPage docTools ;
	private Logger log = LoggerHelper.getLogger(TestCapturePage.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	AlertHelper alertHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	AdminDocumentSection adminDocument;
	IntrayToolsPage intrayTools;
	public LoginPage login;
	public FolderFlagReference folderPage ;
	
	private String folder1Prefix;
	private String folder2Prefix;
	private String folder3Prefix;
	
	List<String> getAllusers = new ArrayList<String>();
	List<String> getAllTeams = new ArrayList<String>();
	List<String> getAllDocTypes = new ArrayList<String>();
	
	private String superAdminUserName;
	private String documentTypeId;
	private String FSName;
	
	private String folder1Ref1;
	
	@BeforeClass
	public void setupClass()  {
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
	
	@Test(priority=1,enabled = true, dataProvider = "captureFormData")				//Case 7
	public void CaptureMultipleDocument(Map<String, String> map) {
		String filename1 = "file2.tif";
		String routingType="To Team",
				routingTo=map.get("TeamName"),
				documentType=map.get("DocumentType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				docRef= map.get("DocRef")+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		sleepFor(2000);
		capture.clickOnFileUploadIcon();
		String ActualFileName1 = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename1);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		capture.clickOnIndexDocument();
		
		try {
			waitHelper.waitForElement(new CapturePage(driver).successullyIndexMsg,10);
			new CapturePage(driver).clickOkOnSuccesfullPopup();
			navigationMenu.clickOnHomePageIcon();
		} catch (Exception e) {
			navigationMenu.clickOnHomePageIcon();
		}
	}
	
	@Test(priority=2,enabled = true)
	public void GetAllDocTypesTeamsAndUsers() {
		getApplicationUrl();
		//navigationMenu.clickOnTab("Administration");
		//navigationMenu.clickOnIconMenu("Select Document Type category to admin", "Document");
		//navigationMenu.clickOnIconMenu("Active document types");
		getAllDocTypes = new AdminDocumentSection(driver).getExpectedActiveDocTypes();
		System.out.println("getAllDocTypes======== "+getAllDocTypes);
		sleepFor(1000);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");  
		getAllusers = new AdminUserSection(driver).getAllUsersFromUsersPage();
		System.out.println("getAllusers============== "+getAllusers);
		sleepFor(1000);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		getAllTeams = new AdminUserSection(driver).getAllTeams();
		System.out.println("getAllTeams================= "+getAllTeams);
		sleepFor(1000);
	}
	
	@Test(priority=3,enabled = true)
	public void VerifyCapturePageContents() {				//Case 5
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		
		//List<String> items = new AdminDocumentSection(driver).getExpectedActiveDocTypes();
		//System.out.println("items====================="+items);
		
		List<String> ddCapturePageOptions = new DropdownHelper(driver).getAllOptionsFromDropdown("DocumentTypeId",getEnvVariable);
		List<String> newItemList = new ArrayList<String>();

		String [] item = ddCapturePageOptions.toArray(new String[ddCapturePageOptions.size()]);
		for(String str:item) {
			String [] sp1=str.split("-");
			newItemList.add(sp1[0].trim());
		}
		
		
		List<String> ddRoutingType = new DropdownHelper(driver).getAllOptionsFromDropdown("RoutingType",getEnvVariable);
		System.out.println("RoutingType======= "+ddRoutingType);
		
		List<String> ddMailOptions = new DropdownHelper(driver).getAllOptionsFromDropdown("MailOptionId",getEnvVariable);
		System.out.println("Mail Options============= "+ddMailOptions);
		
		capture.selectRoutingTypeDD("To User",getEnvVariable);
		
		List<String> ddRoutingToUser = new DropdownHelper(driver).getAllOptionsFromDropdown("RouteTo",getEnvVariable);
		System.out.println("RoutingToUser=================="+ddRoutingToUser);
		
		//System.out.println("newItemList "+newItemList);
//		for(String option:items) {
//			if(!option.equals("Memo")) {
//				AssertionHelper.verifyTrue(newItemList.contains(option),"Assertion for checking drop down option present "+option);
//			}
//		}
		
		for(String routingElementUser : ddRoutingToUser) {
			AssertionHelper.verifyTrue(getAllusers.contains(routingElementUser),"Verifying the routing type from dropdown"+routingElementUser);

		}
		capture.selectRouteToDD("(none)",getEnvVariable);
		
		capture.selectRoutingTypeDD("To Team",getEnvVariable);
		List<String> ddRoutingToTeam = new DropdownHelper(driver).getAllOptionsFromDropdown("RouteTo",getEnvVariable);
		System.out.println("ddRoutingToTeam=================="+ddRoutingToTeam);
		
		for(String routingElementTeam : ddRoutingToTeam) {
			AssertionHelper.verifyTrue(getAllTeams.contains(routingElementTeam),"Verifying the routing type from dropdown"+routingElementTeam);

		}
		capture.selectRouteToDD("(none)",getEnvVariable);
		navigationMenu.clickOnCancelIcon();
		sleepFor(2000);
	}
	
	@Test(priority=4,enabled = true, dataProvider = "captureFormData")					//Case 10
	public void AddMultipleIndexingDetails(Map<String, String> map) throws InterruptedException {
		String routingType="To Team",
				routingTo=map.get("TeamName"),
				documentType=map.get("DocumentType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				docRef= map.get("DocRef")+generateRandomNumber(),
				folder1RefValue = map.get("Folder1RefValue");
		
		folder1Ref1="N1_"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new AlertHelper(driver).acceptAlertIfPresent();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		
		capture.enterFolder1RefCapturePageAndSearch(folder1RefValue, "Account Ref");
		
		new WaitHelper(driver).waitForElement(new CapturePage(driver).addNewSetOfIndexingDetails, 8);
		click(new CapturePage(driver).addNewSetOfIndexingDetails, "Clicking on Add button on New set of indexing details");
		
		new WaitHelper(driver).waitForElement(new CapturePage(driver).additionalFolder1Ref, 10);
		
		if(folder1Ref1!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearchForAdditionalIndexing(folder1Ref1, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
				capture.enterFolder1Ref(folder1Ref1);
				capture.clickOnSaveButton();
				waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
		}
		
		capture.clickOnIndexDocument();
		
		try {
			waitHelper.waitForElement(new CapturePage(driver).successullyIndexMsg,10);
			new CapturePage(driver).clickOkOnSuccesfullPopup();
			navigationMenu.clickOnHomePageIcon();
		} catch (Exception e) {
			navigationMenu.clickOnHomePageIcon();
		}
		
	}
	
	@Test(priority=5,enabled = true, dataProvider = "captureFormData")					//Case 11
	public void RemoveMultipleIndexingDetailsFromCapturePage(Map<String, String> map) throws InterruptedException {
		String routingType="To Team",
				routingTo=map.get("TeamName"),
				documentType=map.get("DocumentType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				docRef= map.get("DocRef")+generateRandomNumber(),
				folder1RefValue = map.get("Folder1RefValue");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		
		capture.enterFolder1RefCapturePageAndSearch(folder1RefValue, "Account Ref");
		
		new WaitHelper(driver).waitForElement(new CapturePage(driver).addNewSetOfIndexingDetails, 8);
		click(new CapturePage(driver).addNewSetOfIndexingDetails, "Clicking on Add button on New set of indexing details");
		
		new WaitHelper(driver).waitForElement(new CapturePage(driver).additionalFolder1Ref, 10);
		
		if(folder1Ref1!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearchForAdditionalIndexing(folder1Ref1, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
				capture.enterFolder1Ref(folder1Ref1);
				capture.clickOnSaveButton();
				waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
		}
		
		new WaitHelper(driver).waitForElement(new CapturePage(driver).removeIndexingDetailsBtn, 10);
		click(new CapturePage(driver).removeIndexingDetailsBtn, "Clicking on remove indexing details btn");
		new NavigationMenu(driver).clickOnIcon("Cancel capture", "Actions");
		new AlertHelper(driver).acceptAlertIfPresent();
		
	}
	
	@Test(enabled = true,priority = 6)					//Case 15
	public void VerifyThatReceivedDateOnCapturPageNonEditable() {				
		Map<String, String> values = new HashMap<String, String>();
		values.put("DisableRecDate", "1");
		
		Map<String, String> originalValue = new HashMap<String, String>();
		originalValue.put("DisableRecDate", "0");
		try {
			getApplicationUrl();
			navigationMenu.clickOnTab("ToolKit");
			navigationMenu.clickOnIcon("Configuration settings","System");
			new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
			
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(3000);
			
			getApplicationUrl();
			capture = navigationMenu.clickOnCaptureTab();
			capture.clickOnDocumentCaptureBtn();
			
			boolean disableRecDate = new VerificationHelper(driver).isElementEnabled(new CapturePage(driver).disableRecDate);
			System.out.println("disableRecDate "+disableRecDate);		
			AssertionHelper.verifyFalse(disableRecDate);
		} finally {
			//Updating the config value to original once
			getApplicationUrl();
			navigationMenu.clickOnTab("ToolKit");
			navigationMenu.clickOnIcon("Configuration settings","System");
			new ToolkitCaseManagement(driver).changeTheConfigurationValues(originalValue);
			
			new HomePage(driver).refreshCurrentFileSystem();
			sleepFor(3000);	
		}
	}

	@Test(enabled = true,priority = 7)					//Pre requisite for Case 13
	public void UpdateUserSecurityAndDocumentSecurity() {
    	try {
    		String getEnvVariable = System.getProperty("propertyName");
    		System.out.println("getEnvVariable===================="+getEnvVariable); 
        	
        	 getApplicationUrl();
             navigationMenu.clickOnTab("Administration");
             navigationMenu.clickOnIcon("User maintenance", "User");
             navigationMenu.searchInFilter("OfficeConnectUser");
             sleepFor(1000);
             new AdminUserSection(driver).clickOnFilteredUser();
             navigationMenu.clickOnEditIcon();
             new AdminUserSection(driver).enterSecurityLevel(String.valueOf(1));
             sleepFor(2000);
             if(getEnvVariable.equals("Enterprise_Sp1s")) {
            	 navigationMenu.clickOnSaveIcon();
             }else {
            	 navigationMenu.clickOnSaveIconForUser(); 
             }
             
             waitHelper.waitForElement(navigationMenu.addIconBtn, 20);
             
             getApplicationUrl();
     		 new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
     		 navigationMenu.searchInFilter("Default");
     		 sleepFor(2000);
     		 navigationMenu.clickOnFilteredRow("documentTypesTable");
     		 navigationMenu.clickOnEditIcon();
     		 new AdminUserSection(driver).enterSecurityLevel(String.valueOf(2));
     		 navigationMenu.clickOnSaveIcon();
     		navigationMenu.waitForAddIcon();
		} catch (Exception e) {
			windowHelper.clickOkOnPopup();
			
			 getApplicationUrl();
    		 new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
    		 navigationMenu.searchInFilter("Default");
    		 sleepFor(2000);
    		 navigationMenu.clickOnFilteredRow("documentTypesTable");
    		 navigationMenu.clickOnEditIcon();
    		 new AdminUserSection(driver).enterSecurityLevel(String.valueOf(2));
    		 navigationMenu.clickOnSaveIcon();
    		 navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(priority=8,enabled = true, dataProvider = "captureFormData",dependsOnMethods = "UpdateUserSecurityAndDocumentSecurity")				//Case 13 
	public void CaptureDocument(Map<String, String> map) {
		String routingType="To User",
				routingTo="Office Connect User",
				documentType=map.get("DocumentType"),
				docRef= map.get("DocRef")+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		try {
			getApplicationUrl();
			capture = navigationMenu.clickOnCaptureTab();
			capture.clickOnDocumentCaptureBtn();
			String filename = map.get("FileName");
			capture.clickOnFileUploadIcon();
			String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
			
			capture.selectRoutingTypeDD(routingType,getEnvVariable);
			capture.selectRouteToDD(routingTo,getEnvVariable);

			capture.selectDocumentTypemDD(documentType,getEnvVariable);
			//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
			capture.enterDocRef(docRef);
			
			capture.clickOnIndexDocument();
			sleepFor(2000);
			String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();

			AssertionHelper.verifyText(getPopUpMessage, "The selected Route To user does not have access to the selected Document Type.");
			
			new NavigationMenu(driver).clickOnCancelIcon();
			new AlertHelper(driver).acceptAlertIfPresent();
		} finally {
			//Updating doc type security level to original once 
			getApplicationUrl();
			new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
			navigationMenu.searchInFilter("Default");
			sleepFor(2000);
			navigationMenu.clickOnFilteredRow("documentTypesTable");
			navigationMenu.clickOnEditIcon();
			new AdminUserSection(driver).enterSecurityLevel(String.valueOf(0));
			navigationMenu.clickOnSaveIcon();	
			navigationMenu.waitForAddIcon();
		}		
	}
	
	
	@Test(priority=13,enabled = true, dataProvider = "captureFormData")				//Case 23
	public void CaptureDocToDiffFileSystemWithExistingRef(Map<String, String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=map.get("routingTo"),
				documentType=map.get("DocumentType"),
				docRef= "Diff_"+generateRandomNumber(),
				fileSystem = map.get("memoFileSystem"),
				folder1RefValue = map.get("Folder1RefValue");

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		capture.selectFileSystemDD(fileSystem,getEnvVariable);
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		
		capture.enterFolder1RefCapturePageAndSearch(folder1RefValue, "Account Ref");
		capture.clickOnIndexDocument();
		
		//It is to verify that doc get reindexd into diff FS
        getApplicationUrl();
		docTools.searchWithCriteriaAllFileSystem("Doc Ref",docRef,getEnvVariable );
		docTools.clickOnDocumentListButton();
		String expFileSystem = fileSystem.split("-")[0].trim();
		try {
		docTools.clickOnOtherFileSystem(expFileSystem);
		String actFileSystem = navigationMenu.getColumnValueFromTable("File System");			
		AssertionHelper.verifyTextContains(actFileSystem, expFileSystem);
		}
		finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}

	}

	@Test(priority=14,enabled = true, dataProvider = "captureFormData")   			//Case 24 
	public void CaptureDocToDiffFileSystemWithNewRef(Map<String, String> map) throws InterruptedException {
	
		String routingType="To User",
				routingTo=map.get("routingTo"),
				documentType=map.get("DocumentType"),
				docRef= "DiffNe_"+generateRandomNumber(),
				fileSystem = map.get("memoFileSystem"),
				folder1RefValue = "HIG_"+generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		capture.selectFileSystemDD(fileSystem,getEnvVariable);
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		
		if(folder1RefValue!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(folder1RefValue, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
				capture.enterFolder1Ref(folder1RefValue);
				capture.clickOnSaveButton();
				waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
		}
		
		capture.clickOnIndexDocument();
		
		//It is use to verify that doc gets reindexed into diff FS
        getApplicationUrl();
		docTools.searchWithCriteriaAllFileSystem("Doc Ref",docRef,getEnvVariable );
		docTools.clickOnDocumentListButton();
		String expFileSystem = fileSystem.split("-")[0].trim();
		try {
		docTools.clickOnOtherFileSystem(expFileSystem);
		String actFileSystem = navigationMenu.getColumnValueFromTable("File System");			
		AssertionHelper.verifyTextContains(actFileSystem, expFileSystem);
		}
		finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}

	}	
	
	@Test(priority=17,enabled = true,dataProvider = "captureFormData")
	public void ReindexDocumentAcrossFileSystem(Map<String, String> map) {				//case 34  [82937]
		String routingType="To User",
				routingTo=map.get("routingTo"),
				documentTypeInAFileSystem=map.get("DocumentType"),
				documentTypeInBFileSystemS="tp - timepass",				//that is source file FS
				docRef= "ReindexDiff"+generateRandomNumber(),
				diffFileSystem = map.get("memoFileSystem");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentTypeInBFileSystemS,getEnvVariable);
		capture.enterDocRef(docRef);
		
		capture.clickOnIndexDocument();
		sleepFor(2000);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		sleepFor(2000);
		navigationMenu.waitForIcon("Index the following changes");
		capture.selectFileSystemDD(diffFileSystem,getEnvVariable);
		
		capture.selectDocumentTypemDD(documentTypeInAFileSystem,getEnvVariable);
		capture.clickOnIndexDocument();
		 String message = capture.getSuccessfullReindexPopupMessage();
         AssertionHelper.verifyTrue(message.contains("re-indexed successfully."),"Message does not match");
         capture.clickOkOnSuccesfullReIndexPopup();
	}
	
	@Test(priority=18,enabled = false,dataProvider="captureFormData")				//case 39  [113983]
	public void ReindexTwoDocumentAcrossFileSystem(Map<String,String> map) throws InterruptedException {
		
		String docRef11="REONE_"+generateRandomNumber();
		String docRef12="RETWO_"+generateRandomNumber();
		String fileSystem11="D - Default Filing System",
				fileSystem2 = map.get("memoFileSystem"),
				doctype = map.get("DocumentType");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef11, null,getEnvVariable);
		
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef12, null,getEnvVariable);
		
		//Reindex first doc to D file system
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef11);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		navigationMenu.waitForIcon("Index the following changes");
		capture.selectFileSystemDD(fileSystem11,getEnvVariable);
		capture.selectDocumentTypemDD(doctype,getEnvVariable);
		capture.clickOnIndexDocument();
		 String message = capture.getSuccessfullReindexPopupMessage();
         AssertionHelper.verifyTrue(message.contains("re-indexed successfully."),"Message does not match");
         capture.clickOkOnSuccesfullReIndexPopup();
         
       //Reindex second doc to another file system
 		getApplicationUrl();
 		capture.selectSearchTab(); 
 		capture.searchWithCriteria("Doc Ref",docRef12);
 		capture.clickOnDocumentListBtn();
 		capture.clickOnFirstItemOfIntray();
 		navigationMenu.clickOnIcon("Reindex document", "Document");
 		navigationMenu.waitForIcon("Index the following changes");
 		capture.selectFileSystemDD(fileSystem2,getEnvVariable);
 		capture.selectDocumentTypemDD(doctype,getEnvVariable);
 		capture.clickOnIndexDocument();
 		String message1 = capture.getSuccessfullReindexPopupMessage();
        AssertionHelper.verifyTrue(message1.contains("re-indexed successfully."),"Message does not match");
        capture.clickOkOnSuccesfullReIndexPopup();
	}
	
	@Test(priority=19,enabled = true,dataProvider = "addDocumentType")				//case 28   [106192]  
	public void VerifyIndexCriteriaOnCaptureIfDocTypeIsInactive(Map<String, String> map) {
		String typeId = "Nothing",
				description=map.get("typeDescription");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		try {
			getApplicationUrl();
			new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
			navigationMenu.searchInFilter(typeId);
			sleepFor(2000);
			navigationMenu.clickOnFilteredRow("documentTypesTable");
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Save changes");
			new AdminDocumentSection(driver).checkMakeInactive();
			navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");
			navigationMenu.waitForAddIcon();
			
			getApplicationUrl();
			capture.clickOnCaptureTab();
			capture.clickOnDocumentCaptureBtn();
			
			List<String> ddCapturePageOptions = new DropdownHelper(driver).getAllOptionsFromDropdown("DocumentTypeId",getEnvVariable);
			
			List<String> ddRoutingType = new DropdownHelper(driver).getAllOptionsFromDropdown("RoutingType",getEnvVariable);
			System.out.println("RoutingType======= "+ddRoutingType);
			AssertionHelper.verifyTrue(ddRoutingType.size()>0, "Verifying routing type size in routing type drop down");
			
			List<String> ddMailOptions = new DropdownHelper(driver).getAllOptionsFromDropdown("MailOptionId",getEnvVariable);
			System.out.println("Mail Options============= "+ddMailOptions);
			AssertionHelper.verifyTrue(ddMailOptions.size()>0, "Verifying mail option size in mail option drop down");
			
			capture.selectRoutingTypeDD("To User",getEnvVariable);
			
			List<String> ddRoutingToUser = new DropdownHelper(driver).getAllOptionsFromDropdown("RouteTo",getEnvVariable);
			System.out.println("RoutingToUser=================="+ddRoutingToUser);
			AssertionHelper.verifyTrue(ddRoutingToUser.size()>0, "Verifying user list size in route to");
					
			capture.selectRouteToDD("(none)",getEnvVariable);
			

			capture.selectRoutingTypeDD("To Team",getEnvVariable);
			List<String> ddRoutingToTeam = new DropdownHelper(driver).getAllOptionsFromDropdown("RouteTo",getEnvVariable);
			System.out.println("ddRoutingToTeam=================="+ddRoutingToTeam);
			AssertionHelper.verifyTrue(ddRoutingToTeam.size()>0, "Verifying team list size in route to");
			
			boolean docTypeStatus = ddCapturePageOptions.contains("Nothing - Nothing Document Type");
			AssertionHelper.verifyFalse(docTypeStatus);
			
			capture.selectRouteToDD("(none)",getEnvVariable);
			navigationMenu.clickOnCancelIcon();
			sleepFor(2000);
		} finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			//new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Select Document Type category to admin", "Document");
			navigationMenu.clickOnIconMenu("Inactive document types");
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(typeId);
			sleepFor(2000);
			navigationMenu.clickOnFilteredRow("documentTypesTable");
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Save changes");
			new AdminDocumentSection(driver).checkMakeInactive();
			navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");
			navigationMenu.waitForAddIcon();
		}
	}
	
	/*
	 * To run this case need to create non admin user as Capture in source FS
	 */
	@Test(priority=20,enabled = true)				//Case 25  [81351]
	public void VerifyThatDocumentCanBeRoutedToNonAdminUser() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		try {
			String nonAdminDocRef="Non_"+generateRandomNumber();
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getCaptureUser(), ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			
			capture.navigateAndCaptureDocWithParameters(null, null, null,nonAdminDocRef, null, null, ObjectReader.reader.getCaptureUser(),getEnvVariable);
		
			getApplicationUrl();
			capture.selectSearchTab(); 
			capture.searchWithCriteria("Doc Ref",nonAdminDocRef);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}
	
	@Test(priority=21,enabled = false,dataProvider = "captureFormData")				//Case 36  [80649]
	public void VerifyReindexTheDocumentFromBatchIndexing(Map<String,String> map) throws InterruptedException {
		String batchDocRef="Batch"+generateRandomNumber();
		
		String routingType="To User",
				routingTo=map.get("routingTo"),
				doctype=map.get("DocumentType");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateToBatchIndex();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Index selected batch", "Actions");
		sleepFor(2000);
		navigationMenu.waitForIcon("Cancel changes");
		capture.clickOnAddIconInPreview();
		
		capture.enterDocRefInBatch(batchDocRef);
		navigationMenu.clickOnIcon("Index", "Actions");
		new WindowHelper(driver).waitForPopup("Index");
		new WindowHelper(driver).clickOkOnPopup("Index");
		
		//Reindex the batch
 		getApplicationUrl();
 		capture.selectSearchTab(); 
 		capture.searchWithCriteria("Doc Ref",batchDocRef);
 		capture.clickOnDocumentListBtn();
 		capture.clickOnFirstItemOfIntray();
 		navigationMenu.clickOnIcon("Reindex document", "Document");
 		navigationMenu.waitForIcon("Index the following changes");
 		capture.selectDocumentTypemDD(doctype,getEnvVariable);
 		capture.clickOnIndexDocument();
 		String message1 = capture.getSuccessfullReindexPopupMessage();
        AssertionHelper.verifyTrue(message1.contains("re-indexed successfully."),"Message does not match");
        capture.clickOkOnSuccesfullReIndexPopup();
	}
	

	
	/*
	 * To run this test suite need to add nothing document which is having never as publication mode
	 */
	@Test(priority=24,enabled = true)			//Case 35 [117411]
	public void VerifyThatUserAbleToReindexDocFromPublishableToNonpublishableDoctype() throws InterruptedException {
		String docRefPA = "PA_"+generateRandomNumber();
		String doctype = "Nothing - Nothing Document Type";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRefPA, null,getEnvVariable);
		
		//Search the document and publish the doc on Public access
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRefPA);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		docTools.clickOnCreateAndPublishOption();
		new WindowHelper(driver).waitForPopup("Rendition");
		String getPopupMessage = new WindowHelper(driver).getPopupMessage();
		System.out.println("getPopupMessage============"+getPopupMessage);
		new NavigationMenu(driver).clickOkOnPopup();
		
		AssertionHelper.verifyTextContains(getPopupMessage, "You have successfully created and published a rendition");
		
		//To verify rendition status
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRefPA);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		String getRenditionStatus = navigationMenu.getColumnValueFromTable("Rendition Status");
		System.out.println("getStatus "+getRenditionStatus);
		AssertionHelper.verifyText(getRenditionStatus, "Public");
		
		
		//Reindex the document from publishable to non publishable
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRefPA);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		sleepFor(2000);
		navigationMenu.waitForIcon("Index the following changes");
		
		capture.selectDocumentTypemDD(doctype,getEnvVariable);
		capture.clickOnIndexDocument();
		 String message = capture.getSuccessfullReindexPopupMessage();
         AssertionHelper.verifyTrue(message.contains("re-indexed successfully."),"Message does not match");
         capture.clickOkOnSuccesfullReIndexPopup();
	}
	
	@Test(priority = 25,enabled = false,dataProvider = "captureFormData")			//Case 35 [117411]  add here select team code not to harcode add code to remove user security
	public void VerifyCannotReindexDocumentIfUserSecurityEnabled(Map<String, String> map) throws InterruptedException {			//[126657]
		String docRef = "DocSecurity"+generateRandomNumber();
		
		String documentTypeInAFileSystem=map.get("DocumentType"),
				diffFileSystem = map.get("memoFileSystem");

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		//This is used to add security for document type
		getApplicationUrl();
		new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
		navigationMenu.searchInFilter("Default");
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		new JavaScriptHelper(driver).clickElement(new AdminDocumentSection(driver).securityTab);
		new AdminDocumentSection(driver).selectEnableUserSecurityAccess();
		click(new AdminDocumentSection(driver).editUserAccessBtn, diffFileSystem);
		new WindowHelper(driver).waitForPopup("Document Type User Access Security");
		
		click(new AdminDocumentSection(driver).teamsSecurityDropDownButton, "Clicking on team dropdown");
		new DropdownHelper(driver).selectFromAutocompleDD("Qa", new AdminDocumentSection(driver).teamsSecurityDropDownButton,getEnvVariable);
		click(new AdminDocumentSection(driver).teamAddButtonOnDocTypeSecurity, "Clicking on add button");
		click(new AdminDocumentSection(driver).closeBtn, "Clicking on close button");
		navigationMenu.clickOnIcon("Save changes made to document type", "Actions");
		navigationMenu.waitForAddIcon();
		
		//Captute docuemnt 
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		//Reindex the document to the another file system 
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		sleepFor(2000);
		navigationMenu.waitForIcon("Index the following changes");
		capture.selectFileSystemDD(diffFileSystem,getEnvVariable);
		
		capture.selectDocumentTypemDD(documentTypeInAFileSystem,getEnvVariable);
		capture.clickOnIndexDocument();
		String message = capture.getSuccessfullReindexPopupMessage();
        AssertionHelper.verifyTrue(message.contains("re-indexed successfully."),"Message does not match");
        capture.clickOkOnSuccesfullReIndexPopup();
        
        getApplicationUrl();
		docTools.searchWithCriteriaAllFileSystem("Doc Ref",docRef,getEnvVariable );
		docTools.clickOnDocumentListButton();
		String expFileSystem = diffFileSystem.split("-")[0].trim();
		try {
		docTools.clickOnOtherFileSystem(expFileSystem);
		String actFileSystem = navigationMenu.getColumnValueFromTable("File System");			
		AssertionHelper.verifyTextContains(actFileSystem, expFileSystem);
		}
		finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 26,enabled = true,dataProvider = "captureFormData")         //case 29[110930] 
	public void VerifySystemIndexesAndDisplaysNewelySearchedPropRef(Map<String,String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=map.get("routingTo"),
				documentType=map.get("DocumentType"),
				docRef= "FolderRef"+generateRandomNumber(),
				diffFileSystem = map.get("memoFileSystem");

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String folder1Ref="SD1"+generateRandomNumber();
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		String folder3Ref = "PS1"+generateRandomNumber();
		String entityUnderSearch=ObjectReader.reader.getFolder3RefName();
		
		boolean createdRef=false;
		
		getApplicationUrl();
		folderPage.navigateToAddNewEntityPage(fold1entityName);
		folderPage.enterFolder1Ref(folder1Ref);
		sleepFor(1000);
		folderPage.enterFolder3Ref(folder3Ref);
		new ActionHelper(driver).pressTab();
		
		new WindowHelper(driver).waitForPopup("Not found");
		new WindowHelper(driver).clickOkOnPopup();
		//new NavigationMenu(driver).WaitForNavigationBarTitle(entityUnderSearch);
		sleepFor(6000);
		new NavigationMenu(driver).clickOnSaveIcon();
		
		new WaitHelper(driver).waitForElement(new NavigationMenu(driver).saveIconBtn , 10);
		new NavigationMenu(driver).clickOnSaveIcon();
		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		//capture.selectFileSystemDD(fileSystem);
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		capture.enterDocRef(docRef);
		
		capture.enterFolder1RefCapturePageAndSearch(folder1Ref, "Account Ref");
		sleepFor(2000);
		new CapturePage(driver).enterFolder1RefCapturePageAndSearch("","Property Ref");;     //Which is used to reomve exising folder3 ref
		sleepFor(2000);
		new CapturePage(driver).enterFolder1RefCapturePageAndSearch("F3-001R3","Property Ref");
		sleepFor(1000);
		capture.clickOnIndexDocument();
		
		//Which is used to verify the newely added prop reference
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String getPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");
		System.out.println("getStatus "+getPropertyRef);
		AssertionHelper.verifyText(getPropertyRef, "F3-001R3");
	}
	
	//Pre requisite two protective one is PR1 and other is PM1
	@Test(priority = 27,enabled = true,dataProvider = "captureFormData")       //Case 86,87,88 [162720,160964,155482]
	public void VerifyprotectivemarkerAuditLogsWhileReindex(Map<String,String> map) {
		
		String routingType="To User",
				routingTo=map.get("routingTo"),
				documentType=map.get("DocumentType"),
				docRef= "DocProtective_"+generateRandomNumber(),
		        protectiveMarker = map.get("ProtectiveMarker");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);
		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		capture.selectProtectiveMarkerDD(protectiveMarker,getEnvVariable);
		capture.enterDocRef(docRef);
		capture.clickOnIndexDocument();
		
		//Search the document & edit the metadata
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnLandingPageIcon();
		
		intrayTools.clickOnEditMetadata();
		capture.selectProtectiveMarkerDD("PM2",getEnvVariable);
		navigationMenu.clickOnIconMenu("Save", "Actions");
		
		new JavaScriptHelper(driver).scrollToBottom();
    	sleepFor(2000);
        navigationMenu.clickOnNavTab("Document Audit");
        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 10);
        
        boolean updateProtectiveActionColstatus = new VerificationHelper(driver).isElementDisplayedByEle(new IntrayToolsPage(driver).updateProtectiveMarkerActionColumn);
        AssertionHelper.verifyTrue(updateProtectiveActionColstatus, "Checking action column of protective marker");
        
        boolean updateProtectiveDetailsColstatus = new VerificationHelper(driver).isElementDisplayedByEle(new IntrayToolsPage(driver).updateProtectiveMarkerDetailsColumn);
        AssertionHelper.verifyTrue(updateProtectiveDetailsColstatus, "Checking details column of protective marker");
	}
	
	@Test(priority = 28, enabled = true, dataProvider = "docreReferenceData")
	public void AddDocumentTypeAndUpdateUserSecurityLevel(String doctype, String folder2Ref, String docref,
			String fileSystem) {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		String [] getFileSystemName=new HomePage(driver).getCurrentFileSystemName().split("\n");
		FSName=getFileSystemName[0];

		documentTypeId = "High";
		getApplicationUrl();
		new HomePage(driver).changeFileSystem(fileSystem);

		new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		new AdminDocumentSection(driver).enterTypeId(documentTypeId);
		new AdminDocumentSection(driver).enterDescription("High security document type");
		new AdminDocumentSection(driver).selectMailOption("Mail",getEnvVariable);
		new AdminDocumentSection(driver).selectMediaType("No Specified Media Type",getEnvVariable);
		new AdminUserSection(driver).enterSecurityLevel(String.valueOf(10));
		new AdminDocumentSection(driver).checkEditable();
		sleepFor(1000);
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(documentTypeId);
			Thread.sleep(2000);
			String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
			AssertionHelper.verifyText(filteredText, documentTypeId);
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter("GatewayUser");
			sleepFor(1000);
			new AdminUserSection(driver).clickOnFilteredUser();
			navigationMenu.clickOnEditIcon();
			new AdminUserSection(driver).enterSecurityLevel(String.valueOf(7));
			sleepFor(2000);
			try {
				navigationMenu.clickOnSaveIconForUser();
				navigationMenu.waitForAddIcon();
			} catch (Exception e) {
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "No changes to save.");
			}

		} catch (Exception ex) {
			String actMessage = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyTextContains(actMessage, "already exists");

			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter("GatewayUser");
			sleepFor(1000);
			new AdminUserSection(driver).clickOnFilteredUser();
			navigationMenu.clickOnEditIcon();
			new AdminUserSection(driver).enterSecurityLevel(String.valueOf(7));
			sleepFor(2000);
			try {
				navigationMenu.clickOnSaveIconForUser();
				navigationMenu.waitForAddIcon();
			} catch (Exception e) {
				String actMessage1 = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage1, "No changes to save.");

			}
		}
	}
	
	//To run this test case need to create one doc type as High with security level 10 in source file system
	@Test(priority = 29,enabled = true,dataProvider = "captureFormData")		              //case26  [83176]
	public void VerifyThatDocumentCanBeRoutedToUserInAnotherFileSystem(Map<String, String> map) {			
		String routingType="To User",
				routingTo="Gateway User",
				documentType=documentTypeId,
				docRef= "DOCError"+generateRandomNumber();
		String filename = map.get("FileName");


		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);
		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		capture.enterDocRef(docRef);
		capture.clickOnIndexDocument();
		
		new WindowHelper(driver).waitForPopup("Index Failed");
		String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		AssertionHelper.verifyTextContains(getPopUpMessage,"The selected Route To user does not have access to the selected Document Type.");		
		
		new NavigationMenu(driver).clickOnCancelIcon();
		new AlertHelper(driver).acceptAlertIfPresent();	
	}
	
	//To run this test case need to create one doc type with security level 10 in source file system and create one Master12 as doc type
	@Test(priority = 30,enabled = true,dataProvider = "captureFormData")		
	public void VerifyThatDocumentCanBeRoutedToUserInAnotherFileSystemForSource(Map<String, String> map) {			//case26  [83176]
		String routingType="To User",
				routingTo=ObjectReader.reader.getUserName(),
				documentType="Master12",
				docRef= "DOCError"+generateRandomNumber();
		String filename = map.get("FileName");
		String [] filesystem = FSName.split("-");
		String prevFileSystem = filesystem[0].trim();
		

		String getEnvVariable = System.getProperty("propertyName");	
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		try {
			getApplicationUrl();
			capture = navigationMenu.clickOnCaptureTab();
			capture.clickOnDocumentCaptureBtn();
			capture.selectFileSystemDD(FSName,getEnvVariable);
			capture.clickOnFileUploadIcon();
			String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
			
			capture.selectRoutingTypeDD(routingType,getEnvVariable);
			capture.selectRouteToDD(routingTo,getEnvVariable);
			capture.selectDocumentTypemDD(documentType,getEnvVariable);
			capture.enterDocRef(docRef);
			capture.clickOnIndexDocument();
			
			new WindowHelper(driver).waitForPopup("Index Failed");
			String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			AssertionHelper.verifyTextContains(getPopUpMessage,"The selected Route To user does not have access to the selected Document Type.");			//"The selected Route To user does not have access to the selected Document Type."
			
			new NavigationMenu(driver).clickOnCancelIcon();
			new AlertHelper(driver).acceptAlertIfPresent();
		} finally {
			getApplicationUrl();
			sleepFor(2000);
			new HomePage(driver).changeFileSystem(prevFileSystem);
		}
	}
	
	
	//To run this case need to create doc type Folderde with F1-001R1 as default folder in destination FS
	@Test(priority = 31, enabled = true, dataProvider = "captureFormData")
	public void InternalServerErrorWhileReindex(Map<String,String> map) throws InterruptedException {				//case 40 [121809]			
		String docRef = "Internal"+generateRandomNumber();
		String documentType = "Folderde";
		String FS = map.get("memoFileSystem");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		//Reindex the document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		sleepFor(2000);
		navigationMenu.waitForIcon("Index the following changes");

		capture.selectFileSystemDD(FS,getEnvVariable);
		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		capture.clickOnIndexDocument();
		String message = capture.getSuccessfullReindexPopupMessage();
		AssertionHelper.verifyTrue(message.contains("re-indexed successfully."), "Message does not match");
		capture.clickOkOnSuccesfullReIndexPopup();
		
		//Search that document in destination file system and verify
		getApplicationUrl();
		docTools.searchWithCriteriaAllFileSystem("Doc Ref", docRef, getEnvVariable);
		docTools.clickOnDocumentListButton();
		String [] expFileSystem = FS.split("-");
		//String latestFS = expFileSystem[0].trim();
		try {
			docTools.clickOnOtherFileSystem(expFileSystem[0].trim());
			String actFileSystem = navigationMenu.getColumnValueFromTable("Account Ref");
			AssertionHelper.verifyTextContains(actFileSystem, "F1-001R1");
		} finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 32, enabled = true)
	public void VerifyAuditLogForAfterReindexingDocument() throws InterruptedException {
		String docRef = "DocReindexLog"+generateRandomNumber();
		String docType = "ScanDoc - Generic Scanned Document";

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);

		//Reindex the document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		sleepFor(2000);
		navigationMenu.waitForIcon("Index the following changes");

		capture.selectDocumentTypemDD(docType,getEnvVariable);
		capture.clickOnIndexDocument();
		String message = capture.getSuccessfullReindexPopupMessage();
		AssertionHelper.verifyTrue(message.contains("re-indexed successfully."), "Message does not match");
		capture.clickOkOnSuccesfullReIndexPopup();
		
		//Search the document & verify audut data for reindex
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnLandingPageIcon();
		
		new JavaScriptHelper(driver).scrollToBottom();
    	sleepFor(2000);
		click(new IntrayToolsPage(driver).documentAuditTab, "Clicking on document audit tab");
        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 10);
        
        boolean updateProtectiveActionColstatus = new VerificationHelper(driver).isElementDisplayedByEle(new IntrayToolsPage(driver).reindexAuditActionColumn);
        AssertionHelper.verifyTrue(updateProtectiveActionColstatus, "Checking audit action column of Reindex column");
        
        boolean updateProtectiveDetailsColstatus = new VerificationHelper(driver).isElementDisplayedByEle(new IntrayToolsPage(driver).reindexAuditDetailsColumn);
        AssertionHelper.verifyTrue(updateProtectiveDetailsColstatus, "Checking audit details column of Reindex column");
	}
	
	public void VerifyPdfThumbnail() throws InterruptedException {
		String docRef="PDFThum"+generateRandomNumber();
		

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);		
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		// Reindex the document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("View document", "Document");
		sleepFor(2000);
		new WindowHelper(driver).switchToNewlyOpenedTab();
		new WaitHelper(driver).waitForElement(new IntrayToolsPage(driver).viewerTabHeader, 10);
		System.out.println("First Switch=========="+driver.getWindowHandle());
		
		click(new IntrayToolsPage(driver).thumbnailBtn, "Clicking on thumb nail button");
		
		new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();
		String actTitle = intrayTools.getViewDocumentTitle();
	    AssertionHelper.verifyText(actTitle, "");
	}
	
	@Test(enabled = true,priority = 33,dataProvider = "captureFormData")
	public void VerifyAddingHTMLTagToDocRef2Field(Map<String, String> map) {
		String routingType="To User",
				routingTo=map.get("routingTo"),
				documentType=map.get("DocumentType"),
				docRef= "DOCHTML_"+generateRandomNumber();
		String docRef2="<script>alert('Hello')</script>";

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);
		
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		capture.enterDocRef(docRef2);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		String getValidationMsg = new CapturePage(driver).GetValidationMessageFromCapturePage("Doc Ref");
		AssertionHelper.verifyText(getValidationMsg, "Please check this value.");
		capture.enterDocRef2(docRef2);
		new ActionHelper(driver).pressTab();
		sleepFor(1000);
		String getValidationMsg1 = new CapturePage(driver).GetValidationMessageFromCapturePage("Doc Ref 2");
		AssertionHelper.verifyText(getValidationMsg1, "Please check this value.");
	
		new NavigationMenu(driver).clickOnCancelIcon();
		new AlertHelper(driver).acceptAlertIfPresent();
	}
	
	/************* Memo module ******************/

	//Create doc type with memo
	@Test( priority=60,enabled = true, dataProvider = "captureFormData")
	public void TestCaptureMemoIndividualDocument(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		//SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		String filename = map.get("FileName"),
				memoDocRef= map.get("memoDocRef"),
				routingType=map.get("RoutingType"),
				routingTo=ObjectReader.reader.getUserName(),
				memoDocType=map.get("MemoDocType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				expMessage="1 Document indexed successfully."
				;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		navigationMenu.clickOnIcon("Create a new memo", "Capture");
		navigationMenu.waitForIcon("Index the following changes");
		sleepFor(2000);
		capture.selectActionTakenForMemo("Test");
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(memoDocType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(memoDocRef);
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));

		sleepFor(1000);
		capture.clickOnIndexDocument();
		//String actMessage = capture.getMemoModePopupMessage();
		//AssertionHelper.verifyText(actMessage, expMessage);

		try {
			String actMessage = capture.getMemoModePopupMessage();
			AssertionHelper.verifyText(actMessage, expMessage);
		} catch (Exception e) {
			getApplicationUrl();
		}
	}

	//Create doc type with memo
	@Test(priority=61, enabled = true, dataProvider = "captureFormData")
	public void TestCaptureMemoTeamDocument(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		String filename = map.get("FileName"),
				memoDocRef= map.get("memoDocRef")+"Team",
				routingType="To Team",
				routingTo=ObjectReader.reader.getLoggedInUsersTeam(),
				memoDocType=map.get("MemoDocType"),
				protectiveMarker=map.get("ProtectiveMarker")
				;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		navigationMenu.clickOnIcon("Create a new memo", "Capture");
		navigationMenu.waitForIcon("Index the following changes");
		sleepFor(2000);
		capture.selectActionTakenForMemo("Test");
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(memoDocType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(memoDocRef);
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));	
		sleepFor(1000);
		capture.clickOnIndexDocument();
		//windowHelper.clickOkOnPopup();

		try {
			windowHelper.clickOkOnPopup();
			sleepFor(2000);
			getApplicationUrl();
			home.clickOnMetroTile("Number of documents received today.");
			sleepFor(2000);
			navigationMenu.searchInFilter(memoDocRef);
			String getActualDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			AssertionHelper.verifyText(getActualDocRef, memoDocRef);
		} catch (Exception e) {
			sleepFor(2000);
			getApplicationUrl();
			home.clickOnMetroTile("Number of documents received today.");
			sleepFor(2000);
			navigationMenu.searchInFilter(memoDocRef);
			String getActualDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			AssertionHelper.verifyText(getActualDocRef, memoDocRef);
		}

	}

	//Create doc type with memo
	@Test(priority=62, enabled = true, dataProvider = "captureFormData")			
	public void TestCaptureMemoAutomaticDocument(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		String filename = map.get("FileName"),
				memoAutoDocRef= map.get("memoDocRef")+"Auto",
				routingType="Automatic",
				memoDocType=map.get("MemoDocType"),
				protectiveMarker=map.get("ProtectiveMarker")
				;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		navigationMenu.clickOnIcon("Create a new memo", "Capture");
		navigationMenu.waitForIcon("Index the following changes");
		sleepFor(2000);
		capture.selectActionTakenForMemo("Test");
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectDocumentTypemDD(memoDocType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(memoAutoDocRef);
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));	
		sleepFor(1000);
		capture.clickOnIndexDocument();
		//String memoAutoMsg = capture.getMemoModePopupMessage();
		//AssertionHelper.verifyTextContains(memoAutoMsg, "indexed successfully.");
		//waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);

		try {
			//String memoAutoMsg = capture.getMemoModePopupMessage();
			//AssertionHelper.verifyTextContains(memoAutoMsg, "indexed successfully.");
			//waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);

			sleepFor(2000);
			getApplicationUrl();
			home.clickOnMetroTile("Number of documents received today.");
			sleepFor(2000);						//Added because not able to clear search text
			navigationMenu.searchInFilter(memoAutoDocRef);
			String getActualDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			AssertionHelper.verifyText(getActualDocRef, memoAutoDocRef);
		} catch (Exception e) {
			sleepFor(2000);
			getApplicationUrl();
			home.clickOnMetroTile("Number of documents received today.");
			sleepFor(2000);						//Added because not able to clear search text
			navigationMenu.searchInFilter(memoAutoDocRef);
			String getActualDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			AssertionHelper.verifyText(getActualDocRef, memoAutoDocRef);
		}

	}


	//Priority last
	//Create doc type with memo
	@Test( enabled = false, dataProvider = "captureFormData")			//true
	public void TestCaptureMemoDelete(Map<String, String> map ) { 
		test = ExtentTestManager.getTest();
		SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());

		waitHelper.waitForElementVisible(capture.intrayListBtn, 20, 1);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", "%memo");
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Delete document", "Document");
		windowHelper.clickOkOnPopup();
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", "%memo");
		//AssertionHelper.verifyTextContains(navigationMenu.getNoRecordsFoundMessage(tableId), "No records found");
	}

	@Test(priority=63, enabled = true, dataProvider = "captureFormData")
	public void TestCaptureMemoNavigationViwer(Map<String, String> map ) throws InterruptedException { 
		test = ExtentTestManager.getTest();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		;
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", "%Memo%");
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			windowHelper.switchToNewlyOpenedTab();
			docTools.clickOnNextButtonViewer();
			AssertionHelper.verifyFalse(navigationMenu.getPageTitle().contains("Error"));
			docTools.clickOnNextButtonViewer();
			AssertionHelper.verifyFalse(navigationMenu.getPageTitle().contains("Error"));
			docTools.clickOnNextButtonViewer();
			AssertionHelper.verifyFalse(navigationMenu.getPageTitle().contains("Error"));
		}
		finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}
	}

	@Test(priority = 64,enabled = true, dataProvider = "captureFormData")
	public void TestCaptureMemoDifferentFileSystem(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		String  memoDocRef= map.get("memoDocRef")+"FS",
				routingType=map.get("RoutingType"),
				routingTo=ObjectReader.reader.getUserName(),
				memoDocType=map.get("MemoDocType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				fileSystem=map.get("memoFileSystem")
				;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		navigationMenu.clickOnIcon("Create a new memo", "Capture");
		navigationMenu.waitForIcon("Index the following changes");
		sleepFor(2000);
		capture.selectActionTakenForMemo("Test");
		capture.selectFileSystemDD(fileSystem,getEnvVariable);
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(memoDocType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(memoDocRef);
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));

		sleepFor(1000);
		capture.clickOnIndexDocument();
		sleepFor(2000);
		//windowHelper.clickOkOnPopup();

		try {
			windowHelper.clickOkOnPopup();
			String expFileSystem = fileSystem.split("-")[0].trim();
			String actFileSystem = docTools.checkIfDocExistInFileSystem(expFileSystem,memoDocRef,getEnvVariable);
			AssertionHelper.verifyTextContains(actFileSystem, expFileSystem);
		}catch(Exception e) {
			String expFileSystem = fileSystem.split("-")[0].trim();
			String actFileSystem = docTools.checkIfDocExistInFileSystem(expFileSystem,memoDocRef,getEnvVariable);
			AssertionHelper.verifyTextContains(actFileSystem, expFileSystem);
		}
		finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}

	}		
	
	@Test(priority=65,enabled = true, dataProvider = "captureFormData")			//Case 16   Keep second last
	public void UpdateProtectiveMarkerValueAndVerifyErrorMsgWhileCapture(Map<String, String> map) {
		Map<String, String> values = new HashMap<String, String>();
		values.put("ProtectiveMarkerMandatory", "1");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
		
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		
		String routingType="To User",
				routingTo="Office Connect User",
				documentType=map.get("DocumentType"),
				docRef= map.get("DocRef")+generateRandomNumber();

		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		capture.clickOnIndexDocument();
		
		new WindowHelper(driver).clickOkOnPopup();
		String getErrorMsg = new CapturePage(driver).protectiveMarkerErrorMsg.getText();
		sleepFor(1000);
		navigationMenu.clickOnCancelIcon();
		log.info("getProtectiveErrorMessage ============"+getErrorMsg);
		AssertionHelper.verifyText(getErrorMsg, "This field is required.");
		
	}
	
	@Test(priority=66,enabled = true)				//Keep second last
	public void UpdateProtectiveMarkerValueBackToOrigin() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("ProtectiveMarkerMandatory", "0");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
		
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
	}
	
	@Test(priority=67,enabled = true)			//Keep last  case 18
	public void VerifyCapturePageAfterRemovingDIPSecurityFromUsers() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
    	
		try {
			new AdminUserSection(driver).checkSecurityRightsForUser("DIP", "Admin", ObjectReader.reader.getUserName(), false,getEnvVariable);
	         
	        capture = navigationMenu.clickOnCaptureTab();
	        AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(capture.documentCaptureBtn));

		} finally {
			new AdminUserSection(driver).checkSecurityRightsForUser("DIP", "Admin", ObjectReader.reader.getUserName(), true,getEnvVariable);
		}      
	}
	
	@Test(priority=68,enabled = true)		//Keep last  case 19
	public void VerifyCapturePageAfterRemovingCaptureSecurityFromUsrs() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
    	
		try {
			new AdminUserSection(driver).checkSecurityRightsForUser("Capture Documents", "Document", ObjectReader.reader.getUserName(), false,getEnvVariable);
	         
	        capture = navigationMenu.clickOnCaptureTab();
	        AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(capture.documentCaptureBtn));

		} finally {
			new AdminUserSection(driver).checkSecurityRightsForUser("Capture Documents", "Document", ObjectReader.reader.getUserName(), true,getEnvVariable);
		}    
	}

	
	@Test(priority=69,enabled = true, dataProvider = "captureFormData")       //Case 17   Keep ekdam last
	public void EditWarnOnGenericDocTypeIndexAndVerifyPopUpWhileCapture(Map<String, String> map) {
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		Map<String, String> values = new HashMap<String, String>();
		values.put("WarnOnGenericDocTypeIndex", "1");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
		
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
		
		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		
		String routingType="To User",
				routingTo="Office Connect User",
				documentType=map.get("DocumentType"),
				docRef= map.get("DocRef")+generateRandomNumber();

		getApplicationUrl();
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		capture.clickOnIndexDocument();
		
		new WindowHelper(driver).waitForPopup("Index");
		String getPopUpMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		AssertionHelper.verifyTextContains(getPopUpMsg, "Are you sure you wish to index to the generic document type");		
	}
	
	@Test(priority=70,enabled = true)				////Keep ekdam last
	public void UpdateWarnOnGenericDocTypeIndexConfigValueToOriginalOnce() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("WarnOnGenericDocTypeIndex", "0");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
		
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
	}


}




























//Chk 24 & 25 Later