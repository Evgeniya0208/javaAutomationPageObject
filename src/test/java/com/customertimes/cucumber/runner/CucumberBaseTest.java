package com.customertimes.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions (
        plugin = "pretty",
        monochrome = true,
        tags = "smoke",
        glue = "io.ctdev.cucumber",
        features = "classpath: io/ctdev/features")

public class CucumberBaseTest extends AbstractTestNGCucumberTests {

}
