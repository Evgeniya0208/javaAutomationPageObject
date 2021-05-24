package com.customertimes.test.homework3;

public class RegistrationPage {

    private String emailFieldCss = "[aria-label = 'Email address field']";
    private String emailErrorCss = "div.form-container > :nth-child(1) > div > :nth-child(2) > div";
    private String passwordFieldCss = "[aria-label = 'Field for the password']";
    private String passwordErrorCss = "div.form-container > :nth-child(2) > div > :nth-child(2) em";
    private String repeatPasswordFieldCss = "[aria-label = 'Field to confirm the password']";
    private String repeatPasswordErrorCss = "div.form-container > :nth-child(3) > div > :nth-child(2) > div";
    private String showPasswordAdviceCss = "input[type = checkbox]";
    private String securityQuestionCss = "[name = securityQuestion]";
    private String answerFieldCss = "[aria-owns = securityAnswerControl]";
    private String registerButtonCss = "[type = submit]";
    private String alreadyCustomerCss = "#alreadyACustomerLink a[href = '#/login']";

    private String emailFieldXpath = "//*[@aria-label = 'Email address field']";
    private String emailErrorXpath = "//*[@aria-label = 'Email address field']/../../following-sibling::div/div";
    private String passwordFieldXpath = "//*[@aria-label = 'Field for the password']";
    private String passwordErrorXpath = "//input[@id='passwordControl']/../../following-sibling::div//em";
    private String repeatPasswordFieldXpath = "//*[@aria-label = 'Field to confirm the password']";
    private String repeatPasswordErrorXss = "//*[@aria-label = 'Field to confirm the password']/../../following-sibling::div/div";
    private String showPasswordAdviceXpath = "//*[@type = 'checkbox']";
    private String securityQuestionXpath = "//*[@name = 'securityQuestion']";
    private String answerFieldXpath = "//*[@aria-owns = 'securityAnswerControl']";
    private String registerButtonXpath = "//*[@type = 'submit']/span/i";
    private String alreadyCustomerXpath = "//*[@id='alreadyACustomerLink']/a[@routerlink = '/login']";

}
