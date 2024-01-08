package tests;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
import main.EDRM.hybridFramework.pageObject.MoreSearch;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;
import utils.ExtentReports.ExtentTestManager;

public class ATestMoreSearch extends TestBase{
	
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
	public AdminWorkflowSection campaign;
	
	private String folder1Prefix;
	private String folder2Prefix;
	private String folder3Prefix;

	
	private String textAreaDesctiption="";

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
		campaign = new AdminWorkflowSection(driver);
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

	
    /** More search   **/
	//This is added because to verify in progress mail from more search
	@Test(priority=50,enabled=false,dataProvider = "captureFormData",  description = "Test captured doc change status flow") 
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


	@Test(priority=51, enabled = false)
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
	@Test(priority=52, enabled = false)
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
	@Test(priority=87, enabled = false)				//Changed priority because doc is not captured with team
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

	@Test(priority=54, enabled = false)
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
	
	@Test(priority=55, enabled = false)
	public void VerifyColumnConfigurationValue() {
		//Verifying the document after Rereference					//Case 20
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Account Ref","F1-001R1" );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String getDocType = navigationMenu.getColumnValueFromTable("Document Type");			
		//AssertionHelper.verifyTextContains(getDocType, "Default");

	}
	
	@Test(priority=56, enabled = false)
	public void VerifyColumnConfigurationValue1() {
		//Verifying the document after Rereference					//Case 20
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Account Ref","F1-001R1" );
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String getDocType = navigationMenu.getColumnValueFromTable("Account Ref");			
		//AssertionHelper.verifyTextContains(getDocType, "Default");
	}
	
	//More Searching
	@DataProvider(name="moreSearchDateData")
    public Object[][] bankHolidaysData() throws Exception {
        Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","MoreSearch");
        return formData;
    }
	
	@Test(enabled = true,priority = 6)
	public void SortDocumentsUsingReceivedDates() throws ParseException {
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		click(new DocumentToolsPage(driver).dateReceivedBtn, "Clicking on date received asecending");
		sleepFor(2000);
		List<Date> dates = campaign.getDateReceivedColumnValuesForAllRows();
		System.out.println("Dates================ "+dates);
		AssertionHelper.verifyTrue(campaign.checkIfDatesAreInAscendingOrder(dates), "Checking if dates are in Asecending order");
	}
	
	@Test(enabled = true,priority = 7,dataProvider = "moreSearchDateData")
	public void SearchDocumentUsingDateRange(Map<String,String> map) throws ParseException {
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
//		List<String> getReceivedDates = new DocumentToolsPage(driver).GetReceivedDateFromDocumentList();
//		System.out.println("getReceivedDates ========================"+getReceivedDates);
//		boolean dateStatus = getReceivedDates.contains("17/08/2023");
//		AssertionHelper.verifyFalse(dateStatus,"Date is not present in the list");
//		
//		boolean dateStatus1 = getReceivedDates.contains("12/07/2023");
//		AssertionHelper.verifyFalse(dateStatus1,"Date is not present in the list");

		List<String> getReceivedDates = new DocumentToolsPage(driver).GetReceiveDateValue();
		System.out.println("getReceivedDates ========================"+getReceivedDates);
		boolean dateStatus = getReceivedDates.contains("17/08/2023");
		AssertionHelper.verifyFalse(dateStatus,"Date is not present in the list");
		
		boolean dateStatus1 = getReceivedDates.contains("12/07/2023");
		AssertionHelper.verifyFalse(dateStatus1,"Date is not present in the list");
	}
	
	@Test(enabled = true,priority = 8)
	public void FilterDocumentList() {
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnDocumentListBtn();
		sleepFor(2000);
		navigationMenu.searchInFilter("Default");
		
		List<String> getAllDocumentType = new DocumentToolsPage(driver).GetAllDocumentType();
		System.out.println("getReceivedDates ========================"+getAllDocumentType);
		boolean docStatus = getAllDocumentType.contains("Default");
		AssertionHelper.verifyTrue(docStatus, "Verifying document type value");

	}
	
	@Test(priority=342,enabled = false)
	public void TestExportedMetadataFunctionality() throws InterruptedException {
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref","%%");
		capture.clickOnIntrayListBtn();
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
	
	/**      Notes module **/
	
	@Test(enabled=false,priority = 108)
	public void TestAddParagraphNotesFromAdministration() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		textAreaDesctiption = "This is new line characted for the above line"+generateRandomNumber();
		getApplicationUrl();
		new AdminDocumentSection(driver).clickOnAdminNotes();
		navigationMenu.clickOnAddIcon();
		
		new AdminDocumentSection(driver).SelectNotesType("Paragraph",getEnvVariable);			//Changed in R551
		new AdminDocumentSection(driver).EnterTextAreaDescriptionForNotes(textAreaDesctiption);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled=false,priority = 109)
	public void CaptureDocumentFromIntrayList() throws InterruptedException {
		String docRef = "Notes"+generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Notes", "Document");
		docTools.ClickOnAddNotesToDocument();
		docTools.EnterTitleInNotes("Sample");
		docTools.SelctParagraphforDocument(textAreaDesctiption,getEnvVariable);
		navigationMenu.clickOnSaveIcon();
	}


}
