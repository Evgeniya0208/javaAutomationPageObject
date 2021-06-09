package com.customertimes.pages;

import com.customertimes.model.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.customertimes.framework.driver.WebdriverRunner.getWebDriver;

public class RegistrationPage extends AbstractPage {

    private WebDriverWait wait;
    private String expectedSuccessfulMessage = "Registration completed successfully. You can now log in.";
    private By email = By.cssSelector("[aria-label = 'Email address field']");
    private By password = By.cssSelector("[aria-label = 'Field for the password']");
    private By repeatPassword = By.cssSelector("[aria-label = 'Field to confirm the password']");
    private By securityQuestion = By.cssSelector("[name = securityQuestion]");
    private By answer = By.id("securityAnswerControl");
    private By getSecurityQuestionOption = By.cssSelector(".mat-option-text");
    private By registrationButton = By.cssSelector("[type = submit]");
    private By registrationSuccessMessage = By.xpath("//*[@class = 'mat-simple-snackbar ng-star-inserted']/span");
    private By userExistsErrorMessage = By.xpath("//*[@aria-label = 'Email address field']/ancestor::mat-card/*[@class='error']");
    private By emptyEmailError = By.xpath("//*[@aria-label = 'Email address field']/ancestor::mat-form-field//mat-error");
    private By emptyPasswordError = By.xpath("//*[@aria-label = 'Field for the password']/ancestor::mat-form-field//mat-error");
    private By emptyRepeatPasswordError = By.xpath("//*[@aria-label = 'Field to confirm the password']/ancestor::mat-form-field//mat-error");
    private By emptySecurityQuestionError = By.xpath("//*[@name = 'securityQuestion']/ancestor::mat-form-field//mat-error");
    private By emptyAnswerError = By.xpath("//*[@aria-owns = 'securityAnswerControl']/ancestor::mat-form-field//mat-error");
    private By passwordDoNotMatchError = By.xpath("//*[@aria-label = 'Field to confirm the password']/ancestor::mat-form-field//mat-error");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        driver.get(BASE_PAGE + "/register");
    }

    public void registerAsCustomer(Customer customer) {
        enterEmail(customer.getEmail());
        enterPassword(customer.getPassword());
        enterRepeatPassword(customer.getRepeatPassword());
        clickOnSecurityQuestion();
        chooseAnySecurityQuestion();
        enterAnswer(customer.getAnswer());
        clickOnRegisterButton();
    }

    public WebElement getRegistrationSuccessMessage() {
        wait.until(ExpectedConditions.textToBe(this.registrationSuccessMessage, expectedSuccessfulMessage));
        WebElement registrationSuccessMessage = driver.findElement(this.registrationSuccessMessage);
        return registrationSuccessMessage;
    }

    public WebElement getUserExistsErrorMessage() {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(getWebDriver().findElement(userExistsErrorMessage), "innerText"));
        WebElement actualErrorMessage = driver.findElement(userExistsErrorMessage);
        return actualErrorMessage;
    }

    public void clickOnRegisterButton() {
        WebElement registerButton = driver.findElement(registrationButton);
        registerButton.click();
    }

    public void enterAnswer(String answer) {
        WebElement answerField = driver.findElement(this.answer);
        answerField.clear();
        answerField.sendKeys(answer);
    }

    public void chooseAnySecurityQuestion() {
        WebElement anySecurityQuestion = driver.findElement(getSecurityQuestionOption);
        anySecurityQuestion.click();
    }

    public void clickOnSecurityQuestion() {
        WebElement securityQuestion = driver.findElement(this.securityQuestion);
        securityQuestion.click();
    }

    public void enterRepeatPassword(String repeatPassword) {
        WebElement repeatPasswordField = driver.findElement(this.repeatPassword);
        repeatPasswordField.clear();
        repeatPasswordField.sendKeys(repeatPassword);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(this.password);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(this.email);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickAnswer() {
        WebElement answerField = driver.findElement(answer);
        answerField.click();
        answerField.sendKeys(Keys.TAB);
    }

    public void clickSecurityQuestion() {
        WebElement securityQuestionField = driver.findElement(securityQuestion);
        securityQuestionField.sendKeys(Keys.ESCAPE);
    }

    public void clickRepeatPassword() {
        WebElement repeatPasswordField = driver.findElement(repeatPassword);
        repeatPasswordField.click();
    }

    public void clickPassword() {
        WebElement passwordField = driver.findElement(password);
        passwordField.click();
    }

    public void clickEmail() {
        WebElement emailField = driver.findElement(email);
        emailField.click();
    }

    public void emptyRegistrationFormWithErrors() {
        clickEmail();
        clickPassword();
        clickRepeatPassword();
        clickSecurityQuestion();
        clickAnswer();
    }

    public String getActualEmptyEmailErrorMessage() {
        String actualEmailErrorMessage = driver.findElement(emptyEmailError).getText();
        return actualEmailErrorMessage;
    }

    public String getActualEmptyPasswordErrorMessage() {
        String actualPasswordErrorMessage = driver.findElement(emptyPasswordError).getText();
        return actualPasswordErrorMessage;
    }

    public String getActualEmptyRepeatPasswordErrorMessage() {
        String actualRepeatPasswordErrorMessage = driver.findElement(emptyRepeatPasswordError).getText();
        return actualRepeatPasswordErrorMessage;
    }

    public String getActualEmptySecurityQuestionErrorMessage() {
        String actualSecurityQuestionErrorMessage = driver.findElement(emptySecurityQuestionError).getText();
        return actualSecurityQuestionErrorMessage;
    }

   public String getActualEmptyAnswerErrorMessage() {
        String actualAnswerErrorMessage = driver.findElement(emptyAnswerError).getText();
        return actualAnswerErrorMessage;
    }

    public String getActualPasswordDoNotMatchErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(passwordDoNotMatchError)));
        String actualErrorMessage = driver.findElement(passwordDoNotMatchError).getText();
        return actualErrorMessage;
    }
}
