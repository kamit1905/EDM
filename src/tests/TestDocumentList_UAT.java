package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Bundle.Bundling;
import utils.ExtentReports.ExtentTestManager;

/*
 * Suite created by Sagar
 */

public class TestDocumentList_UAT extends TestBase {

	public CapturePage capture;
	public HomePage home;
	public static NavigationMenu navigationMenu;
	public AdminUserSection adminUser;
	public VerificationHelper verificationHelper;
	public LoginPage login;
	public AdminUserSection adminUserSection;
	public WindowHelper windowHelper;
	public WaitHelper waitHelper;
	public DocumentToolsPage docTools;
	public Bundling bundle;
	public IntrayToolsPage intrayTools;
	public AdminDocumentSection adminDoc;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	private String docRef;
	private String docRef1;
	private String docRef2;
	private String docRef3;
	private String docRef4;
	private String docRefReIndex;
	private String randomNo = generateRandomNumber();
	
	private String accRefIntray = "";
	private String miscRefIntray = "";
	private String propRefIntray = "";

	@BeforeClass
	public void setupClass() {
		intrayTools = new IntrayToolsPage(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDoc = new AdminDocumentSection(driver);
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		verificationHelper = new VerificationHelper(driver);
		adminUser = new AdminUserSection(driver);
		docTools = new DocumentToolsPage(driver);
		bundle = new Bundling(driver);
	}

	@Test(priority = 1, enabled = true)
	public void TestOpenADocumentList() throws InterruptedException {
		docRef = "DR0" + randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null, null, null, getEnvVariable);
		sleepFor(2000);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
	}

	@Test(priority = 2, enabled = true)
	public void TestViewDocumentInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
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

	@Test(priority = 3, enabled = true)
	public void TestLaunchDocumentInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();

		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");

	}

	@Test(priority = 4, enabled = true)
	public void TestCopyDocumentInDocumentList() throws InterruptedException {
		docRef3 = "DR3" + randomNo;

		String docType = "Default - General Default Document Type";
		String accRef = "AR" + randomNo, miscRef = "MR" + randomNo,
				propRef = "PR" + randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Create a copy of document", "Document");
		waitHelper.waitForElementVisible(capture.docRefTxt, 45, 1);
		AssertionHelper.verifyFalse(verificationHelper.isElementDisplayedByEle(capture.fileUploadIcon),
				"Verifying file upload icon is not present");

		capture.enterDocRef(docRef3);
		capture.enterIndexingInformation("0", accRef, miscRef, propRef);
		//new JavaScriptHelper(driver).scrollToBottom();
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);

		capture.clickOnIndexDocument();
		new WindowHelper(driver).waitForPopup("Copy");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(3000);
		// verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef3);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef3, ActualDocumentRef);
		
	}

	@Test(priority = 5, enabled = true)
	public void TestDeleteDocumentInDocumentList() throws InterruptedException {
		docRef1 = "DR1" + randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//		adding captured document to bundle
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef1, null, null, null, getEnvVariable);
		sleepFor(2000);

// 		Delete document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Delete document", "Document");
		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg1);
		new WindowHelper(driver).clickOkOnPopup();

// 		search Deleted document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);

// 		verify document is deleted
//		String verifyEmptyTable = capture.verifyEmptyTable.getText();
		String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("searchResultDataTable");
		String expMsgInEmptyTable = "No items available";
		AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);
	}

	@Test(priority = 6, enabled = true)
	public void TestViewDocumentLandingPageInDocumentList() throws InterruptedException {
		String expMsg = "Document Landing Page";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Document landing page", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl  , 35, 1);
		
		String actMsg = navigationMenu.navBarTitleLbl.getText();
		AssertionHelper.verifyText(actMsg, expMsg);
	}

	@Test(priority = 7, enabled = true)
	public void TestAddNewIndexToDocumentInDocumentList() throws InterruptedException {
		String expMsg = "Capture Document";
		String docRef = "ReFolder2" + randomNo;
		String accRef = "AC1" + randomNo, miscRef = "MR1" + randomNo,
				propRef = "PR1" + randomNo;
		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null, null, null, getEnvVariable);
		sleepFor(2000);

