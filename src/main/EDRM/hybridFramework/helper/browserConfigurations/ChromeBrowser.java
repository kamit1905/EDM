package main.EDRM.hybridFramework.helper.browserConfigurations;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;

public class ChromeBrowser {
	
	public ChromeOptions getChromeOptions() {
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--test-type");
		options.addArguments("disable-infobars");
		options.addArguments("--remote-allow-origins=*");
		//options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				
		
		return options; 
		
	}
	
	public WebDriver getChromeDriver(ChromeOptions cap) {
		//WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver",ObjectReader.reader.getChromeDriverPath());
		 WebDriver driver = new ChromeDriver(cap);
		return driver;
	}

}
