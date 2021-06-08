package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.BasketPage;
import com.customertimes.test.pages.LoginPage;
import com.customertimes.test.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 10);
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").build();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        basketPage = new BasketPage(driver);

        loginPage.openPage();
        loginPage.loginAs(customer);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span")));
        WebElement productCounter = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span"));

        if (!productCounter.getAttribute("innerText").equals("0")){
            getWebDriver().findElement(By.xpath("//*[@aria-label = 'Show the shopping cart']")).click();
            List<WebElement> basketProducts = getWebDriver().findElements(By.xpath(".//mat-row"));

            for (WebElement basketProduct : basketProducts) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//mat-row")));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-icon='trash-alt']")));
                getWebDriver().findElement(By.xpath("//*[@data-icon='trash-alt']")).click();
            }
        }
        WebElement goToMainPage = getWebDriver().findElement(By.xpath("//*[@alt='OWASP Juice Shop']"));
        goToMainPage.click();


        product = Product.newBuilder().withName(" Apple Juice (1000ml)  1 1.99¤").build();
    }

    @AfterClass
    public void cleanData() {
        List<WebElement> basketProducts = getWebDriver().findElements(By.xpath(".//mat-row"));
        for (WebElement basketProduct : basketProducts) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//mat-row")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-icon='trash-alt']")));
            getWebDriver().findElement(By.xpath("//*[@data-icon='trash-alt']")).click();
        }
            WebdriverRunner.closeWebDriver();
        }

    @Test
    public void checkAddingProductToBasket() {
        mainPage.addProductToBasket();

        WebElement actualProductMessage = mainPage.getAddToBasketSuccessMessage();
        Assert.assertEquals(actualProductMessage.getText(), productAddedToBasketMessage, "Message is not expected");

        WebElement productCounter = mainPage.getBasketCounter();
        Assert.assertEquals(productCounter.getAttribute("innerText"), expectedProductCounter, "Product quantity is not expected");

        mainPage.clickOnBasket();

        WebElement userBasketContent = basketPage.getUserBasketContent();
        Assert.assertEquals(userBasketContent.getAttribute("textContent"), product.getName(), "Product is not expected");
    }
}
