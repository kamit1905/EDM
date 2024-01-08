package main.EDRM.hybridFramework.pageObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.EmailAttachment;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;

public class IntrayToolsPage extends TestBase{
			
			private WebDriver driver;
			private Logger log = LoggerHelper.getLogger(IntrayToolsPage.class);
			WaitHelper waitHelper;
			NavigationMenu navigationMenu;
			
			@FindBy(how=How.XPATH,using="(//tr[@class='odd']/td[3])[1]")
			public WebElement firstItemDocList;
			
			@FindBy(how=How.XPATH,using="//td/button[@data-button='Re-Reference']")
			public WebElement reReferenceBtn ;
			
			@FindBy(how=How.XPATH,using="//li/button[text()='Re-Reference']")
			public WebElement reReferenceListBtn ;
			
			@FindBy(how=How.XPATH,using="//label[text()='Folder1_Ref']/../../following-sibling::div/div/input")
			public WebElement folder1RefChk ;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']")
			public WebElement documentTypeddl ; 
			
			@FindBy(how=How.XPATH,using="//label[text()='Folder2_Ref']/../div/input")
			public WebElement folder2RefTxt;
			
			@FindBy(how=How.XPATH,using="//label[text()='Folder2_Ref']/../span[@class='references-details-align']")
			public WebElement folder2RefDetailsLbl;
			
			@FindBy(how=How.XPATH,using="//td/button[@data-original-title='Apply changes']")
			public WebElement applyBtn;
			
			@FindBy(how=How.XPATH,using="//li/button[@data-original-title='Apply changes']")
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
			
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxInfoOnly']/following-sibling::div/div[contains(@class,'dropdown')]/div/div[@class='option active'][1]")
			public WebElement  ddInfoOnlySelectValue; 
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxInfoOnly']/following-sibling::div/div/div[@class='item']")
			public List<WebElement> txtInfoOnlyPillBoxValues; 
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxInfoOnly']/following-sibling::div/div/div/a[@class='remove']")
			public WebElement  lnkInfoOnlyPillBoxRemoveIcon; 
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxInfoOnly']/following-sibling::div/div/input")
			public WebElement  txtInfoOnlyInputBox; 
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxEscapeMultipleRef']/following-sibling::div/div/input")
			public WebElement  txtEscalateInputBox; 
			