//		For verification store the Guid and Physical Doc Guid value
		getApplicationUrl();
		//sleepFor(3000);
		capture.searchWithCriteria("Doc Ref", docRef);
		//sleepFor(3000);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		String actPhysicalGuidColumnValue1 = navigationMenu.getColumnValue("Physical Doc Guid");
		String actGuidColumnValue1 = navigationMenu.getColumnValue("Guid");			
		sleepFor(2000);
		
//		Add new index to document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Add additional index to document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);

		String actMsg = navigationMenu.navBarTitleLbl.getText();
		AssertionHelper.verifyText(actMsg, expMsg);
		capture.enterIndexingInformation("0", accRef, miscRef, propRef);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.clickOnIndexDocument();

		try {
			waitHelper.waitForElement(capture.successullyIndexMsg, 10);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}

//		verify new index is added
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Account Ref"), accRef);
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Property Ref"), propRef);	
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Doc Ref"), docRef);	
		
		String actPhysicalGuidColumnValue2 = navigationMenu.getColumnValue("Physical Doc Guid");
		String actGuidColumnValue2 = navigationMenu.getColumnValue("Guid");				
		sleepFor(2000);	
		AssertionHelper.verifyText(actPhysicalGuidColumnValue1, actPhysicalGuidColumnValue2);	
		boolean actGuidCompare = actGuidColumnValue1.equals(actGuidColumnValue2);;
		AssertionHelper.verifyFalse(actGuidCompare);
	}

	@Test(priority = 8, enabled = true)
	public void TestExportDocumentDocumentOnlyInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		sleepFor(2000);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		intrayTools.clickOnExportDocument();
		String fileName = intrayTools.getExportFileName();
		windowHelper.waitForModalFormDialog("Export");
		intrayTools.selectFromExportContentDropdown("Document Only", getEnvVariable);
