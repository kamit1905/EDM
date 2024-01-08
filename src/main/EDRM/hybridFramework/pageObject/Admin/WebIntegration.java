package main.EDRM.hybridFramework.pageObject.Admin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
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

public class WebIntegration extends TestBase {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	AlertHelper alertHelper;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	ActionHelper actionHelper;
	JavaScriptHelper jsHelper;
	Logger log = LoggerHelper.getLogger(WebIntegration.class);
	
	@FindBy(how = How.XPATH,using = "//input[@id='Name']")
	public WebElement inputField;
	
	
	@FindBy(how=How.XPATH,using="//input[@name='DecoratedLink']")
	public WebElement decoratedFieldLink;

	
	@FindBy(how=How.XPATH,using="//input[@name='AvailableInAllFileSystems']")
	public WebElement availableInAllFileSystems;
	
	
	@FindBy(how=How.XPATH,using="//input[@name='LaunchIncurrentWindow']")
	public WebElement launchIncurrentWindow;
	
	
	@FindBy(how=How.XPATH,using="//input[@name='DocumentLink']")
	public WebElement documentLink;
	
	
	@FindBy(how=How.XPATH,using="//input[@class='gLFyf gsfi']")
	public WebElement getValueFromSearchBox;
	
	@FindBy(how=How.XPATH,using="//table[@id='webIntegrationOptionsDatatable']/tbody/tr[1]/td[1]")
	public WebElement lblFilteredUserRow;
	
	
	
	
	public WebIntegration(WebDriver driver) {
		this.driver=driver;
		jsHelper = new JavaScriptHelper(driver);
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		home=new HomePage(driver);
	}
	
	public void enterWebIntegrationName(String webInteName) {
		sendKeys(inputField, webInteName, "Entering WebIntegration Name "+webInteName);
	}
	
	public void enterDecoratedLink(String decoratedLink) {
		sendKeys(decoratedFieldLink, decoratedLink, "Entering WebIntegration Name "+decoratedLink);
	}
	
	
	public void selectAvaliableInAllFileSystem(boolean status) {
		//boolean actualValue=new VerificationHelper(driver).isElementSelected(availableInAllFileSystems);
		
		if(status) {
			click(availableInAllFileSystems, "Selecting avaliable all file system checkbox");
		}
	}
	
	public void selectLaunchInCurrentWindow(boolean status) {
		if(status) {
			click(decoratedFieldLink, "Selecting decorated Link checkbox");
		}
	}
	
	public void selectDocumentLink(boolean status) {
		if(status) {
			click(documentLink, "Selecting document Link checkbox");
		}
	}
	
	public String getValueFromNewelyOpenedTab() {
		waitHelper.waitForElement(getValueFromSearchBox, 10);
		String getValues=getValueFromSearchBox.getAttribute("value");
		return getValues;
	}
	
	/**
	   * Click on filtered row
	   */
	  public void clickOnFilteredWebIntegration() {
		  click(lblFilteredUserRow, "clicking on filtered user row");
	  }
	
}
