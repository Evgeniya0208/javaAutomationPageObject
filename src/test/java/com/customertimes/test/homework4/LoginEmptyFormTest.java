package com.customertimes.test.homework4;

import com.customertimes.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginEmptyFormTest {

    private String emptyEmailFieldMessage = "Please provide an email address.";
    private String emptyPasswordFieldMessage = "Please provide a password.";

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
    public void CheckLoginEmptyForm() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.click();

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.click();
        passwordField.sendKeys(Keys.TAB);

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String actualEmailErrorMessage = getWebDriver().findElement(By.xpath("//input[@name = 'email']/../../following-sibling::div/div/mat-error")).getText();
        String actualPasswordErrorMessage = getWebDriver().findElement(By.xpath("//input[@name = 'password']/../../following-sibling::div/div/mat-error")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailErrorMessage, emptyEmailFieldMessage, "Error message is not expected");
        softAssert.assertEquals(actualPasswordErrorMessage, emptyPasswordFieldMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
