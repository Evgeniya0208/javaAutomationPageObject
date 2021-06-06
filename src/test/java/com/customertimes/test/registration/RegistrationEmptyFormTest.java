package com.customertimes.test.registration;
import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationEmptyFormTest extends BaseTest {

    WebDriverWait wait;
    RegistrationPage registrationPage;
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
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkEmptyRegFormValidation()
    {
        registrationPage.emptyRegistrationFormWithErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registrationPage.getActualEmptyEmailErrorMessage(), emptyEmailMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyPasswordErrorMessage(), emptyPasswordMessage, "Error 0message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyRepeatPasswordErrorMessage(), emptyRepeatPasswordMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyRepeatPasswordErrorMessage(), emptySecurityQuestionMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyAnswerErrorMessage(), emptyAnswerMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
