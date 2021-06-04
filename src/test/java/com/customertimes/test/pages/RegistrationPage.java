package com.customertimes.test.pages;

public class RegistrationPage {

    private String emailFieldCss = "[aria-label = 'Email address field']";
    private String passwordFieldCss = "[aria-label = 'Field for the password']";
    private String repeatPasswordFieldCss = "[aria-label = 'Field to confirm the password']";
    private String showPasswordAdviceCss = "input[type = checkbox]";
    private String securityQuestionCss = "[name = securityQuestion]";
    private String answerFieldCss = "[aria-owns = securityAnswerControl]";
    private String registerButtonCss = "[type = submit]";
    private String alreadyCustomerCss = "#alreadyACustomerLink a[href = '#/login']";
    private String dismissWelcomeButtonCss = "[aria-label = 'Close Welcome Banner']";


    private String emailFieldXpath = "//*[@aria-label = 'Email address field']";
    private String emailErrorXpath = "//*[@aria-label = 'Email address field']/../../following-sibling::div/div/mat-error";
    private String passwordFieldXpath = "//*[@aria-label = 'Field for the password']";
    private String passwordErrorXpath = "//*[@aria-label = 'Field for the password']/../../following-sibling::div/div/mat-error";
    private String repeatPasswordFieldXpath = "//*[@aria-label = 'Field to confirm the password']";
    private String repeatPasswordErrorXss = "//*[@aria-label = 'Field to confirm the password']/../../following-sibling::div/div/mat-error";
    private String showPasswordAdviceXpath = "//*[@type = 'checkbox']";
    private String securityQuestionXpath = "//*[@name = 'securityQuestion']";
    private String answerFieldXpath = "//*[@aria-owns = 'securityAnswerControl']";
    private String registerButtonXpath = "//*[@type = 'submit']/span/i";
    private String alreadyCustomerXpath = "//*[@id='alreadyACustomerLink']/a[@routerlink = '/login']";

}
