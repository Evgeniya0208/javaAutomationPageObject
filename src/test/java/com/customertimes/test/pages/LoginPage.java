package com.customertimes.test.pages;

import com.customertimes.model.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class LoginPage extends AbstractPage {

    private WebDriverWait wait;
    private String loginPage = "http://beeb0b73705f.sn.mynetname.net:3000/#/login";
    private By navbarAccount = By.id("navbarAccount");
    private By userAccountButton = By.cssSelector("button[aria-label='Go to user profile'] span");
    private By passwordFieldLocator = By.cssSelector("input[name=password]");
    private By emailLocator = By.cssSelector("input[name=email]");
    private By loginButton = By.cssSelector("[type = submit]");
    private By emptyPasswordErrorMessage = By.xpath("//input[@name = 'password']/ancestor::mat-form-field//mat-error");
    private By emptyEmailErrorMessage = By.xpath("//input[@name = 'email']/ancestor::mat-form-field//mat-error");
    private By invalidEmailPasswordErrorMessage = By.xpath("//*[@class='error ng-star-inserted']");

    public LoginPage(WebDriver driver) {
        super(getWebDriver());
        wait = new WebDriverWait(getWebDriver(), TIME_OUT);
    }

    @Override
    public void openPage() {
        wait = new WebDriverWait(getWebDriver(), 5);
        getWebDriver().get("http://beeb0b73705f.sn.mynetname.net:3000/#/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label = 'Close Welcome Banner']")));
        WebElement dismissButton = getWebDriver().findElement(By.cssSelector("[aria-label = 'Close Welcome Banner']"));
        dismissButton.click();
    }

    public String getActualUserName(String currentEmail) {
        wait.until(ExpectedConditions.presenceOfElementLocated(userAccountButton));
        WebElement userAccount = getWebDriver().findElement(userAccountButton);
        String actualUserName = userAccount.getText();
        return actualUserName;
    }

    public void clickOnAccountButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(navbarAccount));
        getWebDriver().findElement(navbarAccount).click();
    }

    public void clickOnLoginButton() {
        getWebDriver().findElement(loginButton).click();
    }

    public void enterEmail(String email) {
        WebElement emailField = getWebDriver().findElement(emailLocator);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = getWebDriver().findElement(passwordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void loginAs(Customer customer) {
        //openPage();
        enterEmail(customer.getEmail());
        enterPassword(customer.getPassword());
        clickOnLoginButton();
    }

    public void leftPasswordFieldEmpty() {
        WebElement passwordField = getWebDriver().findElement(passwordFieldLocator);
        passwordField.click();
        passwordField.sendKeys(Keys.TAB);
    }

    public void leftEmailFieldEmpty() {
        WebElement emailField = getWebDriver().findElement(emailLocator);
        emailField.click();
        emailField.sendKeys(Keys.TAB);
    }

    public String getActualEmptyPasswordErrorMessage() {
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(emptyPasswordErrorMessage)));
        String actualPasswordErrorMessage = passwordError.getText();
        return actualPasswordErrorMessage;
    }

    public String getActualEmptyEmailErrorMessage() {
        WebElement emailError = wait.until(ExpectedConditions.visibilityOf(getWebDriver().findElement(emptyEmailErrorMessage)));
        return emailError.getText();
    }
    public String getActualInvalidEmailPasswordError() {
        wait.until(ExpectedConditions.presenceOfElementLocated(invalidEmailPasswordErrorMessage));
        WebElement error = getWebDriver().findElement(invalidEmailPasswordErrorMessage);
        String actualEmailPasswordError = error.getText();
        return actualEmailPasswordError;
    }
}
