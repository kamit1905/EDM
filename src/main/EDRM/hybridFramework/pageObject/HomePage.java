package main.EDRM.hybridFramework.pageObject;

import java.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;

public class HomePage extends TestBase{
			
	
			private WebDriver driver;
			WaitHelper waitHelper;
			private NavigationMenu navigationMenu;
			private Logger log = LoggerHelper.getLogger(HomePage.class);
			
			@FindBy(how=How.XPATH,using="//div[contains(@onclick,'FromDate')]")
	 		public WebElement docReceivedTodayDiv;
			
			@FindBy(how=How.XPATH,using="//b[text()='Document Search']")
			public WebElement documentSearchTitle ;		     
			
			@FindBy(how=How.XPATH,using="//div[contains(@onclick,'CurrentDate=false')]")
			public WebElement outstandingInTraydiv;
			
			@FindBy(how=How.XPATH,using="//b[text()='In-Tray']")
			public WebElement inTrayTitle;
			
			@FindBy(how=How.XPATH,using="//a[@id='cogIcon']")
			public WebElement gearIconLnk ;
			
			@FindBy(how=How.XPATH,using="//a[@id='changefilesystem']")
			public WebElement changeFileSystemLnk ;
			
			@FindBy(how=How.XPATH,using="//section[@class='metroDoubleIconTile  '][last()]/div/div[@class='metroExtra']")
			public WebElement lblLastTiles ;
			
			//@FindBy(how=How.XPATH,using="//div[@class='dropdown-menu user']/h2")
			@FindBy(how=How.XPATH,using="//div[contains(@class,'dropdown-menu')]/h2")
			public WebElement lblFileSystemName ;
			
			@FindBy(how=How.XPATH,using="//a[text()='Change User']")
			public WebElement lnkChangeUser ;
			
			@FindBy(how=How.XPATH,using="//a[@id='browseFileSystem']")
			public WebElement lnkBrowseFileSystem ;
			
			@FindBy(how=How.XPATH,using="//a[text()='Sign Out']")
			public WebElement lnkSignOut ;
			
			@FindBy(how=How.XPATH,using="//a[text()='Click here']")
			public WebElement lnkThankYouPostLogoff ;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div/button[contains(@data-original-title,'Select entity to search')]"),
				@FindBy(how=How.XPATH,using="//div/button[contains(@data-bs-original-title,'Select entity to search')]")
			})
			public WebElement btnSearchEntityHomePage ;
			
			@FindBy(how = How.XPATH ,using="//button[@data-id='JobRoleId']")
			public WebElement jobRoleBtn;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='ColourScheme']")
			public WebElement colourSchemeDD;
			
			@FindBy(how = How.XPATH,using = "//a[@id='changeColourScheme']")
			public WebElement changeColourSchemeLink;
			
			@FindBy(how = How.XPATH,using = "//button[@data-icon='Edit']")
			public WebElement editTileBtn;
			
			@FindBy(how = How.XPATH,using = "//input[@id='Folder1_Ref']")
		    public WebElement folder1RefParamInput;
			@FindBy(how = How.XPATH,using = "//div[@id='MailStatusesInputs']")
			public WebElement MailStatusesInputs;
			
