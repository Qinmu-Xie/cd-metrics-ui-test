package com.thoughtworks.lean.utils;

import org.openqa.selenium.WebDriver;

/**;
 *
 * Created by qmxie on 5/6/16.
 */
public class DriverThread extends Thread {
    private WebDriver REAL_DRIVER=null;
    public DriverThread() {
    }

    public void setREAL_DRIVER(WebDriver REAL_DRIVER) {
        this.REAL_DRIVER = REAL_DRIVER;
    }

    @Override
    public void run() {
        super.run();
        REAL_DRIVER.close();
    }
}
