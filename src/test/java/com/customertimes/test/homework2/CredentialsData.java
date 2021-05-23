package com.customertimes.test.homework2;

import org.testng.annotations.DataProvider;

public class CredentialsData {
    @DataProvider(name = "credentials")
    public Object[][] getData() {
        Object[][] data = {{"evgeniya@gmail.com", "123456"}, {"cat2013@ukr.net", "qwerty"}, {"eva01@gmail.com", "password"}};
        return data;
    }
}
