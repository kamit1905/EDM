package tests;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

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
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import utils.ExtentReports.ExtentTestManager;

public class ATestRereferenceRegression extends TestBase {
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
	
	private String lockedDocRef;
	private String accRef;
	private String miscRef;
	private String propRef;

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
	
	@Test(priority = 1,enabled = true)
	public void VerifyErrorMessageWhileReReferenceOfCheckedOutDoc() throws InterruptedException {
		test = ExtentTestManager.getTest();
		test.log(Status.INFO, "Re-refencing document");

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		 String docRef = "Checked_"+generateRandomNumber();
		 getApplicationUrl();
		 capture.navigateAndCaptureDocWithParameters(null, "Dim.csv", null, docRef,null,getEnvVariable);
		 
		 //Check out the document
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRef);
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnEditIconUnderVersionControl();
		 docTools.clickOnEditDocumentPopup("Later");
		 sleepFor(2000);
		 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");
		 
		 //Verify error while reference of doc if it is checked out
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRef);
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("Re-Reference document", "Document");;
		 
		 String getErrorMsg = new WindowHelper(driver).getPopupMessageClickOk("Re-Reference");
		 test.log(Status.INFO, "Getting Error message as "+getErrorMsg);
		 AssertionHelper.verifyTextContains(getErrorMsg, "You cannot re-reference the document(s) until it has been checked back in.");
	}
	
	@Test(priority = 2,enabled = true)
	public void VerifyThatLockedDocumentCanRereference() throws InterruptedException {
		lockedDocRef="Lock"+generateRandomNumber();
		test = ExtentTestManager.getTest();
		test.log(Status.INFO, "Intray doc lock with docref "+lockedDocRef);
		String getFileSystem = ObjectReader.reader.getFileSystemName();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null,null, null, lockedDocRef, null,getEnvVariable);

		//Lock the document	 
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",lockedDocRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Lock document", "Document");
	
		String finalMsg=windowHelper.getPopupMessageClickOk("Lock Document");
		System.out.println("Final msg is "+finalMsg);
		AssertionHelper.verifyText(finalMsg, "Document(s) locked from further editing.");	
		sleepFor(3000);
		
		//Rereference the locked document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",lockedDocRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.enterFold1Ref("F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickYesOnConfirmReferencePopUp();
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		//AssertionHelper.verifyTrue(content.contains("Account Ref: F1-001R1") ,"Verifying the account ref content");
		AssertionHelper.verifyTrue(popContent.contains("You are about to modify 1 Document(s).") && popContent.contains("You have chosen the following:") && popContent.contains("File System: "+getFileSystem)
				&& popContent.contains("Document Type: ScanDoc") && popContent.contains("Account Ref: F1-001R1") && popContent.contains("Do you wish to continue?"), "Verifying content");

	}
	
	@Test(priority = 3,enabled = true,dataProvider = "docreReferenceData")
	public void VerifyLockedDocumentStatusInAnotherFSAfterReReference(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String docRef= "DiffDoc"+generateRandomNumber();
		test.log(Status.INFO, "Re-refencing document with docref "+docRef);
		
		//getApplicationUrl();
		//capture.navigateAndCaptureDocWithParameters(null,null, null, docRef, null);

		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",lockedDocRef );
		capture.clickOnDocumentListBtn();
		docTools.clickOnFirstItemInList();
		docTools.clickOnReReferenceButton();
		docTools.selectFileSystem(fileSystem,getEnvVariable);
		sleepFor(2000);
		docTools.selectDocumentType(doctype,getEnvVariable);
		sleepFor(2000);
		//AssertionHelper.verifyFalse(verificationHelper.isElementEnabled(docTools.chkFolder1Ref));
		//AssertionHelper.verifyFalse(verificationHelper.isElementEnabled(docTools.chkFolder2Ref));
		docTools.clickOnApplyButtonAndConfirmReReferencePopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		sleepFor(3000);     //Adding because getting unhandled alert exception
		getApplicationUrl();
		docTools.searchWithCriteriaAllFileSystem("Doc Ref",lockedDocRef,getEnvVariable );
		docTools.clickOnDocumentListButton();
		String expFileSystem = fileSystem.split("-")[0].trim();
		try {
		docTools.clickOnOtherFileSystem(expFileSystem);
		String actFileSystem = navigationMenu.getColumnValueFromTable("File System");			
		AssertionHelper.verifyTextContains(actFileSystem, expFileSystem);
		
		sleepFor(1000);
		AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Locked"), "Checkking if green tick present for Checked out column");

		}
		finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 4 ,enabled = true)				//Case 18, Case 19,Case 20
	public void VerifyKeepExistingRefMsgAndVerifySuccessMsg() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null,null, null, docRef, "F1-001R1",getEnvVariable);
		
		//Rereference the document & verify keep referencing msg			//Case 18,Case 19
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		//docTools.selectDocumentType("ScanDoc - Generic Scanned Document");
		docTools.selectChkboxForDocumentType();
		sleepFor(2000);
		docTools.selectChkboxForFolder1KeepRef();
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		
		//Clicking ok & verifying Rereference successfull msg
		new WindowHelper(driver).waitForPopup("Re-Reference");
		String rereferenceMsg = new WindowHelper(driver).getPopupMessage();
		AssertionHelper.verifyTextContains(rereferenceMsg, "Document(s) re-referenced successfully.");
		
		//Verifying keep existing msg while Rereference
		AssertionHelper.verifyTrue(popContent.contains("Document Type: Keep existing Document Type") && popContent.contains("Account Ref: Keep existing reference"), "Verifying Keep exisitng msg while Rereferencing document");
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		//Verifying the document after Rereference					//Case 20
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String getDocType = navigationMenu.getColumnValueFromTable("Document Type");			
		AssertionHelper.verifyTextContains(getDocType, "Default");
		
		//String getAccountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		//AssertionHelper.verifyTextContains(getAccountRef, "F1-001R1");
	}
	
	@Test(priority = 5, enabled = true)		//Check Tc with team   case23
	public void VerifyLockedStatusOfDocument() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		
		String docRef = "DOCLOC"+generateRandomNumber();
		String typeId = "Locked";
		getApplicationUrl();
		new AdminDocumentSection(driver).navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		new AdminDocumentSection(driver).enterTypeId(typeId);
		new AdminDocumentSection(driver).enterDescription("Document type");
		new AdminDocumentSection(driver).selectMailOption("Mail",getEnvVariable);
		new AdminDocumentSection(driver).selectMediaType("No Specified Media Type",getEnvVariable);
		new AdminDocumentSection(driver).checkEditable();
		new AdminDocumentSection(driver).checkLockDocumentOnIndex();
		sleepFor(1000);
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.clickOnAddIcon();
			
			
			getApplicationUrl();
			new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, typeId, docRef, null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docRef );
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			
			AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Locked"), "Checkking if green tick present for Checked out column");
		} catch (Exception e) {
			String actMessage = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyTextContains(actMessage, "already exists");
			
			getApplicationUrl();
			new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, typeId, docRef, null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docRef );
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			
			AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Locked"), "Checkking if green tick present for Checked out column");
		}

	}

	@Test(priority = 6,enabled = true)			//Case 24
	public void VerifyThatDocumentWillBeLockedAfterRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "LOCKRe"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		docTools.selectDocumentType("Locked - Document type",getEnvVariable);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickYesOnConfirmReferencePopUp();
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Locked"), "Checkking if green tick present for Checked out column");
	}
	
	@Test(priority = 7,enabled = true)
	public void VerifyAllowToRereferenceMultipleDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "MultiRe"+generateRandomNumber();
		String docRef1 = "MultiRe1"+generateRandomNumber();
		String getFileSystem = ObjectReader.reader.getFileSystemName();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef1, null,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref","MultiRe%" );
		capture.clickOnIntrayListBtn();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).SelectMultiSelectCheckbox();
			sleepFor(1000);
		}
		navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"2");
		new DocumentToolsPage(driver).clickOnReReferenceButton();
		new DocumentToolsPage(driver).selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickYesOnConfirmReferencePopUp();
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		AssertionHelper.verifyTrue(popContent.contains("You are about to modify 2 Document(s).") && popContent.contains("You have chosen the following:") && popContent.contains("File System: "+getFileSystem)
				&& popContent.contains("Document Type: ScanDoc") && popContent.contains("Account Ref: null") && popContent.contains("Do you wish to continue?"), "Verifying content");
		}
	
	@Test(priority = 8,enabled = true)
	public void VerifyRereferenceCancelAndNoBtn() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOCU"+generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnIntrayListBtn();
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementEnabled(new DocumentToolsPage(driver).reReferenceBtn));
		capture.clickOnFirstItemOfIntray();
		new DocumentToolsPage(driver).clickOnReReferenceButton();
		new DocumentToolsPage(driver).selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.clickOnApplyButton();
		
		docTools.clickNoOnConfirmReferencePopUp();
		sleepFor(2000);
		new DocumentToolsPage(driver).selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.clickOnApplyButton();
		docTools.clickCancelOnConfirmReferencePopUp();
		sleepFor(2000);
		
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickYesOnConfirmReferencePopUp();
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
	}
	
	@Test(priority = 9,enabled = true)				//Case 29,Case 30
	public void ChangeFolder1RefWhileRereferenceOfDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "ReFolder1"+generateRandomNumber();
		String accountRef = "A1"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef, accountRef,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.enterFold1Ref("F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		//Verifying content in Rereference pop up
		AssertionHelper.verifyTrue(popContent.contains("Document Type: Scan") && popContent.contains("Account Ref: F1-001R1"), "Verifying Keep exisitng msg while Rereferencing document");
		sleepFor(4000);
		
		//To verify the rereferenced document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String actFileSystem = navigationMenu.getColumnValueFromTable("Account Ref");
		AssertionHelper.verifyTextContains(actFileSystem, "F1-001R1");
	}
	
	@Test(priority = 10,enabled = true,dataProvider = "docreReferenceData")
	public void RereferenceDocumentToDiffFileSystem(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "ReFolder1"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(1000);
		docTools.selectFileSystem(fileSystem,getEnvVariable);
		docTools.selectDocumentType(doctype,getEnvVariable);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		//Search that document in destination file system and verify
		getApplicationUrl();
		docTools.searchWithCriteriaAllFileSystem("Doc Ref", docRef, getEnvVariable);
		docTools.clickOnDocumentListButton();
		String expFileSystem = fileSystem.split("-")[0].trim();
		//String latestFS = expFileSystem[0].trim();
		try {
			docTools.clickOnOtherFileSystem(expFileSystem);
			String actFileSystem = navigationMenu.getColumnValueFromTable("File System");
			AssertionHelper.verifyTextContains(actFileSystem, fileSystem);
		} finally {
			windowHelper.closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority = 11,enabled = true)				//Case 32
	public void ChangeFolder2RefWhileRereferenceOfDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "ReFolder2"+generateRandomNumber();
		String miscRef = "B1"+generateRandomNumber(),accRef="",propRef="";
		String docType="Default - General Default Document Type";
				
		Boolean createRef=true;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		//new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef, miscRef);
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, createRef,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.enterFold2Ref("F2-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		//Verifying content in Rereference pop up
		AssertionHelper.verifyTrue(popContent.contains("Document Type: Scan") && popContent.contains("Misc Ref: F2-001R1"), "Verifying Keep exisitng msg while Rereferencing document");
		//sleepFor(4000);
		
		//To verify the rereferenced document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String actFileSystem = navigationMenu.getColumnValueFromTable("Misc Ref");
		AssertionHelper.verifyTextContains(actFileSystem, "F2-001R1");
	}

	@Test(priority = 12,enabled = true)				//Case 33
	public void ChangeFolder3RefWhileRereferenceOfDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "ReFolder3"+generateRandomNumber();
		String propRef = "C1"+generateRandomNumber(),accRef="",miscRef="";
		String docType="Default - General Default Document Type";
				
		Boolean createRef=true;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		//new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef, miscRef);
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, createRef,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.enterFold3Ref("F3-001R3");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		//Verifying content in Rereference pop up
		AssertionHelper.verifyTrue(popContent.contains("Document Type: Scan") && popContent.contains("Property Ref: F3-001R3"), "Verifying Keep exisitng msg while Rereferencing document");
		//sleepFor(4000);
		
		//To verify the rereferenced document
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();

		String actFileSystem = navigationMenu.getColumnValueFromTable("Property Ref");
		AssertionHelper.verifyTextContains(actFileSystem, "F3-001R3");
	}

	@Test(priority = 13,enabled = true)				//Case34,35,36,37,38
	public void VerifyFreezesCheckboxOnRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "ReFolder3"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		propRef = "C1"+generateRandomNumber();
		accRef="A1"+generateRandomNumber();
		miscRef="B1"+generateRandomNumber();
		
		String docType="Default - General Default Document Type";
		Boolean createRef=true;
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, docRef, accRef, miscRef, propRef, createRef,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		docTools.selectChkboxForFileSystem();
		String fileSystemStatus =  new DocumentToolsPage(driver).ddFileSystemReReference.getAttribute("aria-disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(fileSystemStatus), "Checking freezing of file system dropdown");
		
		sleepFor(1000);
		docTools.selectChkboxForDocumentType();
		String documentTypeStatus =  new DocumentToolsPage(driver).documentTypeddl.getAttribute("aria-disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(documentTypeStatus), "Checking freezing of document type dropdown");
		
		
		sleepFor(1000);
		docTools.selectChkboxForFolder1KeepRef();
		String folder1RefStatus = new DocumentToolsPage(driver).txtFolder1Ref.getAttribute("disabled");
		test.log(Status.INFO, folder1RefStatus);
		AssertionHelper.verifyTrue(Boolean.parseBoolean(folder1RefStatus), "Checking freezing of folder1 Ref field");
		
		docTools.selectChkboxForFolder2KeepRef();
		sleepFor(1000);
		String folder2RefStatus =new DocumentToolsPage(driver).txtFolder2Ref.getAttribute("disabled");
		test.log(Status.INFO, folder2RefStatus);
		AssertionHelper.verifyTrue(Boolean.parseBoolean(folder2RefStatus), "Checking freezing of folder2 Ref field");
		
		docTools.selectChkboxForFolder3KeepRef();
		sleepFor(1000);
		String folder3RefStatus =new DocumentToolsPage(driver).txtFolder3Ref.getAttribute("disabled");
		test.log(Status.INFO, folder3RefStatus);
		AssertionHelper.verifyTrue(Boolean.parseBoolean(folder3RefStatus), "Checking freezing of folder3 Ref field");
		
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		AssertionHelper.verifyTrue(popContent.contains("You are about to modify 1 Document(s).") && popContent.contains("You have chosen the following:") && popContent.contains("File System: Keep existing file system")
				&& popContent.contains("Document Type: Keep existing Document Type") && popContent.contains("Account Ref: Keep existing reference") && popContent.contains("Misc Ref: Keep existing reference") && popContent.contains("Property Ref: Keep existing reference") 
				&& popContent.contains("Alt Account Ref: null")  && popContent.contains("Do you wish to continue?"), "Verifying content");

	}
	
	@Test(priority = 14,enabled = true,dataProvider = "docreReferenceData")			//Case 39,40
	public void ApplicationDisablesKeepRereferenceCheckbox(String doctype, String folder2Ref, String docref, String fileSystem) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "Keep"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		docTools.selectFileSystem(fileSystem,getEnvVariable);
		
		String fileSystemChkboxStatus = new DocumentToolsPage(driver).chkFileSystem.getAttribute("disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(fileSystemChkboxStatus), "Verifying the file system checkbox");		
		
		String documentTypeStatus = new DocumentToolsPage(driver).chkDocumentType.getAttribute("disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(documentTypeStatus), "Verifying the document type checkbox");
		
		String folder1RefStatus = new DocumentToolsPage(driver).chkFolder1Ref.getAttribute("disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(folder1RefStatus), "Verifying the folder1 ref checkbox");
		
		String folder2RefStatus = new DocumentToolsPage(driver).chkFolder2Ref.getAttribute("disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(folder2RefStatus), "Verifying the folder2 ref checkbox");

		String folder3RefStatus = new DocumentToolsPage(driver).chkFolder2Ref.getAttribute("disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(folder3RefStatus), "Verifying the folder3 ref checkbox");

		String altAccountRefStatus = new DocumentToolsPage(driver).chkAltAccountRef.getAttribute("disabled");
		AssertionHelper.verifyTrue(Boolean.parseBoolean(altAccountRefStatus), "Verifying the alt account ref checkbox");

		sleepFor(1000);
		docTools.selectDocumentType(doctype,getEnvVariable);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		AssertionHelper.verifyTrue(popContent.contains("You are about to modify 1 Document(s).") && popContent.contains("You have chosen the following:") && popContent.contains("File System: "+fileSystem),"Verifying Rereference pop up content");
	}
	
	@Test(priority = 15,enabled = true)				//Case 47,50,52,53,54
	public void SearchFolder1RefUsingLookupAndSelectWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "LookUpFolde1"+generateRandomNumber();
		String getFolder1Ref=ObjectReader.reader.getFolder1RefName();
		String entityName="Account";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		
		click(docTools.folder1RefLookUpBtn, "Clicking on Folder1 Ref Look up button");
		new NavigationMenu(driver).waitForIcon("Cancel the Search", "Actions");
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementEnabled(new FolderFlagReference(driver).selectButtonOnFolderRef));
		
		new FolderFlagReference(driver).SearchAndSelectFolderReference(entityName, "F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actFolder1Ref = navigationMenu.getColumnValueFromTable("Account Ref");			
		AssertionHelper.verifyTextContains(actFolder1Ref, "F1-001R1");
	}
	
	@Test(priority = 16,enabled = true)			//Case 48,55,57,58,59
	public void SearchFolder2RefUsingLookupAndSelectWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "LookUpFolder2"+generateRandomNumber();
		String getFolder2Ref=ObjectReader.reader.getFolder2RefName();
		String entityName="Misc";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		
		click(docTools.folder2RefLookUpBtn, "Clicking on Folder2 Ref Look up button");
		new NavigationMenu(driver).waitForIcon("Cancel the Search", "Actions");
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementEnabled(new FolderFlagReference(driver).selectButtonOnFolderRef));

		new FolderFlagReference(driver).SearchAndSelectFolderReference(entityName, "F2-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actFolder1Ref = navigationMenu.getColumnValueFromTable("Misc Ref");			
		AssertionHelper.verifyTextContains(actFolder1Ref, "F2-001R1");
	}
	
	@Test(priority = 17,enabled = true)				//Case 49,60,62
	public void SearchFolder3RefUsingLookupAndSelectWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "LookUpFolder3"+generateRandomNumber();
		String getFolder3Ref=ObjectReader.reader.getFolder3RefName();
		String entityName="Property";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		
		click(docTools.folder3RefLookUpBtn, "Clicking on Folder3 Ref Look up button");
		new NavigationMenu(driver).waitForIcon("Cancel the Search", "Actions");
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementEnabled(new FolderFlagReference(driver).selectButtonOnFolderRef));
		
		new FolderFlagReference(driver).SearchAndSelectFolderReference(entityName, "F3-001R3");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		sleepFor(2000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actFolder1Ref = navigationMenu.getColumnValueFromTable("Property Ref");			
		AssertionHelper.verifyTextContains(actFolder1Ref, "F3-001R3");
	}
	
	@Test(priority = 18,enabled = true)				//Case 51,56,61
	public void AddFoleder1Folder2Folder3RefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DocRefe"+generateRandomNumber();
		String folder1Ref = "RA"+generateRandomNumber();
		String folder2Ref = "RM"+generateRandomNumber();
		String folder3Ref = "RP"+generateRandomNumber();
		
		String accountRef = ObjectReader.reader.getFolder1RefName();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String propRef = ObjectReader.reader.getFolder3RefName();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, folder1Ref, "Entering folder1 ref as"+folder1Ref);
		navigationMenu.clickOnIcon("Save changes made to "+accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, folder2Ref, "Entering folder2 ref as"+folder2Ref);
		navigationMenu.clickOnIcon("Save changes made to "+miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, folder3Ref, "Entering folder3 ref as"+folder3Ref);
		navigationMenu.clickOnIcon("Save changes made to "+propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);		
		sleepFor(3000);
		
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
	}
	
	@Test(enabled = true,priority =19)
	public void RerefereceTest() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC"+generateRandomNumber();
		String entityName="Account";
		String accountRef = ObjectReader.reader.getFolder1RefName();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();

		//Click on add & click on cancel
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		new NavigationMenu(driver).clickOnCancelIcon();
		//new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference("Re-Reference");
		sleepFor(3000);
		
		//Verify error message while edit of folder1 ref
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder1RefEditButton);
		String getErrorMsg = new WindowHelper(driver).getPopupMessageClickOk("Re-Reference");
		AssertionHelper.verifyText(getErrorMsg, "Please enter a value before clicking the Edit button");
		sleepFor(3000);
		
		//Click on cancel from folder1 ref edit page 
		docTools.enterFold1Ref("F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder1RefEditButton);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(accountRef);
		new NavigationMenu(driver).clickOnCancelIcon();
		//new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference("Re-Reference");
		sleepFor(3000);
		
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);		
		sleepFor(3000);
		
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		//sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);

	}
		
	@Test(priority = 21,enabled = true)
	public void TranslateFolder1RefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC"+generateRandomNumber();
		String accountRef = ObjectReader.reader.getFolder1RefName();
		String folder1Ref = "FG1"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, folder1Ref, "Entering folder1 ref as"+folder1Ref);
		navigationMenu.clickOnIcon("Save changes made to "+accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		//Translate folder1 ref while rereference
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder1RefEditButton);
		sleepFor(3000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(accountRef);
		String popupMsg = docTools.translateFolderReferenceTo("F1-001R1");			
		AssertionHelper.verifyTextContains(popupMsg, "Are you sure you want to translate all Documents");
		sleepFor(2000);
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", folder1Ref);
		new ActionHelper(driver).pressTab();
		String getMsg = driver.findElement(By.xpath("//div[@id='FlexibleLookedUpFolderDetails']")).getText();
		AssertionHelper.verifyTextContains(getMsg, accountRef+" not found.");
		capture.clickOnDocumentListBtn();
	}

	@Test(priority = 22,enabled = true)
	public void TranslateFolder2RefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC2"+generateRandomNumber();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String folder2Ref = "FG2"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, folder2Ref, "Entering folder2 ref as"+folder2Ref);
		navigationMenu.clickOnIcon("Save changes made to "+miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		//Translate folder2 ref while rereference
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder2RefEditButton);
		sleepFor(3000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(miscRef);
		String popupMsg = docTools.translateFolderReferenceTo("F2-001R1");			
		AssertionHelper.verifyTextContains(popupMsg, "Are you sure you want to translate all Documents");
		sleepFor(2000);
		
		getApplicationUrl();
		capture.searchWithCriteria("Misc Ref", folder2Ref);
		new ActionHelper(driver).pressTab();
		String getMsg = driver.findElement(By.xpath("//div[@id='FlexibleLookedUpFolderDetails']")).getText();
		AssertionHelper.verifyTextContains(getMsg, miscRef+" not found.");
		capture.clickOnDocumentListBtn();
	}

	@Test(priority = 23,enabled = true)
	public void TranslateFolder3RefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC3"+generateRandomNumber();
		String propRef = ObjectReader.reader.getFolder3RefName();
		String folder3Ref = "FG3"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, folder3Ref, "Entering folder3 ref as"+folder3Ref);
		navigationMenu.clickOnIcon("Save changes made to "+propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		//Translate folder1 ref while rereference
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder3RefEditButton);
		sleepFor(3000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(propRef);
		String popupMsg = docTools.translateFolderReferenceTo("F3-001R3");			
		AssertionHelper.verifyTextContains(popupMsg, "Are you sure you want to translate all Documents");
		sleepFor(2000);
		
		getApplicationUrl();
		capture.searchWithCriteria("Property Ref", folder3Ref);
		new ActionHelper(driver).pressTab();
		String getMsg = driver.findElement(By.xpath("//div[@id='FlexibleLookedUpFolderDetails']")).getText();
		AssertionHelper.verifyTextContains(getMsg, propRef+" not found.");
		capture.clickOnDocumentListBtn();
	}
	
	@Test(priority = 24,enabled = true)			//Bug 112607
	public void RereferenceMultipleDocument() throws InterruptedException {
		test = ExtentTestManager.getTest();
		 String getEnvVariable = System.getProperty("propertyName");
		 System.out.println("getEnvVariable===================="+getEnvVariable);

		String docRef1 = "MULRT"+generateRandomNumber();
		String docRef2 = "MULRT"+generateRandomNumber();
		String docRef3 = "MULRT"+generateRandomNumber();
		
		String accRef = "ACC"+generateRandomNumber();
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef1,accRef,getEnvVariable);

		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef2,accRef,getEnvVariable);

		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef3,accRef,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Account Ref",accRef);
		capture.clickOnDocumentListBtn();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).SelectMultiSelectCheckbox();
			sleepFor(1000);
		}
		navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"2");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"3");
		
		docTools.clickOnReReferenceButton();
		sleepFor(2000);
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		sleepFor(2000);
		docTools.selectChkboxForFolder1KeepRef();
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
	}

	@Test(priority = 25,enabled = true)		//case 71,72,73
	public void GoToTranslateFolder1RefAndDeletedRefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC"+generateRandomNumber();
		String accountRef = ObjectReader.reader.getFolder1RefName();
		String folder1Ref = "FG"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, folder1Ref, "Entering folder1 ref as"+folder1Ref);
		navigationMenu.clickOnIcon("Save changes made to "+accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder1RefEditButton);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(accountRef);
		new NavigationMenu(driver).clickOnIconUnderAction("Translate");
		sleepFor(3000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTranslateTitleWhileRereference(accountRef);
		new NavigationMenu(driver).clickOnCancelIcon();
		sleepFor(2000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(accountRef);
		new NavigationMenu(driver).clickOnDeleteIcon();
		sleepFor(3000);
		//new WindowHelper(driver).waitForPopup("Not found");
		//new WindowHelper(driver).clickOnModalFooterButton("Cancel");
		click(new DocumentToolsPage(driver).cancelDeleteBtnWhileReReferenceOfDOc, "Clicking on cancel button");
		sleepFor(3000);
		navigationMenu.clickOnCancelIcon();
		new AlertHelper(driver).acceptAlertIfPresent();
		
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
	}
	
	@Test(priority = 26,enabled = true)		//Case 74,75,77,80,81
	public void GoToTranslateFolder2RefAndDeletedRefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC"+generateRandomNumber();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String folder2Ref = "MG"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, folder2Ref, "Entering folder2 ref as"+folder2Ref);
		navigationMenu.clickOnIcon("Save changes made to "+miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder2RefEditButton);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(miscRef);
		new NavigationMenu(driver).clickOnIconUnderAction("Translate");
		sleepFor(3000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTranslateTitleWhileRereference(miscRef);
		new NavigationMenu(driver).clickOnCancelIcon();
		sleepFor(2000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(miscRef);
		new NavigationMenu(driver).clickOnDeleteIcon();
		sleepFor(3000);
		//new WindowHelper(driver).waitForPopup("Not found");
		//new WindowHelper(driver).clickOnModalFooterButton("Cancel");
		click(new DocumentToolsPage(driver).cancelDeleteBtnWhileReReferenceOfDOc, "Clicking on cancel button");
		sleepFor(3000);
		navigationMenu.clickOnCancelIcon();
		new AlertHelper(driver).acceptAlertIfPresent();
		
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
	}

	@Test(priority = 27,enabled = true)		//Case 83,84,85,86,88,89,90
	public void GoToTranslateFolder3RefAndDeletedRefWhileRereference() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRef = "DOC"+generateRandomNumber();
		String propRef = ObjectReader.reader.getFolder3RefName();
		String folder3Ref = "PG"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, docRef,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef );
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnReReferenceButton();
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, folder3Ref, "Entering folder3 ref as"+folder3Ref);
		navigationMenu.clickOnIcon("Save changes made to "+propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);
		
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).folder3RefEditButton);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(propRef);
		new NavigationMenu(driver).clickOnIconUnderAction("Translate");
		sleepFor(3000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTranslateTitleWhileRereference(propRef);
		new NavigationMenu(driver).clickOnCancelIcon();
		sleepFor(2000);
		new DocumentToolsPage(driver).WaitForEditFolderRefTitleWhileRereference(propRef);
		new NavigationMenu(driver).clickOnDeleteIcon();
		sleepFor(3000);
		//new WindowHelper(driver).waitForPopup("Not found");
		//new WindowHelper(driver).clickOnModalFooterButton("Cancel");
		click(new DocumentToolsPage(driver).cancelDeleteBtnWhileReReferenceOfDOc, "Clicking on cancel button");
		sleepFor(3000);
		navigationMenu.clickOnCancelIcon();
		new AlertHelper(driver).acceptAlertIfPresent();
		
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
	}

	@Test(priority = 28,enabled = true)			//Bug 112769
	public void RereferenceFolder2RefMandatoryOnFS() throws InterruptedException {
		String FSName = home.getCurrentFileSystemName().split("-")[0].trim();
		String docRef = "BUG"+generateRandomNumber();
		String miscRef = "GT"+generateRandomNumber();
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);		
		try {
			//Mark folder2 ref mandatory on FS level
			getApplicationUrl();		
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("File System maintenance","File System");
			navigationMenu.searchInFilter(FSName);
			navigationMenu.clickOnFilteredRow(new FileSystemSection(driver).fileSystemTableId);
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Save changes");
			
			new FileSystemSection(driver).ClickOnNavigationBar("Folders");
			new FileSystemSection(driver).selectMandatoryFolder(false, true, false);
			sleepFor(1000);
			navigationMenu.clickOnSaveIcon();
			new WindowHelper(driver).waitForPopup("Update Folder Mandatory");
			new WindowHelper(driver).clickOnModalFooterButton("Yes");
			
			navigationMenu.waitForAddIcon();
			home.refreshCurrentFileSystem();
			sleepFor(3000);
			
			//Capture document with necessary details
			getApplicationUrl();
			new CapturePage(driver).navigateAndCaptureDocWithCreateRefParameters("Default", docRef, "", miscRef, "", true,getEnvVariable);
			
			//Remove folder2 mandatory field on FS
			getApplicationUrl();		
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("File System maintenance","File System");
			navigationMenu.searchInFilter(FSName);
			navigationMenu.clickOnFilteredRow(new FileSystemSection(driver).fileSystemTableId);
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Save changes");
			
			new FileSystemSection(driver).ClickOnNavigationBar("Folders");
			new FileSystemSection(driver).selectMandatoryFolder(false, true, false);
			sleepFor(1000);
			navigationMenu.clickOnSaveIcon();
			new WindowHelper(driver).waitForPopup("Update Folder Mandatory");
			new WindowHelper(driver).clickOnModalFooterButton("Yes");

			navigationMenu.waitForAddIcon();
			home.refreshCurrentFileSystem();
			sleepFor(3000);
			
			//Rereference the document
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docRef );
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			docTools.clickOnReReferenceButton();
			docTools.selectChkboxForDocumentType();
			sleepFor(2000);
			docTools.selectChkboxForFolder1KeepRef();
			docTools.clickOnApplyButton();
			String popContent = docTools.getReferencePopupContent();
			docTools.clickYesOnConfirmReferencePopUp();
			test.log(Status.INFO, "Content of ReReference pop up as"+popContent);
			docTools.clickOkOnRereferenceSusccessfulPopup();
			new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);

		}finally {
			
			//Remove folder2 mandatory field on FS
			getApplicationUrl();		
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("File System maintenance","File System");
			navigationMenu.searchInFilter(FSName);
			navigationMenu.clickOnFilteredRow(new FileSystemSection(driver).fileSystemTableId);
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Save changes");
			
			new FileSystemSection(driver).ClickOnNavigationBar("Folders");
			new FileSystemSection(driver).selectMandatoryFolder(false, true, false);
			sleepFor(1000);
			navigationMenu.clickOnSaveIcon();
			new WindowHelper(driver).waitForPopup("Update Folder Mandatory");
			new WindowHelper(driver).clickOnModalFooterButton("Yes");

			navigationMenu.waitForAddIcon();
			home.refreshCurrentFileSystem();
			sleepFor(3000);
		}
	}

}
