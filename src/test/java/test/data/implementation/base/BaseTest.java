package test.data.implementation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import test.data.implementation.reports.ExtentReportManager;
import test.data.implementation.utils.ScreenshotUtil;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeSuite
    public void initReport() {
        ExtentReportManager.initReport();
    }

    @BeforeMethod
    public void setup(Method method) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.set(wd);
        ExtentReportManager.createTest(method.getName());
        Thread.sleep(5000);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        WebDriver wd = driver.get();
        String testName = result.getMethod().getMethodName();
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenShotPath = ScreenshotUtil.captureScreenshot(wd,testName);

            ExtentReportManager.getTest()
                    .fail(result.getThrowable())
                    .addScreenCaptureFromPath(screenShotPath , " Screenshot on Failure");
        }else {
            ExtentReportManager.getTest().pass("Test Passed ");
        }
        if( wd !=null){

            wd.quit();
        }
        driver.remove();
    }
    @AfterSuite
    public void flushReport() {
        ExtentReportManager.flushReport();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
