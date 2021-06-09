package com.customertimes.test.product;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Product;
import com.customertimes.test.BaseTest;
import com.customertimes.pages.MainPage;
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
    public void openPageBeforeClass() throws InterruptedException {
        wait = new WebDriverWait(driver, 5);
        mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.closeWelcomeBanner();
        product = Product.newBuilder().withName("Apple Juice (1000ml)").withPrice("1.99¤").withPicture("http://beeb0b73705f.sn.mynetname.net:3000/assets/public/images/products/apple_juice.jpg").build();
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
