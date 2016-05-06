package com.thoughtworks.lean.impls;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by qmxie on 5/6/16.
 */
public class UITestHooks {

    private static Map<String, WebDriver> instance = new LinkedHashMap<String, WebDriver>();

    @cucumber.api.java.Before("@ui_test")
    public void before_ui_test(Scenario scenario) {
        if (instance.get(scenario.getName()) == null) {
            instance.put(scenario.getName(), new ChromeDriver());
        }
    }

    public static WebDriver getWebDriver(String scenarioName) {
        return instance.get(scenarioName);
    }

    @After("@ui_test")
    public void after_ui_test(Scenario scenario) {
        WebDriver webDriver = instance.get(scenario.getName());
        if (webDriver != null) {
            webDriver.close();
            instance.remove(scenario.getName());
        }
    }
}
