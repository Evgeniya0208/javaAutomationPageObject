package com.customertimes.testdata;

public class DataStore {
    private String username = "evgeniya" + System.currentTimeMillis() + "@gmail.com";
    private String password = "123456";
    private String repeatPassword = "123456";
    private String answer = "Cat";
    private String emptyEmailMessage = "Please provide an email address.";
    private String emptyPasswordMessage = "Please provide a password.";
    private String emptyRepeatPasswordMessage = "Please repeat your password.";
    private String emptySecurityQuestionMessage = "Please select a security question.";
    private String emptyAnswerMessage = "Please provide an answer to your security question.";

    public String getUsername() { return username; }

    public String getPassword() { return  password; }

    public String getRepeatPassword() { return repeatPassword; }

    public String getAnswer() { return answer; }

    public String getEmptyEmailMessage() { return emptyEmailMessage; }

    public String getEmptyPasswordMessage() { return  emptyPasswordMessage; }

    public String getEmptyRepeatPasswordMessage() { return emptyRepeatPasswordMessage; }

    public String getEmptySecurityQuestionMessage() { return emptySecurityQuestionMessage; }

   // public String getEmptyAnswerMessage() { return emptyAnswerMessage; }
}
