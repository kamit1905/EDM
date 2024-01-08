package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class AdminUserSection extends TestBase{
	
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			LoginPage login;
			HomePage home;
			WindowHelper windowHelper;
			JavaScriptHelper jsHelper;
			Logger log = LoggerHelper.getLogger(AdminUserSection.class);
	
			@FindBy(how=How.XPATH,using="//label[text()='Role']/../input")
			public WebElement roleTxt;
			
			@FindBy(how=How.ID,using="Name")
			public WebElement roleNameTxt;
			
			@FindBy(how=How.XPATH,using="//table[@id='rolesTable']/tbody/tr[last()]/td[1]/a")
			public WebElement lblAddedRoleId;
			
			@FindBy(how=How.XPATH,using="//table[@id='rolesTable']/tbody/tr[last()]/td[2]")
			public WebElement lblAddedRoleName;
			
			@FindBy(how=How.XPATH,using="//table[@id='userDataTable']/tbody/tr[1]/td[1]")
			public WebElement lblFilteredUserRow;
			
			@FindBy(how=How.XPATH,using="//table[@id='teamsTable']/tbody/tr[1]/td[1]")
			public WebElement lblFilteredTeamRow;
			
			@FindBy(how=How.XPATH,using="//table[@id='userDataTable']/tbody/tr[1]/td[1]/a")
			public WebElement lnkFilteredUserRow;
			
			@FindBy(how=How.ID,using="Id")
			public WebElement teamIdTxt;
			
			@FindBy(how=How.ID,using="TeamName")
			public WebElement teamNameTxt;
			
			@FindBy(how=How.XPATH,using="//button[@data-Id='RoleId']")
			public WebElement ddRoleName;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@data-Id='RoleId']//div[@class='filter-option-inner-inner']"),
				@FindBy(how=How.XPATH,using="//button[@data-Id='RoleId']//span")
			})
			public WebElement ddGetRoleName;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='MailStatuses']")
			public WebElement mailStatusesdd;
			
			@FindBy(how=How.XPATH,using="//table[@id='teamsTable']/tbody/tr[last()]/td[1]/a")
			public WebElement addedTeamIdLbl;
			
			@FindBy(how=How.XPATH,using="//table[@id='teamsTable']/tbody/tr[last()]/td[2]")
			public WebElement addedTeamNameLbl;
			
			@FindBy(how=How.XPATH,using="//a[@data-title='Add new mail status']")
			public WebElement btnMailStatusesadd;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='TeamId']")
			public WebElement ddUsersTeam;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@data-id='TeamId']//div[@class='filter-option-inner-inner']"),
				@FindBy(how=How.XPATH,using="//button[@data-id='TeamId']//span")
			})
			public WebElement ddGetUsersTeam;
			
			@FindBy(how=How.ID,using="Id")
			public WebElement txtId;
			
			@FindBy(how=How.ID,using="SecurityLevel")
			public WebElement txtSecurityLevel;
			
			@FindBy(how=How.ID,using="UserName")
			public WebElement txtUsername;
			
			@FindBy(how=How.XPATH,using="//button[@data-button='Restore']")
			public WebElement btnRestoreElement;
			
			@FindBy(how=How.XPATH,using="//li/a[text()='Mail Statuses']")
			public WebElement tabMailStatuses;
			@FindBy(how=How.XPATH,using="//li/a[text()='Team Users']")
			public WebElement tabTeamUsers;

			@FindBy(how=How.CLASS_NAME,using="team-landing-nav")
			public WebElement lnkLandingPageTeamName;
			
			@FindBy(how=How.XPATH,using="//a[text()='Audit Actions']")
			public WebElement tabAuditAction;
			
			@FindBy(how=How.XPATH,using="//a[text()='Checked Out Items']")
			public WebElement tabCheckedOutItems;
			
			@FindBy(how=How.XPATH,using="(//div[@class='metroName' and text()='Checked Out']/following-sibling::div[@class='metroBadge'])[2]")
			public WebElement lblCountCheckedOutTile;
			
			@FindBy(how=How.XPATH,using="//input[@id='Path']")
			public WebElement txtPrinterPath;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@id='auditActionTab']//label[contains(@for,'ActionDataRange')]"),
				@FindBy(how=How.XPATH,using="//div[@id='documentAuditActionTab']//label[contains(@for,'ActionDataRange')]")
			})
			public WebElement lblActionDataRangeAuditTab;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div[@id='auditActionTab']//label[contains(@for,'ActionType')]"),
				@FindBy(how=How.XPATH,using="//div[@id='documentAuditActionTab']//label[contains(@for,'ActionType')]")
			})
			public WebElement lblActionTypeAuditTab;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='dropdownActionType']")
			public WebElement ddActionTypeAuditTab;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='dropdownActionType']/ancestor::div[2]/ancestor::section[1]")
			public WebElement ddActionTypeAuditTab551OnWards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='dropdownActionDataRange']")
			public WebElement ddActionDataRangeAuditTab;
			
			@FindBy(how=How.XPATH,using="//input[@id='Name']")
			public WebElement txtPrinterName;

			
			@FindBy(how=How.ID, using="FetchRecords")
			public WebElement btnFetchRecords;
			
			
			@FindBy(how=How.XPATH,using="//button[@data-id='BacklogCampaign']")
			public WebElement ddUsersCampaign;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='BacklogCampaign']/ancestor::div[2]/ancestor::section[1]")
			public WebElement ddUsersCampaignR551OnWards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='SecurityProfile']")
			public WebElement ddUsersSecurityProfile;
			
