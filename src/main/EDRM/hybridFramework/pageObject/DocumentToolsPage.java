package main.EDRM.hybridFramework.pageObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;

public class DocumentToolsPage extends TestBase{
			
			private WebDriver driver;
			private Logger log = LoggerHelper.getLogger(CapturePage.class);
			WaitHelper waitHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			HomePage homePage;
			
			@FindBy(how=How.XPATH,using="(//tr[@class='odd']/td[3])[1]")
			public WebElement firstItemDocList;
			
			@FindBy(how=How.XPATH,using="//td/button[@data-button='Re-Reference']")
			public WebElement reReferenceBtn ;
			
			@FindBy(how=How.XPATH,using="//li/button[text()='Re-Reference']")
			public WebElement reReferenceListBtn ;
			
			@FindBy(how=How.XPATH,using="//label[text()='Account Ref']/../../following-sibling::div/div/input")
			public WebElement chkFolder1Ref ;
			
			@FindBy(how=How.XPATH,using="//label[text()='Misc Ref']/../../following-sibling::div/div/input")
			public WebElement chkFolder2Ref ;
			
			@FindBy(how=How.XPATH,using="//label[text()='Property Ref']/../../following-sibling::div/div/input")
			public WebElement chkFolder3Ref;
			
			@FindBy(how = How.XPATH,using = "//label[text()='Alt Account Ref']/../../following-sibling::div/div/input")
			public WebElement chkAltAccountRef;
			
			@FindBy(how=How.XPATH,using="//span[@class='references-details-align']")
			public WebElement lblReferenceDetailsFolderRef ;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']")
			public WebElement documentTypeddl ; 
			
			@FindBy(how = How.XPATH,using = "//label[text()='Document Type']/../../following-sibling::div/div/input[@id='KeepDocumentTypeId']")
			public WebElement chkDocumentType;
			
			@FindBy(how = How.XPATH,using = "//label[text()='File System']/../../following-sibling::div/div/input[@id='KeepFileSystemId']")
			public WebElement chkFileSystem;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='FileSystemId']")
			public WebElement ddFileSystemReReference ;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='FileSystemId']/ancestor::section[contains(@class,'page container')]")
			public WebElement ddFileSystemReReference551OnWards;
			
			@FindBy(how=How.XPATH,using="//label[text()='Account Ref']/../div/input")
			public WebElement txtFolder1Ref;
			
			@FindBy(how=How.XPATH,using="//label[text()='Misc Ref']/../div/input")
			public WebElement txtFolder2Ref;
			
			@FindBy(how=How.XPATH,using="//label[text()='Misc Ref']/../span[@class='references-details-align']")
			public WebElement folder2RefDetailsLbl;
			
			@FindBy(how=How.XPATH,using="//label[text()='Account Ref']/../span[@class='references-details-align']")
			public WebElement folder1RefDetailsLbl;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//td/button[@data-original-title='Apply changes']"),
				@FindBy(how=How.XPATH,using="//td/button[@data-bs-original-title='Apply changes']")
			})
			public WebElement applyBtn;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//li/button[@data-original-title='Apply changes']"),
				@FindBy(how=How.XPATH,using="//li/button[@data-bs-original-title='Apply changes']")
			})
			public WebElement applyBtnLst ;
			
			@FindBy(how=How.XPATH,using="//*[@class='modal-title' and text()='Re-Reference']")
			public WebElement rereferencePopupTitleLblXpath;
			
			@FindBy(how=How.XPATH,using="//button[text()='Yes']")
			public WebElement confirmYesBtn ;
			
			@FindBy(how=How.XPATH,using="//p[@id='dialogMessage' and contains(text(),'re-referenced successfully')]")
			public WebElement successullyRereferencedMsgXpath ;
			
			@FindBy(how=How.XPATH,using="//p[contains(text(),'re-referenced successfully')]/../following-sibling::div/div/button[1]")
			public WebElement successfullyRereferenceOkBtnXpath;
			
			@FindBy(how=How.XPATH,using="//td/button[@data-original-title='Create and edit rendition']")
			public WebElement createAndEditRenditionBtnXpath;
			
			@FindBy(how=How.XPATH,using="//li/button[@data-original-title='Create and edit rendition']")
			public WebElement createAndEditRenditionBtnLstXpath;
			
			@FindBy(how=How.CLASS_NAME ,using="leaflet-toggle-toolbar-button")
			public WebElement showAnnotationToolsBtnClass;
			
			@FindBy(how=How.XPATH,using="//div[@id='omniview']")
			public WebElement omniviewMainContentXpath ; 
			
			//More search
			@FindBy(how=How.XPATH,using="//button[@data-button='More']")
			public WebElement BtnMoreSearch ; 
			
			@FindBy(how=How.XPATH,using="//input[@id='SaveSearchHomepageTile']")
			public WebElement ChkSaveSearchHomePage ; 
			
			@FindBy(how=How.XPATH,using="//input[@id='SearchName']")
			public WebElement txtSearchName ; 
			
			@FindBy(how=How.XPATH,using="//button[@id='saveSearchOk']")
			public WebElement btnSaveSearchOk ; 
			
			@FindBy(how=How.ID ,using="ViewDeleted")
			public WebElement chkViewDeleted;
			
			@FindBy(how=How.XPATH ,using="//button[@data-button='SaveSearch']")
			public WebElement btnSaveSearch;
			
			@FindBy(how=How.XPATH ,using="//input[@id='input-docTypes']/..//input[@type='text' and @autocomplete='off']")
			public WebElement txtInputDocTypeMoreSearch;
			
			@FindBy(how=How.XPATH ,using="//button[@data-id='Team']")
			public WebElement ddTeamUnderIntrayMoreSearch;
			
			
			
			@FindBy(how=How.XPATH ,using="//table[@data-configuration-name='TagDatatable']/tbody/tr[1]/td[1]")
			public WebElement firstRowTagTable;
			
			@FindBy(how=How.XPATH ,using="//label[text()='Tag']/../div[@class='pillboxWidth']//div/input")
			public WebElement txtTagAreaMoreSearch;
			
			@FindBy(how=How.XPATH ,using="//div[@id='myModal']//div[@class='modal-body']//div[@class='selectize-dropdown-content']/div[1]")
			public WebElement ddTagsOptionInAddTagPopup;
			
