package com.viavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By SIGN_IN_LINK = By.xpath("//span[contains(text(), 'Sign in')]/ancestor::a");
    private final By SEARCH_INPUT = By.xpath("//div[@id='search_widget']//input[@type='text']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPrestaShop() {
        driver.navigate().to("https://demo.prestashop.com/");
        driver.manage().window().maximize();
        driver.switchTo().frame(0);
    }

    public void searchProduct(String productName) {
        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(SEARCH_INPUT)
        );
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void clickSignInLink() {
        WebElement signInButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(SIGN_IN_LINK)
        );
        signInButton.click();
    }
    public boolean isUserLoggedIn(String firstName) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), '" + firstName + "')]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