//			@FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
//			public WebElement ddUsersSecurityProfile551OnWards;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='SecurityProfile']/ancestor::div[2]/ancestor::section[1]")
			public WebElement ddUsersSecurityProfile551OnWards;
			
			@FindBy(how=How.XPATH,using="//button[@data-id='SecurityRestrictionType']")
			public WebElement ddUserSecurityRestrictionType;
			
			@FindBy(how=How.XPATH,using="//input[@name='FolderRef']")
			public WebElement inputFolderRestriction;
			
			@FindBy(how=How.XPATH,using="//a[@data-title='Add Folder']")
			public WebElement addFolderBtn;
			
			@FindBy(how=How.XPATH,using="//div[@id='FlexibleLookedUpFolderDetails']")
			public WebElement flexibleLookedUpFolderDetails;
			
			@FindBy(how = How.XPATH,using="//button[@data-id='SecurityTemplateJobRole']")
			public WebElement ddSecurityJobRole;
			
			@FindBy(how = How.XPATH,using="//a[@data-title='Add new job role']")
			public WebElement addJobRoleBtn;
			
			@FindBy(how = How.XPATH,using="//button[@data-id='UserJobRoles']")
			public WebElement ddUserJobRole;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='UserJobRoles']/ancestor::div[2]/ancestor::section[1]")
			public WebElement ddUserJobRole551OnWards;
			
			@FindBy(how = How.XPATH,using = "//button[@aria-label='Security Profile maintenance']")
			public WebElement securityProfileBtn;
			
			@FindBy(how = How.XPATH,using = "//div[contains(@class,'bs-container dropdown bootstrap-select')]")
			public WebElement ddCommonDropdownElementWithoutInput;
			
			@FindBy(how = How.XPATH,using = "//table[@class='dataTableGrid audit-actions-table']//td[text()='Intray Audit Actions']/ancestor::tr[1]//img")
			public WebElement expandIntrayAuditActions;
			
			@FindBy(how = How.XPATH,using = "//table[@class='dataTableGrid audit-actions-table']//td[text()='Intray Audit Actions']/ancestor::tr[1]//input[@type='checkbox']")
			public WebElement intrayAuditGroupCheckbox;
			
			public String printerTableID = "printerTable",
					aduitActionGridTableID = "userLandingAuditActionsSubGridDataTable",
					tmpBtnExpandSectionXpath= "//td[@data-bind='text:Name' and text()='%s']/preceding-sibling::td",
					tmpChkSecurityXpath="//tr[@style='']/td[@data-bind='text: Name' and text()='%s']/following-sibling::td/input",
					tmpChkSecurityXpathForProfile="//tr[contains(@data-bind,'visible:')]/td[@data-bind='text: Name' and text()='%s']/following-sibling::td/input",
					tmpGetConflictedRowXpath="//tr[contains(@data-bind,'visible:')]/td[@data-bind='text: Name' and text()='%s']/ancestor::tr[1]",
					tmpNavTabXpath = "//ul[contains(@class,'nav-tabs')]/li/a[text()='%s']",
					tmpUserCascadeXpath = "//td[text()='%s']/../td/input",
					tmpDeleteRestrictedXpath = "//td[contains(text(),'%s')]/ancestor::tr[1]//a[@data-icon='Delete']",
					tmpGroupRestrictedAddBtn = "//td[text()='%s']/ancestor::tr[1]//a",
					
					tmpDeleteJobRoleBtn = "//td[text()='%s']/ancestor::tr[1]//img",
					tmpCascadeUserChechbox = "//td[text()='%s']/ancestor::tr[1]//input"
					;
			
			
				    //tmpChkSecurityToGetColor="//tr[contains(@data-bind,'visible:')]/td[@data-bind='text: Name' and text()='%s']/ancestor::tr[1]";
					//tmpSecurityStatusXpath="//tr[contains(@data-bind,'visible:')]/td[text()='System Administration']/following-sibling::td/input"
			
			String tmpIntrayAuditTable = "//table[@class='dataTableGrid audit-actions-table']//td[text()='%s']/ancestor::tr[1]//input";
			
			@FindBy(how=How.XPATH,using="//input[@id='Id']")
			public WebElement profileId;
			
			@FindBy(how=How.XPATH,using="//input[@id='Description']")
			public WebElement profileDescription;
			
			@FindBy(how=How.XPATH,using="//label[text()='Security Profile']/ancestor::div[1]//span[@class='validationMessage']")
			public WebElement getSecurityProfileValidation;
			
			@FindBy(how=How.XPATH,using="//button[@id='dialogButton1' and text()='Yes']")
			public WebElement securityYesBtn;
			
			@FindBy(how = How.XPATH,using = "//input[@id='EmailAddress']")
			public WebElement addEmailAddress;
					
	  public AdminUserSection(WebDriver driver) {
		this.driver=driver;
		jsHelper = new JavaScriptHelper(driver);
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		login = new LoginPage(driver);
		home=new HomePage(driver);
	}
	 
	  /**
	   * Enter role info role form
	   * @param roleId 
	   * @param roleName
	   * @throws InterruptedException
	   */
	 public void enterRole(String roleId, String roleName) throws InterruptedException {
		 waitHelper.waitForElementClickable(driver, roleTxt, 5);
		 sendKeys(roleTxt,roleId,"Entering roleId as "+roleId);
		 sendKeys(roleNameTxt, roleName, "Entering role name as "+roleName);
		 navigationMenu.clickOnSaveIcon();
		 navigationMenu.waitForAddIcon();
	 }
	 
	 /**
	  * Click on mail statuses of tab 
	  */
	 public void clickOnMailStatusesTab() {
		 click(tabMailStatuses,"Clicking on mail statuses");
	 }
	 /**
	  * Click on team users tab 
	  */
	 public void clickOnTeamUsersTab() {
		 click(tabTeamUsers,"Clicking on team users");
	 }
	 
	 /**
	  * Get added role data from table
	  * @return
	  */
	 public List<String> getAddedRoleData() {
		 waitHelper.waitForElement(lblAddedRoleId, 10);
		 String roleId = verificationHelper.getText(lblAddedRoleId);
		 String roleName = verificationHelper.getText(lblAddedRoleName);
		 List<String> roleData= new ArrayList<>(Arrays.asList(roleId,roleName));
		 return roleData;
	 }
	 
	 /**
	  * Get added team data from table
	  * @return
	  */
	 public List<String> getAddedTeamData() {
		 waitHelper.waitForElement(addedTeamIdLbl, 10);
		 String teamId = verificationHelper.getText(addedTeamIdLbl);
		 String teamName = verificationHelper.getText(addedTeamNameLbl);
		 List<String> roleData= new ArrayList<>(Arrays.asList(teamId,teamName));
		 return roleData;
	 }
	 
	 /**
	  * Add team Id in team form
	  * @param teamId
	  */
	 public void addTeamId(String teamId) {
		 waitHelper.waitForElementClickable(driver, teamIdTxt, 5);
		 sendKeys(teamIdTxt, teamId, "Entering teamId as "+teamId);
	 }
	 
	 /**
	  * Add team name in team form
	  * @param teamName
	  */
	 public void addTeamName(String teamName) {
		 sendKeys(teamNameTxt, teamName, "Entering team name as "+teamName);
	 }
	 
	 /**
	  * Select Users role from dropdown  
	  */
	  public void selectUsersRole(String roleName,String getEnvVariable) {
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  click(ddRoleName, "Clicking on role drop down");
			  dropdownHelper.selectFromAutocompleDDWithoutInput(roleName, ddRoleName,getEnvVariable);
		  }else {
			  dropdownHelper.selectFromAutocompleDDWithoutInput(roleName, ddRoleName);  
		  }
	 }
	 	  
	  /**
	   * Add amil status in team form
	   * @param mailStatuses
	   */
	  public void addMailStatuses(List<String> mailStatuses,String getEnvVariable) {
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  for(int i=0; i<mailStatuses.size();i++) {
				  click(mailStatusesdd, "Clicking on mail status drop down");
			  		dropdownHelper.selectFromAutocompleDDWithoutInput(mailStatuses.get(i), mailStatusesdd,getEnvVariable);
			  		click(btnMailStatusesadd, "Clicking on mail status add");
			  	} 
		  }else {
			  for(int i=0; i<mailStatuses.size();i++) {
			  		dropdownHelper.selectFromAutocompleDDWithoutInput(mailStatuses.get(i), mailStatusesdd);
			  		click(btnMailStatusesadd, "Clicking on mail status add");
			  	}   
		  }
	  }
	  
	  /**
	   * Click on filtered user
	   */
	  public void clickOnFilteredUser() {
		  click(lblFilteredUserRow, "clicking on filtered user row");
	  }
	  
	  public void clickOnFilteredTeam() {
		  click(lblFilteredTeamRow, "clicking on filtered team row");  
	  }
	  
	  /**
	   * Clicked on filtered user link
	   */
	   public void clickOnFilteredUserLink() {
		  click(lnkFilteredUserRow, "clicking on filtered user link");
	  }
	   
	   /**
	    * Select users team from users edit form
	    * @param teamName
	    */
	  public void selectUsersTeam(String teamName,String getEnvVariable) {
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  new JavaScriptHelper(driver).scrollByPixel();
			  sleepFor(2000);
			  click(ddUsersTeam, "Clicking on user team drop down");
			  dropdownHelper.selectFromAutocompleDDWithoutInput(teamName, ddUsersTeam,getEnvVariable);
		  }else {
			  new JavaScriptHelper(driver).scrollByPixel();
			  sleepFor(2000);
			  dropdownHelper.selectFromAutocompleDDWithoutInput(teamName, ddUsersTeam);
		  }
	  }
	  
	  /**
	   * Enter userId in users from
	   * @param usersId
	   */
	public void enterUserId(String usersId) {
		waitHelper.waitForElement(txtId, 5);
		sendKeys(txtId, usersId, "Entering user id as "+usersId);
	}
	
	/**
	 * Enter username in users form
	 * @param userName
	 */
	public void enterUsername(String userName) {
		sendKeys(txtUsername, userName, "Entering username as "+userName);
	}
	
	/**
	 * Enter secutity level in users form
	 * @param securityLevel
	 */
	public void enterSecurityLevel(String securityLevel) {
		sendKeys(txtSecurityLevel, securityLevel, "Entering security level as "+securityLevel);
	}
	
	/**
	 * Change security level of user
	 */
	public void changeSecurityLevel() {
		String level = getSecurityLevel();
		int actLevel = Integer.parseInt(level);
		if(actLevel <= 8)
			enterSecurityLevel("9");
		if (actLevel >=9)
			enterSecurityLevel("8");
	}
	  
	  /**
	   * Click on first row of deleted users table
	   */
	 public void clickOnFirstRow() {
		 WebElement filteredElement = navigationMenu.getfilteredRowElement("deletedUsersDataTable");
		 filteredElement.click();
	 }
	 
	 /**
	  * 
	  * @return
	  */
	 public String getDeletedUserText(){
		WebElement filteredElement = navigationMenu.getfilteredRowElement("deletedUsersDataTable");
		 String filteredUser =  filteredElement.getText();
		 return filteredUser;
	 }
	  
	 /**
	  * Get text of filtered deleted users
	  * @return filtered users text
	  */
	 public String getFilteredUserText(){
		WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
		 String filteredUser =  filteredElement.getText();
		 return filteredUser;
	 }
	 
	 /**
	  * Click on Restore user 
	  */
	 public void clickOnRestore() {
		 click(btnRestoreElement,"Clicking on restore user");
		 navigationMenu.clickOkOnPopup();
	 }
	
	/**
	 * 
	 * @return
	 */
	public String getSelectedTeamName() {
		//String teamName = ddUsersTeam.findElement(By.xpath("./span")).getText();
		//String teamName = ddUsersTeam.findElement(By.xpath(".//div[@class='filter-option-inner-inner']")).getText();
		String teamName = ddGetUsersTeam.getText();
		return teamName;
	}
	
	/**
	 * Get selected Role name 
	 * @return
	 */
	public String getSelectedRoleName() {
		//String roleName = ddRoleName.findElement(By.xpath("./span")).getText();
		//String roleName = ddRoleName.findElement(By.xpath(".//div[@class='filter-option-inner-inner']")).getText();
		String roleName = ddGetRoleName.getText();
		return roleName;
	}
	
	/**
	 * Get security level of user
	 * @return
	 */
	public String getSecurityLevel() {
		String securityLevel= txtSecurityLevel.getAttribute("Value");
		return securityLevel;
	}
	
	/**
	 * Click on Team name
	 */
	public void clickOnTeamName() {
		click(lnkLandingPageTeamName, "Clicking on team landing page");
	}

	/**
	 * Click on Audit actions tab
	 */
	public void clickOnAuditActionsTab(){
		click(tabAuditAction, "Clicking on Audit action tab");
	}
	
	/**
	 * Check whether audit action elements are present or not
	 * @return true,false
	 */
	 public boolean isAuditActionsElementsPresent() {
		waitHelper.waitForElementVisible(btnFetchRecords, 20, 1);
		boolean isActionDataRangeDisplayed = verificationHelper.isElementDisplayedByEle(lblActionDataRangeAuditTab);
		boolean isActionDataTypeDisplayed = verificationHelper.isElementDisplayedByEle(lblActionTypeAuditTab);

		boolean isddActionDataRangeDisplayed = verificationHelper.isElementDisplayedByEle(ddActionDataRangeAuditTab);
		boolean isddActionTypeDisplayed = verificationHelper.isElementDisplayedByEle(ddActionTypeAuditTab);
		log.info("isActionDataRangeDisplayed val "+isActionDataRangeDisplayed +" isActionDataTypeDisplayed val "+isActionDataTypeDisplayed
				+" isddActionDataRangeDisplayed val "+ isddActionDataRangeDisplayed +" isddActionTypeDisplayed val"+ isddActionTypeDisplayed);
		return isActionDataRangeDisplayed && isActionDataTypeDisplayed && isddActionDataRangeDisplayed && isddActionTypeDisplayed;
	}
	
	 /**
	  * Click on checked out items tab
	  */
	public void clickOnCheckedOutItemsTab(){
		click(tabCheckedOutItems, "Clicking on checked out items tab");
	}
	
	/**
	 * Get checked out tile count
	 * @return
	 */
	public int getCheckedOutTileCount() {
		String tileCount = verificationHelper.getText(lblCountCheckedOutTile);	
		log.info("Checked out tile count is "+tileCount);
		return Integer.parseInt(tileCount);
	}
	
	/**
	 * Get checked out table rows
	 * @return
	 */
	public int getCountCheckedoutTableRows() {
		return navigationMenu.getRowsCountForTable("userLandingCheckedOutDocsSubGridDataTable");
	}
	
	/**
	 * Enter printer name in form
	 * @param name printer name
	 */
	public void enterPrinterName(String name) {
		sendKeys(txtPrinterName, name, "Entering printer name as "+name);
	}
	
	/**
	 * Enter printer path in form 
	 * @param path
	 */
	public void enterPrinterPath(String path) {
		sendKeys(txtPrinterPath, path, "Entering printer path as "+path);
	}
	
	/**
	 * Click on users save icon
	 * @param teamName
	 * @param roleName
	 */
	public void clickOnUserSaveIcon(String teamName, String roleName,String getEnvVariable) {
		navigationMenu.clickOnSaveIcon();
		windowHelper.waitForPopup("Warning");
		windowHelper.clickOkOnPopup();
		try {
			navigationMenu.waitForAddIcon();
		}
		catch(Exception e){
		//Following code in case no changes to save popup appears
		if(windowHelper.isPopupDisplayed()) {
			windowHelper.clickOkOnPopup();
			navigationMenu.clickOnCancelIcon();
			navigationMenu.waitForAddIcon();
			clickOnFilteredUser();	
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Cancel changes");
			selectUsersRole(roleName,getEnvVariable);
			selectUsersTeam(teamName,getEnvVariable);
			navigationMenu.clickOnSaveIcon();
			windowHelper.clickOkOnPopup();
		}
		}	
	}
	
	/**
	 * click on user save icon
	 */
	public void clickOnUserSaveIcon() {
		navigationMenu.clickOnSaveIcon();
		navigationMenu.waitForAddIcon();
	}
	
	/**
	 * Check/uncheck security right of user
	 * @param securityRight
	 * @param section section of that security right e.g Documents, Admin
	 * @param username username user whose
	 * @param checked if true it checks the checkbox
	 * @throws InterruptedException
	 */
	public void checkSecurityRightsForUser(String securityRight, String section, String username, boolean checked,String environmentVar) throws InterruptedException {
		home.clickOnChangeUser();
		sleepFor(2000);
		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		//sleepFor(2000);
		//login.loginWithCredentials(ObjectReader.reader.getSuperLoginId(), ObjectReader.reader.getSuperUserPassword());
		home.changeFileSystem(ObjectReader.reader.getFileSystemName());
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("User maintenance", "User");
		navigationMenu.searchInFilter(username);
		Thread.sleep(2000);
		clickOnFilteredUser();
		navigationMenu.clickOnEditIcon();
		clickOnUserNavTab("Security");
		String sectionExpandXpath = String.format(tmpBtnExpandSectionXpath, section);
		WebElement sectionExpandEle = driver.findElement(By.xpath(sectionExpandXpath));
		String sectionTreeStatus = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
		if (sectionTreeStatus.equals("tree-collapsed")) {
			//click(sectionExpandEle, "Clicking on expand section element");
			new JavaScriptHelper(driver).clickElement(sectionExpandEle);
			sleepFor(1000);
		}
		String secuirtyRightXpath = String.format(tmpChkSecurityXpath, securityRight);
		WebElement chkSecurityRightEle = waitHelper.waitForElementVisible(driver.findElement(By.xpath(secuirtyRightXpath)),20,1);
		if(!chkSecurityRightEle.isSelected()) {
			//click(chkSecurityRightEle, "clicking on security right of "+secuirtyRightXpath);
			new JavaScriptHelper(driver).clickElement(chkSecurityRightEle);
			sleepFor(1000);
			clickOnUserNavTab("General");
		changeSecurityLevel();
		Thread.sleep(2000);
		if(environmentVar.equals("Enterprise_Sp1s")) {
			clickOnUserSaveIcon();
		}else {
			navigationMenu.clickOnSaveIconForUser();			
		}
		//clickOnUserSaveIcon();
		//navigationMenu.clickOnSaveIconForUser();			
		navigationMenu.waitForAddIcon();
		}
		else {
			if(!checked) {										//Used to uncheck the rights
				sleepFor(2000);
				//click(chkSecurityRightEle, "clicking on security right of "+secuirtyRightXpath);
				new JavaScriptHelper(driver).clickElement(chkSecurityRightEle);
				sleepFor(1000);
				clickOnUserNavTab("General");
				Thread.sleep(2000);
				if(environmentVar.equals("Enterprise_Sp1s")) {
					clickOnUserSaveIcon();
				}else {
					navigationMenu.clickOnSaveIconForUser();			
				}
				navigationMenu.waitForAddIcon();
			}else {
			navigationMenu.clickOnCancelIcon();
			navigationMenu.waitForAddIcon();			   
			}
		}
		home.clickOnChangeUser();
		sleepFor(4000);
		login.loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		sleepFor(2000);
	}
	
	/**
	 * Click on provided users nav tab
	 * @param tabName nav tab name
	 */
	public void clickOnUserNavTab(String tabName) {
		String navTabXpath = String.format(tmpNavTabXpath, tabName);
		jsHelper.scrollToTop();
		WebElement lnkNavTabEle = waitHelper.waitForElementClickable(driver,driver.findElement(By.xpath(navTabXpath)),20);
		click(lnkNavTabEle, "Clicking on tab of "+tabName);
	}
	
	/**
	 * Get unique action value of user landing audit actions table
	 * @return unique column value
	 */
	public Set<String> getUniqueActionColumnValues(){
		List<String> columnVals = navigationMenu.getAllColumnValues("userLandingAuditActionsSubGridDataTable", 4);
		Set<String> uniqueColumnVals = new HashSet<String>(columnVals);
		return uniqueColumnVals;
	}
	
	/**
	 * Select audit action type from dropdown
	 * @param actionValue audit dropdown value
	 * @throws InterruptedException
	 */
	public void selectFromAuditActionType(String actionValue,String getEnvVariable) throws InterruptedException {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddActionTypeAuditTab, "Clicking on audit action dropdown");
			dropdownHelper.selectFromAutocompleDD(actionValue, ddActionTypeAuditTab551OnWards,getEnvVariable);
			click(btnFetchRecords, "Clicking on button fetch records");
			sleepFor(1000);
		}else {
			dropdownHelper.selectFromAutocompleDD(actionValue, ddActionTypeAuditTab);
			click(btnFetchRecords, "Clicking on button fetch records");
			sleepFor(1000);
		}
	}
	
	/**
	 * Navigate to team maintenance page
	 */
	public void clickOnTeamsUnderAdmin() {
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Team maintenance", "User");
		navigationMenu.waitForAddIcon();
	}
	
	/**
	 * Navigate to add new team form
	 */
	public void clickOnAddIconForTeam() {
		navigationMenu.clickOnIconMenu("Add a new team", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
	}
	
	/**
	 * To get all values in dropdown printers text under add team form 
	 * @return List of all values in string format
	 */
	public List<String> getDropdownPrintersTextUnderAddTeam(String getEnvVariable) {
		return dropdownHelper.getAllOptionsFromDropdown("PrinterId",getEnvVariable);
		
	}


		/**
	    * Select campaign from users edit form
	    * @param campaign name
	    */
	 
	public void selectUsersCampaign(String campaignName,String getEnvVariable) {
		  new JavaScriptHelper(driver).scrollByPixel();
		  //new JavaScriptHelper(driver).scrollToElement(ddUsersCampaign);
		  sleepFor(2000);
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  click(ddUsersCampaign, "Clicking on user campaiga drop down");
			  dropdownHelper.selectFromAutocompleDD(campaignName, ddUsersCampaignR551OnWards,getEnvVariable);
		  }else {
			  dropdownHelper.selectFromAutocompleDDWithoutInput(campaignName, ddUsersCampaign);  
		  }
	  }
	  
	  
	  public void enterProfileName(String profileName) {
		  sendKeys(profileId,profileName , "Entering profile name "+profileName);
	  }
	  
	  
	  public void enterProfileDescription(String pdescription) {
		  sendKeys(profileDescription, pdescription, "Entering profile desc "+pdescription);
	  }
	  
	  /*
	   * It is used to add user security permission
	   */
	  public void addSecurityPermissionForUser(Map<String, List<String>> userPermission) {
		  
		  for(Map.Entry<String, List<String>> map : userPermission.entrySet()) {
			  
			  String sectionExpandXpath = String.format(tmpBtnExpandSectionXpath, map.getKey());
				WebElement sectionExpandEle = driver.findElement(By.xpath(sectionExpandXpath));
				String sectionTreeStatus = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
				if (sectionTreeStatus.equals("tree-collapsed")) {
					sleepFor(1000);
					new JavaScriptHelper(driver).clickElement(sectionExpandEle);
					//click(sectionExpandEle, "Clicking on expand section element");
					sleepFor(1000);
				}
				List<String> values=map.getValue();
				for(String mapValues : values) {
					String secuirtyRightXpath = String.format(tmpChkSecurityXpathForProfile, mapValues);
					sleepFor(1000);
					WebElement chkSecurityRightEle = waitHelper.waitForElementVisible(driver.findElement(By.xpath(secuirtyRightXpath)),20,1);
					if(!chkSecurityRightEle.isSelected()) {
						sleepFor(1000);
						//click(chkSecurityRightEle, "clicking on security right of "+secuirtyRightXpath);
						new JavaScriptHelper(driver).clickElement(chkSecurityRightEle);
					}
				}
				//tree-expanded
//				String sectionCollapseXpath = String.format(tmpBtnExpandSectionXpath, map.getKey());
//				WebElement sectionCollapseEle = driver.findElement(By.xpath(sectionCollapseXpath));
//				String sectionTreeStatus1 = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
//				if(sectionTreeStatus1.equals("tree-expanded")) {
//					sleepFor(2000);
//					new JavaScriptHelper(driver).scrollToElement(sectionCollapseEle);
//					sleepFor(1000);
//					click(sectionCollapseEle, "Clicking on element");
//				}	
		  }
		  
		  sleepFor(1000);
		  
	  }

	  
	  
	  public void checkSecurityPermissionWhileAddngUser(HashMap<String,Boolean> map) {

		  for(Map.Entry<String, Boolean> mapV : map.entrySet()) {
			  String securityStatus = String.format(tmpChkSecurityXpathForProfile, mapV.getKey());
			  WebElement getSecurityRightStatus=driver.findElement(By.xpath(securityStatus));
			  sleepFor(1000);
			  boolean getActualSecurityRightStatus=getSecurityRightStatus.isSelected();
			  //System.out.println(getSecurityRightStatus+"=============================");
			  if(mapV.getValue()) {
				  AssertionHelper.verifyTrue(getActualSecurityRightStatus,"Verifying security right "+securityStatus);
			  }else {
				  AssertionHelper.verifyFalse(getActualSecurityRightStatus,"Verifying security right "+securityStatus);
			  }
		  } 
	  }


	  public void selectUserSecurityProfile(String profileName,String getEnvVariable) {
		 if(getEnvVariable.equals("Enterprise_R551")) {
			  new JavaScriptHelper(driver).scrollToBottom();     //Added because in R540 element is not clickable
			  sleepFor(2000);
			  click(ddUsersSecurityProfile, getEnvVariable);
			  //dropdownHelper.selectFromAutocompleDDWithoutInput(profileName, ddUsersSecurityProfile551OnWards,getEnvVariable);
			  dropdownHelper.selectFromAutocompleDD(profileName, ddUsersSecurityProfile551OnWards, getEnvVariable);
		 }else {
			  new JavaScriptHelper(driver).scrollToBottom();     //Added because in R540 element is not clickable
			  sleepFor(1000);
			  dropdownHelper.selectFromAutocompleDDWithoutInput(profileName, ddUsersSecurityProfile);
		 }
	  }

	  public String getValidationMessage() {
		  String getMessage = getSecurityProfileValidation.getText();
		  log.info("Validation message "+getMessage);
		  return getMessage;
	  }
	  
