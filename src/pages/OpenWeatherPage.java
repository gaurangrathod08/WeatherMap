package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;

public class OpenWeatherPage extends CommonUtils {
	WebDriver driver;

	String strServices1 = "Get current weather, daily forecast for 16 days, and 3-hourly forecast 5 days for your city. Helpful stats, graphics, and this day in history charts are available for your reference. Interactive maps show precipitation, clouds, pressure, wind around your location.";
	String strServices2 = "Run your advertising campaign with OpenWeatherMap weather API through Google AdWords.";
	
	@FindBy(xpath = "//a/span[contains(text(),'Support Center')]")
	private WebElement lnkSupportLink;

	@FindBy(id = "nav-search")
	private WebElement lnkWetherInCity;

	@FindBy(css = "#nav-search-form .form-control")
	private WebElement txtWetherInCity;

	@FindBy(xpath = "//a[contains(text(),'Sign In')]")
	private WebElement lnkSignIn;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	private WebElement lnkSignUp;

	@FindBy(css = "#metric")
	private WebElement chkCelsius;

	@FindBy(css = "#imperial")
	private WebElement chkFahrenheit;

	@FindBy(xpath = "//img[@alt='openweather']")
	private WebElement lnkHome;

	@FindBy(css = "input[placeholder='Your city name']")
	private WebElement txtCityName;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	private WebElement btnSearch;

	@FindBy(xpath = "//div[contains(text(),'Not found')]")
	private WebElement msgNotfound;

	@FindBy(css = "#forecast-list a[href^='/city']")
	private WebElement lnkCityNameResults;

	@FindBy(xpath = "//span[@class='badge badge-info']")
	private WebElement txtTempDisplayed;

	@FindBy(css = ".nav__item")
	private WebElement lstTopNav;

	@FindBy(id = "main-slideshow")
	private WebElement slideShow;

	@FindBy(css = ".btn.search-cities__lnk")
	private WebElement lnkCurrentLocation;

	@FindBy(id = "weather-widget")
	private WebElement weatherWidget;

	@FindBy(className = "widget__graphic")
	private WebElement weatherGraphic;

	@FindBy(css = ".widget__graphic .widget-tabs li")
	private WebElement tbGraphicTab;

	@FindBy(css = ".weather-forecast-hourly-graphic__header")
	private WebElement lblMainGraphicalTab;

	@FindBy(css = ".highcharts-legend .highcharts-legend-item")
	private List<WebElement> lnkHighChartLegendForMainTab;

	@FindBy(css = ".weather-forecast-graphic__header")
	private WebElement lblDailyGraphicalTab;

	@FindBy(css = ".highcharts-axis-labels.highcharts-xaxis-labels  text tspan")
	private List<WebElement> lstDays;

	@FindBy(css = ".widget__graphic .weather-forecast-hourly-list__header")
	private WebElement lblHourlyGraphicalTab;

	@FindBy(css = ".weather-forecast-chartval-graphic__header")
	private WebElement lblChartGraphicalTab;

	@FindBy(css = ".weather-map-layers__header")
	private WebElement lablMapGraphicalChart;

	@FindBy(xpath = "//a[contains(text(),'More weather in your city')]/parent::p")
	private WebElement service1;

	@FindBy(xpath = "//a[contains(text(),'Read more')]/parent::p/parent::div")
	private WebElement service2;

	@FindBy(css = ".row.owm-weathermap .row.owm-weathermap__items")
	private WebElement weatherMap;

