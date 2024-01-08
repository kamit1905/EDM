package main.EDRM.hybridFramework.pageObject;

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
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;

public class FolderFlagReference extends TestBase{
			
			private WebDriver driver;
			private Logger log = LoggerHelper.getLogger(FolderFlagReference.class);
			WaitHelper waitHelper;
			NavigationMenu navigationMenu;
			
			private String folderRefCaptureTextXpath ="//label[text()='%s']/../div/input";																	 
			@FindBy(how=How.XPATH,using="(//tr[@class='odd']/td[3])[1]")
			public WebElement firstItemDocList;
			
			@FindBy(how=How.XPATH,using="//input[@id='Folder1_Ref']")
            public WebElement folder1Reftxt;
            
            @FindBy(how=How.XPATH,using="//input[@id='Folder2_Ref']")
            public WebElement folder2Reftxt;
            
            @FindBy(how=How.XPATH,using="//input[@id='Folder3_Ref']")
            public WebElement folder3Reftxt;
            
            /**      Dummy reference form    **/
            @FindBy(how=How.XPATH ,using="//input[@id='Prefix']")
            public WebElement txtPrefixDummyReference;
            
            @FindBy(how=How.XPATH ,using="//input[@id='SpnCodelength']")
            public WebElement txtCodeLengthDummyReference;
            
            
            @FindBy(how=How.XPATH ,using="//button[@data-id='EntityDropDown']")
            public WebElement btnEntityDropdownDummyReference;
            
            @FindBy(how = How.XPATH,using = "//button[@data-id='EntityDropDown']/ancestor::section[contains(@class,'page container')]")
            public WebElement btnEntityDropdownDummyReference551OnWards;
            
            @FindBy(how=How.XPATH,using="//button[@class='btn dropdown-toggle btn-primary']")
            public WebElement btnDummyRef;
            
            @FindAll({
            	@FindBy(how=How.XPATH,using="//button[@class='btn dropdown-toggle btn-primary']/following-sibling::ul/li/a"),
            	@FindBy(how=How.XPATH,using="//button[@class='btn dropdown-toggle btn-primary show']/following-sibling::ul/li/a")
            })
            public WebElement ddBtnDummytRef;
            
            @FindBy(how = How.XPATH,using = "//div[@id='DummyReference']/button//img[@id='dummyrefs']")
            public WebElement dummyRefBtn;
            
            @FindBy(how=How.XPATH,using="//input[contains(@data-bind,'DummyRef')]")
            public WebElement txtDummyRef;
            
            /*********Folder flag*******/
            @FindBy(how=How.XPATH,using="//input[@id='FlagId']")
            public WebElement txtFlagId;
            
            @FindBy(how=How.XPATH,using="//textarea[@id='Description']")
            public WebElement txaFlagDescriptionId;
            
            @FindBy(how=How.XPATH,using="//label[@for='Character']/../input")
            public WebElement rdoCharcterFlag;
            
            @FindBy(how=How.XPATH,using="//input[@name='Character']")
            public WebElement txtCharacterInput;
            
            @FindBy(how=How.XPATH,using="//label[@for='FolderFlagImageId']/../input")
            public WebElement rdoFolderFlagImage;
            
