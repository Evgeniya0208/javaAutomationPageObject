package com.customertimes.test.registration;

import com.customertimes.framework.driver.WebdriverRunner;
import com.customertimes.model.Customer;
import com.customertimes.test.BaseTest;
import com.customertimes.test.pages.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegisterNotMatchedPasswordTest extends BaseTest {
    WebDriverWait wait;
    Customer customer;
    RegistrationPage registrationPage;
    private String expectedErrorMessage = "Passwords do not match";

    @BeforeClass
    public void setup() throws InterruptedException {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/register");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
        customer = Customer.newBuilder().withName("evgeniya1@gmail.com").withPassword("123456").withRepeatPassword("12345").build();
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebdriverRunner.closeWebDriver();
    }
    @Test
    public void checkRegFormPasswordValidation() {
        registrationPage.enterEmail(customer.getEmail());
        registrationPage.enterPassword(customer.getPassword());
        registrationPage.enterRepeatPassword(customer.getRepeatPassword());
        registrationPage.clickPassword();

        Assert.assertEquals(registrationPage.getActualPasswordDoNotMatchErrorMessage(), expectedErrorMessage, "Error message is not expected");
    }
}
