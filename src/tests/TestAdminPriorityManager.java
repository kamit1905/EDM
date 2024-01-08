package tests;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;
import utils.ExtentReports.ExtentTestManager;

public class TestAdminPriorityManager extends TestBase {
	public AdminWorkflowSection workflow;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	WindowHelper windowHelper;
	public LoginPage login;
	public AdminUserSection adminUser;
	
	private String campaignName = "";
	
	@BeforeClass
	public void setupClass()  {
		workflow = new AdminWorkflowSection(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		windowHelper=new WindowHelper(driver);
		xls = new ExcelReader();
		dropdownHelper=new DropdownHelper(driver);
		adminUser = new AdminUserSection(driver);
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
	
	@Test(priority=500,enabled = true)
	public void TestAddCampaign() throws InterruptedException {
		campaignName = "Auto"+generateRandomNumber();
		getApplicationUrl();
		sleepFor(2000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.waitForAddIcon();
		navigationMenu.clickOnAddIcon();
		navigationMenu.waitForIcon("Cancel changes");
		workflow.addCampaignName(campaignName);				//Added refresh and sleep because Campaign field is not visible
		workflow.selectAvailableFields("Doc Ref");
		workflow.clickOnAddField();
		workflow.clickOnAddWeightedValueToField("Camp","Doc Ref","Text");
		workflow.checkExcludeAllOthers();
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(campaignName);
			sleepFor(1000);
			String actualCampaign = workflow.getFilteredCampaignRowText();
			AssertionHelper.verifyText(actualCampaign, campaignName.toUpperCase());
		} catch (Exception e) {
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "Campaign name already exists.");
		}
		
		
		}
	
	@DataProvider(name="addEntityData")
	public Object[][] addEntityFromData() throws Exception
	{
		Object[][] formData = xls.readExcel( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","EntityFormData");
				
		return formData;
	}
	
	@Test(priority=501,enabled = true)
	public void TestAddCampaignToUser() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		 //String campaignname="Autocamp";
		 sleepFor(2000);
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("User maintenance", "User");
		 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
		 sleepFor(1000);
		 adminUser.clickOnFilteredUser();
		 navigationMenu.clickOnEditIcon();
		 sleepFor(2000);
		 try {
			 adminUser.selectUsersCampaign(campaignName.toUpperCase(),getEnvVariable);
			 
			 if(getEnvVariable.equals("Enterprise_Sp1s")) {
				 navigationMenu.clickOnSaveIcon();
			 }else {
				 navigationMenu.clickOnSaveIconForUser(); 
			 }
			 navigationMenu.waitForAddIcon();
			 home.refreshCurrentFileSystem();
			 sleepFor(4000);
		} catch (Exception e) {
			
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "No changes to save.");
			 home.refreshCurrentFileSystem();		
			 sleepFor(4000);
		}	
	}
	

