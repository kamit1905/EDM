package main.EDRM.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.Admin.FileSystemSection;

public class CapturePage extends TestBase {
			
			private WebDriver driver;
			WaitHelper waitHelper;
			private Logger log = LoggerHelper.getLogger(CapturePage.class);
			private NavigationMenu navigationMenu;
			
			private String folderRefCaptureTextXpath ="//label[text()='%s']/../div/input";
			
			private String folderRefCaptureTextXpathForAdditonal = "//label[text()='%s']/../div/input[@name='Reference' and @id='10']";
			
			@FindBy(how=How.XPATH,using="//li[@id='Capture']")
			public WebElement captureTab;
			
			@FindBy(how=How.XPATH,using="//p[contains(text(),'Capture')]")	
			public WebElement capturePageTitleTxt;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//td/button[contains(@data-original-title,'Capture a new document')]"),
				@FindBy(how=How.XPATH,using="//td/button[contains(@data-bs-original-title,'Capture a new document')]")
			})	
	        public WebElement documentCaptureBtn;
	    	
	        @FindBy(how=How.XPATH,using="//li/button[contains(@data-original-title,'/Document/Capture/Create')]")			
	        public WebElement documentCaptureLst;
			
	        @FindAll({			//For R540
	        	@FindBy(how=How.XPATH,using="//td/button[@data-bs-original-title='Index the following changes']"),
	        	@FindBy(how=How.XPATH,using="//td/button[@data-original-title='Index the following changes']"),
	        })    
	        //@FindBy(how=How.XPATH,using="//td/button[@data-original-title='Index the following changes']")
	        public WebElement indexDocumentBtn;
			
	        @FindBy(how=How.XPATH,using="//li/button[@data-original-title='Index the following changes']")
			public WebElement indexDocumentBtnLst;
	        
	        @FindBy(how=How.XPATH,using="//*[@id='plContainer']/a")
			public WebElement fileUploadIcon;
			
			@FindBy(how=How.XPATH,using="//div[@id='dropTarget']/table//td[@class='break-out-words']")
			public WebElement firstUploadedFileName;
			
			@FindBy(how=How.XPATH,using="//div[@id='dropTarget']/table//a[@title='Delete this file']")
			public WebElement firstUploadedFileDeleteBtn;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='RoutingType']")
			public WebElement routingTypeDD;
			
			@FindBy(how=How.XPATH,using="//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			public WebElement routingTypeDD551OnWards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='RouteTo']")
			public WebElement routeToDD;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='RouteTo']/ancestor::section[contains(@class,'page container')]")
			public WebElement routeToDDR551Onwards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='FileSystemId']")
			public WebElement fileSystemDD;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']")
			public WebElement documentTypeDD;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']/ancestor::section[contains(@class,'page container')]")
			public WebElement documentTypeDDR551Onwards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='ProtectiveMarkerId']")
			public WebElement protectiveMarkerDD;
			
			@FindBy(how=How.XPATH,using="//input[@id='Priority']/following-sibling::span/span[contains(@class,'up')]")
			public WebElement increasePriorityIcn;
			
			@FindBy(how=How.XPATH,using="//input[@id='Priority']/following-sibling::span/span[contains(@class,'down')]")
			public WebElement decreasePriorityIcn;
			
			@FindBy(how=How.XPATH,using="//input[@id='Priority']")
			public WebElement priorityTxt;
			
			@FindBy(how=How.XPATH,using="//*[@id='FlexibleDocumentTag']/div/div[1]/div")
			//@FindBy(how=How.XPATH,using="//*[@id='FlexibleDocumentTag']/div/div[1]")
			public WebElement selectedTagdiv;
			
			@FindBy(how=How.ID,using="DocumentRef")			//driver.findElement(By.xpath(""))	
			public WebElement docRefTxt;
			
			@FindBy(how=How.ID,using="DocumentRef2")
			public WebElement docRef2Txt;
			
			@FindBy(how=How.ID,using="DateReceived")
			public WebElement dateReceivedPicker;
			
			@FindBy(how=How.XPATH,using="//td[@class='day active today']")
			public WebElement todaysDateCal;
			
			@FindBy(how=How.XPATH,using="//div[contains(@class,'selectize-input')]/input")
			public WebElement tagAreaTxt;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='item']"),
				@FindBy(how=How.XPATH,using="//div[@class='item active']")			//For R540
			})
			public WebElement selectedTagLbl; 
			
			@FindBy(how=How.XPATH,using="//*[@class='modal-title' and text()='Index']")
			public WebElement indexModalTitle;
			
			@FindBy(how=How.XPATH,using="//p[@id='dialogMessage' and contains(text(),'Are you sure')]")
			public WebElement confirmIndexMsgTxt;
			
			@FindBy(how=How.XPATH,using="//p[@id='dialogMessage' and contains(text(),'successfully')]")
			public WebElement successullyIndexMsg;
			
			//Create Reference
			@FindBy(how=How.XPATH,using="//input[@id='Folder1_Ref']")
			public WebElement folder1Reftxt;
			
			@FindBy(how=How.XPATH,using="//input[@id='Folder2_Ref']")
			public WebElement folder2Reftxt;
			
			@FindBy(how=How.XPATH,using="//input[@id='Folder3_Ref']")
			public WebElement folder3Reftxt;
			
			@FindBy(how=How.XPATH,using="//button[@data-button='Save']")
			public WebElement saveBtn;
			
			@FindBy(how=How.XPATH,using="//button[@class='btn dropdown-toggle btn-primary']")
			public WebElement btnDummyRef;
			
			@FindBy(how=How.XPATH,using="//button[@class='btn dropdown-toggle btn-primary']/following-sibling::ul/li/a")
			public WebElement ddBtnDummytRef;
			
			@FindBy(how=How.XPATH,using="//input[contains(@data-bind,'Dummy')]")
			public WebElement txtDummyRef;
			
			//search area
			@FindBy(how=How.XPATH,using="//button[@data-id='FlexibleFieldId']")
			public WebElement searchDD;
			
			@FindBy(how=How.XPATH,using="//input[@id='FlexibleText']")
			public WebElement searchTxt;
			
			//Locator changed in 5.31 and then reverted in build 2.. initially it was flexible field height 
			@FindBy(how=How.XPATH,using="//div[contains(@class,'flexibleFieldHeight')]//input")
			public WebElement searchDDInput;
			
			@FindBy(how=How.XPATH,using="//div[@class='bs-searchbox']/..//li[contains(@class,'active')]/a")
			public WebElement searchDDMatch;// This element changes.. e.g active
			
			@FindBy(how=How.XPATH,using="//button[@data-icon='SearchIntray']")
			public WebElement intrayListBtn;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@data-original-title='Run document search']"),
				@FindBy(how=How.XPATH,using="//button[@data-bs-original-title='Run document search']")		//For R540
			})
			public WebElement docListBtn;
			
			
			
			
			//intrayList area
			@FindBy(how=How.XPATH,using="//*[text()='In-Tray']")
			public WebElement inTrayPageTitleTxt;
			
			@FindBy(how=How.XPATH,using="(//tr[contains(@class,'odd')]/td[3])[1]")
			public WebElement intrayItemRowCell;
			
			@FindBy(how=How.XPATH,using="(//tr[contains(@class,'odd')])[1]")
			public WebElement intrayItemRow;
			
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//td/button[@data-original-title='Change mail status']"),
				@FindBy(how=How.XPATH,using="//td/button[@data-bs-original-title='Change mail status']")
			})
			public WebElement changeMailStatusbtn;
			
			@FindAll({			
				@FindBy(how=How.XPATH,using="//li/button[@data-original-title='Change mail status']"),
				@FindBy(how=How.XPATH,using="//li/button[@data-bs-original-title='Change mail status']")
			})			
			//@FindBy(how=How.XPATH,using="//li/button[@data-original-title='Change mail status']")
			public WebElement changeMailStatusLstBtn;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='mailStatusDropDown']")
			public WebElement changeMailsStatusDDBtn;
			
			@FindBy(how=How.XPATH,using="//select[@id='mailStatusDropDown']")
			public WebElement changeMailStatusSelectDDBtn;
			
			@FindBy(how=How.XPATH,using="//h4[text()='Change Mail Status']")
			public WebElement changeMailStatusLbl;
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Mail Status:')])[1]")
			public WebElement mailStatusColumn;
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Misc Ref:')])[1]")
			public WebElement folder2refDocListColumn;
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Document Type:')])[1]")
			public WebElement documentTypeDocListColumn;
			
			@FindBy(how=How.XPATH,using="(//table//th[2])[1]")
			public WebElement inTrayItemFirstColumn;
			
			@FindBy(how=How.XPATH,using="//a[@title='Remove']")
			public WebElement lnkTagRemove;
			
			
			//Generic Ok btn //*[@class='modal fade in']//button[text()='Ok']
			@FindBy(how=How.XPATH,using="//div/button[contains(@data-original-title,'add record')]")
			public WebElement addEntityBtn;
			
			@FindBy(how=How.XPATH,using="//p[@class='navbar-text ellipsisWrap']")
			public WebElement navbarTitleLbl;
			
			@FindBy(how=How.XPATH,using="//a[@href='#tabsCrud']")
			public WebElement tabTitleLbl;
			
			@FindBy(how=How.XPATH,using="//button[@id='mailStatusCancel']")
			public WebElement cancelMailStatusBtn;
			
			@FindBy(how=How.XPATH,using="//button[@id='mailStatusOk']")
			public WebElement okMailStatusBtn;						 
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//textarea[@id='ActionTakenText']/following-sibling::span/a[@data-original-title='Add standard note']"),
				@FindBy(how=How.XPATH,using="//textarea[@id='ActionTakenText']/following-sibling::span/a[@data-bs-original-title='Add standard note']")
			})		
			public WebElement icnActionTakenAddMemo;
			
			//@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//div[@class='modal-body']//textarea")
			@FindBy(how=How.XPATH,using="//div[@class='modal-content']//div[@class='modal-body']//textarea")
			public WebElement txtNotesStandardTextMemo;
			
			//Batch indexing
			@FindBy(how=How.XPATH,using="(//tr[@role='row']/td)[1]/a")
			public WebElement lnkFirstBatchRef;
			
			@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//button[@data-id='fileSystem']")
			public WebElement btnFileSystemSelectionBatch;
			
			@FindBy(how=How.XPATH ,using="//div[@id='metadata']/div[@class='form']/button")
			public WebElement frmMetadataBatch;
			
			@FindBy(how=How.XPATH ,using="//div[@id='preview']//div[@class='pageIcon pageAction']")
			public WebElement icnAddPreviewBatch;
			
			@FindBy(how=How.XPATH ,using="//div[@id='preview']//div[@class='pageIcon pageNumber']")
			public WebElement icnNumberPreviewBatch;
			
			@FindBy(how=How.XPATH ,using="//div[@id='document']/div[@class='thumb selected']")
			public WebElement icnDocumentSelectedBatch;
			
			@FindBy(how=How.XPATH ,using="//button[@id='btn-CoreFields']")
			public WebElement btnCoreFieldsBatch;
			
			@FindBy(how=How.XPATH ,using="//div[@id='createNewDocument']//textarea")
			public WebElement txaCreateNewDocCapture;
			
			/**      Dummy reference form    **/
            @FindBy(how=How.XPATH ,using="//input[@id='Prefix']")
            public WebElement txtPrefixDummyReference;
            
            @FindBy(how=How.XPATH ,using="//input[@id='SpnCodelength']")
            public WebElement txtCodeLengthDummyReference;
            
            
            @FindBy(how=How.XPATH ,using="//button[@data-id='EntityDropDown']")
            public WebElement btnEntityDropdownDummyReference;
            
            @FindBy(how=How.XPATH,using="//label[text()='Account Ref']/ancestor::div[1]//span[contains(@class,'references-details')]")
            public WebElement getReferenceDetails;
            
            @FindBy(how=How.XPATH,using="//tr[contains(@class,'odd')]/td[1]")
            public WebElement firstRowElementXpath;
            
            @FindBy(how = How.XPATH,using = "//input[@id='EmailAddress']")
            public WebElement folder1RefEmailAddress;
            
            @FindBy(how = How.XPATH,using = "//input[@id='EmailVerified']")
            public WebElement folder1RefEmailVerified;
            
            @FindAll({
            	@FindBy(how = How.XPATH,using = "//a[@data-bs-original-title='Add a new set of indexing details']"),
            	@FindBy(how = How.XPATH,using = "//a[@data-original-title='Add a new set of indexing details']")
            })
            public WebElement addNewSetOfIndexingDetails;
            
            @FindBy(how = How.XPATH,using ="//input[@name='Reference' and @id='10']")
            public WebElement additionalFolder1Ref;
            
            @FindBy(how = How.XPATH,using = "//a[@aria-label='Remove these indexing details']")
            public WebElement removeIndexingDetailsBtn;
            
            @FindBy(how = How.XPATH,using = "//input[@id='DateReceived']")
            public WebElement disableRecDate;
            
            @FindBy(how = How.XPATH,using = "//label[text()='Protective Marker Id']/ancestor::div[1]//span[@class='validationMessage']")
            public WebElement protectiveMarkerErrorMsg;
            
            @FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='List all my checked-out documents']")
            public WebElement editableDocumentButton;
            
            @FindBy(how = How.XPATH,using = "//button[@id='btn-CoreFields']")
            public WebElement coreFieldBtn;
            
            @FindBy(how = How.XPATH,using = "//button[text()='General']")
            public WebElement generalBtn;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='fileSystem']")
            public WebElement ddFileSystem;
            
            @FindBy(how = How.XPATH,using = "//textarea[@id='PageComment']")
            public WebElement commentTxtForBatch;
            
            @FindBy(how = How.XPATH,using = "//div[contains(@class,'pageIcon pageComment')]//i")
            public WebElement commentIconOnBatchIndexing;
            
            @FindBy(how = How.XPATH,using = "//div[contains(@class,'navbar-header-title')]//p[text()='Batch']")
            public WebElement batchPageHeader;
            
            @FindBy(how = How.XPATH,using = "//div[contains(@class,'navbar-header-title')]//p[text()='Batches']")
            public WebElement batchPageHeaderList;
            
            @FindBy(how = How.XPATH,using = "//table[@id='IndexedDocumentListDatatable']/tbody/tr[1]")
            public WebElement indexDocumentListRow;
            
			@FindBy(how=How.XPATH,using="//button[@data-id='MailOptionId']")
			public WebElement mailOptionDD;
			
            @FindBy(how = How.XPATH,using = "//*[text()='General']")
            public WebElement generalTab;
            
            @FindBy(how = How.XPATH,using = "//input[@id='pendingPeriod']")
            public WebElement pendingPeriodGet;
            
            
