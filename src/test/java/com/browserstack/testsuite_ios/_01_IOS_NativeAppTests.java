package com.browserstack.testsuite_ios;

import com.browserstack.test_base.TestBase;
import org.junit.BeforeClass;
import org.junit.Test;

public class _01_IOS_NativeAppTests extends TestBase {

    @BeforeClass
    public static void beforeSuite() {
        setPlatform("iOS_App");
        setRealMobile("true");
        setProject("eBook.com Re-skin Project");
        setBuild("Build 1.2.1");
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
