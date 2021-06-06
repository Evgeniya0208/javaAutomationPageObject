package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.MainPage;
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
    Product product;
    MainPage mainPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        mainPage = new MainPage(driver);
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/search");
        Thread.sleep(1_000);
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
        product = Product.newBuilder().withName("Apple Juice (1000ml)").withPrice("1.99¤").withPicture("http://beeb0b73705f.sn.mynetname.net:3000/assets/public/images/products/apple_juice.jpg").build();
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void verifyProductInformation() {

        mainPage.clickOnProduct();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mainPage.getActualProductName(), product.getName(), "Product is not match");
        softAssert.assertEquals(mainPage.getActualProductPrice(), product.getPrice(), "Price is not match");
        softAssert.assertEquals(mainPage.getActualProductPictureSource(), product.getPicture(), "Picture is not expected");
        softAssert.assertAll();
    }
}
