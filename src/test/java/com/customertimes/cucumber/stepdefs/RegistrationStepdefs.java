package com.customertimes.cucumber.stepdefs;

import com.customertimes.cucumber.page.MainPageCucumber;
import com.customertimes.cucumber.page.RegistrationPageCucumber;
import com.customertimes.cucumber.runner.CucumberBaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationStepdefs {
    private RegistrationPageCucumber registrationPageCucumber = new RegistrationPageCucumber(getWebDriver());
    private MainPageCucumber mainPageCucumber = new MainPageCucumber(getWebDriver());


    @Given("User goes to registration page")
    public void navigateToRegistrationPage() {
        registrationPageCucumber.openPage();
        //mainPageCucumber.closeWelcomeBanner();
    }

    @When("User enters email {string}, password {string}, repeat password {string}, chooses security question and enter answer {string}")
    public void userFillsRegistrationForm(String email, String password, String repeatPassword, String answer) {
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
