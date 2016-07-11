package com.thoughtworks.lean.utils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SharedDriver extends EventFiringWebDriver {
    private static  WebDriver REAL_DRIVER;
    private static Thread CLOSE_THREAD = new Thread(){
        @Override
        public void run() {
            REAL_DRIVER.close();
        }
    };

    static {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            String chromeServer = System.getenv("SELENIUM_CHROME_SERVER");
            REAL_DRIVER = new RemoteWebDriver(new URL(chromeServer), capabilities);
            REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            REAL_DRIVER.manage().window().setSize(new Dimension(1024,768));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }
}