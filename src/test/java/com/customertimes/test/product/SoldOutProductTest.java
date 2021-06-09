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
    WebDriverWait wait;
    LoginPage loginPage;
    Customer customer;
    MainPage mainPage;
    JavascriptExecutor js;
    private String expectedErrorMessage = "We are out of stock! Sorry for the inconvenience.";

    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 10);
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        js = (JavascriptExecutor)WebdriverRunner.getWebDriver();
        mainPage = new MainPage(driver);

        loginPage.openPage();
        loginPage.loginAs(customer);
        mainPage.dismissCookie();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
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
