package com.browserstack.testsuite_android;

import com.browserstack.test_base.TestBase;
import org.junit.BeforeClass;
import org.junit.Test;

public class _01_Android_NativeAppTests extends TestBase {

    @BeforeClass
    public static void beforeSuite() {
        setPlatform("Android_App");
        setRealMobile("true");
        setProject("eBook.com Re-skin Project");
        setBuild("Build 1.2.1");
    }

    @Test
    public void androidNativeAppTest_1() throws Exception {
        System.out.println(getDriver().getContext());
        Thread.sleep(3000);
    }

    @Test
    public void androidNativeAppTest_2() throws Exception {
        System.out.println(getDriver().getContext());
        Thread.sleep(3000);
    }

}