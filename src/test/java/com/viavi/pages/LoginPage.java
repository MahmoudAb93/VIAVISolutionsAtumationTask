package com.viavi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriverWait wait;

    private final By REGISTER_LINK = By.xpath("//a[contains(text(), 'No account? Create one here')]");
    private final By PAGE_TITLE = By.xpath("//h1[contains(text(), 'Log in to your account')]");

    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(PAGE_TITLE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRegisterLink() {
        WebElement registerLink = wait.until(
                ExpectedConditions.elementToBeClickable(REGISTER_LINK)
        );
        registerLink.click();
    }

}
