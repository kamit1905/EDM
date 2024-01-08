package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitSystemSection;
import utils.ExtentReports.ExtentTestManager;

//Priority 10,17 commented enable later

public class TestRegressionDistribution extends TestBase {
	public IntrayToolsPage intrayTools ;
	private Logger log = LoggerHelper.getLogger(TestRegressionDistribution.class);
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
	
	private String distrNote1;
	private String distrNote2;
	private String distrNote3;
	private String distrNote4;
	private String distrNote5;
	private String distrNote6;
	private String distrNote7;
	private String distrNote8;
	private String distrNote9;
	private String distrNote10;
	private String distrNote11;
	private String distrNote12;
	private String distrNote13;
	private String distrNote14;
	private String distrNote15;
	private String distrNote16;
	private String distrNote17;
	private String distrNote18;
	
	private String teamId1;
	private String teamName1;
	private String diffFSNameForRefresh;
	
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

    @DataProvider(name="docreReferenceData")
    public Object[][] docreReferenceData() throws Exception
    {
        Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");   
        return formData;
    }

    @DataProvider(name="newRoleTeamData")
	public Object[][] newRoleTeamData() throws Exception
	{
		Object[][] formData = xls.readExcel( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","newRoleAndTeam");
				
		return formData;
	}
    
	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}

	@Test(enabled = true,priority = 0,dataProvider = "docreReferenceData")
	public void GetDiffFSName(String doctype, String folder2Ref, String docref, String fileSystem) {
		diffFSNameForRefresh = fileSystem;
	}