	@Test(priority=502,enabled = false)			//true
	public void TestEditCampaign() throws InterruptedException {
		String campaignName = "Autocamp";
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);
		workflow.clickOnFilteredCampaignRowText();
		navigationMenu.clickOnEditIcon();
		workflow.selectSelectedFields("Doc Ref");
		sleepFor(2000);
		workflow.clickOnAddField();
		sleepFor(1000);
		workflow.clickOnAddWeightedValueToField("CampTest","Doc Ref","Text");
		//workflow.checkExcludeAllOthers();
		sleepFor(1000);
		workflow.selectDescendingOrderRadioButton();
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		String actualCampaign = workflow.getFilteredCampaignRowText();
		AssertionHelper.verifyText(actualCampaign, campaignName.toUpperCase());
		}
	

	
	@Test(priority=503,enabled = true)
	public void TestCampaignDropdownAvailability() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		//String campaignName = "Autocamp";
		sleepFor(2000);
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt)) {
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		}
		workflow.isCampaignDropdownButtonShown();
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnIconMenu("Select Campaign","Document");
			List<String> ddOptions =workflow.clickOnCampaignDropdownGetText(getEnvVariable);
			AssertionHelper.verifyTrue(ddOptions.contains(campaignName.toUpperCase()), "Checking whether dropdown contains campaign");
		}else {
			List<String> ddOptions =workflow.clickOnCampaignDropdownGetText(getEnvVariable);
			AssertionHelper.verifyTrue(ddOptions.contains(campaignName.toUpperCase()), "Checking whether dropdown contains campaign");
		}		
	}
	
	@Test(priority=504,enabled = true)
	public void TestCampaignDropdownNotShownOnIntraySearch() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		String docRef="Camp";
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		AssertionHelper.verifyFalse(workflow.isCampaignDropdownButtonShown());
		}
	
	//Prepare capture data for more items of campaign
	@Test(priority=505,enabled = true)
	public void TestCampaignDescendingOrderFunctionality() throws InterruptedException, ParseException {
		String docRef="Camp";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt)) {
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		}
		workflow.isCampaignDropdownButtonShown();
		//Create generic function to read any column values
		List<Date> dates = workflow.getDateReceivedColumnValuesForAllRows();
	AssertionHelper.verifyTrue(workflow.checkIfDatesAreInDescedningOrder(dates), "Checking if dates are in descneding order");
	
		sleepFor(1000);							//111 TC
		List<String> actualDocRef=workflow.getDocRefColumnValuesForAllRows();
		System.out.println("actualDocRef "+actualDocRef);
		for(int i=0;i<actualDocRef.size();i++) {
	AssertionHelper.verifyTextContains(actualDocRef.get(i), docRef);
		}
	}
	
	@Test(priority=506,enabled = true)
	public void TestCampaignAscendingOrderFunctionality() throws InterruptedException, ParseException {
		//String campaignName="Autocamp";
		test = ExtentTestManager.getTest();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);
		workflow.clickOnFilteredCampaignRowText();
		navigationMenu.clickOnEditIcon();
		//workflow.selectAvailableFields("Date Received");					//For second time run comment this line
		workflow.clickOnSpecifiedSelectedField("Date Received");
		sleepFor(1000);
		workflow.selectAscendingOrderRadioButton();
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt)) {
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		}
		//Create generic function to read any column values
		List<Date> dates = workflow.getDateReceivedColumnValuesForAllRows();
		navigationMenu.waitForFilterTextBox();
		test.info("Received Dates in desc "+dates.toString());
		AssertionHelper.verifyTrue(workflow.checkIfDatesAreInAscendingOrder(dates), "Checking if dates are in ascending order");
	}
	
	
	@Test(priority=507,enabled = true)
	public void TestEditButtonWeightedValuesCampaign() throws InterruptedException {
		//String campaignName = "Autocamp",
			 	String docRef = "Camp",
				editedDocRef = "CampEdit";
			 	
			 	String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable===================="+getEnvVariable);
				
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);
		workflow.clickOnFilteredCampaignRowText();
		navigationMenu.clickOnEditIcon();
		sleepFor(1000);
		workflow.verifyEditIconForSelectedField();    				//Added because edit icon is not enabled
		workflow.selectSelectedFields("Doc Ref");
		workflow.clickOnEditWeightedValueToField(getEnvVariable);
		AssertionHelper.verifyTrue(workflow.isAllEditWeightedPopupButtonsPresent(), "Checking for all edit button presence");
		workflow.enterWeightedTextValue(editedDocRef, "Doc Ref");
