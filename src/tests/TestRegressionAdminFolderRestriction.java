package tests;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.FolderFlagReference;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminFolderSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;

public class TestRegressionAdminFolderRestriction extends TestBase {
	
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
	public AdminFolderSection adminFolderSection;
	public AdminUserSection adminUser;
	
	private String folder3Ref="";
	private String groupName="";
	private List<String> folderRefList;
	
	private String existingFolder3Ref="";
	private String existingFolder1Ref="";
	
	private String folder1Ref1="";
	private String folder1Ref2="";
	private String folder1Ref="";
	
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
		adminFolderSection = new AdminFolderSection(driver);
		adminUser = new AdminUserSection(driver);
	}
	
	@DataProvider(name="intrayData")
    public Object[][] intrayData() throws Exception
    {
        Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","IntrayTools");
        return formData;
    }
	
	@Test(enabled = true,priority = 1)					//Case 11
	public void AddFolder3Restriction() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		folder3Ref="P3"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder3RefName();
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminPropertySecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder3Ref);
		adminFolderSection.clickOnSaveInFolderRestriction(refName,getEnvVariable);
	}
	
	@Test(enabled = true,priority = 2)						//Case 15,17
	public void AddExistingFolder3Restriction() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		existingFolder3Ref="SS3"+generateRandomNumber();
		String surname = "PL_"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder3RefName();
		String entityName="Property";
		
		getApplicationUrl();
		new FolderFlagReference(driver).navigateToAddNewEntityPage(refName);
		new FolderFlagReference(driver).enterFolder3Ref(existingFolder3Ref);
		new ActionHelper(driver).pressTab();
		new FolderFlagReference(driver).clickOnSaveIconWaitForItsInvisiblity();
		sleepFor(3000);
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminPropertySecurity();
		navigationMenu.clickOnAddIcon();
		click(new AdminFolderSection(driver).folder3LookupBtn, "Clicking on Folder3 Ref Lookup button");
		
		new AdminFolderSection(driver).SearchAndSelectFolderReference(entityName, existingFolder3Ref);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		//Edit folder3 ref 
		click(new AdminFolderSection(driver).folder3RefEditBtn, "Clickin on folder3 ref edit button");
		navigationMenu.waitForIcon("Translate "+refName,"Translate");
		navigationMenu.clickOnNavTab("Person");
    	sendKeys(new FolderFlagReference(driver).surnameFieldInput, surname, "Entering Surname "+surname);
    	sleepFor(1000);
    	navigationMenu.clickOnSaveIcon();
        navigationMenu.waitForIconWithDataButton("Save", "Actions");
        sleepFor(3000);

		
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();

	}

	@Test(enabled = true,priority = 3)					//Case 12,14
	public void AddExistingFolder1RestrictionAndEditFolder1Ref() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		existingFolder1Ref="AA1"+generateRandomNumber();
		String surname = "AL_"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder1RefName();
		String entityName="Account";
		
		getApplicationUrl();
		new FolderFlagReference(driver).navigateToAddNewEntityPage(refName);
		new FolderFlagReference(driver).enterFolder1Ref(existingFolder1Ref);
		new ActionHelper(driver).pressTab();
		new FolderFlagReference(driver).clickOnSaveIconWaitForItsInvisiblity();
		sleepFor(3000);
		
		//Adding existing folder1 reference
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		//navigationMenu.waitForIcon("Cancel changes", "Actions");
		new NavigationMenu(driver).waitForIcon("Cancel changes", "Actions");
		click(new AdminFolderSection(driver).folder1LookupBtn, "Clicking on Folder1 Ref Lookup button");
		
		new AdminFolderSection(driver).SearchAndSelectFolderReference(entityName, existingFolder1Ref);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		//Edit folder1 ref 
		click(new AdminFolderSection(driver).folder1RefEditBtn, "Clickin on folder1 ref edit button");
		navigationMenu.waitForIcon("Translate "+refName,"Translate");
		navigationMenu.clickOnNavTab("Person");
    	sendKeys(new FolderFlagReference(driver).surnameFieldInput, surname, "Entering Surname "+surname);
    	sleepFor(1000);
    	navigationMenu.clickOnSaveIcon();
        navigationMenu.waitForIconWithDataButton("Save", "Actions");
        sleepFor(3000);
		
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();

	}
	
	@Test(priority=4,enabled = true, dataProvider = "intrayData")				//Case 6
	public void ToVerifyThatPossibleToAccessAllDocuments(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef("F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		//adminFolderSection.clickOnSaveInFolderRestriction(refName);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		
		adminUser.checkSecurityRightsForUser("Update Restricted Folder Reference Documents", "Folder", user1, true,getEnvVariable);
       
        capture.searchWithCriteria("Account Ref", "F1-001R1");
        capture.clickOnDocumentListBtn();
        navigationMenu.waitForFilterTextBox();
		capture.clickOnFirstItemOfIntray();
		
		String accountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		System.out.println("accountRef "+accountRef);	
		AssertionHelper.verifyText(accountRef, "F1-001R1");
	}
	
	@Test(enabled = true,priority = 5,dependsOnMethods = "AddExistingFolder3Restriction")			//Case 19
	public void DeleteFolder3Restriction() {
		getApplicationUrl();
		adminFolderSection.navigateToAdminPropertySecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,existingFolder3Ref , "Inputing the text "+existingFolder3Ref);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(3000);
		
		refreshPage();
		sendKeys(navigationMenu.filterTxt,existingFolder3Ref , "Inputing the text "+existingFolder3Ref);
		sleepFor(1000);
		String filteredText = navigationMenu.getfilteredRowElement("folderSecurityDataTable").getText();
		AssertionHelper.verifyText(filteredText, "No matching records found");
	}
	
	@Test(enabled = true,priority = 6)					//Case 22
	public void VerifyDuplicateErrorMsgForFolder1Restriction() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
			
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(existingFolder1Ref);
		sleepFor(2000);
		navigationMenu.clickOnSaveIcon();
		new WindowHelper(driver).waitForPopup("Save Failed");
		String getErrorMsg = new WindowHelper(driver).getPopupMessageClickOk("Save Failed");
		AssertionHelper.verifyTextContains(getErrorMsg, "This record already exists in the database");
	}
	
	@Test(enabled = true,priority = 7)				//Case 23	
	public void VerifyDuplicateErrorMsgForFolder3Restriction() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
			
		getApplicationUrl();
		adminFolderSection.navigateToAdminPropertySecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder3Ref);
		sleepFor(2000);
		navigationMenu.clickOnSaveIcon();
		new WindowHelper(driver).waitForPopup("Save Failed");
		String getErrorMsg = new WindowHelper(driver).getPopupMessageClickOk("Save Failed");
		AssertionHelper.verifyTextContains(getErrorMsg, "This record already exists in the database");
	}
	
	@Test(enabled = true,priority = 8)				//Case 24,25
	public void CancelDeleteFolder1Folder3Restriction() {
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,existingFolder1Ref , "Inputing the text "+existingFolder1Ref);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		click(new NavigationMenu(driver).btnDeleteIcon,"Clicking on delete icon");
		sleepFor(2000);
		new WindowHelper(driver).clickOnModalFooterButton("Cancel");

		getApplicationUrl();
		adminFolderSection.navigateToAdminPropertySecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folder3Ref , "Inputing the text "+folder3Ref);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		click(new NavigationMenu(driver).btnDeleteIcon,"Clicking on delete icon");
		sleepFor(2000);
		new WindowHelper(driver).clickOnModalFooterButton("Cancel");
	}
	
	@Test(enabled = true,priority = 9)				//Case 26
	public void AddFolder1Restriction() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		folder1Ref="TA"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder1Ref);
		adminFolderSection.clickOnSaveInFolderRestriction(refName,getEnvVariable);
	}
	
	@Test(enabled = true,priority = 10,dependsOnMethods = "AddFolder1Restriction")			//Case 26
	public void UpdateUserAndAddRestriction() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Grant";
		String ref=folder1Ref;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction,getEnvVariable);
        new JavaScriptHelper(driver).scrollToTop();
        sleepFor(1000);
        adminUser.clickOnUserNavTab("Folder Restrictions");
        sleepFor(2000);
        adminUser.enterFolderRefValue(ref);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();
	}

	@Test(enabled = true,priority = 11,dependsOnMethods = "AddFolder1Restriction")					//Case 26
	public void CaptureDocumentAndSearchDocument() throws InterruptedException {
		String Ref=folder1Ref;
		String docRef="AA_"+generateRandomNumber();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, Ref,getEnvVariable);
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",Ref);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actDocRef = navigationMenu.getColumnValueFromTable("Doc Ref");
		System.out.println("actDocRef "+actDocRef);
		navigationMenu.searchInFilter(docRef);	
		AssertionHelper.verifyText(actDocRef, docRef);
		
		//Verify other account ref is not accessible
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref","F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
	}
	
	/*@Test(enabled = true,priority = 10,dependsOnMethods = "AddFolder1Restriction")			
	public void UpdateUserAndUpdateRestriction() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
		String ref=folder1Ref;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction);
        new JavaScriptHelper(driver).scrollToTop();
        sleepFor(1000);
        adminUser.clickOnUserNavTab("Folder Restrictions");
        sleepFor(2000);
        adminUser.deleteRestrictedFolder(ref);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();
	}*/

	
	@Test(enabled = true,priority = 12,dependsOnMethods = "AddFolder1Restriction")				//Case 27
	public void VerifyThatAbleToSearchWithFolder1Ref() {
		getApplicationUrl();			
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folder1Ref , "Inputing the text "+folder1Ref);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		sleepFor(1000);
		navigationMenu.clickOnDeleteIcon();
		sleepFor(3000);


		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folder1Ref , "Inputing the text "+folder1Ref);
		sleepFor(2000);
		String filteredText = navigationMenu.getfilteredRowElement("folderSecurityDataTable").getText();
		AssertionHelper.verifyText(filteredText, "No matching records found");
		
		getApplicationUrl();
        capture.searchWithCriteria("Account Ref", folder1Ref);
        capture.clickOnDocumentListBtn();
        navigationMenu.waitForFilterTextBox();
		capture.clickOnFirstItemOfIntray();
		
		String accountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		System.out.println("accountRef "+accountRef);	
		AssertionHelper.verifyText(accountRef, folder1Ref);
	}
	
	@Test(enabled = true,priority = 13,dependsOnMethods = "AddFolder1Restriction")			
	public void UpdateUserAndUpdateRestriction() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
		String ref=folder1Ref;
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction,getEnvVariable);
        new JavaScriptHelper(driver).scrollToTop();
        sleepFor(1000);
        adminUser.clickOnUserNavTab("Folder Restrictions");
        sleepFor(2000);
        adminUser.deleteRestrictedFolder(ref);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();
	}
	
	@Test(enabled = true,priority = 14,dependsOnMethods = "AddFolder1Restriction")				//Case 28
	public void UpdateUserAndAddFolder1Restriction() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Grant";
	
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder1Ref);
		adminFolderSection.clickOnSaveInFolderRestriction(folder1Ref,getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction,getEnvVariable);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();
	}

	@Test(enabled = true,priority = 15,dependsOnMethods = "AddFolder1Restriction")				//Case 28
	public void VerifyThatNotAbleToAccessFolder1Ref() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		//Verify other account ref is not accessible
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref",folder1Ref);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
	}
	
	@Test(enabled = true,priority = 16,dependsOnMethods = "AddFolder1Restriction")			//Case 29
	public void NotAbleToAccessAnyReference() {
		getApplicationUrl();			
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folder1Ref , "Inputing the text "+folder1Ref);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(3000);
		
		//Verify other account ref is not accessible
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref","F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
		}
	
	@Test(enabled = true,priority = 17,dependsOnMethods = "AddFolder1Restriction")
	public void UpdateUserAndRestrictionTypeToRestrict() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
	
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction,getEnvVariable);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();

	}

	@Test(enabled = true,priority = 18,dependsOnMethods = "AddFolder1Restriction")
	public void UpdateUserAndRestrictionType() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
	
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction,getEnvVariable);
        new JavaScriptHelper(driver).scrollToTop();
        sleepFor(1000);
        adminUser.clickOnUserNavTab("Folder Restrictions");
        sleepFor(2000);
        adminUser.enterFolderRefValue(folder1Ref);

        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();

	}
	
	@Test(enabled = true,priority = 19,dependsOnMethods = "AddFolder1Restriction")
	public void VerifyRestrictionErrorMsgForRestrictedFolderRef() {					//Case 31
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref",folder1Ref);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref","F1-001R1");
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actAccountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		System.out.println("actDocRef "+actAccountRef);	
		AssertionHelper.verifyText(actAccountRef, "F1-001R1");
	}
	
	@Test(enabled = true,priority = 20,dependsOnMethods = "AddFolder1Restriction")
	public void UpdateUserAndRestrictionTypeAndDeleteFolderRestriction() {				//Case 32
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
	
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("User maintenance", "User");
        navigationMenu.searchInFilter(userName);
        sleepFor(1000);
        adminUser.clickOnFilteredUser();
        navigationMenu.clickOnEditIcon();
        sleepFor(2000);
        adminUser.selectSecurityRestrictionType(restriction,getEnvVariable);
        new JavaScriptHelper(driver).scrollToTop();
        sleepFor(1000);
        adminUser.clickOnUserNavTab("Folder Restrictions");
        sleepFor(2000);
        adminUser.deleteRestrictedFolder(folder1Ref);

        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }
        new HomePage(driver).refreshCurrentFileSystem();
	}
	
	@Test(enabled = true,priority = 21,dependsOnMethods = "AddFolder1Restriction")
	public void Folder1RestrictionUnderFolder1AndVerifyAllRefAccessible() {					//Case 32
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		String refName=ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder1Ref);
		adminFolderSection.clickOnSaveInFolderRestriction(refName,getEnvVariable);
		
		//Verify that any of account ref will be accessible
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref","F1-001R1");
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actAccountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		System.out.println("actDocRef "+actAccountRef);	
		AssertionHelper.verifyText(actAccountRef, "F1-001R1");	
	}
	
	@Test(enabled = true,priority = 22,dependsOnMethods = "AddFolder1Restriction")
	public void DeleteFolder1RestrictionAndVerifyThatAllRefAreAccessible() {					//Case 33
		getApplicationUrl();			
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folder1Ref , "Inputing the text "+folder1Ref);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(3000);
		
		//Verify that any of account ref will be accessible
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",folder1Ref);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		String actAccountRef = navigationMenu.getColumnValueFromTable("Account Ref");
		System.out.println("actDocRef "+actAccountRef);	
		AssertionHelper.verifyText(actAccountRef, folder1Ref);
	}
	

}
