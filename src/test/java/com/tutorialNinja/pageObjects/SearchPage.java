package com.tutorialNinja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Objects
	@FindBy(xpath="//div[@class='product-thumb']")
	private WebElement searchProduct;

	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement noProductMatchWarning;


    //Actions

    public boolean isProductDisplayed() {
        return searchProduct.isDisplayed();
    }

    public String noProductMatchWarningMsg() {
        return noProductMatchWarning.getText();
    }



}
