package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {

    private WebDriver driver;
    private Logger logger;

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.warning("Configuration for browser" + browser + "is missing so running in chrome");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-login/");

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test(groups = {"positive", "smoke", "regression"})
    public void testLoginFunctionality() {
        logger.info("Starting logging functionality");
        WebElement userNameField = driver.findElement(By.id("username"));
        logger.info("Type Username");
        userNameField.sendKeys("student");

        WebElement userPasswordField = driver.findElement(By.id("password"));
        logger.info("Type Password");
        userPasswordField.sendKeys("Password123");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.isDisplayed();
        logger.info("Click Submit button");
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Verify the logging functionality");
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String expectedMsg = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMsg));

        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutButton.isDisplayed());
    }


    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negative logging test");

        // Type username incorrectUser into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        logger.info("Type Username " + username);
        usernameInput.sendKeys(username);

        // Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Type Password " + password);
        passwordInput.sendKeys(password);

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        logger.info("Click Submit button");

        submitButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Verify the expected error message " + expectedErrorMessage);
        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");

        // Verify error message text is Your username is invalid!
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

}
