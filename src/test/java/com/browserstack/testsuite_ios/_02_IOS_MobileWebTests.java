package com.browserstack.testsuite_ios;

import com.browserstack.base.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.Test;



public class _02_IOS_MobileWebTests extends DriverManager {
    private final AppiumDriver<?> driver;

    public _02_IOS_MobileWebTests() throws Exception {
        DriverManager driverManager = new DriverManager();
        driverManager.setPlatform("iOS_Web");
        driver = getDriver();
    }

    @Test
    public void iosMobileWebTest() throws Exception {
        System.out.println(driver.getContext());
        driver.get("https://google.com/");
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(6000);
    }
}
