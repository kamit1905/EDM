package main.EDRM.hybridFramework.pageObject.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;															
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;

public class ToolkitSystemSection extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			Logger log = LoggerHelper.getLogger(ToolkitSystemSection.class);
	
			@FindBy(how=How.XPATH,using="//input[@id='Name']")
			public WebElement nameTxt;
			

			@FindBy(how=How.XPATH,using="//table[@id='configSettingsDataTable']/tbody/tr/td[2]")
			public WebElement lblFirstRowName;
			
			
			@FindBy(how=How.XPATH,using="//table[@id='configSettingsDataTable']/tbody/tr/td[3]/input")
			public WebElement txtFirstRowValue;
			
			
			
			
			public ArrayList<String> expectedActiveDocTypes = new ArrayList<String>(
					Arrays.asList("Default - General Default Document Type",
							"doc2 - document2","ScanDoc - Generic Scanned Document",
							"team1 - teamOne","tp - timepass","user1 - u1"));
			ArrayList<String> inActiveDocTypes = new ArrayList<String>();
			
			
			
			
	public ToolkitSystemSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
	}
	
	/**
	 * Check config value and change it if its not matching config value
	 * @param configKey
	 * @param configValue
	 * @return
	 */
	public boolean checkConfigValue(String configKey, String configValue) {
		navigationMenu.clickOnTab("ToolKit");
		navigationMenu.clickOnIcon("Configuration settings", "System");
		windowHelper.waitForPopup("Configuration Settings");
		windowHelper.clickOkOnPopup();
		navigationMenu.searchInFilter(configKey);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String configKeyName = getFirstRowName();
		String configValueName = getFirstRowValue();
		if(configKey.equals(configKeyName)) {
			if(configValue.equals(configValueName)) {
				navigationMenu.clickOnCancelIcon();
				waitHelper.waitForElementVisible(new LoginPage(driver).welcomeTitleText, 30, 1);
				return true;
			}
			else {
				enterFirstRowValue(configValue);
				new ActionHelper(driver).pressTab();
				sleepFor(2000);						
				navigationMenu.clickOnIcon("Save changes made to configurations", "Actions");
				waitHelper.waitForElementVisible(new LoginPage(driver).welcomeTitleText, 30, 1);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Get first row name
	 * @return first row name
	 */
	public String getFirstRowName() {
		waitHelper.waitForElementClickable(driver, lblFirstRowName, 20);
		return lblFirstRowName.getText();
	}
	
	/**
	 * Get first row value
	 * @return
	 */
	public String getFirstRowValue() {
		return txtFirstRowValue.getAttribute("value");
	}
	
	/**
	 * Enter first row value
	 * @param configValue
	 */
	public void enterFirstRowValue(String configValue) {
		click(lblFirstRowName, "Clicking on first row");
		sleepFor(1000);
		sendKeys(txtFirstRowValue, configValue, "Entering config value as"+configValue);
		//click(navigationMenu.navBarTitleLbl,"Clicking anywhere else to enable save");
		 sleepFor(1000);
		 txtFirstRowValue.sendKeys(Keys.ENTER);
	}

}