//    	intrayTools.selectFileFormat("Save Tiff file(s) as PDF",getEnvVariable);
		sleepFor(2000);
		windowHelper.clickOnModalFormFooterButton("Ok");
		Thread.sleep(4000);

		boolean ispdfDocExist = navigationMenu.isDownloadedFileExist(fileName + ".pdf");
		AssertionHelper.verifyTrue(ispdfDocExist, "Verifying pdf file exist");
	}

	@Test(priority = 9, enabled = true)
	public void TestExportDocumentMetadataOnlyInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		sleepFor(2000);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		intrayTools.clickOnExportDocument();
		String fileName = intrayTools.getExportFileName();
		intrayTools.selectFromExportContentDropdown("Metadata Only", getEnvVariable);
		String fileName1 = intrayTools.getExportFileName();
		log.info("Value of filename " + fileName1);
		intrayTools.waitTillMetadataFileTypeVisible();
		windowHelper.clickOnModalFormFooterButton("Ok");
		Thread.sleep(4000);

		boolean ispdfDocExist1 = navigationMenu.isDownloadedFileExist(fileName1 + "_Metadata.pdf");
		AssertionHelper.verifyTrue(ispdfDocExist1, "Verifying pdf file exist");
	}

	@Test(priority = 10, enabled = true)
	public void TestExportDocumentBothDocumentAndMetadataInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		sleepFor(2000);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		intrayTools.clickOnExportDocument();
		String fileName = intrayTools.getExportFileName();
		intrayTools.selectFromExportContentDropdown("Both Document And Metadata", getEnvVariable);
		String fileName2 = intrayTools.getExportFileName();
		log.info("Value of filename " + fileName);
		intrayTools.waitTillMetadataFileTypeVisible();
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToBottom();
		windowHelper.clickOnModalFormFooterButton("Ok");
		Thread.sleep(4000);

		boolean istiffDocExist2 = navigationMenu.isDownloadedFileExist(fileName2 + ".tiff");
		boolean ispdfDocExist2 = navigationMenu.isDownloadedFileExist(fileName2 + "_Metadata" + ".pdf");
		AssertionHelper.verifyTrue(ispdfDocExist2, "Verifying pdf file exist");
		AssertionHelper.verifyTrue(istiffDocExist2, "Verifying tiff file exist");
	}

	@Test(priority = 11, enabled = true)
	public void TestViewDocumentIntrayDetailsInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Intray details", "Document");
		sleepFor(4000);
		new WindowHelper(driver).waitForModalDialog("Intray Details");
		String ActualUserID = navigationMenu.getTableCellValueByClassName("dataTableGrid", "1", "1");

		String expectedUserID = ObjectReader.reader.getUserName();
		AssertionHelper.verifyText(expectedUserID, ActualUserID);
	}

	@Test(priority = 12, enabled = true)
	public void TestAddNotesToDocumentInDocumentList() throws InterruptedException {
		String noteTitle = "sample Testing Title for Notes" + generateRandomNumber();
		String noteText = noteTitle + "sample Testing Text for Notes" + generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Notes", "Document");
		new WindowHelper(driver).waitForModalDialog("Notes");

		click(docTools.addNoteBtn, "Clicking on add document note button");
		new WindowHelper(driver).waitForModalDialog("Notes");
		sendKeys(docTools.notesTitleInput, "sample Testing", "Entering the notes Title Input");
		sendKeys(docTools.notesTextAreaInput, "sample Testing - Text Area Input", "Entering the Text Area Input");
		click(docTools.saveDocumentNoteBtn, "Clicking on save button");
		new WindowHelper(driver).waitForModalDialog("Notes");
//    		new WindowHelper(driver).waitForModalDialog("Notes");
		click(docTools.documentCloseNoteBtn, "Clicking on close document note button");

//            2nd way to add notes from administration >> notes 
		String textAreaDesctiption = "This is new line characted for the above line" + generateRandomNumber();
		getApplicationUrl();
		adminDoc.clickOnAdminNotes();
		navigationMenu.clickOnAddIcon();
		adminDoc.SelectNotesType("Paragraph", getEnvVariable); // Changed in R551
		adminDoc.EnterTextAreaDescriptionForNotes(textAreaDesctiption);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null, getEnvVariable);
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnLandingPageIcon();
		docTools.ClickOnAddNotesToDocument();
		docTools.EnterTitleInNotes("Sample");
		docTools.SelctParagraphforDocument(textAreaDesctiption, getEnvVariable);
		navigationMenu.clickOnSaveIcon();
		
//=============================================================================================================
//		verification
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Notes", "Document");
		new WindowHelper(driver).waitForModalDialog("Notes");
		
		
//		WebElement ActualTitle=driver.findElement(By.xpath(String.format(docTools.notesParagraph, textAreaDesctiption)));
//		Note : --> ask amit to add this temp xpath (tmpNotesContent) in doctools
		AssertionHelper.verifyText(textAreaDesctiption, docTools.notesParagraph.getText());
