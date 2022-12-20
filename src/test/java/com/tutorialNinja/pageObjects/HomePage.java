package com.tutorialNinja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Objects
    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement account;
	@FindBy(linkText = "Login")
	private WebElement login;
	@FindBy(linkText = "Register")
	private WebElement register;
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchBox;
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchBtn;

	//Actions

	public void clickOnMyAccount() {
		account.click();
	}

	public LoginPage clickOnLogin() {
		login.click();
		return new LoginPage(driver);
	}

	public RegisterPage clickOnRegister() {
		register.click();
		return new RegisterPage(driver);
	}

	public void setSearchBox(String arg) {
		searchBox.sendKeys(arg);
	}

	public SearchPage clickOnSearch() {
		searchBtn.click();
		return new SearchPage(driver);
	}
}
