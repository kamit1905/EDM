package tests;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ISelect;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;
import utils.ExtentReports.ExtentTestManager;

public class TestIntrayTools extends TestBase {
	public IntrayToolsPage intrayTools ;
	private Logger log = LoggerHelper.getLogger(TestIntrayTools.class);
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	WaitHelper waitHelper;
	WindowHelper windowHelper;
	AdminDocumentSection adminDocument;
	public ToolkitSystemSection toolkitSystem;
	public VerificationHelper verificationHelper;
	public AdminUserSection adminUser;
	public LoginPage login;
	public ActionHelper action;
	
	
	@BeforeClass
	public void setupClass()  {
		intrayTools = new IntrayToolsPage(driver);
		adminUser= new AdminUserSection(driver);
		capture = new CapturePage(driver);
		home= new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		waitHelper= new WaitHelper(driver);
		xls = new ExcelReader();
		toolkitSystem= new ToolkitSystemSection(driver);
		verificationHelper= new VerificationHelper(driver);
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
	
	@Test(priority=302,enabled = false, dataProvider="captureFormData")
	public void TestViewIntrayDocument(Map<String, String> map) throws InterruptedException {
		String docRef= map.get("DocRef"),
				expTitle = "Viewer";
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Viewing intray document with docref "+docRef);
			getApplicationUrl();
			intrayTools.searchDocumentClickOnFirstIntrayItem("Doc Ref",docRef );
			navigationMenu.clickOnIcon("View document", "Document");
			try {
				sleepFor(2000);
				new WindowHelper(driver).switchToNewlyOpenedTab();
				waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
				sleepFor(3000);
			} finally {
				new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			}
	}
	
	

	
	@Test(priority=303,enabled = true)
	public void TestIntrayDocumentLock() throws InterruptedException {
			String docRef="intrayLockUnlock";
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Intray doc lock with docref "+docRef);
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docRef );
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Lock document", "Document");
			
			//Added by amit to verify lock msg
			String finalMsg=windowHelper.getPopupMessageClickOk("Lock Document");
			//String finalMsg = intrayTools.clickOkOnDeletePopup();
			System.out.println("Final msg is "+finalMsg);
			AssertionHelper.verifyText(finalMsg, "Document(s) locked from further editing.");	
	}
	
	@Test(priority=304,enabled = true)
	public void TestVerifyErrorMessageForLockedDocument() throws InterruptedException {
			String docRef="intrayLockUnlock";
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Intray doc lock with docref "+docRef);
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",docRef );
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Edit document", "Version Control");
			
			String finalMsg=windowHelper.getPopupMessageClickOk("Edit Document");
			//String finalMsg = intrayTools.clickOkOnDeletePopup();
			System.out.println("Final msg is "+finalMsg);
			AssertionHelper.verifyText(finalMsg, "The document is currently locked from editing.");
	}
	
	@Test(priority=306,enabled = true)
	public void TestIntrayDocumentUnlock() throws InterruptedException {
			String docRef="intrayLockUnlock";
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Intray doc unlock with docref "+docRef);
			getApplicationUrl();
			intrayTools.searchDocumentClickOnFirstIntrayItem("Doc Ref",docRef );
			AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Unlock document", "Document"),
					"Checking if unlock document is enabled");
			navigationMenu.clickOnIcon("Unlock document", "Document");
			//capture.clickOnFirstItemOfIntray();
			
