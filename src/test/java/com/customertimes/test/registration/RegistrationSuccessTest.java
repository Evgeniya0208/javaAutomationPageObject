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

public class RegistrationSuccessTest extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    RegistrationPage registrationPage;
    private String expectedSuccessfulMessage = "Registration completed successfully. You can now log in.";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        customer = Customer.newBuilder().withName("evgeniya" + System.currentTimeMillis() + "@gmail.com").withPassword("123456").withRepeatPassword("123456").withAnswer("Cat").build();
        registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkUserCanRegister() {
        registrationPage.registerAsCustomer(customer);

        WebElement registrationSuccessMessage = registrationPage.getRegistrationSuccessMessage();
        Assert.assertEquals(registrationSuccessMessage.getText(), expectedSuccessfulMessage, "Message is not expected");
    }
}
