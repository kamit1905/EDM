package tests;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
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
import main.EDRM.hybridFramework.pageObject.Admin.AdminFolderSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.GIM;
import main.EDRM.hybridFramework.pageObject.Toolkit.RetentionAndDisposal;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitEmailTemplate;
import utils.ExtentReports.ExtentTestManager;

public class AUAT_Automation2 extends TestBase {
	
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

	
	
	//creat email template
	@Test(priority = 33,enabled = true)
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
	@Test(priority = 34,enabled = true)
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
	@Test(priority = 35,enabled = true)
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
	
	@Test(priority = 36,enabled = true)
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
	
	//It is use to verify that after sending document, document gets captured
	@Test(priority = 37,enabled = true)
	public void SearchDocumentAndVerifyDocumentIsPresent() {
		 getApplicationUrl();
		 capture.searchWithCriteria("Account Ref", folder1Ref);
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		
		 String actAccoutRef = navigationMenu.getColumnValue("File Type");
		 AssertionHelper.verifyText(actAccoutRef, ".pdf");			
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
	
	@Test(priority = 44,enabled = false)
	public void UnpublishDocumentFromPublicAccess() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",accRefPA1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		docTools.unpublishDocumentOnPublishAccess(getEnvVariable);
		
		String getRenditionStatus = navigationMenu.getColumnValueFromTable("Rendition Status");
		System.out.println("getStatus "+getRenditionStatus);
		AssertionHelper.verifyText(getRenditionStatus, "Private");
	}
	
	@Test(priority = 45,enabled = true)
	public void AddJobInGIM() {
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");;
		navigationMenu.clickOnIcon("Generic Import Module", "Modules");
		sleepFor(1000);
		navigationMenu.clickOnIcon("Jobs", "Implementation");
		gimJobName = "Job"+generateRandomNumber();
		String folder = "Test";						//Folder1 or Test
		String maskFileType = "*.csv";
		
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");;
		navigationMenu.clickOnIcon("Generic Import Module", "Modules");
		sleepFor(1000);
		navigationMenu.clickOnIcon("Jobs", "Implementation");
		navigationMenu.clickOnAddIcon();
		sleepFor(2000);
		
		new GIM(driver).EnterJobName(gimJobName);
		new GIM(driver).SelectFolder(folder);
		new GIM(driver).EnterControlMaskFile(maskFileType);
		
		new AdminUserSection(driver).clickOnUserNavTab("Processing");
		sleepFor(1000);
		new GIM(driver).SelectPreProcess("CSVtoXML Pre Run");
		
		new AdminUserSection(driver).clickOnUserNavTab("Field Mappings");
		sleepFor(1000);
		new GIM(driver).SelectEntityOptionDD("Do not create or update entities");
		new GIM(driver).SelectJobDocumentTypeDD("Default");
		new GIM(driver).EnterFieldMappingInJob("Document", "SourceFile", "1");
		new GIM(driver).EnterFieldMappingInJob("Document", "Account Ref", "2");
		new GIM(driver).EnterFieldMappingInJob("Document", "Doc Ref", "3");
		navigationMenu.clickOnSaveIcon();
	}
	
	@Test(priority = 46,enabled = true)
	public void VerifyThatJobIsAdded() {
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");;
		navigationMenu.clickOnIcon("Generic Import Module", "Modules");
		sleepFor(1000);
		navigationMenu.clickOnIcon("Jobs", "Implementation");
		navigationMenu.searchInFilter(gimJobName);
	}
	
	//Retention and Disposal
	@Test(priority = 47,enabled = true)
	public void CreateRetentationAndDisposalPolicy() {
		retentionPolicyName = "Policy"+generateRandomNumber();
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIconMenu("Retention & Disposal Administration", "Modules");
		sleepFor(2000);
		
		navigationMenu.clickOnAddIcon();
		new RetentionAndDisposal(driver).EnterPolicyName(retentionPolicyName);
		new RetentionAndDisposal(driver).EnterPeriodInput("12");
		new RetentionAndDisposal(driver).SelectDocumenToBeRetained("Months");
		new RetentionAndDisposal(driver).SelectFieldIdDD("Received");
		
		new RetentionAndDisposal(driver).DeleteEveryThingCheckbox();
		new RetentionAndDisposal(driver).DeletePhysicalFileCheckbox();
		navigationMenu.clickOnSaveIcon();
		new WindowHelper(driver).waitForModalDialog("Confirm Changes");
		click(new RetentionAndDisposal(driver).confirmChangesCheckbox, "Clicking on confirm changes checkbox");
		new WindowHelper(driver).clickOnModalFooterButton("Yes (not recommended)");
		navigationMenu.waitForAddIcon();
		
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIconMenu("Retention & Disposal Administration", "Modules");
		sleepFor(2000);
		navigationMenu.searchInFilter(retentionPolicyName);	
	}
	
