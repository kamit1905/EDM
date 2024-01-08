package main.EDRM.hybridFramework.helper.window;

import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;

public class WindowHelper extends TestBase{

	
	private WebDriver driver;
	ActionHelper actionHelper;
	WaitHelper waitHelper;
	
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);
	
	//Popup box 
	    @FindBy(how=How.ID,using="dialogTitle")
		public WebElement dialogTitleId;
		
		@FindBy(how=How.ID,using="dialogMessage")
		public WebElement dialogMessageId;		
		
		@FindBy(how=How.XPATH,using="//button[@id='dialogButton1']")
		public WebElement dialogOkBtn;
		
		@FindBy(how=How.XPATH,using="//button[@id='intrayDetailsOk']")
		public WebElement dialogIntrayDetailsOkBtn;
		
		@FindBy(how=How.XPATH,using="(//button[text()='Cancel'])[last()]")
		public WebElement cancelBtn;
		
		@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//div[@class='modal-footer']//button[text()='Ok']")
		public WebElement btnModalOk;
		
		@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//div[@class='modal-footer']")
		public WebElement divModalFooter;
		@FindBy(how=How.XPATH, using="//button[@id='save-statuses-btn']")
		public WebElement saveDefaultMailStatusYesBtn;
		
		@FindBy(how=How.XPATH, using="//button[@id='cancel-save-statuses-btn']")
		public WebElement CancelDefaultMailStatusYesBtn;
		//Use modal fade in for 522 old release and modal fade show for new relase 531
		public String tmpModalTitle = "//div[contains(@class,'modal')]//div[@class='modal-header']//h4[text()='%s']",    //using contains because in some cases it shows modal fade show and modal show
				tmpModalFormTitle = "//div[@class='modal fade form show' or @class='modal fade form in']//div[@class='modal-header']//h4[text()='%s']",
				tmpPopupTitle="//div[@class='modal fade show' or @class='modal fade in']//div[@class='modal-header']/h3[text()='%s']",
				tmpModalFooterButtons = "//div[@class='modal fade show' or @class='modal fade in']//div[@class='modal-footer']//button[text()='%s']",
				tmpModalFormFooterButtons = "//div[@class='modal fade form show' or @class='modal fade form in']//div[@class='modal-footer']//button[text()='%s']",
				tmpPopupTitleOkButton = "//h3[text()='%s']/../..//button[@id='dialogButton1']",
				tmpPopupTitleCancelButton = "//h3[text()='%s']/../..//button[@id='dialogButton2']",
						tmpPopupTitleCancelButton1 = "//h3[text()='%s']/../..//button[@id='dialogButton3']",
						tmpModalTitle1 = "//div[contains(@class,'modal')]//div[@class='modal-header']//h4/span[text()='%s']",    //using contains because in some cases it shows modal fade show and modal show
						tmpModalTitle2 = "//div[contains(@class,'modal')]//div[@class='modal-header']//h4[text()='%s']";
				
		public String tmpModalFooterButtonsForLinking = "//div[@class='modal show' or @class='modal fade in']//div[@class='modal-footer']//button[text()='%s']";
		
		/*
		 * Following variables are used for below R530 and for R530
		 */
		
