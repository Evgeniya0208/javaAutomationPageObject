package com.customertimes.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class BasketPage extends AbstractPage{
    private WebDriverWait wait;
    private String registrationPage = "http://beeb0b73705f.sn.mynetname.net:3000/#/basket";
    private By basketTable = By.xpath("// mat-table");


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
}
