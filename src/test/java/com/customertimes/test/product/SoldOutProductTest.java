package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
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
    private String loginRegisteredUser = "evgeniya1@gmail.com";
    private String passwordRegisteredUser = "123456";
    private String expectedErrorMessage = "We are out of stock! Sorry for the inconvenience.";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 10);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        Thread.sleep(1_000);
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.sendKeys(loginRegisteredUser);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.sendKeys(passwordRegisteredUser);

        WebElement logInButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        logInButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span")));

        WebElement dismissCookie = getWebDriver().findElement(By.xpath("//*[@aria-label='dismiss cookie message']"));
        dismissCookie.click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkAddingSoldOutProductToBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class = 'mat-grid-tile ng-star-inserted'])[1]//*[@class='mat-button-wrapper']")));
        JavascriptExecutor js = (JavascriptExecutor)WebdriverRunner.getWebDriver();
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

        WebElement nextPage = getWebDriver().findElement(By.xpath("//mat-paginator/descendant::button[2]//*[@class='mat-paginator-icon']"));
        nextPage.click();

        WebElement soldOutProduct = getWebDriver().findElement(By.xpath("//mat-card/div/span[text()='Sold Out']"));
        js.executeScript("arguments[0].scrollIntoView(true)", soldOutProduct);

        WebElement addToBasketAnySoldOutProduct = getWebDriver().findElement(By.xpath("//mat-card/div/span[text()='Sold Out']/ancestor::mat-card//button"));
        addToBasketAnySoldOutProduct.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//simple-snack-bar/span"), expectedErrorMessage));

        WebElement errorMessage = getWebDriver().findElement(By.xpath("//simple-snack-bar/span"));
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Message is not expected");
    }
}
