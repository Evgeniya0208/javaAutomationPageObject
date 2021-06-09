package com.customertimes.pages;

import com.customertimes.model.Customer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginPage extends AbstractPage {

    private WebDriverWait wait;
    private By navbarAccount = By.id("navbarAccount");
    private By userAccountButton = By.cssSelector("button[aria-label='Go to user profile'] span");
    private By passwordFieldLocator = By.cssSelector("input[name=password]");
    private By emailLocator = By.cssSelector("input[name=email]");
    private By loginButton = By.cssSelector("[type = submit]");
    private By emptyPasswordErrorMessage = By.xpath("//input[@name = 'password']/ancestor::mat-form-field//mat-error");
    private By emptyEmailErrorMessage = By.xpath("//input[@name = 'email']/ancestor::mat-form-field//mat-error");
    private By invalidEmailPasswordErrorMessage = By.xpath("//*[@class='error ng-star-inserted']");
    private String expectedUserName = "evgeniya1@gmail.com";

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    @Step("Open login page")
    public void openPage() {
        wait = new WebDriverWait(driver, 5);
        driver.get(BASE_PAGE + "/login");
    }

    @Step("Get actual logged in user name")
    public String getActualUserName(String currentEmail) {
        wait.until(ExpectedConditions.textToBe(userAccountButton, expectedUserName));
        WebElement userAccount = driver.findElement(userAccountButton);
        String actualUserName = userAccount.getText();
        return actualUserName;
    }

    @Step("Click on Account button")
    public void clickOnAccountButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(navbarAccount));
        driver.findElement(navbarAccount).click();
    }

    @Step("Click on Login button")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailLocator);
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Login as specified user")
    public void loginAs(Customer customer) {
        enterEmail(customer.getEmail());
        enterPassword(customer.getPassword());
        clickOnLoginButton();
    }

    @Step("Left Password field empty")
    public void leftPasswordFieldEmpty() {
        WebElement passwordField = driver.findElement(passwordFieldLocator);
        passwordField.click();
        passwordField.sendKeys(Keys.TAB);
    }

    @Step("Left Email field empty")
    public void leftEmailFieldEmpty() {
        WebElement emailField = driver.findElement(emailLocator);
        emailField.click();
        emailField.sendKeys(Keys.TAB);
    }

    @Step("Get actual empty password error message")
    public String getActualEmptyPasswordErrorMessage() {
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOf(driver.findElement(emptyPasswordErrorMessage)));
        String actualPasswordErrorMessage = passwordError.getText();
        return actualPasswordErrorMessage;
    }

    @Step("Get actual empty email error message")
    public String getActualEmptyEmailErrorMessage() {
        WebElement emailError = wait.until(ExpectedConditions.visibilityOf(driver.findElement(emptyEmailErrorMessage)));
        return emailError.getText();
    }

    @Step("get actual invalid email error message")
    public String getActualInvalidEmailPasswordError() {
        wait.until(ExpectedConditions.presenceOfElementLocated(invalidEmailPasswordErrorMessage));
        WebElement error = driver.findElement(invalidEmailPasswordErrorMessage);
        String actualEmailPasswordError = error.getText();
        return actualEmailPasswordError;
    }
}
