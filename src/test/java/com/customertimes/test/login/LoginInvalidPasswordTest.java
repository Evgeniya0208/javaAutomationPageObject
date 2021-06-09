package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.pages.LoginPage;
import com.customertimes.pages.MainPage;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginInvalidPasswordTest extends BaseTest {
    Customer customer;
    LoginPage loginPage;
    MainPage mainPage;

    private String invalidEmailPasswordMessage = "Invalid email or password.";

    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("1234567").build();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        loginPage.openPage();
        mainPage.closeWelcomeBanner();
    }

    @Test
    public void checkLoginInvalidPassword() {
        loginPage.loginAs(customer);

        String actualEmailPasswordError = loginPage.getActualInvalidEmailPasswordError();

        Assert.assertEquals(invalidEmailPasswordMessage, actualEmailPasswordError, "Error is not expected");
    }
}
