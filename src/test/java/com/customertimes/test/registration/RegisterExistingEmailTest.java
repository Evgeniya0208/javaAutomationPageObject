package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegisterExistingEmailTest extends BaseTest {
    WebDriverWait wait;
    private String expectedErrorMessage = "Email must be unique";
    Customer customer;
    RegistrationPage registrationPage;


    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").withRepeatPassword("123456").withAnswer("Cat").build();
        registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void checkRegistrationWithExistingUser() {

        registrationPage.registerAsCustomer(customer);

        WebElement actualErrorMessage = registrationPage.getUserExistsErrorMessage();
        Assert.assertEquals(actualErrorMessage.getText(), expectedErrorMessage, "Error message is not expected");
    }
}
