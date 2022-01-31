package com.browserstack.testsuite_ios;

import com.browserstack.test_base.TestBase;
import org.junit.BeforeClass;
import org.junit.Test;

public class _01_IOS_NativeAppTests extends TestBase {

    @BeforeClass
    public static void beforeSuite() {
        setPlatform("iOS_App");
        setAppOnCloud("bs://1036dce7c222fe25fb00d4a98e8e77a57693f160");
        setDevice("iPhone XS");
        setOsVersion("13.3");
        setRealMobile("true");
        setProject("eBook.com Re-skin Project");
        setBuild("Build 1.2.1");
        setBrowserstackDebug("true");
    }

    @Test
    public void iosNativeAppTest_1() throws Exception {
        System.out.println(getDriver().getContext());
        Thread.sleep(3000);
    }

    @Test
    public void iosNativeAppTest_2() throws Exception {
        System.out.println(getDriver().getContext());
        Thread.sleep(3000);
    }

}
