package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.ArrayList;
import java.util.List;					  
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;											 
import main.EDRM.hybridFramework.TestBase.TestBase;
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

public class Teams extends TestBase{
	
	private WebDriver driver;
			AdminUserSection adminuser;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			LoginPage login;
			HomePage home;
			WindowHelper windowHelper;
			JavaScriptHelper jsHelper;
			Logger log = LoggerHelper.getLogger(Teams.class);
			int count; 
			
			@FindBy(how = How.XPATH,using = "//input[@id='EmailAddress']")
			public WebElement addEmailAddress; 
			@FindBy(how=How.XPATH,using="//button[@data-id='users']")
			public WebElement teamUsersdd;
			
			@FindBy(how=How.XPATH,using="//a[@data-title='Add new team users']")
			public WebElement btnTeamUsersadd;

        public Teams(WebDriver driver) {
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
		adminuser=new AdminUserSection (driver);

        }
		  
		// created by sagar on 20.07.2023
		public void addTeam(String TeamId, String TeamName, String RoleName,
				String getEnvVariable) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Team maintenance", "User");
			adminuser.clickOnAddIconForTeam();
			adminuser.addTeamId(TeamId);
			adminuser.addTeamName(TeamName);
			adminuser.selectUsersRole(RoleName, getEnvVariable);
			sendKeys(addEmailAddress, ObjectReader.reader.getEmailId(),
					"Entering email " + ObjectReader.reader.getEmailId());
			adminuser.clickOnMailStatusesTab();
				ArrayList<String> mailStatuses = new ArrayList<String>();
				mailStatuses.add("Complete");
				mailStatuses.add("Pending");
				mailStatuses.add("Verified");
				mailStatuses.add("Matched");
				mailStatuses.add("New");
				mailStatuses.add("New Template");
				mailStatuses.add("In Progress");
				adminuser.addMailStatuses(mailStatuses, getEnvVariable);
			navigationMenu.clickOnIconMenu("Save changes made to team", "Actions");
			navigationMenu.waitForAddIcon();

		}
				
		public void addMailStatusToTeam(String TeamId, String mailStatus, String getEnvVariable) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Team maintenance", "User");
			navigationMenu.searchInFilter(TeamId);
			navigationMenu.clickOnFilteredRow("teamsTable");
			navigationMenu.clickOnIcon("Edit selected team ", "Actions");
			
			adminuser.clickOnMailStatusesTab();
				ArrayList<String> mailStatuses = new ArrayList<String>();
				mailStatuses.add(mailStatus);
				adminuser.addMailStatuses(mailStatuses, getEnvVariable);
			navigationMenu.clickOnIconMenu("Save changes made to team", "Actions");
			navigationMenu.waitForAddIcon();	
		}

		// created by sagar on 20.07.2023
		public void editTeam(String TeamId, String EditedTeamName) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Team maintenance", "User");
			navigationMenu.searchInFilter(TeamId);
			navigationMenu.clickOnFilteredRow("teamsTable");
			navigationMenu.clickOnIcon("Edit selected team ", "Actions");
			adminuser.addTeamName(EditedTeamName);
			navigationMenu.clickOnIconMenu("Save changes made to team", "Actions");
			sleepFor(2000);
		}

		// created by sagar on 20.07.2023
		public void deleteTeam(String TeamId) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIcon("Team maintenance", "User");
			navigationMenu.searchInFilter(TeamId);
			navigationMenu.clickOnFilteredRow("teamsTable");
			navigationMenu.clickOnIcon("Delete selected team", "Teams");
			new WindowHelper(driver).waitForPopup("Delete");
			String getMsg = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
		}
		
		public void verifyUserAddedToTeamIfNotAddUserToTeam (String UserID, String getEnvVariable) {
	        WebElement tableRowsInTable = driver.findElement(By.xpath("//div[@id='tabs-3']//tbody"));
	        List<WebElement>TotalRowsList = tableRowsInTable.findElements(By.tagName("tr"));	        
       	 	int count1 = 0;
	        for (int j=1; j<TotalRowsList.size()+1; j++) {
	        	count = 0;
	        	WebElement RowContent = driver.findElement(By.xpath("//div[@id='tabs-3']//table//tbody//tr["+j+"]"));
	        	String actTableContent = RowContent.getText();
	        	boolean isElementPresent = (UserID.equals(actTableContent));
	        	 if(isElementPresent == true) {
		        		 count1 = count+1;
			        	 if (count != 0) {
					            System.out.println("-------- " + UserID + " user is already added to team----");
			        	 }
			     }
	        }
	        	 if(count1 == 0) {
		            addUser(UserID+" (No Team)", getEnvVariable);
		         }
		}
		
	public void addUser(String UserID, String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
				  click(teamUsersdd, "Clicking on team users drop down");
			  		dropdownHelper.selectFromAutocompleDDWithoutInput(UserID, teamUsersdd,getEnvVariable);
			  		click(btnTeamUsersadd, "Clicking on mail status add");
		  	}else if(getEnvVariable.equals("Enterprise_R550")) {
		  		dropdownHelper.selectFromAutocompleDDWithoutInput(UserID, teamUsersdd);
		  		click(btnTeamUsersadd, "Clicking on mail status add");
		  	}
		else {
			  	click(teamUsersdd, "Clicking on team users drop down");
		  		dropdownHelper.selectFromAutocompleDDWithoutInput(UserID, teamUsersdd,getEnvVariable);
		  		click(btnTeamUsersadd, "Clicking on mail status add");
 		  	}
	}

}
	