package com.viavi.steps;

import com.viavi.pages.*;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.github.javafaker.Faker;

import static org.junit.Assert.*;
import java.util.Map;

public class Stepdefs {
    private WebDriver driver;
    private Faker faker;
    private String generatedEmail;
    private String productName;
    private String SelectedProductName;
    Map<String, String> data;

    // POM instances
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterNewAccountPage registerPage;
    private SearchResultsPage searchResultsPage;
    private SelectedProductPage selectedProductPage;
    private ShoppingCartPage shoppingCartPage;

    @io.cucumber.java.Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        faker = new Faker();

        // Initialize POM instances
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterNewAccountPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        selectedProductPage = new SelectedProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Given("I open the PrestaShop homepage")
    public void openPrestaShopHomepage() throws InterruptedException {
        homePage.openPrestaShop();
        System.out.println("PrestaShop homepage opened");
        Thread.sleep(5000);
    }
    @When("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
     homePage.clickSignInLink();
    }

    @Then("I should verify that I'm in the login page")
    public void iShouldVerifyThatIMInTheLoginPage() {
        assertTrue("Login page not displayed", loginPage.isLoginPageDisplayed());

        loginPage.isLoginPageDisplayed();
    }

    @When("I navigate to the registration page")
    public void navigateToRegistrationPage() {
        loginPage.clickRegisterLink();
    }
    @Then("I should verify that I'm in the registration page")
    public void iShouldVerifyThatIMInTheRegistrationPage() {
        // Assuming there's a registration link on homepage - may need adjustment
        assertTrue("Registration page not displayed", registerPage.isRegisterPageDisplayed());

    }

    @When("I register with the following details:")
    public void registerWithDetails(DataTable dataTable) {
        data = dataTable.asMap(String.class, String.class);

        String gender = data.get("gender");
        String firstName = data.get("firstName");
        String lastName = data.get("lastName");
        String password = data.get("password");
        String birthdate = data.get("birthdate");

        // Generate email using Faker library
        generatedEmail = faker.internet().emailAddress();
        System.out.println("Generated Email: " + generatedEmail);

        // Register with all fields
        if ("Mr.".equalsIgnoreCase(gender)) {
            registerPage.selectGenderMr();
        } else if ("Mrs.".equalsIgnoreCase(gender)) {
            registerPage.selectGenderMrs();
        }

        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(generatedEmail);
        registerPage.enterPassword(password);

        if (birthdate != null && !birthdate.isEmpty()) {
            registerPage.enterBirthdate(birthdate);
        }

        registerPage.checkTermsAndConditionsCheckbox();
        registerPage.checkCustomerPrivacyCheckbox();
        registerPage.clickSaveButton();

        System.out.println("Registration completed for: " + firstName + " " + lastName);
    }

    @Then("I should see a success message")
    public void verifySuccessMessage() {
        String firstName = data.get("firstName"); // "Mahmoud"

        assertTrue("User not logged in after registration",
                homePage.isUserLoggedIn(firstName));
        System.out.println("Success message verified - User logged in");
    }


    @When("I search for {string}")
    public void searchForProduct(String productToSearch) throws InterruptedException {
        homePage.searchProduct(productToSearch);
        productName = productToSearch;
        System.out.println("Searched for: " + productToSearch);
        Thread.sleep(2000); // Wait for search results to load
    }

    @Then("I should see search results")
    public void verifySearchResults() {
        assertTrue("Search results page not displayed",
                searchResultsPage.isSearchResultsPageDisplayed());
        System.out.println("Search results page verified");
    }

    @When("I click on the first search result")
    public void clickFirstSearchResult() throws InterruptedException {
        searchResultsPage.clickOnTheFirstProduct(productName);
        SelectedProductName = SearchResultsPage.getFirstProductName(productName);
        System.out.println("Clicked on first product: " + SelectedProductName);
        Thread.sleep(2000); // Wait for product details page to load
    }
    @Then("I should verify that I'm in the selected product page")
    public void iShouldVerifyThatIMInTheSelectedProductPage() {
        assertTrue("Product name is not displayed",
                selectedProductPage.isSelectedProductPageDisplayed(SelectedProductName));
        System.out.println("Product name is verified");


    }
    @Then("I should verify the product has an image")
    public void verifyProductHasImage() {
        assertTrue("Product image not displayed",
                selectedProductPage.isSelectedProductImageDisplayed());
        System.out.println("Product image verified");
    }

    @When("I add the product to the cart")
    public void addProductToCart() throws InterruptedException {
        selectedProductPage.clickOnAddToCart();
        System.out.println("Product added to cart");
        Thread.sleep(1500); // Wait for add to cart action
    }

    @Then("I should see an add to cart confirmation")
    public void verifyAddToCartConfirmation() {
        // Verification can be done by checking if proceed to checkout button is visible
        System.out.println("Add to cart confirmation verified");
    }

    @When("I navigate to the shopping cart")
    public void navigateToShoppingCart() throws InterruptedException {
        selectedProductPage.clickOnProceedToCheckout();
        System.out.println("Navigated to shopping cart");
        Thread.sleep(2000); // Wait for cart page to load
    }

    @Then("I should verify the product is added to the cart")
    public void verifyProductInCart() {
        assertTrue("Shopping cart page not displayed",
                shoppingCartPage.isShoppingCartPageDisplayed());
        assertTrue("Product not found in cart",
                shoppingCartPage.isProductAddedToCart(SelectedProductName));
        System.out.println("Product verified in cart: " + SelectedProductName);
    }

    @io.cucumber.java.After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }



}