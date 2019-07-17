package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class DriverUtils {
	WebDriver driver;

	@Before
	public void init(Scenario scenario) {
		System.setProperty("webdriver.chrome.driver", "D://BDD_INT_WORKSPACE//bdd//Driver//chromedriver.exe");
		this.driver = new ChromeDriver();
	}

	public WebDriver getdriver() {

		return driver;
	}

}