//		//Use modal fade in for 522 old release and modal fade show for new relase 531
//		public String tmpModalTitleOther = "//div[@class='modal fade in']//div[@class='modal-header']//h4[text()='%s']",
//				tmpModalFormTitleOther = "//div[@class='modal fade form in']//div[@class='modal-header']//h4[text()='%s']",
//				tmpPopupTitleOther = "//div[@class='modal fade in']//div[@class='modal-header']/h3[text()='%s']",
//				tmpModalFooterButtonsOther = "//div[@class='modal fade in']//div[@class='modal-footer']//button[text()='%s']",
//				tmpModalFormFooterButtonsOther = "//div[@class='modal fade form in']//div[@class='modal-footer']//button[text()='%s']",
//				tmpPopupTitleOkButtonOther = "//h3[text()='%s']/../..//button[@id='dialogButton1']";
		
	public WindowHelper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper= new WaitHelper(driver);
	}

	/**
	 * This method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
		
	}

	/**
	 * This method will switch to child window based on index
	 * @param index
	 */
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();
		int i = 0;
		for (String window : windows) {
			if (i == 0) {
				driver.switchTo().window(mainwindow);
			} 
			else if(i== index) {
				log.info("switched to : "+index + " window");
				driver.switchTo().window(window);
			}
			else {
				i++;
			}
		}
	}

	public boolean isPopupDisplayed() {
		//Below code with wait since 
		boolean isPopupShown = false;
		try {
		waitHelper.waitForElement(dialogTitleId,10);
		isPopupShown = true;
		return isPopupShown;
		}
		catch(Exception e) {
			isPopupShown=false;
		}
		
	return isPopupShown;
	}
	
	/**
	 * This method will close all tabbed window and 
	 * switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		ArrayList<String> allWindows  = new ArrayList<String> (driver.getWindowHandles());
		String mainwindow = allWindows.get(0);

		for (String window : allWindows) {
			if (!window.equalsIgnoreCase(mainwindow)) {
			  try{
			  driver.switchTo().window(window);
			  driver.close();
			  }
			  catch (Exception e) {
                log.info("Exception while closing the window "+e);
              }
			  }
		}
		log.info("switched to main window");
		driver.switchTo().window(mainwindow);
	}
	
	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack(){
		log.info("navigating back");
		driver.navigate().back();
	}
	
	/**
	 * This method will do browser forward navigation
	 */
	public void navigateForward(){
		log.info("navigating forward");
		driver.navigate().forward();
	}
	
	/**
	 * 
	 * @return String containing title of the popup
	 */
	public String getPopupTitle() {
		return dialogTitleId.getText();
	}
	
	/**
	 * 
	 * @return String having the message content of the popup 
	 */
	public String getPopupMessage() {
		waitHelper.waitForElementClickable(driver, dialogMessageId, 20);
		return dialogMessageId.getText();
	}
	
	/**
	 * Get popup message and click on ok
	 * @return popup message present 
	 */
	public String getPopupMessageClickOk(String popupText) {
		waitForPopup(popupText);
		String popMsg = getPopupMessage();
		clickOkOnPopup(popupText);
		return popMsg;
	}
	
	public void clickOkOnPopup() {
		try {
		waitHelper.waitForElementClickable(driver, dialogOkBtn, 20);
		log.info("Clicking ok on popup");
		new ActionHelper(driver).moveToElementAndClick(dialogOkBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking ok on popup");
		}
	}
	public void clickSaveOnDefaultMailStatusPopup() {
		try {
		waitHelper.waitForElementClickable(driver, saveDefaultMailStatusYesBtn, 20);
		log.info("Clicking Yes on Save default mail status popup");
		new ActionHelper(driver).moveToElementAndClick(saveDefaultMailStatusYesBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking yes on save default mail status popup");
		}
	}	

	public void clickCancelOnDefaultMailStatusPopup() {
		try {
		waitHelper.waitForElementClickable(driver, CancelDefaultMailStatusYesBtn, 20);
		log.info("Clicking Cancel on Save default mail status popup");
		new ActionHelper(driver).moveToElementAndClick(CancelDefaultMailStatusYesBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking Cancel on save default mail status popup");
		}
	}
	
	public void clickIntrayDetailsOkOnPopup() {
		try {
		waitHelper.waitForElementClickable(driver, dialogIntrayDetailsOkBtn, 20);
		log.info("Clicking ok on popup");
		new ActionHelper(driver).moveToElementAndClick(dialogIntrayDetailsOkBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking ok on popup");
		}
		}	
	
	public void clickOkOnPopup(String popupTitle) {
		try {
		WebElement popupOkBtn = driver.findElement(By.xpath(String.format(tmpPopupTitleOkButton,popupTitle)));
		waitHelper.waitForElementClickable(driver, popupOkBtn, 20);
		log.info("Clicking ok on popup "+popupTitle);
		new ActionHelper(driver).moveToElementAndClick(popupOkBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking ok on popup");
		}
		}
	
	public void clickCancelOnPopup(String popupTitle) {
		try {
		WebElement popupCancelBtn = driver.findElement(By.xpath(String.format(tmpPopupTitleCancelButton,popupTitle)));
		waitHelper.waitForElementClickable(driver, popupCancelBtn, 20);
		log.info("Clicking Cancel on popup "+popupTitle);
		new ActionHelper(driver).moveToElementAndClick(popupCancelBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking cancel on popup");
		}
	}

	public void clickCancelOnPopup1(String popupTitle) {
		try {
		WebElement popupCancelBtn = driver.findElement(By.xpath(String.format(tmpPopupTitleCancelButton1,popupTitle)));
		waitHelper.waitForElementClickable(driver, popupCancelBtn, 20);
		log.info("Clicking Cancel on popup "+popupTitle);
		new ActionHelper(driver).moveToElementAndClick(popupCancelBtn);
		}
		catch (Exception e) {
			log.info("Exception while clicking cancel on popup");
		}
	}


	/**
	 * Wait for popup with h3 title
	 * @param title
	 */
	public void waitForPopup(String title) {
			By modalTitleLocator = By.xpath(String.format(tmpPopupTitle, title));
			waitHelper.waitForElementVisible(modalTitleLocator, 35, 1);
	}
	
	
	/**
	 * Switches to new open tab
	 */
	public void switchToNewlyOpenedTab() {
		  ArrayList<String> multiTabs = new ArrayList<String> (driver.getWindowHandles());
		  System.out.println("Multitabs size is"+multiTabs.size());
		  driver.switchTo().window(multiTabs.get(multiTabs.size()-1));
	}
	
	/**
	 * Closes current tab & switches to first tab
	 */
	public void closeCurrentAndSwithcToFirstTab() {
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	public void clickOnCancelButton() {
		click(cancelBtn,"Clicking on cancel of dialog window");
	}
	
	/**
	 * Modal dialog with text in h4
	 * @param modalTitle
	 */
	public void waitForModalDialog(String modalTitle) {
			By modalTitleLocator = By.xpath(String.format(tmpModalTitle, modalTitle));
			waitHelper.waitForElementVisible(modalTitleLocator, 20, 1);	
	}
	
	
	/**
	 * Modal dialog with text in h4
	 * @param modalTitle
	 */
	public void waitForModalDialogToBeInvisble(WebDriver driver,String modalTitle, int modTimeout) {
			WebElement modalTitleLocator = driver.findElement(By.xpath(String.format(tmpModalTitle, modalTitle)));
			waitHelper.waitForElementInvisible(driver,modalTitleLocator, modTimeout);
	}
	
	/**
	 * Modal dialog with text in h4 and class modalfadeform
	 * @param modalTitle
	 */
//	public void waitForModalFormDialog(String modalTitle) {
//			By modalFormTitleLocator = By.xpath(String.format(tmpModalFormTitle, modalTitle));
//			waitHelper.waitForElementVisible(modalFormTitleLocator, 20, 1);
//	}

		
	public void clickOnModalFooterButton(String buttonText) {
			WebElement btnEle = driver.findElement(By.xpath(String.format(tmpModalFooterButtons, buttonText)));
			//click(btnEle, "Clicking on modal ok button");
			new JavaScriptHelper(driver).scrollToElement(btnEle);
			sleepFor(2000);
			new JavaScriptHelper(driver).clickElement(btnEle);
	}
	
	//Adding because footer button is not working there is slight change in locator
	public void clickOnModalFooterButtonForLinking(String buttonText) {
			WebElement btnEle = driver.findElement(By.xpath(String.format(tmpModalFooterButtonsForLinking, buttonText)));
			click(btnEle, "Clicking on modal ok button");
	}
	
	public void clickOnModalFormFooterButton(String buttonText) {
			WebElement btnEle = driver.findElement(By.xpath(String.format(tmpModalFormFooterButtons, buttonText)));
			new JavaScriptHelper(driver).scrollToElement(btnEle);				//Added for export
			click(btnEle, "Clicking on modal ok button");
	}
	
	public boolean isModalFooterButtonDisplayed(String buttonText) {
			WebElement btnEle = driver.findElement(By.xpath(String.format(tmpModalFooterButtons, buttonText)));
			return new VerificationHelper(driver).isElementDisplayedByEle(btnEle);
	}

	public void clickOnModalFooter() {
		
		
	}
	
	public void uploadFileNativeWindow(String filePath, String fileName) {
		String completeFilePath = filePath + fileName;
		String autoITExecutable = ObjectReader.reader.getAutoITExePath() +" "+completeFilePath; 
		try {
			Thread.sleep(2000);
		    Runtime.getRuntime().exec(autoITExecutable);
		    Thread.sleep(2000);
		} catch (Exception e) {
		    log.info("Exception while uploading file at "+completeFilePath+fileName+" Exception is " + e);
		}
		
	}
	
	public void uploadRecentlyDownloadedFile(String docExtension) {
			String downloadExePath = "RecentFileDownloads_1.exe";
		String uploadDownloadedFileAutoExe = ObjectReader.reader.getAutoITExeFolderPath()+downloadExePath+" "+docExtension; 
		System.out.println("uploadDownloadedFileAutoExe============"+uploadDownloadedFileAutoExe);
		
		try {
			Thread.sleep(3000);
		    Runtime.getRuntime().exec(uploadDownloadedFileAutoExe);
		    Thread.sleep(4000);
		} catch (Exception e) {
		    log.info("Exception while uploading file at "+uploadDownloadedFileAutoExe+" Exception is " + e);
		}
	}
  public void clickOkOnPopupIfExist() {
    try {
      clickOkOnPopup();
    }
    catch (Exception e) {
    }
  }	
  
	/**
	 * Modal dialog with text in span
	 * @param modalTitle
	 */
	public void waitForModalDialogWithTextInSpan(String modalTitle) {
			By modalTitleLocator = By.xpath(String.format(tmpModalTitle1, modalTitle));
			waitHelper.waitForElementVisible(modalTitleLocator, 20, 1);	
	}	
	
	public void waitForModalFormDialog(String modalTitle) {

		try {

				By modalFormTitleLocator = By.xpath(String.format(tmpModalFormTitle, modalTitle));

				waitHelper.waitForElementVisible(modalFormTitleLocator, 40, 1);

							} catch (Exception e) {

				By modalTitleLocator = By.xpath(String.format(tmpModalTitle2, modalTitle));

				waitHelper.waitForElementVisible(modalTitleLocator, 40, 1);				}

	}	

	
}
