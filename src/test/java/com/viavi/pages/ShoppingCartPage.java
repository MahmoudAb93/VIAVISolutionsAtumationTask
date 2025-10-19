package com.viavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {
    private static  WebDriver driver;
    private final WebDriverWait wait;

    private final By PAGE_TITLE = By.xpath("//h1[contains(text(), 'Shopping Cart')]");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public boolean isShoppingCartPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_TITLE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductAddedToCart(String selectedProductName) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), '" + selectedProductName + "')]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
