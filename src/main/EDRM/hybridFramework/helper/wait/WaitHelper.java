package main.EDRM.hybridFramework.helper.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;

public class WaitHelper {

	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("WaitHelper : " + this.driver.hashCode());
	}
	
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info(timeout);
		//driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}
	
	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		log.info(timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	/**
	 * This method will return the object of wait 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.debug("");
		//@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		//wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		wait.pollingEvery(Duration.ofMillis(10));
		wait.ignoring(NoSuchElementException.class);
		//wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	/**
	 * Waiting for element to be visible 
	 * @param ele
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return WebElement
	 */
	public WebElement waitForElementVisible(WebElement ele, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for elemenet visible");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		return wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	/**
	 * Waiting for element to be visible
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return WebElement
	 */
	public WebElement waitForElementVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for elemenet visible by locator "+locator.toString());
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 *  Waiting for element to be Invisible
	 * @param driver
	 * @param element
	 * @param timeout
	 */
	public void waitForElementInvisible(WebDriver driver, WebElement element, int timeout) {
		log.info("waiting for element to be invisible"+element.getText());
		WebDriverWait wait = getWait(timeout, 1);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	/**
	 *  Waiting for element to be Clickable
	 * @param driver
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement waitForElementClickable(WebDriver driver,WebElement element,long time){
		log.info("waiting for element to be clickable "+element.getText());
		//WebDriverWait wait = new WebDriverWait(driver, time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	/**
	 * Wait for java script alert
	 * @return 
	 */
	public Alert waitForAlert(WebDriver driver,long time) {
		log.info("waiting for java script alert");
		//WebDriverWait wait = new WebDriverWait(driver, time);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.alertIsPresent());
			
		}
	
	/**
	 * This method will give is fluentWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}
	
	/**
	 * Wait for elemenet to be visible
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec){
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	/**
	 * page load time
	 * @param timeout
	 * @param unit
	 */
	public void pageLoadTime(long timeout, TimeUnit unit){
		log.info("waiting for page to load for : "+ unit+ " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
	}
	
	/**
	 * This method will make sure elementToBeVisible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return webelement
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		//WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
		return element;
	}
	
	/**
	 * 
	 * @param ele
	 * @param attributeName
	 * @param attributeValue
	 * @param timeOutInSeconds
	 * @return
	 */
	public Boolean waitForElementAttributeChange(WebElement ele,String attributeName, String attributeValue, int timeOutInSeconds) {
		//WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
		
		return wait.until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver driver) {
		        String enabled = ele.getAttribute(attributeName);
		        if(enabled.equals(attributeValue)) 
		            return true;
		        else
		            return false;
		    }
			});
	}

		/**
	 * Is this element displayed or not? This method avoids the problem of having to parse an element's "style" attribute.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementDisplayedByEle(WebElement element){
		try {
			return element.isDisplayed();
		}
		catch(NoSuchElementException e) {
			log.info("Exception in isElementDisplayedByEle : " + e);
			return false;
		}
	}

}
	