//=============================================================================================================
	}

	@Test(priority = 13, enabled = true)
	public void TestReviewDocumentAuditActionInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
		sleepFor(2000);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
	}
	


	@Test(priority = 14, enabled = true)
	public void TestOpenF1LandingPageInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();

		capture.selectSearchTab();
		String entityUnderSearch = ObjectReader.reader.getFolder1RefName();
		home.clickOnSearchEntityAndSelect(entityUnderSearch);
		//waitHelper.waitForElementVisible(docTools.viewerText, 35, 1);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		home.searchAndNavigateToLandingPage("Account", "accRef%");
		//waitHelper.waitForElementVisible(docTools.viewerText, 20, 1);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
				"Verifying text name of 'Account Landing Page' is displayed");
	}

	@Test(priority = 15, enabled = true)
	public void TestOpenF3LandingPageInDocumentList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();

		capture.selectSearchTab();
		String entityUnderSearch1 = ObjectReader.reader.getFolder3RefName();
		home.clickOnSearchEntityAndSelect(entityUnderSearch1);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		home.searchAndNavigateToLandingPage("Property", "propRef%");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
				"Verifying text name of 'Property Landing Page' is displayed");
	}

	// Intray List flows

	@Test(priority = 16, enabled = true)
	public void TestOpenAIntrayList() throws InterruptedException {
		docRef = "DRI" + randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//    		adding captured document to bundle
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null, null, null, getEnvVariable);
		sleepFor(2000);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
	}

	@Test(priority = 17, enabled = true)
	public void TestViewDocumentInIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
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

	@Test(priority = 18, enabled = true)
	public void TestLaunchDocumentInIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
	}

	@Test(priority = 19, enabled = true)
	public void TestReIndexDocumentInIntrayList() throws InterruptedException {
//    			String expMsg = "Capture Document";
		docRefReIndex = "DRIRI" + randomNo;
		accRefIntray = "ARI" + randomNo; miscRefIntray = "MRI" + randomNo;
				propRefIntray = "PRI" + randomNo;
		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRefReIndex, null, null, null, getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRefReIndex);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Reindex document", "Document");
		//waitHelper.waitForElementVisible(docTools.viewerText, 35, 1);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		capture.enterIndexingInformation("0", accRefIntray, miscRefIntray, propRefIntray);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);

		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 10);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}

		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRefIntray);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		String ActualDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");					
		AssertionHelper.verifyText(docRefReIndex, ActualDocRef);
		String ActualPropertyRef = navigationMenu.getColumnValueFromTable("Property Ref");					
		AssertionHelper.verifyText(propRefIntray, ActualPropertyRef);
	}

	@Test(priority = 20, enabled = true)
	public void TestReReferenceDocumentInIntrayList() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String docRefReReference = "DRIRR" + randomNo;
		String folder1Ref = "ARIRR" + randomNo;
		String folder2Ref = "MRIRR" + randomNo;
		String folder3Ref = "PRIRR" + randomNo;
		String accountRef = ObjectReader.reader.getFolder1RefName();
		String miscRef = ObjectReader.reader.getFolder2RefName();
		String propRef = ObjectReader.reader.getFolder3RefName();
		String getEnvVariable = System.getProperty("propertyName");

		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRefReReference, null, null, null,
				getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRefReReference);
		capture.clickOnIntrayListBtn();
		//sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		//sleepFor(2000);
		docTools.clickOnReReferenceButton();
		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder1RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(accountRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder1Ref, folder1Ref, "Entering folder1 ref as" + folder1Ref);
		navigationMenu.clickOnIcon("Save changes made to " + accountRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder2RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(miscRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder2Ref, folder2Ref, "Entering folder2 ref as" + folder2Ref);
		navigationMenu.clickOnIcon("Save changes made to " + miscRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		new JavaScriptHelper(driver).clickElement(new DocumentToolsPage(driver).addFolder3RefBtnOnRereferencePage);
		sleepFor(4000);
		new DocumentToolsPage(driver).WaitForFolderRefTitleWhileRereference(propRef);
		sendKeys(new DocumentToolsPage(driver).txtFolder3Ref, folder3Ref, "Entering folder3 ref as" + folder3Ref);
		navigationMenu.clickOnIcon("Save changes made to " + propRef.toLowerCase(), "Actions");
		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);

		docTools.selectDocumentType("ScanDoc - Generic Scanned Document", getEnvVariable);
		sleepFor(3000);
		docTools.clickOnApplyButton();
		String popContent = docTools.getReferencePopupContent();
		docTools.clickYesOnConfirmReferencePopUp();
		test.log(Status.INFO, "Content of ReReference pop up as" + popContent);
		docTools.clickOkOnRereferenceSusccessfulPopup();
		// sleepFor(4000);
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
//        		Reference : RerefereceTest(), AddFoleder1Folder2Folder3RefWhileRereference()
	}

	@Test(priority = 21, enabled = true)
	public void TestDeleteDocumentInIntrayList() throws InterruptedException {
		docRef1 = "DRI1" + generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

//    			adding captured document to bundle
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef1, null, null, null, getEnvVariable);
		sleepFor(2000);

		// Delete document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Delete document", "Document");
		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		new WindowHelper(driver).waitForPopup("Delete Document");
		String getMsg1 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg1);
		new WindowHelper(driver).clickOkOnPopup();

		// search Deleted document
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);

		// verify document is deleted
		//String verifyEmptyTable = capture.verifyEmptyIntrayTable.getText();
		String verifyEmptyTable = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
