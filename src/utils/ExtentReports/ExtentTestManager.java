package utils.ExtentReports;
 
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
 
public class ExtentTestManager {
	public static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    public static ExtentReports extent = ExtentManager.getInstance();
 
    public static synchronized ExtentTest getTest() {
    	//System.out.println("GetTest "+ Thread.currentThread().getId());
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
 
	
	  public static synchronized void endTest() { 
		  extent.flush();
	  }
	 
 
    public static synchronized ExtentTest startTest(String testName) {
    	System.out.println("Extent value is "+extent);
    	ExtentTest test = extent.createTest(testName);
       extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
       return test;
    }
}