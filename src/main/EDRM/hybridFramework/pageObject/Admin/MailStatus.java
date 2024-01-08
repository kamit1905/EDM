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
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class MailStatus extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			Logger log = LoggerHelper.getLogger(MailStatus.class);
	
		
			@FindBy(how=How.XPATH,using="//input[@id='StatusCode']")
			public WebElement statusCodeTxt;									//created by sagar on 20.07.2023
			
			@FindBy(how=How.XPATH,using="//input[@id='Description']")
			public WebElement statusDescriptionTxt;							//created by sagar on 20.07.2023
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@data-id='TeamList']"),
				@FindBy(how=How.XPATH,using="//div[@class='dropdown bootstrap-select form-control dropup']"),
				@FindBy(how=How.XPATH,using="//table[@class='dataTableGrid']//button[@role='combobox']")
			})
			public WebElement availableTeamDD;
			
//			@FindBy(how=How.XPATH,using="//table[@class='dataTableGrid']//button[@role='combobox']")
//			public WebElement availableTeamDD;								//created by sagar on 20.07.2023		
			
			@FindBy(how=How.XPATH,using="//table[@class='dataTableGrid']//input[@type='search']")
			public WebElement availableTeamTxt;		
			
			@FindAll({
//				@FindBy(how=How.XPATH,using="//div[@class='dropdown bootstrap-select disabled form-control']"),
				@FindBy(how=How.XPATH,using="//select[@id='ChangesTo']")		
			})	
			public WebElement changesToDD;
		
			@FindBy(how=How.XPATH,using="//input[@id='IsTimedChange']")
			public WebElement isTimedChangedChkbx;		
			
			@FindBy(how=How.XPATH,using="//input[@id='IsOutstandingItem']")
			public WebElement IsOutstandingItemChkbx;		

			@FindBy(how=How.XPATH,using="//input[@id='RouteOn']")
			public WebElement RouteOnChkbx;		

			@FindBy(how=How.XPATH,using="//input[@id='pendingPeriod']")
			public WebElement PendingPeriodChkbx;		

			//mail status xpaths
//			@FindBy(how=How.XPATH,using="//table[@id='mailStatusDataTable']//td")
//			public WebElement mailStatusDataTableEmpty;											//created by sagar on 24.07.2023

			public String tmpLblTextbox  = "//label[text()='%s']/..//input",
					campaignTableId = "priorityManagerTable",
					tmpIconWeightedValues = "//button[@data-original-title='%s']";
			
			public String tmpNECDMIconWeightedValues = "//button[@data-bs-original-title='%s']";
			
	public MailStatus(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		actionHelper=new ActionHelper(driver);
	}
	
	//created by sagar on 20.07.2023
	public void selectAndAddMailStatus(String mailStatus, String statusCode, String statusDescription, String TeamName, String getEnvVariable)
	{
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
		if (!status) {
			sleepFor(1000);
			refreshPage();
			sleepFor(1000);
		}				
		navigationMenu.clickOnIcon("Add a new mail status", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
		sendKeys(statusCodeTxt,statusCode,"Entering status code as "+statusCode);
		sendKeys(statusDescriptionTxt,statusDescription,"Entering status description as "+statusDescription);
		sleepFor(1000);
		click(IsOutstandingItemChkbx,"Clicking on Is Outstanting Item Checkbox");
		sleepFor(1000);
		click(RouteOnChkbx,"Clicking on Is Outstanting Item Checkbox");
		sleepFor(1000);

		new JavaScriptHelper(driver).scrollToBottom();
		availableTeamDD(TeamName, getEnvVariable);
		new JavaScriptHelper(driver).scrollToTop();
		navigationMenu.clickOnIcon("Save changes made to mail status", "Actions");
		navigationMenu.waitForIcon("Add a new mail status");
		//		navigationMenu.waitForIcon("Add a new mail status");
		sleepFor(4000);
	}

	
	//created by sagar on 20.07.2023
	public void availableTeamDD(String teamName,String getEnvVariable) {
		teamName = teamName == null ? ObjectReader.reader.getLoggedInUsersTeam() : teamName;
		
		if(getEnvVariable.equals("Enterprise_R551")) {
			try {
				Thread.sleep(3000);
				new DropdownHelper(driver).selectFromAutocompleDD(teamName, availableTeamTxt);
																								 
				new JavaScriptHelper(driver).scrollToTop();
				sleepFor(2000);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}else {
			try {
				Thread.sleep(2000);
				click(availableTeamDD, "Cliking on routing To drop down");
				Thread.sleep(2000);
				new DropdownHelper(driver).selectFromAutocompleDD1(teamName, availableTeamTxt,getEnvVariable);
				Thread.sleep(2000);
				new JavaScriptHelper(driver).scrollToTop();
				   
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

	}	
	

	//created by sagar on 21.07.2023
	public void editMailStatus(String mailStatus, String statusCode, String statusDescriptionEdited, String teamName, String getEnvVariable)
	{
		//Edit mail status		
		getApplicationUrl();
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
//		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);
//		if (!status) {
//			sleepFor(1000);
//			refreshPage();
//			sleepFor(1000);
//		}
		navigationMenu.searchInFilter(statusCode);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);			
		navigationMenu.clickOnIcon("Edit selected mail status ", "Actions");
		navigationMenu.waitForIcon("Cancel changes");
		sendKeys(statusDescriptionTxt,statusDescriptionEdited,"Entering status description as "+statusDescriptionEdited);
		new JavaScriptHelper(driver).scrollToTop();
		sleepFor(1000);
		navigationMenu.clickOnIcon("Save changes made to mail status", "Actions");
		navigationMenu.waitForIcon("Add a new mail status");	
	}		
	
	//created by sagar on 21.07.2023
	public void deleteMailStatus(String mailStatus, String statusCode)
	{
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Select Mail Status category to admin", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIconMenu(mailStatus);
		navigationMenu.searchInFilter(statusCode);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailStatusDataTable");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Delete selected mail status", "Actions");
		new WindowHelper(driver).waitForPopup("Delete");
		String getMsg = new WindowHelper(driver).getPopupMessage();
		new WindowHelper(driver).clickOkOnPopup();
		sleepFor(2000);	
		System.out.println("get message ===================================="+getMsg);
	}
	


}
