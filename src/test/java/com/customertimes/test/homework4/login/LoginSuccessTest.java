package com.customertimes.test.homework4.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginSuccessTest extends BaseTest {
    WebDriverWait wait;
    String loginRegisteredUser = "evgeniya1@gmail.com";;
    String passwordRegisteredUser = "123456";

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
    public void checkLoginSuccess() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.sendKeys(loginRegisteredUser);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.sendKeys(passwordRegisteredUser);

        WebElement logInButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        logInButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navbarAccount")));
        WebElement navbarAccount = getWebDriver().findElement(By.id("navbarAccount"));
        navbarAccount.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[aria-label='Go to user profile'] span")));

        WebElement userAccount = getWebDriver().findElement(By.cssSelector("button[aria-label='Go to user profile'] span"));
        String actualUserName = userAccount.getText();
        Assert.assertEquals(actualUserName, loginRegisteredUser, "User does not match");
    }
}