			@FindBy(how=How.XPATH,
					using="//div[@class='modal-body']//select[@id='pillBoxEscapeMultipleRef']/following-sibling::div/div[contains(@class,'dropdown')]/div/div[@class='option active'][1]")
			public WebElement  ddEscalateSelectValue; 
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxEscapeMultipleRef']/following-sibling::div/div/div[@class='item']")
			public List<WebElement>  txtEscalatePillBoxValues; 
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='pillBoxEscapeMultipleRef']/following-sibling::div/div/div/a[@class='remove']")
			public WebElement  lnkEscalatePillBoxRemoveIcon; 
			
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-footer']//button[@id='distributeCancel']")
			public WebElement  btnEscalateCancel;
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-footer']//button[text()='Apply changes']")
			public WebElement  btnDistributeApply;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='File_System']"),				//button for 531
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='File_System']")
			})
			public WebElement  ddFileSystemDistribute;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='DistributionType']"),			//button for 531
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='DistributionType']")
				
			})
			public WebElement  ddBtnDistributionTypeDistribute;
			
			//@FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='DistributionType']")
			//public WebElement  btnDDDistributionTypeDistribute;
			
			
			@FindBy(how=How.XPATH,using="//h4[@class='modal-title' and text()='Distribute']")
			public WebElement  lblModalTitleDistribute;
			
			//Export
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='SelectedExportType']"),			//button for 531
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='SelectedExportType']")
			})
			public WebElement  ddBtnSelectExportType;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='SelectedExportType']"),
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='SelectedExportType']")
			})
			public WebElement  ddSelectedExportFileType;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='Format']"),
				@FindBy(how=How.XPATH,using="//div[@class='modal-body']//select[@id='Format']")
			})
			public WebElement  ddFormatExport;
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-body']//input[@id='exportFileName']")
			public WebElement  txtExportFileName;
			
			//Landing page notes section
			@FindBy(how=How.XPATH,using="//input[@id='Title']")
			public WebElement  txtTitleNotesUnderAddNote;
			
			@FindBy(how=How.XPATH,using="//div[@class='tt-dataset tt-dataset-noteTitleList']/div")
			public WebElement  ddSuggestionTitleSelectNote;
			
			//nGridIntrayListConfiguration
			public String tempcolumnNamesXpath = "//table[@data-configuration-name='%s']/thead/tr/th[@tabindex='0']",
					tmpIntrayTableColumn = "(//table//th[starts-with(@aria-label,'%s:')])[1]",
			//Export module
					tmpExportCheckbox="//div[@class='modal fade form show' or @class='modal fade form in']//input[@id='%s']";			//Changed by amit for R531
			
			
			public By ddPillBoxSelectValueXpath = By.xpath("//div[@class='modal-body']//select[@id='pillBoxEscapeMultipleRef']/following-sibling::div/div[contains(@class,'dropdown')]/div/div[@class='option active'][1]");
			
			/*Added by amit*/
            @FindBy(how=How.XPATH,using="//table[@id='versionsSubgrid']//tr[contains(@class,'odd')][1]")
            public WebElement firstVersionFromList;
            
            @FindBy(how=How.XPATH,using="//td[text()='Active']/ancestor::tr[1]")
            public WebElement activeRendition;
            
            @FindBy(how=How.XPATH,using = "//button[@data-button='Next']")
            public WebElement nextMailButton;
            
            @FindBy(how=How.XPATH,using="//button[@data-icon='LinkDocument']")
            public WebElement linkingDocumentBtn;
            
            @FindBy(how = How.XPATH,using = "//p[text()='Viewer']")
            public WebElement viewerTabHeader;
            
            public String tmpMenuIconXpath = "//div/button[@data-original-title='%s']";
            
            public String versionControlIconXpath="//button[@data-button='%s']";
            
            
            @FindBy(how=How.XPATH,using = "//div[@class='modal-body']//textarea[@name='Notes']")
			public WebElement txtNotesInputBox;
            
            @FindBy(how = How.XPATH,using = "//table[@id='auditSubgrid']//td[text()='UpdateProtectiveMarker']")
            public WebElement updateProtectiveMarkerActionColumn;
            
            @FindBy(how = How.XPATH,using = "//table[@id='auditSubgrid']//td[contains(text(),'Protective Marker changed from')]")
            public WebElement updateProtectiveMarkerDetailsColumn;

            @FindBy(how = How.XPATH,using = "//table[@id='auditSubgrid']//td[text()='ReIndexDocument']")
            public WebElement reindexAuditActionColumn;
            
            @FindBy(how = How.XPATH,using = "//table[@id='auditSubgrid']//td[contains(text(),'Document Type Description changed from Default to ScanDoc.')]")
            public WebElement reindexAuditDetailsColumn;

            
            @FindAll({
            	@FindBy(how=How.XPATH,using = "//button[@data-original-title='Select entity']"),
            	@FindBy(how=How.XPATH,using = "//button[@data-bs-original-title='Select entity']")
            })
            public WebElement associatedEntity;
			
            @FindBy(how = How.XPATH,using = "//ul[contains(@class,'nav-tabs')]/li/a[text()='Document Audit']")
            public WebElement documentAuditTab;
            
            @FindAll({
            	@FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Show/Hide thumbnails']"),
            	@FindBy(how = How.XPATH,using = "//button[@data-original-title='Show/Hide thumbnails']")
            })
            public WebElement thumbnailBtn;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='User']")
            public WebElement allocateUserDD;
            
            @FindBy(how = How.XPATH,using = "//button[@id='allocateOk']")
            public WebElement allocateOkPopUpBtn;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='EmailTemplates']")
            public WebElement emailTempDD;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='FromEmailAccount']")
            public WebElement emailAccountDD;
            
            @FindBy(how = How.XPATH,using = "//input[@id='ToEmailAddress']")
            public WebElement toEmailTextBox;
            
            @FindBy(how = How.XPATH,using = "//input[@id='ToDisplayName']")
            public WebElement toDisplayName;
            
            @FindBy(how = How.XPATH,using = "//body[@data-id='GenerateEmailBody']")
            public WebElement emailBodyParagraph;
            
            @FindBy(how = How.XPATH,using = "//iframe[@id='GenerateEmailBody_ifr']")
            public WebElement emailBodyFrame;
            
            @FindBy(how = How.XPATH,using = "//button[@id='generateEmailOk']")
            public WebElement generateEmailOkButton;
            
            @FindBy(how = How.XPATH,using = "//div[@class='modal-body']//img")
            public WebElement busyEmailDialogueWhileSendingEmail;
            
            @FindBy(how = How.XPATH,using = "//a[text()='Document Audit']/ancestor::li[1]")
            public WebElement documentAuditBtn;
            
            @FindBy(how = How.XPATH,using = "//table[@id='auditSubgrid']/tbody//td[text()='ReIndexDocument']/ancestor::tr[1]//td[7]")
            public WebElement documentLandingPageAuditDetails;
            
            @FindBy(how = How.XPATH,using = "//table[@id='auditActionId']/tbody//td[text()='ReIndexDocument']/ancestor::tr[1]//td[7]")
            public WebElement intrayAuditDetails;
            
            @FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Audit Actions']")
            public WebElement intrayAuditActionBtn;
            
            @FindBy(how = How.XPATH,using = "//a[text()='Intray Audit']/ancestor::li[1]")
			public WebElement intrayAuditBtn;																											
            
            @FindBy(how = How.XPATH,using = "//a[text()='Intray Actions']")
			public WebElement intrayActionsBtnOnAuditAction;																			   
            @FindBy(how = How.XPATH,using = "//input[@id='timedPendingPeriod']")
		    public WebElement timedPeriosInput;				

			@FindBy(how = How.XPATH,using = "//p[@id='dialogContent']//td[text()='Intray count:']/ancestor::tr/td[2]")
            public WebElement statisticPopupIntrayCount;								//created by sagar for statistics popup Intray Count
			
			            @FindBy(how = How.XPATH,using = "//p[@id='dialogContent']//td[text()='Account Ref count:']/ancestor::tr/td[2]")
            public WebElement statisticPopupAccountRefCount;								//created by sagar for statistics popup Intray Count
 
            @FindBy(how = How.XPATH,using = "//p[@id='dialogContent']//td[text()='Document count:']/ancestor::tr/td[2]")
            public WebElement statisticPopupDocumentCount;								//created by sagar for statistics popup Document Count
            
    		@FindBy(how=How.XPATH,using="//section[@id='MainSection']//h2[text()='Something went wrong, please retry or contact your system administrator.']")
    		public WebElement viewDocumentH2txt;								//created by sagar to get the h2 heading 
    	           
			@FindAll({
				@FindBy(how=How.XPATH,using="//input[@id='input-tags-selectized']"),
				@FindBy(how=How.XPATH,using="//div[@class='modal-content']//div[@class='selectize-input items not-full has-options']"),
				@FindBy(how=How.XPATH,using="//div[@class='selectize-control input-tags demo-default multi plugin-remove_button']"),
				@FindBy(how=How.XPATH,using="//div[@class='modal-content']//div[@class='selectize-input items not-full has-options dropdown-active input-active']")
			})
    		public WebElement addOrRemoveTagTxt;								//created by sagar for manage tags add or remove tag text
    		    	    
