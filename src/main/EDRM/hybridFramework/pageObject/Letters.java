package main.EDRM.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.calender.CalenderHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;

public class Letters extends TestBase {
	
	
	private WebDriver driver;
	WaitHelper waitHelper;
	private Logger log = LoggerHelper.getLogger(Letters.class);
	
	@FindBy(how = How.XPATH,using = "//p[text()='Letter Templates ']")
	public WebElement letterTemplatePageHeader;
	
	@FindBy(how = How.XPATH,using = "//button[@id='pickfiles']")
	public WebElement uploadFileBtn;
	
	@FindBy(how = How.XPATH,using = "//p[text()='Letter Production']")
	public WebElement adhocLetterHeader;
	
	
	
	public Letters(WebDriver driver) {
		this.driver = driver;
		log.debug("DropDown helper : " + this.driver.hashCode());
		waitHelper = new WaitHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void GenerateLetter(String tempName,String filepath,String filename) {
		filepath= filepath == null ? ObjectReader.reader.getTestDocFolderPath(): filepath;	
		filename= filename == null ? "Default_1.docm": filename; 

		new WaitHelper(driver).waitForElement(new Letters(driver).letterTemplatePageHeader,10);
		new NavigationMenu(driver).searchInFilter(tempName);
		new CapturePage(driver).clickOnFirstItemOfIntray();
		new NavigationMenu(driver).clickOnIcon("Create Letter", "Actions");
		new WindowHelper(driver).waitForPopup("Create Letter");
		new WindowHelper(driver).clickOnModalFooterButton("Save");
		new WindowHelper(driver).waitForModalDialog("Update Document");
		sleepFor(1000);
		click(uploadFileBtn, "Clicking on upload file button");
		new CapturePage(driver).uploadFileAngGetFileName(filepath,filename);
		//new DocumentToolsPage(driver).clickOnFileUploadOnUpdateDocument();
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		sleepFor(5000);
	}

}
