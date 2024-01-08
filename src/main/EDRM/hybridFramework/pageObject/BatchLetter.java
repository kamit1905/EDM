package main.EDRM.hybridFramework.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;

public class BatchLetter extends TestBase {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	private Logger log = LoggerHelper.getLogger(BatchLetter.class);
	private NavigationMenu navigationMenu;
	
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='OfficeTemplate']")
	public WebElement officeTemplateDD;
	
	@FindBy(how = How.XPATH,using = "//p[text()='Batch Letter Creation']")
	public WebElement batchLetterHeader;
	
	@FindBy(how = How.XPATH,using = "//p[@id='dialogContent']//label[text()='Number of successes: ']/ancestor::div[1]/following-sibling::div//label")
	public WebElement getSuccessCountOnBatchLetter;

	
	
	public void navigateToBatchLetter() throws InterruptedException {
		 navigationMenu.clickOnCaptureTab();
		 navigationMenu.clickOnIcon("Produce letters in batch", "Capture");
		  Thread.sleep(1000);
		  //waitHelper.waitForElementVisible(navigationMenu.filterTxt, 20, 1);
		  new WaitHelper(driver).waitForElement(batchLetterHeader, 10);
	}
	
	public BatchLetter(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
	}
	
	

}
