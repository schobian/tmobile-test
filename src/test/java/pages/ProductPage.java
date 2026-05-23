package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public class ProductPage {

    private final WebDriver driver = DriverManager.getDriver();

    private static final By ADD_TO_CART_BUTTON =
            By.xpath("//button[@data-qa='PRD_AddToBasket']");

    private static final By PRODUCT_PRICE =
            By.xpath("(//*[contains(@class,'actualText') and contains(.,'zł')])[1]");

    public void openWithoutSubscriptionPhones() {
        driver.get("https://www.t-mobile.pl/sklep/kategoria/telefony/lista/produkty?hardwareOnlySale=true");
    }

    public void selectProduct(String productName) {
        WebElement productLink = WaitHelper.waitForClickable(
                By.xpath("//a[contains(@aria-label,'" + productName + "')]")
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", productLink);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", productLink);

        WaitHelper.waitForUrlContains("/urzadzenie/");
    }

    public String getProductPrice() {
        return WaitHelper.waitForVisible(PRODUCT_PRICE).getText();
    }

    public void addToCart() {
        WebElement button = WaitHelper.waitForPresent(ADD_TO_CART_BUTTON);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);

        WaitHelper.waitForUrlContains("basket");
    }

    public boolean isWithoutSubscriptionPhonesPageOpened() {

        return driver.getCurrentUrl().contains("hardwareOnlySale=true");
    }

    public boolean isProductPageOpened() {

        return driver.getCurrentUrl().contains("/urzadzenie/");
    }
}