//			@FindBy(how=How.XPATH,using="//div[@id='MailStatusesInputs']/..//div[@class='selectize-control MailStatusTextBox demo-default multi plugin-remove_button']")
//			public WebElement MailStatusAllDefaultInputs;
			
			@FindAll(@FindBy(how = How.XPATH, using = "//div[@id='MailStatusesInputs']/..//div[@class='selectize-control MailStatusTextBox demo-default multi plugin-remove_button']//div[@class='item']"))
			public List<WebElement> MailStatusAllDefaultInputs;
			
			@FindBy(how = How.XPATH,using = "//div[@id='MailStatusesInputs']//div[contains(@class,'MailStatusTextBox')]//div[@class='item']")
			public WebElement getDefaultMailStatusValue;
			
			@FindBy(how = How.XPATH,using = "//div[text()='Number of checked-out items.']/ancestor::div[1]//div[@id='metroBadge2']")
			public WebElement getCheckedOutCount;
			
			@FindBy(how = How.XPATH,using = "//div[@id='checkedOutDocumentsTable_info']")
			public WebElement checkedOutItems;
			
			@FindBy(how = How.XPATH,using = "//div[text()='Outstanding items for today.']/ancestor::div[1]//div[@id='metroBadge3']")
			public WebElement getOutstandingItemsForToday;
			
			String metroTile = "//div[@class='metroExtra' and text()='%s']/..",
					tmpHomeIcon = "//div/button[contains(@data-original-title,'%s')]";
			
			String tmpNECDMHomeIcon = "//div/button[contains(@data-bs-original-title,'%s')]";
			
			String metroTileDeleteBtn = "//div[@class='metroExtra' and text()='%s']/ancestor::div[1]//a[@title='Delete Tile']" ;
			String tmpMetroTileParameterIcon = "//div[@class='metroExtra' and text()='%s']/ancestor::div[1]//i" ;
			String tmpHideMetroTileBtn = "//div[@class='metroExtra' and text()='%s']/ancestor::div[1]//a[@title='Hide tile']";
			String tmpShowMetroTileBtn = "//div[@class='metroExtra' and text()='%s']/ancestor::div[1]//a[@title='Show tile']",
		    metroTileEditBtn = "//div[@class='metroExtra' and text()='%s']/ancestor::div[1]//a[@title='Edit Tile']";		
			
			
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu= new NavigationMenu(driver);
		log.info("Home page object created");
	}
	
	/**
	 *Click on tile of document received today 
	 */
	public void clickOnDocReceivedToday(){
		docReceivedTodayDiv.click();
		waitHelper.waitForElement(documentSearchTitle,500);
	}
	
	/**
	 * Click on tile of outstanding intray
	 */
	public void clickOnOutstandingIntray(){
		outstandingInTraydiv.click();
		waitHelper.waitForElement(inTrayTitle,20);
	}
	
	/**
	 * Click on gear icon of home page
	 */
	public void clickOnGearIcon() {
		new ActionHelper(driver).moveToElementAndClick(gearIconLnk); 
		try {
		waitHelper.waitForElement(changeFileSystemLnk, 10);
		}
		catch (Exception e) {
			new ActionHelper(driver).moveToElementAndClick(gearIconLnk);
		}
	}
	
	/**
	 * Click on change file system 
	 */
	public void clickOnChangeFileSystem() {
		waitHelper.waitForElementClickable(driver,changeFileSystemLnk,20).click();
	}
	
	/**
	 * Click on specific file system
	 * @param FSName file system name on which needs to be clicked
	 */
	public void clickOnSpecificFileSystem(String FSName){
		String fslocator = "//p//a[text()='"+FSName+"']";
		waitHelper.waitForElementClickable(driver,driver.findElement(By.xpath(fslocator)),20).click();
	}
	
	/**
	 * Click on change user from gear options
	 */
	public void clickOnChangeUser() {
		clickOnGearIcon();
		click(lnkChangeUser, "Clicking on change user link");
		waitHelper.waitForElementClickable(driver, new LoginPage(driver).usernameTxt, 20);
	}
	
	/**
	 * Change file system with complete steps start to end
	 * @param FSName file system name with which we want to change
	 */
	public void changeFileSystem(String FSName) {
		clickOnGearIcon();
		clickOnChangeFileSystem();
		clickOnSpecificFileSystem(FSName);
		sleepFor(2000);
		waitHelper.waitForElementClickable(driver,gearIconLnk,20);
	}
	
	/**
	 * Refresh file system with specified file system name 
	 * @param FSName
	 */
	public void refreshFileSystem(String FSName) {
		changeFileSystem(FSName);
	}
	
	/**
	 * Refresh current logged in file system
	 */
	public void refreshCurrentFileSystem() {
		String currentFS = getCurrentFileSystemName().split("-")[0].trim();
		clickOnGearIcon();
		clickOnChangeFileSystem();
		clickOnSpecificFileSystem(currentFS);
		waitHelper.waitForElementClickable(driver,gearIconLnk,20);
	}
	
	/**
	 * Get text last create tiles on home page
	 * @return
	 */
	public String getLastTilesText() {
		waitHelper.waitForElement(lblLastTiles, 10);
		return lblLastTiles.getText();
	}
	
	/**
	 * Get current logged in file system name
	 * @return
	 */
	public String getCurrentFileSystemName() {
		clickOnGearIcon();
		waitHelper.waitForElement(lblFileSystemName, 10);
		String fsName = lblFileSystemName.getAttribute("innerText");
		return fsName;
	}
	
	/**
	 * Clicking on metro tile with specified name
	 * @param tileTitle metrotileTiel 
	 */
	public void clickOnMetroTile(String tileTitle) {
		String tileLocator = String.format(metroTile, tileTitle);
		click(driver.findElement(By.xpath(tileLocator)),"Clicking on metro tile "+tileLocator);
	}
	
	/**
	 * Click on home page icons with specified tooltip
	 * @param iconTooltip tooltip of icon which needs to be clicked
	 */
	public void clickOnHomeIcon(String iconTooltip,String environment) {
		//if(!environment.equals("Enterprise_R540")) {
		if(environment.equals("Enterprise_Sp1s") ||environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531") ) {
			String fnlIconXpath = String.format(tmpHomeIcon , iconTooltip);
			WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
			waitHelper.waitForElementClickable(driver, iconElment, 5);
			click(iconElment,"Clicking on icon "+iconTooltip);
		}else {
			String fnlIconXpath = String.format(tmpNECDMHomeIcon , iconTooltip);
			WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
			waitHelper.waitForElementClickable(driver, iconElment, 5);
			click(iconElment,"Clicking on icon "+iconTooltip);
		}
	}
	
	/**
	 * Click on myIntrayIcon of home page
	 */
	public void clickOnMyIntrayIcon(String environment) {
		clickOnHomeIcon("View my intray",environment);
	}
	
	/**
	 * It returns true or false on basis of browse file system option is shown or not
	 * @return true or false 
	 */
	public boolean isBrowserFileSystemOptionShown() {
		clickOnGearIcon();
		return lnkBrowseFileSystem.isDisplayed();
	}
	
	/**
	 * Click on browse file system option based on file system provided
	 * @param fileSystem e.g D, R1, R2
	 */
	public void clickOnBrowserFileSystemOption(String fileSystem) {
		clickOnGearIcon();
		click(lnkBrowseFileSystem, "Clicking on browser file system");
		clickOnSpecificFileSystem(fileSystem);
	}
	
	/**
	 * Click on sign out option
	 */
	public void clickOnSignOut() {
		clickOnGearIcon();
		click(lnkSignOut, "Clicking on sign out");
	}
	
	/** --- search entity page related methods on home page------ */
	
	/**
	 *Click on search entity of home page and select specific entity name 
	 * @param entityName e.g Acc, Misc
	 */
	public void clickOnSearchEntityAndSelect(String entityName) {
		btnSearchEntityHomePage.click();
		String locator = "//ul[@role='menu']/li/a[text()='Search "+entityName+"']";
		new WaitHelper(driver).waitForElementClickable(driver, driver.findElement(By.xpath(locator)), 10);			//Added because getting element clickable exception
		//click(driver.findElement(By.xpath(locator)),"Clicking on entity "+entityName);
		new JavaScriptHelper(driver).clickElement(driver.findElement(By.xpath(locator)));
	}
	
	/**
	 * On search page enter entity for search and navigate to edit that particular entity
	 * @param editRefName e.g Account Ref, MiscRef
	 * @param editRefValue Value of entity which needs to be edit
	 */
	public void searchAndNavigateToEditEnity(String editRefName,String editRefValue) {
		navigationMenu.expandAccordionIfCollapsed("General");
		CapturePage capture = new CapturePage(driver);
		capture.enterFolderRefEnityPage(editRefName, editRefValue);
		navigationMenu.clickOnIconUnderAction("Run");
		new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 20);
		//navigationMenu.waitForFilterTextBox();
		navigationMenu.clickOnFilteredRowLink("crudGridResults");
		navigationMenu.clickOnEditIcon();
		navigationMenu.waitForIcon("Save changes", "Actions");
	}
	
	/**
	 * On search page search entity click on hyperlink and navigate to landing of entity
	 * @param editRefName Account Ref, Misc Ref
	 * @param editRefValue Entity which needs be searched
	 */
	public void searchAndNavigateToLandingPage(String editRefName,String editRefValue) {
		navigationMenu.expandAccordionIfCollapsed("General");
		CapturePage capture = new CapturePage(driver);
		capture.enterFolderRefEnityPage(editRefName, editRefValue);
		navigationMenu.clickOnIconUnderAction("Run");
		new WaitHelper(driver).waitForElement(new NavigationMenu(driver).filterTxt, 20);
		navigationMenu.clickOnFilteredRowLink("crudGridResults");
		navigationMenu.waitForIconWithDataButton("Edit", "Actions");
	}
	
	/**
	 * login with non admin user provided in config
	 * @throws InterruptedException
	 */
	public void loginWithNonAdminUser() throws InterruptedException {
		clickOnChangeUser();
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getNonAdminLoginId(), ObjectReader.reader.getNonAdminUserPassword());
	}
	
	/**
	 * Login with default admin user provided in config
	 * @throws InterruptedException
	 */
	public void loginWithDefaultAdminUser() throws InterruptedException {
		clickOnChangeUser();
		new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getLoginId(), ObjectReader.reader.getPassword());
		
	}
	
	/**
	 * Click on Number of document received today
	 */
	public void clickOnOnDocumentReceivedToday() {
		clickOnMetroTile("Number of documents received today.");
		navigationMenu.waitForFilterTextBoxSearchResult();
		
	}
	
	/**
	 * Click on all file system option for searching
	 */
	public void clickOnAllFileSystemButton(String environment) {
		String allFS = navigationMenu.getAttributeIcon("Toggle All File Systems option for searching", "Search", "class",environment);
		if(!allFS.contains("selected")) 
			navigationMenu.clickOnIcon("Toggle All File Systems option for searching", "Search");		
		
	}
	
	public void SelectJobRoleForAccountRef(String text,String getEnvVariable) {
		sleepFor(1000);
		navigationMenu.clickOnTab("Other");
		new JavaScriptHelper(driver).scrollToBottom();
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(jobRoleBtn, "Clicking on job role dropdown");
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(text, jobRoleBtn,getEnvVariable);
		}else {
			new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(text, jobRoleBtn);
		}
		new ActionHelper(driver).pressTab();
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	/*
	 * Clicking on colour scheme drop down
	 */
	public void ClickOnColourCodeDropDown(String colourName) {
		new DropdownHelper(driver).selectFromAutocompleDDWithoutInput(colourName, colourSchemeDD);
	}
	
	/**
	 * Click on change changes scheme 
	 */
	public void clickOnChangeColourScheme() {
		waitHelper.waitForElementClickable(driver,changeColourSchemeLink,20).click();
	}
 
	public void SelectEditTileButton() {
		click(editTileBtn, "Clicking on edit tile button");
	}
	
	public String DeleteMetroTile(String tileName) {
		String tileLocator = String.format(metroTile, tileName);
		new ActionHelper(driver).mouseHoverOnElement(By.xpath(tileLocator));
		sleepFor(1000);
		
		String metroTileDelete = String.format(metroTileDeleteBtn, tileName);
		click(driver.findElement(By.xpath(metroTileDelete)), "Clicking on metro tile delete button");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		sleepFor(1000);
		new WindowHelper(driver).clickOnModalFooterButton("Yes");
		return getMsg;
	}
	
	public boolean VerifyThatMetroTileNotPresent(String tileName) {
		String tileLocator = String.format(metroTile, tileName);
		boolean flag = new VerificationHelper(driver).isElementDisplayed(By.xpath(tileLocator));
		return flag;
	}

	  public void ClickOnMetroTileParameterizedIcon(String parameter,String folder1Ref) {
		  String metroTileParam = String.format(tmpMetroTileParameterIcon, parameter);
		  WebElement metroTileParamLoc = driver.findElement(By.xpath(metroTileParam));
		  click(metroTileParamLoc, "Clicking on metro parameter icon");
		  
		  new WindowHelper(driver).waitForModalDialog("Search Parameters (Optional)");
		  sendKeys(folder1RefParamInput, folder1Ref, "sending parameter as "+folder1Ref);
		  new WindowHelper(driver).clickOnModalFooterButton("Ok");
		  sleepFor(3000);
	  }
	  
	  public void HideMetroTile(String tileName) {
			String tileLocator = String.format(metroTile, tileName);
			new ActionHelper(driver).mouseHoverOnElement(By.xpath(tileLocator));
			sleepFor(2000);
			String metroTileHide = String.format(tmpHideMetroTileBtn, tileName);
			click(driver.findElement(By.xpath(metroTileHide)), "Clicking on metro tile hide button");
			sleepFor(2000);
	  }
	  
	  public void ShowMetroTile(String tileName) {
			String tileLocator = String.format(metroTile, tileName);
			new ActionHelper(driver).mouseHoverOnElement(By.xpath(tileLocator));
			sleepFor(2000);
			String metroTileShow = String.format(tmpShowMetroTileBtn, tileName);
			click(driver.findElement(By.xpath(metroTileShow)), "Clicking on metro tile show button");
			sleepFor(2000);
	  }
	  