//    		manage tags xpaths
			@FindAll({
				@FindBy(how=How.XPATH,using="//a[@title='Add']"),
				@FindBy(how=How.XPATH,using="//img[@title='Add a new  Value']")
			})
			public WebElement addRemoveTagsAddIcon;							//created by sagar on 25.09.2023 for add tag (plus) icon
           
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@id='saveAddTag']"),
				@FindBy(how=How.XPATH,using="//button[@id='tagSave']")
			})    		
    		public WebElement okBtn;										//created by sagar on 25.09.2023 for ok btn on add/remove tags popup
            
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@id='cancelAddTag']")
			})    		
    		public WebElement cancelBtn;										//created by sagar on 25.09.2023 for cancel btn on add/remove tags popup

     		@FindAll({
     			@FindBy(how=How.XPATH,using="//input[@id='TagNameText']"),
     			@FindBy(how=How.XPATH,using="//input[@id='EditTagName']")
     		})
     		public WebElement editTagsOrNameTxt;									//created by sagar on 25.09.2023 for ok btn on add/remove tags popup
      			
			@FindAll({
				@FindBy(how=How.XPATH,using="//select[@id='selectedEditParentTag']"),
				@FindBy(how=How.XPATH,using="//button[@data-id='selectedEditParentTag']"),
     			@FindBy(how=How.XPATH,using="//button[@data-id='selectedAddParentTag']")
			})
			public WebElement editTagsPathOrParentListbox;							//created by sagar on 25.09.2023 for edit tags path list box
            
     		@FindBy(how=How.XPATH,using="//div[@class='modal-content']//span[@class='validationMessage']")
     		public WebElement validationMsg;								//created by sagar on 25.09.2023 for validation message
  			
     		@FindBy(how=How.XPATH,using="//input[@id='fromMailStatus']")
     		public WebElement fromTxtBox;								//created by sagar on 25.09.2023 for validation message

			@FindAll({
     			@FindBy(how=How.XPATH,using="//textarea[@placeholder='Add Comment']"),
     			@FindBy(how=How.XPATH,using="//div[@id='addComment']")
     		})	
			public WebElement addCommentTxtBox;							//created by sagar on 02.11.2023 for edit tags path list box
			     		
     		@FindBy(how=How.XPATH,using="//table[@id='notesubgrid']/tbody/tr[1]/td[3]")
     		public WebElement notesTitle;								//created by sagar on 25.09.2023 for validation message

     		@FindBy(how=How.XPATH,using="//table[@id='notesubgrid']/tbody/tr[1]/td[4]")
     		public WebElement notesDescription;								//created by sagar on 25.09.2023 for validation message

			


																				
            String tmpUserRemoveIcon = "//div[contains(text(),'%s')]//a";
            String tmpIntrayAuditDetailsColumnVal = "//table[@id='intrayAuditActionId']/tbody//td[text()='%1$s']/ancestor::tr[1]//td[%2$s]";
            String tmpLandingPageIntrayAudit = "//table[@id='intrayAuditSubgrid']/tbody//td[text()='%1$s']/ancestor::tr[1]//td[%2$s]";
			String tmpclickOnAuditActionsTab = "//li[@class='nav-item']//a[text()='%s']";
            
	public IntrayToolsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		log.info("Intray page object is created.");
	}
	
	/**
	 * Click on first row in the list
	 */
	public void clickOnFirstItemInList() {
		click(firstItemDocList,"Clicking on first item in list");
	}
	
	/**
	 * 	Click on ReReference button
	 */
	public void clickOnReReferenceButton() {
		navigationMenu.clickOnMenuButtonAtTop(reReferenceBtn,reReferenceListBtn);
	}

	/**
	 * Select document type from dropdown
	 * @param docType
	 */
	public void selectDocumentType(String docType) {
		new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(docType, documentTypeddl);
	}
	
	/**
	 * Select checkbox in folder1 keep ref
	 */
	public void selectChkboxForFolder1KeepRef() {
		new ActionHelper(driver).moveToElementAndClick(folder1RefChk);
	}
	
	/**
	 * Enter folder2 reference ReReference page
	 * @param folder2ref
	 */
	public void enterFold2Ref(String folder2ref) {
		sendKeys(folder2RefTxt,folder2ref,"Enter folder 2 ref ="+folder2ref);
		new ActionHelper(driver).pressTab();
		waitHelper.waitForElement(folder2RefDetailsLbl,20);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReferencePopupContent() {
		waitHelper.waitForElement(rereferencePopupTitleLblXpath,20);
		return new WindowHelper(driver).getPopupMessage();
	}
	
	public void clickYesOnConfirmReferencePopUp() {
		click(confirmYesBtn,"Clicked on confirm yes button");
		if(!new VerificationHelper(driver).isElementDisplayedByEle(successullyRereferencedMsgXpath)){
			waitHelper.waitForElement(busyDialogIcon,10);
		}
	}
	
	/**
	 * Click on ReReference successful popup
	 */
	public void clickOkOnRereferenceSusccessfullPopup(){
		waitHelper.waitForElementInvisible(driver,busyDialogIcon,20);
		click(waitHelper.waitForElementClickable(driver,successfullyRereferenceOkBtnXpath, 10),"Clicked on successfully reference path");
		waitHelper.waitForElementInvisible(driver,successullyRereferencedMsgXpath,10);
		if(new VerificationHelper(driver).isElementDisplayedByEle(successullyRereferencedMsgXpath)) {
			try {
				click(waitHelper.waitForElementClickable(driver,successfullyRereferenceOkBtnXpath,10),"Clicked on successfully ref ok button");
			}
			catch(Exception e) {
				log.info("Execption caught while clicking on Rereference successfull popup");
			}
	    }
	}
	
	/**
	 * Click on create and Edit Rendition button
	 */
	public void clickOnCreateAndEditRendition() {
		navigationMenu.clickOnMenuButtonAtTop(createAndEditRenditionBtnXpath, createAndEditRenditionBtnLstXpath);
	}
	
	/**
	 * Check whether omniview is shown or not
	 * @return true,false
	 */
	public boolean isOmnviewShown() {
		boolean omniviewVisiblity =false;
		try {
		waitHelper.waitForElement(omniviewMainContentXpath,20);
		omniviewVisiblity =true;
		}
		catch (Exception e) {
			log.info("Omniview is not visible "+e);
		}
		return omniviewVisiblity;
	}
	
	/**
	 * Click on show annotation tool button
	 * @return
	 */
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

	/**
	 * Click on more search button under search tab
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
	 * Enter save search name 
	 * @param input search name for save
	 */
	public void enterSaveSearchName(String input) {
		waitHelper.waitForElementClickable(driver, txtSearchName, 5);
		sendKeys(txtSearchName, input, "Entering save search name as "+input);
	}
	
	/**
	 * Click on save as home page
	 */
	public void clickOnSaveAsHomePage() {
		click(ChkSaveSearchHomePage,"clicking on save as homepage");
	}
	
	/**
	 * Click ok on save popup
	 */
	public void clickOkOnSavePopup() {
		click(btnSaveSearchOk,"clicking on save search ok popup");
		new WindowHelper(driver).waitForPopup("Save Search");
		new WindowHelper(driver).clickOkOnPopup();
	}
	
	/**
	 * Click ok on delete popup
	 * @return final delete popup message
	 * @throws InterruptedException
	 */
	public String clickOkOnDeletePopup() throws InterruptedException {
		WindowHelper windowHelper=new WindowHelper(driver);
		windowHelper.clickOkOnPopup();
		windowHelper.waitForPopup("Delete Document");
			String popupMsg =  windowHelper.getPopupMessage();
			log.info("Popup message is "+popupMsg);
			if(popupMsg.contains("Mail Item(s) with status(s):")) {
				log.info("In if condition of popup msg");
				windowHelper.clickOkOnPopup();
				windowHelper.waitForPopup("Delete Document");
				String finPopupMsg =  windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				return finPopupMsg;
			}
			else {
				String finPopupMsg =  windowHelper.getPopupMessage();
				windowHelper.clickOkOnPopup();
				return finPopupMsg;
			}
	}
	
	/**
	 * Get visible column names of table
	 * @param tableName tablename of data
	 * @return List of String containing all visible column name
	 */
	public List<String> getVisibleColumnNames(String tableName) {
		List<String> columnNames= new ArrayList<>();
		List<WebElement> columnEles;
		String columnNamesXpath = String.format(tempcolumnNamesXpath, tableName);
		columnEles = driver.findElements(By.xpath(columnNamesXpath));
		for (WebElement ele:columnEles) {
			columnNames.add(ele.getAttribute("innerText"));
		}
		return columnNames;
	}
	
	/**
	 * Enter in pills of esalte inputbox
	 * @param pillValue pill values of escalate
	 */
	public void enterInPillsEscalate(String pillValue) {
		waitHelper.waitForElementClickable(driver,txtEscalateInputBox, 20);
		sleepFor(1000);
		sendKeysWithoutClear(txtEscalateInputBox, pillValue, "Entering value in escalte box");
	}
	
	/**
	 * Enter and select pills in escalte dropdown
	 * @param pillValue pillvalue which needs to be selected
	 */
	public void enterAndSelectPillsInEscalate(String pillValue) {
		enterInPillsEscalate(pillValue);
		waitHelper.waitForElementVisible(ddEscalateSelectValue, 20,1);
		click(ddEscalateSelectValue, "clicking on active pill value");		
	}
	
	/**
	 * Get info only in pillsbox text
	 * @return complete info only text 
	 */
	public String getInfoOnlyPillboxText() {
		String completeString="";
		if(txtInfoOnlyPillBoxValues.size()==1) {
			return txtInfoOnlyPillBoxValues.get(0).getAttribute("data-value");
		}
		else {
			Iterator<WebElement> itr= txtInfoOnlyPillBoxValues.iterator();
			
			while(itr.hasNext()){
			 completeString+= itr.next().getAttribute("data-value");
			}
		}
		return completeString;
	}
	
	/**
	 * Remove info only in pillbox text
	 */
	public void removeInfoOnlyPillboxText() {
		click(lnkInfoOnlyPillBoxRemoveIcon, "Clicking on escalate remove icon");
		click(lblModalTitleDistribute,"Click on modal title distribute");
	}
	
	/**
	 * Enter in pills of info only
	 * @param pillValues to be entered in info only
	 */
	public void enterInPillsInfoOnly(String pillValue) {
		waitHelper.waitForElementVisible(btnDistributeApply, 20,1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();}
		sendKeys(txtInfoOnlyInputBox, pillValue, "Entering value in InfoOnly box : "+pillValue);
	}
	
	/**
	 * Eneter and select pills in info only
	 * @param pillValue
	 */
	public void enterAndSelectPillsInInfoOnly(String pillValue) {
		enterInPillsInfoOnly(pillValue);
		waitHelper.waitForElementVisible(ddInfoOnlySelectValue, 20,1);
		click(ddInfoOnlySelectValue, "clicking on active pill value");
		//new ActionHelper(driver).pressEscape();
	}
	
	/**
	 * Get escalte pills box text
	 * @return escalte pills box text value
	 */
	public String getEscaltePillboxText() {
		
		String completeString="";
		if(txtEscalatePillBoxValues.size()==1) {
			return txtEscalatePillBoxValues.get(0).getAttribute("data-value");
		}
		else {
			Iterator<WebElement> itr= txtEscalatePillBoxValues.iterator();
			
			while(itr.hasNext()){
			 completeString+= itr.next().getAttribute("data-value");
			}
		}
		return completeString;		
	}
	
	/**
	 * Remove escalate pillbox text
	 */
	public void removeEscalatePillboxText() {
		click(lnkEscalatePillBoxRemoveIcon, "Clicking on escalate remove icon");
		click(lblModalTitleDistribute,"Clicking on modal title");
	}
	
	/**
	 * Click on modal cancel button without changes
	 */
	public void clickOnModalCanelButtonWithoutChanges() {
		click(btnEscalateCancel, "Clicking on cancel escalte button");	
		navigationMenu.waitForFilterTextBox();
		}
	
	/**
	 * Click on escalate cancel button
	 * @return popup message of distribute intray item
	 */
	public String clickOnModalCancelButton() {
		click(btnEscalateCancel, "Clicking on cancel escalte button");	
		new WindowHelper(driver).waitForPopup("Distribute Intray item");
		String popupMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		return popupMsg;
		}
	
	/**
	 * Click on modal apply changes button with popup
	 */
	public void clickOnModalApplyChangesButtonWithPopup() {
		click(btnDistributeApply, "Clicking on apply changes button");	
		new WindowHelper(driver).waitForPopup("Distribute intray item");
		new WindowHelper(driver).clickOkOnPopup();
		new WaitHelper(driver).waitForElementInvisible(driver,navigationMenu.busyDialogue, 10);
		//new ActionHelper(driver).pressEscape();
		sleepFor(1000);
	}
	
	/**
	 * Click on modal apply changes button
	 */
	public void clickOnModalApplyChangesButton() {
		click(btnDistributeApply, "Clicking on apply changes button");	
	}
	
	/**
	 * Get column value of document intray table
	 * @param columnName column name of table
	 * @return column value of document
	 */
	public String getColumnValueOfDocument(String columnName) {
		String value = "";
		String index;
		WebElement columneELe = driver.findElement(By.xpath(String.format(tmpIntrayTableColumn, columnName)));  
		try {
		index = columneELe.getAttribute("data-column-index");
		}
		catch (NoSuchElementException e) {
			navigationMenu.selectColumnVisiblityFromColumnConfig("columneELe");
			index = columneELe.getAttribute("data-column-index");
		}
		int actualIndex = Integer.parseInt(index)+1;
		value = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		log.info("Getting folder 2 ref document and its value = "+value);
		return value;
		}

	/**
	 * Select from file system distribute
	 * @param ddValue
	 */
	public void selectFromFileSystemDistribute(String ddValue,String getEnvVariable) {
		//Changed for sp1s
		if(getEnvVariable.equals("Enterprise_R522")) {
			click(ddFileSystemDistribute, "Clicking on drop down");
			new DropdownHelper(driver).selectFromDropdown(ddFileSystemDistribute, ddValue);
		}else if(getEnvVariable.equals("Enterprise_R530") || getEnvVariable.equals("Enterprise_R531") || getEnvVariable.equals("Enterprise_R540") || getEnvVariable.equals("Enterprise_R541")){
			new DropdownHelper(driver).selectFromAutocompleDD(ddValue, ddFileSystemDistribute);
		}else {
			click(ddFileSystemDistribute, "Clicking on FS drop down");
			sleepFor(3000);
			System.out.println("ddFSDD===================="+ddFileSystemDistribute.toString());
			//new DropdownHelper(driver).selectFromAutocompleDD(ddValue, ddFileSystemDistribute,getEnvVariable);
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(ddValue, ddFileSystemDistribute, getEnvVariable);
		}
	}
	
	/**
	 * Select distribution type distribute value
	 * @param ddValue value which needs to be selected
	 */
	public void selectFromDistributionTypeDistribute(String ddValue,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddBtnDistributionTypeDistribute, "Clicking on distribute type drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(ddValue,ddBtnDistributionTypeDistribute,getEnvVariable);
		}
		//changed for sp1s
		else if(ddBtnDistributionTypeDistribute.getTagName().equals("button")) {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(ddValue,ddBtnDistributionTypeDistribute);
		}else {
			//click(ddBtnDistributionTypeDistribute, "Clicking on drop down");
			new DropdownHelper(driver).selectFromDropdown(ddBtnDistributionTypeDistribute, ddValue);
		}
		
	}
	
	/**
	 * Select from distribution type auto complete distribute
	 * @param ddValue value which needs to be selected
	 */
	public void selectFromDistributionTypeAutoCompleteDistribute(String ddValue) {
	new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(ddValue, ddBtnDistributionTypeDistribute);	
	}
	
	/**
	 * Select from export content dropdown 
	 * @param exportValue export
	 */
	public void selectFromExportContentDropdown(String exportValue,String getEnvVariable) {
		
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddBtnSelectExportType, "Clicking on export type drop down");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(exportValue, ddBtnSelectExportType,getEnvVariable);
		}
		else if(ddBtnSelectExportType.getTagName().equals("button")) {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(exportValue, ddBtnSelectExportType);
		}else {
			new DropdownHelper(driver).selectFromDropdown(ddBtnSelectExportType, exportValue);
		}
	
	}
	
	/**
	 * Wait till metdata file type is visible
	 */
	public void waitTillMetadataFileTypeVisible() {
		waitHelper.waitForElementVisible(ddSelectedExportFileType, 20, 1);
	}
	
	/**
	 * Get export file name
	 * @return export file name
	 */
	public String getExportFileName() {
		return txtExportFileName.getAttribute("value");
	}
	
	/**
	 * Unacheck all checkbox of export file system popup
	 */
	public void uncheckAllCheckedCheckbox() {
		String []chkBox = {"DocumentData","IntrayData","AuditData","FlexibleData","NotesData"}; 
		for(String ids:chkBox) {
		WebElement ele=driver.findElement(By.xpath(String.format(tmpExportCheckbox, ids))); 
		if(ele.isSelected())
			ele.click();
		}
	}
	
	/**
	 * Select file format of export dropdown value
	 * @param value ex
	 */
	public void selectFileFormat(String value,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			sleepFor(1000);
			click(ddFormatExport, "Clicking on drop down format export button");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(value, ddFormatExport,getEnvVariable);
		}
		else if(ddFormatExport.getTagName().equals("button")) {
			sleepFor(1000);
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(value, ddFormatExport);
		}else {
			sleepFor(1000);
			new DropdownHelper(driver).selectFromDropdown(ddFormatExport, value);
		}
		
		//sleepFor(1000);
		//new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(value, ddFormatExport);
	}

		/**
		 * Get last modified file from downloads folder
		 * @return last modified file name
		 */
		public String getLastModifiedFile() {
		//check this path by running
			String homepath = System.getProperty("user.home");
		String downloadPath = homepath+"\\Downloads\\";	
		  File dir = new File(downloadPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	    File lastModifiedFile = files[0];
	    log.info("Last modified file before iteration "+lastModifiedFile.getName());
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile.getName();		
	}

		/**
		 * Sort target data column in descending 
		 * @throws InterruptedException
		 */
		public void sortTargetDateColumnInDescending() throws InterruptedException {
		//To sort target date column in descending order
			WebElement colEle = navigationMenu.getColumnHeadEle("nGridIntrayListConfiguration", "Target Date");
			//To enable aria sort attr clicking on column name
			new ActionHelper(driver).moveToElementAndClick(colEle);
			Thread.sleep(1000);
			WebElement colEle2 = navigationMenu.getColumnHeadEle("nGridIntrayListConfiguration", "Target Date");
			if(!colEle2.getAttribute("class").contains("desc")){
				new ActionHelper(driver).moveToElementAndClick(colEle2);
				Thread.sleep(1000);
			}
		}
		
		/**
		 * Open outstanding items from home
		 * @throws InterruptedException
		 */
		public void openOutstandingItemsFromHome() throws InterruptedException {
			new HomePage(driver).clickOnMetroTile("Outstanding items.");
			waitHelper.waitForElement(navigationMenu.filterTxt, 20);
			sortTargetDateColumnInDescending();
		}
		
		/**
		 * Click on index pdf as tiff
		 */
		public void clickOnIndexPDFAsTiff() {
			navigationMenu.clickOnIcon("Index PDF as TiFF", "Document");
			navigationMenu.waitForIcon("Index the following changes");
		}
		
		/**
		 * Click on Edit Metadata
		 */
		public void clickOnEditMetadata() {
			navigationMenu.clickOnIcon("Edit metadata", "Document");
			navigationMenu.waitForIcon("Cancel changes");
			navigationMenu.expandAccordionIfCollapsed("Core fields");
		}
		
		/**
		 * Click ok on pdf as tiff popup
		 */
		public void clickOkOnPDFasTiffPopup() {
			WindowHelper windowHelper=new WindowHelper(driver);
			windowHelper.waitForPopup("Pdf to Tiff");
			windowHelper.clickOkOnPopup();
			try {
			windowHelper.waitForPopup("Pdf to Tiff");
			windowHelper.clickOkOnPopup();
			}
			catch (Exception e) {
				// Do nothing
			}
			
			}
		
		/**
		 * Click ok on index successfully popup
		 */
		public void clickOkOnIndexSuccessfully() {
			WindowHelper windowHelper=new WindowHelper(driver);
			windowHelper.waitForPopup("Index");
			windowHelper.clickOkOnPopup();
			try {
			windowHelper.waitForPopup("Index");
			windowHelper.clickOkOnPopup();
			}
			catch (Exception e) {
				log.info("Exception while clicking on index successfully message "+e);
			}
		}
		
		/**
		 * Click on add new document to case under document
		 */
		public void clickOnAddButtonUnderDocument() {
			navigationMenu.clickOnIcon("Add new document to case", "Document");
			navigationMenu.waitForIcon("Index the following changes");	
		}
		
		/**
		 * Click on associated entity and select option
		 * @param option
		 */
		public void clickOnAssociatedAndSelect(String option,String environmentVar) {
			if(environmentVar.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Select entity", "Document");
				navigationMenu.clickOnIconMenu(option);
				navigationMenu.waitForIcon("Cancel changes");
			}else {
				sleepFor(1000);
				//driver.findElement(By.xpath("//button[@data-original-title='Select entity']")).click();
				click(associatedEntity, "Clicking on Associated button");
				navigationMenu.clickOnIconMenu(option);
				navigationMenu.waitForIcon("Cancel changes");
			}
			
		}
		
		/**
		 * Click on landing page icon under document
		 */
		public void clickOnLandingPageIcon() {
		navigationMenu.clickOnIcon("Document landing page", "Document");
		navigationMenu.waitForIcon("Cancel changes");
		}
		
		
		/**
         * Click on landing page icon under intray document
         */
        public void clickOnIntrayLandingPageIcon() {
       navigationMenu.clickOnIcon("Intray landing page", "Document");
        navigationMenu.waitForIcon("Cancel changes");
        }
	
		
		
		/**
		 * Click on document delete 
		 * @return delete document popup message
		 */
		public String clickOnDocumentDelete() {
			navigationMenu.clickOnIcon("Delete document", "Document");
			WindowHelper windowHelper=new WindowHelper(driver);
			windowHelper.waitForPopup("Delete Document");
			windowHelper.clickOkOnPopup();
			windowHelper.waitForPopup("Delete Document");
			windowHelper.clickOkOnPopup();
			windowHelper.waitForPopup("Delete Document");
			String msg = windowHelper.getPopupMessage();
			windowHelper.clickOkOnPopup();
			navigationMenu.waitForIcon("View document");
			return msg;
		}
		
		/**
		 * Click on add note under nav tab default first
		 */
		public void clickOnAddNoteUnderNotesNavTab() {
			new JavaScriptHelper(driver).scrollToBottom();
			navigationMenu.waitForAddIcon();
			navigationMenu.clickOnAddIcon();
		}
		
		/**
		 * Get ttle of notes under Added note
		 * @return
		 */
		public String getTitleOfNotesUnderAddNote() {
			new ActionHelper(driver).moveToElementAndClick(txtTitleNotesUnderAddNote);
			waitHelper.waitForElement(ddSuggestionTitleSelectNote, 20);
			return new VerificationHelper(driver).getText(ddSuggestionTitleSelectNote);
		}
		
		/**
		 * Get paragrapth options values under Add note
		 * @return
		 */
		public List<String> getParaOfNotesUnderAddNote(String getEnvVariable) {
			return new DropdownHelper(driver).getAllOptionsFromDropdown("Paragraph",getEnvVariable);
		}
		
		/**
		 * Get View documents title
		 * @return title text
		 */
		public String getViewDocumentTitle() {
			new WindowHelper(driver).switchToNewlyOpenedTab();
			String title =navigationMenu.getNavbarText();
			new WindowHelper(driver).closeCurrentAndSwithcToFirstTab();
			System.out.println(driver.getWindowHandle());
			return title;
		}
		
		/**
		 * Click on next mail from team tray button
		 */
		public void clickOnNextMailButton() {
			navigationMenu.clickOnIcon("Take next item from team tray", "Mail");
			new WindowHelper(driver).waitForPopup("Next");
		}
		
		/**
		 * Get next mail popup message
		 * @return next mail popup message
		 */
		public String getNextPopupMessage() {
			new WindowHelper(driver).waitForPopup("Next");
			String actMsg =  new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			return actMsg;
		}
		
		/**
		 * Check whether pills menu shown or not
		 * @return true,false
		 * @throws InterruptedException
		 */
		public boolean isPillsMenuShown() throws InterruptedException {
			Thread.sleep(1000);
			return new VerificationHelper(driver).isElementDisplayedByEle(ddInfoOnlySelectValue);
		}
		
		/**
		 * Enter metadata document ref
		 * @param docRefMetadata
		 */
		public void enterMetadataDocRef(String docRefMetadata) {
			new CapturePage(driver).enterDocRef(docRefMetadata);
			new ActionHelper(driver).pressTab();
		}
		
		/**
		 * Get select metadata option popup message under export title
		 * @return actual popup message title of export
		 */
		public String getSelectMetadataOptionPopupMessage() {
			String actMsg =  new WindowHelper(driver).getPopupMessageClickOk("Export");
			new ActionHelper(driver).pressEscape();
			new ActionHelper(driver).pressEscape();
			return actMsg;
		}

		/**
		 * Click on distribute intray item under mail
		 */
		public void clickOnDistributeIcon() {
			sleepFor(1000);
			navigationMenu.clickOnIcon("Distribute intray item", "Mail");
			sleepFor(2000);
			waitHelper.waitForElementVisible(btnDistributeApply, 20,1);
		}
		
	  /**
	   * Click on Export document icon and wait for export dialog
	   */
      public void clickOnExportDocument() {
        navigationMenu.clickOnIcon("Export document", "Document");
        new WindowHelper(driver).waitForModalFormDialog("Export");
      }
      
    /**
     * Search document in intrayList and click on first item of intray  
     * @param label
     * @param labelValue
     */
    public void searchDocumentClickOnFirstIntrayItem(String label, String labelValue) {
            CapturePage capture= new CapturePage(driver);
            capture.selectSearchTab();
            capture.searchWithCriteria(label,labelValue );
            capture.clickOnIntrayListBtn();
            capture.clickOnFirstItemOfIntray();
    }
    
     /*
       * This method is used to click on View Rendition
       */
      public void clickOnViewRendition() {
            try {
            	new JavaScriptHelper(driver).scrollToBottom();
            	sleepFor(2000);
                navigationMenu.clickOnNavTab("History");
                new JavaScriptHelper(driver).scrollToBottom();
                Thread.sleep(3000);
                click(firstVersionFromList, "Click on first version from list");
                String fnlIconXpath = String.format(tmpMenuIconXpath,"View rendition");
                WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
                click(iconElment, "Click on view rendition");
                navigationMenu.waitForFilterTextBox();
                click(activeRendition, "Click on active rendition");
                navigationMenu.clickOnIconMenu("Export document", "Actions");
                new WindowHelper(driver).waitForModalFormDialog("Export Rendition");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      
      public String getErrorMessageInDistribute() {
        WindowHelper windowHelper=new WindowHelper(driver);
        windowHelper.waitForPopup("Distribute");
        String copyMsg = windowHelper.getPopupMessage();
        windowHelper.clickOkOnPopup();
        return copyMsg;
      }
    
      
      /*
       * This method is used to verify visibility of Version Control Icon
       */
      public void verifyVisibilityOfVersionControlIcon() {
         
          WebElement versionHistoryBtn=driver.findElement(By.xpath(String.format(versionControlIconXpath, "VersionHistory")));
          boolean versionStatus=versionHistoryBtn.isEnabled();
          log.info("versionStatus "+versionStatus);
          AssertionHelper.verifyTrue(versionStatus, "Version History button is visible");
          
          WebElement updateDocumentBtn=driver.findElement(By.xpath(String.format(versionControlIconXpath, "UpdateDocument")));
          boolean updateStatus=updateDocumentBtn.isEnabled();
          log.info("updateStatus "+updateStatus);
          AssertionHelper.verifyTrue(updateStatus, "Update button is visible");
          
          WebElement undoDocumentBtn=driver.findElement(By.xpath(String.format(versionControlIconXpath, "UndoDocument")));
          boolean undoStatus=undoDocumentBtn.isEnabled();
          log.info("undoStatus "+undoStatus);
          AssertionHelper.verifyTrue(undoStatus, "Undo button is visible");
          
          WebElement recaptureBtn=driver.findElement(By.xpath(String.format(versionControlIconXpath, "Recapture")));
          boolean recaptureStatus=recaptureBtn.isEnabled();
          log.info("recaptureBtn "+recaptureBtn);
          AssertionHelper.verifyTrue(recaptureStatus, "Recapture button is visible");
          
          WebElement editBtn=driver.findElement(By.xpath(String.format(versionControlIconXpath, "Edit")));
          boolean editStatus=editBtn.isEnabled();
          log.info("editStatus "+editStatus);
          AssertionHelper.verifyTrue(editStatus, "Edit button is visible");  
          
      }
    

    /*
  	 *Enter notes in distribute 
  	 */
  	
  	public void enterNotesPillsInEscalate(String notesValue) {
  		waitHelper.waitForElementClickable(driver,txtNotesInputBox, 20);
  		sendKeys(txtNotesInputBox, notesValue , "Entering value in notes box");
  	}
	
  	
  	/*
	 * To refresh page if search filter text is not visible
	 */
	public void refreshInIntrayForSearchTxt() {
		boolean statusFilterTxt=new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if(!statusFilterTxt) {
			refreshPage();
			waitHelper.waitForElement(navigationMenu.filterTxt, 20);
		}else {
			waitHelper.waitForElement(navigationMenu.filterTxt, 20);
		}
	}
	
	/*
	 * It is used to  remove users from distribution pop up
	 */
	public void RemoveUserFromDistribute(String userName) {
		String removeUserIconXpath = String.format(tmpUserRemoveIcon, userName);
		WebElement removeIcon = driver.findElement(By.xpath(removeUserIconXpath));
		click(removeIcon, "Clicking on remove icon of User");
	}
	
		
	public void clickOnAuditActionsTab(String tabName) {
		String tmpclickOnAuditActionsTabXpath = String.format(tmpclickOnAuditActionsTab, tabName);
		WebElement AuditActionsTab = driver.findElement(By.xpath(tmpclickOnAuditActionsTabXpath));
		click(AuditActionsTab, "Clicking on '"+tabName+"' tab");
		
	}
	
	public void SendEmailUsingOutbound(String emailTemp,String fromEmailAcc,String getEnvVariale) {
		new WindowHelper(driver).waitForModalDialog("Generate Email");
		new DropdownHelper(driver).selectFromAutocompleDD(emailTemp, emailTempDD, getEnvVariale);
		sleepFor(1000);
		new DropdownHelper(driver).selectFromAutocompleDD(fromEmailAcc, emailAccountDD, getEnvVariale);
	}	
	
	
	public String GetAuditIntrayDetailsColumnValue(String colName,String colNum) {
		sleepFor(2000);
		String auditDetails = String.format(tmpIntrayAuditDetailsColumnVal, colName,colNum);
		WebElement ele = driver.findElement(By.xpath(auditDetails));
		String getIntrayAudit = ele.getText();
		return getIntrayAudit;
	}
	
	public String GetLandingPageIntrayAuditDetails(String colName,String colNum) {
		sleepFor(2000);
		String auditDetails = String.format(tmpLandingPageIntrayAudit, colName,colNum);
		WebElement ele = driver.findElement(By.xpath(auditDetails));
		String getIntrayAudit = ele.getText();
		return getIntrayAudit;

	}
	
////	 public void SendEmailUsingGmail(String fromEmail,String toEmail,String pass,String filePath,String fileName,String subject) throws EmailException {
////		  EmailAttachment attachment = new EmailAttachment();
////		  attachment.setPath("C:\\Users\\Amit.Khambad\\Projectdata\\Testdata\\Tiffiles\\file1.tif");
////		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
////		  //attachment.setDescription("Picture of John");
////		  //attachment.setName("John");
////
////		  // Create the email message
////		  MultiPartEmail email1 = new MultiPartEmail();
////		  email1.setHostName("smtp.gmail.com");
////		  email1.setSmtpPort(587);
////		  email1.setAuthenticator(new DefaultAuthenticator(fromEmail,pass));			//elojizsjpzligoaf
////		  email1.setSSLOnConnect(true);
////		  email1.setFrom(fromEmail, "Me");
////		  email1.addTo(toEmail, "John Doe");
////		  
////		  email1.setSubject(subject);
////		  email1.setMsg("File Forwarded");
////
////		  // add the attachment
////		  email1.attach(attachment);
////
////		  // send the email
////		  email1.send();
////
////	 }
////	 
////		public void SendEmailUsingJava(String fromEmail,String toEmail,String password,String folder1Ref) {
////			Properties prop = new Properties();
////			prop.put("mail.smtp.auth", "true");
////			prop.put("mail.smtp.starttls.enable", "true");
////			prop.put("mail.smtp.host", "smtp.gmail.com");
////			prop.put("mail.smtp.port", "587");
////			
////	        //create Authenticator object to pass in Session.getInstance argument
////			Authenticator auth = new Authenticator() {
////			//override the getPasswordAuthentication method
////			protected PasswordAuthentication getPasswordAuthentication() {
////			return new PasswordAuthentication(fromEmail, password);
////				}
////			};
////			
////			Session session = Session.getInstance(prop,auth);
////			MimeMessage msg = new MimeMessage(session);
////			try {
////				msg.setFrom(fromEmail);
////				msg.addRecipients(Message.RecipientType.TO, toEmail);
////				msg.setSubject(folder1Ref);
////				
////				Multipart emailContent = new MimeMultipart();
////				
////				MimeBodyPart textBodyPart = new MimeBodyPart();
////				textBodyPart.setText("My multi part");
////
////				MimeBodyPart pdfAttachment = new MimeBodyPart();
////				pdfAttachment.attachFile("C:\\Users\\Amit.Khambad\\Projectdata\\Testdata\\Tiffiles\\file1.tif");
////				emailContent.addBodyPart(textBodyPart);
////				emailContent.addBodyPart(pdfAttachment);
////				msg.setContent(emailContent);
////				Transport.send(msg);
////				
////				System.out.println("Email sent successfully");
////			} catch (Exception e) {
////				e.printStackTrace();
////			}		
////		}
//		
//		
//		//This is responsible to send the message with attachment [Working Method]
//		public void sendEmailWithAttach(String message, String subject, String to, String from,String pass,String filepath,String filename) {
//			filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
//			filename= filename == null ?"file1.tif": filename; 
//			String finalPath = filepath+filename;
//
//			// Variable for gmail
//			String host = "smtp.gmail.com";
//
//			// get the system properties
//			Properties properties = System.getProperties();
//			System.out.println("PROPERTIES " + properties);
//
//			// setting important information to properties object
//
//			// host set
//			properties.put("mail.smtp.host", host);
//			properties.put("mail.smtp.port", "465");
//			properties.put("mail.smtp.ssl.enable", "true");
//			properties.put("mail.smtp.auth", "true");
//
//			// Step 1: to get the session object..
//			Session session = Session.getInstance(properties, new Authenticator() {
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(from, pass);
//				}
//
//			});
//
//			session.setDebug(true);
//
//			// Step 2 : compose the message [text,multi media]
//			MimeMessage m = new MimeMessage(session);
//
//			try {
//
//				// from email
//				m.setFrom(from);
//
//				// adding recipient to message
//				m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//				// adding subject to message
//				m.setSubject(subject);
//				m.setText("Adding new");
//
//				// attachement..
//
//				// file path
//				//String path = "D:\\EDRM_Regression_Project\\EDRMPoc_Rem\\EDRMPoc_Rem\\src\\main\\EDRM\\resources\\testdocs\\file1.tif";
//
//				MimeMultipart mimeMultipart = new MimeMultipart();
//				// text
//				// file
//
//				MimeBodyPart textMime = new MimeBodyPart();
//
//				MimeBodyPart fileMime = new MimeBodyPart();
//
//				try {
//
//					textMime.setText(message);
//
//					File file = new File(finalPath);
//					fileMime.attachFile(file);
//
//					mimeMultipart.addBodyPart(textMime);
//					mimeMultipart.addBodyPart(fileMime);
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//				}
//
//				m.setContent(mimeMultipart);
//
//				// Step 3 : send the message using Transport class
//				Transport.send(m);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("Sent success...................");
//		}

				public String statisticPopupContents() {
			String statisticPopupIntrayCount = "";
			String statisticPopupAccountRefCount = "";
			

			return statisticPopupIntrayCount;
		}
		

		public String isImageLoaded(String columnName) {
			String status = "",index="";
			try {
				sleepFor(2000);
				WebElement columnEle = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, columnName)));
				boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
				log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
			if (!columnEleVisibility){   //Previously is element present
				navigationMenu.selectColumnVisiblityFromColumnConfig(columnName);
			}
			index = columnEle.getAttribute("data-column-index");
			}
			catch(Exception e) {
				if(!new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.gearIconBtn)) {
					navigationMenu.clickOnIconMenu("Re-run the same search to refresh the lists", "General");
					sleepFor(1000);
				}
				navigationMenu.selectColumnVisiblityFromColumnConfig(columnName);
				WebElement columnElePost = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, columnName)));
				sleepFor(2000);
				//dragAndDropColumnToFirstPosition(columnElePost);
				index = columnElePost.getAttribute("data-column-index");

			}
			int actualIndex = Integer.parseInt(index)+1;


			WebElement StatusWebEle = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]"));
			status = StatusWebEle.getText();
			return status;
		
			
			
