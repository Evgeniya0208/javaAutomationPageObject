package com.customertimes.test.homework4.login;

import com.customertimes.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginInvalidPasswordTest {
    WebDriverWait wait;
    String loginRegisteredUser = "evgeniya1@gmail.com";
    String passwordRegisteredUser = "1234567";

    private String invalidEmailPasswordMessage = "Invalid email or password.";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkLoginInvalidPassword() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.sendKeys(loginRegisteredUser);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.sendKeys(passwordRegisteredUser);

        WebElement logInButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        logInButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='error ng-star-inserted']")));

        String actualEmailPasswordError = getWebDriver().findElement(By.xpath("//*[@class='error ng-star-inserted']")).getText();

        Assert.assertEquals(invalidEmailPasswordMessage, actualEmailPasswordError, "Error is not expected");
    }
}
