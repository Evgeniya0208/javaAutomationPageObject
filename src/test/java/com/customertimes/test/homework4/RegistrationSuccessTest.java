package com.customertimes.test.homework4;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationSuccessTest extends BaseTest {

    private String userEmail;
    private String password = "123456";
    private String answer = "Cat";
    private String expectedSuccessfulMessage = "Registration completed successfully. You can now log in.";

    @BeforeClass
    public void setup() throws InterruptedException {
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/register");
        Thread.sleep(1_000);
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
        userEmail = "evgeniya" + System.currentTimeMillis() + "@gmail.com";
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void CheckUserCanRegister()
    {
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

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement registrationSuccessMessage = getWebDriver().findElement(By.xpath("//*[@class = 'mat-simple-snackbar ng-star-inserted']/span"));
        Assert.assertEquals(registrationSuccessMessage.getText(), expectedSuccessfulMessage, "Message is not expected");
    }
}
