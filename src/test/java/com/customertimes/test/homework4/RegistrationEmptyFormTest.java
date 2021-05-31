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
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationEmptyFormTest extends BaseTest {

    private String emptyEmailMessage = "Please provide an email address.";
    private String emptyPasswordMessage = "Please provide a password.";
    private String emptyRepeatPasswordMessage = "Please repeat your password.";
    private String emptySecurityQuestionMessage = "Please select a security question.";
    private String emptyAnswerMessage = "Please provide an answer to your security question.";


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
    public void CheckEmptyRegFormValidation()
    {
        WebElement emailField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Email address field']"));
        emailField.click();

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Field for the password']"));
        passwordField.click();

        WebElement repeatPasswordField = getWebDriver().findElement(By.cssSelector("[aria-label = 'Field to confirm the password']"));
        repeatPasswordField.click();

        WebElement securityQuestion = getWebDriver().findElement(By.cssSelector("[name = securityQuestion]"));
        securityQuestion.sendKeys(Keys.ESCAPE);

        WebElement answerField = getWebDriver().findElement(By.id("securityAnswerControl"));
        answerField.click();

        answerField.sendKeys(Keys.TAB);

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement actualEmailErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Email address field']/../../../div/following-sibling::div/div/mat-error"));
        WebElement actualPasswordErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Field for the password']/../../following-sibling::div/div/mat-error"));
        WebElement actualRepeatPasswordErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Field to confirm the password']/../../following-sibling::div/div/mat-error"));
        WebElement actualSecurityQuestionErrorMessage = getWebDriver().findElement(By.xpath("//*[@name = 'securityQuestion']/../../following-sibling::div/div/mat-error"));
        WebElement actualAnswerErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-owns = 'securityAnswerControl']/../../../following-sibling::div/div/mat-error"));


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailErrorMessage.getText(), emptyEmailMessage, "Error message is not expected");
        softAssert.assertEquals(actualPasswordErrorMessage.getText(), emptyPasswordMessage, "Error 0message is not expected");
        softAssert.assertEquals(actualRepeatPasswordErrorMessage.getText(), emptyRepeatPasswordMessage, "Error message is not expected");
        softAssert.assertEquals(actualSecurityQuestionErrorMessage.getText(), emptySecurityQuestionMessage, "Error message is not expected");
        softAssert.assertEquals(actualAnswerErrorMessage.getText(), emptyAnswerMessage, "Error message is not expected");
        softAssert.assertAll();
    }

}
