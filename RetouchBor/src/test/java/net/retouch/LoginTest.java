package net.retouch;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private static WebDriver driver;

    private  static final String START_URL = "https://retouched.net/orders/new";
    private  static final String LOGIN = "retouched.net@gmail.com";
    private  static final String PASSWORD = "wsxqaz";
    private  static final String LOGIN_L = "//input[@name='user[email]']";
    private  static final String PASSWORD_L = "//input[@name='user[password]']";
    private  static final String LOGIN_BUTTON = "//input[@name='commit']";
    private  static final String ALERT_SUCCESS_CAPTION = "//body/p";

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @Test
    public void verifySuccessCaption () {

        driver.navigate().to(START_URL);
        driver.findElement(By.xpath(LOGIN_L)).sendKeys(LOGIN);
        driver.findElement(By.xpath(PASSWORD_L)).sendKeys(PASSWORD);
        driver.findElement(By.xpath(LOGIN_BUTTON)).click();
        String TEXT_ALERT_SUCCESS_CAPTION = driver.findElement(By.xpath(ALERT_SUCCESS_CAPTION)).getText();
        assertEquals("Вход в систему выполнен.", TEXT_ALERT_SUCCESS_CAPTION);
    }

    @After
    public void tearDown() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("Screenshot", "TestResult.png"));
        } catch (IOException e) {

        }
        driver.quit();
    }

}
