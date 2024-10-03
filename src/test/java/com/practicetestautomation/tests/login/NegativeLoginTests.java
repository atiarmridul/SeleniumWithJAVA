package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTests {

    @Test
    public void incorrectUsernameTest() {
        //Open page
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Type username incorrectUser into Username field
        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys("student123");

        //Type password Password123 into Password field
        WebElement userPasswordField = driver.findElement(By.id("password"));
        userPasswordField.sendKeys("Password123");

        //Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.isDisplayed();
        submitButton.click();

        //Verify error message is displayed
        WebElement errorMessageBox = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessageBox.isDisplayed());

        //Verify error message text is Your username is invalid!
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessageBox.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


        driver.quit();

    }

    @Test
    public void incorrectPasswordTest() {
        //Open page
        WebDriver driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Type username incorrectUser into Username field
        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys("student");

        //Type password Password123 into Password field
        WebElement userPasswordField = driver.findElement(By.id("password"));
        userPasswordField.sendKeys("Password123123");

        //Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.isDisplayed();
        submitButton.click();

        //Verify error message is displayed
        WebElement errorMessageBox = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessageBox.isDisplayed());

        //Verify error message text is Your username is invalid!
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessageBox.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


        driver.quit();

    }

}
