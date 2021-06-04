package com.customertimes.test.homework4.registration;
import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationEmptyFormTest extends BaseTest {

    WebDriverWait wait;
    private String emptyEmailMessage = "Please provide an email address.";
    private String emptyPasswordMessage = "Please provide a password.";
    private String emptyRepeatPasswordMessage = "Please repeat your password.";
    private String emptySecurityQuestionMessage = "Please select a security question.";
    private String emptyAnswerMessage = "Please provide an answer to your security question.";


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
    public void checkEmptyRegFormValidation()
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

        WebElement actualEmailErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Email address field']/ancestor::mat-form-field//mat-error"));
        WebElement actualPasswordErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Field for the password']/ancestor::mat-form-field//mat-error"));
        WebElement actualRepeatPasswordErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Field to confirm the password']/ancestor::mat-form-field//mat-error"));
        WebElement actualSecurityQuestionErrorMessage = getWebDriver().findElement(By.xpath("//*[@name = 'securityQuestion']/ancestor::mat-form-field//mat-error"));
        WebElement actualAnswerErrorMessage = getWebDriver().findElement(By.xpath("//*[@aria-owns = 'securityAnswerControl']/ancestor::mat-form-field//mat-error"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailErrorMessage.getText(), emptyEmailMessage, "Error message is not expected");
        softAssert.assertEquals(actualPasswordErrorMessage.getText(), emptyPasswordMessage, "Error 0message is not expected");
        softAssert.assertEquals(actualRepeatPasswordErrorMessage.getText(), emptyRepeatPasswordMessage, "Error message is not expected");
        softAssert.assertEquals(actualSecurityQuestionErrorMessage.getText(), emptySecurityQuestionMessage, "Error message is not expected");
        softAssert.assertEquals(actualAnswerErrorMessage.getText(), emptyAnswerMessage, "Error message is not expected");
        softAssert.assertAll();
    }

}