//        		String expMsgInEmptyTable = "No matching records found";
		String expMsgInEmptyTable = "No items available";
		AssertionHelper.verifyText(verifyEmptyTable, expMsgInEmptyTable);
	}

	@Test(priority = 22, enabled = true)
	public void TestViewDocumentLandingPageInIntrayList() throws InterruptedException {
		String expMsg = "Intray Item Landing Page";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		String actMsg = navigationMenu.navBarTitleLbl.getText();
		AssertionHelper.verifyText(actMsg, expMsg);
	}

	@Test(priority = 23, enabled = true)
	public void TestAddNewIndexToDocumentInIntrayList() throws InterruptedException {
		docRef2 = "DRI2" + randomNo;
		String expMsg = "Capture Document";
//    			String docRef = "ReFolder2"+generateRandomNumber();
		String accRef = "ARI" + generateRandomNumber(), miscRef = "MRI" + generateRandomNumber(),
				propRef = "PRI" + generateRandomNumber();
		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef2, null, null, null, getEnvVariable);
		sleepFor(2000);

////		====================================================================================================
////		For verification store the Guid and Document Guid value
//		getApplicationUrl();
//		sleepFor(3000);
//		capture.searchWithCriteria("Doc Ref", docRef2);
//		sleepFor(3000);
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//		String actDocumentGuidColumnValue1 = navigationMenu.getColumnValue("Document Guid");
//		String actGuidColumnValue1 = navigationMenu.getColumnValue("Guid");			
//		sleepFor(2000);
//		
////		Add new index to document
//		getApplicationUrl();
//		capture.searchWithCriteria("Doc Ref", docRef2);
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//		navigationMenu.clickOnIcon("Add additional index to document", "Document");
//		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
//
//		String actMsg = navigationMenu.navBarTitleLbl.getText();
//		AssertionHelper.verifyText(actMsg, expMsg);
//		capture.enterIndexingInformation("0", accRef, miscRef, propRef);
//		new JavaScriptHelper(driver).scrollToTop();
//		sleepFor(2000);
//		capture.selectDocumentTypemDD(docType, getEnvVariable);
//		sleepFor(2000);
//		capture.clickOnIndexDocument();
//
//		try {
//			waitHelper.waitForElement(capture.successullyIndexMsg, 10);
//			capture.clickOkOnSuccesfullPopup();
//			getApplicationUrl(true);
//		} catch (Exception e) {
//			getApplicationUrl(true);
//		}
//
////		verify new index is added
//		getApplicationUrl();
//		capture.searchWithCriteria("Account Ref", accRef);
//		capture.clickOnDocumentListBtn();
//		capture.clickOnFirstItemOfIntray();
//		AssertionHelper.verifyText(navigationMenu.getColumnValue("Account Ref"), accRef);
//		AssertionHelper.verifyText(navigationMenu.getColumnValue("Property Ref"), propRef);	
//		AssertionHelper.verifyText(navigationMenu.getColumnValue("Doc Ref"), docRef);	
//		
//		String actDocumentGuidColumnValue2 = navigationMenu.getColumnValue("Document Guid");
//		String actGuidColumnValue2 = navigationMenu.getColumnValue("Guid");				
//		sleepFor(2000);	
//	
//		boolean actGuidCompare = actGuidColumnValue1.equals(actGuidColumnValue2);;
//		AssertionHelper.verifyFalse(actGuidCompare);
//		boolean actDocumentGuidCompare = actDocumentGuidColumnValue1.equals(actDocumentGuidColumnValue2);;
//		AssertionHelper.verifyFalse(actDocumentGuidCompare);
//		
//		AssertionHelper.verifyText(actGuidColumnValue1, actDocumentGuidColumnValue1);	
//		AssertionHelper.verifyText(actGuidColumnValue2, actDocumentGuidColumnValue2);
////=======================================================================================
		
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Add additional index to document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);

		// verification
		String actMsg2 = navigationMenu.navBarTitleLbl.getText();
		AssertionHelper.verifyText(actMsg2, expMsg);

