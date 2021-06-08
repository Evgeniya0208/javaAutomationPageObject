package com.customertimes.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class BasketPage extends AbstractPage{
    private WebDriverWait wait;
    private String registrationPage = "http://beeb0b73705f.sn.mynetname.net:3000/#/basket";
    private By basketTable = By.xpath("// mat-table");
    private By basketTableRow = By.xpath(".//mat-row");
    private By trashButton = By.xpath("//*[@data-icon='trash-alt']");
    private By mainPageButton = By.xpath("//*[@alt='OWASP Juice Shop']");


    public BasketPage(WebDriver driver) {
        super(getWebDriver());
        wait = new WebDriverWait(getWebDriver(), TIME_OUT);
    }

    @Override
    public void openPage() {

    }

    public WebElement getUserBasketContent() {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(getWebDriver().findElement(basketTable), "innerText"));
        WebElement userBasketContent = getWebDriver().findElement(basketTable);
        return userBasketContent;
    }

    public void getEmptyBasket() {
        List<WebElement> basketProducts = getWebDriver().findElements(basketTableRow);

        for (WebElement basketProduct : basketProducts) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(basketTableRow));
            wait.until(ExpectedConditions.elementToBeClickable(trashButton));
            getWebDriver().findElement(trashButton).click();
        }
    }

    public void returnToMainPage() {
        WebElement goToMainPage = getWebDriver().findElement(mainPageButton);
        goToMainPage.click();
    }
}
