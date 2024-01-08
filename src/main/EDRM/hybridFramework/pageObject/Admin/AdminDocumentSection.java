package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class AdminDocumentSection extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			Logger log = LoggerHelper.getLogger(AdminDocumentSection.class);
	
			@FindBy(how=How.XPATH,using="//input[@id='Name']")
			public WebElement nameTxt;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='ParentId']")
			public WebElement tagParentTxt;
			
			@FindBy(how=How.XPATH,using="//table[@id='tagDatatable']/tbody/tr[1]/td/a")
			public WebElement addedTagLbl;
			
			@FindBy(how=How.XPATH,using="//table[@id='protectiveMarkerTable']/tbody/tr[last()]/td/a")
			public WebElement addedProtMarkerLbl;
			
			@FindBy(how=How.ID,using="Id")
			public WebElement txtTypeId;
			
			@FindBy(how=How.ID,using="Description")
			public WebElement txtDescription;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='MailOption']")
			public WebElement ddMailOption;
			
			@FindBy(how=How.XPATH,using="//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			public WebElement ddMailOptionAfter551;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='MediaType']")
			public WebElement ddMediaType;
			
			@FindBy(how=How.XPATH,using="//input[@id='CanCheckInOut']")
			public WebElement chkEditable;
			
			@FindBy(how=How.XPATH,using="//input[@id='IsDisabled']")
			public WebElement chkInactive;
			
			@FindBy(how=How.XPATH,using="//table[@id='documentTypesTable']/tbody/tr/td[2]")
			public WebElement lblFilteredRowDescription;
			
			@FindBy(how=How.XPATH ,using="//label[text()='Filter:']/input[@type='search']")       // Added by amit
			public WebElement  filterTxtForTags;
			
			@FindBy(how=How.XPATH,using="//td//button[contains(@data-original-title,'Edit Selected tag')]")
			public WebElement editTagBtn;
			
			@FindBy(how=How.XPATH,using="//td//button[contains(@data-original-title,'Delete Selected tag')]")
			public WebElement deleteTagBtn;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='RenditionPublishTypeId']")
			public WebElement publicationModeOption;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='AnnotationBurning']")
			public WebElement ddAnnotationBurining;
			
			@FindBy(how=How.XPATH,using="//input[@name='PublicationPriority']")
			public WebElement publicationPriorityInput;
			
			@FindBy(how=How.XPATH,using="//a[text()='Mandatory']/ancestor::ul[1]//a[text()='Public Access']")
			public WebElement publicAccessTab;
			
			@FindBy(how=How.XPATH , using="//a[text()='Mandatory']/ancestor::ul[1]//a[text()='Security']")
			public WebElement securityTab;
			
			@FindBy(how = How.XPATH, using = "//a[text()='Mandatory']/ancestor::ul[1]//a[text()='General']")
			public WebElement generalTab;
			
			@FindBy(how=How.XPATH,using="//input[@id='IsDocTypeSecurityEnabled']")
			public WebElement docTypeSecurityEnabled;
			
			@FindBy(how = How.XPATH,using="//button[@data-id='JobRoleId']")
			public WebElement jobRoleDD;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='JobRoleId']/ancestor::div[2]/ancestor::section[1]")
			public WebElement jobRoleDD551OnWards;
			
			@FindBy(how=How.XPATH,using="//a[text()='Mandatory']/ancestor::ul[1]//a[text()='Document Connect']")
			public WebElement documentConnectTab;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='DocumentConnectPublishTypeId']")
			public WebElement ddDocumentConnectPublicationMode;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='ResponseDocTypeId']")
			public WebElement ddResponseDocumentType;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='drpFolder']")
			public WebElement ddNotesTypeButton;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='drpFolder']/ancestor::section[@class='page container gridHeader']")
			public WebElement ddNotesTypeButtonAfter551;
			
			@FindBy(how = How.XPATH,using = "//textarea[@id='Text']")
			public WebElement notesTxtArea;
			
			@FindBy(how = How.XPATH,using = "//button[text()=' Edit User Access']")
			public WebElement editUserAccessBtn;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='securityTeams']")
			public WebElement teamsSecurityDropDownButton;
			
			@FindBy(how = How.XPATH,using = "//th[text()='Teams']/ancestor::table[1]//img")
			public WebElement teamAddButtonOnDocTypeSecurity;
			
			@FindBy(how = How.XPATH,using = "//button[text()='Close']")
			public WebElement closeBtn;
			
			@FindBy(how = How.XPATH,using = "//input[@id='IsLockOnIndex']")
			public WebElement chkLockDocumentOnIndex;
			
			@FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			public WebElement ddCommonDropDownElementWithoutInput;
			
