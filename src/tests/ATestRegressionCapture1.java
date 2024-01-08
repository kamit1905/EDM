package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

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
import main.EDRM.hybridFramework.pageObject.Admin.AdminFolderSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;

//SUA198,FSA1010,SUV1163,STD8396,BAS6590

public class ATestRegressionCapture1 extends TestBase {
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
	private String fileSystemAdminUserName;
	private String supervisorUserName;
	private String standardUserName;
	private String basicUserName;
	
	private String folder1Ref1;

	private String superAdmingroupName;
	private String fileSystemgroupName;
	
	private List<String> folderRefListSAU;
	private List<String> folderRefListFSA;
	
	private String folder1RefSAU;
	private String folder1RefFSA;
	private String folder1RefSUV;

	private String superAdminSecurityProfie1;
	private String fileSystemAdminSecurityProfile1;
	
	private String superAdminCreatedUser;
	private String fileSystemAdminCreatedUser;
	
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
	
	@Test(priority=22,enabled = true)
	public void VerifySuperAdminUserAccessCapturePage() throws InterruptedException {
		superAdminUserName = "SAU"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(superAdminUserName);
        new AdminUserSection(driver).enterUsername(superAdminUserName);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
        new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Stamp Administrator");
		systemAdministrationList.add("Is RM Administrator");
		systemAdministrationList.add("Folder Security Administrator");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		systemAdministrationList.add("Allow Flexible entity Import ");
		systemAdministrationList.add("Allow All FileSystem On Integration ");
		systemAdministrationList.add("Is System Implementer");
		
		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		adminList.add("Update Environment");
		
		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		intrayList.add("View Intrays Across File Systems");
		
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");
		
		List<String> folderList = new ArrayList<String>();
		folderList.add("Update Restricted Folder Reference Documents");
		folderList.add("Allow Create Dummy Folder References ");
		folderList.add("Allow Translate Folder References");

		List<String> documentList = new ArrayList<String>();
		documentList.add("Delete Documents");
		documentList.add("Use Clipbook");
		documentList.add("Create Renditions and apply Redaction Templates");
		documentList.add("Edit Documents (CheckIn and CheckOut)");
		documentList.add("Create and edit Redaction Templates");
		documentList.add("Scan Documents");
		documentList.add("Batch Index Documents");
		documentList.add("Capture Documents");
		documentList.add("ReIndex Documents");
		documentList.add("Allow locking of documents to prevent editing");
		documentList.add("Allow unlocking of documents to allow editing");
		documentList.add("Allow routing of reindexed documents");
		documentList.add("Alter Protective Marker");
		documentList.add("Can Export Document");
		documentList.add("Can Export Document Metadata");
		documentList.add("Can Edit Metadata");
		documentList.add("Can Print Document");
		documentList.add("Allow Document Linking");
		
		List<String> notesList = new ArrayList<String>();
		notesList.add("Delete / Update Notes");
		
		List<String> archiveList = new ArrayList<String>();
		archiveList.add("Archive Documents");
		
		List<String> reportsList = new ArrayList<String>();
		reportsList.add("View Reports");
		
		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");
		
		//List<String> memoList = new ArrayList<String>();
		//memoList.add("Update Another User's Memos (excluding Intray)");
		
		List<String> caseManagementList = new ArrayList<String>();
		caseManagementList.add("QA User (VF)");
		caseManagementList.add("Pre-Assessor (VF)");
		
		List<String> workFlowList = new ArrayList<String>();
		workFlowList.add("Access Enterprise Workflow");
		workFlowList.add("Open Item");
		workFlowList.add("Delete Item");
		workFlowList.add("Pick Item");
		workFlowList.add("Return Item");
		workFlowList.add("Forward Items By User");
		workFlowList.add("Forward Items By Role");
		workFlowList.add("Resubmit Item");
		workFlowList.add("Display Audit Information");
		
		userPermission.put("System Administration", systemAdministrationList);
		userPermission.put("Admin", adminList);
		userPermission.put("Intray", intrayList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Folder", folderList);
		userPermission.put("Document", documentList);
		userPermission.put("Notes", notesList);
		userPermission.put("Archive", archiveList);
		userPermission.put("Reports", reportsList);
		userPermission.put("Cold", coldList);
		//userPermission.put("Memo", memoList);
		userPermission.put("Case Management", caseManagementList);
		userPermission.put("Enterprise Workflow", workFlowList);
		
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		new AdminUserSection(driver).clickOnUserNavTab("General");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(superAdminUserName, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.setUserPassword());
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		new CapturePage(driver).clickOnCaptureTab();
		new CapturePage(driver).clickOnDocumentCaptureBtn();
	}
	
	@Test(priority=23,enabled = true,dataProvider = "captureFormData")
	public void VerifySuperAdminUserCaptureDocument(Map<String, String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=superAdminUserName,
				documentType=map.get("DocumentType"),
				docRef= "Super"+generateRandomNumber();
		
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
		capture.enterDocRef(docRef);
		
		capture.clickOnIndexDocument();
		sleepFor(2000);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(priority=24,enabled = true)
	public void VerifyFileSystemAdminUserAccessCapturePage() throws InterruptedException {
		fileSystemAdminUserName = "FSA"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(fileSystemAdminUserName);
        new AdminUserSection(driver).enterUsername(fileSystemAdminUserName);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
        new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		systemAdministrationList.add("Stamp Administrator");
		systemAdministrationList.add("Is RM Administrator");
		systemAdministrationList.add("Folder Security Administrator");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		systemAdministrationList.add("Allow Flexible entity Import ");
		systemAdministrationList.add("Allow All FileSystem On Integration ");
		//systemAdministrationList.add("Is System Implementer");
		
		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		adminList.add("Update Environment");
		
		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		intrayList.add("View Intrays Across File Systems");
		
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");
		
		List<String> folderList = new ArrayList<String>();
		folderList.add("Update Restricted Folder Reference Documents");
		folderList.add("Allow Create Dummy Folder References ");
		folderList.add("Allow Translate Folder References");

		List<String> documentList = new ArrayList<String>();
		documentList.add("Delete Documents");
		documentList.add("Use Clipbook");
		documentList.add("Create Renditions and apply Redaction Templates");
		documentList.add("Edit Documents (CheckIn and CheckOut)");
		documentList.add("Create and edit Redaction Templates");
		documentList.add("Scan Documents");
		documentList.add("Batch Index Documents");
		documentList.add("Capture Documents");
		documentList.add("ReIndex Documents");
		documentList.add("Allow locking of documents to prevent editing");
		documentList.add("Allow unlocking of documents to allow editing");
		documentList.add("Allow routing of reindexed documents");
		documentList.add("Alter Protective Marker");
		documentList.add("Can Export Document");
		documentList.add("Can Export Document Metadata");
		documentList.add("Can Edit Metadata");
		documentList.add("Can Print Document");
		documentList.add("Allow Document Linking");
		
		List<String> notesList = new ArrayList<String>();
		notesList.add("Delete / Update Notes");
		
		List<String> archiveList = new ArrayList<String>();
		archiveList.add("Archive Documents");
		
		List<String> reportsList = new ArrayList<String>();
		reportsList.add("View Reports");
		
		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");
		
		//List<String> memoList = new ArrayList<String>();
		//memoList.add("Update Another User's Memos (excluding Intray)");
		
		List<String> caseManagementList = new ArrayList<String>();
		caseManagementList.add("QA User (VF)");
		caseManagementList.add("Pre-Assessor (VF)");
		
		List<String> workFlowList = new ArrayList<String>();
		workFlowList.add("Access Enterprise Workflow");
		workFlowList.add("Open Item");
		workFlowList.add("Delete Item");
		workFlowList.add("Pick Item");
		workFlowList.add("Return Item");
		workFlowList.add("Forward Items By User");
		workFlowList.add("Forward Items By Role");
		workFlowList.add("Resubmit Item");
		workFlowList.add("Display Audit Information");
		
		userPermission.put("System Administration", systemAdministrationList);
		userPermission.put("Admin", adminList);
		userPermission.put("Intray", intrayList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Folder", folderList);
		userPermission.put("Document", documentList);
		userPermission.put("Notes", notesList);
		userPermission.put("Archive", archiveList);
		userPermission.put("Reports", reportsList);
		userPermission.put("Cold", coldList);
		//userPermission.put("Memo", memoList);
		userPermission.put("Case Management", caseManagementList);
		userPermission.put("Enterprise Workflow", workFlowList);
		
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		new AdminUserSection(driver).clickOnUserNavTab("General");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(fileSystemAdminUserName, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUserName, ObjectReader.reader.setUserPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		new CapturePage(driver).clickOnCaptureTab();
		new CapturePage(driver).clickOnDocumentCaptureBtn();
	}
	
	@Test(priority=25,enabled = true,dataProvider = "captureFormData")
	public void VerifyFileSystemAdminUserCaptureDocument(Map<String, String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=fileSystemAdminUserName,
				documentType=map.get("DocumentType"),
				docRef= "FileS"+generateRandomNumber();
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
			capture.enterDocRef(docRef);
			
			capture.clickOnIndexDocument();
			sleepFor(2000);
			
			getApplicationUrl();
			capture.selectSearchTab(); 
			capture.searchWithCriteria("Doc Ref",docRef);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
	}

	@Test(priority=26,enabled = true)
	public void VerifySupervisorUserAccessCapturePage() throws InterruptedException {
		supervisorUserName = "SUV"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(supervisorUserName);
        new AdminUserSection(driver).enterUsername(supervisorUserName);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
        new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		//systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Supervisor");
		//systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		//systemAdministrationList.add("Stamp Administrator");
		//systemAdministrationList.add("Is RM Administrator");
		//systemAdministrationList.add("Folder Security Administrator");
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		//systemAdministrationList.add("Allow Flexible entity Import ");
		//systemAdministrationList.add("Allow All FileSystem On Integration ");
		//systemAdministrationList.add("Is System Implementer");
		
		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		//adminList.add("Update Environment");
		
		List<String> intrayList = new ArrayList<String>();
		intrayList.add("View Other Users In-Trays");
		intrayList.add("View Other Teams In-Trays");
		//intrayList.add("View Intrays Across File Systems");
		
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");
		
		List<String> folderList = new ArrayList<String>();
		//folderList.add("Update Restricted Folder Reference Documents");
		folderList.add("Allow Create Dummy Folder References ");
		folderList.add("Allow Translate Folder References");

		List<String> documentList = new ArrayList<String>();
		//documentList.add("Delete Documents");
		documentList.add("Use Clipbook");
		documentList.add("Create Renditions and apply Redaction Templates");
		documentList.add("Edit Documents (CheckIn and CheckOut)");
		documentList.add("Create and edit Redaction Templates");
		documentList.add("Scan Documents");
		documentList.add("Batch Index Documents");
		documentList.add("Capture Documents");
		documentList.add("ReIndex Documents");
		documentList.add("Allow locking of documents to prevent editing");
		documentList.add("Allow unlocking of documents to allow editing");
		documentList.add("Allow routing of reindexed documents");
		documentList.add("Alter Protective Marker");
		documentList.add("Can Export Document");
		documentList.add("Can Export Document Metadata");
		documentList.add("Can Edit Metadata");
		documentList.add("Can Print Document");
		documentList.add("Allow Document Linking");
		
		//List<String> notesList = new ArrayList<String>();
		//notesList.add("Delete / Update Notes");
		
		//List<String> archiveList = new ArrayList<String>();
		//archiveList.add("Archive Documents");
		
		List<String> reportsList = new ArrayList<String>();
		reportsList.add("View Reports");
		
		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");
		
		//List<String> memoList = new ArrayList<String>();
		//memoList.add("Update Another User's Memos (excluding Intray)");
		
		List<String> caseManagementList = new ArrayList<String>();
		caseManagementList.add("QA User (VF)");
		caseManagementList.add("Pre-Assessor (VF)");
		
		List<String> workFlowList = new ArrayList<String>();
		workFlowList.add("Access Enterprise Workflow");
		workFlowList.add("Open Item");
		workFlowList.add("Delete Item");
		workFlowList.add("Pick Item");
		workFlowList.add("Return Item");
		workFlowList.add("Forward Items By User");
		workFlowList.add("Forward Items By Role");
		workFlowList.add("Resubmit Item");
		workFlowList.add("Display Audit Information");
		
		userPermission.put("System Administration", systemAdministrationList);
		userPermission.put("Admin", adminList);
		userPermission.put("Intray", intrayList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Folder", folderList);
		userPermission.put("Document", documentList);
		//userPermission.put("Notes", notesList);
		//userPermission.put("Archive", archiveList);
		userPermission.put("Reports", reportsList);
		userPermission.put("Cold", coldList);
		//userPermission.put("Memo", memoList);
		userPermission.put("Case Management", caseManagementList);
		userPermission.put("Enterprise Workflow", workFlowList);
		
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		new AdminUserSection(driver).clickOnUserNavTab("General");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(supervisorUserName, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(supervisorUserName, ObjectReader.reader.setUserPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		new CapturePage(driver).clickOnCaptureTab();
		new CapturePage(driver).clickOnDocumentCaptureBtn();
	}
	
	@Test(priority=27,enabled = true,dataProvider = "captureFormData")
	public void VerifySupervisorUserCaptureDocument(Map<String, String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=supervisorUserName,
				documentType=map.get("DocumentType"),
				docRef= "FileS"+generateRandomNumber();
	
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
			capture.enterDocRef(docRef);
			
			capture.clickOnIndexDocument();
			sleepFor(2000);
			
			getApplicationUrl();
			capture.selectSearchTab(); 
			capture.searchWithCriteria("Doc Ref",docRef);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		}		
	}
	
	@Test(priority=28,enabled = true)
	public void VerifyStandardUserAccessCapturePage() throws InterruptedException {
		standardUserName = "STD"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(standardUserName);
        new AdminUserSection(driver).enterUsername(standardUserName);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
        new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Can Create Tags ");
		
		List<String> adminList = new ArrayList<String>();
		adminList.add("DIP");
		//adminList.add("Update Environment");
		
		List<String> intrayProcessingList = new ArrayList<String>();
		intrayProcessingList.add("Take Work from a Shared In-Tray");
		intrayProcessingList.add("Process Work From Another Users In-Tray");
		intrayProcessingList.add("Distribute Work to Other Users In-Trays");
		
		List<String> folderList = new ArrayList<String>();
		//folderList.add("Update Restricted Folder Reference Documents");
		folderList.add("Allow Create Dummy Folder References ");
		folderList.add("Allow Translate Folder References");

		List<String> documentList = new ArrayList<String>();
		//documentList.add("Delete Documents");
		documentList.add("Use Clipbook");
		documentList.add("Create Renditions and apply Redaction Templates");
		documentList.add("Edit Documents (CheckIn and CheckOut)");
		//documentList.add("Create and edit Redaction Templates");
		documentList.add("Scan Documents");
		documentList.add("Batch Index Documents");
		documentList.add("Capture Documents");
		documentList.add("ReIndex Documents");
		//documentList.add("Allow locking of documents to prevent editing");
		//documentList.add("Allow unlocking of documents to allow editing");
		documentList.add("Allow routing of reindexed documents");
		//documentList.add("Alter Protective Marker");
		documentList.add("Can Export Document");
		documentList.add("Can Export Document Metadata");
		documentList.add("Can Edit Metadata");
		documentList.add("Can Print Document");
		documentList.add("Allow Document Linking");
		
		List<String> coldList = new ArrayList<String>();
		coldList.add("COLD");
		coldList.add("Allow Cold Document Redaction");
		
		userPermission.put("System Administration", systemAdministrationList);
		userPermission.put("Admin", adminList);
		userPermission.put("Intray Processing", intrayProcessingList);
		userPermission.put("Folder", folderList);
		userPermission.put("Document", documentList);
		userPermission.put("Cold", coldList);

		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		new AdminUserSection(driver).clickOnUserNavTab("General");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(standardUserName, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(standardUserName, ObjectReader.reader.setUserPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		new CapturePage(driver).clickOnCaptureTab();
		new CapturePage(driver).clickOnDocumentCaptureBtn();
	}
	
	@Test(priority=29,enabled = true,dataProvider = "captureFormData")
	public void VerifyStandardUserCaptureDocument(Map<String, String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=standardUserName,
				documentType=map.get("DocumentType"),
				docRef= "FileS"+generateRandomNumber();
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
			capture.enterDocRef(docRef);
			
			capture.clickOnIndexDocument();
			sleepFor(2000);
			
			getApplicationUrl();
			capture.selectSearchTab(); 
			capture.searchWithCriteria("Doc Ref",docRef);
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
	}
	
	@Test(priority=30,enabled = true)
	public void VerifyBasicUserCannotAccessCapturePage() throws InterruptedException {
		basicUserName = "BAS"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(basicUserName);
        new AdminUserSection(driver).enterUsername(basicUserName);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
        new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> folderList = new ArrayList<String>();
		folderList.add("Deny Create Folder1");
		folderList.add("Deny Update Folder1");
		folderList.add("Deny Delete Folder1");
		folderList.add("Deny Create Folder2");
		folderList.add("Deny Create Folder2");
		folderList.add("Deny Create Folder2");
		folderList.add("Deny Create Folder3");
		folderList.add("Deny Create Folder3");
		folderList.add("Deny Create Folder3");
		
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToTop();
		new AdminUserSection(driver).clickOnUserNavTab("General");
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//For new user change password
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentialsForChangePassword(basicUserName, "P@ssword123");
		
		//Login with new user
		new LoginPage(driver).loginWithCredentials(basicUserName, ObjectReader.reader.setUserPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		new CapturePage(driver).clickOnCaptureTab();
		boolean captureTab = new VerificationHelper(driver).isElementDisplayedByEle(new CapturePage(driver).documentCaptureBtn);
		System.out.println("captureTab status======="+captureTab);
		AssertionHelper.verifyFalse(captureTab);
	}
	
	@Test(priority=31,enabled = true,dataProvider = "captureFormData")
	public void VerifyBasicUserCannotCaptureDocument(Map<String, String> map) throws InterruptedException {
		String routingType="To User",
				routingTo=basicUserName,
				documentType=map.get("DocumentType"),
				docRef= "FileS"+generateRandomNumber();
		
		try {
			getApplicationUrl();
			new CapturePage(driver).clickOnCaptureTab();
			boolean captureTab = new VerificationHelper(driver).isElementDisplayedByEle(new CapturePage(driver).documentCaptureBtn);
			System.out.println("captureTab status======="+captureTab);
			AssertionHelper.verifyFalse(captureTab);
		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}
	
	@Test(priority=32,enabled = true)
	public void VerifySuperAdminUserCanAccessEditableScreenAndViewEditableDoc() throws InterruptedException {
		
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			getApplicationUrl();
			new CapturePage(driver).clickOnCaptureTab();
			docTools.clickonEditableDocumentUnderCapture();
//			docTools.clickOnFirstItemInList();
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			navigationMenu.clickOnIcon("View document", "Actions");
//			sleepFor(2000);
//			
//			new WindowHelper(driver).switchToNewlyOpenedTab();
//			new WaitHelper(driver).waitForElement(new IntrayToolsPage(driver).viewerTabHeader, 10);
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();
		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}		
	}
	
	@Test(priority=33,enabled = true)
	public void VerifyFileSystemAdminUserCanAccessEditableDocScreenAndViewEditableDoc() throws InterruptedException {
		
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(fileSystemAdminUserName, ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			getApplicationUrl();
			new CapturePage(driver).clickOnCaptureTab();
			docTools.clickonEditableDocumentUnderCapture();
//			docTools.clickOnFirstItemInList();
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			navigationMenu.clickOnIcon("View document", "Actions");
//			sleepFor(2000);
//			
//			new WindowHelper(driver).switchToNewlyOpenedTab();
//			new WaitHelper(driver).waitForElement(new IntrayToolsPage(driver).viewerTabHeader, 10);
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}		
	}
	
	@Test(priority=34,enabled = true)
	public void VerifySupervisorUserCanAccessEditableDocScreenAndViewEditableDoc() throws InterruptedException {
		
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(supervisorUserName, ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			getApplicationUrl();
			new CapturePage(driver).clickOnCaptureTab();
			docTools.clickonEditableDocumentUnderCapture();
//			docTools.clickOnFirstItemInList();
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			navigationMenu.clickOnIcon("View document", "Actions");
//			sleepFor(2000);
//			
//			new WindowHelper(driver).switchToNewlyOpenedTab();
//			new WaitHelper(driver).waitForElement(new IntrayToolsPage(driver).viewerTabHeader, 10);
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}		
	}
	
	@Test(priority=35,enabled = true)
	public void VerifyStandardUserCanAccessEditableDocScreenAndViewEditableDoc() throws InterruptedException {
		
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(standardUserName, ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			getApplicationUrl();
			new CapturePage(driver).clickOnCaptureTab();
			docTools.clickonEditableDocumentUnderCapture();
//			docTools.clickOnFirstItemInList();
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			navigationMenu.clickOnIcon("View document", "Actions");
//			sleepFor(2000);
//			
//			new WindowHelper(driver).switchToNewlyOpenedTab();
//			new WaitHelper(driver).waitForElement(new IntrayToolsPage(driver).viewerTabHeader, 10);
//			System.out.println("First Switch=========="+driver.getWindowHandle());
//			new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}		
	}
	
	@Test(priority=36,enabled = true)
	public void VerifyBasicdUserCannotAccessEditableDocScreenAndNotableViewEditableDoc() throws InterruptedException {
		
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(basicUserName, ObjectReader.reader.getPassword());
			getApplicationUrl();
			new CapturePage(driver).clickOnCaptureTab();
			boolean editableBtnStatus = new VerificationHelper(driver).isElementDisplayedByEle(new CapturePage(driver).editableDocumentButton);
			AssertionHelper.verifyFalse(editableBtnStatus);

		} finally {
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		}
	}
	
	/*@Test(priority=37,enabled = true)
	public void VerifyThatSuperAdminUserCanAccessAndCreateFolder1Restriction() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String refName=ObjectReader.reader.getFolder1RefName();
		folder1RefSAU ="SAUF"+generateRandomNumber(); 
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());

		getApplicationUrl();
		new AdminFolderSection(driver).navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		new AdminFolderSection(driver).enterFolderRef(folder1RefSAU);
		new AdminFolderSection(driver).clickOnSaveInFolderRestriction(refName,getEnvVariable);
	}
	
	@Test(priority=38,enabled = true)
	public void ThatSuperAdminUserCanEditFolder1RestrictionAndDelete() {
		String surname = "SA_"+generateRandomNumber();
		getApplicationUrl();
		new AdminFolderSection(driver).navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		new AdminFolderSection(driver).enterFolderRef(folder1RefSAU);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		click(new AdminFolderSection(driver).folder1RefEditBtn, "Clickin on folder1 ref edit button");
		navigationMenu.waitForIcon("Translate "+folder1RefSAU,"Translate");
		navigationMenu.clickOnNavTab("Person");
    	sendKeys(new FolderFlagReference(driver).surnameFieldInput, surname, "Entering Surname "+surname);
    	sleepFor(1000);
    	navigationMenu.clickOnSaveIcon();
        navigationMenu.waitForIconWithDataButton("Save", "Actions");
        sleepFor(3000);
        
        getApplicationUrl();
        new AdminFolderSection(driver).navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folder1RefSAU , "Inputing the text "+folder1RefSAU);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(3000);
	}*/
	
	@Test(enabled = true,priority = 37)
	public void AddSecurityRestrictionGroupForSuperAdminAndAddFolderRef() throws InterruptedException {							
		superAdmingroupName="SAUG"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder1RefName();
		folder1RefSAU ="SAUF"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.clickOnAddIcon();
		new AdminFolderSection(driver).enterGroupName(superAdmingroupName);
		sendKeys(new AdminFolderSection(driver).folder1RefInput, folder1RefSAU, "Entering reference "+folderRefListSAU);
		new AdminFolderSection(driver).AddNewFolderRefFromGroupRestriction(refName,getEnvVariable);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnSaveIcon();
			sleepFor(2000);
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//To verify that SAU able to add folder restriction throught group edit
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(superAdmingroupName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("securityRestrictionGroupDataTable");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Cancel changes","Actions");
		
		sendKeys(new AdminFolderSection(driver).folder1RefInput, "F1-001R1", "Entering reference "+folderRefListSAU);
		new AdminFolderSection(driver).AddNewFolderRefFromGroupRestriction(refName,getEnvVariable);
		
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnSaveIcon();
			sleepFor(2000);
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}
	
	@Test(enabled = true,priority = 38)
	public void AddSecurityRestrictionGroupUsingFileSystemAdminUserAndAddFolderRef() throws InterruptedException {							//Adding folder ref under group
		fileSystemgroupName = "FSAG"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder1RefName();
		folder1RefFSA ="FSA_"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.clickOnAddIcon();
		new AdminFolderSection(driver).enterGroupName(fileSystemgroupName);
		sendKeys(new AdminFolderSection(driver).folder1RefInput, folder1RefFSA, "Entering reference "+folderRefListSAU);
		new AdminFolderSection(driver).AddNewFolderRefFromGroupRestriction(refName,getEnvVariable);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnSaveIcon();
			sleepFor(2000);
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
	}

	@Test(enabled = true,priority = 39)
	public void DeleteFolderRefThroughEditRestricitonUsingFSA() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(fileSystemgroupName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("securityRestrictionGroupDataTable");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Cancel changes","Actions");
		
		new AdminFolderSection(driver).DeleteFolderRestrictionFromGroup(folder1RefFSA);
		new WindowHelper(driver).waitForPopup("Delete");
		new NavigationMenu(driver).clickOkOnPopup();
		sleepFor(2000);
		
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnSaveIcon();
			sleepFor(2000);
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}		

	}
	
	@Test(enabled = true,priority = 40)
	public void DeleteFolderRefThroughEditRestricitonUsingSAU() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(superAdmingroupName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("securityRestrictionGroupDataTable");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Cancel changes","Actions");
		
		new AdminFolderSection(driver).DeleteFolderRestrictionFromGroup(folder1RefSAU);
		new WindowHelper(driver).waitForPopup("Delete");
		new NavigationMenu(driver).clickOkOnPopup();
		sleepFor(2000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnSaveIcon();
			sleepFor(2000);
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}		
	}
	
	@Test(enabled = true,priority = 41)
	public void DeleteRestrictionGroupUsingSuperAdminUser() {
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(superAdmingroupName);
		
		navigationMenu.clickOnFilteredRow("securityRestrictionGroupDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(4000);
	}
	
	@Test(enabled = true,priority = 42)
	public void DeleteRestrictionGroupUsingFileSystemUser() throws InterruptedException {
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(fileSystemgroupName);
		
		navigationMenu.clickOnFilteredRow("securityRestrictionGroupDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(4000);
	}
	
	@Test(enabled = true,priority = 43)
	public void VerifyThatSuperAdminUserCanAccessAndCreateSecurityProfile() throws InterruptedException {
		superAdminSecurityProfie1 = "TeamSAU_" + generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(superAdminUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");
		navigationMenu.clickOnAddIcon();

		new AdminUserSection(driver).enterProfileName(superAdminSecurityProfie1);
		new AdminUserSection(driver).enterProfileDescription("profile description");
		new AdminUserSection(driver).clickOnUserNavTab("Security");

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");

		userPermission.put("System Administration", systemAdministrationList);

		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		navigationMenu.clickOnIconMenu("Save changes made to security profile", "Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled = true,priority = 44)
	public void VerifyThatSuperAdminUserEditSecurityProfile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(superAdminSecurityProfie1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		sleepFor(2000);
		new AdminUserSection(driver).enterProfileDescription("profile description updated");
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> systemAdminList = new ArrayList<String>();
		systemAdminList.add("System Administration");
		systemAdminList.add("Is Administrator");
		
		userPermission.put("System Administration", systemAdminList);
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//To verify security rights after editing
		HashMap<String, List<String>> userPermission1 = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		userPermission1.put("System Administration", systemAdministrationList);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(superAdminSecurityProfie1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);
		new AdminUserSection(driver).verifySecurityPermissionAfterOverriding(userPermission1);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled = true,priority = 45)
	public void CreateUserAndAssignSecurityProfileUsingSAU() {							//Case 42
		 superAdminCreatedUser = "SAUCRE_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(superAdminCreatedUser);
        new AdminUserSection(driver).enterUsername(superAdminCreatedUser);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(3000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(1000);
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		
		userPermission.put("System Administration", systemAdministrationList);
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		
		//For existing user adding security profile
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminCreatedUser);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(4000);
		new AdminUserSection(driver).selectUserSecurityProfile(superAdminSecurityProfie1,getEnvVariable);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		String getValidationErrorMessage=new AdminUserSection(driver).getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		new WindowHelper(driver).waitForPopup("User Security Profile");
        String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
        System.out.println("getPopUpMessage "+getPopUpMessage);
        AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
        sleepFor(1000);
        new WindowHelper(driver).clickOnModalFooterButton("No");
        sleepFor(3000);
        navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled = true,priority = 46)
	public void VerifyThatSuperAdminUserCanDeleteSecurityProfile() {
		//Unassigning security profile from user because of deleter purpose
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(superAdminCreatedUser);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(4000);
		new AdminUserSection(driver).selectUserSecurityProfile("(none)",getEnvVariable);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		navigationMenu.waitForAddIcon();

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(superAdminSecurityProfie1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//navigationMenu.clickOnDeleteIcon();
		navigationMenu.clickOnIconMenu("Delete this security profile", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		navigationMenu.clickOkOnPopup();
		sleepFor(3000);
	}

	
	@Test(enabled = true,priority = 47)
	public void VerifythatFileSystemAdminUserCanAccessAndCreateSecurityProfile() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		fileSystemAdminSecurityProfile1 = "TeamFSA_"+generateRandomNumber();
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(fileSystemAdminUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Profile maintenance", "User");
		navigationMenu.clickOnAddIcon();

		new AdminUserSection(driver).enterProfileName(fileSystemAdminSecurityProfile1);
		new AdminUserSection(driver).enterProfileDescription("profile description");
		new AdminUserSection(driver).clickOnUserNavTab("Security");

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");

		userPermission.put("System Administration", systemAdministrationList);

		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		navigationMenu.clickOnIconMenu("Save changes made to security profile", "Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled = true,priority = 48)
	public void VerifyThatFileSystemAdminUserEditSecurityProfile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(fileSystemAdminSecurityProfile1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		sleepFor(2000);
		new AdminUserSection(driver).enterProfileDescription("profile description updated");
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		
		List<String> systemAdminList = new ArrayList<String>();
		systemAdminList.add("System Administration");
		systemAdminList.add("Is Administrator");
		
		userPermission.put("System Administration", systemAdminList);
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to security profile","Actions");
			//navigationMenu.waitForAddIcon();
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
		//To verify security rights after editing
		HashMap<String, List<String>> userPermission1 = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("System Administration");
		systemAdministrationList.add("Is Administrator");
		systemAdministrationList.add("Is Supervisor");
		systemAdministrationList.add("Campaign Admin");
		systemAdministrationList.add("Alter User/Team Campaigns");
		userPermission1.put("System Administration", systemAdministrationList);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(fileSystemAdminSecurityProfile1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);
		new AdminUserSection(driver).verifySecurityPermissionAfterOverriding(userPermission1);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled = true,priority = 49)
	public void CreateUserAndAssignSecurityProfileUsingFSA() {							//Case 43
		String fileSystemAdminCreatedUser = "FSACRE_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.clickOnAddIcon();
        
        new AdminUserSection(driver).enterUserId(fileSystemAdminCreatedUser);
        new AdminUserSection(driver).enterUsername(fileSystemAdminCreatedUser);
        sleepFor(1000);
        new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(3000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(1000);
		
		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
		List<String> systemAdministrationList = new ArrayList<String>();
		systemAdministrationList.add("Can Create Tags ");
		systemAdministrationList.add("Can Edit Tags ");
		systemAdministrationList.add("Can Delete Tags ");
		
		userPermission.put("System Administration", systemAdministrationList);
		new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		
		//For existing user adding security profile
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(fileSystemAdminCreatedUser);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(4000);
		new AdminUserSection(driver).selectUserSecurityProfile(fileSystemAdminSecurityProfile1,getEnvVariable);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		String getValidationErrorMessage=new AdminUserSection(driver).getValidationMessage();
		AssertionHelper.verifyText(getValidationErrorMessage, "User Security conflicted with selected Profile Security.");
		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		new WindowHelper(driver).waitForPopup("User Security Profile");
        String getPopUpMessage = new WindowHelper(driver).getPopupMessage();
        System.out.println("getPopUpMessage "+getPopUpMessage);
        AssertionHelper.verifyTextContains(getPopUpMessage, "There is a conflict between the user's security attributes you have selected here and the linked profile's security attributes.Do you want to override the profile's security attributes?");
        sleepFor(1000);
        new WindowHelper(driver).clickOnModalFooterButton("No");
        sleepFor(3000);
        navigationMenu.waitForAddIcon();
	}

	
	@Test(priority=50,enabled = true)
	public void VerifyThatFileSystemAdminUserCanDeleteSecurityProfile() {
		//Unassigning security profile from user because of deleter purpose
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(fileSystemAdminCreatedUser);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(4000);
		new AdminUserSection(driver).selectUserSecurityProfile("(none)",getEnvVariable);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
		navigationMenu.waitForAddIcon();

		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Security Profile maintenance", "User");
        navigationMenu.searchInFilter(fileSystemAdminSecurityProfile1);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRowLink("securityProfilesTable");
		//navigationMenu.clickOnDeleteIcon();
		navigationMenu.clickOnIconMenu("Delete this security profile", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		navigationMenu.clickOkOnPopup();
		sleepFor(3000);
	}


	
	@Test(priority=51,enabled = true)				//SUV1163,STD8396,BAS6590
	public void VerifyThatSupervisourUserCanNotAccessAndCreateSecurityProfile() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String refName=ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(supervisorUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();
		new NavigationMenu(driver).clickOnTab("Administration");
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(new AdminUserSection(driver).securityProfileBtn);
		AssertionHelper.verifyFalse(status);
	}
	
	@Test(priority=52,enabled = true)
	public void VerifyThatStandardUserCanNotAccessAndCreateSecurityProfile() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String refName=ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		
		new LoginPage(driver).loginWithCredentials(standardUserName, ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();
		//new NavigationMenu(driver).clickOnTab("Administration");
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(new AdminUserSection(driver).securityProfileBtn);
		AssertionHelper.verifyFalse(status);
	}
	@Test(priority=53,enabled = true)
	public void VerifyThatBasicUserCanNotAccessAndCreateSecurityProfile() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String refName=ObjectReader.reader.getFolder1RefName();
		
		try {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(basicUserName, ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
			
			getApplicationUrl();
			//new NavigationMenu(driver).clickOnTab("Administration");
			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(new AdminUserSection(driver).securityProfileBtn);
			AssertionHelper.verifyFalse(status);
		} finally {
			getApplicationUrl();
			new HomePage(driver).clickOnChangeUser();
			sleepFor(2000);
			
			new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
	}

}
