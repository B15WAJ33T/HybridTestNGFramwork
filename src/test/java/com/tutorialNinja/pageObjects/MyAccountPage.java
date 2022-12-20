package com.tutorialNinja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    WebDriver driver;
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Objects
	@FindBy(css="div[id='content'] h1")
	private WebElement accCreationConfirmation;

    //Actions
    public String pageTitle() {
        return driver.getTitle();
    }

    public String getConfirmationMsg() {
        return accCreationConfirmation.getText();
    }
}
