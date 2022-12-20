package com.tutorialNinja.base;

import com.tutorialNinja.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties prop;
    public Properties testData;

    public void loadPropertiesFile() throws IOException {
        prop = new Properties();
        testData = new Properties();
        File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\config.properties");
        File testDataFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\testData.properties");
        FileInputStream fis = new FileInputStream(propFile);
        FileInputStream fisTestData = new FileInputStream(testDataFile);
        prop.load(fis);
        testData.load(fisTestData);
    }
    public WebDriver initiate( String browser) {

        if(browser.equalsIgnoreCase("chrome")) {
            driver =new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Utilities.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Utilities.PAGE_LOAD_WAIT_TIME, TimeUnit.SECONDS);

        return driver;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
