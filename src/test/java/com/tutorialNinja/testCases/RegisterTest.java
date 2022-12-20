package com.tutorialNinja.testCases;

import com.tutorialNinja.base.Base;
import com.tutorialNinja.pageObjects.HomePage;
import com.tutorialNinja.pageObjects.MyAccountPage;
import com.tutorialNinja.pageObjects.RegisterPage;
import com.tutorialNinja.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterTest extends Base {

    public WebDriver driver;
    RegisterPage register;

    @BeforeMethod
    public void setUp() throws IOException {
        loadPropertiesFile();
        driver = initiate(prop.getProperty("browser"));
        driver.navigate().to(prop.getProperty("url"));

        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        register = homePage.clickOnRegister();
    }
    @Test(priority = 1)
    public void verify_registering_with_mandatory_field() {
        MyAccountPage accountPage = register.fillMandatoryRegData(
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                Utilities.GenerateEmailTimestamp(),
                testData.getProperty("telephoneNum"),
                testData.getProperty("password")
                );
        Assert.assertEquals(accountPage.getConfirmationMsg(), testData.getProperty("accountCreateConfirmation"));
    }

    @Test(priority = 2)
    public void verify_registration_with_all_the_field() {
        MyAccountPage accountPage = register.fillAllRegData(
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                Utilities.GenerateEmailTimestamp(),
                testData.getProperty("telephoneNum"),
                testData.getProperty("password"),
                true
        );
        Assert.assertEquals(accountPage.getConfirmationMsg(), testData.getProperty("accountCreateConfirmation"));

    }

    @Test(priority = 3)
    public void verify_registration_with_an_existing_email() {
        MyAccountPage accountPage = register.fillAllRegData(
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                prop.getProperty("validEmail"),
                testData.getProperty("telephoneNum"),
                testData.getProperty("password"),
                true
        );
        Assert.assertEquals(register.existingEmailAlertMsg(), testData.getProperty("existingEmailWarning"));
    }

    @Test(priority = 3)
    public void verify_registration_without_filling_any_mandatory_field() {

        register.clickSubmit();

        Assert.assertEquals(register.privacyPolicyWarningMsg(),
                testData.getProperty("privacyPolicyWarning"),
                "There is no privacy policy warning message");

        Assert.assertEquals(register.firstNameWarningMsg(),
                testData.getProperty("firstNameWarning"),
                "There is no first name warning message");

        Assert.assertEquals(register.lastNameWarningMsg(),
                testData.getProperty("lastNameWarning"),
                "There is no last name warning message");

        Assert.assertEquals(register.invalidEmailWarningMsg(),
                testData.getProperty("emailWarning"),
                "There is no Email warning message");

        Assert.assertEquals(register.telephoneNoWarningMsg(),
                testData.getProperty("telephoneNumWarning"),
                "There is no telephone warning message");

        Assert.assertEquals(register.passwordWarningMsg(),
                testData.getProperty("passwordWarning"),
                "There is no password warning message");

    }
}

