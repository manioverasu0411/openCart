package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationPageTest extends BaseClass {


    @Test(groups = {"Regression","Master"})
    public void test_account_Registration() throws InterruptedException {

        logger.info("***   Starting TC_001_AccountRegistrationTest   ***");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("*** CLICKED ON MYACCOUNT LINK ***");
            hp.clickRegister();
            logger.info("*** CLICKED ON REGISTER LINK ***");

            AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);

            logger.info("*** PROVIDING CUSTOMER DATA ***");
            registrationPage.setFirstName(randomString().toUpperCase());
            registrationPage.setLastName(randomString().toUpperCase());

            // Random email creation
            registrationPage.setEmail(randomString() + "@gmail.com");

            // random Phone number creation
            registrationPage.setTelephone(randomInt());

            // random password creation
            String password = randomAlphaNumeric();
            registrationPage.setPassword(password);
            registrationPage.setConfirmPassword(password);
            registrationPage.chkAgree();
            registrationPage.clkSubmit();
            logger.info("*** CLICKED ON CONTINUE  ***");
            logger.info("*** VALIDATING EXPECTED MESSAGE ***");
            String confMsge = registrationPage.getConfirmationMsge();
            Assert.assertEquals(confMsge, "Your Account Has Been Created!","Message verification failed");
        } catch (Exception e) {
            logger.error("Test failed");
            Assert.fail();
        }

        logger.info("***   Finished TC_001_AccountRegistrationTest   ***");
    }






}