//	  public void checkColour(HashMap<String,Boolean> map) {
//
//		  for(Map.Entry<String, Boolean> mapV : map.entrySet()) {
//			  String securityStatus = String.format(tmpChkSecurityToGetColor, mapV.getKey());
//			  WebElement getSecurityRightStatus=driver.findElement(By.xpath(securityStatus));
//			  sleepFor(1000);
//			  String getColour=getSecurityRightStatus.getCssValue("background-color");
//			  System.out.println(getColour+"============================="+getColour);
//		  } 
//	  }
	  
	  
	  public void verifySecurityPermissionAfterOverriding(HashMap<String,List<String>> map) {

		  for(Map.Entry<String, List<String>> mapV : map.entrySet()) {
			  
			  
			  String sectionExpandXpath = String.format(tmpBtnExpandSectionXpath, mapV.getKey());
				WebElement sectionExpandEle = driver.findElement(By.xpath(sectionExpandXpath));
				String sectionTreeStatus = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
				if (sectionTreeStatus.equals("tree-collapsed")) {
					sleepFor(1000);
					new JavaScriptHelper(driver).clickElement(sectionExpandEle);
					//click(sectionExpandEle, "Clicking on expand section element");
					sleepFor(2000);
				}
			  
				List<String> values=mapV.getValue();
				for(String mapValues : values) {
					String secuirtyRightXpath = String.format(tmpChkSecurityXpathForProfile, mapValues);
					WebElement chkSecurityRightEle = waitHelper.waitForElementVisible(driver.findElement(By.xpath(secuirtyRightXpath)),20,1);
					sleepFor(1000);
					boolean getSecurityStatus=chkSecurityRightEle.isSelected();
					//System.out.println("getSecurityStatus =============="+getSecurityStatus);
					AssertionHelper.verifyTrue(getSecurityStatus, "Verifying security right checkbox "+secuirtyRightXpath);
				}
			  
		  } 
	  }
	  
	  public void AddCascadeUserToProfile(String userName) {
		  String userNameCheckbox = String.format(tmpUserCascadeXpath, userName);
		  WebElement selectUserNameCheckbox = driver.findElement(By.xpath(userNameCheckbox));
		  if(!selectUserNameCheckbox.isSelected()) {
			  click(selectUserNameCheckbox, "Clicking on userName checkbox"+userNameCheckbox);
		  }
	  }
	  
	  
	  public void VerifyConfilctedRowInSecurityRight(HashMap<String,List<String>> map) {
		  
		  for(Map.Entry<String, List<String>> mapV : map.entrySet()) {
			  
			  
			  String sectionExpandXpath = String.format(tmpBtnExpandSectionXpath, mapV.getKey());
				WebElement sectionExpandEle = driver.findElement(By.xpath(sectionExpandXpath));
				String sectionTreeStatus = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
				if (sectionTreeStatus.equals("tree-collapsed")) {
					//new JavaScriptHelper(driver).scrollToElementAndClick(sectionExpandEle);
					click(sectionExpandEle, "Clicking on expand section element");
					sleepFor(1000);
				}
			  
				List<String> values=mapV.getValue();
				for(String mapValues : values) {
					String secuirtyRightXpath = String.format(tmpGetConflictedRowXpath, mapValues);
					WebElement chkSecurityRightEle = waitHelper.waitForElementVisible(driver.findElement(By.xpath(secuirtyRightXpath)),20,1);
					String getConflictedRowValue=chkSecurityRightEle.getAttribute("class");
					AssertionHelper.verifyText(getConflictedRowValue, "conflictedRow");
				}
			  
		  } 
		  
	  }
	  
	  public void selectSecurityRestrictionType(String restrictionType,String getEnvVariable) {
		  new JavaScriptHelper(driver).scrollToElement(ddUserSecurityRestrictionType);
		  sleepFor(2000);
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  click(ddUserSecurityRestrictionType, "Clicking on user restriction drop down");
			  dropdownHelper.selectFromAutocompleDDWithoutInput(restrictionType, ddCommonDropdownElementWithoutInput,getEnvVariable);
		  }else {
			  dropdownHelper.selectFromAutocompleDDWithoutInput(restrictionType, ddUserSecurityRestrictionType);  
		  }
	  }
	  
	  public void enterFolderRefValue(String refValue) {
		  sendKeys(inputFolderRestriction, refValue, "Inputting the "+refValue);
		  new ActionHelper(driver).pressTab();
		  sleepFor(1000);
		  addFolderBtn.click();
	  }
	  
	  public void deleteRestrictedFolder(String folderRef) {
		  String deleteBtn=String.format(tmpDeleteRestrictedXpath, folderRef);
		  WebElement deleteRestrictedBtn=driver.findElement(By.xpath(deleteBtn));
		  deleteRestrictedBtn.click();
		  navigationMenu.clickOkOnPopup();
		  sleepFor(1000);
		  
	  }
	  
	  public void addRestrictedGroupToUser(String restrictedGroup) {
		  String addBtn=String.format(tmpGroupRestrictedAddBtn, restrictedGroup);
		  WebElement addGroupRestrictedBtn=driver.findElement(By.xpath(addBtn));
		  click(addGroupRestrictedBtn, "Clicking on add group restricted btn");
	  }
	  
	  
	  public void enterJobRoleName(String roleName) {
		  sendKeys(roleNameTxt, roleName, "Entering role name "+roleName);
	  }
	  
	  public void addJobRoleForSecurityProfile(String jobrole,String getEnvVariable) {
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  click(ddSecurityJobRole, "Clicking on job role DD in security profile");
			  dropdownHelper.selectFromAutocompleDDWithoutInput(jobrole, ddSecurityJobRole,getEnvVariable);
			  sleepFor(2000);
			  click(addJobRoleBtn, "clicking on add job role button");
		  }else {
			  dropdownHelper.selectFromAutocompleDDWithoutInput(jobrole, ddSecurityJobRole);
			  sleepFor(2000);
			  click(addJobRoleBtn, "clicking on add job role button");
		  }
	  }
	  
	  public void addJobRoleForUser(List<String> jobrolelist,String getEnvVariable) {
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  for(int i=0;i<jobrolelist.size();i++) {
				  click(ddUserJobRole, "Clicking on job role DD in user");
				  dropdownHelper.selectFromAutocompleDD(jobrolelist.get(i), ddUserJobRole551OnWards,getEnvVariable);
				  sleepFor(2000);
				  click(addJobRoleBtn, "Clicking on add user job role button");
			  }
		  }else {
			  for(int i=0;i<jobrolelist.size();i++) {
				  dropdownHelper.selectFromAutocompleDD(jobrolelist.get(i), ddUserJobRole);
				  sleepFor(2000);
				  click(addJobRoleBtn, "Clicking on add user job role button");
			  }
		  }
	  }
	  
	  public List<String> getAssociatedJobRolesFromUser() {
		  List<String> jobroles = new ArrayList<String>();
		  
		  List<WebElement> eleList = driver.findElements(By.xpath("//th[text()='Job Roles']/ancestor::thead/following-sibling::tbody/tr"));
		  
		  for(int i = 1;i<=eleList.size();i++) {
			 String eleText = driver.findElement(By.xpath("//th[text()='Job Roles']/ancestor::thead/following-sibling::tbody/tr["+i+"]//td[1]")).getText();
			 jobroles.add(eleText);
		  }
		  log.info("Job Role List "+jobroles);
		  return jobroles;
	  }
	  
	  public void deleteJobRoleFromSecurityProfile(String jobroleName) {
		  String deleteBtn = String.format(tmpDeleteJobRoleBtn, jobroleName);
		  WebElement deleteJobRoleBtn = driver.findElement(By.xpath(deleteBtn));
		  click(deleteJobRoleBtn, "Clicking on delete job role button");
		  sleepFor(1000);
		  navigationMenu.clickOkOnPopup();;
	  }
	  
	  /*
	   * Used to get All Users from users page
	  */
	 public List<String> getAllUsersFromUsersPage() {
		  List<String> users =  navigationMenu.getAllColumnValues("userDataTable", 2);
		  return users;
	  }
	 
	 /*
	   * Used to get All Teams
	  */
	 public List<String> getAllTeams() {
		  List<String> teams =  navigationMenu.getAllColumnValues("teamsTable", 2);
		  return teams;
	  }
	 
	 /*
	  * Cascade User Checkbox in Security profile
	  */
	  public void CascadeUserCheckbox(String userName) {
		  String cascadeChkbox = String.format(tmpCascadeUserChechbox, userName);
		  WebElement cascadeChkboxBtn = driver.findElement(By.xpath(cascadeChkbox));
		  click(cascadeChkboxBtn, "Clicking on cascasde User checkbox");
	  }
	  
	  
	  /*
	   * Used to check that correct security rights are assign after cascade 
	   */
		public void checkSecurityPermissionAfterCascadingToUser(HashMap<String, List<String>> map) {

			for (Map.Entry<String, List<String>> mapV : map.entrySet()) {

				String sectionExpandXpath = String.format(tmpBtnExpandSectionXpath, mapV.getKey());
				WebElement sectionExpandEle = driver.findElement(By.xpath(sectionExpandXpath));
				String sectionTreeStatus = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
				if (sectionTreeStatus.equals("tree-collapsed")) {
					sleepFor(1000);
					new JavaScriptHelper(driver).clickElement(sectionExpandEle);
					// click(sectionExpandEle, "Clicking on expand section element");
				}

				List<String> values = mapV.getValue();
				for (String mapValues : values) {
					String securityStatus = String.format(tmpChkSecurityXpathForProfile, mapValues);
					WebElement getSecurityRightStatus = driver.findElement(By.xpath(securityStatus));
					sleepFor(1000);
					boolean getActualSecurityRightStatus = getSecurityRightStatus.isSelected();
					// System.out.println(getSecurityRightStatus+"=============================");
					if (getActualSecurityRightStatus) {
						AssertionHelper.verifyTrue(getActualSecurityRightStatus,
								"Verifying security right " + securityStatus);
					} else {																//if cascaded changes are not there	then it shows in conflicted row	
						String secuirtyRightXpath = String.format(tmpGetConflictedRowXpath, mapValues);
						WebElement chkSecurityRightEle = waitHelper.waitForElementVisible(driver.findElement(By.xpath(secuirtyRightXpath)),20,1);
						String getConflictedRowValue=chkSecurityRightEle.getAttribute("class");
						AssertionHelper.verifyText(getConflictedRowValue, "conflictedRow");

						AssertionHelper.verifyFalse(getActualSecurityRightStatus,
								"Verifying security right " + securityStatus);
					}

				}
			}
		}
		
		  /*
		   * It is used to remove user security rights of user
		   */
		  public void RemoveSecurityRightsFromUser(Map<String, List<String>> userPermission) {
			  
			  for(Map.Entry<String, List<String>> map : userPermission.entrySet()) {
				  
				  String sectionExpandXpath = String.format(tmpBtnExpandSectionXpath, map.getKey());
					WebElement sectionExpandEle = driver.findElement(By.xpath(sectionExpandXpath));
					String sectionTreeStatus = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
					if (sectionTreeStatus.equals("tree-collapsed")) {
						sleepFor(1000);
						new JavaScriptHelper(driver).clickElement(sectionExpandEle);
						//click(sectionExpandEle, "Clicking on expand section element");
						sleepFor(1000);
					}
					List<String> values=map.getValue();
					for(String mapValues : values) {
						String secuirtyRightXpath = String.format(tmpChkSecurityXpathForProfile, mapValues);
						sleepFor(1000);
						WebElement chkSecurityRightEle = waitHelper.waitForElementVisible(driver.findElement(By.xpath(secuirtyRightXpath)),20,1);
						sleepFor(1000);
						new JavaScriptHelper(driver).clickElement(chkSecurityRightEle);
					}
					//tree-expanded
//					String sectionCollapseXpath = String.format(tmpBtnExpandSectionXpath, map.getKey());
//					WebElement sectionCollapseEle = driver.findElement(By.xpath(sectionCollapseXpath));
//					String sectionTreeStatus1 = sectionExpandEle.findElement(By.xpath("./img")).getAttribute("class");
//					if(sectionTreeStatus1.equals("tree-expanded")) {
//						sleepFor(2000);
//						new JavaScriptHelper(driver).scrollToElement(sectionCollapseEle);
//						sleepFor(1000);
//						click(sectionCollapseEle, "Clicking on element");
//					}	
			  }
			  
			  sleepFor(1000);
			  
		  }
		  
			/*
			 * Created by sagar
			 */
			public void AddStandardUser(String standardUserName,String getEnvVariable) throws InterruptedException {
				getApplicationUrl();
				navigationMenu.clickOnTab("Administration");
				navigationMenu.clickOnIcon("User maintenance", "User");
				navigationMenu.clickOnAddIcon();

				new AdminUserSection(driver).enterUserId(standardUserName);
				new AdminUserSection(driver).enterUsername(standardUserName);
				sleepFor(1000);
				new AdminUserSection(driver).enterSecurityLevel("9");
				sleepFor(1000);
				new AdminUserSection(driver).selectSecurityRestrictionType("Restrict", getEnvVariable);
				new JavaScriptHelper(driver).scrollToTop();
				sleepFor(2000);
				new AdminUserSection(driver).clickOnUserNavTab("Security");

				// HashMap<String, String> userPermission = new LinkedHashMap<String, String>();

				HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

				List<String> systemAdministrationList = new ArrayList<String>();
				systemAdministrationList.add("Can Create Tags ");

				List<String> adminList = new ArrayList<String>();
				adminList.add("DIP");
				// adminList.add("Update Environment");

				List<String> intrayProcessingList = new ArrayList<String>();
				intrayProcessingList.add("Take Work from a Shared In-Tray");
				intrayProcessingList.add("Process Work From Another Users In-Tray");
				intrayProcessingList.add("Distribute Work to Other Users In-Trays");

				List<String> folderList = new ArrayList<String>();
				// folderList.add("Update Restricted Folder Reference Documents");
				folderList.add("Allow Create Dummy Folder References ");
				folderList.add("Allow Translate Folder References");

				List<String> documentList = new ArrayList<String>();
				// documentList.add("Delete Documents");
				documentList.add("Use Clipbook");
				documentList.add("Create Renditions and apply Redaction Templates");
				documentList.add("Edit Documents (CheckIn and CheckOut)");
				// documentList.add("Create and edit Redaction Templates");
				documentList.add("Scan Documents");
				documentList.add("Batch Index Documents");
				documentList.add("Capture Documents");
				documentList.add("ReIndex Documents");
				// documentList.add("Allow locking of documents to prevent editing");
				// documentList.add("Allow unlocking of documents to allow editing");
				documentList.add("Allow routing of reindexed documents");
				// documentList.add("Alter Protective Marker");
				documentList.add("Can Export Document");
				documentList.add("Can Export Document Metadata");
				documentList.add("Can Edit Metadata");
				documentList.add("Can Print Document");
				documentList.add("Allow Document Linking");

				List<String> coldList = new ArrayList<String>();
				coldList.add("COLD");
				coldList.add("Allow Cold Document Redaction");

				userPermission.put("System Administration", systemAdministrationList);
				userPermission.put("Admin", adminList);
				userPermission.put("Intray Processing", intrayProcessingList);
				userPermission.put("Folder", folderList);
				userPermission.put("Document", documentList);
				userPermission.put("Cold", coldList);

				new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
				sleepFor(1000);
				new JavaScriptHelper(driver).scrollToTop();
				new AdminUserSection(driver).clickOnUserNavTab("General");
				sleepFor(1000);
				if (getEnvVariable.equals("Enterprise_Sp1s")) {
					navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
					// navigationMenu.waitForAddIcon();
				} else {
					navigationMenu.clickOnSaveIcon();
					navigationMenu.waitForAddIcon();
				}

			}

			/*
			 * creatd by sagar
			 */
			public void EditUserWithTeamAndRole(String fileSystemAdminUserName, String roleName, String teamName)
					throws InterruptedException {
				String getEnvVariable = System.getProperty("propertyName");
				System.out.println("getEnvVariable====================" + getEnvVariable);

				getApplicationUrl();
				navigationMenu.clickOnTab("Administration");
				navigationMenu.clickOnIcon("User maintenance", "User");
				navigationMenu.searchInFilter(fileSystemAdminUserName); // User_N147 //User_N5341
				navigationMenu.clickOnFilteredRow("userDataTable");
				navigationMenu.clickOnEditIcon();
				sleepFor(2000);

				sleepFor(2000);
				selectUsersRole(roleName, getEnvVariable);
				sleepFor(1000);
				selectUsersTeam(teamName, getEnvVariable);

				sleepFor(1000);
				new JavaScriptHelper(driver).scrollToTop();
				new AdminUserSection(driver).clickOnUserNavTab("General");
				sleepFor(1000);
				if (getEnvVariable.equals("Enterprise_Sp1s")) {
					navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
					// navigationMenu.waitForAddIcon();
					sleepFor(2000);
					WebElement noChangesPopup = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
					boolean status = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
					String getMsg = new WindowHelper(driver).getPopupMessage();
					if(getMsg == "No changes to save.") {
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					navigationMenu.clickOnIcon("Cancel changes", "Actions");
					sleepFor(5000);
					try {
						navigationMenu.waitForAddIcon();
					} catch (Exception e) {
						getApplicationUrl();
					}
				} else {
					navigationMenu.clickOnSaveIcon();
					sleepFor(2000);
					WebElement noChangesPopup1 = driver.findElement(By.xpath(String.format(windowHelper.tmpPopupTitle, "Save")));
					boolean status1 = new VerificationHelper(driver).isElementDisplayedByEle(noChangesPopup);
					String getMsg1 = new WindowHelper(driver).getPopupMessage();
					if(getMsg1 == "No changes to save.") {
					new WindowHelper(driver).clickOkOnPopup();
					navigationMenu.clickOnIcon("Cancel changes", "Actions");
					navigationMenu.waitForAddIcon();
					}
				}
				boolean status1 = new WindowHelper(driver).isPopupDisplayed();
				if (status1) {
					String actMessage = new WindowHelper(driver).getPopupMessage();
					new WindowHelper(driver).clickOkOnPopup();
					sleepFor(2000);
					System.out.println("get error message ====================================" + actMessage);
					sleepFor(2000);
				}
				}

				
//				try {
//					navigationMenu.waitForAddIcon();
//					navigationMenu.clickOnAddIcon();
//				} catch (Exception e) {
//					 String actMessage = windowHelper.getPopupMessage();
//					 windowHelper.clickOkOnPopup();
//					 AssertionHelper.verifyTextContains(actMessage, "No changes to save.");
//				}
			}
			
			public void CreateSuperAdminUser(String superAdminUserName,String getEnvVariable) {
				getApplicationUrl();
				navigationMenu.clickOnTab("Administration");
				navigationMenu.clickOnIcon("User maintenance", "User");
				navigationMenu.clickOnAddIcon();

				new AdminUserSection(driver).enterUserId(superAdminUserName);
				new AdminUserSection(driver).enterUsername(superAdminUserName);
				sleepFor(1000);
				new AdminUserSection(driver).enterSecurityLevel("9");
				sleepFor(1000);
				new AdminUserSection(driver).selectSecurityRestrictionType("Restrict", getEnvVariable);
				new JavaScriptHelper(driver).scrollToTop();
				sleepFor(2000);
				new AdminUserSection(driver).clickOnUserNavTab("Security");

				// HashMap<String, String> userPermission = new LinkedHashMap<String, String>();

				HashMap<String, List<String>> userPermission = new LinkedHashMap<>();

				List<String> systemAdministrationList = new ArrayList<String>();
				systemAdministrationList.add("System Administration");
				systemAdministrationList.add("Is Supervisor");
				systemAdministrationList.add("Is Administrator");
				systemAdministrationList.add("Campaign Admin");
				systemAdministrationList.add("Alter User/Team Campaigns");
				systemAdministrationList.add("Stamp Administrator");
				systemAdministrationList.add("Is RM Administrator");
				systemAdministrationList.add("Folder Security Administrator");
				systemAdministrationList.add("Can Create Tags ");
				systemAdministrationList.add("Can Edit Tags ");
				systemAdministrationList.add("Can Delete Tags ");
				systemAdministrationList.add("Allow Flexible entity Import ");
				systemAdministrationList.add("Allow All FileSystem On Integration ");
				systemAdministrationList.add("Is System Implementer");
				systemAdministrationList.add("Can Edit File System Tiles");
				systemAdministrationList.add("Can Edit Team Tiles");
				systemAdministrationList.add("Can Edit Role Tiles");
				List<String> adminList = new ArrayList<String>();
				adminList.add("DIP");
				adminList.add("Update Environment");

				List<String> intrayList = new ArrayList<String>();
				intrayList.add("View Other Users In-Trays");
				intrayList.add("View Other Teams In-Trays");
				intrayList.add("View Intrays Across File Systems");

				List<String> intrayProcessingList = new ArrayList<String>();
				intrayProcessingList.add("Take Work from a Shared In-Tray");
				intrayProcessingList.add("Process Work From Another Users In-Tray");
				intrayProcessingList.add("Distribute Work to Other Users In-Trays");

				List<String> folderList = new ArrayList<String>();
				folderList.add("Update Restricted Folder Reference Documents");
				folderList.add("Allow Create Dummy Folder References ");
				folderList.add("Allow Translate Folder References");

				List<String> documentList = new ArrayList<String>();
				documentList.add("Delete Documents");
				documentList.add("Use Clipbook");
				documentList.add("Create Renditions and apply Redaction Templates");
				documentList.add("Edit Documents (CheckIn and CheckOut)");
				documentList.add("Create and edit Redaction Templates");
				documentList.add("Scan Documents");
				documentList.add("Batch Index Documents");
				documentList.add("Capture Documents");
				documentList.add("ReIndex Documents");
				documentList.add("Allow locking of documents to prevent editing");
				documentList.add("Allow unlocking of documents to allow editing");
				documentList.add("Allow routing of reindexed documents");
				documentList.add("Alter Protective Marker");
				documentList.add("Can Export Document");
				documentList.add("Can Export Document Metadata");
				documentList.add("Can Edit Metadata");
				documentList.add("Can Print Document");
				documentList.add("Allow Document Linking");

				List<String> notesList = new ArrayList<String>();
				notesList.add("Delete / Update Notes");

				List<String> archiveList = new ArrayList<String>();
				archiveList.add("Archive Documents");

				List<String> reportsList = new ArrayList<String>();
				reportsList.add("View Reports");

				List<String> coldList = new ArrayList<String>();
				coldList.add("COLD");
				coldList.add("Allow Cold Document Redaction");

				// List<String> memoList = new ArrayList<String>();
				// memoList.add("Update Another User's Memos (excluding Intray)");

				List<String> caseManagementList = new ArrayList<String>();
				caseManagementList.add("QA User (VF)");
				caseManagementList.add("Pre-Assessor (VF)");

				List<String> workFlowList = new ArrayList<String>();
				workFlowList.add("Access Enterprise Workflow");
				workFlowList.add("Open Item");
				workFlowList.add("Delete Item");
				workFlowList.add("Pick Item");
				workFlowList.add("Return Item");
				workFlowList.add("Forward Items By User");
				workFlowList.add("Forward Items By Role");
				workFlowList.add("Resubmit Item");
				workFlowList.add("Display Audit Information");

				userPermission.put("System Administration", systemAdministrationList);
				userPermission.put("Admin", adminList);
				userPermission.put("Intray", intrayList);
				userPermission.put("Intray Processing", intrayProcessingList);
				userPermission.put("Folder", folderList);
				userPermission.put("Document", documentList);
				userPermission.put("Notes", notesList);
				userPermission.put("Archive", archiveList);
				userPermission.put("Reports", reportsList);
				userPermission.put("Cold", coldList);
				// userPermission.put("Memo", memoList);
				userPermission.put("Case Management", caseManagementList);
				userPermission.put("Enterprise Workflow", workFlowList);

				new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
				sleepFor(1000);
				new JavaScriptHelper(driver).scrollToTop();
				new AdminUserSection(driver).clickOnUserNavTab("General");
				sleepFor(1000);
				if (getEnvVariable.equals("Enterprise_Sp1s")) {
					navigationMenu.clickOnIconMenu("Save changes made to user", "Actions");
					// navigationMenu.waitForAddIcon();
				} else {
					navigationMenu.clickOnSaveIcon();
					navigationMenu.waitForAddIcon();
				}

			}
	
		public void CreateFileSystemAdminUser(String fileSystemAdminUserName,String getEnvVariable) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
	        navigationMenu.clickOnIcon("User maintenance", "User");
	        navigationMenu.clickOnAddIcon();
	        
	        new AdminUserSection(driver).enterUserId(fileSystemAdminUserName);
	        new AdminUserSection(driver).enterUsername(fileSystemAdminUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("System Administration");
			systemAdministrationList.add("Is Supervisor");
			systemAdministrationList.add("Is Administrator");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");
			systemAdministrationList.add("Stamp Administrator");
			systemAdministrationList.add("Is RM Administrator");
			systemAdministrationList.add("Folder Security Administrator");
			systemAdministrationList.add("Can Create Tags ");
			systemAdministrationList.add("Can Edit Tags ");
			systemAdministrationList.add("Can Delete Tags ");
			systemAdministrationList.add("Allow Flexible entity Import ");
			systemAdministrationList.add("Allow All FileSystem On Integration ");
			//systemAdministrationList.add("Is System Implementer");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			adminList.add("Update Environment");
			
			List<String> intrayList = new ArrayList<String>();
			intrayList.add("View Other Users In-Trays");
			intrayList.add("View Other Teams In-Trays");
			intrayList.add("View Intrays Across File Systems");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			documentList.add("Allow locking of documents to prevent editing");
			documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			List<String> notesList = new ArrayList<String>();
			notesList.add("Delete / Update Notes");
			
			List<String> archiveList = new ArrayList<String>();
			archiveList.add("Archive Documents");
			
			List<String> reportsList = new ArrayList<String>();
			reportsList.add("View Reports");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			//List<String> memoList = new ArrayList<String>();
			//memoList.add("Update Another User's Memos (excluding Intray)");
			
			List<String> caseManagementList = new ArrayList<String>();
			caseManagementList.add("QA User (VF)");
			caseManagementList.add("Pre-Assessor (VF)");
			
			List<String> workFlowList = new ArrayList<String>();
			workFlowList.add("Access Enterprise Workflow");
			workFlowList.add("Open Item");
			workFlowList.add("Delete Item");
			workFlowList.add("Pick Item");
			workFlowList.add("Return Item");
			workFlowList.add("Forward Items By User");
			workFlowList.add("Forward Items By Role");
			workFlowList.add("Resubmit Item");
			workFlowList.add("Display Audit Information");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray", intrayList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			userPermission.put("Notes", notesList);
			userPermission.put("Archive", archiveList);
			userPermission.put("Reports", reportsList);
			userPermission.put("Cold", coldList);
			//userPermission.put("Memo", memoList);
			userPermission.put("Case Management", caseManagementList);
			userPermission.put("Enterprise Workflow", workFlowList);
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}

		}
		
	
	  public void CreateSupervisorUser(String supervisorUserName,String getEnvVariable) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
	        navigationMenu.clickOnIcon("User maintenance", "User");
	        navigationMenu.clickOnAddIcon();
	        
	        new AdminUserSection(driver).enterUserId(supervisorUserName);
	        new AdminUserSection(driver).enterUsername(supervisorUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			//systemAdministrationList.add("System Administration");
			systemAdministrationList.add("Is Supervisor");
			//systemAdministrationList.add("Is Administrator");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");
			//systemAdministrationList.add("Stamp Administrator");
			//systemAdministrationList.add("Is RM Administrator");
			//systemAdministrationList.add("Folder Security Administrator");
			systemAdministrationList.add("Can Create Tags ");
			systemAdministrationList.add("Can Edit Tags ");
			systemAdministrationList.add("Can Delete Tags ");
			//systemAdministrationList.add("Allow Flexible entity Import ");
			//systemAdministrationList.add("Allow All FileSystem On Integration ");
			//systemAdministrationList.add("Is System Implementer");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			//adminList.add("Update Environment");
			
			List<String> intrayList = new ArrayList<String>();
			intrayList.add("View Other Users In-Trays");
			intrayList.add("View Other Teams In-Trays");
			//intrayList.add("View Intrays Across File Systems");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			//folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			//documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			documentList.add("Allow locking of documents to prevent editing");
			documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			//List<String> notesList = new ArrayList<String>();
			//notesList.add("Delete / Update Notes");
			
			//List<String> archiveList = new ArrayList<String>();
			//archiveList.add("Archive Documents");
			
			List<String> reportsList = new ArrayList<String>();
			reportsList.add("View Reports");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			//List<String> memoList = new ArrayList<String>();
			//memoList.add("Update Another User's Memos (excluding Intray)");
			
			List<String> caseManagementList = new ArrayList<String>();
			caseManagementList.add("QA User (VF)");
			caseManagementList.add("Pre-Assessor (VF)");
			
			List<String> workFlowList = new ArrayList<String>();
			workFlowList.add("Access Enterprise Workflow");
			workFlowList.add("Open Item");
			workFlowList.add("Delete Item");
			workFlowList.add("Pick Item");
			workFlowList.add("Return Item");
			workFlowList.add("Forward Items By User");
			workFlowList.add("Forward Items By Role");
			workFlowList.add("Resubmit Item");
			workFlowList.add("Display Audit Information");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray", intrayList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			//userPermission.put("Notes", notesList);
			//userPermission.put("Archive", archiveList);
			userPermission.put("Reports", reportsList);
			userPermission.put("Cold", coldList);
			//userPermission.put("Memo", memoList);
			userPermission.put("Case Management", caseManagementList);
			userPermission.put("Enterprise Workflow", workFlowList);
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}  
	  }
