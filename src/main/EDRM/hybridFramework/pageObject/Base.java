package main.EDRM.hybridFramework.pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;

public class Base {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String baseUrl;
	
	
	public Base(WebDriver driver) {
		Base.driver = driver;
		//wait = new WebDriverWait(driver,10);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		baseUrl=ObjectReader.reader.getUrl();
	}
	
/**===============Selenium specific functions below==================**/
	
	
	
	/**
	 * wait function by visibilityOfElementLocated
	 * @param locator
	 * @return
	 */
	 public  WebElement waitTillElementVisible(WebElement ele){
		WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
		return element;
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
			System.out.println("isElementDisplayed : " + e);
			return false;
		}
	}
	
	
		/**
	 * Is this element displayed or not? This method avoids the problem of having to parse an element's "style" attribute.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementDisplayed(By locator){
		try {
			return driver.findElement(locator).isDisplayed();
		}
		catch(NoSuchElementException e) {
			System.out.println("isElementDisplayed : " + e);
			return false;
		}
	}
	
	
	
	/**
	 * Is this element present or not? This method is useful when element is not in viewport but its there in dom.
	 * @param locator
	 * @return Returns true or false based on element passed
	 */

	public boolean isElementPresent(List<WebElement> elements){
		try {
		    if(elements.size() > 0) {
		    	return true;
		    	}
		    else
		    	return false;
		  }
		catch (org.openqa.selenium.NoSuchElementException e) {
		    return false;
		  }
	}
	
	
	/**
	 * wait function by elementToBeClickable
	 * @param locator
	 * @return
	 */
	public  WebElement waitTillElementClickable(WebElement element){
		WebElement foundelement = wait.until(ExpectedConditions.elementToBeClickable(element));
		return foundelement;
	}
	


	

	
	
	
	/**
	 * function to execute any javascript 
	 * @param value Passed Javascript code as a string e.g. '$('#ID').click();'
	 */
	public void javaScriptExecutorMethod(String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(value);
	}
	
	
	/**
	 * function to get data in string using javascript 
	 * @param value Passed Javascript code as a string e.g. return  document.getElementById("Id").value;'
	 */
	public String  javaScriptExecutorToGetResult(String command) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String result =  String.valueOf(js.executeScript(command));
		return result;
	}
	
	 /**
     * Returns title of the page
     * @return
     */
	public String getTitle(){
		return driver.getTitle();
	}
	
	
	/**
	 * Returns H1 text based on Element passed
	 * @param locator
	 * @return
	 */
	public String getH1TagText(){
		return driver.findElement(By.tagName("h1")).getText();
	}
	
	/**
	 * Returns text of any locator passed
	 * @param locator e.g. By.id("abc")
	 * @return
	 */
	public String getElementText(By locator){
		return driver.findElement(locator).getText();
	}
	/**
	 * Returns Attribute value of locator
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public String getElementAttribute(By locator,String attribute ){
		return driver.findElement(locator).getAttribute(attribute) ;
	}
	

	
	

	/**
	 * wait function by invisibilityOfElementLocated
	 * @param locator
	 */
	public void waitTillElementInvisible(WebElement element){
		try {
		wait.until(ExpectedConditions.invisibilityOf(element));
		}
		catch(NoSuchElementException e) {
			System.out.println("Exception occured but it can be ignore since waiting for element to be invisbile");
		}
	}
	
	/**
	 * wait function by selenium isEnabled function
	 * @param element
	 */
	 public void waitTillElementEnabled(final WebElement element){
        wait.until(new ExpectedCondition<Boolean>() {
        	public Boolean apply(WebDriver driver) {
                        if (element.isEnabled()){
                        	System.out.println(element.getText());
        			return Boolean.TRUE;
        		}
        		return null;
            }
        }); 
	 }
	

	
	/**
	 * Is this element enabled or not? 
	 * @param locator
	 * @return Returns true or false based on element passed
	 */
	public boolean isElementEnabled(By locator){
		try {
			return driver.findElement(locator).isEnabled();	
		}
		catch(NoSuchElementException e) {
			System.out.println("isElementEnabled : " + e);
			return false;
		}
	}
	
	
	/**
	 * wait function untill alertIsPresent 
	 */
	public void waitForAlert() {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	/**
	 * It is for checking if alert is present or not
	 * @return : True or False based on alert present
	 */
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	

	
	/**
	 * To switch to frame based on String passed
	 * @param id e.g "iFrameID"
	 */
	public void switchToiframe(String id) {
		driver.switchTo().frame(id);
	}
	
	/**
	 * Accepts alert & closes it.
	 */
	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	/**
	 * Selects value from drop-down
	 * @param by any locator e.g By.className("abc")
	 * @param value "Value of dropdown"
	 */
	public void selectFromDropdownByValue(By by,String value) {
		Select dropdown = new Select(driver.findElement(by));
		dropdown.selectByValue(value);
	}
	
	
	
	/**
	 * Determine whether or not this element is selected or not. 
	 * This operation only applies to input elements such as checkboxes, options in a select and radio buttons.
	 * @param by locator e.g By.name("xyz")
	 * @return Returns true or false based on element selected
	 */
	public boolean isElementSelected(By by) {
		try {
			return driver.findElement(by).isSelected();
		}
		catch(Exception e) {
			System.out.println("isElementSelected" + e);
			return false;
		}
	}
	
	/**
	 * Returns List of Elements having same id/class etc based on locators passed 
	 * @param by locator e.g By.name("xyz")
	 * @return Returns Element List
	 */
	public List<WebElement> getListElementsByLocator(By locator) {
	   	List<WebElement> elementList = driver.findElements(locator);
		return elementList;
	}
	
	
	/**
	 * This function will Scroll to the specified element 
	 * @param by locator e.g By.name("xyz") 
	 * */
		
	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	
	

}
