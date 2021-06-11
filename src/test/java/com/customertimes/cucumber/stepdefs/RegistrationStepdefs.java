package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.MainPageCucumber;
import com.customertimes.cucumber.page.RegistrationPageCucumber;
import com.customertimes.cucumber.runner.CucumberBaseTest;
import com.customertimes.testdata.DataStore;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationStepdefs {
    private RegistrationPageCucumber registrationPageCucumber = new RegistrationPageCucumber(getWebDriver());
    private DataStore dataStore = new DataStore();


    @Given("User goes to registration page")
    public void navigateToRegistrationPage() {
        registrationPageCucumber.openPage();
    }

    @When("User enters email, password, repeat password, chooses security question and enter answer")

    public void userFillsRegistrationForm() {
        String email  =  dataStore.getUsername();
        String password = dataStore.getPassword();
        String repeatPassword = dataStore.getRepeatPassword();
        String answer = dataStore.getAnswer();
        registrationPageCucumber.enterEmail(email);
        registrationPageCucumber.enterPassword(password);
        registrationPageCucumber.enterRepeatPassword(repeatPassword);
        registrationPageCucumber.clickOnSecurityQuestion();
        registrationPageCucumber.chooseAnySecurityQuestion();
        registrationPageCucumber.enterAnswer(answer);
    }

    @And("User clicks on Register button")
    public void userClicksOnRegisterButton() {
        registrationPageCucumber.clickOnRegisterButton();
    }

    @Then("User should be registered in application with success message {string}")
    public void userShouldBeRegisteredInApplication(String expectedMessage) throws InterruptedException {
        Assert.assertEquals(registrationPageCucumber.getRegistrationSuccessMessage(), expectedMessage, "User is not registered");
    }
}