//			@FindBy(how=How.XPATH ,using="//div[@id='myModal']//div[@class='modal-body']//div[contains(@class,'has-options')]/input[@type='text']")
//			public WebElement txtAreaInAddTagPopup;
						
			//Document viewer
			@FindBy(how=How.XPATH ,using="//button[@id='documentNavigateNext']")
			public WebElement btnNextViewer;
			
			@FindBy(how=How.XPATH ,using="//button[@id='documentNavigatePrevious']")
			public WebElement btnPreviousViewer;
			
			//Linking doc
			@FindAll({
				@FindBy(how=How.XPATH ,using="//header//div[@class='input-inlineRibbonDropDown-btn']/button[@data-original-title='Add Document Link Label']"),
				@FindBy(how=How.XPATH ,using="//header//div[@class='input-inlineRibbonDropDown-btn']/button[@data-bs-original-title='Add Document Link Label']")
			})		
			public WebElement btnAddDocLinkLabel;
			
			@FindAll({
				@FindBy(how=How.XPATH ,using="//div[@class='modal show']//div[@class='modal-body']//input[@id='DocumentLinkLabelText']"),     //modal fade in to modal show in R531
				@FindBy(how=How.XPATH ,using="//div[@class='modal fade in']//div[@class='modal-body']//input[@id='DocumentLinkLabelText']")
			})	 
			public WebElement txtDocumentLinkLabel;
			
			//Check In check Out
			@FindBy(how=How.XPATH ,using="//div[@id='updateDocumentModal']//div[@class='modal-body']//button[@id='pickfiles']")
			public WebElement btnFileUploadDocumentCheckIn;
			
			@FindBy(how=How.XPATH ,using="//div[@id='updateDocumentModal']//div[@class='modal-body']//textarea[@id='Comments']")
			public WebElement txtCommentUploadDocumentCheckIn;
			
			@FindBy(how=How.XPATH ,using="//button[@data-id='User']")			//Added by amit for under intray search in more search
			public WebElement ddUserUnderIntrayMoreSearch;
			
			@FindBy(how = How.XPATH , using = "//button[@id='addDocumentNote']")
			public WebElement addNoteBtn;
			
			@FindBy(how = How.XPATH , using = "//input[@id='DocumentNoteTitle']")
			public WebElement notesTitleInput;
			
			@FindBy(how = How.XPATH , using = "//textarea[@id='DocumentNoteAnnotation']")
			public WebElement notesTextAreaInput;
			
			@FindBy(how = How.XPATH , using = "//button[@id='saveDocumentNote']")
			public WebElement saveDocumentNoteBtn;
			
			@FindBy(how = How.XPATH , using = "//button[@id='closeDocumentNote']")
			public WebElement documentCloseNoteBtn;
			
            @FindAll({
            	@FindBy(how = How.XPATH,using="//button[@data-original-title='Save Rendition']"),
            	@FindBy(how = How.XPATH,using="//button[@data-bs-original-title='Save Rendition']"),
            })
            public WebElement saveRenditionBtn;
            
            @FindBy(how = How.XPATH,using="//input[@name='renditionName']")
            public WebElement renditionNameInput;
            
            @FindBy(how = How.XPATH,using = "//p[contains(text(),'Rendition')]")
            public WebElement renditionViewerHeader;
            
            @FindAll({
            	@FindBy(how = How.XPATH,using = "//div//button[@data-original-title='Public Access']"),
            	@FindBy(how = How.XPATH,using = "//div//button[@data-bs-original-title='Public Access']")
            })
            public WebElement publicAccessBtn;
            

            @FindBy(how = How.XPATH,using = "//div//button[@data-button='DocumentConnect']")
            public WebElement documentConnectBtn;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='RenditionType']")
            public WebElement renditionTypeDD;
            
            @FindBy(how = How.XPATH,using = "//input[@type='search']")
            public WebElement filtexTxtForPublicAccess;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='ddlRedactionTemplate']")
            public WebElement ddredactionTemplate;
            
            @FindBy(how = How.XPATH,using = "//button[@data-button='Apply']")
            public WebElement redactionApplyButton;
            
            @FindBy(how = How.XPATH,using = "//table[@id='listVersionsDataTable']/tbody/tr[1]")
            public WebElement tmpRowUndeVersionHistory;
            
            @FindBy(how = How.XPATH,using = "//table[@id='listRenditionDataTable']/tbody/tr[1]")
            public WebElement tmpRenditonTableRow;
            
            @FindBy(how = How.XPATH,using = "//p[text()='Document Version Viewer']")
            public WebElement documentVersionViewerNavBar;
            
            @FindBy(how = How.XPATH,using = "//button[@data-button='DocumentConnect']/ancestor::div[1]/following-sibling::ul/li/button[text()='Unpublish']")
            public WebElement documentConnectUnpublishBtn;
            
            @FindBy(how = How.XPATH,using = "//a[text()='Referencing']")
            public WebElement referencingTab;
            
            @FindBy(how = How.XPATH,using = "//a[text()='Notes']")
            public WebElement notesTab;
            
            @FindAll({
            	@FindBy(how = How.XPATH,using = "//button[@data-original-title='Add a new note']"),
            	@FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Add a new note']")
            })
            public WebElement createNoteButton;
            
            @FindBy(how = How.XPATH,using = "//input[@name='Title']")
            public WebElement enterNotesTitle;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='Paragraph']")
            public WebElement ddParagraph;
          
            @FindBy(how = How.XPATH,using = "//button[@data-id='Paragraph']/ancestor::section[contains(@class,'page container')]")
            public WebElement ddParagraph551OnWards;

            @FindBy(how = How.XPATH,using = "//button[text()='Edit and Publish']")
            public WebElement editAndPublishBtn;
            
            @FindBy(how = How.XPATH,using = "//input[@id='multiSelectCheckbox']")
            public WebElement multiSelectCheckbox;
            
			@FindBy(how=How.XPATH,using="//button[text()='No']")
			public WebElement confirmNoBtn ;

			@FindBy(how = How.XPATH,using = "//button[text()='Cancel' and @id='dialogButton3']")
			public WebElement confirmCancelBtn;
			
			@FindBy(how=How.XPATH,using="//label[text()='Property Ref']/../div/input")
			public WebElement txtFolder3Ref;
			
			@FindBy(how=How.XPATH,using="//label[text()='Property Ref']/../span[@class='references-details-align']")
			public WebElement folder3RefDetailsLbl;
			
            @FindBy(how = How.XPATH,using = "//label[text()='Account Ref']/../div/div/a[@data-title='Search for Reference']")
            public WebElement folder1RefLookUpBtn;
            
            @FindBy(how = How.XPATH,using = "//label[text()='Misc Ref']/../div/div/a[@data-title='Search for Reference']")
            public WebElement folder2RefLookUpBtn;

            @FindBy(how = How.XPATH,using = "//label[text()='Property Ref']/../div/div/a[@data-title='Search for Reference']")
            public WebElement folder3RefLookUpBtn;
            
            @FindBy(how = How.XPATH,using = "//label[text()='Account Ref']/../div/div/a[@data-title='Add a new Reference']")
            public WebElement addFolder1RefBtnOnRereferencePage;

            @FindBy(how = How.XPATH,using = "//label[text()='Misc Ref']/../div/div/a[@data-title='Add a new Reference']")
            public WebElement addFolder2RefBtnOnRereferencePage;

            @FindBy(how = How.XPATH,using = "//label[text()='Property Ref']/../div/div/a[@data-title='Add a new Reference']")
            public WebElement addFolder3RefBtnOnRereferencePage;
            
            @FindBy(how = How.XPATH,using = "//label[text()='Account Ref']/ancestor::div[1]//a[@data-bs-original-title='Edit the Reference']")
            public WebElement folder1RefEditButton;

            @FindBy(how = How.XPATH,using = "//label[text()='Misc Ref']/ancestor::div[1]//a[@data-bs-original-title='Edit the Reference']")
            public WebElement folder2RefEditButton;

            @FindBy(how = How.XPATH,using = "//label[text()='Property Ref']/ancestor::div[1]//a[@data-bs-original-title='Edit the Reference']")
            public WebElement folder3RefEditButton;
            
            @FindBy(how = How.XPATH,using = "//h3[text()='Not found']/ancestor::div[1]/following-sibling::div[2]//button[@id='dialogButton2']")			//Added because facing issues with already cancel btn
            public WebElement cancelDeleteBtnWhileReReferenceOfDOc;
            
            @FindBy(how = How.XPATH,using = "//input[@id='Doc_Ref']")
            public WebElement docRefInputFieldOnMoreSearch;
            
            
            @FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
            public WebElement ddCommentElemntWithoutInput;
            
            @FindBy(how = How.XPATH,using = "//a[@title='Reset Column Configuration']")
            public WebElement resetColumnConfigurationLink;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='ddlRedactionTemplate']")
            public WebElement redactionTempDD;
            
            @FindBy(how = How.XPATH,using = "//div[@class='dropdown-menu show']//input")
            public WebElement redactionTempInputSearch;
            
            @FindBy(how = How.XPATH,using = "//label[text()='Save and Publish Rendition']/ancestor::div[1]//input")
            public WebElement saveAndPublishRadioBtn;
            
            @FindBy(how = How.XPATH,using = "//button[@id='pickFile']")
            public WebElement chooseFileBtnPA;
            
            @FindBy(how = How.XPATH,using = "//a[@title='Reset Groups']")
            public WebElement resetGroupsBtn;
            
            @FindBy(how = How.XPATH,using = "//li[@class='paginate_button page-item next']")
            public WebElement paginationNextButton;

            @FindBy(how = How.XPATH,using = "//table[@data-configuration-name='DocumentListConfiguration']//th[contains(@aria-label,'Date Received:')]")
            public WebElement dateReceivedBtn;
            
            @FindBy(how=How.XPATH,using="//table[@class='dataTableGrid']/tbody/tr[2]/td[2]/label")
            public WebElement notesParagraph;                        //created by sagar on 07.07.2023
            
            @FindBy(how = How.XPATH,using = "//button[@id='closeDocumentNote']")
            public WebElement closeBtn;
            
            @FindBy(how = How.XPATH,using = "//label[text()='Document Type']/ancestor::div[1]//div[@class='item']")
            public WebElement getDocTypeValue;
			
			@FindBy(how=How.XPATH,using="//table[@class='dataTableGrid']/tbody/tr[1]/td[2]")
            public WebElement notesTitle;                        	//created by sagar
			
			
			@FindAll({
				@FindBy(how=How.XPATH ,using="//input[@id='input-tags-selectized']"),     
				@FindBy(how=How.XPATH ,using="//div[@id='myModal']//div[@class='modal-body']//div[contains(@class,'has-options')]"),     
				@FindBy(how=How.XPATH ,using="//div[@id='myModal']//div[@class='modal-body']//div[contains(@class,'has-options')]/input[@type='text']")
			})	 
			public WebElement txtAreaInAddTagPopup;			
			
    		@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Account Ref:')])[1]")
			public WebElement accountRefColValue;
    		
    		@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Team:')])[1]")
			public WebElement teamColValue;

    		@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Mail Status:')])[1]")
			public WebElement mailStatusColValue;			
			
            private String tmpPublishType = "//label[text()='%s']/ancestor::div[1]/input";
			
			private  String tmpTagArea = "//div[@class='selectize-dropdown-content']/div[text()='%s']";
			
			public String btnSearchFolderRefDocReReference = "//label[text()='%s']/../div/div/a[1]",
					btnAddFolderRefDocReReference = "//label[text()='%s']/../div/div/a[2]",
					btnEditFolderRefDocReReference = "//label[text()='%s']/../div/div/a[3]",
					tmpDataTableCell = "(//table[@id='searchResultDataTable']/tbody/tr/td[text()='%1$s'])[%2$s]",
					tmpTxtVersionUploadDocumentCheckIn = "//div[@id='updateDocumentModal']//div[@class='modal-body']//input[@id='%s']",
					//mpBtnMoreSection="//button[@id='btn-%sMore']",
					tmpBtnMoreSection="//button[contains(@id,'btn-%s')]",
					tmpSearchResultDocumentTableId ="searchResultDataTable",
					tmpTxtFolderRefInput = "//label[text()='%s']/../div/input";
			
			private String folderRefNavBarTitle = "//p[text()='Add %s']";
			private String folderRefEditNavBarTitle = "//p[text()='Edit %s']";
			private String folderRefEditTranslateNavBarTitle = "//p[text()='Translate %s']";
			
			private String tmpDocListGroup = "//table[@data-configuration-name='DocumentListConfiguration']//th[contains(@aria-label,'%s')]//i";
			private String tmpViewDoc = "//td[contains(text(),'%s')]/ancestor::tr[1]//a[@id='viewDocument']";

			
			private static final String DELETE_DOC = "Delete Document";
			private static final String UPDATE_DOC = "Update Document";
			
			private String tmpDocumentGrpDocTypeVerification = "//span[text()='Document Type']/ancestor::tr[1]//span[contains(text(),'%s')]";
			
			
	public DocumentToolsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		TestBase.logExtentReport("Document tools Page Object Created");
		dropdownHelper = new DropdownHelper(driver);
		homePage = new HomePage(driver);
	}

	/**
	 * Click on first item of document search list
	 */
	public void clickOnFirstItemInList() {
		
		boolean status= new CapturePage(driver).verifyFilterTextBoxInIntray();				//Adding because filter text box is not visible
		if(status) {
			waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
			//intratY
			if(!new CapturePage(driver).intrayItemRow.getAttribute("class").contains("selected"))
				click(firstItemDocList,"Clicking on first item in list");
		}else {
			sleepFor(1000);
			refreshPage();
			waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		
			if(!new CapturePage(driver).intrayItemRow.getAttribute("class").contains("selected"))
				click(firstItemDocList,"Clicking on first item in list");
		}
		
		
		//click(firstItemDocList,"Clicking on first item in list");
		
	}
	
	/**
	 * Click on specific row of search table based on value and index
	 * @param value actualValue like e.g rahul1
	 * @param index index in case multiple rows
	 */
	public void clickOnSpecificDocumentDataTable(String value, String index) {
		driver.findElement(By.xpath(String.format(tmpDataTableCell, value,index))).click();
	}
	
	/**
	 * Click on Reference button from document options 
	 */
	public void clickOnReReferenceButton() {
		navigationMenu.clickOnIcon("Re-Reference document", "Document");;
		navigationMenu.waitForIcon("Clear changes","Actions");
	}
	
	/**
	 * Select file system dropdown based provided value
	 * @param fileSystem
	 */
	public void selectFileSystem(String fileSystem,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddFileSystemReReference, "Clicking on file system drop down");
			new DropdownHelper(driver).selectFromAutocompleDD(fileSystem, ddFileSystemReReference551OnWards,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDD(fileSystem, ddFileSystemReReference);
		}
	}
	
	/**
	 * Select document type dropdown based on provided values
	 * @param docType
	 */
	public void selectDocumentType(String docType,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(documentTypeddl, "Clicking on document type drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(docType, ddCommentElemntWithoutInput,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(docType, documentTypeddl);
		}
	}
	
	public void selectChkboxForDocumentType() {
		new ActionHelper(driver).moveToElementAndClick(chkDocumentType);
	}
	
	/*
	 * Select checkbox of filesystem
	 */
	public void selectChkboxForFileSystem() {
		new ActionHelper(driver).moveToElementAndClick(chkFileSystem);
	}


	
	/**
	 * Select checkbox of folder1keepref 
	 */
	public void selectChkboxForFolder1KeepRef() {
		new ActionHelper(driver).moveToElementAndClick(chkFolder1Ref);
	}
	
	/**
	 * Select checkbox of folder2keepref
	 */
	public void selectChkboxForFolder2KeepRef() {
		new ActionHelper(driver).moveToElementAndClick(chkFolder2Ref);
	}
	
	/**
	 * Select checkbox of folder3keepref
	 */
	public void selectChkboxForFolder3KeepRef() {
		new ActionHelper(driver).moveToElementAndClick(chkFolder3Ref);
	}
	
	/**
	 *Returns true or false based on folder1 text box enabled on ReReference page 
	 * @return true,false
	 */
	public boolean isFolder1TextEnabled() {
		return new VerificationHelper(driver).isElementEnabled(txtFolder1Ref);
	}
	
	/**
	 *Returns true or false based on folder2 text box enabled on ReReference page 
	 * @return true,false
	 */
	public boolean isFolder2TextEnabled() {
		return new VerificationHelper(driver).isElementEnabled(txtFolder1Ref);
	}
	
	/**
	 * Enter folder2 reference on ReReference page
	 * @param folder2ref e.g F1-001R1
	 */
	public void enterFold2Ref(String folder2ref) {
		sendKeys(txtFolder2Ref,folder2ref,"Enter folder 2 ref ="+folder2ref);
		new ActionHelper(driver).pressTab();
		waitHelper.waitForElement(folder2RefDetailsLbl,20);
	}
	
	public void enterFold1Ref(String folder1ref) {
		sendKeys(txtFolder1Ref,folder1ref,"Enter folder 1 ref ="+folder1ref);
		new ActionHelper(driver).pressTab();
		waitHelper.waitForElement(folder1RefDetailsLbl,20);
	}
	
	/**
	 * Click on apply ReReference button 
	 */
	public void clickOnApplyButton() {
		navigationMenu.clickOnMenuButtonAtTop(applyBtn, applyBtnLst);
	}
	
	/**
	 * Click on apply button confirm ReReference popup
	 */
	public void clickOnApplyButtonAndConfirmReReferencePopup() {
		navigationMenu.clickOnMenuButtonAtTop(applyBtn, applyBtnLst);
		clickYesOnConfirmReferencePopUp();
		sleepFor(2000);
		clickOkOnRereferenceSusccessfulPopup();
	}
	
	/**
	 * Get text content of entire confirm ReReference popup
	 */
	public String getReferencePopupContent() {
		waitHelper.waitForElement(rereferencePopupTitleLblXpath,20);
		return new WindowHelper(driver).getPopupMessage();
	}
	
	/**
	 * Click yes on Confirm ReReference popup
	 */
	public void clickYesOnConfirmReferencePopUp() {
		new WindowHelper(driver).waitForPopup("Re-Reference");
		click(confirmYesBtn,"Clicked on confirm yes button");
		sleepFor(2000);
		new WindowHelper(driver).waitForPopup("Re-Reference");
//		waitHelper.waitForElement(busyDialogIcon,10);
//		if(!new VerificationHelper(driver).isElementDisplayedByEle(successullyRereferencedMsgXpath)){
//			waitHelper.waitForElement(busyDialogIcon,10);
//		}
	}
	
	/**
	 * Click Ok on Confirm ReReference Successful popup
	 */
	public void clickOkOnRereferenceSusccessfulPopup(){
		new WindowHelper(driver).waitForPopup("Re-Reference");
		//click(waitHelper.waitForElementClickable(driver,navigationMenu.dialogOkBtn, 20),"Clicked on successfully reference path");
		waitHelper.waitForElement(navigationMenu.dialogOkBtn, 20);
		sleepFor(1000);
		click(navigationMenu.dialogOkBtn, "Clicked on successfully reference path");
		try {
		waitHelper.waitForElementInvisible(driver,successullyRereferencedMsgXpath,20);
		}
		catch(Exception e) {
			new WindowHelper(driver).clickOkOnPopup();
		}
		if(new VerificationHelper(driver).isElementDisplayedByEle(successullyRereferencedMsgXpath)) {
			try {
				click(waitHelper.waitForElementClickable(driver,navigationMenu.dialogOkBtn,20),"Clicked on successfully ref ok button");
			}
			catch(Exception e) {
				log.info("Execption caught while clicking on Rereference successfull popup");
			}
	    }
	}
	
	/**
	 * Clicking on add folder refrence name based on provided reference 
	 * @param referenceName e.g Account Ref, Misc Ref, Property Ref
	 */
	public void clickOnAddFolderReference(String referenceName) {
		String addFolderRefXpath = String.format(btnAddFolderRefDocReReference, referenceName);
		WebElement addFolderRefEle = driver.findElement(By.xpath(addFolderRefXpath));
		click(addFolderRefEle, "Clicking on add folder reference for "+referenceName);
		waitHelper.waitForElement(navigationMenu.lnkbreadCrumb, 20);
		navigationMenu.waitForIcon("Save changes");
	}
	
	/**
	 * Clicking on search of folder refrence based on provided reference capture/rereference 
	 * @param referenceName e.g Account Ref, Misc Ref, Property Ref
	 */
	public void clickOnSearchFolderReference(String referenceName) {
		String searchFolderRefXpath = String.format(btnSearchFolderRefDocReReference, referenceName);
		WebElement searchFolderRefEle = driver.findElement(By.xpath(searchFolderRefXpath));
		click(searchFolderRefEle, "Clicking on search folder reference for "+referenceName);
	}
	
	/**
	 * Clicking on edit folder on provided reference on capture/rereference page 
	 * @param referenceName e.g Account Ref, Misc Ref, Property Ref
	 */
	public void clickOnEditFolderReferenceCapturePage(String referenceName) {
		String editFolderRefXpath = String.format(btnEditFolderRefDocReReference, referenceName);
		WebElement editFolderRefEle = driver.findElement(By.xpath(editFolderRefXpath));
		click(editFolderRefEle, "Clicking on edit folder reference for "+referenceName);
	}
	
	
	/**     ----------------------- Rereference section ends ------------------------**/
	
	/**
	* Click On Create and Edit Rendition option 
	 */
	public void clickOnCreateAndEditRendition() {
		navigationMenu.clickOnMenuButtonAtTop(createAndEditRenditionBtnXpath, createAndEditRenditionBtnLstXpath);
	}
	
	public boolean isOmnviewShown() {
		boolean omniviewVisiblity =false;
		try {
		waitHelper.waitForElement(omniviewMainContentXpath,20);
		omniviewVisiblity =true;
		}
		catch (Exception e) {
			System.out.println("Element not visible "+e);
		}
		return omniviewVisiblity;
	}
	
	public boolean clickOnShowAnnotationToolBtn() {
		boolean annotationClicked =false;
		if(isOmnviewShown()){
		click(waitHelper.waitForElementClickable(driver,showAnnotationToolsBtnClass,20),"Clicking on show annotation tool");
		annotationClicked = true;
		}
		else {
			annotationClicked = false;
		}
		
		return annotationClicked ;
	}

	/** --------------------------------More search area ---------------------------------**/
	
	/**
	 * Click on More search button under search tab
	 */
	public void clickOnMoreSearch() {
		navigationMenu.clickOnTab("Search");
		click(BtnMoreSearch,"Clicking on more search");
	}
	
	/**
	 * Click on view deleted checkbox
	 */
	public void clickOnViewDeletedCheckbox() {
		waitHelper.waitForElementClickable(driver, chkViewDeleted, 5);
		click(chkViewDeleted,"Clicking on view deleted");
	}
	
	/**
	 * Click on save search button under more search
	 */
	public void clickOnSaveSearchButton() {
		click(btnSaveSearch,"Clicking on more search");
	}
	
	/**
	 * Set tag by text in textarea of more search
	 * @param tagName name of text e.g crime
	 */
	public void setTagInMoreSearch(String tagName) {
		new JavaScriptHelper(driver).scrollToElement(txtTagAreaMoreSearch);
		sleepFor(2000);
		click(txtTagAreaMoreSearch,"Clicking on tagAreaTxt");
		String fnlTagArea = String.format(tmpTagArea, tagName);
		new ActionHelper(driver).moveToElementAndClick(By.xpath(fnlTagArea));	
	}
	
	/**
	 * Get text of tag under more search
	 * @return text value of tag area
	 */
	public String getTextAreaOfTag() {
		return txtTagAreaMoreSearch.getText();
	}
	
	/**
	 * Click on tag are and choose available tag using down arrow
	 */
	public void setAvailableTag() {
		txtTagAreaMoreSearch.clear();
		txtTagAreaMoreSearch.click();
		new ActionHelper(driver).pressDownArrow();
		new ActionHelper(driver).pressEnter();
	}
	
	/**
	 * Enter and save search name in more search
	 * @param input search name value
	 */
	public void enterSaveSearchName(String input) {
		waitHelper.waitForElementClickable(driver, txtSearchName, 5);
		sendKeys(txtSearchName, input, "Entering save search name as "+input);
	}
	
	/**
	 * Click on Save as home page of more search
	 */
	public void clickOnSaveAsHomePage() {
		click(ChkSaveSearchHomePage,"clicking on save as homepage");
	}
	
	/**
	 * Click ok on save popup of more search
	 */
	public void clickOkOnSavePopup() {
		click(btnSaveSearchOk,"clicking on save search ok popup");
		new WindowHelper(driver).waitForPopup("Save Search");
		new WindowHelper(driver).clickOkOnPopup();
	}
	
	/**
	 * Click on delete popup of more search
	 * @return final popup message of delete popup
	 */
	public String clickOkOnDeletePopup() {
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup(DELETE_DOC);
		windowHelper.clickOkOnPopup();
		windowHelper.waitForPopup(DELETE_DOC);
			String popupMsg =  windowHelper.getPopupMessage();
			log.info("Popup message is "+popupMsg);
			if(popupMsg.contains("Are you sure you want")) {
				windowHelper.clickOkOnPopup();
			}
			if(popupMsg.contains("Mail Item(s) with status(s)")) {
				log.info("In if condition of popup msg");
				windowHelper.clickOkOnPopup();
				windowHelper.waitForPopup(DELETE_DOC);
				String finPopupMsg =  windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				return finPopupMsg;
			}
			else {
				windowHelper.waitForPopup(DELETE_DOC);
				String finPopupMsg =  windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				return finPopupMsg;
			}	
		
	}
	
	
	/** --------------------------------More search area Ends ---------------------------------**/
	
	/**
	 * Click on undelte document option under document
	 * @return final popup message string format
	 */
	public String clickOnUndeleteDocument() {
		navigationMenu.clickOnIcon("Undelete document", "Document");
		WindowHelper windowHelper= new WindowHelper(driver);
		windowHelper.waitForPopup("Undelete document");
		windowHelper.clickOkOnPopup();
		windowHelper.waitForPopup("Undelete document");
		String fnlmsg = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		return fnlmsg;
	}
	
	/**
	 * Click on clear changes under action
	 */
	public void clickOnClearButton() {
		navigationMenu.clickOnIcon("Clear changes", "Actions");		
	}
	
	/**
	 * Click on first row of tag table
	 */
	public void clickOnFirstRecordOfTag() {
		boolean status=new AdminDocumentSection(driver).verifyFilterBoxInTags();			//Adding because getting blank page on tags
		if(status) {
			click(firstRowTagTable,"Clicking on first record of tag table");
		}else {
			sleepFor(500);
			refreshPage();
			click(firstRowTagTable,"Clicking on first record of tag table");
		}
	}
	
	/**
	 * Click on delete selected tag under action
	 */
	public void deleteTag() {
		navigationMenu.clickOnIcon("Delete selected tag", "Actions");		
		new WindowHelper(driver).waitForPopup("Delete");
		new WindowHelper(driver).clickOkOnPopup();
		navigationMenu.waitForAddIcon();
	}
	
	/**
	 * Get delete popup warning message
	 * @return message under delete popup
	 */
	public String getDeletePopupMessage() {
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup("Delete");
		String warnMsg =  windowHelper.getPopupMessage();
		sleepFor(2000);  
		windowHelper.clickOkOnPopup();
		return warnMsg;
	}
	
	/**
	 * Click on next button in viewer
	 * @throws InterruptedException
	 */
	public void clickOnNextButtonViewer() throws InterruptedException {
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		click(btnNextViewer, "Clicking on next button in viewer");
		Thread.sleep(1000);
	}
	
	/**
	 * Click on previous button in viewer
	 */
	public void clickOnPreviousButtonViewer() {
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		click(btnPreviousViewer, "Clicking on previous button in viewer");
	}
	
	/**
	 * Click on link one or more document icon under docucment
	 */
	public void clickOnLinkDocumentIcon() {
		navigationMenu.clickOnIcon("Link one or more documents", "Document");
		navigationMenu.waitForIcon("Close Document Linking");
		
	}
	
	/**
	 * Click on add icon in link document under linking
	 * @param linkLabel link label which needs to be selected and added
	 */
	public void clickOnAddIconLinkDocs(String linkLabel) {
		click(btnAddDocLinkLabel, "Click on add doc link label");
		new WindowHelper(driver).waitForModalDialog("Add Document Link Label");
		sendKeys(txtDocumentLinkLabel, linkLabel, "Entering link label as "+linkLabel);
		new WindowHelper(driver).clickOnModalFooterButtonForLinking("Save ");
		navigationMenu.waitForFilterTextBox();
	}
	
	/**
	 * Click ok on document linking popup
	 * @return
	 */
	public String clickOkOnDocLinkingPopup(String environment) {
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup("Document Linking");
		String actMsg = windowHelper.getPopupMessage();
		windowHelper.clickOnModalFooterButton("Ok");
		windowHelper.waitForPopup("Document Linking");
		sleepFor(2000);
		 windowHelper.clickOkOnPopup();
		 navigationMenu.clickOnIcon("Close Document Linking", "Actions");
		 sleepFor(1000);
		 navigationMenu.waitForMenuIcon("Show results statistics","General",environment);
		return actMsg;
	}
	
	/**
	 * Click on save linking under linking
	 */
	public void clickOnSaveLinking() {
		 navigationMenu.clickOnIcon("Save Document Linking", "Actions");
	}
	
	/**
	 * Click on landing page icon under document
	 */
	public void clickOnLandingPageIcon() {
		navigationMenu.clickOnIcon("Document landing page", "Document");
		navigationMenu.waitForIcon("Cancel changes");
	}
	
	/**
	 * Click on intray landing page under document
	 */
	public void clickOnIntrayLandingPageIcon() {
		navigationMenu.clickOnIcon("Intray landing page", "Document");
		navigationMenu.waitForIcon("Cancel changes");
	}
	
	/**
	 * Get link Name from referencing tab
	 * @param lastRowNum last row num e.g 1,2 
	 * @return
	 */
	public String getLinkNameFromRefrencingTab(String lastRowNum) {
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		//navigationMenu.clickOnNavTab("Referencing");		//commented because in R541 not working
		
		click(referencingTab, "Clicking on Referencing tab ");
		//navigationMenu.waitForFilterTextBoxSubgrid();
		//new JavaScriptHelper(driver).scrollToBottom();
		boolean filterStatus=new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.txtFilterSubgrid);
		if(!filterStatus) {				//Added because filtertxt is not visible
			refreshPage();
			sleepFor(2000);
			navigationMenu.clickOnNavTab("Referencing");
			navigationMenu.waitForFilterTextBoxSubgrid();
			new JavaScriptHelper(driver).scrollToBottom();
		}else {
			navigationMenu.waitForFilterTextBoxSubgrid();
			new JavaScriptHelper(driver).scrollToBottom();
		}
		
		return navigationMenu.getTableCellValue("referencingSubGrid", "last()-"+lastRowNum, "2");
	}
	
	/**
	 * Click on view document landing page
	 * @return view document landing page text
	 */
	public String clickOnViewDocumetLandingPage() {
		navigationMenu.clickOnIcon("View document", "Document");
		sleepFor(500);
		new WindowHelper(driver).switchToNewlyOpenedTab();
		waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
		return navigationMenu.getNavbarText();
	}
	
	/**
	 * Click on launch linked document in external viewer under document
	 * @param lastRowNum e.g 1,2
	 * @return true,false
	 * @throws InterruptedException
	 */
	public boolean clickOnLaunchLinkedDocLandingPage(String lastRowNum) throws InterruptedException {
		navigationMenu.clickOnIcon("Launch document in external viewer", "Document");
		String guidValue = navigationMenu.getTableCellValue("referencingSubGrid", "last()-"+lastRowNum, "5");
		Thread.sleep(2000);
		boolean isFileDownloaded = navigationMenu.isDownloadedFileExist(guidValue);
		return isFileDownloaded;
	}
	
	/**
	 * Click on delete document under document section
	 * @return delete popup message
	 */
	public String clickOnLinkedDeleteDocument() {
		navigationMenu.clickOnIcon("Delete document", "Document");
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup(DELETE_DOC);
		String popMsg = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		return popMsg;
	}
	
	/**
	 * Click on edit document under version control
	 * @throws InterruptedException
	 */
	public void clickOnEditIconUnderVersionControl() throws InterruptedException {
		navigationMenu.clickOnIcon("Edit document", "Version Control");
		Thread.sleep(1000);//Waiting for item to download
		new WindowHelper(driver).waitForPopup("Edit Document");	
		
	}
	
	/**
	 * Click on edit document popup
	 * @param buttonText text with which modal footer button
	 * @throws InterruptedException
	 */
	public void clickOnEditDocumentPopup(String buttonText) throws InterruptedException {
		new WindowHelper(driver).clickOnModalFooterButton(buttonText);
		Thread.sleep(2000);//waiting for reloading of page
		waitTillInvisiblityOfBusyIcon(20);
		//navigationMenu.waitForFilterTextBox();
	}
	
	/**
	 * Check if green tick is present or not on checked out
	 * @return true,false
	 */
	public boolean isGreenTickPresentCheckedOutInd(String colName) {	
		return navigationMenu.isGreenTickPresentForColumn(colName);
	}
	
	/**
	 * Click on undo edit of version control and get message of popup
	 * @return undo edit popup message
	 */
	public String clickOnUndoEditAndGetMessage() {
		navigationMenu.clickOnIcon("Undo edit", "Version Control");
		return new WindowHelper(driver).getPopupMessageClickOk("Undo Edit");
	}

	/**
	 * Click on undo edit of version control and click on ok
	 */
	public void clickOnUndoEditAndOk() {
		navigationMenu.clickOnIcon("Undo edit", "Version Control");
		new WindowHelper(driver).waitForPopup("Undo Edit");
		sleepFor(1000);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
	}
	
	/**
	 * Get undo edit document popup message
	 * @return get popup message of undo edit
	 */
	public String getEditDocumentPopupMessage() {
		new WindowHelper(driver).waitForPopup("Undo Edit");
		String msg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		return msg;
	}
	
	/**
	 * Click on update document of version control
	 */
	public void clickOnUpdateDocument() {
		navigationMenu.clickOnIcon("Update document", "Version Control");
	}
	
	/**
	 * Get popup message title
	 * @param popupTitle popup title of popup window
	 * @return message of popup
	 */
	public String getPopupMessage(String popupTitle) {
		return new WindowHelper(driver).getPopupMessageClickOk(popupTitle);
	}
	
	/**
	 * Click on update document and get message of update doc
	 * @return get popup message of update doc
	 */
	public String clickOnUpdateDocumentAndGetMessage() {
		clickOnUpdateDocument();
		return getPopupMessage(UPDATE_DOC);	
	}
	
	/**
	 * Wait for update document form
	 */
	public void waitForUpdateDocumentForm() {
		new WindowHelper(driver).waitForModalDialog(UPDATE_DOC);
	}
	
	/**
	 * Click on update document popup with button text
	 * @param buttonText button text on which needs to be clicked
	 */
	public void clickOnUpdateDocumentPopup(String buttonText) {
		new WindowHelper(driver).waitForModalDialog(UPDATE_DOC);
		new WindowHelper(driver).clickOnModalFooterButton(buttonText);
	}
	
	/**
	 * Get the latest file from downloads folder and uploads it
	 */
	public void clickOnFileUploadOnUpdateDocument() {
		click(btnFileUploadDocumentCheckIn, "Clicking on upload file documents check in");
		String downloadExePath = "RecentFileDownloads.exe";
		String uploadDownloadedFileAutoExe = ObjectReader.reader.getAutoITExeFolderPath()+downloadExePath; 
		try {
			Thread.sleep(3000);
		    Runtime.getRuntime().exec(uploadDownloadedFileAutoExe);
		    Thread.sleep(4000);
		} catch (Exception e) {
		    log.info("Exception while uploading file at "+uploadDownloadedFileAutoExe+" Exception is " + e);
		}		
	}
	
	/**
	 * Enter comments update document with input comment
	 * @param inputComment entering comment upload document checkIn
	 */
	public void enterCommentsUpdateDocuments(String inputComment) {
		waitHelper.waitForElementClickable(driver, txtCommentUploadDocumentCheckIn, 20);
		sendKeys(txtCommentUploadDocumentCheckIn, inputComment, "Entering comment as "+inputComment);
		
	}
	
	/**
	 * Get combined revision of upload document
	 * @return combined version of document which needs to be uploaded
	 */
	public String getRevisionUploadDocument() {
		WebElement majorVersion=driver.findElement(By.xpath(String.format(tmpTxtVersionUploadDocumentCheckIn, "Major")));
		String majorText = majorVersion.getAttribute("Value");
		WebElement minorVersion=driver.findElement(By.xpath(String.format(tmpTxtVersionUploadDocumentCheckIn, "Minor")));
		String minorText = minorVersion.getAttribute("Value");
		WebElement revisionVersion=driver.findElement(By.xpath(String.format(tmpTxtVersionUploadDocumentCheckIn, "Revision")));
		String revsionText = revisionVersion.getAttribute("Value");
		int revsionText_1=Integer.parseInt(revsionText)+1;				//Need to add +1 because after update values gets updated by 1
		String concatVal = ".";
		return majorText+concatVal+minorText+concatVal+String.valueOf(revsionText_1);
	}
	
	/**
	 * 
	 * @param buttonText
	 * @throws InterruptedException
	 */
	public void clickOnUpdateDocumentButton(String buttonText) throws InterruptedException {	
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForModalDialog(UPDATE_DOC);
		windowHelper.clickOnModalFooterButton(buttonText);
		Thread.sleep(1000);//Waiting for document to be uploaded
		windowHelper.waitForModalDialogToBeInvisble(driver, "Update Document", 20);
		sleepFor(1000);
		boolean status=new CapturePage(driver).verifyFilterTextBoxInIntray();				//Adding because filter text box is not visible
		if(status) {
			navigationMenu.waitForFilterTextBox();
			
		}else {
			refreshPage();
			navigationMenu.waitForFilterTextBox();
		}
	}
	
	/**
	 * Get the version number of first document
	 * @return version number of document in ver number column
	 */
	public String getFirstDocumentVersion() {
		return navigationMenu.getColumnValueFromTable("Ver Number");
	}
	
	/**
	 * Click on editable document under capture
	 */
	public void clickonEditableDocumentUnderCapture() {
		navigationMenu.clickOnIcon("List all my checked-out documents", "Other");
		navigationMenu.waitForFilterTextBox();
		
	}
	
	/**
	 * Click on editable document under Admin
	 */
	public void clickOnEditableDocumentUnderAdmin() {
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("List all checked-out documents", "Document");
		
		sleepFor(1000);
		boolean status=new CapturePage(driver).verifyFilterTextBoxInIntray();				//Adding because filter text box is not visible
		if(status) {
			navigationMenu.waitForFilterTextBox();
			
		}else {
			refreshPage();
			navigationMenu.waitForFilterTextBox();
		}
		
	}
	
	/**
	 * Click on manage tags under document
	 */
	public void clickOnManageTagsUnderDocument() {
		navigationMenu.clickOnIcon("Manage tags for selected document", "Document");
		new WindowHelper(driver).waitForModalDialog("Add/Remove Tags");
	}
	
	/**
	 * Select tags from add or remove tag popup 
	 */
	public void selectTagsFromAddRemoveTagsPopup() {
		sleepFor(1000);
		new ActionHelper(driver).moveToElementAndClick(txtAreaInAddTagPopup);
		click(waitHelper.waitForElementClickable(driver, ddTagsOptionInAddTagPopup, 20),"Clicking on dropdown tag");
		try {
		click(waitHelper.waitForElementClickable(driver, ddTagsOptionInAddTagPopup, 20),"click on dropdown tag");
			}
			catch (Exception e) {
				log.info("Exception while selecting tag from Add remove or tags popup "+e);
		}
		}
	
	/**
	 * Click on document delete
	 * @return view document message
	 */
	public String clickOnDocumentDelete() {
		navigationMenu.clickOnIcon("Delete document", "Document");
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.waitForPopup(DELETE_DOC);
		windowHelper.clickOkOnPopup();
		windowHelper.waitForPopup(DELETE_DOC);
		windowHelper.clickOkOnPopup();
		windowHelper.waitForPopup(DELETE_DOC);
		String msg = windowHelper.getPopupMessage();
		windowHelper.clickOkOnPopup();
		navigationMenu.waitForIcon("View document");
		return msg;
	}
	
		/**
		 * Click on document delete cancel button
		 */
		public void clickOnDocumentDeleteCancel() {
		navigationMenu.clickOnIcon("Delete document", "Document");
		new WindowHelper(driver).waitForPopup(DELETE_DOC);
		sleepFor(1000);
		new WindowHelper(driver).clickOnModalFooterButton("Cancel");
	}
		
		/**
		 * Select from input document type under index of More section
		 * @param selectValue
		 */
		public void selectFromInputDocTypesUnderIndex(String selectValue) {
			WebElement moreEle = driver.findElement(By.xpath(String.format(tmpBtnMoreSection, "Index")));
			if(!moreEle.getAttribute("class").contains("active")) {
				click(moreEle, "Clicking on index more button");
			}
				new DropdownHelper(driver).selectFromSelectsizeDropdown("input-docTypes", selectValue);
		}
		
		/**
		 * Click on document list button
		 */
		public void clickOnDocumentListButton() {
			navigationMenu.clickOnIcon("Run document search", "Search");
			navigationMenu.waitForFilterTextBoxSearchResult();
			
		}
		
		/**
		 * Get all document type of row and return a set of values
		 * @return unique set of values
		 */
		public String getAllDocumentTypeValuesToSet() {
			return navigationMenu.getColumnValueFromTable("Document Type");
			//navigationMenu.getAllColumnValues(table, index);
		}
		
		
		public String getAllFolder1RefToSet() {
			return navigationMenu.getColumnValueFromTable("Account Ref");
			//navigationMenu.getAllColumnValues(table, index);
		}
		
		/**
		 * Select from team under intray section of more search
		 * @param teamName teamName intray section
		 */
		public void selectFromTeamUnderIntray(String teamName) {
			//expandMoreSectionIfHidden("Intray");
			//new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(teamName, ddTeamUnderIntrayMoreSearch); 
			new JavaScriptHelper(driver).scrollToElement(ddTeamUnderIntrayMoreSearch);
			sleepFor(2000);
			new DropdownHelper(driver).selectFromAutocompleDD(teamName, ddTeamUnderIntrayMoreSearch);
		}
		
		/**
		 * Expand more section if its collapsed
		 * @param sectionName section name which needs to be expanded
		 */
		public void expandMoreSectionIfHidden(String sectionName) {
			WebElement moreEle = driver.findElement(By.xpath(String.format(tmpBtnMoreSection,sectionName)));
			new JavaScriptHelper(driver).scrollToElement(moreEle);		    	
	    	sleepFor(2000);
			if(!moreEle.getAttribute("class").contains("active")) {
				waitHelper.waitForElementClickable(driver, moreEle, 20);											
				click(moreEle, "Clicking on index more button");
			}
		}
		
		/**
		 * Select status from mail status intray under more search
		 * @param selectStatusValue  select mail status value
		 */
		public void selectFromMailStatusIntrayMoreSearch(String selectStatusValue,String environmentVar) {
			if(environmentVar.equals("Enterprise_Sp1s")) {
				expandMoreSectionIfHidden("OtherCriteria");
				new DropdownHelper(driver).selectFromSelectsizeDropdown("input-mailStatuses", selectStatusValue);
			}else {
				new JavaScriptHelper(driver).scrollByPixel();
				sleepFor(2000);
				expandMoreSectionIfHidden("Intray");
				new DropdownHelper(driver).selectFromSelectsizeDropdown("input-mailStatuses", selectStatusValue);
			}
		}
		
		/**
		 * Translate folder reference to provided transRef param
		 * @param transRef reference with which needs to be translated
		 * @return successful translated reference popup message
		 */
		public String translateFolderReferenceTo(String transRef) {
			navigationMenu.clickOnIconUnderAction("Translate");
			navigationMenu.waitForIcon("Cancel changes");
			//Code for entering destRef
			WebElement destRefEle = driver.findElement(By.xpath(String.format(tmpTxtFolderRefInput, "Destination Ref")));
			sendKeys(destRefEle, transRef, "Entering Translater ref as "+ transRef);
			new ActionHelper(driver).pressTab();
			waitHelper.waitForElement(lblReferenceDetailsFolderRef,20);
			
			navigationMenu.clickOnIconUnderAction("Save");
			WindowHelper windowHelper=new WindowHelper(driver);
			windowHelper.waitForPopup("Translate");
			String transRefMsg = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			sleepFor(2000);
			//Clicking on successfull popup
			windowHelper.waitForPopup("Translate");
			String getPopUpMsg = new WindowHelper(driver).getPopupMessage();
			if(getPopUpMsg.equals("Translation has been successful")) {
				windowHelper.clickOkOnPopup();
				sleepFor(3000);
			}else{
				new WindowHelper(driver).clickOkOnPopup();
				new WindowHelper(driver).waitForPopup("Translate");
				windowHelper.clickOkOnPopup();
				sleepFor(3000);
			}
			//new WaitHelper(driver).waitForElement(windowHelper.dialogOkBtn, 10);
			//windowHelper.clickOkOnPopup();			//added by amit
			return transRefMsg;
		}
		
		/**
		 * Click on file system and search with critera
		 * @param label e.g Doc Ref, Account Ref
		 * @param searchValue e.g F1-001R1
		 */
		public void searchWithCriteriaAllFileSystem(String label, String searchValue,String environment ) {
			new HomePage(driver).clickOnAllFileSystemButton(environment);	
			new CapturePage(driver).searchWithCriteria(label, searchValue);
		}
		
		/**
		 * Click on other file system and select provided exp file system
		 * @param expFileSystem expected file system
		 */
		public void clickOnOtherFileSystem(String expFileSystem) {
			navigationMenu.clickOnIconMenu("Select Other file system row count", "General");
			navigationMenu.clickOnIconMenu(expFileSystem);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			
			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);			//Added because getting blank page
			sleepFor(1000);
			if(!status) {
				refreshPage();
			}
			navigationMenu.waitForFilterTextBoxSearchResult();
		}
		
		/**
		 * Checking doc exist in othere file system with All file system search
		 * @param expFileSystem expected file system
		 * @param searchDocRef Doc Ref of document which needs to be searched
		 * @return Actual file system name found after search
		 */
		public String checkIfDocExistInFileSystem(String expFileSystem, String searchDocRef,String environment) {
			getApplicationUrl();
			searchWithCriteriaAllFileSystem("Doc Ref",searchDocRef,environment );
			clickOnDocumentListButton();
			clickOnOtherFileSystem(expFileSystem);
			String actFileSystem = navigationMenu.getColumnValueFromTable("File System");
			return actFileSystem;
		}


		public boolean isOtherIconClickable() {
			try {
			navigationMenu.clickOnIconMenu("Select Other file system row count", "General");
			return true;
			}
			catch (Exception e) {
				log.info("Exception is other icon clickable or not "+e);
				return false;
			}
			}
	
	
		/**
		 * Select from user under intray section of more search
		 * @param userName intray section
		 */
		public void selectFromUserUnderIntray(String userName,String environmentVar) {
			if(environmentVar.equals("Enterprise_Sp1s")) {
				expandMoreSectionIfHidden("OtherCriteria");
				new DropdownHelper(driver).selectFromAutocompleDDWithoutInputForUser(userName, ddUserUnderIntrayMoreSearch);
			}else {
				new JavaScriptHelper(driver).scrollByPixel();
				sleepFor(2000);
				expandMoreSectionIfHidden("Intray");
				//new JavaScriptHelper(driver).scrollToElement(ddUserUnderIntrayMoreSearch);
				new DropdownHelper(driver).selectFromAutocompleDDWithoutInputForUser(userName, ddUserUnderIntrayMoreSearch);
			}
			
		}
		
		public void selectFromUserUnderIntray(String selectValue) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			WebElement moreEle = driver.findElement(By.xpath(String.format(tmpBtnMoreSection, "IntrayCriteriaMore")));
			if(!moreEle.getAttribute("class").contains("active")) {
				click(moreEle, "Clicking on index more button");
			}
			
			new DropdownHelper(driver).selectFromSelectsizeDropdown("input-Users", selectValue,"input-users");	
		}
		
		/*
		 * Used to create internal rendition of document
		 */
		public void createInternalRendition() {
			navigationMenu.clickOnIconMenu("Create Internal Rendition", "Rendition");
			sleepFor(3000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			log.info("First Switch=========="+driver.getWindowHandle());
			new WaitHelper(driver).waitForElement(renditionViewerHeader, 10);
			click(saveRenditionBtn, "Clicking on save rendition btn");
			sleepFor(1000);
			sendKeys(renditionNameInput, "sample","Entering the input");
			click(confirmYesBtn,"Clicked on confirm yes button");
			sleepFor(4000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			new WaitHelper(driver).waitForElement(navigationMenu.filterTxt, 10);
		}
		
		
		/*
		 * It is used to click on Create and Edit option of Public Access
		 */
		public void clickOnCreateAndEditButtonOfPublicAccess() {
				click(publicAccessBtn, "Clickng on public access button");
				navigationMenu.clickOnIconMenu("Create and edit rendition", "Rendition");
				sleepFor(5000);
				new WindowHelper(driver).switchToNewlyOpenedTab();
				System.out.println("First Switch=========="+driver.getWindowHandle());
				//new WaitHelper(driver).waitForElement(renditionViewerHeader, 10);
		}
		
		
		/*
		 * Publish the document on public access
		 */
		public void PublishDocumentOnPublicAccess(String renditionType,String publishOption) {
			String saveAndPublishRenditionXpath = String.format(tmpPublishType, publishOption);
			WebElement saveAndPublishRenditionEle = driver.findElement(By.xpath(saveAndPublishRenditionXpath));
			
			click(saveRenditionBtn, "Clicking on save rendition btn");
			sleepFor(1000);
			sendKeys(renditionNameInput, "sample", "Entering the input");
			
			new DropdownHelper(driver).selectFromAutocompleDD(renditionType,renditionTypeDD);
			click(saveAndPublishRenditionEle, "Selecting Option"+publishOption);
			sleepFor(1000);
			click(confirmYesBtn,"Clicked on confirm yes button");
			sleepFor(8000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
		}	
		
		public void SelectRedactionTemplateForPA(String tempName) {
			new AdminUserSection(driver).clickOnUserNavTab("Redaction");
			sleepFor(3000);
			
			click(redactionTempDD, "Clicking on redaction template DD");
			//new DropdownHelper(driver).selectFromAutocompleDD(tempName, redactionTempDD);

			WebElement eleDD = driver.findElement(By.xpath("//li/a/span[text()='"+tempName+"']"));
			new ActionHelper(driver).moveToElementAndClick(eleDD);
			new NavigationMenu(driver).clickOnIcon("Apply Redaction", "Redaction Template");
			sleepFor(3000);
		}
		
		public void verifyDocumentOnPublicAccess(String accRef) {
			String getPublicAccessURL = ObjectReader.reader.getPublicAccessUrl();
			
			new JavaScriptHelper(driver).openNewTabUsingJavascriptExecutor();
			new WindowHelper(driver).switchToNewlyOpenedTab();
			driver.get(getPublicAccessURL);
			
			new WaitHelper(driver).waitForElement(filtexTxtForPublicAccess, 10);
			sleepFor(1000);
			sendKeys(filtexTxtForPublicAccess, accRef, "Entering account reference");
			
			String getAccountRef = driver.findElement(By.xpath("//table[@id='searchResult']/tbody/tr[1]/td[5]")).getText();
			System.out.println("getAccountRef "+getAccountRef);
			AssertionHelper.verifyText(getAccountRef, accRef);
			//driver.close();
			//new WindowHelper(driver).switchToNewlyOpenedTab();
		}
		
		public void ViewDocumentOnPublicAccess(String accRef) {
			String tmpViemDocLoc = String.format(tmpViewDoc, accRef);
			WebElement viewDocEle = driver.findElement(By.xpath(tmpViemDocLoc));
			click(viewDocEle, "Clicking on view document button");
			sleepFor(3000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			sleepFor(20000);
//			WebElement ele = driver.findElement(By.xpath("//pdf-viewer[@id='viewer']")).getShadowRoot().
//					findElement(By.cssSelector("viewer-toolbar.toolbar")).getShadowRoot().
//					findElement(By.cssSelector("span.title"));
//			new WaitHelper(driver).waitForElement(ele, 20);
//			String getTxt = ele.getText();
//			System.out.println("getText============================"+getTxt);
			driver.close();
		}
		
		public void ApplyRedactionTemplate(String tempName) {
			new NavigationMenu(driver).clickOnTab("Redaction");
			sleepFor(2000);
			new DropdownHelper(driver).selectFromAutocompleDDInPopup(tempName, ddredactionTemplate);
			click(redactionApplyButton, "Clicking on redaction apply button");
			sleepFor(2000);
			
			new NavigationMenu(driver).clickOnTab("Viewer");
		}
		
		/*
		 * Used to click on Version history button
		 */
		public void clickOnVersionHistoryUnderVersionControl() {
			navigationMenu.clickOnIcon("Show document version history list", "Version Control");
		}
		
		/*
		 * It is used to view document under version history
		 */
		public void viewDocumentUnderVersionHistory() {
			click(tmpRowUndeVersionHistory, "Clickng on First Row Under Version History");
			navigationMenu.clickOnIconMenu("View document", "Selected");
			sleepFor(3000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			new WaitHelper(driver).waitForElement(documentVersionViewerNavBar, 10);
			driver.close();
			new WindowHelper(driver).switchToNewlyOpenedTab();
		}
		
		/*
		 * It is used to launch document under version history
		 */
		public void launchDocumentUnderVersionHistory() {
			click(tmpRowUndeVersionHistory, "Clickng on First Row Under Version History");
			navigationMenu.clickOnIconMenu("Launch document in external viewer", "Selected");
			sleepFor(3000);
		}
		
		/*
		 * It is used to verify rendition status under vesion history
		 */
		public void verifyRenditionStatusUnderVersionHistory() {
			click(tmpRowUndeVersionHistory, "Clickng on First Row Under Version History");
			navigationMenu.clickOnIconMenu("View rendition", "Selected");
			click(tmpRenditonTableRow, "Clicking on first row");
		}
		
		/*
		 * It is used to click on unpublish option of public access
		 */
		public void unpublishDocumentOnPublishAccess(String environment) {
			click(publicAccessBtn, "Clickng on public access button");
			navigationMenu.clickOnIconMenu("Unpublish", "Rendition");
			
			if(!environment.equals("Enterprise_R522")) {
			new WindowHelper(driver).waitForPopup("Unpublish Rendition");
			new WindowHelper(driver).clickOkOnPopup("Unpublish Rendition");
			sleepFor(3000);
			}
		}
		
		
		/*
		 * It is used to click on create and publish option of public access
		 */
		public void clickOnCreateAndPublishOption() {
			click(publicAccessBtn, "Clickng on public access button");
			navigationMenu.clickOnIconMenu("Create and publish rendition", "Rendition");
		}
		
		/*
		 * It is used to click on publish option of public access
		 */
		public void clickOnPublishOption() {
			click(publicAccessBtn, "Clickng on public access button");
			navigationMenu.clickOnIconMenu("Publish document", "Rendition");
		}
		
		/*
		 * It is used to click on publish now option of document connect
		 */
		public void ClickOnPublishNowOption() {
			click(documentConnectBtn, "Clicking on document connect button");
			navigationMenu.clickOnIconMenu("Publish now rendition", "Rendition");
		}
		
		/*
		 * It is used to click on unpublish option of Document Connect Site
		 */
		public void UnpublishDocumentOnDocumentConnect() {
			click(documentConnectBtn, "Clicking on document connect button");
			//navigationMenu.clickOnIconMenu("Unpublish document", "Rendition");
			click(documentConnectUnpublishBtn, "Clicking on unpublish button");
			
			new WindowHelper(driver).waitForPopup("Unpublish Rendition");
			new WindowHelper(driver).clickOkOnPopup("Unpublish Rendition");
			sleepFor(3000);
		}
		
		public void ClickOnAddNotesToDocument() {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(1000);
			click(notesTab, "Clicking on Notes Tab");
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(1000);
			click(createNoteButton, "Clicking on add notes button");
		}
		
		public void EnterTitleInNotes(String notesTitle) {
			sendKeys(enterNotesTitle, notesTitle, "Entering notes title "+notesTitle);
		}
		
		public void SelctParagraphforDocument(String para,String getEnvVariable) {
			if(getEnvVariable.equals("Enterprise_R551")) {
				sleepFor(1000);
				click(ddParagraph, "Clicking on paragraph");
				new DropdownHelper(driver).selectFromAutocompleDD(para, ddParagraph551OnWards,getEnvVariable);
			}else {
				sleepFor(1000);
				new DropdownHelper(driver).selectFromAutocompleDD(para, ddParagraph);
			}
		}
		
		public void ClickOnEditAndPublishOptionOfDocumentConnect() {
			click(documentConnectBtn, "Clicking on document connect button");
			new NavigationMenu(driver).clickOnIcon("Edit and publish rendition", "Rendition");
			sleepFor(5000);
			new WindowHelper(driver).switchToNewlyOpenedTab();
			System.out.println("First Switch=========="+driver.getWindowHandle());
		}
		
		/*
		 * Publish the document on Document Connect
		 */
		public void PublishDocumentOnDocumentConnect() {
			click(saveRenditionBtn, "Clicking on save rendition btn");
			sleepFor(1000);
			sendKeys(renditionNameInput, "sample", "Entering the input");
		
			click(confirmYesBtn,"Clicked on confirm yes button");
			sleepFor(5000);
		}

		/*
		 * It is used to select multi selectcheck box
		 */
		public void SelectMultiSelectCheckbox() {
			boolean chkStatus = new VerificationHelper(driver).isElementSelected(multiSelectCheckbox);
			
			if(!chkStatus) {
				click(multiSelectCheckbox, "Clicking on multi select checkbox");
			}	
		}
		
		public void UnselectMultiSelectCheckbox() {
			boolean chkStatus = new VerificationHelper(driver).isElementSelected(multiSelectCheckbox);
			
			if(chkStatus) {
				click(multiSelectCheckbox, "Clicking on multi select checkbox");
			}	
		}
		
		public void WaitForFolderRefTitleWhileRereference(String folderRef) {
			//WebElement folderRefTitle = driver.findElement(By.xpath(String.format(folderRefNavBarTitle, folderRef)));
			//new WaitHelper(driver).waitForElement(folderRefTitle, 10);
			new WaitHelper(driver).waitForElementVisible(driver.findElement(By.xpath(String.format(folderRefNavBarTitle, folderRef))), 15, 3);
		}
		
		/*
		 * Click No on Confirm Rereference popup
		 */
		public void clickNoOnConfirmReferencePopUp() {
			new WindowHelper(driver).waitForPopup("Re-Reference");
			sleepFor(2000);
			click(confirmNoBtn, "Clicking on Confirm No button");
			navigationMenu.waitForIcon("Clear changes","Actions");
		}
		
		/*
		 * Clicking Cancel on confirm rereference popup
		 */
		public void clickCancelOnConfirmReferencePopUp() {
			new WindowHelper(driver).waitForPopup("Re-Reference");
			sleepFor(2000);
			click(confirmCancelBtn, "Clicking on Confirm Cancel button");
			navigationMenu.waitForIcon("Clear changes","Actions");

		}
		
		/**
		 * Enter folder3 reference on ReReference page
		 * @param folder3ref e.g F3-001R3
		 */
		public void enterFold3Ref(String folder3ref) {
			sendKeys(txtFolder3Ref,folder3ref,"Enter folder 3 ref ="+folder3ref);
			new ActionHelper(driver).pressTab();
			waitHelper.waitForElement(folder3RefDetailsLbl,20);
		}
		
		public void WaitForEditFolderRefTitleWhileRereference(String folderRef) {
			new WaitHelper(driver).waitForElementVisible(driver.findElement(By.xpath(String.format(folderRefEditNavBarTitle, folderRef))), 15, 3);
		}


		public void WaitForEditFolderRefTranslateTitleWhileRereference(String folderRef) {
			new WaitHelper(driver).waitForElementVisible(driver.findElement(By.xpath(String.format(folderRefEditTranslateNavBarTitle, folderRef))), 15, 3);
		}

		/*
		 * It is used to select team value on more search page Onwards Releaes550
		 */
		public void	selectFromInputTeamUnderIntray(String selectValue) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			WebElement moreEle = driver.findElement(By.xpath(String.format(tmpBtnMoreSection, "IntrayCriteriaMore")));
			if(!moreEle.getAttribute("class").contains("active")) {
				click(moreEle, "Clicking on index more button");
			}
			
			new DropdownHelper(driver).selectFromSelectsizeDropdown("input-Teams", selectValue,"input-teams");
		}
		
		/*
		 * It is used to select user value on more search page Onwards Releaes550
		 */
		public void	selectFromInputUserUnderIntray(String selectValue) {
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			WebElement moreEle = driver.findElement(By.xpath(String.format(tmpBtnMoreSection, "IntrayCriteriaMore")));
			if(!moreEle.getAttribute("class").contains("active")) {
				click(moreEle, "Clicking on index more button");
			}
			
			new DropdownHelper(driver).selectFromSelectsizeDropdown("input-Users", selectValue,"input-users");
		}
		
		public void EnterInDocRefField(String docRef) {
			new WaitHelper(driver).waitForElement(docRefInputFieldOnMoreSearch, 10);
			sendKeys(docRefInputFieldOnMoreSearch, docRef, "Inputting Doc Ref value "+docRef);
			new ActionHelper(driver).pressTab();
		}
		
		/*
		 * It is used to select user/team/doc type/mail status value on more search page Onwards Releaes550
		 */
		public void	selectFromInputUnderMoreSearch(String selectValue,String dropDownId,String dropDownId1) {
			//new JavaScriptHelper(driver).scrollToBottom();
			//sleepFor(2000);
			WebElement moreEle = driver.findElement(By.xpath(String.format(tmpBtnMoreSection, "IntrayCriteriaMore")));
			if(!moreEle.getAttribute("class").contains("active")) {
				click(moreEle, "Clicking on index more button");
			}
			
			new DropdownHelper(driver).selectFromSelectsizeDropdownFrom550Onwards(dropDownId, selectValue,dropDownId1);
		}
		public void	enterValueUnderMoreSearch(String sectionName, String dropDownId, String selectValue) {
			expandMoreSectionIfHidden(sectionName);
			
			WebElement inputEle = driver.findElement(By.xpath(String.format(dropdownHelper.tmpSelectsizeInputAfter550, dropDownId)));
			//inputEle.click();
			new JavaScriptHelper(driver).scrollToElement(inputEle);
			sleepFor(2000);
			new JavaScriptHelper(driver).clickElement(inputEle);
			inputEle.sendKeys(selectValue);	
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER).build().perform();
		}
		
		public TreeSet<String> getAllValuesUnderMoreSearch(String sectionName, String dropDownId)
		{
			expandMoreSectionIfHidden(sectionName);
			sleepFor(2000);
			WebElement abc = driver.findElement(By.xpath(""));
			
			List<WebElement> inputEle = driver.findElements(By.xpath(String.format(dropdownHelper.tmpgetElementValue, dropDownId)));
			TreeSet<String> all_elements_text = new TreeSet<String>();
			
			sleepFor(2000);
			for(int i=0; i<inputEle.size(); i++){
				all_elements_text.add(inputEle.get(i).getAttribute("data-value"));
			}
			return all_elements_text;
		}
		
		public TreeSet<String> getAllValuesMailStatusesInputs()
		{
			List<WebElement> inputEle = homePage.MailStatusAllDefaultInputs;
			TreeSet<String> all_elements_text = new TreeSet<String>();
			sleepFor(2000);
			for(int i=0; i<inputEle.size(); i++){
				all_elements_text.add(inputEle.get(i).getAttribute("data-value"));
			}
			return all_elements_text;
		}	
		
		public String getValueUnderMoreSearch(String sectionName, String dropDownId)
		{	
			expandMoreSectionIfHidden(sectionName);
			sleepFor(3000);
			WebElement inputEle = driver.findElement(By.xpath(String.format(dropdownHelper.tmpgetElementValue, dropDownId)));
			sleepFor(2000);
			String actValue1 = inputEle.getText();
			return actValue1;
		}
		
		public String getBlankValueUnderMoreSearch(String sectionName, String dropDownId)
		{	
			expandMoreSectionIfHidden(sectionName);
			sleepFor(3000);
			WebElement inputEle = driver.findElement(By.xpath(String.format(dropdownHelper.tmpSelectsizeInputAfter550, dropDownId)));
			sleepFor(2000);
			String actValue1 = inputEle.getText();
			return actValue1;
		}
		
		/*
		 * It is used to group document by using coloumn name
		 */
		public void GroupDocumentListByUsingColoumn(String colName) {
			String docListGrouping = String.format(tmpDocListGroup, colName);
			WebElement groupEle = driver.findElement(By.xpath(docListGrouping));
			click(groupEle, "Clicking on group element on doc list");
		}
		
		public void ResetColumnConfiguration() {
			sleepFor(1000);
			click(new NavigationMenu(driver).gearIconBtn,"Clicking on gear icon button");
			click(resetColumnConfigurationLink,"Clicking on reset column config link");
		}
		
		public boolean VerifyDocumentGroupByDocumentType(String docType) {
			String tmpDocType = String.format(tmpDocumentGrpDocTypeVerification, docType);
			WebElement docTypeEle = driver.findElement(By.xpath(tmpDocType));
			return new VerificationHelper(driver).isElementDisplayedByEle(docTypeEle);
		}
		
		public List<String> GetReceivedDateFromDocumentList() {
			List<String> receivedDates = new ArrayList<>();
			
			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			while(sta) {
				List<WebElement> dateLists = driver.findElements(By.xpath("//table[@id='searchResultDataTable']/tbody/tr"));
				sleepFor(2000);
				for(int i=1;i<=dateLists.size();i++) {
					WebElement date1 =driver.findElement(By.xpath("//table[@id='searchResultDataTable']/tbody/tr["+i+"]/td[6]"));
					String getDate = date1.getText();
					String [] getDateSplit = getDate.split(" ");
					receivedDates.add(getDateSplit[0]);
				}
				sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
				if(sta)
					click(paginationNextButton, "Clicking on pagination next button");
				else
					break;
			}
			
			return receivedDates;
		}
		
		public List<String> GetReceiveDateValue() throws ParseException {
			List<String> receivedDates = new ArrayList<>();
			
			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			while(sta) {
				String index = new AdminWorkflowSection(driver).clmDateReceived.getAttribute("data-column-index");
				int actualIndex = Integer.parseInt(index)+1;
				List<WebElement> dateEle = driver.findElements(By.xpath("//tr[@class='odd' or @class='even']/td["+actualIndex+"]"));
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				for(WebElement ele:dateEle) {
					receivedDates.add(ele.getText().split(" ")[0]);
					
					//receivedDates.add( format.parse(ele.getText()));
				}
				log.info("Dates "+receivedDates);
				
				sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
				if(sta) {
					click(paginationNextButton, "Clicking on pagination next button");
				}else {
					break;
				}
				
			}
				return receivedDates;
		}
		
		public List<String> GetAllDocumentType() {
			List<String> docType = new ArrayList<>();
			
			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			while(sta) {
				String index = new AdminWorkflowSection(driver).clmDocumentType.getAttribute("data-column-index");
				int actualIndex = Integer.parseInt(index)+1;
				List<WebElement> dateEle = driver.findElements(By.xpath("//tr[@class='odd' or @class='even']/td["+actualIndex+"]"));
				for(WebElement ele:dateEle) {
					docType.add(ele.getText().trim());
				}
				log.info("document type "+docType);
				
				sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
				if(sta) {
					click(paginationNextButton, "Clicking on pagination next button");
				}else {
					break;
				}
				
			}
				return docType;			
		}

		public List<String> GetTargetDateValue() throws ParseException {
			List<String> receivedDates = new ArrayList<>();
			
			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			while(sta) {
				String index = new AdminWorkflowSection(driver).targetDate.getAttribute("data-column-index");
				int actualIndex = Integer.parseInt(index)+1;
				List<WebElement> dateEle = driver.findElements(By.xpath("//tr[@class='odd' or @class='even']/td["+actualIndex+"]"));
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				for(WebElement ele:dateEle) {
					receivedDates.add(ele.getText().split(" ")[0]);
					
					//receivedDates.add( format.parse(ele.getText()));
				}
				log.info("Dates "+receivedDates);
				
				sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
				if(sta) {
					click(paginationNextButton, "Clicking on pagination next button");
				}else {
					break;
				}
				
			}
				return receivedDates;
		}
		
		public List<String> GetAllAccountRef() {
			List<String> accRef = new ArrayList<>();

			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			if (sta) {
				while (sta) {
					String index = accountRefColValue.getAttribute("data-column-index");
					int actualIndex = Integer.parseInt(index) + 1;
					List<WebElement> dateEle = driver
							.findElements(By.xpath("//tr[@class='odd' or @class='even']/td[" + actualIndex + "]"));
					for (WebElement ele : dateEle) {
						accRef.add(ele.getText().trim());
					}
					log.info("account ref " + accRef);

					sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
					if (sta) {
						click(paginationNextButton, "Clicking on pagination next button");
					} else {
						break;
					}
				}
				return accRef;
			} else {
				String index = accountRefColValue.getAttribute("data-column-index");
				int actualIndex = Integer.parseInt(index) + 1;
				List<WebElement> dateEle = driver
						.findElements(By.xpath("//tr[@class='odd' or @class='even']/td[" + actualIndex + "]"));
				for (WebElement ele : dateEle) {
					accRef.add(ele.getText().trim());
				}
				log.info("account ref " + accRef);
				return accRef;
			}
		}

		public List<String> GetAllTeamName() {
			List<String> accRef = new ArrayList<>();

			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			if (sta) {
				while (sta) {
					String index = teamColValue.getAttribute("data-column-index");
					int actualIndex = Integer.parseInt(index) + 1;
					List<WebElement> dateEle = driver
							.findElements(By.xpath("//tr[@class='odd' or @class='even']/td[" + actualIndex + "]"));
					for (WebElement ele : dateEle) {
						accRef.add(ele.getText().trim());
					}
					log.info("account ref " + accRef);

					sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
					if (sta) {
						click(paginationNextButton, "Clicking on pagination next button");
					} else {
						break;
					}
				}
				return accRef;
			} else {
				String index = accountRefColValue.getAttribute("data-column-index");
				int actualIndex = Integer.parseInt(index) + 1;
				List<WebElement> dateEle = driver
						.findElements(By.xpath("//tr[@class='odd' or @class='even']/td[" + actualIndex + "]"));
				for (WebElement ele : dateEle) {
					accRef.add(ele.getText().trim());
				}
				log.info("account ref " + accRef);
				return accRef;
			}
		}
		
		public void	selectFromInputForDefaultMailStatus(String selectValue,String dropDownId,String dropDownId1) {
			sleepFor(2000);
			new DropdownHelper(driver).selectFromSelectsizeDropdownFrom550Onwards(dropDownId, selectValue,dropDownId1);
		}
		
		public List<String> GetAllMailStatus() {
			List<String> accRef = new ArrayList<>();

			boolean sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
			sleepFor(2000);
			if (sta) {
				while (sta) {
					String index = mailStatusColValue.getAttribute("data-column-index");
					int actualIndex = Integer.parseInt(index) + 1;
					List<WebElement> dateEle = driver
							.findElements(By.xpath("//tr[@class='odd' or @class='even']/td[" + actualIndex + "]"));
					for (WebElement ele : dateEle) {
						accRef.add(ele.getText().trim());
					}
					log.info("account ref " + accRef);

					sta = new VerificationHelper(driver).isElementDisplayedByEle(paginationNextButton);
					if (sta) {
						click(paginationNextButton, "Clicking on pagination next button");
					} else {
						break;
					}
				}
				return accRef;
			} else {
				String index = accountRefColValue.getAttribute("data-column-index");
				int actualIndex = Integer.parseInt(index) + 1;
				List<WebElement> dateEle = driver
						.findElements(By.xpath("//tr[@class='odd' or @class='even']/td[" + actualIndex + "]"));
				for (WebElement ele : dateEle) {
					accRef.add(ele.getText().trim());
				}
				log.info("account ref " + accRef);
				return accRef;
			}
		}

}
