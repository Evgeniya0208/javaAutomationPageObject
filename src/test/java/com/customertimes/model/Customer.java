package com.customertimes.model;

public class Customer {

    private String email;
    private String password;
    private String repeatPassword;
    private String answer;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() { return repeatPassword; }

    public void setRepeatPassword (String repeatPassword) { this.repeatPassword = repeatPassword; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Customer() {
    }

    private Customer(final Builder builder) {
        email = builder.email;
        password = builder.password;
        repeatPassword = builder.repeatPassword;
        answer = builder.answer;
    }

    public static final class Builder {
        private String email;
        private String password;
        private String repeatPassword;
        private String answer;

        private Builder() {
        }

        public Builder withName(final String val) {
            email = val;
            return this;
        }

        public Builder withPassword(final String val) {
            password = val;
            return this;
        }

        public Builder withRepeatPassword(final String val) {
            repeatPassword = val;
            return this;
        }

        public Builder withAnswer(final String val) {
            answer = val;
            return this;
        }


        public Customer build() {
            return new Customer(this);
        }
    }

}