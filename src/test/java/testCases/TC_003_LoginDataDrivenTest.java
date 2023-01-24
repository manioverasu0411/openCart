package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
    public void test_loginDDT(String email,String password,String exp){

        try {


            logger.info("*** Starting TC_003_LoginDataDrivenTest *** ");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Click on My Account");
            hp.clickLogin();
            logger.info("Click on Login link");

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);   // valid email from config file
            lp.setPassword(password);
            lp.clkLoginButton();

            MyAccountPage accountPage = new MyAccountPage(driver);
            boolean targetPage = accountPage.verifyMyAccountPage();
            logger.info("My account message verification completed");

            if (exp.equals("Valid")) {

                if (targetPage == true) {
                    accountPage.clkLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }

            }


            if (exp.equals(("Invalid"))) {

                if (targetPage == true) {
                    accountPage.clkLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }

            logger.info("*** Finished TC_003_LoginDataDrivenTest *** ");

    }
}
