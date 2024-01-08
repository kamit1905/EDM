package tests;

import java.util.Map;

import org.apache.log4j.Logger;
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
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.Base;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import utils.ExcelUtils;
import utils.ExtentReports.ExtentTestManager;

public class TestDocumentTools extends TestBase {
	public DocumentToolsPage docTools ;
	private Logger log = LoggerHelper.getLogger(TestCapturePage.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	AlertHelper alertHelper;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	AdminDocumentSection adminDocument;
	AdminUserSection adminUser;
	public LoginPage login;
	
	
	@BeforeClass
	public void setupClass()  {
		docTools = new DocumentToolsPage(driver);
		capture = new CapturePage(driver);
		home= new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		adminUser = new AdminUserSection(driver);
		verificationHelper = new VerificationHelper(driver);
		waitHelper = new WaitHelper(driver);
		xls = new ExcelReader();
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
	
	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}	
	
	//Verified with 522	
	@Test(priority=201,enabled = true, dataProvider="captureFormData")
	public void TestReindexDocument(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		  SoftAssert soft = new SoftAssert();
		  test.log(Status.INFO, "Running test with arguments : "+map.toString());
		  String filename = map.get("FileName"),
		  accountRefValue = map.get("AccountRefValue"),
				  routingType=map.get("RoutingType"),
				  routingTo=ObjectReader.reader.getUserName(),
				  documentType=map.get("DocumentType"),
				  protectiveMarker=map.get("ProtectiveMarker"),
				  docRef= map.get("DocRef")
				  ;
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
		  getApplicationUrl();
		  capture.selectSearchTab(); 
		  test.log(Status.INFO, "Search with doc ref "+map.get("DocRef"));
		  capture.searchWithCriteria("Doc Ref", map.get("DocRef"));
		  capture.clickOnDocumentListBtn();
		  capture.clickOnFirstItemOfIntray();
		  navigationMenu.clickOnIcon("Reindex document", "Document");
		  Thread.sleep(2000);
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
		  //capture.setAvailableTag();
		  //AssertionHelper.verifyTrue(capture.isTagDisplayed(),"Check if tag is displayed");
		  //test.log(Status.INFO, "Entering new folder refernec in order to create one");
		  
		  //capture.enterFolder1RefCapturePageAndSearch(accountRefValue,"Folder1_Ref");
		  
		  //capture.waitForRoutingToDDClickable();
		  //Thread.sleep(4000);// Route to DD takes time to load
		  capture.clickOnIndexDocument();
		  
		  //Change it to some concrete logic
		  //if(documentType.contains("Default") || documentType.contains("ScanDoc")) {
		  //capture.clickOkOnCofirmIndexDocument(); }
		 
          test.log(Status.INFO, "Clicked on index document");
          String message = capture.getSuccessfullReindexPopupMessage();
          AssertionHelper.verifyTrue(message.contains("re-indexed successfully."),"Message does not match");
          capture.clickOkOnSuccesfullReIndexPopup();
          test.log(Status.INFO, "Document re-indexed successffully");
          navigationMenu.clickOnHomePageIcon();
	}
	
	
	@DataProvider(name="docreReferenceData")
		public Object[][] docreReferenceData() throws Exception
		{
			Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");	
			return formData;
		}
		
		@Test(priority=202,enabled = true,dataProvider = "docreReferenceData" )
		public void TestReReferenceFlow(String doctype, String folder2Ref, String docref, String fileSystem) {
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			test.log(Status.INFO ,"======="+getEnvVariable+"==========");

			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docref );
			docTools = capture.clickOnIntrayListBtn();
			docTools.clickOnFirstItemInList();
			docTools.clickOnReReferenceButton();
			docTools.selectDocumentType(doctype,getEnvVariable);
			docTools.selectChkboxForFolder1KeepRef();
			docTools.enterFold2Ref(folder2Ref);	
			//docTools.clickOnApplyButton();
			if(getEnvVariable.equals("Sp1s")) {
				navigationMenu.clickOnIconMenu("Apply changes", "Actions");
			}else {
				docTools.clickOnApplyButton();
			}
			
			String content = docTools.getReferencePopupContent();
			AssertionHelper.verifyTrue(content.contains("Account Ref: Keep existing reference") && content.contains("Misc Ref: "+folder2Ref),
					"Content on Confirm re-reference popup is not proper");
			docTools.clickYesOnConfirmReferencePopUp();
			docTools.clickOkOnRereferenceSusccessfulPopup();
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docref );
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			AssertionHelper.verifyText(capture.getFolder2RefOfDocument(), folder2Ref);
			String expectedDocType = doctype.split(" - ")[0];
			AssertionHelper.verifyText(capture.getDocumentTypeOfDocument(),expectedDocType);
		}
		
	
	//pass
	@Test(priority=204,enabled = true, dataProvider="docreReferenceData")
	public void TestClearOnDocReRefrence(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			test.log(Status.INFO ,"======="+getEnvVariable+"==========");
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docref );
			capture.clickOnDocumentListBtn();
			docTools.clickOnFirstItemInList();
			docTools.clickOnReReferenceButton();
			docTools.selectChkboxForFolder1KeepRef();
			docTools.enterFold2Ref(folder2Ref);	
			//docTools.clickOnClearButton();
			if(getEnvVariable.equals("Sp1s")) {
				navigationMenu.clickOnIconMenu("Clear changes", "Actions");
			}else {
				docTools.clickOnClearButton();
			}
			
			Thread.sleep(4000);
			AssertionHelper.verifyFalse( verificationHelper.isElementSelected(docTools.chkFolder1Ref));
			AssertionHelper.verifyTrue( verificationHelper.isElementEnabled(docTools.txtFolder1Ref),"Verifying folder1 text is enabled");
			AssertionHelper.verifyText(verificationHelper.getText(docTools.txtFolder2Ref),"");
	}
	
	@Test(priority=205,enabled = true, dataProvider="docreReferenceData")
	public void TestCancelOnDocReRefrence(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
	test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docref );
			capture.clickOnDocumentListBtn();
			docTools.clickOnFirstItemInList();
			docTools.clickOnReReferenceButton();
			navigationMenu.waitForIcon("Cancel changes");
			navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
			navigationMenu.waitForIcon("Send the search criteria via email");
			AssertionHelper.verifyTextContains(navigationMenu.getNavbarText(), "Document Search");
		}
	
	//Pass
	@Test(priority=206,enabled = true, dataProvider="docreReferenceData")
	public void TestKeepReferenceOnDocReRefrence(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
	test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docref );
			capture.clickOnDocumentListBtn();
			docTools.clickOnFirstItemInList();
			docTools.clickOnReReferenceButton();
			docTools.selectChkboxForFolder1KeepRef();
			docTools.selectChkboxForFolder2KeepRef();
			Thread.sleep(3000);
			AssertionHelper.verifyFalse(docTools.isFolder1TextEnabled());
			AssertionHelper.verifyFalse(docTools.isFolder2TextEnabled());
	}
	
	//Incomplete
	@Test(priority=207,enabled = true, dataProvider="docreReferenceData")				//later true enable		
	public void TestDifferentFileSystemOnDocReRefrence(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
			test = ExtentTestManager.getTest();
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			String reRefDocRef= "diffFSDiffDoc";
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",reRefDocRef );
			capture.clickOnDocumentListBtn();
			docTools.clickOnFirstItemInList();
			docTools.clickOnReReferenceButton();
			docTools.selectFileSystem(fileSystem,getEnvVariable);
			Thread.sleep(2000);
			docTools.selectDocumentType(doctype,getEnvVariable);
			AssertionHelper.verifyFalse(verificationHelper.isElementEnabled(docTools.chkFolder1Ref));
			AssertionHelper.verifyFalse(verificationHelper.isElementEnabled(docTools.chkFolder2Ref));
			docTools.clickOnApplyButtonAndConfirmReReferencePopup();
			sleepFor(3000);     //Adding because getting unhandled alert exception
			getApplicationUrl();
			docTools.searchWithCriteriaAllFileSystem("Doc Ref",reRefDocRef,getEnvVariable );
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
	
		
	@Test(priority=208,enabled = false, dataProvider="docreReferenceData")			//true later
	public void TestMultipleDocumentOnDocReRefrence(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
	test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			String multipleDocRef = "%1%";
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", multipleDocRef);
			capture.clickOnDocumentListBtn();
			refreshPage();       //Added because getting blank page after search
			navigationMenu.clickOnSpecificRow("1");
			navigationMenu.clickOnSpecificRow("2");
			docTools.clickOnReReferenceButton();
			AssertionHelper.verifyText(navigationMenu.getNavbarText(), "Re-Reference");
			
	}
	
	
	@Test(priority=209,enabled = true, dataProvider = "docreReferenceData")
	public void TestFolderRefAddInDocReRefrence(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
	test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docref);
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Account Ref", "F1%");
			capture.clickOnDocumentListBtn();
			docTools.clickOnFirstItemInList();
			docTools.clickOnReReferenceButton();
			docTools.clickOnAddFolderReference("Account Ref");
			String navBartitle = navigationMenu.getNavbarText();
			navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
			String getFolderRef=ObjectReader.reader.getFolder1RefName();
			AssertionHelper.verifyText(navBartitle, "Add "+getFolderRef);						//Change according to folder ref [Acc or Account or Folder1]
	}

	
	@Test(priority=212,enabled = true)				
	public void TestDocumentDeleteCancelPopup() throws InterruptedException {
			test = ExtentTestManager.getTest();
			String expMsg = "Document(s) delete successfully.";
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Account Ref","F1%" );
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			docTools.clickOnDocumentDeleteCancel();
			AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Delete document", "Document"),"Verifying delete icon enabled");	
	}
	

	
	
	/*************Linking document****************/
	
	@Test(priority=217,enabled =true)				//true
	public void TestDocumentLinkingIntray()throws InterruptedException  {
		String docRefLinkIntraySrc = "docRefLinkIntraySrc",
		docRefLinkIntrayDest="docRefLinkIntrayDest";
		 String docRef = "AutoAllocation",
				 linkIntrayLabel = "Link Intray Test",
				 docRefLinkIntray="docRefLinkIntray%";
		 
		 String getEnvVariable = System.getProperty("propertyName");
		 System.out.println("getEnvVariable===================="+getEnvVariable);
				 
		 test = ExtentTestManager.getTest();
		 try {
			  getApplicationUrl();
			  capture.navigateAndCaptureDocWithParameters(null, null, null, docRefLinkIntraySrc, null,getEnvVariable);
			  capture.navigateAndCaptureDocWithParameters(null, null, null, docRefLinkIntrayDest, null,getEnvVariable);
			  capture.searchWithCriteria("Doc Ref", docRefLinkIntray);
			  capture.clickOnDocumentListBtn();
			 //capture.clickOnIntrayListBtn();
			 capture.clickOnFirstItemOfIntray();
			 docTools.clickOnLinkDocumentIcon();
			 if(!getEnvVariable.equals("Enterprise_Sp1s") || !getEnvVariable.equals("Enterprise_R520") || !getEnvVariable.equals("Enterprise_R530") || !getEnvVariable.equals("Enterprise_R531") || !getEnvVariable.equals("Enterprise_R540") || !getEnvVariable.equals("Enterprise_R541")) {
				 docTools.UnselectMultiSelectCheckbox(); 
			 }
			 sleepFor(2000);
			 docTools.clickOnAddIconLinkDocs(linkIntrayLabel);				//Changed locator for linking document
			 if(getEnvVariable.equals("Enterprise_Sp1s") || getEnvVariable.equals("Enterprise_R520") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540")) {
				 navigationMenu.clickOnSpecificRow("2");
			 }else {
				 navigationMenu.clickOnSpecificRow(getEnvVariable,"2");
			 }
			 
			 docTools.clickOnSaveLinking();
			 //Clicking ok on confirmation popup
			 String actMsg = docTools.clickOkOnDocLinkingPopup(getEnvVariable);
			 AssertionHelper.verifyTextContains(actMsg, "1 Document will be linked with Label: "+linkIntrayLabel);
			 
			 new HomePage(driver).refreshCurrentFileSystem();
			 
			 capture.searchWithCriteria("Doc Ref", docRefLinkIntrayDest);			//change docRefLinkIntrayDest to docRefLinkIntraySrc for release below 550					
			 capture.clickOnIntrayListBtn();
			 capture.clickOnFirstItemOfIntray();
			 docTools.clickOnIntrayLandingPageIcon();
			 String actlnkIntrayName = docTools.getLinkNameFromRefrencingTab("1");
			 AssertionHelper.verifyText(actlnkIntrayName, linkIntrayLabel);

		} catch (Exception e) {	
			getApplicationUrl();
			 new HomePage(driver).refreshCurrentFileSystem();
		}
	}

	
		
	@Test(priority=218,enabled =true,dependsOnMethods = {"TestDocumentLinkingIntray"})			//true
	public void TestViewLinkingUnderReferencing()throws InterruptedException  {
		 String docRef = "AutoAllocation",
				 linkLabel = "Link Intray Test",
				//docRefLinkIntray = "docRefLinkIntraySrc";
		 		docRefLinkIntray = "docRefLinkIntrayDest";					//Changes for below R550 release to docRefLinkIntraySrc
		 System.out.println("docRefLinkIntray "+docRefLinkIntray);
		 test = ExtentTestManager.getTest();
		 
		 String getEnvVariable = System.getProperty("propertyName");
		 System.out.println("getEnvVariable===================="+getEnvVariable);
		 
		  getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRefLinkIntray);					
		 capture.clickOnIntrayListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnIntrayLandingPageIcon();
		 String actlnkName = docTools.getLinkNameFromRefrencingTab("1");
		 AssertionHelper.verifyText(actlnkName, linkLabel);
		 navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
		 String actNavTitle = docTools.clickOnViewDocumetLandingPage();
		 try
		 {
		 AssertionHelper.verifyText(actNavTitle, "Viewer");
		 AssertionHelper.verifyTrue(docTools.isOmnviewShown(), "Verifying omniview is shown under referencing of link document");
		 }
		 finally{
		 windowHelper.closeAllTabsAndSwitchToMainWindow();
		 }
		}
	
	@Test(priority=219,enabled =true,dependsOnMethods = {"TestDocumentLinkingIntray"})				//true
	public void TestLaunchLinkingUnderReferencing()throws InterruptedException  {
		 String linkLabel = "Link Intray Test",
				  docRefLinkIntray = "docRefLinkIntrayDest";				//Changes for below R550 release to docRefLinkIntraySrc
		 //docRefLinkIntray = docRefLinkIntraySrc;
		 System.out.println("docRefLinkIntray "+docRefLinkIntray);
		 test = ExtentTestManager.getTest();
		 
		 String getEnvVariable = System.getProperty("propertyName");
		 System.out.println("getEnvVariable===================="+getEnvVariable);
		 
		  getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRefLinkIntray);
		 capture.clickOnIntrayListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnIntrayLandingPageIcon();
		 String actlnkName = docTools.getLinkNameFromRefrencingTab("1");
		 AssertionHelper.verifyText(actlnkName, linkLabel);
		 navigationMenu.clickOnSpecificRow(getEnvVariable,"last()");
		 AssertionHelper.verifyTrue(docTools.clickOnLaunchLinkedDocLandingPage("1"), "Verifying launch of doc under referencing of linked doc");
	}
		
				
		@Test(priority=220,enabled =true,dependsOnMethods = {"TestDocumentLinkingIntray"})				//true
	public void TestRemoveLinkingUnderReferencing()throws InterruptedException  {
		 String linkIntrayLabel = "Link Intray Test",
				 docRefLinkIntray = "docRefLinkIntraySrc",
				 docRefLinkIntrayDest = "docRefLinkIntrayDest";
		 //docRefLinkIntray = docRefLinkIntraySrc;
		 System.out.println("docRefLinkIntrayDest "+docRefLinkIntrayDest);			 
		 test = ExtentTestManager.getTest();
		  getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRefLinkIntrayDest);					//Change docRefLinkIntrayDest to docRefLinkIntray for release below 550
		 capture.clickOnIntrayListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnIntrayLandingPageIcon();
		 String actlnkIntrayName = docTools.getLinkNameFromRefrencingTab("1");
		 AssertionHelper.verifyText(actlnkIntrayName, linkIntrayLabel);
		 String actMsg = docTools.clickOnLinkedDeleteDocument();	
		 sleepFor(1000);
		 windowHelper.clickOkOnPopup();					//Added to click on Ok popup
		 sleepFor(1000);
		 windowHelper.clickOkOnPopup();
		 AssertionHelper.verifyTextContains(actMsg, "Delete the highlighted Document(s)?");
		 
		//Added because to avoid conflicts when we run multiple times
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRefLinkIntray);				//Changes docRefLinkIntray to docRefLinkIntrayDest for below 550
		 capture.clickOnIntrayListBtn();				//Added code of refresh		
		 capture.clickOnFirstItemOfIntray();
		 String msg=docTools.clickOnDocumentDelete();
	//AssertionHelper.verifyTextContains(msg, "Document(s) deleted successfully");			//Error message changed in R531
		
		}

		
		
		/******************* Check in checkout *************/
		
		@Test(priority=221,enabled =true)
		public void TestCheckedOutFunctionality()throws InterruptedException  {
			 test = ExtentTestManager.getTest();
			 //home.refreshCurrentFileSystem();					//Later remove
			 sleepFor(3000);
			 getApplicationUrl();
			 capture.searchWithCriteria("Doc Ref", "%csv");
			 //if no doc found then capture doc with docref
			 capture.clickOnDocumentListBtn();
			 capture.clickOnFirstItemOfIntray();
			 docTools.clickOnEditIconUnderVersionControl();
			 docTools.clickOnEditDocumentPopup("Later");
			 sleepFor(2000);
			 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");
			 
			 sleepFor(2000);
			 getApplicationUrl();
			 capture.searchWithCriteria("Doc Ref", "%csv");
			 capture.clickOnDocumentListBtn();
			 capture.clickOnFirstItemOfIntray();
			 docTools.clickOnUndoEditAndOk();
			 log.info("Popup message is "+docTools.getEditDocumentPopupMessage());			
			 sleepFor(2000);
			 
			 //Adding because for checked out purpose & verified in priority 224 & 225
			 getApplicationUrl();
			 capture.searchWithCriteria("Doc Ref", "%csv");
			 capture.clickOnDocumentListBtn();
			 capture.clickOnFirstItemOfIntray();
			 docTools.clickOnEditIconUnderVersionControl();
			 sleepFor(1000);
			 docTools.clickOnEditDocumentPopup("Later");
			 sleepFor(2000);
			 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");
		 }	
		
	
			@Test(priority=222,enabled =true)
	public void TestDocumentNotCheckedOutCurrently()throws InterruptedException  {
		 String expMsg = "The document is not checked out currently",
				 expDiscardMsg = "Discard Changes Successful.";
		 test = ExtentTestManager.getTest();
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", "%docFile%");
		 //if no doc found then capture doc with docref
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 AssertionHelper.verifyText(docTools.clickOnUndoEditAndGetMessage(), expMsg);
		 /**
		 docTools.clickOnEditIconUnderVersionControl();
		 docTools.ClickOnEditDocumentPopup("Save");
		 AssertionHelper.verifyText(docTools.clickOnU, expDiscardMsg); 
		**/
		}	

	@Test(priority=223,enabled =true)
	public void TestDocumentVersionFunctionalityCheckedOut()throws InterruptedException  {
		 test = ExtentTestManager.getTest();
		 String docFileName="109015.docx";
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", "%docFile%");
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnEditIconUnderVersionControl();
		 docTools.clickOnEditDocumentPopup("Save");
		 docTools.waitForUpdateDocumentForm();
		 docTools.clickOnFileUploadOnUpdateDocument();
		 docTools.enterCommentsUpdateDocuments("CommentAuto");
		 Thread.sleep(1000);
		 String expVersion = docTools.getRevisionUploadDocument();		//Added +1 because after update actual value is visible 
		 docTools.clickOnUpdateDocumentButton("Ok");				//Handled blank page scenerio here
		 String actVersion = docTools.getFirstDocumentVersion();
		 AssertionHelper.verifyText(actVersion, expVersion);
		}
	
	@Test(priority=224,enabled =true)
	public void TestCheckedOutDocListUnderCapture()throws InterruptedException  {				
		 test = ExtentTestManager.getTest();
		 getApplicationUrl();
		 capture.clickOnCaptureTab();
		 docTools.clickonEditableDocumentUnderCapture();
		 docTools.clickOnFirstItemInList();
		 Thread.sleep(500);
		 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Upload changes", "Actions"), "Checking if icon enabled for upload changes");
		 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Discard changes", "Actions"), "Checking if discard changes button enabled");
	
	
		 //Undo changes for delete purpose
		 /* NavigationMenu(driver).clickOnIcon("Discard changes", "Actions");
		 new WindowHelper(driver).getPopupMessageClickOk("Undo Edit");
		 sleepFor(2000);
		 new WindowHelper(driver).clickOnModalFooterButton("Ok");*/
	}
	
	//dependsOnMethods = {"TestCheckedOutFunctionality"}
	@Test(priority=225,enabled =true)
	public void TestCheckedOutDocListUnderAdmin()throws InterruptedException  {				
		 test = ExtentTestManager.getTest();
		 getApplicationUrl();
		 docTools.clickOnEditableDocumentUnderAdmin();
		 docTools.clickOnFirstItemInList();
		 Thread.sleep(500);
		 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Upload changes", "Actions"), "Checking if icon enabled for upload changes");
		 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Discard changes", "Actions"), "Checking if discard changes button enabled");
		 
		 //For Check in purpose adding again by amit
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", "%csv");
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnUndoEditAndOk();
		 log.info("Popup message is "+docTools.getEditDocumentPopupMessage());
		 sleepFor(2000);
	}
	
		/********Tags ******/
	
	
	//pass
	@Test(priority=230,enabled =true, dataProvider="captureFormData")
	public void TestAddTags(Map<String, String> map) {
		getApplicationUrl();
		String tag = map.get("Tag");
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Tag maintenance","Document");
		try{
		String actualTag = adminDocument.addTag(tag);
		AssertionHelper.verifyText(actualTag, tag);
		}
		catch (Exception e) {
			String actMessage = windowHelper.getPopupMessage();
			 AssertionHelper.verifyTextContains(actMessage, "already exists");
		}
	}
	
	
	@Test(priority=231,enabled =true)
	public void TestEditDeleteDisabledTags() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Tag maintenance","Document");
		if(getEnvVariable.equals("Sp1s")) {
			AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(adminDocument.editTagBtn));
			AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(adminDocument.deleteTagBtn));
		}else {
			AssertionHelper.verifyFalse(navigationMenu.isIconEnabled("Edit selected tag", "Action"));
			AssertionHelper.verifyFalse(navigationMenu.isIconEnabled("Delete selected tag", "Action"));
		}
	}
	
	@Test(priority=232,enabled =true, dataProvider="captureFormData")
	public void TestEditTags(Map<String, String> map) {
		getApplicationUrl();
		String tag = map.get("Tag");
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Tag maintenance","Document");
		navigationMenu.waitForAddIcon();
		docTools.clickOnFirstRecordOfTag();
		navigationMenu.clickOnIcon("Edit selected tag", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
		AssertionHelper.verifyText(navigationMenu.getNavbarText(), "Edit Tag");
		}
			
	@Test(priority=234,enabled =true, dataProvider="captureFormData")			
	public void TestSetTagsOnReIndexAssociatedDocs(Map<String, String> map) throws InterruptedException {
		  test = ExtentTestManager.getTest();
		  String tag= map.get("Tag");
		  getApplicationUrl();
		  capture.selectSearchTab(); 
		  test.log(Status.INFO, "Search with doc ref "+map.get("DocRef"));
		  capture.searchWithCriteria("Doc Ref", map.get("DocRef"));
		  capture.clickOnDocumentListBtn();
		  capture.clickOnFirstItemOfIntray();
		  navigationMenu.clickOnIcon("Reindex document", "Document");
		  navigationMenu.waitForIcon("Index the following changes");
		  Thread.sleep(2000);
		  capture.setAvailableTag();
		  AssertionHelper.verifyText(capture.getAvailableTag(), tag);
		  sleepFor(2000);       //Adding sleep 
		  capture.clickOnIndexDocument();
		}
	
	@Test(priority=235,enabled =true, dataProvider="captureFormData")			
	public void TestSetTagsOnMoreSearch(Map<String, String> map) throws InterruptedException {
		  test = ExtentTestManager.getTest();
		  String tag= map.get("Tag");
		  getApplicationUrl();
		  docTools.clickOnMoreSearch();
		  docTools.setTagInMoreSearch(tag);
		  /*String actualTag = capture.getAvailableTag();
		  AssertionHelper.verifyText(actualTag, tag);
		  sleepFor(4000);
		  capture.clickOnDocumentListBtn();
		  sleepFor(2000);
		  docTools.clickOnFirstItemInList();
		  String tagName = navigationMenu.getColumnValueFromTable("Tags").replaceAll("\\s", "");
		  AssertionHelper.verifyFalse(tagName.isEmpty() && tagName!=null);*/
	}
	
	@Test(priority=236,enabled =true, dataProvider="captureFormData")			
	public void TestRemoveTagsOnReIndexAssociatedDocs(Map<String, String> map) throws InterruptedException {
		 String tag= map.get("Tag");  
		test = ExtentTestManager.getTest();
		  getApplicationUrl();
		  capture.selectSearchTab(); 
		  test.log(Status.INFO, "Search with doc ref "+map.get("DocRef"));
		  capture.searchWithCriteria("Doc Ref", map.get("DocRef"));
		  capture.clickOnDocumentListBtn();
		  capture.clickOnFirstItemOfIntray();
		  navigationMenu.clickOnIcon("Reindex document", "Document");
		  Thread.sleep(2000);
		  navigationMenu.waitForIcon("Index the following changes");
		  capture.clickOnRemoveTag();
		  AssertionHelper.verifyText(capture.getTextAreaOfTag(), "");
		  
		//Adding again because while delete need to verify error message
		  capture.setAvailableTag();
		  sleepFor(2000);
		  AssertionHelper.verifyText(capture.getAvailableTag(), tag);		
		  capture.clickOnIndexDocument();
		}
	
		@DataProvider(name="documentTypeData")
		public Object[][] documentTypeData() throws Exception
		{
			Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","DocumentTypeData");	
			return formData;
		}
	
		@Test(priority=237,enabled =true, dataProvider="captureFormData")	  		 
		public void TestDeleteTagsAssociatedDocs(Map<String, String> map) {
			getApplicationUrl();
			String tag = map.get("Tag");
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Tag maintenance","Document");
			navigationMenu.waitForAddIcon();
			docTools.clickOnFirstRecordOfTag();
			docTools.deleteTag();
			sleepFor(3000);  
			String warnMsg = docTools.getDeletePopupMessage();
			      //AssertionHelper.verifyTextContains(warnMsg, "Documents are still associated with the selected tags");
			sleepFor(2000);  
			}
		
		@Test(priority=238,enabled =true, dataProvider="documentTypeData")		//true
		public void TestOneParentMultiChildTagsOn(Map<String, String> map) throws InterruptedException {
			 test = ExtentTestManager.getTest();
			  String tag= map.get("tag"),
					  child1Tag=map.get("child1Tag"),
					  child2Tag=map.get("child2Tag");
				String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable===================="+getEnvVariable);

			  getApplicationUrl();
			  System.out.println("Tag is "+tag);
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Tag maintenance","Document");
			try {
			String actualTag = adminDocument.addTag(tag);
			AssertionHelper.verifyText(actualTag, tag);
			adminDocument.addTag(child1Tag, tag,getEnvVariable);
			String actualChild1Tag = adminDocument.searchTagUsingName(child1Tag);
			//System.out.println("actualChild1Tag "+actualChild1Tag);
			refreshPage();     		//Adding because getting blank page
			AssertionHelper.verifyTextContains(actualChild1Tag, child1Tag);
			adminDocument.addTag(child2Tag, tag,getEnvVariable);
			String actualChild2Tag = adminDocument.searchTagUsingName(child2Tag);
			//System.out.println("actualChild2Tag "+actualChild2Tag);
			refreshPage();
			AssertionHelper.verifyTextContains(actualChild2Tag, child2Tag);
			}
			catch (Exception e) {
				String actMessage = windowHelper.getPopupMessage();
				 windowHelper.clickOkOnPopup();
				 AssertionHelper.verifyTextContains(actMessage, "already exists");
			}
			}
	

	@Test(enabled =false, dataProvider="documentTypeData")
	public void TestCaptureDocMultipleTags(Map<String, String> map) throws InterruptedException {
		  test = ExtentTestManager.getTest();
		  getApplicationUrl();
	
	}

	
	@Test(priority=239,enabled =true)
	public void TestDocumentSearchManageTags() throws InterruptedException {
		  test = ExtentTestManager.getTest();
		  getApplicationUrl();
		  capture.searchWithCriteria("Account Ref", "F1%");
		  capture.clickOnDocumentListBtn();
		  docTools.clickOnFirstItemInList();
		  docTools.clickOnManageTagsUnderDocument();
		  docTools.selectTagsFromAddRemoveTagsPopup();
		  windowHelper.clickOnModalFooterButton("Ok");
		  sleepFor(2000);
		  String tagName = navigationMenu.getColumnValueFromTable("Tags").replaceAll("\\s", "");
		  AssertionHelper.verifyFalse(tagName.isEmpty() && tagName!=null); 
	}
	
	@Test(priority = 240, enabled = true, dataProvider = "documentTypeData") // true
	public void TestDeleteTags(Map<String, String> map) {
		getApplicationUrl();
		String tag = map.get("tag");
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Tag maintenance", "Document");
		navigationMenu.waitForAddIcon();
		sleepFor(2000);
		adminDocument.searchTagUsingName(tag);
		docTools.clickOnFirstRecordOfTag();
		docTools.deleteTag();
		sleepFor(3000);  
		String warnMsg = docTools.getDeletePopupMessage();
		AssertionHelper.verifyTextContains(warnMsg, "Documents are still associated with the selected tags");
		sleepFor(2000);
	}
	
	@Test(priority = 241,enabled = true)
	public void ToAssignNextMailItemsToUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);	

		//There are no more shared In-tray items left to allocate
		for(int i=0;i<=10;i++) {
			new IntrayToolsPage(driver).clickOnNextMailButton();
			String getMsg = new IntrayToolsPage(driver).getNextPopupMessage();
			sleepFor(2000); 			
			if(getMsg.equals("There are no more shared In-tray items left to allocate")) {
				break;
			}
			
		}
	}
	
}
	

