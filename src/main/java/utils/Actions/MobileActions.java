package utils.Actions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileActions {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MobileActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By locator) {
        if (isElementVisible(locator)) {
            WebElement element = driver.findElement(locator);
            if (element.isEnabled() && element.isDisplayed()) {
                element.click();
            } else {
                scrollAndClick(locator);
            }
        } else {
            scrollAndClick(locator);
        }
    }

    public void type(By locator, String text) {
        if (isElementVisible(locator)) {
            WebElement element = driver.findElement(locator);
            if (element.isEnabled() && element.isDisplayed()) {
                element.sendKeys(text);
            } else {
                scrollAndType(locator, text);
            }
        } else {
            scrollAndType(locator, text);
        }
    }

    public void select(By locator) {
        if (isElementVisible(locator)) {
            WebElement element = driver.findElement(locator);
            if (element.isEnabled() && element.isDisplayed()) {
                element.click();
            } else {
                scrollAndClick(locator);
            }
        } else {
            scrollAndClick(locator);
        }
    }

    // Method to scroll and click on an element
    private void scrollAndClick(By locator) {
        if (driver.findElements(locator).isEmpty()) return;

        scroll(locator);
        WebElement element = driver.findElement(locator);
        if (element.isEnabled() && element.isDisplayed()) {
            element.click();
        }
    }

    private void scrollAndType(By locator, String text) {
        if (driver.findElements(locator).isEmpty()) return;

        scroll(locator);
        WebElement element = driver.findElement(locator);
        if (element.isEnabled() && element.isDisplayed()) {
            element.sendKeys(text);
        }
    }

    private boolean isElementVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scroll(By locator) {
        WebElement dropdown = driver.findElement(locator); // Adjust the locator for your dropdown

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Coordinates for the swipe
        int startX = dropdown.getLocation().getX();
        int startY = dropdown.getLocation().getY() + dropdown.getSize().getHeight() - 10;  // Start near the bottom
        int endX = startX;
        int endY = dropdown.getLocation().getY() + 10;  // End near the top

        // JavaScript swipe (or drag) command
        js.executeScript("mobile: swipe", ImmutableMap.of(
                "direction", "up", // swipe direction
                "startX", startX,
                "startY", startY,
                "endX", endX,
                "endY", endY,
                "duration", 800  // Duration for the swipe action
        ));
    }
    public void scrollDownToSpecificTextContained(String text) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)" + ".instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }



}
