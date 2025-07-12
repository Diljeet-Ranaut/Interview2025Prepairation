package test.data.implementation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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
        ChromeOptions options = new ChromeOptions();
// Use headless only in CI environments like GitHub Actions
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new"); // For Chrome 109+
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            System.out.println("[INFO] CI Environment: " + System.getenv("CI"));
        }

        WebDriver wd = new ChromeDriver(options);
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
            String screenShotPath = ScreenshotUtil.captureScreenshot(wd, testName);

            ExtentReportManager.getTest()
                    .fail(result.getThrowable())
                    .addScreenCaptureFromPath(screenShotPath, " Screenshot on Failure");
        } else {
            ExtentReportManager.getTest().pass("Test Passed ");
        }
        if (wd != null) {

            wd.quit();
        }
        driver.remove();
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        ExtentReportManager.flushReport();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
