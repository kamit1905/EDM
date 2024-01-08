package main.EDRM.hybridFramework.pageObject;

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


public class PublicAccess extends TestBase {

	private WebDriver driver;
	WaitHelper waitHelper;
	AlertHelper alertHelper;
	NavigationMenu navigationMenu;
	DropdownHelper dropdownHelper;
	VerificationHelper verificationHelper;
	WindowHelper windowHelper;
	public DocumentToolsPage docTools;
	Logger log = LoggerHelper.getLogger(PublicAccess.class);
	
	
	@FindAll({
		@FindBy(how = How.XPATH,using = "//button[@data-original-title='Rendition File Types']"),
		@FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Rendition File Types']")
	})
	public WebElement renditionFileTypeBtn;
	
	@FindBy(how = How.XPATH,using = "//input[@id='Extension']")
	public WebElement extensionInput;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='RenditionCreator']")
	public WebElement ddRenditionCreator;
	
	@FindBy(how = How.XPATH,using = "//table[@id='FileTypeConverterConfigurationTable']/tbody/tr[1]/td[1]")
	public WebElement lblFilteredFileTypeConvertor;
	
	@FindBy(how = How.XPATH,using = "//span[text()='ViewDocument']")
	public WebElement viewDocumentIcon;
	
	
	private String fnlViewXpath = "//td[contains(text(),'%s')]/ancestor::tr[1]//i[@data-bs-original-title='View Document']";
	
	private String fnlViewXpathBefore540 = "//td[contains(text(),'%s')]/ancestor::tr[1]//i[@data-bs-original-title='View Document']";
	
	
	public PublicAccess(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		docTools = new DocumentToolsPage(driver);
	}
	
	
	public void ClickOnRenditionFileType() {
		new WaitHelper(driver).waitForElement(renditionFileTypeBtn, 10);
		click(renditionFileTypeBtn, "Clicking on renditionFileTypeButton");
	}
	
	public void EnterExtensionName(String extensionName) {
		sendKeys(extensionInput, extensionName, "Inputing "+extensionName);
	}
	
	public void SelectRenditionCreator(String renditionName) {
		new WaitHelper(driver).waitForElement(ddRenditionCreator, 10);
		new DropdownHelper(driver).selectFromAutocompleDD(renditionName, ddRenditionCreator);
	}
	
	public void ClickOnFilteredFileTypeConvertor() {
		click(lblFilteredFileTypeConvertor, "Clicking on filtered row of file type convertor");
	}
	
	public void ViewDocumentOnPublicAccess(String docRef,String environment) {
		    String getPublicAccessURL = ObjectReader.reader.getPublicAccessUrl();
			sleepFor(2000);			
			new JavaScriptHelper(driver).openNewTabUsingJavascriptExecutor();
			new WindowHelper(driver).switchToNewlyOpenedTab();
			driver.get(getPublicAccessURL);
			
			new WaitHelper(driver).waitForElement(docTools.filtexTxtForPublicAccess, 10);
			sleepFor(1000);
			sendKeys(docTools.filtexTxtForPublicAccess, docRef, "Entering account reference");
			
			if(environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531")) {
				String fnlViewXpathString = String.format(fnlViewXpathBefore540,docRef);
				WebElement viewIcon = driver.findElement(By.xpath(fnlViewXpathString));
				click(viewIcon, fnlViewXpathString);
			}else {
				String fnlViewXpathString = String.format(fnlViewXpath,docRef);
				WebElement viewIcon = driver.findElement(By.xpath(fnlViewXpathString));
				click(viewIcon, fnlViewXpathString);
			}
			
			new WindowHelper(driver).switchToNewlyOpenedTab();
			sleepFor(1000);
			//new WaitHelper(driver).waitForElement(viewDocumentIcon, 10);
			driver.close();
			new WindowHelper(driver).switchToNewlyOpenedTab();
			sleepFor(1000);
			driver.close();
			new WindowHelper(driver).switchToNewlyOpenedTab();
	}

}
