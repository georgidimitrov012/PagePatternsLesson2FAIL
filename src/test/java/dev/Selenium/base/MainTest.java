package dev.Selenium.base;

import dev.selenium.driver.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class MainTest {
    public WebDriver driver;
    private String url;
    private int implicitWait;
    private String browser;

    @BeforeMethod
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
                driver = DriverFactory.getChromeDriver(implicitWait);
                break;
            case "firefox":
                driver = DriverFactory.getFireFoxDriver(implicitWait);
                break;
            default:
                throw new IllegalStateException("Unsupported browser type!");
        }
    }

    private void loadUrl() {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver driver =
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
            String fileName = result.getName() + "_" + timestamp + ".png";

            try {
                FileUtils.copyFile(source, new File("./Screenshots/" + fileName));
                System.out.println("Screenshot taken: " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //driver.quit();
    }
}
