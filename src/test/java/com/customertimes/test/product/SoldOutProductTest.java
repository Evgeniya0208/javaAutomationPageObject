package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.BasketPage;
import com.customertimes.test.pages.LoginPage;
import com.customertimes.test.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 10);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();

        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        loginPage.loginAs(customer);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span")));
        WebElement dismissCookie = getWebDriver().findElement(By.xpath("//*[@aria-label='dismiss cookie message']"));
        dismissCookie.click();
        mainPage = new MainPage(driver);
        js = (JavascriptExecutor)WebdriverRunner.getWebDriver();
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
