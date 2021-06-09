package com.customertimes.pages;

import com.customertimes.framework.driver.WebdriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class MainPage extends AbstractPage {

    private WebDriverWait wait;
    JavascriptExecutor js;
    private String productAddedToBasketMessage = "Placed Apple Juice (1000ml) into basket.";
    private By productCardPicture = By.cssSelector(".cdk-overlay-pane .img-thumbnail");
    private By productCardPrice = By.cssSelector(".cdk-overlay-pane .item-price");
    private By productCardName = By.xpath("//mat-dialog-content/descendant::h1");
    private By productInList = By.xpath("//mat-card/descendant::div/*[contains(text(),'Apple Juice (1000ml)')]");
    private By addToBasketButton = By.xpath("//mat-card/descendant::div/*[contains(text(),'Apple Juice (1000ml)')]/ancestor::mat-card//button");
    private By addToBasketSuccessMessage = By.xpath("//*[@class = 'mat-simple-snackbar ng-star-inserted']/span");
    private By basketCounter = By.xpath("//*[contains(text(), 'Your Basket')]/following-sibling::span");
    private By basketIcon = By.xpath("//*[@aria-label = 'Show the shopping cart']");
    private By nextPageButton = By.xpath("//mat-paginator/descendant::button[2]//*[@class='mat-paginator-icon']");
    private By soldOutProduct = By.xpath("//mat-card/div/span[text()='Sold Out']");
    private By errorToastMessage = By.xpath("//simple-snack-bar/span");
    private By addSoldOutToBasket = By.xpath("//mat-card/div/span[text()='Sold Out']/ancestor::mat-card//button");
    private String scrollPageDown = "window.scrollTo(0,document.body.scrollHeight)";
    private String scrollToElement = "arguments[0].scrollIntoView(true)";
    private String expectedErrorMessage = "We are out of stock! Sorry for the inconvenience.";
    private By closeWelcomeBannerButton = By.cssSelector("[aria-label = 'Close Welcome Banner']");
    private By dismissCookieButton = By.xpath("//*[@aria-label='dismiss cookie message']");

    public MainPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
        js = (JavascriptExecutor)driver;
    }

    @Override
    @Step("Open main page")
    public void openPage() {
        driver.get(BASE_PAGE);
    }

    @Step("Close Welcome Banner")
    public void closeWelcomeBanner() {
        wait.until(ExpectedConditions.presenceOfElementLocated(closeWelcomeBannerButton));
        WebElement dismissButton = driver.findElement(closeWelcomeBannerButton);
        dismissButton.click();
    }

    @Step("Close cookies message")
    public void dismissCookie() {
        WebElement dismissCookie = driver.findElement(dismissCookieButton);
        dismissCookie.click();
    }

    @Step("Check counter number on the basket icon")
    public String getBasketCounter() {
        wait.until(ExpectedConditions.presenceOfElementLocated(basketCounter));
        String basketCounter = driver.findElement(this.basketCounter).getAttribute("innerText");
        return basketCounter;
    }

    @Step("Get actual product picture source from product card pop-up")
    public String getActualProductPictureSource() {
        String actualProductPictureSource = driver.findElement(productCardPicture).getAttribute("src");
        return actualProductPictureSource;
    }

    @Step("Get actual product price from product card pop-up")
    public String getActualProductPrice() {
        return driver.findElement(productCardPrice).getText();
    }

    @Step("Get actual product name from product card pop-up")
    public String getActualProductName() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(productCardName)));
        String actualProductName = driver.findElement(productCardName).getText();
        return actualProductName;
    }

    @Step("Click on product in product list")
    public void clickOnProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(productInList));
        WebElement desiredProduct = driver.findElement(productInList);
        desiredProduct.click();
    }

    @Step("Add product to the basket")
    public void addProductToBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(addToBasketButton));
        WebElement addToBasket = driver.findElement(addToBasketButton);
        addToBasket.click();
    }

    @Step("Get success message after adding product to the basket")
    public WebElement getAddToBasketSuccessMessage() {
        wait.until(ExpectedConditions.textToBe(addToBasketSuccessMessage, productAddedToBasketMessage));
        WebElement actualProductMessage = driver.findElement(addToBasketSuccessMessage);
        return actualProductMessage;
    }

    @Step("Click on basket icon")
    public void clickOnBasket() {
        WebElement userBasket = driver.findElement(basketIcon);
        userBasket.click();
    }

    @Step("Scroll to page down")
    public void goPageDown() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productInList));
        js.executeScript(scrollPageDown);
    }

    @Step("Go to the next page")
    public void goNextPage() {
        WebElement nextPage = driver.findElement(nextPageButton);
        nextPage.click();
    }

    @Step("Scroll to any sold out product")
    public void goSoldOutProduct() {
        WebElement soldOutProduct = driver.findElement(this.soldOutProduct);
        js.executeScript(scrollToElement, soldOutProduct);
    }

    @Step("Get error message after trying to add sold out product to the basket")
    public String getSoldOutErrorMessage() {
        wait.until(ExpectedConditions.textToBe(errorToastMessage, expectedErrorMessage));
        String errorMessage = driver.findElement(errorToastMessage).getText();
        return errorMessage;
    }

    @Step("Add sold out product to the basket")
    public void addSoldOutToBasket() {
        WebElement addToBasketAnySoldOutProduct = driver.findElement(addSoldOutToBasket);
        addToBasketAnySoldOutProduct.click();
    }
}
