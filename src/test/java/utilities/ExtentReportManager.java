package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext context){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("OpencartFunctional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customers");
        extent.setSystemInfo("operating System",System.getProperty("user.name"));
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("Browser Name", "Chrome,FireFox,Edge");
    }

    public void onTestSuccess(ITestResult result){

        test = extent.createTest(result.getName());
        test.log(Status.PASS,"Test has passed");
    }

    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.FAIL,"Test failed: "+result.getName());
        test.log(Status.FAIL,"Test failed: "+result.getThrowable().getMessage());

        try{
             String imgPath= new BaseClass().captureScreen(result.getName());
             test.addScreenCaptureFromPath(imgPath);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.SKIP,"Test Skipped: "+result.getName());
    }

    public void onFinish(ITestContext context){
        extent.flush();
    }

}
