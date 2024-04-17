package dev.Selenium.steps;

import dev.selenium.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestHook {
    public WebDriver driver;
    private String url;
    private int implicitWait;
    private String browser;

    @Before
    public void setUp() {
        /*driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));*/
        setupBrowserDriver();
        loadUrl();
    }

    private void setupBrowserDriver() {
        try (FileInputStream configFile = new FileInputStream("src/test/java/config.properties")){
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (browser) {
            case "chrome":
                DriverFactory.getChromeDriver(implicitWait);
                break;
            /*case "firefox":
                driver = DriverFactory.getFireFoxDriver(implicitWait);
                break;*/
            default:
                throw new IllegalStateException("Unsupported browser type!");
        }
    }

    private void loadUrl() {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
    }


    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","Screenshot_" + new SimpleDateFormat("yyyy_MM_DD__hh_mm_ss").format(new Date()));
        }

        driver.quit();
    }
}
