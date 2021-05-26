package com.customertimes.test.homework2;

import org.testng.annotations.Test;

public class DataSetsTest {

    @Test (dataProvider = "credentials", dataProviderClass = CredentialsData.class)
    public void checkCredentials(String email, String password) {
        System.out.println("My login and password is " + email + "   " + password);
    }

}