			//Added by amit to verify unlock msg    Unlock Document
			String finalMsg=windowHelper.getPopupMessageClickOk("Unlock Document");
			System.out.println("Final msg is "+finalMsg);
			AssertionHelper.verifyText(finalMsg, "Document(s) unlocked, allowing further editing.");	
	}
	
	@Test(priority=307,enabled = true, dataProvider="captureFormData")
	public void TestIntrayEditMetada(Map<String, String> map) throws InterruptedException {
			String docRef= map.get("DocRef");
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docRef);
			
			getApplicationUrl();
			intrayTools.searchDocumentClickOnFirstIntrayItem("Doc Ref",docRef );
			intrayTools.clickOnEditMetadata();
			AssertionHelper.verifyText(navigationMenu.getNavbarText(), "Edit Metadata");
	}
	
		@Test(priority=308,enabled = false)
	public void TestIntrayIndexPDFAsTiff() throws InterruptedException {
			test = ExtentTestManager.getTest();
			String tiffDocRef = "IntrayAutoPDFasTiff";
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			getApplicationUrl();
			intrayTools.searchDocumentClickOnFirstIntrayItem("Doc Ref","%pdf" );			//%pdf to capture
			intrayTools.clickOnIndexPDFAsTiff();
			capture.enterDocRef(tiffDocRef);
			capture.selectRoutingTypeDD("To User",getEnvVariable);
			capture.selectRouteToDD(ObjectReader.reader.getUserName(),getEnvVariable);
			capture.clickOnIndexDocument();
			intrayTools.clickOkOnPDFasTiffPopup();
			getApplicationUrl();
			//capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", tiffDocRef);
			capture.clickOnIntrayListBtn();
			AssertionHelper.verifyText(navigationMenu.getColumnValueFromTable("File Type"),".tif");
	}
		
			@Test(priority=309,enabled = true,dataProvider = "captureFormData")
	public void TestIntrayAddButtonFunctionality(Map<String, String> map ) throws InterruptedException {
			test = ExtentTestManager.getTest();
			String addDocRef = "IntrayAddButtonAsso",
					searchDocRef="IntrayAddButton";
			String accRefLabel="Account Ref";
			String accRef="Z1-0"+generateRandomNumber();
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			getApplicationUrl();
			intrayTools.searchDocumentClickOnFirstIntrayItem("Doc Ref",searchDocRef );
			intrayTools.clickOnAddButtonUnderDocument();
			capture.enterDocRef(addDocRef);
			capture.clickOnFileUploadIcon();
			capture.uploadFileAngGetFileName(map.get("FilePath"),map.get("FileName"));
			
			//Folder ref is added because to verify Associated ref in next node		[by amit]
			boolean success =capture.enterFolder1RefCapturePageAndSearch(accRef, accRefLabel);
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(1000);
			
			capture.selectRoutingTypeDD("To User",getEnvVariable);
			capture.selectRouteToDD(ObjectReader.reader.getUserName(),getEnvVariable);
			capture.clickOnIndexDocument();
			//intrayTools.clickOkOnIndexSuccessfully();
			sleepFor(2000);
			getApplicationUrl();
			//capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", addDocRef);
			capture.clickOnIntrayListBtn();
			AssertionHelper.verifyText(navigationMenu.getColumnValueFromTable("Doc Ref"),addDocRef);
	}
		
			@Test(priority=310,enabled = true)
	public void TestIntrayAssociatedFunctionality() throws InterruptedException {
			test = ExtentTestManager.getTest();
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			String addDocRef = "IntrayAddButtonAsso";			//changed from IntrayAddButton to IntrayAddButtonAsso
			String folder1Title=ObjectReader.reader.getFolder1RefName();
			getApplicationUrl();
			intrayTools.searchDocumentClickOnFirstIntrayItem("Doc Ref", addDocRef );
			intrayTools.clickOnAssociatedAndSelect("Show "+folder1Title,getEnvVariable);						
			String navBartitle = navigationMenu.getNavbarText();
			//AssertionHelper.verifyText(navigationMenu.getColumnValueFromTable("Doc Ref"),addDocRef);
			AssertionHelper.verifyText(navBartitle, folder1Title+" Landing Page");						
	}	
	

	@Test(priority=311,enabled = true)					
	public void TestMyIntrayEditMetada() throws InterruptedException {
			String editDocRef = "Edit MetaDocRef",
					editDocRef2 = "Edit MetaDocRef3";
			String docRef="Edit_"+generateRandomNumber();
			test = ExtentTestManager.getTest();			
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			//Added because to avoid confilcts in multiple run [by amit]
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null,docRef, null, null, null,getEnvVariable);
			
			getApplicationUrl();
			home.clickOnMyIntrayIcon(getEnvVariable);	
			navigationMenu.searchInFilter(docRef);
			
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnEditMetadata();
            capture.enterDocRef(editDocRef);
			capture.enterDocRef2(editDocRef2);
			navigationMenu.clickOnIconMenu("Save", "Actions");
			
			sleepFor(1000);
			getApplicationUrl();
			home.clickOnMyIntrayIcon(getEnvVariable);	
			navigationMenu.searchInFilter(editDocRef);				 
			String actDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
			System.out.println("actDocRef "+actDocRef);
			navigationMenu.searchInFilter(editDocRef);	
			AssertionHelper.verifyText(actDocRef, editDocRef);
			
			
			sleepFor(2000);
			getApplicationUrl();
			home.clickOnMyIntrayIcon(getEnvVariable);  
			navigationMenu.searchInFilter(editDocRef);				
			String actDocRef2 = navigationMenu.getColumnValueFromTable("Doc Ref 2");
			navigationMenu.searchInFilter(editDocRef);	
			//AssertionHelper.verifyText(actDocRef2, editDocRef2);
	}
	
	@Test(priority=312,enabled = true)
	public void TestMyIntrayEditMetadaCancelFunctionality() throws InterruptedException {
			String docRef = "Edit MetaDocRef",
					cancelMsg = "Do you want to save changes?";
			test = ExtentTestManager.getTest();		
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			getApplicationUrl();
			home.clickOnMyIntrayIcon(getEnvVariable);	
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnEditMetadata();
			intrayTools.enterMetadataDocRef(docRef);
			navigationMenu.clickOnIconMenu("Cancel changes", "Actions");
			
			windowHelper.waitForPopup("Leave Page");				//Adding because getting unhandled alert exception in next node
			sleepFor(2000);
			windowHelper.clickOnModalFooterButton("No");
			sleepFor(3000);
			/**
			windowHelper.waitForModalDialog("Leave Page");
			AssertionHelper.verifyText(windowHelper.getPopupMessage(), cancelMsg);
			AssertionHelper.verifyTrue(windowHelper.isModalFooterButtonDisplayed("Yes"), "Verifying for Yes button presence");
			AssertionHelper.verifyTrue(windowHelper.isModalFooterButtonDisplayed("No"), "Verifying for No button presence");
			AssertionHelper.verifyTrue(windowHelper.isModalFooterButtonDisplayed("Cancel"), "Verifying for Cancel button presence");
			*/
	}
	

	
	/************* Export module ******************/	

	@Test(priority=341,enabled = true)
	public void TestExportDocMetadataFunctionality() throws InterruptedException {
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");
		
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		//waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		navigationMenu.waitForFilterTextBox();
		intrayTools.sortTargetDateColumnInDescending();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnExportDocument();
		intrayTools.selectFromExportContentDropdown("Both Document And Metadata",getEnvVariable);
		//intrayTools.selectFromExportConterntDropdownIn530("Both Document And Metadata");
		String fileName = intrayTools.getExportFileName();
		log.info("Value of filename "+fileName);
		intrayTools.waitTillMetadataFileTypeVisible();
		//intrayTools.selectFromExportContentDropdown("Metadata Only");
		//intrayTools.waitTillMetadataFileTypeVisibleIn530();					
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToBottom();
		windowHelper.clickOnModalFormFooterButton("Ok");
		Thread.sleep(4000);
		boolean istiffDocExist = navigationMenu.isDownloadedFileExist(fileName+".tiff");
		boolean ispdfDocExist =navigationMenu.isDownloadedFileExist(fileName+"_Metadata"+".pdf");
		AssertionHelper.verifyTrue(ispdfDocExist, "Verifying pdf file exist");
		AssertionHelper.verifyTrue(istiffDocExist, "Verifying tiff file exist");
	}	


	@Test(priority=342,enabled = true)
	public void TestExportedMetadataFunctionality() throws InterruptedException {
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		//waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		navigationMenu.waitForFilterTextBox();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnExportDocument();
		intrayTools.selectFromExportContentDropdown("Metadata Only",getEnvVariable);
		//intrayTools.selectFromExportConterntDropdownIn530("Metadata Only");
		String fileName = intrayTools.getExportFileName();
		log.info("Value of filename "+fileName);
		intrayTools.waitTillMetadataFileTypeVisible();
		//intrayTools.selectFromExportContentDropdown("Metadata Only");
		//intrayTools.waitTillMetadataFileTypeVisibleIn530();					
		windowHelper.clickOnModalFormFooterButton("Ok");
		Thread.sleep(4000);
		boolean ispdfDocExist = navigationMenu.isDownloadedFileExist(fileName+"_Metadata.pdf");
		AssertionHelper.verifyTrue(ispdfDocExist, "Verifying pdf file exist");

	}	



	@Test(priority=343,enabled = true)
	public void TestExportMetadataValidation()throws InterruptedException {
		String expMsg = "Please select a document metadata option to export.";
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		//waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		navigationMenu.waitForFilterTextBox();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnExportDocument();
		intrayTools.selectFromExportContentDropdown("Metadata Only",getEnvVariable);
		//intrayTools.selectFromExportConterntDropdownIn530("Metadata Only");
		intrayTools.uncheckAllCheckedCheckbox();					
		windowHelper.clickOnModalFormFooterButton("Ok");
		windowHelper.waitForPopup("Export");
		String actMsg = intrayTools.getSelectMetadataOptionPopupMessage();
		AssertionHelper.verifyText(actMsg, expMsg);	
		sleepFor(2000);
		new ActionHelper(driver).pressEscape();
	}	

	@Test(priority=344,enabled = true)
	public void TestExportDocumentPdfOnly()throws InterruptedException {
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		//navigationMenu.searchInFilter("intray");				
		//waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		navigationMenu.waitForFilterTextBox();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnExportDocument();
		String fileName = intrayTools.getExportFileName();
		windowHelper.waitForModalFormDialog("Export");
		intrayTools.selectFromExportContentDropdown("Document Only",getEnvVariable);
		//intrayTools.selectFromExportConterntDropdownIn530("Document Only");
		intrayTools.selectFileFormat("Save Tiff file(s) as PDF",getEnvVariable);
		//intrayTools.selectFileFormatIn530("Save Tiff file(s) as PDF");		
		windowHelper.clickOnModalFormFooterButton("Ok");					
		Thread.sleep(4000);
		boolean ispdfDocExist = navigationMenu.isDownloadedFileExist(fileName+".pdf");
		AssertionHelper.verifyTrue(ispdfDocExist, "Verifying pdf file exist");
		
		home.refreshCurrentFileSystem();			//Added because in next node on history tab of landing page getting balnk even though there is rendition of document is present 
		sleepFor(3000);
	}
	
	
	
	/**  ---------------      Next mail   -------------**/

	@Test(priority=371,enabled = true, dataProvider="captureFormData")
	public void TestIntrayNextMailAvailablity(Map<String, String> map) throws InterruptedException {
			String docRef= map.get("DocRef");
			test = ExtentTestManager.getTest();
			test.log(Status.INFO, "Re-refencing document with docref "+docRef);
			getApplicationUrl();
			navigationMenu.clickOnTab("Search");
			capture.searchWithCriteria("Doc Ref", docRef);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.expandNavigationMenu("Mail");
			Boolean isNextEnabled = navigationMenu.isIconEnabled("Take next item from team tray", "Mail");
			AssertionHelper.verifyTrue(isNextEnabled, "Checking next mail is enabled");
	}
		
	@Test(priority=374,enabled = true,dataProvider = "captureFormData")
	public void TestIntrayNextMailBtnAllocationOldestFirst(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
			String expMessage = "1 shared In-tray item(s) has been allocated",
					filepath=map.get("FilePath"),
				  docRef= "nextMailTeamOld",filename = map.get("FileName"),
				  docType = map.get("DocumentType"),accRef = map.get("Folder1RefValue"),
				  userType="To Team",userName=ObjectReader.reader.getLoggedInUsersTeam(),expectedOldDocRef=docRef+"2";
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			
			test = ExtentTestManager.getTest();
			getApplicationUrl();
			//capture.navigateAndCaptureDocWithParameters(filepath, filename, docType, expectedOldDocRef, accRef,userType,userName);
			//capture.navigateAndCaptureDocWithParameters(filepath, filename, docType, docRef+"3", accRef,userType,userName);
			
			capture.navigateAndCaptureDocWithDateParameters(filepath, filename, docType, expectedOldDocRef, accRef, userType, userName, "2",getEnvVariable);			//Added date parameter for nextMail
			capture.navigateAndCaptureDocWithDateParameters(filepath, filename, docType, docRef+3, accRef, userType, userName, "3",getEnvVariable);
			capture.navigateAndCaptureDocWithDateParameters(filepath, filename, docType, docRef+4, accRef, userType, userName, "4",getEnvVariable);
			//toolkitSystem.checkConfigValue("IntrayNextOrder","1");
			home.clickOnMyIntrayIcon(getEnvVariable);	
			intrayTools.clickOnNextMailButton();
			AssertionHelper.verifyText(intrayTools.getNextPopupMessage(), expMessage);
			sleepFor(3000); 			
			getApplicationUrl();
			//home.clickOnOnDocumentReceivedToday();
			home.clickOnMyIntrayIcon(getEnvVariable);	
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);				//commentd uncomment later
			navigationMenu.searchInFilter(docRef+4);
			String actRef = navigationMenu.getColumnValueFromTable("Doc Ref");
			//AssertionHelper.verifyText(actRef, docRef+4);
	}
		
	@Test(priority=375,enabled = true)
	public void TestIntrayNextMailBtnAllocationOldestSecond() {
		String docRef= "nextMailTeamOld",
				expMessage = "1 shared In-tray item(s) has been allocated";
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();

		home.clickOnMyIntrayIcon(getEnvVariable);	
		intrayTools.clickOnNextMailButton();
		AssertionHelper.verifyText(intrayTools.getNextPopupMessage(), expMessage);
		sleepFor(3000); 			
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		//waitHelper.waitForElement(navigationMenu.filterTxt, 20);				//commentd uncomment later
		navigationMenu.searchInFilter(docRef+3);
		String actRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		//AssertionHelper.verifyText(actRef, docRef+3);
	}
		
		
		
	/** -----------------------------------Distribution-------------------------------------------------**/ 
	 
	@DataProvider(name="intrayData")
	public Object[][] intrayData() throws Exception
	{
		Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","IntrayTools");
		return formData;
	}	
	
	//Corrected with 530
	@Test(priority=382,enabled = true)		//383
	public void TestDistributionCancelFunctionality() {
			test = ExtentTestManager.getTest();
			getApplicationUrl();
			home.clickOnMetroTile("Outstanding items.");
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			intrayTools.refreshInIntrayForSearchTxt();					//Added by because some time filter txt is not visible
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.clickOnModalCanelButtonWithoutChanges();
			//AssertionHelper.verifyFalse(verificationHelper.isElementDisplayedByEle(intrayTools.ddFileSystemDistribute));
	}	
	
	@Test(priority=383,enabled = true)		//384
	public void TestDistributionEscalateSingleUser() throws InterruptedException {
			test = ExtentTestManager.getTest();
			getApplicationUrl();
			home.clickOnMetroTile("Outstanding items.");
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			intrayTools.refreshInIntrayForSearchTxt();
			intrayTools.clickOnFirstItemInList();
			navigationMenu.clickOnIcon("Distribute intray item", "Mail");
			sleepFor(2000);     		//Addding because below element is not visible
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			intrayTools.enterInPillsEscalate("User2");
			AssertionHelper.verifyFalse(intrayTools.isPillsMenuShown());	
	}	
	
	//Complete with 530
	@Test(priority=384,enabled = true)		//385
	public void TestDistributionApplyButtonDisable() {
			test = ExtentTestManager.getTest();
			getApplicationUrl();
			home.clickOnMetroTile("Outstanding items.");
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			intrayTools.refreshInIntrayForSearchTxt();
			Boolean isIconEnabled = navigationMenu.isIconEnabled("Distribute intray item", "Mail");
			AssertionHelper.verifyFalse(isIconEnabled);
	}	
	
	@Test(priority=387,enabled = true)			//382
	public void TestDistributionFunctionality() throws InterruptedException{
		String docRef="DistributeSingle";
			test = ExtentTestManager.getTest();
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			getApplicationUrl();			
			//intrayTools.openOutstandingItemsFromHome();
			//sendKeys(navigationMenu.filterTxt, "captureFormNovEscalate", "Entering text into filterbox");     
			//String getExpectedDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			//Write code to Enter notes in popup
			intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
			intrayTools.clickOnModalApplyChangesButtonWithPopup();;
			sleepFor(2000);
			
			getApplicationUrl();
			sleepFor(2000);
			home.clickOnMetroTile("Outstanding items for today.");
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			sleepFor(1000);
			navigationMenu.searchInFilter(docRef);
			String getActualDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			AssertionHelper.verifyText(getActualDocRef, docRef);
			
			home.refreshCurrentFileSystem();
			sleepFor(2000);
	}	
	
	@Test(priority=388,enabled = true,dataProvider = "intrayData")
	public void TestDistributionMultiReferral(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
			test = ExtentTestManager.getTest();
			String docRef="DistributeMultiple";
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			test.log(Status.INFO ,"======="+getEnvVariable+"==========");
			
			getApplicationUrl();
			//home.clickOnMetroTile("Outstanding items.");
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			//intrayTools.refreshInIntrayForSearchTxt();
			
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef);
			capture.clickOnIntrayListBtn();	
			navigationMenu.waitForFilterTextBox();
			intrayTools.clickOnFirstItemInList();
			String getExpectedDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			intrayTools.clickOnDistributeIcon();
			sleepFor(1000);
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			intrayTools.enterAndSelectPillsInEscalate(user1);
			intrayTools.enterAndSelectPillsInEscalate(user2);
			String multipleReferralText = intrayTools.getEscaltePillboxText();
			AssertionHelper.verifyTextContains(multipleReferralText, user1);
			//AssertionHelper.verifyTextContains(multipleReferralText, user2);
			intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			log.info(getExpectedDocRef+" Distributed to "+user1+" and "+user2);
			
			getApplicationUrl();
			home.refreshCurrentFileSystem();
			sleepFor(2000);
			
	}
	
	@Test(priority=389,enabled = true,dataProvider = "intrayData")
	public void TestDistributionMultiReferralForDifferentFileSystem(String user1,String user2, String team1, String team2, String diffFileSystem) throws InterruptedException {
			String docRef="DistributeDifferent";
		
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
			//test.log(Status.INFO ,"======="+getEnvVariable+"==========");
			
			getApplicationUrl();
			//home.clickOnMetroTile("Outstanding items.");
			//waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			//intrayTools.refreshInIntrayForSearchTxt();
			//String getExpectedDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			
			capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", docRef);
			capture.clickOnIntrayListBtn();	
			navigationMenu.waitForFilterTextBox();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			sleepFor(2000);
			intrayTools.selectFromFileSystemDistribute(diffFileSystem,getEnvVariable);
			intrayTools.enterAndSelectPillsInInfoOnly(user1);
			String multipleReferralText = intrayTools.getInfoOnlyPillboxText();
			AssertionHelper.verifyTextContains(multipleReferralText, user1);
			sleepFor(2000);
			intrayTools.enterNotesPillsInEscalate("Notes added in distribution");				
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			
			/** check this using all file system button
			home.changeFileSystem("R2");
			home.clickOnMetroTile("Outstanding items for today.");
			waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			String getActualDocRef= intrayTools.getColumnValueOfDocument("Doc Ref");
			AssertionHelper.verifyText(getActualDocRef, getExpectedDocRef);
			home.changeFileSystem("R3");
			//Verify same item exists for  user in different file system
		**/
			
			home.refreshCurrentFileSystem();
			sleepFor(2000);
	}	
	
		@Test(priority=398,enabled = true)
	public void TestDocumentLandingPageDocumentDelete() throws InterruptedException {
			test = ExtentTestManager.getTest();
			String expMsg = "Document(s) deleted successfully.";
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref","landingDelDoc" );
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnIntrayLandingPageIcon();
			String actMsg = intrayTools.clickOnDocumentDelete();
			//AssertionHelper.verifyText(actMsg, expMsg);	
	}	


}


