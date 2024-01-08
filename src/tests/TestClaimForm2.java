package tests;



import java.util.List;
import java.util.Map;

//import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import utils.ExtentReports.ExtentTestManager;

public class TestClaimForm2 extends TestBase {
	public FolderFlagReference folderPage ;
	private Logger log = LoggerHelper.getLogger(TestClaimForm2.class);
	public NavigationMenu navigationMenu;
	public CapturePage capture;
	public AdminWorkflowSection priority;
	public ExtentTest test;
	public ExcelReader xls;
	public String protMarker;
	public String folder1Ref;
	public String folder1TranRef;
	
	
	private String masterDocRef;
	private String subDocRef;
	private String folder1Prefix;
	private String latestDummyFolder1Ref;

	@BeforeClass
	public void setupClass()  {
		capture = new CapturePage(driver);
		folderPage=new FolderFlagReference(driver);
		navigationMenu = new NavigationMenu(driver);
		priority = new AdminWorkflowSection(driver);
		xls = new ExcelReader();

	}

	@DataProvider(name="folderRefData")
	public Object[][] folderReferenceData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","FolderRefData");
		return formData;
	}
	
	@DataProvider(name="claimFormCase")
	public Object[][] claimFormCase() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","ClaimFormCaseData");
		return formData;
	}
	
//	@Test(enabled = true,priority = 1)
//	public void VerifyMailStatusOfDocument() throws InterruptedException {
//		test = ExtentTestManager.getTest();
//		String getEnvVariable = System.getProperty("propertyName");
//		System.out.println("getEnvVariable===================="+getEnvVariable);
//
//		getApplicationUrl();
//		home.clickOnMyIntrayIcon(getEnvVariable);
//		capture.clickOnFirstItemOfIntray();
//		String actAccoutRef = navigationMenu.getColumnValue("Account Ref");
//		test.log(Status.INFO, "actAccountRef "+actAccoutRef);
//		//AssertionHelper.verifyText(actAccoutRef, folder1Ref);
//		
////		new HomePage(driver).clickOnChangeUser();
////		sleepFor(2000);
////		new LoginPage(driver).loginWithCredentials("STD1","3");
////		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
//
//		getApplicationUrl();
//		capture.selectSearchTab();
//		capture.searchWithCriteria("Doc Ref","Case1");
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//		//String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
//		String actMailStatus = navigationMenu.getColumnValue("Mail Status");
//		test.log(Status.INFO, "actMailStatus "+actMailStatus);
//		AssertionHelper.verifyText(actMailStatus, "Pending");
//		
//		getApplicationUrl();
//		capture.selectSearchTab();
//		capture.searchWithCriteria("Doc Ref","autoMast9063");
//		capture.clickOnIntrayListBtn();
//		capture.clickOnFirstItemOfIntray();
//		//String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
//		String actMailStatus1 = navigationMenu.getColumnValue("Mail Status");
//		test.log(Status.INFO, "actMailStatus1 "+actMailStatus1);
//		AssertionHelper.verifyText(actMailStatus1, "Complete");
//	}
	
	@Test(enabled = true,priority = 0)
	public void LoginWithSupervisorUser() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("SUV1","3");
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());		
	}
	
	@Test(enabled = true,priority = 1)
	public void CreateDummyReference() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		//folder1Prefix="X"+generateRandomNumber(); String folder1Name="Folder1";
		folder1Prefix= "DF123";
