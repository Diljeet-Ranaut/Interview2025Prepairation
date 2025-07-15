package mobile.testing.seprateimplementation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class AndroidLoginTest extends DriverFactory {
    @Test
    public void loginWithValidCredentials() {
        WebElement user = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"));
        WebElement passwordField = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"));
        WebElement loginBtn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"LOGIN\"]"));


        user.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginBtn.click();

        WebElement home = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView"));
        assert home.isDisplayed();
    }

}