// 		adding multiple reference to document
		sleepFor(3000);
		capture.enterIndexingInformation("0", accRef, miscRef, propRef);
//		new JavaScriptHelper(driver).scrollToBottom();
//		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 10);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
	}
	
	@Test(priority = 24, enabled = true)
	public void TestReviewDocumentAuditActionInIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
	}

	@Test(priority = 25, enabled = true)
	public void TestOpenF1LandingPageInIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		String entityUnderSearch = ObjectReader.reader.getFolder1RefName();
		home.clickOnSearchEntityAndSelect(entityUnderSearch);
		//waitHelper.waitForElementVisible(docTools.viewerText, 35, 1);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		home.searchAndNavigateToLandingPage("Account", accRefIntray);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
				"Verifying text name of 'Account Landing Page' is displayed");
	}

	@Test(priority = 26, enabled = true)
	public void TestOpenF3LandingPageInIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		String entityUnderSearch1 = ObjectReader.reader.getFolder3RefName();
		home.clickOnSearchEntityAndSelect(entityUnderSearch1);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		home.searchAndNavigateToLandingPage("Property", propRefIntray);
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(navigationMenu.navBarTitleLbl),
				"Verifying text name of 'Property Landing Page' is displayed");
	}

	@Test(priority = 27, enabled = true)
	public void TestCompleteDocumentInIntrayList() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Change mail status to complete", "Mail");
		new WindowHelper(driver).waitForPopup("Update Intray Mail Status");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);
		System.out.println("get message ====================================" + getMsg);
		sleepFor(2000);
		boolean status = new WindowHelper(driver).isPopupDisplayed();
		if (status) {
			String getErrorMsg = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get error message ====================================" + getErrorMsg);
			sleepFor(2000);
		}

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		String ActualMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		AssertionHelper.verifyText(ActualMailStatus, "Complete");
	}

	@Test(priority = 28, enabled = true)
	public void TestViewTheHistoryInIntrayList() throws InterruptedException {
//    			String expMsg = "Capture Document";
		docRef = "DRI2" + randomNo;
		String accRef = "ARIVH" + randomNo, miscRef = "MRIVH" + randomNo,
				propRef = "PRIVH" + randomNo;
		String docType = "Default - General Default Document Type";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null, null, null, getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Reindex document", "Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
		capture.enterIndexingInformation("0", accRef, miscRef, propRef);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType, getEnvVariable);
		sleepFor(2000);
		
		capture.clickOnIndexDocument();
		try {
			//waitHelper.waitForElement(capture.successullyIndexMsg, 10);
			sleepFor(3000);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Show document associated with this account", "Mail");
		sleepFor(4000);
	}

	@Test(priority = 29, enabled = true)
	public void TestViewTheIntrayHistory() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		sleepFor(2000);
		navigationMenu.clickOnIcon("Intray History", "Mail");
		sleepFor(4000);
	}

	@Test(priority = 30, enabled = true)
	public void TestAllocateDocumentInIntrayList() throws InterruptedException {
		String expDdTeamValue = "All";
		String expDdUserValue = "EmailConnectUser - Email Connect User";
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Allocate intray item", "Mail");
		new WindowHelper(driver).waitForModalDialog("Allocate intray item");
		new DropdownHelper(driver).selectFromAutocompleDD(expDdTeamValue,
				new DocumentToolsPage(driver).ddTeamUnderIntrayMoreSearch, getEnvVariable);
		new DropdownHelper(driver).selectFromAutocompleDD(expDdUserValue, new IntrayToolsPage(driver).allocateUserDD,
				getEnvVariable);
		click(new IntrayToolsPage(driver).allocateOkPopUpBtn, "Clicking in allocate ok button");
		sleepFor(20000);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
		AssertionHelper.verifyTextContains(expDdUserValue, navigationMenu.getColumnValueFromTable("User ID"));
	}
	
	
	//capture document flows
	
	@Test(priority=31,enabled = true)
	public void TestCaptureDocumentUsingCreateNewDocumentIcon() throws InterruptedException 
	{
		docRef = "DRC"+randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null,  docRef , null, null,  null, getEnvVariable);
		