//		getApplicationUrl();
//		folderPage.navigateToAddDummyReference();
//		folderPage.addDummyReference(folder1Prefix,"2","Folder1",false,getEnvVariable);
//		boolean folder1Presence = folderPage.verifyDummyRefAdded(folder1Prefix,folder1Name);
//		AssertionHelper.verifyTrue(folder1Presence, "Checking for folder1 presence");
	}
	
	@Test(priority = 2,enabled = true,dataProvider="folderRefData")
	public void TestCreateFolder1Ref(Map<String, String> map) throws InterruptedException {
		String forename = "Narendra"+generateRandomNumber();
		String surname = "Adani"+generateRandomNumber();
		String email = "amit.khambad@necsws.com";
		folder1Ref = "RD"+generateThreeDigitRandomNumber();
		
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		folderPage.navigateToAddNewEntityPage(fold1entityName);
		folderPage.enterFolder1Ref(folder1Ref);
		new ActionHelper(driver).pressTab();
    	new JavaScriptHelper(driver).scrollToElement(new CapturePage(driver).folder1RefEmailAddress);
    	sleepFor(2000);
    	sendKeys(new CapturePage(driver).folder1RefEmailAddress, email, "Entering value as "+email);

    	new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
    	navigationMenu.clickOnNavTab("Person");
    	sendKeys(folderPage.surnameFieldInput, surname, "Entering Surname "+surname);
    	sleepFor(1000);
    	sendKeys(folderPage.forenameFieldInput, forename, "Entering forename "+forename);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();	
	}
	
	@Test(enabled = true,priority = 3,dataProvider = "claimFormCase")
	public void CaptureDocumentUsingDummyReferenceAndTranslate(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		
		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();
		masterDocRef = map.get("masterDocRef")+generateRandomNumber();
		subDocRef = map.get("subDocRef1")+generateRandomNumber();
		String mDocType = map.get("masterDocType");
		String s1DocType = map.get("subDocType1");
		String folder1Title=ObjectReader.reader.getFolder1RefName();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");
		
		getApplicationUrl();
		latestDummyFolder1Ref = folderPage.enterAndSelectDummyRef(entityUnderSearch,getEnvVariable,folder1Prefix);
		log.info("folder 1 dummy ref value is "+latestDummyFolder1Ref);
		test.log(Status.INFO, latestDummyFolder1Ref);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null,mDocType,masterDocRef, latestDummyFolder1Ref, "To Team","",ObjectReader.reader.getOtherUserTeamName(),getEnvVariable);
		capture.navigateAndCaptureDocWithParameters(null, null,s1DocType,subDocRef, latestDummyFolder1Ref, "To Team","",ObjectReader.reader.getOtherUserTeamName(),getEnvVariable);
		
		String expectedMsg1 = "Are you sure you want to translate all Documents Indexed to "+"'"+latestDummyFolder1Ref+"'"+" to "+"'"+folder1Ref+"'"+" and delete the Reference "+"'"+latestDummyFolder1Ref+"'"+" ?";
		
		String expectedMsg2 = "The destination "+folder1Ref+" has different associated reference(s) Alt Account Ref: Misc Ref: Property Ref: Do you want to use this reference? Please note that if your choice is yes, then all entities will have the new associated reference values.";
		
//Comment if translate is not working
		getApplicationUrl();
		new HomePage(driver).clickOnMetroTile("Claim Appforms");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(masterDocRef);
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		
//		getApplicationUrl();
//		new IntrayToolsPage(driver).searchDocumentClickOnFirstIntrayItem("Doc Ref", masterDocRef );
		new IntrayToolsPage(driver).clickOnAssociatedAndSelect("Show "+folder1Title,getEnvVariable);
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Save changes", "Actions");
		
		navigationMenu.clickOnIconUnderAction("Translate");
		navigationMenu.waitForIcon("Cancel changes");
		WebElement destRefEle = driver.findElement(By.xpath(String.format(new DocumentToolsPage(driver).tmpTxtFolderRefInput, "Destination Ref")));
		sendKeys(destRefEle, folder1Ref, "Entering Translater ref as "+ folder1Ref);
		new ActionHelper(driver).pressTab();
		sleepFor(3000);
		waitHelper.waitForElement(new DocumentToolsPage(driver).lblReferenceDetailsFolderRef,20);
		
		navigationMenu.clickOnIconUnderAction("Save");
		new WindowHelper(driver).waitForPopup("Translate");
		String transRefMsg1 = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(3000);
		test.log(Status.INFO, "transRefMsg1  "+transRefMsg1);
		new WindowHelper(driver).waitForPopup("Translate");
		String transRefMsg2 = new WindowHelper(driver).getPopupMessage();
		//sleepFor(2000);
		new WindowHelper(driver).clickOkOnPopup();
		test.log(Status.INFO, "transRefMsg2  "+transRefMsg2);
		new WindowHelper(driver).waitForPopup("Translate");
		String transRefMsg3 = new WindowHelper(driver).getPopupMessage();
		test.log(Status.INFO, "transRefMsg3  "+transRefMsg3);
		new JavaScriptHelper(driver).clickElement(new WindowHelper(driver).dialogOkBtn);
		sleepFor(5000);
		
		AssertionHelper.verifyTextContains(transRefMsg1, expectedMsg1);
	}
	
	@Test(enabled = true,priority = 4)			//Makse false if translate is not working
	public void VerifyDeletedRefIsNotAvailable() {
		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();
		String entityName = "Account";
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref", folder1Ref);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
				
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(entityUnderSearch);
		navigationMenu.expandAccordionIfCollapsed("General");
		CapturePage capture = new CapturePage(driver);
		capture.enterFolderRefEnityPage(entityName, latestDummyFolder1Ref);
		navigationMenu.clickOnIconUnderAction("Run");
		new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 20);
		sleepFor(2000);
		String getMsg = driver.findElement(By.xpath("//table[@id='crudGridResults']//tr[1]/td[1]")).getText();
		AssertionHelper.verifyTextContains(getMsg, "No items available");
	}

	