    @Test(priority = 1,enabled = true)				//Case 9
	public void RemoveUserFromDistributionPopup() throws InterruptedException {
		distrNote1 = "DistrNote"+generateRandomNumber();
		test = ExtentTestManager.getTest();
		try {
			getApplicationUrl();			
			
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);

			capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote1, null,null,null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote1);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			sleepFor(2000);
			intrayTools.RemoveUserFromDistribute(ObjectReader.reader.getUserName());
			new ActionHelper(driver).pressEscape();
			new ActionHelper(driver).pressEscape();

		} finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
	
	@Test(priority = 2, enabled = true, dependsOnMethods = "RemoveUserFromDistributionPopup") // Case 10,11,17
	public void AddNotesInDistributionPopupAndVerifyErrorMsg() {
		String notes = "Added comments in notes popup Added comments in notes popup Added comments in notes popup Added comments in notes popup Added comments in notes popup Added comments in notes popup Added comments in notes popup Added comments in notes popup Added comm";
		try {
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote1);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();

			click(intrayTools.btnDistributeApply, "Clicking on apply changes button");
			String getErrorMsg = new WindowHelper(driver).getPopupMessageClickOk("Distribute intray item");
			AssertionHelper.verifyTextContains(getErrorMsg, "Please select at least one user or team");
			sleepFor(1000);
			new ActionHelper(driver).pressEscape();
			sleepFor(2000);
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			intrayTools.enterNotesPillsInEscalate(notes);
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(2000);
			new HomePage(driver).refreshCurrentFileSystem();

			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote1);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			String mailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(mailStatus, "New");

		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}

	}
	
	@Test(priority = 3,enabled = true)
	public void VerifyErrorMessageOnClickOfCancelInDistribute() throws InterruptedException {
		distrNote2 = "DistrNote2_"+generateRandomNumber();
		test = ExtentTestManager.getTest();
		getApplicationUrl();			
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		//capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote2, null,null,null,null);
		try {
			capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote2, null,null,null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote2);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			sleepFor(2000);
			click(new IntrayToolsPage(driver).btnEscalateCancel, "Clicking on cancel button");
			String getErrorMsg = new WindowHelper(driver).getPopupMessageClickOk("Distribute intray item");
			AssertionHelper.verifyTextContains(getErrorMsg, "You have data being edited, any changes will be lost.");
			sleepFor(2000);	
			
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			sleepFor(1000);
			click(new IntrayToolsPage(driver).btnEscalateCancel, "Clicking on cancel button");
			new WindowHelper(driver).waitForPopup("Distribute intray item");
			String getErrorMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickCancelOnPopup("Distribute intray item");
			new ActionHelper(driver).pressEscape();
			AssertionHelper.verifyTextContains(getErrorMsg1, "You have data being edited, any changes will be lost.");	

		} finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
	
	@Test(priority = 4,enabled = true)			//Case 19
	public void VerifyNotesTitleOnLandingPage() {
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote1);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		new DocumentToolsPage(driver).clickOnLandingPageIcon();
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		navigationMenu.clickOnNavTab("Notes");
		sleepFor(2000);
		String getNotesTitle = driver.findElement(By.xpath("//table[@id='notesubgrid']/tbody/tr[1]/td[3]")).getText();
		AssertionHelper.verifyTextContains(getNotesTitle, "Escalate");
	}
	
	@Test(priority = 5,enabled = true)			//Case 22
	public void DistributeUsingInfoOnly() throws InterruptedException {
		distrNote3 = "DistrNote3_"+generateRandomNumber();
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		String otherUserTeam = ObjectReader.reader.getOtherUserTeamName();
		String otherUserTeamId = ObjectReader.reader.getOtherUserTeamId();
		
		try {
			getApplicationUrl();			
			
			capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote3, null,null,null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote3);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInInfoOnly(otherUserTeamId);
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(2000);
			new HomePage(driver).refreshCurrentFileSystem();
			
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote3);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if(getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromTeamUnderIntray(otherUserTeam);
			}else {
				//new DocumentToolsPage(driver).selectFromInputTeamUnderIntray(otherUserTeam);
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(otherUserTeam,"input-Teams","input-teams");
			}
			
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote3);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus =  navigationMenu.getColumnValueFromTable("Mail Status");			
			AssertionHelper.verifyText(actMailStatus, "Info Only"); 			 				
		} finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
	
	@Test(priority = 6,enabled = true)						//Case 24
	public void VerifyTitleOfInfoOnlyDocument() {
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote3);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		new DocumentToolsPage(driver).clickOnLandingPageIcon();
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		navigationMenu.clickOnNavTab("Notes");
		sleepFor(2000);
		String getNotesTitle = driver.findElement(By.xpath("//table[@id='notesubgrid']/tbody/tr[1]/td[3]")).getText();
		AssertionHelper.verifyTextContains(getNotesTitle, "Copy Item");
	}
	
	@Test(priority = 7,enabled = true)
	public void DistributeToMultipleTeamsUsingInfoOnlyAndVerifyMailStatus() throws InterruptedException {
		String otherTeamId=ObjectReader.reader.getOtherUserTeamName();
		distrNote4 = "DistrNote4"+generateRandomNumber();
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();			
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote4, null,null,null,getEnvVariable);

		try {			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote4);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getLoggedInUsersTeamId());
			sleepFor(1000);
			intrayTools.enterAndSelectPillsInInfoOnly(otherTeamId);
			sleepFor(2000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(2000);
			new HomePage(driver).refreshCurrentFileSystem();
			
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote4);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if(getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromTeamUnderIntray(ObjectReader.reader.getLoggedInUsersTeamId());
			}else {
				//new DocumentToolsPage(driver).selectFromInputTeamUnderIntray(ObjectReader.reader.getLoggedInUsersTeamId());
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getLoggedInUsersTeam(),"input-Teams","input-teams");
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getOtherUserTeamName(),"input-Teams","input-teams");
			}
			
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote4);
			//capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnSpecificRow(getEnvVariable, "2");
			String actMailStatus =  navigationMenu.getColumnValueFromTable("Mail Status");
			sleepFor(1000);
			navigationMenu.clickOnSpecificRow(getEnvVariable, "2");			//Used to unselect the row
			
			navigationMenu.clickOnSpecificRow(getEnvVariable, "3");
			String actMailStatus1 =  navigationMenu.getColumnValueFromTable("Mail Status");			
			AssertionHelper.verifyText(actMailStatus, "Info Only"); 			 				
			AssertionHelper.verifyText(actMailStatus1, "Info Only");
		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
	
	@Test(enabled = true ,priority = 8,dataProvider = "captureFormData")				//Case 25
	public void DistributeDocumentToOtherFSUsingInfoOnly(Map<String, String> map) throws InterruptedException {
		String diffFileSystem = map.get("memoFileSystem");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test = ExtentTestManager.getTest();
		
		distrNote5 = "DistrNote5"+generateRandomNumber();
		try {
			getApplicationUrl();			
			capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote5, null,null,null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote5);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			
			intrayTools.selectFromFileSystemDistribute(diffFileSystem, getEnvVariable);
			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getOtherUserTeamId());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			
			new HomePage(driver).changeFileSystem(diffFSNameForRefresh);
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote5);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if(getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromTeamUnderIntray(ObjectReader.reader.getOtherUserTeamName());
			}else {
				//new DocumentToolsPage(driver).selectFromInputTeamUnderIntray(ObjectReader.reader.getOtherUserTeamName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getOtherUserTeamName(),"input-Teams","input-teams");
			}
			
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote5);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus =  navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Info Only"); 
			
			//new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());

		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
	}
	
    @Test(enabled = true ,priority = 9)				//Case 30
	public void DistributeUsingMultipleReferralOption() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test = ExtentTestManager.getTest();
		distrNote6 = "DistrNote6"+generateRandomNumber();	
		getApplicationUrl();			
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote6, null,null,null,getEnvVariable);

		try {
			home.clickOnMyIntrayIcon(getEnvVariable);	
			navigationMenu.searchInFilter(distrNote6);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();
			
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getLoggedInUsersTeamId());
			sleepFor(1000);
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getOtherUserTeamId());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			//new HomePage(driver).refreshCurrentFileSystem();
		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}		
	}
    
	@Test(enabled = false, priority = 10) 				// Case 12
	public void VerifyErrorMessageIfOnlyNotesAreAdded() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote7 = "DistrNote7" + generateRandomNumber();

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote7, null, null, null, getEnvVariable);

		try {
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote7);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();

			intrayTools.enterNotesPillsInEscalate("Notes added");
			sleepFor(1000);
			click(intrayTools.btnDistributeApply, "Clicking on apply changes button");
			new WindowHelper(driver).waitForPopup("Distribute intray item");
			String getErrorMsg1 = new WindowHelper(driver).getPopupMessage();
			navigationMenu.clickOkOnPopup();
			sleepFor(1000);
			intrayTools.enterAndSelectPillsInEscalate("EmailConnectUser");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			//new ActionHelper(driver).pressEscape();
			AssertionHelper.verifyText(getErrorMsg1,
					"Please select at least one user or team in 'Escalate' or 'Info Only' before applying changes.");
			//new HomePage(driver).refreshCurrentFileSystem();

		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}

	@Test(enabled = true, priority = 11, dataProvider = "captureFormData") // Case 28
	public void DistributeToDiffFileSystemUserUsingInfoOnly(Map<String, String> map) throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		String diffFileSystem = map.get("memoFileSystem");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();
		distrNote8 = "DistrNote8" + generateRandomNumber();

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote8, null, null, null,getEnvVariable);

		try {
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote8);
			capture.clickOnIntrayListBtn();
			sleepFor(1000);
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.selectFromFileSystemDistribute(diffFileSystem, getEnvVariable);
			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getUserName());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);

			new HomePage(driver).changeFileSystem(diffFSNameForRefresh);
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote8);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if (getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530")
					|| getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540")
					|| getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromUserUnderIntray(ObjectReader.reader.getUserName(),
						getEnvVariable);
			} else {
				//new DocumentToolsPage(driver).selectFromInputUserUnderIntray(ObjectReader.reader.getUserName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getUserName(),"input-Users","input-users");
			}

			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote8);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Info Only");

			//new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());

		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
    }
    
    @Test(enabled = true ,priority = 12)					//Case 27
    public void DistributeToMultipleUserUsingInfoOnlyOption() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote9 = "DistrNote9" + generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote9, null, null, null, getEnvVariable);

		try {
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote9);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();

			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getUserName());
			sleepFor(1000);
			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getSuperLoginId());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);

			new HomePage(driver).refreshCurrentFileSystem();
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote9);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if (getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530")
					|| getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540")
					|| getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromUserUnderIntray(ObjectReader.reader.getUserName(),
						getEnvVariable);
			} else {
				//new DocumentToolsPage(driver).selectFromInputUserUnderIntray(ObjectReader.reader.getUserName());
				//new DocumentToolsPage(driver).selectFromInputUserUnderIntray(ObjectReader.reader.getSuperUserName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getUserName(),"input-Users","input-users");
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getSuperUserName(),"input-Users","input-users");
			}

			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote9);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Info Only");

			//new HomePage(driver).refreshCurrentFileSystem();

		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
    
    @Test(enabled = true ,priority = 13)				//Case 29
    public void DistributeUsingMultipleReferralAndVefiyNotesOnLandingPage() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote10 = "DistrNote10" + generateRandomNumber();
		getApplicationUrl();			
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote10, null,null,null,getEnvVariable);
		
		try {
			home.clickOnMyIntrayIcon(getEnvVariable);	
			navigationMenu.searchInFilter(distrNote10);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getOtherUserTeamId());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			new HomePage(driver).refreshCurrentFileSystem();
			
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote10);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if (getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530")
					|| getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540")
					|| getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromTeamUnderIntray(ObjectReader.reader.getOtherUserTeamName());
			} else {
				//new DocumentToolsPage(driver).selectFromInputTeamUnderIntray(ObjectReader.reader.getOtherUserTeamName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getOtherUserTeamName(),"input-Teams","input-teams");
			}

			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			capture.clickOnFirstItemOfIntray();
			String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "New");
		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
    }
    
    @Test(enabled = true ,priority = 14)				//Case 31
    public void VerifyNotesOnLandingPageOfDocument() {
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote10);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		new DocumentToolsPage(driver).clickOnLandingPageIcon();
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		navigationMenu.clickOnNavTab("Notes");
		sleepFor(2000);
		String getNotesTitle = driver.findElement(By.xpath("//table[@id='notesubgrid']/tbody/tr[1]/td[3]")).getText();
		AssertionHelper.verifyTextContains(getNotesTitle, "MultipleReferral");
    } 
    
    @Test(enabled = true ,priority = 15,dataProvider = "captureFormData")			//Case 32
    public void DistributeToDifferentFSUsingMutiReferralTeam(Map<String, String> map) throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		String diffFileSystem = map.get("memoFileSystem");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote11 = "DistrNote11" + generateRandomNumber();
		getApplicationUrl();			
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote11, null,null,null,getEnvVariable);
		
		try {
			home.clickOnMyIntrayIcon(getEnvVariable);	
			navigationMenu.searchInFilter(distrNote11);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();
			
			intrayTools.selectFromFileSystemDistribute(diffFileSystem, getEnvVariable);
			sleepFor(2000);
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			sleepFor(2000);
			//intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getOtherUserTeamId());
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getOtherUserTeamId());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			
			new HomePage(driver).changeFileSystem(diffFSNameForRefresh);
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote11);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if(getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromTeamUnderIntray(ObjectReader.reader.getOtherUserTeamName());
			}else {
				//new DocumentToolsPage(driver).selectFromInputTeamUnderIntray(ObjectReader.reader.getOtherUserTeamName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getOtherUserTeamName(),"input-Teams","input-teams");
			}
			
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote11);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus =  navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "New");
			System.out.println("===========MailStatus======"+actMailStatus);
			//new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());

		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
    }
    
    @Test(enabled = true ,priority = 16,dataProvider = "captureFormData")			//Case 35
    public void DistributeToDiffUsingMultiReferralUser(Map<String, String> map) throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		String diffFileSystem = map.get("memoFileSystem");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote12 = "DistrNote12" + generateRandomNumber();
		getApplicationUrl();			
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote12, null,null,null,getEnvVariable);
		try {
			home.clickOnMyIntrayIcon(getEnvVariable);	
			navigationMenu.searchInFilter(distrNote12);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();
			
			intrayTools.selectFromFileSystemDistribute(diffFileSystem, getEnvVariable);
			sleepFor(1000);
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			//intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getSuperUserName());
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSuperUserName());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			
			new HomePage(driver).changeFileSystem(diffFSNameForRefresh);
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote12);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if(getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromUserUnderIntray(ObjectReader.reader.getSuperUserName(),
						getEnvVariable);
			}else {
				//new DocumentToolsPage(driver).selectFromInputUserUnderIntray(ObjectReader.reader.getUserName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getSuperUserName(),"input-Users","input-users");
			}
			
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote12);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus =  navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "New");
			System.out.println("===========MailStatus======"+actMailStatus);
			//new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).changeFileSystem(ObjectReader.reader.getFileSystemName());
		}
    }
    
	@Test(enabled = false, priority = 17) 				// Case 38								//Verifying the error & distributing to team to avoid the error
	public void VerifyErrorMessageIfNotesAremissingWhileDistribute() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote13 = "DistrNote13" + generateRandomNumber();
		try {
			getApplicationUrl();
			capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote13, null, null, null,getEnvVariable);

			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote13);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getOtherUserTeamId());
			sleepFor(1000);
			click(new IntrayToolsPage(driver).btnDistributeApply, "Clicking on apply changes button");
			String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Distribute intray item");
			//new ActionHelper(driver).pressTab();
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);

			AssertionHelper.verifyTextContains(getMsg,
					"Notes are mandatory on this system. You must enter a note before you apply your setting.");
		} finally {
		    getApplicationUrl();
		    new AlertHelper(driver).acceptAlertIfPresent();
		    new HomePage(driver).refreshCurrentFileSystem();
		}
		
	}

	@Test(enabled = true, priority = 18) 					// Case 34
	public void DistributeToMultipleUserUsingMultipleReferral() throws InterruptedException {

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote14 = "DistrNote14" + generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote14, null, null, null,getEnvVariable);
		try {
			home.clickOnMyIntrayIcon(getEnvVariable);
			navigationMenu.searchInFilter(distrNote14);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();

			sleepFor(1000);
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			//intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getSuperUserName());
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSuperUserName());
			sleepFor(1000);
			//intrayTools.enterAndSelectPillsInInfoOnly("Email Connect User");
			intrayTools.enterAndSelectPillsInEscalate("Email Connect User");
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
			
			getApplicationUrl();
			new DocumentToolsPage(driver).clickOnMoreSearch();
			new DocumentToolsPage(driver).EnterInDocRefField(distrNote14);
			if (getEnvVariable.equals("Enterprise_Sp1s")) {
				test.log(Status.INFO, "====In if condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
			} else {
				test.log(Status.INFO, "====In else condition");
				new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
			}
			if(getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")) {
				new DocumentToolsPage(driver).selectFromUserUnderIntray(ObjectReader.reader.getUserName(),
						getEnvVariable);
			}else {
				//new DocumentToolsPage(driver).selectFromInputUserUnderIntray(ObjectReader.reader.getUserName());
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(ObjectReader.reader.getSuperUserName(),"input-Users","input-users");
			}
			
			capture.clickOnIntrayListBtn();
			navigationMenu.waitForFilterTextBox();
			navigationMenu.searchInFilter(distrNote14);
			capture.clickOnFirstItemOfIntray();
			String actMailStatus =  navigationMenu.getColumnValueFromTable("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "New");


			//new HomePage(driver).refreshCurrentFileSystem();
		} finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}

	@Test(enabled = true, priority = 19) 					// Case 36
	public void RemoveDistributeWorkToOtherUserIntrayAndDistributeDocAndVerifyErrorMsg() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		getApplicationUrl();
		sleepFor(2000);
		adminUser.checkSecurityRightsForUser("Distribute Work to Other Users In-Trays", "Intray Processing",
				ObjectReader.reader.getUserName(), false, getEnvVariable);

		try {
			home.clickOnMyIntrayIcon(getEnvVariable);
			navigationMenu.searchInFilter(distrNote14);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();

			sleepFor(1000);
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getSuperUserName());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			click(new IntrayToolsPage(driver).btnDistributeApply, "Clicking on apply changes button");
			String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Distribute intray item Failed");
			//navigationMenu.clickOkOnPopup();
			AssertionHelper.verifyTextContains(getMsg,
					"The user does not have permission to distribute an intray item under escalate and multiple referral distribution");

		}finally {
			getApplicationUrl();
//			sleepFor(2000);
//			adminUser.checkSecurityRightsForUser("Distribute Work to Other Users In-Trays", "Intray Processing",
//					ObjectReader.reader.getUserName(), true, getEnvVariable);
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}

	}
	
	@Test(enabled = true, priority = 20)
	public void WithoutDistributeToOtherUserCanPerformInfoOnly() throws InterruptedException {								//Case 37
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote15 = "DistrNote15" + generateRandomNumber();
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote15, null, null, null,getEnvVariable);
		try {
			home.clickOnMyIntrayIcon(getEnvVariable);
			navigationMenu.searchInFilter(distrNote15);
			capture.clickOnFirstItemOfIntray();
			intrayTools.clickOnDistributeIcon();

			sleepFor(1000);
			//intrayTools.selectFromDistributionTypeDistribute("Multiple Referral");
			//intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getSuperUserName());
			//sleepFor(1000);
			intrayTools.enterAndSelectPillsInInfoOnly(ObjectReader.reader.getLoginId());
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);

			new HomePage(driver).refreshCurrentFileSystem();
		}finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			sleepFor(2000);
			adminUser.checkSecurityRightsForUser("Distribute Work to Other Users In-Trays", "Intray Processing",
					ObjectReader.reader.getUserName(), true, getEnvVariable);

			new HomePage(driver).refreshCurrentFileSystem();			
		}
	}
	
	@Test(priority=21, enabled = true,dataProvider = "newRoleTeamData")
	public void CreateNewTeam(String roleId, String rolename, String teamId, String teamName) {
		ArrayList <String> mailStatuses = new ArrayList <String>();
		mailStatuses.add("Complete");
		mailStatuses.add("Pending");
		mailStatuses.add("Verified");
		mailStatuses.add("Matched");
		mailStatuses.add("New");
		mailStatuses.add("New Template");
		mailStatuses.add("In Progress");
		teamId1 = "Flash";
		String forward = "/"; String underScroe ="_";
		String backward = "\\";
		teamName1 = "Flash"+forward+underScroe+backward;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.clickOnAddIcon();
		adminUser.addTeamId(teamId1);
		adminUser.addTeamName(teamName1);
		adminUser.selectUsersRole(rolename,getEnvVariable);
		adminUser.clickOnMailStatusesTab();
		adminUser.addMailStatuses(mailStatuses,getEnvVariable);
		try {
		navigationMenu.clickOnSaveIcon();
		List<String> teamData = adminUser.getAddedTeamData();
		//AssertionHelper.verifyText(teamData.get(0), teamId1);
		//AssertionHelper.verifyText(teamData.get(1), teamName1);
		}
		 catch(Exception ex){
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "already");
		 }
	}
	
	@Test(priority=22, enabled = true)
	public void DistributeUsingTeamNameIfItContainSlash() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote18 = "DistrNote18" + generateRandomNumber();
		getApplicationUrl();
		try {
			capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote18, null, null, null,getEnvVariable);
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref", distrNote18);
			capture.clickOnIntrayListBtn();
			intrayTools.clickOnFirstItemInList();
			intrayTools.clickOnDistributeIcon();
			intrayTools.enterAndSelectPillsInEscalate(teamId1);
			sleepFor(1000);
			intrayTools.enterAndSelectPillsInInfoOnly(teamId1);
			sleepFor(1000);
			intrayTools.enterNotesPillsInEscalate("Notes added");
			new ActionHelper(driver).pressTab();
			intrayTools.clickOnModalApplyChangesButtonWithPopup();
			sleepFor(4000);
		} finally {
			getApplicationUrl();
			new AlertHelper(driver).acceptAlertIfPresent();
			new HomePage(driver).refreshCurrentFileSystem();
		}
	}
	
	@Test(enabled = true,priority = 23)
	public void VerifyAuditOfDistributionOnLandingPage() throws InterruptedException {		//No audit
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote16 = "DistrNote16" + generateRandomNumber();
		
		Map<String, String> values = new HashMap<String, String>();
		values.put("DistributeIntrayItem", "0");
				
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
		
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote16, null, null, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote16);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate("EmailConnectUser");
		sleepFor(1000);
		intrayTools.enterNotesPillsInEscalate("Notes added");
		new ActionHelper(driver).pressTab();
		intrayTools.clickOnModalApplyChangesButtonWithPopup();
		sleepFor(4000);
		
		new HomePage(driver).refreshCurrentFileSystem();
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote16);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		
		intrayTools.clickOnIntrayLandingPageIcon();
		new JavaScriptHelper(driver).scrollToBottom();
    	sleepFor(2000);
        navigationMenu.clickOnNavTab("Intray Audit");
        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 10);
        String getMsg = driver.findElement(By.xpath("//table[@id='intrayAuditSubgrid']//tr[1]//td")).getText();
        AssertionHelper.verifyTextContains(getMsg, "No items available");
	}

	@Test(enabled = true,priority = 24)
	public void VerifiAuditOfDistribution() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable====================" + getEnvVariable);
		test = ExtentTestManager.getTest();

		distrNote17 = "DistrNote17" + generateRandomNumber();
		
		Map<String, String> values = new HashMap<String, String>();
		values.put("DistributeIntrayItem", "1");
				
		getApplicationUrl();
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings","System");
		new ToolkitCaseManagement(driver).changeTheConfigurationValues(values);
		
		new HomePage(driver).refreshCurrentFileSystem();
		sleepFor(3000);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, distrNote17, null, null, null,getEnvVariable);
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote17);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		intrayTools.clickOnDistributeIcon();
		intrayTools.enterAndSelectPillsInEscalate("EmailConnectUser");
		sleepFor(1000);
		intrayTools.enterNotesPillsInEscalate("Notes added");
		new ActionHelper(driver).pressTab();
		intrayTools.clickOnModalApplyChangesButtonWithPopup();
		sleepFor(4000);
		
		new HomePage(driver).refreshCurrentFileSystem();
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref", distrNote17);
		capture.clickOnIntrayListBtn();
		intrayTools.clickOnFirstItemInList();
		
		intrayTools.clickOnIntrayLandingPageIcon();
		new JavaScriptHelper(driver).scrollToBottom();
    	sleepFor(2000);
        navigationMenu.clickOnNavTab("Intray Audit");
        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 10);
        String getActionColumnName = driver.findElement(By.xpath("//table[@id='intrayAuditSubgrid']//tr[1]//td[5]")).getText();
        test.log(Status.INFO, "AcitonItemOfDistribution "+getActionColumnName);
        AssertionHelper.verifyTextContains(getActionColumnName, "DistributeIntrayItem");
	}
}
