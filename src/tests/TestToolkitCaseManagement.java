package tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;

public class TestToolkitCaseManagement extends TestBase {
	public ToolkitCaseManagement toolkitCase;
	public CapturePage capture ;
	public DocumentToolsPage docTools;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	VerificationHelper verificationHelper;
	public LoginPage login;
	
	//Private variables used in methods
		private String masterDocRef="",accRef="";
		private String subDocRef="";
		
		private String masterDocRef1="",accRef1="";
		private String masterDocRef22="";
		private String subDocRef1="";
		
		private String masterDocRef2="",accRef2="";
		private String subDocRef2="";
		
		private String masterDocRef3="",accRef3="";
		private String subDocRef3="";

		private String masterDocRef4="",accRef4="";
		private String subDocRef4="";
		
		private String masterDocRef5="",accRef5="";
		private String subDocRef5="";
		
		private String docRef6="",accRef6="";
		private String subDocRef6="";
	
	@BeforeClass
	public void setupClass()  {
		toolkitCase = new ToolkitCaseManagement(driver);
		capture = new CapturePage(driver);
		docTools=new DocumentToolsPage(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		windowHelper=new WindowHelper(driver);
		xls = new ExcelReader();
		dropdownHelper=new DropdownHelper(driver);
		waitHelper =  new WaitHelper(driver);
		verificationHelper = new VerificationHelper(driver);
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
	
	@DataProvider(name="caseManagement")
	public Object[][] caseManagementData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaseManagementData");
		return formData;
	}
	
	// Pre requsite a template should be created
	// Dropdown option validation remaining
	@Test(enabled = true, priority = 600)
	public void TestAddSubFolderTemplateContent() throws InterruptedException {
		getApplicationUrl();
		toolkitCase.navigateToCaseMangementMaintenancePage();
		toolkitCase.clickOnEditOfFilteredRow();
		AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.txtNameTemplateDetails),
				"Verifying text name of template detail displayed");
		AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.txtNameTemplateDetails),
				"Verifying text name of template detail displayed");
		//refreshPage();
		navigationMenu.clickOnNavTab("Template Contents");
		boolean status = new VerificationHelper(driver)
				.isElementDisplayed(By.xpath("//div[text()='1 to 1 of 1 entries']"));

		if (!status) {
			sleepFor(1000);
			refreshPage();
			navigationMenu.clickOnNavTab("Template Contents");
			AssertionHelper.verifyTrue(toolkitCase.verifyTemplateContent(), "Verifying template content");
			navigationMenu.clickOnNavTab("Master Document Types");
			AssertionHelper.verifyTrue(toolkitCase.verifyMasterDocType(), "Verifying master document type");

		} else {
			AssertionHelper.verifyTrue(toolkitCase.verifyTemplateContent(), "Verifying template content");
			navigationMenu.clickOnNavTab("Master Document Types");
			AssertionHelper.verifyTrue(toolkitCase.verifyMasterDocType(), "Verifying master document type");
		}

	}

	// Complete
	@Test(enabled = true, priority = 601)
	public void TestDocumentTypeAdditionTemplateContent() throws InterruptedException {
		String tempRefName = "autoTempScript", templateRowTable = "Memo";
		getApplicationUrl();
		toolkitCase.navigateToCaseMangementMaintenancePage();
		navigationMenu.clickOnAddIcon();
		toolkitCase.addTemplateDetails(tempRefName);
		navigationMenu.clickOnNavTab("Template Contents");

		boolean status = new VerificationHelper(driver)
				.isElementDisplayed(By.xpath("//tr/td[text()='No items available']"));
		if (!status) {
			sleepFor(1000);
			refreshPage();
			toolkitCase.addTemplateDetails(tempRefName);
			sleepFor(1000);
			navigationMenu.clickOnNavTab("Template Contents");

			navigationMenu.clickOnIcon("Add items into template", "Template Contents");
			toolkitCase.selectAddToTemplatePopup(templateRowTable);
			AssertionHelper.verifyTrue(
					verificationHelper.isElementDisplayedByEle(toolkitCase.getAddedTempElement(templateRowTable)),
					"Verifying for "); // locator changed by amit
			toolkitCase.clickOnCancelChanges("No");

		} else {

			navigationMenu.clickOnIcon("Add items into template", "Template Contents");
			toolkitCase.selectAddToTemplatePopup(templateRowTable);
			AssertionHelper.verifyTrue(
					verificationHelper.isElementDisplayedByEle(toolkitCase.getAddedTempElement(templateRowTable)),
					"Verifying for "); // locator changed by amit
			toolkitCase.clickOnCancelChanges("No");

		}

	}

	// Complete - Testing required doc edition on add template
	@Test(enabled = true, priority = 602)
	public void TestEditRequiredOnAddDocTemplateContent() throws InterruptedException {
		String tempRefName = "autoTempScript", templateRowTable = "Memo", requiredDoc = "1";
		getApplicationUrl();
		toolkitCase.navigateToCaseMangementMaintenancePage();
		navigationMenu.clickOnAddIcon();
		toolkitCase.addTemplateDetails(tempRefName);
		navigationMenu.clickOnNavTab("Template Contents");

		boolean status = new VerificationHelper(driver)
				.isElementDisplayed(By.xpath("//tr/td[text()='No items available']"));
		if (!status) {
			sleepFor(1000);
			refreshPage();
			toolkitCase.addTemplateDetails(tempRefName);
			sleepFor(1000);
			navigationMenu.clickOnNavTab("Template Contents");
			navigationMenu.clickOnIcon("Add items into template", "Template Contents");
			toolkitCase.selectAddToTemplatePopup(templateRowTable);
			toolkitCase.clickOnAddedTemplate(templateRowTable);
			toolkitCase.clickOnEditTemplateIcon();
			sleepFor(1000);
			toolkitCase.changeRequiredDocOnEditPopup(requiredDoc);
			sleepFor(1000);
			String actualValue = toolkitCase.getTemplateRequiredValue();
		//AssertionHelper.verifyText(actualValue, requiredDoc);

			new NavigationMenu(driver).clickOnIcon("Cancel changes", "Action");
			windowHelper.waitForPopup("Leave Page"); // Adding because getting unhandled alert exception in next node
			sleepFor(2000);
			windowHelper.clickOnModalFooterButton("No");

		} else {
			navigationMenu.clickOnIcon("Add items into template", "Template Contents");
			toolkitCase.selectAddToTemplatePopup(templateRowTable);
			toolkitCase.clickOnAddedTemplate(templateRowTable);
			toolkitCase.clickOnEditTemplateIcon();
			sleepFor(1000);
			toolkitCase.changeRequiredDocOnEditPopup(requiredDoc);
			String actualValue = toolkitCase.getTemplateRequiredValue();
		//AssertionHelper.verifyText(actualValue, requiredDoc);

			new NavigationMenu(driver).clickOnIcon("Cancel changes", "Action");
			windowHelper.waitForPopup("Leave Page"); // Adding because getting unhandled alert exception in next node
			sleepFor(2000);
			windowHelper.clickOnModalFooterButton("No");
		}

	}
	
	@Test(enabled = true,priority=604)					//changing from 604 to 618
	public void TestVerifyEditionTemplate() throws InterruptedException {
		 String requiredDoc = "1";
		 getApplicationUrl();
		 toolkitCase.navigateToCaseMangementMaintenancePage();
		 toolkitCase.clickOnEditOfFilteredRow();
		 navigationMenu.clickOnNavTab("Template Contents");
		 
		 toolkitCase.RefreshAndMove();			//Added because on template contents row is not visible
		 
		 navigationMenu.clickOnFilteredRow(toolkitCase.templateDataTableId);
		 toolkitCase.clickOnEditTemplateIcon();
		 sleepFor(1000);
		 toolkitCase.changeRequiredDocOnEditPopup(requiredDoc);
		 sleepFor(1000);
		 String actualValue = toolkitCase.getTemplateRequiredValue();
		 AssertionHelper.verifyText(actualValue, requiredDoc);
		 try {
			 navigationMenu.clickOnIcon("Save changes made to template","Actions" ); //added by amit because of see screenshot
			 navigationMenu.waitForAddIcon();
		 } catch (Exception e) {
			 String actMessage = new WindowHelper(driver).getPopupMessage();
			 new WindowHelper(driver).clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "No changes");
		 }
	}
		 
	

	//Checked with 5.30
		@Test(enabled = true,priority=605,dataProvider = "caseManagement")
	public void TestVerifyCaseCapturedInAspect(Map<String, String> map) throws InterruptedException {
				accRef6 = map.get("Folder1RefData")+generateRandomNumber();
				docRef6= "autoAspect_"+generateRandomNumber()+"Master";
				String masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"); 
				
				String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable===================="+getEnvVariable);

		 getApplicationUrl();
		 capture.navigateAndCaptureDocWithParameters(null, null, masterDocType, docRef6, accRef6,getEnvVariable);			//uncomment line
		 capture.navigateAndCaptureDocWithParameters(null, null, subDocType, docRef6+"Sub", accRef6,getEnvVariable);
		 capture.searchWithCriteria("Doc Ref", docRef6);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 //waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 //AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
				// "Verifying case sub folder accessible");
		 
		//Addding because to tblcasesubfolder is not visible
		 boolean tblCaseSubfolderStatus=new VerificationHelper(driver).isElementDisplayedByEle(toolkitCase.tblCaseSubfolder);
		 if(tblCaseSubfolderStatus) {
			 sleepFor(1000);
			 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
					 "Verifying case sub folder accessible");
		 }else {
			 sleepFor(1000);
			 refreshPage();
			 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
					 "Verifying case sub folder accessible");
		 }
		 
	 }
		
	@Test(enabled = true,priority=606)
	public void TestVerifyInstigateIntrayCase() throws InterruptedException {
		 String docRef = "AutoAspectMaster"; 
		 getApplicationUrl();
		 capture.searchWithCriteria("Doc Ref", docRef6);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 
		//Addding because to tblcasesubfolder is not visible
		 boolean tblCaseSubfolderStatus=new VerificationHelper(driver).isElementDisplayedByEle(toolkitCase.tblCaseSubfolder);
		 if(tblCaseSubfolderStatus) {
			 sleepFor(1000);
			 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
					 "Verifying case sub folder accessible");
		 }else {
			 sleepFor(1000);
			 refreshPage();
			 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
					 "Verifying case sub folder accessible");
		 }
		 
		 	
	}
	
	
	//Complete
	@Test(enabled = true,priority=607,dataProvider = "caseManagement")
	public void TestVerifyCaseSubDocAccessed(Map<String, String> map) throws InterruptedException {
		  		accRef = map.get("Folder1RefData")+generateRandomNumber();
		  		masterDocRef = map.get("masterDocRef")+generateRandomNumber();
		  		subDocRef= map.get("subDocRef")+generateRandomNumber();
				String masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"),templateName=map.get("CaseName");
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
         getApplicationUrl();
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, masterDocType, masterDocRef, accRef,false,getEnvVariable);			//uncomment line
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, subDocType, subDocRef, accRef,false,getEnvVariable);
		 capture.searchWithCriteria("Doc Ref", masterDocRef);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
