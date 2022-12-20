package com.tutorialNinja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Objects
	@FindBy(id="input-firstname")
	private WebElement firstName;
	@FindBy(id="input-lastname")
	private WebElement lastName;
	@FindBy(id="input-email")
	private WebElement email;
	@FindBy(id="input-telephone")
	private WebElement telephone;
	@FindBy(id="input-password")
	private WebElement password;
	@FindBy(id="input-confirm")
	private WebElement confirmPassword;
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicy;
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submit;
	@FindBy(css="input[value='1'][name='newsletter']")
	private WebElement newsletterSubYes;
	@FindBy(css="input[value='0']")
	private WebElement newsletterSubNo;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailAlert;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement getPrivacyPolicyWarning;
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement invalidEmailWarning;
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneNoWarning;
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarning;

    //Actions

	public MyAccountPage fillMandatoryRegData(String firstNameTxt, String lastNameTxt, String emailTxt, String phoneNo, String pwd){
		firstName.sendKeys(firstNameTxt);
		lastName.sendKeys(lastNameTxt);
		email.sendKeys(emailTxt);
		telephone.sendKeys(phoneNo);
		password.sendKeys(pwd);
		confirmPassword.sendKeys(pwd);
		privacyPolicy.click();
		submit.click();
		return new MyAccountPage(driver);
	}

	public MyAccountPage fillAllRegData(String firstNameTxt, String lastNameTxt, String emailTxt, String phoneNo, String pwd, boolean subStatus){
		firstName.sendKeys(firstNameTxt);
		lastName.sendKeys(lastNameTxt);
		email.sendKeys(emailTxt);
		telephone.sendKeys(phoneNo);
		password.sendKeys(pwd);
		confirmPassword.sendKeys(pwd);

		if(subStatus==true) {
			newsletterSubYes.click();
		} else {
			newsletterSubNo.click();
		}

		privacyPolicy.click();
		submit.click();
		return new MyAccountPage(driver);
	}

	public MyAccountPage clickSubmit() {
		submit.click();
		return new MyAccountPage(driver);
	}
	public String existingEmailAlertMsg() {
		return existingEmailAlert.getText();
	}

	public String firstNameWarningMsg() {
		return firstNameWarning.getText();
	}

	public String lastNameWarningMsg() {
		return lastNameWarning.getText();
	}

	public String invalidEmailWarningMsg() {
		return invalidEmailWarning.getText();
	}

	public String telephoneNoWarningMsg() {
		return telephoneNoWarning.getText();
	}

	public String passwordWarningMsg() {
		return passwordWarning.getText();
	}

	public String privacyPolicyWarningMsg() {
		return getPrivacyPolicyWarning.getText();
	}
}
