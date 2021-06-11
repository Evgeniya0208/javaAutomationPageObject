package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.MainPageCucumber;
import com.customertimes.cucumber.page.RegistrationPageCucumber;
import com.customertimes.cucumber.runner.CucumberBaseTest;
import com.customertimes.testdata.DataStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class EmptyRegisterFormStepdefs {
    private RegistrationPageCucumber registrationPageCucumber = new RegistrationPageCucumber(getWebDriver());
    private DataStore dataStore = new DataStore();


    @Given("User navigates to registration page")
    public void navigateToRegistrationPage() {
        registrationPageCucumber.openPage();
    }

    @When("User lefts all fields empty")
    public void userLeftsAllFieldsEmpty() {
        registrationPageCucumber.emptyRegistrationFormWithErrors();
    }

    @Then("Error message should be displayed on every field")
    public void errorMessageShouldBeDisplayedOnEveryField() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyEmailErrorMessage(), dataStore.getEmptyEmailMessage(), "Error message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyPasswordErrorMessage(), dataStore.getEmptyPasswordMessage(), "Error 0message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyRepeatPasswordErrorMessage(), dataStore.getEmptyRepeatPasswordMessage(), "Error message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptySecurityQuestionErrorMessage(), dataStore.getEmptySecurityQuestionMessage(), "Error message is not expected");
        softAssert.assertEquals(registrationPageCucumber.getActualEmptyAnswerErrorMessage(), dataStore.getEmptyAnswerMessage(), "Error message is not expected");
        softAssert.assertAll();
    }
}
