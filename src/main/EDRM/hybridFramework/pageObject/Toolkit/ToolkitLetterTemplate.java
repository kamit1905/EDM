package main.EDRM.hybridFramework.pageObject.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;

public class ToolkitLetterTemplate extends TestBase{
		
	private WebDriver driver;
			NavigationMenu navigationMenu;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			CapturePage capture;
			Logger log = LoggerHelper.getLogger(ToolkitLetterTemplate.class);
	
			
			@FindBy(how=How.XPATH,using="//div[@class='modal fade show']//button[@id='pickfiles']")
			public WebElement btnUploadFileUpdateDocumentLetterTemp;
			
			@FindBy(how=How.XPATH,using="//span[@class='references-details-align' and contains(text(),',')]")
			public WebElement lblReferenceDetailsBatchLetter;
			
			@FindBy(how=How.XPATH,using="//p[@id='dialogContent']//label[contains(@data-bind,'Success')]")
			public WebElement lblSuccessValueBatchLetter;
			
			
			
			String tmpTxtFolderRefUsingLabel = "//label[text()='%s' and contains(@data-bind,'Entity')]/..//input",
					tmpRdoButtonEleBatchLetter = "//label[text()='%s']/following-sibling::input";
			private static final String ACTIONS="Actions";
			private static final String CANCEL_CHANGE="Cancel changes";
			private static final String LETTER="Letter";
			private static final String PRODUCTION="Production";
			
						
	public ToolkitLetterTemplate(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		navigationMenu = new NavigationMenu(driver);
		windowHelper = new WindowHelper(driver);
		capture= new CapturePage(driver);
		alertHelper= new AlertHelper(driver);
		windowHelper=new WindowHelper(driver);
		waitHelper=new WaitHelper(driver);
	}
	
	/**
	 * Navigate to adhoc letter creation
	 */
	public void navigateToAdhocLetterCreation() {
		capture.clickOnCaptureTab();
		navigationMenu.clickOnIcon("Produce an adhoc letter", "Capture");
		navigationMenu.waitForIcon(CANCEL_CHANGE, ACTIONS);
	}
	
