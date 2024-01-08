package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.*;					 
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.calender.CalenderHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;																
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class FileSystemSection extends TestBase{
			  
			private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			public ExcelReader xls;			  
			Logger log = LoggerHelper.getLogger(FileSystemSection.class);
				
			  @FindBy(how=How.XPATH,using="//li[@id='Administration']")
			  public WebElement adminTabXpath;
			  
			  @FindBy(how=How.XPATH,using="//td/button[contains(@onclick,'FlexibleIndexAdministration')]")
			  public WebElement flexibleIndexAdminBtn ;
			  
			  @FindBy(how=How.XPATH,using="//li/button[contains(@onclick,'FlexibleIndexAdministration')]")
			  public WebElement flexibleIndexAdminLst ;
			  
			  @FindBy(how=How.XPATH,using="//div/span[text()='Document']")
			  public WebElement documentNodeLbl ;
			  
			  @FindBy(how=How.XPATH,using="//button[@data-original-title='Add a new entity']")
			  public WebElement addEntityBtn;
			  
			  @FindBy(how=How.XPATH,using="//button[@data-original-title='Add a new field']")
			  public WebElement addFieldBtn;
			  
			  @FindBy(how=How.XPATH,using="//input[@name='AddEntityName']")
			  public WebElement addEnityNameTxt ;
			  
			  @FindBy(how=How.XPATH,using="//button[@id='addEntity']")
			  public WebElement entityAddBtn ;
			  
			  @FindBy(how=How.XPATH,using="//input[@id='ColumnName']")
			  public WebElement fieldColumnNameTxt;
			  
			  @FindBy(how=How.XPATH,using="//button[@data-id='FlexibleFieldType']")
			  public WebElement fileTypeDropdownBtn ;
			  
			  @FindBy(how=How.XPATH,using="//td/button[@data-original-title='Show drop down options']")
			  public WebElement showDropdownOptionBtn;
			  
			  @FindBy(how=How.XPATH,using="//*[@id='popupTitle' and contains(text(),'Drop Down')]")
			  public WebElement dropDownOptionsTitle ;
			  
			  @FindBy(how=How.ID,using="AddOption")
			  public WebElement addOptionsTxt ;
			  
			  @FindBy(how=How.XPATH,using="//th/a[@data-icon='Add']")
			  public WebElement addOptionsBtn ;
			  
			  @FindBy(how=How.XPATH,using="//button[@id='popupButton1' and text()='Save']")
			  public WebElement saveOptionsBtn ;
			  
			  @FindBy(how=How.XPATH,using="//td/button[@data-original-title='Publish the schema']")
			  public WebElement publishSchemaBtn ;
			  
			  @FindBy(how=How.XPATH,using="//button[@id='dialogButton1' and text()='Ok']")
			  public WebElement  publishSchemaOkBtn ;
			  
			  /**           File System section			 **/
			  @FindBy(how=How.XPATH,using="//li[@title='Move to the next page']/a")
			  public WebElement  nextBtn ;
			  
			  @FindBy(how=How.XPATH,using="//li[@title='Move to the previous page']/a")
			  public WebElement  previousBtn ;
			  
			  @FindBy(how=How.XPATH,using="//label[text()='File System']/../input")
			  public WebElement  fileSystemTxt ;
			  
			  @FindBy(how=How.ID,using="Description")
			  public WebElement  DescriptionTxt ;
			  
			  @FindBy(how=How.ID,using="AdminUserId")
			  public WebElement  adminUserIdTxt ;
			  
			  @FindBy(how=How.ID,using="AdminUserName")
			  public WebElement  adminUsernameTxt ;
			  
			  @FindBy(how=How.ID,using="rdadminstrativeUser")
			  public WebElement  addAdminUserRdo ;
			  
			  @FindBy(how=How.XPATH ,using="//li/a[text()='Finish']")
			  public WebElement  finishBtn ;
			  
			  @FindBy(how=How.XPATH ,using="//input[@type='search']")
			  public WebElement  filterTxt ;
			  
			  @FindBy(how=How.XPATH ,using="//tr[@class='odd']/td[1]")
			  public WebElement  fileSystemNameLbl ;
			  
			  //Cache system
			  @FindBy(how=How.XPATH ,using="//button[@data-id='Status']")
			  public WebElement  cacheStatusdd ;
			  
			  @FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			  public WebElement cacheStatusdd551OnWards;
			  
			  @FindBy(how=How.ID ,using="HighWaterMark")
			  public WebElement  highWaterMarkTxt ;
			  
			  @FindBy(how=How.ID ,using="ClearBackMark")
			  public WebElement  clearBackMarkTxt;
			  
			  @FindBy(how=How.ID ,using="Weighting")
			  public WebElement  weightingTxt;
			  
			  @FindBy(how=How.ID ,using="CachePath")
			  public WebElement  cachePathTxt;
			  
			  @FindBy(how=How.XPATH ,using="//li/a[text()='File System']")
			  public WebElement  fileSystemTab ;
			  
			  @FindBy(how=How.XPATH ,using="//button[@data-id='FileSystem']")
			  public WebElement  fileSystemdd ;
			  
			  @FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			  public WebElement fileSystemdd551OnWards;
			  
			  @FindBy(how=How.XPATH ,using="//tbody[contains(@data-bind,'FileSystemList')]/tr[last()]/td[1]/a")
			  public WebElement  addedFileSystemLbl ;
			  
			  @FindBy(how=How.XPATH ,using="//button[@data-id='PrinterId']")
			  public WebElement  ddPrinterFileSystem; 
			  
			  @FindBy(how=How.XPATH ,using="//a[@data-title='Add File System for cache']")
			  public WebElement  addFileSystemIcon ;
			  
			  @FindBy(how=How.XPATH ,using="//button[@data-button='Save']")
			  public WebElement  saveBtn ;
			  
			  @FindBy(how=How.XPATH,using="//button[@id='dialogButton2']")
			  public WebElement dialogCancelBtn;
			  
			  @FindBy(how = How.XPATH , using = "//input[@id='AlmostDueDays']")
			  public WebElement dueDaysInput;
			  			  			  
			  public String tempTabFileSystem = "//li/a[text()='%s']",
					  	tmpTxtInputFlexibleLabel = "//td[text()='%1$s']/following-sibling::td[text()='%2$s']/following-sibling::td/input",
						  folderNameTxt = "Folder%sName",
						  folderMandatoryChk = "Folder%sMandatory",
						  fileSystemTableId="fileSystemDataTable",
						  ddPrinterFileSystemId = "PrinterId";
			  
			  @FindBy(how=How.XPATH,using="//table[@id='cacheServerTable']/tbody/tr[1]/td[1]")
			  public WebElement lblCacheFilteredtRow;
			  
			  public String tempRemoveFileSystem="//a[text()='%s']/ancestor::tr[1]//a[@data-original-title='Delete File System for cache']";
			  
			  public String tempRemoveFileSystemAfter540="//a[text()='%s']/ancestor::tr[1]//a[@data-bs-original-title='Delete File System for cache']";
				
			  public String tempFolderTab="//li/a[text()='%s']";
			  
			  @FindBy(how=How.XPATH,using="//input[@id='BankHolidayDate']")
			  public WebElement bankHolidayInput;     //
			  
			  @FindBy(how=How.XPATH,using="//input[@id='Description']")
			  public WebElement bankHolidayDescription;
			  
			  @FindBy(how=How.XPATH,using="//input[@id='IsSpecificToThisFileSystem']")
			  public WebElement specificToFileSystemCheckbox;
			  
			  @FindBy(how = How.XPATH,using = "//label[text()='Allow Publish']/ancestor::div[1]/input")
			  public WebElement tmpAllowPublishBtn;
			  
			  @FindBy(how = How.XPATH,using = "//button[@data-id='PublicRenditionCreator']")
			  public WebElement ddPublicRenditionCreator;
			  
			  @FindBy(how = How.XPATH,using = "//label[text()='Send To Document Connect']/ancestor::div[1]/input")
			  public WebElement tmpSendToDocumentConnect;
			  
			  @FindBy(how = How.XPATH,using = "//button[@data-id='DocumentConnectCreatorId']")
			  public WebElement ddDefaultPDFCreator;
			  
			  @FindBy(how = How.XPATH,using = "//button[@data-id='CacheId']")
			  public WebElement ddCacheServer;
			  
			  @FindBy(how = How.XPATH,using = "//input[@id='AlmostDueDays']")
			  public WebElement almostDueInput;
			  
			  public String tmpNavFolderTab = "//section[@class='page container gridHeader']//ul/li/a[text()='%s']";
			  
	public FileSystemSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		actionHelper=new ActionHelper(driver);
		xls = new ExcelReader();				  
	}
	
	
	
	public void enterFileSystemName(String FSName) {
	waitHelper.waitForElementClickable(driver, fileSystemTxt, 5);
	sendKeys(fileSystemTxt, FSName, "Entering file system name");
	}
	
	public void enterDescriptionName(String FSDescription) {
	sendKeys(DescriptionTxt, FSDescription, "Entering file system description");
	}
	
	public void enterAdminUserId(String adminUserId) {
	waitHelper.waitForElementClickable(driver, adminUserIdTxt, 5);
	sendKeys(adminUserIdTxt, adminUserId, "Entering admin username");
	}
	
	public void enterAdminUsername(String adminUsername) {
	sendKeys(adminUsernameTxt, adminUsername, "Entering admin username");
	}
	
	public void enterFolderNames(String folder1Name,String folder2Name, String folder3Name) {
		WebElement folder1Txt=driver.findElement(By.id(String.format(folderNameTxt, "1")));
		WebElement folder2Txt=driver.findElement(By.id(String.format(folderNameTxt, "2")));
		WebElement folder3Txt=driver.findElement(By.id(String.format(folderNameTxt, "3")));
		sendKeys(folder1Txt, folder1Name, "Entering folder value as"+folder1Name);
		sendKeys(folder2Txt, folder2Name, "Entering folder value as"+folder2Name);
		sendKeys(folder3Txt, folder3Name, "Entering folder value as"+folder3Name);
	}
	
	public void selectMandatoryFolder(Boolean folder1,Boolean folder2, Boolean folder3) {
		WebElement folder1MandatChk =  driver.findElement(By.id(String.format(folderMandatoryChk, "1")));
		WebElement folder2MandatChk =  driver.findElement(By.id(String.format(folderMandatoryChk, "2")));
		WebElement folder3MandatChk =  driver.findElement(By.id(String.format(folderMandatoryChk, "3")));
		if(folder1) { 
			click(folder1MandatChk,"clicking on folder1 mandatory chkbox") ;
			}
		if(folder2) { 
			click(folder2MandatChk,"clicking on folder2 mandatory chkbox") ;
			}
		if(folder3) { 
			click(folder3MandatChk,"clicking on folder3 mandatory chkbox") ;
			}
	}
	
	public void clickOnNextButton() {
		click(nextBtn,"Clicking on next button");
	}
	
	public void clickOnFinishButton() {
		click(finishBtn,"Clicking on finish button");
	}
	
	public void searchInFilter(String filterText) {
		sendKeys(filterTxt, filterText, "Entering in filter"+filterText);
	}
	
	public String getFileSystemName() {
		try {
		Thread.sleep(2000);
		}
		catch(Exception ex) {log.info("Exception in get file system name");}
		return fileSystemNameLbl.getText();
	}
	
	

	public void selectCacheStatus(String status,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			waitHelper.waitForElementClickable(driver, cacheStatusdd, 5);
			click(cacheStatusdd, "Clicking on cache status drop down");
			dropdownHelper.selectFromAutocompleDDWithoutInput(status, cacheStatusdd551OnWards,getEnvVariable);
		}else {
			waitHelper.waitForElementClickable(driver, cacheStatusdd, 5);
			dropdownHelper.selectFromAutocompleDDWithoutInput(status, cacheStatusdd);
		}
	}


	public void enterHighWaterMark(String highMark) {
	sendKeys(highWaterMarkTxt, highMark, "Entering high water mark as "+highMark);	
	}


	public void enterClearBlackMark(String clearMark) {
		sendKeys(clearBackMarkTxt, clearMark, "Entering clear back mark as "+clearMark);
	}
	
	public void enterWeighting(String weighting) {
		sendKeys(weightingTxt, weighting, "Entering weighting as "+weighting);
	}
	
	public void enterServerPath(String serverPath) {
		sendKeys(cachePathTxt, serverPath, "Entering server path as "+serverPath);
	}

	
	public void clickOnFileSystem() {
	click(fileSystemTab,"Clicking on file system tab")	;
	}
	
	public String AddFileSystem(String fileSystem,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			waitHelper.waitForElementClickable(driver, fileSystemdd, 5);
			click(fileSystemdd, "Clicking on file system drop down");
			dropdownHelper.selectFromAutocompleDDWithoutInput(fileSystem, fileSystemdd551OnWards,getEnvVariable);
			click(addFileSystemIcon, "Clicking on add file system icon");
			return addedFileSystemLbl.getText();
		}else {
			waitHelper.waitForElementClickable(driver, fileSystemdd, 5);
			dropdownHelper.selectFromAutocompleDDWithoutInput(fileSystem, fileSystemdd);
			click(addFileSystemIcon, "Clicking on add file system icon");
			return addedFileSystemLbl.getText();
		}
	}
		public void AddFileSystem(String FSName, String UserName, String FSDescription, String folder1EntityName, String folder2EntityName, String folder3EntityName,  boolean folder1chk, boolean folder2chk, boolean folder3chk, String cacheServer, String getEnvVariable) {
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("File System maintenance","File System");
		navigationMenu.waitForAddIcon();
		navigationMenu.searchInFilter(FSName);
		WebElement actColumnValueXpath = navigationMenu.getfilteredRowElement("fileSystemDataTable");
		String actColumnValue = actColumnValueXpath.getText();
		if(actColumnValue.equalsIgnoreCase("No matching records found")) {
			navigationMenu.getApplicationUrl();		
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("File System maintenance","File System");
			navigationMenu.clickOnAddIcon();
			clickOnNextButton();
			sleepFor(1000);
			clickOnNextButton();
			enterFileSystemName(FSName);
			enterDescriptionName(FSDescription);
			clickOnNextButton();
			enterAdminUserId(UserName);
			enterAdminUsername(UserName);
			clickOnNextButton();
			enterFolderNames(folder1EntityName, folder2EntityName, folder3EntityName);
			selectMandatoryFolder(folder1chk, folder2chk, folder3chk);
			clickOnNextButton();
			SelectCacheServer(cacheServer);
			clickOnNextButton();
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(1000);
			clickOnNextButton();
			sleepFor(1000);
			clickOnFinishButton();
			sleepFor(3000);
	
//			navigationMenu.waitForAddIcon();
		}
		else {
			System.out.println("---------->> The File system is already present.");
		}
	}
	
	public void clickOnSaveButton() {
		click(saveBtn,"Clicking on save cache server button");
		navigationMenu.waitForAddIcon();
	}
	
	public void clickOnDialogCancelButton() {
		click(dialogCancelBtn, "Clicking on dialog Cancel button");
	}
	
	//------------------------Index admin Section --------------------//
	public void clickOnAdminTab() {
	click(adminTabXpath,"Clicking on admin tab");	
	}
	
	
	public void clickOnFlexibleIndexAdminButton() {
	 navigationMenu.clickOnMenuButtonAtTop(flexibleIndexAdminBtn, flexibleIndexAdminLst);	
	}
	
	public boolean isDocumentElementDisplayed() {
		return verificationHelper.isElementDisplayedByEle(documentNodeLbl);
	}
	
	public void clickOnDocumentLabel() {
		click(documentNodeLbl,"Clicking on document label");	
	}
	
	public void clickOnAddEnity() {
		click(addEntityBtn,"Clicking on add entity button");	
	}
	
	public void clickOnAddField() {
		click(addFieldBtn,"Clicking on add field button");	
	}
	
	public void enterEntityName(String entityName) {
		waitHelper.waitForElementClickable(driver,addEnityNameTxt,10);
		sendKeys(addEnityNameTxt,entityName,"Enetering entity name ="+addEnityNameTxt);
	}
	
	public void clickOnAddEntityButton() {
		click(entityAddBtn,"Clicking on entity add button");
		//waiting for dialog button to go away
		waitHelper.waitForElementInvisible(driver,entityAddBtn,10);
		waitHelper.waitForElementInvisible(driver,busyDialogIcon,10);
	}
	
	public boolean isEntityShown(String entityName) {
		String entityLocator = "(//span[contains(text(),'"+entityName+"')])[1]";
		return verificationHelper.isElementDisplayed(By.xpath(entityLocator));
	}
	
	public void clickOnEntity(String entityName) {
		String entityLocator = "(//span[contains(text(),'"+entityName+"')])[1]";
		click(driver.findElement(By.xpath(entityLocator)),"Clicking on Entity");
	}
	
	public void enterColumnName(String fieldName) {
		waitHelper.waitForElementClickable(driver,fieldColumnNameTxt,10).clear();
		fieldColumnNameTxt.sendKeys(fieldName);
	}
	
	public void selectFieldTypeAsDropdown() {
	 dropdownHelper.selectFromAutocompleDDWithoutInput("Dropdown", fileTypeDropdownBtn);	
	}
	
	public void showDropdownOptions() {
		showDropdownOptionBtn.click();
	}
	
	public void addDropdownOptions(String []optionsArray ) {
		for(String option:optionsArray) {
			waitHelper.waitForElementClickable(driver,addOptionsTxt,10).clear();
			addOptionsTxt.sendKeys(option);
			addOptionsBtn.click();
		}
		
		saveOptionsBtn.click();
		waitHelper.waitForElementInvisible(driver,dropDownOptionsTitle,10);
	}
	
	public void clickOnPublishSchema() {
		publishSchemaBtn.click();
		waitHelper.waitForElementClickable(driver,publishSchemaOkBtn,10).click();
		waitHelper.waitForElementInvisible(driver,publishSchemaOkBtn,10);
		
	}



	public void NavigateToFileSystemLabels() {
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Flexible Field Label maintenance","File System");
		//navigationMenu.clickOnIcon("File System Labels maintenance","File System");
		navigationMenu.waitForIcon("Cancel changes");	
	}



	public void enterLabelValue(String labelValue, String folderValue, String labelInput) {
		navigationMenu.searchInFilter(labelValue);
		WebElement labelInputEle = driver.findElement(By.xpath(String.format(tmpTxtInputFlexibleLabel,labelValue,folderValue)));
		sendKeysWithoutClear(labelInputEle, labelInput, "Enterning label input as "+labelInput);
		actionHelper.pressTab();
	}



	public void clickOnSaveFlexibleLabels() {
		navigationMenu.clickOnIcon("Save changes made to flexible field labels", "Actions");
		windowHelper.waitForPopup("Flexible Field Label maintenance");
		windowHelper.clickOkOnPopup();
	}



	public String getLabelValue(String labelValue, String folderValue) {
		navigationMenu.searchInFilter(labelValue);
		WebElement labelInputEle = driver.findElement(By.xpath(String.format(tmpTxtInputFlexibleLabel,labelValue, folderValue)));
		return verificationHelper.getText(labelInputEle);
	}



	public void clickOnCancelChangesLabel() {
		navigationMenu.clickOnCancelIcon();
	}
	
	
	  /**
	   * Click on filtered row
	   */
	  public void clickOnFilteredCacheRow() {
		  click(lblCacheFilteredtRow, "clicking on filtered user row");
	  }
	  
	  
	  /*
	   * Used to remove file system from cache server
	   */
	  public void RemoveFileSystemFromCache(String fileSystem,String environment) {
		  //if(!environment.equals("Enterprise_R540") & !environment.equals("Enterprise_R541")){
		  if(environment.equals("Enterprise_Sp1s") ||environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531") ) {
		  String fnlRemoveFileSystemXpath = String.format(tempRemoveFileSystem,fileSystem);
		  WebElement fileSystemEle=driver.findElement(By.xpath(fnlRemoveFileSystemXpath));
		  click(fileSystemEle, "Clicking on Delete Btn of file system");
		  new WindowHelper(driver).waitForPopup("Delete");
		  String warnMsg =  windowHelper.getPopupMessage();
		  AssertionHelper.verifyTextContains(warnMsg, "Are you sure you want to delete");
		  sleepFor(1000);
		  new WindowHelper(driver).clickOkOnPopup();
		  }else {
			  String fnlRemoveFileSystemXpath = String.format(tempRemoveFileSystemAfter540,fileSystem);
			  WebElement fileSystemEle=driver.findElement(By.xpath(fnlRemoveFileSystemXpath));
			  click(fileSystemEle, "Clicking on Delete Btn of file system");
			  new WindowHelper(driver).waitForPopup("Delete");
			  String warnMsg =  windowHelper.getPopupMessage();
			  AssertionHelper.verifyTextContains(warnMsg, "Are you sure you want to delete");
			  sleepFor(1000);
			  new WindowHelper(driver).clickOkOnPopup();
		  }
		  
	  }
	  
	  
	  /*
	   * It is used to click on specified tab
	   */
	  public void ClickOnNavigationTab(String tabName) {
		  String fnlClickOnTab=String.format(tempFolderTab, tabName);
		  WebElement clickOnTabElement=driver.findElement(By.xpath(fnlClickOnTab));
		  click(clickOnTabElement, "Clicking on tab "+tabName);
	  }


	  public void enterBankHolidayDate(String date) {
		  bankHolidayInput.click();
		  new JavaScriptHelper(driver).clearText(bankHolidayInput);
		  sendKeys(bankHolidayInput, date, "Entering holiday date "+date);
		  sleepFor(1000);
		  bankHolidayInput.sendKeys(Keys.ENTER);
	  }
	  
	  public void enterBankHolidayDateUsingCalender(String monthYear,String month,String day) {
		  bankHolidayInput.click();
		  new CalenderHelper(driver).SelectMonthFromDatePicker(monthYear, month);
		  new CalenderHelper(driver).SelectMonthFromCalender(month);
		  new CalenderHelper(driver).SelectDayFromCalender(day);
	  }
	  
	  public void enterBankHolidayDescription(String description) {
		  sendKeys(bankHolidayDescription, description, "Entering description "+description);
	  }
	  
	  public void unselectSpecificToFileSystemCheckbox(String flag) {
		  if(flag.equals("true")) {
			  specificToFileSystemCheckbox.click();
		  }
	  }
	  
	  public void enterDueDays(String duedays) {
		  sendKeys(dueDaysInput, duedays, "Entering due days as "+duedays);
	  }
	  
	  public void ClickOnNavigationBar(String navTabName) {
		  String fnlClickOnNavBar=String.format(tmpNavFolderTab, navTabName);
		  WebElement clickOnNabBarElement=driver.findElement(By.xpath(fnlClickOnNavBar));
		  click(clickOnNabBarElement, "Clicking on tab "+clickOnNabBarElement);
		  //new JavaScriptHelper(driver).clickElement(clickOnNabBarElement);
	  }
	  
	  public void ClickOnAllowPublish(boolean status) {
		if(status) {
			boolean allowPublishBtn = new VerificationHelper(driver).isElementSelected(tmpAllowPublishBtn);
			if(!allowPublishBtn) {
				click(tmpAllowPublishBtn, "Clicking on "+tmpAllowPublishBtn);
			}
		}else {
			click(tmpAllowPublishBtn, "Diselecting on "+tmpAllowPublishBtn);
		}
	  }
	  
	  
	  public void SelectPublicRenditionCreator(String renditionCreator) {
		  
		  new WaitHelper(driver).waitForElement(ddPublicRenditionCreator, 10);
		  new DropdownHelper(driver).selectFromAutocompleDD(renditionCreator,ddPublicRenditionCreator);
		  
	  }
	  
	  
		public void ClickOnSendToDocumentConnect(boolean status) {
			if (status) {
				boolean sendToDocumentConnect = new VerificationHelper(driver)
						.isElementSelected(tmpSendToDocumentConnect);
				if (!sendToDocumentConnect) {
					click(tmpSendToDocumentConnect, "Clicking on Send To Document Connect Checkbox");
				}
			} else {
					click(tmpSendToDocumentConnect, "Deselecting on send To Document Connect checkbox");
			}
		}
		
		
		public void SelectDefaultPDFCreator(String pdfCreator) {
			new WaitHelper(driver).waitForElement(ddDefaultPDFCreator, 10);
			new DropdownHelper(driver).selectFromAutocompleDD(pdfCreator, ddDefaultPDFCreator);
		}
		
		public void SelectCacheServer(String cacheName) {
			new DropdownHelper(driver).selectFromAutocompleDD(cacheName, ddCacheServer);
		}
		
		public void EnterAlmostDueDays(String due) {
			sendKeys(almostDueInput, due, "Entering due period as "+due);
		}

		
}
