package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private static final int TIMEOUT = 30;

    public static WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static void waitForUrlContains(String text) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );

        wait.until(
                ExpectedConditions.urlContains(text)
        );
    }

    public static void waitForBody() {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("body"))
        );
    }

    public static WebElement waitForPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(TIMEOUT)
        );

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }
}