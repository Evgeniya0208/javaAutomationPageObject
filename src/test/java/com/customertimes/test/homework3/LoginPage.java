package com.customertimes.test.homework3;

public class LoginPage {
    private String emailFieldCss = "input[name=email]";
    private String emailPasswordErrorCss = ".error";
    private String passwordFieldCss = "input[name=password]";
    private String showPasswordButtonCss = "[aria-label = 'Eye']";
    private String logInButtonCss = "[type = submit]";
    private String rememberMeCheckboxCss = "input[type = checkbox]";
    private String forgotYourPasswordCss = "[href = '#/forgot-password']";
    private String notYetCustomerCss = "[href = '#/register']";

    private String emailFieldXpath = "//input[@name = 'email']";
    private String emailPasswordErrorXpath = "//*[@class='error ng-star-inserted']";
    private String passwordFieldXpath = "//input[@name = 'password']";
    private String showPasswordButtonXpath = "//*[@aria-label='Eye']";
    private String logInButtonXpath = "//button[@type = 'submit']";
    private String rememberMeCheckboxXpath = "//input[@type = 'checkbox']";
    private String forgotYourPasswordXpath = "//a[@href = '#/forgot-password']";
    private String notYetCustomerXpath = "//a[@href = '#/register']";
}
