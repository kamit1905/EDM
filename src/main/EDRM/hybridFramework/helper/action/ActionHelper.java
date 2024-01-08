package main.EDRM.hybridFramework.helper.action;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import utils.ExtentReports.ExtentTestManager;

public class ActionHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(ActionHelper.class);

	
	public ActionHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("Action helper : " + this.driver.hashCode());
	}
	
	
	
	/**
	 * This function will Scroll to the specific element and click
	 * @param WebElement
	 **/
	public void moveToElementAndClick(WebElement ele) {
	Actions actions = new Actions(driver);
	actions.moveToElement(ele);
	actions.click(ele).build().perform();
	}
	
	
		
	/**
	 * Move to Element and click
	 * @param by
	 */
	public void moveToElementAndClick(By by) {
	Actions actions = new Actions(driver);
	WebElement ele=driver.findElement(by);
	actions.moveToElement(ele);
	actions.click(ele).build().perform();
	}
	
		/**
	 * This method will be used to drag and drop element from 
	 * @param srclocator This element will be dragged from its location
	 * @param dstLocator This is the destination of element 
	 */
	public void dragAndDropElement(WebElement srcElement, WebElement dstElement) {
		String logmsg="Performing drag and drop element";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(srcElement)
		   .moveToElement(dstElement)
		   .release(dstElement)
		   .build();
	
		dragAndDrop.perform();
	}
		
	/**
	 * Press tab button
	 */
	public void pressTab(){
		String logmsg="Pressing tab button";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
	}
	
	/**
	 * Press escable button
	 */
	public void pressEscape(){
		String logmsg="Pressing escape button";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}
	
	/**
	 * Press down arrow
	 */
	public void pressDownArrow(){
	String logmsg="Pressing down arrow button";
	log.info(logmsg);
	ExtentTest test=ExtentTestManager.getTest();
	test.log(Status.INFO, logmsg);
	pressSpecifedKey(Keys.ARROW_DOWN);
	}
	
	/**
	 * Press enter
	 */
	public void pressEnter(){
		String logmsg="Pressing Enter key";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		pressSpecifedKey(Keys.ENTER);
	}
	
	/**
	 * 
	 * @param keys
	 */
	public void pressSpecifedKey(Keys keys) {
		String logmsg="Pressing specified key "+String.valueOf(keys);
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Actions action = new Actions(driver);
		action.sendKeys(keys).perform();
	}
	

	/**
	 * Perform hover on Element passed in function or move to that element
	 * @param by locator e.g. By.id("abc") or By.xpath("//p/span[2]")
	 */
	public void mouseHoverOnElement(By by) {
		String logmsg="Performing mouse hover on element";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Actions action = new Actions(driver); 
        action.moveToElement(driver.findElement(by)).build().perform();
	}
	
	/**
	 * Perform hover on Element passed in function or move to that element
	 * @param by locator e.g. By.id("abc") or By.xpath("//p/span[2]")
	 */
	public void doubleClickOnElement(WebElement ele) {
		String logmsg="Performing mouse hover on element";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Actions action = new Actions(driver); 
        action.moveToElement(ele).doubleClick().build().perform();
	}
	
	 /*
	  * Press space
	  */
	public void pressSpace(){
		String logmsg="Pressing Space key";
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		pressSpecifedKey(Keys.SPACE);
	}
	
	public void SwithcToFrame(WebElement frameEle) {
		driver.switchTo().frame(frameEle);
	}
	
	public void SwitchBackToMainFrameOfPage() {
		ExtentTest test=ExtentTestManager.getTest();
		driver.switchTo().defaultContent();
		test.log(Status.INFO,"Switching to default frame");
	}
	
}
