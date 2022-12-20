package com.tutorialNinja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Objects

	@FindBy(id = "input-email")
	private WebElement inputEmail;
	@FindBy(id = "input-password")
	private WebElement inputPassword;
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;
    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement loginFailedAlertMessage;


    //Actions
    public MyAccountPage doLogin(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        loginBtn.click();

        return new MyAccountPage(driver);
    }

    public String alertMsg() {
        return loginFailedAlertMessage.getText();
    }

}
