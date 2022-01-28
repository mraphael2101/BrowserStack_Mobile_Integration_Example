package com.browserstack.testsuite_ios;

import com.browserstack.base.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.Test;

import static com.browserstack.base.DriverManager.getDriver;

public class _02_IOS_MobileWebTests {
    private final AppiumDriver<?> driver;

    public _02_IOS_MobileWebTests() throws Exception {
        DriverManager driverManager = new DriverManager();
        driverManager.setPlatform("iOS_Web");
        driver = getDriver();
    }

    @Test
    public void iosMobileWebTest() throws Exception {
        Thread.sleep(6000);
        System.out.println(driver.getContext());
    }
}
