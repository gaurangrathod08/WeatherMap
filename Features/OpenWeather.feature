Feature: Open Weather scenarios

Background:
Given "https://openweathermap.org/" is loaded

@TC01 @Weather
Scenario: Verify information present on open weather site

And I verify all the label and links present on the site
And I verify weather graphical chart displayed properly
And I verify services displayed properly
And I close the window


@TC02 @Weather
Scenario: Verify user searches for weather with respect to invalid city name

When I enter city name as "NEWMUMBAI" and click on search button
Then I see Not found message is displayed
And I close the window


@TC03 @Weather
Scenario: Verify user searches for weather with respect to valid city name

When I enter city name as "MUMBAI" and click on search button
Then I see "Mumbai, IN" is displayed
And I close the window


@TC04 @Weather
Scenario: Verify API 

Then I validate get API for "London" city using "Get_City_API_valid"




