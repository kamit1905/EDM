package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
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
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;
import utils.ExtentReports.ExtentTestManager;

public class TestUserSecurityAccess extends TestBase {
	public FileSystemSection adminFS ;
	public AdminDocumentSection adminDocument;
	public AdminUserSection adminUser;
	public DocumentToolsPage docTools;
	public IntrayToolsPage intrayTools;
	public CapturePage capture ;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public NavigationMenu navigationMenu;
	public DropdownHelper dropdownHelper;
	public VerificationHelper verificationHelper;
	public WindowHelper windowHelper;
	public LoginPage login;
	public static String teamName;
	public static String roleName;
	public AdminDocumentSection adminDoc;
	private Logger log = LoggerHelper.getLogger(TestUserSecurityAccess.class);
	
	
	@BeforeClass
	public void setupClass()  {
		adminFS = new FileSystemSection(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu=new NavigationMenu(driver);
		xls = new ExcelReader();
		windowHelper=  new WindowHelper(driver);
		adminDocument= new AdminDocumentSection(driver);
		login = new LoginPage(driver);
		adminUser= new AdminUserSection(driver);
		docTools = new DocumentToolsPage(driver);
		intrayTools = new IntrayToolsPage(driver);
		verificationHelper = new VerificationHelper(driver);
		adminDoc = new AdminDocumentSection(driver);
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
	
	   
    @DataProvider(name="captureFormData")
    public Object[][] captureFormData() throws Exception
    {
        Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
        return formData;
    }
    
    @DataProvider(name="intrayData")
    public Object[][] intrayData() throws Exception
    {
        Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","IntrayTools");
        return formData;
    }
    
    @DataProvider(name="docreReferenceData")
    public Object[][] docreReferenceData() throws Exception
    {
        Object[][] formData = xls.readExcel(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","ReReferenceData");   
        return formData;
    }
	
	@Test(enabled = true)
	public void TestVerifyBrowseFileSystem() throws InterruptedException {
			test = ExtentTestManager.getTest();
			getApplicationUrl();
			AssertionHelper.verifyTrue(home.isBrowserFileSystemOptionShown(),"Checking for browse file system"); 
			}
	
	//Pre - Enter user1 and user2 in EDRM.xlsx file with respect to current logined user  489(Done)
    @Test(priority=3,enabled = true, dataProvider = "intrayData")
    public void EditUserSecurityLevel(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
         
    	String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
    	
    	 getApplicationUrl();
         navigationMenu.clickOnTab("Administration");
         navigationMenu.clickOnIcon("User maintenance", "User");
         navigationMenu.searchInFilter(user1);
         Thread.sleep(1000);
         adminUser.clickOnFilteredUser();
         navigationMenu.clickOnEditIcon();
         adminUser.enterSecurityLevel(String.valueOf(10));
         sleepFor(2000);
         if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	 navigationMenu.clickOnSaveIcon();
         }else {
        	 navigationMenu.clickOnSaveIconForUser(); 
         }
         
         //Update User2 security level
         //waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 2);
         waitHelper.waitForElement(navigationMenu.addIconBtn, 20);
         navigationMenu.searchInFilter(user2);
         Thread.sleep(1000);
         adminUser.clickOnFilteredUser();
         navigationMenu.clickOnEditIcon();
         sleepFor(2000);
         adminUser.enterSecurityLevel(String.valueOf(5));
         
         if(getEnvVariable.equals("Enterprise_Sp1s")) {
        	 navigationMenu.clickOnSaveIcon();
         }else {
        	 navigationMenu.clickOnSaveIconForUser(); 
         }
         //navigationMenu.clickOnSaveIconForUser();
         
         sleepFor(3000);
         home.refreshCurrentFileSystem();
    }
    
    @Test(priority=4, enabled = true)			//true
    public void UpdateDocumentTypeSecurity() throws InterruptedException {
    	getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		 //navigationMenu.clickOnSpecificRow("last()");
		  navigationMenu.searchInFilter("Default");
		 Thread.sleep(2000);
		 navigationMenu.clickOnFilteredRow("documentTypesTable");
		 navigationMenu.clickOnEditIcon();
		 adminUser.enterSecurityLevel(String.valueOf(9));
		 navigationMenu.clickOnSaveIcon();
	}
     
        @Test(priority=5, enabled = true, dataProvider = "captureFormData")			//true
         public void TestCaptureFormFilling(Map<String, String> map ) throws IOException, InterruptedException { 
              test = ExtentTestManager.getTest();
              
      		String getEnvVariable = System.getProperty("propertyName");
    		System.out.println("getEnvVariable===================="+getEnvVariable);

              getApplicationUrl();
              test.log(Status.INFO, "Running test with arguments : "+map.toString());
              capture = navigationMenu.clickOnCaptureTab();
              capture.clickOnDocumentCaptureBtn();
              AssertionHelper.verifyTrue(capture.isIndexDocumentButtonPresent(),"Assertion to check if index doc button present");
              capture.clickOnFileUploadIcon();
              String filename = map.get("FileName");
              String ActualFileName = capture.uploadFileAngGetFileName(map.get("FilePath"),filename),
                      accountRefValue = map.get("AccountRefValue"),
                      routingType=map.get("RoutingType"),
                      routingTo=map.get("routingTo"),
                      documentType=map.get("DocumentType"),
                      protectiveMarker=map.get("ProtectiveMarker"),
                      docRef= map.get("DocRef")+"Escalate"
                      ;
              
              AssertionHelper.verifyText(ActualFileName, filename);
              //Assert.assertTrue(condition);
              
              capture.selectRoutingTypeDD(routingType,getEnvVariable);
              capture.selectRouteToDD(routingTo,getEnvVariable);
            
              capture.selectDocumentTypemDD(documentType,getEnvVariable);
              //capture.selectProtectiveMarkerDD(protectiveMarker); Protective marker not created
              capture.enterDocRef(docRef);
              capture.clickOnIndexDocument();
              try {
      			waitHelper.waitForElement(new CapturePage(driver).successullyIndexMsg,10);
      			new CapturePage(driver).clickOkOnSuccesfullPopup();
      			navigationMenu.clickOnHomePageIcon();
      		} catch (Exception e) {
      			navigationMenu.clickOnHomePageIcon();
      		}
        }
        
        /*
         * Pre -Document type i.e default should have security level of 9
         */
        @Test(priority=6,enabled = true, dataProvider = "intrayData")       //489  true
        public void VerifyErrorInDistribute(String user1,String user2, String team1, String team2, String fileSystem) {
             String docref="captureFormNovEscalate";
             
       		String getEnvVariable = System.getProperty("propertyName");
     		System.out.println("getEnvVariable===================="+getEnvVariable);

             getApplicationUrl();
             capture.searchWithCriteria("Doc Ref", docref);
             capture.clickOnIntrayListBtn();
             navigationMenu.waitForFilterTextBox();
             navigationMenu.clickOnSpecificRow(getEnvVariable,"1");
             intrayTools.clickOnDistributeIcon();
             intrayTools.enterAndSelectPillsInEscalate(user2);
             String getMsg=intrayTools.getErrorMessageInDistribute();
             System.out.println("getMSG "+getMsg);
             sleepFor(1000);     //Re
             AssertionHelper.verifyTextContains(getMsg, "User "+user2.substring(0,1).toUpperCase()+user2.substring(1).toLowerCase()+" does not have sufficient security level to access Document");
             intrayTools.clickOnModalCanelButtonWithoutChanges();
        }
        
        @Test(priority=7, enabled = true)			//true
        public void UpdateDocumentTypeSecurityToDefault() throws InterruptedException {
        	getApplicationUrl();
			adminDoc.navigateToAdminDocumentTypesActive();
			 //navigationMenu.clickOnSpecificRow("last()");
			  navigationMenu.searchInFilter("Default");
			 Thread.sleep(2000);
			 navigationMenu.clickOnFilteredRow("documentTypesTable");
			 navigationMenu.clickOnEditIcon();
			 adminUser.enterSecurityLevel(String.valueOf(0));
			 navigationMenu.clickOnSaveIcon();
		}
        
        @Test(priority=8,enabled = true, dataProvider = "intrayData")
        public void EditUserSecurityLevelOnceAgain(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
        	
        	String getEnvVariable = System.getProperty("propertyName");
    		System.out.println("getEnvVariable===================="+getEnvVariable);
    		
        	 getApplicationUrl();
             navigationMenu.clickOnTab("Administration");
             navigationMenu.clickOnIcon("User maintenance", "User");
             navigationMenu.searchInFilter(user1);
             Thread.sleep(1000);
             adminUser.clickOnFilteredUser();
             navigationMenu.clickOnEditIcon();
             sleepFor(2000);
             adminUser.enterSecurityLevel(String.valueOf(9));
             
             if(getEnvVariable.equals("Enterprise_Sp1s")) {
            	 navigationMenu.clickOnSaveIcon();
             }else {
            	 navigationMenu.clickOnSaveIconForUser(); 
             }
             
             //Update User2 security level
             waitHelper.waitForElement(navigationMenu.addIconBtn, 20);
             navigationMenu.searchInFilter(user2);
             sleepFor(1000);
             adminUser.clickOnFilteredUser();
             navigationMenu.clickOnEditIcon();
             sleepFor(1000);
             adminUser.enterSecurityLevel(String.valueOf(9));
             
             if(getEnvVariable.equals("Enterprise_Sp1s")) {
            	 navigationMenu.clickOnSaveIcon();
             }else {
            	 navigationMenu.clickOnSaveIconForUser(); 
             }
          
             sleepFor(3000);
             home.refreshCurrentFileSystem();
        }
    
    
    /*
     *
     * Verify visibility of Version Control Icon based on user permission
     */
    @Test(priority=10,enabled = true, dataProvider = "intrayData")               //500
    public void VerifyCheckInRight(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
        test = ExtentTestManager.getTest();
        
        String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
        getApplicationUrl();
        sleepFor(2000);
        adminUser.checkSecurityRightsForUser("Edit Documents (CheckIn and CheckOut)", "Document", user1, true,getEnvVariable);
        String docref="intrayLockUnlock";						
        capture.searchWithCriteria("Doc Ref", docref);
        capture.clickOnIntrayListBtn();
        capture.clickOnFirstItemOfIntray();
        intrayTools.verifyVisibilityOfVersionControlIcon();     //If any error is there then its visible in report
    }
    
    
    /*
     * Verify that Next options is not available when logged in user do not have 
     * 'Take Work from a Shared In-Tray' security permission  695
     */
    @Test(priority=11,enabled = true, dataProvider = "intrayData")
    public void VerifyNextEmailButton(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
        test = ExtentTestManager.getTest();
        
        String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
        getApplicationUrl();
        sleepFor(2000);
        adminUser.checkSecurityRightsForUser("Take Work from a Shared In-Tray", "Intray Processing", user1, false,getEnvVariable);         //for false added uncheck logic
        String docref="intrayLockUnlock";			//rendition to intrayLockUnlock
        capture.searchWithCriteria("Doc Ref", docref);
        capture.clickOnIntrayListBtn();
        capture.clickOnFirstItemOfIntray();
        AssertionHelper.verifyFalse(verificationHelper.isElementDisplayedByEle(intrayTools.nextMailButton));
        
        getApplicationUrl();
        sleepFor(2000);
        adminUser.checkSecurityRightsForUser("Take Work from a Shared In-Tray", "Intray Processing", user1, true,getEnvVariable);
            
    }
    
    /*
     * - Verify if user having Distribution right then in that case he/she will able to distribute the document
     * - If user is not having Distribution right then verify the error  
     */
    @Test(priority=390,enabled = true,dataProvider = "intrayData")
	public void TestDistributionSecurityRight(String user1,String user2, String team1, String team2, String fileSystem) throws InterruptedException {
    	String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);	
    	
    		getApplicationUrl();
			sleepFor(2000);
			adminUser.checkSecurityRightsForUser("Distribute Work to Other Users In-Trays", "Intray Processing", user1,false,getEnvVariable);
			home.clickOnMetroTile("Outstanding items for today.");
			waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			intrayTools.clickOnFirstItemInList();
			navigationMenu.clickOnIcon("Distribute intray item", "Mail");
			sleepFor(1000);
			//intrayTools.selectFromDistributionTypeDistribute("Escalate");
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			intrayTools.enterNotesPillsInEscalate("Notes added in distribution");
			sleepFor(1000);
			intrayTools.clickOnModalApplyChangesButton();
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				windowHelper.waitForPopup("Mail Status");
			}else {
				windowHelper.waitForPopup("Distribute intray item Failed");
			}
			String actualEscMsg = windowHelper.getPopupMessage();
			String expectedEscMsg = "The user does not have permission to distribute an intray item under escalate and multiple referral distribution"; 
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyText(actualEscMsg, expectedEscMsg);
			intrayTools.removeEscalatePillboxText();
			intrayTools.selectFromDistributionTypeDistribute("Multiple Referral",getEnvVariable);
			intrayTools.enterAndSelectPillsInEscalate(ObjectReader.reader.getUserName());
			intrayTools.enterNotesPillsInEscalate("Notes added in distribution");
			sleepFor(1000);
			intrayTools.clickOnModalApplyChangesButton();
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				windowHelper.waitForPopup("Mail Status");
			}else {
				windowHelper.waitForPopup("Distribute intray item Failed");
			}
			//windowHelper.waitForPopup("Distribute intray item Failed");
			String actualMultiMsg = windowHelper.getPopupMessage();
			String expectedMultiMsg = "The user does not have permission to distribute an intray item under escalate and multiple referral distribution"; 
			windowHelper.clickOkOnPopup();
			AssertionHelper.verifyText(actualMultiMsg, expectedMultiMsg);
			new ActionHelper(driver).pressEscape();
			//home.clickOnChangeUser();
			//login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			getApplicationUrl();
			sleepFor(2000);
			adminUser.checkSecurityRightsForUser("Distribute Work to Other Users In-Trays", "Intray Processing", user1,true,getEnvVariable);
	}
    
    /*
     *  Verify visibility of Linking btn if user is not having Allow Document Linking
     */
	@Test(priority=214,enabled =true)
	public void TestLinkingAvailableSecurityUnchecked()throws InterruptedException  {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
		
		String accRef = "F1-001R3";
		 getApplicationUrl();
		 sleepFor(2000);
		 test = ExtentTestManager.getTest();
		 adminUser.checkSecurityRightsForUser("Allow Document Linking", "Document", ObjectReader.reader.getUserName(), false,getEnvVariable);
		 capture.searchWithCriteria("Account Ref", "F1%");
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 AssertionHelper.verifyFalse(new VerificationHelper(driver).isElementDisplayedByEle(intrayTools.linkingDocumentBtn));
	}
    
	@Test(priority=215,enabled =true)
	public void TestLinkingAvailableSecurityChecked()throws InterruptedException  {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable); 
		
		String accRef = "F1-001R3";
		 getApplicationUrl();
		 sleepFor(2000);
		 test = ExtentTestManager.getTest();
		 adminUser.checkSecurityRightsForUser("Allow Document Linking", "Document", ObjectReader.reader.getUserName(), true,getEnvVariable);
		 capture.searchWithCriteria("Account Ref", "F1%");
		 capture.clickOnDocumentListBtn();
		 capture.clickOnFirstItemOfIntray();
		 AssertionHelper.verifyTrue(navigationMenu.isIconEnabled("Link one or more documents", "Document"),
				 "Verifying if linking icon is enabled");
	}	
	

}
