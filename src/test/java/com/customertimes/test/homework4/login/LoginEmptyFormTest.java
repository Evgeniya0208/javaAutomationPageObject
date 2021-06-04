package com.customertimes.test.homework4.login;

import com.customertimes.framework.driver.WebdriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginEmptyFormTest {

    WebDriverWait wait;
    private String emptyEmailFieldMessage = "Please provide an email address.";
    private String emptyPasswordFieldMessage = "Please provide a password.";

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
    public void checkLoginEmptyForm() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.click();

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.click();
        passwordField.sendKeys(Keys.TAB);


        WebElement emailError = wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.xpath("//input[@name = 'email']/ancestor::mat-form-field//mat-error"))));
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.xpath("//input[@name = 'password']/ancestor::mat-form-field//mat-error"))));
        String actualEmailErrorMessage = emailError.getText();
        String actualPasswordErrorMessage = passwordError.getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailErrorMessage, emptyEmailFieldMessage, "Error message is not expected");
        softAssert.assertEquals(actualPasswordErrorMessage, emptyPasswordFieldMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
