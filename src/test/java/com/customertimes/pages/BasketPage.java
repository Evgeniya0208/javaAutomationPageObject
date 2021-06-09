package com.customertimes.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BasketPage extends AbstractPage{
    private WebDriverWait wait;
    private By basketTable = By.xpath("// mat-table");
    private By basketTableRow = By.xpath(".//mat-row");
    private By trashButton = By.xpath("//*[@data-icon='trash-alt']");
    private By mainPageButton = By.xpath("//*[@alt='OWASP Juice Shop']");


    public BasketPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    @Step("Open basket page")
    public void openPage() {
        driver.get(BASE_PAGE + "/basket");
    }

    @Step("Get user basket content")
    public WebElement getUserBasketContent() {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(basketTable), "innerText"));
        WebElement userBasketContent = driver.findElement(basketTable);
        return userBasketContent;
    }

    @Step("Remove all products from the basket")
    public void getEmptyBasket() {
        List<WebElement> basketProducts = driver.findElements(basketTableRow);

        for (WebElement basketProduct : basketProducts) {
            //wait.until(ExpectedConditions.visibilityOfElementLocated(basketTableRow));
            wait.until(ExpectedConditions.elementToBeClickable(trashButton));
            driver.findElement(trashButton).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(trashButton)));
            //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(basketTableRow)));
             wait.until(ExpectedConditions.invisibilityOf(basketProduct));
            //wait.until(ExpectedConditions.stalenessOf(basketProduct));
        }
    }

    @Step("Go to the main page")
    public void returnToMainPage() {
        WebElement goToMainPage = driver.findElement(mainPageButton);
        goToMainPage.click();
    }
}
