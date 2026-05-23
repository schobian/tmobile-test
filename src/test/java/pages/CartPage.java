package pages;

import driver.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitHelper;

public class CartPage {

    private final WebDriver driver = DriverManager.getDriver();

    private static final By CART_TITLE =
            By.xpath("//*[contains(.,'Twój koszyk')]");

    private static final By CART_PRICE =
            By.xpath("//*[@data-qa='BKT_Activation']");

    private static final By CART_BUTTON =
            By.xpath("//button[contains(@data-url,'basket')]");

    public void openCart() {

        WebElement cartButton = WaitHelper.waitForClickable(CART_BUTTON);

        cartButton.click();

        WaitHelper.waitForUrlContains("basket");
    }

    public void verifyCartPageIsVisible() {

        WaitHelper.waitForUrlContains("basket");

        WaitHelper.waitForVisible(CART_TITLE);
    }

    public void verifyProductExists(String productName) {

        WaitHelper.waitForVisible(
                By.xpath("//*[contains(.,'" + productName + "')]")
        );
    }

    public void verifyPrice(String expectedPrice) {

        WebElement cartPrice = WaitHelper.waitForVisible(CART_PRICE);

        String actualPrice = cartPrice.getText();

        Assert.assertEquals(
                "Cena w koszyku nie zgadza się z ceną ze strony produktu",
                expectedPrice,
                actualPrice
        );
    }
    public boolean isCartPageOpened() {

        return driver.getCurrentUrl().contains("basket");
    }

    public boolean isProductVisible(String productName) {

        return WaitHelper.waitForVisible(
                By.xpath("//*[contains(.,'" + productName + "')]")
        ).isDisplayed();
    }
    public String getCartPrice() {

        return WaitHelper.waitForVisible(CART_PRICE).getText();
    }
}