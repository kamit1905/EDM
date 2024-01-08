package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.*;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.MoreSearch;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import utils.ExtentReports.ExtentTestManager;

public class ATestEndToEnd1 extends TestBase {
	
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
	public FileSystemSection adminFS ;
	
	private String teamDocRef = "";
	private String checkout1 = "";
	private String checkout2 = "";
	private String checkout3 = "";
	private String outstanding1 = "";
	private String outstanding2 = "";
	private String savedSearchInputName = "";
	private String parameMetroTile = "";
	private String teamSearch = "";
	private String savedSearchFS = "";
	private String savedSearchDocRef = "";
	private String homePageFSTile = "";

	
	
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
		adminFS = new FileSystemSection(driver);
	}
	
	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}
	
	@Test(priority = 1,enabled=false)
	public void ViewOtherTeamIntray() throws InterruptedException {
		test = ExtentTestManager.getTest();
		teamDocRef = "GT"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String teamName = ObjectReader.reader.getOtherUserTeamId();
		String teamId = ObjectReader.reader.getOtherUserTeamId();


		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, teamDocRef, null,"To Team",null,ObjectReader.reader.getOtherUserTeamId(),getEnvVariable);
//		capture.navigateAndCaptureDocWithParameters(null, null, null, teamDocRef, null,null,"Imasys Admin User",getEnvVariable);
		
		getApplicationUrl();
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
		navigationMenu.searchInFilter(teamDocRef);
		sleepFor(2000);
		String actTeamName =  navigationMenu.getColumnValueFromTable("Team");			
		AssertionHelper.verifyText(actTeamName, teamId); 			 		
	}
	
	@Test(priority = 2,enabled=false)
	public void AddMailStatusOnMoreSearchPage() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
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
			docTools.selectFromInputUnderMoreSearch("F - Info Only","input-mailStatuses","input-mailStatuses");		//Added by amit to select user
			click(new MoreSearch(driver).mailStatusSaveBtn, "Clicking on save btn on more search page");
			new WindowHelper(driver).waitForModalDialog("Save default mail statuses");
			new WindowHelper(driver).clickOnModalFooterButton("Save");
			sleepFor(3000);
		}else {
			docTools.selectFromTeamUnderIntray("F - Info Only");
		}
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword());
		sleepFor(2000);
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());		
		
		navigationMenu.clickOnIcon("Default Statuses", "Workflow");
		new WindowHelper(driver).waitForModalDialog("Save default mail statuses");
		sleepFor(2000);
		String getMailStatus = new HomePage(driver).getDefaultMailStatusValue.getText();
		AssertionHelper.verifyTextContains(getMailStatus, "F - Info Only");
		new WindowHelper(driver).clickOnModalFooterButton("Cancel");
	}
	
	@Test(priority = 3,enabled=false)
	public void VerifyOverDueFunctionality() throws ParseException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String dateFormat = formater.format(calendar.getTime());

		getApplicationUrl();
		docTools.clickOnMoreSearch();
		//new JavaScriptHelper(driver).scrollToElement(new MoreSearch(driver).overDueCheckbox);
		sleepFor(2000);
		click(new MoreSearch(driver).overDueCheckbox, "Clicking on overdue checkbox");
		capture.clickOnIntrayListBtn();
		sleepFor(2000);
		List<String> getTargetDateValue = new DocumentToolsPage(driver).GetTargetDateValue();
		System.out.println("getTargetDateValue ========================"+getTargetDateValue);
		boolean dateStatus = getTargetDateValue.contains(dateFormat);
		AssertionHelper.verifyFalse(dateStatus,"Date is not present in the list");
	}
	
	@Test(priority = 4,enabled=false)
	public void UpdateAlmostDueInFileSystem() {
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("File System maintenance", "File System");
		 navigationMenu.waitForAddIcon();
		 String fsName = home.getCurrentFileSystemName().split("-")[0].trim();
		 
		 navigationMenu.searchInFilter(fsName);
		 navigationMenu.clickOnFilteredRow(adminFS.fileSystemTableId);
		 navigationMenu.clickOnEditIcon();
		 navigationMenu.waitForIcon("Save changes");
		 new JavaScriptHelper(driver).scrollToBottom();
		 sleepFor(2000);
		 adminFS.EnterAlmostDueDays("5");
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
	
	@Test(priority = 5,enabled=false,dataProvider="captureFormData")
	public void CaptureDocumentForAlmostDue(Map<String, String> map) throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String filepath=map.get("FilePath"),
			  docRef= "nextMailTeamOld",filename = map.get("FileName"),
			  docType = map.get("DocumentType"),
			  userType="To Team",userName=ObjectReader.reader.getLoggedInUsersTeam(),expectedOldDocRef=docRef+generateRandomNumber();

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(filepath, filename, docType, expectedOldDocRef, null, userType, userName,userName,getEnvVariable);			//Added date parameter for nextMail
		
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		//new JavaScriptHelper(driver).scrollToElement(new MoreSearch(driver).overDueCheckbox);
		sleepFor(2000);
		click(new MoreSearch(driver).almostDueCheckbox, "Clicking on almost due checkbox");
		capture.clickOnIntrayListBtn();
		navigationMenu.searchInFilter(expectedOldDocRef);
		docTools.clickOnFirstItemInList();
	}
	
	@Test(priority = 6,enabled=false)
	public void VerifyDateFieldAfterSelectingDueCheckbox() {
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		sleepFor(2000);
		click(new MoreSearch(driver).overDueCheckbox, "Clicking on overdue checkbox");
		
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(new MoreSearch(driver).fromDateField));
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(new MoreSearch(driver).toDateField));
		
		AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementEnabled(new MoreSearch(driver).mailStatusInput));
	}
	
	@Test(priority = 7,enabled=false)
	public void VerifyCheckedOutDocuments() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		checkout1 = "CHK1"+generateRandomNumber();
		checkout2 = "CHK2"+generateRandomNumber();
		checkout3 = "CHK3"+generateRandomNumber();
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "Demo.docx", null, checkout1, null,getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "Demo.docx", null, checkout2, null,getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, "Demo.docx", null, checkout3, null,getEnvVariable);

		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", checkout1);
		 //if no doc found then capture doc with docref
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnEditIconUnderVersionControl();
		 docTools.clickOnEditDocumentPopup("Later");
		 sleepFor(2000);
		 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");

		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", checkout2);
		 //if no doc found then capture doc with docref
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnEditIconUnderVersionControl();
		 docTools.clickOnEditDocumentPopup("Later");
		 sleepFor(2000);
		 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");
		 
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", checkout3);
		 //if no doc found then capture doc with docref
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 docTools.clickOnEditIconUnderVersionControl();
		 docTools.clickOnEditDocumentPopup("Later");
		 sleepFor(2000);
		 AssertionHelper.verifyTrue(docTools.isGreenTickPresentCheckedOutInd("Checked Out Ind"), "Checkking if green tick present for Checked out column");
	}
	
	@Test(priority = 8,enabled=false)
	public void VerifyCheckedOutCount() {
		getApplicationUrl();
		sleepFor(3000);
		String getCheckoutCnt = new HomePage(driver).getCheckedOutCount.getText();
		
		new HomePage(driver).clickOnMetroTile("Number of checked-out items.");
		navigationMenu.waitForFilterTextBox();
		sleepFor(3000);
		String getCheckoutItems = new HomePage(driver).checkedOutItems.getText();
		String [] splitCheckout = getCheckoutItems.split(" ");
		System.out.println("================================== "+splitCheckout[2]);

		AssertionHelper.verifyText(getCheckoutCnt,splitCheckout[2].trim());
	}
	
	@Test(priority = 9,enabled=false)
	public void CheckinDocumentAndVerifyCheckoutCnt() {
		 getApplicationUrl();
		 new HomePage(driver).clickOnMetroTile("Number of checked-out items.");
		 navigationMenu.waitForFilterTextBox();
		 navigationMenu.searchInFilter(checkout1);
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("Discard changes", "Actions");
		 
		 new WindowHelper(driver).waitForPopup("Undo Edit");
		 new WindowHelper(driver).clickOnModalFooterButton("Ok");
		 sleepFor(3000);
		 new WindowHelper(driver).waitForPopup("Undo Edit");
		 new WindowHelper(driver).clickOnModalFooterButton("Ok");
		 new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 10);
			
		 getApplicationUrl();
		 sleepFor(3000);
		 String getCheckoutCnt = new HomePage(driver).getCheckedOutCount.getText();
			
		 new HomePage(driver).clickOnMetroTile("Number of checked-out items.");
		 navigationMenu.waitForFilterTextBox();
		 new JavaScriptHelper(driver).clearText(navigationMenu.filterTxt);
		 sleepFor(2000);
		 click(navigationMenu.filterTxt, "Clikcing on filter input");
		 sleepFor(2000);
		 String getCheckoutItems = new HomePage(driver).checkedOutItems.getText();
		 String [] splitCheckout = getCheckoutItems.split(" ");
		 System.out.println("================================== "+splitCheckout[2]);
			
		 AssertionHelper.verifyTextContains(getCheckoutCnt, splitCheckout[2]);		 
	}
	
	@Test(priority = 10,enabled=false)
	public void VerifyOutstandingForTodayItemsCount() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		outstanding1 = "OT1"+generateRandomNumber();
		outstanding2 = "OT2"+generateRandomNumber();

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, outstanding1, null,getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, outstanding2, null,getEnvVariable);
		
		 getApplicationUrl();
		 sleepFor(3000);
		 new WaitHelper(driver).waitForElementVisible(new HomePage(driver).getOutstandingItemsForToday, 2, 20);
		 String getCheckoutCnt = new HomePage(driver).getOutstandingItemsForToday.getText().trim().toString();
		 System.out.println("getCheckoutCnt ==========================="+getCheckoutCnt);
		 new HomePage(driver).clickOnMetroTile("Outstanding items for today.");
		 navigationMenu.waitForFilterTextBox();
		 navigationMenu.searchInFilter(outstanding1);
		 capture.clickOnFirstItemOfIntray();
			
		 if(getEnvVariable.equals("Enterprise_Sp1s") || getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531")) {
			capture.changeEmailStatus("Complete");				//Changed drop down locator for email status in R531
			}else {
				capture.changeMailStatusUsingSelectClass("Complete");
		 }
		 
		 sleepFor(3000);
		 getApplicationUrl();
		 Integer calculatedOutstandingCnt = Integer.parseInt(getCheckoutCnt)-1;
		 System.out.println("calculatedOutstandingCnt=========================="+calculatedOutstandingCnt);
		 //Integer calculatedOutstandingCnt = convertToInt - 1;
		 
		 sleepFor(3000);
		 new WaitHelper(driver).waitForElementVisible(new HomePage(driver).getOutstandingItemsForToday, 2, 20);
		 String getLatestCheckoutCnt = new HomePage(driver).getOutstandingItemsForToday.getText();
		 AssertionHelper.verifyText(getLatestCheckoutCnt, String.valueOf(calculatedOutstandingCnt));
	}
	
	@Test(priority = 11,enabled=false)
	public void VerifySearchInMySearchAndSharedSearch() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		savedSearchInputName = "DocuemntTy"+generateRandomNumber();
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
		
		new MoreSearch(driver).ClickOnSaveBtnOnMoreSearchPage();
		new WindowHelper(driver).waitForModalDialog("Save Search");
		new MoreSearch(driver).InputSearchName(savedSearchInputName);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Save Search");		//
		
		AssertionHelper.verifyText(getMsg, "Search saved successfully.");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Search"); // Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		boolean verifyElement = new VerificationHelper(driver).isElementDisplayed(By.xpath("//span[text()='My Searches']/ancestor::li[1]/following-sibling::li[1]//span[text()='"+savedSearchInputName+"']"));
		AssertionHelper.verifyTrue(verifyElement, "Verifyin search criteria under My Searches");
	}
	
	@Test(priority = 12,enabled=false)
	public void SharedSearchUnderSearch() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		List<String> parameterized = new ArrayList<>();
		
		parameMetroTile = "PRTile"+generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("Default - General Default Document Type","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex("Default - General Default Document Type");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
		new MoreSearch(driver).CreateHomePageTile(parameMetroTile, "File System", false, parameterized,"");

		getApplicationUrl();
		navigationMenu.clickOnTab("Search"); // Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		boolean verifyElement = new VerificationHelper(driver).isElementDisplayed(By.xpath("//span[text()='Shared Searches']/ancestor::li[1]/following-sibling::li[1]//span[text()='"+parameMetroTile+" ("+ObjectReader.reader.getFileSystemName()+")']"));
		AssertionHelper.verifyTrue(verifyElement, "Verifyin search criteria under My Searches");
	}
	
	@Test(priority = 13,enabled=true)
	public void PerformMoreSearchUsingIntrayGroup() {
		String getEnvVariable = System.getProperty("propertyName");
		String teamName = ObjectReader.reader.getLoggedInUsersTeam();
		String teamId = ObjectReader.reader.getLoggedInUsersTeamId();

		getApplicationUrl();
		docTools.clickOnMoreSearch();
		
		//test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
		  //test.log(Status.INFO,"====In if condition");
		  docTools.expandMoreSectionIfHidden("OtherCriteria");
		}
		else {
		  //test.log(Status.INFO,"====In else condition");
		docTools.expandMoreSectionIfHidden("Intray");
		}
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(teamName,"input-Teams","input-teams");		
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(ObjectReader.reader.getUserName(),"input-Users","input-users");		//Added by amit to select user
		}else {
			docTools.selectFromTeamUnderIntray(teamName);
			sleepFor(2000);
			docTools.selectFromUserUnderIntray(ObjectReader.reader.getUserName(),getEnvVariable);		
		}
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		String actTeamName =  navigationMenu.getColumnValueFromTable("Team");	
		
		String actUserName =  navigationMenu.getColumnValueFromTable("User ID");
		
		AssertionHelper.verifyText(actTeamName, teamId);
		AssertionHelper.verifyText(actUserName, ObjectReader.reader.getUserName());
	}
	
	@Test(priority = 14,enabled=true)
	public void CreateTeamSearch() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String teamName = ObjectReader.reader.getLoggedInUsersTeam();
		
		teamSearch = "Team"+generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("ScanDoc - Generic Scanned Document","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch(teamName,"input-Teams","input-teams");
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex("ScanDoc - Generic Scanned Document");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromTeamUnderIntray(teamName);
		}
		
		new MoreSearch(driver).ClickOnSaveBtnOnMoreSearchPage();
		new WindowHelper(driver).waitForModalDialog("Save Search");
		new MoreSearch(driver).InputSearchName(teamSearch);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Save Search");		//
		
		AssertionHelper.verifyText(getMsg, "Search saved successfully.");
	}
	
	@Test(priority = 15,enabled=true)
	public void DeleteTeamSearch() {
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(teamSearch);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();
		
		getApplicationUrl();
		//new MoreSearch(driver).searchWithSavedSearch(savedSearchInputName);
		navigationMenu.clickOnTab("Search"); // Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName,
					"Enetering in search =" + teamSearch);
		String getMsg = driver.findElement(By.xpath("//li[@class='no-results']")).getText();
		AssertionHelper.verifyTextContains(getMsg, "No results matched");		
	}
	
	@Test(enabled = true, priority = 16)
	public void HideSystemTiles() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		try {
			getApplicationUrl();
			new HomePage(driver).SelectEditTileButton();
			new HomePage(driver).HideMetroTile("Number of checked-out items.");
			sleepFor(5000);
			
			getApplicationUrl();
			boolean metroTile = new HomePage(driver).VerifyThatMetroTileNotPresent("Number of checked-out items.");
			AssertionHelper.verifyFalse(metroTile);	
			sleepFor(5000);
		} finally {
			getApplicationUrl();
			new HomePage(driver).SelectEditTileButton();
			new HomePage(driver).ShowMetroTile("Number of checked-out items.");
			sleepFor(5000);
		}
	}
	
	@Test(enabled = true, priority = 17)
	public void VerifyQuickSearchCheckbox() {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Quick Search", "File System");
		navigationMenu.waitForNavBarTitle("Quick Search Configuration");
		new DropdownHelper(driver).selectFromAutocompleDD("Doc_Ref2", new NavigationMenu(driver).flexibleFieldDD);
		click(new NavigationMenu(driver).addFlexibleBtn, "Clicking on add flexible button");
		sleepFor(1000);
		new NavigationMenu(driver).clickOnSaveIcon();
		new WindowHelper(driver).waitForPopup("Success");
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Flexible Index maintenance", "File System");
		new NavigationMenu(driver).waitForIcon("Move selected item up", "Actions");
		navigationMenu.waitForNavBarTitle("Index Admin");
		
		//new NavigationMenu(driver).waitForNavBarTitle("Index Admin");
		click(new NavigationMenu(driver).docRef2, "Clicking on doc ref2");
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		click(navigationMenu.quickSearchCheckbox, "Clikcing on quick search checkbox");
		navigationMenu.clickOnIcon("Save the current changes as draft", "Structure");
		new WindowHelper(driver).waitForPopup("Save Draft");
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		
		navigationMenu.clickOnIcon("Publish the schema", "Publish");
		new WindowHelper(driver).waitForModalDialog("Publish the schema");
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		
		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 30);
	}
	
	@Test(enabled = false, priority = 18)
	public void CreateFSLevelSavedSearch() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		List<String> paramsList = new ArrayList<>();
		String roleName = ObjectReader.reader.getRoleName();
		
		savedSearchFS = "DCTY"+generateRandomNumber();
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

		
		new MoreSearch(driver).CreateHomePageTile(savedSearchFS, "File System", false, paramsList, roleName);
		
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchFS);
		sleepFor(2000);
		new DocumentToolsPage(driver).clickOnDocumentListButton();
		String actDocType =  new DocumentToolsPage(driver).getAllDocumentTypeValuesToSet();
		AssertionHelper.verifyText(actDocType, "ScanDoc"); 
	}
	
	@Test(enabled = false, priority = 19)
	public void EditFSSavedSearch() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String docType = "ScanDoc - Generic Scanned Document", accRef = "F1-001R1", miscRef = "",propRef = "";
		savedSearchDocRef = "STF"+generateRandomNumber();
		Boolean createRef = true;
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithCreateRefParameters(docType, savedSearchDocRef, accRef, miscRef, propRef, createRef,getEnvVariable);
		
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchFS);
		new MoreSearch(driver).EditHomePageTile(accRef, getEnvVariable, "");
		sleepFor(2000);
		docTools.clickOnDocumentListButton();
		navigationMenu.waitForFilterTextBox();
		
		String actTeamName =  navigationMenu.getColumnValueFromTable("Account Ref");
		sleepFor(2000);
		List<String> accRefValue = new DocumentToolsPage(driver).GetAllAccountRef();
		System.out.println("accRefValue====================="+accRefValue);
		boolean accRefStatus = accRefValue.contains(accRef);
		AssertionHelper.verifyTrue(accRefStatus,"Account Ref is present in the list");
	}
	
	@Test(enabled = false,priority = 20)
	public void DeleteFSSavedSearch() {
		getApplicationUrl();
		new MoreSearch(driver).searchWithSavedSearch(savedSearchFS);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		new MoreSearch(driver).ClickOnDeleteBtnOnMoreSearchPage();
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Search"); // Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchFS,
					"Enetering in search =" + savedSearchFS);
		String getMsg = driver.findElement(By.xpath("//li[@class='no-results']")).getText();
		AssertionHelper.verifyTextContains(getMsg, "No results matched");		
	}
	
	@Test(enabled = false,priority = 21)
	public void CreateHomePageTile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		List<String> paramsList = new ArrayList<>();

		homePageFSTile = "NewTile"+generateRandomNumber();
		getApplicationUrl();
		new DocumentToolsPage(driver).clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("Default - General Default Document Type","input-docTypes","input-docTypes");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}else {
			new DocumentToolsPage(driver).selectFromInputDocTypesUnderIndex("Default - General Default Document Type");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
		}
		
		new MoreSearch(driver).CreateHomePageTile(homePageFSTile, "File System", true, paramsList,"");
		
		getApplicationUrl();
		boolean tileFlag = new MoreSearch(driver).CheckTileIsPresentOnHomePage(homePageFSTile);
		AssertionHelper.verifyTrue(tileFlag, "Verifing home page Tile");
	}
	
	@Test(enabled = false,priority = 22,dataProvider = "captureFormData")
	public void VerifyThatFSTileIsNotPresentInOtherFS(Map<String,String> map) {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String getCurrentFSName = ObjectReader.reader.getFileSystemName();

		String [] getFileSystem = map.get("memoFileSystem").split("-");

		try {
			getApplicationUrl();
			new HomePage(driver).changeFileSystem(getFileSystem[0].trim());
			getApplicationUrl();
			boolean tileFlag = new HomePage(driver).VerifyThatMetroTileNotPresent(homePageFSTile);
			AssertionHelper.verifyFalse(tileFlag, "Verifing home page File System Tile is not present");
		} finally {
			new HomePage(driver).changeFileSystem(getCurrentFSName);
		}
	}
	
	@Test(enabled = false,priority = 23)
	public void VerifyThatUnableToEditTile() {
		try {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(ObjectReader.reader.getUserName()); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);
			new AdminUserSection(driver).clickOnUserNavTab("Security");
			sleepFor(2000);

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

			List<String> sysadminList = new ArrayList<String>();
			sysadminList.add("Can Edit File System Tiles");
			
			userPermission.put("System Administration", sysadminList);

			
			new AdminUserSection(driver).RemoveSecurityRightsFromUser(userPermission);
			navigationMenu.clickOnSaveIconForUser();
			navigationMenu.waitForAddIcon();
			
			new HomePage(driver).refreshCurrentFileSystem();
			getApplicationUrl();
			new HomePage(driver).SelectEditTileButton();
			boolean status = new VerificationHelper(driver).isElementDisplayed(By.xpath("//div[@class='metroExtra' and text()='"+homePageFSTile+"']/ancestor::div[1]//a[@title='Edit Tile']"));
			System.out.println("status =================="+status);
			AssertionHelper.verifyFalse(status);
		} finally {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(ObjectReader.reader.getUserName()); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);
			new AdminUserSection(driver).clickOnUserNavTab("Security");
			sleepFor(2000);

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

			List<String> sysadminList = new ArrayList<String>();
			sysadminList.add("Can Edit File System Tiles");
			
			userPermission.put("System Administration", sysadminList);
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			navigationMenu.clickOnSaveIconForUser();
			navigationMenu.waitForAddIcon();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
	
	@Test(enabled = false,priority = 24)			//check with team edit case
	public void EditHomePageTile() {
		String teamName = ObjectReader.reader.getLoggedInUsersTeam();
		String teamId = ObjectReader.reader.getLoggedInUsersTeamId();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		new HomePage(driver).SelectEditTileButton();
		new HomePage(driver).EditMetroTile(homePageFSTile);
		sleepFor(3000);
		new MoreSearch(driver).EditHomePageTile("", getEnvVariable, teamName);
		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		
		String actTeamName =  navigationMenu.getColumnValueFromTable("Team");
		sleepFor(2000);
		List<String> accTeamValue = new DocumentToolsPage(driver).GetAllAccountRef();
		System.out.println("accTeamValue====================="+accTeamValue);
		boolean teamStatus = accTeamValue.contains(teamId);
		AssertionHelper.verifyTrue(teamStatus,"Team name is mismatch in list");
	}
	
	@Test(enabled = false,priority = 25)
	public void DeleteHomeFSPageTile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		new HomePage(driver).SelectEditTileButton();
		String getPopUpMsg = new HomePage(driver).DeleteMetroTile(homePageFSTile);
		sleepFor(3000);
		AssertionHelper.verifyTextContains(getPopUpMsg, "Do you want to delete associated saved search as well?");
		
		getApplicationUrl();
		boolean metroTile = new HomePage(driver).VerifyThatMetroTileNotPresent(homePageFSTile);
		AssertionHelper.verifyFalse(metroTile);	
	}
	
	@Test(enabled = true , priority = 26)
	public void AddDefaultMailStatus() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		new NavigationMenu(driver).clickOnIcon("Default Statuses", "Workflow");
		new WindowHelper(driver).waitForModalDialog("Save default mail statuses");
		docTools.selectFromInputForDefaultMailStatus("N - New","input-defaultMailStatuses-selectized","MailStatusTextBox");		//Added by amit to select user
		docTools.selectFromInputForDefaultMailStatus("I - In Progress","input-defaultMailStatuses-selectized","MailStatusTextBox");		//Added by amit to select user
		new WindowHelper(driver).clickOnModalFooterButton("Save");
		
		getApplicationUrl();
		docTools.clickOnMoreSearch();
		if(getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch("Default Mail Statuses","input-mailStatuses","input-mailStatuses");
			sleepFor(2000);
			new MoreSearch(driver).RemoveMailStatus("I");
			sleepFor(2000);
			docTools.selectFromInputUnderMoreSearch("C - Complete","input-mailStatuses","input-mailStatuses");		//Added by amit to select user
		}else {
			docTools.selectFromInputUnderMoreSearch("Default Mail Statuses","input-mailStatuses","input-mailStatuses");
			sleepFor(2000);
			new MoreSearch(driver).RemoveMailStatus("I");
			sleepFor(2000);
			docTools.selectFromMailStatusIntrayMoreSearch("C - Complete",getEnvVariable);
		}

		capture.clickOnIntrayListBtn();
		navigationMenu.waitForFilterTextBox();
		
		String actTeamName =  navigationMenu.getColumnValueFromTable("Mail Status");
		sleepFor(2000);
		List<String> accMailStatusValue = new DocumentToolsPage(driver).GetAllMailStatus();
		System.out.println("accMailStatusValue====================="+accMailStatusValue);
		
		boolean newMailStatus = accMailStatusValue.contains("New");
		AssertionHelper.verifyTrue(newMailStatus,"mail status is  mismatch in list");

		boolean completeMailStatus = accMailStatusValue.contains("Complete");
		AssertionHelper.verifyTrue(completeMailStatus,"mail status is  mismatch in list");
		
		boolean inprogressMailStatus = accMailStatusValue.contains("In Progress");
		AssertionHelper.verifyFalse(inprogressMailStatus,"mail status is  mismatch in list");
	}
	
	@Test(enabled = true , priority = 27)
	public void RemoveDefaultMailStatus() {
		getApplicationUrl();
		new NavigationMenu(driver).clickOnIcon("Default Statuses", "Workflow");
		new WindowHelper(driver).waitForModalDialog("Save default mail statuses");
		new MoreSearch(driver).RemoveMailStatus("I");
		sleepFor(2000);
		new MoreSearch(driver).RemoveMailStatus("N");
		sleepFor(2000);
		
		new WindowHelper(driver).clickOnModalFooterButton("Save");
		getApplicationUrl();
	}
	

}
