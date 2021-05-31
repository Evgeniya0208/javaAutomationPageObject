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

public class LoginSuccessTest extends BaseTest {

    String loginRegisteredUser = "evgeniya1@gmail.com";;
    String passwordRegisteredUser = "123456";

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
    public void CheckLoginSuccess() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.sendKeys(loginRegisteredUser);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.sendKeys(passwordRegisteredUser);

        WebElement logInButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        logInButton.click();

        getWebDriver().findElement(By.id("navbarAccount")).click();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String actualUserName = getWebDriver().findElement(By.cssSelector("button[aria-label='Go to user profile'] span")).getText();

        Assert.assertEquals(actualUserName, loginRegisteredUser, "User does not match");
    }
}
