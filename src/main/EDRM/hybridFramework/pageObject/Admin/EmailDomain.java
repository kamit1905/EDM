package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class EmailDomain extends TestBase{
	
	private WebDriver driver;
	public DocumentToolsPage docTools;
			WaitHelper waitHelper;

			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			LoginPage login;
			HomePage home;
			WindowHelper windowHelper;
			JavaScriptHelper jsHelper;
			Logger log = LoggerHelper.getLogger(EmailDomain.class);
	
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
			
			@FindBy(how = How.XPATH,using = "//input[@id='EmailAddress']")
			public WebElement addEmailAddress; 
			
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
					
					tmpTxtInputId ="//input[@id='%s']",
					tmpDeleteJobRoleBtn = "//td[text()='%s']/ancestor::tr[1]//img",
					tmpCascadeUserChechbox = "//td[text()='%s']/ancestor::tr[1]//input"
					;
			
			
				    //tmpChkSecurityToGetColor="//tr[contains(@data-bind,'visible:')]/td[@data-bind='text: Name' and text()='%s']/ancestor::tr[1]";
					//tmpSecurityStatusXpath="//tr[contains(@data-bind,'visible:')]/td[text()='System Administration']/following-sibling::td/input"
			
			@FindBy(how=How.XPATH,using="//input[@id='Id']")
			public WebElement profileId;
			
			@FindBy(how=How.XPATH,using="//input[@id='Description']")
			public WebElement profileDescription;
			
			@FindBy(how=How.XPATH,using="//label[text()='Security Profile']/ancestor::div[1]//span[@class='validationMessage']")
			public WebElement getSecurityProfileValidation;
			
			@FindBy(how=How.XPATH,using="//button[@id='dialogButton1' and text()='Yes']")
			public WebElement securityYesBtn;

			@FindBy(how=How.XPATH,using="//input[@id='GroupName']")
			public WebElement groupNameTxt;								//created by sagar on 24.07.2023		
			
//        	xpaths on Users Home Screen
        	@FindBy(how=How.XPATH,using="//button[@data-button='Add']")
        	public WebElement addBtn;									//created by sagar on 19.07.2023
        	
//        	xpaths on General tab
        	@FindBy(how=How.XPATH,using="//input[@id='DomainName']")
        	public WebElement addDomainName;									//created by sagar on 19.07.2023
  
        public EmailDomain(WebDriver driver) {
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
		docTools= new DocumentToolsPage(driver);
        }
	 
		public void addAndVerifyEmailDomainInformation(String domainName, String emailAddress) { // created by sagar on
																									// 19.07.2023
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Email Domain Maintenance", "User");
			// waitHelper.waitForElementVisible(docTools.viewerText, 20, 1);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 20, 1);

			List<WebElement> emailDomainTable = driver.findElements(
					By.xpath("//table[@id='emailDomainTable']//*[contains(text(),'" + domainName + "')]"));
			if (emailDomainTable.size() != 0) {
				System.out.println("The domain name " + domainName + " is present. ");
			} else {
				System.out.println("The domain name " + domainName + " is not present. ");
				addBtn.click();
				WaitHelper waitHelper = new WaitHelper(driver);
				waitHelper.waitForElementVisible(addDomainName, 35, 1);
				sendKeys(addDomainName, domainName, "Entering Domain Name " + domainName);
				navigationMenu.clickOnIconUnderAction("Save");
				sleepFor(2000);
			}
		}

		public void deleteEmail(String username) { // created by sagar on 19.07.2023
			navigationMenu.searchInFilter(username); // User_N147 //User_N5341
			navigationMenu.clickOnFilteredRow("userDataTable");
			navigationMenu.clickOnEditIcon();
			navigationMenu.waitForIcon("Save changes made to user");

			sendKeys(new AdminUserSection(driver).addEmailAddress, "",
					"==========Entering Domain email address as blank");
			sleepFor(2000);
			navigationMenu.clickOnSaveIcon();
			new WindowHelper(driver).waitForPopup("Warning");
			String getMsg1 = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg1);
		}

		public void removeEmailDomainInformation(String domainName) { // created by sagar on 25.07.2023
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Email Domain Maintenance", "User");
			// waitHelper.waitForElementVisible(docTools.viewerText, 35, 1);
			waitHelper.waitForElementVisible(navigationMenu.navBarTitleLbl, 35, 1);
			navigationMenu.searchInFilter(domainName);
			sleepFor(1000);
			navigationMenu.clickOnFilteredRow("emailDomainTable");
			sleepFor(2000);
			navigationMenu.clickOnIcon("Delete selected email domain", "Actions");
			String getMsg = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
			System.out.println("get message ====================================" + getMsg);
		}
}
	