//	  created by sagar on 06.07.2023
	  public static String getBGColor (String strTitle, String strKey)
	  {
			HashMap<String, String> hm = new HashMap<String, String>();
			if(strTitle.equals("RGB_Verify"))
			{
				hm.put("Default", "rgba(0, 43, 98, 1)");
				hm.put("Blue", "rgba(5, 114, 206, 1)");
				hm.put("Red", "rgba(218, 27, 27, 1)");
				//hm.put("Slate", "rgba(80, 95, 109, 1)");
				hm.put("Slate", "rgba(0, 43, 98, 1)");
			}
			else if(strTitle.equals("HexaDecimalCode_Verify"))
			{
				hm.put("Default", "#002B62");
				hm.put("Blue", "#0572CE");
				hm.put("Red", "#DA1B1B");
				hm.put("Slate", "#505F6D");
	
			}
			return hm.get(strKey);
	  }

		public void EditMetroTile(String tileName) {
			String tileLocator = String.format(metroTile, tileName);
			new ActionHelper(driver).mouseHoverOnElement(By.xpath(tileLocator));
			sleepFor(1000);
			
			String metroTileEdit = String.format(metroTileEditBtn, tileName);
			click(driver.findElement(By.xpath(metroTileEdit)), "Clicking on metro tile edit button");
			sleepFor(2000);
		}
	  
	
}
