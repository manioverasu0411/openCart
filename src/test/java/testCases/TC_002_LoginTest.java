package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void test_login(){

        try{
            logger.info("*** TC_002_LoginTest ***");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Click on My Account");
            hp.clickLogin();
            logger.info("Click on Login link");

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(rb.getString("email"));   // valid email from config file
            lp.setPassword(rb.getString("password"));
            lp.clkLoginButton();
            logger.info("Login button clicked successfully");

            MyAccountPage accountPage = new MyAccountPage(driver);
            boolean targetPage = accountPage.verifyMyAccountPage();
            Assert.assertTrue(targetPage);
            logger.info("My account message verification completed");

            accountPage.clkLogout();
            logger.info("Logged out successfully");

        } catch (Exception e){
            Assert.fail();
        }

        logger.info(" TC_002_LoginTest completed successfully ");


    }




}
