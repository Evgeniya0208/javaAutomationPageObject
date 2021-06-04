package com.customertimes.test.homework5;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class ProductToBasketTest extends BaseTest {
    WebDriverWait wait;
    private String loginRegisteredUser = "evgeniya1@gmail.com";;
    private String passwordRegisteredUser = "123456";
    private String productAddedToBasketMessage = "Placed Apple Juice (1000ml) into basket.";
    private String expectedProductCounter = "1";
    private String expectedProductInBasket = " Apple Juice (1000ml)  1 1.99¤";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 10);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
        WebElement emailField = getWebDriver().findElement(By.cssSelector("input[name=email]"));
        emailField.sendKeys(loginRegisteredUser);

        WebElement passwordField = getWebDriver().findElement(By.cssSelector("input[name=password]"));
        passwordField.sendKeys(passwordRegisteredUser);

        WebElement logInButton = getWebDriver().findElement(By.cssSelector("[type = submit]"));
        logInButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span")));

        WebElement productCounter = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span"));

        if (!productCounter.getAttribute("innerText").equals("0")) {
            getWebDriver().findElement(By.xpath("//*[@aria-label = 'Show the shopping cart']")).click();
            wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.xpath("// mat-table"))));
            WebElement userBasketSize = getWebDriver().findElement(By.xpath("// mat-table"));
            while (userBasketSize.getAttribute("textContent") != "") {
                getWebDriver().findElement(By.xpath("//*[@data-icon='trash-alt']")).click();
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        WebElement goToMainPage = getWebDriver().findElement(By.xpath("//*[@alt='OWASP Juice Shop']"));
        goToMainPage.click();
    }

    @AfterClass
    public void cleanData() {
        WebElement userBasketSize = getWebDriver().findElement(By.xpath("// mat-table"));
        while (userBasketSize.getAttribute("textContent") != "") {
            getWebDriver().findElement(By.xpath("//*[@data-icon='trash-alt']")).click();
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            WebdriverRunner.closeWebDriver();
        }

    @Test
    public void checkAddingProductToBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class = 'mat-grid-tile ng-star-inserted'])[1]//*[@class='mat-button-wrapper']")));

        WebElement addToBasket = getWebDriver().findElement(By.xpath("(//*[@class = 'mat-grid-tile ng-star-inserted'])[1]//*[@class='mat-button-wrapper']"));
        addToBasket.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@class = 'mat-simple-snackbar ng-star-inserted']/span"), productAddedToBasketMessage));

        WebElement actualProductMessage = getWebDriver().findElement(By.xpath("//*[@class = 'mat-simple-snackbar ng-star-inserted']/span"));
        Assert.assertEquals(actualProductMessage.getText(), productAddedToBasketMessage, "Message is not expected");

        WebElement productCounter = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span"));

        Assert.assertEquals(productCounter.getAttribute("innerText"), expectedProductCounter, "Product quantity is not expected");

        WebElement userBasket = getWebDriver().findElement(By.xpath("//*[@aria-label = 'Show the shopping cart']"));
        userBasket.click();

        wait.until(ExpectedConditions.attributeToBeNotEmpty(getWebDriver().findElement(By.xpath("// mat-table")), "innerText"));

        WebElement userBasketSize = getWebDriver().findElement(By.xpath("// mat-table"));
        Assert.assertEquals(userBasketSize.getAttribute("textContent"), expectedProductInBasket, "Product is not expected");
    }
}
