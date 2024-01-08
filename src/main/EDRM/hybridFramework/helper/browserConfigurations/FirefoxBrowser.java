package main.EDRM.hybridFramework.helper.browserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxBrowser {

	public FirefoxOptions getFirefoxOptions() {
		
		DesiredCapabilities firefox= new DesiredCapabilities();
		
		FirefoxProfile profile= new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		
		//firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);
	
	FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
	
	return firefoxOptions;
	}
	
	
	public WebDriver getFirefoxDriver(FirefoxOptions cap) {
		WebDriverManager.chromedriver().setup();
		return new FirefoxDriver(cap);
	}
	
	
}
