package com.browserstack.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static AppiumDriver<?> driver;
    private static String platform = "UNDEFINED";

    public void setPlatform(String value) {
        platform = value;
    }

    public static AppiumDriver<?> getDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (platform.toLowerCase()) {
            case "ios_app":
                capabilities.setCapability("app","bs://1036dce7c222fe25fb00d4a98e8e77a57693f160");
                capabilities.setCapability("browserstack.debug","true");
                capabilities.setCapability("build","browserstack-build-1");
                capabilities.setCapability("device","iPhone 11 Pro");
                capabilities.setCapability("name","iOS Native App Test");
                capabilities.setCapability("os_version","13");
                capabilities.setCapability("project","Mark's Junit4 iOS Project");
                driver = new IOSDriver<IOSElement>(new URL("http://" + FmwkConstants.USERNAME + ":" + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                break;
            case "ios_web":
                //WebDriverManager.chromedriver().browserVersion("74").setup();
                //capabilities.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().getBinaryPath());
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
                capabilities.setCapability("project", "Mobile Web Project");
                capabilities.setCapability("autoWebView", true);
                driver = new IOSDriver<IOSElement>(new URL("http://" + FmwkConstants.USERNAME + ":" + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                break;
            case "android_app":
                capabilities.setCapability("app","bs://10435da92766886f5180e124be0fda5ff62033c9");
                capabilities.setCapability("browserstack.debug","true");
                capabilities.setCapability("build","browserstack-build-1");
                capabilities.setCapability("device","Google Pixel 3");
                capabilities.setCapability("name","Android Native App Test");
                capabilities.setCapability("os_version","9");
                capabilities.setCapability("project","Mark's Junit4 Android Project");

                driver = new AndroidDriver<AndroidElement>(new URL("http://" + FmwkConstants.USERNAME + ":" + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                break;
            case "android_web":
                // Only required to test locally
                //WebDriverManager.chromedriver().browserVersion("74").setup();
                //capabilities.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().getBinaryPath());
                //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
                //capabilities.setCapability("autoWebView", true);

                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                capabilities.setCapability("browserName", "chrome");    //chromium
                //capabilities.setCapability("seleniumVersion", "3.141.59");
                capabilities.setCapability("device", "Google Pixel 3");
                capabilities.setCapability("os_version","9");
                //capabilities.setCapability("real_mobile", "true");
                capabilities.setCapability("project", "Mobile Web Project");
                capabilities.setCapability("build", "Build Adventure XX3");
                capabilities.setCapability("name", "Android Chromium Mobile Web Test 01");
                //capabilities.setCapability("browserstack.appium_version", "1.20.2");
                driver = new AndroidDriver<AndroidElement>(new URL("http://" + FmwkConstants.USERNAME + ":" + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                break;
            default:
                throw new RuntimeException("The platform and channel was not defined");
        }
        return driver;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
