package test.data.implementation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    @FindBy(xpath = "//input[@name='username']")
    private WebElement userName;
    @FindBy(css = "input[name=\"password\"]")
    private WebElement password;
    @FindBy(css = "button[type=\"submit\"]")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getLogin(String user, String pass) {
        userName.clear();
        userName.sendKeys(user);
        password.clear();
        password.sendKeys(pass);
        submit.click();
    }

}
