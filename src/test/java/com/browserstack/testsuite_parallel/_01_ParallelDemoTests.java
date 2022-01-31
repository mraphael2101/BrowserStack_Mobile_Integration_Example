package com.browserstack.testsuite_parallel;

import com.browserstack.config.parallel.DriverManager_Parallelized;
import org.junit.Test;

public class _01_ParallelDemoTests extends DriverManager_Parallelized {

    @Test
    public void androidNativeAppTest_Parallel() throws Exception {
        System.out.println(getDriver().getContext());
        Thread.sleep(3000);
    }

}
