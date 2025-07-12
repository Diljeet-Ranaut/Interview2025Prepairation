package test.data.implementation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class  ExtentReportManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = getInstance();
        extent.attachReporter(spark);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Automation Engineer");
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            new File("test-output").mkdirs(); // ✅ Ensures folder exists in CI

            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            reporter.config().setReportName("Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Environment", "GitHub Actions");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }


    public static synchronized void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void flushReport() {
        if (extent != null) extent.flush();
    }
}
