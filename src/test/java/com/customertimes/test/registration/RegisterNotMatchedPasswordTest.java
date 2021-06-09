package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.pages.MainPage;
import com.customertimes.test.BaseTest;
import com.customertimes.pages.RegistrationPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegisterNotMatchedPasswordTest extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    RegistrationPage registrationPage;
    MainPage mainPage;
    private String expectedErrorMessage = "Passwords do not match";

    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        wait = new WebDriverWait(driver, 5);
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").withRepeatPassword("12345").build();
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        registrationPage.openPage();
        mainPage.closeWelcomeBanner();
    }

    @Test
    public void checkRegFormPasswordValidation() {
        registrationPage.enterEmail(customer.getEmail());
        registrationPage.enterPassword(customer.getPassword());
        registrationPage.enterRepeatPassword(customer.getRepeatPassword());
        registrationPage.clickPassword();

        Assert.assertEquals(registrationPage.getActualPasswordDoNotMatchErrorMessage(), expectedErrorMessage, "Error message is not expected");
    }
}