//		 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
//		 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
//				 "Verifying case sub folder accessible"); 	
		 
		//Addding because to tblcasesubfolder is not visible
		 boolean tblCaseSubfolderStatus=new VerificationHelper(driver).isElementDisplayedByEle(toolkitCase.tblCaseSubfolder);
		 if(tblCaseSubfolderStatus) {
			 sleepFor(1000);
			 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
					 "Verifying case sub folder accessible");
		 }else {
			 sleepFor(1000);
			 refreshPage();
			 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.tblCaseSubfolder), 
					 "Verifying case sub folder accessible");
		 }
	}
	
	@Test(enabled = true,priority=608,dataProvider = "caseManagement")
	public void TestVerifyCaseCapturedSubDocAcceptance(Map<String, String> map) throws InterruptedException {
				String prevmasterDocRef = masterDocRef;
				String accRef = map.get("Folder1RefData"),
				 masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"),subDocRef= map.get("subDocRef"),templateName=map.get("CaseName");
		 getApplicationUrl();
		 //capture.navigateAndCaptureDocWithParameters(filepath, filename, masterDocType, docRef, accRef);
		 //capture.navigateAndCaptureDocWithParameters(filepath, filename, subDocType, docRef, accRef);
		 capture.searchWithCriteria("Doc Ref", prevmasterDocRef);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 //navigationMenu.clickOnSpecificRow("1");
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 //waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 toolkitCase.doubleClickOnFirstSubDoc();
		 waitHelper.waitForElementVisible(toolkitCase.chkRejectedFirstSubDoc, 20, 1);
		 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.chkAcceptedFirstSubDoc), 
				 "Verifying accepted checkbox accessible");
		}
			
		//Incomplete
		@Test(enabled = true,priority=610,dataProvider = "caseManagement")
		public void TestAutomaticAllocationOfMasterAndSubDocument(Map<String, String> map) throws InterruptedException {
			masterDocRef1 = map.get("masterDocRef")+"AutoAllocation"+generateRandomNumber();
			accRef1 = map.get("Folder1RefAutoAllocation")+generateRandomNumber(); 		//folderrefernce mismatch
			subDocRef1 = map.get("subDocRef")+"AutoAllocation"+generateRandomNumber();
			String masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"),subDocRef= map.get("subDocRef"),templateName=map.get("CaseName");
			String getEnvVariable = System.getProperty("propertyName");
			System.out.println("getEnvVariable===================="+getEnvVariable);
		 getApplicationUrl();
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, masterDocType, masterDocRef1, accRef1,false,getEnvVariable);
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, subDocType, subDocRef1, accRef1,false,getEnvVariable);
		 capture.searchWithCriteria("Doc Ref", masterDocRef1);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 //navigationMenu.clickOnSpecificRow("1");
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 //waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 toolkitCase.doubleClickOnFirstSubDoc();
		 waitHelper.waitForElementVisible(toolkitCase.chkRejectedFirstSubDoc, 20, 1);
		 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.chkRejectedFirstSubDoc), 		//changed from chkAcceptedFirstSubDoc  to chkRejectedFirstSubDoc
				 "Verifying rejected checkbox accessible");							//Verifying rejected checkbox accessible
		}
		
		//Incomplete
		@Test(enabled = true,priority=611,dataProvider = "caseManagement")
		public void TestAllocationOfTwoMasterAndSubDocument(Map<String, String> map) throws InterruptedException {
				 accRef2 = map.get("Folder1RefData")+generateRandomNumber();
				 masterDocRef1 = map.get("masterDocRef")+"OneS"+generateRandomNumber();
				 masterDocRef22 = map.get("masterDocRef")+"TwoS"+generateRandomNumber();		//added by amit,
				 subDocRef2= map.get("subDocRef")+"_"+generateRandomNumber();
				 String masterDocType = map.get("masterDocType"),
				 masterDocType2 = map.get("masterDocType2"),		 
				 subDocType=map.get("subDocType"),templateName=map.get("CaseName");
		 
					String getEnvVariable = System.getProperty("propertyName");
					System.out.println("getEnvVariable===================="+getEnvVariable);
					
		 getApplicationUrl();
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, masterDocType, masterDocRef1, accRef2,false,getEnvVariable);
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, masterDocType2, masterDocRef22, accRef2,false,getEnvVariable);
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, subDocType, subDocRef2, accRef2,false,getEnvVariable);
		
		  getApplicationUrl(true);
		 capture.searchWithCriteria("Doc Ref", masterDocRef1);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 docTools.clickOnSpecificDocumentDataTable("ScanDoc ", "1");
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 //waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 toolkitCase.doubleClickOnFirstSubDoc();			//Refresh added by amit
		 waitHelper.waitForElementVisible(toolkitCase.chkRejectedFirstSubDoc, 20, 1);
		 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.chkAcceptedFirstSubDoc), 
				 "Verifying accepted checkbox accessible");
		}
		
		@Test(enabled = true,priority=612)
		public void VerifySubDocAllocationUsingTwoMasterDoc() {
			 getApplicationUrl();
			 capture.searchWithCriteria("Doc Ref", masterDocRef22);
			 capture.clickOnDocumentListBtn();
			 navigationMenu.waitForFilterTextBox();
			 //navigationMenu.clickOnSpecificRow("1");
			 capture.clickOnFirstItemOfIntray();
			 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
			 //waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
			 toolkitCase.doubleClickOnFirstSubDoc();
			 waitHelper.waitForElementVisible(toolkitCase.chkRejectedFirstSubDoc, 20, 1);
			 AssertionHelper.verifyTrue(verificationHelper.isElementDisplayedByEle(toolkitCase.chkAcceptedFirstSubDoc), 
					 "Verifying accepted checkbox accessible");
		}
		
		
				//Almost complete
		@Test(enabled = true,priority=613,dataProvider = "caseManagement")
		public void TestVerifyUnassignSubDocument(Map<String, String> map) throws InterruptedException {
			String masterDocRefUnassign = masterDocRef;
			String accRef = map.get("Folder1RefData"),
				 masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"),subDocRef= map.get("subDocRef"),templateName=map.get("CaseName");
		 getApplicationUrl();
		 //capture.navigateAndCaptureDocWithParameters(filepath, filename, masterDocType, docRef, accRef);
		 //getApplicationUrl();
		 //capture.navigateAndCaptureDocWithParameters(filepath, filename, subDocType, docRef, accRef);
		 capture.searchWithCriteria("Doc Ref", masterDocRefUnassign);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 //navigationMenu.clickOnSpecificRow("1");
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 
		 boolean status=new VerificationHelper(driver).isElementDisplayedByEle(toolkitCase.tblCaseSubfolder);
		 sleepFor(1000);
		 if(!status) {
			 refreshPage();
		 }
		 
		 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 toolkitCase.clickonFirstSubDoc();
		 toolkitCase.clickOnUnassign();
		 sleepFor(1000);
		 
		//Verified unassignment of sub document [added by amit]
		 toolkitCase.saveCaseToPendingStatusWithDelay("1");
		 navigationMenu.waitForFilterTextBox();
		 
		 getApplicationUrl();
		 home.refreshCurrentFileSystem();
		 
		 capture.searchWithCriteria("Doc Ref", masterDocRefUnassign);
		 capture.clickOnDocumentListBtn();
		 navigationMenu.waitForFilterTextBox();
		 //navigationMenu.clickOnSpecificRow("1");
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIconMenu("View Case Sub-Folder", "Case");
		 sleepFor(1000);
		 new NavigationMenu(driver).clickOnIcon("Re-run the same search to refresh the lists", "General");
		 AssertionHelper.verifyFalse(verificationHelper.isElementDisplayedByEle(toolkitCase.firstSubDocCaseSubfolderContent));
		}
		
		
		@Test(enabled = false,priority=615,dataProvider = "caseManagement")   //TT
		public void TestPartMatchTemplateChangesStatusMatchedFromPending(Map<String, String> map) throws InterruptedException {
				accRef4 = map.get("Folder1ChangeStatus")+generateRandomNumber();
				 masterDocRef4 = map.get("masterDocRef")+generateRandomNumber();
				 subDocRef4= map.get("subDocRef")+generateRandomNumber();
				String masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"),templateName=map.get("CaseName");
		 
				String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable===================="+getEnvVariable);		
	   	 getApplicationUrl();
		 //toolkitCase.changeTemplateValues(templateName,"Part Match");
		  toolkitCase.navigateAndCaptureDocWithParameters(null, null, masterDocType, masterDocRef4, accRef4,false,getEnvVariable);
		 toolkitCase.searchDocAndViewCaseSubFolder("Account Ref", accRef4,"1");			//Changes inside this method for row after search
		
		 boolean status=new VerificationHelper(driver).isElementDisplayedByEle(toolkitCase.tblCaseSubfolder);
		 sleepFor(1000);
		 if(!status) {
			 refreshPage();
		 }
		 String actualStatus = toolkitCase.getMasterDocStatus();
		 AssertionHelper.verifyText(actualStatus, "Outstanding");
		 toolkitCase.saveCaseToPendingStatusWithDelay("1");
		 sleepFor(1000);
		 navigationMenu.waitForFilterTextBox();
		 sleepFor(2000);
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, subDocType, subDocRef4, accRef4,false,getEnvVariable);
		 toolkitCase.searchDocAndViewCaseSubFolder("Doc Ref", masterDocRef4,"1");
		 
		 //Added by amit for matched case
		 getApplicationUrl();
		 capture.searchWithCriteria("Account Ref", accRef4);
		 capture.clickOnIntrayListBtn();
		 navigationMenu.waitForFilterTextBox();
		 AssertionHelper.verifyText(capture.getMailStatusForFirstItem(),"Matched");
		 
