package com.customertimes.test.login;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.framework.listeners.TestListener;
import com.customertimes.pages.LoginPage;
import com.customertimes.pages.MainPage;
import com.customertimes.test.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

@Epic("Log in")
@Story("Empty Login form validation")

public class LoginEmptyFormTest extends BaseTest {

    private String emptyEmailFieldMessage = "Please provide an email address.";
    private String emptyPasswordFieldMessage = "Please provide a password.";
    LoginPage loginPage;
    MainPage mainPage;

    @BeforeClass
    public void openPageBeforeClass() throws InterruptedException {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        loginPage.openPage();
        mainPage.closeWelcomeBanner();
    }

    @Test
    @Feature("login")
    @Description("User can login to app")
    @TmsLink("ELEM-1220")
    public void checkLoginEmptyForm() {
        loginPage.leftEmailFieldEmpty();

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        loginPage.leftPasswordFieldEmpty();

        String actualEmailErrorMessage = loginPage.getActualEmptyEmailErrorMessage();

        String actualPasswordErrorMessage = loginPage.getActualEmptyPasswordErrorMessage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailErrorMessage, emptyEmailFieldMessage, "Error message is not expected");
        softAssert.assertEquals(actualPasswordErrorMessage, emptyPasswordFieldMessage, "Error message is not expected");
        softAssert.assertAll();
    }
}
