package com.viavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectedProductPage {
    private static  WebDriver driver;
    private final WebDriverWait wait;

    private final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-button-action='add-to-cart']");
    private final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//div[@class='cart-content-btn']//a[contains(text(), 'Proceed to checkout')]");

    public SelectedProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSelectedProductPageDisplayed(String selectedProductName) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), '" + selectedProductName + "')]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isSelectedProductImageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-cover']//picture//img")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void clickOnAddToCart() {
        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON));
        addToCartButton.click();
    }
    public void clickOnProceedToCheckout() {
        WebElement proceedToCheckoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(PROCEED_TO_CHECKOUT_BUTTON));
        proceedToCheckoutButton.click();
    }


}