//		 String aftersubDocStatus = toolkitCase.getMasterDocStatus();
//		 AssertionHelper.verifyText(aftersubDocStatus, "Matched");
		 
		 //String aftersubDocStatus = toolkitCase.getMasterDocStatus();
		 //AssertionHelper.verifyText(aftersubDocStatus, "Matched");
		 /**
		 waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 toolkitCase.doubleClickOnFirstSubDoc();
		 toolkitCase.clickOnFirstAcceptedSubDoc();
		 navigationMenu.clickOnSaveIcon();
		**/
		//Check with team how to verify doc is unassigned
		}

		
		//Complete need to run
		@Test(enabled = true,priority=616,dataProvider = "caseManagement")
		public void TestMailStatusChangedVerified(Map<String, String> map) throws InterruptedException {
			masterDocRef5 = map.get("masterDocRef")+"stsVerify_"+generateRandomNumber();
			subDocRef5= map.get("subDocRef")+"_"+generateRandomNumber();
			accRef5 = map.get("Folder1StatusVerified")+"_"+generateRandomNumber();
				String masterDocType = map.get("masterDocType"), 
				 subDocType=map.get("subDocType"),templateName=map.get("CaseName"); 
				String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable===================="+getEnvVariable);
		 getApplicationUrl();
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, masterDocType, masterDocRef5, accRef5,false,getEnvVariable);
		 toolkitCase.navigateAndCaptureDocWithParameters(null, null, subDocType, subDocRef5, accRef5,false,getEnvVariable);
		 capture.searchWithCriteria("Doc Ref", masterDocRef5);
		 capture.clickOnIntrayListBtn();
		 navigationMenu.waitForFilterTextBox();
		 //navigationMenu.clickOnSpecificRow("1");
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 //waitHelper.waitForElementVisible(toolkitCase.tblCaseSubfolder, 20, 1);
		 sleepFor(1000);
		 toolkitCase.doubleClickOnFirstSubDoc();
		 toolkitCase.clickOnFirstAcceptedSubDoc();
		 //navigationMenu.clickOnSaveIcon();
		 navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
		 sleepFor(2000);
		 new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		 sleepFor(1000);
		 
		 //Added by amit for verified case
	     toolkitCase.saveCaseToVerifiedStatus();
	     getApplicationUrl();
	     capture.searchWithCriteria("Doc Ref", masterDocRef5);
	     capture.clickOnIntrayListBtn();
	     navigationMenu.waitForFilterTextBox();
	     AssertionHelper.verifyText(capture.getMailStatusForFirstItem(),"Verified");
	     
	     //String aftersubDocStatus = toolkitCase.getMasterDocStatus();
	     //AssertionHelper.verifyText(aftersubDocStatus, "Matched");

	    //For accepted getting checked 
	    //When will get matched status against master doc

		}


}

