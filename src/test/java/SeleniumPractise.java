import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

public class SeleniumPractise {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        DesiredCapabilities handleSSl = new DesiredCapabilities();
        //  handleSSl.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        // Step 2: Launch Chrome browser
        driver = new ChromeDriver();
       driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void alertCheckAndDropdownCheck() {
        Alert al = driver.switchTo().alert();
        al.accept();
        al.dismiss();
        al.getText();
        al.sendKeys("");

        Select sl = new Select(driver.findElement(By.id("")));
        sl.selectByIndex(1);
        sl.selectByVisibleText("");
        sl.selectByValue("");
        sl.selectByContainsVisibleText("");
    }

    //  @Test(priority = 0)
    public void loginWithWait() throws InterruptedException, IOException {

        System.out.println("Page Title Bro: " + driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        element.sendKeys("Admin");

        Thread.sleep(5000);
        Wait<WebDriver> waitF = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        WebElement Passw = waitF.until(new Function<WebDriver, WebElement>() {

                                           @Override
                                           public WebElement apply(WebDriver driver) {
                                               WebElement ele = driver.findElement(By.cssSelector("input[name=\"password\"]"));
                                               if (ele.isDisplayed()) {
                                                   return ele;
                                               } else {

                                                   return null;
                                               }
                                           }

                                       }

        );

        Actions action = new Actions(driver);
        action.sendKeys(Keys.F5);

        Passw.sendKeys("admin123");

        Wait<WebDriver> ww = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        WebElement submit = ww.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {

                WebElement s = driver.findElement(By.cssSelector("button[type=\"submit\"]"));

                if (s.isDisplayed()) {
                    return s;
                } else {

                    return null;
                }

            }
        });

        submit.click();
        System.out.println("Test Pass");
        Thread.sleep(2000);

    }

    //  @Test(priority = 4)
    public void openNewTabs() throws InterruptedException {
        Thread.sleep(2000);
        String admin = driver.findElement(By.linkText("Admin")).getAttribute("href");
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", admin);
        Thread.sleep(5000);
        String parent = driver.getWindowHandle();
        // driver.switchTo().window(parent);
        Thread.sleep(2000);
        String pim = driver.findElement(By.linkText("PIM")).getAttribute("href");
        System.out.println(" URL print: " + pim);
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", pim);
        Thread.sleep(2000);
    }

    // @Test(priority = 2)
    public static void windowHandleCheck() throws InterruptedException {

        String parent = driver.getWindowHandle();

        Set<String> allwindows = driver.getWindowHandles();
        for (String handle : allwindows) {

            String pageTitle = driver.switchTo().window(handle).getTitle();
            System.out.println(" Check Broom:" + pageTitle);
        }
        driver.switchTo().window(parent);
        Thread.sleep(5000);

    }

    // @Test(priority = 1)
    public static void screenShot() throws IOException, InterruptedException {
        fiveSecWait();
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile, new File("Screenshots/scr.jpeg"));


    }

    // @Test(priority = 0)
    public void mouseActions() throws InterruptedException {
        driver.get("https://vinothqaacademy.com/mouse-event/?utm_source=chatgpt.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Actions actions = new Actions(driver);
        fiveSecWait();
        WebElement Drag = driver.findElement(By.cssSelector("#draggableElement"));
        fiveSecWait();
        WebElement Drop = driver.findElement(By.cssSelector("#droppableElement"));
        fiveSecWait();

        actions.dragAndDrop(Drag, Drop).build().perform();
        fiveSecWait();
        WebElement db = driver.findElement(By.cssSelector("#dblclick"));
        actions.scrollToElement(db);
        fiveSecWait();
        actions.doubleClick(db).build().perform();
        fiveSecWait();
        WebElement textBox = driver.findElement(By.cssSelector("#textbox"));
        actions.scrollToElement(textBox);
        System.out.println("toolTip:" + textBox.getAttribute("title"));

        fiveSecWait();
        actions.moveToElement(textBox).pause(Duration.ofSeconds(3)).build().perform();
        fiveSecWait();
        WebElement rightClick = driver.findElement(By.cssSelector("#rightclick"));
        actions.scrollToElement(rightClick);
        fiveSecWait();
        actions.contextClick(rightClick).build().perform();

        fiveSecWait();
        System.out.println(" Mouse Passed bro");

    }

    //  @Test(priority = 0, dataProvider = "myData")
    public void dataCheck(String k, String v) {
        System.out.println("My Key:" + k + " MyValue:" + v);
    }

    //   @Test(priority = 1)
    public void brokenLinks() throws InterruptedException {
        Thread.sleep(500);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {

            String url = link.getAttribute("href");
            if (url == null || url.isEmpty() || url.startsWith("javascript")) {
                System.out.println("Skipping invalid and unsupported URLs" + url);
                continue;
            }
            try {
                Response response = RestAssured.given().relaxedHTTPSValidation().head(url);
                int statusCode = response.statusCode();
                if (statusCode >= 400) {
                    System.out.println("Broken link:" + url + " Status code:" + statusCode);
                } else {
                    System.out.println("Valid link :" + url + " Status code:" + statusCode);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test(priority = 0)
    public void simpleShadowDom() throws InterruptedException {
        driver.get("file:///C:/Users/dilje/Downloads/simpleShodowDom.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement shadowHost = driver.findElement(By.tagName("my-element"));
        fiveSecWait();
       WebElement input = (WebElement) ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot.querySelector('#shadow-input')",shadowHost);

            input.sendKeys(" hello shadow root ");
        fiveSecWait();
        driver.quit();
    }

    //@Test(priority = 0)
    public void shadowDom() {

        driver.get("https://shop.polymer-project.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement searchIcon = nestedShadowroot(driver,
                "shop-app",              // Shadow Host 1
                "app-header",            // Shadow Host 2
                "app-toolbar",           // Shadow Host 3
                "div.logo"         // Target inside nested shadow DOM
        );
        // Perform actions
        System.out.println("Text content: " + searchIcon.getText());

        driver.quit();
    }

    public WebElement nestedShadowroot(WebDriver driver, String... selectors) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector(selectors[0]));

        for (int i = 1; i < selectors.length; i++) {
            element = (WebElement) js.executeScript(
                    "return arguments[0].shadowRoot.querySelector(arguments[1])",
                    element, selectors[i]);
        }
        return element;
    }

    @DataProvider(name = "myData")
    public String[][] data() {
        return new String[][]{{"Key1", "Val1"}, {"Key2", "val2"}};
    }

    @AfterClass
    public void teardown() {
        driver.quit();

    }

    public static void fiveSecWait() throws InterruptedException {
        Thread.sleep(5000);

    }

}
