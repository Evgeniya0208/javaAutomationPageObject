package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.MainPageCucumber;
import com.customertimes.cucumber.page.RegistrationPageCucumber;
import com.customertimes.cucumber.runner.CucumberBaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class EmptyRegisterFormStepdefs {
    private RegistrationPageCucumber registrationPageCucumber = new RegistrationPageCucumber(getWebDriver());
    private MainPageCucumber mainPageCucumber = new MainPageCucumber(getWebDriver());
    private String emptyEmailMessage = "Please provide an email address.";
    private String emptyPasswordMessage = "Please provide a password.";
    private String emptyRepeatPasswordMessage = "Please repeat your password.";
    private String emptySecurityQuestionMessage = "Please select a security question.";
    private String emptyAnswerMessage = "Please provide an answer to your security question.";

    @Given("User navigates to registration page")
    public void navigateToRegistrationPage() {
        registrationPageCucumber.openPage();
        //mainPageCucumber.closeWelcomeBanner();
    }

    @When("User lefts all fields empty")
    public void userLeftsAllFieldsEmpty() {
        registrationPageCucumber.emptyRegistrationFormWithErrors();
    }

    @Then("Error message should be displayed on every field")
    public void errorMessageShouldBeDisplayedOnEveryField() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyEmailErrorMessage(), emptyEmailMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyPasswordErrorMessage(), emptyPasswordMessage, "Error 0message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyRepeatPasswordErrorMessage(), emptyRepeatPasswordMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptySecurityQuestionErrorMessage(), emptySecurityQuestionMessage, "Error message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyAnswerErrorMessage(), emptyAnswerMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
