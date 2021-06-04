package com.customertimes.test.homework2;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class FacebookTitleTest extends BaseTest {

    @Test
    public void checkFacebookTitle() {
        getWebDriver().get("https://www.facebook.com/");

        String expectedTitle = "Facebook — увійдіть або зареєструйтеся";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title is not expected");
    }
}
