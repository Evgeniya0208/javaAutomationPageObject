package com.customertimes.cucumber.hooks;

import com.customertimes.framework.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.customertimes.framework.driver.WebdriverRunner.closeWebDriver;
import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class WevDriverHooks {

    @Before
    public void setUp() {
        getWebDriver().get(TestConfig.CONFIG.baseUrl());
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