	public OpenWeatherPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is used to launch the URL and verify URL launched properly
	 * 
	 * @param url
	 *            contains URL
	 */
	public void loadURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		waitForElement(lnkHome);

	}

	/**
	 * This method is used to enter name in the City text box
	 * 
	 * @param strCityName
	 *            contains city name
	 */
	public void enterCityName(String strCityName) {
		waitForElement(txtCityName);
		txtCityName.sendKeys(strCityName);
	}

	/**
	 * This method is used to click on search button
	 * 
	 */
	public void clkSearchbtn() {
		waitForElement(btnSearch);
		btnSearch.click();
	}

	/**
	 * This method is used to validate "Not Found" message
	 * 
	 * @return
	 */
	public boolean validationMessage() {

		if (waitForElement(msgNotfound)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to check the temperature of entered city
	 * 
	 * @param strCityName
	 * @return
	 */
	public boolean cityResults(String strCityName) {

		waitForElement(txtTempDisplayed);
		String text = txtTempDisplayed.findElement(By.xpath("parent::p")).getText();
		if (waitForElement(lnkCityNameResults) && text.contains("wind") && text.contains("clouds")
				&& text.contains("hpa") && lnkCityNameResults.getText().contains(strCityName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to check all the element present in the headers it
	 * will return true is all the element displayed properly
	 * 
	 * @return
	 */
	public boolean verifyHeader() {

		if (waitForElement(lnkSupportLink) && waitForElement(lnkWetherInCity) && waitForElement(lnkSignIn)
				&& waitForElement(lnkSignUp) && waitForElement(chkCelsius) && waitForElement(chkFahrenheit)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to verify all the links present in tab section
	 * 
	 * @return
	 */
	public boolean verifyTabsOnPage() {

		if (waitForElement(lnkHome)
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Weather')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Maps')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Guide')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'API')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Price')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Partners')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Stations')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Widgets')]")))
				&& waitForElement(lstTopNav.findElement(By.xpath("//a[contains(text(),'Blog')]")))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to verify slide show, city search box, search button,
	 * current location link, weather widget, Weather maps 2.0 section and
	 * graphical widget
	 * 
	 * @return
	 */
	public boolean verifyCityTextBoxAndButton() {

		if (waitForElement(slideShow) && waitForElement(txtCityName) && waitForElement(btnSearch)
				&& waitForElement(lnkCurrentLocation) && waitForElement(weatherWidget) && waitForElement(weatherGraphic)
				&& waitForElement(weatherMap)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to validate graph tab like Main, Daily, Hourly and
	 * Chart
	 * 
	 * @param strTab
	 * @return
	 */
	public boolean verifyGraphicalTabs(String strTab) {

		boolean flag = false;
		switch (strTab) {
		case "Main":
			waitForElement(tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Main')]")));
			tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Main')]")).click();
			if (lblMainGraphicalTab.getText().contains("Weather and forecasts in")
					&& lnkHighChartLegendForMainTab.get(0).getText().contains("Precipitation")
					&& lnkHighChartLegendForMainTab.get(1).getText().contains("Temperature")) {
				flag = true;
			}
			break;
		case "Daily":
			waitForElement(tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Daily')]")));
			tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Daily')]")).click();
			if (lblDailyGraphicalTab.getText().contains("Daily weather and forecasts in")) {
				flag = true;
			}
			break;
		case "Hourly":
			waitForElement(tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Hourly')]")));
			tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Hourly')]")).click();
			if (lblHourlyGraphicalTab.getText().contains("Hourly weather and forecasts in")) {
				flag = true;
			}
			break;
		case "Chart":
			waitForElement(tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Chart')]")));
			tbGraphicTab.findElement(By.xpath("//a[contains(text(),'Chart')]")).click();
			if (lblChartGraphicalTab.getText().contains("Chart weather and forecasts in")) {
				flag = true;
			}
			break;
		}

		return flag;

	}

	/**
	 * This method is used to test services
	 * 
	 * @param strService
	 * @return
	 */
	public boolean verifyServices(String strService) {

		boolean flag = false;

		if (strService.equalsIgnoreCase("More weather")) {
			if (waitForElement(service1) && service1.getText().startsWith(strServices1)) {
				flag = true;
			}
		} else if (strService.equalsIgnoreCase("Open weather map")) {
			if (waitForElement(service2) && service2.findElement(By.xpath("h3")).getText()
					.equalsIgnoreCase("Google Weather-Based Campaign Management with OpenWeatherMap API")) {
				flag = true;
			}

		}
		return flag;
	}

}
