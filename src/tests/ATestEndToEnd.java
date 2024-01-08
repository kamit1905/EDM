package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import utils.ExtentReports.ExtentTestManager;


//Add STD1 user & add it in testing team

public class ATestEndToEnd extends TestBase {
	
	public AdminDocumentSection adminDoc;
	public IntrayToolsPage intrayTools ;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	public LoginPage login;
	public DocumentToolsPage docTools;
	
	private String textAreaDesctiption="";
	private String pendingPeriod= "";
	private String allocateDocRef1 = "";
	private String distributeDocRef1 = "";
	private String allocateDocRef2 = "";
	private String diffFSNameForRefresh;
	private String distrNote5 = "";
	
	@BeforeClass
	public void setupClass()  {
		adminDoc = new AdminDocumentSection(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		windowHelper=new WindowHelper(driver);
		xls = new ExcelReader();
		dropdownHelper=new DropdownHelper(driver);
		intrayTools=new IntrayToolsPage(driver);
		login = new LoginPage(driver);
		docTools = new DocumentToolsPage(driver);
	}
	
    @DataProvider(name="docreReferenceData")
    public Object[][] docreReferenceData() throws Exception
    {
        Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");   
        return formData;
    }
	
	@Test(enabled = true,priority = 0,dataProvider = "docreReferenceData")
	public void GetDiffFSName(String doctype, String folder2Ref, String docref, String fileSystem) {
		diffFSNameForRefresh = fileSystem;
	}
	
	@Test(priority=102,enabled = true)
	public void TestUpdateDocumentType() throws InterruptedException {
		pendingPeriod = "10" ;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new AlertHelper(driver).acceptAlertIfPresent();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.searchInFilter("Default");
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		//adminDoc.enterDescription(updatedDesc);
		navigationMenu.clickOnNavTab("General");
		new AdminDocumentSection(driver).enterPeriodInput(pendingPeriod);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(priority=103,enabled = true)
	public void CaptureDocAndVerifyPendingPeriod() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String docRef = "AD"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		
		if(getEnvVariable.equals("Enterprise_Sp1s") || getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531")) {
		capture.changeEmailStatus("Pending");				//Changed drop down locator for email status in R531
		}else {
			capture.changeMailStatusUsingSelectClassAndVerifyPendingPeriod("Pending",String.valueOf(pendingPeriod));
		}
	}
	
	@Test(priority=104,enabled = true)
	public void TestUpdateDocumentTypeBackToOriginal() throws InterruptedException {
		pendingPeriod = "10" ;
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new AlertHelper(driver).acceptAlertIfPresent();
		adminDoc.navigateToAdminDocumentTypesActive();
		//navigationMenu.clickOnSpecificRow("last()");
		navigationMenu.searchInFilter("Default");
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		//adminDoc.enterDescription(updatedDesc);
		navigationMenu.clickOnNavTab("General");
		new AdminDocumentSection(driver).enterPeriodInput(String.valueOf(0));
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(priority = 105, enabled = true)
	public void TestDocumentLandingPageDocumentDelete() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String expMsg = "Document(s) deleted successfully.";
		String docRef1 = "STF"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef1, null,getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", docRef1);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		intrayTools.clickOnIntrayLandingPageIcon();
		String actMsg = intrayTools.clickOnDocumentDelete();
	}
	
	
	@Test(priority=106,enabled = true)
	public void CaptureMultipleDocAndDeleteit() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String docRef1 = "AD1"+generateRandomNumber();
		String docRef2 = "AD12"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef1, null,getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef2, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","AD1%");
		
		capture.clickOnIntrayListBtn();
		navigationMenu.SelectMultiCheckbox();
		navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"2");
		
