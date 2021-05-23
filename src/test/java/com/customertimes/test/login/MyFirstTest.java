package com.customertimes.test.login;

import com.customertimes.test.BaseTest;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class MyFirstTest extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is after class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("This is before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is after method");
    }


    @Test
    public void checkSiteTitle()
    {
        //WebDriverManager.firefoxdriver().setup();
        //ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--start-fullscreen");
        //chromeOptions.addExtensions()
        //WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com/");

        try {
            Thread.sleep(15_000);
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
