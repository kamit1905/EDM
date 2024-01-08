package main.EDRM.hybridFramework.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;

public class JavaScriptHelper {

	
	private WebDriver driver;
	private static Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver=driver;
		log.info("JavaScriptHelper is initialised");
	}
	
	
		/**
	 * function to execute any javascript 
	 * @param value Passed Javascript code as a string e.g. '$('#ID').click();'
	 */
	public Object executeScript(String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(value);
	}
	
	
		/**
	 * function to execute any javascript 
	 * @param value Passed Javascript code as a string e.g. '$('#ID').click();'
	 */
	public Object executeScript(String script, Object...args) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script,args);
	}
	
	/**
	 * function to get data in string using javascript 
	 * @param value Passed Javascript code as a string e.g. return  document.getElementById("Id").value;'
	 */
	public String  executeScriptGetResult(String command) {
		String result =  String.valueOf(executeScript(command));
		return result;
	}
	
	
	
	/**
	 * This function will Scroll to the specified element 
	 * @param by locator e.g By.name("xyz") 
	 * */
		
	public void scrollToElement(WebElement element) {
		log.info("Scroll to webelement");
		executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * Scroll to top of the page
	 */
	public void scrollToTop() {
		log.info("Scroll to top");
		executeScript("window.scrollTo(0, 0)");
	}
	
	/**
	 * Scroll to bottom of page
	 */
	public void scrollToBottom() {
		log.info("Scroll to bottom");
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	/**
	 * This function will Scroll to the specified element and click 
	 * @param by locator e.g By.name("xyz") 
	 * */
		
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("element is clicked "+element.toString());
	}
	
	/**
	 * This function will click the specified element 
	 * @param WebElement 
	 * */
		
	public void clickElement(WebElement element) {
		log.info("Scroll to webelement");
		executeScript("arguments[0].click();", element);
	}
	
	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomInBy100Percentage(){
		executeScript("document.body.style.zoom='100%'");
	}
	
	/**
	 * This method will zoom screen by 60%
	 */
	public void zoomInBy60Percentage(){
		executeScript("document.body.style.zoom='40%'");
	}


	/*
	 * Added by amit to clear text using javascript executor
	 */
	public void clearText(WebElement element) {
		log.info("Clearing the text");
		executeScript("arguments[0].value='';", element);
	}
	
	public void enterTextUsingJavascriptExecutor(WebElement element,String value) {
		log.info("Entering text");
		executeScript("arguments[0].value='"+value+"';",element);
	}
	
	public void scrollByPixel() {
		executeScript("window.scrollBy(0,350)","");
	}
	
	/**
	 * it used to open new tab in same browser
	 */
	public void openNewTabUsingJavascriptExecutor() {
		executeScript("window.open()");
	}
	
}
