package main.EDRM.hybridFramework.helper.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;

public class Retry implements IRetryAnalyzer{
	
	private int retryCount = 0;
	private int maxRetryCount =2;
	
	
	private Logger log = LoggerHelper.getLogger(Retry.class);
	
	public boolean retry(ITestResult result) {
		String errorMsg = String.valueOf(result.getThrowable());
		boolean isAssertion;
		//Condition to check if test failed due to assertion
		isAssertion = (errorMsg.contains("java.lang.AssertionError")? true:false);
		if(retryCount < maxRetryCount && !isAssertion) {
			log.info("Retrying test "+result.getName()+" with Status "+getResultStatusName(result.getStatus())+ " for the "+String.valueOf(retryCount+1)+" times");
			log.info("Get failed exception"+result.getThrowable());
			retryCount++;
			return true;
		}
		return false;
	}
	
	
	public String getResultStatusName(int status) {
		String resultName = null;
		if(status==1) {
			resultName="SUCCESS";
		}
		else if(status==2) {
			resultName="FAILURE";
		}
		else if(status==3) {
			resultName="SKIP";
		}
		return resultName;
		
	}
	

	
	
}
