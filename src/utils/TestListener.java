
package utils;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import utils.ExtentReports.ExtentTestManager;

public class TestListener implements ITestListener{

	public String destination;
	public ExtentTest test;
	
	private Logger log = LoggerHelper.getLogger(TestListener.class);
	
	public void onTestStart(ITestResult result) {
        
    }

	public void onTestSuccess(ITestResult result) {
	}

	
	public void onTestFailure(ITestResult result) {
				 
		
	}
	
	
	@Override
	public void onTestSkipped(ITestResult method) {	
		test = ExtentTestManager.startTest( method.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(method.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		log.info(method.getName() + " - Test Case Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {	
	}
	
	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
	

}

