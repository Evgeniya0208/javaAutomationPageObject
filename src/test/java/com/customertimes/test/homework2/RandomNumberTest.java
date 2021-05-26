package com.customertimes.test.homework2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RandomNumberTest {
    @Test
    public void checkRandomNumber() {
        Random rand = new Random();
        int number = rand.nextInt(11);
        System.out.println(number);
        Assert.assertTrue((number >= 0 && number <= 10), "rand is not in the correct range");
    }
}
