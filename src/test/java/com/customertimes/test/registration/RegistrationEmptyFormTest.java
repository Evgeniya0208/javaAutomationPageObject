package com.customertimes.test.registration;
import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.pages.MainPage;
import com.customertimes.test.BaseTest;
import com.customertimes.pages.RegistrationPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class RegistrationEmptyFormTest extends BaseTest {

    RegistrationPage registrationPage;
    MainPage mainPage;
    private String emptyEmailMessage = "Please provide an email address.";
    private String emptyPasswordMessage = "Please provide a password.";
    private String emptyRepeatPasswordMessage = "Please repeat your password.";
    private String emptySecurityQuestionMessage = "Please select a security question.";
    private String emptyAnswerMessage = "Please provide an answer to your security question.";


    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        registrationPage.openPage();
        mainPage.closeWelcomeBanner();
    }

    @Test
    public void checkEmptyRegFormValidation()
    {
        registrationPage.emptyRegistrationFormWithErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registrationPage.getActualEmptyEmailErrorMessage(), emptyEmailMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyPasswordErrorMessage(), emptyPasswordMessage, "Error 0message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyRepeatPasswordErrorMessage(), emptyRepeatPasswordMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptySecurityQuestionErrorMessage(), emptySecurityQuestionMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPage.getActualEmptyAnswerErrorMessage(), emptyAnswerMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
