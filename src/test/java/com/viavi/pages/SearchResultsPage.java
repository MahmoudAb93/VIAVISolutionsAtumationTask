package com.viavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {
    private static  WebDriver driver;
    private final WebDriverWait wait;

    private final By PAGE_TITLE = By.xpath("//h1[contains(text(), 'Search results')]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSearchResultsPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_TITLE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnTheFirstProduct(String productName) {
        WebElement firstProduct = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(), '" + productName + "')])[1]"))
        );
        firstProduct.click();
    }

    public static String getFirstProductName(String productName) {
        WebElement element = driver.findElement(By.xpath("(//a[contains(text(), '" + productName + "')])[1]"));
        String fullText = element.getText().trim();
        System.out.println(fullText);

        // Split by space and return the first word
        String firstWord = fullText.split("\\s+")[0];

        return firstWord;
    }
}
