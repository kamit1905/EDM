package main.EDRM.hybridFramework.pageObject;

import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;

public class Page extends TestBase{
	
		
		private WebDriver driver;
		WaitHelper waitHelper;
		AlertHelper alertHelper;
		VerificationHelper verificationHelper;
		JavaScriptHelper jsHelper;
		
		@FindBy(how=How.XPATH,using="//*[@class='modal-dialog-busy']")
		public WebElement busyDialogIcon ;
		
		@FindBy(how=How.XPATH,using="//a[@data-original-title='Information@Work Enterprise Home']")
		public WebElement homePageIcon ;
		
		@FindBy(how=How.XPATH,using="//p[@class='navbar-text ellipsisWrap']")
		public WebElement navbarTitleLblClass ;
	
			
	public Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		alertHelper=new AlertHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		jsHelper = new JavaScriptHelper(driver);
		//new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReport("Page Object Created");
			
	}
	
	/***       Project Specific functions below             ****/
	/**
	public void visitBaseUrl() {
		try {
			driver.get(baseUrl);
			 }
			catch(UnhandledAlertException f){
				try {
				acceptAlert();
				}
		     catch (NoAlertPresentException e) {
		        e.printStackTrace();
		       }
			 }
		
	}**/
	
	public void clickOnHomePageIcon() {
		try {
		homePageIcon.click();
		 }
		catch(UnhandledAlertException f){
			try {
			alertHelper.acceptAlert();
			}
	     catch (NoAlertPresentException e) {
	        e.printStackTrace();
	       }
		 }
	  }
	
	
	public void clickOnMenuButtonAtTop(WebElement iconElement, WebElement listElement) {
		if(verificationHelper.isElementDisplayedByEle(iconElement)) {
			iconElement.click();
		}
		else {
			listElement.findElement(By.xpath("./ancestor::div[contains(@class,'btn-group')]/div/button")).click();
			listElement.click();
		}
	}
	
	
	


	
	public void ZoomIN(String percentage) {
		String zoomCommand = "document.body.style.zoom ='"+percentage+"'"; 
		jsHelper.executeScript(zoomCommand);
		
	}
	
	public void ZoomDefault() {
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}
	

	
	
	
	public boolean isNavbarTitleVisible() {
		boolean visiblity=false;
		try {
			waitHelper.waitForElement(navbarTitleLblClass,500);
		}
		catch (Exception e) {
		System.out.println("Element not visible exception "+e);
		}
		
		return visiblity;
	}
	
	
	
}
