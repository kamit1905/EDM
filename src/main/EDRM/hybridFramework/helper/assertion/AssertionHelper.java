package main.EDRM.hybridFramework.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import utils.ExtentReports.ExtentTestManager;

public class AssertionHelper {

	private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);
	
	/**
	 * Verify text will compare actual and expected to be exact match
	 * @param actual
	 * @param expected
	 */
	public static void verifyText(String actual, String expected) {
		log.info("Verifying text "+actual+" with "+expected);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, "Verifying text actual = "+actual+" expected = "+expected);
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * This method verify whether actual string contains expected string or not
	 * @param actual
	 * @param expected
	 */
	public static void verifyTextContains(String actual, String expected) {
		log.info("Verifying text contains "+actual+" with "+expected);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, "Verifying text actual = "+actual+" expected = "+expected);
		Assert.assertTrue(actual.contains(expected));
	}

	/**
	 * Verify provided statment is true 
	 * @param status boolean result of statement
	 * @param logmsg message for logging in report
	 */
	public static void verifyTrue(boolean status,String logmsg) {
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Assert.assertTrue(status);
	}
	
	/**
	 * Verify provide status is false
	 * @param status
	 */
	public static void verifyFalse(boolean status) {
		log.info("Verifying false statement");
		Assert.assertFalse(status);
	}
	
	/**
	 * Verify provide status is false
	 * @param status
	 */
	public static void verifyFalse(boolean status,String logmsg) {
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		Assert.assertFalse(status);
	}
	
	/**
	 * Verify provided parameter is null
	 * @param s1
	 */
	public static void verifyNull(String s1) {
		log.info("Verifying object is null");
		Assert.assertNull(s1);
	}
	
	
	
}

