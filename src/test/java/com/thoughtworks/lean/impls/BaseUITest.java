package com.thoughtworks.lean.impls;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;

/**
 * Created by qmxie on 5/6/16.
 */
public class BaseUITest {

    WebDriver driver;
    Scenario scenario;

    public WebDriver getWebDriver(String scenarioName) {
        return UITestHooks.getWebDriver(scenarioName);
    }

}