//		Verify document is captured
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef, ActualDocumentRef);		
	}

	@Test(priority=32,enabled = true)
	public void TestCaptureDocumentUsingCopyPrevious() throws InterruptedException 
	{
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
		capture.verifyAndCaptureDocWithCopyPrevious(null, null, null, docRef, null, null, null, getEnvVariable);
		
//		Verify document is captured using copy previous
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();

		String ActualDocumentRef1 =  navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef, ActualDocumentRef1);	
	}

	@Test(priority=33,enabled = true)
	public void TestCaptureSingleDocumentForSingleDocumentTypeAndMultipleReferences() throws InterruptedException 
	{
		test = ExtentTestManager.getTest();
//		String randomNo = generateRandomNumber();
		String docRef1 = "DRC1"+randomNo;
		String accRef="ARC1"+randomNo,
			   miscRef = "MRC1"+randomNo,
			   propRef="PRC1"+randomNo,
			   accRef1="ARC2"+randomNo,
			   miscRef1 = "MRC2"+randomNo,
			   propRef1="PRC2"+randomNo;
		String docType="Default - General Default Document Type";

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();

		String userType =  "To User", userName = ObjectReader.reader.getUserName(), 
				filepath= ObjectReader.reader.getTestDocFolderPath(),
				filename="file1.tif";
				
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		capture.clickOnFileUploadIcon();
		capture.uploadFileAngGetFileName(filepath,filename);
		capture.selectRoutingTypeDD(userType,getEnvVariable);
		capture.selectRouteToDD(userName,getEnvVariable);
		capture.enterDocRef(docRef1);
		sleepFor(5000);
					
		capture.enterIndexingInformation("0", accRef, miscRef, propRef);
		new JavaScriptHelper(driver).scrollToBottom();
		capture.addNewSetOfIndexingDetails();
		capture.enterIndexingInformation("1", accRef1, miscRef1, propRef1);
				
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(2000);
		capture.clickOnIndexDocument();
				
		try {
			waitHelper.waitForElement(capture.successullyIndexMsg,10);
			capture.clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}	
				
//		verify the multiple references are captured
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
					
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Property Ref"), propRef);	
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Doc Ref"), docRef1);	
				
		String actPhysicalGuidColumnValue1 = navigationMenu.getColumnValue("Physical Doc Guid");
		String actGuidColumnValue1 = navigationMenu.getColumnValue("Guid");			
		sleepFor(2000);
				
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref", accRef1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Property Ref"), propRef1);	
		AssertionHelper.verifyText(navigationMenu.getColumnValue("Doc Ref"), docRef1);	
				
		String actPhysicalGuidColumnValue2 = navigationMenu.getColumnValue("Physical Doc Guid");
		String actGuidColumnValue2 = navigationMenu.getColumnValue("Guid");				
		sleepFor(2000);	
				
		AssertionHelper.verifyText(actPhysicalGuidColumnValue1, actPhysicalGuidColumnValue2);	

		boolean actGuidCompare = actGuidColumnValue1.equals(actGuidColumnValue2);;
		AssertionHelper.verifyFalse(actGuidCompare);
	}

	@Test(priority=34,enabled = true)
	public void TestCaptureDocumentChangingMailOption() throws InterruptedException 
	{
		docRef2 = "DRC2"+randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
//	    capture.navigateAndCaptureDocWithParameters(null, null, null, docRef2, "Backfile", null, "To User", null, null, getEnvVariable);	
	    capture.navigateAndCaptureDocWithParameters(null, null, null, docRef2, "Completed", null, "Automatic", null, null, getEnvVariable);	
	 
//=============================================================================================================
	   //================Note : For automatic one change have done in the original method=====================
//=============================================================================================================
	    //		Verify document is captured and displayed in document list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref",docRef2);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
//		String ActualDocumentRef =  docTools.filteredDocRef.getText();
		AssertionHelper.verifyText(docRef2, ActualDocumentRef);		

//		Verify document is captured and not displayed in intray list
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", docRef2);
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		String ActualDocumentRef1 = navigationMenu.getNoRecordsFoundMessage("searchResultIntrayDataTable");
		String expDocumentRef1 = "No items available";
		AssertionHelper.verifyText(ActualDocumentRef1, expDocumentRef1);
	}

	@Test(priority=35,enabled = true)
	public void TestCaptureDocumentChangingRoutingPreferenceMailOptionOnly() throws InterruptedException 
	{
		docRef3 = "DRC3"+randomNo;
		docRef4 = "DRC4"+randomNo;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
	
		getApplicationUrl();
	    capture.navigateAndCaptureDocWithParameters(null, null, null, docRef3, "Mail", null, "To User", null, null, getEnvVariable);

		sleepFor(1000);	
		
//		Verify document is captured in document list
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef3);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		AssertionHelper.verifyText(docRef3, ActualDocumentRef);		
		sleepFor(4000);	

