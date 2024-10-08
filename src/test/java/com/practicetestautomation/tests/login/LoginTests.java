package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test(groups = {"positive", "smoke", "regression"})
    public void testLoginFunctionality() {
        logger.info("Starting logging functionality");
        LoginPage loginpage = new LoginPage(driver);
        loginpage.visit();
        SuccessfulLoginPage successfulLoginPage = loginpage.executeLogin("student", "Password123");
        successfulLoginPage.load();
        logger.info("Verify the logging functionality");
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = successfulLoginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        String expectedMsg = "Congratulations student. You successfully logged in!";
        String pageSource = successfulLoginPage.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMsg));
        Assert.assertTrue(successfulLoginPage.isLogoutButtonDisplayed());
    }


    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negative logging functionality");
        LoginPage loginpage = new LoginPage(driver);
        loginpage.visit();
        loginpage.executeLogin(username, password);
        loginpage.getErrorMessage();
        Assert.assertEquals(loginpage.getErrorMessage(), expectedErrorMessage);

    }


}
