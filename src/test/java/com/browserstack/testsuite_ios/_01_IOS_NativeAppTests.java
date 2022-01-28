package com.browserstack.testsuite_ios;

import com.browserstack.base.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.Test;

public class _01_IOS_NativeAppTests extends DriverManager {
    private final AppiumDriver<?> driver;

    public _01_IOS_NativeAppTests() throws Exception {
        DriverManager driverManager = new DriverManager();
        driverManager.setPlatform("iOS_App");
        driver = getDriver();
    }

    @Test
    public void androidAppTest() throws Exception {
        Thread.sleep(6000);
        System.out.println(driver.getContext());
    }
}
