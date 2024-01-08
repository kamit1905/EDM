package main.EDRM.hybridFramework.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;

public class VerificationHelper {

	private WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	/**	
	 * Is this element displayed or not? This method avoids the problem of having to parse an element's "style" attribute.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementDisplayedByEle(WebElement element){
		try {
			 if(element.isDisplayed()) {
				 log.info("Element is displayed "+element.getText());
				 return true;
			 }
			 else 
				 return false;
		}
		catch(Exception e) {
			log.info("Element is not displayed "+e.getCause());
			return false;
		}
	}
	
	/**	
	 * Is this element present or not? This method avoids the problem of having to parse an element's "style" attribute.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementPresent(By xpathString){
		return driver.findElements(xpathString).size() != 0;
	}
	
	
	/**
	 * Is this element displayed or not? This method avoids the problem of having to parse an element's "style" attribute.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementDisplayed(By locator){
		try {
			if (driver.findElement(locator).isDisplayed()) {
			log.info("Element is displayed "+driver.findElement(locator).getText());
			return true;
			}
			else 
				return false;
		}
		catch(NoSuchElementException e) {
			log.info("Element is not displayed "+e.getCause());
			return false;
		}
	}
	
	/**
	 * Is this element enabled or not? This method avoids the problem of having to parse an element's "style" attribute.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementEnabled(WebElement ele){
		try {
			Boolean isEnabled = ele.isEnabled();
			return isEnabled;
		}
		catch(NoSuchElementException e) {
			log.info("Exception while checking if element enabled "+e.getCause());
			return false;
		}
	}
	
	
	
	/**
	 * Is this element select or not? e.g if checkbox is selected or not.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementSelected(WebElement ele){
		try {
			Boolean isSelected = ele.isSelected();
			return isSelected;
		}
		catch(NoSuchElementException e) {
			log.info("Exception while checking if element enabled "+e.getCause());
			return false;
		}
	}
	
	/**
	 * Get text of webelement
	 * @param element 
	 * @return 
	 */
	public String getText(WebElement element) {
		if(null==element) {
			log.info("Element is null");;
			return null;
		}
		boolean status= isElementDisplayedByEle(element);
		if(status) {
			log.info("element text is "+element.getText());
			return element.getText();
		}
		else {
			return null;
		}
		
	}
	
	
}
