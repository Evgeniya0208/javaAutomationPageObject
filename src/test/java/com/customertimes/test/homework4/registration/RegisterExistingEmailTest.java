package com.customertimes.test.homework4.registration;

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

public class RegisterExistingEmailTest extends BaseTest {
    WebDriverWait wait;
    private String userEmail = "evgeniya1@gmail.com";
    private String password = "123456";
    private String answer = "Cat";
    private String expectedErrorMessage = "Email must be unique";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/register");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void checkRegistrationWithExistingUser() {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Email address field']"));
        emailField.sendKeys(userEmail);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Field for the password']"));
        passwordField.sendKeys(password);

        WebElement repeatPasswordField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Field to confirm the password']"));
        repeatPasswordField.sendKeys(password);

        WebElement securityQuestion = getWebDriver().findElement(By.cssSelector("[name = securityQuestion]"));
        securityQuestion.click();

        WebElement anySecurityQuestion = getWebDriver().findElement(By.cssSelector(".mat-option-text"));
        anySecurityQuestion.click();

        WebElement answerField = getWebDriver().findElement(By.id("securityAnswerControl"));
        answerField.clear();
        answerField.sendKeys(answer);

        WebElement registerButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        registerButton.click();

        wait.until(ExpectedConditions.attributeToBeNotEmpty(getWebDriver().findElement(By.xpath("//*[@aria-label = 'Email address field']/ancestor::mat-card/*[@class='error']")), "innerText"));

        WebElement actualErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Email address field']/ancestor::mat-card/*[@class='error']"));
        Assert.assertEquals(actualErrorMessage.getText(), expectedErrorMessage, "Error message is not expected");
    }
}