		intrayTools.clickOnDocumentDelete();
	}
	
	@Test(priority=107,enabled = true)
	public void RemoveSecurityRightsOfUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();

		//login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(ObjectReader.reader.getUserName()); 										// User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> sysAdmin = new ArrayList<String>();
		sysAdmin.add("System Administration");
		
		
		List<String> documentList = new ArrayList<>(); 
		documentList.add("Delete Documents");

		userPermission.put("System Administration", sysAdmin);
		userPermission.put("Document", documentList);

		new AdminUserSection(driver).RemoveSecurityRightsFromUser(userPermission);
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIconForUser();
			navigationMenu.waitForAddIcon();
		}
		sleepFor(1000);
	}
	
	@Test(priority=108,enabled = true)
	public void verifyThatDeleteDocumentBtnIsNotVisible() {
		new HomePage(driver).refreshCurrentFileSystem();
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		boolean status = new VerificationHelper(driver).isElementDisplayed(By.xpath("//td//button[contains(@data-bs-original-title,'Delete document')]"));
		AssertionHelper.verifyFalse(status);
	}
	
	/*@Test(priority=109,enabled = true)
	public void UpdateUserSecurityRightsToOriginal() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();

		//login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(ObjectReader.reader.getUserName()); 										// User_N147 //User_N5341
		navigationMenu.clickOnFilteredRow("userDataTable");
		navigationMenu.clickOnEditIcon();
		sleepFor(2000);
		new AdminUserSection(driver).clickOnUserNavTab("Security");
		sleepFor(2000);

		HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

		List<String> sysAdmin = new ArrayList<String>();
		sysAdmin.add("System Administration");
		
		
		List<String> documentList = new ArrayList<>(); 
		documentList.add("Delete Documents");

		userPermission.put("System Administration", sysAdmin);
		userPermission.put("Document", documentList);

		new AdminUserSection(driver).RemoveSecurityRightsFromUser(userPermission);
		sleepFor(1000);
		if (getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
			// navigationMenu.waitForAddIcon();
		} else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		sleepFor(1000);
		new HomePage(driver).refreshCurrentFileSystem();
	}
	
	@Test(priority=110,enabled = true)
	public void VerifyAuditActionBtnOnDocTools() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String docRef1 = "HP"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef1, null,getEnvVariable);

		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",docRef1 );
		docTools = capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		docTools.clickOnReReferenceButton();
		docTools.selectDocumentType("ScanDoc - Generic Scanned Document",getEnvVariable);
		docTools.clickOnApplyButton();
		String content = docTools.getReferencePopupContent();
		AssertionHelper.verifyTrue(content.contains("Document Type: ScanDoc"),
				"Content on Confirm re-reference popup is not proper");
		docTools.clickYesOnConfirmReferencePopUp();
		docTools.clickOkOnRereferenceSusccessfulPopup();


		//Verify audit action using intray
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef1);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		sleepFor(2000);
		String getIntrayAuditDetails = new IntrayToolsPage(driver).intrayAuditDetails.getText();
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		AssertionHelper.verifyTextContains(getIntrayAuditDetails, "Document Type Description changed from Default to ScanDoc.");
		sleepFor(3000);
		
		//Verify audit action using landing page of docuemnt
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(new IntrayToolsPage(driver).documentAuditBtn, "Clicking on Document audit button");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		String getLandingPageAuditDetails = new IntrayToolsPage(driver).documentLandingPageAuditDetails.getText();
		AssertionHelper.verifyTextContains(getLandingPageAuditDetails, "Document Type Description changed from Default to ScanDoc.");
	}
	
	@Test(priority=111,enabled = true)
	public void VerifyAuditActionBtnDisable() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
		
		navigationMenu.SelectMultiCheckbox();
		navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"2");
		
		boolean status = new VerificationHelper(driver).isElementEnabled(new IntrayToolsPage(driver).intrayAuditActionBtn);
		AssertionHelper.verifyFalse(status);
	}

	
	@Test(priority=112,enabled = true)
	public void AuditIntrayAllocateForDocument() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		allocateDocRef1 = "PP"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, allocateDocRef1, null,null,"Imasys Admin User",getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",allocateDocRef1);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Allocate intray item", "Mail");
		new WindowHelper(driver).waitForModalDialog("Allocate intray item");
		new DropdownHelper(driver).selectFromAutocompleDD(ObjectReader.reader.getUserName(), new IntrayToolsPage(driver).allocateUserDD, getEnvVariable);
		click(new IntrayToolsPage(driver).allocateOkPopUpBtn, "Clicking in allocate ok button");
		sleepFor(2000);
	}
	
	@Test(priority=113,enabled = true)
	public void VerifyAuditActionOfAllowIntray() {
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",allocateDocRef1);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
		click(new IntrayToolsPage(driver).intrayActionsBtnOnAuditAction, "clicking on intray actions");
		sleepFor(1000);
		String getIntrayAuditDetailsFromCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("AllocateIntrayItem","7");
		System.out.println("====================== "+getIntrayAuditDetailsFromCol);
		//String getIntrayAuditDetailsToCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("AllocateIntrayItem",8);
		//System.out.println("====================== "+getIntrayAuditDetailsToCol);

		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Verify audit action using landing page of docuemnt
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(new IntrayToolsPage(driver).intrayAuditBtn, "Clicking on Intray audit button");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		String getLandingPageAuditFromDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("AllocateIntrayItem","7");
		System.out.println("=============================="+getLandingPageAuditFromDetails);
		
		String getLandingPageAuditToDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("AllocateIntrayItem","8");
		System.out.println("=============================="+getLandingPageAuditToDetails);
		
		AssertionHelper.verifyTextContains(getIntrayAuditDetailsFromCol, ObjectReader.reader.getSuperLoginId().substring(0,1).toUpperCase()+ObjectReader.reader.getSuperLoginId().substring(1).toLowerCase());
		//AssertionHelper.verifyTextContains(getIntrayAuditDetailsToCol, ObjectReader.reader.getLoginId());
		
		AssertionHelper.verifyTextContains(getLandingPageAuditFromDetails, ObjectReader.reader.getSuperLoginId().substring(0,1).toUpperCase()+ObjectReader.reader.getSuperLoginId().substring(1).toLowerCase());
		AssertionHelper.verifyTextContains(getLandingPageAuditToDetails, ObjectReader.reader.getLoginId());
	}
	
	@Test(priority=114,enabled = true)
	public void DistributeAnIntrayItem() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		distributeDocRef1 = "PPD"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distributeDocRef1, null,null,"Imasys Admin User",getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distributeDocRef1);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getLoginId());
		sleepFor(1000);
		intrayTools.enterNotesPillsInEscalate("Notes added");
		new ActionHelper(driver).pressTab();
		intrayTools.clickOnModalApplyChangesButtonWithPopup();
		sleepFor(2000);
	}
	
	@Test(priority=115,enabled = true)
	public void VerifyAuditActionOfDistribute() {
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",distributeDocRef1);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
		click(new IntrayToolsPage(driver).intrayActionsBtnOnAuditAction, "clicking on intray actions");
		sleepFor(1000);
		String getIntrayAuditDetailsFromCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("DistributeIntrayItem","7");
		System.out.println("====================== "+getIntrayAuditDetailsFromCol);
		//String getIntrayAuditDetailsToCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("AllocateIntrayItem",8);
		//System.out.println("====================== "+getIntrayAuditDetailsToCol);

		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Verify audit action using landing page of docuemnt
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(new IntrayToolsPage(driver).intrayAuditBtn, "Clicking on Intray audit button");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		String getLandingPageAuditFromDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("DistributeIntrayItem","7");
		System.out.println("=============================="+getLandingPageAuditFromDetails);
		
		String getLandingPageAuditToDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("DistributeIntrayItem","8");
		System.out.println("=============================="+getLandingPageAuditToDetails);
		
		AssertionHelper.verifyTextContains(getIntrayAuditDetailsFromCol, ObjectReader.reader.getSuperLoginId().substring(0,1).toUpperCase()+ObjectReader.reader.getSuperLoginId().substring(1).toLowerCase());
		//AssertionHelper.verifyTextContains(getIntrayAuditDetailsToCol, ObjectReader.reader.getLoginId());
		
		AssertionHelper.verifyTextContains(getLandingPageAuditFromDetails, ObjectReader.reader.getSuperLoginId().substring(0,1).toUpperCase()+ObjectReader.reader.getSuperLoginId().substring(1).toLowerCase());
		AssertionHelper.verifyTextContains(getLandingPageAuditToDetails, ObjectReader.reader.getLoginId());
	}
	
	@Test(priority=116,enabled = true)
	public void TakeWorkFromSharedIntrayAudit() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String takeShared1 = "TS"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, takeShared1, null,"To Team",ObjectReader.reader.getLoggedInUsersTeam(),getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", takeShared1);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Take shared mail item", "Mail");
		new WindowHelper(driver).waitForPopup("Take Item");
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Veriyf audit action details
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
		click(new IntrayToolsPage(driver).intrayActionsBtnOnAuditAction, "clicking on intray actions");
		sleepFor(1000);
		String getIntrayAuditDetailsToCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("AllocateIntrayItem","8");
		System.out.println("====================== "+getIntrayAuditDetailsToCol);

		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Verify audit action using landing page of docuemnt
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(new IntrayToolsPage(driver).intrayAuditBtn, "Clicking on Intray audit button");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		
		String getLandingPageAuditToDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("AllocateIntrayItem","8");
		System.out.println("=============================="+getLandingPageAuditToDetails);


		AssertionHelper.verifyTextContains(getIntrayAuditDetailsToCol, ObjectReader.reader.getUserName());
		AssertionHelper.verifyTextContains(getLandingPageAuditToDetails, ObjectReader.reader.getUserName());
	}
	
	@Test(priority=117,enabled = true)
	public void NextMailAudit() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String nextMail1 = "NM"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, nextMail1, null,"To Team",ObjectReader.reader.getLoggedInUsersTeam(),getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", nextMail1);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Take next item from team tray", "Mail");
		new WindowHelper(driver).waitForPopup("Next");
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Veriyf audit action details
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
		click(new IntrayToolsPage(driver).intrayActionsBtnOnAuditAction, "clicking on intray actions");
		sleepFor(1000);
		String getIntrayAuditDetailsToCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("AllocateIntrayItem","8");
		System.out.println("====================== "+getIntrayAuditDetailsToCol);

		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Verify audit action using landing page of docuemnt
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(new IntrayToolsPage(driver).intrayAuditBtn, "Clicking on Intray audit button");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		
		String getLandingPageAuditToDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("AllocateIntrayItem","8");
		System.out.println("=============================="+getLandingPageAuditToDetails);


		AssertionHelper.verifyTextContains(getIntrayAuditDetailsToCol, ObjectReader.reader.getUserName());
		AssertionHelper.verifyTextContains(getLandingPageAuditToDetails, ObjectReader.reader.getUserName());
	}
	
	@Test(priority=118,enabled = true)
	public void ChangeMailStatusAudit() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		allocateDocRef2 = "ALL"+generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, allocateDocRef2, null,"To Team",ObjectReader.reader.getLoggedInUsersTeam(),getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",allocateDocRef2);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		navigationMenu.clickOnIcon("Allocate intray item", "Mail");
		new WindowHelper(driver).waitForModalDialog("Allocate intray item");
		new DropdownHelper(driver).selectFromAutocompleDD(ObjectReader.reader.getUserName(), new IntrayToolsPage(driver).allocateUserDD, getEnvVariable);
		click(new IntrayToolsPage(driver).allocateOkPopUpBtn, "Clicking in allocate ok button");
		sleepFor(2000);
	}	
	
	@Test(priority=119,enabled = true)
	public void ViewAndEnterTimedPeriodValue() {
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",allocateDocRef2);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			sleepFor(3000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
			
			new WindowHelper(driver).waitForModalDialog("Timed Mail Status");
			sendKeys(new IntrayToolsPage(driver).timedPeriosInput, "1", "Entering timed period value ");
			new ActionHelper(driver).pressTab();
			sleepFor(1000);
			new ActionHelper(driver).pressTab();
			sleepFor(1000);
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			sleepFor(2000);
		}
	}
	
	@Test(priority=120,enabled = true)
	public void VerifyAuditActions() {
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",allocateDocRef2);
		capture.clickOnIntrayListBtn();
		docTools.clickOnFirstItemInList();
		
		//Veriyf audit action details
		navigationMenu.clickOnIcon("Audit Actions", "Document");
		new WindowHelper(driver).waitForModalDialog("Audit Actions");
		click(new IntrayToolsPage(driver).intrayActionsBtnOnAuditAction, "clicking on intray actions");
		sleepFor(1000);
		String getIntrayAuditDetailsFromCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("ChangeMailStatusIntray","7");
		System.out.println("====================== "+getIntrayAuditDetailsFromCol);

		String getIntrayAuditDetailsToCol = new IntrayToolsPage(driver).GetAuditIntrayDetailsColumnValue("ChangeMailStatusIntray","8");
		System.out.println("====================== "+getIntrayAuditDetailsToCol);

		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(3000);
		
		//Verify audit action using landing page of docuemnt
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(new IntrayToolsPage(driver).intrayAuditBtn, "Clicking on Intray audit button");
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		String getLandingPageAuditFromDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("ChangeMailStatusIntray","7");
		System.out.println("=============================="+getLandingPageAuditFromDetails);

		String getLandingPageAuditToDetails = new IntrayToolsPage(driver).GetLandingPageIntrayAuditDetails("ChangeMailStatusIntray","8");
		System.out.println("=============================="+getLandingPageAuditToDetails);

		AssertionHelper.verifyTextContains(getIntrayAuditDetailsFromCol, "I");
		AssertionHelper.verifyTextContains(getIntrayAuditDetailsToCol, "N");
		AssertionHelper.verifyTextContains(getLandingPageAuditFromDetails, "I");
		AssertionHelper.verifyTextContains(getLandingPageAuditFromDetails, "N");
	}
	
	@Test(priority=120,enabled = true)
	public void VerifyMultiselectCheckboxFunctionality() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnDocumentListBtn();
		
		navigationMenu.SelectMultiCheckbox();
		navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"2");
		navigationMenu.clickOnSpecificRow(getEnvVariable,"3");
		
		boolean status = new VerificationHelper(driver).isElementSelected(new NavigationMenu(driver).multiSelectCheckbox);
		AssertionHelper.verifyTrue(status, "Checking multi select checkbox");	
	}
	
	@Test(priority=121,enabled = true)
	public void VerifyMultiSelectchecboxDisable() throws InterruptedException {
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnDocumentListBtn();
		
		navigationMenu.UnSelectMultiCheckbox();
		sleepFor(2000);
		boolean status = new VerificationHelper(driver).isElementSelected(new NavigationMenu(driver).multiSelectCheckbox);
		
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);

		// Login with new user first time and change password
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnDocumentListBtn();
		
		boolean status1 = new VerificationHelper(driver).isElementSelected(new NavigationMenu(driver).multiSelectCheckbox);
		
		AssertionHelper.verifyFalse(status);
		AssertionHelper.verifyFalse(status1);
	}
	
	@Test(priority=122,enabled = true)
	public void VerifyQuickSearchInFlexibleIndex() {
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Quick Search", "File System");
		
		boolean status = new VerificationHelper(driver).isElementDisplayed(By.xpath("//table[@id='flexibleFieldAllocationTable']/tbody//td[text()='Doc_Ref2']"));
		sleepFor(2000);
		if(status) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Flexible Index maintenance", "File System");
			navigationMenu.waitForNavBarTitle("Index Admin");
			
			click(new NavigationMenu(driver).docRef2, "Clicking on doc ref2");
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			
			boolean status1 = new VerificationHelper(driver).isElementSelected(new NavigationMenu(driver).quickSearchCheckbox);
			AssertionHelper.verifyTrue(status1, "Checking quick search checkbox");
		}else {
			
			new DropdownHelper(driver).selectFromAutocompleDD("Doc_Ref2", new NavigationMenu(driver).flexibleFieldDD);
			click(new NavigationMenu(driver).addFlexibleBtn, "Clicking on add flexible button");
			sleepFor(1000);
			new NavigationMenu(driver).clickOnSaveIcon();
			new WindowHelper(driver).waitForPopup("Success");
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Flexible Index maintenance", "File System");
			navigationMenu.waitForNavBarTitle("Index Admin");
			
			new NavigationMenu(driver).waitForNavBarTitle("Index Admin");
			click(new NavigationMenu(driver).docRef2, "Clicking on doc ref2");
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			
			boolean status2 = new VerificationHelper(driver).isElementSelected(new NavigationMenu(driver).quickSearchCheckbox);
			AssertionHelper.verifyTrue(status2, "Checking quick search checkbox");
		}	
	}
	
	@Test(priority=123,enabled = true)
	public void VerifyLastSearchFieldsOnMoreSearch() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String teamName = ObjectReader.reader.getLoggedInUsersTeam();
		String teamId = ObjectReader.reader.getLoggedInUsersTeamId();

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
		
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		
		navigationMenu.clickOnIcon("Refine current search", "General");
		new NavigationMenu(driver).waitForNavBarTitle("Refine Search");
		String getDocType = new DocumentToolsPage(driver).getDocTypeValue.getAttribute("data-value");
		
		docTools.expandMoreSectionIfHidden("Intray");
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
		
		AssertionHelper.verifyTextContains(getDocType, "ScanDoc");
		
	}
	
	@Test(priority=124,enabled = true)
	public void UpdateConfigValueForAudit() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("AllocateIntrayItem", "0");
		values.put("ChangeMailStatusIntray", "0");
		values.put("DeleteIntray", "0");
		values.put("DistributeIntrayItem", "0");
	
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
			
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
	}
	
	@Test(priority=125,enabled = true)
	public void CreateNewUserWithAuditActions() {
		String userNew = "UserT"+generateRandomNumber();
		
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();

		new AdminUserSection(driver).enterUserId(userNew);
		new AdminUserSection(driver).enterUsername(userNew);

		sleepFor(1000);
		new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).clickOnUserNavTab("Audit Actions");
		
		click(new AdminUserSection(driver).expandIntrayAuditActions, "Expanding intray audit section");
		sleepFor(1000);
		click(new AdminUserSection(driver).intrayAuditGroupCheckbox, "Clicking on group checkbox");
		sleepFor(1000);
		

		boolean status1 = new AdminUserSection(driver).GetIntrayAuditCheckboxWhileAddingUser("AllocateIntrayItem");
		boolean status2 = new AdminUserSection(driver).GetIntrayAuditCheckboxWhileAddingUser("ChangeMailStatusIntray");
		boolean status3 = new AdminUserSection(driver).GetIntrayAuditCheckboxWhileAddingUser("DeleteIntray");
		boolean status4 = new AdminUserSection(driver).GetIntrayAuditCheckboxWhileAddingUser("DistributeIntrayItem");
		
		//Uncheck any one audit action
		new AdminUserSection(driver).UnselectAuditActionCheckbox("DeleteIntray");
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		
		//Verifying group checkbox
		AssertionHelper.verifyTrue(status1, "Checking audit action checkbox is selected");
		AssertionHelper.verifyTrue(status2, "Checking audit action checkbox is selected");
		AssertionHelper.verifyTrue(status3, "Checking audit action checkbox is selected");
		AssertionHelper.verifyTrue(status4, "Checking audit action checkbox is selected");
	}
	
	@Test(priority=126,enabled = true)
	public void UpdateConfigValueForAuditBackToOriginal() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("AllocateIntrayItem", "1");
		values.put("ChangeMailStatusIntray", "1");
		values.put("DeleteIntray", "1");
		values.put("DistributeIntrayItem", "1");
	
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
			
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
	}
	
	@Test(priority=127,enabled = true)
	public void VerifyAuditActionsWhileCreaingUserIsDisabled() {
		String userNew = "UserT"+generateRandomNumber();
		
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.clickOnAddIcon();

		new AdminUserSection(driver).enterUserId(userNew);
		new AdminUserSection(driver).enterUsername(userNew);

		sleepFor(1000);
		new AdminUserSection(driver).enterSecurityLevel("9");
		sleepFor(1000);
		new AdminUserSection(driver).clickOnUserNavTab("Audit Actions");
		
		click(new AdminUserSection(driver).expandIntrayAuditActions, "Expanding intray audit section");
		sleepFor(1000);
		
		boolean status1 = new AdminUserSection(driver).GetStatusIntrayAuditCheckboxWhileAddingUser("AllocateIntrayItem");
		boolean status2 = new AdminUserSection(driver).GetStatusIntrayAuditCheckboxWhileAddingUser("ChangeMailStatusIntray");
		boolean status3 = new AdminUserSection(driver).GetStatusIntrayAuditCheckboxWhileAddingUser("DeleteIntray");
		boolean status4 = new AdminUserSection(driver).GetStatusIntrayAuditCheckboxWhileAddingUser("DistributeIntrayItem");

		navigationMenu.clickOnCancelIcon();
		new WindowHelper(driver).clickOnModalFooterButton("No");
		navigationMenu.waitForAddIcon();
		
		AssertionHelper.verifyFalse(status1);
		AssertionHelper.verifyFalse(status2);
		AssertionHelper.verifyFalse(status3);
		AssertionHelper.verifyFalse(status4);
	}
	
	@Test(priority=128,enabled = true)
	public void CreateNewDocType() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String typeId = "Test";
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		adminDoc.enterTypeId(typeId);
		adminDoc.enterDescription("docuemnt type");
		adminDoc.selectMailOption("Mail",getEnvVariable);
		adminDoc.selectMediaType("No Specified Media Type",getEnvVariable);
		adminDoc.checkEditable();
		sleepFor(1000);
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(typeId);
			Thread.sleep(2000);
			String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
			AssertionHelper.verifyText(filteredText, typeId);
		}
		catch(Exception ex){
			String actMessage = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyTextContains(actMessage, "already exists");
			getApplicationUrl();
		} 
	}
	
	@Test(priority=129,enabled = true)
	public void AddDocumentTypeinAnotherFS() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new HomePage(driver).changeFileSystem(diffFSNameForRefresh);

		String typeId = "Test";
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		adminDoc.enterTypeId(typeId);
		adminDoc.enterDescription("docuemnt type");
		adminDoc.selectMailOption("Mail",getEnvVariable);
		adminDoc.selectMediaType("No Specified Media Type",getEnvVariable);
		adminDoc.checkEditable();
		sleepFor(1000);
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(typeId);
			Thread.sleep(2000);
			String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
			AssertionHelper.verifyText(filteredText, typeId);
		}
		catch(Exception ex){
			String actMessage = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyTextContains(actMessage, "already exists");
			getApplicationUrl();
		} 		
	}
	
	@Test(priority=130,enabled = true)
	public void CaptureDocAndDistributeToAnotherFS() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());

		distrNote5 = "Dis"+generateRandomNumber();
		getApplicationUrl();			
		capture.navigateAndCaptureDocWithParameters(null, null, "Test", distrNote5, null,null,null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote5);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		
		intrayTools.selectFromFileSystemDistribute(diffFSNameForRefresh, getEnvVariable);
		intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getOtherUserTeamId());
		sleepFor(1000);
		intrayTools.enterNotesPillsInEscalate("Notes added");
		new ActionHelper(driver).pressTab();
		intrayTools.clickOnModalApplyChangesButtonWithPopup();
		sleepFor(4000);
	}
	
	@Test(priority=131,enabled = true)
	public void DeleteDocuemnt() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", distrNote5);
		capture.clickOnIntrayListBtn();
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
	}*/

}
