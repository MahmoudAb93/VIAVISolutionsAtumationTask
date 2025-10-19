package com.viavi.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class RegisterNewAccountPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterNewAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By PAGE_TITLE = By.xpath("//h1[contains(text(), 'Create an account')]");

    // Social Title (Gender) - Radio Buttons
    private final By GENDER_MR = By.id("field-id_gender-1");
    private final By GENDER_MRS = By.id("field-id_gender-2");

    // Personal Information Section
    private final By FIRST_NAME = By.id("field-firstname");
    private final By LAST_NAME = By.id("field-lastname");
    private final By EMAIL = By.id("field-email");
    private final By PASSWORD = By.id("field-password");
    private final By BIRTHDATE = By.id("field-birthday");

    // Terms and Conditions / Customer Privacy - Checkboxes
    private final By TERMS_CONDITIONS_CHECKBOX = By.name("psgdpr");
    private final By CUSTOMER_PRIVACY_CHECKBOX = By.name("customer_privacy");

    // Submit Button
    private final By SAVE_BUTTON = By.xpath("//button[@data-link-action='save-customer']");


    public boolean isRegisterPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_TITLE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // Social Title (Gender) Selection Methods
    public void selectGenderMr() {
        WebElement mrButton = driver.findElement(GENDER_MR);
        if (!mrButton.isSelected()) {
            mrButton.click();
        }
    }

    public void selectGenderMrs() {
        WebElement mrsButton = driver.findElement(GENDER_MRS);
        if (!mrsButton.isSelected()) {
            mrsButton.click();
        }
    }
    // Personal Information Methods
    public void enterFirstName(String firstName) {
        WebElement firstNameInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(FIRST_NAME)
        );
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameInput = driver.findElement(LAST_NAME);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(EMAIL);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(PASSWORD);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterBirthdate(String birthdate) {
        WebElement birthdateInput = driver.findElement(BIRTHDATE);
        birthdateInput.clear();
        birthdateInput.sendKeys(birthdate);
    }
    // Checkbox Methods
    public void checkTermsAndConditionsCheckbox() {
        WebElement termsCheckbox = driver.findElement(TERMS_CONDITIONS_CHECKBOX);
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }
    public void checkCustomerPrivacyCheckbox() {
        WebElement customerPrivacy = driver.findElement(CUSTOMER_PRIVACY_CHECKBOX);
        if (!customerPrivacy.isSelected()) {
            customerPrivacy.click();
        }
    }
    // Submit Button Method
    public void clickSaveButton() {
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(SAVE_BUTTON)
        );
        saveButton.click();
    }
}
