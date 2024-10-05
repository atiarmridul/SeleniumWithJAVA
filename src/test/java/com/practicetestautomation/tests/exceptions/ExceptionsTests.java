package com.practicetestautomation.tests.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {

    private WebDriver driver;
    private Logger logger;

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
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

//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void noSuchElementExceptionTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click Add button
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();

        // Verify Row 2 input field is displayed
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        Assert.assertTrue(row2InputField.isDisplayed(), "The text field is not displayed");

    }

    @Test
    public void timeoutExceptionTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Click Add button
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();

        // Verify Row 2 input field is displayed
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        Assert.assertTrue(row2InputField.isDisplayed(), "The text field is not displayed");

    }

    @Test
    public void elementNotInteractableExceptionTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click Add button
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();

        // Verify Row 2 input field is displayed
        WebElement row2InputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        //Type text into the second input field
        row2InputField.sendKeys("Test");
        //Push Save button using locator By.name(“Save”)

        //WebElement saveBtn = driver.findElement(By.name("Save"));
        WebElement saveBtn = driver.findElement(By.xpath("//div[@id=\"row2\"]//button[@name=\"Save\"]"));


        saveBtn.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String expectedMessage = "Row 2 was saved";
        String actualMessage = successMessage.getText();

        //Verify text saved
        Assert.assertEquals(actualMessage, expectedMessage, "Asserting failed beacuse the text is not saved");


    }
    @Test
    public void staleElementReferenceExceptionTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement instructionsText = driver.findElement(By.id("instructions"));
        // Click Add button
        WebElement addBtn = driver.findElement(By.id("add_btn"));
        addBtn.click();
        //Verify instruction text element is no longer displayed
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))),"Instruction Text is still displayed ");
    }

}
