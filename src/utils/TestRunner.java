package utils;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "Features" }, glue = { "test", "pages", "utils" }, plugin = { "pretty",
		"html:target/cucumber-reports", "junit:target/cucumber-reports/Cucumber.xml",
		"json:target/cucumber-reports/Cucumber.json" }, tags = { "@Weather" }, monochrome = true)

public class TestRunner {

}