//		created by Sagar
		public void CreateStandardUser(String standardUserName,String getEnvVariable) throws InterruptedException {
//			standardUserName = "STD"+generateRandomNumber();
			
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
	        navigationMenu.clickOnIcon("User maintenance", "User");
	        navigationMenu.clickOnAddIcon();
	        
	        new AdminUserSection(driver).enterUserId(standardUserName);
	        new AdminUserSection(driver).enterUsername(standardUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("Can Create Tags ");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			//adminList.add("Update Environment");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			//folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			//documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			//documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			//documentList.add("Allow locking of documents to prevent editing");
			//documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			//documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			userPermission.put("Cold", coldList);

			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}
		
	  public void CreateBasicUser(String basicUserName,String getEnvVariable) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
	        navigationMenu.clickOnIcon("User maintenance", "User");
	        navigationMenu.clickOnAddIcon();
	        
	        new AdminUserSection(driver).enterUserId(basicUserName);
	        new AdminUserSection(driver).enterUsername(basicUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			List<String> folderList = new ArrayList<String>();
			folderList.add("Deny Create Folder1");
			folderList.add("Deny Update Folder1");
			folderList.add("Deny Delete Folder1");
			folderList.add("Deny Create Folder2");
			folderList.add("Deny Create Folder2");
			folderList.add("Deny Create Folder2");
			folderList.add("Deny Create Folder3");
			folderList.add("Deny Create Folder3");
			folderList.add("Deny Create Folder3");
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
	  }
	  
	  public boolean GetIntrayAuditCheckboxWhileAddingUser(String auditName) {
		  String tmpAudit = String.format(tmpIntrayAuditTable, auditName);
		  WebElement element = driver.findElement(By.xpath(tmpAudit));
		  sleepFor(2000);
		  boolean status = new VerificationHelper(driver).isElementSelected(element);
		  
		  return status;
	  }
	  
	  public void UnselectAuditActionCheckbox(String auditName) {
		  boolean status = GetIntrayAuditCheckboxWhileAddingUser(auditName);
		  if(status) {
			  String tmpAudit = String.format(tmpIntrayAuditTable, auditName);
			  WebElement element = driver.findElement(By.xpath(tmpAudit));
			  sleepFor(2000);
			  click(element, "Unselecting audit checkbox");
		  }
	  }
	  
	  public boolean GetStatusIntrayAuditCheckboxWhileAddingUser(String auditName) {
		  String tmpAudit = String.format(tmpIntrayAuditTable, auditName);
		  WebElement element = driver.findElement(By.xpath(tmpAudit));
		  sleepFor(2000);
		  boolean status = new VerificationHelper(driver).isElementEnabled(element);
		  
		  return status;
	  }
	//		created by Sagar
		public void reAssignRightsToSuperAdminUser(String superAdminUserName, String getEnvVariable) throws InterruptedException {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(superAdminUserName); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);
	        new AdminUserSection(driver).enterUsername(superAdminUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("System Administration");
			systemAdministrationList.add("Is Supervisor");
			systemAdministrationList.add("Is Administrator");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");
			systemAdministrationList.add("Stamp Administrator");
			systemAdministrationList.add("Is RM Administrator");
			systemAdministrationList.add("Folder Security Administrator");
			systemAdministrationList.add("Can Create Tags ");
			systemAdministrationList.add("Can Edit Tags ");
			systemAdministrationList.add("Can Delete Tags ");
			systemAdministrationList.add("Allow Flexible entity Import ");
			systemAdministrationList.add("Allow All FileSystem On Integration ");
			systemAdministrationList.add("Is System Implementer");
			systemAdministrationList.add("Can Edit File System Tiles");
			systemAdministrationList.add("Can Edit Team Tiles");
			systemAdministrationList.add("Can Edit Role Tiles");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			adminList.add("Update Environment");
			
			List<String> intrayList = new ArrayList<String>();
			intrayList.add("View Other Users In-Trays");
			intrayList.add("View Other Teams In-Trays");
			intrayList.add("View Intrays Across File Systems");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			documentList.add("Allow locking of documents to prevent editing");
			documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			List<String> notesList = new ArrayList<String>();
			notesList.add("Delete / Update Notes");
			
			List<String> archiveList = new ArrayList<String>();
			archiveList.add("Archive Documents");
			
			List<String> reportsList = new ArrayList<String>();
			reportsList.add("View Reports");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			//List<String> memoList = new ArrayList<String>();
			//memoList.add("Update Another User's Memos (excluding Intray)");
			
			List<String> caseManagementList = new ArrayList<String>();
			caseManagementList.add("QA User (VF)");
			caseManagementList.add("Pre-Assessor (VF)");
			
			List<String> workFlowList = new ArrayList<String>();
			workFlowList.add("Access Enterprise Workflow");
			workFlowList.add("Open Item");
			workFlowList.add("Delete Item");
			workFlowList.add("Pick Item");
			workFlowList.add("Return Item");
			workFlowList.add("Forward Items By User");
			workFlowList.add("Forward Items By Role");
			workFlowList.add("Resubmit Item");
			workFlowList.add("Display Audit Information");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray", intrayList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			userPermission.put("Notes", notesList);
			userPermission.put("Archive", archiveList);
			userPermission.put("Reports", reportsList);
			userPermission.put("Cold", coldList);
			//userPermission.put("Memo", memoList);
			userPermission.put("Case Management", caseManagementList);
			userPermission.put("Enterprise Workflow", workFlowList);
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}

//		created by Sagar
		public void reAssignRightsToFileSystemAdminUser(String fileSystemAdminUserName, String getEnvVariable) throws InterruptedException {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(fileSystemAdminUserName); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);

	        new AdminUserSection(driver).enterUsername(fileSystemAdminUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("System Administration");
			systemAdministrationList.add("Is Supervisor");
			systemAdministrationList.add("Is Administrator");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");
			systemAdministrationList.add("Stamp Administrator");
			systemAdministrationList.add("Is RM Administrator");
			systemAdministrationList.add("Folder Security Administrator");
			systemAdministrationList.add("Can Create Tags ");
			systemAdministrationList.add("Can Edit Tags ");
			systemAdministrationList.add("Can Delete Tags ");
			systemAdministrationList.add("Allow Flexible entity Import ");
			systemAdministrationList.add("Allow All FileSystem On Integration ");
			//systemAdministrationList.add("Is System Implementer");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			adminList.add("Update Environment");
			
			List<String> intrayList = new ArrayList<String>();
			intrayList.add("View Other Users In-Trays");
			intrayList.add("View Other Teams In-Trays");
			intrayList.add("View Intrays Across File Systems");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			documentList.add("Allow locking of documents to prevent editing");
			documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			List<String> notesList = new ArrayList<String>();
			notesList.add("Delete / Update Notes");
			
			List<String> archiveList = new ArrayList<String>();
			archiveList.add("Archive Documents");
			
			List<String> reportsList = new ArrayList<String>();
			reportsList.add("View Reports");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			//List<String> memoList = new ArrayList<String>();
			//memoList.add("Update Another User's Memos (excluding Intray)");
			
			List<String> caseManagementList = new ArrayList<String>();
			caseManagementList.add("QA User (VF)");
			caseManagementList.add("Pre-Assessor (VF)");
			
			List<String> workFlowList = new ArrayList<String>();
			workFlowList.add("Access Enterprise Workflow");
			workFlowList.add("Open Item");
			workFlowList.add("Delete Item");
			workFlowList.add("Pick Item");
			workFlowList.add("Return Item");
			workFlowList.add("Forward Items By User");
			workFlowList.add("Forward Items By Role");
			workFlowList.add("Resubmit Item");
			workFlowList.add("Display Audit Information");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray", intrayList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			userPermission.put("Notes", notesList);
			userPermission.put("Archive", archiveList);
			userPermission.put("Reports", reportsList);
			userPermission.put("Cold", coldList);
			//userPermission.put("Memo", memoList);
			userPermission.put("Case Management", caseManagementList);
			userPermission.put("Enterprise Workflow", workFlowList);
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}

//		created by Sagar
		public void reAssignRightsToSupervisorUser(String supervisorUserName, String getEnvVariable) throws InterruptedException {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(supervisorUserName); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);

	        new AdminUserSection(driver).enterUsername(supervisorUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			//systemAdministrationList.add("System Administration");
			systemAdministrationList.add("Is Supervisor");
			//systemAdministrationList.add("Is Administrator");
			systemAdministrationList.add("Campaign Admin");
			systemAdministrationList.add("Alter User/Team Campaigns");
			//systemAdministrationList.add("Stamp Administrator");
			//systemAdministrationList.add("Is RM Administrator");
			//systemAdministrationList.add("Folder Security Administrator");
			systemAdministrationList.add("Can Create Tags ");
			systemAdministrationList.add("Can Edit Tags ");
			systemAdministrationList.add("Can Delete Tags ");
			//systemAdministrationList.add("Allow Flexible entity Import ");
			//systemAdministrationList.add("Allow All FileSystem On Integration ");
			//systemAdministrationList.add("Is System Implementer");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			//adminList.add("Update Environment");
			
			List<String> intrayList = new ArrayList<String>();
			intrayList.add("View Other Users In-Trays");
			intrayList.add("View Other Teams In-Trays");
			//intrayList.add("View Intrays Across File Systems");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			//folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			//documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			documentList.add("Allow locking of documents to prevent editing");
			documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			//List<String> notesList = new ArrayList<String>();
			//notesList.add("Delete / Update Notes");
			
			//List<String> archiveList = new ArrayList<String>();
			//archiveList.add("Archive Documents");
			
			List<String> reportsList = new ArrayList<String>();
			reportsList.add("View Reports");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			//List<String> memoList = new ArrayList<String>();
			//memoList.add("Update Another User's Memos (excluding Intray)");
			
			List<String> caseManagementList = new ArrayList<String>();
			caseManagementList.add("QA User (VF)");
			caseManagementList.add("Pre-Assessor (VF)");
			
			List<String> workFlowList = new ArrayList<String>();
			workFlowList.add("Access Enterprise Workflow");
			workFlowList.add("Open Item");
			workFlowList.add("Delete Item");
			workFlowList.add("Pick Item");
			workFlowList.add("Return Item");
			workFlowList.add("Forward Items By User");
			workFlowList.add("Forward Items By Role");
			workFlowList.add("Resubmit Item");
			workFlowList.add("Display Audit Information");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray", intrayList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			//userPermission.put("Notes", notesList);
			//userPermission.put("Archive", archiveList);
			userPermission.put("Reports", reportsList);
			userPermission.put("Cold", coldList);
			//userPermission.put("Memo", memoList);
			userPermission.put("Case Management", caseManagementList);
			userPermission.put("Enterprise Workflow", workFlowList);
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}
		
//		created by Sagar
		public void reAssignRightsToStandardUser(String standardUserName, String getEnvVariable) throws InterruptedException {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(standardUserName); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);

	    	new AdminUserSection(driver).enterUsername(standardUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			
			List<String> systemAdministrationList = new ArrayList<String>();
			systemAdministrationList.add("Can Create Tags ");
			
			List<String> adminList = new ArrayList<String>();
			adminList.add("DIP");
			//adminList.add("Update Environment");
			
			List<String> intrayProcessingList = new ArrayList<String>();
			intrayProcessingList.add("Take Work from a Shared In-Tray");
			intrayProcessingList.add("Process Work From Another Users In-Tray");
			intrayProcessingList.add("Distribute Work to Other Users In-Trays");
			
			List<String> folderList = new ArrayList<String>();
			//folderList.add("Update Restricted Folder Reference Documents");
			folderList.add("Allow Create Dummy Folder References ");
			folderList.add("Allow Translate Folder References");

			List<String> documentList = new ArrayList<String>();
			//documentList.add("Delete Documents");
			documentList.add("Use Clipbook");
			documentList.add("Create Renditions and apply Redaction Templates");
			documentList.add("Edit Documents (CheckIn and CheckOut)");
			//documentList.add("Create and edit Redaction Templates");
			documentList.add("Scan Documents");
			documentList.add("Batch Index Documents");
			documentList.add("Capture Documents");
			documentList.add("ReIndex Documents");
			//documentList.add("Allow locking of documents to prevent editing");
			//documentList.add("Allow unlocking of documents to allow editing");
			documentList.add("Allow routing of reindexed documents");
			//documentList.add("Alter Protective Marker");
			documentList.add("Can Export Document");
			documentList.add("Can Export Document Metadata");
			documentList.add("Can Edit Metadata");
			documentList.add("Can Print Document");
			documentList.add("Allow Document Linking");
			
			List<String> coldList = new ArrayList<String>();
			coldList.add("COLD");
			coldList.add("Allow Cold Document Redaction");
			
			userPermission.put("System Administration", systemAdministrationList);
			userPermission.put("Admin", adminList);
			userPermission.put("Intray Processing", intrayProcessingList);
			userPermission.put("Folder", folderList);
			userPermission.put("Document", documentList);
			userPermission.put("Cold", coldList);

			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}
	
		
//		created by Sagar
		public void reAssignRightsToBasicUser(String basicUserName, String getEnvVariable) throws InterruptedException {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(basicUserName); 										// User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);

	        new AdminUserSection(driver).enterUsername(basicUserName);
	        sleepFor(1000);
	        new AdminUserSection(driver).enterSecurityLevel("9");
			sleepFor(1000);
			new AdminUserSection(driver).selectSecurityRestrictionType("Restrict",getEnvVariable);
			new JavaScriptHelper(driver).scrollToTop();
			sleepFor(2000);
	        new AdminUserSection(driver).clickOnUserNavTab("Security");
			
			//HashMap<String, String> userPermission = new LinkedHashMap<String, String>();
			
			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			
			List<String> folderList = new ArrayList<String>();
			folderList.add("Deny Create Folder1");
			folderList.add("Deny Update Folder1");
			folderList.add("Deny Delete Folder1");
			folderList.add("Deny Create Folder2");
			folderList.add("Deny Create Folder2");
			folderList.add("Deny Create Folder2");
			folderList.add("Deny Create Folder3");
			folderList.add("Deny Create Folder3");
			folderList.add("Deny Create Folder3");
			
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			sleepFor(1000);
			new JavaScriptHelper(driver).scrollToTop();
			new AdminUserSection(driver).clickOnUserNavTab("General");
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
		}
		
		
		public void CreateSuperAdminUserWithTeamAndRole(String UN, String Role, String RoleName, String TeamId, String TeamName, String getEnvVariable) throws InterruptedException  {
			try {
//				Verify new user is added
				getApplicationUrl();
				navigationMenu.clickOnTab("Administration");
				navigationMenu.clickOnIcon("User maintenance", "User");
				navigationMenu.searchInFilter(UN);
				sleepFor(1000);
				WebElement filteredElement = navigationMenu.getfilteredRowElement("userDataTable");
				String filteredUser = filteredElement.getText();
				boolean userStatus = (UN.equals(filteredUser));  		
				if (userStatus==false) {
					sleepFor(1000);
//					Create user
					CreateSuperAdminUser(UN, getEnvVariable);
					sleepFor(1000);
					
//					logout from the user or change user
					getApplicationUrl();
					new HomePage(driver).clickOnChangeUser();
					sleepFor(2000);
//			 		For new user change password
					new LoginPage(driver).loginWithCredentialsForChangePassword(UN, "P@ssword123");
				}	
				else {
//					reAssignRightsToSuperAdminUser(UN, getEnvVariable);
					sleepFor(2000);
					
//					logout from the user or change user
					new HomePage(driver).clickOnChangeUser();
					sleepFor(2000);
				}
//		 		Login with new user first time and change password
				new LoginPage(driver).loginWithCredentials(UN, ObjectReader.reader.getPassword());

//				verify role is added
				getApplicationUrl();
				sleepFor(1000);
				navigationMenu.clickOnTab("Administration");
				navigationMenu.clickOnIconMenu("Role maintenance", "User");
				navigationMenu.searchInFilter(Role);
				WebElement filteredrole = navigationMenu.getfilteredRowElement("rolesTable");
				String filteredRole = filteredrole.getText();
				boolean roleStatus = (Role.equals(filteredRole));
				if (roleStatus==false) {
					sleepFor(1000);
//					add Role
					new Role(driver).addNewRole(Role, RoleName);
					sleepFor(1000);
				}

//				verify Team is added
				getApplicationUrl();
				sleepFor(1000);
				navigationMenu.clickOnTab("Administration");
				navigationMenu.clickOnIcon("Team maintenance", "User");
				navigationMenu.searchInFilter(TeamId);
				WebElement filteredteam = navigationMenu.getfilteredRowElement("teamsTable");
				String filteredTeam = filteredteam.getText();
				boolean teamStatus = (TeamId.equals(filteredTeam));
				if (teamStatus== false) {
					sleepFor(1000);
//					add Team
					getApplicationUrl();
					new Teams(driver).addTeam(TeamId, TeamName, RoleName, getEnvVariable);	
					sleepFor(1000);
				}		
//				Edit user with role and team
				EditUserWithTeamAndRole(UN, RoleName, TeamName);	//basic user or standard user use this
				sleepFor(1000);

			}catch (Exception e) {log.info("-------------->>> warning : Exception while creating and adding role, team to User " + UN);	}
			finally {
//				logout from the user or change user
				new HomePage(driver).clickOnChangeUser();
				sleepFor(2000);

//				Login with new user first time and change password
				new LoginPage(driver).loginWithCredentials(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			}
		}		
		
		public void removeOrAddSecuirityRightFromUser(String userName, String section, String rightName, String right, String getEnvVariable) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("User maintenance", "User");
			navigationMenu.searchInFilter(userName); 									
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			sleepFor(2000);
			new AdminUserSection(driver).clickOnUserNavTab("Security");
			sleepFor(2000);

			HashMap<String, List<String>> userPermission = new LinkedHashMap<>();
			List<String> L = new ArrayList<String>();
//			L.add("Can Edit Team Tiles");
//			L.add("Can Edit Role Tiles");
			L.add(rightName);
			userPermission.put(section, L);
			
			if (right.equals("add")) {
			new AdminUserSection(driver).addSecurityPermissionForUser(userPermission);
			}
			if (right.equals("remove")) {
			new AdminUserSection(driver).RemoveSecurityRightsFromUser(userPermission);
			}
			sleepFor(1000);
			if(getEnvVariable.equals("Enterprise_Sp1s")) {
				navigationMenu.clickOnIconMenu("Save changes made to user","Actions");
				//navigationMenu.waitForAddIcon();
			}else {
				navigationMenu.clickOnSaveIcon();
				navigationMenu.waitForAddIcon();
			}
			sleepFor(4000);
		}
		

}
	