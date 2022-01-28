package com.browserstack.testsuite_android;

import com.browserstack.base.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.Test;

public class _01_Android_NativeAppTests extends DriverManager {
    private final AppiumDriver<?> driver;

    public _01_Android_NativeAppTests() throws Exception {
        DriverManager driverManager = new DriverManager();
        driverManager.setPlatform("Android_App");
        driver = getDriver();
    }

    @Test
    public void iosAppTest() throws Exception {
        Thread.sleep(6000);
        System.out.println(driver.getContext());
    }

}