package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class MyFirstTest extends BaseTest {


    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }


    @Test
    public void checkSiteTitle()
    {
        //WebDriverManager.firefoxdriver().setup();
        //ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--start-fullscreen");
        //chromeOptions.addExtensions()
        //WebDriver driver = new FirefoxDriver();
        WebdriverRunner.getWebDriver().get("https://www.google.com/");

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(2, 2);
        softAssert.assertEquals(actualTitle, expectedTitle, "title is not expected");
        softAssert.assertAll();

        //Assert.assertEquals(actualTitle, expectedTitle, "title is not expected");
    }
}
