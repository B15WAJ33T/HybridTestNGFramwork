package com.tutorialNinja.testCases;

import com.tutorialNinja.base.Base;
import com.tutorialNinja.pageObjects.HomePage;
import com.tutorialNinja.pageObjects.LoginPage;
import com.tutorialNinja.pageObjects.MyAccountPage;
import com.tutorialNinja.utils.Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LogInTest extends Base {

    LoginPage loginPage;

    public WebDriver driver;

    @BeforeMethod
    public void startUp() throws IOException {
        loadPropertiesFile();
        driver = initiate(prop.getProperty("browser"));
        driver.navigate().to(prop.getProperty("url"));

        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        loginPage=homePage.clickOnLogin();
    }
    @Test(priority = 1, dataProvider = "valid_credentials")
    public void verify_login_with_valid_credentials(String email, String password) {
        MyAccountPage accPage = loginPage.doLogin(email, password);
        Assert.assertEquals(accPage.pageTitle(), testData.getProperty("accountPageTitle"));
    }

    @DataProvider(name = "valid_credentials")
    public Object[][] testData() throws IOException {
        Object[][] data = Utilities.excelReader("loginData");
        return data;
    }

    @Test(priority = 2)
    public void verify_login_with_invalid_credential() {
        loginPage.doLogin(testData.getProperty("invalidEmail"), testData.getProperty("invalidPassword"));
        Assert.assertEquals(loginPage.alertMsg(), testData.getProperty("loginWarning"));
    }

    @Test(priority = 3)
    public void verify_login_with_only_valid_email() {
        loginPage.doLogin(prop.getProperty("validEmail"), testData.getProperty("invalidPassword"));
        Assert.assertEquals(loginPage.alertMsg(), testData.getProperty("loginWarning"));
    }

    @Test(priority = 4)
    public void verify_login_with_only_valid_password() {
        loginPage.doLogin(testData.getProperty("invalidEmail"), prop.getProperty("validPassword"));
        Assert.assertEquals(loginPage.alertMsg(), testData.getProperty("loginWarning"));
    }

    @Test(priority = 5)
    public void verify_login_without_any_credential() {
        loginPage.doLogin("", "");
        Assert.assertEquals(loginPage.alertMsg(), testData.getProperty("loginWarning"));
    }
}
