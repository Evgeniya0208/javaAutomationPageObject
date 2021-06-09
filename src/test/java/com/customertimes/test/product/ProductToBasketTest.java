package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.pages.BasketPage;
import com.customertimes.pages.LoginPage;
import com.customertimes.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class ProductToBasketTest extends BaseTest {
    WebDriverWait wait;
    LoginPage loginPage;
    Customer customer;
    Product product;
    MainPage mainPage;
    BasketPage basketPage;
    private String productAddedToBasketMessage = "Placed Apple Juice (1000ml) into basket.";
    private String expectedProductCounter = "1";

    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        wait = new WebDriverWait(driver, 10);
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver);

        loginPage.openPage();
        mainPage.closeWelcomeBanner();
        loginPage.loginAs(customer);

        if (!mainPage.getBasketCounter().equals("0"))
        {
            mainPage.clickOnBasket();
            basketPage.getEmptyBasket();
        }
        basketPage.returnToMainPage();

        product = Product.newBuilder().withName(" Apple Juice (1000ml)  1 1.99¤").build();
    }


    @AfterClass
    public void cleanData() {
        basketPage.getEmptyBasket();
        }

    @Test
    public void checkAddingProductToBasket() {
        mainPage.addProductToBasket();

        WebElement actualProductMessage = mainPage.getAddToBasketSuccessMessage();
        Assert.assertEquals(actualProductMessage.getText(), productAddedToBasketMessage, "Message is not expected");
        Assert.assertEquals(mainPage.getBasketCounter(), expectedProductCounter, "Product quantity is not expected");

        mainPage.clickOnBasket();

        WebElement userBasketContent = basketPage.getUserBasketContent();
        Assert.assertEquals(userBasketContent.getAttribute("textContent"), product.getName(), "Product is not expected");
    }
}
