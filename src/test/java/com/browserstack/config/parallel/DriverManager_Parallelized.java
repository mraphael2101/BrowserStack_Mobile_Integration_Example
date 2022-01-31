package com.browserstack.config.parallel;

import com.browserstack.config.FmwkConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RunWith(Parallelized.class)
public class DriverManager_Parallelized {
    private static DesiredCapabilities capabilities;
    private static AppiumDriver<?> driver;
    private static JSONObject config;
    private static String app = "POPULATE IF APPLICABLE",
            browser = "POPULATE IF APPLICABLE",
            device = "NOT DECLARED",
            osVersion = "NOT DECLARED",
            platform = "NOT DECLARED",
            build = "NOT DECLARED",
            project = "NOT DECLARED",
            testName = "NOT DECLARED",
            realMobile = "false",
            browserstackSeleniumVersion = "3.141.59",
            browserstackAppiumVersion = "1.20.2",
            browserstackDebug = "false";

    public static AppiumDriver<?> getDriver() {
        return driver;
    }

    @Parameter(value = 0)
    private static int taskID;

    @Parameters
    private static Iterable<? extends Object> data() {
        List<Integer> taskIDs = new ArrayList<Integer>();
        JSONParser parser = new JSONParser();
        capabilities = new DesiredCapabilities();
        try {
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/browserstack/parallel.conf.json"));
            int envs = ((JSONArray) config.get("environments")).size();
            for (int i = 0; i < envs; i++) {
                taskIDs.add(i);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return taskIDs;
    }

    @Before
    @Parameters
    public static void initializeDriver() {
        data();
        JSONArray envs = (JSONArray) config.get("environments");

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskID);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        capabilities.setCapability("seleniumVersion", browserstackSeleniumVersion);
        capabilities.setCapability("browserstack.appium_version", browserstackAppiumVersion);
        capabilities.setCapability("real_mobile", realMobile);

        switch (capabilities.getCapability("platform").toString().toLowerCase()) {
            case "ios_app":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability("autoWebView", false);
                capabilities.setCapability("app", app);
                capabilities.setCapability("device", device);
                capabilities.setCapability("os_version", osVersion);
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
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
                capabilities.setCapability("device", device);
                capabilities.setCapability("os_version", osVersion);
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
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
                capabilities.setCapability("device",  device);
                capabilities.setCapability("os_version", osVersion);
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

    @After
    public void tearDown() throws Exception {
        getDriver().quit();
    }

    public static void setAppOnCloud(String app) {
        DriverManager_Parallelized.app = app;
    }

    public static void setBrowser(String browser) {
        DriverManager_Parallelized.browser = browser;
    }

    public static void setDevice(String device) {
        DriverManager_Parallelized.device = device;
    }

    public static void setOsVersion(String osVersion) {
        DriverManager_Parallelized.osVersion = osVersion;
    }

    public static void setBuild(String build) {
        DriverManager_Parallelized.build = build;
    }

    public static void setProject(String project) {
        DriverManager_Parallelized.project = project;
    }

    public static void setTestName(String testName) {
        DriverManager_Parallelized.testName = testName;
    }

    public static void setRealMobile(String realMobile) {
        DriverManager_Parallelized.realMobile = realMobile;
    }

    public static void setPlatform(String platform) {
        DriverManager_Parallelized.platform = platform;
    }

    public static void setBrowserstackSeleniumVersion(String browserstackSeleniumVersion) {
        DriverManager_Parallelized.browserstackSeleniumVersion = browserstackSeleniumVersion;
    }

    public static void setBrowserstackAppiumVersion(String browserstackAppiumVersion) {
        DriverManager_Parallelized.browserstackAppiumVersion = browserstackAppiumVersion;
    }

    public static void setBrowserstackDebug(String browserstackDebug) {
        DriverManager_Parallelized.browserstackDebug = browserstackDebug;
    }
}
