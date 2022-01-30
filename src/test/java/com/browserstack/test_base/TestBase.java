package com.browserstack.test_base;

import com.browserstack.config.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public class TestBase extends DriverManager {

    @Rule
    public TestName name = new TestName();

    @Before
    public void beforeTest() {
        setTestName(name.getMethodName());
        initializeDriver();
    }

    @After
    public void tearDown() throws Exception {
        getDriver().quit();
    }

}
