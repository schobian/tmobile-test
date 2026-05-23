package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitHelper;

public class HomePage {

    private final WebDriver driver = DriverManager.getDriver();

    private static final By SHOP_BUTTON =
            By.xpath("//*[contains(text(),'Sklep')]");

    private static final By SHOP_DROPDOWN =
            By.cssSelector(".ODSGlobalHeaderMegaMenu");

    public void open(String url) {
        driver.get(url);
    }

    public void openBlankPage() {
        driver.get("about:blank");
    }

    public void acceptCookiesIfVisible() {
        try {
            WaitHelper.waitForClickable(
                    By.xpath("//button[contains(.,'Akceptuj')]")
            ).click();
        } catch (Exception e) {
        }
    }

    public void openShopFromTopMenu() {
        acceptCookiesIfVisible();
        WaitHelper.waitForClickable(SHOP_BUTTON).click();
    }

    public void goToHomePage() {

        driver.get("https://www.t-mobile.pl");
    }
    public boolean isBlankPageOpened() {

        return driver.getCurrentUrl().equals("about:blank");
    }

    public boolean isPageOpened(String url) {

        return driver.getCurrentUrl().contains(url);
    }

    public boolean isShopDropdownVisible() {

        try {
            return WaitHelper.waitForVisible(SHOP_DROPDOWN).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}