//            @FindBy(how = How.XPATH,using = "//table[@id='searchResultDataTable']//td")
//            public WebElement verifyEmptyTable;												//created by sagar on 27.07.2023
// 
//            @FindBy(how = How.XPATH,using = "//table[@id='searchResultIntrayDataTable']//td")
//            public WebElement verifyEmptyIntrayTable;
            
            public WebElement folderRefCapturePageTxt; 
            public WebElement folder2RefCapturePageTxt;
            public WebElement folder3RefCapturePageTxt;
            
    private String tmpTagArea = "//div[@class='selectize-dropdown-content']/div[text()='tag']",
            tmpNavigateBatch = "//div[@data-group='Navigate']//button[@data-original-title='%s']",
            tmpTextFolderRef="//input[@id='%s_Ref']",
            tmpTextFolderRefEntityPage = "//input[@title='%s']",
            tmpCaptureFieldLabel="//label[text()='%s']/ancestor::div[1]//span";
    private static String accRefLabel = "Account Ref";
    private static final String GENERAL_SECT = "General";  
	
	public CapturePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
	}
			
	
	/**
	 * Click on select search tab
	 */
	public void selectSearchTab() {
		try {
			navigationMenu.clickOnTab("Search");
		}
		catch(UnhandledAlertException f){
			try {
			//Sometimes if existing form is open and we click on search
			new AlertHelper(driver).acceptAlert();
			navigationMenu.clickOnTab("Search");
			}
	     catch (NoAlertPresentException e) {
	        e.printStackTrace();
	      
	     }
		 }
	}
	
	public void acceptAlertIfPresent() {
			try {
			new AlertHelper(driver).acceptAlert();
			}
	     catch (NoAlertPresentException e) {
	        e.printStackTrace();
	      
	     }
	}
	
	
	 public WebElement findDynamicElement(String xpath, String variable) {
	  log.info("Driver value is "+driver ); return
	  driver.findElement(By.xpath(String.format(xpath, variable))); 
	 }
	 
	
	/**
	 * It click on document icon under capture tab
	 */
	public void clickOnDocumentCaptureBtn(){
		//navigationMenu.clickOnMenuButtonAtTop(documentCaptureBtn, documentCaptureLst);
		navigationMenu.clickOnIconMenu("Capture a new document into the system", "Capture");
		waitHelper.waitForElement(indexDocumentBtn, 10);
	}
	
	/**
	 * Click on capture tab
	 * @return it will return CapturePage object
	 */
	public CapturePage clickOnCaptureTab() {
		waitHelper.waitForElement(captureTab,10);
		click(captureTab, "Clicking on capture tab");
		return new CapturePage(driver);
	}
	

	
	
	/** --- Add entity/ Add reference page related methods------ */
	/**
	 * Click on add entity button and select entity name from dropdown
	 * @param entityName Entity name add acc, add miscm add prop
	 */
	public void clickOnAddEntityAndSelect(String entityName) {
		addEntityBtn.click();
		String locator = "//ul[@role='menu']/li/a[text()='"+entityName+"']";
		click(driver.findElement(By.xpath(locator)),"Clicking on entity "+entityName);
		navigationMenu.waitForIcon("Save changes","Actions");
	}
	
	/**
	 * Get navbar title text
	 * @return text of navbar
	 */
	public String getNavbarTitle() {
		waitHelper.waitForElement(navbarTitleLbl,10);
		return navbarTitleLbl.getText();
	}
	
	/**
	 * Get tab name
	 * @return tab name text
	 */
	public String getTabTitle() {
		waitHelper.waitForElement(tabTitleLbl,10);
		return tabTitleLbl.getText();
	}
	
	/**
	 * Enter folder reference on add folder reference page
	 * @param folderName folderName like Folder1, Folder2, Folder3
	 * @param foldRefValue value with which you want to create reference
	 */
	public void enterFolderRef(String folderName,String foldRefValue) {
		WebElement folderRefEle = driver.findElement(By.xpath(String.format(tmpTextFolderRef, folderName)));
		sendKeys(waitHelper.waitForElement(folderRefEle,10),foldRefValue,"Enetering folder ref value = "+foldRefValue);
	}
	
	/**
	 * Enter folder raference based on enity name on add enity page
	 * @param entityName this entity name is title attribute of entity input e.g Account Ref
	 * @param foldRefValue Value with which you want to create entity
	 */
	public void enterFolderRefEnityPage(String entityName,String foldRefValue) {
		WebElement folderRefEle = driver.findElement(By.xpath(String.format(tmpTextFolderRefEntityPage, entityName+" Ref")));
		sendKeys(waitHelper.waitForElement(folderRefEle,10),foldRefValue,"Enetering folder ref value = "+foldRefValue);
	}
	
	/**
	 * Enter Account on add account ref page from capture form  
	 * @param fold1RefValue The value with which you want to create reference
	 */
	public void enterFolder1Ref(String fold1RefValue) {
		enterFolderRef("Folder1",fold1RefValue);
	}
	
	/**
	 * Expand folder1 ref section and enter folder1 ref value
	 * @param fold1RefValue Value
	 */
	public void enterFolder1RefEntitySearchPage(String fold1RefValue) {
		navigationMenu.expandAccordionIfCollapsed(GENERAL_SECT);
		enterFolderRef("Folder1",fold1RefValue);
	}
	
	/**
	 * Enter folder2 ref vlue in add folder ref page
	 * @param fold2RefValue
	 */
	public void enterFolder2Ref(String fold2RefValue) {
		enterFolderRef("Folder2",fold2RefValue);
	}
	
	/**
	 * Enter folder2 ref vlue in add folder entity search page
	 * @param fold2RefValue
	 */
	public void enterFolder2RefEntitySearchPage(String fold2RefValue) {
		navigationMenu.expandAccordionIfCollapsed(GENERAL_SECT);
		enterFolderRef("Folder2",fold2RefValue);
	}
	
	/**
	 * Enter folder3 ref vlue in add folder ref page
	 * @param fold3RefValue
	 */
	public void enterFolder3Ref(String fold3RefValue) {
		enterFolderRef("Folder3",fold3RefValue);
	}
	
	/**
	 * Enter folder3 ref vlue in add folder entity search page
	 * @param fold3RefValue
	 */
	public void enterFolder3RefEntitySearchPage(String fold3RefValue) {
		navigationMenu.expandAccordionIfCollapsed(GENERAL_SECT);
		enterFolderRef("Folder3",fold3RefValue);
	}
	
	/**
	 * Enter and select dummy ref
	 * @param entityName
	 * @return dummy reference name
	 */
	public String enterAndSelectDummyRef(String entityName) {
		navigationMenu.clickOnCaptureTab();
		clickOnAddEntityAndSelect(entityName);
		waitHelper.waitForElement(btnDummyRef, 20).click();
		waitHelper.waitForElementClickable(driver, ddBtnDummytRef, 20).click();
		String dummyRef = txtDummyRef.getAttribute("InnerText");
		return dummyRef;
	}
	
	/**
	 * Save of add reference and waiting for capture page
	 */
	public void clickOnSaveButton() {
		new WaitHelper(driver).waitForElementClickable(driver, saveBtn, 10);
		click(saveBtn,"Clicking on save button");
		waitHelper.waitForElement(capturePageTitleTxt,20);//Waiting for navigation since save reference takes time
	}
	
	/**
	 * 
	 * @return
	 */
	public String selectAvailableDummyRef() {
		return null;
	}
	
	
	/**
	 * Click on folder ref save button
	 */
	public void clickOnFolderRefSaveButton() {
	  try {
		click(saveBtn,"Clicking on save button");
		sleepFor(3000);
	    }
		catch (Exception e) {log.info("Exception while saving reference");	}
		waitHelper.waitForElementVisible(new LoginPage(driver).welcomeTitleText, 30, 1);
	}
	
	/**
	 * Add folder reference 
	 * @param folderEntityName
	 * @param folderRef
	 */
	public void addFolderReference(String folderEntityName,String folderRef) {
		clickOnCaptureTab();
		clickOnAddEntityAndSelect(folderEntityName);
		enterFolder1Ref(folderRef);
		clickOnFolderRefSaveButton();
	}
	
	/**
	 * 
	 * @param folderEntityName
	 * @return
	 */
	public String addFolderReferenceWithDummyAvailable(String folderEntityName) {
		clickOnCaptureTab();
		clickOnAddEntityAndSelect(folderEntityName);
		String dummyRef= selectAvailableDummyRef();
		clickOnFolderRefSaveButton();
		return dummyRef;
	}
	/** ---End of Add entity page related methods------ */
	
	
	
	public void openCaptureForm() {
		//clickOnCaptureTab();
		//clickOnDocumentCaptureBtn();
		waitHelper.waitForElement(indexDocumentBtn, 10);
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean isIndexDocumentButtonPresent() {
		return waitHelper.isElementDisplayedByEle(indexDocumentBtn);
	}
	
	/**
	 * 
	 */
	public void clickOnFileUploadIcon() {
		fileUploadIcon.click();
	}
	
	/**
	 * 
	 * @param filepath
	 * @param filename
	 * @return
	 */
	public String uploadFileAngGetFileName(String filepath, String filename){
		String completeFilePath = filepath+filename;
		System.out.println("Complete file path is "+completeFilePath);
		String autoITExecutable = ObjectReader.reader.getAutoITExePath() +" "+completeFilePath; 
		String actualFileName="";
		try {
			Thread.sleep(2000);
		    Runtime.getRuntime().exec(autoITExecutable);
		    Thread.sleep(2000);
		    actualFileName =  firstUploadedFileName.getText();
		} catch (Exception e) {
		    log.info("Exception while uploading file at "+completeFilePath+" Exception is " + e);
		}
		
		return actualFileName;
	}
	
	/**
	 * 
	 * @param inputText
	 */
	public void selectRoutingTypeDD(String inputText,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(routingTypeDD, "Clicking on routing type drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, routingTypeDD551OnWards,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, routingTypeDD);
		}	
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRoutingTypeDD() {
		return new DropdownHelper(driver).getDropdownSelectionValue(routingTypeDD);
	}
	
	/**
	 * 
	 */
	public void waitForRoutingToDDClickable() {
		waitHelper.waitForElementClickable(driver, routeToDD, 10);
	}
	
	/**
	 * 
	 * @param inputText
	 */
	public void selectRouteToDD(String inputText,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			try {
				Thread.sleep(3000);
				click(routeToDD, "Cliking on routing To drop down");
				new DropdownHelper(driver).selectFromAutocompleDD(inputText, routeToDDR551Onwards,getEnvVariable);
				new JavaScriptHelper(driver).scrollToTop();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}else {
			try {
				Thread.sleep(3000);
				new DropdownHelper(driver).selectFromAutocompleDD(inputText, routeToDD);
				new JavaScriptHelper(driver).scrollToTop();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	 
	/**
	* @return
	 */
	public String getRouteToDD() {
		waitHelper.waitForElementClickable(driver,routeToDD,10);
		return new DropdownHelper(driver).getDropdownSelectionValue(routeToDD);
	}
	
	/**
	 * 
	 * @param inputText
	 */
	public void selectFileSystemDD(String inputText,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(fileSystemDD, "Clikcing on FS drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, fileSystemDD,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, fileSystemDD);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileSystemDD() {
		return new DropdownHelper(driver).getDropdownSelectionValue(fileSystemDD);
	}
	
	/**
	 * 
	 * @param inputText
	 */
	public void selectDocumentTypemDD(String inputText,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(documentTypeDD, "Clikcing on document type drop down");
			new DropdownHelper(driver).selectFromAutocompleDD(inputText, documentTypeDDR551Onwards,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDD(inputText, documentTypeDD);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDocumentTypemDD() {
		return new DropdownHelper(driver).getDropdownSelectionValue(documentTypeDD);//locator changed by amit
	}
	
	/**
	 * 
	 * @param inputText
	 */
	public void selectProtectiveMarkerDD(String inputText,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(protectiveMarkerDD, "Clicking on protective marker drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, protectiveMarkerDD,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, protectiveMarkerDD);
		}	
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProtectiveMarkerDD() {
		return new DropdownHelper(driver).getDropdownSelectionValue( protectiveMarkerDD);
	}
	
	/**
	 * 
	 * @param inputText
	 */
	public void enterDocRef(String inputText) {
		sendKeys(docRefTxt,inputText,"Enetering "+inputText+" doc ref");
	}
	
	/**
	 * Get entered doc ref
	 * @return
	 */
	public String getDocRef() {
		return docRefTxt.getAttribute("value");
	}
	
	/**
	 * Enter doc ref2 on edit metadata
	 * @param inputText
	 */
	public void enterDocRef2(String inputText) {
		sendKeys(docRef2Txt,inputText,"Enetering "+inputText+" docref2");
		new ActionHelper(driver).pressTab();
	}
	
	/**
	 * 
	 */
	public void setDateReceivedToToday() {
		click(dateReceivedPicker,"Clicking on date received date picker");
		click(todaysDateCal,"Clicking on todays date calendar");
		new ActionHelper(driver).pressEscape();
		new ActionHelper(driver).pressTab();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDateReceivedToToday() {
		return dateReceivedPicker.getAttribute("Value");
	}
	
	/**
	 * 
	 * @param tagName
	 */
	public void setTag(String tagName) {
		click(tagAreaTxt,"Clicking on tagAreaTxt");
		String fnlTagArea = tmpTagArea.replaceAll("\\btag\\b", tagName);
		new ActionHelper(driver).moveToElementAndClick(By.xpath(fnlTagArea));
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTextAreaOfTag() {
		return tagAreaTxt.getText();
	}
	
	public void setAvailableTag() {
		tagAreaTxt.clear();
		tagAreaTxt.click();
		new ActionHelper(driver).pressDownArrow();
		new ActionHelper(driver).pressEnter();
	}
	
	public String getAvailableTag() {
		//Actual tag text is add with space e.g thief      x
		String tagText = selectedTagLbl.getAttribute("innerText");
		String noSpaceTag = tagText.replaceAll("\\s", "");//Replacing all blank space
		String fnlTagText = noSpaceTag.substring(0, noSpaceTag.length()-1); //Removing x
		log.info("Available tag text is "+fnlTagText);
		return fnlTagText;
	}
	
	public void clickOnRemoveTag() {
		click(lnkTagRemove, "clicking on remove tag");
	}
	
	public void enterFolderRefCapturePageAndSearchWaitForAddFolder(String folderRefValue, String label) {
		folderRefCapturePageTxt= findDynamicElement(folderRefCaptureTextXpath,label);
		WebElement f1ref = folderRefCapturePageTxt;
		f1ref.sendKeys(folderRefValue);
		new ActionHelper(driver).pressTab();
		while(!waitHelper.isElementDisplayedByEle(dialogTitleId)) {
			f1ref.clear();
			sendKeys(f1ref,folderRefValue,"Enetring folder ref value "+folderRefValue);
			new ActionHelper(driver).pressTab();
			sleepFor(2000);
		}
			sleepFor(1000);
			new WindowHelper(driver).clickOkOnPopup();
		  //enterFolder1Ref(folderRefValue);
			sleepFor(1000);
		  clickOnSaveButton();
		
	}
	
	public boolean enterFolder1RefCapturePageAndSearch(String folder1refValue, String label) throws InterruptedException {
		folderRefCapturePageTxt= findDynamicElement(folderRefCaptureTextXpath,label);
		WebElement f1ref = folderRefCapturePageTxt;
		//f1ref.sendKeys(folder1refValue);							//changed
		sendKeys(f1ref, folder1refValue, "Entering value of folder ref");
		new ActionHelper(driver).pressTab();
		while(!waitHelper.isElementDisplayedByEle(f1ref.findElement(By.xpath("./../following-sibling::span")))) {
			f1ref.clear();
			sendKeys(f1ref,folder1refValue,"Enetring folder1 ref ");
			new ActionHelper(driver).pressTab();
		}
		sleepFor(1000);
		boolean flag=new WindowHelper(driver).isPopupDisplayed();
		return !flag;
	}
	
	/*
	 * It is used to enter addition index details for folder1 ref
	 * 
	 */
	public boolean enterFolder1RefCapturePageAndSearchForAdditionalIndexing(String folder1refValue, String label) throws InterruptedException {
		WebElement folderRefCapturePageTxtForAdditional= findDynamicElement(folderRefCaptureTextXpathForAdditonal,label);
		WebElement f1ref = folderRefCapturePageTxtForAdditional;
		f1ref.sendKeys(folder1refValue);
		new ActionHelper(driver).pressTab();
		while(!waitHelper.isElementDisplayedByEle(f1ref.findElement(By.xpath("./../following-sibling::span")))) {
			f1ref.clear();
			sendKeys(f1ref,folder1refValue,"Enetring folder1 ref ");
			new ActionHelper(driver).pressTab();
		}
		sleepFor(1000);
		boolean flag=new WindowHelper(driver).isPopupDisplayed();
		return !flag;
	}
	
	public String getFolder1RefValueCapturePage() {
		folderRefCapturePageTxt=findDynamicElement(folderRefCaptureTextXpath, "Folder1_Ref");
		waitHelper.waitForElement(folderRefCapturePageTxt,10); 
		String id =folderRefCapturePageTxt.getAttribute("id");
		String jscommand="return document.getElementById('"+id+"').value";
		return new JavaScriptHelper(driver).executeScriptGetResult(jscommand);
	}
	
	public String getFolder1PersonNameValueCapturePage() {
		WebElement f1ref = folderRefCapturePageTxt;
		return f1ref.findElement(By.xpath("./../following-sibling::span")).getText();
	}
	
	public void enterFolder2RefCapturePage(String folder2ref) {
		folder2RefCapturePageTxt = findDynamicElement(folderRefCaptureTextXpath, "Misc Ref");
		sendKeys(folder2RefCapturePageTxt,folder2ref,"Entering folder 2 reference = "+folder2ref);
		new ActionHelper(driver).pressTab();
	}
	
	public void enterFolder3RefCapturePage(String folder3ref) {
		folder3RefCapturePageTxt = findDynamicElement(folderRefCaptureTextXpath, "Prop Ref");
		sendKeys(folder3RefCapturePageTxt,folder3ref,"Entering folder 3 reference ="+folder3ref);
		new ActionHelper(driver).pressTab();
	}
	
	
		

	
	public String getPriorityValue() {
		return priorityTxt.getAttribute("value");
	}
	
	public void incrementPriority() {
		click(increasePriorityIcn,"Incrementing priority");
	}
	
	public void decrementPriority() {
		click(decreasePriorityIcn,"Decrementing priority");
	}
	
	public void clickOnIndexDocumentWaitForIndexPopup() {
        navigationMenu.clickOnMenuButtonAtTop(indexDocumentBtn, indexDocumentBtnLst);
         //new WindowHelper(driver).waitForPopup("Index");
    }												  
	public void clickOnIndexDocument() {
		//navigationMenu.clickOnMenuButtonAtTop(indexDocumentBtn, indexDocumentBtnLst);
		navigationMenu.clickOnIconMenu("Index the following changes", "Actions");
	}
	
	public boolean isConfirmIndexShown() {
		return waitHelper.isElementDisplayedByEle(confirmIndexMsgTxt);
	}
	
	public void clickOkOnCofirmIndexDocument() {
		waitHelper.waitForElement(confirmIndexMsgTxt,10);
		new WindowHelper(driver).clickOkOnPopup();
		waitTillInvisiblityOfBusyIcon(10);
	}
	
	public boolean isTagDisplayed() {
		return waitHelper.isElementDisplayedByEle(selectedTagdiv);//changed by amit
	}

	public void searchWithCriteria(String label, String searchValue) {
		navigationMenu.clickOnTab("Search"); //Change this if it causes prblm
		click(searchDD,"Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver,searchDDInput,10),label,"Enetering in search ="+label);
		click(waitHelper.waitForElementClickable(driver,searchDDMatch,10),"Clicking on dropdown matching value");//check why failing
		sendKeys(searchTxt,searchValue,"Entering in searchtext = "+searchValue);
	}

	public void clickOnTeamsUnderAdmin() {
		// TODO Auto-generated method stub
	}
	
	//Remove this method since not referred anywhere
	public void clickOnMenuItem() {
		By restrictionBtnXpath = By.xpath("//button[contains(text(),'Restriction')]"),
				restrictionGroupBtnXpath = By.xpath("//li/button[contains(text(),'Restrictions')]/ancestor::div[contains(@class,'btn-group')]/div/button"),
				restrictionMenuBtnXpath = By.xpath("//li/button[contains(text(),'Restrictions')]");
		
		if(waitHelper.isElementDisplayedByEle(driver.findElement(restrictionBtnXpath))) {
			click(driver.findElement(restrictionBtnXpath),"Clicking on restrication button ");
		}
		else {
			WebElement grpBtnEle = driver.findElement(restrictionGroupBtnXpath);
			WebElement menuBtnEle = driver.findElement(restrictionMenuBtnXpath);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].scrollIntoView()",grpBtnEle);
			click(waitHelper.waitForElementClickable(driver,driver.findElement(restrictionGroupBtnXpath), 10),"Clicking on restrictin grp btn");
			executor.executeScript("arguments[0].scrollIntoView()",menuBtnEle);
			click(waitHelper.waitForElementClickable(driver,driver.findElement(restrictionMenuBtnXpath), 10),"Clicking on restriction menu button");
			}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public DocumentToolsPage clickOnIntrayListBtn() {
		try {
		Thread.sleep(1000);
		click(intrayListBtn,"Clicking on Intray list button");
		}
		catch(UnhandledAlertException ex){
			acceptAlertIfPresent();
			click(intrayListBtn,"Clicking on Intray list button");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//navigationMenu.waitForFilterTextBox();			//commenting because element is not visible and handled in clickOnFirstItemOfIntray
		return new DocumentToolsPage(driver);
	}
	
	/**
	 * clickOnDocumentListBtn
	 */
	public void clickOnDocumentListBtn() {
	    sleepFor(1000);
		click(docListBtn,"Clicking on document list button");	
	}
	
	/** In tray action items ****/
	
	public void clickOnFirstItemOfIntray() {
		boolean status=verifyFilterTextBoxInIntray();				//Adding because filter text box is not visible
		if(status) {
			waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
			//intratY
			if(!intrayItemRow.getAttribute("class").contains("selected"))
				click(intrayItemRowCell,"Clicking on first item of intray");
		}else {
			sleepFor(1000);
			refreshPage();
			waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
			//intratY
			if(!intrayItemRow.getAttribute("class").contains("selected"))
				click(intrayItemRowCell,"Clicking on first item of intray");
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isChangeMailStatusVisible() {
		boolean ischangeEmailDisplayed = false;
		
		try {
		waitHelper.waitForElement(driver.findElement(By.id("mailStatusModal")),10);
		ischangeEmailDisplayed = waitHelper.isElementDisplayedByEle(changeMailStatusLbl);
		}
		catch(Exception e) {
			log.info("Error while waiting for change mail status"+e);
		}
		return ischangeEmailDisplayed;
	}
	
	/**
	 * 
	 * @param status
	 * @return
	 * @throws InterruptedException
	 */
	public boolean changeEmailStatus(String status) throws InterruptedException {
		clickOnChangeEmailStatusButton();
		//waitTillElementInvisible(busyDialogIcon);
		boolean statusChanged = false;
		
		if(isChangeMailStatusVisible()) {
			waitHelper.waitForElement(changeMailStatusLbl, 10);
			//click(changeMailsStatusDDBtn,"Clicking on change mail status drop down btn");
			new DropdownHelper(driver).selectFromAutocompleDDInPopup(status,changeMailsStatusDDBtn);     //Not working for R540 
			//new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(status, changeMailsStatusDDBtn);
			clickOnOkMailStatusButton();			//changed by amit
			//sleepFor(2000);
			new WaitHelper(driver).waitForElement(changeMailStatusLbl, 5);
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			sleepFor(2000);
			statusChanged = true;
		}
		return statusChanged;
	}
	
	/*
	 * Added this method for R540 change mail status
	 */
	public boolean changeMailStatusUsingSelectClass(String visibleTxt) {
		clickOnChangeEmailStatusButton();
		boolean statusChanged = false;
		
		if(isChangeMailStatusVisible()) {
		waitHelper.waitForElement(changeMailStatusLbl, 10);
		changeMailsStatusDDBtn.click();
		
		//new DropdownHelper(driver).selectFromDropdown(changeMailStatusSelectDDBtn, visibleTxt);
		
		driver.findElement(By.xpath("//span[text()='"+visibleTxt+"']/ancestor::a[1]")).click();
		sleepFor(1000);
		clickOnOkMailStatusButton();			//changed by amit
		
		new WaitHelper(driver).waitForElement(changeMailStatusLbl, 5);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(2000);
		statusChanged = true;
		}
		return statusChanged;
	}
	
	public boolean changeMailStatusUsingSelectClassAndVerifyPendingPeriod(String visibleTxt,String pending) {
		clickOnChangeEmailStatusButton();
		boolean statusChanged = false;
		
		if(isChangeMailStatusVisible()) {
		waitHelper.waitForElement(changeMailStatusLbl, 10);
		changeMailsStatusDDBtn.click();
		
		//new DropdownHelper(driver).selectFromDropdown(changeMailStatusSelectDDBtn, visibleTxt);
		
		driver.findElement(By.xpath("//span[text()='"+visibleTxt+"']/ancestor::a[1]")).click();
		sleepFor(3000);
		
		String getPendingValue = pendingPeriodGet.getAttribute("Value");

		clickOnOkMailStatusButton();			//changed by amit
		new WaitHelper(driver).waitForElement(changeMailStatusLbl, 5);
		AssertionHelper.verifyTextContains(String.valueOf(getPendingValue), String.valueOf(pending));
		sleepFor(2000);
		statusChanged = true;
		}
		return statusChanged;
	}
	
	public void clickOnChangeEmailStatusButton() {
		sleepFor(2000);
		navigationMenu.clickOnMenuButtonAtTop(changeMailStatusbtn, changeMailStatusLstBtn);
		//new ActionHelper(driver).moveToElementAndClick(changeMailStatusbtn);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitHelper.waitForElementClickable(driver, cancelMailStatusBtn, 10);
	}
	
	public void clickOnCancelMailStatusButton() {
		click(cancelMailStatusBtn,"CancelEmailStatusButton");
	}
	
	public void clickOnOkMailStatusButton() {
		click(okMailStatusBtn,"OkEmailStatusButton");
	}
	
	
	public String getMailStatusOfDocument() {
		String status = "",index="";
		try {
		index = mailStatusColumn.getAttribute("data-column-index");
		}
		catch(Exception e) {
			navigationMenu.selectColumnVisiblityFromColumnConfig("Mail Status");
			index = mailStatusColumn.getAttribute("data-column-index");
		}
		int actualIndex = Integer.parseInt(index)+1;
		status = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		return status;
		}
	
	public String getFolder2RefOfDocument() {
		String value = "";
		String index;
		try {
		index = folder2refDocListColumn.getAttribute("data-column-index");
		}
		catch (NoSuchElementException e) {
			navigationMenu.selectColumnVisiblityFromColumnConfig("Misc Ref");
			index = folder2refDocListColumn.getAttribute("data-column-index");
		}
		int actualIndex = Integer.parseInt(index)+1;
		value = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		log.info("Getting folder 2 ref document and its value = "+value);
		return value;
		}
	
		
	
	public String getDocumentTypeOfDocument() {
		String value = "";
		String index = documentTypeDocListColumn.getAttribute("data-column-index");
		int actualIndex = Integer.parseInt(index)+1;
		value = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		log.info("Getting document type and its value = "+value);
		return value;
		}
	
	public String getMailStatusForFirstItem() {
		String mailStatus="";
		waitHelper.waitForElement(inTrayPageTitleTxt,10);
		if (waitHelper.isElementDisplayedByEle(mailStatusColumn)){   //Previously is element present
			navigationMenu.selectColumnVisiblityFromColumnConfig("Mail Status");
		}
		mailStatus = getMailStatusOfDocument();	
		return mailStatus;
	}
	
	public boolean dragAndDropMailStatusToFirstPosition() {
		new ActionHelper(driver).dragAndDropElement(mailStatusColumn, inTrayItemFirstColumn);
		log.info("Drag and drop is successfull");
		return true;
	}
	
	
	public String getSuccessfullPopupMessage() {
		try {
			waitTillInvisiblityOfBusyIcon(20);
			waitHelper.waitForElement(successullyIndexMsg,20);
			return new WindowHelper(driver).getPopupMessage();
		}
		catch(Exception e) {
			log.info("Something went wrong while getting popup message"+e);
		}
		return null;
	}
	
	
	public String getSuccessfullReindexPopupMessage() {
		try {
			new WindowHelper(driver).waitForPopup("ReIndex");
			return new WindowHelper(driver).getPopupMessage();
		}
		catch(Exception e) {
			log.info("Something went wrong while getting popup message"+e);
		}
		return null;
	}
	
	public void clickOkOnSuccesfullPopup() {
		waitTillInvisiblityOfBusyIcon(10);
			new WindowHelper(driver).waitForPopup("Index");
			new WindowHelper(driver).clickOkOnPopup();	
	}
	
	public void clickOkOnSuccesfullReIndexPopup() {
        waitTillInvisiblityOfBusyIcon(10);
        new WindowHelper(driver).waitForPopup("ReIndex");
        new WindowHelper(driver).clickOkOnPopup();
        new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 15);					//Remove this line for smoke running
    }
	
	/**
	 * Capture document with provided params
	 * @param filepath can be null
	 * @param filename can be null
	 * @param docType can be null
	 * @param docRef
	 * @param accRef can be null
	 * @throws InterruptedException 
	 */
	public void navigateAndCaptureDocWithParameters(String filepath,String filename,String docType, String docRef ,String accRef,String userType, String userName,String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To User" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		filename= filename == null ? "file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		selectRoutingTypeDD(userType,getEnvVariable);
		selectRouteToDD(userName,getEnvVariable);
		enterDocRef(docRef);
		
		if(accRef!=null) {
			boolean success =enterFolder1RefCapturePageAndSearch(accRef, accRefLabel);
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  enterFolder1Ref(accRef);
			  clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, routeToDD, 20);
			}
		}
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(1000);
		clickOnIndexDocument();
		
		//Comment this to handle notification
		// if((docType.contains("Default") || docType.contains("ScanDoc"))&& baseUrl.contains("Lives")) {
		 // clickOkOnCofirmIndexDocument(); }
		 //waitHelper.waitForElement(successullyIndexMsg,20);
		 //clickOkOnSuccesfullPopup();
		
		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);		
			 clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		//Thread.sleep(2000);				//added beacause file is failing pls remove after some time	
		
		 //getApplicationUrl();
	}
	
	//Overloading for less params
	public void navigateAndCaptureDocWithParameters(String filepath,String filename,String docType, String docRef ,String accRef,String getEnvVariable) throws InterruptedException {
    navigateAndCaptureDocWithParameters(filepath,filename, docType, docRef, accRef,null,null,getEnvVariable);
	}

	/**
	 * Capture document with provided params and creating reference
	 * @param filepath
	 * @param filename
	 * @param docType
	 * @param docRef
	 * @param accRef
	 * @throws InterruptedException 
	 */
	public void navigateAndCaptureDocWithCreateRefParameters(String docType, String docRef ,String accRef,String miscRef,String propRef, boolean createRef,String getEnvVariable) throws InterruptedException {
		String userType =  "To User", userName = ObjectReader.reader.getUserName(), 
		 //filepath="C:\\users\\amit.khambad\\Projectdata\\Testdata\\Tiffiles\\",	
		filepath= ObjectReader.reader.getTestDocFolderPath(),
		filename="file1.tif";
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		selectRoutingTypeDD(userType,getEnvVariable);
		selectRouteToDD(userName,getEnvVariable);
		enterDocRef(docRef);
		
		if(createRef && !accRef.isEmpty()) {
		createReferenceIfNotAvailable(accRef,accRefLabel);
		}
		if(createRef && !miscRef.isEmpty()) {
		createReferenceIfNotAvailable(miscRef,"Misc Ref");
		}
		if(createRef && !propRef.isEmpty()) {
			createReferenceIfNotAvailable(propRef,"Property Ref");
			}
		Thread.sleep(3000);
		
		/*String getSelectedDocmentType=getDocumentTypemDD();
		if(!getSelectedDocmentType.equals(docType)) {
			new JavaScriptHelper(driver).scrollToElement(documentTypeDD);
			selectDocumentTypemDD(docType);
		}*/				 
		//Comment this to handle notification	R531	
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(2000);
		
		clickOnIndexDocument();
//		 if((docType.contains("Default") || docType.contains("ScanDoc")) && baseUrl.contains("Lives")) {
//		  clickOkOnCofirmIndexDocument(); }
//		 waitHelper.waitForElement(successullyIndexMsg,20);
//		 clickOkOnSuccesfullPopup();
		try {
			waitHelper.waitForElement(successullyIndexMsg,10);
			clickOkOnSuccesfullPopup();
			getApplicationUrl(true);
		} catch (Exception e) {
			getApplicationUrl(true);
		}
		//getApplicationUrl(true);
	}
	
	/**
	 * Create Reference from capture page
	 * @param refValue
	 * @param refLabel
	 * @return
	 * @throws InterruptedException
	 */
	public boolean createReferenceIfNotAvailable(String refValue, String refLabel) throws InterruptedException {
		boolean success =enterFolder1RefCapturePageAndSearch(refValue,  refLabel );
		log.info("Flag value of success is "+success);
		if(!success) {
			new WindowHelper(driver).clickOkOnPopup();
			navigationMenu.waitForIcon("Cancel change");
		  if(refLabel.equals(accRefLabel)) {
		  enterFolder1Ref(refValue);
		  }
		  else if(refLabel.equals("Misc Ref")) {
		  enterFolder2Ref(refValue);
		  }
		  else if(refLabel.equals("Prop Ref")) {
		  enterFolder3Ref(refValue);
		  }
		  clickOnSaveButton();
		  success=true;
		}
		return success;
	}
	
	
	public void selectActionTakenForMemo(String notesText) {
		try {
	    click(icnActionTakenAddMemo, "Click on action taken icon memo");
		new WindowHelper(driver).waitForModalDialog("Standard Notes");
		sleepFor(1000);
		sendKeys(txtNotesStandardTextMemo, notesText, "Entering text in standard notes "+notesText);//locator changed by amit
		sleepFor(3000);
		new ActionHelper(driver).pressTab();
		new ActionHelper(driver).pressEnter();
		//new WindowHelper(driver).clickOnModalFooterButton("Add ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnMoveBatchToFileSystem() {
		navigationMenu.clickOnIcon("Change fileSystem for selected item", "Actions");
		new WindowHelper(driver).waitForModalDialog("File System Selection");
	}
	
	public boolean isModalForFileSystemSelectionVisible() {
		return new VerificationHelper(driver).isElementDisplayedByEle(btnFileSystemSelectionBatch);
	}
	
	public String getFirstBatchRefName() {
		return new VerificationHelper(driver).getText(lnkFirstBatchRef);
	}
	
	public void SelectFileSystemFromDDBatch(String fileSystem) {
		new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(fileSystem, btnFileSystemSelectionBatch);
		new WindowHelper(driver).clickOnModalFooterButton("Update");
	}
	
	public void navigateToBatchIndex() throws InterruptedException {
		 navigationMenu.clickOnCaptureTab();
		 navigationMenu.clickOnIcon("Batch indexing", "Capture");
		  Thread.sleep(1000);
		  //waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		  new NavigationMenu(driver).waitForFilterTextBox();
	}

	/**
	 * 
	 * @throws InterruptedException
	 */
	public void clickOnShowHideMetadata() throws InterruptedException {
		navigationMenu.clickOnIcon("Show/Hide metadata", "Document");
		Thread.sleep(2000);	
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMetadataShown() {
		return new VerificationHelper(driver).isElementDisplayedByEle(frmMetadataBatch);
	}

	/**
	 * 
	 * @param iconTooltip
	 * @return
	 */
	public boolean clickOnNavigateIcon(String iconTooltip) {
		WebElement iconEle = driver.findElement(By.xpath(String.format(tmpNavigateBatch, iconTooltip)));
		if(new VerificationHelper(driver).isElementEnabled(iconEle)) {
			click(iconEle, "Element is enabled and clicking on icon "+iconTooltip);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPreviewDocumentNumber() {
		return icnNumberPreviewBatch.getText();
	}

	/**
	 * 
	 */
	public void clickOnAddIconInPreview() {
		click(icnAddPreviewBatch, "Clicking on icon add preview batch");
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSelectedDocumentNumber() {
		return icnDocumentSelectedBatch.getAttribute("data-page-number");
	}


	/**
	 * 
	 */
	public void clickOnCopyPreviousButton() {
		navigationMenu.clickOnIcon("Copy index details from previous capture", "Actions");
		//new WindowHelper(driver).waitForModalDialog("");
		//new WindowHelper(driver).clickOkOnPopup();
	}


	/**
	 * 
	 * @param batchDocRef
	 */
	public void enterDocRefInBatch(String batchDocRef) {
		String coreFieldsClass = btnCoreFieldsBatch.getAttribute("class");
		log.info("Button core fields value is "+coreFieldsClass);
		if(coreFieldsClass.equals("accordion")) {
			click(btnCoreFieldsBatch, "Clicking on according since its closed");
		}
		enterDocRef(batchDocRef);
	}


	/**
	 * 
	 */
	public void clickOnToggleAllFileSystemOption() {
		navigationMenu.clickOnIcon("Toggle All File Systems option for searching", "");;
	}


	/**
	 * 
	 * @param inputInDoc
	 */
	public void ClickOnCreateNewDoc(String inputInDoc) {
		navigationMenu.clickOnIcon("Create new text document", "Actions");
		sleepFor(1000);
		new WindowHelper(driver).waitForModalDialog("Create new text document");
		sendKeys(txaCreateNewDocCapture, inputInDoc, "Entering capture doc text area with input "+inputInDoc);
		new WindowHelper(driver).clickOnModalFooterButton("OK");//Added by amit	
	}


	/**
	 * 
	 * @return
	 */
	public String getMemoModePopupMessage() {
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup("MemoMode");
		String memoMsg = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		return memoMsg;
	}


	/**
	 * 
	 * @return
	 */
	public String getCopyDocumentPopupMessage() {
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup("Copy");
		String copyMsg = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		return copyMsg;
	}

	/**
	 * Navigate to Dummy reference under admin
	 */
    public void navigateToAddDummyReference() {
        navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Dummy Reference", "Folder");
        navigationMenu.waitForIconWithDataButton("Add", "Actions");
    }
    
    
    /**
     * Add dummy reference with given parameters
     * @param prefixName
     * @param length
     * @param folderName
     */
    public void addDummyReference(String prefixName, String length, String folderName) {
      navigationMenu.clickOnIconUnderAction("Add");
      navigationMenu.waitForIconWithDataButton("Save", "Actions");
      navigationMenu.sendKeys(txtPrefixDummyReference, prefixName, "Entered prefix as "+prefixName);//Replace with act content
      navigationMenu.sendKeys(txtCodeLengthDummyReference, length, "Entere length");
      new JavaScriptHelper(driver).scrollToBottom();
      new DropdownHelper(driver).selectFromAutocompleDD(folderName,btnEntityDropdownDummyReference);
      try {
			navigationMenu.clickOnIconUnderAction("Save");
			navigationMenu.waitForIconWithDataButton("Add", "Actions");
		} catch (Exception e) {

			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			sleepFor(1000);
			new NavigationMenu(driver).clickOnIconMenu("Cancel changes", "Action");
			new WindowHelper(driver).waitForPopup("Leave Page");
			sleepFor(1000);
			new WindowHelper(driver).clickOnModalFooterButton("No");
			sleepFor(1000);

		}
      
       
    }


    public boolean verifyDummyRefAdded(String folder1Prefix, String folder1Name) {
      navigationMenu.searchInFilter(folder1Prefix);
      String actualPrefixValue = navigationMenu.getColumnValueFromTable("Prefix");
      log.info("Actual prefix value "+actualPrefixValue);
      String actualFolderValue = navigationMenu.getColumnValueFromTable("Flexible Entity Name");
       log.info("Actual folder value "+folder1Name);
      boolean prefixMatched = folder1Prefix.equals(actualPrefixValue);
      boolean folderMatched = folder1Name.equals(actualFolderValue);
      return prefixMatched && folderMatched;
    }
	
	
	
    /*
     * Added navigation method for Document & Intray Tools
     */
    public void NavigateToDocumentOrIntray(String listname,String reference,String docRef) {
        if(listname.equalsIgnoreCase("Document Tools")) {
            getApplicationUrl();
            selectSearchTab(); 
            if(reference.equalsIgnoreCase("Doc Ref")) {
                log.info("Search with Doc Ref "+docRef);
                searchWithCriteria(reference, docRef);
            }else {
                log.info("Search with Account Ref "+docRef);
                searchWithCriteria(reference, docRef);
            }
            
            clickOnDocumentListBtn();
            clickOnFirstItemOfIntray();
            
        }else {
            getApplicationUrl();
            if(reference.equalsIgnoreCase("Doc Ref")) {
                log.info("Search with Doc Ref "+docRef);
                searchWithCriteria(reference, docRef);
            }else {
                log.info("Search with Account Ref "+docRef);
                searchWithCriteria(reference, docRef);
            }
            
            clickOnIntrayListBtn();
            clickOnFirstItemOfIntray();
        }
        
    }
    
    /*
     * Used to verify filter text box
     */
    public boolean verifyFilterTextBoxInIntray() {
    	return new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
    }
    
    
    /*
     * Used to capture the document with received date parameter 
     * pass days in string format
     */
    public void navigateAndCaptureDocWithDateParameters(String filepath,String filename,String docType, String docRef ,String accRef,String userType, String userName,String days,String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To User" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		 //filepath= filepath == null ? "C:\\users\\amit.khambad\\Projectdata\\Testdata\\Tiffiles\\": filepath;	
		 //filename= filename == null ?"file1.tif": filename;
		 filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		 filename= filename == null ?"file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		selectRoutingTypeDD(userType,getEnvVariable);
		selectRouteToDD(userName,getEnvVariable);
		enterDocRef(docRef);
		
		sleepFor(1000);
		dateReceivedPicker.click();
		new JavaScriptHelper(driver).clearText(dateReceivedPicker);
		
		String setReceivedDate=getDate(days,"Minus");
		dateReceivedPicker.sendKeys(setReceivedDate);
		sleepFor(1000);
		dateReceivedPicker.sendKeys(Keys.ENTER);
		new ActionHelper(driver).pressTab();
		
		
		if(accRef!=null) {
			boolean success =enterFolder1RefCapturePageAndSearch(accRef, accRefLabel);
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  enterFolder1Ref(accRef);
			  clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, routeToDD, 20);
			}
		}
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(1000);
		clickOnIndexDocument();
		
		//Comment this to handle notification
		// if((docType.contains("Default") || docType.contains("ScanDoc"))&& baseUrl.contains("Lives")) {
		 // clickOkOnCofirmIndexDocument(); }
		 //waitHelper.waitForElement(successullyIndexMsg,20);
		 //clickOkOnSuccesfullPopup();
		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);
			 clickOkOnSuccesfullPopup();
			getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		
		//Thread.sleep(2000);				//added beacause file is failing pls remove after some time	
		
		 //getApplicationUrl();
	}
    
    
    /*
     * Used to search using batch description
     */
    public void SearchUsingBatchDescription(String batchDesc) {
    	navigationMenu.waitForFilterTextBox();
    	sendKeys(navigationMenu.filterTxt, batchDesc, "Searching with "+batchDesc);
    }
    
    public String clickOkOnCofirmIndexDocumentForBatch() {
		//waitHelper.waitForElement(confirmIndexMsgTxt,10);
    	String msg=new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		waitTillInvisiblityOfBusyIcon(10);
		return msg;
	}
    
    public void navigateAndCaptureDocWithEmailParameters(String filepath,String filename,String docType, String docRef ,String accRef,String userType, String userName,String emailId,boolean emailVerified,String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To User" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		 //filepath= filepath == null ? "C:\\users\\amit.khambad\\Projectdata\\Testdata\\Tiffiles\\": filepath;	
		 //filename= filename == null ?"file1.tif": filename;
		 filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		 filename= filename == null ?"file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		selectRoutingTypeDD(userType,getEnvVariable);
		selectRouteToDD(userName,getEnvVariable);
		enterDocRef(docRef);
		
		
		if(accRef!=null) {
			boolean success =enterFolder1RefCapturePageAndSearch(accRef, accRefLabel);
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			    enterFolder1Ref(accRef);
			    
			    EnterEmailAddressInFolderReference(emailId, accRefLabel);
			    
			    SelectEmailVerifiedCheckbox(emailVerified);
			  
			  clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, routeToDD, 20);
			}
		}
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(1000);
		clickOnIndexDocument();

		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);
			 clickOkOnSuccesfullPopup();
			getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
    
    }
    
    public void EnterEmailAddressInFolderReference(String email,String label) {
    	new JavaScriptHelper(driver).scrollToElement(folder1RefEmailAddress);
    	sleepFor(1000);
    	sendKeys(folder1RefEmailAddress, email, "Entering value as "+email);
    }
    
    public void SelectEmailVerifiedCheckbox(boolean status) {
    	  sleepFor(1000);
    	  new JavaScriptHelper(driver).scrollToTop();
    	  sleepFor(1000);
    	  String fnlClickOnNavBar=String.format(new FileSystemSection(driver).tmpNavFolderTab, "Other");
		  WebElement clickOnNabBarElement=driver.findElement(By.xpath(fnlClickOnNavBar));
		  click(clickOnNabBarElement, "Clicking on navbar");
		   
		  if(status) {
			  new JavaScriptHelper(driver).scrollToBottom();
			  sleepFor(1000);
			  click(folder1RefEmailVerified, "Clicking on email verified checkbox");
		  }

    }
    
    
    public String GetValidationMessageFromCapturePage(String labelName) {
    	WebElement captureFieldVar = driver.findElement(By.xpath(String.format(tmpCaptureFieldLabel, labelName)));
    	String getTextFromCaptureField = captureFieldVar.getText();
    	return getTextFromCaptureField;
    }
    
    /*
     * It is used to capture document based on To Team
     */
	public void navigateAndCaptureDocWithParameters(String filepath,String filename,String docType, String docRef ,String accRef,String userType, String userName,String teamName,String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To Team" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		teamName = teamName == null ? ObjectReader.reader.getLoggedInUsersTeam() : teamName;
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		filename= filename == null ? "file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		if(userType.equals("To User")) {
			selectRoutingTypeDD(userType,getEnvVariable);
			selectRouteToDD(userName,getEnvVariable);
		}else {
			selectRoutingTypeDD(userType,getEnvVariable);
			selectRouteToDD(teamName,getEnvVariable);
		}
		
		enterDocRef(docRef);
		
		if(accRef!=null) {
			boolean success =enterFolder1RefCapturePageAndSearch(accRef, accRefLabel);
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  enterFolder1Ref(accRef);
			  clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, routeToDD, 20);
			}
		}
				
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);
		sleepFor(1000);
		clickOnIndexDocument();
		
		//Comment this to handle notification
		// if((docType.contains("Default") || docType.contains("ScanDoc"))&& baseUrl.contains("Lives")) {
		 // clickOkOnCofirmIndexDocument(); }
		 //waitHelper.waitForElement(successullyIndexMsg,20);
		 //clickOkOnSuccesfullPopup();
		
		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);		
			 clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
	}
    
	public void ExpandCoreFieldSectionOfBatchIndexing() {
		boolean flag = new VerificationHelper(driver).isElementDisplayed(By.xpath("//div[@id='div-CoreFields' and @style='display: table;']"));
		if(flag) {
			System.out.println("Not clicking on core field expand button");
		}else{
			click(coreFieldBtn, "Clicking on core field of Batch Indexing button");
		}
	}
	
	public void ExpandGeneralFieldSectionOfBatchIndexing() {
		boolean flag = new VerificationHelper(driver).isElementDisplayed(By.xpath("//button[text()='General']/following-sibling::div[@style='display: table;']"));
		if(flag) {
			System.out.println("Not clicking on General Field expand button");
		}else {
			click(generalBtn, "clicking on General field expand button");
		}
	}
	
	public void selectFileSystemForBatchIndexing(String fileSystem) {
			new DropdownHelper(driver).selectFromAutocompleDD(fileSystem, ddFileSystem);
	}
	
	/**
	 * Enter Account on add account ref page from capture form  
	 * @param fold1RefValue The value with which you want to create reference
	 */
	public void enterIndexingInformation (String indexNo, String accountRefValue, String miscRefValue, String propertyRefValue) {
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		enterAccountRef(indexNo, accountRefValue);
		enterMiscRef(indexNo, miscRefValue);
		enterPropertyRef(indexNo, propertyRefValue);
	}

	public void enterAccountRef(String indexNo, String accountRefValue)
	{
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);		
		
		WebElement addAccountRef = driver.findElement(By.xpath("(//div[@id='div-IndexingDetails_"+indexNo+"']//a[@data-action='Add'])[1]"));
//		WebElement addAccountRef = driver.findElement(By.xpath("//div[@id='div-IndexingDetails_"+indexNo+"']//a[@aria-label='Add a new Reference'])[1]"));
		waitHelper.waitForElementClickable(driver, addAccountRef, 35);
		click(addAccountRef, "Clicking on add Account ref");
		sleepFor(2000);
		waitHelper.waitForElementVisible(generalTab, 35, 1);
		//WebElement accountRef = driver.findElement(By.xpath("//input[@id='Folder1_Ref']"));
		sendKeys(folder1Reftxt,accountRefValue,"Enetering Account ref value = "+accountRefValue);
		navigationMenu.clickOnSaveIcon();
		sleepFor(4000);
	}	
	
	public void enterMiscRef(String indexNo, String miscRefValue)
	{
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		
		WebElement addMiscRef = driver.findElement(By.xpath("(//div[@id='div-IndexingDetails_"+indexNo+"']//a[@data-action='Add'])[2]")); 
//		WebElement addMiscRef = driver.findElement(By.xpath("//div[@id='div-IndexingDetails_"+indexNo+"']//a[@aria-label='Add a new Reference'])[2]"));
		waitHelper.waitForElementClickable(driver, addMiscRef, 35);
		click(addMiscRef, "Clicking on add Misc Ref");
		sleepFor(2000);
		waitHelper.waitForElementVisible(generalTab, 35, 1);
		//WebElement MiscRef = driver.findElement(By.xpath("//input[@id='Folder2_Ref']"));
		sendKeys(folder2Reftxt,miscRefValue,"Enetering Account ref value = "+miscRefValue);
		navigationMenu.clickOnSaveIcon();
		sleepFor(4000);
	}	
	
	public void enterPropertyRef(String indexNo, String propertyRefValue)
	{
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);
		WebElement addPropertyRef = driver.findElement(By.xpath("(//div[@id='div-IndexingDetails_"+indexNo+"']//a[@data-action='Add'])[3]"));
//		WebElement addPropertyRef = driver.findElement(By.xpath("//div[@id='div-IndexingDetails_"+indexNo+"']//a[@aria-label='Add a new Reference'])[3]"));
		waitHelper.waitForElementClickable(driver, addPropertyRef, 35);
		click(addPropertyRef, "Click on add Propert ref");
		sleepFor(2000);
		waitHelper.waitForElementVisible(generalTab, 35, 1);
		//WebElement PropertyRef = driver.findElement(By.xpath("//input[@id='Folder3_Ref']"));
		sendKeys(folder3Reftxt,propertyRefValue,"Enetering Account ref value = "+propertyRefValue);
		navigationMenu.clickOnSaveIcon();
		sleepFor(4000);
	}
	
	
	/**
	 * 
	 * @param inputText
	 */
	public void selectMailOptionDD(String inputText,String getEnvVariable) {	//created by sagar on 06.07.2023
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(mailOptionDD, "Clicking on mail option drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, mailOptionDD,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(inputText, mailOptionDD);
		}	
	}
	
	public void verifyAndCaptureDocWithIndex (String docRef, String getEnvVariable) throws InterruptedException {
//		TestBase base = new TestBase();
//		test = ExtentTestManager.getTest();
		String accRef="accRef"+generateRandomNumber(),
			   miscRef = "miscRef"+generateRandomNumber(),
			   propRef="propRef"+generateRandomNumber(),
			   accRef1="accRef1"+generateRandomNumber(),
			   miscRef1 = "miscRef1"+generateRandomNumber(),
			   propRef1="propRef1"+generateRandomNumber();
		String docType="Default - General Default Document Type";


		getApplicationUrl();

		String userType =  "To User", 
				userName = ObjectReader.reader.getUserName(), 
				filepath= ObjectReader.reader.getTestDocFolderPath(),
				filename="file1.tif";
				new AlertHelper(driver).acceptAlertIfPresent();
				navigationMenu.clickOnCaptureTab();
				clickOnDocumentCaptureBtn();
				Thread.sleep(2000);
				clickOnFileUploadIcon();
				uploadFileAngGetFileName(filepath,filename);
				selectRoutingTypeDD(userType,getEnvVariable);
				selectRouteToDD(userName,getEnvVariable);
				enterDocRef(docRef);
				sleepFor(5000);
					
				enterIndexingInformation("0", accRef, miscRef, propRef);
				new JavaScriptHelper(driver).scrollToBottom();
				addNewSetOfIndexingDetails();
				enterIndexingInformation("1", accRef1, miscRef1, propRef1);
				
				new JavaScriptHelper(driver).scrollToTop();
				sleepFor(2000);
				selectDocumentTypemDD(docType,getEnvVariable);
				sleepFor(2000);
				clickOnIndexDocument();
				
				try {
					waitHelper.waitForElement(successullyIndexMsg,10);
					clickOkOnSuccesfullPopup();
					getApplicationUrl(true);
				} catch (Exception e) {
					getApplicationUrl(true);
				}	
	}
	
	
//	created by sagar on 06.07.2023
	public void verifyAndCaptureDocWithCopyPrevious(String filepath,String filename,String docType, String docRef ,String accRef,String userType, String userName,String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To User" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		filename= filename == null ? "file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnCopyPreviousButton();
		Thread.sleep(3000);
		
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);

		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		String getDocumentTypeDD=documentTypeDD.getAttribute("title");
		String getRoutingTypeDD=routingTypeDD.getAttribute("title");
		String getRoutDD=routeToDD.getAttribute("title");
		
//		Verify info is pre-populated from previous capture document process
//		AssertionHelper.softAssertVerifyText(docRef, docRefTxt.getText(), "============comparing docref fails");			
		clickOnIndexDocument();
		
		AssertionHelper.verifyText(getDocumentTypeDD, docType);
		AssertionHelper.verifyText(getRoutingTypeDD, userType);
		AssertionHelper.verifyText(getRoutDD, userName);
	
		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);		
			 clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
		//sleepFor(5000);
	}
	
	   /*
  * It is used to capture document based on To Team
  */
 //created by sagar on 06.07.2023 for adding mailOptionDD 
	public void navigateAndCaptureDocWithParameters(String filepath, String filename, String docType, String docRef, String mailOption, String accRef, String userType, String userName, String teamName, String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To Team" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		teamName = teamName == null ? ObjectReader.reader.getLoggedInUsersTeam() : teamName;
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		filename= filename == null ? "file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		//selectDocumentTypemDD(docType);
		if(userType.equals("To User")) {
			selectRoutingTypeDD(userType,getEnvVariable);
			selectRouteToDD(userName,getEnvVariable);
		}else if(userType.equals("To Team")){
			selectRoutingTypeDD(userType,getEnvVariable);
			selectRouteToDD(teamName,getEnvVariable);
		}
		
		enterDocRef(docRef);
		selectMailOptionDD(mailOption,getEnvVariable);  	//changed by sagar on 06.07.2023 for adding mailOptionDD 
		
		if(accRef!=null) {
			boolean success =enterFolder1RefCapturePageAndSearch(accRef, accRefLabel);
			log.info("Flag value of success is "+success);
			if(!success) {
				new WindowHelper(driver).clickOkOnPopup();
				navigationMenu.waitForIconWithDataButton("Save", "Actions");
			  enterFolder1Ref(accRef);
			  clickOnSaveButton();
			  waitHelper.waitForElementClickable(driver, routeToDD, 20);
			}
		}
				
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);

		sleepFor(1000);
		clickOnIndexDocument();

		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);		
			 clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
	}
	
