package com.customertimes.test.homework5;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class ProductInformationTest extends BaseTest {
    WebDriverWait wait;
    private String productName = "Apple Juice (1000ml)";
    private String productPrice = "1.99¤";
    private String productPictureSource = "http://beeb0b73705f.sn.mynetname.net:3000/assets/public/images/products/apple_juice.jpg";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/search");
        Thread.sleep(1_000);
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void verifyProductInformation() {
        WebElement firstProduct = getWebDriver().findElement(By.xpath("//mat-card/descendant::div/*[contains(text(),'Apple Juice (1000ml)')]"));
        firstProduct.click();

        wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(By.xpath("//mat-dialog-content/descendant::h1"))));

        WebElement productPopUp = getWebDriver().findElement(By.cssSelector(".cdk-overlay-pane"));

        String actualProductName = getWebDriver().findElement(By.xpath("//mat-dialog-content/descendant::h1")).getText();
        String actualProductPrice = getWebDriver().findElement(By.cssSelector(".cdk-overlay-pane .item-price")).getText();
        String actualProductPictureSource = getWebDriver().findElement(By.cssSelector(".cdk-overlay-pane .img-thumbnail")).getAttribute("src");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductName, productName, "Product is not match");
        softAssert.assertEquals(actualProductPrice, productPrice, "Price is not match");
        softAssert.assertEquals(actualProductPictureSource, productPictureSource, "Picture is not expected");
        softAssert.assertAll();

    }
}
