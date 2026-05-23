package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import pages.HomePage;
import pages.ProductPage;
import pages.CartPage;

public class PhonePurchaseSteps {

    private final HomePage homePage = new HomePage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cartPage = new CartPage();

    private String productName;
    private String productPrice;
    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("użytkownik otwiera przeglądarkę")
    public void userOpensBrowser() {
        homePage.openBlankPage();
    }

    @Then("przeglądarka jest uruchomiona")
    public void browserIsStarted() {

        Assert.assertTrue(
                "Browser was not opened on blank page",
                homePage.isBlankPageOpened()
        );
    }

    @When("użytkownik przechodzi na stronę {string}")
    public void userNavigatesToPage(String url) {
        homePage.open(url);
    }

    @Then("strona główna T-Mobile jest widoczna")
    public void homePageIsVisible() {

        Assert.assertTrue(
                "T-Mobile home page is not visible",
                homePage.isPageOpened("t-mobile.pl")
        );
    }

    @When("użytkownik wybiera {string} z górnej belki")
    public void userSelectsFromTopMenu(String menuOption) {

        Assert.assertEquals(
                "Unsupported top menu option",
                "Sklep",
                menuOption
        );

        homePage.openShopFromTopMenu();
    }

    @Then("widoczna jest rozwijana lista")
    public void dropdownListIsVisible() {

        Assert.assertTrue(
                "Shop dropdown is not visible",
                homePage.isShopDropdownVisible()
        );
    }

    @When("użytkownik klika {string} w sekcji {string}")
    public void userClicksOptionInSection(String option, String section) {

        Assert.assertEquals(
                "Unsupported option",
                "Bez abonamentu",
                option
        );

        Assert.assertEquals(
                "Unsupported section",
                "Smartfony",
                section
        );

        productPage.openWithoutSubscriptionPhones();
    }

    @Then("widoczna jest lista smartfonów")
    public void smartphonesListIsVisible() {

        Assert.assertTrue(
                "Phones without subscription page is not visible",
                productPage.isWithoutSubscriptionPhonesPageOpened()
        );
    }

    @When("użytkownik klika urządzenie o nazwie {string}")
    public void userClicksDeviceByName(String productName) {
        this.productName = productName;
        scenario.attach(
                "Selected product: " + productName,
                "text/plain",
                "Selected product"
        );
        productPage.selectProduct(productName);
    }

    @Then("widoczna jest strona produktu")
    public void productPageIsVisible() {

        Assert.assertTrue(
                "Product page is not visible",
                productPage.isProductPageOpened()
        );
    }

    @And("zapamiętana zostaje cena urządzenia ze strony produktu")
    public void productPriceIsStored() {

        productPrice = productPage.getProductPrice();
        scenario.attach(
                "Product price from product page: " + productPrice,
                "text/plain",
                "Product price"
        );

        Assert.assertNotNull(
                "Product price was not stored",
                productPrice
        );

        Assert.assertFalse(
                "Product price is empty",
                productPrice.isEmpty()
        );
    }

    @When("użytkownik klika {string}")
    public void userClicksButton(String buttonName) {

        if (buttonName.equals("Dodaj do koszyka")) {

            productPage.addToCart();

            Assert.assertTrue(
                    "Cart page was not opened after adding product",
                    cartPage.isCartPageOpened()
            );

        } else if (buttonName.equals("Koszyk")) {

            cartPage.openCart();

            Assert.assertTrue(
                    "Cart page was not opened",
                    cartPage.isCartPageOpened()
            );

        } else {
            Assert.fail("Unsupported button name: " + buttonName);
        }
    }

    @Then("widoczna jest strona {string}")
    public void pageIsVisible(String pageName) {

        if (pageName.equals("Twój koszyk")) {

            Assert.assertTrue(
                    "Cart page is not visible",
                    cartPage.isCartPageOpened()
            );
        }
    }

    @And("cena urządzenia w koszyku zgadza się z ceną ze strony produktu")
    public void cartPriceMatchesProductPrice() {

        String cartPrice = cartPage.getCartPrice();

        Assert.assertEquals(
                "Cart price is different than product page price",
                productPrice,
                cartPrice
        );

        scenario.attach(
                "Product page price: " + productPrice + "\nCart price: " + cartPrice,
                "text/plain",
                "Price comparison"
        );
    }

    @When("użytkownik przechodzi na stronę główną T-Mobile")
    public void userGoesToHomePage() {

        homePage.goToHomePage();

        Assert.assertTrue(
                "Home page was not opened",
                homePage.isPageOpened("t-mobile.pl")
        );
    }

    @And("koszyk zawiera urządzenie {string}")
    public void cartContainsDevice(String productName) {

        Assert.assertTrue(
                "Product is not visible in cart: " + productName,
                cartPage.isProductVisible(productName)
        );
    }
}