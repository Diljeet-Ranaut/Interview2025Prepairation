package test.data.implementation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.data.implementation.base.BaseTest;
import test.data.implementation.data.TestDataReader;
import test.data.implementation.model.LoginData;
import test.data.implementation.pages.LoginPage;
import test.data.implementation.reports.ExtentReportManager;

import java.io.IOException;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithMultipleUsers() throws IOException, InterruptedException {
        List<LoginData> loginDataList = TestDataReader.getLoginTestData();
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
        for (LoginData loginData : loginDataList) {
            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.getLogin(loginData.getUserName(), loginData.getPassword());
            ExtentReportManager.getTest().info(" Login attempted with: " + loginData.getUserName());
            Thread.sleep(3000);
            getDriver().navigate().back();

        }
    }
    @Test
    public void testLoginOne() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
        ExtentReportManager.getTest().info("Running testLoginOne");
    }

    @Test
    public void testLoginTwo() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
        ExtentReportManager.getTest().info("Running testLoginTwo");
    }

    @Test
    public void testLoginThree() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/");
        ExtentReportManager.getTest().info("Running testLoginThree");
    }
    @Test
    public void testFailureWithScreenshot() throws InterruptedException {
        Thread.sleep(3000);
        getDriver().get("https://opensource-demo.orangehrmlive.com");
        ExtentReportManager.getTest().info("Opening website");
        Thread.sleep(2000);
      //  Assert.fail("Forcing failure to trigger screenshot");
    }
}
