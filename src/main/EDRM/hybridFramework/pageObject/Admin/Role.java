package main.EDRM.hybridFramework.pageObject.Admin;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class Role extends TestBase{
	
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
			Logger log = LoggerHelper.getLogger(Role.class);
	
			@FindBy(how=How.XPATH,using="//label[text()='Role']/../input")
			public WebElement roleTxt;
			

			
        public Role(WebDriver driver) {
		this.driver=driver;
		jsHelper = new JavaScriptHelper(driver);
		PageFactory.initElements(driver, this);
		adminuser = new AdminUserSection(driver);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		login = new LoginPage(driver);
		home=new HomePage(driver);
        }
	 
		// created by sagar on 20.07.2023
		public void addNewRole(String Role, String RoleName) throws InterruptedException {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Role maintenance", "User");
			navigationMenu.clickOnAddIcon();
			navigationMenu.waitForIcon("Cancel changes");
			adminuser.enterRole(Role, RoleName);
//			navigationMenu.clickOnSaveIcon();
		}

		// created by sagar on 20.07.2023
		public void deleteRole(String Role) {
			getApplicationUrl();
			navigationMenu.clickOnTab("Administration");
			navigationMenu.clickOnIconMenu("Role maintenance", "User");
			navigationMenu.searchInFilter(Role);
			navigationMenu.clickOnFilteredRow("rolesTable");
			navigationMenu.clickOnIcon("Delete selected role", "Actions");
			new WindowHelper(driver).waitForPopup("Delete");
			String getMsg = new WindowHelper(driver).getPopupMessage();
			new WindowHelper(driver).clickOkOnPopup();
			sleepFor(2000);
		}
	}
	