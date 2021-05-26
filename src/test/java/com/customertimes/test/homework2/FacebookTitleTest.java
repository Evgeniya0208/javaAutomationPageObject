package com.customertimes.test.homework2;

import com.customertimes.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FacebookTitleTest extends BaseTest {

    @Test
    public void checkFacebookTitle() {
        driver.get("https://www.facebook.com/");

        String expectedTitle = "Facebook — Выполните вход или зарегистрируйтесь";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title is not expected");
    }
}