//			@FindBy(how=How.XPATH,using="//table[@id='documentTypesTable']//td")
//			public WebElement documentTypesTableEmpty;											//created by sagar on 24.07.2023
			
			@FindBy(how=How.XPATH,using="//button[@data-id='GroupId']")
			public WebElement ddGroup;															//created by sagar on 24.07.2023
			
			@FindBy(how = How.XPATH,using="//button[@data-id='RoleId']")
			public WebElement RoleDD;														//created by sagar on 28.07.2023
			
	         @FindBy(how=How.XPATH,using="//button[@data-id='WorkflowType']")					//created by sagar on 28.07.2023
	         public WebElement workflowTypeDD;           
	         
	          @FindBy(how=How.XPATH,using="//button[@data-id='TemplateId']")		//created by sagar on 29.07.2023
	          public WebElement TemplateDD;   
	          
	        @FindBy(how = How.XPATH,using = "//input[@name='PendingPeriod']")  
	        public WebElement pedingPerriodInput;  



			
			public final String docmentTypesTableID="documentTypesTable";
			
			//Active document type dropdown options
			public ArrayList<String> expectedActiveDocTypes = new ArrayList<String>(
					Arrays.asList("Default - General Default Document Type",
							"ScanDoc - Generic Scanned Document",
							"tp - timepass"));					//"Memo - memo"
			ArrayList<String> inActiveDocTypes = new ArrayList<String>();
			
			@FindBy(how=How.XPATH,using="//input[@name='TargetDays']")
			public WebElement targetDays;
			
	public AdminDocumentSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
	}
	
	/**
	 * Add parent tag
	 * @param parentTag
	 */
	public void addParentTag(String parentTag,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(tagParentTxt, "Clicking on paraent tag drop down");
			dropdownHelper.selectFromAutocompleDDWithoutInput(parentTag, ddCommonDropDownElementWithoutInput,getEnvVariable);
		}else {
			dropdownHelper.selectFromAutocompleDDWithoutInput(parentTag, tagParentTxt);
		}
	}
	
	/**
	 * Navigate to active document types then active section
	 */
	public void navigateToAdminDocumentTypesActive() {
		navigationMenu.clickOnTab("Administration");
		 navigationMenu.clickOnIconMenu("Select Document Type category to admin", "Document");
		 navigationMenu.clickOnIconMenu("Active document types");
		 navigationMenu.waitForAddIcon();
	}
	
	/**
	 * This method will add tag name with parent provided in parentTag parameter
	 * @param tagName Tag name to created
	 * @param parentTag parent tag associated with tag
	 * @return
	 */
	public String addTag(String tagName, String parentTag,String getEnvVariable) {
		navigationMenu.clickOnAddIcon();
		waitHelper.waitForElementClickable(driver, nameTxt, 5);
		sendKeys(nameTxt, tagName, "Entering tag name "+ tagName);
		addParentTag(parentTag,getEnvVariable);
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to tag", "Actions");
		sleepFor(2000);
		boolean status=new VerificationHelper(driver).isElementDisplayedByEle(addedTagLbl);			//Adding because getting blank page on tags on click of save
		if(status) {
			waitHelper.waitForElementVisible(addedTagLbl, 5, 1);
			return verificationHelper.getText(addedTagLbl);
		}else {
			refreshPage();
			waitHelper.waitForElementVisible(addedTagLbl, 5, 1);
			return verificationHelper.getText(addedTagLbl);
		}
		//waitTillInvisiblityOfBusyIcon(5);
	}
	
	/**
	 * Navigate to add tag form and create tag which is provided in parameter tagName
	 * @param tagName  Tag name to created
	 * @return It will return added tag label
	 */
	public String addTag(String tagName) {
		navigationMenu.clickOnAddIcon();
		waitHelper.waitForElementClickable(driver, nameTxt, 5);
		sendKeys(nameTxt, tagName, "Entering tag name "+ tagName);
		sleepFor(2000);					//Adding because save btn is not clickable in R531
		//navigationMenu.clickOnSaveIcon();
		navigationMenu.clickOnIconMenu("Save changes made to tag", "Actions");
		sleepFor(2000);
		boolean status=new VerificationHelper(driver).isElementDisplayedByEle(addedTagLbl);			//Adding because getting blank page on click of save
		if(status) {
			waitHelper.waitForElementVisible(addedTagLbl, 5, 1);
			return verificationHelper.getText(addedTagLbl);
		}else {
			refreshPage();
			waitHelper.waitForElementVisible(addedTagLbl, 5, 1);
			return verificationHelper.getText(addedTagLbl);
		}
		
	}
		public void verifyAndAddTag (String tagName) {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Tag maintenance","Document");
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 10, 1);
		navigationMenu.searchInFilter(tagName);
		String actEmptyTable = navigationMenu.getNoRecordsFoundMessage("tagDatatable");
		String expMsgInEmptyTable = "No matching records found";
		String expMsgInEmptyTable1 = "No items available";
		if(actEmptyTable.equalsIgnoreCase(expMsgInEmptyTable)) {
			String actualTag = addTag(tagName);
		}
		else if(actEmptyTable.equalsIgnoreCase(expMsgInEmptyTable1)){
			String actualTag = addTag(tagName);
		}
		sleepFor(1000);
	}
	
	/**
	 * Navigating add protective marker and creating protective marker
	 * with text provided in parameter
	 * @param markerText Protective marker name
	 * @return Added protective marker label
	 */
	public String addProtectiveMarker(String markerText) {
		navigationMenu.clickOnAddIcon();
		waitHelper.waitForElementClickable(driver, nameTxt, 5);
		sendKeys(nameTxt, markerText, "Adding protective marker "+markerText);
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
		//waitHelper.waitForElementVisible(addedProtMarkerLbl, 5, 1);
		new NavigationMenu(driver).searchInFilter(markerText);
		sleepFor(1000);
		return verificationHelper.getText(addedProtMarkerLbl);
	}
	
	/**
	 * Enter type id in document type
	 */
	public void enterTypeId(String type) {
	sendKeys(txtTypeId, type, "Entering type as "+type);	
	}
	
	/**
	 * Enter description in document type form
	 * @param description description field
	 */
	public void enterDescription(String description) {
	sendKeys(txtDescription, description, "Entering description as "+description);	
	}
	
	/**
	 * Select mail option in document type form
	 * @param mailOption mail option of document type
	 */
	public void selectMailOption(String mailOption,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddMailOption, "Clickin on Drop down button");
			dropdownHelper.selectFromAutocompleDDWithoutInput(mailOption, ddMailOptionAfter551, getEnvVariable);
		}else {
			dropdownHelper.selectFromAutocompleDDWithoutInput(mailOption, ddMailOption);
		}
	}
	
	/**
	 * Select media type in document type form
	 * @param mediaType
	 */
	public void selectMediaType(String mediaType,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddMediaType, "Clicking on media type drop down");
			dropdownHelper.selectFromAutocompleDDWithoutInput(mediaType, ddMediaType,getEnvVariable);
		}else {
			dropdownHelper.selectFromAutocompleDDWithoutInput(mediaType, ddMediaType);
		}
		
	}
	
	/**
	 * Check editable checkbox in edit document type form
	 */
	public void checkEditable() {
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(chkEditable, "Clicking on editable checkbox");
	}
	
	/**
	 * Check make Inative checkbox in document type form
	 */
	public void checkMakeInactive() {
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(chkInactive, "Clicking on make inactive checkbox");
	}
	
	/**
	 * Get filtered description row in document table
	 * @return filter table of document type description
	 */
	public String getFilteredRowDescriptionFromDocumentTable() {
		String actualFilteredTableDescr =  lblFilteredRowDescription.getText();
		return actualFilteredTableDescr;
	}
	
	/**
	 * Get description text of document type
	 * @return
	 */
	public String getDescriptionText() {
		String actualDescription = txtDescription.getAttribute("Value");
		return actualDescription;
	}
	
	/**
	 * Click on admin notes in document type
	 */
	public void clickOnAdminNotes() {
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Note Standard Texts maintenance", "Document");
		navigationMenu.waitForAddIcon();
		
	}
	
	/**
	 * Click on edit title text
	 * @param editText edit text of standard notes
	 * @return edited title of standard notes
	 */
	public String clickOnEditTitle(String editText) {
		navigationMenu.clickOnFilteredRow("standardNotesTable");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Cancel changes");
		sendKeys(driver.findElement(By.xpath("//textarea")), editText, "Editing title to "+editText);
		String editedTitle = driver.findElement(By.xpath("//textarea")).getText();
		navigationMenu.clickOnSaveIcon();
		try {
		navigationMenu.waitForAddIcon();
		}
		catch (Exception e) {
			//No changes to save popup handling
			windowHelper.waitForPopup("Save");
			windowHelper.clickOkOnPopup();
			navigationMenu.clickOnIcon("Cancel changes", "Actions");
		}		
		return editedTitle;
	}
	
	/**
	 * Click on edit paragrapth
	 * @param paraEditText edited text of paragraph standard notes
	 * @return editedTitle of standard notes
	 */
	public String clickOnEditParagraph(String paraEditText) {
		navigationMenu.clickOnSpecificRow("2");
		navigationMenu.clickOnEditIcon();
		sendKeys(driver.findElement(By.xpath("//textarea")), paraEditText, "Editing para to "+paraEditText);
		String editedTitle = driver.findElement(By.xpath("//textarea")).getText();
		navigationMenu.clickOnSaveIcon();
		try {
		navigationMenu.waitForAddIcon();
		}
		catch (Exception e) {
			//No changes to save popup handling
			windowHelper.waitForPopup("Save");
			windowHelper.clickOkOnPopup();
			navigationMenu.clickOnIcon("Cancel changes", "Actions");
		}
		return editedTitle;
	}

	/*
	 * Used to select editable checkbox of document type 
	 */
	public void checkDocumentTypeEditable() {
		
		boolean editableStatus=new VerificationHelper(driver).isElementSelected(chkEditable);
		log.info("Document type editable "+editableStatus);
		
		if(!editableStatus) {
			checkEditable();
		}
		
		try {
			navigationMenu.clickOnSaveIcon();
			navigationMenu.clickOnAddIcon();
		} catch (Exception e) {
			 String actMessage = windowHelper.getPopupMessage();
			 windowHelper.clickOkOnPopup();
			 AssertionHelper.verifyTextContains(actMessage, "No changes to save.");
		}
		
	}
	
	
	/*
	 * It is used return filter box is visible or not
	 */
	public boolean verifyFilterBoxInTags() { // Adding try catch if element is not found then it will return false

		try {
			WebElement element = driver.findElement(By.xpath("//input[@aria-controls='tagDatatable']"));
			return new VerificationHelper(driver).isElementDisplayedByEle(element);
		} catch (Exception e) {
			return false;
		}

	}	
	
	
	/*
	 * Used to search tag using tagname
	 */
	public String searchTagUsingName(String tagName) {
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(filterTxtForTags);
		
		if(status) {
			sendKeys(filterTxtForTags, tagName, "Entering text ");
			waitHelper.waitForElementVisible(addedTagLbl, 5, 1);
			return verificationHelper.getText(addedTagLbl);
		}else {
			refreshPage();
			sendKeys(filterTxtForTags, tagName, "Entering text ");
			waitHelper.waitForElementVisible(addedTagLbl, 5, 1);
			return verificationHelper.getText(addedTagLbl);
		}
		
	}
	
	/*
	 * Used to get All active document types
	 */
	public List<String> getExpectedActiveDocTypes() {
	    navigateToAdminDocumentTypesActive();
	    List<String> activeDocTypes =  navigationMenu.getAllColumnValues(docmentTypesTableID, 1);
	    return activeDocTypes;
	  }
	
	
	public void enterTargetDays(String days) {
		sendKeys(targetDays, days, "Entering target days "+days);
	}
	
	/**
	 * Select publication option in document type form
	 * @param publicOption option of document type
	 */
	public void selectPublicationMode(String publicOption) {
		dropdownHelper.selectFromAutocompleDDWithoutInput(publicOption, publicationModeOption);
	}
	
	
	public void selectEnableUserSecurityAccess() {
		boolean status  = new VerificationHelper(driver).isElementSelected(docTypeSecurityEnabled);
		if(!status) {
			docTypeSecurityEnabled.click();
		}
	}
	
	public void unselectEnableUserSecurityAccess() {
		boolean status  = new VerificationHelper(driver).isElementSelected(docTypeSecurityEnabled);
		if(status) {
			docTypeSecurityEnabled.click();
		}
	}
		
		
	public void selectJobRoleFromDDForDoc(String ddRoleValue,String getEnvVariable) {	
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(jobRoleDD, "Clicking on role drop down");
			new DropdownHelper(driver).selectFromAutocompleDD(ddRoleValue, jobRoleDD551OnWards,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDD(ddRoleValue, jobRoleDD);
		}
	}
	
	public void SelectPublicationModeForDocumentConnect(String publication) {
		new WaitHelper(driver).waitForElement(ddDocumentConnectPublicationMode, 10);
		new DropdownHelper(driver).selectFromAutocompleDD(publication, ddDocumentConnectPublicationMode);
	}
	
	public void SelectResponseDocumentType(String resDocType) {
		new WaitHelper(driver).waitForElement(ddResponseDocumentType, 10);
		new DropdownHelper(driver).selectFromAutocompleDD(resDocType, ddResponseDocumentType);
	}
	
	
	public void SelectNotesType(String type,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddNotesTypeButton, "Clicking on notes drop down");
			new DropdownHelper(driver).selectFromAutocompleDD(type, ddNotesTypeButtonAfter551,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDD(type, ddNotesTypeButton);
		}
	}
	
	public void EnterTextAreaDescriptionForNotes(String txtDescription) {
		sendKeys(notesTxtArea, txtDescription, "Entering the text area "+txtDescription);
	}
		
	public void SelectAnnotationBurningForPublicAccess(String annotationBur) {
		new WaitHelper(driver).waitForElement(ddAnnotationBurining, 10);
		new DropdownHelper(driver).selectFromAutocompleDD(annotationBur, ddAnnotationBurining);
	}
	
	public void checkLockDocumentOnIndex() {
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(chkLockDocumentOnIndex, "Clicking on editable checkbox");
	}
	
	//created by sagar on 24.07.2023
	public void selectGroup(String group,String getEnvVariable) {
		new DropdownHelper(driver).selectFromAutocompleDD(group, ddGroup);
	}
	
	//created by sagar on 24.07.2023
	public void addDocumentType(String typeId, String groupName, String getEnvVariable) {
		groupName = groupName == null ? "(none)" :groupName;//if groupName is null then (none)
		getApplicationUrl();
		navigateToAdminDocumentTypesActive();
		navigationMenu.clickOnAddIcon();
		enterTypeId(typeId);
		enterDescription("timepass");
		selectGroup(groupName,getEnvVariable);
		selectMailOption("Mail",getEnvVariable);
		sleepFor(4000);
		selectMediaType("No Specified Media Type",getEnvVariable);
		checkEditable();
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			try {
				new JavaScriptHelper(driver).scrollToTop();
				new JavaScriptHelper(driver).clickElement(publicAccessTab);
				sleepFor(1000);
				selectPublicationMode("Automatic");
				sendKeys(publicationPriorityInput, "1", "Entering text into textbox");
				sleepFor(1000);
				selectPublicationMode("Never");
				sleepFor(1000);
				navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");

				//verify document type with groupName				
				navigationMenu.waitForAddIcon();
				navigationMenu.searchInFilter(groupName);
				Thread.sleep(2000);
				String filteredText2 = navigationMenu.getColumnValueFromTable("Group Name");			
				AssertionHelper.verifyText(filteredText2, groupName);
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
				navigationMenu.searchInFilter(groupName);
				Thread.sleep(2000);
				String filteredText2 = navigationMenu.getColumnValueFromTable("Group Name");			
				AssertionHelper.verifyText(filteredText2, groupName);
			}
			catch(Exception ex){
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "already exists");
			} 
		}
	}

	//created by sagar on 24.07.2023
	public void deleteDocumentType (String typeId, String getEnvVariable) {
		getApplicationUrl();
		navigateToAdminDocumentTypesActive();
//		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
//			if (!status) {
//				sleepFor(1000);
//				refreshPage();
//				sleepFor(1000);
//			}
		navigationMenu.searchInFilter(typeId);
		sleepFor(1000);		    		
   		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnIcon("Delete selected document type", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
	}
	
	//created by sagar on 29.07.2023
	public void selectRole(String role,String getEnvVariable) {
		new DropdownHelper(driver).selectFromAutocompleDD(role, RoleDD);
	} 
	
	
	//created by sagar on 29.07.2023
	public void selectWorkflowType(String workflowType,String getEnvVariable) 
	{
			new DropdownHelper(driver).selectFromAutocompleDD(workflowType, workflowTypeDD);
	}

	//created by sagar on 29.07.2023
	public void selectTemplate (String template,String getEnvVariable) 
	{
			new DropdownHelper(driver).selectFromAutocompleDD(template, TemplateDD);
	}
	
	//created by sagar on 29.07.2023
	public void editDocumentType(String typeId, String groupName, String Role, String workflowType, String template, String getEnvVariable) {
		groupName = groupName == null ? "(none)" :groupName;//if groupName is null then (none)
		workflowType = workflowType == null ? "(none)" :workflowType;
		template = template == null ? "(none)" :template;
		getApplicationUrl();
		navigateToAdminDocumentTypesActive();
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if (!status) {
			sleepFor(1000);
			refreshPage();
			sleepFor(1000);
		}
		navigationMenu.searchInFilter(typeId);
		sleepFor(1000);		    		
		navigationMenu.clickOnFilteredRow("documentTypesTable");
		navigationMenu.clickOnIcon("Edit selected document type ", "Actions");
		sleepFor(3000);
		navigationMenu.waitForIcon("Save changes made to document type");
		//new options for edit are added on 29.07.2023 to this method
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		selectRole(Role, getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectWorkflowType(workflowType, getEnvVariable);
		sleepFor(1000);
		selectTemplate(template, getEnvVariable);
		
		sleepFor(1000);
		if(getEnvVariable.equals("Enterprise_Sp1s")) {
			try {
				new JavaScriptHelper(driver).scrollToTop();
				new JavaScriptHelper(driver).clickElement(publicAccessTab);
				sleepFor(1000);
				selectPublicationMode("Automatic");
				sendKeys(publicationPriorityInput, "1", "Entering text into textbox");
				sleepFor(1000);
				selectPublicationMode("Never");
				sleepFor(1000);
				navigationMenu.clickOnIconMenu("Save changes made to document type", "Actions");

				//verify document type with typeId				
				navigationMenu.waitForAddIcon();
				navigationMenu.searchInFilter(typeId);
				Thread.sleep(2000);
				String filteredText2 = navigationMenu.getColumnValueFromTable("Document Type");			
				AssertionHelper.verifyText(filteredText2, typeId);
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
				String filteredText2 = navigationMenu.getColumnValueFromTable("Document Type");			
				AssertionHelper.verifyText(filteredText2, typeId);
			}
			catch(Exception ex){
				String actMessage = windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				AssertionHelper.verifyTextContains(actMessage, "already exists");
			} 
		}
	}
	
	public void enterPeriodInput(String pending) {
		sendKeys(pedingPerriodInput, pending, "Entering Pending periond as "+pending);
	}

	
}
