package com.tutorialNinja.testCases;

import com.tutorialNinja.base.Base;
import com.tutorialNinja.pageObjects.HomePage;
import com.tutorialNinja.pageObjects.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends Base {

    public WebDriver driver;
    SearchPage search;

    @BeforeMethod
    public void setUp() throws IOException {
        loadPropertiesFile();
        driver = initiate(prop.getProperty("browser"));
        driver.navigate().to(prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void verify_search_with_valid_product() {

        HomePage homePage = new HomePage(driver);

        homePage.setSearchBox(testData.getProperty("validProduct"));
        search = homePage.clickOnSearch();

        Assert.assertTrue(search.isProductDisplayed());
    }

    @Test(priority = 2)
    public void verify_search_with_invalid_product() {

        HomePage homePage = new HomePage(driver);

        homePage.setSearchBox(testData.getProperty("invalidProduct"));
        search = homePage.clickOnSearch();

        Assert.assertEquals(search.noProductMatchWarningMsg(), testData.getProperty("noProductWarning"));
    }

    @Test(priority = 3)
    public void verify_search_without_putting_any_product_name() {

        HomePage homePage = new HomePage(driver);

        homePage.setSearchBox("");
        search = homePage.clickOnSearch();

        Assert.assertEquals(search.noProductMatchWarningMsg(), testData.getProperty("noProductWarning"));
    }
}
