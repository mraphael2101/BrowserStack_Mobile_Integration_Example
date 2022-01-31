package com.browserstack.testsuite_ios;

import com.browserstack.test_base.TestBase;
import org.junit.*;

public class _02_IOS_MobileWebTests extends TestBase {

    @BeforeClass
    public static void beforeSuite() {
        setPlatform("iOS_Web");
        setBrowser("chromium");
        setDevice("iPhone 11 Pro");
        setOsVersion("13.2");
        setRealMobile("true");
        setProject("eBook.com Re-skin Project");
        setBuild("Build 1.2.1");
        setBrowserstackDebug("true");
    }

    @Test
    public void iosMobileWebTest_Google() throws Exception {
        System.out.println(getDriver().getContext());
        getDriver().get("https://google.com/");
        System.out.println(getDriver().getCurrentUrl());
        Thread.sleep(3000);
    }

    @Test
    public void iosMobileWebTest_Amazon() throws Exception {
        System.out.println(getDriver().getContext());
        getDriver().get("https://amazon.com/");
        System.out.println(getDriver().getCurrentUrl());
        Thread.sleep(3000);
    }

}