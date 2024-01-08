package main.EDRM.hybridFramework.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
@SuppressWarnings("rawtypes")
public class LoggerHelper {
	
	private static boolean root = false ;
	
	public static Logger getLogger(Class cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("src\\main\\EDRM\\resources\\log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	 }
	
	
	public static void main(String [] args) {
		
		Logger log = LoggerHelper.getLogger(LoggerHelper.class);
		log.info("Logger configured");
		log.info("Logger configured");
		log.info("Logger configured");
	}
	
}	
	