//		windowHelper.clickOnModalFooterButton("Cancel");
//		Thread.sleep(1000);
//		workflow.verifyEditIconForSelectedField();    				//Added because edit icon is not enabled
//		workflow.clickOnEditWeightedValueToField();
//		String originalValue = workflow.getWeightedTextValue("Doc Ref");
//		AssertionHelper.verifyText(originalValue, docRef);
//		sleepFor(2000); 				//Added because element is taking time to load
//		workflow.enterWeightedTextValue(editedDocRef, "Doc Ref");
		sleepFor(1000);
		windowHelper.clickOnModalFooterButton("Ok");
		sleepFor(2000);
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			navigationMenu.searchInFilter(campaignName);			//Added to select filtered row
			workflow.clickOnFilteredCampaignRowText();
			navigationMenu.clickOnEditIcon();
			sleepFor(1000);
			workflow.verifyEditIconForSelectedField();    				//Added because edit icon is not enabled
			workflow.selectSelectedFields("Doc Ref");
			workflow.clickOnEditWeightedValueToField(getEnvVariable);
			sleepFor(1000);
			AssertionHelper.verifyText(workflow.getWeightedTextValue("Doc Ref"), editedDocRef);
			windowHelper.clickOnModalFooterButton("Cancel");
			
			Thread.sleep(1000);
			workflow.verifyEditIconForSelectedField();    				//Added because edit icon is not enabled
			workflow.clickOnRemoveWeightedValueToField(getEnvVariable);
			sleepFor(1000);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		} catch (Exception e) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
			navigationMenu.searchInFilter(campaignName);
			workflow.clickOnFilteredCampaignRowText();
			navigationMenu.clickOnEditIcon();
			workflow.selectSelectedFields("Doc Ref");
			workflow.clickOnRemoveWeightedValueToField(getEnvVariable);
			sleepFor(1000);
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
			
		}
	}
	
	
	@Test(priority=508,enabled = true)
	public void TestWeightedValuesOnPriorityCampaign() throws InterruptedException {
		//String campaignName = "Autocamp",
				String docRef = "Camp",
				editedDocRef = "CampEdit";
				
				String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable===================="+getEnvVariable);
				
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);
		workflow.clickOnFilteredCampaignRowText();
		navigationMenu.clickOnEditIcon();
		workflow.selectSelectedFields("Doc Ref");
		sleepFor(1000);
		workflow.clickOnAddWeightedValueToField("Camp", "Doc Ref", "Text");
		sleepFor(1000);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		getApplicationUrl();
		home.clickOnMyIntrayIcon(getEnvVariable);
		if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt)) {
			new NavigationMenu(driver).clickOnIconMenu("Re-run the same search to refresh the lists", "General");
		}
		//Create generic function to read any column values
		//Assertion for checking campedit ahead of camp doc ref
		List<String> docRefs = workflow.getDocRefNamesforDocuments();
		//AssertionHelper.verifyTrue(workflow.checkIfDatesAreInAscendingOrder(docRefs), "Checking if dates are in ascending order");
	}
	
	@Test(priority=551,enabled = true)
	public void TestRemoveCampaignToUser() {
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		//String campaignname="Autocamp";
		 //sleepFor(2000);
		 getApplicationUrl();
		 navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIcon("User maintenance", "User");
		 navigationMenu.searchInFilter(ObjectReader.reader.getUserName());
		 sleepFor(1000);
		 adminUser.clickOnFilteredUser();
		 navigationMenu.clickOnEditIcon();
		 sleepFor(2000);
		 try {
			 adminUser.selectUsersCampaign("(none)",getEnvVariable);
			 if(getEnvVariable.equals("Enterprise_Sp1s")) {
				 navigationMenu.clickOnSaveIcon();
			 }else {
				 navigationMenu.clickOnSaveIconForUser();
			 }		 
			 navigationMenu.waitForAddIcon();
			 home.refreshCurrentFileSystem();
		} catch (Exception e) {
			
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "No changes to save.");
			 home.refreshCurrentFileSystem();		
		}	
	}
	
	//Make the priority of this test last in module
	@Test(priority=552,enabled = true)
	public void TestDeleteCampaign() throws InterruptedException {
		//String campaignName = "AUTOCAMP";
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Priority Manager campaigns maintenance","Workflow");
		navigationMenu.searchInFilter(campaignName);
		//workflow.clickOnFilteredCampaignRowText();
		navigationMenu.clickOnFilteredRow("priorityManagerTable");
		navigationMenu.clickOnIcon("Delete selected campaign", "Actions");
		sleepFor(2000);
		workflow.clickOkOnDeletePopup();
		sleepFor(2000);
		navigationMenu.waitForAddIcon();
		
	}
	
}


