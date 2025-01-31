package setup;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;
import utils.DataReader.PropertiesReader;
import io.qameta.allure.Step;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Setup {

    WebDriver driver;
    PropertiesReader propertiesReader = new PropertiesReader("src/main/resources/Properties/MobileCapabilities.properties");

    public Setup(WebDriver driver) {
        this.driver = driver;
    }

    @Step("setup the Device")
    public WebDriver setupDevice(String platform) {
        String appPath = System.getProperty("user.dir") +propertiesReader.getProperty("androidAppPath");
        String appiumServerURL = propertiesReader.getProperty("appiumServerURL");

        if (platform.equalsIgnoreCase(propertiesReader.getProperty("androidPlatform"))) {
            UiAutomator2Options androidOptions = new UiAutomator2Options();
            androidOptions.setApp(appPath);
            androidOptions.setAppPackage(propertiesReader.getProperty("appPackage"));
            androidOptions.setAppActivity(propertiesReader.getProperty("appActivity"));
            androidOptions.setAutomationName(propertiesReader.getProperty("androidAutomationName"));
            androidOptions.setDeviceName(propertiesReader.getProperty("deviceName"));
            androidOptions.setPlatformName(propertiesReader.getProperty("androidPlatform"));
            androidOptions.setPlatformVersion(propertiesReader.getProperty("androidPlatformVersion"));
            androidOptions.setAppWaitActivity(propertiesReader.getProperty("appActivity"));

            try {
                driver = new AndroidDriver(new URL(appiumServerURL), androidOptions);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while setting up Android Driver: " + e.getMessage(), e);
            }

        } else if (platform.equalsIgnoreCase(propertiesReader.getProperty("iosPlatform"))) {
            XCUITestOptions iosOptions = new XCUITestOptions();
            iosOptions.setApp(propertiesReader.getProperty("iosAppPath"));
            iosOptions.setAutomationName(propertiesReader.getProperty("iosAutomationName"));
            iosOptions.setDeviceName(propertiesReader.getProperty("iOSDeviceName"));
            iosOptions.setPlatformName(propertiesReader.getProperty("iosPlatform"));
            iosOptions.setPlatformVersion(propertiesReader.getProperty("iosPlatformVersion"));
            try {
                driver = new io.appium.java_client.ios.IOSDriver(new URL(appiumServerURL), iosOptions);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while setting up iOS Driver: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }
}