//	created by sagar on 06.07.2023
	public void addNewSetOfIndexingDetails() {
//		addNewSetOfIndexingDetails.click();
//		click(addNewSetOfIndexingDetails, "clicking on add new set of indexing details");
		waitHelper.waitForElementClickable(driver, addNewSetOfIndexingDetails, 35).click();

		sleepFor(2000);
	}
	
	   /*
	    * It is used to capture document based on To Team also used for the range routing purpose
	    */
		//created by sagar on 03.08.2023 for adding property ref 
	public void navigateAndCaptureDocWithParameters(String filepath, String filename, String docType, String docRef, String mailOption, String accRef, String userType, String userName, String teamName, boolean createRef, String propRef, String getEnvVariable) throws InterruptedException {
		userType = userType == null ? "To Team" :userType;//if userType null then user
		userName = userName == null ? ObjectReader.reader.getUserName() :userName;
		teamName = teamName == null ? ObjectReader.reader.getLoggedInUsersTeam() : teamName;
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		mailOption = mailOption == null ? "Mail" :mailOption;

		filename= filename == null ? "file1.tif": filename; 
		docType= docType == null ? "Default - General Default Document Type" : docType;
		 log.info("capturing doc with "+userType+userName+docType);
		new AlertHelper(driver).acceptAlertIfPresent();
		navigationMenu.clickOnCaptureTab();
		clickOnDocumentCaptureBtn();
		Thread.sleep(2000);
		clickOnFileUploadIcon();
		uploadFileAngGetFileName(filepath,filename);
		sleepFor(2000);
		selectDocumentTypemDD(docType,getEnvVariable);
		//selectDocumentTypemDD(docType);
		if(userType.equals("To User")) {
			selectRoutingTypeDD(userType,getEnvVariable);
			selectRouteToDD(userName,getEnvVariable);
		}else{
			selectRoutingTypeDD(userType,getEnvVariable);
			selectRouteToDD(teamName,getEnvVariable);
		}
		enterDocRef(docRef);
		selectMailOptionDD(mailOption,getEnvVariable);  	//changed by sagar on 06.07.2023 for adding mailOptionDD 

		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(2000);

//		enterPropertyRef("0", propRef);						//alternate method to create property reference directly
		if(createRef && !accRef.isEmpty()) {
		createReferenceIfNotAvailable(accRef,accRefLabel);
		}
		if(createRef && !propRef.isEmpty()) {
			createReferenceIfNotAvailable(propRef,"Property Ref");
			}
		Thread.sleep(3000);
		
		
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(2000);
		clickOnIndexDocument();
		try {
			 waitHelper.waitForElement(successullyIndexMsg,10);		
			 clickOkOnSuccesfullPopup();
			 getApplicationUrl();
		} catch (Exception e) {
			getApplicationUrl();
		}
	}

	
}