	/**
	 * Select letter template and create adhoc letter
	 * @return docref of select document
	 */
	public String selectLetterTempAndCreateLetterAdhocLetter(String tempName) {
		if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt)) {
			refreshPage();
		}
		SearchTemplateUsingName(tempName);			//Added by amit to searh and select the template
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		String docRef = navigationMenu.getColumnValueFromTable("Name");
		navigationMenu.clickOnIcon("Create Letter", ACTIONS);
		navigationMenu.waitForIcon("Letter Production", ACTIONS);
		return docRef;
	}
	
	/**
	 * Select Letter Temp and create letter
	 * @return docRef of selected document
	 */
	public String selectLetterTempAndCreateLetter(String tempName) {
		SearchTemplateUsingName(tempName);				//Added by amit to searh and select
		sleepFor(1000);
		capture.clickOnFirstItemOfIntray();
		String docRef = navigationMenu.getColumnValueFromTable("Name");
		clickOnCreateLetterForCreateLetter();
		return docRef;
	}
	
	/**
	 * Create letter for adhoc letter
	 */
	public void clickOnCreateLetterForAdhocLetter() {
		navigationMenu.clickOnIcon(LETTER, PRODUCTION);
		//capture.clickOkOnCofirmIndexDocument();
		waitHelper.waitForAlert(driver, 20);
		alertHelper.acceptAlert();
		windowHelper.waitForPopup("Capture Letter");
	}
	
	/**
	 * Create letter
	 */
	public void clickOnCreateLetterForCreateLetter() {
		navigationMenu.clickOnIcon(LETTER, PRODUCTION);
		windowHelper.waitForPopup("Create Letter");
	}

	/**
	 * Save document on capture letter create letter
	 */
	public void saveDocOnCaptureLetterCreateLetter() {
		//windowHelper.waitForPopup("Capture Letter");
		windowHelper.clickOnModalFooterButton("Save");
		windowHelper.waitForModalDialog("Update Document");
		sleepFor(2000);
		click(btnUploadFileUpdateDocumentLetterTemp,"Clicking on upload file document");
		windowHelper.uploadRecentlyDownloadedFile("*.docm");
		windowHelper.clickOnModalFooterButton("Ok");
		waitHelper.waitForElementClickable(driver, navigationMenu.filterTxt, 20);
	}
	
	/**
	 * Select document and create letter in doc list
	 */
	public void selectDocAndClickOnCreateLetterDocList() {
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Produce letters from document list.", "Letter Production");
		navigationMenu.waitForIcon(CANCEL_CHANGE, ACTIONS);
	}
	
	/**
	 * Check whether create letter is enabled
	 * @return
	 */
	public boolean isCreateLetterEnabled() {
		boolean isEnabled = navigationMenu.isIconEnabled("Create Letter", "Letter Production");
		return isEnabled;
	}
	
	/**
	 * Select document click on adhoc letter document list
	 */
	public void selectDocAndClickOnAdhocLetterDocList() {
		capture.clickOnFirstItemOfIntray();
		navigationMenu.clickOnIcon("Produce an adhoc letter", "Letter Production");
		navigationMenu.waitForIcon(CANCEL_CHANGE, ACTIONS);
	}
	
	/**
	 * Navigate to batch letter creation page  
	 */
	public void navigateToBatchLetterCreation() {
		navigationMenu.clickOnTab("Capture");
		navigationMenu.clickOnIcon("Produce letters in batch", "Capture");
		navigationMenu.waitForIcon("Create Letter", "Action");
	}
	
	/**
	 * Enter account ref and select ref batch letter
	 * @param refValue ref value for account ref
	 */
	public void enterAccountRefAndSelectRefBatchLetter(String refValue) {
		enterRefForLabel("Account Ref",refValue);
	}
	
	/**
	 * Enter property reference and select reference batch letter
	 * @param refValue reference value for property ref
	 */
	public void enterPropertyRefAndSelectRefBatchLetter(String refValue) {
		enterRefForLabel("Property Ref",refValue);
	}
	
	/**
	 * Enter reference for label
	 * @param refLabel reference label
	 * @param refValue Reference value
	 */
	private void enterRefForLabel(String refLabel, String refValue) {
	WebElement folderRefEle = driver.findElement(By.xpath(String.format(tmpTxtFolderRefUsingLabel, refLabel)));
		sendKeys(waitHelper.waitForElement(folderRefEle,10),refValue,"Enetering folder ref value = "+refValue);			
		new ActionHelper(driver).pressTab();
		navigationMenu.clickOnFilteredRow("referenceDataTable");
	}
	
	/**
	 * Select radio button batch letter 
	 * @param buttonLabel button label which needs to be clicked
	 */
	public void selectRadioButtonBatchLetter(String buttonLabel) {
		if(!new VerificationHelper(driver).isElementDisplayed(By.xpath("//button[@title='Menu']"))) {				//Added becuse below list is getting blank
			sleepFor(1000);
			refreshPage();
		}
		WebElement rdoButtonEle = driver.findElement(By.xpath(String.format(tmpRdoButtonEleBatchLetter, buttonLabel)));
		click(rdoButtonEle, "Clicking on radio button ele "+buttonLabel);
	}
	
	/**
	 * Click on Add icon of Batch letter
	 */
	public void clickOnAddIconBatchLetter() {
			navigationMenu.clickOnIcon("Add", "Reference");
	}
	
	/**
	 * Create letter in Batch letter creation
	 * @return SuccessValue Digit
	 */
	public int clickOnCreateLetterForBatchLetterCreation() {
		navigationMenu.clickOnIcon(LETTER, PRODUCTION);
		windowHelper.waitForPopup("Batch Letter Creation");
		String successVal = lblSuccessValueBatchLetter.getText();
		int successValDigit = successVal.isEmpty() ? 0:Integer.parseInt(successVal);
		windowHelper.clickOkOnPopup("Batch Letter Creation");
		return successValDigit;
	}
	
	/**
	 * Create import batch letter
	 */
	public void importBatchLetterCreate() {
		navigationMenu.clickOnIcon("Import", "Reference");
		windowHelper.uploadFileNativeWindow(ObjectReader.reader.getTestDocFolderPath(), "batchLetter1.txt");
		waitHelper.waitForElementClickable(driver, capture.inTrayItemFirstColumn, 20);
	}
	
		/**
		 * Save document on capture letter Adhoc letter
		 **/
		public void SaveDocOnCaptureLetterAdhocLetter() {
		windowHelper.clickOnModalFooterButton("Save");
		windowHelper.waitForModalDialog("Update Document");
		sleepFor(2000);
		click(btnUploadFileUpdateDocumentLetterTemp,"Clicking on upload file document");
		windowHelper.uploadRecentlyDownloadedFile("*.docm");
		windowHelper.clickOnModalFooterButton("Ok");
		//navigationMenu.waitForFilterTextBox();
	}

	/*
	 * Used to search letter template using name
	 */
	public void SearchTemplateUsingName(String tempName) {
		new NavigationMenu(driver).sendKeys(navigationMenu.filterTxt, tempName, "Entering value into textbox");
	}


}
