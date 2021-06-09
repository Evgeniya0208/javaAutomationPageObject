package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.pages.LoginPage;
import com.customertimes.pages.MainPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class SoldOutProductTest extends BaseTest {
    LoginPage loginPage;
    Customer customer;
    MainPage mainPage;
    private String expectedErrorMessage = "We are out of stock! Sorry for the inconvenience.";

    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        loginPage.openPage();
        mainPage.closeWelcomeBanner();
        loginPage.loginAs(customer);
        mainPage.dismissCookie();
    }

    @Test
    public void checkAddingSoldOutProductToBasket() {
        mainPage.goPageDown();

        mainPage.goNextPage();

        mainPage.goSoldOutProduct();

        mainPage.addSoldOutToBasket();

        Assert.assertEquals(mainPage.getSoldOutErrorMessage(), expectedErrorMessage, "Message is not expected");
    }



}