//	@Test(priority = 1,enabled = true,dataProvider="folderRefData")
//	public void TestCreateFolder1Ref(Map<String, String> map) throws InterruptedException {
//		String forename = "Narendra"+generateRandomNumber();
//		String surname = "Adani"+generateRandomNumber();
//		String email = "amit.khambad@necsws.com";
//		folder1Ref = "C"+generateThreeDigitRandomNumber();
//		
//		String fold1entityName = ObjectReader.reader.getFolder1RefName();
//		
//		getApplicationUrl();
//		folderPage.navigateToAddNewEntityPage(fold1entityName);
//		folderPage.enterFolder1Ref(folder1Ref);
//		new ActionHelper(driver).pressTab();
//    	new JavaScriptHelper(driver).scrollToElement(new CapturePage(driver).folder1RefEmailAddress);
//    	sleepFor(2000);
//    	sendKeys(new CapturePage(driver).folder1RefEmailAddress, email, "Entering value as "+email);
//
//    	new JavaScriptHelper(driver).scrollToTop();
//		sleepFor(2000);
//    	navigationMenu.clickOnNavTab("Person");
//    	sendKeys(folderPage.surnameFieldInput, surname, "Entering Surname "+surname);
//    	sleepFor(1000);
//    	sendKeys(folderPage.forenameFieldInput, forename, "Entering forename "+forename);
//		folderPage.clickOnSaveIconWaitForItsInvisiblity();
//		
//	}
	
