package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionsPage extends BasePage {


    private By addButtonLocator = By.id("add_btn");
    private By row2InputField = By.xpath("//div[@id='row2']/input");
    private By row2SaveButton = By.xpath("//div[@id='row2']/button[@name='Save']");
    private By successMessage = By.id("confirmation");


    public ExceptionsPage(WebDriver driver) {
        super(driver);
    }

    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void pushAddButton(){
        driver.findElement(addButtonLocator).click();
    }

    public boolean isRowTwoDisplayedAfterWait(){
        return waitForDisplayed(row2InputField);
    }

    public void enterTextInRow2(String demotext){
        driver.findElement(row2InputField).sendKeys(demotext);
    }

    public void saveRow2(){
        driver.findElement(row2SaveButton).click();
    }

    public String getSuccessMessage() {
        WebElement message = waitForElement(successMessage);
        return message.getText();
    }
}
