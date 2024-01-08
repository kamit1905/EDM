package tests;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
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
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
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
import utils.ExcelUtils;
import utils.ExtentReports.ExtentTestManager;

public class TestCapturePage extends TestBase{

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

	/*@Test( enabled=true,priority=0, description = "This function tests the valid flow of login", groups = "smoke")
	public void TestLoginFunctionality() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		log.info("======="+getEnvVariable+"==========");
		String username = ObjectReader.reader.getLoginId();
		String password = ObjectReader.reader.getPassword();
		login.loginWithCredentials(username,password);
		String welcomeActual = login.getWelcomeText();
		String usernameActual = login.getUsernameText();
		AssertionHelper.verifyText(welcomeActual, "Welcome");
		System.out.println("No record message is "+ObjectReader.reader.getNoRecordMessage());
		AssertionHelper.verifyText(usernameActual, username);
	}*/

	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}

	@DataProvider(name="MultipleCaptureFormData")
	public Object[][] MultipleCaptureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","MultipleCaptureFormData");
		return formData;
	}	

	@DataProvider(name="folderRefData")
	public Object[][] folderReferenceData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","FolderRefData");
		return formData;
	}


	@Test(priority=1,enabled = true, description = "This tests is to check navigation flow of capture")		//true
	public void TestCaptureDocumentNavigation() {
		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		AssertionHelper.verifyTrue(capture.isIndexDocumentButtonPresent(),"Assertion to check if index doc button present");
	}



	/**This test will be used as prerequisite for capturing docs for entire suit
	 */
	@Test(priority=48,enabled = true ,dataProvider = "MultipleCaptureFormData")		//true
	public void TestCaptureFormFillingMultipleDocsPrereq(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		//SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		capture.clickOnFileUploadIcon();
		String filename = map.get("FileName");
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename),
				routingType=map.get("RoutingType"),
				routingTo=ObjectReader.reader.getUserName(),
				documentType=map.get("DocumentType"),
				docRef= map.get("DocRef")
				;

		AssertionHelper.verifyText(ActualFileName, filename);
		//Assert.assertTrue(condition);

		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);		  


		capture.waitForRoutingToDDClickable();
		Thread.sleep(2000);// Route to DD takes time to load
		capture.clickOnIndexDocument();
		sleepFor(1000);
		//Add comment line no.154 to 162 if running on R531
		/*log.info("Base URL is "+baseUrl);
		  if((documentType.contains("Default") || documentType.contains("ScanDoc")) && baseUrl.contains("Lives")) {
		  capture.clickOkOnCofirmIndexDocument(); }

		  String message = capture.getSuccessfullPopupMessage();
		  AssertionHelper.verifyTrue(message.contains("indexed successfully."),"Message does not match");
		  capture.clickOkOnSuccesfullPopup();
		  test.log(Status.INFO, "Document captured successffully");*/
		try {
			waitHelper.waitForElement(new CapturePage(driver).successullyIndexMsg,10);
			new CapturePage(driver).clickOkOnSuccesfullPopup();
			navigationMenu.clickOnHomePageIcon();
		} catch (Exception e) {
			navigationMenu.clickOnHomePageIcon();
		}
		//navigationMenu.clickOnHomePageIcon();


	}


	@Test(priority=49,enabled = true, dataProvider = "captureFormData")			//true
	public void TestCaptureFormFillingWithCreateDocument(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		//SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String  accountRefValue = map.get("AccountRefValue"),
				routingType=map.get("RoutingType"),
				routingTo=ObjectReader.reader.getUserName(),
				documentType=map.get("DocumentType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				docRef= map.get("DocRef")+"CreatedDoc"
				;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		capture.ClickOnCreateNewDoc("Test document input ");
		sleepFor(2000);				//Adding because to select route type correctly
		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		//capture.setDateReceivedToToday();
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));
		//capture.setAvailableTag();					//Commented because need to create manually
		//AssertionHelper.verifyTrue(capture.isTagDisplayed(),"Check if tag is displayed");
		capture.clickOnIndexDocument();

		/*if((documentType.contains("Default") || documentType.contains("ScanDoc")) && baseUrl.contains("Lives")) {
		  capture.clickOkOnCofirmIndexDocument(); }

		  String message = capture.getSuccessfullPopupMessage();
		  AssertionHelper.verifyTrue(message.contains("indexed successfully."),"Message does not match");
		  capture.clickOkOnSuccesfullPopup();
		  test.log(Status.INFO, "Document captured successffully");*/

		try {
			waitHelper.waitForElement(new CapturePage(driver).successullyIndexMsg,10);
			new CapturePage(driver).clickOkOnSuccesfullPopup();
			navigationMenu.clickOnHomePageIcon();
		} catch (Exception e) {
			navigationMenu.clickOnHomePageIcon();
		}

	}

    /** More search   **/
	//This is added because to verify in progress mail from more search
	@Test(priority=50,enabled=true,dataProvider = "captureFormData",  description = "Test captured doc change status flow") 
	public void TestSearchDocInTraylistandChangeStatusToInprogress(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");
		
		String changeStatusDocRef = "changeStatusDoc";
		test.log(Status.INFO, "Running test with arguments : "+map.toString());

		getApplicationUrl();
		capture.selectSearchTab(); 
		test.log(Status.INFO, "Search with doc ref "+changeStatusDocRef);
		capture.searchWithCriteria("Doc Ref",changeStatusDocRef);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		if(getEnvVariable.equals("Enterprise_Sp1s") || getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531")) {
		capture.changeEmailStatus("In Progress");				//Changed drop down locator for email status in R531
		}else {
			capture.changeMailStatusUsingSelectClass("In Progress");
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", changeStatusDocRef);
		capture.clickOnIntrayListBtn();
		String mailStatus =capture.getMailStatusForFirstItem();	
		navigationMenu.waitForFilterTextBox();
		log.info("Mail status is "+mailStatus); 
		AssertionHelper.verifyText(mailStatus, "In Progress");	
		//AssertionHelper.verifyTrue(capture.dragAndDropMailStatusToFirstPosition(),"Assertion for drag and drop of mail status to first position");	  

	}


	@Test(priority=51, enabled = true)
	public void TestMoreSearchDocumentList() throws InterruptedException { 
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

	//Need to change according to flow			
	@Test(priority=52, enabled = true)
	public void TestMoreSearchIntrayList() throws InterruptedException { 
		test = ExtentTestManager.getTest();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			docTools.selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getUserName(),"input-Users","input-users");		//Added by amit to select user
		}else {
			docTools.selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getUserName(),getEnvVariable);		//Added by amit to select user 
		}
		capture.clickOnIntrayListBtn();
		String actDocType =  docTools.getAllDocumentTypeValuesToSet();
		AssertionHelper.verifyText(actDocType, "ScanDoc"); 
	}


	//Need to change according to flow
	@Test(priority=87, enabled = true)				//Changed priority because doc is not captured with team
	public void TestTeamDocumentIntrayListMoreSearch() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		//String teamName = "testersman";			//testerman to Testing
		String teamName = ObjectReader.reader.getLoggedInUsersTeam();
		String teamId = ObjectReader.reader.getLoggedInUsersTeamId();
		test.log(Status.INFO, "TeamName============="+teamId);

		getApplicationUrl();
		//navigationMenu.clickOnIcon("Toggle All File Systems option for searching", "Search");
		home.refreshCurrentFileSystem();
		sleepFor(1000);
		docTools.clickOnMoreSearch();
		
		test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(teamName,"input-Teams","input-teams");		//Added by amit to select user
		}else {
			docTools.selectFromTeamUnderIntray(teamName);
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		String actTeamName =  navigationMenu.getColumnValueFromTable("Team");			
		AssertionHelper.verifyText(actTeamName, teamId); 			 		
	}

	@Test(priority=54, enabled = true)
	public void TestMailStatusDocumentIntrayListMoreSearch() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String status = "In Progress";

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getUserName(),"input-Users","input-users");		//Added by amit to select user
			docTools.selectFromInputUnderMoreSearch("I - In Progress","input-mailStatuses","input-mailStatuses");		//Added by amit to select user
		}else {
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getUserName(),getEnvVariable);		//Added by amit to select user
			docTools.selectFromMailStatusIntrayMoreSearch("I - In Progress",getEnvVariable);				
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		String actMailStatus =  capture.getMailStatusForFirstItem();
		AssertionHelper.verifyText(actMailStatus, status); 
	}
	
	/*** --- Capture module *******/





	@Test(priority=57,enabled = true, dataProvider = "captureFormData", 
			dependsOnMethods = { "TestCaptureDocumentNavigation"},description = "This tests is to check capture form fill submission")
	public void TestCaptureFormWithTeamAndValidations(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		SoftAssert soft = new SoftAssert();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		String filename = map.get("FileName");
		capture.clickOnFileUploadIcon();
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename),

				folder1Ref = map.get("Folder1RefValue"),
				folder2Ref = map.get("Folder2RefValue"),
				folder3Ref = map.get("Folder3RefValue"),
				routingType="To Team",
				routingTo=map.get("TeamName"),
				documentType=map.get("DocumentType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				docRef= map.get("DocRef"),
				expectedFileValidationMsg = "No files have been uploaded for indexing.";
		;

		//Assert.assertTrue(condition);

		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		//capture.setDateReceivedToToday();
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));
		//capture.setTag("tag1");
		//capture.setAvailableTag();				//commented because need to create manually
		//AssertionHelper.verifyTrue(capture.isTagDisplayed(),"Check if tag is displayed");
		test.log(Status.INFO, "Entering new folder refernec in order to create one");


		//capture.enterFolderRefCapturePageAndSearchWaitForAddFolder(folder1Ref,"Account Ref");			//Changed from Folder1_Ref to Account Ref
		//Adding this is because if accref is already creatd then no need to put in while loop
		if(folder1Ref!=null) {
			boolean success =capture.enterFolder1RefCapturePageAndSearch(folder1Ref, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
				capture.enterFolder1Ref(folder1Ref);
				capture.clickOnSaveButton();
				waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
		}



		//capture.enterFolder2RefCapturePage(folder2Ref);
		//capture.enterFolder3RefCapturePage(folder3Ref);

		capture.waitForRoutingToDDClickable();
		Thread.sleep(4000);// Route to DD takes time to load
		capture.clickOnIndexDocument();
		sleepFor(1000);
		//windowHelper.waitForPopup("Index");
		//String actualFilevalidationMsg = windowHelper.getPopupMessage();				//commented check 
		//AssertionHelper.verifyText(actualFilevalidationMsg, expectedFileValidationMsg);   //verifying failure msg as well as checking success msg
		//test.log(Status.INFO, "Clicked on index document");
		//String message = capture.getSuccessfullPopupMessage();
		//AssertionHelper.verifyTrue(message.contains("indexed successfully."),"Message does not match");
		//capture.clickOkOnSuccesfullPopup();
		//test.log(Status.INFO, "Document captured successffully");

		//navigationMenu.clickOnHomePageIcon();

		try {
			waitHelper.waitForElement(new CapturePage(driver).successullyIndexMsg,10);
			new CapturePage(driver).clickOkOnSuccesfullPopup();
			navigationMenu.clickOnHomePageIcon();
		} catch (Exception e) {
			navigationMenu.clickOnHomePageIcon();
		}

	}

	@Test(priority=58,enabled=true,dataProvider = "captureFormData",  description = "Test captured doc change status flow") 
	public void TestSearchDocInTraylistandChangeStatus(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String changeStatusDocRef = "changeStatusDoc";
		test.log(Status.INFO, "Running test with arguments : "+map.toString());

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		test.log(Status.INFO, "Search with doc ref "+changeStatusDocRef);
		capture.searchWithCriteria("Doc Ref",changeStatusDocRef);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String status = map.get("ChangeStatusTo");
		capture.clickOnChangeEmailStatusButton();
		capture.clickOnCancelMailStatusButton();
		Thread.sleep(3000);
		
		if(getEnvVariable.equals("Enterprise_Sp1s") || getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531")) {
			AssertionHelper.verifyTrue(capture.changeEmailStatus(status),"Email status changed to "+status);
		}else {
			AssertionHelper.verifyTrue(capture.changeMailStatusUsingSelectClass(status),"Email status changed to "+status);
		}
		//AssertionHelper.verifyTrue(capture.changeEmailStatus(status),"Email status changed to "+status);
		//capture.pressEscape(); 
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", changeStatusDocRef);
		capture.clickOnIntrayListBtn();
		String mailStatus =capture.getMailStatusForFirstItem();				
		log.info("Mail status is "+mailStatus); 
		AssertionHelper.verifyText(mailStatus, status);					
		//AssertionHelper.verifyTrue(capture.dragAndDropMailStatusToFirstPosition(),"Assertion for drag and drop of mail status to first position");	  
	}


	@Test(priority=59,enabled = true, dataProvider = "captureFormData")		//true
	public void TestCopyDocument(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		//SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		String filename = map.get("FileName"),
				docRef= "copyDocumentDoc",
				folder1RefValue = map.get("folder1RefValue"),
				folder2RefValue = map.get("folder2RefValue"),
				folder3RefValue = map.get("folder3RefValue"),
				routingType=map.get("RoutingType"),
				routingTo=ObjectReader.reader.getUserName(),
				documentType=map.get("DocumentType"),
				protectiveMarker=map.get("ProtectiveMarker"),
				copyDocRef = "Copy"+map.get("DocRef"),
				expCopyMessage="Document copied successfully."
				;

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		docTools = capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		//AssertionHelper.verifyText(ActualFileName, filename);
		//Assert.assertTrue(condition);

		capture.selectRoutingTypeDD(routingType,getEnvVariable);
		capture.selectRouteToDD(routingTo,getEnvVariable);

		capture.selectDocumentTypemDD(documentType,getEnvVariable);
		//capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
		capture.enterDocRef(docRef);
		//capture.setDateReceivedToToday();
		int priority = Integer.parseInt(capture.getPriorityValue());
		capture.incrementPriority();
		int Incrementdpriority = Integer.parseInt(capture.getPriorityValue());
		AssertionHelper.verifyText(String.valueOf(Incrementdpriority), String.valueOf(priority+1));
		//capture.setTag("tag1");
		//capture.setAvailableTag();				//commented because need to create manually and added and verified in document tools
		//AssertionHelper.verifyTrue(capture.isTagDisplayed(),"Check if tag is displayed");
		test.log(Status.INFO, "Entering new folder refernec in order to create one");

		//capture.enterFolder1RefCapturePageAndSearchWaitForAddFolder(folder1RefValue,"Account Ref");
		//capture.enterFolder1RefCapturePageAndSearchWaitForAddFolder(folder2RefValue,"Misc Ref");
		//capture.enterFolder1RefCapturePageAndSearchWaitForAddFolder(folder3RefValue,"Property Ref");
		capture.clickOnIndexDocument();
		//String actCopyMessage = capture.getCopyDocumentPopupMessage();

		//test.log(Status.INFO, "Document copied successffully");

		//AssertionHelper.verifyText(actCopyMessage,expCopyMessage);
		try {
			String actCopyMessage = capture.getCopyDocumentPopupMessage();
			navigationMenu.clickOnHomePageIcon();
		} catch (Exception e) {
			navigationMenu.clickOnHomePageIcon();
		}
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


	/************* Batch indexing ******************/


	@Test(priority=72,enabled = false)
	public void TestBatchIndexUnlock() throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		capture.navigateToBatchIndex();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Unlock selected batch", "Action");
		windowHelper.waitForPopup("Batch Unlocked");
		AssertionHelper.verifyTextContains(windowHelper.getPopupMessage(), "unlocked");
		windowHelper.clickOkOnPopup();
	}

	@Test(priority=73, enabled = false)
	public void TestBatchIndexFunctionality() throws InterruptedException { 
		String batchDocRef = "4",
				batchDocRef2 = "5";
		test = ExtentTestManager.getTest();
		getApplicationUrl();
		capture.navigateToBatchIndex();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Index selected batch", "Actions");
		Thread.sleep(2000);
		navigationMenu.waitForIcon("Cancel changes");
		capture.clickOnShowHideMetadata();
		AssertionHelper.verifyFalse(capture.isMetadataShown());
		capture.clickOnNavigateIcon("Go to next page");
		capture.clickOnNavigateIcon("Last Page");
		capture.clickOnNavigateIcon("Go to previous page");
		capture.clickOnShowHideMetadata();
		AssertionHelper.verifyTrue(capture.isMetadataShown(),"Verifying for visiblity of metadata");
		String expectedNum = capture.getPreviewDocumentNumber();
		capture.clickOnAddIconInPreview();
		String actualNum = capture.getSelectedDocumentNumber();
		AssertionHelper.verifyText(actualNum, expectedNum);
		capture.enterDocRefInBatch(batchDocRef);
		capture.enterDocRef2(batchDocRef2);
		navigationMenu.clickOnIcon("Reset all details", "Actions");
		navigationMenu.clickOnIcon("Index", "Actions");
		capture.clickOkOnCofirmIndexDocument(); 
		String actMessage = capture.getSuccessfullPopupMessage();
		AssertionHelper.verifyTrue(actMessage.contains("indexed successfully."),"Message does not match");

	}

	/**** Folder translate *****/

	@Test(priority = 81, enabled = true, dataProvider = "captureFormData")
	public void TestFolderTranslateAccountRef(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docType = "ScanDoc - Generic Scanned Document", docRef = "folder1Trans", accRef = "F1-005", miscRef = "",
				propRef = "", transRef = map.get("Folder1RefValue"),
				expFolderTransMSg = "Are you sure you want to translate all Documents", entityName = "Account";
		//entityUnderSearch = "Account"; // change according to folder ref
		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();
		Boolean createRef = true;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, createRef,getEnvVariable);

		home.clickOnSearchEntityAndSelect(entityUnderSearch);
		home.searchAndNavigateToEditEnity(entityName, accRef);
		String popupMsg = docTools.translateFolderReferenceTo(transRef);			//Added ok pop up locator in R531
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);
		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		navigationMenu.waitForFilterTextBoxSearchResult();
		String actTransRef = navigationMenu.getColumnValueFromTable("Account Ref");
		AssertionHelper.verifyText(actTransRef, transRef);
	}

	//complete
	@Test( priority=82,enabled = true)
	public void TestFolderTranslateMiscRef() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String docType="ScanDoc - Generic Scanned Document", docTrans2Ref="folder2Trans",
				accRef="", miscRef="F2-005",
				propRef="",transRef="F2-001R1",
				expFolderTransMSg = "Are you sure you want to translate all Documents Indexed",entityName="Misc";
		//entityUnderSearch="Misc";			//change according ot foleder ref

		String entityUnderSearch=ObjectReader.reader.getFolder2RefName();
		Boolean createRef=true;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docTrans2Ref, accRef, miscRef, propRef, createRef,getEnvVariable);
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				
		home.searchAndNavigateToEditEnity(entityName,miscRef);
		String popupMsg = docTools.translateFolderReferenceTo(transRef);
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);
		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docTrans2Ref);
		docTools.clickOnDocumentListButton();
		String actTransRef = navigationMenu.getColumnValueFromTable("Misc Ref");
		AssertionHelper.verifyText(actTransRef, transRef);
	}

	
	@Test(priority=83, enabled = true)
	public void TestFolderTranslatePropRef() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String docType="ScanDoc - Generic Scanned Document", docRef="folder3Trans",
				accRef="", miscRef="",
				propRef="F3-006",transRef="F3-001R3",
				expFolderTransMSg = "Are you sure you want to translate all Documents Indexed",entityName="Property";	//Misc
		//entityUnderSearch="Folder2";			
		String entityUnderSearch=ObjectReader.reader.getFolder3RefName();
		Boolean createRef=true;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, createRef,getEnvVariable);
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				
		home.searchAndNavigateToEditEnity(entityName,propRef);

		//docTools.clickOnEditFolderReferenceCapturePage(propRef);
		String popupMsg = docTools.translateFolderReferenceTo(transRef);
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);

		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		//navigationMenu.waitForFilterTextBox();
		capture.clickOnDocumentListBtn();
		String actTransRef = navigationMenu.getColumnValueFromTable("Property Ref");
		AssertionHelper.verifyText(actTransRef, transRef);
	}


	@Test(priority=84, enabled = true)
	public void TestAddDummyReferenceAll() throws InterruptedException { 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		folder1Prefix="X1"+generateRandomNumber(); String folder1Name="Folder1";
		folder2Prefix="X2"+generateRandomNumber(); String folder2Name="Folder2";
		folder3Prefix="X3"+generateRandomNumber(); String folder3Name="Folder3";
		getApplicationUrl();
		folderPage.navigateToAddDummyReference();
		//folderPage.selectAllAndDelete();
		folderPage.addDummyReference(folder1Prefix,"1","Folder1",false,getEnvVariable);
		folderPage.addDummyReference(folder2Prefix,"1","Folder2",false,getEnvVariable);
		folderPage.addDummyReference(folder3Prefix,"1","Folder3",false,getEnvVariable); 
		boolean folder1Presence = folderPage.verifyDummyRefAdded(folder1Prefix,folder1Name);
		boolean folder2Presence = folderPage.verifyDummyRefAdded(folder2Prefix,folder2Name);
		boolean folder3Presence = folderPage.verifyDummyRefAdded(folder3Prefix,folder3Name);
		AssertionHelper.verifyTrue(folder1Presence, "Checking for folder1 presence"); 
		AssertionHelper.verifyTrue(folder2Presence, "Checking for folder2 presence"); 
		AssertionHelper.verifyTrue(folder3Presence, "Checking for folder3 presence");
	}

	@Test(priority=85, enabled = true)
	public void TestFolderTranslateDummyAccountRef() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String docType="ScanDoc - Generic Scanned Document", docRef="f1DummyTrans"+generateRandomNumber(),
				miscRef="",
				propRef="",transRef="F1-001R1",entityName="Account",
				expFolderTransMSg = "Are you sure you want to translate all Documents Indexed"; 
		
		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		String accRef = folderPage.enterAndSelectDummyRef(entityUnderSearch);
		log.info("folder 1 dummy ref value is "+accRef);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, true,getEnvVariable);
		
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				//Added entity under search
		home.searchAndNavigateToEditEnity(entityName,accRef);
		String popupMsg = docTools.translateFolderReferenceTo(transRef);
		log.info("================="+popupMsg+"==================");
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);
		
		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		//navigationMenu.waitForFilterTextBox();
		capture.clickOnDocumentListBtn();
		String actTransRef = navigationMenu.getColumnValueFromTable("Account Ref");
		AssertionHelper.verifyText(actTransRef, transRef);
	}

	@Test(priority=86, enabled = true)
	public void TestFolderTranslateDummyMiscRef() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String docType="ScanDoc - Generic Scanned Document", docRef="f2DummyTrans"+generateRandomNumber(),accRef="",
				propRef="",transRef="F2-001R1",entityName="Misc",
				expFolderTransMSg = "Are you sure you want to translate all Documents Indexed"; 
		String entityUnderSearch=ObjectReader.reader.getFolder2RefName();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		String miscRef = folderPage.enterAndSelectDummyRef(entityUnderSearch);
		log.info("folder 2 dummy ref value is "+miscRef);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, true,getEnvVariable);
		
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				//Added entity under search
		home.searchAndNavigateToEditEnity(entityName,miscRef);
		String popupMsg = docTools.translateFolderReferenceTo(transRef);
		log.info("================="+popupMsg+"==================");
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);
		
		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		//navigationMenu.waitForFilterTextBox();
		capture.clickOnDocumentListBtn();
		String actTransRef = navigationMenu.getColumnValueFromTable("Misc Ref");
		AssertionHelper.verifyText(actTransRef, transRef);
	}

	@Test(priority=87, enabled = true)
	public void TestFolderTranslateDummyPropRef() throws InterruptedException { 
		test = ExtentTestManager.getTest();
		String docType="ScanDoc - Generic Scanned Document", docRef="f3DummyTrans"+generateRandomNumber(),
				miscRef="",accRef="",
				transRef="F3-001R3",
				expFolderTransMSg = "Are you sure you want to translate all Documents Indexed",entityName="Property"; 

		String entityUnderSearch=ObjectReader.reader.getFolder3RefName();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		String propRef = folderPage.enterAndSelectDummyRef(entityUnderSearch);
		log.info("folder 3 dummy ref value is "+propRef);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, true,getEnvVariable);
		
		home.clickOnSearchEntityAndSelect(entityUnderSearch);				//Added entity under search
		home.searchAndNavigateToEditEnity(entityName,propRef);
		String popupMsg = docTools.translateFolderReferenceTo(transRef);
		log.info("================="+popupMsg+"==================");
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);
		
		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		//navigationMenu.waitForFilterTextBox();
		capture.clickOnDocumentListBtn();
		String actTransRef = navigationMenu.getColumnValueFromTable("Property Ref");
		AssertionHelper.verifyText(actTransRef, transRef);
	}
	
	@Test(priority = 88,enabled =true)
	public void TestDeleteDummyReference() {
		getApplicationUrl();
		folderPage.navigateToAddDummyReference();
		
		folderPage.SearchDummyFolderRefAndDelete(folder1Prefix);
		sleepFor(2000);
		
		folderPage.SearchDummyFolderRefAndDelete(folder2Prefix);
		sleepFor(2000);
		
		folderPage.SearchDummyFolderRefAndDelete(folder3Prefix);
		sleepFor(2000);
	}

}

