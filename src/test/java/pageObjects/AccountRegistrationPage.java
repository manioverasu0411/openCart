package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    WebElement txtLastName;

    @FindBy(id = "input-email")
    WebElement txtEmail;

    @FindBy(id = "input-telephone")
    WebElement txtTelephone;

    @FindBy(id = "input-password")
    WebElement txtPassword;

    @FindBy(id = "input-confirm")
    WebElement txtConfirmPw;

    @FindBy(name = "agree")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    WebElement btnSubmit;

    @FindBy(xpath = "//div[@id='content']/h1")
    WebElement msgeConfirmation;

    /*
     * Action methods in AccountRegistrationPage page
     *
     */

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String phone) {
        txtTelephone.sendKeys(phone);
    }

    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        txtConfirmPw.sendKeys(password);
    }

    public void chkAgree() {
        chkPolicy.click();
    }

    public void clkSubmit() {
        btnSubmit.click();
    }

    public String getConfirmationMsge() {

       /* try {
            return msgeConfirmation.getText();
        } catch (Exception e) {
            return (e.getMessage());
        }*/

       return msgeConfirmation.getText();


    }

}
