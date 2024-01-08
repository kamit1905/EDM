package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminFolderSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;



public class TestFolderRestriction extends TestBase {
	
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
	
	private String folderRef="";
	private String groupName="";
	private List<String> folderRefList;
	
	private String folder1Ref1="";
	private String folder1Ref2="";
	
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
	
	@Test(enabled = true,priority = 2)
	public void AddFoldeRestriction() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		folderRef="TA"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folderRef);
		adminFolderSection.clickOnSaveInFolderRestriction(refName,getEnvVariable);
	}
	
	@Test(enabled = true,priority = 3)
	public void UpdateUserAndAddRestriction() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
		String ref=folderRef;
		
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
	
	@Test(enabled = true,priority = 4)
	public void VerifyErrorMessageAfterFolderRestriction() {
		String accountRef=folderRef;
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref",accountRef);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
	}
	
	@Test(enabled = true,priority = 5)
	public void VerifyErrorMessageWhileCapturing() throws InterruptedException {
		String accountRef=folderRef;
		
		getApplicationUrl();
		navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		new JavaScriptHelper(driver).scrollToBottom();
		
		new CapturePage(driver).enterFolder1RefCapturePageAndSearch(accountRef, "Account Ref");
		sleepFor(2000);
		String getErrorMsg=new CapturePage(driver).getReferenceDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");	
	}
	
	@Test(enabled = true,priority = 6)
	public void UpdateUserAndAddGrantRestrictionToUser() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Grant";
		String ref=folderRef;
		
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
        //adminUser.enterFolderRefValue(ref);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }  
        new HomePage(driver).refreshCurrentFileSystem();
	}
	
	@Test(enabled = true,priority = 7)
	public void CaptureDocumentAndSearchDocument() throws InterruptedException {
		String Ref=folderRef;
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
	}
	
	@Test(enabled = true,priority = 8)
	public void SearchAnotherAccountRefAndVerifyErrorMessage() {
		String accountRef="F1-001R1";
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref",accountRef);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
	}
	
	@Test(enabled = true,priority = 9)
	public void UpdateUserAndChangeRestrictionTypeForUser() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
		String ref=folderRef;
		
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
        sleepFor(1000);
        adminUser.deleteRestrictedFolder(ref);
        sleepFor(1000);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }  
        new HomePage(driver).refreshCurrentFileSystem();
		
	}
	
	@DataProvider(name="intrayData")
    public Object[][] intrayData() throws Exception
    {
        Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","IntrayTools");
        return formData;
    }
	

	
	@Test(enabled = true,priority = 12)
	public void AddFolderRestrictionForGroups() {				//Creating folder ref for groups
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		folder1Ref1="BA"+generateRandomNumber();
		folder1Ref2="VC"+generateRandomNumber();
		String refName=ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder1Ref1);
		adminFolderSection.clickOnSaveInFolderRestriction(refName,getEnvVariable);
		sleepFor(2000);
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterFolderRef(folder1Ref2);
		adminFolderSection.clickOnSaveInFolderRestriction(refName,getEnvVariable);
		
		folderRefList = new ArrayList<String>();
		folderRefList.add(folder1Ref1);
		folderRefList.add(folder1Ref2);
	}
	
	@Test(enabled = true,priority = 13,dependsOnMethods = {"AddFolderRestrictionForGroups"})
	public void AddSecurityRestrictionGroup() {							//Adding folder ref under group
		groupName="GG"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.clickOnAddIcon();
		adminFolderSection.enterGroupName(groupName);
		adminFolderSection.addFolderRefInGroup(folderRefList);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			navigationMenu.clickOnSaveIcon();
			sleepFor(2000);
		}else {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.waitForAddIcon();
		}
		
	}
	
	@Test(enabled = true,priority = 14,dependsOnMethods = {"AddFolderRestrictionForGroups"})
	public void AddSecurityRestrictionTypeAsGrantForGroupTo() {					//Adding security restricton(Grant) & adding group for that user
		String userName=ObjectReader.reader.getUserName();
		String restriction="Grant";
		//String ref=folderRef;
		
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
        sleepFor(1000);
        adminUser.addRestrictedGroupToUser(groupName);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
            navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
        	navigationMenu.waitForAddIcon();
        }
        
        new HomePage(driver).refreshCurrentFileSystem();
	
	}
	
	@Test(enabled = true,priority = 15,dependsOnMethods = {"AddFolderRestrictionForGroups"})
	public void VerifyMessageAfterGivingGrantPermission() throws InterruptedException {				//Searching with another folder ref & verifying error message
		String Ref=folder1Ref1;
		String docRef="AAG_"+generateRandomNumber();
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, Ref,getEnvVariable);
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref",Ref);
		capture.clickOnIntrayListBtn();
		capture.clickOnFirstItemOfIntray();
		
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Account Ref","F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
		
	}
	
	
	@Test(enabled = true,priority = 16,dependsOnMethods = {"AddFolderRestrictionForGroups"})
	public void ChangeRestrictionTypeForUser() {			//Changing restriction type for user to restrict
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
		//String ref=folderRef;
		
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
        //adminUser.addRestrictedGroupToUser(groupName);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
            navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
        	navigationMenu.waitForAddIcon();
        }
        
        new HomePage(driver).refreshCurrentFileSystem();
	}
	
	@Test(enabled = true,priority = 17,dependsOnMethods = {"AddFolderRestrictionForGroups"})
	public void VerifyErrorMessageAfterRestrictingGroup() {					//Search with folder ref which is restricted & verify error message
		//String accountRef="AA7313";
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref",folder1Ref1);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
		
		capture.searchWithCriteria("Account Ref",folder1Ref2);
		new ActionHelper(driver).pressTab();
		sleepFor(2000);

		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
	}
	
	@Test(enabled = true,priority = 18)
	public void DeleteFolderRestriction() {
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		sendKeys(navigationMenu.filterTxt,folderRef , "Inputing the text "+folderRef);
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(4000);
		}
	
	@Test(enabled = true,priority = 19,dependsOnMethods = {"AddFolderRestrictionForGroups"})
	public void DeleteSecurityRestrictionGroup() {
		getApplicationUrl();	
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Security Restrictions Group maintenance", "Folder");
		navigationMenu.waitForFilterTextBox();
		navigationMenu.searchInFilter(groupName);
		
		navigationMenu.clickOnFilteredRow("securityRestrictionGroupDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(4000);
	}
	
	@Test(priority=20,enabled = true, dataProvider = "intrayData")
	public void EditSecurityPermissionOfUser(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
		
		String userName=ObjectReader.reader.getUserName();
		String refName=ObjectReader.reader.getFolder1RefName();
		String restriction="Restrict";
		String ref=folderRef;
		
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
		
		adminUser.checkSecurityRightsForUser("Update Restricted Folder Reference Documents", "Folder", user1, false,getEnvVariable);
        
        capture.searchWithCriteria("Account Ref", "F1-001R1");
        capture.clickOnDocumentListBtn();
        
        navigationMenu.waitForFilterTextBox();
        String getMessage=new CapturePage(driver).firstRowElementXpath.getText();
        System.out.println("getMessage "+getMessage);
        AssertionHelper.verifyText(getMessage, "No items available");
	}
	
	@Test(enabled = true,priority = 21,dataProvider = "intrayData")
	public void DeleteFolderRestriction(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException{
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		adminFolderSection.navigateToAdminAccountSecurity();
		navigationMenu.waitForFilterTextBox();
		//sendKeys(navigationMenu.filterTxt,folderRef , "Inputing the text "+folderRef);
		navigationMenu.searchInFilter("F1-001R1");
		navigationMenu.clickOnFilteredRow("folderSecurityDataTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(4000);
		
		getApplicationUrl();
		adminUser.checkSecurityRightsForUser("Update Restricted Folder Reference Documents", "Folder", user1, true,getEnvVariable);
	}
	
	@Test(enabled=true,priority = 22)
	public void VerifiyRestricted() {
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
        sleepFor(1000);
        adminUser.enterFolderRefValue("F1-001R1");
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
            navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
        	navigationMenu.waitForAddIcon();
        }
        
        new HomePage(driver).refreshCurrentFileSystem();
	}
	
	@Test(enabled=true,priority = 23)
	public void VerifyErrorMessage() {
		String userName=ObjectReader.reader.getUserName();
		String restriction="Restrict";
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.searchWithCriteria("Account Ref","F1-001R1");
		new ActionHelper(driver).pressTab();
		sleepFor(2000);
		
		String getErrorMsg=adminUser.flexibleLookedUpFolderDetails.getText();
		System.out.println("getErrorMsg "+getErrorMsg);
		AssertionHelper.verifyTextContains(getErrorMsg, "Access to the specified entity is restricted.");
		
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
        sleepFor(1000);
        adminUser.deleteRestrictedFolder("F1-001R1");
        sleepFor(1000);
        if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	navigationMenu.clickOnSaveIcon();
        	navigationMenu.waitForAddIcon();
        }else {
        	navigationMenu.clickOnSaveIconForUser();
            navigationMenu.waitForAddIcon();
        }  
        new HomePage(driver).refreshCurrentFileSystem();
	}
	
}


//Update Restricted Folder Reference Documents