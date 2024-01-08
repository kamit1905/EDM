package main.EDRM.hybridFramework.helper.calender;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;

public class CalenderHelper {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	private Logger log = LoggerHelper.getLogger(CalenderHelper.class);
	
	
	@FindBy(how=How.XPATH,using="//table[@class='table-condensed']/thead/tr[1]/th[@class='picker-switch']")
	public WebElement getPresentMonthYearFromCalender;
	
	@FindBy(how=How.XPATH,using="//table[@class='table-condensed']/thead/tr[1]/th[@class='next']")
	public WebElement nextArrowBtn;
	
	
	
	
	public String monthYearSelect="//table[@class='table-condensed']/thead/tr[1]/th[@class='picker-switch'][text()='%s']",
			monthSelect="//table[@class='table-condensed']/tbody/tr[1]/td[1]/span[contains(@class,'month')][text()='%s']",
			daySelect="//table[@class='table-condensed']/tbody//td[@class='day'][text()='%s']";
	
	
	
	public CalenderHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("DropDown helper : " + this.driver.hashCode());
		waitHelper = new WaitHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	public void SelectMonthFromDatePicker(String monthYear,String month) {				//It will select month & year
		WebElement fnlmonthYearSelect = null;
		String getMonthAndYear=getPresentMonthYearFromCalender.getText();
		System.out.println("getMonthAndYear "+getMonthAndYear);
		if(!monthYear.equals(getMonthAndYear)) {
			boolean flag=false;
			while(flag==false) {
				new TestBase().sleepFor(1000);
				nextArrowBtn.click();
				String getNextMonthAndYear=getPresentMonthYearFromCalender.getText();
				if(!getNextMonthAndYear.equals(monthYear)) {
					  flag=false;
				}else {
					  flag=true;
					  String formatmonthYear=String.format(monthYearSelect, monthYear);
					  fnlmonthYearSelect = driver.findElement(By.xpath(formatmonthYear));
					  fnlmonthYearSelect.click();
				}
			}
			
		}else {
			  String formatmonthYear=String.format(monthYearSelect, monthYear);
			  fnlmonthYearSelect = driver.findElement(By.xpath(formatmonthYear));
			  fnlmonthYearSelect.click();
		}
	}
	
	
	public void SelectMonthFromCalender(String month) {
		new TestBase().sleepFor(1000);
		String formatMonthSelection=String.format(monthSelect, month);
		WebElement fnlmonthSelection=driver.findElement(By.xpath(formatMonthSelection));
		fnlmonthSelection.click();
	}
	
	public void SelectDayFromCalender(String day) {
		new TestBase().sleepFor(1000);
		WebElement fnldaySelection=driver.findElement(By.xpath(String.format(daySelect, day)));
		fnldaySelection.click();
	}

}
