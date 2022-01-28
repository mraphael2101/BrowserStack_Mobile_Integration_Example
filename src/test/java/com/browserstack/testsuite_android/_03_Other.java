package com.browserstack.testsuite_android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class _03_Other {
    public static final String USERNAME = "markraphael_28PLMH";
    public static final String AUTOMATE_KEY = "acU3hx7t8qnezzzUdsoK";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("name", "My First Test");
        caps.setCapability("build", "Build #1");
        caps.setCapability("project", "Sample sandbox project");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("http://www.google.com");

        // Your test script like you usually write

        driver.quit();
    }
}