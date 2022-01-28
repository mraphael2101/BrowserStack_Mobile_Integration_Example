package com.browserstack.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DriverManager {
    private static AppiumDriver<?> driver;
    private static String platform = "UNDEFINED";
    private static final String USERNAME = "markraphael_28PLMH";
    private static final String ACCESS_KEY = "acU3hx7t8qnezzzUdsoK";
    private static final String SERVER = "hub-cloud.browserstack.com";

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
                driver = new IOSDriver<IOSElement>(new URL("http://" + USERNAME + ":" + ACCESS_KEY + "@" + SERVER + "/wd/hub"), capabilities);
                break;
            case "ios_web":
                WebDriverManager.chromedriver().browserVersion("74").setup();
                capabilities.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().getBinaryPath());
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
                capabilities.setCapability("autoWebView", true);
                driver = new IOSDriver<IOSElement>(new URL("http://" + USERNAME + ":" + ACCESS_KEY + "@" + SERVER + "/wd/hub"), capabilities);
                break;
            case "android_app":
                capabilities.setCapability("app","bs://10435da92766886f5180e124be0fda5ff62033c9");
                capabilities.setCapability("browserstack.debug","true");
                capabilities.setCapability("build","browserstack-build-1");
                capabilities.setCapability("device","Google Pixel 3");
                capabilities.setCapability("name","Android Native App Test");
                capabilities.setCapability("os_version","9");
                capabilities.setCapability("project","Mark's Junit4 Android Project");

                driver = new AndroidDriver<AndroidElement>(new URL("http://" + USERNAME + ":" + ACCESS_KEY + "@" + SERVER + "/wd/hub"), capabilities);
                break;
            case "android_web":
                // Only required to test locally
                //WebDriverManager.chromedriver().browserVersion("74").setup();
                //capabilities.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().getBinaryPath());
                //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
                //capabilities.setCapability("autoWebView", true);
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("seleniumVersion", "3.141.59");
                capabilities.setCapability("buildName","browserstack-build-1");
                capabilities.setCapability("device","Google Pixel 3");
                capabilities.setCapability("os_version","9");
                driver = new AndroidDriver<AndroidElement>(new URL("http://" + USERNAME + ":" + ACCESS_KEY + "@" + SERVER + "/wd/hub"), capabilities);
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
