package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.test.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginInvalidPasswordTest {
    WebDriverWait wait;
    Customer customer;
    LoginPage loginPage;

    private String invalidEmailPasswordMessage = "Invalid email or password.";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("1234567").build();
        loginPage = new LoginPage(getWebDriver());
        loginPage.openPage();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkLoginInvalidPassword() {
        loginPage.loginAs(customer);

        String actualEmailPasswordError = loginPage.getActualInvalidEmailPasswordError();

        Assert.assertEquals(invalidEmailPasswordMessage, actualEmailPasswordError, "Error is not expected");
    }
}
