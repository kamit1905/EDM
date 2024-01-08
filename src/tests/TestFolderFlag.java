package tests;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.FolderFlagReference;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import utils.ExtentReports.ExtentTestManager;

public class TestFolderFlag extends TestBase {
	
	public FolderFlagReference folderPage ;
	private Logger log = LoggerHelper.getLogger(TestFolderFlag.class);
	public NavigationMenu navigationMenu;
	public ExcelReader xls;
	public String protMarker;
	public String folder1Ref;
	public String folder2Ref;
	public String folder3Ref;
	
	private String folder1Prefix;
	private String folder2Prefix;
	private String folder3Prefix;
	
	private String folder1AutoPrefix;
	private String folder2AutoPrefix;
	private String folder3AutoPrefix;
	
	@BeforeClass
	public void setupClass()  {
		folderPage=new FolderFlagReference(driver);
		navigationMenu = new NavigationMenu(driver);
		xls = new ExcelReader();

	}

	@DataProvider(name="folderRefData")
	public Object[][] folderReferenceData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","FolderRefData");
		return formData;
	}
	
	@Test(priority = 1,enabled = true,dataProvider="folderRefData")
	public void TestCreateFolder1Ref(Map<String, String> map) throws InterruptedException {
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		folder1Ref=map.get("AddFolder1Ref")+generateRandomNumber();
		getApplicationUrl();
		folderPage.navigateToAddNewEntityPage(fold1entityName);
		folderPage.enterFolder1Ref(folder1Ref);
		new ActionHelper(driver).pressTab();
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold1entityName);
		String entity1Value = folderPage.searchEntityAndGetValue("Account", folder1Ref);
		AssertionHelper.verifyText(entity1Value, folder1Ref);
	}

	//Depends on testCreateFolder1Ref but don't add depnds on flag since create folder ref failing
	@Test(priority = 2,enabled = false)
	public void TestFolderRefDeletion() throws InterruptedException {     
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		folder1Ref = this.folder1Ref;
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold1entityName);
		String deletePopupMsg = folderPage.searchEntityAndClickOnDelete(fold1entityName, folder1Ref);
		AssertionHelper.verifyTextContains(deletePopupMsg, "Are you sure you want to delete");
	}

	@Test(priority = 3,enabled = true,dataProvider="folderRefData")
	public void TestCreateFolder2Ref(Map<String, String> map) throws InterruptedException {     
		String fold2entityName = ObjectReader.reader.getFolder2RefName();
				folder2Ref=map.get("AddFolder2Ref")+generateRandomNumber();
		getApplicationUrl();
		folderPage.navigateToAddNewEntityPage(fold2entityName);
		folderPage.enterFolder2Ref(folder2Ref);
		new ActionHelper(driver).pressTab();
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold2entityName);
		String entity2Value = folderPage.searchEntityAndGetValue("Misc", folder2Ref);
		AssertionHelper.verifyText(entity2Value, folder2Ref);

	}

	@Test(priority = 4,enabled = true,dataProvider="folderRefData")
	public void TestCreateFolder3Ref(Map<String, String> map) throws InterruptedException {     
		String fold3entityName = ObjectReader.reader.getFolder3RefName();
				folder3Ref=map.get("AddFolder3Ref")+generateRandomNumber();
		getApplicationUrl();
		folderPage.navigateToAddNewEntityPage(fold3entityName);
		folderPage.enterFolder3Ref(folder3Ref);
		new ActionHelper(driver).pressTab();
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold3entityName);
		String entity3Value = folderPage.searchEntityAndGetValue("Property", folder3Ref);
		AssertionHelper.verifyText(entity3Value, folder3Ref);
	}
	
	@Test(priority = 5, enabled = true)
	public void TestAddDummyReferenceAll() throws InterruptedException { 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		folder1Prefix="F1"+generateRandomNumber(); String folder1Name="Folder1";
		folder2Prefix="F2"+generateRandomNumber(); String folder2Name="Folder2";
		folder3Prefix="F3"+generateRandomNumber(); String folder3Name="Folder3";
		getApplicationUrl();
		folderPage.navigateToAddDummyReference();
		//folderPage.selectAllAndDelete();
		folderPage.addDummyReference(folder1Prefix,"3","Folder1",false,getEnvVariable);
		folderPage.addDummyReference(folder2Prefix,"3","Folder2",false,getEnvVariable);
		folderPage.addDummyReference(folder3Prefix,"3","Folder3",false,getEnvVariable); 
		boolean folder1Presence = folderPage.verifyDummyRefAdded(folder1Prefix,folder1Name);
		boolean folder2Presence = folderPage.verifyDummyRefAdded(folder2Prefix,folder2Name);
		boolean folder3Presence = folderPage.verifyDummyRefAdded(folder3Prefix,folder3Name);
		AssertionHelper.verifyTrue(folder1Presence, "Checking for folder1 presence"); 
		AssertionHelper.verifyTrue(folder2Presence, "Checking for folder2 presence"); 
		AssertionHelper.verifyTrue(folder3Presence, "Checking for folder3 presence"); 
	}

	@Test(priority = 6, enabled = true ,dependsOnMethods = {"TestAddDummyReferenceAll"} )
	public void TestCaptureDummyReferenceAll() throws InterruptedException { 
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		String fold2entityName = ObjectReader.reader.getFolder2RefName();
		String fold3entityName = ObjectReader.reader.getFolder3RefName();
		getApplicationUrl(); 
		String fold1DummyRefValue = folderPage.enterAndSelectDummyRef(fold1entityName);
		log.info("folder 1 dummy ref value is "+fold1DummyRefValue);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		getApplicationUrl(); 
		String fold2DummyRefValue =  folderPage.enterAndSelectDummyRef(fold2entityName);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();
		getApplicationUrl(); 
		String fold3DummyRefValue = folderPage.enterAndSelectDummyRef(fold3entityName);
		folderPage.clickOnSaveIconWaitForItsInvisiblity();       
	}

	@DataProvider(name="captureFormData")
	public Object[][] captureFormData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","CaptureFormData");
		return formData;
	}       

	//pass
	@Test(priority = 7,enabled =true, dataProvider="captureFormData")
	public void TestAddProtectiveMarker(Map<String, String> map) {
		this.protMarker = map.get("ProtectiveMarker")+generateRandomNumber();    
		getApplicationUrl();
		folderPage.navigteToProtectiveMarkerPage();

		try {
			String actualMarker = new AdminDocumentSection(driver).addProtectiveMarker(protMarker);
			AssertionHelper.verifyText(actualMarker,protMarker);
		}
		catch(Exception ex){
			String actMessage = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			AssertionHelper.verifyTextContains(actMessage, "already exists");
		}
	}

	//,dependsOnMethods = {"TestAddProtectiveMarker"}
	@Test(priority = 8,enabled = true, dataProvider = "captureFormData")
	public void TestCaptureFormFillingWithProtectiveMarker(Map<String, String> map ) throws IOException, InterruptedException { 
		test = ExtentTestManager.getTest();
		getApplicationUrl();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		SoftAssert soft = new SoftAssert();
		test.log(Status.INFO, "Running test with arguments : "+map.toString());
		CapturePage capture=new CapturePage(driver);
		capture = navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		AssertionHelper.verifyTrue(capture.isIndexDocumentButtonPresent(),"Assertion to check if index doc button present");
		capture.clickOnFileUploadIcon();
		String filename = "file1.tif";
		String ActualFileName = capture.uploadFileAngGetFileName(ObjectReader.reader.getTestDocFolderPath(),filename),
				routingType="To User",
				routingTo=ObjectReader.reader.getUserName(),

				protectiveMarker= this.protMarker == null? map.get("ProtectiveMarker"):this.protMarker,
						docRef= "FileProtectiveMark"+generateRandomNumber();
						;

				AssertionHelper.verifyText(ActualFileName, filename);         
				capture.selectRoutingTypeDD(routingType,getEnvVariable);
				capture.selectRouteToDD(routingTo,getEnvVariable);


				capture.selectProtectiveMarkerDD(protectiveMarker,getEnvVariable); 
				capture.enterDocRef(docRef);
				capture.clickOnIndexDocumentWaitForIndexPopup();
				String envVariable = System.getProperty("propertyName");
				if(envVariable.equals("Enterprise_R530")||envVariable.equals("Enterprise_R522") || envVariable.equals("Enterprise_Sp1s")) {
					new WindowHelper(driver).waitForPopup("Index");
					String message = capture.getSuccessfullPopupMessage();
					AssertionHelper.verifyTrue(message.contains("indexed successfully."),"Message does not match");
					capture.clickOkOnSuccesfullPopup();
				}
				else
					navigationMenu.waitTillInvisiblityOfBusyIcon(20);
				test.log(Status.INFO, "Document captured successffully");
				
				sleepFor(2000);
				getApplicationUrl();
				navigationMenu.clickOnHomePageIcon();
				String actualProtMark = folderPage.searchDocumentGetColumnValue(docRef,"Protective Marker Id_Protective Marker");
				AssertionHelper.verifyText(actualProtMark, protectiveMarker);
	}



	@Test(priority = 9 ,enabled =true, dataProvider="folderRefData")
	public void TestFolderFlagWithCharacter(Map<String, String> map) throws InterruptedException {
		String flagId = map.get("flagId")+generateRandomNumber(), folder1Entity = ObjectReader.reader.getFolder1RefName(),
				flagDescription = map.get("flagDescription"),folderFlagDocRef="FolderFlagDoc"+generateRandomNumber(),
				flagCharName=map.get("CharName");//, folder1Ref = map.get("AddFolder1Ref")+"%";    
		
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		folderPage.navigteToFolderFlagPage();
		folderPage.addFolderFlagWithCharacters(flagId, flagDescription, flagCharName);
		getApplicationUrl();
		String folder1RefActual = folderPage.associateFlagWithFolder1Ref(folder1Entity,folder1Ref,flagId,getEnvVariable);
		folderPage.captureDocWithFlaggedFolder1Ref(folder1RefActual,folderFlagDocRef,getEnvVariable);
		String actFlagValue = folderPage.searchDocumentGetColumnValue(folderFlagDocRef, "Flag ID");
		AssertionHelper.verifyText(actFlagValue, flagCharName);
	}


	//Flag image should be added to make this test successfull
	@Test(enabled =false, dataProvider="folderRefData")
	public void TestFolderFlagWithImage(Map<String, String> map) throws InterruptedException {
		String flagId = map.get("flagImageId")+generateRandomNumber(), folder1Entity = ObjectReader.reader.getFolder1RefName(),
				flagDescription = map.get("flagImageDesc"),folderFlagDocRef="FolderFlagImage",
				folder1Ref = map.get("AddFolder1Ref"),flagImageName = map.get("flagImageName");    
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		folderPage.navigteToFolderFlagPage();
		folderPage.addFolderFlagWithImage(flagId, flagDescription,flagImageName);
		folderPage.associateFlagWithFolder1Ref(folder1Entity,folder1Ref,flagId,getEnvVariable);
		folderPage.captureDocWithFlaggedFolder1Ref(folder1Ref,folderFlagDocRef,getEnvVariable);
		boolean isFlagPresent =folderPage.searchDocumentCheckImagePresent(folderFlagDocRef); 
		AssertionHelper.verifyTrue(isFlagPresent,"Checking if flag image present");
	}

	@Test(priority = 10,enabled =true, dataProvider="captureFormData")
	public void TestProtectiveMarkerDeletion(Map<String, String> map) {
		String protMarker =  map.get("ProtectiveMarker")+generateRandomNumber();
		getApplicationUrl();
		folderPage.navigteToProtectiveMarkerPage();
		String actualMarker = new AdminDocumentSection(driver).addProtectiveMarker(protMarker);
		AssertionHelper.verifyText(actualMarker,protMarker);
		getApplicationUrl();
		folderPage.navigateToProtectiveMarker();
		folderPage.searchMarkerAndDelete(protMarker);
	}
	
	@Test(priority = 11,enabled =true)
	public void TestDeleteDummyReference() {
		getApplicationUrl();
		folderPage.navigateToAddDummyReference();
		
		folderPage.SearchDummyFolderRefAndDelete(folder1Prefix);
		sleepFor(2000);
		
		folderPage.SearchDummyFolderRefAndDelete(folder2Prefix);
		sleepFor(2000);
		
		folderPage.SearchDummyFolderRefAndDelete(folder3Prefix);
		sleepFor(2000);
	}
	
	@Test(priority = 12,enabled =true)
	public void EditFolder1EntityAndVerifyValues() {
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		String surName = "Su_"+generateRandomNumber();
		String foreName = "Fo_"+generateRandomNumber();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold1entityName);
		home.searchAndNavigateToEditEnity("Account", folder1Ref);
		folderPage.EditValuesOfFolderReference(surName, foreName);
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold1entityName);
		String entity1Value = folderPage.searchEntityAndGetOtherValue("Account", folder1Ref);
		AssertionHelper.verifyText(entity1Value, surName);
	}
	
	@Test(priority = 13,enabled =true)
	public void EditFolder2EntityAndVerifyValues() {
		String fold2entityName = ObjectReader.reader.getFolder2RefName();
		String surName = "Su_"+generateRandomNumber();
		String foreName = "Fo_"+generateRandomNumber();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold2entityName);
		home.searchAndNavigateToEditEnity("Misc", folder2Ref);
		folderPage.EditValuesOfFolderReference(surName, foreName);
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold2entityName);
		String entity1Value = folderPage.searchEntityAndGetOtherValue("Misc", folder2Ref);
		AssertionHelper.verifyText(entity1Value, surName);
	}
	
	@Test(priority = 14,enabled =true)
	public void EditFolder3EntityAndVerifyValues() {
		String fold3entityName = ObjectReader.reader.getFolder3RefName();
		String surName = "Su_"+generateRandomNumber();
		String foreName = "Fo_"+generateRandomNumber();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold3entityName);
		home.searchAndNavigateToEditEnity("Property", folder3Ref);
		folderPage.EditValuesOfFolderReference(surName, foreName);
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold3entityName);
		String entity1Value = folderPage.searchEntityAndGetOtherValue("Property", folder3Ref);
		AssertionHelper.verifyText(entity1Value, surName);
	}
	
	@Test(priority = 15,enabled =true)
	public void DeleteFolder1Entity() {
		String fold1entityName = ObjectReader.reader.getFolder1RefName();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold1entityName);
		home.searchAndNavigateToEditEnity("Account", folder1Ref);
		String deletePopupMsg = folderPage.ClickOnDeleteRefAndGetPopMessage();
		AssertionHelper.verifyTextContains(deletePopupMsg, "Are you sure you want to delete");
	}
	
	@Test(priority = 16,enabled =true)
	public void DeleteFolder2Entity() {
		String fold2entityName = ObjectReader.reader.getFolder2RefName();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold2entityName);
		home.searchAndNavigateToEditEnity("Misc", folder2Ref);
		String deletePopupMsg = folderPage.ClickOnDeleteRefAndGetPopMessage();
		AssertionHelper.verifyTextContains(deletePopupMsg, "Are you sure you want to delete");
	}
	
	@Test(priority = 17,enabled =true)
	public void DeleteFolder3Entity() {
		String fold3entityName = ObjectReader.reader.getFolder3RefName();
		
		getApplicationUrl();
		home.clickOnSearchEntityAndSelect(fold3entityName);
		home.searchAndNavigateToEditEnity("Property", folder3Ref);
		String deletePopupMsg = folderPage.ClickOnDeleteRefAndGetPopMessage();
		AssertionHelper.verifyTextContains(deletePopupMsg, "Are you sure you want to delete");		
	}
	
	@Test(priority = 18, enabled = true)
	public void TestAddDummyReferenceWithAutoincrement() throws InterruptedException { 
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		test.log(Status.INFO ,"======="+getEnvVariable+"==========");

		folder1AutoPrefix="F1"+generateRandomNumber(); String folder1Name="Folder1";
		folder2AutoPrefix="F2"+generateRandomNumber(); String folder2Name="Folder2";
		folder3AutoPrefix="F3"+generateRandomNumber(); String folder3Name="Folder3";
		getApplicationUrl();
		folderPage.navigateToAddDummyReference();
		//folderPage.selectAllAndDelete();
		folderPage.addDummyReference(folder1AutoPrefix,"3","Folder1",true,getEnvVariable);
		folderPage.addDummyReference(folder2AutoPrefix,"3","Folder2",true,getEnvVariable);
		folderPage.addDummyReference(folder3AutoPrefix,"3","Folder3",true,getEnvVariable); 
		boolean folder1Presence = folderPage.verifyDummyRefAdded(folder1AutoPrefix,folder1Name);
		boolean folder2Presence = folderPage.verifyDummyRefAdded(folder2AutoPrefix,folder2Name);
		boolean folder3Presence = folderPage.verifyDummyRefAdded(folder3AutoPrefix,folder3Name);
		AssertionHelper.verifyTrue(folder1Presence, "Checking for folder1 presence"); 
		AssertionHelper.verifyTrue(folder2Presence, "Checking for folder2 presence"); 
		AssertionHelper.verifyTrue(folder3Presence, "Checking for folder3 presence"); 
	}
	
	@Test(priority = 19,enabled =true)
	public void TestDeleteDummyReferenceWithAutoIncrement() {
		getApplicationUrl();
		folderPage.navigateToAddDummyReference();
		
		folderPage.SearchDummyFolderRefAndDelete(folder1AutoPrefix);
		sleepFor(2000);
		
		folderPage.SearchDummyFolderRefAndDelete(folder2AutoPrefix);
		sleepFor(2000);
		
		folderPage.SearchDummyFolderRefAndDelete(folder3AutoPrefix);
		sleepFor(2000);
	}

}
