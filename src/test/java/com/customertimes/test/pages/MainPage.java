package com.customertimes.test.pages;

import com.customertimes.model.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class MainPage extends AbstractPage {

    private WebDriverWait wait;
    private String registrationPage = "http://beeb0b73705f.sn.mynetname.net:3000";
    private String productAddedToBasketMessage = "Placed Apple Juice (1000ml) into basket.";
    private By productCardPicture = By.cssSelector(".cdk-overlay-pane .img-thumbnail");
    private By productCardPrice = By.cssSelector(".cdk-overlay-pane .item-price");
    private By productCardName = By.xpath("//mat-dialog-content/descendant::h1");
    private By productInList = By.xpath("//mat-card/descendant::div/*[contains(text(),'Apple Juice (1000ml)')]");
    private By addToBasketButton = By.xpath("//mat-card/descendant::div/*[contains(text(),'Apple Juice (1000ml)')]/ancestor::mat-card//button");
    private By addToBasketSuccessMessage = By.xpath("//*[@class = 'mat-simple-snackbar ng-star-inserted']/span");
    private By basketCounter = By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span");
    private By basketIcon = By.xpath("//*[@aria-label = 'Show the shopping cart']");


    public MainPage(WebDriver driver) {
        super(getWebDriver());
        wait = new WebDriverWait(getWebDriver(), TIME_OUT);
    }

    @Override
    public void openPage() {

    }

    public String getActualProductPictureSource() {
        String actualProductPictureSource = getWebDriver().findElement(productCardPicture).getAttribute("src");
        return actualProductPictureSource;
    }

    public String getActualProductPrice() {
        return getWebDriver().findElement(productCardPrice).getText();
    }

    public String getActualProductName() {
        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(productCardName)));
        String actualProductName = getWebDriver().findElement(productCardName).getText();
        return actualProductName;
    }

    public void clickOnProduct() {
        WebElement desiredProduct = getWebDriver().findElement(productInList);
        desiredProduct.click();
    }

    public void addProductToBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(addToBasketButton));
        WebElement addToBasket = getWebDriver().findElement(addToBasketButton);
        addToBasket.click();
    }

    public WebElement getAddToBasketSuccessMessage() {
        wait.until(ExpectedConditions.textToBe(addToBasketSuccessMessage, productAddedToBasketMessage));
        WebElement actualProductMessage = getWebDriver().findElement(addToBasketSuccessMessage);
        return actualProductMessage;
    }

    public WebElement getBasketCounter() {
        return getWebDriver().findElement(basketCounter);
    }

    public void clickOnBasket() {
        WebElement userBasket = getWebDriver().findElement(basketIcon);
        userBasket.click();
    }
}
