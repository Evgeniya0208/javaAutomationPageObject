package com.customertimes.framework.driver;

import com.customertimes.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebdriverRunner {

    private static WebDriver driver;

    private WebdriverRunner() {
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            switch (TestConfig.CONFIG.browser()) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                case "opera": {
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                    }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                }
                case  "safari": {
                    driver = new SafariDriver();
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeWebDriver() {
        if (driver != null) {
            driver.close();
        }
    }
}
