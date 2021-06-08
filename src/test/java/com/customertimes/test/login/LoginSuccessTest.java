package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
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

public class LoginSuccessTest extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    LoginPage loginPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        loginPage.openPage();

        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkLoginSuccess() {

        loginPage.loginAs(customer);

        loginPage.clickOnAccountButton();

        String actualUserName = loginPage.getActualUserName(customer.getEmail());

        Assert.assertEquals(actualUserName, customer.getEmail(), "User does not match");
    }

}