//		Verify document is captured in Intray list
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef3);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef1 = navigationMenu.getColumnValueFromTable("Doc Ref");
		waitHelper.waitForElementVisible(navigationMenu.filterTxt, 35, 5);
		String ActualUser1 = navigationMenu.getColumnValueFromTable("User ID");
		AssertionHelper.verifyText(docRef3, ActualDocumentRef1);		
		AssertionHelper.verifyText(ObjectReader.reader.getUserName(), ActualUser1);
		sleepFor(4000);	
		
		getApplicationUrl();
	    capture.navigateAndCaptureDocWithParameters(null,null, null, docRef4, "Mail", null, null, null, null, getEnvVariable);	
		sleepFor(2000);	
		
//		Verify document is captured in document list
		getApplicationUrl();
//		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef4);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef2 = navigationMenu.getColumnValueFromTable("Doc Ref");
//		String ActualTeam = navigationMenu.getColumnValueFromTable("Team");
		AssertionHelper.verifyText(docRef4, ActualDocumentRef2);	
//		AssertionHelper.verifyText(ObjectReader.reader.getFileSystemName(), ActualTeam);	

//		Verify document is captured in Intray list
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef4);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		String ActualDocumentRef3 = navigationMenu.getColumnValueFromTable("Doc Ref");
		waitHelper.waitForElementVisible(navigationMenu.filterTxt, 35, 5);
		String ActualTeam1 = navigationMenu.getColumnValueFromTable("Team");
		AssertionHelper.verifyText(docRef4, ActualDocumentRef3);	
		AssertionHelper.verifyText(ObjectReader.reader.getLoggedInUsersTeamId(), ActualTeam1);	
	}
}