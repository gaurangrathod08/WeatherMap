package test;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import io.restassured.specification.RequestSpecification;

import junit.framework.Assert;
import pages.OpenWeatherPage;
import utils.DriverUtils;
import utils.TestDataUtils;

public class OpenWeatherTest {
	WebDriver driver;

	
	public OpenWeatherTest(DriverUtils utils) {
		this.driver = utils.getdriver();
	}

	@Given("\"([^\"]*)\" is loaded$")
	public void loadURL(String strURL) {
		OpenWeatherPage owp = new OpenWeatherPage(driver);
		owp.loadURL(strURL);
	}

	@And("^I enter city name as \"([^\"]*)\" and click on search button$")
	public void CityName(String strCityName) {
		OpenWeatherPage owp = new OpenWeatherPage(driver);
		owp.enterCityName(strCityName);
		owp.clkSearchbtn();
	}

	@Then("^I see Not found message is displayed$")
	public void validationMessage() {
		OpenWeatherPage owp = new OpenWeatherPage(driver);
		Assert.assertTrue("Verifies that website suggests city is 'Not found'", owp.validationMessage());
	}

	@Then("^I see \"([^\"]*)\" is displayed$")
	public void cityResults(String strCityName) {
		OpenWeatherPage owp = new OpenWeatherPage(driver);
		Assert.assertTrue("Verifies that website successfully returns weather details for the city",
				owp.cityResults(strCityName));
	}

	@Then("^I verify all the label and links present on the site$")
	public void verifyAllElementOfPage() {

		OpenWeatherPage owp = new OpenWeatherPage(driver);
		Assert.assertTrue("Verified All the links present in the headers", owp.verifyHeader());
		Assert.assertTrue("Verified All the taps with home link present on the page", owp.verifyTabsOnPage());
		Assert.assertTrue("Verified slidshow, enter city text box, search button, current location link",
				owp.verifyCityTextBoxAndButton());
	}

	@And("^I verify weather graphical chart displayed properly$")
	public void verifyGraphicalChart() {

		OpenWeatherPage owp = new OpenWeatherPage(driver);
		Assert.assertTrue("Verified 'Main' tab present on Graphical Chart", owp.verifyGraphicalTabs("Main"));
		Assert.assertTrue("Verified 'Daily' tab present on Graphical Chart", owp.verifyGraphicalTabs("Daily"));
		Assert.assertTrue("Verified 'Hourly' tab present on Graphical Chart", owp.verifyGraphicalTabs("Hourly"));
		Assert.assertTrue("Verified 'Chart' tab present on Graphical Chart", owp.verifyGraphicalTabs("Chart"));

	}

	@And("^I verify services displayed properly$")
	public void verifyServices() {

		OpenWeatherPage wp = new OpenWeatherPage(driver);
		Assert.assertTrue("Verified 'Main' tab present on Graphical Chart", wp.verifyServices("More weather"));
		Assert.assertTrue("Verified 'Main' tab present on Graphical Chart", wp.verifyServices("Open weather map"));
	}

	@And("^I validate get API for \"([^\"]*)\" city using \"([^\"]*)\"$")
	public void getAPI(String name, String strID) {
		try {

			HashMap<String, String> testData = TestDataUtils.getDataFromDataprovider(strID);
			String strAPI = testData.get("API");
			String strAPIParameter = testData.get("API_Parameter").replace("|", "&");
			String apiBody = testData.get("API_Body");
			String apiStatus = testData.get("API_Status");

			strAPI = strAPI + "?" + strAPIParameter;
			RestAssured.baseURI = strAPI;
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get(strAPI);
			ResponseBody body = response.getBody();
			String bodyStringValue = body.asString();
			JsonPath jsonPath = new JsonPath(body.asString());
			String strCityName = response.jsonPath().getString("name");
			int status = RestAssured.given().get(strAPI).getStatusCode();

			Assert.assertTrue("Verified name attribut is Json is exported correctly",
					strCityName.toLowerCase().equals(name.toLowerCase()));
			Assert.assertTrue("Verified status code", String.valueOf(status).equals(apiStatus));
			Assert.assertTrue("Verified body", apiBody.equals(bodyStringValue));

		} catch (IOException e) {
			closeWindow();
		}
	}

	@And("^I close the window$")
	public void closeWindow() {
		driver.quit();
	}
}
