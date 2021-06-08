package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginInvalidEmailTest extends BaseTest {
    WebDriverWait wait;
    private String invalidEmailPasswordMessage = "Invalid email or password.";
    Customer customer;
    LoginPage loginPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        customer = Customer.newBuilder().withName("evgeniya123@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkLoginInvalidEmail() {
        loginPage.loginAs(customer);

        String actualEmailPasswordError = loginPage.getActualInvalidEmailPasswordError();

        Assert.assertEquals(invalidEmailPasswordMessage, actualEmailPasswordError, "Error is not expected");
    }
}
