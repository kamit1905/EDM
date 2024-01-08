package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import utils.ExtentReports.ExtentTestManager;

public class TestAdminDocumentSection extends TestBase {
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
	
	private String textAreaDesctiption="";

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


	@Test(priority=101,enabled = true,dataProvider = "addDocumentType")
	public void TestAddDocumentType(Map<String, String> map) throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		String typeId = map.get("typeId");
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		adminDoc.enterTypeId(typeId);
		adminDoc.enterDescription("timepass");
		adminDoc.selectMailOption("Mail",getEnvVariable);
		adminDoc.selectMediaType("No Specified Media Type",getEnvVariable);
		adminDoc.checkEditable();
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			try {
				new JavaScriptHelper(driver).scrollToTop();
				new JavaScriptHelper(driver).clickElement(adminDoc.publicAccessTab);
				//click(adminDoc.publicAccessTab, "Clicked on public access tab");
				sleepFor(1000);
				adminDoc.selectPublicationMode("Automatic");
				sendKeys(adminDoc.publicationPriorityInput, "1", "Entering text into textbox");

				sleepFor(1000);
				adminDoc.selectPublicationMode("Never");
				sleepFor(1000);
				
				//navigationMenu.clickOnSaveIcon();
				navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");

				navigationMenu.waitForAddIcon();
				navigationMenu.searchInFilter(typeId);
				Thread.sleep(2000);
				String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
				AssertionHelper.verifyText(filteredText, typeId);
			}
			catch(Exception ex){
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "already exists");
			}
		}else {
			try {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
				navigationMenu.searchInFilter(typeId);
				Thread.sleep(2000);
				String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
				AssertionHelper.verifyText(filteredText, typeId);
			}
			catch(Exception ex){
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "already exists");
			} 
		}
	}

	@DataProvider(name="addDocumentType")
	public Object[][] addDocumentType() throws Exception
	{
		Object[][] formData = xls.readExcelToMap( ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","DocumentTypeData");

		return formData;
	}

	@Test(priority=102,enabled = true,dataProvider = "addDocumentType")
	public void TestUpdateDocumentType(Map<String, String> map ) throws InterruptedException {
		String typeId = map.get("typeId"),
				description=map.get("typeDescription");
		String updatedDesc=description.concat("ab");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		new AlertHelper(driver).acceptAlertIfPresent();
		adminDoc.navigateToAdminDocumentTypesActive();
		//navigationMenu.clickOnSpecificRow("last()");
		navigationMenu.searchInFilter(typeId);
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		adminDoc.enterDescription(updatedDesc);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		refreshPage();
		String actualTableDescription = adminDoc.getFilteredRowDescriptionFromDocumentTable();
		navigationMenu.searchInFilter(typeId);
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		//navigationMenu.waitForIcon("Save Changes");

		String actualDescription = adminDoc.getDescriptionText();
		AssertionHelper.verifyText(actualTableDescription, updatedDesc);
		AssertionHelper.verifyText(actualDescription, updatedDesc);

		//Once again updated with originalavalue  added by amit
		adminDoc.enterDescription(description);
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");
	}

	@Test(priority=103,enabled = true,dataProvider = "addDocumentType")
	public void TestMakeDocumentTypeInactive(Map<String, String> map) throws InterruptedException {
		String typeId = map.get("typeId"),
				description=map.get("typeDescription");
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.searchInFilter(typeId);
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Save changes");
		adminDoc.checkMakeInactive();
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(typeId);
		Thread.sleep(2000);
		String noRecordMsg = navigationMenu.getNoRecordsFoundMessage("documentTypesTable");
		AssertionHelper.verifyText(noRecordMsg, "No matching records found");			//error message changed by amit	
	}


	@Test(priority=104,enabled = true,dataProvider = "addDocumentType")
	public void TestMoveDocumentTypeFromInactiveToActive(Map<String, String> map) throws InterruptedException {
		String typeId = map.get("typeId"),
				description=map.get("typeDescription"),
				expNoRecordMessage = ObjectReader.reader.getNoRecordMessage();
		getApplicationUrl();
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Select Document Type category to admin", "Document");
		navigationMenu.clickOnIconMenu("Inactive document types");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(typeId);
		Thread.sleep(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Save changes");
		adminDoc.checkMakeInactive();
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(typeId);
		Thread.sleep(2000);
		String noRecordMsg = navigationMenu.getNoRecordsFoundMessage("documentTypesTable");
		AssertionHelper.verifyText(noRecordMsg, "No items available");				//error message changed by amit
	}

	@Test(priority=105,enabled = true)
	public void TestActiveInactiveDocTypeInDropdown() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.waitForFilterTextBox();
		sleepFor(1000);
		sendKeys(navigationMenu.filterTxt, " ", "Entered text into textbox");
		sleepFor(1000);

		//sendKeys(navigationMenu.filterTxt, "", "Entering text into textbox");
		List<String> items = adminDoc.getExpectedActiveDocTypes();
		System.out.println("items "+items);

		getApplicationUrl();
		navigationMenu.clickOnTab("Capture");
		capture.clickOnDocumentCaptureBtn();
		navigationMenu.waitForIcon("Index the following changes");
		//ArrayList<String> items = adminDoc.expectedActiveDocTypes;

		List<String> ddCapturePageOptions = dropdownHelper.getAllOptionsFromDropdown("DocumentTypeId",getEnvVariable);
		List<String> newItemList = new ArrayList<String>();

		String [] item = ddCapturePageOptions.toArray(new String[ddCapturePageOptions.size()]);
		for(String str:item) {
			String [] sp1=str.split("-");
			newItemList.add(sp1[0].trim());
		}

		//System.out.println("newItemList "+newItemList);
		for(String option:items) {
			if(!option.equals("Memo")) {
				AssertionHelper.verifyTrue(newItemList.contains(option),"Assertion for checking drop down option present "+option);
			}
		}

	}


	//It is used to select editable checkbox of document if it not enabled
	@Test(priority=106,enabled = true)
	public void checkEditableStatusOfDocument() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		//navigationMenu.clickOnSpecificRow("last()");
		navigationMenu.searchInFilter("Default");
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		adminDoc.checkDocumentTypeEditable();

		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		//navigationMenu.clickOnSpecificRow("last()");
		navigationMenu.searchInFilter("ScanDoc");
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnEditIcon();
		adminDoc.checkDocumentTypeEditable();
	}


	@Test(priority=107,enabled = true)
	public void TestAddMemoDocumentType() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		getApplicationUrl();
		adminDoc.navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		adminDoc.enterTypeId("Memo");
		adminDoc.enterDescription("memo");
		adminDoc.selectMailOption("Mail",getEnvVariable);			//Chanegd in R551
		adminDoc.selectMediaType("Memos and Telephone Logs",getEnvVariable);
		adminDoc.checkEditable();
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			try {
				new JavaScriptHelper(driver).clickElement(adminDoc.publicAccessTab);
				//click(adminDoc.publicAccessTab, "Clicked on public access tab");
				sleepFor(1000);
				adminDoc.selectPublicationMode("Automatic");
				sendKeys(adminDoc.publicationPriorityInput, "1", "Entering text into textbox");

				sleepFor(1000);
				adminDoc.selectPublicationMode("Never");

				//navigationMenu.clickOnSaveIcon();
				navigationMenu.clickOnIcon("Save changes made to document type", "Actions");
				navigationMenu.waitForAddIcon();
				navigationMenu.searchInFilter("Memo");
				Thread.sleep(2000);
				String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
				AssertionHelper.verifyText(filteredText, "Memo");
			}
			catch(Exception ex){
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "already exists");
			}

		}else {
			try {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
				navigationMenu.searchInFilter("Memo");
				Thread.sleep(2000);
				String filteredText = navigationMenu.getfilteredRowElement("documentTypesTable").getText();
				AssertionHelper.verifyText(filteredText, "Memo");
			}
			catch(Exception ex){
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "already exists");
			} 
		}
	}


	/**      Notes module **/
	
	@Test(enabled=true,priority = 108)
	public void TestAddParagraphNotesFromAdministration() {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

		textAreaDesctiption = "This is new line characted for the above line"+generateRandomNumber();
		getApplicationUrl();
		adminDoc.clickOnAdminNotes();
		navigationMenu.clickOnAddIcon();
		
		adminDoc.SelectNotesType("Paragraph",getEnvVariable);			//Changed in R551
		adminDoc.EnterTextAreaDescriptionForNotes(textAreaDesctiption);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	@Test(enabled=true,priority = 109)
	public void CaptureDocumentAndAddNotesFromLandingPage() throws InterruptedException {
		String docRef = "Notes"+generateRandomNumber();

		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		
		getApplicationUrl();
		capture.navigateAndCaptureDocWithParameters(null, null, null, docRef, null,getEnvVariable);
		
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
		docTools.clickOnLandingPageIcon();
		docTools.ClickOnAddNotesToDocument();
		docTools.EnterTitleInNotes("Sample");
		docTools.SelctParagraphforDocument(textAreaDesctiption,getEnvVariable);
		navigationMenu.clickOnSaveIcon();
	}
	
	@Test(enabled=true,priority = 110)
	public void DeleteNoteStandardTexts() {
		getApplicationUrl();
		adminDoc.clickOnAdminNotes();
		
		navigationMenu.searchInFilter(textAreaDesctiption);
		navigationMenu.clickOnFilteredRow("standardNotesTable");
		navigationMenu.clickOnDeleteIcon();
		sleepFor(2000);
		
	}
}

/*
 * - Solved stale element ref exception while navigation Admin->Document Types->Active/Inactive
 */
