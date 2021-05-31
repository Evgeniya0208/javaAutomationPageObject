package com.customertimes.test.homework4;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegisterNotMatchedPasswordTest extends BaseTest {
    private String userEmail = "evgeniya1@gmail.com";
    private String password = "123456";
    private String repeatPassword = "12345";
    private String expectedErrorMessage = "Passwords do not match";

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/register");
        Thread.sleep(1_000);
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void CheckRegFormPasswordValidation() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Email address field']"));
        emailField.sendKeys(userEmail);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Field for the password']"));
        passwordField.sendKeys(password);

        WebElement repeatPasswordField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Field to confirm the password']"));
        repeatPasswordField.sendKeys(repeatPassword);
        repeatPasswordField.sendKeys(Keys.TAB);

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement actualErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Field to confirm the password']/../../following-sibling::div/div/mat-error"));
        Assert.assertEquals(actualErrorMessage.getText(), expectedErrorMessage, "Error message is not expected");
    }

}