            @FindBy(how=How.XPATH,using="//select[@name='FolderFlagImageId']")
            public WebElement ddSelectFlagImage;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='FlagId']")
            public WebElement ddBtnFlagIdUnderAccount;
            
            @FindBy(how = How.XPATH,using = "//input[@id='Surname']")
            public WebElement surnameFieldInput;
            
            @FindBy(how = How.XPATH,using = "//input[@id='Forename']")
            public WebElement forenameFieldInput;
            
            @FindBy(how = How.XPATH,using = "//input[@id='chkAutoIncrementr']")
            public WebElement autoIncrementCheckbox;
            
            @FindBy(how = How.XPATH,using = "//h1[contains(text(),'Server Error in')]")
            public WebElement errorTitle;
            
            @FindBy(how = How.XPATH,using = "//button[@data-button='Select']")
            public WebElement selectButtonOnFolderRef;

            
            private String tmpTextFolderRef="//input[@id='%s_Ref']",
            tmpTextFolderRefEntityPage = "//input[@title='%s']";
            
            private String tmpDummyRefEleAfterR541 = "//button[@class='btn dropdown-toggle btn-primary show']/following-sibling::ul/li/a[text()='%s']";
            private String tmpDummyRefEleBefore541 ="//button[@class='btn dropdown-toggle btn-primary']/following-sibling::ul/li/a[text()='%s']";
            
			
			
	public FolderFlagReference(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		TestBase.logExtentReport("Document tools Page Object Created");
	}
	
	/**
	 * Adding new entity like Acc,Misc,Prop under capture
	 * @param entityName 
	 */
	public void navigateToAddNewEntityPage(String entityName) {
	  navigationMenu.clickOnTab("Capture");
	  navigationMenu.clickOnIcon("Select entity to add record","Entity");
	  navigationMenu.clickOnIconMenu("Add a new "+entityName);
	  navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
	}
	
	/**
	 * Click on first item of document search list
	 */
	public void clickOnFirstItemInList() {
		click(firstItemDocList,"Clicking on first item in list");
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
     * Enter Account on add account ref page from capture form  
     * @param fold1RefValue The value with which you want to create reference
     */
    public void enterFolder1Ref(String fold1RefValue) {
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
     * Enter folder3 ref vlue in add folder ref page
     * @param fold3RefValue
     */
    public void enterFolder3Ref(String fold3RefValue) {
        enterFolderRef("Folder3",fold3RefValue);
    }

    /**
     * Click on save icon of add entity page e.g add acc,add misc etc
     */
    public void clickOnSaveIconWaitForItsInvisiblity() {
	  //Waiting for apis to get loaded else it will show error
      //sleepFor(2000);														  		 
      navigationMenu.clickOnSaveIcon();
      sleepFor(6000);
      waitHelper.waitForElement(errorTitle, 20);
      //waitHelper.waitForElementInvisible(driver, navigationMenu.saveIconBtn, 20);
     // navigationMenu.waitForAddIcon();
    }
    
    /**
     * Click on save icon of add entity page e.g add acc,add misc etc
     */
    public void clickOnSaveIconWaitForAddIcon() {
      navigationMenu.clickOnSaveIcon();
      navigationMenu.waitForAddIcon();
    }
    
    /**
     * On search page enter entity for search and navigate to edit that particular entity
     * @param editRefName e.g Account Ref, MiscRef
     * @param editRefValue Value of entity which needs to be edit
     */

    public String searchEntityAndGetValue(String entityName,String entityValue) {
        navigationMenu.expandAccordionIfCollapsed("General");
        CapturePage capture = new CapturePage(driver);
        capture.enterFolderRefEnityPage(entityName, entityValue);
        navigationMenu.clickOnIconUnderAction("Run");
        sleepFor(3000);
        //navigationMenu.waitForFilterTextBoxSearchResult();
        try {
        String columnName=navigationMenu.getColumnValueFromTable(entityName+" Ref");
        return columnName;
        }
        catch (Exception e) {
         return null;
        }
        
    }
    
     /**
     * Navigate to Dummy reference under admin
     */
    public void navigateToAddDummyReference() {
        navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("Dummy Reference", "Folder");
        navigationMenu.waitForIconWithDataButton("Add", "Actions");
    }
     
    /* Delete all dummy reference which exist in FS
     */
    
    public void selectAllAndDelete()
    {
      try {
      navigationMenu.clickOnSelectAllUnderTableMenu();
      sleepFor(500);
      navigationMenu.clickOnIconUnderAction("Delete");
      }
      catch (Exception e) {
        log.info("Exception in select all delete is "+e);
      }
    }
	
      /**
     * Add dummy reference with given parameters
     * @param prefixName
     * @param length
     * @param folderName
     */
    public void addDummyReference(String prefixName, String length, String folderName,boolean flag,String getEnvVariable) {
      navigationMenu.clickOnIconUnderAction("Add");
      navigationMenu.waitForIconWithDataButton("Save", "Actions");
      navigationMenu.sendKeys(txtPrefixDummyReference, prefixName, "Entered prefix as "+prefixName);//Replace with act content
      navigationMenu.sendKeys(txtCodeLengthDummyReference, length, "Entere length");
      new JavaScriptHelper(driver).scrollToBottom();
      if(getEnvVariable.equals("Enterprise_R551")) {
    	  click(btnEntityDropdownDummyReference, "Clikcing on Entiyt drop down");
    	  new DropdownHelper(driver).selectFromAutocompleDD(folderName,btnEntityDropdownDummyReference551OnWards,getEnvVariable);  
      }else {
    	  new DropdownHelper(driver).selectFromAutocompleDD(folderName,btnEntityDropdownDummyReference);  
      }
      
      if(flag) {
    	  click(autoIncrementCheckbox, "Clicking on autoIncrementCheckbox");
      }
      navigationMenu.clickOnIconUnderAction("Save");
       navigationMenu.waitForIconWithDataButton("Add", "Actions");
    }
    
    /**
     * Test Verify dummy reference added
     * @param folder1Prefix
     * @param folder1Name
     * @return
     */
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
    
        /**
     * Enter and select dummy ref
     * @param entityName
     * @return dummy reference name
     */
    public String enterAndSelectDummyRef(String entityName) {
        navigationMenu.clickOnCaptureTab();
        navigateToAddNewEntityPage(entityName);
        waitHelper.waitForElement(btnDummyRef, 20).click();
        sleepFor(2000);
        //click(ddBtnDummytRef, "Clicking on dummy reference element");
        new JavaScriptHelper(driver).clickElement(ddBtnDummytRef);
        //waitHelper.waitForElementClickable(driver, ddBtnDummytRef, 20).click();
        sleepFor(1000);
        String dummyRef = txtDummyRef.getAttribute("value");
        log.info("Dummy ref text ========="+dummyRef+"=======================");
        sleepFor(1000);
        return dummyRef;
    }
    
    public String enterAndSelectDummyRef(String entityName,String getEnvVariable,String refValue) {
    	if(getEnvVariable.equals("Enterprise_R550")) {
            navigationMenu.clickOnCaptureTab();
            navigateToAddNewEntityPage(entityName);
            waitHelper.waitForElement(dummyRefBtn, 20).click();
            sleepFor(2000);
            String dummyRef =  String.format(tmpDummyRefEleAfterR541, refValue);
            WebElement dummyRefEle = driver.findElement(By.xpath(dummyRef));
            new JavaScriptHelper(driver).clickElement(dummyRefEle);
            sleepFor(1000);
            String getDummyRef = txtDummyRef.getAttribute("value");
            log.info("Dummy ref text ========="+dummyRef+"=======================");
            sleepFor(1000);
            return getDummyRef;
    	}else {
            navigationMenu.clickOnCaptureTab();
            navigateToAddNewEntityPage(entityName);
            waitHelper.waitForElement(btnDummyRef, 20).click();
            sleepFor(2000);
            //click(ddBtnDummytRef, "Clicking on dummy reference element");
            new JavaScriptHelper(driver).clickElement(ddBtnDummytRef);
            //waitHelper.waitForElementClickable(driver, ddBtnDummytRef, 20).click();
            sleepFor(1000);
            String dummyRef = txtDummyRef.getAttribute("value");
            log.info("Dummy ref text ========="+dummyRef+"=======================");
            sleepFor(1000);
            return dummyRef;
    	}
    }
    
    
        public String searchDocumentGetColumnValue(String docRef, String columnName) {
          new CapturePage(driver).searchWithCriteria("Doc Ref", docRef);
          new CapturePage(driver).clickOnDocumentListBtn();
          navigationMenu.waitForFilterTextBoxSearchResult();
          String actColumnVal = navigationMenu.getColumnValueFromTable(columnName);
           return actColumnVal;
        }
        
        /**
         * Navigate to protective marker page
         */
        public void navigteToProtectiveMarkerPage() {
          navigationMenu.clickOnTab("Administration");
          navigationMenu.clickOnIcon("Protective Markers","Document");
          navigationMenu.waitForAddIcon();
          
        }

        public void navigteToFolderFlagPage() {
           navigationMenu.clickOnTab("Administration");
          navigationMenu.clickOnIcon("Folder Flags maintenance","Folder");
          navigationMenu.waitForAddIcon();
          
        }

        public void addFolderFlagWithCharacters(String flagId, String flagDescription, String flagCharacter) {
          navigationMenu.clickOnIconUnderAction("Add");
          navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
          sendKeys(txtFlagId, flagId, "Entering flag Id as "+flagId);
          sendKeys(txaFlagDescriptionId, flagDescription, "Entering flag description as "+flagDescription);
          click(rdoCharcterFlag, "Clicking on flag Id char radio button");
          sendKeys(txtCharacterInput, flagCharacter, "Entering flag char as "+flagCharacter);
          clickOnSaveIconWaitForAddIcon();
        }
        
        public void addFolderFlagWithImage(String flagId, String flagDescription,String imageName) {
           navigationMenu.clickOnIconUnderAction("Add");
          navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
          sendKeys(txtFlagId, flagId, "Entering flag Id as "+flagId);
          sendKeys(txaFlagDescriptionId, flagDescription, "Entering flag description as "+flagDescription);
          click(rdoFolderFlagImage, "Clicking on flag Id char radio button");
          new DropdownHelper(driver).selectFromDropdown(ddSelectFlagImage, imageName);
          clickOnSaveIconWaitForAddIcon();
        }

        public String associateFlagWithFolder1Ref(String folder1Entity, String folder1Ref, String flagId,String getEnvVariable) {
          navigationMenu.clickOnTab("Search");
          home.clickOnSearchEntityAndSelect(folder1Entity);
          //home.searchAndNavigateToEditEnity("Account", folder1Ref+"%");
          home.searchAndNavigateToEditEnity("Account", folder1Ref);
          String folder1RefName = getAccountRefValueOnEditEntityPage();
          sleepFor(1000);
          navigationMenu.clickOnNavTab("Other");
          new JavaScriptHelper(driver).scrollToElement(ddBtnFlagIdUnderAccount);
          sleepFor(1000);
          if(getEnvVariable.equals("Enterprise_R551")) {
        	  click(ddBtnFlagIdUnderAccount, "Clicking on flag drop down");
        	  new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(flagId, ddBtnFlagIdUnderAccount,getEnvVariable);
          }else {
        	  new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(flagId, ddBtnFlagIdUnderAccount);  
          }
          navigationMenu.clickOnSaveIcon();
          navigationMenu.waitForIconWithDataButton("Edit", "Actions");
          log.info("Folder ref for association is "+folder1RefName);
          return folder1RefName;
        }
        
         public String searchEntityAndClickOnDelete(String folder1Entity, String folder1Ref) {
           navigationMenu.clickOnTab("Search");
          home.clickOnSearchEntityAndSelect(folder1Entity);
          home.searchAndNavigateToLandingPage("Account", folder1Ref+"%");
          navigationMenu.clickOnIconUnderAction("Delete");
          String folderRefName = new WindowHelper(driver).getPopupMessageClickOk("Delete");
          return folderRefName;
         }
        
        

        private String getAccountRefValueOnEditEntityPage() {
        WebElement folderRefCapturePageEle=findDynamicElement(folderRefCaptureTextXpath, "Account Ref"); 
        String accountRefActValue = folderRefCapturePageEle.getAttribute("value").trim();
        return accountRefActValue;
        }

        public void captureDocWithFlaggedFolder1Ref(String folder1Ref, String folderFlagDoc,String getEnvVariable) throws InterruptedException {
          new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null, 
              folderFlagDoc, folder1Ref, null, null,getEnvVariable);
        
        }

         public boolean searchDocumentCheckImagePresent(String folderFlagDocRef) {
         new CapturePage(driver).searchWithCriteria("Doc Ref", folderFlagDocRef);
          new CapturePage(driver).clickOnDocumentListBtn();
          navigationMenu.waitForFilterTextBoxSearchResult();
          boolean isImagePresent = navigationMenu.isImagePresentForColumn("Flag ID");
           return isImagePresent;
        }

        public void navigateToProtectiveMarker() {
          navigationMenu.clickOnTab("Administration");
          navigationMenu.clickOnIcon("Protective Markers","Document");
          navigationMenu.waitForAddIcon();
        }

        public void searchMarkerAndDelete(String markerName) {
          navigationMenu.searchInFilter(markerName);
          navigationMenu.clickOnFilteredRow("protectiveMarkerTable");
          navigationMenu.clickOnIconUnderAction("Delete");
          new WindowHelper(driver).clickOkOnPopup("Delete");
        }
        
        public void SearchDummyFolderRefAndDelete(String folderPrefix) {
        	navigationMenu.searchInFilter(folderPrefix);
            navigationMenu.clickOnFilteredRow("dummyReferenceDataTable");
            navigationMenu.clickOnIconUnderAction("Delete");
            new WindowHelper(driver).clickOkOnPopup("Delete");
        }
        
        public void EditValuesOfFolderReference(String surname,String forename) {
        	sleepFor(1000);
        	navigationMenu.clickOnNavTab("Person");
        	sendKeys(surnameFieldInput, surname, "Entering Surname "+surname);
        	sleepFor(1000);
        	sendKeys(forenameFieldInput, forename, "Entering forename "+forename);
        	navigationMenu.clickOnSaveIcon();
            navigationMenu.waitForIconWithDataButton("Edit", "Actions");
        }
        
        //Used to get Other values of entity like Surname,Forename
        public String searchEntityAndGetOtherValue(String entityName,String entityValue) {
            navigationMenu.expandAccordionIfCollapsed("General");
            CapturePage capture = new CapturePage(driver);
            capture.enterFolderRefEnityPage(entityName, entityValue);
            navigationMenu.clickOnIconUnderAction("Run");
            sleepFor(3000);
            //navigationMenu.waitForFilterTextBoxSearchResult();
            try {
            String columnName=navigationMenu.getColumnValueFromTable("Surname");
            return columnName;
            }
            catch (Exception e) {
             return null;
            }
            
        }
        
        public String ClickOnDeleteRefAndGetPopMessage() {
        	navigationMenu.clickOnIconUnderAction("Delete");
        	String delMessage = new WindowHelper(driver).getPopupMessageClickOk("Delete");
            return delMessage;
        }
        
        /*
         * Search and select the folder reference While Rereference document
         */
        public void SearchAndSelectFolderReference(String entityName,String folderRefValue) {
        	navigationMenu.expandAccordionIfCollapsed("General");
    		new CapturePage(driver).enterFolderRefEnityPage(entityName, folderRefValue);
    		navigationMenu.clickOnIconUnderAction("Run");
    		//sleepFor(3000);
    		new WaitHelper(driver).waitForElementInvisible(driver, new NavigationMenu(driver).busyDialogue, 20);
    		navigationMenu.clickOnFilteredRow("crudGridResults");
    		navigationMenu.clickOnIcon("Select this", "Actions");
    		new WaitHelper(driver).waitForElement(new DocumentToolsPage(driver).ddFileSystemReReference, 10);
        }
        
        //pass the Street value & folder 3 ref entity name
    	public void addFolder3Reference(String fold3entityName, String StreetValue) {   					
    		getApplicationUrl();
    		new FolderFlagReference(driver).navigateToAddNewEntityPage(fold3entityName);
    		new FolderFlagReference(driver).enterFolder3Ref(StreetValue);
    	
    		sleepFor(2000);
            new AdminUserSection(driver).clickOnUserNavTab("Address");
            EnterFolderRefEnityByTitle("Street", StreetValue);
            sleepFor(2000);	
    		
            new FolderFlagReference(driver).clickOnSaveIconWaitForItsInvisiblity();
    		
//    		getApplicationUrl();
//    		home.clickOnSearchEntityAndSelect(fold3entityName);
//    		String entity3Value = searchEntityAndGetValue("Property", StreetValue);
//    		AssertionHelper.verifyText(entity3Value, StreetValue);
    	}
    	
    	//created by sagar on 02.08.2023		--> added to fill the text on any field in the folder1/2/3 reference;	
    	public void EnterFolderRefEnityByTitle(String entityName,String foldRefValue) {	
    		WebElement folderRefEle = driver.findElement(By.xpath(String.format(tmpTextFolderRefEntityPage, entityName)));
    		sendKeys(waitHelper.waitForElement(folderRefEle,10),foldRefValue,"Enetering folder ref value = "+foldRefValue);
    	}

        

}
