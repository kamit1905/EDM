package main.EDRM.hybridFramework.pageObject.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;

public class ToolkitCaseManagement extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			CapturePage capture;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			Logger log = LoggerHelper.getLogger(ToolkitCaseManagement.class);
	
			@FindBy(how=How.ID,using="Name")
			public WebElement txtNameTemplateDetails;
			
			@FindBy(how=How.XPATH,using="//input[@name='Description']")
			public WebElement txtDescriptionTemplateDetails;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='MatchingIndicator']")
			public WebElement ddMatchingOptionTemplateDetails;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='AllocationIndicator']")
			public WebElement ddAllocationOptionTemplateDetails;
			
			@FindBy(how=How.XPATH,using="//input[@id='IsRequired']")
			public WebElement spnEditRequiredTemplateDetails;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//div[@style='']//input[@name='Required']"),
				@FindBy(how=How.XPATH,using="//div[@class='modal fade show']//div[@style='']//input[@name='Required']")
			})
			public WebElement txtRequiredDocEditTemplate;
			
			@FindBy(how=How.XPATH,using="//table[@id='caseViewerDataTable']/tbody/tr/td")
			public WebElement tblCaseSubfolder;
			
			@FindBy(how=How.XPATH,using="(//div[@class='dataTable-tree dataTable-leaf dataTable-collapse'])[1]")
			public WebElement firstSubDocCaseSubfolder;
			
			@FindBy(how=How.XPATH,using="(//input[@class='standardcheckbox caseSubFolderRejected'])[1]")
			public WebElement chkRejectedFirstSubDoc;
			
			@FindBy(how=How.XPATH,using="(//input[@class='standardcheckbox caseSubFolderAccepted'])[1]")
			public WebElement chkAcceptedFirstSubDoc;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//div[@class='modal-body']//input[@id='pendingPeriod']"),
				@FindBy(how=How.XPATH,using="//div[@class='modal fade show']//div[@class='modal-body']//input[@id='pendingPeriod']")         //Changed locator for R531
			})			
			public WebElement txtPendingPeriodChangeMailStatus;
			
			@FindBy(how=How.XPATH,using="//button[@aria-controls='templateControlseDataTable']")
			public WebElement gearIcon;
			
			@FindBy(how=How.XPATH,using = "//td[contains(text(),'Doc: tp')]")
			public WebElement firstSubDocCaseSubfolderContent;
			
			
			
			public String tempTableRowAddPopupTemplate = "//div[@class='modal fade show' or @class='modal fade in']//table[@id='templateListDataTable']//td[text()='%s']",		//Changed locator for R531
					tempTableRowAddedTemplate = "//table[@id='templateControlseDataTable']//td[text()='%s']",
					templateDataTableId = "templateControlseDataTable",
					templateDataTableHeaderId = "TemplateControlseDataTable",
					masterDocTypeDataTableId="masterDocTypeDataTable",
					masterDocTypeDataTableHeaderId="MasterDocTypeDataTable",
					tblTemplateCaseSubFodlerId="caseViewerDataTable";
			
			public String tmpCaseSubFolder =  "//td[text()='%s']/ancestor::tr[1]/following-sibling::tr//td[1]",
					tmpCaseSubTask = "//td[text()='%s']/following-sibling::td[5]//input";
			
	public ToolkitCaseManagement(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		alertHelper=new AlertHelper(driver);
		actionHelper= new ActionHelper(driver);
		capture= new CapturePage(driver);
	}
	
	/**
	 * Add template details of template reference
	 * @param tempReference
	 */
	public void addTemplateDetails(String tempReference) {
		sendKeys(txtNameTemplateDetails, tempReference, "Entering temp details as "+tempReference);
	}
	
	/**
	 * Select Add to template popup with provide template name
	 * @param templateName
	 * @throws InterruptedException
	 */
	public void selectAddToTemplatePopup(String templateName) throws InterruptedException {
		windowHelper.waitForModalDialog("Add To Template");
		Thread.sleep(2000);
		WebElement rowEle = driver.findElement(By.xpath(String.format(tempTableRowAddPopupTemplate, templateName)));
		rowEle.click();
		Thread.sleep(1000);
		windowHelper.clickOnModalFooterButton("Add Selected");
	}
	
	/**
	 * Get Added temp element name 
	 * @param docName document name
	 * @return text of added templated name
	 */
	public WebElement getAddedTempElement(String docName) {
		navigationMenu.waitForIcon("Cancel changes");
		return driver.findElement(By.xpath(String.format(tempTableRowAddedTemplate, docName)));
	}
	
	/**
	 * Click on cancel changes with provided button text
	 * @param cancelOption button text which needs to be clicked
	 */
	public void clickOnCancelChanges(String cancelOption) {
	navigationMenu.clickOnIcon("Cancel changes", "Actions");
	windowHelper.waitForPopup("Leave Page");
	windowHelper.clickOnModalFooterButton(cancelOption);
	}
	
	/**
	 * Click on added template
	 * @param templateRowTable template row name
	 */
	public void clickOnAddedTemplate(String templateRowTable) {
		click(getAddedTempElement(templateRowTable), "Clicking on template row");
	}
	
	/**
	 * Click on Edit template icon
	 */
	public void clickOnEditTemplateIcon() {
		navigationMenu.clickOnIcon("Edit selected template content", "Template content");
		windowHelper.waitForModalDialog("Edit Template Content");
	}
	
	/**
	 * Change Required doc on edit popup 
	 * @param required
	 */
	public void changeRequiredDocOnEditPopup(String required) {
		waitHelper.waitForElementVisible(txtRequiredDocEditTemplate, 20, 1);
		//incrementCheveronValue(txtRequiredDocEditTemplate);
		sendKeys(txtRequiredDocEditTemplate, required, "Entering required text");
		windowHelper.clickOnModalFooterButton("Ok");
		navigationMenu.waitForIcon("Cancel changes");
	}
	
	/**
	 * Get template required column value
	 * @return required column value
	 */
	public String getTemplateRequiredValue() {
		/**String requiredIndex = navigationMenu.getColumnHeadEle(templateDataTableId,"Required").getAttribute("data-column-index");
		log.info("Required index is "+requiredIndex);
		int actualIndex = Integer.parseInt(requiredIndex)+1;
		String reqValue = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		**/
		String reqValue = navigationMenu.getColumnValueByIndex(templateDataTableId, 4);
		return reqValue;		
	}
	
	/**
	 * verify template content
	 * @return true,false based on expected header and icons
	 * @throws InterruptedException
	 */
	public boolean verifyTemplateContent() throws InterruptedException {
		List<String> expectedHeader = new ArrayList<>(Arrays.asList("Type","Reference","Description",
				"Required","Not Applicable"));
		List<String> expectedICons = new ArrayList<>(Arrays.asList("Add items into template",
				"Add new task into template","Remove selected item","Remove all items","Preview template","Edit selected template content"));
			Thread.sleep(1000);
			boolean headercheck = expectedHeader.equals(navigationMenu.getAllDisplayedColumnNames(templateDataTableHeaderId));
			log.info("Header check value "+headercheck);
			navigationMenu.clickOnFilteredRow(templateDataTableId);
			Thread.sleep(1000);
			boolean optionIcon = navigationMenu.isIconsEnabled(expectedICons);
			log.info("option icon value "+optionIcon);
			return headercheck && optionIcon ;
	}
	
	/**
	 * Verify master document type
	 * @return true,false based on expected header
	 * @throws InterruptedException
	 */
	public boolean verifyMasterDocType() throws InterruptedException {
		List<String> expectedHeader = new ArrayList<>(Arrays.asList("Reference","Description"));
		List<String> expectedICons = new ArrayList<>(Arrays.asList("Add new master document type",
				"Remove selected master document type","Remove all master document types"));
			Thread.sleep(1000);	
			boolean headercheck = expectedHeader.equals(navigationMenu.getAllDisplayedColumnNames(masterDocTypeDataTableHeaderId));
			navigationMenu.clickOnFilteredRow(masterDocTypeDataTableId);
			Thread.sleep(1000);
			boolean optionIcon = navigationMenu.isIconsEnabled(expectedICons);
			return headercheck && optionIcon ;
	}
	
	/**
	 * Double click on first sub document
	 */
	public void doubleClickOnFirstSubDoc() {
		sleepFor(2000);
		boolean firstSubCaseFolderStatus=new VerificationHelper(driver).isElementDisplayedByEle(firstSubDocCaseSubfolder);			//firstSubDocCaseSubfolder.isDisplayed();
		log.info("First subcase folder status "+firstSubCaseFolderStatus);
		sleepFor(1000);
		if(!firstSubCaseFolderStatus) {
			refreshPage();
		}
		waitHelper.waitForElementVisible(tblCaseSubfolder, 20, 1);
		actionHelper.doubleClickOnElement(firstSubDocCaseSubfolder);
	}
	
	/**
	 * Click on first sub document
	 */
	public void clickonFirstSubDoc() {
		click(firstSubDocCaseSubfolder, "Clicking on first subdoc");		
	}
	
	/**
	 * Unassign sub document from case
	 */
	public void clickOnUnassign() {
		navigationMenu.clickOnIcon("Unassign sub-document from case", "Case");
		windowHelper.waitForPopup("Unassign Sub-Document");
		sleepFor(1000);
		windowHelper.clickOnModalFooterButton("Ok");
	}
	
	/**
	 * Click on first accepted sub doc
	 */
	public void clickOnFirstAcceptedSubDoc() {
		click(chkAcceptedFirstSubDoc,"Clicking on first accepted checkbox");		
	}
	
	/**
	 * Change template values to provided matching option
	 * @param templateName edit template name
	 * @param matchingOption matchingOption which needs to be edited
	 * @throws InterruptedException
	 */
	public void changeTemplateValues(String templateName, String matchingOption) throws InterruptedException {
		 navigateToCaseMangementMaintenancePage();
		 navigationMenu.searchInFilter(templateName);
		 Thread.sleep(1000);
		 navigationMenu.clickOnFilteredRow("templateMaintainenceDataTable");
		 navigationMenu.clickOnEditIcon();
		 navigationMenu.waitForIcon("Cancel changes");
		 String matchingOptionValue = ddMatchingOptionTemplateDetails.getAttribute("title");
		 if(!matchingOptionValue.equalsIgnoreCase(matchingOption)) {
			dropdownHelper.selectFromAutocompleDDWithoutInput(matchingOption, ddMatchingOptionTemplateDetails); 
			navigationMenu.clickOnIcon("Save changes made to template", "Actions");
			navigationMenu.waitForAddIcon();
		 }
		 else {
			 navigationMenu.clickOnIcon("Cancel changes","Actions" );
			 navigationMenu.waitForAddIcon();
		 }
		 
	}
	
	/**
	 * This method helps in searching doc ref and clicking on case sub folder
	 * @param label label for searching e.g Account Ref
	 * @param accRef value for searching e.g F1-001R1
	 * @param rowNum Rownumber which you wish to click
	 */
	public void searchDocAndViewCaseSubFolder(String label, String accRef,String rowNum) {
		 capture.searchWithCriteria(label, accRef);
		 //capture.clickOnDocumentListBtn();
		 capture.clickOnIntrayListBtn();
		 navigationMenu.waitForFilterTextBox();
		 //navigationMenu.clickOnSpecificRow(rowNum);
		 capture.clickOnFirstItemOfIntray();
		 navigationMenu.clickOnIcon("View Case Sub-Folder", "Case");
		 navigationMenu.waitForIcon("Close");
		 
		 sleepFor(1000);
		// refreshPage();				//Added by amit to refresh page
	}
	
	/**
	 * Click on footer button of Allocation dialog
	 * @param btntext button text which needs to be clicked
	 */
	public void clickOnFooterButtonAllocation(String btntext) {
		windowHelper.waitForModalDialog("Allocation");
		windowHelper.clickOnModalFooterButton(btntext);
	}
	
	/**
	 * Get master document status
	 * @return status value of master doc
	 */
	public String getMasterDocStatus() {
	return navigationMenu.getTableCellValue(tblTemplateCaseSubFodlerId,"1","4");	
	}
	
	
		/**
	 * Capture document with provided params
	 * @param filepath
	 * @param filename
	 * @param docType
	 * @param docRef
	 * @param accRef
	 * @throws InterruptedException 
	 */
	public void navigateAndCaptureDocWithParameters(String filepath,String filename,String docType, String docRef ,String accRef, boolean AllocationWindow,String getEnvVariable) throws InterruptedException {
		 String userType = "To User",
		 userName = ObjectReader.reader.getUserName();
		 //filepath= filepath == null ? "C:\\users\\amit.khambad\\Projectdata\\Testdata\\Tiffiles\\": filepath;	
		 //filename= filename == null ?"file1.tif": filename;
		 filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		 filename= filename == null ?"file1.tif": filename;
		 docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		navigationMenu.clickOnCaptureTab();
		capture.clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		capture.clickOnFileUploadIcon();
		capture.uploadFileAngGetFileName(filepath,filename);
		//capture.selectDocumentTypemDD(docType);
		capture.selectRoutingTypeDD(userType,getEnvVariable);
		capture.selectRouteToDD(userName,getEnvVariable);
		capture.enterDocRef(docRef);
		if(accRef!=null) {
			boolean success = capture.enterFolder1RefCapturePageAndSearch(accRef, "Account Ref");
			log.info("Flag value of success is "+success);
			if(!success) {
				windowHelper.clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  capture.enterFolder1Ref(accRef);
			  capture.clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, capture.routeToDD, 20);
			}
		}
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		capture.selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(1000);
		capture.clickOnIndexDocument();
		 //if(AllocationWindow) {
			// clickOnFooterButtonAllocation("Apply changes");
		 //}
		 //if((docType.contains("Default") || docType.contains("ScanDoc"))&& baseUrl.contains("Lives")) {
		  //capture.clickOkOnCofirmIndexDocument(); }
		 //waitHelper.waitForElement(capture.successullyIndexMsg,20);
		 //capture.clickOkOnSuccesfullPopup();
		try {
			waitHelper.waitForElement(capture.successullyIndexMsg,10);							
			capture.clickOkOnSuccesfullPopup();																					//Remove this line later
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		 //getApplicationUrl(true);
	}
		
		/**
		 * Save case to pending status with delay value
		 * @param delay e.g 1,2 delay value
		 * @return popup message of change mail status
		 * @throws InterruptedException
		 */
		public String saveCaseToPendingStatusWithDelay(String delay) throws InterruptedException {
			navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
			windowHelper.waitForPopup("Change Mail Status");
			String popupMsg = windowHelper.getPopupMessage();
			windowHelper.clickOnModalFooterButton("Yes");
			sleepFor(2000);
			windowHelper.waitForModalDialog("Change Mail Status");
			sleepFor(2000);
			sendKeys(txtPendingPeriodChangeMailStatus, delay, "Changing delay to "+delay);
			actionHelper.pressTab();
			sleepFor(1000);
			//windowHelper.clickOnModalFooter();
			windowHelper.clickOnModalFooterButton("Ok");
			return popupMsg;
		}
		
		/**
		 * Click on edit icon of filtered row
		 */
		public void clickOnEditOfFilteredRow() {
			navigationMenu.clickOnFilteredRow("templateMaintainenceDataTable");
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Cancel changes");
		}
		
		/**
		 * Navigate to case management Maintenance page
		 */
		public void navigateToCaseMangementMaintenancePage() {
			 navigationMenu.clickOnTab("ToolKit");
			 navigationMenu.clickOnIcon("Case Manager template maintenance", "Case Manager");
			 navigationMenu.waitForAddIcon();
		}
		
		
		/*
		 * Save to caseVerifiedstatus
		 */
		public String saveCaseToVerifiedStatus() throws InterruptedException {
			navigationMenu.clickOnIconMenu("Save changes made to Case Sub-Folder", "General");
			windowHelper.waitForPopup("Change Mail Status");
			String popupMsg = windowHelper.getPopupMessage();
			sleepFor(1000);
			windowHelper.clickOnModalFooterButton("Yes");
			sleepFor(5000);
			return popupMsg;
		}
		
		
		/*
		 *  Used to refresh and click to template contents
		 */
		public void RefreshAndMove() {
			boolean status=new VerificationHelper(driver).isElementDisplayedByEle(gearIcon);
			
			if(!status) {
				refreshPage();
				navigationMenu.clickOnNavTab("Template Contents");
			}else {
				navigationMenu.clickOnNavTab("Template Contents");
			}
		}
		
		/*
		 * It is used to update the configuration values
		 */
		public void changeTheConfigurationValues(Map<String, String> configValues) {
			new WindowHelper(driver).waitForPopup("Configuration Settings");
			click(navigationMenu.dialogOkBtn, "Clicking on ok button of config settings");
			
			
			for(Map.Entry<String, String> mp : configValues.entrySet()) { 
				sleepFor(1000);
				navigationMenu.searchInFilter(mp.getKey());
				WebElement element = driver.findElement(By.xpath("//td[text()='"+mp.getKey()+"']/ancestor::tr[1]//input[@type='text']"));
				sendKeys(element, mp.getValue(), "Entering the value");
				sleepFor(1000);
				element.click();
				new ActionHelper(driver).pressTab();
				sleepFor(2000);
			}
			
			navigationMenu.clickOnSaveIcon();
			waitHelper.waitForElementVisible(new LoginPage(driver).welcomeTitleText, 30, 1);
		}
		
		
		public void ClickOnSubCaseFolder(String docType) {
			String subDocFolder = String.format(tmpCaseSubFolder, docType);
			WebElement caseSubFolderEle = driver.findElement(By.xpath(subDocFolder));
			// click(caseSubFolderEle, "Clicking on case sub folder row");
			new ActionHelper(driver).doubleClickOnElement(caseSubFolderEle);
			sleepFor(2000);
		}
		
		public void AccceptSubTaskInCase(String subTaskName) {
			String caseSubTask = String.format(tmpCaseSubTask, subTaskName);
			WebElement caseSubTaskEle = driver.findElement(By.xpath(caseSubTask));
			click(caseSubTaskEle, "Clicking on case sub task element");
		}
	}
