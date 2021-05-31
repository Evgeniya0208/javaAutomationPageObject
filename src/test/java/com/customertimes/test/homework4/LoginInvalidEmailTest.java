package com.customertimes.test.homework4;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginInvalidEmailTest extends BaseTest {
    String loginRegisteredUser = "evgeniya123@gmail.com";
    String passwordRegisteredUser = "123456";
    private String invalidEmailPasswordMessage = "Invalid email or password.";

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        Thread.sleep(1_000);
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void CheckLoginInvalidEmail() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.sendKeys(loginRegisteredUser);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.sendKeys(passwordRegisteredUser);

        WebElement logInButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        logInButton.click();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String actualEmailPasswordError = getWebDriver().findElement(By.xpath("//*[@class='error ng-star-inserted']")).getText();
        Assert.assertEquals(invalidEmailPasswordMessage, actualEmailPasswordError, "Error is not expected");
    }
}
