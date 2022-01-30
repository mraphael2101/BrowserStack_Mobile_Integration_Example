package com.browserstack.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static AppiumDriver<?> driver;
    private static String platform = "UNDEFINED",
                             build = "UNDEFINED",
                             project = "UNDEFINED",
                             testName = "UNDEFINED",
                             realMobile = "false";

    public static AppiumDriver<?> getDriver() {
        return driver;
    }

    public static void initializeDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("seleniumVersion", "3.141.59");
        capabilities.setCapability("browserstack.appium_version", "1.20.2");
        capabilities.setCapability("browserstack.debug","true");
        capabilities.setCapability("real_mobile", realMobile);
        capabilities.setCapability("build", build);
        capabilities.setCapability("project", project);
        capabilities.setCapability("name", testName);

        switch (platform.toLowerCase()) {
            case "ios_app":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability("autoWebView", false);
                capabilities.setCapability("app","bs://1036dce7c222fe25fb00d4a98e8e77a57693f160");
                capabilities.setCapability("device","iPhone 11 Pro");
                capabilities.setCapability("os_version","13");
                try {
                    driver = new IOSDriver<IOSElement>(new URL("http://" + FmwkConstants.USERNAME + ":"
                            + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case "ios_web":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability("autoWebView", true);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
                capabilities.setCapability("device", "iPhone XS");
                capabilities.setCapability("os_version","13.3");
                try {
                    driver = new IOSDriver<IOSElement>(new URL("http://" + FmwkConstants.USERNAME + ":"
                            + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case "android_app":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                capabilities.setCapability("autoWebView", false);
                capabilities.setCapability("app","bs://10435da92766886f5180e124be0fda5ff62033c9");
                capabilities.setCapability("device","Google Pixel 3");
                capabilities.setCapability("os_version","9");
                try {
                    driver = new AndroidDriver<AndroidElement>(new URL("http://" + FmwkConstants.USERNAME + ":"
                            + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case "android_web":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                capabilities.setCapability("autoWebView", true);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROMIUM);  // OR CHROME
                capabilities.setCapability("device", "Google Pixel 3");
                capabilities.setCapability("os_version","9");
                try {
                    driver = new AndroidDriver<AndroidElement>(new URL("http://" + FmwkConstants.USERNAME + ":"
                            + FmwkConstants.ACCESS_KEY + "@" + FmwkConstants.SERVER + "/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                throw new RuntimeException("The platform and channel was not defined");
        }
    }

    public static void setBuild(String build) {
        DriverManager.build = build;
    }

    public static void setProject(String project) {
        DriverManager.project = project;
    }

    public static void setTestName(String testName) {
        DriverManager.testName = testName;
    }

    public static void setRealMobile(String realMobile) {
        DriverManager.realMobile = realMobile;
    }

    public static void setPlatform(String platform) {
        DriverManager.platform = platform;
    }

}