//	@Test(enabled = true,priority = 5)
//	public void LoginwithSupervisorUser() throws InterruptedException {
//		getApplicationUrl();
//		new HomePage(driver).clickOnChangeUser();
//		sleepFor(2000);
//		new LoginPage(driver).loginWithCredentials("Supervisor","n");
//		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
//	}
	
	@Test(enabled = false,priority = 6,dataProvider = "claimFormCase")
	public void CaptureDocumentUsingSupervisor(Map<String, String> map) throws InterruptedException {
		//masterDocRef = map.get("masterDocRef")+generateRandomNumber();
		subDocRef = map.get("subDocRef1")+generateRandomNumber();
		String mDocType = map.get("masterDocType");
		String s1DocType = map.get("subDocType1");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		//capture.navigateAndCaptureDocWithParameters(null, null,mDocType,masterDocRef, folder1Ref, "To Team","",ObjectReader.reader.getOtherUserTeamName(),getEnvVariable);
		
		capture.navigateAndCaptureDocWithParameters(null, null,s1DocType,subDocRef, folder1Ref, "To Team","",ObjectReader.reader.getOtherUserTeamName(),getEnvVariable);
	}

	@Test(enabled = true,priority = 7)			//STD1
	public void OnHomePageSelectMetroTile() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		new HomePage(driver).clickOnMetroTile("Claim Appforms");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(folder1Ref);				//change to latestDummyFolder1Ref/folder1Ref
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("Allocate intray item", "Mail");
		new WindowHelper(driver).waitForModalDialog("Allocate intray item");
		new DropdownHelper(driver).selectFromAutocompleDD("STD1", new IntrayToolsPage(driver).allocateUserDD, getEnvVariable);
		click(new IntrayToolsPage(driver).allocateOkPopUpBtn, "Clicking in allocate ok button");
		sleepFor(2000);
	}
	
	@Test(enabled = true,priority = 8)
	public void LoginWithStandardUserAndVerifyCampaign() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("STD1","3");
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		capture.clickOnFirstItemOfIntray();
		
		priority.isCampaignDropdownButtonShown();
		List<String> ddOptions =priority.clickOnCampaignDropdownGetText(getEnvVariable);
		test.log(Status.INFO, "ddOptions==============="+ddOptions.toString());
		//AssertionHelper.verifyTrue(ddOptions.contains(campaignName.toUpperCase()), "Checking whether dropdown contains campaign");
		
		//String actAccoutRef = navigationMenu.getColumnValueFromTable("Account Ref");
		String actAccoutRef = navigationMenu.getColumnValue("Account Ref");
		AssertionHelper.verifyText(actAccoutRef, folder1Ref);			//change to latestDummyFolder1Ref/folder1Ref
	}

	@Test(enabled = true,priority = 9)
	public void ViewDocument() {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		navigationMenu.searchInFilter(folder1Ref);				//change to latestDummyFolder1Ref/folder1Ref
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("View document", "Document");
		try {
			sleepFor(2000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			sleepFor(6000);
		} finally {
			new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
	}
	
	@Test(enabled = true,priority = 10,dataProvider = "claimFormCase")
	public void AcceptOneSubDocuemntInCase(Map<String, String> map) throws InterruptedException {
		//String mDocType,String s1DocType,String s2DocType,String mDocRef,String s1DocRef,String s2DocRef,String foldRef
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String [] subDocSplit = map.get("subDocType1").split("-");				//s1DocType.split("-");

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		navigationMenu.searchInFilter(folder1Ref);				//change to latestDummyFolder1Ref/folder1Ref
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		sleepFor(2000);
		boolean tblCaseSubfolderStatus=new VerificationHelper(driver).isElementDisplayedByEle(new ToolkitCaseManagement(driver).tblCaseSubfolder);

		if(tblCaseSubfolderStatus) {
			new ToolkitCaseManagement(driver).ClickOnSubCaseFolder(subDocSplit[1].trim());
			new ToolkitCaseManagement(driver).clickOnFirstAcceptedSubDoc();
			navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
			sleepFor(2000);
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
			sleepFor(1000);
			
			new ToolkitCaseManagement(driver).AccceptSubTaskInCase("Verify Id and Signature");		//For now hardc
			String getPendingPopUpMsg = new ToolkitCaseManagement(driver).saveCaseToPendingStatusWithDelay("5");
			test.log(Status.INFO, "getPendingPopUpMsg==== "+getPendingPopUpMsg);
			navigationMenu.waitForFilterTextBox();
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",masterDocRef);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			//String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			String actMailStatus = navigationMenu.getColumnValue("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Pending");
			
			AssertionHelper.verifyTextContains(getPendingPopUpMsg, "Do you want to change the mail status to Pending?");

		}else {
			refreshPage();
			sleepFor(2000);
			new ToolkitCaseManagement(driver).ClickOnSubCaseFolder(subDocSplit[1].trim());
			new ToolkitCaseManagement(driver).clickOnFirstAcceptedSubDoc();
			navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
			sleepFor(2000);
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
			sleepFor(1000);
			
			new ToolkitCaseManagement(driver).AccceptSubTaskInCase("Verify Id and Signature");		//For now hardc
			String getPendingPopUpMsg = new ToolkitCaseManagement(driver).saveCaseToPendingStatusWithDelay("5");
			test.log(Status.INFO, "getPendingPopUpMsg==== "+getPendingPopUpMsg);
			navigationMenu.waitForFilterTextBox();	
			
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",masterDocRef);
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			//String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
			String actMailStatus = navigationMenu.getColumnValue("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Pending");
			
			AssertionHelper.verifyTextContains(getPendingPopUpMsg, "Do you want to change the mail status to Pending?");
		}
	}
	
	@Test(enabled = true,priority = 11)
	public void SearchDocumentAndSendMail() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
//		new HomePage(driver).clickOnChangeUser();
//		sleepFor(2000);
//		new LoginPage(driver).loginWithCredentials("Standard","n");
//		home.changeFileSystem(ObjectReader.reader.getFileSystemName());

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		navigationMenu.searchInFilter(folder1Ref);				//change to latestDummyFolder1Ref/folder1Ref
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIconMenu("Generate Email", "Outbound");
		new IntrayToolsPage(driver).SendEmailUsingOutbound("Pending", "SMTP1", getEnvVariable);
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
	
	@Test(enabled = false,priority = 12)
	public void ClientSendingEmailForPendingDocuments(){
		getApplicationUrl();
		//new IntrayToolsPage(driver).SendEmailUsingGmail("royal.mine12@gmail.com", "Ruchika@mynedrm1.onmicrosoft.com", "pfiyempaivbiqsjx", null, null, "C642");
		//new IntrayToolsPage(driver).SendEmailUsingJava("royal.mine12@gmail.com", "Ruchika@mynedrm1.onmicrosoft.com", "pfiyempaivbiqsjx", folder1Ref);
	}
	
	@Test(enabled = false,priority = 13)
	public void LoginAndLogout() throws InterruptedException {
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("Supervisor","n");
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		
		getApplicationUrl();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials("Standard","n");
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
	}
	
	@Test(enabled = true,priority = 14,dataProvider = "claimFormCase")
	public void CaptureSecondSubDocumentAndVerifyCaseStatus(Map<String, String> map) throws InterruptedException {
		String subDocRef2 = map.get("subDocRef2")+generateRandomNumber();
		String s1DocType = map.get("subDocType2");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null,s1DocType,subDocRef2, folder1Ref, "To Team","",ObjectReader.reader.getOtherUserTeamName(),getEnvVariable);				//replace latestDummyFolder1Ref to folder1Ref
		
		getApplicationUrl();
		capture.selectSearchTab();
		capture.searchWithCriteria("Doc Ref",masterDocRef);
		//home.clickOnMyIntrayIcon(getEnvVariable);
		//navigationMenu.searchInFilter(masterDocRef);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		//String actMailStatus = navigationMenu.getColumnValueFromTable("Mail Status");
		String actMailStatus = navigationMenu.getColumnValue("Mail Status");
		AssertionHelper.verifyText(actMailStatus, "Matched");
	}
	
	@Test(enabled = true,priority = 15,dataProvider = "claimFormCase" )
	public void OpenCaseAndAcceptSubDocAndTask(Map<String, String> map) throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String [] subDocSplit = map.get("subDocType2").split("-");				//s1DocType.split("-");

		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		navigationMenu.searchInFilter(folder1Ref);			//change to latestDummyFolder1Ref/folder1Ref
		capture.clickOnFirstItemOfIntray();
		
		navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		sleepFor(2000);
		boolean tblCaseSubfolderStatus=new VerificationHelper(driver).isElementDisplayedByEle(new ToolkitCaseManagement(driver).tblCaseSubfolder);

		if(tblCaseSubfolderStatus) {
			new ToolkitCaseManagement(driver).ClickOnSubCaseFolder(subDocSplit[1].trim());
			new ToolkitCaseManagement(driver).clickOnFirstAcceptedSubDoc();
			navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
			sleepFor(2000);
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
			sleepFor(1000);
			
			new ToolkitCaseManagement(driver).AccceptSubTaskInCase("Validate Insurance Number");		//For now hardc
			navigationMenu.waitForFilterTextBox();
			String getVerifiedPopUpMsg = new ToolkitCaseManagement(driver).saveCaseToVerifiedStatus();
			test.log(Status.INFO, "getVerifiedPopUpMsg==== "+getVerifiedPopUpMsg);
		    getApplicationUrl();
		    capture.searchWithCriteria("Doc Ref", masterDocRef);
		    capture.clickOnIntrayListBtn();
		    navigationMenu.waitForFilterTextBox();
		    //AssertionHelper.verifyText(capture.getMailStatusForFirstItem(),"Verified");
			String actMailStatus = navigationMenu.getColumnValue("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Verified");
			navigationMenu.waitForFilterTextBox();
			
			AssertionHelper.verifyTextContains(getVerifiedPopUpMsg, "Do you want to change the mail status to Verified?");
		}else {
			refreshPage();
			sleepFor(2000);
			new ToolkitCaseManagement(driver).ClickOnSubCaseFolder(subDocSplit[1].trim());
			new ToolkitCaseManagement(driver).clickOnFirstAcceptedSubDoc();
			navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
			sleepFor(2000);
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
			sleepFor(1000);
			
			new ToolkitCaseManagement(driver).AccceptSubTaskInCase("Validate Insurance Number");		//For now hardc
			navigationMenu.waitForFilterTextBox();
			String getVerifiedPopUpMsg = new ToolkitCaseManagement(driver).saveCaseToVerifiedStatus();
			test.log(Status.INFO, "getVerifiedPopUpMsg==== "+getVerifiedPopUpMsg);
		    getApplicationUrl();
		    capture.searchWithCriteria("Doc Ref", masterDocRef);
		    capture.clickOnIntrayListBtn();
		    navigationMenu.waitForFilterTextBox();
		    //AssertionHelper.verifyText(capture.getMailStatusForFirstItem(),"Verified");
			String actMailStatus = navigationMenu.getColumnValue("Mail Status");
			AssertionHelper.verifyText(actMailStatus, "Verified");
			navigationMenu.waitForFilterTextBox();	
			
			AssertionHelper.verifyTextContains(getVerifiedPopUpMsg, "Do you want to change the mail status to Verified?");
		}
	}
	
	@Test(enabled = true,priority = 16)
	public void ChangeMailStatusOfCaseToComplete() throws InterruptedException {
		test = ExtentTestManager.getTest();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
	    capture.searchWithCriteria("Doc Ref", masterDocRef);
	    capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		if(getEnvVariable.equals("Enterprise_Sp1s") || getEnvVariable.equals("Enterprise_R522") || getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531")) {
		capture.changeEmailStatus("Complete");				//Changed drop down locator for email status in R531
		}else {
			capture.changeMailStatusUsingSelectClass("Complete");
		}
		
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", masterDocRef);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		//String mailStatus =capture.getMailStatusForFirstItem();
		String mailStatus = navigationMenu.getColumnValue("Mail Status");
		navigationMenu.waitForFilterTextBox();
		log.info("Mail status is "+mailStatus); 
		AssertionHelper.verifyText(mailStatus, "Complete");
	}
	
	@Test(enabled = false,priority = 17)
	public void LoginWIthActualUser() throws InterruptedException {
		getApplicationUrl();
		new AlertHelper(driver).acceptAlertIfPresent();
		new HomePage(driver).clickOnChangeUser();
		sleepFor(2000);
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(),ObjectReader.reader.getPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());

		
	}
	
	@Test(priority = 17,enabled = false,dataProvider="folderRefData")
	public void TestCreateFolder1RefForTranslate(Map<String, String> map) throws InterruptedException {
		String forename = "Stock"+generateRandomNumber();
		String surname = "Purn"+generateRandomNumber();
		String email = "amit.khambad@necsws.com";
		folder1TranRef = "TS"+generateThreeDigitRandomNumber();
		
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		//folder1Ref=map.get("AddFolder1Ref")+generateRandomNumber();
		
		getApplicationUrl();
		folderPage.navigateToAddNewEntityPage(fold1entityName);
		folderPage.enterFolder1Ref(folder1TranRef);
		new ActionHelper(driver).pressTab();
    	new JavaScriptHelper(driver).scrollToElement(new CapturePage(driver).folder1RefEmailAddress);
    	sleepFor(2000);
    	sendKeys(new CapturePage(driver).folder1RefEmailAddress, email, "Entering value as "+email);

    	new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
    	navigationMenu.clickOnNavTab("Person");
    	sendKeys(folderPage.surnameFieldInput, surname, "Entering Surname "+surname);
    	sleepFor(1000);
    	sendKeys(folderPage.forenameFieldInput, forename, "Entering forename "+forename);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();	
	}
	
	@Test(enabled = false,priority = 14)
	public void TranslateFolder1RefOfCase() {
		String entityUnderSearch=ObjectReader.reader.getFolder1RefName();
		String entityName = "Account";
		String expFolderTransMSg = "Are you sure you want to translate all Documents";
		
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(entityUnderSearch);
		home.searchAndNavigateToEditEnity(entityName, folder1Ref);
		String popupMsg = new DocumentToolsPage(driver).translateFolderReferenceTo(folder1TranRef);			//Added ok pop up locator in R531
		AssertionHelper.verifyTextContains(popupMsg, expFolderTransMSg);
		sleepFor(2000);
		getApplicationUrl();
		capture.searchWithCriteria("Doc Ref", masterDocRef);
		capture.clickOnDocumentListBtn();
		navigationMenu.waitForFilterTextBoxSearchResult();
		String actTransRef = navigationMenu.getColumnValueFromTable("Account Ref");
		AssertionHelper.verifyText(actTransRef, folder1TranRef);

	}


}

