package utils.ExtentReports;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;

 
//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
 
public class ExtentManager {
 
    private static ExtentReports extent;
    //private static ExtentHtmlReporter htmlReporter;
    private static ExtentSparkReporter htmlReporter;
    private static ExtentTest test;
 
    public synchronized static ExtentReports getInstance(){
        if(extent == null){
        	//htmlReporter = new ExtentHtmlReporter (System.getProperty("user.dir") +"/test-output/EDRMReport.html");
        	htmlReporter = new ExtentSparkReporter (System.getProperty("user.dir") +"/test-output/EDRMReport.html").viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD,ViewName.TEST}).apply();
        	extent = new ExtentReports();  //create object of ExtentReports
    		extent.attachReporter(htmlReporter);
            
    		htmlReporter.config().setDocumentTitle("EDRM Automation Report"); // Tittle of Report
    		htmlReporter.config().setReportName("EDRM Automation Suit Report"); // Name of the report
    		htmlReporter.config().setTheme(Theme.STANDARD);//Default Theme of Report

    		// General information releated to application
    		extent.setSystemInfo("Application Name", "EDM Web Application");
    		extent.setSystemInfo("User Name", ObjectReader.reader.getUserName());
    		extent.setSystemInfo("Envirnoment", ObjectReader.reader.getUrl());
    		
    		//extent.loadConfig(new File(System.getProperty("user.dir")+"/src/extent-config.xml"));
        }
        return extent;
    }
}