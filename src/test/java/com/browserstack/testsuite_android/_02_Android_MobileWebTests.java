package com.browserstack.testsuite_android;

import com.browserstack.test_base.TestBase;
import org.junit.BeforeClass;
import org.junit.Test;

public class _02_Android_MobileWebTests extends TestBase {

    @BeforeClass
    public static void beforeSuite() {
        setPlatform("Android_Web");
        setBrowser("chrome");
        setDevice("Samsung Galaxy S10e");
        setOsVersion("9.0");
        setRealMobile("true");
        setProject("eBook.com Re-skin Project");
        setBuild("Build 1.2.1");
        setBrowserstackDebug("true");
    }

    @Test
    public void androidMobileWebTest_Google() throws Exception {
        System.out.println(getDriver().getContext());
        getDriver().get("https://google.com/");
        System.out.println(getDriver().getCurrentUrl());
        Thread.sleep(3000);
    }

    @Test
    public void androidMobileWebTest_Amazon() throws Exception {
        System.out.println(getDriver().getContext());
        getDriver().get("https://amazon.com/");
        System.out.println(getDriver().getCurrentUrl());
        Thread.sleep(3000);
    }

}