	@Test(priority = 48,enabled = true)
	public void AddDocumentTypesToPolicy() {
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIconMenu("Retention & Disposal Administration", "Modules");
		sleepFor(2000);
		navigationMenu.searchInFilter(retentionPolicyName);	
		navigationMenu.clickOnFilteredRow("retentionPolicyTable");
		navigationMenu.clickOnIconMenu("Document Types", "Actions");
		
		new WaitHelper(driver).waitForElement(new RetentionAndDisposal(driver).documentTypeHeader, 15);
		navigationMenu.searchInFilter("Delete ");
		new RetentionAndDisposal(driver).SeletRententionPolicyForDocumentType(retentionPolicyName);
		navigationMenu.clickOnSaveIcon();
		new WindowHelper(driver).waitForModalDialog("Confirm Changes");
		//click(new RetentionAndDisposal(driver).turnOffAuditCheckbox, "Clicking on turn of audit checkbox");
		sleepFor(1000);
		//click(new RetentionAndDisposal(driver).delayUpdateCheckbox, "Clicking on delay update checkobx");
		new WindowHelper(driver).clickOnModalFooterButton("Yes");
		navigationMenu.waitForAddIcon();
	}
	
//	@Test(priority = 48,enabled = false)
//	public void GroupDocumentListByDifferentColumnAndResetGroups() {
//		getApplicationUrl();
//		capture.searchWithCriteria("Doc Ref", "%%");
//		capture.clickOnDocumentListBtn();
//		new DocumentToolsPage(driver).GroupDocumentListByUsingColoumn("Document Type");
//		
//		AssertionHelper.verifyTrue(new DocumentToolsPage(driver).VerifyDocumentGroupByDocumentType("DEFAULT"), "Verifying default document type after group");
//		AssertionHelper.verifyTrue(new DocumentToolsPage(driver).VerifyDocumentGroupByDocumentType("SCANDOC"), "Verifying default document type after group");
//		
//		//To Reset groups
//		click(new NavigationMenu(driver).gearIconBtn, "clicking on Gear icon button");
//		click(new DocumentToolsPage(driver).resetGroupsBtn, "Clicking on Reset group button");
//	}
	
	//View HBMS document
	@Test(priority = 49,enabled = false)
	public void VerifyHBMSExisitingJobs() {
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");;
		navigationMenu.clickOnIcon("Generic Import Module", "Modules");
		sleepFor(1000);
		navigationMenu.clickOnIcon("Jobs", "Implementation");
		navigationMenu.searchInFilter("HBMS");
	}
	
	@Test(priority = 50,enabled = false)
	public void ViewHBMSDocument() {
		getApplicationUrl();
		new HomePage(driver).clickOnMetroTile("Outstanding items.");
		sleepFor(2000);
		navigationMenu.searchInFilter("EFORM");
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View document", "Document");
		 try {
			 sleepFor(4000);
			 new WindowHelper(driver).switchToNewlyOpenedTab();
			 waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			 sleepFor(12000);
		} finally {
			 new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(priority=51,enabled = false)
	public void TestDataDownloadFolder2RefCSV() throws InterruptedException {
		//test = ExtentTestManager.getTest();
		folder2RefName = ObjectReader.reader.getFolder2RefName();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String expMsg = "Save successful.";
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
		
		boolean islogDocExist = navigationMenu.isDownloadedFileExist("FlexibleImport"+".log");
		AssertionHelper.verifyText(actMsg, expMsg);
		AssertionHelper.verifyTrue(islogDocExist, "Verifying pdf file exist");
		String jobIndex = adminFolder.getJobIndexNumber();
		log.info("Job Index is "+jobIndex);
	}

}
