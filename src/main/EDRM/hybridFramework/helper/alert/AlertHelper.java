package main.EDRM.hybridFramework.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import utils.ExtentReports.ExtentTestManager;

public class AlertHelper {

	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver){
		this.driver = driver;
		log.info("AlertHelper object is craeted..");
	}
	
	/**
	 * Get alert object for further actions
	 * @return
	 */
	public Alert getAlert(){
		log.info("alert test: "+driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}
	
	/**
	 * Accept alert present on page
	 */
	public void acceptAlert(){
		log.info("accept Alert is done...");
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, "Accepting alert");
		getAlert().accept();
	}
	
	/**
	 * Dismiss / cancel alert
	 */
	public void dismissAlert(){
		getAlert().dismiss();
		log.info("dismiss Alert is done...");
	}
	
	/**
	 * Get alert text
	 * @return text of alert present
	 */
	public String getAlertText(){
		String text = getAlert().getText();
		log.info("alert text: "+text);
		return text;
	}
	
	/**
	 * Check whether alert is present or not
	 * @return true,false alert presence
	 */
	public Alert isAlertPresent(){
		try{
			Alert alertBox = driver.switchTo().alert();
			log.info("alert is present");
			return alertBox;
		}
		catch(NoAlertPresentException e){
			log.info(e.getCause());
			return null;
		}
	}
	
	/**
	 * Accept alert if its present
	 */
	public void acceptAlertIfPresent(){
		Alert acAlert = isAlertPresent();
		if(acAlert!=null){
			acAlert.accept();
		}
		else{
			log.info("Alert is not present..");
		}
	}
	
	/**
	 * Dismiss alert if its present
	 */
	public void dismissAlertIfPresent(){
		Alert dmAlert = isAlertPresent();
		if(dmAlert!=null){
			dmAlert.dismiss();
		}
		else{
			log.info("Alert is not present..");
		}
	}
	
}
