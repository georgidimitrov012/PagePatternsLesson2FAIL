package dev.selenium.driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void getChromeDriver(int wait) {
        Map<String, String> mobileEmulation = Map.of("deviceName", "Nexus 5");




        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

        tlDriver.set(driver);
        //return driver;
    }

  public static void getFireFoxDriver(int wait) {
      //FirefoxOptions options = new FirefoxOptions();
       WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
      return driver;
       //tlDriver.set(driver);
   }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
