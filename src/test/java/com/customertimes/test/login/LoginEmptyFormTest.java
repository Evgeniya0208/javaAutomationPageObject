package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.test.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginEmptyFormTest {

    WebDriverWait wait;
    private String emptyEmailFieldMessage = "Please provide an email address.";
    private String emptyPasswordFieldMessage = "Please provide a password.";
    LoginPage loginPage;

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
        loginPage = new LoginPage(getWebDriver());
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }

    @Test
    public void checkLoginEmptyForm() {
        loginPage.leftEmailFieldEmpty();

        loginPage.leftPasswordFieldEmpty();

        String actualEmailErrorMessage = loginPage.getActualEmptyEmailErrorMessage();

        String actualPasswordErrorMessage = loginPage.getActualEmptyPasswordErrorMessage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailErrorMessage, emptyEmailFieldMessage, "Error message is not expected");
        softAssert.assertEquals(actualPasswordErrorMessage, emptyPasswordFieldMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