//			WebElement ActUrgentValue = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, columnName)));
			
			
//			Boolean imageLoaded = (Boolean)((org.openqa.selenium.JavascriptExecutor)driver).executeAsyncScript(
//			"return arguments[0].complete && type of arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", 
//			ActUrgentValue);
//			AssertionHelper.verifyTrue(imageLoaded, "The "+columnName+" status is ticked");
//			return imageLoaded;
			
			
//			 WebElement ImageFile = driver.findElement(By.xpath(String.format(navigationMenu.tmpTableColumnEle, columnName)));
//			    Boolean ImagePresent = (Boolean)((JavascriptExecutor)driver).executeScript(
//			    		((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
//			    if (!ImagePresent)
//			    {
//			         System.out.println("Image not displayed.");
//			    }
//			    else
//			    {
//			        System.out.println("Image displayed.");
//			    }
		}
		
		public void expandCoreField() {
            CapturePage capture= new CapturePage(driver);
 				try {
		            waitHelper.waitForElementVisible(capture.protectiveMarkerDD, 20, 1);
				}
				catch (Exception e) {
		            click(capture.btnCoreFieldsBatch, "Clicking on Core Field tab");
				}
		}
		
		public void editMetaData(String markerID, String mailOption, String priority, String docRef, String docRef2, String getEnvVariable) {
//			markerID = markerID == null ? "To User" :userType;//if userType null then user
//			markerID = markerID == markerID ? ObjectReader.reader.getUserName() :userName;
			log.info("Editing the MetaData");
			CapturePage capture= new CapturePage(driver);

			System.out.println("getEnvVariable====================" + getEnvVariable);
			getApplicationUrl();
			capture.searchWithCriteria("Doc Ref", docRef);						//docRef3		 "DR4_SAU_AT6744"
			capture.clickOnIntrayListBtn();
			capture.clickOnFirstItemOfIntray();
			navigationMenu.clickOnIcon("Edit metadata", "Document");
		    navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
//			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);
			
			expandCoreField();
    		if(markerID!=null) {
    			log.info("Entering the marker ID as "+markerID);
                capture.selectProtectiveMarkerDD(markerID, getEnvVariable);
    		}
    		if(priority!=null) {
    			log.info("Entering the priority as "+priority);
    			sendKeys(capture.priorityTxt,priority,"Enter the priority as "+priority);
    		}
     		if(mailOption!=null) {
    			log.info("Selecting the mail option as "+priority);
    			capture.selectMailOptionDD(mailOption, getEnvVariable);
    		}            

    		if(docRef!=null) {
    			log.info("Entering the doc ref 2 as "+docRef2);
    			capture.enterDocRef2(docRef2);
    		}			
			navigationMenu.clickOnIcon("Save","Actions");
			sleepFor(2000);	
		}

}
