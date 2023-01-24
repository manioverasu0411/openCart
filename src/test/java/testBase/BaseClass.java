package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;


public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public ResourceBundle rb;


    @BeforeClass(groups = {"Master","Sanity","Regression"})
    @Parameters("browser")
    public void setup(String br) {

        rb = ResourceBundle.getBundle("config"); // Load config.properties file


        // current class file have to added in logger file so using this.getClass()
        logger = LogManager.getLogger(this.getClass());

        // To avoid message -> chrome controlled automated software
       /* ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});*/

        if (br.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (br.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(rb.getString("appURl"));
        driver.manage().window().maximize();

    }

    @AfterClass(groups = {"Master","Sanity","Regression"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        return (RandomStringUtils.randomAlphabetic(5));

    }

    public String randomInt() {
        return (RandomStringUtils.randomNumeric(10));
    }

    public String randomAlphaNumeric() {
        return (RandomStringUtils.randomAlphanumeric(8));

    }

    public String captureScreen(String tname) {

         /*First generate time stamp
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        Date dt = new Date();
        String timestamp = df.format(dt);
        */

        // combine above statement into single line

        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String fileName = tname + "_" + timestamp + ".png";
        String destination = "../openCart/screenshots/" + fileName ;

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return "../screenshots/"+ fileName;


    }


}
